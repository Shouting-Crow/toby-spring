package springbook.user.sqlService.test;

import springbook.user.sqlService.ConcurrentHashMapSqlRegistry;
import springbook.user.sqlService.UpdatableSqlRegistry;

public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistry{

        @Override
        protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
            return new ConcurrentHashMapSqlRegistry();
        }
}
