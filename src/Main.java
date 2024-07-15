public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(4);
        tree.insert(6);
        tree.insert(88);
        System.out.println(tree.find(4));

    }
}
