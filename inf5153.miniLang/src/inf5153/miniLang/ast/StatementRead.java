package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;


/**
 * Modèlise l'instruction Print
 *   
 * @author elhachemi Alikacem 
 */
public class StatementRead  extends Statement {
	private String message ; 
	private ExpressionVariable varName ;
	
	
	public StatementRead(String message, ExpressionVariable varName) {
		super();
		this.message = message;
		this.varName = varName;
	}  
	
	public ExpressionVariable getVarName() {
		return varName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitRead(this);
    }

}
