package springbook.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import springbook.user.sqlService.OxmSqlService;
import springbook.user.sqlService.SqlRegistry;
import springbook.user.sqlService.SqlService;
import springbook.user.sqlService.updatable.EmbeddedDbSqlRegistry;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "springbook.user.dao")
public class SqlServiceContext {
    @Autowired
    SqlMapConfig sqlMapConfig;

    @Bean
    public SqlService sqlService(){
        OxmSqlService sqlService = new OxmSqlService();
        sqlService.setUnmarshaller(unmarshaller());
        sqlService.setSqlRegistry(sqlRegistry());
        sqlService.setSqlmap(this.sqlMapConfig.getSqlMapResource());
        return sqlService;
    }

    @Bean
    public SqlRegistry sqlRegistry(){
        EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
        sqlRegistry.setDataSource(embeddedDatabase());
        return sqlRegistry;
    }

    @Bean
    public Unmarshaller unmarshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("springbook.user.sqlservice.jaxb");
        return marshaller;
    }

    @Bean
    public DataSource embeddedDatabase(){
        return new EmbeddedDatabaseBuilder()
                .setName("embeddedDatabase")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("databaseSql/sqlRegistrySchema.sql")
                .build();
    }
}
