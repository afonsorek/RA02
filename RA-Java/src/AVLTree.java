class NodeA {
    int key, height;
    NodeA left, right;

    public NodeA(int key) {
        this.key = key;
        height = 1;
        left = right = null;
    }
}

public class AVLTree {
    NodeA root;

    public AVLTree() {
        root = null;
    }

    int height(NodeA node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int balanceFactor(NodeA node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    NodeA rotateRight(NodeA node) {
        NodeA left = node.left;
        NodeA rightOfLeft = left.right;

        left.right = node;
        node.left = rightOfLeft;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.height = Math.max(height(left.left), height(left.right)) + 1;

        return left;
    }

    NodeA rotateLeft(NodeA node) {
        NodeA right = node.right;
        NodeA leftOfRight = right.left;

        right.left = node;
        node.right = leftOfRight;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        right.height = Math.max(height(right.left), height(right.right)) + 1;

        return right;
    }

    NodeA insert(NodeA node, int key) {
        if (node == null)
            return new NodeA(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balanceFactor(node);

        if (balance > 1) {
            if (key < node.left.key)
                return rotateRight(node);
            else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (key > node.right.key)
                return rotateLeft(node);
            else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    void insert(int key) {
        root = insert(root, key);
    }

    NodeA minValueNode(NodeA node) {
        NodeA current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    NodeA deleteNode(NodeA root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                NodeA temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                NodeA temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = balanceFactor(root);

        if (balance > 1) {
            if (balanceFactor(root.left) >= 0)
                return rotateRight(root);
            else {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }

        if (balance < -1) {
            if (balanceFactor(root.right) <= 0)
                return rotateLeft(root);
            else {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }

        return root;
    }

    void delete(int key) {
        root = deleteNode(root, key);
    }

    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(NodeA root, int key) {
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

    void printRec(NodeA node, String prefix, boolean isLeft) {
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
