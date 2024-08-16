package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

/**
 * 
 * Une variable  
 *  
 * @author elhachemi Alikacem
 */
public class ExpressionVariable extends Expression {
	private String varName ;

	public ExpressionVariable(String varName) {
		super();
		this.varName = varName;
	}  
	
	public String getVarName() {
		return varName;
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitVariable(this);
    }

}
