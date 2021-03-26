/*    */ package org.apache.http.nio.entity;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.channels.Channels;
/*    */ import java.nio.channels.ReadableByteChannel;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.nio.ContentEncoder;
/*    */ import org.apache.http.nio.IOControl;
/*    */ import org.apache.http.util.Args;
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
/*    */ public class EntityAsyncContentProducer
/*    */   implements HttpAsyncContentProducer
/*    */ {
/*    */   private final HttpEntity entity;
/*    */   private final ByteBuffer buffer;
/*    */   private ReadableByteChannel channel;
/*    */   
/*    */   public EntityAsyncContentProducer(HttpEntity entity) {
/* 56 */     Args.notNull(entity, "HTTP entity");
/* 57 */     this.entity = entity;
/* 58 */     this.buffer = ByteBuffer.allocate(4096);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 64 */     if (this.channel == null) {
/* 65 */       this.channel = Channels.newChannel(this.entity.getContent());
/*    */     }
/* 67 */     int i = this.channel.read(this.buffer);
/* 68 */     this.buffer.flip();
/* 69 */     encoder.write(this.buffer);
/* 70 */     boolean buffering = this.buffer.hasRemaining();
/* 71 */     this.buffer.compact();
/* 72 */     if (i == -1 && !buffering) {
/* 73 */       encoder.complete();
/* 74 */       close();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRepeatable() {
/* 80 */     return this.entity.isRepeatable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 85 */     ReadableByteChannel local = this.channel;
/* 86 */     this.channel = null;
/* 87 */     if (local != null) {
/* 88 */       local.close();
/*    */     }
/* 90 */     if (this.entity.isStreaming()) {
/* 91 */       InputStream inStream = this.entity.getContent();
/* 92 */       inStream.close();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 98 */     return this.entity.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/EntityAsyncContentProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */