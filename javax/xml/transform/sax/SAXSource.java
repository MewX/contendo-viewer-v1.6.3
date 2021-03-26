package javax.xml.transform.sax;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class SAXSource implements Source {
  public static final String FEATURE = "http://javax.xml.transform.sax.SAXSource/feature";
  
  private XMLReader a;
  
  private InputSource b;
  
  public SAXSource() {}
  
  public SAXSource(XMLReader paramXMLReader, InputSource paramInputSource) {
    this.a = paramXMLReader;
    this.b = paramInputSource;
  }
  
  public SAXSource(InputSource paramInputSource) {
    this.b = paramInputSource;
  }
  
  public void setXMLReader(XMLReader paramXMLReader) {
    this.a = paramXMLReader;
  }
  
  public XMLReader getXMLReader() {
    return this.a;
  }
  
  public void setInputSource(InputSource paramInputSource) {
    this.b = paramInputSource;
  }
  
  public InputSource getInputSource() {
    return this.b;
  }
  
  public void setSystemId(String paramString) {
    if (null == this.b) {
      this.b = new InputSource(paramString);
    } else {
      this.b.setSystemId(paramString);
    } 
  }
  
  public String getSystemId() {
    return (this.b == null) ? null : this.b.getSystemId();
  }
  
  public static InputSource sourceToInputSource(Source paramSource) {
    if (paramSource instanceof SAXSource)
      return ((SAXSource)paramSource).getInputSource(); 
    if (paramSource instanceof StreamSource) {
      StreamSource streamSource = (StreamSource)paramSource;
      InputSource inputSource = new InputSource(streamSource.getSystemId());
      inputSource.setByteStream(streamSource.getInputStream());
      inputSource.setCharacterStream(streamSource.getReader());
      inputSource.setPublicId(streamSource.getPublicId());
      return inputSource;
    } 
    return null;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/sax/SAXSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */