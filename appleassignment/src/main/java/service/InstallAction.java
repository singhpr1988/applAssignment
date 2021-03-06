package service;

import commands.Command;
import processor.Node;

import java.util.List;
import java.util.Set;

/**
 * Created by prateekraj on 15/6/20.
 */
public class InstallAction extends AbstractAction {

    //private Map<String, Node> allNodes = new HashMap<String, Node>();

    private static final String INSTALLING = "Installing";

    private ListNodePreparator listNodePreparator;

    public InstallAction() {

    }

    public InstallAction(ListNodePreparator listNodePreparator, CentralService centralService) {
        super(centralService);
        this.listNodePreparator = listNodePreparator;
    }

    public String performAction(List<String> itemNames, String statement) {
        String commandName = itemNames.get(0);
        super.changeStatement(statement);
        if (Command.INSTALL.getCommandName().equals(commandName)) {
            itemNames.remove(0);
            String itemName = itemNames.get(0);
            if (super.getCentralService().fetchAllNodesMap().keySet().contains(itemName)) {
                Node node = super.getCentralService().fetchAllNodesMap().get(itemName);
                Set<Node> childNodes = node.getChildNodes();
                if (childNodes == null || childNodes.isEmpty()) {
                    if (!node.isItemInstalled()) {
                        listNodePreparator.prepareChainOfListNodes(node.getItemName());
                        node.setItemInstalled(true);
                        return INSTALLING + " " + node.getItemName();
                    } else {
                        return itemName + " is already installed";
                    }
                } else if (!childNodes.isEmpty()) {
                    String result = recursiveMethodToInstallDependencies(childNodes);
                    if (!node.isItemInstalled()) {
                        listNodePreparator.prepareChainOfListNodes(node.getItemName());
                        node.setItemInstalled(true);
                        result += INSTALLING + " " + node.getItemName();
                    }
                    return result;
                }
            } else {
                listNodePreparator.prepareChainOfListNodes(itemName);
                return INSTALLING + " " + itemName;
            }
        } else {
            return super.getNextAction().performAction(itemNames, statement);
        }
        return null;
    }

    private String recursiveMethodToInstallDependencies(Set<Node> nodes) {
        String str = new String();
        for (Node node : nodes) {
            Set<Node> childNodes = node.getChildNodes();
            if (childNodes != null && !childNodes.isEmpty()) {
                str = recursiveMethodToInstallDependencies(childNodes);
            }
            if (!node.isItemInstalled()) {
                listNodePreparator.prepareChainOfListNodes(node.getItemName());
                node.setItemInstalled(true);
                str += INSTALLING + " " + node.getItemName() + "\n";
            } else {
                //str += node.getItemName() + " " + "is already installed";
            }
        }
        return str;
    }
}
