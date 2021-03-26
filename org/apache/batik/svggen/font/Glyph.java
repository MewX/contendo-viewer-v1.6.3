/*    */ package org.apache.batik.svggen.font;
/*    */ 
/*    */ import org.apache.batik.svggen.font.table.GlyphDescription;
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
/*    */ public class Glyph
/*    */ {
/*    */   protected short leftSideBearing;
/*    */   protected int advanceWidth;
/*    */   private Point[] points;
/*    */   
/*    */   public Glyph(GlyphDescription gd, short lsb, int advance) {
/* 36 */     this.leftSideBearing = lsb;
/* 37 */     this.advanceWidth = advance;
/* 38 */     describe(gd);
/*    */   }
/*    */   
/*    */   public int getAdvanceWidth() {
/* 42 */     return this.advanceWidth;
/*    */   }
/*    */   
/*    */   public short getLeftSideBearing() {
/* 46 */     return this.leftSideBearing;
/*    */   }
/*    */   
/*    */   public Point getPoint(int i) {
/* 50 */     return this.points[i];
/*    */   }
/*    */   
/*    */   public int getPointCount() {
/* 54 */     return this.points.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void reset() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void scale(int factor) {
/* 67 */     for (Point point : this.points) {
/*    */ 
/*    */       
/* 70 */       point.x = (point.x << 10) * factor >> 26;
/* 71 */       point.y = (point.y << 10) * factor >> 26;
/*    */     } 
/* 73 */     this.leftSideBearing = (short)(this.leftSideBearing * factor >> 6);
/* 74 */     this.advanceWidth = this.advanceWidth * factor >> 6;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void describe(GlyphDescription gd) {
/* 81 */     int endPtIndex = 0;
/* 82 */     this.points = new Point[gd.getPointCount() + 2];
/* 83 */     for (int i = 0; i < gd.getPointCount(); i++) {
/* 84 */       boolean endPt = (gd.getEndPtOfContours(endPtIndex) == i);
/* 85 */       if (endPt) {
/* 86 */         endPtIndex++;
/*    */       }
/* 88 */       this.points[i] = new Point(gd.getXCoordinate(i), gd.getYCoordinate(i), ((gd.getFlags(i) & 0x1) != 0), endPt);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 96 */     this.points[gd.getPointCount()] = new Point(0, 0, true, true);
/* 97 */     this.points[gd.getPointCount() + 1] = new Point(this.advanceWidth, 0, true, true);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/Glyph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */