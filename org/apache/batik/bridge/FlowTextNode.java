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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlowTextNode
/*    */   extends TextNode
/*    */ {
/*    */   public void setTextPainter(TextPainter textPainter) {
/* 38 */     if (textPainter == null) {
/* 39 */       this.textPainter = FlowTextPainter.getInstance();
/*    */     } else {
/* 41 */       this.textPainter = textPainter;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/FlowTextNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */