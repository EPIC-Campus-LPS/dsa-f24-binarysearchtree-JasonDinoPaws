public class BST<E extends Comparable<E>> {
    private TreeNode<E> root = null;
    private int count = 0;


    public BST(){}


    /**
     * @param value to add
     * <pre>
     *       creates a new node and sets the value to the given value and left and right ot null
     *       if root is null sets the new to root
     *       else
     *              creats a kasr var and sets it to root
     *              while true
     *                  creates a comapre to and compares last to the value
     *
     *                  if compare is grater than or equal to 0
     *                      if last has no left child
     *                          sets the new node to the last left child
     *                      sets last to the left child
     *                 if compare is less than 0
     *                         if last has no right child
     *                         sets the new node to the last right child
     *                      sets last to the right child
     *
     *
     *        adds 1 to count
     * </pre>
     */
    public void add(E value) {
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
                    if (last.getLeftChild() == null) {
                        last.setLeftChild(Created);
                        break;
                    }
                    last = last.getLeftChild();
                }
                else if (compare == -1){
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
    /**
     * @param value the value to delete
     *              <pre>
     *              ---- readd ----
     *              if temproot isnt null
     *                  removes 1 from count
     *                  adds temproot to value
     *                  calls readd for left and right child
     *
     *              ---- delete ----
     *              creates a last node and sets it to root
     *              creates a prev node and sets it to null
     *              creates a size int and sets it to 0
     *
     *              while last is not null
     *                  creates a comapre to and compares last to the value
     *
     *                  if the compare is 1 sets prev to last, side to 1 and last to the left child
     *                  if compare is -1 sets prev to last, side to -1 and last to the right child
     *
     *                  if compare is 0
     *                      if the last is root
     *                          sets the root variable to the left veriable of root
     *                          readds all of right
     *
     *                      if last has no children kills it
     *
     *                      else
     *                          sets the prev left/right child to last depending on the side var
     *                          readds all of right
     *
     *                      minus one from count
     *                      breaks the loop
     *
     *              checks if last was null
     *                  throws a indexoutofbounds error
     *              else
     *              </pre>
     * @return the value of the last variable
     */
    public E delete(E value){
        TreeNode<E> last = root;
        TreeNode<E> prev = null;
        int side = 0;
        while (last != null)
        {
            int compare = last.compareTo(value);
            if (compare == 1) {
                prev = last;
                side = 1;
                last = last.getLeftChild();
            }
            else if (compare == -1){
                prev = last;
                side = -1;
                last = last.getRightChild();
            }
            else
            {
                if (last == root)
                {
                    if (side == 1) {
                        root = last.getLeftChild();
                        Readd(last.getRightChild());
                    }
                    else
                        root = last.getRightChild();
                }
                else if (last.getRightChild() == null && last.getLeftChild() == null) {
                    if (side == 1)
                        prev.setLeftChild(null);
                    else
                        prev.setRightChild(null);
                }
                else
                {
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

    public void DeleteAll()
    {
        root = null;
        count = 0;
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

    /**
     * @param value the value to check if it is there
     *              <pre>
     *                  ---- Contain ----
     *              takes  a temproot and a value
     *              if if temproot.compareTo is 0 return true
     *
     *              else
     *                  creates a boolean of left and right
     *                  if temroot has a left child sets left bool to value and calls it self
     *                  if temproot has a right child sets right bool to value and calls its self
     *
     *                  returns either left or right
     *              </pre>
     * @return a true or false if it is there
     */
    public boolean contains(E value) { return contain(root,value); }

    private int Height(TreeNode<E> temproot)
    {
        if (temproot != null) {
            int left = 1, right = 1;

            if (temproot.getLeftChild() != null)
                left += Height(temproot.getLeftChild());

            if (temproot.getRightChild() != null)
                right += Height(temproot.getRightChild());

            if (left > right)
                return left;
            else
                return right;
        }
        return 0;
    }

    /**
     * <pre>
     *     ---- Height ----
     *     Takes a Temproot
     *
     *     creates a left and right int variables and sets it to 1
     *
     *     if left has child calls height with left child and adds it to left var
     *     if right has child calls height with right child and adds it to right var
     *
     *     if left is higher return left variable
     *     else return right variable
     * </pre>
     * @return the height
     */
    public int getHeight(){ return Height(root)-1;}

    /**
     * @return the count of nodes
     */
    public int countNodes(){ return count; }


    private int GetLeafs(TreeNode<E> temproot)
    {
        if (temproot != null)
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
        return 0;
    }

    /**
     * <pre>
     *     ---- Getleafs ---
     *     takes a temp root
     *     creates a int var called leaf and sets it to 0
     *     if temproot doesnt have a left and right child add one to leaf
     *     else
     *          if temproot has left child calls Getleafs with the left child and adds it on to leafs
     *          if temproot has a right child calls Getleafs with the right child and adds it on to leafs
     *
     *     returns the leafs
     * </pre>
     * @return the amount of leafs
     */
    public int countLeafNodes(){ return GetLeafs(root); }



    private void InOrder(TreeNode<E> temproot)
    {
        if (temproot != null)
        {
            if (temproot.getLeftChild() != null)
                InOrder(temproot.getLeftChild());

            System.out.print(temproot.getValue()+", ");

            if (temproot.getRightChild() != null)
                InOrder(temproot.getRightChild());
        }
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
        if (root != null)
            InOrder(root);
        System.out.println("\b");
    }



    private void Preorder(TreeNode<E> temproot)
    {
        if (temproot != null) {
            System.out.print(temproot.getValue() + ", ");

            if (temproot.getLeftChild() != null)
                Preorder(temproot.getLeftChild());

            if (temproot.getRightChild() != null)
                Preorder(temproot.getRightChild());
        }
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
        if (root != null)
            Preorder(root);
        System.out.println("\b");
    }


    private void Postorder(TreeNode<E> temproot)
    {
        if (temproot != null) {
            if (temproot.getLeftChild() != null)
                Postorder(temproot.getLeftChild());

            if (temproot.getRightChild() != null)
                Postorder(temproot.getRightChild());

            System.out.print(temproot.getValue() + ", ");
        }
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
        if (root != null)
            Postorder(root);
        System.out.println("\b");
    }
}
