package com.a.a.e;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.InvalidMarkException;
import java.nio.ReadOnlyBufferException;

public interface d extends e<b> {
  byte[] e() throws UnsupportedOperationException;
  
  int v() throws UnsupportedOperationException;
  
  boolean j();
  
  int a();
  
  b r();
  
  b m();
  
  int g();
  
  boolean q();
  
  boolean b();
  
  int k();
  
  b h(int paramInt) throws IllegalArgumentException;
  
  b x();
  
  b u();
  
  int f();
  
  b g(int paramInt) throws IllegalArgumentException;
  
  b h();
  
  b p() throws InvalidMarkException;
  
  b s();
  
  ByteBuffer l();
  
  b a(ByteBuffer paramByteBuffer) throws BufferOverflowException, IllegalArgumentException, ReadOnlyBufferException;
  
  b a(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) throws BufferOverflowException, IllegalArgumentException, ReadOnlyBufferException;
  
  ByteOrder w();
  
  b a(ByteOrder paramByteOrder);
  
  int a(OutputStream paramOutputStream) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */