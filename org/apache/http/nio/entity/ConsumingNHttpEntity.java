package org.apache.http.nio.entity;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.IOControl;

@Deprecated
public interface ConsumingNHttpEntity extends HttpEntity {
  void consumeContent(ContentDecoder paramContentDecoder, IOControl paramIOControl) throws IOException;
  
  void finish() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/ConsumingNHttpEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */