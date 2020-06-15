import service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public class Runner {

    public static void main(String args[]) {

        AbstractAction dependAction = new DependAction();
        AbstractAction installAction = new InstallAction();
        AbstractAction listAction = new ListAction();
        AbstractAction removeAction = new RemoveAction();

        dependAction.setNextAction(installAction);
        installAction.setNextAction(listAction);
        listAction.setNextAction(removeAction);

        String str = "DEPEND    TELNET TCPIP   NETCARD";
        String containers[] = str.split(" ");

        List<String> inputItems = new ArrayList<String>();
        for (int i = 0; i < containers.length; i++) {
            containers[i] = containers[i].trim();
            inputItems.add(containers[i]);
        }
    }

}
