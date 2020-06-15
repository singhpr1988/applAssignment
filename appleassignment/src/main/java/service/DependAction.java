package service;

import commands.Command;
import processor.Node;

import java.util.*;

/**
 * Created by prateekraj on 15/6/20.
 */
public class DependAction extends AbstractAction {

    public DependAction() {
    }

    public DependAction(CentralService centralService) {
        super(centralService);
    }

    public String performAction(List<String> itemNames, String statement) {
        String commandName = itemNames.get(0);
        if (Command.DEPEND.getCommandName().equals(commandName)) {
            super.changeStatement(statement);
            itemNames.remove(0);
            Node parentNode = null;
            for (int i = 0; i < itemNames.size(); i++) {
                String itemName = itemNames.get(i);
                if (!super.getCentralService().fetchAllNodesMap().keySet().contains(itemName)) {
                    Node node = new Node(itemName);
                    if (i == 0) {
                        parentNode = node;
                    }
                    super.getCentralService().fetchAllNodesMap().put(node.getItemName(), node);
                    if (i != 0) {
                        Set<Node> parentNodes = node.getParentNodes();
                        Set<Node> childNodes = parentNode.getChildNodes();
                        if (parentNodes == null) {
                            parentNodes = new LinkedHashSet<Node>();
                        }
                        parentNodes.add(parentNode);
                        node.setParentNodes(parentNodes);

                        if (childNodes == null) {
                            childNodes = new LinkedHashSet<Node>();
                        }
                        childNodes.add(node);
                        parentNode.setChildNodes(childNodes);
                    }
                } else {
                    parentNode = super.getCentralService().fetchAllNodesMap().get(itemNames.get(0));
                    if (i != 0) {
                        Set<Node> parentNodes = super.getCentralService().fetchAllNodesMap().get(itemName).getParentNodes();
                        Set<Node> childNodes = parentNode.getChildNodes();
                        parentNodes.add(parentNode);
                        super.getCentralService().fetchAllNodesMap().get(itemName).setParentNodes(parentNodes);

                        childNodes.add(super.getCentralService().fetchAllNodesMap().get(itemName));
                        parentNode.setChildNodes(childNodes);
                    }
                }
            }
        } else {
            return super.getNextAction().performAction(itemNames, statement);
        }
        return this.getStatement();
    }
}
