package activeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Film {
    private String titre;
    private int id;
    private int id_real;

    public Film(String t, Personne p){
        titre = t;
        id_real = p.getId();
        id = -1;
    }

    private Film(String titre, int id, int id_real){
        this.titre = titre;
        this.id = id;
        this.id_real = id_real;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public int getId_real() {
        return id_real;
    }

    public void setId_real(Personne p) {
        this.id_real = p.getId();
    }

    public static Film findById(int id){
        Film film = null;
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from film where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Film f = new Film(resultSet.getString("TITRE"), resultSet.getInt("ID"), resultSet.getInt("ID_REA"));
                film = f;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

    public Personne getRealisateur(){
        return Personne.findById(id_real);
    }

    public static ArrayList<Film> findbyRealisateur(Personne p){
        Connection connection = DBConnection.getConnection();
        ArrayList<Film> films = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM FILM WHERE ID_REA = ?");
            statement.setInt(1, p.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                films.add(new Film(rs.getString("TITRE"),rs.getInt("ID"), rs.getInt("ID_REA")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return films;
    }

    public static void createTable(){
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `film` (`ID` int(11) NOT NULL AUTO_INCREMENT, `TITRE` varchar(40) NOT NULL, `ID_REA` int(11) DEFAULT NULL, PRIMARY KEY (`ID`), KEY `ID_REA` (`ID_REA`))AUTO_INCREMENT=1");
            statement.executeUpdate();
            statement = connection.prepareStatement("ALTER TABLE `film` ADD CONSTRAINT `film_ibfk_1` FOREIGN KEY (`ID_REA`) REFERENCES `personne` (`ID`);");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTable(){
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("drop table if exists film");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("delete from film where id = ?");
            statement.setInt(1, this.id);
            statement.executeUpdate();
            this.id = -1;
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void save() throws RealisateurAbsentException{
        if(this.id == -1){
            this.saveNew();
        }else{
            this.update();
        }
    }

    private void saveNew() throws RealisateurAbsentException{
        if(id_real == -1){
            throw new RealisateurAbsentException();
        }
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("insert into film(titre,id_rea) values(?,?)");
            statement.setString(1, this.titre);
            statement.setInt(2, this.id_real);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void update() throws RealisateurAbsentException{
        if(id_real == -1){
            throw new RealisateurAbsentException();
        }
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("update film set titre = ? , id_rea = ? where id = ?");
            statement.setString(1,this.titre);
            statement.setInt(2,this.id_real);
            statement.setInt(3,this.id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
