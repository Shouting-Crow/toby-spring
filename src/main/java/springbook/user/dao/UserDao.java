package springbook.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException{

        Connection connection = dataSource.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        connection.close();

    }

    public User get(String id) throws SQLException{

        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");

        ps.setString(1, id);

        ResultSet resultSet = ps.executeQuery();

        User user = null;
        if (resultSet.next()){
            user = new User();
            user.setId(resultSet.getString("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
        }

        resultSet.close();
        ps.close();
        c.close();

        if (user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("delete from users");

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }

    public int getCount() throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from users");

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return count;
    }



}
