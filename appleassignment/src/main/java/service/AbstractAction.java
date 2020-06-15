package service;

import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public abstract class AbstractAction {

    private String statement;

    public AbstractAction(String statement) {
        this.statement = statement;
    }

    abstract public String performAction(List<String> itemNames);

}
