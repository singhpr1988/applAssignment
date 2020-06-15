package service;

import commands.Command;

import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public class EndAction extends AbstractAction {

    public String performAction(List<String> itemNames, String statement) {
        String commandName = itemNames.get(0);
        super.changeStatement(statement);
        if (Command.END.getCommandName().equals(commandName)) {
            return super.getStatement();
        } else {
            return "NOT A VALID INPUT";
        }
    }
}
