package service;

import commands.Command;
import processor.ListNode;

import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public class ListAction extends AbstractAction {

    private AbstractAction nextAction;

    private ListNodePreparator listNodePreparator;

    public ListAction() {
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
            return nextAction.performAction(itemNames, statement);
        }
        return str;
    }
}
