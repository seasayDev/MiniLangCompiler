package inf5153.miniLang.visitor;

import java.util.HashMap;
import java.util.Map;
import inf5153.miniLang.parser.MiniLangBaseVisitor;
import inf5153.miniLang.parser.MiniLangParser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneratorVisitor extends MiniLangBaseVisitor<String> {

	private Map<String, String> variables = new HashMap<>();
	private StringBuilder output = new StringBuilder();
	private StringBuilder output2 = new StringBuilder();
	private String className;
	
	  public  GeneratorVisitor(String fileName) {
	         this.className = "Gen" + fileName.substring(0, fileName.lastIndexOf('.'));
	        
	    }


	@Override
	public String visitCompUnitBlock(MiniLangParser.CompUnitBlockContext ctx) {
		output.append("package inf5153.miniLang.javaGeneration;\n");
		output.append("public class ").append(className).append(" {\n");
		output.append("\tpublic static void main(String[] args) {\n");
		visitChildren(ctx);
		output.append("\n").append(output2);
		output.append("\t}\n");
		output.append("}\n");
		System.out.print(output.toString());
		ecrireFichier();
		return null;
	}

	@Override
	public String visitStatemntAssign(MiniLangParser.StatemntAssignContext ctx) {
		String varName = ctx.IDENTIFIER().getText();
		String expression = ctx.expression().getText();

		if (!variables.containsKey(varName)) {
			output.append("\t\tint ").append(varName).append(" ;\n");
			variables.put(varName, "int");
		}
		if (ctx.depth() > 3) {
			output2.append("\t\t\t").append(varName).append(" = ").append(expression).append(" ;\n");
		} else {
			output2.append("\t\t").append(varName).append(" = ").append(expression).append(" ;\n");
		}
		return null;
	}

	@Override
	public String visitStatemntIF(MiniLangParser.StatemntIFContext ctx) {
		String condition = ctx.cdt.getText();
		output2.append("\n\t\tif (").append(condition).append(" ) {\n");
		visit(ctx.blockThen);
		if (ctx.blockElse != null) {
			output2.append("\t\t} else {\n");
			visit(ctx.blockElse);
			output2.append("\t\t}\n");
		}
		return null;
	}

	@Override
	public String visitStatemntPrint(MiniLangParser.StatemntPrintContext ctx) {
		String expression = ctx.expression().getText();
		output2.append("\t\tSystem.out.println(").append(expression).append(") ;\n");
		return null;
	}

	public String visitStatemntWhile(MiniLangParser.StatemntWhileContext ctx) {
		String condition = ctx.cdt.getText();
		output2.append("\n\t\twhile(").append(condition).append(") {\n");
		output2.append("\t");
		visit(ctx.block());
		output2.append("\t\t}\n");
		return null;
	}

	@Override
	public String visitStatemntRead(MiniLangParser.StatemntReadContext ctx) {

		output2.append("\n\t\tScanner scanner = new Scanner(System.in);\n");

		String msg = ctx.STRINGLITERAL().getText();
		String identifiant = ctx.IDENTIFIER().getText();

		output.append("\t\tint ").append(identifiant).append(" ;\n");
		output2.append("\t\tSystem.out.print(").append(msg).append(");\n");
		output2.append("\t\t").append(identifiant).append(" = scanner.nextInt();\n");

		return null;
	}
	
	public String ecrireFichier() {
	    String chemin = "src/inf5153/miniLang/javaGenerator/" + className + ".java";
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(chemin))) {
	        writer.write(output.toString());
	        System.out.println(" Le fichier à été créé, voici son chemin : " + chemin);
	        return null ;
	    } catch (IOException e) {
	        System.err.println("Erreur de la création du fichier : " + e.getMessage());
	        return null;
	    }
	}

}
