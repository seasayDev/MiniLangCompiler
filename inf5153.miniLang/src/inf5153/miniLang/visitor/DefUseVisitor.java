package inf5153.miniLang.visitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import inf5153.miniLang.parser.MiniLangBaseVisitor;
import inf5153.miniLang.parser.MiniLangParser;

public class DefUseVisitor extends MiniLangBaseVisitor<Void> {
    private List<AssignInfo> assignInfos = new ArrayList<>();

    public static class AssignInfo {
        private String definedVariable;
        private Set<String> usedVariables;

        public AssignInfo(String definedVariable, Set<String> usedVariables) {
            this.definedVariable = definedVariable;
            this.usedVariables = usedVariables;
        }

        public String getDefinedVariable() {
            return definedVariable;
        }

        public Set<String> getUsedVariables() {
            return usedVariables;
        }
    }

    @Override
    public Void visitStatemntAssign(MiniLangParser.StatemntAssignContext ctx) {
        String definedVar = ctx.IDENTIFIER().getText();
        Set<String> usedVars = new HashSet<>();
        collectUsedVariables(ctx.expr, usedVars);
        assignInfos.add(new AssignInfo(definedVar, usedVars));
        return null;
    }

    private void collectUsedVariables(MiniLangParser.ExpressionContext exprCtx, Set<String> usedVars) {
        if (exprCtx instanceof MiniLangParser.PrimaryExprContext) {
            MiniLangParser.PrimaryExprContext primaryCtx = (MiniLangParser.PrimaryExprContext) exprCtx;
            if (primaryCtx.primary() instanceof MiniLangParser.IdentifierContext) {
                MiniLangParser.IdentifierContext idCtx = (MiniLangParser.IdentifierContext) primaryCtx.primary();
                usedVars.add(idCtx.IDENTIFIER().getText());
            }
        } else if (exprCtx instanceof MiniLangParser.BinaryExprAddContext) {
            MiniLangParser.BinaryExprAddContext binaryCtx = (MiniLangParser.BinaryExprAddContext) exprCtx;
            collectUsedVariables(binaryCtx.left, usedVars);
            collectUsedVariables(binaryCtx.right, usedVars);
        } else if (exprCtx instanceof MiniLangParser.BinaryExprMultContext) {
            MiniLangParser.BinaryExprMultContext binaryCtx = (MiniLangParser.BinaryExprMultContext) exprCtx;
            collectUsedVariables(binaryCtx.left, usedVars);
            collectUsedVariables(binaryCtx.right, usedVars);
        } else if (exprCtx instanceof MiniLangParser.BinaryExprCompContext) {
            MiniLangParser.BinaryExprCompContext binaryCtx = (MiniLangParser.BinaryExprCompContext) exprCtx;
            collectUsedVariables(binaryCtx.left, usedVars);
            collectUsedVariables(binaryCtx.right, usedVars);
        }
    }

    public List<AssignInfo> getAssignInfos() {
        return assignInfos;
    }
}