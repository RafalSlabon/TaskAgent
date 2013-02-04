package taskAgent.task;

import com.google.common.collect.Maps;

import javax.ws.rs.*;
import java.util.Map;
import java.util.Random;

@Path("/process")
public class ProcessTask {

    protected static final Map<String, String> processes = Maps.newHashMap();
    private static final String NOT_STARTED = "";

    @GET
    @Path("/newId")
    @Produces("text/plain")
    public String newId() {
        String newId = "" + new Random().nextLong();
        processes.put(newId, NOT_STARTED);
        return newId;
    }

    @POST
    @Path("/start/{id}")
    @Consumes("text/plain")
    public void startCommand(@PathParam("id") String id, String command) {
        checkIfExist(id);
        checkIfWasAlreadyStarted(id);
        processes.put(id, command);
    }

    private void checkIfWasAlreadyStarted(String id) {
        if (processes.get(id) != NOT_STARTED)
            throw new ProcessWithIdAlreadyStarted();
    }

    @POST
    @Path("/stop/{id}")
    @Consumes("text/plain")
    public void stopCommand(@PathParam("id") String id) {
        checkIfExist(id);
        processes.remove(id);
    }

    @GET
    @Path("/status/{id}")
    @Produces("application/xml")
    public ProcessStatus status(@PathParam("id") String id) {
        return new ProcessStatus();
    }

    private void checkIfExist(String id) {
        if (processes.get(id) == null)
            throw new InvalidId();
    }

    public class InvalidId extends RuntimeException {
    }

    public class ProcessWithIdAlreadyStarted extends RuntimeException {
    }
}
