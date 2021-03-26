/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import org.apache.http.MessageConstraintException;
/*     */ import org.apache.http.config.MessageConstraints;
/*     */ import org.apache.http.nio.reactor.SessionInputBuffer;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.ExpandableBuffer;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.CharArrayBuffer;
/*     */ import org.apache.http.util.CharsetUtils;
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
/*     */ public class SessionInputBufferImpl
/*     */   extends ExpandableBuffer
/*     */   implements SessionInputBuffer
/*     */ {
/*     */   private final CharsetDecoder charDecoder;
/*     */   private final MessageConstraints constraints;
/*     */   private final int lineBufferSize;
/*     */   private CharBuffer charBuffer;
/*     */   
/*     */   public SessionInputBufferImpl(int bufferSize, int lineBufferSize, MessageConstraints constraints, CharsetDecoder charDecoder, ByteBufferAllocator allocator) {
/*  90 */     super(bufferSize, (allocator != null) ? allocator : (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
/*  91 */     this.lineBufferSize = Args.positive(lineBufferSize, "Line buffer size");
/*  92 */     this.constraints = (constraints != null) ? constraints : MessageConstraints.DEFAULT;
/*  93 */     this.charDecoder = charDecoder;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public SessionInputBufferImpl(int bufferSize, int lineBufferSize, CharsetDecoder charDecoder, ByteBufferAllocator allocator) {
/* 114 */     this(bufferSize, lineBufferSize, (MessageConstraints)null, charDecoder, allocator);
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
/*     */   @Deprecated
/*     */   public SessionInputBufferImpl(int bufferSize, int lineBufferSize, ByteBufferAllocator allocator, HttpParams params) {
/* 128 */     super(bufferSize, allocator);
/* 129 */     this.lineBufferSize = Args.positive(lineBufferSize, "Line buffer size");
/* 130 */     String charsetName = (String)params.getParameter("http.protocol.element-charset");
/* 131 */     Charset charset = CharsetUtils.lookup(charsetName);
/* 132 */     if (charset != null) {
/* 133 */       this.charDecoder = charset.newDecoder();
/* 134 */       CodingErrorAction a1 = (CodingErrorAction)params.getParameter("http.malformed.input.action");
/*     */       
/* 136 */       this.charDecoder.onMalformedInput((a1 != null) ? a1 : CodingErrorAction.REPORT);
/* 137 */       CodingErrorAction a2 = (CodingErrorAction)params.getParameter("http.unmappable.input.action");
/*     */       
/* 139 */       this.charDecoder.onUnmappableCharacter((a2 != null) ? a2 : CodingErrorAction.REPORT);
/*     */     } else {
/* 141 */       this.charDecoder = null;
/*     */     } 
/* 143 */     this.constraints = MessageConstraints.DEFAULT;
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
/*     */   public SessionInputBufferImpl(int bufferSize, int lineBufferSize, HttpParams params) {
/* 155 */     this(bufferSize, lineBufferSize, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
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
/*     */   public SessionInputBufferImpl(int bufferSize, int lineBufferSize, Charset charset) {
/* 173 */     this(bufferSize, lineBufferSize, (MessageConstraints)null, (charset != null) ? charset.newDecoder() : null, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SessionInputBufferImpl(int bufferSize, int lineBufferSize, MessageConstraints constraints, Charset charset) {
/* 195 */     this(bufferSize, lineBufferSize, constraints, (charset != null) ? charset.newDecoder() : null, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
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
/*     */   public SessionInputBufferImpl(int bufferSize, int lineBufferSize) {
/* 210 */     this(bufferSize, lineBufferSize, (MessageConstraints)null, (CharsetDecoder)null, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SessionInputBufferImpl(int bufferSize) {
/* 221 */     this(bufferSize, 256, (MessageConstraints)null, (CharsetDecoder)null, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public int fill(ReadableByteChannel channel) throws IOException {
/* 226 */     Args.notNull(channel, "Channel");
/* 227 */     setInputMode();
/* 228 */     if (!this.buffer.hasRemaining()) {
/* 229 */       expand();
/*     */     }
/* 231 */     return channel.read(this.buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() {
/* 236 */     setOutputMode();
/* 237 */     return this.buffer.get() & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(ByteBuffer dst, int maxLen) {
/* 242 */     if (dst == null) {
/* 243 */       return 0;
/*     */     }
/* 245 */     setOutputMode();
/* 246 */     int len = Math.min(dst.remaining(), maxLen);
/* 247 */     int chunk = Math.min(this.buffer.remaining(), len);
/* 248 */     if (this.buffer.remaining() > chunk) {
/* 249 */       int oldLimit = this.buffer.limit();
/* 250 */       int newLimit = this.buffer.position() + chunk;
/* 251 */       this.buffer.limit(newLimit);
/* 252 */       dst.put(this.buffer);
/* 253 */       this.buffer.limit(oldLimit);
/* 254 */       return len;
/*     */     } 
/* 256 */     dst.put(this.buffer);
/* 257 */     return chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(ByteBuffer dst) {
/* 262 */     if (dst == null) {
/* 263 */       return 0;
/*     */     }
/* 265 */     return read(dst, dst.remaining());
/*     */   }
/*     */   
/*     */   public int read(WritableByteChannel dst, int maxLen) throws IOException {
/*     */     int bytesRead;
/* 270 */     if (dst == null) {
/* 271 */       return 0;
/*     */     }
/* 273 */     setOutputMode();
/*     */     
/* 275 */     if (this.buffer.remaining() > maxLen) {
/* 276 */       int oldLimit = this.buffer.limit();
/* 277 */       int newLimit = oldLimit - this.buffer.remaining() - maxLen;
/* 278 */       this.buffer.limit(newLimit);
/* 279 */       bytesRead = dst.write(this.buffer);
/* 280 */       this.buffer.limit(oldLimit);
/*     */     } else {
/* 282 */       bytesRead = dst.write(this.buffer);
/*     */     } 
/* 284 */     return bytesRead;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(WritableByteChannel dst) throws IOException {
/* 289 */     if (dst == null) {
/* 290 */       return 0;
/*     */     }
/* 292 */     setOutputMode();
/* 293 */     return dst.write(this.buffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean readLine(CharArrayBuffer lineBuffer, boolean endOfStream) throws CharacterCodingException {
/* 301 */     setOutputMode();
/*     */     
/* 303 */     int pos = -1;
/* 304 */     for (int i = this.buffer.position(); i < this.buffer.limit(); i++) {
/* 305 */       int b = this.buffer.get(i);
/* 306 */       if (b == 10) {
/* 307 */         pos = i + 1;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 312 */     int maxLineLen = this.constraints.getMaxLineLength();
/* 313 */     if (maxLineLen > 0) {
/* 314 */       int currentLen = ((pos > 0) ? pos : this.buffer.limit()) - this.buffer.position();
/* 315 */       if (currentLen >= maxLineLen) {
/* 316 */         throw new MessageConstraintException("Maximum line length limit exceeded");
/*     */       }
/*     */     } 
/*     */     
/* 320 */     if (pos == -1) {
/* 321 */       if (endOfStream && this.buffer.hasRemaining()) {
/*     */         
/* 323 */         pos = this.buffer.limit();
/*     */       }
/*     */       else {
/*     */         
/* 327 */         return false;
/*     */       } 
/*     */     }
/* 330 */     int origLimit = this.buffer.limit();
/* 331 */     this.buffer.limit(pos);
/*     */     
/* 333 */     int requiredCapacity = this.buffer.limit() - this.buffer.position();
/*     */     
/* 335 */     lineBuffer.ensureCapacity(requiredCapacity);
/*     */     
/* 337 */     if (this.charDecoder == null) {
/* 338 */       if (this.buffer.hasArray()) {
/* 339 */         byte[] b = this.buffer.array();
/* 340 */         int off = this.buffer.position();
/* 341 */         int j = this.buffer.remaining();
/* 342 */         lineBuffer.append(b, off, j);
/* 343 */         this.buffer.position(off + j);
/*     */       } else {
/* 345 */         while (this.buffer.hasRemaining())
/* 346 */           lineBuffer.append((char)(this.buffer.get() & 0xFF)); 
/*     */       } 
/*     */     } else {
/*     */       CoderResult result;
/* 350 */       if (this.charBuffer == null) {
/* 351 */         this.charBuffer = CharBuffer.allocate(this.lineBufferSize);
/*     */       }
/* 353 */       this.charDecoder.reset();
/*     */       
/*     */       do {
/* 356 */         result = this.charDecoder.decode(this.buffer, this.charBuffer, true);
/*     */ 
/*     */ 
/*     */         
/* 360 */         if (result.isError()) {
/* 361 */           result.throwException();
/*     */         }
/* 363 */         if (!result.isOverflow())
/* 364 */           continue;  this.charBuffer.flip();
/* 365 */         lineBuffer.append(this.charBuffer.array(), this.charBuffer.position(), this.charBuffer.remaining());
/*     */ 
/*     */ 
/*     */         
/* 369 */         this.charBuffer.clear();
/*     */       }
/* 371 */       while (!result.isUnderflow());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 377 */       this.charDecoder.flush(this.charBuffer);
/* 378 */       this.charBuffer.flip();
/*     */       
/* 380 */       if (this.charBuffer.hasRemaining()) {
/* 381 */         lineBuffer.append(this.charBuffer.array(), this.charBuffer.position(), this.charBuffer.remaining());
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     this.buffer.limit(origLimit);
/*     */ 
/*     */     
/* 391 */     int len = lineBuffer.length();
/* 392 */     if (len > 0) {
/* 393 */       if (lineBuffer.charAt(len - 1) == '\n') {
/* 394 */         len--;
/* 395 */         lineBuffer.setLength(len);
/*     */       } 
/*     */       
/* 398 */       if (len > 0 && 
/* 399 */         lineBuffer.charAt(len - 1) == '\r') {
/* 400 */         len--;
/* 401 */         lineBuffer.setLength(len);
/*     */       } 
/*     */     } 
/*     */     
/* 405 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String readLine(boolean endOfStream) throws CharacterCodingException {
/* 410 */     CharArrayBuffer tmpBuffer = new CharArrayBuffer(64);
/* 411 */     boolean found = readLine(tmpBuffer, endOfStream);
/* 412 */     return found ? tmpBuffer.toString() : null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/SessionInputBufferImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */