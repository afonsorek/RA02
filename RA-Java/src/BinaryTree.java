class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
        left = right = null;
    }
}

public class BinaryTree {
    Node root;

    public BinaryTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    void remove(int key) {
        root = removeRec(root, key);
    }

    Node removeRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = removeRec(root.left, key);
        else if (key > root.key)
            root.right = removeRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);
            root.right = removeRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key) {
        if (root == null)
            return false;

        if (root.key == key)
            return true;

        //If the node value is smaller, it searchs to the lef, if not it  searchs to the right
        return key < root.key ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    void print() {
        printRec(root, "", true);
    }

    void printRec(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.key);

            String newPrefix = prefix + (isLeft ? "│   " : "    ");
            printRec(node.right, newPrefix, true);
            printRec(node.left, newPrefix, false);
        } else {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "Vazio");
        }
    }
}

