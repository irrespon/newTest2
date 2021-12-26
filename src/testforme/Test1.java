import java.sql.*;

public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/world";
        String userName = "root";
        String pass = "kotovsk";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url,userName,pass);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM city";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            System.out.println("id " + id);
        }

    }
}
