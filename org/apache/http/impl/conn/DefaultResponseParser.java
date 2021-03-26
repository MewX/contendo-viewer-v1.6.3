/*     */ package org.apache.http.impl.conn;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpMessage;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.NoHttpResponseException;
/*     */ import org.apache.http.ProtocolException;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.impl.io.AbstractMessageParser;
/*     */ import org.apache.http.io.SessionInputBuffer;
/*     */ import org.apache.http.message.LineParser;
/*     */ import org.apache.http.message.ParserCursor;
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
/*     */ @Deprecated
/*     */ @Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/*     */ public class DefaultResponseParser
/*     */   extends AbstractMessageParser<HttpMessage>
/*     */ {
/*  69 */   private final Log log = LogFactory.getLog(getClass());
/*     */ 
/*     */   
/*     */   private final HttpResponseFactory responseFactory;
/*     */   
/*     */   private final CharArrayBuffer lineBuf;
/*     */   
/*     */   private final int maxGarbageLines;
/*     */ 
/*     */   
/*     */   public DefaultResponseParser(SessionInputBuffer buffer, LineParser parser, HttpResponseFactory responseFactory, HttpParams params) {
/*  80 */     super(buffer, parser, params);
/*  81 */     Args.notNull(responseFactory, "Response factory");
/*  82 */     this.responseFactory = responseFactory;
/*  83 */     this.lineBuf = new CharArrayBuffer(128);
/*  84 */     this.maxGarbageLines = getMaxGarbageLines(params);
/*     */   }
/*     */   
/*     */   protected int getMaxGarbageLines(HttpParams params) {
/*  88 */     return params.getIntParameter("http.connection.max-status-line-garbage", 2147483647);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected HttpMessage parseHead(SessionInputBuffer sessionBuffer) throws IOException, HttpException {
/*  97 */     int count = 0;
/*  98 */     ParserCursor cursor = null;
/*     */     
/*     */     while (true) {
/* 101 */       this.lineBuf.clear();
/* 102 */       int i = sessionBuffer.readLine(this.lineBuf);
/* 103 */       if (i == -1 && count == 0)
/*     */       {
/* 105 */         throw new NoHttpResponseException("The target server failed to respond");
/*     */       }
/* 107 */       cursor = new ParserCursor(0, this.lineBuf.length());
/* 108 */       if (this.lineParser.hasProtocolVersion(this.lineBuf, cursor)) {
/*     */         break;
/*     */       }
/* 111 */       if (i == -1 || count >= this.maxGarbageLines)
/*     */       {
/* 113 */         throw new ProtocolException("The server failed to respond with a valid HTTP response");
/*     */       }
/*     */       
/* 116 */       if (this.log.isDebugEnabled()) {
/* 117 */         this.log.debug("Garbage in response: " + this.lineBuf.toString());
/*     */       }
/* 119 */       count++;
/*     */     } 
/*     */     
/* 122 */     StatusLine statusline = this.lineParser.parseStatusLine(this.lineBuf, cursor);
/* 123 */     return (HttpMessage)this.responseFactory.newHttpResponse(statusline, null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/conn/DefaultResponseParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */