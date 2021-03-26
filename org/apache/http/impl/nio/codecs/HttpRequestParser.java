/*    */ package org.apache.http.impl.nio.codecs;
/*    */ 
/*    */ import org.apache.http.HttpException;
/*    */ import org.apache.http.HttpMessage;
/*    */ import org.apache.http.HttpRequestFactory;
/*    */ import org.apache.http.ParseException;
/*    */ import org.apache.http.RequestLine;
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
/*    */ public class HttpRequestParser
/*    */   extends AbstractMessageParser
/*    */ {
/*    */   private final HttpRequestFactory requestFactory;
/*    */   
/*    */   public HttpRequestParser(SessionInputBuffer buffer, LineParser parser, HttpRequestFactory requestFactory, HttpParams params) {
/* 68 */     super(buffer, parser, params);
/* 69 */     Args.notNull(requestFactory, "Request factory");
/* 70 */     this.requestFactory = requestFactory;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected HttpMessage createMessage(CharArrayBuffer buffer) throws HttpException, ParseException {
/* 76 */     ParserCursor cursor = new ParserCursor(0, buffer.length());
/* 77 */     RequestLine requestLine = this.lineParser.parseRequestLine(buffer, cursor);
/* 78 */     return (HttpMessage)this.requestFactory.newHttpRequest(requestLine);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/HttpRequestParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */