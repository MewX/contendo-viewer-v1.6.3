/*     */ package org.apache.commons.io.output;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
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
/*     */ public class WriterOutputStream
/*     */   extends OutputStream
/*     */ {
/*     */   private static final int DEFAULT_BUFFER_SIZE = 1024;
/*     */   private final Writer writer;
/*     */   private final CharsetDecoder decoder;
/*     */   private final boolean writeImmediately;
/*  85 */   private final ByteBuffer decoderIn = ByteBuffer.allocate(128);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final CharBuffer decoderOut;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WriterOutputStream(Writer writer, CharsetDecoder decoder) {
/* 104 */     this(writer, decoder, 1024, false);
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
/*     */   public WriterOutputStream(Writer writer, CharsetDecoder decoder, int bufferSize, boolean writeImmediately) {
/* 122 */     checkIbmJdkWithBrokenUTF16(decoder.charset());
/* 123 */     this.writer = writer;
/* 124 */     this.decoder = decoder;
/* 125 */     this.writeImmediately = writeImmediately;
/* 126 */     this.decoderOut = CharBuffer.allocate(bufferSize);
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
/*     */   public WriterOutputStream(Writer writer, Charset charset, int bufferSize, boolean writeImmediately) {
/* 143 */     this(writer, charset
/* 144 */         .newDecoder()
/* 145 */         .onMalformedInput(CodingErrorAction.REPLACE)
/* 146 */         .onUnmappableCharacter(CodingErrorAction.REPLACE)
/* 147 */         .replaceWith("?"), bufferSize, writeImmediately);
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
/*     */   public WriterOutputStream(Writer writer, Charset charset) {
/* 161 */     this(writer, charset, 1024, false);
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
/*     */   public WriterOutputStream(Writer writer, String charsetName, int bufferSize, boolean writeImmediately) {
/* 178 */     this(writer, Charset.forName(charsetName), bufferSize, writeImmediately);
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
/*     */   public WriterOutputStream(Writer writer, String charsetName) {
/* 190 */     this(writer, charsetName, 1024, false);
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
/*     */   @Deprecated
/*     */   public WriterOutputStream(Writer writer) {
/* 203 */     this(writer, Charset.defaultCharset(), 1024, false);
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
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 216 */     while (len > 0) {
/* 217 */       int c = Math.min(len, this.decoderIn.remaining());
/* 218 */       this.decoderIn.put(b, off, c);
/* 219 */       processInput(false);
/* 220 */       len -= c;
/* 221 */       off += c;
/*     */     } 
/* 223 */     if (this.writeImmediately) {
/* 224 */       flushOutput();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 236 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 247 */     write(new byte[] { (byte)b }, 0, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 258 */     flushOutput();
/* 259 */     this.writer.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 270 */     processInput(true);
/* 271 */     flushOutput();
/* 272 */     this.writer.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processInput(boolean endOfInput) throws IOException {
/*     */     CoderResult coderResult;
/* 283 */     this.decoderIn.flip();
/*     */     
/*     */     while (true) {
/* 286 */       coderResult = this.decoder.decode(this.decoderIn, this.decoderOut, endOfInput);
/* 287 */       if (coderResult.isOverflow())
/* 288 */       { flushOutput(); continue; }  break;
/* 289 */     }  if (coderResult.isUnderflow()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       this.decoderIn.compact();
/*     */       return;
/*     */     } 
/*     */     throw new IOException("Unexpected coder result");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void flushOutput() throws IOException {
/* 307 */     if (this.decoderOut.position() > 0) {
/* 308 */       this.writer.write(this.decoderOut.array(), 0, this.decoderOut.position());
/* 309 */       this.decoderOut.rewind();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkIbmJdkWithBrokenUTF16(Charset charset) {
/* 319 */     if (!"UTF-16".equals(charset.name())) {
/*     */       return;
/*     */     }
/* 322 */     String TEST_STRING_2 = "vés";
/* 323 */     byte[] bytes = "vés".getBytes(charset);
/*     */     
/* 325 */     CharsetDecoder charsetDecoder2 = charset.newDecoder();
/* 326 */     ByteBuffer bb2 = ByteBuffer.allocate(16);
/* 327 */     CharBuffer cb2 = CharBuffer.allocate("vés".length());
/* 328 */     int len = bytes.length;
/* 329 */     for (int i = 0; i < len; i++) {
/* 330 */       bb2.put(bytes[i]);
/* 331 */       bb2.flip();
/*     */       try {
/* 333 */         charsetDecoder2.decode(bb2, cb2, (i == len - 1));
/* 334 */       } catch (IllegalArgumentException e) {
/* 335 */         throw new UnsupportedOperationException("UTF-16 requested when runninng on an IBM JDK with broken UTF-16 support. Please find a JDK that supports UTF-16 if you intend to use UF-16 with WriterOutputStream");
/*     */       } 
/*     */       
/* 338 */       bb2.compact();
/*     */     } 
/* 340 */     cb2.rewind();
/* 341 */     if (!"vés".equals(cb2.toString()))
/* 342 */       throw new UnsupportedOperationException("UTF-16 requested when runninng on an IBM JDK with broken UTF-16 support. Please find a JDK that supports UTF-16 if you intend to use UF-16 with WriterOutputStream"); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/output/WriterOutputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */