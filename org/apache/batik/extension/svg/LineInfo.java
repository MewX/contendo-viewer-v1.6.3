/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ import java.text.AttributedCharacterIterator;
/*    */ import org.apache.batik.gvt.font.GVTGlyphVector;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LineInfo
/*    */ {
/*    */   Point2D.Float loc;
/*    */   AttributedCharacterIterator aci;
/*    */   GVTGlyphVector gv;
/*    */   int startIdx;
/*    */   int endIdx;
/*    */   float advance;
/*    */   float visualAdvance;
/*    */   float lastCharWidth;
/*    */   float lineWidth;
/*    */   boolean partial;
/*    */   Point2D.Float verticalAlignOffset;
/*    */   
/*    */   public LineInfo(Point2D.Float loc, AttributedCharacterIterator aci, GVTGlyphVector gv, int startIdx, int endIdx, float advance, float visualAdvance, float lastCharWidth, float lineWidth, boolean partial, Point2D.Float verticalAlignOffset) {
/* 60 */     this.loc = loc;
/* 61 */     this.aci = aci;
/* 62 */     this.gv = gv;
/* 63 */     this.startIdx = startIdx;
/* 64 */     this.endIdx = endIdx;
/* 65 */     this.advance = advance;
/* 66 */     this.visualAdvance = visualAdvance;
/* 67 */     this.lastCharWidth = lastCharWidth;
/* 68 */     this.lineWidth = lineWidth;
/* 69 */     this.partial = partial;
/* 70 */     this.verticalAlignOffset = verticalAlignOffset;
/*    */   }
/*    */   
/* 73 */   public Point2D.Float getLocation() { return this.loc; }
/* 74 */   public AttributedCharacterIterator getACI() { return this.aci; }
/* 75 */   public GVTGlyphVector getGlyphVector() { return this.gv; }
/* 76 */   public int getStartIdx() { return this.startIdx; }
/* 77 */   public int getEndIdx() { return this.endIdx; }
/* 78 */   public float getAdvance() { return this.advance; }
/* 79 */   public float getVisualAdvance() { return this.visualAdvance; }
/* 80 */   public float getLastCharWidth() { return this.lastCharWidth; }
/* 81 */   public float getLineWidth() { return this.lineWidth; }
/* 82 */   public boolean isPartialLine() { return this.partial; } public Point2D.Float getVerticalAlignOffset() {
/* 83 */     return this.verticalAlignOffset;
/*    */   }
/*    */   public String toString() {
/* 86 */     return "[LineInfo loc: " + this.loc + " [" + this.startIdx + ',' + this.endIdx + "] " + " LWidth: " + this.lineWidth + " Adv: " + this.advance + " VAdv: " + this.visualAdvance + " LCW: " + this.lastCharWidth + " Partial: " + this.partial + " verticalAlignOffset: " + this.verticalAlignOffset;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/LineInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */