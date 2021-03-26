/*     */ package jp.cssj.homare.b.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import jp.cssj.homare.b.a.a.k;
/*     */ import jp.cssj.homare.b.a.b.k;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.c.c;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.v;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.g.a;
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
/*     */ public abstract class e
/*     */   extends h
/*     */ {
/*  33 */   protected double a = 0.0D;
/*     */   
/*     */   protected boolean b = false;
/*     */   
/*     */   private static final boolean o = false;
/*     */ 
/*     */   
/*     */   public abstract c c();
/*     */   
/*     */   public byte a() {
/*  43 */     return 3;
/*     */   }
/*     */   
/*     */   public z b_() {
/*  47 */     return (z)v.a;
/*     */   }
/*     */   
/*     */   public boolean f() {
/*  51 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(double ascent, double descent) {
/*  56 */     if (ascent > this.k) {
/*  57 */       this.k = ascent;
/*     */     }
/*  59 */     if (descent > this.l) {
/*  60 */       this.l = descent;
/*     */     }
/*  62 */     if (!c && jp.cssj.homare.b.f.e.a(this.k + this.l)) throw new AssertionError();
/*     */   
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
/*     */   public void a(double textIndent, double offset, double maxLineAxis, boolean last) {
/*  79 */     if (!c && (this.j == null || this.j.d())) throw new AssertionError(); 
/*  80 */     this.b = last;
/*  81 */     c params = c();
/*  82 */     double lineWidth = this.m + textIndent;
/*  83 */     textIndent += offset;
/*  84 */     byte textAlign = params.f; while (true)
/*     */     { double remainderAdvance, fontSize; int count; double letterSpacing;
/*  86 */       switch (textAlign) {
/*     */         
/*     */         case 2:
/*  89 */           this.a = (maxLineAxis - lineWidth) / 2.0D + textIndent;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/*  94 */           this.a = maxLineAxis - lineWidth + textIndent;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/*  99 */           if (last) {
/*     */             
/* 101 */             if (params.g != 1) {
/* 102 */               textAlign = params.g;
/* 103 */               last = false;
/*     */               
/*     */               continue;
/*     */             } 
/*     */           } else {
/* 108 */             double d = maxLineAxis - lineWidth;
/* 109 */             if (d > 0.0D) {
/* 110 */               int i = a(new k());
/* 111 */               if (i > 0) {
/* 112 */                 double d1 = d / i;
/* 113 */                 if (d1 != 0.0D) {
/* 114 */                   a(d1, new k());
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/* 119 */           this.a = textIndent;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 124 */           this.a = textIndent;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 101:
/* 129 */           remainderAdvance = maxLineAxis - lineWidth;
/* 130 */           if (remainderAdvance <= 0.0D) {
/* 131 */             this.a = (maxLineAxis - lineWidth) / 2.0D + textIndent;
/*     */             break;
/*     */           } 
/* 134 */           fontSize = (g()).C.e();
/* 135 */           if (remainderAdvance <= fontSize) {
/* 136 */             this.a = (maxLineAxis - lineWidth) / 2.0D + textIndent;
/*     */             
/*     */             break;
/*     */           } 
/* 140 */           count = a(new k());
/* 141 */           if (count <= 0) {
/* 142 */             this.a = (maxLineAxis - lineWidth) / 2.0D + textIndent;
/*     */             break;
/*     */           } 
/* 145 */           letterSpacing = (remainderAdvance - fontSize) / count;
/* 146 */           a(letterSpacing, new k());
/* 147 */           this.a = textIndent + fontSize / 2.0D;
/*     */           break;
/*     */         
/*     */         default:
/* 151 */           throw new IllegalStateException();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 156 */       a(this, 0.0D); return; }  a(this, 0.0D);
/*     */   }
/*     */   
/*     */   public k a(i params) {
/* 160 */     k newLine = new k(params);
/* 161 */     return newLine;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 166 */     switch ((c()).D) {
/*     */       
/*     */       case 1:
/* 169 */         x += this.a;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 175 */         y += this.a;
/*     */         break;
/*     */       
/*     */       default:
/* 179 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 182 */     visitor.a(transform, this, x, y);
/* 183 */     super.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */