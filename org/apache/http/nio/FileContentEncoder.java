package org.apache.http.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;

public interface FileContentEncoder extends ContentEncoder {
  long transfer(FileChannel paramFileChannel, long paramLong1, long paramLong2) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/FileContentEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */