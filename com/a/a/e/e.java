package com.a.a.e;

import java.nio.BufferUnderflowException;
import java.nio.ReadOnlyBufferException;

public interface e<T extends e<?>> {
  byte b(int paramInt) throws IndexOutOfBoundsException;
  
  char c(int paramInt) throws IndexOutOfBoundsException;
  
  short d(int paramInt) throws IndexOutOfBoundsException;
  
  int e(int paramInt) throws IndexOutOfBoundsException;
  
  long f(int paramInt) throws IndexOutOfBoundsException;
  
  byte i() throws BufferUnderflowException;
  
  char c() throws BufferUnderflowException;
  
  short t() throws BufferUnderflowException;
  
  int n() throws BufferUnderflowException;
  
  long d() throws BufferUnderflowException;
  
  T c(int paramInt, byte[] paramArrayOfbyte) throws IndexOutOfBoundsException;
  
  T c(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) throws IndexOutOfBoundsException;
  
  T f(byte[] paramArrayOfbyte) throws BufferUnderflowException;
  
  T c(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws BufferUnderflowException;
  
  T b(char[] paramArrayOfchar) throws BufferUnderflowException;
  
  T b(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws BufferUnderflowException;
  
  T b(short[] paramArrayOfshort) throws BufferUnderflowException;
  
  T b(short[] paramArrayOfshort, int paramInt1, int paramInt2) throws BufferUnderflowException;
  
  T b(int[] paramArrayOfint) throws BufferUnderflowException;
  
  T b(int[] paramArrayOfint, int paramInt1, int paramInt2) throws BufferUnderflowException;
  
  T b(int paramInt, byte paramByte) throws IndexOutOfBoundsException, ReadOnlyBufferException;
  
  T b(int paramInt, char paramChar) throws IndexOutOfBoundsException, ReadOnlyBufferException;
  
  T b(int paramInt, short paramShort) throws IndexOutOfBoundsException, ReadOnlyBufferException;
  
  T c(int paramInt1, int paramInt2) throws IndexOutOfBoundsException, ReadOnlyBufferException;
  
  T b(int paramInt, long paramLong) throws IndexOutOfBoundsException, ReadOnlyBufferException;
  
  T b(byte paramByte) throws BufferUnderflowException, ReadOnlyBufferException;
  
  T b(char paramChar) throws BufferUnderflowException, ReadOnlyBufferException;
  
  T b(short paramShort) throws BufferUnderflowException, ReadOnlyBufferException;
  
  T n(int paramInt) throws BufferUnderflowException, ReadOnlyBufferException;
  
  T b(long paramLong) throws BufferUnderflowException, ReadOnlyBufferException;
  
  T d(int paramInt, byte[] paramArrayOfbyte) throws IndexOutOfBoundsException, ReadOnlyBufferException;
  
  T d(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) throws IndexOutOfBoundsException, ReadOnlyBufferException;
  
  T g(byte[] paramArrayOfbyte) throws BufferUnderflowException, ReadOnlyBufferException;
  
  T d(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws BufferUnderflowException, ReadOnlyBufferException, IndexOutOfBoundsException;
  
  T b(e<?> parame) throws ReadOnlyBufferException;
  
  T b(e<?> parame, int paramInt1, int paramInt2) throws ReadOnlyBufferException, IndexOutOfBoundsException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */