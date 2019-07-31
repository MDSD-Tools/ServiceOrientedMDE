package tools.mdsd.somde.services.node.impl;

import tools.mdsd.somde.services.node.Literal;

public class NodeLiteralImpl<NodeType> implements Literal<NodeType> {

    private NodeType literal;

    public NodeLiteralImpl(NodeType literal) {
        this.literal = literal;
    }

    @Override
    public NodeType getNodeLiteral() {
        return literal;
    }

}
