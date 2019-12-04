package activeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<Personne> findAll(){
        ArrayList<Personne> personnes = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from personne");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Personne p = new Personne(resultSet.getString("NOM"), resultSet.getString("PRENOM"));
                p.id = resultSet.getInt("ID");
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
                p.id = resultSet.getInt("ID");
                personne = p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personne;
    }

    public static ArrayList<Personne> findByName(String name){
        ArrayList<Personne> personnes = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from personne where nom=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Personne p = new Personne(resultSet.getString("NOM"), resultSet.getString("PRENOM"));
                p.id = resultSet.getInt("ID");
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
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `personne` (`ID` int(11) NOT NULL AUTO_INCREMENT, `NOM` varchar(40) NOT NULL, `PRENOM` varchar(40) NOT NULL, PRIMARY KEY (`ID`))AUTO_INCREMENT=1");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTable(){
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("drop table if exists personne");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM personne WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            id = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(){
        if(id==-1){
            saveNew();
        }else{
            update();
        }
    }

    private void saveNew(){
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into personne(nom, prenom) values (?, ?)");
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(){
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE personne SET nom = ?, prenom = ? WHERE id = ?");
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nom+" "+prenom+", ID:"+id;
    }
}
