package inf5153.miniLang.main;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import inf5153.miniLang.ast.CompilationUnit;
import inf5153.miniLang.parser.ASTBuilder;
import inf5153.miniLang.parser.MiniLangLexer;
import inf5153.miniLang.parser.MiniLangParser;
import inf5153.miniLang.visitor.EvaluatorVisitor;

/**
 * Programme principal 
 * 
 */
public class Principale {

    public static CompilationUnit parsing(String fileName) {
        CompilationUnit cUnit = null;

        try {
            ANTLRFileStream input = new ANTLRFileStream(fileName);

            MiniLangLexer lex = new MiniLangLexer(input);          // Transforms characters into tokens
            CommonTokenStream tokens = new CommonTokenStream(lex); // A token stream
            MiniLangParser parser = new MiniLangParser(tokens);    // Transforms tokens into parse trees
            ParseTree t = parser.compileUnit();
            ASTBuilder astBuilder = new ASTBuilder();
            cUnit = (CompilationUnit) astBuilder.visit(t);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cUnit;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        String source = ".\\source\\Exemple2.mnl";
        System.out.println("Start Processing: " + source);
        CompilationUnit cUnit = parsing(source);
        System.out.println("End Parsing: " + source + " - " + cUnit);

        
        if (cUnit != null) {
            EvaluatorVisitor evaluator = new EvaluatorVisitor();
            evaluator.visit(cUnit);  
        }
    }
}
