package inf5153.miniLang.visitor;

import inf5153.miniLang.parser.MiniLangBaseVisitor;
import inf5153.miniLang.parser.MiniLangParser;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JavaCodeGeneratorVisitor extends MiniLangBaseVisitor<String> {

    private StringBuilder javaCode = new StringBuilder();
    private Set<String> declaredVariables = new HashSet<>();

    public String generateJavaCode(String fileName) {
        String className = "Gen" + fileName.substring(0, fileName.lastIndexOf('.'));
        javaCode.append("package inf5153.miniLang.javaGeneration;\n\n");
        javaCode.append("import java.util.Scanner;\n\n");
        javaCode.append("public class ").append(className).append(" {\n");
        javaCode.append("    public static void main(String[] args) {\n");
        return className;
    }

    @Override
    public String visitStatemntAssign(MiniLangParser.StatemntAssignContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        String expr = visit(ctx.expr);
        if (!declaredVariables.contains(varName)) {
            declaredVariables.add(varName);
        }
        javaCode.append("        ").append(varName).append(" = ").append(expr).append(";\n");
        return null;
    }

    @Override
    public String visitStatemntPrint(MiniLangParser.StatemntPrintContext ctx) {
        String expr = visit(ctx.expr);
        if (expr == null || expr.isEmpty()) {
            expr = "\"\""; 
        }
        javaCode.append("        System.out.println(").append(expr).append(");\n");
        return null;
    }

    @Override
    public String visitStatemntRead(MiniLangParser.StatemntReadContext ctx) {
        String prompt = ctx.STRINGLITERAL().getText();
        String varName = ctx.IDENTIFIER().getText();
        if (!declaredVariables.contains(varName)) {
            declaredVariables.add(varName);
        }
        javaCode.append("        Scanner scanner = new Scanner(System.in);\n");
        javaCode.append("        System.out.print(").append(prompt).append(");\n");
        javaCode.append("        ").append(varName).append(" = scanner.nextInt();\n");
        return null;
    }

    @Override
    public String visitBinaryExprAdd(MiniLangParser.BinaryExprAddContext ctx) {
        String left = visit(ctx.left);
        String right = visit(ctx.right);
        return left + " " + ctx.bop.getText() + " " + right;
    }

    @Override
    public String visitBinaryExprMult(MiniLangParser.BinaryExprMultContext ctx) {
        String left = visit(ctx.left);
        String right = visit(ctx.right);
        return left + " " + ctx.bop.getText() + " " + right;
    }

    @Override
    public String visitBinaryExprComp(MiniLangParser.BinaryExprCompContext ctx) {
        String left = visit(ctx.left);
        String right = visit(ctx.right);
        return left + " " + ctx.bop.getText() + " " + right;
    }

    @Override
    public String visitNumberLiteral(MiniLangParser.NumberLiteralContext ctx) {
        return ctx.NUMBER().getText();
    }

    @Override
    public String visitIdentifier(MiniLangParser.IdentifierContext ctx) {
        return ctx.IDENTIFIER().getText();
    }

    @Override
    public String visitExprParenth(MiniLangParser.ExprParenthContext ctx) {
        return "(" + visit(ctx.expression()) + ")";
    }

    @Override
    public String visitStatemntIF(MiniLangParser.StatemntIFContext ctx) {
        String condition = visit(ctx.cdt);
        javaCode.append("        if (").append(condition).append(") {\n");
        visit(ctx.blockThen);
        javaCode.append("        }");
        if (ctx.blockElse != null) {
            javaCode.append(" else {\n");
            visit(ctx.blockElse);
            javaCode.append("        }\n");
        }
        return null;
    }

    @Override
    public String visitStatemntWhile(MiniLangParser.StatemntWhileContext ctx) {
        String condition = visit(ctx.cdt);
        javaCode.append("        while (").append(condition).append(") {\n");
        visit(ctx.blockWhile);
        javaCode.append("        }\n");
        return null;
    }

    @Override
    public String visitStatements(MiniLangParser.StatementsContext ctx) {
        for (MiniLangParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        return null;
    }

    public String finishJavaCode() {
        StringBuilder declarations = new StringBuilder();
        for (String var : declaredVariables) {
            declarations.append("        int ").append(var).append(";\n");
        }
        javaCode.insert(javaCode.indexOf("{", javaCode.indexOf("main")) + 1, declarations);
        javaCode.append("    }\n");
        javaCode.append("}\n");
        return javaCode.toString();
    }

    public String getJavaCode() {
        return javaCode.toString();
    }
}