package javax.xml.stream;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;

public abstract class XMLEventFactory {
  private static final String PROPERTY_NAME = "javax.xml.stream.XMLEventFactory";
  
  private static final String DEFAULT_FACTORY = "org.apache.xerces.stax.XMLEventFactoryImpl";
  
  public static XMLEventFactory newInstance() throws FactoryConfigurationError {
    try {
      return (XMLEventFactory)FactoryFinder.a("javax.xml.stream.XMLEventFactory", "org.apache.xerces.stax.XMLEventFactoryImpl");
    } catch (ConfigurationError configurationError) {
      throw new FactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public static XMLEventFactory newInstance(String paramString, ClassLoader paramClassLoader) throws FactoryConfigurationError {
    if (paramClassLoader == null)
      paramClassLoader = SecuritySupport.a(); 
    try {
      return (XMLEventFactory)FactoryFinder.a(paramString, paramClassLoader, "org.apache.xerces.stax.XMLEventFactoryImpl");
    } catch (ConfigurationError configurationError) {
      throw new FactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public abstract void setLocation(Location paramLocation);
  
  public abstract Attribute createAttribute(QName paramQName, String paramString);
  
  public abstract Attribute createAttribute(String paramString1, String paramString2);
  
  public abstract Attribute createAttribute(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract Namespace createNamespace(String paramString);
  
  public abstract Namespace createNamespace(String paramString1, String paramString2);
  
  public abstract StartElement createStartElement(QName paramQName, Iterator paramIterator1, Iterator paramIterator2);
  
  public abstract StartElement createStartElement(String paramString1, String paramString2, String paramString3);
  
  public abstract StartElement createStartElement(String paramString1, String paramString2, String paramString3, Iterator paramIterator1, Iterator paramIterator2);
  
  public abstract StartElement createStartElement(String paramString1, String paramString2, String paramString3, Iterator paramIterator1, Iterator paramIterator2, NamespaceContext paramNamespaceContext);
  
  public abstract EndElement createEndElement(QName paramQName, Iterator paramIterator);
  
  public abstract EndElement createEndElement(String paramString1, String paramString2, String paramString3);
  
  public abstract EndElement createEndElement(String paramString1, String paramString2, String paramString3, Iterator paramIterator);
  
  public abstract Characters createCharacters(String paramString);
  
  public abstract Characters createCData(String paramString);
  
  public abstract Characters createSpace(String paramString);
  
  public abstract Characters createIgnorableSpace(String paramString);
  
  public abstract StartDocument createStartDocument();
  
  public abstract StartDocument createStartDocument(String paramString);
  
  public abstract StartDocument createStartDocument(String paramString1, String paramString2);
  
  public abstract StartDocument createStartDocument(String paramString1, String paramString2, boolean paramBoolean);
  
  public abstract EndDocument createEndDocument();
  
  public abstract EntityReference createEntityReference(String paramString, EntityDeclaration paramEntityDeclaration);
  
  public abstract Comment createComment(String paramString);
  
  public abstract ProcessingInstruction createProcessingInstruction(String paramString1, String paramString2);
  
  public abstract DTD createDTD(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/XMLEventFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */