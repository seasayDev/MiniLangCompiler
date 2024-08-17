package inf5153.miniLang.visitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import inf5153.miniLang.ast.*;

/*
 * La classe GeneratorJavaVisitor implémente le patron de conception Visiteur pour
 * parcourir l'AST et générer du code Java équivalent.
 */
public class GeneratorJavaVisitor implements AstVisitor<String> {

	private Map<String, String> variables = new HashMap<>();
	private StringBuilder output = new StringBuilder();
	private StringBuilder output2 = new StringBuilder();
	private String className;
	private int indentation = 2;

	/**
	 * Constructeur de la classe GeneratorJavaVisitor.
	 */
	public GeneratorJavaVisitor(String fileName) {
		this.className = "Gen" + fileName.substring(0, fileName.lastIndexOf('.'));
	}

	/**
	 * Visite un bloc de code (une liste d'instructions).
	 */
	@Override
	public String visitBlock(Block block) {
		for (Statement statement : block) {
			statement.accept(this);
		}
		return null;
	}

	/**
	 * Visite l'unité de compilation principale et génère la structure de la classe
	 * Java.
	 */
	@Override
	public String visitCompilationUnit(CompilationUnit compilationUnit) {
		output.append("package inf5153.miniLang.javaGeneration;\n");
		output.append("public class ").append(className).append(" {\n");
		output.append("\tpublic static void main(String[] args) {\n");
		compilationUnit.getBlock().accept(this);
		output.append("\n").append(output2);
		output.append("\t}\n");
		output.append("}\n");
		ecrireFichier();
		return null;
	}

	/**
	 * Visite une expression binaire générique.
	 */
	@Override
	public String visitBinaryExpression(ExpressionBinaire expressionBinaire) {
		return null;
	}

	/**
	 * Visite une addition binaire et génère le code Java correspondant.
	 */
	@Override
	public String visitBinaryAddition(ExpressionBinaireAdd expressionBinaireAdd) {
		String left = expressionBinaireAdd.getLeftExpresion().accept(this);
		String right = expressionBinaireAdd.getRightExpresion().accept(this);
		return left + " + " + right;
	}

	/**
	 * Visite une division binaire et génère le code Java correspondant.
	 */
	@Override
	public String visitBinaryDivision(ExpressionBinaireDiv expressionBinaireDiv) {
		String left = expressionBinaireDiv.getLeftExpresion().accept(this);
		String right = expressionBinaireDiv.getRightExpresion().accept(this);
		return "(" + left + ")" + " / " + right;
	}

	/**
	 * Visite une soustraction binaire et génère le code Java correspondant.
	 */
	@Override
	public String visitBinarySubtraction(ExpressionBinaireMinus expressionBinaireMinus) {
		String left = expressionBinaireMinus.getLeftExpresion().accept(this);
		String right = expressionBinaireMinus.getRightExpresion().accept(this);
		return left + " - " + right;
	}

	/**
	 * Visite une multiplication binaire et génère le code Java correspondant.
	 */
	@Override
	public String visitBinaryMultiplication(ExpressionBinaireMult expressionBinaireMult) {
		String left = expressionBinaireMult.getLeftExpresion().accept(this);
		String right = expressionBinaireMult.getRightExpresion().accept(this);
		return left + " * " + right;
	}

	/**
	 * Visite une expression de comparaison et génère le code Java correspondant.
	 */
	@Override
	public String visitComparison(ExpressionComparaison expressionComparaison) {
		String left = expressionComparaison.getLeftExpresion().accept(this);
		String right = expressionComparaison.getRightExpresion().accept(this);
		return left + " " + expressionComparaison.getComparaisonOperAsString() + " " + right;
	}

	/**
	 * Visite une expression numérique et génère la valeur numérique sous forme de
	 * chaîne de caractères.
	 */
	@Override
	public String visitNumber(ExpressionNumber expressionNumber) {
		return expressionNumber.getValueAsString();
	}

	/**
	 * Visite une expression entourée de parenthèses et génère le code Java
	 * correspondant.
	 */
	@Override
	public String visitParenthesized(ExpressionParenthesee expressionParenthesee) {
		return "(" + expressionParenthesee.getExpression().accept(this) + ")";
	}

