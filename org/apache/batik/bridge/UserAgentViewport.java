/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserAgentViewport
/*    */   implements Viewport
/*    */ {
/*    */   private UserAgent userAgent;
/*    */   
/*    */   public UserAgentViewport(UserAgent userAgent) {
/* 36 */     this.userAgent = userAgent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getWidth() {
/* 43 */     return (float)this.userAgent.getViewportSize().getWidth();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getHeight() {
/* 50 */     return (float)this.userAgent.getViewportSize().getHeight();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/UserAgentViewport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */