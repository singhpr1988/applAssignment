/**
 * Created by prateekraj on 15/6/20.
 */
public class Runner {

    public static void main(String args[]) {
        String str = "DEPEND    TELNET TCPIP   NETCARD";
        String containers[] = str.split(" ");
        for (int i = 0; i < containers.length; i++) {
            containers[i] = containers[i].trim();
            System.out.println(containers[i]);
        }
    }

}
