/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.http.ConnectionClosedException;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.MalformedChunkCodingException;
/*     */ import org.apache.http.MessageConstraintException;
/*     */ import org.apache.http.ParseException;
/*     */ import org.apache.http.TruncatedChunkException;
/*     */ import org.apache.http.config.MessageConstraints;
/*     */ import org.apache.http.impl.io.HttpTransportMetricsImpl;
/*     */ import org.apache.http.message.BufferedHeader;
/*     */ import org.apache.http.nio.reactor.SessionInputBuffer;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.CharArrayBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkDecoder
/*     */   extends AbstractContentDecoder
/*     */ {
/*     */   private static final int READ_CONTENT = 0;
/*     */   private static final int READ_FOOTERS = 1;
/*     */   private static final int COMPLETED = 2;
/*     */   private int state;
/*     */   private boolean endOfChunk;
/*     */   private boolean endOfStream;
/*     */   private CharArrayBuffer lineBuf;
/*     */   private long chunkSize;
/*     */   private long pos;
/*     */   private final MessageConstraints constraints;
/*     */   private final List<CharArrayBuffer> trailerBufs;
/*     */   private Header[] footers;
/*     */   
/*     */   public ChunkDecoder(ReadableByteChannel channel, SessionInputBuffer buffer, MessageConstraints constraints, HttpTransportMetricsImpl metrics) {
/*  82 */     super(channel, buffer, metrics);
/*  83 */     this.state = 0;
/*  84 */     this.chunkSize = -1L;
/*  85 */     this.pos = 0L;
/*  86 */     this.endOfChunk = false;
/*  87 */     this.endOfStream = false;
/*  88 */     this.constraints = (constraints != null) ? constraints : MessageConstraints.DEFAULT;
/*  89 */     this.trailerBufs = new ArrayList<CharArrayBuffer>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkDecoder(ReadableByteChannel channel, SessionInputBuffer buffer, HttpTransportMetricsImpl metrics) {
/*  96 */     this(channel, buffer, (MessageConstraints)null, metrics);
/*     */   }
/*     */   
/*     */   private void readChunkHead() throws IOException {
/* 100 */     if (this.lineBuf == null) {
/* 101 */       this.lineBuf = new CharArrayBuffer(32);
/*     */     } else {
/* 103 */       this.lineBuf.clear();
/*     */     } 
/* 105 */     if (this.endOfChunk) {
/* 106 */       if (this.buffer.readLine(this.lineBuf, this.endOfStream)) {
/* 107 */         if (!this.lineBuf.isEmpty()) {
/* 108 */           throw new MalformedChunkCodingException("CRLF expected at end of chunk");
/*     */         }
/*     */       } else {
/* 111 */         if (this.buffer.length() > 2 || this.endOfStream) {
/* 112 */           throw new MalformedChunkCodingException("CRLF expected at end of chunk");
/*     */         }
/*     */         return;
/*     */       } 
/* 116 */       this.endOfChunk = false;
/*     */     } 
/* 118 */     boolean lineComplete = this.buffer.readLine(this.lineBuf, this.endOfStream);
/* 119 */     int maxLineLen = this.constraints.getMaxLineLength();
/* 120 */     if (maxLineLen > 0 && (this.lineBuf.length() > maxLineLen || (!lineComplete && this.buffer.length() > maxLineLen)))
/*     */     {
/*     */       
/* 123 */       throw new MessageConstraintException("Maximum line length limit exceeded");
/*     */     }
/* 125 */     if (lineComplete) {
/* 126 */       int separator = this.lineBuf.indexOf(59);
/* 127 */       if (separator < 0) {
/* 128 */         separator = this.lineBuf.length();
/*     */       }
/* 130 */       String s = this.lineBuf.substringTrimmed(0, separator);
/*     */       try {
/* 132 */         this.chunkSize = Long.parseLong(s, 16);
/* 133 */       } catch (NumberFormatException e) {
/* 134 */         throw new MalformedChunkCodingException("Bad chunk header: " + s);
/*     */       } 
/* 136 */       this.pos = 0L;
/* 137 */     } else if (this.endOfStream) {
/* 138 */       throw new ConnectionClosedException("Premature end of chunk coded message body: closing chunk expected");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseHeader() throws IOException {
/* 144 */     CharArrayBuffer current = this.lineBuf;
/* 145 */     int count = this.trailerBufs.size();
/* 146 */     if ((this.lineBuf.charAt(0) == ' ' || this.lineBuf.charAt(0) == '\t') && count > 0) {
/*     */       
/* 148 */       CharArrayBuffer previous = this.trailerBufs.get(count - 1);
/* 149 */       int i = 0;
/* 150 */       while (i < current.length()) {
/* 151 */         char ch = current.charAt(i);
/* 152 */         if (ch != ' ' && ch != '\t') {
/*     */           break;
/*     */         }
/* 155 */         i++;
/*     */       } 
/* 157 */       int maxLineLen = this.constraints.getMaxLineLength();
/* 158 */       if (maxLineLen > 0 && previous.length() + 1 + current.length() - i > maxLineLen) {
/* 159 */         throw new MessageConstraintException("Maximum line length limit exceeded");
/*     */       }
/* 161 */       previous.append(' ');
/* 162 */       previous.append(current, i, current.length() - i);
/*     */     } else {
/* 164 */       this.trailerBufs.add(current);
/* 165 */       this.lineBuf = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processFooters() throws IOException {
/* 170 */     int count = this.trailerBufs.size();
/* 171 */     if (count > 0) {
/* 172 */       this.footers = new Header[this.trailerBufs.size()];
/* 173 */       for (int i = 0; i < this.trailerBufs.size(); i++) {
/*     */         try {
/* 175 */           this.footers[i] = (Header)new BufferedHeader(this.trailerBufs.get(i));
/* 176 */         } catch (ParseException ex) {
/* 177 */           throw new IOException(ex);
/*     */         } 
/*     */       } 
/*     */     } 
/* 181 */     this.trailerBufs.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(ByteBuffer dst) throws IOException {
/* 186 */     Args.notNull(dst, "Byte buffer");
/* 187 */     if (this.state == 2) {
/* 188 */       return -1;
/*     */     }
/*     */     
/* 191 */     int totalRead = 0;
/* 192 */     while (this.state != 2) {
/*     */       long maxLen; int len;
/* 194 */       if (!this.buffer.hasData() || this.chunkSize == -1L) {
/* 195 */         int bytesRead = fillBufferFromChannel();
/* 196 */         if (bytesRead == -1) {
/* 197 */           this.endOfStream = true;
/*     */         }
/*     */       } 
/*     */       
/* 201 */       switch (this.state) {
/*     */         
/*     */         case 0:
/* 204 */           if (this.chunkSize == -1L) {
/* 205 */             readChunkHead();
/* 206 */             if (this.chunkSize == -1L)
/*     */             {
/* 208 */               return totalRead;
/*     */             }
/* 210 */             if (this.chunkSize == 0L) {
/*     */               
/* 212 */               this.chunkSize = -1L;
/* 213 */               this.state = 1;
/*     */               continue;
/*     */             } 
/*     */           } 
/* 217 */           maxLen = this.chunkSize - this.pos;
/* 218 */           len = this.buffer.read(dst, (int)Math.min(maxLen, 2147483647L));
/* 219 */           if (len > 0) {
/* 220 */             this.pos += len;
/* 221 */             totalRead += len;
/*     */           }
/* 223 */           else if (!this.buffer.hasData() && this.endOfStream) {
/* 224 */             this.state = 2;
/* 225 */             setCompleted();
/* 226 */             throw new TruncatedChunkException("Truncated chunk (expected size: %,d; actual size: %,d)", new Object[] { Long.valueOf(this.chunkSize), Long.valueOf(this.pos) });
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 232 */           if (this.pos == this.chunkSize) {
/*     */             
/* 234 */             this.chunkSize = -1L;
/* 235 */             this.pos = 0L;
/* 236 */             this.endOfChunk = true;
/*     */             continue;
/*     */           } 
/* 239 */           return totalRead;
/*     */         case 1:
/* 241 */           if (this.lineBuf == null) {
/* 242 */             this.lineBuf = new CharArrayBuffer(32);
/*     */           } else {
/* 244 */             this.lineBuf.clear();
/*     */           } 
/* 246 */           if (!this.buffer.readLine(this.lineBuf, this.endOfStream)) {
/*     */             
/* 248 */             if (this.endOfStream) {
/* 249 */               this.state = 2;
/* 250 */               setCompleted();
/*     */             } 
/* 252 */             return totalRead;
/*     */           } 
/* 254 */           if (this.lineBuf.length() > 0) {
/* 255 */             int maxHeaderCount = this.constraints.getMaxHeaderCount();
/* 256 */             if (maxHeaderCount > 0 && this.trailerBufs.size() >= maxHeaderCount) {
/* 257 */               throw new MessageConstraintException("Maximum header count exceeded");
/*     */             }
/* 259 */             parseHeader(); continue;
/*     */           } 
/* 261 */           this.state = 2;
/* 262 */           setCompleted();
/* 263 */           processFooters();
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 269 */     return totalRead;
/*     */   }
/*     */   
/*     */   public Header[] getFooters() {
/* 273 */     return (this.footers != null) ? (Header[])this.footers.clone() : new Header[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 278 */     StringBuilder sb = new StringBuilder();
/* 279 */     sb.append("[chunk-coded; completed: ");
/* 280 */     sb.append(this.completed);
/* 281 */     sb.append("]");
/* 282 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/ChunkDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */