package javax.xml.transform;

public abstract class TransformerFactory {
  public static TransformerFactory newInstance() throws TransformerFactoryConfigurationError {
    try {
      return (TransformerFactory)FactoryFinder.a("javax.xml.transform.TransformerFactory", "org.apache.xalan.processor.TransformerFactoryImpl");
    } catch (ConfigurationError configurationError) {
      throw new TransformerFactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public static TransformerFactory newInstance(String paramString, ClassLoader paramClassLoader) throws TransformerFactoryConfigurationError {
    if (paramString == null)
      throw new TransformerFactoryConfigurationError("factoryClassName cannot be null."); 
    if (paramClassLoader == null)
      paramClassLoader = SecuritySupport.a(); 
    try {
      return (TransformerFactory)FactoryFinder.a(paramString, paramClassLoader, false);
    } catch (ConfigurationError configurationError) {
      throw new TransformerFactoryConfigurationError(configurationError.a(), configurationError.getMessage());
    } 
  }
  
  public abstract Transformer newTransformer(Source paramSource) throws TransformerConfigurationException;
  
  public abstract Transformer newTransformer() throws TransformerConfigurationException;
  
  public abstract Templates newTemplates(Source paramSource) throws TransformerConfigurationException;
  
  public abstract Source getAssociatedStylesheet(Source paramSource, String paramString1, String paramString2, String paramString3) throws TransformerConfigurationException;
  
  public abstract void setURIResolver(URIResolver paramURIResolver);
  
  public abstract URIResolver getURIResolver();
  
  public abstract void setFeature(String paramString, boolean paramBoolean) throws TransformerConfigurationException;
  
  public abstract boolean getFeature(String paramString);
  
  public abstract void setAttribute(String paramString, Object paramObject);
  
  public abstract Object getAttribute(String paramString);
  
  public abstract void setErrorListener(ErrorListener paramErrorListener);
  
  public abstract ErrorListener getErrorListener();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/TransformerFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */