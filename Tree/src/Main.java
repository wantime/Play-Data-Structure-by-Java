public class Main {



    public static void main(String[] args) {
	// write your code here
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 5; i++)
            bst.add(i);

        bst.inOrder();
        System.out.println(bst.getSize());
        bst.remove(2);

        bst.inOrder();
        System.out.println(bst.getSize());
    }
}
