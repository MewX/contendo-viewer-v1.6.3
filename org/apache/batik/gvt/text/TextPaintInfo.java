/*     */ package org.apache.batik.gvt.text;
/*     */ 
/*     */ import java.awt.Composite;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextPaintInfo
/*     */ {
/*     */   public boolean visible;
/*     */   public Paint fillPaint;
/*     */   public Paint strokePaint;
/*     */   public Stroke strokeStroke;
/*     */   public Composite composite;
/*     */   public Paint underlinePaint;
/*     */   public Paint underlineStrokePaint;
/*     */   public Stroke underlineStroke;
/*     */   public Paint overlinePaint;
/*     */   public Paint overlineStrokePaint;
/*     */   public Stroke overlineStroke;
/*     */   public Paint strikethroughPaint;
/*     */   public Paint strikethroughStrokePaint;
/*     */   public Stroke strikethroughStroke;
/*     */   public int startChar;
/*     */   public int endChar;
/*     */   
/*     */   public TextPaintInfo() {}
/*     */   
/*     */   public TextPaintInfo(TextPaintInfo pi) {
/*  57 */     set(pi);
/*     */   }
/*     */   
/*     */   public void set(TextPaintInfo pi) {
/*  61 */     if (pi == null) {
/*  62 */       this.fillPaint = null;
/*  63 */       this.strokePaint = null;
/*  64 */       this.strokeStroke = null;
/*  65 */       this.composite = null;
/*     */       
/*  67 */       this.underlinePaint = null;
/*  68 */       this.underlineStrokePaint = null;
/*  69 */       this.underlineStroke = null;
/*     */       
/*  71 */       this.overlinePaint = null;
/*  72 */       this.overlineStrokePaint = null;
/*  73 */       this.overlineStroke = null;
/*     */       
/*  75 */       this.strikethroughPaint = null;
/*  76 */       this.strikethroughStrokePaint = null;
/*  77 */       this.strikethroughStroke = null;
/*     */       
/*  79 */       this.visible = false;
/*     */     } else {
/*  81 */       this.fillPaint = pi.fillPaint;
/*  82 */       this.strokePaint = pi.strokePaint;
/*  83 */       this.strokeStroke = pi.strokeStroke;
/*  84 */       this.composite = pi.composite;
/*     */       
/*  86 */       this.underlinePaint = pi.underlinePaint;
/*  87 */       this.underlineStrokePaint = pi.underlineStrokePaint;
/*  88 */       this.underlineStroke = pi.underlineStroke;
/*     */       
/*  90 */       this.overlinePaint = pi.overlinePaint;
/*  91 */       this.overlineStrokePaint = pi.overlineStrokePaint;
/*  92 */       this.overlineStroke = pi.overlineStroke;
/*     */       
/*  94 */       this.strikethroughPaint = pi.strikethroughPaint;
/*  95 */       this.strikethroughStrokePaint = pi.strikethroughStrokePaint;
/*  96 */       this.strikethroughStroke = pi.strikethroughStroke;
/*     */       
/*  98 */       this.visible = pi.visible;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean equivilent(TextPaintInfo tpi1, TextPaintInfo tpi2) {
/* 103 */     if (tpi1 == null) {
/* 104 */       if (tpi2 == null) return true; 
/* 105 */       return false;
/* 106 */     }  if (tpi2 == null) return false;
/*     */     
/* 108 */     if (((tpi1.fillPaint == null) ? true : false) != ((tpi2.fillPaint == null) ? true : false)) {
/* 109 */       return false;
/*     */     }
/* 111 */     if (tpi1.visible != tpi2.visible) return false;
/*     */     
/* 113 */     boolean tpi1Stroke = (tpi1.strokePaint != null && tpi1.strokeStroke != null);
/*     */ 
/*     */     
/* 116 */     boolean tpi2Stroke = (tpi2.strokePaint != null && tpi2.strokeStroke != null);
/*     */ 
/*     */     
/* 119 */     return (tpi1Stroke == tpi2Stroke);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/text/TextPaintInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */