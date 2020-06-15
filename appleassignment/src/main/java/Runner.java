import processor.CommandReader;
import service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekraj on 15/6/20.
 */
public class Runner {

    public static void main(String args[]) {

        ListNodePreparator listNodePreparator = new ListNodePreparator();
        CentralService centralService = new CentralService();

        AbstractAction dependAction = new DependAction(centralService);
        AbstractAction installAction = new InstallAction(listNodePreparator, centralService);
        AbstractAction listAction = new ListAction(listNodePreparator, centralService);
        AbstractAction removeAction = new RemoveAction(listNodePreparator, centralService);

        dependAction.setNextAction(installAction);
        installAction.setNextAction(listAction);
        listAction.setNextAction(removeAction);

        CommandReader commandReader = new CommandReader();
        commandReader.setAbstractActionChain(dependAction);

        String str = "DEPEND    TELNET TCPIP   NETCARD";
        commandReader.performAction(str);
    }

}
