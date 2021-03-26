package javax.xml.parsers;

import javax.xml.validation.Schema;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public abstract class SAXParserFactory {
  private boolean validating = false;
  
  private boolean namespaceAware = false;
  
  public static SAXParserFactory newInstance() {
    try {
      return (SAXParserFactory)FactoryFinder.a("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
    } catch (ConfigurationError configurationError) {
      throw new FactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public static SAXParserFactory newInstance(String paramString, ClassLoader paramClassLoader) {
    if (paramString == null)
      throw new FactoryConfigurationError("factoryClassName cannot be null."); 
    if (paramClassLoader == null)
      paramClassLoader = SecuritySupport.a(); 
    try {
      return (SAXParserFactory)FactoryFinder.a(paramString, paramClassLoader, false);
    } catch (ConfigurationError configurationError) {
      throw new FactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public abstract SAXParser newSAXParser() throws ParserConfigurationException, SAXException;
  
  public void setNamespaceAware(boolean paramBoolean) {
    this.namespaceAware = paramBoolean;
  }
  
  public void setValidating(boolean paramBoolean) {
    this.validating = paramBoolean;
  }
  
  public boolean isNamespaceAware() {
    return this.namespaceAware;
  }
  
  public boolean isValidating() {
    return this.validating;
  }
  
  public abstract void setFeature(String paramString, boolean paramBoolean) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException;
  
  public abstract boolean getFeature(String paramString) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException;
  
  public Schema getSchema() {
    throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
  }
  
  public void setSchema(Schema paramSchema) {
    throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
  }
  
  public void setXIncludeAware(boolean paramBoolean) {
    throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
  }
  
  public boolean isXIncludeAware() {
    throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/parsers/SAXParserFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */