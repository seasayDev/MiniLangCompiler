package inf5153.miniLang.visitor;

import inf5153.miniLang.ast.*;
import java.util.HashMap;
import java.util.Map;

public class EvaluatorVisitor implements AstVisitor<Integer> {
    private Map<String, Integer> variables = new HashMap<>();

    // Visite un bloc d'instructions
    @Override
    public Integer visitBlock(Block block) {
        for (Statement statement : block) {
            statement.accept(this);
        }
        return 0;
    }

    // Visite l'unité de compilation principale
    @Override
    public Integer visitCompilationUnit(CompilationUnit compilationUnit) {
        return visitBlock(compilationUnit.getBlock());
    }

    // Visite une expression binaire générique
    @Override
    public Integer visitBinaryExpression(ExpressionBinaire expressionBinaire) {
        Integer left = expressionBinaire.getLeftExpresion().accept(this);
        Integer right = expressionBinaire.getRightExpresion().accept(this);
        return null;
    }

    // Visite une addition binaire
    @Override
    public Integer visitBinaryAddition(ExpressionBinaireAdd expressionBinaireAdd) {
        Integer left = expressionBinaireAdd.getLeftExpresion().accept(this);
        Integer right = expressionBinaireAdd.getRightExpresion().accept(this);
        return left + right;
    }

    // Visite une soustraction binaire
    @Override
    public Integer visitBinarySubtraction(ExpressionBinaireMinus expressionBinaireMinus) {
        Integer left = expressionBinaireMinus.getLeftExpresion().accept(this);
        Integer right = expressionBinaireMinus.getRightExpresion().accept(this);
        return left - right;
    }

    // Visite une multiplication binaire
    @Override
    public Integer visitBinaryMultiplication(ExpressionBinaireMult expressionBinaireMult) {
        Integer left = expressionBinaireMult.getLeftExpresion().accept(this);
        Integer right = expressionBinaireMult.getRightExpresion().accept(this);
        return left * right;
    }

    // Visite une division binaire
    @Override
    public Integer visitBinaryDivision(ExpressionBinaireDiv expressionBinaireDiv) {
        Integer left = expressionBinaireDiv.getLeftExpresion().accept(this);
        Integer right = expressionBinaireDiv.getRightExpresion().accept(this);
        if (right == 0) {
            System.err.println("Error: Division by zero.");
            return 0;
        }
        return left / right;
    }

    // Visite une comparaison binaire
    @Override
    public Integer visitComparison(ExpressionComparaison expressionComparaison) {
        Integer left = expressionComparaison.getLeftExpresion().accept(this);
        Integer right = expressionComparaison.getRightExpresion().accept(this);
        switch (expressionComparaison.getComparaisonOper()) {
            case EQUAL:
                return left.equals(right) ? 1 : 0;
            case DIFF:
                return !left.equals(right) ? 1 : 0;
            default:
                return 0;
        }
    }

    // Visite un nombre littéral
    @Override
    public Integer visitNumber(ExpressionNumber expressionNumber) {
        return expressionNumber.intValue();
    }

    // Visite une expression parenthésée
    @Override
    public Integer visitParenthesized(ExpressionParenthesee expressionParenthesee) {
        return expressionParenthesee.getExpression().accept(this);
    }

    // Visite une expression unaire
    @Override
    public Integer visitUnary(ExpressionUnaire expressionUnaire) {
        Integer value = expressionUnaire.getExpression().accept(this);
        switch (expressionUnaire.getOperateur()) {
            case MINUS:
                return -value;
            default:
                return value;
        }
    }

    // Visite une variable
    @Override
    public Integer visitVariable(ExpressionVariable expressionVariable) {
        return variables.getOrDefault(expressionVariable.getVarName(), 0);
    }

    // Visite une instruction d'affectation
    @Override
    public Integer visitAssignment(StatementAssign statementAssign) {
        String varName = statementAssign.getVariableName();
        Integer value = statementAssign.getExpression().accept(this);
        variables.put(varName, value);
        return value;
    }

    // Visite une instruction if
    @Override
    public Integer visitIfStatement(StatementIF statementIF) {
        Integer condition = statementIF.getCondition().accept(this);
        if (condition != null && condition != 0) {
            statementIF.getBlockThen().accept(this);
        } else if (statementIF.getBlockElse() != null) {
            statementIF.getBlockElse().accept(this);
        }
        return 0;
    }

    // Visite une instruction print
    @Override
    public Integer visitPrint(StatementPrint statementPrint) {
        Integer value = statementPrint.getExpression().accept(this);
        if (value != null) {
            System.out.println(value);
        }
        return 0;
    }

    // Visite une instruction read
    @Override
    public Integer visitRead(StatementRead statementRead) {
        String variableName = statementRead.getVarName().getVarName();
        variables.put(variableName, 1);
        return 1;
    }

    // Visite une boucle while
    @Override
    public Integer visitWhile(StatementWhile statementWhile) {
        while (true) {
            Integer condition = statementWhile.getCondition().accept(this);
            if (condition == null || condition == 0) {
                break;
            }
            statementWhile.getBlockWhile().accept(this);
        }
        return 0;
    }
}
