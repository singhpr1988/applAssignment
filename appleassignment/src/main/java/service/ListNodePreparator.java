package service;

import processor.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prateekraj on 15/6/20.
 */
public class ListNodePreparator {

    private ListNode head;
    private ListNode tail;
    private Map<String, ListNode> map = new HashMap<String, ListNode>();

    public void prepareChainOfListNodes(String itemName) {
        if (itemName != null) {
            if (head == null) {
                ListNode listNode = new ListNode(itemName);
                head = listNode;
                tail = head;
                map.put(itemName, head);
            } else {
                ListNode listNode = new ListNode(itemName);
                listNode.setPrevious(tail);
                tail.setNext(listNode);
                tail = tail.getNext();
                map.put(itemName, listNode);
            }
        }
    }

    public void updateListOfNodesPostDeletion(String itemName) {
        if (map.containsKey(itemName)) {
            ListNode listNode = map.get(itemName);
            if (listNode == head) {
                if (head.getNext() != null) {
                    head = head.getNext();
                } else {
                    head = null;
                    tail = null;
                }
            } else if (listNode == tail) {
                if (tail.getPrevious() != null) {
                    tail = tail.getPrevious();
                } else {
                    tail = null;
                    head = null;
                }
            } else {
                ListNode nextNode = listNode.getNext();
                ListNode previousNode = listNode.getPrevious();
                previousNode.setNext(nextNode);
                nextNode.setPrevious(previousNode);
            }
            map.remove(itemName);
        }
    }

    public ListNode getHead() {
        return head;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }

    public ListNode getTail() {
        return tail;
    }

    public void setTail(ListNode tail) {
        this.tail = tail;
    }

}
