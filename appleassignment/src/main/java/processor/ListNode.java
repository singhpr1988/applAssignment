package processor;

/**
 * Created by prateekraj on 15/6/20.
 */
public class ListNode {

    private String itemName;
    private ListNode previous;
    private ListNode next;

    public ListNode(String itemName) {
        this.itemName = itemName;
    }

    public ListNode getPrevious() {
        return previous;
    }

    public void setPrevious(ListNode previous) {
        this.previous = previous;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
