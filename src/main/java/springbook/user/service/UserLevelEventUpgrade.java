package springbook.user.service;

import springbook.user.dao.UserDaoJdbc;
import springbook.user.domain.Level;
import springbook.user.domain.User;

public class UserLevelEventUpgrade implements UserLevelUpgradePolicy{

    UserDaoJdbc userDao;

    public static final int MIN_LOGCOUNT_FOR_SILVER = 40;
    public static final int MIN_RECOMMNED_FOR_GOLD = 25;

    public void setUserDao(UserDaoJdbc userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER: return (user.getRecommend() >= MIN_RECOMMNED_FOR_GOLD);
            case GOLD: return false;
            default: throw new IllegalArgumentException("Error : " + currentLevel);
        }
    }

    @Override
    public void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
    }
}
