/*    */ package org.apache.batik.gvt.flow;
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
/*    */ public class MarginInfo
/*    */ {
/*    */   public static final int ALIGN_START = 0;
/*    */   public static final int ALIGN_MIDDLE = 1;
/*    */   public static final int ALIGN_END = 2;
/*    */   public static final int ALIGN_FULL = 3;
/*    */   protected float top;
/*    */   protected float right;
/*    */   protected float bottom;
/*    */   protected float left;
/*    */   protected float indent;
/*    */   protected int alignment;
/*    */   protected float lineHeight;
/*    */   protected boolean fontSizeRelative;
/*    */   protected boolean flowRegionBreak;
/*    */   
/*    */   public MarginInfo(float top, float right, float bottom, float left, float indent, int alignment, float lineHeight, boolean fontSizeRelative, boolean flowRegionBreak) {
/* 49 */     this.top = top;
/* 50 */     this.right = right;
/* 51 */     this.bottom = bottom;
/* 52 */     this.left = left;
/*    */     
/* 54 */     this.indent = indent;
/*    */     
/* 56 */     this.alignment = alignment;
/*    */     
/* 58 */     this.lineHeight = lineHeight;
/* 59 */     this.fontSizeRelative = fontSizeRelative;
/*    */     
/* 61 */     this.flowRegionBreak = flowRegionBreak;
/*    */   }
/*    */   
/*    */   public MarginInfo(float margin, int alignment) {
/* 65 */     setMargin(margin);
/* 66 */     this.indent = 0.0F;
/* 67 */     this.alignment = alignment;
/* 68 */     this.flowRegionBreak = false;
/*    */   }
/*    */   
/*    */   public void setMargin(float margin) {
/* 72 */     this.top = margin;
/* 73 */     this.right = margin;
/* 74 */     this.bottom = margin;
/* 75 */     this.left = margin;
/*    */   }
/*    */   
/* 78 */   public float getTopMargin() { return this.top; }
/* 79 */   public float getRightMargin() { return this.right; }
/* 80 */   public float getBottomMargin() { return this.bottom; } public float getLeftMargin() {
/* 81 */     return this.left;
/*    */   } public float getIndent() {
/* 83 */     return this.indent;
/*    */   } public int getTextAlignment() {
/* 85 */     return this.alignment;
/*    */   }
/*    */   
/* 88 */   public float getLineHeight() { return this.lineHeight; } public boolean isFontSizeRelative() {
/* 89 */     return this.fontSizeRelative;
/*    */   } public boolean isFlowRegionBreak() {
/* 91 */     return this.flowRegionBreak;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/flow/MarginInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */