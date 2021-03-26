package org.apache.http.nio;

import org.apache.http.nio.reactor.SessionOutputBuffer;

public interface NHttpMessageWriterFactory<T extends org.apache.http.HttpMessage> {
  NHttpMessageWriter<T> create(SessionOutputBuffer paramSessionOutputBuffer);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpMessageWriterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */