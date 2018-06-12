public class DemoBinTree {
        public static void main(String[] args) {
                // Alright. Now it is time to build the binary tree.

                // The point is, we can pass any type of object to the circles of binary tree.
                // Because we, "Generated". So cool!

                BinaryTreeNode<Object> root = new BinaryTreeNodeImpl<>();
                BinaryTreeNode<Object> le = new BinaryTreeNodeImpl<>();
                BinaryTreeNode<Object> ri = new BinaryTreeNodeImpl<>();
                BinaryTreeNode<Object> lele = new BinaryTreeNodeImpl<>();
                BinaryTreeNode<Object> leri = new BinaryTreeNodeImpl<>();
                BinaryTreeNode<Object> lerile = new BinaryTreeNodeImpl<>();
                BinaryTreeNode<Object> leriri = new BinaryTreeNodeImpl<>();

                // Circles of binary tree are being created and evaluated BUT not related to each other yet:
                root.setValue(23);
                le.setValue(7);
                ri.setValue(12);
                lele.setValue(2);
                leri.setValue("Bu da Bonuslu STRING");
                lerile.setValue(5);

                // Here, we set up the relations between the 'branches', 'circles' ... you name it!
                root.setLeft(le);
                root.setRight(ri);
                le.setLeft(lele);
                le.setRight(leri);
                leri.setRight(leriri);
                leri.setLeft(lerile);

                // Time to roll out!
                System.out.println(root.getLeft().getRight().getLeft().getValue());
                System.out.println(root.getLeft().getRight().getValue());
                System.out.println(leri.getParent().getValue());
        }
}
