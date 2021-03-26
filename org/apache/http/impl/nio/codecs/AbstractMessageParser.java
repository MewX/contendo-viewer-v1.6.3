/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpMessage;
/*     */ import org.apache.http.MessageConstraintException;
/*     */ import org.apache.http.ParseException;
/*     */ import org.apache.http.ProtocolException;
/*     */ import org.apache.http.config.MessageConstraints;
/*     */ import org.apache.http.message.BasicLineParser;
/*     */ import org.apache.http.message.LineParser;
/*     */ import org.apache.http.nio.NHttpMessageParser;
/*     */ import org.apache.http.nio.reactor.SessionInputBuffer;
/*     */ import org.apache.http.params.HttpParamConfig;
/*     */ import org.apache.http.params.HttpParams;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMessageParser<T extends HttpMessage>
/*     */   implements NHttpMessageParser<T>
/*     */ {
/*     */   private final SessionInputBuffer sessionBuffer;
/*     */   private static final int READ_HEAD_LINE = 0;
/*     */   private static final int READ_HEADERS = 1;
/*     */   private static final int COMPLETED = 2;
/*     */   private int state;
/*     */   private boolean endOfStream;
/*     */   private T message;
/*     */   private CharArrayBuffer lineBuf;
/*     */   private final List<CharArrayBuffer> headerBufs;
/*     */   protected final LineParser lineParser;
/*     */   private final MessageConstraints constraints;
/*     */   
/*     */   @Deprecated
/*     */   public AbstractMessageParser(SessionInputBuffer buffer, LineParser lineParser, HttpParams params) {
/*  92 */     Args.notNull(buffer, "Session input buffer");
/*  93 */     Args.notNull(params, "HTTP parameters");
/*  94 */     this.sessionBuffer = buffer;
/*  95 */     this.state = 0;
/*  96 */     this.endOfStream = false;
/*  97 */     this.headerBufs = new ArrayList<CharArrayBuffer>();
/*  98 */     this.constraints = HttpParamConfig.getMessageConstraints(params);
/*  99 */     this.lineParser = (lineParser != null) ? lineParser : (LineParser)BasicLineParser.INSTANCE;
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
/*     */   public AbstractMessageParser(SessionInputBuffer buffer, LineParser lineParser, MessageConstraints constraints) {
/* 118 */     this.sessionBuffer = (SessionInputBuffer)Args.notNull(buffer, "Session input buffer");
/* 119 */     this.lineParser = (lineParser != null) ? lineParser : (LineParser)BasicLineParser.INSTANCE;
/* 120 */     this.constraints = (constraints != null) ? constraints : MessageConstraints.DEFAULT;
/* 121 */     this.headerBufs = new ArrayList<CharArrayBuffer>();
/* 122 */     this.state = 0;
/* 123 */     this.endOfStream = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 128 */     this.state = 0;
/* 129 */     this.endOfStream = false;
/* 130 */     this.headerBufs.clear();
/* 131 */     this.message = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int fillBuffer(ReadableByteChannel channel) throws IOException {
/* 136 */     int bytesRead = this.sessionBuffer.fill(channel);
/* 137 */     if (bytesRead == -1) {
/* 138 */       this.endOfStream = true;
/*     */     }
/* 140 */     return bytesRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract T createMessage(CharArrayBuffer paramCharArrayBuffer) throws HttpException, ParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseHeadLine() throws HttpException, ParseException {
/* 156 */     this.message = createMessage(this.lineBuf);
/*     */   }
/*     */   
/*     */   private void parseHeader() throws IOException {
/* 160 */     CharArrayBuffer current = this.lineBuf;
/* 161 */     int count = this.headerBufs.size();
/* 162 */     if ((this.lineBuf.charAt(0) == ' ' || this.lineBuf.charAt(0) == '\t') && count > 0) {
/*     */       
/* 164 */       CharArrayBuffer previous = this.headerBufs.get(count - 1);
/* 165 */       int i = 0;
/* 166 */       while (i < current.length()) {
/* 167 */         char ch = current.charAt(i);
/* 168 */         if (ch != ' ' && ch != '\t') {
/*     */           break;
/*     */         }
/* 171 */         i++;
/*     */       } 
/* 173 */       int maxLineLen = this.constraints.getMaxLineLength();
/* 174 */       if (maxLineLen > 0 && previous.length() + 1 + current.length() - i > maxLineLen) {
/* 175 */         throw new MessageConstraintException("Maximum line length limit exceeded");
/*     */       }
/* 177 */       previous.append(' ');
/* 178 */       previous.append(current, i, current.length() - i);
/*     */     } else {
/* 180 */       this.headerBufs.add(current);
/* 181 */       this.lineBuf = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public T parse() throws IOException, HttpException {
/* 187 */     while (this.state != 2) {
/* 188 */       if (this.lineBuf == null) {
/* 189 */         this.lineBuf = new CharArrayBuffer(64);
/*     */       } else {
/* 191 */         this.lineBuf.clear();
/*     */       } 
/* 193 */       boolean lineComplete = this.sessionBuffer.readLine(this.lineBuf, this.endOfStream);
/* 194 */       int maxLineLen = this.constraints.getMaxLineLength();
/* 195 */       if (maxLineLen > 0 && (this.lineBuf.length() > maxLineLen || (!lineComplete && this.sessionBuffer.length() > maxLineLen)))
/*     */       {
/*     */         
/* 198 */         throw new MessageConstraintException("Maximum line length limit exceeded");
/*     */       }
/* 200 */       if (!lineComplete) {
/*     */         break;
/*     */       }
/*     */       
/* 204 */       switch (this.state) {
/*     */         case 0:
/*     */           try {
/* 207 */             parseHeadLine();
/* 208 */           } catch (ParseException px) {
/* 209 */             throw new ProtocolException(px.getMessage(), px);
/*     */           } 
/* 211 */           this.state = 1;
/*     */           break;
/*     */         case 1:
/* 214 */           if (this.lineBuf.length() > 0) {
/* 215 */             int maxHeaderCount = this.constraints.getMaxHeaderCount();
/* 216 */             if (maxHeaderCount > 0 && this.headerBufs.size() >= maxHeaderCount) {
/* 217 */               throw new MessageConstraintException("Maximum header count exceeded");
/*     */             }
/*     */             
/* 220 */             parseHeader(); break;
/*     */           } 
/* 222 */           this.state = 2;
/*     */           break;
/*     */       } 
/*     */       
/* 226 */       if (this.endOfStream && !this.sessionBuffer.hasData()) {
/* 227 */         this.state = 2;
/*     */       }
/*     */     } 
/* 230 */     if (this.state == 2) {
/* 231 */       for (CharArrayBuffer buffer : this.headerBufs) {
/*     */         try {
/* 233 */           this.message.addHeader(this.lineParser.parseHeader(buffer));
/* 234 */         } catch (ParseException ex) {
/* 235 */           throw new ProtocolException(ex.getMessage(), ex);
/*     */         } 
/*     */       } 
/* 238 */       return this.message;
/*     */     } 
/* 240 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/AbstractMessageParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */