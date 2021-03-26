package org.apache.http.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.CharacterCodingException;
import org.apache.http.util.CharArrayBuffer;

public interface SessionInputBuffer {
  boolean hasData();
  
  int length();
  
  int fill(ReadableByteChannel paramReadableByteChannel) throws IOException;
  
  int read();
  
  int read(ByteBuffer paramByteBuffer, int paramInt);
  
  int read(ByteBuffer paramByteBuffer);
  
  int read(WritableByteChannel paramWritableByteChannel, int paramInt) throws IOException;
  
  int read(WritableByteChannel paramWritableByteChannel) throws IOException;
  
  boolean readLine(CharArrayBuffer paramCharArrayBuffer, boolean paramBoolean) throws CharacterCodingException;
  
  String readLine(boolean paramBoolean) throws CharacterCodingException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/SessionInputBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */