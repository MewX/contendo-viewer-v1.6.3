/*    */ package org.apache.http.nio.protocol;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.http.ContentTooLongException;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.HttpResponse;
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
/*    */ public class BasicAsyncResponseConsumer
/*    */   extends AbstractAsyncResponseConsumer<HttpResponse>
/*    */ {
/*    */   private static final int MAX_INITIAL_BUFFER_SIZE = 262144;
/*    */   private volatile HttpResponse response;
/*    */   private volatile SimpleInputBuffer buf;
/*    */   
/*    */   protected void onResponseReceived(HttpResponse response) throws IOException {
/* 63 */     this.response = response;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onEntityEnclosed(HttpEntity entity, ContentType contentType) throws IOException {
/* 69 */     long len = entity.getContentLength();
/* 70 */     if (len > 2147483647L) {
/* 71 */       throw new ContentTooLongException("Entity content is too long: %,d", new Object[] { Long.valueOf(len) });
/*    */     }
/* 73 */     if (len < 0L) {
/* 74 */       len = 4096L;
/*    */     }
/* 76 */     int initialBufferSize = Math.min((int)len, 262144);
/* 77 */     this.buf = new SimpleInputBuffer(initialBufferSize, (ByteBufferAllocator)new HeapByteBufferAllocator());
/* 78 */     this.response.setEntity((HttpEntity)new ContentBufferEntity(entity, (ContentInputBuffer)this.buf));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onContentReceived(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 84 */     Asserts.notNull(this.buf, "Content buffer");
/* 85 */     this.buf.consumeContent(decoder);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void releaseResources() {
/* 90 */     this.response = null;
/* 91 */     this.buf = null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected HttpResponse buildResult(HttpContext context) {
/* 96 */     return this.response;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/BasicAsyncResponseConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */