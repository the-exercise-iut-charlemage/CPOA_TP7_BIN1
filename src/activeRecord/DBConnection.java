package activeRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
    private Connection c;
    private static DBConnection s;
    String dbName = "testpersonne";

    private DBConnection(){
        try{
            // variables a modifier en fonction de la base
            String userName = "root";
            String password = "";
            String serverName = "localhost";
            //Attention, sous MAMP, le port est 8889
            String portNumber = "3306";
            String tableName = "personne";

            // creation de la connection
            Properties connectionProps = new Properties();
            connectionProps.put("user", userName);
            connectionProps.put("password", password);
            String urlDB = "jdbc:mysql://" + serverName + ":";
            urlDB += portNumber + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            c = DriverManager.getConnection(urlDB, connectionProps);
        }catch(SQLException e){
            System.out.println(e);
        }
    }


    public static DBConnection getInstance(){
        if(s == null){
            s = new DBConnection();
        }else {
            try {
                if(s.c!=null && s.c.isClosed()){
                    s = new DBConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public Connection getConnection(){
        return c;
    }

    public void setNomDB(String nomDB){
        dbName = nomDB;
        s = new DBConnection();
    }
}
