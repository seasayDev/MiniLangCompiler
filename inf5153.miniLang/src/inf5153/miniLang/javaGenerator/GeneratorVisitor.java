package inf5153.miniLang.javaGenerator;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import inf5153.miniLang.parser.MiniLangBaseVisitor;
import inf5153.miniLang.parser.MiniLangParser;

public class GeneratorVisitor extends MiniLangBaseVisitor<Void> {

    private Map<String, String> variables = new HashMap<>();
    private StringBuilder output = new StringBuilder();
    private String className = "GenGeneratorVisisor";


    @Override
    public Void visitCompUnitBlock(MiniLangParser.CompUnitBlockContext ctx) {
       
        output.append("package inf5153.miniLang.javaGeneration;\n\n");
        output.append("public class ").append(className).append(" {\n");
        output.append("    public static void main(String[] args) {\n");

        visitChildren(ctx);

        output.append("    }\n");
        output.append("}\n");
        System.out.print(output.toString());
        return null;
    }

    @Override
    public Void visitStatemntAssign(MiniLangParser.StatemntAssignContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        String expression = ctx.expression().getText();

        if (!variables.containsKey(varName)) {
            output.append("        int ").append(varName).append(";\n");
            variables.put(varName, "int");
        }

        output.append("        ").append(varName).append(" = ").append(expression).append(";\n");
        return null;
    }

    @Override
    public Void visitStatemntIF(MiniLangParser.StatemntIFContext ctx) {
        String condition = ctx.cdt.getText();
        output.append("        if (").append(condition).append(") {\n");
        visit(ctx.blockThen);
        output.append("        } else {\n");
        visit(ctx.blockElse);
        output.append("        }\n");
        return null;
    }

    @Override
    public Void visitStatemntPrint(MiniLangParser.StatemntPrintContext ctx) {
        String expression = ctx.expression().getText();
        output.append("        System.out.println(").append(expression).append(");\n");
        return null;
    }

}