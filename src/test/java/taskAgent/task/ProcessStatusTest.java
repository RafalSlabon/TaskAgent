package taskAgent.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.transform.dom.DOMSource;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.xmlmatchers.XmlMatchers.hasXPath;


public class ProcessStatusTest {

    private ProcessStatus ps;
    private Document xml;

    @Before
    public void setup() throws Exception {
        ps = new ProcessStatus();
        createXml();
    }

    @Test
    public void should_have_root_tag() throws Exception {
        assertXPath("/processStatus");
    }

    @Test
    public void should_have_id_tag() throws Exception {
        ps.id = "any id";
        createXml();
        assertXPath("/processStatus/id", "any id");
    }

    @Test
    public void should_have_command_tag() throws Exception {
        ps.command = "any command";
        createXml();
        assertXPath("/processStatus/command", "any command");
    }

    @Test
    public void should_have_status_tag() throws Exception {
        assertStatusEnum(ProcessStatus.Status.NOT_STARTED, "NOT_STARTED");
        assertStatusEnum(ProcessStatus.Status.RUNNING, "RUNNING");
        assertStatusEnum(ProcessStatus.Status.STOPPED, "STOPPED");
    }

    @Test
    public void should_not_have_process_tag() throws Exception {
        ps.process = new FakeProcess();
        createXml();
        assertXPathNotExist("/processStatus/process");
    }

    private void assertStatusEnum(ProcessStatus.Status status, String expectedStatusText) throws Exception {
        ps.status = status;
        createXml();
        assertXPath("/processStatus/status", expectedStatusText);
    }

    private void createXml() throws Exception {
        xml = XmlUtil.toXml(ps);
    }

    private void assertXPathNotExist(String notToFindXPath) {
        Assert.assertThat(new DOMSource(xml), not(hasXPath(notToFindXPath)));
    }

    private void assertXPath(String expectedXPath) {
        Assert.assertThat(new DOMSource(xml), hasXPath(expectedXPath));
    }

    private void assertXPath(String expectedXPath, String expectedValue) {
        Assert.assertThat(new DOMSource(xml), hasXPath(expectedXPath, containsString(expectedValue)));
    }
}
