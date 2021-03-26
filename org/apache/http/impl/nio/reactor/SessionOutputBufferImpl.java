/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import org.apache.http.nio.reactor.SessionOutputBuffer;
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
/*     */ public class SessionOutputBufferImpl
/*     */   extends ExpandableBuffer
/*     */   implements SessionOutputBuffer
/*     */ {
/*  61 */   private static final byte[] CRLF = new byte[] { 13, 10 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final CharsetEncoder charEncoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int lineBufferSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CharBuffer charBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SessionOutputBufferImpl(int bufferSize, int lineBufferSize, CharsetEncoder charEncoder, ByteBufferAllocator allocator) {
/*  86 */     super(bufferSize, (allocator != null) ? allocator : (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
/*  87 */     this.lineBufferSize = Args.positive(lineBufferSize, "Line buffer size");
/*  88 */     this.charEncoder = charEncoder;
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
/*     */   public SessionOutputBufferImpl(int bufferSize, int lineBufferSize, ByteBufferAllocator allocator, HttpParams params) {
/* 102 */     super(bufferSize, allocator);
/* 103 */     this.lineBufferSize = Args.positive(lineBufferSize, "Line buffer size");
/* 104 */     String charsetName = (String)params.getParameter("http.protocol.element-charset");
/* 105 */     Charset charset = CharsetUtils.lookup(charsetName);
/* 106 */     if (charset != null) {
/* 107 */       this.charEncoder = charset.newEncoder();
/* 108 */       CodingErrorAction a1 = (CodingErrorAction)params.getParameter("http.malformed.input.action");
/*     */       
/* 110 */       this.charEncoder.onMalformedInput((a1 != null) ? a1 : CodingErrorAction.REPORT);
/* 111 */       CodingErrorAction a2 = (CodingErrorAction)params.getParameter("http.unmappable.input.action");
/*     */       
/* 113 */       this.charEncoder.onUnmappableCharacter((a2 != null) ? a2 : CodingErrorAction.REPORT);
/*     */     } else {
/* 115 */       this.charEncoder = null;
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
/*     */   @Deprecated
/*     */   public SessionOutputBufferImpl(int bufferSize, int lineBufferSize, HttpParams params) {
/* 128 */     this(bufferSize, lineBufferSize, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SessionOutputBufferImpl(int bufferSize) {
/* 138 */     this(bufferSize, 256, (CharsetEncoder)null, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
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
/*     */   public SessionOutputBufferImpl(int bufferSize, int lineBufferSize, Charset charset) {
/* 156 */     this(bufferSize, lineBufferSize, (charset != null) ? charset.newEncoder() : null, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
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
/*     */   public SessionOutputBufferImpl(int bufferSize, int lineBufferSize) {
/* 171 */     this(bufferSize, lineBufferSize, (CharsetEncoder)null, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
/*     */   }
/*     */   
/*     */   public void reset(HttpParams params) {
/* 175 */     clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public int flush(WritableByteChannel channel) throws IOException {
/* 180 */     Args.notNull(channel, "Channel");
/* 181 */     setOutputMode();
/* 182 */     return channel.write(this.buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(ByteBuffer src) {
/* 187 */     if (src == null) {
/*     */       return;
/*     */     }
/* 190 */     setInputMode();
/* 191 */     int requiredCapacity = this.buffer.position() + src.remaining();
/* 192 */     ensureCapacity(requiredCapacity);
/* 193 */     this.buffer.put(src);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(ReadableByteChannel src) throws IOException {
/* 198 */     if (src == null) {
/*     */       return;
/*     */     }
/* 201 */     setInputMode();
/* 202 */     src.read(this.buffer);
/*     */   }
/*     */   
/*     */   private void write(byte[] b) {
/* 206 */     if (b == null) {
/*     */       return;
/*     */     }
/* 209 */     setInputMode();
/* 210 */     int off = 0;
/* 211 */     int len = b.length;
/* 212 */     int requiredCapacity = this.buffer.position() + len;
/* 213 */     ensureCapacity(requiredCapacity);
/* 214 */     this.buffer.put(b, 0, len);
/*     */   }
/*     */   
/*     */   private void writeCRLF() {
/* 218 */     write(CRLF);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeLine(CharArrayBuffer lineBuffer) throws CharacterCodingException {
/* 223 */     if (lineBuffer == null) {
/*     */       return;
/*     */     }
/* 226 */     setInputMode();
/*     */     
/* 228 */     if (lineBuffer.length() > 0) {
/* 229 */       if (this.charEncoder == null) {
/* 230 */         int requiredCapacity = this.buffer.position() + lineBuffer.length();
/* 231 */         ensureCapacity(requiredCapacity);
/* 232 */         if (this.buffer.hasArray()) {
/* 233 */           byte[] b = this.buffer.array();
/* 234 */           int len = lineBuffer.length();
/* 235 */           int off = this.buffer.position();
/* 236 */           for (int i = 0; i < len; i++) {
/* 237 */             b[off + i] = (byte)lineBuffer.charAt(i);
/*     */           }
/* 239 */           this.buffer.position(off + len);
/*     */         } else {
/* 241 */           for (int i = 0; i < lineBuffer.length(); i++) {
/* 242 */             this.buffer.put((byte)lineBuffer.charAt(i));
/*     */           }
/*     */         } 
/*     */       } else {
/* 246 */         if (this.charBuffer == null) {
/* 247 */           this.charBuffer = CharBuffer.allocate(this.lineBufferSize);
/*     */         }
/* 249 */         this.charEncoder.reset();
/*     */         
/* 251 */         int remaining = lineBuffer.length();
/* 252 */         int offset = 0;
/* 253 */         while (remaining > 0) {
/* 254 */           int l = this.charBuffer.remaining();
/* 255 */           boolean eol = false;
/* 256 */           if (remaining <= l) {
/* 257 */             l = remaining;
/*     */             
/* 259 */             eol = true;
/*     */           } 
/* 261 */           this.charBuffer.put(lineBuffer.buffer(), offset, l);
/* 262 */           this.charBuffer.flip();
/*     */           
/* 264 */           boolean bool1 = true;
/* 265 */           while (bool1) {
/* 266 */             CoderResult result = this.charEncoder.encode(this.charBuffer, this.buffer, eol);
/* 267 */             if (result.isError()) {
/* 268 */               result.throwException();
/*     */             }
/* 270 */             if (result.isOverflow()) {
/* 271 */               expand();
/*     */             }
/* 273 */             bool1 = !result.isUnderflow();
/*     */           } 
/* 275 */           this.charBuffer.compact();
/* 276 */           offset += l;
/* 277 */           remaining -= l;
/*     */         } 
/*     */         
/* 280 */         boolean retry = true;
/* 281 */         while (retry) {
/* 282 */           CoderResult result = this.charEncoder.flush(this.buffer);
/* 283 */           if (result.isError()) {
/* 284 */             result.throwException();
/*     */           }
/* 286 */           if (result.isOverflow()) {
/* 287 */             expand();
/*     */           }
/* 289 */           retry = !result.isUnderflow();
/*     */         } 
/*     */       } 
/*     */     }
/* 293 */     writeCRLF();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeLine(String s) throws IOException {
/* 298 */     if (s == null) {
/*     */       return;
/*     */     }
/* 301 */     if (s.length() > 0) {
/* 302 */       CharArrayBuffer tmp = new CharArrayBuffer(s.length());
/* 303 */       tmp.append(s);
/* 304 */       writeLine(tmp);
/*     */     } else {
/* 306 */       write(CRLF);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/SessionOutputBufferImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */