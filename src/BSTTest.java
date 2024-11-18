import org.junit.jupiter.api.Test;

import java.security.PublicKey;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    private BST<Integer> bst = new BST<>();

    @Test
    void add() {
        bst.add(1);
        bst.add(5);
        bst.add(32);
        bst.add(61);
        bst.add(621);
        bst.add(51);
        bst.add(51);
        bst.add(16);
        bst.add(18);
        bst.add(162);
        bst.add(1332);
    }

    @Test
    void delete() {
        add();
        bst.delete(5);
        bst.delete(1);
        bst.delete(1332);
        bst.delete(16);
        bst.delete(61);

        try {
            bst.delete(2);
        }
        catch (IndexOutOfBoundsException E)
        {
            System.out.println("2 is not in BST");
        }
    }

    @Test
    void contains() {
        add();
        if (bst.contains(5))
            System.out.println("Bst has 5");
        if (bst.contains(1332))
            System.out.println("Bst has 1332");
        if (bst.contains(61))
            System.out.println("Bst has 61");
        if (bst.contains(18))
            System.out.println("Bst has 18");
        if (!bst.contains(2))
            System.out.println("Bst doesn't have 2");
    }

    @Test
    void getHeight() {
        add();

        if (bst.getHeight() == 5)
            System.out.println("Bst is the height of 5");
        else
            System.err.println("It isnt the height of 5");

    }

    @Test
    void countNodes() {
        add();
        if (bst.countNodes() == 11)
            System.out.println("Bst has 11 nodes");
        else
            System.err.println("It isnt 11 nodes");

        bst.delete(5);
        bst.delete(1);
        bst.delete(1332);
        bst.delete(16);
        bst.delete(61);

        if (bst.countNodes() == 6)
            System.out.println("Bst has 6 nodes");
        else
            System.err.println("It isnt 6 nodes");
    }

    @Test
    void countLeafNodes() {
        add();
        if (bst.countLeafNodes() == 4)
            System.out.println("Bst has 4 leafs");
        else
            System.err.println("It isnt 4 leafs");
    }
}