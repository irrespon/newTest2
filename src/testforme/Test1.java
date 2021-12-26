import java.sql.*;

public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String userName = "test";
        String pass = "secret";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url,userName,pass);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        String deleteTable = "drop table userTable";
        String newSqlTable = "Create table userTable " +
                "(id integer not null, " +
                " name Varchar(50), " +
                " lastname Varchar(50), " +
                " age integer not null, " +
                "primary key (id))";
        String sql = "SELECT * FROM userTable";
        statement.executeUpdate(deleteTable);
        statement.executeUpdate(newSqlTable);
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            System.out.println("id " + id);
        }

    }
}
