package service;

import commands.Command;
import processor.Node;

import java.util.*;

/**
 * Created by prateekraj on 15/6/20.
 */
public class DependAction implements ActionInterface {

    private Map<String, Node> parentNodes = new HashMap<String, Node>();
    private Map<String, Node> allNodes = new HashMap<String, Node>();

    private ActionInterface nextAction;

    public String performAction(List<String> itemNames, String statement) {
        String commandName = itemNames.get(0);
        if (Command.DEPEND.getCommandName().equals(commandName)) {
            itemNames.remove(0);
            for (int i = 0; i < itemNames.size(); i++) {
                String itemName = itemNames.get(i);
                Node parentNode = null;
                if (!allNodes.keySet().contains(itemName)) {
                    Node node = new Node(itemName);
                    if (i == 0) {
                        parentNode = node;
                        parentNodes.put(node.getItemName(), node);
                    }
                    allNodes.put(node.getItemName(), node);
                    if (i != 0) {
                        Set<Node> parentNodes = node.getParentNodes();
                        Set<Node> childNodes = parentNode.getChildNodes();
                        if (parentNodes == null) {
                            parentNodes = new HashSet<Node>();
                        }
                        parentNodes.add(parentNode);
                        node.setParentNodes(parentNodes);

                        if (childNodes == null) {
                            childNodes = new HashSet<Node>();
                        }
                        childNodes.add(node);
                        parentNode.setChildNodes(childNodes);
                    }
                } else {
                    parentNode = allNodes.get(itemNames.get(0));
                    if (i != 0) {
                        Set<Node> parentNodes = allNodes.get(itemName).getParentNodes();
                        Set<Node> childNodes = parentNode.getChildNodes();
                        parentNodes.add(parentNode);
                        allNodes.get(itemName).setParentNodes(parentNodes);

                        childNodes.add(allNodes.get(itemName));
                        parentNode.setChildNodes(childNodes);
                    }
                }
            }
        } else {
            return nextAction.performAction(itemNames, statement);
        }
        return null;
    }
}
