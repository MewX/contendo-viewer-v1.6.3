/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HeaderIterator;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpMessage;
/*     */ import org.apache.http.message.BasicLineFormatter;
/*     */ import org.apache.http.message.LineFormatter;
/*     */ import org.apache.http.nio.NHttpMessageWriter;
/*     */ import org.apache.http.nio.reactor.SessionOutputBuffer;
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
/*     */ public abstract class AbstractMessageWriter<T extends HttpMessage>
/*     */   implements NHttpMessageWriter<T>
/*     */ {
/*     */   protected final SessionOutputBuffer sessionBuffer;
/*     */   protected final CharArrayBuffer lineBuf;
/*     */   protected final LineFormatter lineFormatter;
/*     */   
/*     */   @Deprecated
/*     */   public AbstractMessageWriter(SessionOutputBuffer buffer, LineFormatter formatter, HttpParams params) {
/*  72 */     Args.notNull(buffer, "Session input buffer");
/*  73 */     this.sessionBuffer = buffer;
/*  74 */     this.lineBuf = new CharArrayBuffer(64);
/*  75 */     this.lineFormatter = (formatter != null) ? formatter : (LineFormatter)BasicLineFormatter.INSTANCE;
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
/*     */   public AbstractMessageWriter(SessionOutputBuffer buffer, LineFormatter formatter) {
/*  91 */     this.sessionBuffer = (SessionOutputBuffer)Args.notNull(buffer, "Session input buffer");
/*  92 */     this.lineFormatter = (formatter != null) ? formatter : (LineFormatter)BasicLineFormatter.INSTANCE;
/*  93 */     this.lineBuf = new CharArrayBuffer(64);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void writeHeadLine(T paramT) throws IOException;
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(T message) throws IOException, HttpException {
/* 109 */     Args.notNull(message, "HTTP message");
/* 110 */     writeHeadLine(message);
/* 111 */     for (HeaderIterator<Header> headerIterator = message.headerIterator(); headerIterator.hasNext(); ) {
/* 112 */       Header header = headerIterator.next();
/* 113 */       this.sessionBuffer.writeLine(this.lineFormatter.formatHeader(this.lineBuf, header));
/*     */     } 
/*     */     
/* 116 */     this.lineBuf.clear();
/* 117 */     this.sessionBuffer.writeLine(this.lineBuf);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/AbstractMessageWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */