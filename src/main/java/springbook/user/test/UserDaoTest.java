package springbook.user.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {

    UserDao dao;

    private User user1;
    private User user2;
    private User user3;

    public static void main(String[] args) {
        JUnitCore.main("springbook.user.test.UserDaoTest");
    }

    @Before
    public void setUp(){

        this.user1 = new User("whiteship", "백사장", "spring1");
        this.user2 = new User("leeman", "이사람", "spring2");
        this.user3 = new User("kimid", "김인간", "spring3");

        dao = new UserDao();
        DataSource dataSource = new SingleConnectionDataSource(
                "jdbc:mariadb://localhost:3306/testdb", "root", "1234", true
        );
        dao.setDataSource(dataSource);

//        JdbcContext jdbcContext = new JdbcContext();
//        jdbcContext.setDataSource(dataSource);
//        dao.setJdbcContext(jdbcContext);

    }


    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));

    }

    @Test
    public void countTest() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }

}
