package activeRecord;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void getConnection1() {
        boolean res = true;
        Connection c = DBConnection.getConnection();
        if(!(c instanceof java.sql.Connection))
            res = false;
        assertTrue("getInstance ne retourne pas le bon type", res);
    }

    @Test
    public void getInstance2(){
        boolean res = true;
        Connection c1 = DBConnection.getConnection();
        Connection c2 = DBConnection.getConnection();
        if(c1 != c2)
            res = false;
        assertTrue("plusieurs instances de DBConnection", res);
    }

    @Test
    public void setNomDB() {
        try {
            boolean res = false;
            Connection c = DBConnection.getConnection();
            String s1 = c.getCatalog();
            DBConnection.setNomDB("testpersonne2");
            c = DBConnection.getConnection();
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