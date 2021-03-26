/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import org.apache.batik.util.SVGConstants;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSVGBridge
/*    */   implements Bridge, SVGConstants
/*    */ {
/*    */   public String getNamespaceURI() {
/* 40 */     return "http://www.w3.org/2000/svg";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Bridge getInstance() {
/* 49 */     return this;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/AbstractSVGBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */