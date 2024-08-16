package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;


/**
 * Expression binaire : Division
 *  
 * @author elhachemi Alikacem
 */
public class ExpressionBinaireDiv extends ExpressionBinaire{

	public ExpressionBinaireDiv(Expression leftExpresion, Expression rightExpresion) {
		super(leftExpresion, rightExpresion);
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitBinaryDivision(this);
    }

}
