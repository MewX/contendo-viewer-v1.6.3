/*     */ package net.a.a.e.c.b;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.b;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.e.c;
/*     */ import net.a.a.e.d.a.a;
/*     */ import net.a.a.e.d.a.c;
/*     */ import net.a.a.e.d.b;
/*     */ import net.a.a.e.d.c;
/*     */ import net.a.a.e.d.d;
/*     */ import net.a.a.g.b;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import net.a.a.g.j;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLElement;
/*     */ import org.w3c.dom.mathml.MathMLFractionElement;
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
/*     */ public final class e
/*     */   extends c
/*     */   implements MathMLFractionElement
/*     */ {
/*     */   public static final String r = "mfrac";
/*     */   public static final float s = 0.577F;
/*     */   public static final String t = "linethickness";
/*     */   public static final String u = "beveled";
/*     */   public static final String v = "bevelled";
/*     */   public static final String w = "numalign";
/*     */   public static final String x = "denomalign";
/*     */   private static final String y = "0.1em";
/*     */   private static final long z = 1L;
/*     */   private static final float A = 0.001F;
/*     */   
/*     */   public e(String paramString, AbstractDocument paramAbstractDocument) {
/*  99 */     super(paramString, paramAbstractDocument);
/*     */     
/* 101 */     a("linethickness", "1");
/* 102 */     a("bevelled", Boolean.FALSE
/* 103 */         .toString());
/* 104 */     a("numalign", "center");
/*     */     
/* 106 */     a("denomalign", "center");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 113 */     return (Node)new e(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c a(int paramInt, c paramc) {
/* 120 */     return (c)new b(
/* 121 */         b(paramc), 
/* 122 */         !((Boolean)paramc.a(d.q)).booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinethickness(String paramString) {
/* 132 */     setAttribute("linethickness", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float c(Graphics2D paramGraphics2D, c paramc) {
/*     */     float f;
/* 144 */     String str = getLinethickness();
/*     */     
/*     */     try {
/* 147 */       f = Float.parseFloat(str);
/* 148 */       f *= d.b(paramc);
/* 149 */     } catch (NumberFormatException numberFormatException) {
/* 150 */       f = a.a(str, 
/* 151 */           b(paramc), "pt");
/*     */       
/* 153 */       if (f < 1.0F && f >= 0.001F)
/*     */       {
/* 155 */         f = 1.0F;
/*     */       }
/*     */     } 
/* 158 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBevelled(String paramString) {
/* 168 */     setAttribute("bevelled", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBevelled() {
/* 175 */     String str = a("beveled", false);
/*     */     
/* 177 */     if (str == null) {
/* 178 */       return a("bevelled");
/*     */     }
/* 180 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement getDenominator() {
/* 186 */     return (MathMLElement)a(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLinethickness() {
/* 191 */     return a("linethickness");
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement getNumerator() {
/* 196 */     return (MathMLElement)a(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDenominator(MathMLElement paramMathMLElement) {
/* 201 */     a(1, paramMathMLElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumerator(MathMLElement paramMathMLElement) {
/* 206 */     a(0, paramMathMLElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDenomalign() {
/* 211 */     return a("denomalign");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNumalign() {
/* 216 */     return a("numalign");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDenomalign(String paramString) {
/* 221 */     setAttribute("denomalign", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumalign(String paramString) {
/* 226 */     setAttribute("numalign", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(g paramg, d paramd, f paramf, c paramc) {
/* 234 */     Graphics2D graphics2D = paramg.d();
/*     */     
/* 236 */     float f1 = b(graphics2D, paramc);
/* 237 */     boolean bool = Boolean.parseBoolean(getBevelled());
/* 238 */     float f2 = c(graphics2D, paramc);
/* 239 */     float f3 = a.a("0.1em", 
/*     */         
/* 241 */         b(paramc), "");
/*     */     
/* 243 */     d d1 = paramg.a((i)
/* 244 */         getNumerator());
/* 245 */     d d2 = paramg.a((i)
/* 246 */         getDenominator());
/*     */     
/* 248 */     if (bool) {
/* 249 */       b(paramd, paramf, f1, f2, f3, d1, d2, paramc);
/*     */     } else {
/*     */       
/* 252 */       a(paramd, paramf, f1, f2, f3, d1, d2, paramc);
/*     */     } 
/*     */ 
/*     */     
/* 256 */     b b1 = new b(f3 + f2, 0.0F);
/*     */     
/* 258 */     b b2 = new b(f3 + f2, 0.0F);
/*     */     
/* 260 */     c.a(paramg, paramd, (Node)this, paramf, (Dimension2D)b1, (Dimension2D)b2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(d paramd1, f paramf, float paramFloat1, float paramFloat2, float paramFloat3, d paramd2, d paramd3, c paramc) {
/* 271 */     float f1 = paramd2.d(paramf);
/* 272 */     float f2 = paramd3.d(paramf);
/* 273 */     float f3 = Math.max(f2, f1);
/*     */ 
/*     */     
/* 276 */     float f4 = c.a(getNumalign(), c.b).a(paramf, paramd2, f3);
/*     */     
/* 278 */     float f5 = c.a(getDenomalign(), c.b).a(paramf, paramd3, f3);
/*     */     
/* 280 */     paramd2
/* 281 */       .a(f4 + paramFloat3, 
/*     */ 
/*     */         
/* 284 */         -(paramFloat1 + paramFloat2 / 2.0F + paramFloat3 + paramd2.c(paramf)), paramf);
/*     */     
/* 286 */     paramd3.a(f5 + paramFloat3, -paramFloat1 + paramFloat2 / 2.0F + paramFloat3 + paramd3
/*     */         
/* 288 */         .b(paramf), paramf);
/*     */     
/* 290 */     if (paramFloat2 > 0.001F) {
/*     */ 
/*     */ 
/*     */       
/* 294 */       j j = new j(paramFloat3, -paramFloat1, paramFloat3 + f3, -paramFloat1, paramFloat2, (Color)b(paramc).a(d.i));
/* 295 */       paramd1.a((b)j);
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
/*     */   private void b(d paramd1, f paramf, float paramFloat1, float paramFloat2, float paramFloat3, d paramd2, d paramd3, c paramc) {
/* 307 */     float f1 = -paramFloat1 / 2.0F + paramd2.c(paramf);
/*     */     
/* 309 */     float f2 = paramFloat1 / 2.0F + paramd3.c(paramf);
/*     */     
/* 311 */     float f3 = Math.max(-f1 + paramd2
/* 312 */         .b(paramf), -f2 + paramd3
/* 313 */         .b(paramf));
/* 314 */     float f4 = Math.max(f1 + paramd2
/* 315 */         .c(paramf), f2 + paramd3
/* 316 */         .c(paramf));
/*     */     
/* 318 */     float f5 = f3 + f4;
/* 319 */     float f6 = f5 * 0.577F;
/*     */     
/* 321 */     paramd2.a(paramFloat3, f1, paramf);
/* 322 */     float f7 = paramd2.d(paramf) + paramFloat3;
/* 323 */     if (paramFloat2 > 0.001F) {
/*     */ 
/*     */ 
/*     */       
/* 327 */       j j = new j(f7, f4, f6 + f7, f4 - f5, paramFloat2, (Color)b(paramc).a(d.i));
/* 328 */       paramd1.a((b)j);
/*     */     } 
/* 330 */     f7 += f6;
/* 331 */     paramd3.a(f7, f2, paramf);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */