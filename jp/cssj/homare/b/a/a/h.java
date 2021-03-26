/*     */ package jp.cssj.homare.b.a.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.f;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.k;
/*     */ import jp.cssj.homare.b.b.d;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.f.e;
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
/*     */ public class h
/*     */ {
/*     */   public static class a
/*     */     extends c
/*     */   {
/*     */     public final k b;
/*     */     public final double c;
/*     */     public final double d;
/*     */     
/*     */     public a(int serial, k box, double lineAxis, double pageAxis) {
/*  39 */       super(serial);
/*  40 */       this.b = box;
/*  41 */       this.c = lineAxis;
/*  42 */       this.d = pageAxis;
/*     */     }
/*     */     
/*     */     public j a() {
/*  46 */       return (j)this.b; } public void a(jp.cssj.homare.b.b.a.a builder) {
/*     */       c c1;
/*     */       f floatBox;
/*     */       jp.cssj.homare.b.b.a.a floatBindBuilder;
/*  50 */       switch (this.b.a()) {
/*     */ 
/*     */         
/*     */         case 5:
/*  54 */           c1 = (c)this.b;
/*  55 */           floatBindBuilder = new jp.cssj.homare.b.b.a.a((d)builder, c1);
/*  56 */           c1.a(floatBindBuilder, 0);
/*  57 */           floatBindBuilder.x();
/*  58 */           builder.a((j)c1);
/*     */           return;
/*     */ 
/*     */         
/*     */         case 6:
/*  63 */           floatBox = (f)this.b;
/*  64 */           builder.a((j)floatBox);
/*     */           return;
/*     */       } 
/*     */       
/*  68 */       throw new IllegalStateException(this.b.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private final List<a> b = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(a floating) {
/*  84 */     if (!a && e.a(floating.d)) throw new AssertionError("Undefined pageAxis"); 
/*  85 */     if (!a && e.a(floating.c)) throw new AssertionError("Undefined lineAxis"); 
/*  86 */     this.b.add(floating);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(c box, n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/*  91 */     if (!a && e.a(x)) throw new AssertionError("Undefined x"); 
/*  92 */     if (!a && e.a(y)) throw new AssertionError("Undefined y");
/*     */     
/*  94 */     boolean vertical = e.a((box.c_()).D);
/*  95 */     if (vertical) {
/*  96 */       x += box.s();
/*     */     }
/*  98 */     for (int i = 0; i < this.b.size(); i++) {
/*  99 */       double xx, yy; a floating = this.b.get(i);
/*     */ 
/*     */       
/* 102 */       if (vertical) {
/*     */         
/* 104 */         xx = x - floating.d - floating.b.p();
/* 105 */         yy = y + floating.c;
/*     */       } else {
/*     */         
/* 108 */         xx = x + floating.c;
/* 109 */         yy = y + floating.d;
/*     */       } 
/* 111 */       floating.b.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yy);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int a() {
/* 116 */     return this.b.size();
/*     */   }
/*     */   
/*     */   public a a(int i) {
/* 120 */     return this.b.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h a(c box, double pageLimit, byte flags) {
/* 127 */     if (!a && this.b.isEmpty()) throw new AssertionError(); 
/* 128 */     boolean vertical = e.a((box.c_()).D);
/* 129 */     h nextFloatings = this;
/*     */     
/* 131 */     for (int i = 0; i < this.b.size(); i++) {
/* 132 */       k nextBox; a floating = this.b.get(i);
/* 133 */       double pageEnd = floating.d;
/* 134 */       if (vertical) {
/* 135 */         pageEnd += floating.b.p();
/*     */       } else {
/* 137 */         pageEnd += floating.b.q();
/*     */       } 
/*     */       
/* 140 */       boolean first = ((flags & 0x1) != 0 && e.a(floating.d, 0.0D) <= 0);
/*     */       
/* 142 */       if (e.a(pageEnd, pageLimit) <= 0) {
/*     */         
/* 144 */         nextBox = null;
/* 145 */       } else if (!first && e.a(pageLimit, floating.d) < 0) {
/* 146 */         nextBox = floating.b;
/*     */       } else {
/* 148 */         c containerBox; i params; switch (floating.b.a()) {
/*     */ 
/*     */           
/*     */           case 5:
/* 152 */             containerBox = (c)floating.b;
/* 153 */             params = containerBox.c_();
/* 154 */             if (params.U != 1 && vertical == 
/* 155 */               e.a(params.D)) {
/* 156 */               byte xflags = first ? 1 : 4;
/* 157 */               double pageAxis = pageLimit - floating.d;
/* 158 */               k k = (k)containerBox.b(pageAxis, d.c, xflags);
/*     */               break;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 6:
/* 166 */             nextBox = first ? null : floating.b;
/*     */             break;
/*     */           
/*     */           default:
/* 170 */             throw new IllegalStateException(floating.b.toString());
/*     */         } 
/*     */       } 
/* 173 */       if (nextFloatings == this) {
/* 174 */         if (nextBox == floating.b) {
/*     */           continue;
/*     */         }
/* 177 */         if (i > 0 || nextBox != null) {
/* 178 */           nextFloatings = new h();
/* 179 */           for (int j = 0; j < i; j++) {
/* 180 */             nextFloatings.b.add(this.b.remove(j));
/* 181 */             j--;
/* 182 */             i--;
/*     */           } 
/*     */         } else {
/* 185 */           nextFloatings = null;
/*     */         } 
/*     */       } 
/* 188 */       if (nextBox != null) {
/*     */ 
/*     */         
/* 191 */         if (nextFloatings == null) {
/* 192 */           nextFloatings = new h();
/*     */         }
/* 194 */         if (nextBox == floating.b) {
/* 195 */           this.b.remove(i);
/* 196 */           i--;
/*     */         } else {
/* 198 */           floating = new a(floating.a, nextBox, 0.0D, 0.0D);
/*     */         } 
/* 200 */         nextFloatings.b.add(floating);
/*     */       }  continue;
/* 202 */     }  if (!a && nextFloatings != null && nextFloatings.b.isEmpty()) throw new AssertionError(); 
/* 203 */     return nextFloatings;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 207 */     return super.toString() + ": floatings.size=" + this.b.size();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */