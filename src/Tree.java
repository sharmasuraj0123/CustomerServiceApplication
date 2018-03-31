/**
 * Suraj Sharma
 * Id # 109606910.
 * HomeWork 4 - Trees
 * <p/>
 * This class creates the tree using the treeNode references.
 * It has instance variables root , referenceId and name.
 * <p/>
 * The variables ReferenceId and name are only used for handling
 * many trees and are not required for the mandatory part.
 * The reference name will be used to represent a tree in collection of trees.
 */
public class Tree {
    private TreeNode root;
    private String ReferenceID;
    private String name;

    public Tree() {
        root = null;
        ReferenceID = "";
        name = "None";
    }

    /**
     * Checks whether the tree is empty or not
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * The mutator method for referenceID
     *
     * @return
     */
    public String getReferenceID() {
        return ReferenceID;
    }

    /**
     * Accessor method for referenceId
     *
     * @param referenceID
     */
    public void setReferenceID(String referenceID) {
        ReferenceID = referenceID;
    }

    /**
     * The mutator method for name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The mutator method for root
     *
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Accessor method for root
     *
     * @param root
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }


    /**
     * A method to add a TreeNode to the tree.
     * The location will be a child of parentLabel.
     * The child node should be left justified
     * meaning that it should first be placed in the left most TreeNode reference,
     * then the middle, then the right.
     * A return value of true indicates that the node was successfully added to the tree.
     *
     * @param label       label of the newNode
     * @param prompt      prompt of the newNode
     * @param message     message of the newNode
     * @param parentLabel label of its parent.
     * @return
     * @throws NodeFullException if the node you're trying to add is already full.
     */
    public boolean addNode(String label, String prompt,
                           String message, String parentLabel) throws NodeFullException {

        TreeNode cursor = getNodeReference(parentLabel);

        if (cursor == null)
            return false;

        else if (cursor.isFull())
            throw new NodeFullException("The node you're trying to add is currently full");

        else {

            TreeNode newNode = new TreeNode(label, prompt, message);
            newNode.setParent(cursor);

            for (int i = 0; i < TreeNode.CAPACITY; i++) {
                if (cursor.getNode(i) == null) {
                    cursor.setNode(newNode, i);
                    break;
                }
            }
            return true;
        }
    }

    /**
     * Returns a reference to the TreeNode that has the given label.
     * The return value is null if the label is not found.
     * It is also present in TreeNode class
     * Only created here to make the code a bit simpler.
     *
     * @param label
     * @return The reference to the correct node.
     */
    public TreeNode getNodeReference(String label) {


        if (root.isLeaf()) {
            if (root.getLabel().equals(label)) {
                return root;
            } else {
                return null;
            }
        } else return root.getNodeReference(label);

    }

    /**
     * This method is used to start the question and answer session.
     * The signature of this method is changed just to make the implementation easier.
     * which helps to reset the cursor.
     *
     * @param cursor
     * @returns returns the cursor
     */
    public TreeNode beginSession(TreeNode cursor) {
        cursor = root;

        System.out.println(cursor.getMessage());

        for (int i = 0; i < TreeNode.CAPACITY; i++) {
            if (cursor.getNode(i) != null) {
                System.out.println(i + 1 + " " + cursor.getNode(i).getPrompt());
            }
        }
        System.out.println("0 To Quit");

        return cursor;
    }


    /**
     * This is helper method created to make the question answer session easier.
     *
     * @param input  The choice user has picked
     * @param cursor The current node user is at.
     * @return the cursor.
     */
    public TreeNode session(int input, TreeNode cursor) {

        if (input == 10)
            if (cursor != root)
                cursor = cursor.getParent();
            else
                System.out.println("This is the first step!");
        else if (cursor.getNode(input - 1) == null)
            System.out.println("Invalid selection!");

        else {
            cursor = cursor.getNode(input - 1);
        }
        System.out.println(cursor.getMessage());

        if (!cursor.isLeaf()) {
            for (int i = 0; i < TreeNode.CAPACITY; i++) {
                if (cursor.getNode(i) != null) {
                    System.out.println(i + 1 + " " + cursor.getNode(i).getPrompt());
                }
            }
            if (cursor != root)
                System.out.println("10 To Go Back To Previous Question");

            System.out.println("0 To Quit");
        }
        return cursor;


    }

    /**
     * Another repeated method to make the implementation easier.
     */
    public void preOrder() {
        TreeNode cursor = root;
        if (root != null)
            cursor.preOrder();
        else
            System.out.println("Please load the tree before starting the session!");
    }

}
