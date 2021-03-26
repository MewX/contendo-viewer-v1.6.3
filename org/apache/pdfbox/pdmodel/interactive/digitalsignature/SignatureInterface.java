package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.IOException;
import java.io.InputStream;

public interface SignatureInterface {
  byte[] sign(InputStream paramInputStream) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/SignatureInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */