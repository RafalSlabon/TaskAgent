package taskAgent.task;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RootTaskTest extends TaskAgentTest {

    public RootTaskTest() throws Exception {
    }

    @Test
    public void test_root_welcome_text() {
        String text = webResource.path("/").get(String.class);
        assertTrue(text.equalsIgnoreCase("Task Agent is running!"));
    }

}
