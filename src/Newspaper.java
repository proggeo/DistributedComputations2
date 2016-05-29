/**
 * Created by Proggeo on 5/24/2016.
 */

import java.sql.*;

import com.mysql.jdbc.ConnectionFeatureNotAvailableException;
import com.mysql.jdbc.Driver;

public class Newspaper {

    private Connection conn = null;
    private Statement stmt = null;

    public Newspaper() throws Exception {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/newspaper";
        final String USER = "root";
        final String PASS = "admin";

        Class.forName(JDBC_DRIVER).newInstance();
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

    }

    public void stop() throws SQLException {
        conn.close();
    }

    public boolean addCategory(int id, String name) throws SQLException {
        String sql = "INSERT INTO categories (id_cat, name)" +
                "VALUES (" + id + ", '" + name + "')";
        try {
            stmt.executeUpdate(sql);
            System.out.println("You have successfully added category: " + name);
            return true;
        } catch (SQLException e) {
            System.out.println("Error!");
            System.out.println(" >> " + e.getMessage());

            return false;
        }
    }

    public boolean deleteCategory(int id) throws SQLException {
        String sql = "DELETE FROM categories WHERE id_cat = " + id;
        try {
            int c = stmt.executeUpdate(sql);
            if (c > 0) {
                System.out.println("You have successfully deleted category with id " + id);
                return true;
            } else {
                System.out.println("Category not found!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error!");
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }

    public void showCategories() {
        String sql = "SELECT id_cat, name FROM categories";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of categories:");
            while (rs.next()) {
                int id = rs.getInt("id_cat");
                String name = rs.getString("name");
                System.out.println(" >> " + id + " - " + name);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error!");
            System.out.println(" >> " + e.getMessage());
        }
    }

    public boolean addNews(int id, int id_cat, String name) throws SQLException {
        String sql = "INSERT INTO news (id_news, id_cat, name)" +
                "VALUES (" + id + ", " + id_cat + ", '" + name + "')";
        try {
            stmt.executeUpdate(sql);
            System.out.println("You have successfully added news: " + name);
            return true;
        } catch (SQLException e) {
            System.out.println("Error!");
            System.out.println(" >> " + e.getMessage());

            return false;
        }
    }

    public boolean deleteNews(int id) throws SQLException {
        String sql = "DELETE FROM news WHERE id_news = " + id;
        try {
            int c = stmt.executeUpdate(sql);
            if (c > 0) {
                System.out.println("You have successfully deleted news with id " + id);
                return true;
            } else {
                System.out.println("News not found!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error!");
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }

    public void showNews() {
        String sql = "SELECT id_news, id_cat, name FROM news";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List of news:");
            while (rs.next()) {
                int id = rs.getInt("id_news");
                int id_cat = rs.getInt("id_cat");
                String name = rs.getString("name");
                System.out.println(" >> " + id + " - " + id_cat + "-" + name);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error!");
            System.out.println(" >> " + e.getMessage());
        }
    }


    public static void main(String[] args) throws Exception {
        Newspaper n = new Newspaper();

        n.showCategories();
        n.addCategory(3, "Sport");
        n.addCategory(4, "Soccer");
        n.deleteCategory(3);
        n.showCategories();
        n.addNews(4,4,"Sevilla won!");
        n.addNews(5,4,"Liverpool lost!");
        n.showNews();
        n.deleteNews(5);
        n.showNews();
        n.stop();

    }

}