	/**
	 * Visite une expression de chaîne de caractères et génère la valeur sous forme
	 * de chaîne de caractères.
	 */
	@Override
	public String visitString(ExpressionString expressionString) {
		return expressionString.getValueAsString();
	}

	/**
	 * Visite une expression unaire (par exemple, une négation) et génère le code
	 * Java correspondant.
	 */
	@Override
	public String visitUnary(ExpressionUnaire expressionUnaire) {
		return expressionUnaire.getOperateurAsString() + expressionUnaire.getExpression().accept(this);
	}

	/**
	 * Visite une variable et génère le nom de la variable sous forme de chaîne de
	 * caractères.
	 */
	@Override
	public String visitVariable(ExpressionVariable expressionVariable) {
		return expressionVariable.getVarName();
	}

	/**
	 * Visite une instruction d'assignation et génère le code Java correspondant. Si
	 * la variable n'a pas encore été déclarée, elle est ajoutée à la liste des
	 * variables et déclarée en Java.
	 */
	@Override
	public String visitAssignment(StatementAssign statementAssign) {
		String variableName = statementAssign.getVariableName();
		String expression = statementAssign.getExpression().accept(this);
		if (!variables.containsKey(variableName)) {
			variables.put(variableName, "int");
			output.append("\t\tint ").append(variableName).append(";\n");
		}
		if (indentation > 2) {
			output2.append("\t\t\t").append(variableName).append(" = ").append(expression).append(";\n");
		} else {
			output2.append("\t\t").append(variableName).append(" = ").append(expression).append(";\n");
		}
		return null;
	}

	/**
	 * Visite une instruction conditionnelle (if-else) et génère le code Java
	 * correspondant.
	 */
	@Override
	public String visitIfStatement(StatementIF statementIF) {
		indentation += 1;
		String condition = statementIF.getCondition().accept(this);
		output2.append("\n\t\tif (").append(condition).append(") {\n");
	//	output2.append("\t");
		statementIF.getBlockThen().accept(this);
		if (statementIF.getBlockElse() != null) {
			output2.append("\t\t} else {\n");
		//	output2.append("\t");
			statementIF.getBlockElse().accept(this);
			output2.append("\t\t}\n");
		}
		indentation -= 1;
		return null;
	}

	/**
	 * Visite une instruction d'affichage (print) et génère le code Java
	 * correspondant.
	 */
	@Override
	public String visitPrint(StatementPrint statementPrint) {
		String expression = statementPrint.getExpression().accept(this);
		output2.append("\t\tSystem.out.println(").append(expression).append(");\n");

		return null;
	}

	/**
	 * Visite une instruction de lecture (read) et génère le code Java
	 * correspondant.
	 */
	@Override
	public String visitRead(StatementRead statementRead) {
		output2.append("\n\t\tScanner scanner = new Scanner(System.in);\n");
		String variableName = statementRead.getVarName().getVarName();
		String msg = statementRead.getMessage();
		output.append("\t\tint ").append(variableName).append(";\n");
		output2.append("\t\tSystem.out.print(").append(msg).append(");\n");
		output2.append("\t\t").append(variableName).append(" = scanner.nextInt();\n");
		return null;
	}

	/**
	 * Visite une boucle while et génère le code Java correspondant.
	 */
	@Override
	public String visitWhile(StatementWhile statementWhile) {
		indentation += 1;
		String condition = statementWhile.getCondition().accept(this);
		output2.append("\n\t\twhile (").append(condition).append(") {\n");
	//	output2.append("\t");
		statementWhile.getBlockWhile().accept(this);
		output2.append("\t\t}\n");
		indentation -= 1;
		return null;
	}

	/**
	 * Écrit la classe Java générée dans un fichier .java. Le fichier est écrit dans
	 * le répertoire spécifié par la variable chemin.
	 * 
	 * @return null
	 */
	public String ecrireFichier() {
		String chemin = "src/inf5153/miniLang/javaGeneration/" + className + ".java";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(chemin))) {
			writer.write(output.toString());
			System.out.println(" Le fichier à été créé, voici son chemin : " + chemin);
			return null;
		} catch (IOException e) {
			System.err.println("Erreur de la création du fichier : " + e.getMessage());
			return null;
		}
	}

}
