package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Games;
import model.Transaction;
import model.Users;

public class Controller {
    static DatabaseHandler conn = new DatabaseHandler();

    // SELECT WHERE ini email sama pw
    public Users getUser(String email, String password) {
        conn.connect();
        String query = "SELECT * FROM users WHERE email ='" + email + "'&&Password='" + password + "'";
        Users orang = new Users();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                orang.setId(rs.getInt("id"));
                orang.setName(rs.getString("name"));
                orang.setEmail(rs.getString("email"));
                orang.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        return (orang);
    }

    //ini select semua dari table games
    public ArrayList<Games> getAllGames() {
        ArrayList<Games> gameLists = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM games";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Games games = new Games();
                games.setId(rs.getInt("id"));
                games.setName(rs.getString("name"));
                games.setGenre(rs.getString("genre"));
                games.setPrice(rs.getInt("price"));
                gameLists.add(games);
            }
        } catch (SQLException e) {
            System.out.println("MASUK CATCH GET ALL GAMES : ");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("MASUK CATCH GET ALL GAMES NULL : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("MASUK CATCH GET ALL GAMES NULL : ");
            e.printStackTrace();
        }
        conn.disconnect();
        return (gameLists);
    }

    // INSERT
    public boolean insertNewTransaction(Transaction trans) {
        conn.connect();
        String query = "INSERT INTO transaction (user_id, game_id) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, trans.getUser_id());
            stmt.setInt(2, trans.getGame_id());
            stmt.executeUpdate();
            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return false;
        }
    }  

    //ini select join dari user dan game (belum)
    public ArrayList<Games> getDataTrans() {
        ArrayList<Games> gameLists = new ArrayList<>();
        ArrayList<Users> users = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM users INNER JOIN games ON = ";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Games games = new Games();
                games.setId(rs.getInt("id"));
                games.setName(rs.getString("name"));
                games.setGenre(rs.getString("genre"));
                games.setPrice(rs.getInt("price"));
                gameLists.add(games);
            }
        } catch (SQLException e) {
            System.out.println("MASUK CATCH GET ALL GAMES : ");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("MASUK CATCH GET ALL GAMES NULL : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("MASUK CATCH GET ALL GAMES NULL : ");
            e.printStackTrace();
        }
        conn.disconnect();
        return (gameLists);
    }
}
