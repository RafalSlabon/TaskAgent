package taskAgent.task;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class RootTask {

    @GET
    @Produces("text/plain")
    public String index() {
        return "Task Agent is running!";
    }

}