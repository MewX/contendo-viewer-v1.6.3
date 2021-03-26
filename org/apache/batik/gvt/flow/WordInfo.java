/*    */ package org.apache.batik.gvt.flow;
/*    */ 
/*    */ import org.apache.batik.gvt.font.GVTFont;
/*    */ import org.apache.batik.gvt.font.GVTLineMetrics;
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
/*    */ public class WordInfo
/*    */ {
/* 34 */   int index = -1;
/* 35 */   float ascent = -1.0F; float descent = -1.0F; float lineHeight = -1.0F;
/* 36 */   GlyphGroupInfo[] glyphGroups = null;
/* 37 */   Object flowLine = null;
/*    */   
/*    */   public WordInfo(int index) {
/* 40 */     this.index = index;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   WordInfo(int index, float ascent, float descent, float lineHeight, GlyphGroupInfo[] glyphGroups) {
/* 46 */     this.index = index;
/* 47 */     this.ascent = ascent;
/* 48 */     this.descent = descent;
/* 49 */     this.lineHeight = lineHeight;
/* 50 */     this.glyphGroups = glyphGroups;
/*    */   }
/*    */   public int getIndex() {
/* 53 */     return this.index;
/*    */   }
/* 55 */   public float getAscent() { return this.ascent; } public void setAscent(float ascent) {
/* 56 */     this.ascent = ascent;
/*    */   }
/* 58 */   public float getDescent() { return this.descent; } public void setDescent(float descent) {
/* 59 */     this.descent = descent;
/*    */   }
/*    */   public void addLineMetrics(GVTFont font, GVTLineMetrics lm) {
/* 62 */     if (this.ascent < lm.getAscent())
/* 63 */       this.ascent = lm.getAscent(); 
/* 64 */     if (this.descent < lm.getDescent())
/* 65 */       this.descent = lm.getDescent(); 
/*    */   }
/*    */   
/*    */   public float getLineHeight() {
/* 69 */     return this.lineHeight;
/*    */   } public void setLineHeight(float lineHeight) {
/* 71 */     this.lineHeight = lineHeight;
/*    */   } public void addLineHeight(float lineHeight) {
/* 73 */     if (this.lineHeight < lineHeight)
/* 74 */       this.lineHeight = lineHeight; 
/*    */   }
/*    */   
/* 77 */   public Object getFlowLine() { return this.flowLine; } public void setFlowLine(Object fl) {
/* 78 */     this.flowLine = fl;
/*    */   }
/*    */   public int getNumGlyphGroups() {
/* 81 */     if (this.glyphGroups == null)
/* 82 */       return -1; 
/* 83 */     return this.glyphGroups.length;
/*    */   }
/*    */   public void setGlyphGroups(GlyphGroupInfo[] glyphGroups) {
/* 86 */     this.glyphGroups = glyphGroups;
/*    */   }
/*    */   public GlyphGroupInfo getGlyphGroup(int idx) {
/* 89 */     if (this.glyphGroups == null) return null; 
/* 90 */     return this.glyphGroups[idx];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/flow/WordInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */