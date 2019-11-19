package activeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Personne {

    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public static List<Personne> findAll(){
        ArrayList<Personne> personnes = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from personne");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Personne p = new Personne(resultSet.getString("NOM"), resultSet.getString("PRENOM"));
                p.setId(resultSet.getInt("ID"));
                personnes.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }

    public static Personne findById(int id){
        Personne personne = null;
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from personne where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Personne p = new Personne(resultSet.getString("NOM"), resultSet.getString("PRENOM"));
                p.setId(resultSet.getInt("ID"));
                personne = p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personne;
    }

    public static List<Personne> findByName(String name){
        ArrayList<Personne> personnes = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from personne where nom=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Personne p = new Personne(resultSet.getString("NOM"), resultSet.getString("PRENOM"));
                p.setId(resultSet.getInt("ID"));
                personnes.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }

    public static void createTable(){
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("drop table if exist 'personne'");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTable(){
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS 'personne' ('ID' int(11) NOT NULL AUTO_INCREMENT,'NOM' varchar(40) NOT NULL,'PRENOM' varchar(40) NOT NULL,PRIMARY KEY ('ID')) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
