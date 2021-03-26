package org.apache.pdfbox.contentstream;

import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.Matrix;

public interface PDContentStream {
  InputStream getContents() throws IOException;
  
  PDResources getResources();
  
  PDRectangle getBBox();
  
  Matrix getMatrix();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/PDContentStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */