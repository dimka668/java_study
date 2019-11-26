package quoter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 16688641 on 26.11.2019.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//        while(true) {
//            Thread.sleep(200);
//            context.getBean(Quoter.class).sayQuote();
//        }
    }
}
