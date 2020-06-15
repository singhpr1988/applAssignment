package service;

import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public abstract class AbstractAction {

    private String statement;
    private AbstractAction nextAction;

    public AbstractAction() {
    }

    public void changeStatement(String statement) {
        this.statement = statement;
    }


    public void setStatement(String statement) {
        this.statement = statement;
    }

    public AbstractAction getNextAction() {
        return nextAction;
    }

    public void setNextAction(AbstractAction nextAction) {
        this.nextAction = nextAction;
    }

    abstract public String performAction(List<String> itemNames, String statement);

    public String getStatement() {
        return statement;
    }
}
