package javax.xml.transform.sax;

import javax.xml.transform.Result;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public class SAXResult implements Result {
  public static final String FEATURE = "http://javax.xml.transform.sax.SAXResult/feature";
  
  private ContentHandler a;
  
  private LexicalHandler b;
  
  private String c;
  
  public SAXResult() {}
  
  public SAXResult(ContentHandler paramContentHandler) {
    setHandler(paramContentHandler);
  }
  
  public void setHandler(ContentHandler paramContentHandler) {
    this.a = paramContentHandler;
  }
  
  public ContentHandler getHandler() {
    return this.a;
  }
  
  public void setLexicalHandler(LexicalHandler paramLexicalHandler) {
    this.b = paramLexicalHandler;
  }
  
  public LexicalHandler getLexicalHandler() {
    return this.b;
  }
  
  public void setSystemId(String paramString) {
    this.c = paramString;
  }
  
  public String getSystemId() {
    return this.c;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/sax/SAXResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */