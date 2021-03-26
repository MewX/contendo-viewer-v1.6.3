/*     */ package jp.cssj.sakae.pdf.c.a;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import jp.cssj.sakae.a.a;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.c.a.j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class i
/*     */   extends a
/*     */   implements b
/*     */ {
/*     */   private static final long l = 1L;
/*  24 */   private static final char[] m = new char[] { 'x' };
/*     */   
/*  26 */   private static final char[] n = new char[] { 'H' };
/*     */   
/*  28 */   private static final char[] o = new char[] { ' ' };
/*     */   
/*     */   protected final Font i;
/*     */   
/*     */   protected String j;
/*     */   
/*  34 */   private b p = null;
/*     */   
/*     */   private short q;
/*     */   private short r;
/*  38 */   protected j k = null; private short s; private short t; private short u;
/*     */   
/*     */   public i(Font awtFont) {
/*  41 */     this.i = awtFont = awtFont.deriveFont(1000.0F);
/*  42 */     this.j = awtFont.getFontName();
/*  43 */     this.X_ = new String[] { awtFont.getFamily(), awtFont.getPSName() };
/*  44 */     a(awtFont.isItalic());
/*  45 */     Float weight = (Float)awtFont.getAttributes().get(TextAttribute.WEIGHT);
/*     */     
/*  47 */     if (weight != null) {
/*  48 */       rweight = Math.max(weight.doubleValue(), TextAttribute.WEIGHT_EXTRA_LIGHT.doubleValue());
/*     */     } else {
/*  50 */       rweight = TextAttribute.WEIGHT_MEDIUM.doubleValue();
/*     */     } 
/*  52 */     double rweight = Math.min(rweight, TextAttribute.WEIGHT_ULTRABOLD.doubleValue());
/*     */     
/*  54 */     rweight = (rweight - TextAttribute.WEIGHT_EXTRA_LIGHT.doubleValue()) / (TextAttribute.WEIGHT_ULTRABOLD.doubleValue() - TextAttribute.WEIGHT_EXTRA_LIGHT.doubleValue());
/*  55 */     if (rweight <= 0.1D) {
/*  56 */       a((short)100);
/*  57 */     } else if (rweight <= 0.2D) {
/*  58 */       a((short)200);
/*  59 */     } else if (rweight <= 0.3D) {
/*  60 */       a((short)300);
/*  61 */     } else if (rweight <= 0.4D) {
/*  62 */       a((short)400);
/*  63 */     } else if (rweight <= 0.5D) {
/*  64 */       a((short)500);
/*  65 */     } else if (rweight <= 0.6D) {
/*  66 */       a((short)600);
/*  67 */     } else if (rweight <= 0.7D) {
/*  68 */       a((short)700);
/*  69 */     } else if (rweight <= 0.8D) {
/*  70 */       a((short)800);
/*     */     } else {
/*  72 */       a((short)900);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte e() {
/*  77 */     return 1;
/*     */   }
/*     */   
/*     */   public Font q() {
/*  81 */     return this.i;
/*     */   }
/*     */   
/*     */   public void a(String fontName) {
/*  85 */     this.j = fontName;
/*     */   }
/*     */   
/*     */   public j p() {
/*  89 */     return this.k;
/*     */   }
/*     */   
/*     */   public void a(j panose) {
/*  93 */     this.k = panose;
/*     */   }
/*     */   
/*     */   public FontRenderContext r() {
/*  97 */     return j.a;
/*     */   }
/*     */   
/*     */   private void s() {
/* 101 */     if (this.p != null) {
/*     */       return;
/*     */     }
/* 104 */     if (this.i.canDisplay('H') || this.i.canDisplay('x')) {
/* 105 */       LineMetrics lm = this.i.getLineMetrics("Hx", r());
/* 106 */       this.q = (short)(int)lm.getAscent();
/* 107 */       this.r = (short)(int)lm.getDescent();
/*     */     } else {
/* 109 */       this.q = 860;
/* 110 */       this.r = 140;
/*     */     } 
/*     */     
/* 113 */     if (this.i.canDisplay('x')) {
/* 114 */       GlyphVector glyphVector = this.i.createGlyphVector(r(), m);
/* 115 */       this.s = (short)(int)glyphVector.getVisualBounds().getHeight();
/*     */     } else {
/* 117 */       this.s = 500;
/*     */     } 
/* 119 */     if (this.i.canDisplay('H')) {
/* 120 */       GlyphVector glyphVector = this.i.createGlyphVector(r(), n);
/* 121 */       this.t = (short)(int)glyphVector.getVisualBounds().getHeight();
/*     */     } else {
/* 123 */       this.t = 700;
/*     */     } 
/* 125 */     GlyphVector gv = this.i.createGlyphVector(r(), o);
/* 126 */     this.u = (short)(int)gv.getGlyphMetrics(0).getAdvance();
/*     */     
/* 128 */     Rectangle2D bounds = this.i.getMaxCharBounds(r());
/* 129 */     this
/* 130 */       .p = new b((short)(int)bounds.getX(), (short)(int)-bounds.getMaxY(), (short)(int)bounds.getMaxX(), (short)(int)-bounds.getY());
/*     */   }
/*     */   
/*     */   public synchronized b f() {
/* 134 */     s();
/* 135 */     return this.p;
/*     */   }
/*     */   
/*     */   public String d() {
/* 139 */     return this.j;
/*     */   }
/*     */   
/*     */   public synchronized short l() {
/* 143 */     s();
/* 144 */     return this.s;
/*     */   }
/*     */   
/*     */   public synchronized short m() {
/* 148 */     s();
/* 149 */     return this.u;
/*     */   }
/*     */   
/*     */   public synchronized short h() {
/* 153 */     s();
/* 154 */     return this.t;
/*     */   }
/*     */   
/*     */   public synchronized short g() {
/* 158 */     s();
/* 159 */     return this.q;
/*     */   }
/*     */   
/*     */   public synchronized short i() {
/* 163 */     s();
/* 164 */     return this.r;
/*     */   }
/*     */   
/*     */   public short j() {
/* 168 */     return 0;
/*     */   }
/*     */   
/*     */   public short k() {
/* 172 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean a(int c) {
/* 176 */     return this.i.canDisplay((char)c);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */