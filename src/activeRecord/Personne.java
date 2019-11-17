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
        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();
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
}
