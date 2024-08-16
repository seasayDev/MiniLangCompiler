package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

/**
 * 
 * Expression : une constante chaine de caractères 
 *  
 * @author elhachemi Alikacem
 */
public class ExpressionString  extends Expression {
	private String value ;

	public ExpressionString(String value) {
		this.value = value;
	} 
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitString(this);
    }
	
}
