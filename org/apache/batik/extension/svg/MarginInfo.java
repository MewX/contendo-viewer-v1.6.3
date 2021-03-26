/*    */ package org.apache.batik.extension.svg;
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
/*    */   public static final int JUSTIFY_START = 0;
/*    */   public static final int JUSTIFY_MIDDLE = 1;
/*    */   public static final int JUSTIFY_END = 2;
/*    */   public static final int JUSTIFY_FULL = 3;
/*    */   protected float top;
/*    */   protected float right;
/*    */   protected float bottom;
/*    */   protected float left;
/*    */   protected float indent;
/*    */   protected int justification;
/*    */   protected boolean flowRegionBreak;
/*    */   
/*    */   public MarginInfo(float top, float right, float bottom, float left, float indent, int justification, boolean flowRegionBreak) {
/* 45 */     this.top = top;
/* 46 */     this.right = right;
/* 47 */     this.bottom = bottom;
/* 48 */     this.left = left;
/*    */     
/* 50 */     this.indent = indent;
/*    */     
/* 52 */     this.justification = justification;
/* 53 */     this.flowRegionBreak = flowRegionBreak;
/*    */   }
/*    */   
/*    */   public MarginInfo(float margin, int justification) {
/* 57 */     setMargin(margin);
/* 58 */     this.indent = 0.0F;
/* 59 */     this.justification = justification;
/* 60 */     this.flowRegionBreak = false;
/*    */   }
/*    */   
/*    */   public void setMargin(float margin) {
/* 64 */     this.top = margin;
/* 65 */     this.right = margin;
/* 66 */     this.bottom = margin;
/* 67 */     this.left = margin;
/*    */   }
/*    */   
/* 70 */   public float getTopMargin() { return this.top; }
/* 71 */   public float getRightMargin() { return this.right; }
/* 72 */   public float getBottomMargin() { return this.bottom; } public float getLeftMargin() {
/* 73 */     return this.left;
/*    */   } public float getIndent() {
/* 75 */     return this.indent;
/*    */   }
/* 77 */   public int getJustification() { return this.justification; } public boolean isFlowRegionBreak() {
/* 78 */     return this.flowRegionBreak;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/MarginInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */