package commands;

/**
 * Created by prateekraj on 15/6/20.
 */
public enum Command {

    DEPEND("DEPEND"),
    INSTALL("INSTALL"),
    REMOVE("REMOVE"),
    LIST("LIST");

    private String commandName;

    Command(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
