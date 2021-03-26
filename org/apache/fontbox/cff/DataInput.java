/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import org.apache.fontbox.util.Charsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataInput
/*     */ {
/*  32 */   private byte[] inputBuffer = null;
/*  33 */   private int bufferPosition = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataInput(byte[] buffer) {
/*  41 */     this.inputBuffer = buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRemaining() {
/*  50 */     return (this.bufferPosition < this.inputBuffer.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/*  59 */     return this.bufferPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(int position) {
/*  68 */     this.bufferPosition = position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString() throws IOException {
/*  78 */     return new String(this.inputBuffer, Charsets.ISO_8859_1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte readByte() throws IOException {
/*     */     try {
/*  90 */       byte value = this.inputBuffer[this.bufferPosition];
/*  91 */       this.bufferPosition++;
/*  92 */       return value;
/*     */     }
/*  94 */     catch (RuntimeException re) {
/*     */       
/*  96 */       return -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readUnsignedByte() throws IOException {
/* 107 */     int b = read();
/* 108 */     if (b < 0)
/*     */     {
/* 110 */       throw new EOFException();
/*     */     }
/* 112 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int peekUnsignedByte(int offset) throws IOException {
/* 122 */     int b = peek(offset);
/* 123 */     if (b < 0)
/*     */     {
/* 125 */       throw new EOFException();
/*     */     }
/* 127 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short readShort() throws IOException {
/* 137 */     return (short)readUnsignedShort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readUnsignedShort() throws IOException {
/* 147 */     int b1 = read();
/* 148 */     int b2 = read();
/* 149 */     if ((b1 | b2) < 0)
/*     */     {
/* 151 */       throw new EOFException();
/*     */     }
/* 153 */     return b1 << 8 | b2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readInt() throws IOException {
/* 163 */     int b1 = read();
/* 164 */     int b2 = read();
/* 165 */     int b3 = read();
/* 166 */     int b4 = read();
/* 167 */     if ((b1 | b2 | b3 | b4) < 0)
/*     */     {
/* 169 */       throw new EOFException();
/*     */     }
/* 171 */     return b1 << 24 | b2 << 16 | b3 << 8 | b4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] readBytes(int length) throws IOException {
/* 182 */     if (this.inputBuffer.length - this.bufferPosition < length)
/*     */     {
/* 184 */       throw new EOFException();
/*     */     }
/* 186 */     byte[] bytes = new byte[length];
/* 187 */     System.arraycopy(this.inputBuffer, this.bufferPosition, bytes, 0, length);
/* 188 */     this.bufferPosition += length;
/* 189 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int read() {
/*     */     try {
/* 196 */       int value = this.inputBuffer[this.bufferPosition] & 0xFF;
/* 197 */       this.bufferPosition++;
/* 198 */       return value;
/*     */     }
/* 200 */     catch (RuntimeException re) {
/*     */       
/* 202 */       return -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int peek(int offset) {
/*     */     try {
/* 210 */       int value = this.inputBuffer[this.bufferPosition + offset] & 0xFF;
/* 211 */       return value;
/*     */     }
/* 213 */     catch (RuntimeException re) {
/*     */       
/* 215 */       return -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int length() {
/* 221 */     return this.inputBuffer.length;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/DataInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */