package service;

import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public abstract class ActionInterface {

    private String statement;

    public ActionInterface(String statement) {
        this.statement = statement;
    }

    abstract String performAction(List<String> itemNames);

}
