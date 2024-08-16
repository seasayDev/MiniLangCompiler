package inf5153.miniLang.ast;

import inf5153.miniLang.visitor.AstVisitor;
import java.util.ArrayList;
import java.util.Iterator;

public class Block implements ElementAST, Iterable<Statement> {
    private ArrayList<Statement> listStatements = new ArrayList<>();

    public Block(ArrayList<Statement> listStatements) {
        this.listStatements = listStatements;
    }

    public void addStatement(Statement statement) {
        listStatements.add(statement);
    }

    @Override
    public Iterator<Statement> iterator() {
        return listStatements.iterator();
    }

    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitBlock(this);
    }
}