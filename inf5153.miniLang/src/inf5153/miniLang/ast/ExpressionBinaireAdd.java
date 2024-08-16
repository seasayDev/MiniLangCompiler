package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

/**
 * Expression binaire : addition 
 * 
 * @author elhachemi Alikacem
 * 
 */
public class ExpressionBinaireAdd extends ExpressionBinaire {

	public ExpressionBinaireAdd(Expression leftExpresion, Expression rightExpresion) {
		super(leftExpresion, rightExpresion);
	}	
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitBinaryAddition(this);
    }
}
