package javax.xml.xpath;

public abstract class XPathFactory {
  public static final String DEFAULT_PROPERTY_NAME = "javax.xml.xpath.XPathFactory";
  
  public static final String DEFAULT_OBJECT_MODEL_URI = "http://java.sun.com/jaxp/xpath/dom";
  
  static Class a;
  
  public static final XPathFactory newInstance() {
    try {
      return newInstance("http://java.sun.com/jaxp/xpath/dom");
    } catch (XPathFactoryConfigurationException xPathFactoryConfigurationException) {
      throw new RuntimeException("XPathFactory#newInstance() failed to create an XPathFactory for the default object model: http://java.sun.com/jaxp/xpath/dom with the XPathFactoryConfigurationException: " + xPathFactoryConfigurationException.toString());
    } 
  }
  
  public static final XPathFactory newInstance(String paramString) throws XPathFactoryConfigurationException {
    if (paramString == null)
      throw new NullPointerException("XPathFactory#newInstance(String uri) cannot be called with uri == null"); 
    if (paramString.length() == 0)
      throw new IllegalArgumentException("XPathFactory#newInstance(String uri) cannot be called with uri == \"\""); 
    ClassLoader classLoader = SecuritySupport.a();
    if (classLoader == null)
      classLoader = ((a == null) ? (a = a("javax.xml.xpath.XPathFactory")) : a).getClassLoader(); 
    XPathFactory xPathFactory = (new XPathFactoryFinder(classLoader)).newFactory(paramString);
    if (xPathFactory == null)
      throw new XPathFactoryConfigurationException("No XPathFctory implementation found for the object model: " + paramString); 
    return xPathFactory;
  }
  
  public static XPathFactory newInstance(String paramString1, String paramString2, ClassLoader paramClassLoader) throws XPathFactoryConfigurationException {
    if (paramString1 == null)
      throw new NullPointerException("XPathFactory#newInstance(String uri) cannot be called with uri == null"); 
    if (paramString1.length() == 0)
      throw new IllegalArgumentException("XPathFactory#newInstance(String uri) cannot be called with uri == \"\""); 
    if (paramString2 == null)
      throw new XPathFactoryConfigurationException("factoryClassName cannot be null."); 
    if (paramClassLoader == null)
      paramClassLoader = SecuritySupport.a(); 
    XPathFactory xPathFactory = (new XPathFactoryFinder(paramClassLoader)).a(paramString2);
    if (xPathFactory == null || !xPathFactory.isObjectModelSupported(paramString1))
      throw new XPathFactoryConfigurationException("No XPathFctory implementation found for the object model: " + paramString1); 
    return xPathFactory;
  }
  
  public abstract boolean isObjectModelSupported(String paramString);
  
  public abstract void setFeature(String paramString, boolean paramBoolean) throws XPathFactoryConfigurationException;
  
  public abstract boolean getFeature(String paramString) throws XPathFactoryConfigurationException;
  
  public abstract void setXPathVariableResolver(XPathVariableResolver paramXPathVariableResolver);
  
  public abstract void setXPathFunctionResolver(XPathFunctionResolver paramXPathFunctionResolver);
  
  public abstract XPath newXPath();
  
  static Class a(String paramString) {
    try {
      return Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/xpath/XPathFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */