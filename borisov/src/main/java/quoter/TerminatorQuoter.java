package quoter;

import javax.annotation.PostConstruct;

/**
 * Created by 16688641 on 26.11.2019.
 */

@Profiling
public class TerminatorQuoter implements Quoter {
    private String message;

    @InjectRundomInt(min = 2, max = 7)
    private int repeat;

    public TerminatorQuoter() {
        System.out.println("Phase 1");
    }

    @PostConstruct
    public void init() {
        System.out.println("Phase 2");
    }

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3");
        for (int i = 0; i < repeat; i++) {
            System.out.println("message=" + message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
