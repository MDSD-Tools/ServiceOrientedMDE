package tools.mdsd.somde.services.node;

public interface NodeProducer<NodeType> {
    <T> Node<T> createNode(NodeFactory factory, Node<T> nestedNode);
}
