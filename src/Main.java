public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(2);
        bst.insert(8);

        System.out.println("Inorder: " + bst.inorderTraversal());
        System.out.println("Preorder: " + bst.preorderTraversal());
        System.out.println("Postorder: " + bst.postorderTraversal());
        System.out.println("Min: " + bst.getMin());
        System.out.println("Max: " + bst.getMax());
        System.out.println("Height: " + bst.getHeight());
        System.out.println("Size: " + bst.getSize());
        System.out.println("Contains 5: " + bst.contains(5));
        System.out.println("Is Valid BST: " + bst.isValidBST());
    }
}
