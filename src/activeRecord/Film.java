package activeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
