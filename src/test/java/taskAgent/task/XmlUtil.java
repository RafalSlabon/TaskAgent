package taskAgent.task;


import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringWriter;
import java.io.Writer;

public class XmlUtil {

    public static Document toXml(Object o) throws Exception {
        Marshaller jaxbMarshaller = createMarshaller(o);
        Document d = createDocument();
        jaxbMarshaller.marshal(o, d);
        return d;
    }

    private static Marshaller createMarshaller(Object o) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return jaxbMarshaller;
    }

    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.newDocument();
    }
}
