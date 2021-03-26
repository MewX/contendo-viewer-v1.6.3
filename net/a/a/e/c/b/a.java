/*     */ package net.a.a.e.c.b;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.List;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.e.c;
/*     */ import net.a.a.e.d.d;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import net.a.a.g.j;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.mathml.MathMLRadicalElement;
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
/*     */ public abstract class a
/*     */   extends c
/*     */   implements MathMLRadicalElement
/*     */ {
/*     */   private static final String r = "0.1ex";
/*     */   private static final String s = "0.5em";
/*     */   
/*     */   public a(String paramString, AbstractDocument paramAbstractDocument) {
/*  62 */     super(paramString, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract List<i> a();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(g paramg, d paramd, f paramf, c paramc) {
/*     */     float f6;
/*  82 */     Graphics2D graphics2D = paramg.d();
/*  83 */     c c1 = b(paramc);
/*  84 */     float f1 = b(graphics2D, paramc);
/*  85 */     float f2 = d.b(c1);
/*  86 */     float f3 = net.a.a.e.d.a.a.a("0.1ex", c1, "");
/*     */     
/*  88 */     float f4 = net.a.a.e.d.a.a.a("0.5em", paramc, "");
/*     */     
/*  90 */     Color color = (Color)c1.a(d.i);
/*  91 */     float f5 = f2;
/*  92 */     i i = (i)getIndex();
/*  93 */     List<j> list = paramd.e();
/*  94 */     list.clear();
/*     */ 
/*     */ 
/*     */     
/*  98 */     if (i == null) {
/*  99 */       f6 = 0.0F;
/*     */     } else {
/* 101 */       d d1 = paramg.a(i);
/*     */       
/* 103 */       float f11 = f1 + f2 / 2.0F + f3 + d1.c(paramf);
/* 104 */       d1.a(f5, -f11, paramf);
/* 105 */       f5 += d1.d(paramf);
/* 106 */       list.add(new j(f2, -f1, f5, -f1, f2, color));
/*     */       
/* 108 */       f6 = f11 + d1.b(paramf);
/*     */     } 
/*     */ 
/*     */     
/* 112 */     f5 += f4;
/*     */ 
/*     */     
/* 115 */     float f7 = f5;
/*     */     
/* 117 */     FontMetrics fontMetrics = a(paramg.d(), c1);
/* 118 */     float f8 = fontMetrics.getAscent();
/* 119 */     float f9 = fontMetrics.getDescent();
/* 120 */     for (i i1 : a()) {
/* 121 */       d d1 = paramg.a(i1);
/* 122 */       d1.a(f5, 0.0F, paramf);
/* 123 */       f8 = Math.max(f8, d1.b(paramf));
/*     */       
/* 125 */       f9 = Math.max(f9, d1.c(paramf));
/* 126 */       f5 += d1.d(paramf);
/*     */     } 
/* 128 */     f5 += 2.0F * f3;
/* 129 */     float f10 = f8 + 2.0F * f3 + f2 / 2.0F;
/*     */ 
/*     */ 
/*     */     
/* 133 */     paramd.c(Math.max(f10 + f2 / 2.0F, f6), paramf);
/*     */     
/* 135 */     paramd.d(f9 + f2 / 2.0F, paramf);
/* 136 */     paramd.a(f5 / 2.0F, paramf);
/* 137 */     paramd.e(f5 + f2, paramf);
/* 138 */     paramd.c(f8);
/* 139 */     paramd.b(f9);
/*     */ 
/*     */     
/* 142 */     list.add(new j(f7 - f4, -f1, f7 - f4 / 2.0F, f9, f2, color));
/*     */ 
/*     */     
/* 145 */     list.add(new j(f7 - f4 / 2.0F, f9, f7, -f10, f2, color));
/*     */     
/* 147 */     list.add(new j(f7, -f10, f5, -f10, f2, color));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */