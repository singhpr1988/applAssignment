package processor;

import service.ActionInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public class CommandReader {

    private ActionInterface actionInterface;

    public String performAction(String statement) {
        String result = new String();
        if (statement != null) {
            String containers[] = statement.split(" ");
            List<String> itemNames = new ArrayList<String>();
            for (int i = 0; i < containers.length; i++) {
                containers[i] = containers[i].trim();
                if (containers[i].length() > 0) {
                    itemNames.add(containers[i]);
                }
            }
            result = actionInterface.performAction(itemNames, statement);
        }
        return result;
    }
}