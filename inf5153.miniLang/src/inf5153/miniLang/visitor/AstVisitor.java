/**
 * Cette interface définit un visiteur pour parcourir et traiter les différents éléments 
 * d'un arbre syntaxique abstrait (AST) dans le cadre du langage miniLang.
 */
package inf5153.miniLang.visitor;

import inf5153.miniLang.ast.*;

public interface AstVisitor<T> {
    T visitBlock(Block block);
    T visitCompilationUnit(CompilationUnit compilationUnit);
    T visitBinaryExpression(ExpressionBinaire expressionBinaire);
    T visitBinaryAddition(ExpressionBinaireAdd expressionBinaireAdd);
    T visitBinaryDivision(ExpressionBinaireDiv expressionBinaireDiv);
    T visitBinarySubtraction(ExpressionBinaireMinus expressionBinaireMinus);
    T visitBinaryMultiplication(ExpressionBinaireMult expressionBinaireMult);
    T visitComparison(ExpressionComparaison expressionComparaison);
    T visitNumber(ExpressionNumber expressionNumber);
    T visitParenthesized(ExpressionParenthesee expressionParenthesee);
    T visitString(ExpressionString expressionString);
    T visitUnary(ExpressionUnaire expressionUnaire);
    T visitVariable(ExpressionVariable expressionVariable);
    T visitAssignment(StatementAssign statementAssign);
    T visitIfStatement(StatementIF statementIF);
    T visitPrint(StatementPrint statementPrint);
    T visitRead(StatementRead statementRead);
    T visitWhile(StatementWhile statementWhile);
}