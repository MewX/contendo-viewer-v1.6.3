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
/*    */ public class DSCCommentPageHiResBoundingBox
/*    */   extends DSCCommentHiResBoundingBox
/*    */ {
/*    */   public DSCCommentPageHiResBoundingBox() {}
/*    */   
/*    */   public DSCCommentPageHiResBoundingBox(Rectangle2D bbox) {
/* 43 */     super(bbox);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 48 */     return "PageHiResBoundingBox";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentPageHiResBoundingBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */