package activeRecord;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FilmTest {
    @Before
    public void create(){
        Personne.createTable();
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
        Film f1 = Film.findById(1);
        Film f2 = Film.findById(6);
        assertEquals("Titre incorrect","Arche perdue",f1.getTitre());
        assertEquals("Titre incorrect","Fight Club",f2.getTitre());
    }

    @Test
    public void getRealisateur() {
        Personne p1 = Film.findById(1).getRealisateur();
        Personne p2 = Film.findById(6).getRealisateur();
        assertEquals("Réalisateur incorrect","Spielberg",p1.getNom());
        assertEquals("Réalisateur incorrect","Fincher",p2.getNom());
    }

    @Test
    public void findbyRealisateur() {
        String [] titres = {"Arche perdue", "Temple Maudit"};
        ArrayList<Film> l = Film.findbyRealisateur(Personne.findById(1));
        assertEquals("la taille de la liste est incorrect ", 2, l.size());

        for (int i=0; i < l.size(); i++){
            assertEquals("le titre est incorrect ", titres[i], l.get(i).getTitre());
        }
    }

    @Test
    public void testDelete() {
        Film.findById(5).delete();

        ArrayList<Film> l = Film.findbyRealisateur(Personne.findById(4));

        assertEquals("la taille de la liste est incorrect ", 1, l.size());
        assertEquals("le titre est incorrect", "Fight Club", l.get(0).getTitre());
    }

    @After
    public void delete(){
        Film.deleteTable();
        Personne.deleteTable();
    }
}