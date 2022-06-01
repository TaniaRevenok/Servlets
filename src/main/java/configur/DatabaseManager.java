package configur;
import org.hibernate.Session;
public interface DatabaseManager {

    Session getSession();
}

