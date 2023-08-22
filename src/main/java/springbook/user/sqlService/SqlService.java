package springbook.user.sqlService;

import springbook.user.sqlService.exception.SqlRetrievalFailureException;

public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
