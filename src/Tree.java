public class Tree {
    Node root;

    class Node {
        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color {
        BLACK,
        RED
    }

    public void insert(int value) {
        if (root != null) {
            insert(root, value);
            root = balance(root);
        } else {
            root = new Node();
            root.value = value;
        }
        root.color = Color.BLACK;
    }

    private void insert(Node node, int value) {
        if (node.value != value) {
            if (node.value < value) {
                if (node.right == null) {
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = Color.RED;
                } else {
                    insert(node.right, value);
                    node.right = balance(node.right);
                }
            } else {
                if (node.left == null) {
                    node.left = new Node();
                    node.left.value = value;
                } else {
                    insert(node.left, value);
                    node.left = balance(node.left);
                }

            }
        }
    }

    public Node find(int value) {
        return find(root, value);
    }

    private Node find(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        if (node.value < value) {
            return find(node.right, value);
        } else {
            return find(node.left, value);
        }
    }

    private void swapColors(Node node) {
        node.color = (node.color == Color.RED ? Color.BLACK : Color.RED);
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }
    private Node leftRotate(Node node){
        Node currentNode = node.right;
        node.right = currentNode.left;
        currentNode.left = node;
        currentNode.color = node.color;
        node.color= Color.RED;
        return currentNode;
    }
    private Node rightRotate(Node node){
        Node currentNode = node.left;
        node.left = currentNode.right;
        currentNode.right = node;
        currentNode.color = node.color;
        node.color= Color.RED;
        return currentNode;
    }
    private Node balance(Node node){
        boolean flag = true;
        Node currentNode = node;
        do{
            flag = false;
            if (currentNode.right !=null &&
                    currentNode.right.color == Color.RED &&
                    (currentNode.left == null || currentNode.left.color == Color.BLACK)){
                currentNode = leftRotate(currentNode);
                flag = true;
            }
            if (currentNode.left != null &&
                    currentNode.left.color == Color.RED &&
                    currentNode.left.left != null && currentNode.left.left.color == Color.RED){
                currentNode = rightRotate(currentNode);
                flag = true;
            }
            if (currentNode.left != null &&
                    currentNode.left.color == Color.RED &&
                    currentNode.right != null &&
                    currentNode.right.color ==Color.RED){
                swapColors(currentNode);
                flag = true;
            }
        }while (flag);
        return currentNode;
    }
}