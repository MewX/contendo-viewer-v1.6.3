/*     */ package net.a.a.g;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
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
/*     */ public class j
/*     */   implements b
/*     */ {
/*     */   private final float a;
/*     */   private final float b;
/*     */   private final float c;
/*     */   private final float d;
/*     */   private final float e;
/*     */   private final Color f;
/*     */   private final boolean g;
/*     */   
/*     */   public j(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Color paramColor) {
/*  65 */     this.a = paramFloat1;
/*  66 */     this.b = paramFloat2;
/*  67 */     this.c = paramFloat3;
/*  68 */     this.d = paramFloat4;
/*  69 */     this.e = paramFloat5;
/*  70 */     this.f = paramColor;
/*  71 */     this.g = false;
/*     */   }
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
/*     */   public j(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Color paramColor, boolean paramBoolean) {
/*  95 */     this.a = paramFloat1;
/*  96 */     this.b = paramFloat2;
/*  97 */     this.c = paramFloat3;
/*  98 */     this.d = paramFloat4;
/*  99 */     this.e = paramFloat5;
/* 100 */     this.f = paramColor;
/* 101 */     this.g = paramBoolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(float paramFloat1, float paramFloat2, Graphics2D paramGraphics2D) {
/* 106 */     paramGraphics2D.setColor(this.f);
/* 107 */     Stroke stroke = paramGraphics2D.getStroke();
/* 108 */     if (this.g) {
/* 109 */       float f = 3.0F * this.e;
/* 110 */       paramGraphics2D.setStroke(new BasicStroke(this.e, 2, 2, this.e, new float[] { f, f }, 0.0F));
/*     */     }
/*     */     else {
/*     */       
/* 114 */       paramGraphics2D.setStroke(new BasicStroke(this.e));
/*     */     } 
/* 116 */     paramGraphics2D.draw(new Line2D.Float(paramFloat1 + this.a, paramFloat2 + this.b, paramFloat1 + this.c, paramFloat2 + this.d));
/*     */     
/* 118 */     paramGraphics2D.setStroke(stroke);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/g/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */