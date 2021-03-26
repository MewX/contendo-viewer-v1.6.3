/*    */ package org.apache.http.impl.nio.codecs;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.http.HttpMessage;
/*    */ import org.apache.http.HttpRequest;
/*    */ import org.apache.http.message.LineFormatter;
/*    */ import org.apache.http.nio.reactor.SessionOutputBuffer;
/*    */ import org.apache.http.params.HttpParams;
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
/*    */ @Deprecated
/*    */ public class HttpRequestWriter
/*    */   extends AbstractMessageWriter
/*    */ {
/*    */   public HttpRequestWriter(SessionOutputBuffer buffer, LineFormatter formatter, HttpParams params) {
/* 54 */     super(buffer, formatter, params);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeHeadLine(HttpMessage message) throws IOException {
/* 61 */     CharArrayBuffer buffer = this.lineFormatter.formatRequestLine(this.lineBuf, ((HttpRequest)message).getRequestLine());
/*    */     
/* 63 */     this.sessionBuffer.writeLine(buffer);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/HttpRequestWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */