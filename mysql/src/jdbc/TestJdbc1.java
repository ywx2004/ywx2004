package jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJdbc1 {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/homework?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("t234");

        Connection connection=dataSource.getConnection();
        System.out.println(connection);

        String sql ="insert into students values('tianqi',35)";
        PreparedStatement statement = connection.prepareStatement(sql);

        int n = statement.executeUpdate();
        System.out.println(n);

        statement.close();
        connection.close();
    }
}
