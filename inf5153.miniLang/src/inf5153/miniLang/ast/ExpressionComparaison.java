package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

/**
 * Représente une condition, 
 * 
 * Elle est sous-classe de ExpressionBinaire, car composée d'une expression à gauche 
 * et une expression à droite 
 *  
 * 
 * 
 * @author elhachemi Alikacem
 */
public class ExpressionComparaison extends ExpressionBinaire {
    private Operator comparaisonOper; // must be == or !=  

    public ExpressionComparaison(Expression leftExpresion, Expression rightExpresion, Operator oper) {
        super(leftExpresion, rightExpresion);
        this.comparaisonOper = oper;
    }

    public Operator getComparaisonOper() {
        return comparaisonOper;
    }

    public String getComparaisonOperAsString() {
        if (comparaisonOper == Operator.EQUAL) {
            return "==";
        } else if (comparaisonOper == Operator.DIFF) {
            return "!=";
        } else {
            throw new IllegalArgumentException("Invalid operator: " + comparaisonOper);
        }
    }

    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitComparison(this);
    }
}
