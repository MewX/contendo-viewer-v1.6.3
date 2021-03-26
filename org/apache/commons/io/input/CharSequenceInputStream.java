/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ import java.nio.charset.CodingErrorAction;
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
/*     */ public class CharSequenceInputStream
/*     */   extends InputStream
/*     */ {
/*     */   private static final int BUFFER_SIZE = 2048;
/*     */   private static final int NO_MARK = -1;
/*     */   private final CharsetEncoder encoder;
/*     */   private final CharBuffer cbuf;
/*     */   private final ByteBuffer bbuf;
/*     */   private int mark_cbuf;
/*     */   private int mark_bbuf;
/*     */   
/*     */   public CharSequenceInputStream(CharSequence cs, Charset charset, int bufferSize) {
/*  63 */     this
/*     */       
/*  65 */       .encoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
/*     */     
/*  67 */     float maxBytesPerChar = this.encoder.maxBytesPerChar();
/*  68 */     if (bufferSize < maxBytesPerChar) {
/*  69 */       throw new IllegalArgumentException("Buffer size " + bufferSize + " is less than maxBytesPerChar " + maxBytesPerChar);
/*     */     }
/*     */     
/*  72 */     this.bbuf = ByteBuffer.allocate(bufferSize);
/*  73 */     this.bbuf.flip();
/*  74 */     this.cbuf = CharBuffer.wrap(cs);
/*  75 */     this.mark_cbuf = -1;
/*  76 */     this.mark_bbuf = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharSequenceInputStream(CharSequence cs, String charset, int bufferSize) {
/*  88 */     this(cs, Charset.forName(charset), bufferSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharSequenceInputStream(CharSequence cs, Charset charset) {
/* 100 */     this(cs, charset, 2048);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharSequenceInputStream(CharSequence cs, String charset) {
/* 112 */     this(cs, charset, 2048);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void fillBuffer() throws CharacterCodingException {
/* 122 */     this.bbuf.compact();
/* 123 */     CoderResult result = this.encoder.encode(this.cbuf, this.bbuf, true);
/* 124 */     if (result.isError()) {
/* 125 */       result.throwException();
/*     */     }
/* 127 */     this.bbuf.flip();
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 132 */     if (b == null) {
/* 133 */       throw new NullPointerException("Byte array is null");
/*     */     }
/* 135 */     if (len < 0 || off + len > b.length) {
/* 136 */       throw new IndexOutOfBoundsException("Array Size=" + b.length + ", offset=" + off + ", length=" + len);
/*     */     }
/*     */     
/* 139 */     if (len == 0) {
/* 140 */       return 0;
/*     */     }
/* 142 */     if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining()) {
/* 143 */       return -1;
/*     */     }
/* 145 */     int bytesRead = 0;
/* 146 */     while (len > 0) {
/* 147 */       if (this.bbuf.hasRemaining()) {
/* 148 */         int chunk = Math.min(this.bbuf.remaining(), len);
/* 149 */         this.bbuf.get(b, off, chunk);
/* 150 */         off += chunk;
/* 151 */         len -= chunk;
/* 152 */         bytesRead += chunk; continue;
/*     */       } 
/* 154 */       fillBuffer();
/* 155 */       if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining()) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 160 */     return (bytesRead == 0 && !this.cbuf.hasRemaining()) ? -1 : bytesRead;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*     */     while (true) {
/* 166 */       if (this.bbuf.hasRemaining()) {
/* 167 */         return this.bbuf.get() & 0xFF;
/*     */       }
/* 169 */       fillBuffer();
/* 170 */       if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining()) {
/* 171 */         return -1;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 178 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 186 */     long skipped = 0L;
/* 187 */     while (n > 0L && available() > 0) {
/* 188 */       read();
/* 189 */       n--;
/* 190 */       skipped++;
/*     */     } 
/* 192 */     return skipped;
/*     */   }
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
/*     */   public int available() throws IOException {
/* 207 */     return this.bbuf.remaining() + this.cbuf.remaining();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/* 220 */     this.mark_cbuf = this.cbuf.position();
/* 221 */     this.mark_bbuf = this.bbuf.position();
/* 222 */     this.cbuf.mark();
/* 223 */     this.bbuf.mark();
/*     */   }
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
/*     */   public synchronized void reset() throws IOException {
/* 241 */     if (this.mark_cbuf != -1) {
/*     */       
/* 243 */       if (this.cbuf.position() != 0) {
/* 244 */         this.encoder.reset();
/* 245 */         this.cbuf.rewind();
/* 246 */         this.bbuf.rewind();
/* 247 */         this.bbuf.limit(0);
/* 248 */         while (this.cbuf.position() < this.mark_cbuf) {
/* 249 */           this.bbuf.rewind();
/* 250 */           this.bbuf.limit(0);
/* 251 */           fillBuffer();
/*     */         } 
/*     */       } 
/* 254 */       if (this.cbuf.position() != this.mark_cbuf) {
/* 255 */         throw new IllegalStateException("Unexpected CharBuffer postion: actual=" + this.cbuf.position() + " expected=" + this.mark_cbuf);
/*     */       }
/*     */       
/* 258 */       this.bbuf.position(this.mark_bbuf);
/* 259 */       this.mark_cbuf = -1;
/* 260 */       this.mark_bbuf = -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 266 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/CharSequenceInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */