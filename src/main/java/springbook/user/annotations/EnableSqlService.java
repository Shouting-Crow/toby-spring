package springbook.user.annotations;

import org.springframework.context.annotation.Import;
import springbook.user.dao.SqlServiceContext;

@Import(value= SqlServiceContext.class)
public @interface EnableSqlService {
}
