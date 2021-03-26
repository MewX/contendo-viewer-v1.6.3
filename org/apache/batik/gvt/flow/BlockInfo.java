/*     */ package org.apache.batik.gvt.flow;
/*     */ 
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.gvt.font.GVTFont;
/*     */ import org.apache.batik.gvt.font.GVTLineMetrics;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockInfo
/*     */ {
/*     */   public static final int ALIGN_START = 0;
/*     */   public static final int ALIGN_MIDDLE = 1;
/*     */   public static final int ALIGN_END = 2;
/*     */   public static final int ALIGN_FULL = 3;
/*     */   protected float top;
/*     */   protected float right;
/*     */   protected float bottom;
/*     */   protected float left;
/*     */   protected float indent;
/*     */   protected int alignment;
/*     */   protected float lineHeight;
/*     */   protected List fontList;
/*     */   protected Map fontAttrs;
/*  51 */   protected float ascent = -1.0F;
/*  52 */   protected float descent = -1.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean flowRegionBreak;
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockInfo(float top, float right, float bottom, float left, float indent, int alignment, float lineHeight, List fontList, Map fontAttrs, boolean flowRegionBreak) {
/*  61 */     this.top = top;
/*  62 */     this.right = right;
/*  63 */     this.bottom = bottom;
/*  64 */     this.left = left;
/*     */     
/*  66 */     this.indent = indent;
/*     */     
/*  68 */     this.alignment = alignment;
/*     */     
/*  70 */     this.lineHeight = lineHeight;
/*  71 */     this.fontList = fontList;
/*  72 */     this.fontAttrs = fontAttrs;
/*     */     
/*  74 */     this.flowRegionBreak = flowRegionBreak;
/*     */   }
/*     */   
/*     */   public BlockInfo(float margin, int alignment) {
/*  78 */     setMargin(margin);
/*  79 */     this.indent = 0.0F;
/*  80 */     this.alignment = alignment;
/*  81 */     this.flowRegionBreak = false;
/*     */   }
/*     */   
/*     */   public void setMargin(float margin) {
/*  85 */     this.top = margin;
/*  86 */     this.right = margin;
/*  87 */     this.bottom = margin;
/*  88 */     this.left = margin;
/*     */   }
/*     */   
/*     */   public void initLineInfo(FontRenderContext frc) {
/*  92 */     float fontSize = 12.0F;
/*  93 */     Float fsFloat = (Float)this.fontAttrs.get(TextAttribute.SIZE);
/*  94 */     if (fsFloat != null) {
/*  95 */       fontSize = fsFloat.floatValue();
/*     */     }
/*  97 */     Iterator i$ = this.fontList.iterator(); if (i$.hasNext()) { Object aFontList = i$.next();
/*  98 */       GVTFont font = (GVTFont)aFontList;
/*  99 */       GVTLineMetrics lm = font.getLineMetrics("", frc);
/* 100 */       this.ascent = lm.getAscent();
/* 101 */       this.descent = lm.getDescent(); }
/*     */ 
/*     */     
/* 104 */     if (this.ascent == -1.0F) {
/* 105 */       this.ascent = fontSize * 0.8F;
/* 106 */       this.descent = fontSize * 0.2F;
/*     */     } 
/*     */   }
/*     */   
/* 110 */   public float getTopMargin() { return this.top; }
/* 111 */   public float getRightMargin() { return this.right; }
/* 112 */   public float getBottomMargin() { return this.bottom; } public float getLeftMargin() {
/* 113 */     return this.left;
/*     */   } public float getIndent() {
/* 115 */     return this.indent;
/*     */   } public int getTextAlignment() {
/* 117 */     return this.alignment;
/*     */   }
/*     */   
/* 120 */   public float getLineHeight() { return this.lineHeight; }
/* 121 */   public List getFontList() { return this.fontList; }
/* 122 */   public Map getFontAttrs() { return this.fontAttrs; }
/* 123 */   public float getAscent() { return this.ascent; } public float getDescent() {
/* 124 */     return this.descent;
/*     */   } public boolean isFlowRegionBreak() {
/* 126 */     return this.flowRegionBreak;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/flow/BlockInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */