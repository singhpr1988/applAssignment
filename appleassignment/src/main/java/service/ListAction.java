package service;

import commands.Command;
import processor.ListNode;

import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public class ListAction extends AbstractAction {

    private ListNodePreparator listNodePreparator;

    public ListAction() {
    }

    public ListAction(ListNodePreparator listNodePreparator) {
        this.listNodePreparator = listNodePreparator;
    }

    public String performAction(List<String> itemNames, String statement) {
        String commandName = itemNames.get(0);
        super.changeStatement(statement);
        String str = new String();
        if (Command.LIST.getCommandName().equals(commandName)) {
            ListNode headNode = listNodePreparator.getHead();
            while (headNode != null) {
                str += headNode.getItemName() + "\n";
            }
        } else {
            return super.getNextAction().performAction(itemNames, statement);
        }
        return str;
    }
}
