import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public int id;
    public String name;
    public String email;
    public String password;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void createUser() {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/userSchema",
                            "root",
                            "");
            System.out.println("Connected in Database : userSchema");
            PreparedStatement ps = connManager.prepareStatement("INSERT INTO userSchema.user VALUES (?, ?, ?, ?)");
            ps.setLong(1, 0);
            ps.setString(2, this.name);
            ps.setString(3, this.email);
            ps.setString(4, this.password);
            ps.executeUpdate();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
    }

    public void updateUser() {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/userSchema",
                            "root",
                            "");
            System.out.println("Connected in Database : userSchema");
            PreparedStatement ps = connManager
                    .prepareStatement("UPDATE userSchema.user SET name = ?, email = ?, password = ? WHERE id = ?");
            ps.setString(1, this.name);
            ps.setString(2, this.email);
            ps.setString(3, this.password);
            ps.setLong(4, this.id);
            ps.executeUpdate();
            int rowsAffect = ps.executeUpdate();
            if (rowsAffect > 0) {
                System.out.println("Update done! " + rowsAffect + " row(s) affect");
            } else {
                System.out.println("Any rows affect.");
            }
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
    }

    public void deleteUser() {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/userSchema",
                            "root",
                            "");
            System.out.println("Connected in Database : userSchema");
            PreparedStatement ps = connManager.prepareStatement("DELETE FROM userSchema.user WHERE id = ?");
            ps.setInt(1, this.id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully. " + rowsAffected + " row(s) affected.");
            } else {
                System.out.println("No rows affected.");
            }
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
    }

    public static void ViewUsers() {
        try {
            Connection connManager = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/userSchema",
                            "root",
                            "");
            System.out.println("Connected in Database : userSchema");
            PreparedStatement ps = connManager.prepareStatement("SELECT * FROM userSchema.user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                System.out.println("User ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("--------------");
            }
            rs.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
    }

    public boolean login() {
        boolean loggedIn = false;

        try {
            Connection connManager = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userSchema",
                    "root",
                    "");
            System.out.println("Connected to Database: userSchema");

            String query = "SELECT id FROM userSchema.user WHERE email = ? AND password = ?";
            PreparedStatement ps = connManager.prepareStatement(query);
            ps.setString(1, this.email);
            ps.setString(2, this.password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id"); // verify if input information are same of some id in the database
                System.out.println("Login successful for user with ID: " + userId);
                loggedIn = true;
            } else {
                System.out.println("Login failed. Invalid email or password.");
            }
            rs.close();
            connManager.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }

        return loggedIn;
    }

}