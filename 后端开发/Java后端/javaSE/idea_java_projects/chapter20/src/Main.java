import java.net.InetAddress;
import java.net.UnknownHostException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Main {
    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        System.out.println(InetAddress.getByName("isjhd"));
    }
}