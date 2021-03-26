package javax.xml.transform.stream;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.transform.Result;

public class StreamResult implements Result {
  public static final String FEATURE = "http://javax.xml.transform.stream.StreamResult/feature";
  
  private String a;
  
  private OutputStream b;
  
  private Writer c;
  
  public StreamResult() {}
  
  public StreamResult(OutputStream paramOutputStream) {
    setOutputStream(paramOutputStream);
  }
  
  public StreamResult(Writer paramWriter) {
    setWriter(paramWriter);
  }
  
  public StreamResult(String paramString) {
    this.a = paramString;
  }
  
  public StreamResult(File paramFile) {
    setSystemId(paramFile);
  }
  
  public void setOutputStream(OutputStream paramOutputStream) {
    this.b = paramOutputStream;
  }
  
  public OutputStream getOutputStream() {
    return this.b;
  }
  
  public void setWriter(Writer paramWriter) {
    this.c = paramWriter;
  }
  
  public Writer getWriter() {
    return this.c;
  }
  
  public void setSystemId(String paramString) {
    this.a = paramString;
  }
  
  public void setSystemId(File paramFile) {
    this.a = FilePathToURI.filepath2URI(paramFile.getAbsolutePath());
  }
  
  public String getSystemId() {
    return this.a;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/stream/StreamResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */