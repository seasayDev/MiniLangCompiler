package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;


/**
 * Modèlise l'instruction Print 
 * 
 *@author elhachemi Alikacem 
 */
public class StatementPrint  extends Statement {
	private Expression expression ;

	public StatementPrint(Expression expression) {
		super();
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitPrint(this);
    }
	
}
