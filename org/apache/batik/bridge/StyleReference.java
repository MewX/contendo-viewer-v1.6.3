/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import org.apache.batik.gvt.GraphicsNode;
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
/*    */ public class StyleReference
/*    */ {
/*    */   private GraphicsNode node;
/*    */   private String styleAttribute;
/*    */   
/*    */   public StyleReference(GraphicsNode node, String styleAttribute) {
/* 43 */     this.node = node;
/* 44 */     this.styleAttribute = styleAttribute;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GraphicsNode getGraphicsNode() {
/* 51 */     return this.node;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getStyleAttribute() {
/* 58 */     return this.styleAttribute;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/StyleReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */