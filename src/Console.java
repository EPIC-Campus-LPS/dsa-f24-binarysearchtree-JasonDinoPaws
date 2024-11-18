import jdk.jshell.spi.ExecutionControl;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class Console {
    private static BST Bin = null;
    private static String option;
    private static Scanner scan = new Scanner(System.in);


    /**
     * <pre>
     *     Asks for a file name
     *     reads the reads the file
     *          checks every line if the line can be put into the BST
     *               Adds it
     *
     *          Checks if it can do Next line
     *          closes file
     * </pre>
     */
    private static void LoadFile()
    {
        System.out.println("----------------------------------");
        System.out.print("Type a file name that full of "+option+": ");
        String input = scan.nextLine();

        try
        {
            File reader = new File(input);
            Scanner infile = new Scanner(reader);

            String line = infile.nextLine();
            while (true)
            {
                try {
                    if (option.equals("int"))
                        Bin.add(Integer.parseInt(line));
                    else if (option.equals("double"))
                        Bin.add(Double.parseDouble(line));
                    else
                        Bin.add(line);
                }
                catch (Exception E)
                {
                    System.out.println(line+" Not a "+option);
                }

                try {
                    line = infile.nextLine();
                }
                catch (NoSuchElementException E)
                {
                    break;
                }
            }

            infile.close();
        }
        catch (FileNotFoundException E)
        {
            System.out.println("Something wrong with file "+E);
        }
    }

    /**
     * <pre>
     *     ---- while true ----
     *     Asks to put in a String as input
     *
     *     if the length of input is 0
     *          exits
     *     else
     *          checks if input can go into the BST
     *              adds it to the BST
     * </pre>
     */
    private static void AddValue()
    {
        while (true)
        {
            System.out.println("----------------------------------");
            System.out.println("Leave blank to exit");
            System.out.print("Type a "+option+": ");
            String input = scan.nextLine();

            if (input.length() == 0)
                break;
            else
            {
                try {
                    if (option.equals("int"))
                        Bin.add(Integer.parseInt(input));
                    else if (option.equals("double"))
                        Bin.add(Double.parseDouble(input));
                    else
                        Bin.add(input);
                }
                catch (Exception E)
                {
                    System.out.println("Not a "+option);
                }
            }
        }
    }

    /**
     * <pre>
     *     ---- while true ----
     *     Asks to put in a String as input
     *
     *     if the length of input is 0
     *          exits
     *     else
     *          checks if input can go into the BST
     *              Kills its
     * </pre>
     */
    private static void DeleteValue()
    {
        while (true)
        {
            System.out.println("----------------------------------");
            System.out.println("Leave blank to exit");
            System.out.print("Type a "+option+": ");
            String input = scan.nextLine();

            if (input.length() == 0)
                break;
            else
            {
                try {
                    if (option.equals("int"))
                        Bin.delete(Integer.parseInt(input));
                    else if (option.equals("double"))
                        Bin.delete(Double.parseDouble(input));
                    else
                        Bin.delete(input);
                }
                catch (Exception E)
                {
                    System.out.println("Not a "+option);
                }
            }
        }
    }

    /**
     * <pre>
     *     ---- while true ----
     *     Asks to put in a String as input
     *
     *     if the length of input is 0
     *          exits
     *     else
     *          checks if input can go into the BST
     *          returns if the BST contains the value
     * </pre>
     */
    private static void Contains()
    {
        while (true)
        {
            System.out.println("----------------------------------");
            System.out.println("Leave blank to exit");
            System.out.print("Type to fina a value: ");
            String input = scan.nextLine();

            if (input.length() == 0)
                break;
            else
            {
                boolean val = false;
                try {
                    if (option.equals("int"))
                        val = Bin.contains(Integer.parseInt(input));
                    else if (option.equals("double"))
                        val = Bin.contains(Double.parseDouble(input));
                    else
                        val = Bin.contains(input);
                }
                catch (Exception E)
                {
                    System.out.println("Not a "+option);
                }
                System.out.println("Tree contains "+input+": "+val);
            }
        }
    }

    /**
     * <pre>
     *     Prints preorder
     *     Prints Inorder
     *     Prints Postorder
     * </pre>
     */
    private static void Print()
    {
                Bin.printPreorder();
                Bin.printInorder();
                Bin.printPostorder();
    }

    /**
     * <pre>
     *     Prints Height
     *     Prints Nodes
     *     Prints Leaf Nodes
     * </pre>
     */
    private static void Stats()
    {
        System.out.println("Height: "+Bin.getHeight());
        System.out.println("Nodes: "+Bin.countNodes());
        System.out.println("Leaf Nodes: "+Bin.countLeafNodes());
    }

    /** Deletes the root and sets all values to 0 */
    private static void Clear()
    {
        Bin.DeleteAll();
    }

    /**
     * <pre>
     *     Prints Stats
     *     Prints Pre,In,Post
     * </pre>
     */
    private static void All()
    {
        System.out.println("----------------------------------");
        Stats();
        Print();
    }

    /**
     * <pre>
     *     ---- while true ----
     *     Asks what type you want the BST to be (int, double, string, char)
     *     ends if its valid
     *     ---- end  ----
     *
     *     ---- while true ----
     *     Asks what you want (file, add, delete, contains, Prints orders, Stats, All)
     *     </pre>
     */
    public static void main(String[] args) {
        while (true)
        {
            System.out.println("----------------------------------");
            System.out.println("Choose a type for a Binary search tree");
            System.out.println("#. Option");
            System.out.println("1. int");
            System.out.println("2. double");
            System.out.println("3. String");
            System.out.println("4. Character");
            System.out.println("5. LEAVE");
            System.out.print("Type a number: ");
            String input = scan.nextLine();

            if (input.equals("1")) {
                Bin = new BST<Integer>();
                option = "int";
            }
            else if (input.equals("2")){
                Bin = new BST<Double>();
                option = "double";
            }
            else if (input.equals("3")){
                Bin = new BST<String>();
                option = "string";
            }
            else if (input.equals("4")){
                Bin = new BST<Character>();
                option = "character";
            }
            else if (input.equals("5"))
                return;

            if (Bin != null)
                break;;
        }

        while (true)
        {
            System.out.println("----------------------------------");
            System.out.println("#. Option");
            System.out.println("1. Fill the tree from a file");
            System.out.println("2. Add a value to tree");
            System.out.println("3. Delete a value from the tree");
            System.out.println("4. See if tree contains a value");
            System.out.println("5. Test traversals (pre, in, post)");
            System.out.println("6. Print stats (nodes, leaf nodes, height");
            System.out.println("7. Clear the tree");
            System.out.println("8. Print all information");
            System.out.println("9. LEAVE");
            System.out.print("Type a number: ");
            String input = scan.nextLine();

            if (input.equals("1"))
                LoadFile();
            else if (input.equals("2"))
                AddValue();
            else if (input.equals("3"))
                DeleteValue();
            else if (input.equals("4"))
                Contains();
            else if (input.equals("5"))
                Print();
            else if (input.equals("6"))
                Stats();
            else if (input.equals("7"))
                Clear();
            else if (input.equals("8"))
                All();
            else if (input.equals("9"))
                return;
        }
    }
}
