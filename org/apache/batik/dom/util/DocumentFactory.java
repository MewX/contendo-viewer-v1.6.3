package org.apache.batik.dom.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.w3c.dom.Document;
import org.xml.sax.XMLReader;

public interface DocumentFactory {
  void setValidating(boolean paramBoolean);
  
  boolean isValidating();
  
  Document createDocument(String paramString1, String paramString2, String paramString3) throws IOException;
  
  Document createDocument(String paramString1, String paramString2, String paramString3, InputStream paramInputStream) throws IOException;
  
  Document createDocument(String paramString1, String paramString2, String paramString3, XMLReader paramXMLReader) throws IOException;
  
  Document createDocument(String paramString1, String paramString2, String paramString3, Reader paramReader) throws IOException;
  
  DocumentDescriptor getDocumentDescriptor();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/DocumentFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */