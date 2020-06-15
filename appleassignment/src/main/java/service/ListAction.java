package service;

import commands.Command;
import processor.ListNode;

import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public class ListAction implements ActionInterface {

    private ActionInterface nextAction;

    private ListNodePreparator listNodePreparator;

    public String performAction(List<String> itemNames, String statement) {
        String commandName = itemNames.get(0);
        String str = new String();
        if (Command.LIST.getCommandName().equals(commandName)) {
            ListNode headNode = listNodePreparator.getHead();
            while (headNode != null) {
                str += headNode.getItemName() + "\n";
            }
        }
        return str;
    }
}