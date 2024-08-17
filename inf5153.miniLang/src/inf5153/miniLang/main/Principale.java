package inf5153.miniLang.main;

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import inf5153.miniLang.parser.ASTBuilder;
import inf5153.miniLang.parser.MiniLangLexer;
import inf5153.miniLang.parser.MiniLangParser;
import inf5153.miniLang.visitor.EvaluatorVisitor;
import inf5153.miniLang.visitor.DefUseVisitor;
import inf5153.miniLang.visitor.DefUseVisitor.AssignInfo;
import inf5153.miniLang.ast.CompilationUnit;
import inf5153.miniLang.visitor.GeneratorJavaVisitor;

/**
 * Programme principal 
 * 
 */
public class Principale {
	
    public static final String MENU = "\n\nMenu\nVeuillez choisir une tâche:\n1. tache1: Evaluateur de code\n2. tache2: Generateur de code\n3. tache3: Analyse Def-use\n4. Quitter: ";

    public static ParseTree parsing(String fileName) {
        ParseTree parseTree = null;

        try {
            ANTLRFileStream input = new ANTLRFileStream(fileName);

            MiniLangLexer lex = new MiniLangLexer(input);          // Transforme les caractères en tokens
            CommonTokenStream tokens = new CommonTokenStream(lex); // Un flux de tokens
            MiniLangParser parser = new MiniLangParser(tokens);    
            parseTree = parser.compileUnit();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseTree;
    }

    public static void main(String[] args) {
    	
    	// Déclaration des variables 
    	Scanner scanner = new Scanner(System.in);
        int choice = 0;
    	
        String source = "source/Exemple1.mnl";
        String source2 = "source/Exemple2.mnl";
        System.out.println("Start Processing: " + source);
        String fileName = source.substring(source.lastIndexOf('/') + 1);
        String fileName2 = source2.substring(source2.lastIndexOf('/') + 1);
        ParseTree parseTree = parsing(source);
        ParseTree parseTree2 = parsing(source2);
        System.out.println("End Parsing: " + source);

        if (parseTree != null) {
            do {
                System.out.println(MENU);
                try {
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("\nTache 1: Evaluateur de code:");
                            // Construction de l'AST à partir de l'arbre de syntaxe
                            ASTBuilder astBuilderEval = new ASTBuilder();
                            CompilationUnit astEval = (CompilationUnit) astBuilderEval.visit(parseTree2);

                            // Évaluation de l'AST
                            EvaluatorVisitor evaluator = new EvaluatorVisitor();
                            evaluator.visitCompilationUnit(astEval);
                            break;
                            
                        case 2:
                            System.out.println("\nTache 2: Generation de code Java\n");

                            ASTBuilder astBuilderDefUse2 = new ASTBuilder();
                            CompilationUnit astBuilderGen = (CompilationUnit) astBuilderDefUse2.visit(parseTree);
                            CompilationUnit astBuilderGen2 = (CompilationUnit) astBuilderDefUse2.visit(parseTree2);
                            GeneratorJavaVisitor generator = new GeneratorJavaVisitor(fileName);
                            GeneratorJavaVisitor generator2 = new GeneratorJavaVisitor(fileName2);
                            generator.visitCompilationUnit(astBuilderGen);
                            generator2.visitCompilationUnit(astBuilderGen2);
                            

                            break;
                            
                        case 3:
                            System.out.println("\nTache 3: Analyse  Def-Use:\n");
                            ASTBuilder astBuilderDefUse = new ASTBuilder();
                            CompilationUnit astDefUse = (CompilationUnit) astBuilderDefUse.visit(parseTree);
                            DefUseVisitor defUseVisitor = new DefUseVisitor();
                            defUseVisitor.visitCompilationUnit(astDefUse);
                            List<AssignInfo> assignInfos = defUseVisitor.getAssignInfos();

                            for (AssignInfo info : assignInfos) {
                                Set<String> usedVars = info.getUsedVariables();
                                String definedVar = info.getDefinedVariable();
                                if (!usedVars.isEmpty()) {
                                    System.out.println("Definis: " + definedVar + " ---> Utilise: " + usedVars);
                                } else {
                                    System.out.println("Definis: " + definedVar);
                                }
                            }

                            // Résumé des variables
                            System.out.println("\nRésumé des variables:\n");
                            System.out.println("Variables définies: " + defUseVisitor.getAllDefinedVariables());
                            System.out.println("Variables utilisées: " + defUseVisitor.getAllUsedVariables());
                            break;
                            
                        case 4:
                            System.out.println("Au revoir!");
                            break;
                            
                        default:
                            System.out.println("Option invalide, veuillez réessayer.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                    scanner.next(); 
                }
            } while (choice != 4);
            scanner.close();
        }
    }
}
