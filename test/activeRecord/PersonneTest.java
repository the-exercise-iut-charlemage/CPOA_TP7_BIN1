package activeRecord;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PersonneTest {

    @Before
    public void createTable(){
        Personne.createTable();
        Personne p = new Personne("Spielberg", "Steven");
        p.save();
        p = new Personne("Scott", "Ridley");
        p.save();
        p = new Personne("Kubrick", "Stanley");
        p.save();
        p = new Personne("Fincher", "David");
        p.save();
    }

    @Test
    public void saveNew(){
        Personne p = new Personne("Cameron","James");
        p.save();

        ArrayList<Personne> list = (ArrayList<Personne>) Personne.findByName("Cameron");

        assertEquals("la taille de la liste est incorrect ", 1, list.size());

        assertEquals("le nom est incorrect ", "Cameron", list.get(0).getNom());
        assertEquals("le prenom est incorrect ", "James", list.get(0).getPrenom());
    }

    @Test
    public void update(){
        Personne p = Personne.findById(1);
        p.setPrenom("Peter");
        p.save();

        Personne r = Personne.findById(1);
        assertEquals("le nom est incorrect ", "Spielberg", r.getNom());
        assertEquals("le prenom est incorrect ", "Peter", r.getPrenom());
    }

    @Test
    public void findAll() {
        String[] noms = {"Spielberg", "Scott", "Kubrick", "Fincher"};
        String[] prenoms = {"Steven", "Ridley", "Stanley", "David"};
        ArrayList<Personne> list = (ArrayList<Personne>) Personne.findAll();

        assertEquals("la taille de la liste est incorrect ", 4, list.size());

        for (int i=0; i<list.size(); i++){
            assertEquals("le nom est incorrect ", noms[i], list.get(i).getNom());
            assertEquals("le prenom est incorrect ", prenoms[i], list.get(i).getPrenom());
        }
    }

    @Test
    public void findById() {
        Personne p = Personne.findById(2);
        assertEquals("le nom est incorrect ", "Scott", p.getNom());
        assertEquals("le prenom est incorrect ", "Ridley", p.getPrenom());
    }

    @Test
    public void findByName() {
        ArrayList<Personne> list = (ArrayList<Personne>) Personne.findByName("Fincher");

        assertEquals("la taille de la liste est incorrect ", 1, list.size());

        assertEquals("le nom est incorrect ", "Fincher", list.get(0).getNom());
        assertEquals("le prenom est incorrect ", "David", list.get(0).getPrenom());
    }

    @Test
    public void delete() {
        Personne p = new Personne("Nom", "Prenom");
        p.save();
        p.delete();

        ArrayList<Personne> list = (ArrayList<Personne>) Personne.findAll();
        assertEquals("la taille de la liste est incorrect "+list.toString(), 4, list.size());
        assertEquals("l'id est incorrect ", -1, p.getId());
    }

    @After
    public void deleteTable(){
        Personne.deleteTable();
    }
}
