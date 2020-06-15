package service;

import commands.Command;
import processor.ListNode;

import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public class ListAction extends ActionInterface {

    private ActionInterface nextAction;

    private ListNodePreparator listNodePreparator;

    public ListAction(String statement) {
        super(statement);
    }

    public String performAction(List<String> itemNames) {
        String commandName = itemNames.get(0);
        String str = new String();
        if (Command.LIST.getCommandName().equals(commandName)) {
            ListNode headNode = listNodePreparator.getHead();
            while (headNode != null) {
                str += headNode.getItemName() + "\n";
            }
        } else {
            return nextAction.performAction(itemNames);
        }
        return str;
    }
}
