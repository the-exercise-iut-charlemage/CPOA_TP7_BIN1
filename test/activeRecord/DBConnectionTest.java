package activeRecord;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void getInstance() {
        boolean res = true;
        DBConnection d1 = DBConnection.getInstance();
        DBConnection d2 = DBConnection.getInstance();
        if(d1 != d2)
            res = false;
        assertTrue("plusieur instance de DBConnection", res);
    }

    @Test
    public void getConnection() {
        boolean res = true;
        DBConnection d = DBConnection.getInstance();
        Object o = d.getConnection();
        if(!(o instanceof java.sql.Connection))
            res = false;
        assertTrue("getInstance ne retourne pas le bon type", res);
    }

    @Test
    public void setNomDB() {
        try {
            boolean res = false;
            DBConnection d = DBConnection.getInstance();
            java.sql.Connection c = d.getConnection();
            String s1 = c.getCatalog();
            d.setNomDB("testpersonne2");
            c = d.getConnection();
            String s2 = c.getCatalog();
            if(s1.compareTo("testpersonne")==0 && s2.compareTo("testpersonne2")==0){
                res = true;
            }
            assertTrue("La BDD n'a pas changer, avant:"+s1+"  apres:"+s2, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}