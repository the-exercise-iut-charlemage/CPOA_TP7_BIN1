package activeRecord;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertTrue;

public class PersonneTest {

    @Before
    public void create(){
        Personne.createTable();
        Personne p = new Personne("Spielberg", "Steven");
        Personne.save(p);
        Personne p = new Personne("Scott", "Ridley");
        Personne.save(p);
        Personne p = new Personne("Kubrick", "Stanley");
        Personne.save(p);
        Personne p = new Personne("Fincher", "David");
        Personne.save(p);
    }


    @After
    public void delete(){
        Personne.deleteTable();
    }
}
