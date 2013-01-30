package taskAgent.main;

import com.google.common.collect.Maps;
import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import taskAgent.task.RootTask;

import java.util.Map;

public class Main {

    public static final String BASE_URI = "http://localhost:9998/";
    public static final String RESOURCES = RootTask.class.getPackage().getName();

    public static void main(String[] args) throws Exception {
        Map<String, String> initParams = Maps.newHashMap();
        initParams.put("com.sun.jersey.config.property.packages", RESOURCES);

        System.out.println("Starting Task Agent...");
        SelectorThread threadSelector = GrizzlyWebContainerFactory.create(BASE_URI, initParams);
        System.out.println("Task Agent is now running...");
        System.out.println("PRESS ANY BUTTON TO STOP");
        System.in.read();
        threadSelector.stopEndpoint();
        System.exit(0);
    }
}
