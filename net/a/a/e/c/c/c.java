/*     */ package net.a.a.e.c.c;
/*     */ 
/*     */ import java.awt.geom.Dimension2D;
/*     */ import net.a.a.c.a;
/*     */ import net.a.a.c.b;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.c.e;
/*     */ import net.a.a.e.c;
/*     */ import net.a.a.e.d;
/*     */ import net.a.a.e.d.a.a;
/*     */ import net.a.a.e.d.b;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLElement;
/*     */ import org.w3c.dom.mathml.MathMLOperatorElement;
/*     */ import org.w3c.dom.mathml.MathMLUnderOverElement;
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
/*     */ 
/*     */ 
/*     */ public abstract class c
/*     */   extends c
/*     */   implements MathMLUnderOverElement
/*     */ {
/*     */   public static final String r = "0.1ex";
/*     */   public static final float s = 2.5F;
/*     */   public static final String t = "accent";
/*     */   public static final String u = "accentunder";
/*     */   private static final long v = 1L;
/*     */   
/*     */   public c(String paramString, AbstractDocument paramAbstractDocument) {
/*  79 */     super(paramString, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAccent() {
/*  85 */     return a("accent");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a() {
/*  94 */     return Boolean.parseBoolean(getAccent());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean c(net.a.a.c paramc) {
/* 103 */     return (!a() && 
/* 104 */       j() instanceof MathMLOperatorElement && 
/*     */       
/* 106 */       Boolean.parseBoolean(((MathMLOperatorElement)j())
/* 107 */         .getMovablelimits()) && a.b
/* 108 */       .equals(paramc.a(d.a)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAccentunder() {
/* 114 */     return a("accentunder");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public net.a.a.c a(int paramInt, net.a.a.c paramc) {
/* 121 */     net.a.a.c c1 = b(paramc);
/* 122 */     if (paramInt == 0) {
/* 123 */       return c1;
/*     */     }
/*     */     
/* 126 */     return (net.a.a.c)new e((net.a.a.c)new b(c1), 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean i() {
/* 137 */     return Boolean.parseBoolean(getAccentunder());
/*     */   }
/*     */ 
/*     */   
/*     */   public d j() {
/* 142 */     return a(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAccent(String paramString) {
/* 153 */     setAttribute("accent", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAccentunder(String paramString) {
/* 158 */     setAttribute("accentunder", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBase(MathMLElement paramMathMLElement) {
/* 163 */     a(0, paramMathMLElement);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(d paramd, net.a.a.c paramc) {
/* 170 */     return (c(paramc) && paramd.isSameNode((Node)j()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(g paramg, d paramd, f paramf, net.a.a.c paramc) {
/* 178 */     net.a.a.c c1 = b(paramc);
/* 179 */     if (c(c1)) {
/* 180 */       m.a(paramg, paramd, paramf, c1, (d)this, j(), 
/* 181 */           l(), k(), null, null);
/*     */     } else {
/* 183 */       c(paramg, paramd, paramf, c1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(g paramg, d paramd, f paramf, net.a.a.c paramc) {
/*     */     d d5, d6;
/* 190 */     d d1 = j();
/* 191 */     d d2 = l();
/* 192 */     d d3 = k();
/*     */     
/* 194 */     d d4 = paramg.a((i)d1);
/*     */ 
/*     */ 
/*     */     
/* 198 */     float f1 = d4.d(paramf);
/*     */     
/* 200 */     float f2 = a.a("0.1ex", paramc, "pt");
/*     */ 
/*     */     
/* 203 */     if (d2 == null) {
/* 204 */       d5 = null;
/*     */     } else {
/* 206 */       d5 = paramg.a((i)d2);
/* 207 */       f1 = Math.max(f1, d5.d(paramf));
/*     */     } 
/* 209 */     if (d3 == null) {
/* 210 */       d6 = null;
/*     */     } else {
/* 212 */       d6 = paramg.a((i)d3);
/* 213 */       f1 = Math.max(f1, d6.d(paramf));
/*     */     } 
/* 215 */     float f3 = f1 / 2.0F;
/*     */     
/* 217 */     d4.a(f3 - d4.e(paramf), 0.0F, paramf);
/*     */ 
/*     */     
/* 220 */     if (d2 != null) {
/* 221 */       a(paramf, d4, d5, f2, f3);
/*     */     }
/* 223 */     if (d3 != null) {
/* 224 */       b(paramf, d4, d6, f2, f3);
/*     */     }
/*     */     
/* 227 */     b b1 = new b(0.0F, 0.0F);
/* 228 */     b b2 = new b(0.0F, 0.0F);
/* 229 */     net.a.a.e.d.c.a(paramg, paramd, (Node)this, paramf, (Dimension2D)b1, (Dimension2D)b2);
/*     */     
/* 231 */     paramd.a(f1);
/* 232 */     paramd.c(d4.d());
/* 233 */     paramd.b(d4.c());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(f paramf, d paramd1, d paramd2, float paramFloat1, float paramFloat2) {
/*     */     float f1;
/* 240 */     if (i()) {
/* 241 */       f1 = paramFloat1;
/*     */     } else {
/* 243 */       f1 = paramFloat1 * 2.5F;
/*     */     } 
/*     */     
/* 246 */     float f2 = paramd1.c(paramf) + paramd2.b(paramf) + f1;
/* 247 */     paramd2.a(paramFloat2 - paramd2.e(paramf), f2, paramf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(f paramf, d paramd1, d paramd2, float paramFloat1, float paramFloat2) {
/*     */     float f1;
/* 255 */     if (a()) {
/* 256 */       f1 = paramFloat1;
/*     */     } else {
/* 258 */       f1 = paramFloat1 * 2.5F;
/*     */     } 
/*     */     
/* 261 */     float f2 = paramd1.b(paramf) + paramd2.c(paramf) + f1;
/* 262 */     paramd2.a(paramFloat2 - paramd2.e(paramf), -f2, paramf);
/*     */   }
/*     */   
/*     */   public abstract d k();
/*     */   
/*     */   public abstract d l();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */