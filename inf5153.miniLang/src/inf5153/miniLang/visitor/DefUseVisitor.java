package inf5153.miniLang.visitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import inf5153.miniLang.ast.*;

public class DefUseVisitor implements AstVisitor<Void> {
    private List<AssignInfo> assignInfos = new ArrayList<>();
    private Set<String> allDefinedVariables = new HashSet<>();
    private Set<String> allUsedVariables = new HashSet<>();

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
    public Void visitAssignment(StatementAssign statementAssign) {
        String definedVar = statementAssign.getVariableName();
        Set<String> usedVars = new HashSet<>();
        collectUsedVariables(statementAssign.getExpression(), usedVars);
        assignInfos.add(new AssignInfo(definedVar, usedVars));
        allDefinedVariables.add(definedVar);
        allUsedVariables.addAll(usedVars);
        return null;
    }

    private void collectUsedVariables(Expression expr, Set<String> usedVars) {
        if (expr instanceof ExpressionVariable) {
            usedVars.add(((ExpressionVariable) expr).getVarName());
            allUsedVariables.add(((ExpressionVariable) expr).getVarName());
        } else if (expr instanceof ExpressionBinaire) {
            collectUsedVariables(((ExpressionBinaire) expr).getLeftExpresion(), usedVars);
            collectUsedVariables(((ExpressionBinaire) expr).getRightExpresion(), usedVars);
        } else if (expr instanceof ExpressionParenthesee) {
            collectUsedVariables(((ExpressionParenthesee) expr).getExpression(), usedVars);
        }
    }

    @Override
    public Void visitBinaryExpression(ExpressionBinaire expressionBinaire) {
        collectUsedVariables(expressionBinaire.getLeftExpresion(), new HashSet<>());
        collectUsedVariables(expressionBinaire.getRightExpresion(), new HashSet<>());
        return null;
    }

    @Override
    public Void visitParenthesized(ExpressionParenthesee expressionParenthesee) {
        collectUsedVariables(expressionParenthesee.getExpression(), new HashSet<>());
        return null;
    }

    @Override
    public Void visitVariable(ExpressionVariable expressionVariable) {
        return null;
    }

    @Override
    public Void visitBinaryAddition(ExpressionBinaireAdd expressionBinaireAdd) {
        collectUsedVariables(expressionBinaireAdd.getLeftExpresion(), new HashSet<>());
        collectUsedVariables(expressionBinaireAdd.getRightExpresion(), new HashSet<>());
        return null;
    }

    @Override
    public Void visitComparison(ExpressionComparaison expressionComparaison) {
        collectUsedVariables(expressionComparaison.getLeftExpresion(), new HashSet<>());
        collectUsedVariables(expressionComparaison.getRightExpresion(), new HashSet<>());
        return null;
    }

    @Override
    public Void visitBlock(Block block) {
        for (Statement statement : block) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visitCompilationUnit(CompilationUnit compilationUnit) {
        return compilationUnit.getBlock().accept(this);
    }

    @Override
    public Void visitPrint(StatementPrint statementPrint) {
        return null;
    }

    @Override
    public Void visitIfStatement(StatementIF statementIF) {
        statementIF.getCondition().accept(this);
        statementIF.getBlockThen().accept(this);
        if (statementIF.getBlockElse() != null) {
            statementIF.getBlockElse().accept(this);
        }
        return null;
    }

    @Override
    public Void visitWhile(StatementWhile statementWhile) {
        statementWhile.getCondition().accept(this);
        statementWhile.getBlockWhile().accept(this);
        return null;
    }

    @Override
    public Void visitRead(StatementRead statementRead) {
        return null;
    }

    @Override
    public Void visitNumber(ExpressionNumber expressionNumber) {
        return null;
    }

    @Override
    public Void visitUnary(ExpressionUnaire expressionUnaire) {
        collectUsedVariables(expressionUnaire.getExpression(), new HashSet<>());
        return null;
    }

    @Override
    public Void visitString(ExpressionString expressionString) {
        return null;
    }

    @Override
    public Void visitBinarySubtraction(ExpressionBinaireMinus expressionBinaireMinus) {
        collectUsedVariables(expressionBinaireMinus.getLeftExpresion(), new HashSet<>());
        collectUsedVariables(expressionBinaireMinus.getRightExpresion(), new HashSet<>());
        return null;
    }

    @Override
    public Void visitBinaryMultiplication(ExpressionBinaireMult expressionBinaireMult) {
        collectUsedVariables(expressionBinaireMult.getLeftExpresion(), new HashSet<>());
        collectUsedVariables(expressionBinaireMult.getRightExpresion(), new HashSet<>());
        return null;
    }

    @Override
    public Void visitBinaryDivision(ExpressionBinaireDiv expressionBinaireDiv) {
        collectUsedVariables(expressionBinaireDiv.getLeftExpresion(), new HashSet<>());
        collectUsedVariables(expressionBinaireDiv.getRightExpresion(), new HashSet<>());
        return null;
    }

    public List<AssignInfo> getAssignInfos() {
        return assignInfos;
    }

    public Set<String> getAllDefinedVariables() {
        return allDefinedVariables;
    }

    public Set<String> getAllUsedVariables() {
        return allUsedVariables;
    }
}