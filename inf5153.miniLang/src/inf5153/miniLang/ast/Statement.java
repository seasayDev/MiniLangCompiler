package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

/**
 * Classe générale des instructions 
 * 
 * @author elhachemi Alikacem
 */
public abstract class Statement implements ElementAST  {
	public abstract <T> T accept(AstVisitor<T> visitor);

}
