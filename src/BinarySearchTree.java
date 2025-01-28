import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Insert a new value into the BST
     * @param value The value to insert
     */
    public void insert(T value) {
        root = insertRecursive(root, value);
        size++;
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node ==null) {
            return new Node<>(value);
        }
        if (value.compareTo(node.value) < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insertRecursive(node.right, value);
        }
        return node; // duplicates no
    }

    /**
     * Remove a value from the BST
     * @param value The value to remove
     * @return true if value was found and removed, false otherwise
     */
    public boolean remove(T value) {
        if (contains(value)) {
            root = removeRecursive(root, value);
            size--;
            return true;
        }
        return false;
    }

    private Node<T> removeRecursive(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (value.compareTo(node.value) < 0) {
            node.left = removeRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = removeRecursive(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            T minValue = getMinValue(node.right);
            node.value = minValue;
            node.right = removeRecursive(node.right, minValue);
        }
        return node;
    }

    private T getMinValue(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    /**
     * Search for a value in the BST
     * @param value The value to search for
     * @return true if value exists in the tree, false otherwise
     */
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        if (value.compareTo(node.value) < 0) {
            return containsRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            return containsRecursive(node.right, value);
        }
        return true;
    }

    /**
     * Get the minimum value in the BST
     * @return The minimum value, or null if tree is empty
     */
    public T getMin() {
        return root == null ? null : getMinValue(root);
    }

    /**
     * Get the maximum value in the BST
     * @return The maximum value, or null if tree is empty
     */
    public T getMax() {
        if (root == null) return null;
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    /**
     * Perform an inorder traversal of the BST
     * @return A list containing the values in inorder
     */
    public List<T> inorderTraversal() {
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(Node<T> node, List<T> result) {
        if (node != null) {
            inorderRecursive(node.left, result);
            result.add(node.value);
            inorderRecursive(node.right, result);
        }
    }

    /**
     * Perform a preorder traversal of the BST
     * @return A list containing the values in preorder
     */
    public List<T> preorderTraversal() {
        List<T> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    private void preorderRecursive(Node<T> node, List<T> result) {
        if (node != null){
            result.add(node.value);
            preorderRecursive(node.left, result);
            preorderRecursive(node.right, result);
        }
    }

    /**
     * Perform a postorder traversal of the BST
     * @return A list containing the values in postorder
     */
    public List<T> postorderTraversal() {
        List<T> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }

    private void postorderRecursive(Node<T> node, List<T> result) {
        if (node != null) {
            postorderRecursive(node.left, result);
            postorderRecursive(node.right, result);
            result.add(node.value);
        }
    }

    /**
     * Get the height of the BST
     * @return The height of the tree (-1 for empty tree)
     */
    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(Node<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Get the size (number of nodes) in the BST
     * @return The total number of nodes
     */
    public int getSize() {
        return size;
    }

    /**
     * Check if the BST is empty
     * @return true if the tree has no nodes, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Clear all nodes from the BST
     */
    public void clear() {
        root = null;
        size = 0;
    }

    //Should return true if adheres to BST rules, false if otherwise
    public boolean isValidBST() {
        return isValidBSTRecursive(root, null, null);
    }

    private boolean isValidBSTRecursive(Node<T> node, T min, T max) {
        if (node == null) {
            return true;
        }
        if ((min != null && node.value.compareTo(min) <= 0) || (max != null && node.value.compareTo(max) >= 0)) {
            return false;
        }
        return isValidBSTRecursive(node.left, min, node.value) && isValidBSTRecursive(node.right, node.value, max);
    }
}