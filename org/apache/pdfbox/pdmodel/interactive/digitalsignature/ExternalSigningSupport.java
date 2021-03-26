package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.IOException;
import java.io.InputStream;

public interface ExternalSigningSupport {
  InputStream getContent() throws IOException;
  
  void setSignature(byte[] paramArrayOfbyte) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/ExternalSigningSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */