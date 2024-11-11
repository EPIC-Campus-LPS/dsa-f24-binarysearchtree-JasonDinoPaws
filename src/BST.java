public class BST<E extends Comparable<E>> {
    private TreeNode<E> root = null;
    private int count = 0;


    public BST(){}


    public void add(E value) {
        System.out.println("-----------\nValue: "+value);
        TreeNode<E> Created = new TreeNode<E>(value,null,null);

        if (root == null)
            root = Created;
        else
        {
            TreeNode<E> last = root;
            while (true)
            {
                int compare = last.compareTo(value);
                if (compare > -1) {
                    System.out.println(value + " is less than " + last.getValue());
                    if (last.getLeftChild() == null) {
                        last.setLeftChild(Created);
                        break;
                    }
                    last = last.getLeftChild();
                }
                else if (compare == -1){
                    System.out.println(value + " is grater than " + last.getValue());
                    if (last.getRightChild() == null) {
                        last.setRightChild(Created);
                        break;
                    }
                    last = last.getRightChild();
                }
            }
        }
        count ++;
    }

    private void Readd(TreeNode<E> temproot)
    {
        if (temproot != null) {
            count--;
            add(temproot.getValue());
            Readd(temproot.getLeftChild());
            Readd(temproot.getRightChild());
        }
    }
    public E delete(E value){
        System.out.println("-----------\nValue: "+value);
        TreeNode<E> last = root;
        TreeNode<E> prev = null;
        int side = 0;
        while (last != null)
        {
            int compare = last.compareTo(value);
            if (compare == 1) {
                System.out.println(value + " is less than " + last.getValue());
                prev = last;
                side = 1;
                last = last.getLeftChild();
            }
            else if (compare == -1){
                System.out.println(value + " is grater than " + last.getValue());
                prev = last;
                side = -1;
                last = last.getRightChild();
            }
            else
            {
                System.out.print("Found Value: ");

                if (last.getRightChild() == null && last.getLeftChild() == null) {
                    System.out.println("KILL IT");
                    if (side == 1)
                        prev.setLeftChild(null);
                    else
                        prev.setRightChild(null);
                }
                else
                {
                    System.out.println("Redo everything");
                    if (side == 1)
                        prev.setLeftChild(last.getLeftChild());
                    else
                        prev.setRightChild(last.getLeftChild());
                    Readd(last.getRightChild());
                }
                count --;
                break;
            }
        }

        if (last == null)
            throw new IndexOutOfBoundsException(value +" is not in the BST");
        else
            return last.getValue();
    }


    private boolean contain(TreeNode<E> temproot, E value)
    {
        if (temproot.compareTo(value) == 0)
            return true;
        else
        {
            boolean left = false, right = false;
            if (temproot.getRightChild() != null)
                right = contain(temproot.getRightChild(),value);

            if (temproot.getLeftChild() != null)
                left = contain(temproot.getLeftChild(),value);

            return left || right;
        }
    }
    public boolean contains(E value) { return contain(root,value); }

    private int Height(TreeNode<E> temproot)
    {
        int left = 1,right = 1;

        if (temproot.getLeftChild() != null)
            left += Height(temproot.getLeftChild());

        if (temproot.getRightChild() != null)
            right += Height(temproot.getRightChild());

        if (left > right)
            return left;
        else
            return right;
    }
    public int getHeight(){ return Height(root)-1;}


    public int countNodes(){ return count; }


    private int GetLeafs(TreeNode<E> temproot)
    {
        int leaf = 0;
        if (temproot.getRightChild() == null && temproot.getLeftChild() == null)
            leaf ++;
        else
        {
            if (temproot.getLeftChild() != null)
                leaf += GetLeafs(temproot.getLeftChild());

            if (temproot.getRightChild() != null)
                leaf += GetLeafs(temproot.getRightChild());
        }
        return leaf;
    }
    public int countLeafNodes(){ return GetLeafs(root); }



    private void InOrder(TreeNode<E> temproot)
    {
        if (temproot.getLeftChild() != null)
            InOrder(temproot.getLeftChild());

        System.out.print(temproot.getValue()+", ");

        if (temproot.getRightChild() != null)
            InOrder(temproot.getRightChild());
    }

    /**
     * <pre>
     *     Prints "Inorder: "
     *     calls Inorder function with root
     *
     *     ---- Inorder ----
     *     Takes a temproot
     *
     *     if temproot has left Child calls Inorder with left Child
     *     prints temproot value
     *     if temproot has Right child calls Inorder with right Child
     *     ---- end ----
     *
     *     prints a new line
     * </pre>
     */
    public void printInorder(){
        System.out.print("Inorder: ");
        InOrder(root);
        System.out.println();
    }



    private void Preorder(TreeNode<E> temproot)
    {
        System.out.print(temproot.getValue()+", ");

        if (temproot.getLeftChild() != null)
            Preorder(temproot.getLeftChild());

        if (temproot.getRightChild() != null)
            Preorder(temproot.getRightChild());
    }
    /**
     * <pre>
     *     Prints "PreOrder: "
     *     calls Preorder function with root
     *
     *     ---- Preorder ----
     *     Takes a temproot
     *
     *     prints temproot value
     *     if temproot has left Child calls Preorder with left Child
     *     if temproot has Right child calls Preorder with right Child
     *     ---- end ----
     *
     *     prints a new line
     * </pre>
     */
    public void printPreorder(){
        System.out.print("Preorder: ");
        Preorder(root);
        System.out.println();
    }


    private void Postorder(TreeNode<E> temproot)
    {
        if (temproot.getLeftChild() != null)
            Postorder(temproot.getLeftChild());

        if (temproot.getRightChild() != null)
            Postorder(temproot.getRightChild());

        System.out.print(temproot.getValue()+", ");
    }
    /**
     * <pre>
     *     Prints "PostOrder: "
     *     calls Postorder function with root
     *
     *     ---- Postorder ----
     *     Takes a temproot
     *     if temproot has left Child calls Postorder with left Child
     *     if temproot has Right child calls Poastorder with right Child
     *     prints temproot value
     *     ---- end ----
     *
     *     prints a new line
     * </pre>
     */
    public void printPostorder(){
        System.out.print("Postorder: ");
        Postorder(root);
        System.out.println();
    }
}
