class ArvoreBuscaQ1 {
    private Node root;
    public void inserir(int elemento) {
        if (root == null) {
            root = new Node(elemento);
        } else {
            inserindo(elemento, root);
        }
    }
    private void inserindo(int value, Node node) {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                inserindo(value, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                inserindo(value, node.right);
            }
        }
    }


    private static class Node {
        public int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}