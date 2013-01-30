package taskAgent.task;

import org.junit.Assert;
import org.junit.Test;


public class ProcessTaskTest extends TaskAgentTest {
    public ProcessTaskTest() throws Exception {
    }

    @Test
    public void should_get_new_process_id() throws Exception {
        String id = newId();
        Assert.assertNotNull(id);
    }

    @Test
    public void should_start_command_with_process_id() throws Exception {
        String id = newId();
        String command = "ps aux";
        webResource.path("/process/start/" + id).post(command);
    }

    @Test
    public void should_stop_process_with_id() throws Exception {
        String id = newId();
        webResource.path("/process/stop/" + id).post();
    }

    private String newId() {
        return webResource.path("/process/newId").get(String.class);
    }
}
