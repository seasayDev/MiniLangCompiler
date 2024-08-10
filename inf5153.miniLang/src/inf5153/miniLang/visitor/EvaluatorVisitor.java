package inf5153.miniLang.visitor;

import java.util.HashMap;
import java.util.Map;

import inf5153.miniLang.parser.MiniLangBaseVisitor;
import inf5153.miniLang.parser.MiniLangParser;

public class EvaluatorVisitor extends MiniLangBaseVisitor<Integer> {
    private Map<String, Integer> variables = new HashMap<>();

    @Override
    public Integer visitStatemntAssign(MiniLangParser.StatemntAssignContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        int value = visit(ctx.expression());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Integer visitStatemntPrint(MiniLangParser.StatemntPrintContext ctx) {
        int value = visit(ctx.expression());
        System.out.println(value);
        return value;
    }

    @Override
    public Integer visitBinaryExprAdd(MiniLangParser.BinaryExprAddContext ctx) {
        int left = visit(ctx.left);
        int right = visit(ctx.right);
        if (ctx.bop.getText().equals("+")) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitBinaryExprMult(MiniLangParser.BinaryExprMultContext ctx) {
        int left = visit(ctx.left);
        int right = visit(ctx.right);
        if (ctx.bop.getText().equals("*")) {
            return left * right;
        } else {
            return left / right;
        }
    }

    @Override
    public Integer visitNumberLiteral(MiniLangParser.NumberLiteralContext ctx) {
        return Integer.valueOf(ctx.NUMBER().getText());
    }

    @Override
    public Integer visitIdentifier(MiniLangParser.IdentifierContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        return variables.getOrDefault(varName, 0);
    }

    @Override
    public Integer visitExprParenth(MiniLangParser.ExprParenthContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Integer visitStatemntIF(MiniLangParser.StatemntIFContext ctx) {
        int condition = visit(ctx.cdt);
        if (condition != 0) {
            visit(ctx.blockThen);
        } else if (ctx.blockElse != null) {
            visit(ctx.blockElse);
        }
        return 0;
    }

    @Override
    public Integer visitStatemntWhile(MiniLangParser.StatemntWhileContext ctx) {
        while (visit(ctx.cdt) != 0) {
            visit(ctx.blockWhile);
        }
        return 0;
    }
}
