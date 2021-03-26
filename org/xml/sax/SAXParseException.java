package org.xml.sax;

public class SAXParseException extends SAXException {
  private String b;
  
  private String c;
  
  private int d;
  
  private int e;
  
  static final long a = -5651165872476709336L;
  
  public SAXParseException(String paramString, Locator paramLocator) {
    super(paramString);
    if (paramLocator != null) {
      a(paramLocator.getPublicId(), paramLocator.getSystemId(), paramLocator.getLineNumber(), paramLocator.getColumnNumber());
    } else {
      a(null, null, -1, -1);
    } 
  }
  
  public SAXParseException(String paramString, Locator paramLocator, Exception paramException) {
    super(paramString, paramException);
    if (paramLocator != null) {
      a(paramLocator.getPublicId(), paramLocator.getSystemId(), paramLocator.getLineNumber(), paramLocator.getColumnNumber());
    } else {
      a(null, null, -1, -1);
    } 
  }
  
  public SAXParseException(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2) {
    super(paramString1);
    a(paramString2, paramString3, paramInt1, paramInt2);
  }
  
  public SAXParseException(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, Exception paramException) {
    super(paramString1, paramException);
    a(paramString2, paramString3, paramInt1, paramInt2);
  }
  
  private void a(String paramString1, String paramString2, int paramInt1, int paramInt2) {
    this.b = paramString1;
    this.c = paramString2;
    this.d = paramInt1;
    this.e = paramInt2;
  }
  
  public String getPublicId() {
    return this.b;
  }
  
  public String getSystemId() {
    return this.c;
  }
  
  public int getLineNumber() {
    return this.d;
  }
  
  public int getColumnNumber() {
    return this.e;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/SAXParseException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */