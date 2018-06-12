// Implementer class. Everything gets a meaning in the java file. ( I wish it'd be as easy as in life )

public class BinaryTreeNodeImpl<T> implements BinaryTreeNode<T>{
        // veriaybils.
        private T value;
        private BinaryTreeNode<T> left;
        private BinaryTreeNode<T> right;
        private BinaryTreeNode<T> parent;

        // Setter Methods ( as in PDF homework, "Mutators" (?!) )
        @Override
        public void setValue(T value) {
                this.value = value;
        }
        @Override
        public void setParent(BinaryTreeNode<T> parent) {
                this.parent = parent;
        }
        @Override
        public void setLeft(BinaryTreeNode<T> left) {
                this.left = left;
                left.setParent(this);
        }
        @Override
        public void setRight(BinaryTreeNode<T> right) {
                this.right = right;
                right.setParent(this);
        }

        // Getter Methods ( ACCESSORSSS...)
        @Override
        public T getValue() {
                return value;
        }
        @Override
        public BinaryTreeNode<T> getParent() {
                return parent;
        }
        @Override
        public BinaryTreeNode<T> getRight() {
                return right;
        }
        @Override
        public BinaryTreeNode<T> getLeft() {
                return left;
        }
}
