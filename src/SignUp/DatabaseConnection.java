package SignUp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    private static DatabaseConnection obj;
    private static Connection conn;

    private DatabaseConnection() {
        try {
            conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/bookworm?useSSL=false", "root", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (obj==null)
            obj = new DatabaseConnection();
        return obj;
    }

    public static Connection getConn() {
        return conn;
    }
}
