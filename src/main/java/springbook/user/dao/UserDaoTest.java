package springbook.user.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException {

        ApplicationContext context = new ClassPathXmlApplicationContext("dataSourceContext.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("whiteship");
        user.setName("백사장");
        user.setPassword("1234");

        dao.add(user);

        User user2 = dao.get(user.getId());

        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));

//        if (!user.getName().equals(user2.getName())){
//            System.out.println("테스트 실패 (name)");
//        }
//        else if (!user.getPassword().equals(user2.getPassword())){
//            System.out.println("테스트 실패 (password)");
//        }
//        else {
//            System.out.println("조회 테스트 성공");
//        }
    }

}
