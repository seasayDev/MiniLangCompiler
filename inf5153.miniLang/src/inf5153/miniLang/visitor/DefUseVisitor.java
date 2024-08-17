/** 
 * Cette classe analyse chaque affectation dans le code, enregistre la variable qui est définie et toutes les variables qui sont utilisées 
 * dans l'expression de l'affectation. Elle conserve une liste des informations d'affectation (AssignInfo) ainsi que des ensembles de 
 * toutes les variables définies et utilisées dans le programme.
 */

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

    /**
     * Classe interne AssignInfo qui stocke la variable definie et les variables utilisé.
     */
    public static class AssignInfo {
        private String definedVariable;
        private Set<String> usedVariables;

        /**
         * Constructeur de AssignInfo.
         * 
         * @param definedVariable la variable définie par l'affectation
         * @param usedVariables les variables utilisés dans l'affectation
         */

        public AssignInfo(String definedVariable, Set<String> usedVariables) {
            this.definedVariable = definedVariable;
            this.usedVariables = usedVariables;
        }

        
        /**
         * Retourne la variable définie par l'affectation.
         * 
         * @return la variable définie
         */
        public String getDefinedVariable() {
            return definedVariable;
        }

        /**
         * Retourne les variables utilisés dans l'affectation.
         */

        public Set<String> getUsedVariables() {
            return usedVariables;
        }
    }

    /**
     * Visite un noeud d'affectation (StatemntAssign) et collecte les informations
     * sur la variable définie et les variables utilisés.
     * 
     * @param statementAssign le contexte de l'affectation
     * @return null (pas de valeur retourné)
     */
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

    /**
     * Collecte les variables utilisés dans une expression donnée.
     * 
     * @param expr le contexte de l'expression
     * @param usedVars l'ensemble des variables utilisés à  mettre à  jour
     */
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

    /**
     * Retourne la liste des informations d'affectation collectées.
     * 
     * @return la liste des AssignInfo
     */
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