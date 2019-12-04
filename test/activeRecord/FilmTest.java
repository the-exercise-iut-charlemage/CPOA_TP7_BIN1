package activeRecord;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FilmTest {
    @Before
    public void create(){
        Film.createTable();
        Personne p = new Personne("Spielberg", "Steven");
        p.save();
        p = new Personne("Scott", "Ridley");
        p.save();
        p = new Personne("Kubrick", "Stanley");
        p.save();
        p = new Personne("Fincher", "David");
        p.save();
        try {
            Film.createTable();
            Film f = new Film("Arche perdue", Personne.findById(1));
            f.save();
            f = new Film("Alien", Personne.findById(2));
            f.save();
            f = new Film("Temple Maudit", Personne.findById(1));
            f.save();
            f = new Film("Blade Runner", Personne.findById(2));
            f.save();
            f = new Film("Alien3", Personne.findById(4));
            f.save();
            f = new Film("Fight Club", Personne.findById(4));
            f.save();
            f = new Film("Orange Mecanique", Personne.findById(3));
            f.save();
        }catch(RealisateurAbsentException e){
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {

    }

    @Test
    public void getRealisateur() {
    }

    @Test
    public void findbyRealisateur() {
    }

    @Test
    public void testDelete() {
    }

    @After
    public void delete(){
        Film.deleteTable();
    }
}