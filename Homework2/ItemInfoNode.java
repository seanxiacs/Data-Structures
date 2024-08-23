package Homework2;

/**
 * ItemInfoNode is the supporting class of the ItemList class to create the doubly linked list. It wraps the ItemInfo information and ItemList accesses the data through this class.
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class ItemInfoNode {
    private ItemInfo info;
    private ItemInfoNode prev;
    private ItemInfoNode next;

    /**
     * Default constructor of the ItemInfoNode class.
     */
    public ItemInfoNode() {
    }

    /**
     * Overloaded constructor that sets all the information about the node, and makes sure that the preconditions are valid.
     * @param newInfo
     * @throws InvalidDataException
     */
    public ItemInfoNode(ItemInfo newInfo) throws InvalidDataException {
        if(newInfo == null) {
            throw new InvalidDataException("The node you are trying to make is null.\n");
        }
        else {
            this.info = newInfo;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * This method sets the info.
     * @param newInfo
     * @throws InvalidDataException
     */
    public void setInfo(ItemInfo newInfo) throws InvalidDataException {
        if(newInfo == null) {
            throw new InvalidDataException("The node you are trying to make is null.\n");
        }
        else {
            this.info = newInfo;
        }
    }

    /**
     * This method sets the previous node.
     * @param newPrev
     */
    public void setPrev(ItemInfoNode newPrev) {
        this.prev = newPrev;
    }

    /**
     * This method sets the next node.
     * @param newNext
     */
    public void setNext(ItemInfoNode newNext) {
        this.next = newNext;
    }

    /**
     * This method gets the info.
     * @return this.info
     */
    public ItemInfo getInfo() {
        return this.info;
    }

    /**
     * This method gets the previous node.
     * @return this.prev
     */
    public ItemInfoNode getPrev() {
        return this.prev;
    }

    /**
     * This method gets the next node.
     * @return
     */
    public ItemInfoNode getNext() {
        return this.next;
    }
}
