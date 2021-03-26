package com.levigo.jbig2.io;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.stream.ImageInputStream;

public interface InputStreamFactory {
  ImageInputStream getInputStream(InputStream paramInputStream) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/io/InputStreamFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */