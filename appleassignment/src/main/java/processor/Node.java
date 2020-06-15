package processor;

import java.util.Set;

/**
 * Created by prateekraj on 15/6/20.
 */
public class Node {

    private String itemName;
    private boolean itemInstalled;
    private Set<Node> parentNodes;
    private Set<Node> childNodes;

    public Node(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public Set<Node> getParentNodes() {
        return parentNodes;
    }

    public void setParentNodes(Set<Node> parentNodes) {
        this.parentNodes = parentNodes;
    }

    public Set<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Set<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public boolean isItemInstalled() {
        return itemInstalled;
    }

    public void setItemInstalled(boolean itemInstalled) {
        this.itemInstalled = itemInstalled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return itemName.equals(node.itemName);
    }

    @Override
    public int hashCode() {
        return itemName.hashCode();
    }
}
