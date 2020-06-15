import processor.CommandReader;
import service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
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
        AbstractAction endAction = new EndAction();

        dependAction.setNextAction(installAction);
        installAction.setNextAction(listAction);
        listAction.setNextAction(removeAction);
        removeAction.setNextAction(endAction);

        CommandReader commandReader = new CommandReader();
        commandReader.setAbstractActionChain(dependAction);

        //String str = "DEPEND    TELNET TCPIP   NETCARD";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            while ((input = br.readLine()) != null)
            commandReader.performAction(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
