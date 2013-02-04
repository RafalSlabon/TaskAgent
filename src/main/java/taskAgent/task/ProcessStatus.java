package taskAgent.task;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ProcessStatus {

    public ProcessStatus() { //default constructor for JAXB
    }

    public String id;

    public String command;

    @XmlTransient
    public Process process;

    public Status status;

    public enum Status {
        NOT_STARTED, RUNNING, STOPPED;
    }

}
