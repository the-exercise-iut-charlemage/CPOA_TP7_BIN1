package activeRecord;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @org.junit.Test
    public void getInstance() {
        boolean res = true;
        DBConnection d1 = DBConnection.getInstance();
        DBConnection d2 = DBConnection.getInstance();
        if(d1 == d2)
            res = false;
        assertTrue("plusieur instance de DBConnection", res);
    }

    @org.junit.Test
    public void getConnection() {
        boolean res = true;
        DBConnection d = DBConnection.getInstance();
        Object o = d.getConnection();
        if(!(o instanceof java.sql.Connection))
            res = false;
        assertTrue("getInstance ne retourne pas le bon type", res);
    }

    @org.junit.Test
    public void setNomDB() {
        DBConnection d = DBConnection.getInstance();
        java.sql.Connection c = d.getConnection();

    }
}