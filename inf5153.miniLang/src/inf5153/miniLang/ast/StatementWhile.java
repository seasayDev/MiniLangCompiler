package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;


/**
 * Boucle While 
 * 
 * @author elhachemi Alikacem
 */
public class StatementWhile  extends Statement {
	private ExpressionComparaison condition ; 
	private Block blockWhile ;
	
	
	public StatementWhile(ExpressionComparaison condition, Block blockWhile) {
		super();
		this.condition = condition;
		this.blockWhile = blockWhile;
	} 
	
	
	public ExpressionComparaison getCondition() {
		return condition;
	}
	
	public Block getBlockWhile() {
		return blockWhile;
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitWhile(this);
    }
}
