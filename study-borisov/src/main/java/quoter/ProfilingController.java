package quoter;

/**
 * Created by 16688641 on 26.11.2019.
 */
public class ProfilingController implements ProfilingControllerMBean{
    boolean enabled = true;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return this.enabled;
    }
}
