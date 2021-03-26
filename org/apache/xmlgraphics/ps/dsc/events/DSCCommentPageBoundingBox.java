/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.awt.geom.Rectangle2D;
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
/*    */ public class DSCCommentPageBoundingBox
/*    */   extends DSCCommentBoundingBox
/*    */ {
/*    */   public DSCCommentPageBoundingBox() {}
/*    */   
/*    */   public DSCCommentPageBoundingBox(Rectangle2D bbox) {
/* 43 */     super(bbox);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 48 */     return "PageBoundingBox";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentPageBoundingBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */