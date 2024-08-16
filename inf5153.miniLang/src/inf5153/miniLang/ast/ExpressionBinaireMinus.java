package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;


/**
 * Expression binaire : soustraction 
 *  
 * @author elhachemi Alikacem
 */

public class ExpressionBinaireMinus extends ExpressionBinaire {

	public ExpressionBinaireMinus(Expression leftExpresion, Expression rightExpresion) {
		super(leftExpresion, rightExpresion);
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitBinarySubtraction(this);
    }

}
