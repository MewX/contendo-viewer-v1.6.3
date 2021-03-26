/*     */ package net.a.a.e.c.c;
/*     */ 
/*     */ import java.awt.geom.Dimension2D;
/*     */ import net.a.a.c;
/*     */ import net.a.a.e.d;
/*     */ import net.a.a.e.d.b;
/*     */ import net.a.a.e.d.c;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import org.w3c.dom.Node;
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
/*     */ public final class m
/*     */ {
/*     */   static class a
/*     */   {
/*     */     private float a;
/*     */     private float b;
/*     */     
/*     */     protected a(float param1Float1, float param1Float2) {
/*  64 */       this.a = param1Float2;
/*  65 */       this.b = param1Float1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float a() {
/*  74 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float b() {
/*  83 */       return this.b;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(a param1a) {
/*  94 */       this.b = Math.max(this.b, param1a.b);
/*  95 */       this.a = Math.max(this.a, param1a.a);
/*     */     }
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
/*     */   static void a(g paramg, d paramd, f paramf, c paramc, d paramd1, d paramd2, d paramd3, d paramd4, String paramString1, String paramString2) {
/* 108 */     d d1 = paramg.a((i)paramd2);
/* 109 */     float f1 = d1.d(paramf);
/*     */     
/* 111 */     d d2 = paramg.a((i)paramd3);
/* 112 */     d d3 = paramg.a((i)paramd4);
/*     */     
/* 114 */     a a = a(paramf, paramc, paramString1, paramString2, d1, d2, d3);
/*     */ 
/*     */ 
/*     */     
/* 118 */     if (d2 != null) {
/* 119 */       d2.a(f1, a.b(), paramf);
/*     */     }
/* 121 */     if (d3 != null) {
/* 122 */       d3.a(f1, -a.a(), paramf);
/*     */     }
/*     */     
/* 125 */     b b1 = new b(0.0F, 0.0F);
/* 126 */     b b2 = new b(0.0F, 0.0F);
/* 127 */     c.a(paramg, paramd, (Node)paramd1, paramf, (Dimension2D)b1, (Dimension2D)b2);
/*     */     
/* 129 */     paramd.c(d1.d());
/* 130 */     paramd.b(d1.c());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static a a(f paramf, c paramc, String paramString1, String paramString2, d paramd1, d paramd2, d paramd3) {
/* 137 */     float f1 = 0.0F;
/* 138 */     float f2 = 0.0F;
/* 139 */     if (paramd2 != null) {
/* 140 */       f1 = Math.max(paramd1.c(paramf) + (paramd2
/* 141 */           .b(paramf) - paramd2
/* 142 */           .c(paramf)) / 2.0F, 
/* 143 */           net.a.a.e.d.a.a.a(paramString1, paramc, "pt"));
/*     */     }
/* 145 */     if (paramd3 != null) {
/* 146 */       f2 = Math.max(paramd1.b(paramf) - (paramd3
/* 147 */           .b(paramf) - paramd3
/* 148 */           .c(paramf)) / 2.0F, 
/* 149 */           net.a.a.e.d.a.a.a(paramString2, paramc, "pt"));
/*     */     }
/*     */     
/* 152 */     if (paramd2 != null && paramd3 != null) {
/* 153 */       float f3 = -f1 + paramd2.b(paramf) + 1.0F;
/*     */ 
/*     */       
/* 156 */       float f4 = f2 - paramd3.c(paramf) - 1.0F;
/*     */       
/* 158 */       float f5 = Math.max(0.0F, f3 - f4);
/* 159 */       float f6 = f5 / 2.0F;
/*     */       
/* 161 */       f2 += f6;
/* 162 */       f1 += f6;
/*     */     } 
/* 164 */     return new a(f1, f2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */