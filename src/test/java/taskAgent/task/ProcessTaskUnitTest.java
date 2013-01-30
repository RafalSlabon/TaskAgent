package taskAgent.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ProcessTaskUnitTest {

    private TestingProcessTask task;

    @Before
    public void setup() {
        task = new TestingProcessTask();
    }

    @Test
    public void should_compute_unique_id() {
        String id1 = task.newId();
        String id2 = task.newId();
        Assert.assertNotSame(id1, id2);
    }

    @Test(expected = ProcessTask.InvalidId.class)
    public void should_not_start_command_with_invalid_id() {
        task.startCommand("INVALID ID", "ANY COMMAND");
    }

    @Test
    public void should_start_command_with_valid_id() {
        String id = task.newId();
        String command = "ANY COMMAND";
        task.startCommand(id, command);
        Assert.assertTrue(task.commandWasStarted(id, command));
    }

    @Test(expected = ProcessTask.ProcessWithIdAlreadyStarted.class)
    public void should_not_start_already_started_command_with_the_same_id() {
        String id = task.newId();
        String command = "ANY COMMAND";
        task.startCommand(id, command);
        task.startCommand(id, command);
    }


    @Test
    public void should_stop_command_with_valid_id() {
        String id = task.newId();
        task.stopCommand(id);
        Assert.assertTrue(task.commandWasStopped(id));
    }

    @Test(expected = ProcessTask.InvalidId.class)
    public void should_not_stop_command_with_invalid_id() {
        task.stopCommand("INVALID ID");
    }

    private class TestingProcessTask extends ProcessTask {
        public boolean commandWasStarted(String id, String command) {
            return processes.containsKey(id) && processes.get(id).equals(command);
        }

        public boolean commandWasStopped(String id) {
            return !processes.containsKey(id);
        }
    }
}
