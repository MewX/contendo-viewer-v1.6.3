/*    */ package org.apache.xmlgraphics.ps.dsc.events;
/*    */ 
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
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
/*    */ public class DSCCommentBoundingBox
/*    */   extends AbstractDSCComment
/*    */ {
/*    */   private Rectangle2D bbox;
/*    */   
/*    */   public DSCCommentBoundingBox() {}
/*    */   
/*    */   public DSCCommentBoundingBox(Rectangle2D bbox) {
/* 48 */     setBoundingBox(bbox);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Rectangle2D getBoundingBox() {
/* 56 */     return this.bbox;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBoundingBox(Rectangle2D bbox) {
/* 64 */     this.bbox = bbox;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 69 */     return "BoundingBox";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasValues() {
/* 74 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void parseValue(String value) {
/* 79 */     List params = splitParams(value);
/* 80 */     Iterator<String> iter = params.iterator();
/*    */     
/* 82 */     double x1 = Double.parseDouble(iter.next());
/* 83 */     double y1 = Double.parseDouble(iter.next());
/* 84 */     double x2 = Double.parseDouble(iter.next());
/* 85 */     double y2 = Double.parseDouble(iter.next());
/* 86 */     this.bbox = new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void generate(PSGenerator gen) throws IOException {
/* 91 */     if (getBoundingBox() != null) {
/* 92 */       gen.writeDSCComment(getName(), new Object[] { Integer.valueOf((int)Math.floor(this.bbox.getX())), Integer.valueOf((int)Math.floor(this.bbox.getY())), Integer.valueOf((int)Math.ceil(this.bbox.getX() + this.bbox.getWidth())), Integer.valueOf((int)Math.ceil(this.bbox.getY() + this.bbox.getHeight())) });
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 98 */       gen.writeDSCComment(getName(), DSCConstants.ATEND);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentBoundingBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */