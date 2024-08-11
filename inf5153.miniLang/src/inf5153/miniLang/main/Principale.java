package inf5153.miniLang.main;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import inf5153.miniLang.parser.MiniLangLexer;
import inf5153.miniLang.parser.MiniLangParser;
import inf5153.miniLang.visitor.EvaluatorVisitor;
import inf5153.miniLang.visitor.DefUseVisitor;
import inf5153.miniLang.visitor.DefUseVisitor.AssignInfo;

/**
 * Programme principal 
 * 
 */
public class Principale {

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
        String source = "source/Exemple2.mnl";
        System.out.println("Start Processing: " + source);
        ParseTree parseTree = parsing(source);
        System.out.println("End Parsing: " + source);

        if (parseTree != null) {
            // Evaluate the parse tree
            EvaluatorVisitor evaluator = new EvaluatorVisitor();
            evaluator.visit(parseTree);

         // Perform def-use analysis
            DefUseVisitor defUseVisitor = new DefUseVisitor();
            defUseVisitor.visit(parseTree);
            List<AssignInfo> assignInfos = defUseVisitor.getAssignInfos();

            for (AssignInfo info : assignInfos) {
                System.out.println("Definis: " + info.getDefinedVariable());
                if (!info.getUsedVariables().isEmpty()) {
                    System.out.println("Utilise: " + info.getUsedVariables());
                }
            }
        }
    }
}