/**
 * Suraj Sharma
 * Id # 109606910
 * Homework 4 - Trees
 * <p/>
 * <p/>
 * This class handles the nodes for tree.
 * I have introduced the array
 * so that the number of nodes it can hold can vary.
 * It can hold any number of nodes.
 * I introduced an extra TreeNode as parent
 * which in traversing in reverse direction
 */
public class TreeNode {

    public static final int CAPACITY = 9;

    private TreeNode[] nodes = new TreeNode[CAPACITY];

    private TreeNode parent;

    private String label;
    private String message;
    private String prompt;


    public TreeNode() {
    }

    /**
     * This is a custom constructor for the tree.
     *
     * @param label   is a one-word string with no spaces that is used during the creation of a tree.
     *                It determines the location of a node in the tree.
     *                Note that labels do not have to be in any specific format,
     *                and they will not necessarily be made of numbers and dashes.
     * @param prompt  is a string that will be printed to the screen as a choice or answer.
     * @param message is a string that will be displayed to the screen as either question
     *                or if the node is a leaf node, as a final message.
     *                All strings (label, prompt, message) in the input file will be no longer
     *                than 60 characters so that they will not take up more than one line.
     */
    public TreeNode(String label, String prompt, String message) {
        this.label = label;
        this.message = message;
        this.prompt = prompt;

        for (TreeNode node : nodes)
            node = null;
    }

    /**
     * This method checks whether the node has any room for addition of more nodes.
     *
     * @return True or false depending if it hs space
     */
    public boolean isFull() {
        for (TreeNode node : nodes)
            if (node == null)
                return false;

        return true;
    }

    /**
     * This is a accessor method for a node at certain position.
     *
     * @param i The position in the array of the node
     * @return The specific node you want to access
     */
    public TreeNode getNode(int i) {
        return nodes[i];
    }

    /**
     * This is a mutator method for the nodes.
     *
     * @param node The value you want to set the node to
     * @param i    The current position of the node you want to mutate.
     */
    public void setNode(TreeNode node, int i) {
        nodes[i] = node;
    }

    /**
     * Accessor method for the label
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * The mutator method for label
     *
     * @param label sets it to this value.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Accessor method for the message
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * The mutator method for label
     *
     * @param message sets it to this value.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Accessor method for the prompt
     *
     * @return the prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * The mutator method for label
     *
     * @param prompt sets it to this value.
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Checks whether the node is a leaf or not
     *
     * @return Whether leaf or not
     */
    public boolean isLeaf() {
        return ((nodes[0] == null));
    }

    /**
     * Accesor method for the parent of the node
     *
     * @return returns the reference to the parent
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * Mutator method for the parent
     *
     * @param parent
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * This method performs preorder traversal
     * on the tree.
     */
    public void preOrder() {
        System.out.println(label);
        System.out.println(prompt);
        System.out.println(message);


        for (TreeNode node : nodes)
            if (node != null)
                node.preOrder();

    }

    /**
     * Returns a reference to the TreeNode that has the given label.
     * The return value is null if the label is not found.
     *
     * @param label
     * @return The reference to the correct node.
     */
    public TreeNode getNodeReference(String label) {

        if (this.getLabel().compareTo(label) == 0) {
            return this;
        } else {

            TreeNode cursor = null;
            for (int i = 0; i < CAPACITY; i++) {
                if (cursor == null && nodes[i] != null) {
                    cursor = nodes[i].getNodeReference(label);
                }
            }
            return cursor;
        }
    }


}

