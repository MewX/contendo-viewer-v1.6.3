/*    */ package org.apache.batik.gvt.text;
/*    */ 
/*    */ import java.awt.geom.GeneralPath;
/*    */ import java.awt.geom.Point2D;
/*    */ import org.apache.batik.ext.awt.geom.PathLength;
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
/*    */ public class TextPath
/*    */ {
/*    */   private PathLength pathLength;
/*    */   private float startOffset;
/*    */   
/*    */   public TextPath(GeneralPath path) {
/* 43 */     this.pathLength = new PathLength(path);
/* 44 */     this.startOffset = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setStartOffset(float startOffset) {
/* 53 */     this.startOffset = startOffset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getStartOffset() {
/* 62 */     return this.startOffset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float lengthOfPath() {
/* 71 */     return this.pathLength.lengthOfPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float angleAtLength(float length) {
/* 82 */     return this.pathLength.angleAtLength(length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Point2D pointAtLength(float length) {
/* 93 */     return this.pathLength.pointAtLength(length);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/text/TextPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */