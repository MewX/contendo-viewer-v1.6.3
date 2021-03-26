package org.apache.xerces.impl;

import java.util.Hashtable;
import java.util.Locale;
import org.apache.xerces.util.DefaultErrorHandler;
import org.apache.xerces.util.ErrorHandlerProxy;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.ErrorHandler;

public class XMLErrorReporter implements XMLComponent {
  public static final short SEVERITY_WARNING = 0;
  
  public static final short SEVERITY_ERROR = 1;
  
  public static final short SEVERITY_FATAL_ERROR = 2;
  
  protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
  
  protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
  
  private static final String[] RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/continue-after-fatal-error" };
  
  private static final Boolean[] FEATURE_DEFAULTS = new Boolean[] { null };
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/error-handler" };
  
  private static final Object[] PROPERTY_DEFAULTS = new Object[] { null };
  
  protected Locale fLocale;
  
  protected Hashtable fMessageFormatters = new Hashtable();
  
  protected XMLErrorHandler fErrorHandler;
  
  protected XMLLocator fLocator;
  
  protected boolean fContinueAfterFatalError;
  
  protected XMLErrorHandler fDefaultErrorHandler;
  
  private ErrorHandler fSaxProxy = null;
  
  public void setLocale(Locale paramLocale) {
    this.fLocale = paramLocale;
  }
  
  public Locale getLocale() {
    return this.fLocale;
  }
  
  public void setDocumentLocator(XMLLocator paramXMLLocator) {
    this.fLocator = paramXMLLocator;
  }
  
  public void putMessageFormatter(String paramString, MessageFormatter paramMessageFormatter) {
    this.fMessageFormatters.put(paramString, paramMessageFormatter);
  }
  
  public MessageFormatter getMessageFormatter(String paramString) {
    return (MessageFormatter)this.fMessageFormatters.get(paramString);
  }
  
  public MessageFormatter removeMessageFormatter(String paramString) {
    return (MessageFormatter)this.fMessageFormatters.remove(paramString);
  }
  
  public String reportError(String paramString1, String paramString2, Object[] paramArrayOfObject, short paramShort) throws XNIException {
    return reportError(this.fLocator, paramString1, paramString2, paramArrayOfObject, paramShort);
  }
  
  public String reportError(String paramString1, String paramString2, Object[] paramArrayOfObject, short paramShort, Exception paramException) throws XNIException {
    return reportError(this.fLocator, paramString1, paramString2, paramArrayOfObject, paramShort, paramException);
  }
  
  public String reportError(XMLLocator paramXMLLocator, String paramString1, String paramString2, Object[] paramArrayOfObject, short paramShort) throws XNIException {
    return reportError(paramXMLLocator, paramString1, paramString2, paramArrayOfObject, paramShort, null);
  }
  
  public String reportError(XMLLocator paramXMLLocator, String paramString1, String paramString2, Object[] paramArrayOfObject, short paramShort, Exception paramException) throws XNIException {
    String str;
    MessageFormatter messageFormatter = getMessageFormatter(paramString1);
    if (messageFormatter != null) {
      str = messageFormatter.formatMessage(this.fLocale, paramString2, paramArrayOfObject);
    } else {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append(paramString1);
      stringBuffer.append('#');
      stringBuffer.append(paramString2);
      byte b = (paramArrayOfObject != null) ? paramArrayOfObject.length : 0;
      if (b) {
        stringBuffer.append('?');
        for (byte b1 = 0; b1 < b; b1++) {
          stringBuffer.append(paramArrayOfObject[b1]);
          if (b1 < b - 1)
            stringBuffer.append('&'); 
        } 
      } 
      str = stringBuffer.toString();
    } 
    XMLParseException xMLParseException = (paramException != null) ? new XMLParseException(paramXMLLocator, str, paramException) : new XMLParseException(paramXMLLocator, str);
    XMLErrorHandler xMLErrorHandler = this.fErrorHandler;
    if (xMLErrorHandler == null) {
      if (this.fDefaultErrorHandler == null)
        this.fDefaultErrorHandler = (XMLErrorHandler)new DefaultErrorHandler(); 
      xMLErrorHandler = this.fDefaultErrorHandler;
    } 
    switch (paramShort) {
      case 0:
        xMLErrorHandler.warning(paramString1, paramString2, xMLParseException);
        break;
      case 1:
        xMLErrorHandler.error(paramString1, paramString2, xMLParseException);
        break;
      case 2:
        xMLErrorHandler.fatalError(paramString1, paramString2, xMLParseException);
        if (!this.fContinueAfterFatalError)
          throw xMLParseException; 
        break;
    } 
    return str;
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XNIException {
    try {
      this.fContinueAfterFatalError = paramXMLComponentManager.getFeature("http://apache.org/xml/features/continue-after-fatal-error");
    } catch (XNIException xNIException) {
      this.fContinueAfterFatalError = false;
    } 
    this.fErrorHandler = (XMLErrorHandler)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-handler");
  }
  
  public String[] getRecognizedFeatures() {
    return (String[])RECOGNIZED_FEATURES.clone();
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/features/")) {
      int i = paramString.length() - "http://apache.org/xml/features/".length();
      if (i == "continue-after-fatal-error".length() && paramString.endsWith("continue-after-fatal-error"))
        this.fContinueAfterFatalError = paramBoolean; 
    } 
  }
  
  public boolean getFeature(String paramString) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/features/")) {
      int i = paramString.length() - "http://apache.org/xml/features/".length();
      if (i == "continue-after-fatal-error".length() && paramString.endsWith("continue-after-fatal-error"))
        return this.fContinueAfterFatalError; 
    } 
    return false;
  }
  
  public String[] getRecognizedProperties() {
    return (String[])RECOGNIZED_PROPERTIES.clone();
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/properties/")) {
      int i = paramString.length() - "http://apache.org/xml/properties/".length();
      if (i == "internal/error-handler".length() && paramString.endsWith("internal/error-handler"))
        this.fErrorHandler = (XMLErrorHandler)paramObject; 
    } 
  }
  
  public Boolean getFeatureDefault(String paramString) {
    for (byte b = 0; b < RECOGNIZED_FEATURES.length; b++) {
      if (RECOGNIZED_FEATURES[b].equals(paramString))
        return FEATURE_DEFAULTS[b]; 
    } 
    return null;
  }
  
  public Object getPropertyDefault(String paramString) {
    for (byte b = 0; b < RECOGNIZED_PROPERTIES.length; b++) {
      if (RECOGNIZED_PROPERTIES[b].equals(paramString))
        return PROPERTY_DEFAULTS[b]; 
    } 
    return null;
  }
  
  public XMLErrorHandler getErrorHandler() {
    return this.fErrorHandler;
  }
  
  public ErrorHandler getSAXErrorHandler() {
    if (this.fSaxProxy == null)
      this.fSaxProxy = (ErrorHandler)new ErrorHandlerProxy(this) {
          private final XMLErrorReporter this$0;
          
          protected XMLErrorHandler getErrorHandler() {
            return this.this$0.fErrorHandler;
          }
        }; 
    return this.fSaxProxy;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/XMLErrorReporter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */