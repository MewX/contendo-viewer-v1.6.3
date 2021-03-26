package javax.xml.transform.stream;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import javax.xml.transform.Source;

public class StreamSource implements Source {
  public static final String FEATURE = "http://javax.xml.transform.stream.StreamSource/feature";
  
  private String a;
  
  private String b;
  
  private InputStream c;
  
  private Reader d;
  
  public StreamSource() {}
  
  public StreamSource(InputStream paramInputStream) {
    setInputStream(paramInputStream);
  }
  
  public StreamSource(InputStream paramInputStream, String paramString) {
    setInputStream(paramInputStream);
    setSystemId(paramString);
  }
  
  public StreamSource(Reader paramReader) {
    setReader(paramReader);
  }
  
  public StreamSource(Reader paramReader, String paramString) {
    setReader(paramReader);
    setSystemId(paramString);
  }
  
  public StreamSource(String paramString) {
    this.b = paramString;
  }
  
  public StreamSource(File paramFile) {
    setSystemId(paramFile);
  }
  
  public void setInputStream(InputStream paramInputStream) {
    this.c = paramInputStream;
  }
  
  public InputStream getInputStream() {
    return this.c;
  }
  
  public void setReader(Reader paramReader) {
    this.d = paramReader;
  }
  
  public Reader getReader() {
    return this.d;
  }
  
  public void setPublicId(String paramString) {
    this.a = paramString;
  }
  
  public String getPublicId() {
    return this.a;
  }
  
  public void setSystemId(String paramString) {
    this.b = paramString;
  }
  
  public String getSystemId() {
    return this.b;
  }
  
  public void setSystemId(File paramFile) {
    this.b = FilePathToURI.filepath2URI(paramFile.getAbsolutePath());
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/stream/StreamSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */