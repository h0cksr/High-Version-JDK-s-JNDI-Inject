import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LookTest {
    public static void main(String[] args) throws NamingException {
        Context context = new InitialContext();
        String target = "rmi://127.0.0.1:1099/Exploit";
        System.out.println(target);
        System.out.println(context.lookup(target));
    }

}
