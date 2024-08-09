package inf5153.miniLang.visitor;

import java.util.HashMap;
import java.util.Map;

import inf5153.miniLang.parser.MiniLangBaseVisitor;
import inf5153.miniLang.parser.MiniLangParser;

public class EvaluatorVisitor extends MiniLangBaseVisitor<Integer> {
    private Map<String, Integer> variables = new HashMap<>();

    @Override
    public Integer visitAssignment(MiniLangParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        int value = visit(ctx.expr());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Integer visitPrint(MiniLangParser.PrintContext ctx) {
        int value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }

    @Override
    public Integer visitAddSub(MiniLangParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == MiniLangParser.ADD) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitMulDiv(MiniLangParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == MiniLangParser.MUL) {
            return left * right;
        } else {
            return left / right;
        }
    }

    @Override
    public Integer visitInt(MiniLangParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(MiniLangParser.IdContext ctx) {
        String varName = ctx.ID().getText();
        return variables.getOrDefault(varName, 0);
    }

    @Override
    public Integer visitParens(MiniLangParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitIfStat(MiniLangParser.IfStatContext ctx) {
        int condition = visit(ctx.expr());
        if (condition != 0) {
            visit(ctx.block(0));
        } else if (ctx.block(1) != null) {
            visit(ctx.block(1));
        }
        return 0;
    }
}
