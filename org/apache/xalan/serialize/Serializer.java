package org.apache.xalan.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Properties;
import org.xml.sax.ContentHandler;

public interface Serializer {
  void setOutputStream(OutputStream paramOutputStream);
  
  OutputStream getOutputStream();
  
  void setWriter(Writer paramWriter);
  
  Writer getWriter();
  
  void setOutputFormat(Properties paramProperties);
  
  Properties getOutputFormat();
  
  ContentHandler asContentHandler() throws IOException;
  
  DOMSerializer asDOMSerializer() throws IOException;
  
  boolean reset();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/serialize/Serializer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */