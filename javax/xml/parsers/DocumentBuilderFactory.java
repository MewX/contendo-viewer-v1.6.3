package javax.xml.parsers;

import javax.xml.validation.Schema;

public abstract class DocumentBuilderFactory {
  private boolean validating = false;
  
  private boolean namespaceAware = false;
  
  private boolean whitespace = false;
  
  private boolean expandEntityRef = true;
  
  private boolean ignoreComments = false;
  
  private boolean coalescing = false;
  
  public static DocumentBuilderFactory newInstance() {
    try {
      return (DocumentBuilderFactory)FactoryFinder.a("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
    } catch (ConfigurationError configurationError) {
      throw new FactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public static DocumentBuilderFactory newInstance(String paramString, ClassLoader paramClassLoader) {
    if (paramString == null)
      throw new FactoryConfigurationError("factoryClassName cannot be null."); 
    if (paramClassLoader == null)
      paramClassLoader = SecuritySupport.a(); 
    try {
      return (DocumentBuilderFactory)FactoryFinder.a(paramString, paramClassLoader, false);
    } catch (ConfigurationError configurationError) {
      throw new FactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public abstract DocumentBuilder newDocumentBuilder() throws ParserConfigurationException;
  
  public void setNamespaceAware(boolean paramBoolean) {
    this.namespaceAware = paramBoolean;
  }
  
  public void setValidating(boolean paramBoolean) {
    this.validating = paramBoolean;
  }
  
  public void setIgnoringElementContentWhitespace(boolean paramBoolean) {
    this.whitespace = paramBoolean;
  }
  
  public void setExpandEntityReferences(boolean paramBoolean) {
    this.expandEntityRef = paramBoolean;
  }
  
  public void setIgnoringComments(boolean paramBoolean) {
    this.ignoreComments = paramBoolean;
  }
  
  public void setCoalescing(boolean paramBoolean) {
    this.coalescing = paramBoolean;
  }
  
  public boolean isNamespaceAware() {
    return this.namespaceAware;
  }
  
  public boolean isValidating() {
    return this.validating;
  }
  
  public boolean isIgnoringElementContentWhitespace() {
    return this.whitespace;
  }
  
  public boolean isExpandEntityReferences() {
    return this.expandEntityRef;
  }
  
  public boolean isIgnoringComments() {
    return this.ignoreComments;
  }
  
  public boolean isCoalescing() {
    return this.coalescing;
  }
  
  public abstract void setAttribute(String paramString, Object paramObject) throws IllegalArgumentException;
  
  public abstract Object getAttribute(String paramString) throws IllegalArgumentException;
  
  public abstract void setFeature(String paramString, boolean paramBoolean) throws ParserConfigurationException;
  
  public abstract boolean getFeature(String paramString) throws ParserConfigurationException;
  
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/parsers/DocumentBuilderFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */