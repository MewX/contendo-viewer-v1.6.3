package org.apache.http.nio;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import org.apache.http.HttpException;

public interface NHttpMessageParser<T extends org.apache.http.HttpMessage> {
  void reset();
  
  int fillBuffer(ReadableByteChannel paramReadableByteChannel) throws IOException;
  
  T parse() throws IOException, HttpException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpMessageParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */