package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DBConnecttion<T> {
    protected Connection connection;
    public DBConnecttion()
    {
        try {
            String user = "sa";
            String pass = "tung1602";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Shop";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnecttion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
