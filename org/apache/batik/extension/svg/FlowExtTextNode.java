/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import org.apache.batik.bridge.TextNode;
/*    */ import org.apache.batik.bridge.TextPainter;
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
/*    */ public class FlowExtTextNode
/*    */   extends TextNode
/*    */ {
/*    */   public void setTextPainter(TextPainter textPainter) {
/* 41 */     if (textPainter == null) {
/* 42 */       this.textPainter = FlowExtTextPainter.getInstance();
/*    */     } else {
/* 44 */       this.textPainter = textPainter;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/FlowExtTextNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */