package springbook.user.service;

import springbook.user.dao.UserDaoJdbc;
import springbook.user.domain.User;
import springbook.user.exception.TestUserServiceException;

public class TestUserService extends UserLevelGeneralUpgrade{
    private String id;

    public TestUserService(UserDaoJdbc userDao, String id) {
        super(userDao);
        this.id = id;
    }

    @Override
    public void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) throw new TestUserServiceException();
        super.upgradeLevel(user);
    }
}
