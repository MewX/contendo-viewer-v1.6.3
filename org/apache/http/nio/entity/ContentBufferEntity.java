/*    */ package org.apache.http.nio.entity;
/*    */ 
/*    */ import org.apache.http.Header;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.entity.BasicHttpEntity;
/*    */ import org.apache.http.nio.util.ContentInputBuffer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContentBufferEntity
/*    */   extends BasicHttpEntity
/*    */ {
/*    */   private final HttpEntity wrappedEntity;
/*    */   
/*    */   public ContentBufferEntity(HttpEntity entity, ContentInputBuffer buffer) {
/* 54 */     Args.notNull(entity, "HTTP entity");
/* 55 */     this.wrappedEntity = entity;
/* 56 */     setContent(new ContentInputStream(buffer));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isChunked() {
/* 61 */     return this.wrappedEntity.isChunked();
/*    */   }
/*    */ 
/*    */   
/*    */   public long getContentLength() {
/* 66 */     return this.wrappedEntity.getContentLength();
/*    */   }
/*    */ 
/*    */   
/*    */   public Header getContentType() {
/* 71 */     return this.wrappedEntity.getContentType();
/*    */   }
/*    */ 
/*    */   
/*    */   public Header getContentEncoding() {
/* 76 */     return this.wrappedEntity.getContentEncoding();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/ContentBufferEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */