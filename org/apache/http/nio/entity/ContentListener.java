package org.apache.http.nio.entity;

import java.io.IOException;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.IOControl;

@Deprecated
public interface ContentListener {
  void contentAvailable(ContentDecoder paramContentDecoder, IOControl paramIOControl) throws IOException;
  
  void finished();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/ContentListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */