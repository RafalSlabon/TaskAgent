package taskAgent.task;

import com.sun.jersey.test.framework.JerseyTest;
import taskAgent.main.Main;


public abstract class TaskAgentTest extends JerseyTest {
    public TaskAgentTest() throws Exception {
        super(Main.RESOURCES);
    }
}
