// The interface that holds the methods. The methods. Abstract methods.

public interface BinaryTreeNode<T> {
        public void setValue(T value);
        public void setParent(BinaryTreeNode<T> parent);
        public void setLeft(BinaryTreeNode<T> left);
        public void setRight(BinaryTreeNode<T> right);

        public T getValue();
        public BinaryTreeNode<T> getParent();
        public BinaryTreeNode<T> getRight();
        public BinaryTreeNode<T> getLeft();
}
