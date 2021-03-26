/*    */ package org.apache.xmlgraphics.image.loader.cache;
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
/*    */ public class DefaultExpirationPolicy
/*    */   implements ExpirationPolicy
/*    */ {
/*    */   public static final int EXPIRATION_IMMEDIATE = 0;
/*    */   public static final int EXPIRATION_NEVER = -1;
/*    */   private int expirationAfter;
/*    */   
/*    */   public DefaultExpirationPolicy() {
/* 36 */     this(60);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DefaultExpirationPolicy(int expirationAfter) {
/* 44 */     this.expirationAfter = expirationAfter;
/*    */   }
/*    */   
/*    */   private boolean isNeverExpired() {
/* 48 */     return (this.expirationAfter < 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isExpired(TimeStampProvider provider, long timestamp) {
/* 53 */     if (isNeverExpired()) {
/* 54 */       return false;
/*    */     }
/* 56 */     long now = provider.getTimeStamp();
/* 57 */     return (now >= timestamp + this.expirationAfter * 1000L);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/cache/DefaultExpirationPolicy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */