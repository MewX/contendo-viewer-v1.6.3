package org.apache.batik.ext.awt.image.spi;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

public interface ImageWriter {
  void writeImage(RenderedImage paramRenderedImage, OutputStream paramOutputStream) throws IOException;
  
  void writeImage(RenderedImage paramRenderedImage, OutputStream paramOutputStream, ImageWriterParams paramImageWriterParams) throws IOException;
  
  String getMIMEType();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/ImageWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */