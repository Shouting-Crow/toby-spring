package springbook.user.sqlService;

import springbook.user.sqlService.exception.SqlNotFoundException;

public interface SqlRegistry {
    void registerSql(String key, String sql);

    String findSql(String key) throws SqlNotFoundException;
}
