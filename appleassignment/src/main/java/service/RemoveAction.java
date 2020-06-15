package service;

import commands.Command;
import processor.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by prateekraj on 15/6/20.
 */
public class RemoveAction extends AbstractAction {

    private ListNodePreparator listNodePreparator;

    private Map<String, Node> allNodes = new HashMap<String, Node>();

    private static final String REMOVING = "Removing";
    private static final String INSTALLED = "installed";
    private static final String NEEDED = "is still needed";

    public RemoveAction() {
    }

    public RemoveAction(ListNodePreparator listNodePreparator) {
        this.listNodePreparator = listNodePreparator;
    }

    public String performAction(List<String> itemNames, String statement) {
        String commandName = itemNames.get(0);
        super.changeStatement(statement);
        String result = new String();
        if (Command.REMOVE.getCommandName().equals(commandName)) {
            itemNames.remove(0);
            String itemName = itemNames.get(0);
            if (!allNodes.containsKey(itemName)) {
                result += itemName + " is not " + INSTALLED;
            } else {
                Node node = allNodes.get(itemName);
                Set<Node> parenNodes = node.getParentNodes();
                if (parenNodes != null && parenNodes.size() > 0) {
                    if (node.isItemInstalled()) {
                        boolean flag = false;
                        for (Node parentNode : parenNodes) {
                            if (parentNode.isItemInstalled()) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            result += node.getItemName() + " " + NEEDED;
                        }
                    }

                } else if (parenNodes == null || parenNodes.size() == 0) {
                    recursiveMethodToRemoveItems(node);
                }
            }
        } else {
            return super.getNextAction().performAction(itemNames, statement);
        }
        return result;
    }

    private String recursiveMethodToRemoveItems(Node node) {
        String str = new String();
        if (node.isItemInstalled()) {
            allNodes.remove(node.getItemName());
            Set<Node> parentNodes = node.getParentNodes();
            if (parentNodes == null || parentNodes.isEmpty()) {
                listNodePreparator.updateListOfNodesPostDeletion(node.getItemName());
                str += REMOVING + " " + node.getItemName() + "\n";
            }

            Set<Node> childNodes = node.getChildNodes();
            if (childNodes != null) {
                for (Node childNode : childNodes) {
                    str += recursiveMethodToRemoveItems(childNode);
                }
            }
        }
        return str;
    }
}
