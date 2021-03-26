/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.w3c.dom.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Location
/*    */   implements Location
/*    */ {
/*    */   private BridgeContext bridgeContext;
/*    */   
/*    */   public Location(BridgeContext ctx) {
/* 38 */     this.bridgeContext = ctx;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void assign(String url) {
/* 50 */     this.bridgeContext.getUserAgent().loadDocument(url);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void reload() {
/* 57 */     String url = ((AbstractDocument)this.bridgeContext.getDocument()).getDocumentURI();
/*    */     
/* 59 */     this.bridgeContext.getUserAgent().loadDocument(url);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return ((AbstractDocument)this.bridgeContext.getDocument()).getDocumentURI();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/Location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */