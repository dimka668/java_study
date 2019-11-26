package quoter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 16688641 on 26.11.2019.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRundomInt {
    int min();
    int max();
}
