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
import inf5153.miniLang.visitor.GeneratorJavaVisitor;
import inf5153.miniLang.visitor.DefUseVisitor;
import inf5153.miniLang.visitor.DefUseVisitor.AssignInfo;



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

            MiniLangLexer lex = new MiniLangLexer(input);          // Transforms characters into tokens
            CommonTokenStream tokens = new CommonTokenStream(lex); // A token stream
            MiniLangParser parser = new MiniLangParser(tokens);    // Transforms tokens into parse trees
            parseTree = parser.compileUnit();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseTree;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
    	
    	//Declaration des variables 
    	Scanner scanner = new Scanner(System.in);
        int choice = 0;
    	
        String source = "source/Exemple2.mnl";
        String fileName = source.substring(source.lastIndexOf('/') + 1);
        System.out.println("Start Processing: " + source);
        ParseTree parseTree = parsing(source);
        System.out.println("End Parsing: " + source);

        
        if (parseTree != null) {
           
        	do {
                System.out.println(MENU);
                try {
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            
                            System.out.println("\nTache 1: Evaluateur de code:");
                            EvaluatorVisitor evaluator = new EvaluatorVisitor();
                            evaluator.visit(parseTree);
                            
                            break;
                        case 2:
                            System.out.println("Traitement de tache 2...");
                            GeneratorJavaVisitor generator = new GeneratorJavaVisitor(fileName);
                            generator.visit(parseTree);
                            break;
                        case 3:
                      
                            System.out.println("\nTache 3: Analyse  Def-Use:\n");
                            DefUseVisitor defUseVisitor = new DefUseVisitor();
                            defUseVisitor.visit(parseTree);
                            List<AssignInfo> assignInfos = defUseVisitor.getAssignInfos();

                            for (AssignInfo info : assignInfos) {
                            	
                                Set<String> usedVars = info.getUsedVariables();
                                String definedVar = info.getDefinedVariable();
                                if (!usedVars.isEmpty()) {
                                    System.out.println("Definis: " + definedVar+" ---> Utilise: " + usedVars);
                                } else {
                                    System.out.println("Definis: " + definedVar);
                                }
                            }
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
