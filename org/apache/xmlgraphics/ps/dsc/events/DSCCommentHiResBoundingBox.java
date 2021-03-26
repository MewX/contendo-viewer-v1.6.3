/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.io.IOException;
/*    */ import org.apache.xmlgraphics.ps.DSCConstants;
/*    */ import org.apache.xmlgraphics.ps.PSGenerator;
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
/*    */ public class DSCCommentHiResBoundingBox
/*    */   extends DSCCommentBoundingBox
/*    */ {
/*    */   public DSCCommentHiResBoundingBox() {}
/*    */   
/*    */   public DSCCommentHiResBoundingBox(Rectangle2D bbox) {
/* 45 */     super(bbox);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 50 */     return "HiResBoundingBox";
/*    */   }
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 55 */     if (getBoundingBox() != null) {
/* 56 */       gen.writeDSCComment(getName(), new Object[] { Double.valueOf(getBoundingBox().getX()), Double.valueOf(getBoundingBox().getY()), Double.valueOf(getBoundingBox().getX() + getBoundingBox().getWidth()), Double.valueOf(getBoundingBox().getY() + getBoundingBox().getHeight()) });
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 62 */       gen.writeDSCComment(getName(), DSCConstants.ATEND);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentHiResBoundingBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */