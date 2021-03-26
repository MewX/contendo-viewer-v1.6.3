package org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.util.MessageFormatter;

public final class UTF16Reader extends Reader {
  public static final int DEFAULT_BUFFER_SIZE = 4096;
  
  protected final InputStream fInputStream;
  
  protected final byte[] fBuffer;
  
  protected final boolean fIsBigEndian;
  
  private final MessageFormatter fFormatter;
  
  private final Locale fLocale;
  
  public UTF16Reader(InputStream paramInputStream, boolean paramBoolean) {
    this(paramInputStream, 4096, paramBoolean, (MessageFormatter)new XMLMessageFormatter(), Locale.getDefault());
  }
  
  public UTF16Reader(InputStream paramInputStream, boolean paramBoolean, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this(paramInputStream, 4096, paramBoolean, paramMessageFormatter, paramLocale);
  }
  
  public UTF16Reader(InputStream paramInputStream, int paramInt, boolean paramBoolean, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this(paramInputStream, new byte[paramInt], paramBoolean, paramMessageFormatter, paramLocale);
  }
  
  public UTF16Reader(InputStream paramInputStream, byte[] paramArrayOfbyte, boolean paramBoolean, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this.fInputStream = paramInputStream;
    this.fBuffer = paramArrayOfbyte;
    this.fIsBigEndian = paramBoolean;
    this.fFormatter = paramMessageFormatter;
    this.fLocale = paramLocale;
  }
  
  public int read() throws IOException {
    int i = this.fInputStream.read();
    if (i == -1)
      return -1; 
    int j = this.fInputStream.read();
    if (j == -1)
      expectedTwoBytes(); 
    return this.fIsBigEndian ? (i << 8 | j) : (j << 8 | i);
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    int i = paramInt2 << 1;
    if (i > this.fBuffer.length)
      i = this.fBuffer.length; 
    int j = this.fInputStream.read(this.fBuffer, 0, i);
    if (j == -1)
      return -1; 
    if ((j & 0x1) != 0) {
      int m = this.fInputStream.read();
      if (m == -1)
        expectedTwoBytes(); 
      this.fBuffer[j++] = (byte)m;
    } 
    int k = j >> 1;
    if (this.fIsBigEndian) {
      processBE(paramArrayOfchar, paramInt1, k);
    } else {
      processLE(paramArrayOfchar, paramInt1, k);
    } 
    return k;
  }
  
  public long skip(long paramLong) throws IOException {
    long l = this.fInputStream.skip(paramLong << 1L);
    if ((l & 0x1L) != 0L) {
      int i = this.fInputStream.read();
      if (i == -1)
        expectedTwoBytes(); 
      l++;
    } 
    return l >> 1L;
  }
  
  public boolean ready() throws IOException {
    return false;
  }
  
  public boolean markSupported() {
    return false;
  }
  
  public void mark(int paramInt) throws IOException {
    throw new IOException(this.fFormatter.formatMessage(this.fLocale, "OperationNotSupported", new Object[] { "mark()", "UTF-16" }));
  }
  
  public void reset() throws IOException {}
  
  public void close() throws IOException {
    this.fInputStream.close();
  }
  
  private void processBE(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramInt2; b2++) {
      int i = this.fBuffer[b1++] & 0xFF;
      int j = this.fBuffer[b1++] & 0xFF;
      paramArrayOfchar[paramInt1++] = (char)(i << 8 | j);
    } 
  }
  
  private void processLE(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramInt2; b2++) {
      int i = this.fBuffer[b1++] & 0xFF;
      int j = this.fBuffer[b1++] & 0xFF;
      paramArrayOfchar[paramInt1++] = (char)(j << 8 | i);
    } 
  }
  
  private void expectedTwoBytes() throws MalformedByteSequenceException {
    throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "ExpectedByte", new Object[] { "2", "2" });
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/io/UTF16Reader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */