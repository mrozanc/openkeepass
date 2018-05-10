package de.slackspace.openkeepass.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Test;

import de.slackspace.openkeepass.parser.SimpleV3XmlParser;
import de.slackspace.openkeepass.util.XmlStringCleaner;

public class PropertyValueTest {

    @Test
    public void shouldMarshallObjectToXml() throws Exception {
        PropertyValue propertyValue = new PropertyValue(false, "TestValue");
        
        ByteArrayOutputStream bos = new SimpleV3XmlParser().toXml(propertyValue);
        
        String xml = XmlStringCleaner.cleanXmlString(new String(bos.toByteArray()));
        Assert.assertEquals("<propertyValue Protected='False'>TestValue</propertyValue>", xml);
    }
    
    @Test
    public void shouldUnmarshallXmlToObject() throws Exception {
        PropertyValue propertyValue = new PropertyValue(false, "TestValue");

        String xml = "<propertyValue Protected='False'>TestValue</propertyValue>";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        PropertyValue propertyValueUnmarshalled = new SimpleV3XmlParser().fromXml(inputStream, PropertyValue.class);

        Assert.assertEquals(propertyValue.getValue(), propertyValueUnmarshalled.getValue());
    }
    
}
