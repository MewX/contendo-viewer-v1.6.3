package javax.xml.stream;

import java.io.OutputStream;
import java.io.Writer;
import javax.xml.transform.Result;

public abstract class XMLOutputFactory {
  public static final String IS_REPAIRING_NAMESPACES = "javax.xml.stream.isRepairingNamespaces";
  
  private static final String a = "javax.xml.stream.XMLOutputFactory";
  
  private static final String b = "com.ctc.wstx.stax.WstxOutputFactory";
  
  public static XMLOutputFactory newInstance() throws FactoryConfigurationError {
    try {
      return (XMLOutputFactory)FactoryFinder.a("javax.xml.stream.XMLOutputFactory", "com.ctc.wstx.stax.WstxOutputFactory");
    } catch (ConfigurationError configurationError) {
      throw new FactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public static XMLInputFactory newInstance(String paramString, ClassLoader paramClassLoader) throws FactoryConfigurationError {
    if (paramClassLoader == null)
      paramClassLoader = SecuritySupport.a(); 
    try {
      return (XMLInputFactory)FactoryFinder.a(paramString, paramClassLoader, "com.ctc.wstx.stax.WstxInputFactory");
    } catch (ConfigurationError configurationError) {
      throw new FactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public abstract XMLStreamWriter createXMLStreamWriter(Writer paramWriter) throws XMLStreamException;
  
  public abstract XMLStreamWriter createXMLStreamWriter(OutputStream paramOutputStream) throws XMLStreamException;
  
  public abstract XMLStreamWriter createXMLStreamWriter(OutputStream paramOutputStream, String paramString) throws XMLStreamException;
  
  public abstract XMLStreamWriter createXMLStreamWriter(Result paramResult) throws XMLStreamException;
  
  public abstract XMLEventWriter createXMLEventWriter(Result paramResult) throws XMLStreamException;
  
  public abstract XMLEventWriter createXMLEventWriter(OutputStream paramOutputStream) throws XMLStreamException;
  
  public abstract XMLEventWriter createXMLEventWriter(OutputStream paramOutputStream, String paramString) throws XMLStreamException;
  
  public abstract XMLEventWriter createXMLEventWriter(Writer paramWriter) throws XMLStreamException;
  
  public abstract void setProperty(String paramString, Object paramObject) throws IllegalArgumentException;
  
  public abstract Object getProperty(String paramString) throws IllegalArgumentException;
  
  public abstract boolean isPropertySupported(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/XMLOutputFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */