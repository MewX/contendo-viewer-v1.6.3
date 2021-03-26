package org.apache.xmlgraphics.image.writer;

import java.awt.image.RenderedImage;
import java.io.IOException;

public interface MultiImageWriter {
  void writeImage(RenderedImage paramRenderedImage, ImageWriterParams paramImageWriterParams) throws IOException;
  
  void close() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/MultiImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */