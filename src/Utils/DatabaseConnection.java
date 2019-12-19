package Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
    private static SessionFactory _sessionFactory =  new Configuration()
            .configure() // configures settings from hibernate.cfg.xml
            .buildSessionFactory();
    private DatabaseConnection() {}

    public static SessionFactory get_sessionFactory() {
        return _sessionFactory;
    }
}
