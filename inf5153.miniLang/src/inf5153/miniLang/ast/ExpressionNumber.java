package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;


/**
 * Expression correspondant à un nombre (entier) 
 *  
 * @author elhachemi Alikacem
 */

public class ExpressionNumber extends Expression {
	private Integer value ;

	public ExpressionNumber(Integer value) {
		super();
		this.value = value;
		setValue(value);
	} 
	
	
	@Override
    public Integer intValue() {
        return value;
    }
	
	public String getValueAsString() {
	    return Integer.toString(value);
	}

	
	
	public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitNumber(this);
    }
}
