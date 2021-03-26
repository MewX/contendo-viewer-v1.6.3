package org.apache.xmlgraphics.image.writer;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

public interface ImageWriter {
  void writeImage(RenderedImage paramRenderedImage, OutputStream paramOutputStream) throws IOException;
  
  void writeImage(RenderedImage paramRenderedImage, OutputStream paramOutputStream, ImageWriterParams paramImageWriterParams) throws IOException;
  
  String getMIMEType();
  
  boolean isFunctional();
  
  boolean supportsMultiImageWriter();
  
  MultiImageWriter createMultiImageWriter(OutputStream paramOutputStream) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/ImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */