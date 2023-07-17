package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker; //인스턴스 변수
    private Connection c;
    private User user;


    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException{

        Connection connection = connectionMaker.makeConnection();

        PreparedStatement ps = connection.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        connection.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException{

        this.c = connectionMaker.makeConnection();
        this.user = new User();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");

        ps.setString(1, id);

        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        this.user.setId(resultSet.getString("id"));
        this.user.setName(resultSet.getString("name"));
        this.user.setPassword(resultSet.getString("password"));

        resultSet.close();
        ps.close();
        c.close();

        return this.user;
    }



}
