/*    */ package org.apache.http.nio.protocol;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.http.ContentTooLongException;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.HttpEntityEnclosingRequest;
/*    */ import org.apache.http.HttpRequest;
/*    */ import org.apache.http.entity.ContentType;
/*    */ import org.apache.http.nio.ContentDecoder;
/*    */ import org.apache.http.nio.IOControl;
/*    */ import org.apache.http.nio.entity.ContentBufferEntity;
/*    */ import org.apache.http.nio.util.ByteBufferAllocator;
/*    */ import org.apache.http.nio.util.ContentInputBuffer;
/*    */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*    */ import org.apache.http.nio.util.SimpleInputBuffer;
/*    */ import org.apache.http.protocol.HttpContext;
/*    */ import org.apache.http.util.Asserts;
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
/*    */ public class BasicAsyncRequestConsumer
/*    */   extends AbstractAsyncRequestConsumer<HttpRequest>
/*    */ {
/*    */   private static final int MAX_INITIAL_BUFFER_SIZE = 262144;
/*    */   private volatile HttpRequest request;
/*    */   private volatile SimpleInputBuffer buf;
/*    */   
/*    */   protected void onRequestReceived(HttpRequest request) throws IOException {
/* 64 */     this.request = request;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onEntityEnclosed(HttpEntity entity, ContentType contentType) throws IOException {
/* 70 */     long len = entity.getContentLength();
/* 71 */     if (len > 2147483647L) {
/* 72 */       throw new ContentTooLongException("Entity content is too long: %,d", new Object[] { Long.valueOf(len) });
/*    */     }
/* 74 */     if (len < 0L) {
/* 75 */       len = 4096L;
/*    */     }
/* 77 */     int initialBufferSize = Math.min((int)len, 262144);
/* 78 */     this.buf = new SimpleInputBuffer(initialBufferSize, (ByteBufferAllocator)new HeapByteBufferAllocator());
/* 79 */     ((HttpEntityEnclosingRequest)this.request).setEntity((HttpEntity)new ContentBufferEntity(entity, (ContentInputBuffer)this.buf));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onContentReceived(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 86 */     Asserts.notNull(this.buf, "Content buffer");
/* 87 */     this.buf.consumeContent(decoder);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void releaseResources() {
/* 92 */     this.request = null;
/* 93 */     this.buf = null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected HttpRequest buildResult(HttpContext context) {
/* 98 */     return this.request;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/BasicAsyncRequestConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */