package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;

public class CompilationUnit implements ElementAST {
    private String filename;
    private Block block;

    public CompilationUnit(Block block) {
        this.block = block;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public Block getBlock() {
    	return block;
    }
    

    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitCompilationUnit(this);
    }
}