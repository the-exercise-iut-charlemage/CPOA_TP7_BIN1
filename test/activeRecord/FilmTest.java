package activeRecord;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class FilmTest {
    @Before
    public void create(){
        Personne.createTable();
        Personne p = new Personne("Spielberg", "Steven");
        Personne.save(p);
        p = new Personne("Scott", "Ridley");
        Personne.save(p);

        Film.createTable();
        Film f = new Film("ReadyPlayerOne",);
    }


    @After
    public void delete(){
        Personne.deleteTable();
    }

}