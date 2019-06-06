package tools.mdsd.characteristics.services.node;

public interface NodeProducer<NodeType> {
    <T> Node<T> createNode(NodeFactory factory, Node<T> nestedNode);
}
