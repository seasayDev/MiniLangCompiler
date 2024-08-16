package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

/**
 * Représente une expression parenthésée 
 * 
 * @author elhachemi 
 */
public class ExpressionParenthesee extends Expression {
	private Expression expression ;

	public ExpressionParenthesee(Expression expression) {
		super();
		this.expression = expression;
	} 
	
	public Expression getExpression() {
		return expression;
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitParenthesized(this);
    }
}
