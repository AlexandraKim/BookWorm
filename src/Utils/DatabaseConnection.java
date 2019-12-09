package Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
    private static SessionFactory _sessionFactory =  new Configuration()
            .configure() // configures settings from hibernate.cfg.xml
            .buildSessionFactory();
    private static Session _session = _sessionFactory.openSession();
    private DatabaseConnection() {}

    public static Session getSession() {
        return _session;
    }
}
