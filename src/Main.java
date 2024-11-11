public class Main {
    public static void main(String[] args) {
        BST<Integer> num = new BST();
        System.out.println("----------------------------\nAdding\n");
        num.add(10);
        num.add(7);
        num.add(11);
        num.add(23);
        num.add(25);
        num.add(6);
        num.add(1);
        num.add(22);
        num.add(9);
        num.add(18);
        num.add(16);
        num.add(14);
        num.add(3);
        num.add(13);
        num.add(2);
        num.add(19);
        num.add(5);
        num.add(24);
        num.add(21);
        num.add(8);
        num.add(4);
        num.add(15);
        num.add(12);
        num.add(17);
        num.add(20);
        System.out.println("----------------------------\nDeleteing\n");
        try {
            num.delete(0);
        } catch (IndexOutOfBoundsException E)
        {
            System.out.println(E);
        }
        num.delete(2);
        num.delete(8);
        num.delete(16);
        num.delete(20);

        System.out.println("----------------------------\nInfomation\n");
        System.out.println("Node Count: "+num.countNodes());
        System.out.println("Node Leaf Count: "+num.countLeafNodes());
        System.out.println("Height: "+num.getHeight());
        System.out.println("Has 5: "+num.contains(5));
        System.out.println("Has 26: "+num.contains(26));
        System.out.println("Has 25: "+num.contains(25));
        num.printPreorder();
        num.printInorder();
        num.printPostorder();
    }
}