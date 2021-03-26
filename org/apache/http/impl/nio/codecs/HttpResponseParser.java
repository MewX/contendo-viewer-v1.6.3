/*    */ package org.apache.http.impl.nio.codecs;
/*    */ 
/*    */ import org.apache.http.HttpException;
/*    */ import org.apache.http.HttpMessage;
/*    */ import org.apache.http.HttpResponseFactory;
/*    */ import org.apache.http.ParseException;
/*    */ import org.apache.http.StatusLine;
/*    */ import org.apache.http.message.LineParser;
/*    */ import org.apache.http.message.ParserCursor;
/*    */ import org.apache.http.nio.reactor.SessionInputBuffer;
/*    */ import org.apache.http.params.HttpParams;
/*    */ import org.apache.http.util.Args;
/*    */ import org.apache.http.util.CharArrayBuffer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class HttpResponseParser
/*    */   extends AbstractMessageParser
/*    */ {
/*    */   private final HttpResponseFactory responseFactory;
/*    */   
/*    */   public HttpResponseParser(SessionInputBuffer buffer, LineParser parser, HttpResponseFactory responseFactory, HttpParams params) {
/* 68 */     super(buffer, parser, params);
/* 69 */     Args.notNull(responseFactory, "Response factory");
/* 70 */     this.responseFactory = responseFactory;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected HttpMessage createMessage(CharArrayBuffer buffer) throws HttpException, ParseException {
/* 76 */     ParserCursor cursor = new ParserCursor(0, buffer.length());
/* 77 */     StatusLine statusline = this.lineParser.parseStatusLine(buffer, cursor);
/* 78 */     return (HttpMessage)this.responseFactory.newHttpResponse(statusline, null);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/HttpResponseParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */