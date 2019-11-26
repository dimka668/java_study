package lessons;

/**
 * Created by 16688641 on 09.01.2019.
 */
import lessons.spel.Inventor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ExpressionConfiguration {

    @Bean
    Inventor inventor() { return new Inventor();}
}