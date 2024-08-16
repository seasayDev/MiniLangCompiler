package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

/**
 * Expression binaire : multiplication 
 *  
 * @author elhachemi Alikacem
 */
public class ExpressionBinaireMult extends ExpressionBinaire{

	public ExpressionBinaireMult(Expression leftExpresion, Expression rightExpresion) {
		super(leftExpresion, rightExpresion);
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitBinaryMultiplication(this);
    }
}
