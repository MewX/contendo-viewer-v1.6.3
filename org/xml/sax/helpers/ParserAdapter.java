package org.xml.sax.helpers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class ParserAdapter implements DocumentHandler, XMLReader {
  private static final String f = "http://xml.org/sax/features/";
  
  private static final String g = "http://xml.org/sax/features/namespaces";
  
  private static final String h = "http://xml.org/sax/features/namespace-prefixes";
  
  private static final String i = "http://xml.org/sax/features/xmlns-uris";
  
  private NamespaceSupport j;
  
  private AttributeListAdapter k;
  
  private boolean l = false;
  
  private String[] m = new String[3];
  
  private Parser n = null;
  
  private AttributesImpl o = null;
  
  private boolean p = true;
  
  private boolean q = false;
  
  private boolean r = false;
  
  Locator a;
  
  EntityResolver b = null;
  
  DTDHandler c = null;
  
  ContentHandler d = null;
  
  ErrorHandler e = null;
  
  public ParserAdapter() throws SAXException {
    String str = System.getProperty("org.xml.sax.parser");
    try {
      a(ParserFactory.makeParser());
    } catch (ClassNotFoundException classNotFoundException) {
      throw new SAXException("Cannot find SAX1 driver class " + str, classNotFoundException);
    } catch (IllegalAccessException illegalAccessException) {
      throw new SAXException("SAX1 driver class " + str + " found but cannot be loaded", illegalAccessException);
    } catch (InstantiationException instantiationException) {
      throw new SAXException("SAX1 driver class " + str + " loaded but cannot be instantiated", instantiationException);
    } catch (ClassCastException classCastException) {
      throw new SAXException("SAX1 driver class " + str + " does not implement org.xml.sax.Parser");
    } catch (NullPointerException nullPointerException) {
      throw new SAXException("System property org.xml.sax.parser not specified");
    } 
  }
  
  public ParserAdapter(Parser paramParser) {
    a(paramParser);
  }
  
  private void a(Parser paramParser) {
    if (paramParser == null)
      throw new NullPointerException("Parser argument must not be null"); 
    this.n = paramParser;
    this.o = new AttributesImpl();
    this.j = new NamespaceSupport();
    this.k = new AttributeListAdapter(this);
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString.equals("http://xml.org/sax/features/namespaces")) {
      a("feature", paramString);
      this.p = paramBoolean;
      if (!this.p && !this.q)
        this.q = true; 
    } else if (paramString.equals("http://xml.org/sax/features/namespace-prefixes")) {
      a("feature", paramString);
      this.q = paramBoolean;
      if (!this.q && !this.p)
        this.p = true; 
    } else if (paramString.equals("http://xml.org/sax/features/xmlns-uris")) {
      a("feature", paramString);
      this.r = paramBoolean;
    } else {
      throw new SAXNotRecognizedException("Feature: " + paramString);
    } 
  }
  
  public boolean getFeature(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString.equals("http://xml.org/sax/features/namespaces"))
      return this.p; 
    if (paramString.equals("http://xml.org/sax/features/namespace-prefixes"))
      return this.q; 
    if (paramString.equals("http://xml.org/sax/features/xmlns-uris"))
      return this.r; 
    throw new SAXNotRecognizedException("Feature: " + paramString);
  }
  
  public void setProperty(String paramString, Object paramObject) throws SAXNotRecognizedException, SAXNotSupportedException {
    throw new SAXNotRecognizedException("Property: " + paramString);
  }
  
  public Object getProperty(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    throw new SAXNotRecognizedException("Property: " + paramString);
  }
  
  public void setEntityResolver(EntityResolver paramEntityResolver) {
    this.b = paramEntityResolver;
  }
  
  public EntityResolver getEntityResolver() {
    return this.b;
  }
  
  public void setDTDHandler(DTDHandler paramDTDHandler) {
    this.c = paramDTDHandler;
  }
  
  public DTDHandler getDTDHandler() {
    return this.c;
  }
  
  public void setContentHandler(ContentHandler paramContentHandler) {
    this.d = paramContentHandler;
  }
  
  public ContentHandler getContentHandler() {
    return this.d;
  }
  
  public void setErrorHandler(ErrorHandler paramErrorHandler) {
    this.e = paramErrorHandler;
  }
  
  public ErrorHandler getErrorHandler() {
    return this.e;
  }
  
  public void parse(String paramString) throws IOException, SAXException {
    parse(new InputSource(paramString));
  }
  
  public void parse(InputSource paramInputSource) throws IOException, SAXException {
    if (this.l)
      throw new SAXException("Parser is already in use"); 
    a();
    this.l = true;
    try {
      this.n.parse(paramInputSource);
    } finally {
      this.l = false;
    } 
    this.l = false;
  }
  
  public void setDocumentLocator(Locator paramLocator) {
    this.a = paramLocator;
    if (this.d != null)
      this.d.setDocumentLocator(paramLocator); 
  }
  
  public void startDocument() throws SAXException {
    if (this.d != null)
      this.d.startDocument(); 
  }
  
  public void endDocument() throws SAXException {
    if (this.d != null)
      this.d.endDocument(); 
  }
  
  public void startElement(String paramString, AttributeList paramAttributeList) throws SAXException {
    Vector vector = null;
    if (!this.p) {
      if (this.d != null) {
        this.k.a(paramAttributeList);
        this.d.startElement("", "", paramString.intern(), this.k);
      } 
      return;
    } 
    this.j.pushContext();
    int i = paramAttributeList.getLength();
    for (byte b1 = 0; b1 < i; b1++) {
      String str2;
      String str1 = paramAttributeList.getName(b1);
      if (!str1.startsWith("xmlns"))
        continue; 
      int j = str1.indexOf(':');
      if (j == -1 && str1.length() == 5) {
        str2 = "";
      } else {
        if (j != 5)
          continue; 
        str2 = str1.substring(j + 1);
      } 
      String str3 = paramAttributeList.getValue(b1);
      if (!this.j.declarePrefix(str2, str3)) {
        a("Illegal Namespace prefix: " + str2);
      } else if (this.d != null) {
        this.d.startPrefixMapping(str2, str3);
      } 
      continue;
    } 
    this.o.clear();
    for (byte b2 = 0; b2 < i; b2++) {
      String str1 = paramAttributeList.getName(b2);
      String str2 = paramAttributeList.getType(b2);
      String str3 = paramAttributeList.getValue(b2);
      if (str1.startsWith("xmlns")) {
        String str;
        int j = str1.indexOf(':');
        if (j == -1 && str1.length() == 5) {
          str = "";
        } else if (j != 5) {
          str = null;
        } else {
          str = str1.substring(6);
        } 
        if (str != null) {
          if (this.q)
            if (this.r) {
              this;
              this.o.addAttribute("http://www.w3.org/XML/1998/namespace", str, str1.intern(), str2, str3);
            } else {
              this.o.addAttribute("", "", str1.intern(), str2, str3);
            }  
          continue;
        } 
      } 
      try {
        String[] arrayOfString = a(str1, true, true);
        this.o.addAttribute(arrayOfString[0], arrayOfString[1], arrayOfString[2], str2, str3);
      } catch (SAXException sAXException) {
        if (vector == null)
          vector = new Vector(); 
        vector.addElement(sAXException);
        this.o.addAttribute("", str1, str1, str2, str3);
      } 
      continue;
    } 
    if (vector != null && this.e != null)
      for (byte b = 0; b < vector.size(); b++)
        this.e.error((SAXParseException)vector.elementAt(b));  
    if (this.d != null) {
      String[] arrayOfString = a(paramString, false, false);
      this.d.startElement(arrayOfString[0], arrayOfString[1], arrayOfString[2], this.o);
    } 
  }
  
  public void endElement(String paramString) throws SAXException {
    if (!this.p) {
      if (this.d != null)
        this.d.endElement("", "", paramString.intern()); 
      return;
    } 
    String[] arrayOfString = a(paramString, false, false);
    if (this.d != null) {
      this.d.endElement(arrayOfString[0], arrayOfString[1], arrayOfString[2]);
      Enumeration enumeration = this.j.getDeclaredPrefixes();
      while (enumeration.hasMoreElements()) {
        String str = enumeration.nextElement();
        this.d.endPrefixMapping(str);
      } 
    } 
    this.j.popContext();
  }
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    if (this.d != null)
      this.d.characters(paramArrayOfchar, paramInt1, paramInt2); 
  }
  
  public void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    if (this.d != null)
      this.d.ignorableWhitespace(paramArrayOfchar, paramInt1, paramInt2); 
  }
  
  public void processingInstruction(String paramString1, String paramString2) throws SAXException {
    if (this.d != null)
      this.d.processingInstruction(paramString1, paramString2); 
  }
  
  private void a() {
    if (!this.q && !this.p)
      throw new IllegalStateException(); 
    this.j.reset();
    if (this.r)
      this.j.setNamespaceDeclUris(true); 
    if (this.b != null)
      this.n.setEntityResolver(this.b); 
    if (this.c != null)
      this.n.setDTDHandler(this.c); 
    if (this.e != null)
      this.n.setErrorHandler(this.e); 
    this.n.setDocumentHandler(this);
    this.a = null;
  }
  
  private String[] a(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws SAXException {
    String[] arrayOfString = this.j.processName(paramString, this.m, paramBoolean1);
    if (arrayOfString == null) {
      if (paramBoolean2)
        throw b("Undeclared prefix: " + paramString); 
      a("Undeclared prefix: " + paramString);
      arrayOfString = new String[3];
      arrayOfString[1] = "";
      arrayOfString[0] = "";
      arrayOfString[2] = paramString.intern();
    } 
    return arrayOfString;
  }
  
  void a(String paramString) throws SAXException {
    if (this.e != null)
      this.e.error(b(paramString)); 
  }
  
  private SAXParseException b(String paramString) {
    return (this.a != null) ? new SAXParseException(paramString, this.a) : new SAXParseException(paramString, null, null, -1, -1);
  }
  
  private void a(String paramString1, String paramString2) throws SAXNotSupportedException {
    if (this.l)
      throw new SAXNotSupportedException("Cannot change " + paramString1 + ' ' + paramString2 + " while parsing"); 
  }
  
  static AttributesImpl a(ParserAdapter paramParserAdapter) {
    return paramParserAdapter.o;
  }
  
  final class AttributeListAdapter implements Attributes {
    private AttributeList a;
    
    private final ParserAdapter b;
    
    AttributeListAdapter(ParserAdapter this$0) {
      this.b = this$0;
    }
    
    void a(AttributeList param1AttributeList) {
      this.a = param1AttributeList;
    }
    
    public int getLength() {
      return this.a.getLength();
    }
    
    public String getURI(int param1Int) {
      return "";
    }
    
    public String getLocalName(int param1Int) {
      return "";
    }
    
    public String getQName(int param1Int) {
      return this.a.getName(param1Int).intern();
    }
    
    public String getType(int param1Int) {
      return this.a.getType(param1Int).intern();
    }
    
    public String getValue(int param1Int) {
      return this.a.getValue(param1Int);
    }
    
    public int getIndex(String param1String1, String param1String2) {
      return -1;
    }
    
    public int getIndex(String param1String) {
      int i = ParserAdapter.a(this.b).getLength();
      for (byte b = 0; b < i; b++) {
        if (this.a.getName(b).equals(param1String))
          return b; 
      } 
      return -1;
    }
    
    public String getType(String param1String1, String param1String2) {
      return null;
    }
    
    public String getType(String param1String) {
      return this.a.getType(param1String).intern();
    }
    
    public String getValue(String param1String1, String param1String2) {
      return null;
    }
    
    public String getValue(String param1String) {
      return this.a.getValue(param1String);
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/helpers/ParserAdapter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */