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
        Integer value = visit(ctx.expression());
        if (value == null) {
            value = 0;
        }
        variables.put(varName, value);
        return value;
    }

    @Override
    public Integer visitStatemntPrint(MiniLangParser.StatemntPrintContext ctx) {
        Integer value = visit(ctx.expression());
        if (value != null) {
            System.out.println(value);
        }
        return 0;
    }

    @Override
    public Integer visitBinaryExprAdd(MiniLangParser.BinaryExprAddContext ctx) {
        Integer left = visit(ctx.expression(0));
        Integer right = visit(ctx.expression(1));
        if (left == null) {
            left = 0;
        }
        if (right == null) {
            right = 0;
        }
        if (ctx.bop.getText().equals("+")) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitBinaryExprMult(MiniLangParser.BinaryExprMultContext ctx) {
        Integer left = visit(ctx.expression(0));
        Integer right = visit(ctx.expression(1));
        if (left == null) {
            left = 0;
        }
        if (right == null) {
            right = 0;
        }
        if (ctx.bop.getText().equals("*")) {
            return left * right;
        } else if (ctx.bop.getText().equals("/")) {
            if (right == 0) {
                System.err.println("Error: Division by zero.");
                return 0;
            }
            // Use integer division
            return left / right;
        } else {
            return 0;
        }
    }

    @Override
    public Integer visitBinaryExprComp(MiniLangParser.BinaryExprCompContext ctx) {
        Integer left = visit(ctx.expression(0));
        Integer right = visit(ctx.expression(1));
        if (left == null || right == null) {
            return 0;
        }
        if (ctx.bop.getText().equals("==")) {
            return left.equals(right) ? 1 : 0;
        } else if (ctx.bop.getText().equals("!=")) {
            return !left.equals(right) ? 1 : 0;
        } else {
            return 0;
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
        Integer condition = visit(ctx.cdt);
        if (condition != null && condition != 0) {
            visit(ctx.blockThen);
        } else if (ctx.blockElse != null) {
            visit(ctx.blockElse);
        }
        return 0;
    }

    @Override
    public Integer visitStatemntWhile(MiniLangParser.StatemntWhileContext ctx) {
        while (true) {
            Integer condition = visit(ctx.cdt);
            if (condition == null || condition == 0) {
                break;
            }
            visit(ctx.blockWhile);
        }
        return 0;
    }

    @Override
    public Integer visitStatemntRead(MiniLangParser.StatemntReadContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();
        variables.put(variableName, 1); // Simulate reading input by setting the variable to 1
        return 1;
    }
}
