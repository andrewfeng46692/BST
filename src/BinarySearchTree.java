import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {
    /**
     * Node class representing a node in the BST
     */
    private static class Node<T> {
        T value;
        Node<T> left, right;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return value;
        }
    }

    private Node<T> root;
    private int size;
    private int height;

    public T getroot() {
        return (root != null) ? root.getValue() : null;
    }

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
        this.height = 0;
    }

    /**
     * Insert a new value into the BST
     *
     * @param value The value to insert
     */
    public void insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot insert null values.");
        }
        root = insertRecursive(root, value);
        height = getHeightRecursive(root); // Update height
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) {
            size++; // Increment size when a new node is created
            return new Node<>(value);
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, value);
        }
        // If value is equal, do nothing (duplicates not allowed)

        return node;
    }

    /**
     * Remove a value from the BST
     *
     * @param value The value to remove
     * @return true if value was found and removed, false otherwise
     */
    public boolean remove(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot remove null values.");
        }
        if (!contains(value)) {
            return false; // Value not found, nothing to remove
        }
        root = removeRecursive(root, value);

        size--;
        return true;
    }

    private Node<T> removeRecursive(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        int cmp = value.compareTo(node.value);

        if (cmp < 0) {
            node.left = removeRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = removeRecursive(node.right, value);
        } else {
            // Case 1: No children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: One child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Case 3: Two children - Find the in-order successor (smallest in right subtree)
            Node<T> successor = getMinNode(node.right);
            node.value = successor.value; // Copy successor's value
            node.right = removeRecursive(node.right, successor.value); // Remove successor node
        }
        return node;
    }

    /**
     * Helper method to find the smallest node in a given subtree.
     */
    private Node<T> getMinNode(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    /**
     * Search for a value in the BST
     *
     * @param value The value to search for
     * @return true if value exists in the tree, false otherwise
     */
    public boolean contains(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot search for null values in the BST.");
        }
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (node == null) {
            return false;
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            return containsRecursive(node.left, value);
        } else if (cmp > 0) {
            return containsRecursive(node.right, value);
        }
        return true; // Found the value
    }


    /**
     * Get the minimum value in the BST
     *
     * @return The minimum value
     * @throws NoSuchElementException if tree is empty
     */
    public T getMin() {
        if (root == null) {
            throw new NoSuchElementException("The tree is empty.");
        }
        return getMinValue(root);
    }

    private T getMinValue(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    /**
     * Get the maximum value in the BST
     *
     * @return The maximum value
     * @throws NoSuchElementException if tree is empty
     */
    public T getMax() {
        if (root == null) {
            throw new NoSuchElementException("The tree is empty.");
        }
        return getMaxValue(root);
    }

    private T getMaxValue(Node<T> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    /**
     * Perform an inorder traversal of the BST
     *
     * @return A list containing the values in inorder
     */
    public List<T> inorderTraversal() {
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        inorderRecursive(node.left, result);
        result.add(node.value);
        inorderRecursive(node.right, result);
    }

    /**
     * Perform a preorder traversal of the BST
     *
     * @return A list containing the values in preorder
     */
    public List<T> preorderTraversal() {
        List<T> result = new ArrayList<>();
        if (root == null) {
            return result; // Return empty list for empty tree
        }

        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            result.add(current.value); // Visit the node

            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }

        return result;
    }


    /**
     * Perform a postorder traversal of the BST
     *
     * @return A list containing the values in postorder
     */
    public List<T> postorderTraversal() {
        List<T> result = new ArrayList<>();
        Stack<Node<T>> stack1 = new Stack<>();
        Stack<T> stack2 = new Stack<>();
        if (root != null) {
            stack1.push(root);
        }

        while (!stack1.isEmpty()) {
            Node<T> node = stack1.pop();
            stack2.push(node.value);

            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        while (!stack2.isEmpty()) {
            result.add(stack2.pop());
        }
        return result;
    }
    /**
     * Get the height of the BST
     *
     * @return The height of the tree (-1 for empty tree)
     */
    public int getHeight() {
        return getHeightRecursive(root);  // Always compute height dynamically
    }

    private int getHeightRecursive(Node<T> node) {
        if (node == null) {
            return -1; // Return -1 for an empty tree
        }
        return 1 + Math.max(getHeightRecursive(node.left), getHeightRecursive(node.right));
    }


    /**
     * Get the size (number of nodes) in the BST
     *
     * @return The total number of nodes
     */
    public int getSize() {
        return size;
    }

    /**
     * Check if the BST is empty
     *
     * @return true if the tree has no nodes, false otherwise
     */
    public boolean isEmpty() {
        return root == null || size == 0; // Directly check root
    }

    /**
     * Clear all nodes from the BST
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Check if the BST is valid (adheres to BST rules)
     *
     * @return true if the tree is a valid BST, false otherwise
     */
    public boolean isValidBST() {
        return isValidBSTRecursive(root, null, null);
    }

    private boolean isValidBSTRecursive(Node<T> node, T min, T max) {
        if (node == null) {
            return true; // An empty tree is a valid BST
        }

        // Check if the node violates the BST property
        if ((min != null && node.value.compareTo(min) <= 0) || (max != null && node.value.compareTo(max) >= 0)) {
            return false; // Invalid BST
        }

        // Recursively check left and right subtrees with updated constraints
        return isValidBSTRecursive(node.left, min, node.value) &&
                isValidBSTRecursive(node.right, node.value, max);
    }

}
