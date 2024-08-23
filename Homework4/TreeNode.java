package Homework4;

/**
 * TreeNode holds the information about the node as well as containing references to the children that this parent has.
 * @author Sean Xia, SBU ID 113181409, sean.xia@stonybrook.edu, HW#4, CSE214, R30, TAs: Charles Clark, Amogh Joshi, Sharfuddin Mohammed, Vinayak Shenoy.
 */

public class TreeNode {
    private TreeNode[] children = new TreeNode[3];

    private String label;
    private String message;
    private String prompt;

    /**
     * Default constructor of the TreeNode class.
     */
    public TreeNode() {
        this.label = null;
        this.message = null;
        this.prompt = null;
    }

    /**
     * Overloaded constructor that sets all the information about the TreeNode, and makes sure that the preconditions are valid.
     * @param label
     * @param message
     * @param prompt
     */
    public TreeNode(String label, String message, String prompt) {
        this.label = label;
        this.message = message;
        this.prompt = prompt;
    }

    /**
     * This method sets the child.
     * @param child
     * @param index
     */
    public void setChild(TreeNode child, int index) {
        this.children[index] = child;
    }

    /**
     * This method sets the label.
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * This method sets the message.
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method sets the prompt.
     * @param prompt
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * This method gets the children.
     * @return children
     */
    public TreeNode[] getChildren() {
        return children;
    }

    /**
     * This method gets a certain child.
     * @param index
     * @return children[index]
     */
    public TreeNode getCertainChild(int index) {
        return children[index];
    }

    /**
     * This method gets the label.
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * This method gets the message.
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method gets the prompt.
     * @return prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * This method determines if the node is leaf.
     * @return children[0] == null && children[1] == null && children[2] == null
     */
    public boolean isLeaf() {
        return children[0] == null && children[1] == null && children[2] == null;
    }

    /**
     * This method recursively gets the node that has the label indicated by the parameter.
     * @param label
     * @return reference
     */
    public TreeNode getNodeReferenceNode(String label) {
        TreeNode reference = null;

        if(this.label.equals(label)) {
            reference = this;
        }

        if(reference == null && children[0] != null) { //Are we allowed to just do left.label? I thought label was private but there are no errors.
            reference = children[0].getNodeReferenceNode(label);
        }
        if(reference == null && children[1] != null) {
            reference = children[1].getNodeReferenceNode(label);
        }
        if(reference == null && children[2] != null) {
            reference = children[2].getNodeReferenceNode(label);
        }

        return reference;
    }

    /**
     * This method recursively prints the tree by traversing in preorder.
     */
    public void preOrderNode() {
        System.out.println("Label: " + label);
        System.out.println("Prompt: " + prompt);
        System.out.println("Message: " + message);
        System.out.println();

        if(children[0] != null) {
            children[0].preOrderNode();
        }
        if(children[1] != null) {
            children[1].preOrderNode();
        }
        if(children[2] != null) {
            children[2].preOrderNode();
        }
    }
}
