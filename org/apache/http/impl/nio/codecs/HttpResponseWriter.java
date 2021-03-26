/*    */ package org.apache.http.impl.nio.codecs;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.http.HttpMessage;
/*    */ import org.apache.http.HttpResponse;
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
/*    */ public class HttpResponseWriter
/*    */   extends AbstractMessageWriter
/*    */ {
/*    */   public HttpResponseWriter(SessionOutputBuffer buffer, LineFormatter formatter, HttpParams params) {
/* 54 */     super(buffer, formatter, params);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeHeadLine(HttpMessage message) throws IOException {
/* 61 */     CharArrayBuffer buffer = this.lineFormatter.formatStatusLine(this.lineBuf, ((HttpResponse)message).getStatusLine());
/*    */     
/* 63 */     this.sessionBuffer.writeLine(buffer);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/HttpResponseWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */