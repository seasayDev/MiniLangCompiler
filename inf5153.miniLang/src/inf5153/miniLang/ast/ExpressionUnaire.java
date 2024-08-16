package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

/**
 * 
 * Expression unaire 
 *  
 * @author elhachemi Alikacem
 */
public class ExpressionUnaire extends Expression {
	
	private Operator operateur ; // MUST Be PLUS or MINUS
	private Expression expression ;
	
	/**
	 * 
	 * @param operateur
	 * @param expression
	 */
	public ExpressionUnaire(Operator operateur, Expression expression) {
		super();
		this.operateur = operateur;
		this.expression = expression;
	}
	
	public Operator getOperateur() {
		return operateur;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitUnary(this);
    }
}
