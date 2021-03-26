/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReaderInputStream
/*     */   extends InputStream
/*     */ {
/*     */   private static final int DEFAULT_BUFFER_SIZE = 1024;
/*     */   private final Reader reader;
/*     */   private final CharsetEncoder encoder;
/*     */   private final CharBuffer encoderIn;
/*     */   private final ByteBuffer encoderOut;
/*     */   private CoderResult lastCoderResult;
/*     */   private boolean endOfInput;
/*     */   
/*     */   public ReaderInputStream(Reader reader, CharsetEncoder encoder) {
/* 109 */     this(reader, encoder, 1024);
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
/*     */   public ReaderInputStream(Reader reader, CharsetEncoder encoder, int bufferSize) {
/* 121 */     this.reader = reader;
/* 122 */     this.encoder = encoder;
/* 123 */     this.encoderIn = CharBuffer.allocate(bufferSize);
/* 124 */     this.encoderIn.flip();
/* 125 */     this.encoderOut = ByteBuffer.allocate(128);
/* 126 */     this.encoderOut.flip();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReaderInputStream(Reader reader, Charset charset, int bufferSize) {
/* 137 */     this(reader, charset
/* 138 */         .newEncoder()
/* 139 */         .onMalformedInput(CodingErrorAction.REPLACE)
/* 140 */         .onUnmappableCharacter(CodingErrorAction.REPLACE), bufferSize);
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
/*     */   public ReaderInputStream(Reader reader, Charset charset) {
/* 152 */     this(reader, charset, 1024);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReaderInputStream(Reader reader, String charsetName, int bufferSize) {
/* 163 */     this(reader, Charset.forName(charsetName), bufferSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReaderInputStream(Reader reader, String charsetName) {
/* 174 */     this(reader, charsetName, 1024);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public ReaderInputStream(Reader reader) {
/* 186 */     this(reader, Charset.defaultCharset());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void fillBuffer() throws IOException {
/* 196 */     if (!this.endOfInput && (this.lastCoderResult == null || this.lastCoderResult.isUnderflow())) {
/* 197 */       this.encoderIn.compact();
/* 198 */       int position = this.encoderIn.position();
/*     */ 
/*     */ 
/*     */       
/* 202 */       int c = this.reader.read(this.encoderIn.array(), position, this.encoderIn.remaining());
/* 203 */       if (c == -1) {
/* 204 */         this.endOfInput = true;
/*     */       } else {
/* 206 */         this.encoderIn.position(position + c);
/*     */       } 
/* 208 */       this.encoderIn.flip();
/*     */     } 
/* 210 */     this.encoderOut.compact();
/* 211 */     this.lastCoderResult = this.encoder.encode(this.encoderIn, this.encoderOut, this.endOfInput);
/* 212 */     this.encoderOut.flip();
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
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 227 */     if (b == null) {
/* 228 */       throw new NullPointerException("Byte array must not be null");
/*     */     }
/* 230 */     if (len < 0 || off < 0 || off + len > b.length) {
/* 231 */       throw new IndexOutOfBoundsException("Array Size=" + b.length + ", offset=" + off + ", length=" + len);
/*     */     }
/*     */     
/* 234 */     int read = 0;
/* 235 */     if (len == 0) {
/* 236 */       return 0;
/*     */     }
/* 238 */     while (len > 0) {
/* 239 */       if (this.encoderOut.hasRemaining()) {
/* 240 */         int c = Math.min(this.encoderOut.remaining(), len);
/* 241 */         this.encoderOut.get(b, off, c);
/* 242 */         off += c;
/* 243 */         len -= c;
/* 244 */         read += c; continue;
/*     */       } 
/* 246 */       fillBuffer();
/* 247 */       if (this.endOfInput && !this.encoderOut.hasRemaining()) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 252 */     return (read == 0 && this.endOfInput) ? -1 : read;
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
/*     */   public int read(byte[] b) throws IOException {
/* 265 */     return read(b, 0, b.length);
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
/*     */   public int read() throws IOException {
/*     */     while (true) {
/* 278 */       if (this.encoderOut.hasRemaining()) {
/* 279 */         return this.encoderOut.get() & 0xFF;
/*     */       }
/* 281 */       fillBuffer();
/* 282 */       if (this.endOfInput && !this.encoderOut.hasRemaining()) {
/* 283 */         return -1;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 295 */     this.reader.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/ReaderInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */