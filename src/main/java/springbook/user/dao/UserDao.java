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

    public void add(final User user) throws SQLException{

        jdbcContextWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement preparedStatement = connection.prepareStatement(
                                "insert into users(id, name, password) values(?,?,?)"
                        );

                        preparedStatement.setString(1, user.getId());
                        preparedStatement.setString(2, user.getName());
                        preparedStatement.setString(3, user.getPassword());

                        return preparedStatement;
                    }
                });
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
        jdbcContextWithStatementStrategy(
                new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("delete from users");
                return preparedStatement;
            }
        });
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy strategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = strategy.makePreparedStatement(connection);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw e;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e){

                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e){

                }
            }
        }
    }

    public int getCount() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select count(*) from users");

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){

                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){

                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e){

                }
            }
        }
    }



}
