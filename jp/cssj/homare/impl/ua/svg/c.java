/*     */ package jp.cssj.homare.impl.ua.svg;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphJustificationInfo;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import org.apache.batik.gvt.font.GVTFont;
/*     */ import org.apache.batik.gvt.font.GVTGlyphMetrics;
/*     */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*     */ 
/*     */ public class c
/*     */   implements GVTGlyphVector
/*     */ {
/*     */   protected final h a;
/*     */   protected final GVTFont b;
/*     */   protected float[] c;
/*     */   protected float[] d;
/*     */   
/*     */   public c(h text, GVTFont font) {
/*  25 */     this.a = text;
/*  26 */     this.b = font;
/*     */     
/*  28 */     this.c = new float[text.l() + 1];
/*  29 */     this.d = new float[text.l() + 1];
/*     */   }
/*     */   
/*     */   public GVTFont getFont() {
/*  33 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void performDefaultLayout() {}
/*     */ 
/*     */   
/*     */   public float[] getGlyphPositions(int begin, int num, float[] ret) {
/*  41 */     if (ret == null) {
/*  42 */       ret = new float[num * 2];
/*     */     }
/*  44 */     for (int i = 0; i < num; i++) {
/*  45 */       ret[i * 2] = this.c[i + begin];
/*  46 */       ret[i * 2 + 1] = this.d[i + begin];
/*     */     } 
/*  48 */     return ret;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(AttributedCharacterIterator aci) {
/*  52 */     return getLogicalBounds();
/*     */   }
/*     */   
/*     */   public Rectangle2D getLogicalBounds() {
/*  56 */     double advance = this.a.c();
/*  57 */     double size = this.a.b().e();
/*  58 */     return new Rectangle2D.Double(this.c[0], this.d[0], advance, size);
/*     */   }
/*     */   
/*     */   public int getGlyphCode(int ix) {
/*  62 */     return this.a.j()[ix];
/*     */   }
/*     */   
/*     */   public int getNumGlyphs() {
/*  66 */     return this.a.l();
/*     */   }
/*     */   
/*     */   public int getCharacterCount(int start, int end) {
/*  70 */     int numChars = 0;
/*  71 */     if (start < 0) {
/*  72 */       start = 0;
/*     */     }
/*  74 */     if (end > this.a.l() - 1) {
/*  75 */       end = this.a.l() - 1;
/*     */     }
/*  77 */     byte[] clens = this.a.k();
/*  78 */     for (int i = start; i <= end; i++) {
/*  79 */       numChars += clens[i];
/*     */     }
/*  81 */     return numChars;
/*     */   }
/*     */   
/*     */   public GVTGlyphMetrics getGlyphMetrics(int ix) {
/*  85 */     float hadvance = (float)this.a.c() / getNumGlyphs();
/*  86 */     float vadvance = (float)this.a.b().e();
/*  87 */     return new GVTGlyphMetrics(hadvance, vadvance, (Rectangle2D)getGlyphLogicalBounds(ix), (byte)0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Shape getGlyphVisualBounds(int ix) {
/*  92 */     return getGlyphLogicalBounds(ix);
/*     */   }
/*     */   
/*     */   public Shape getGlyphLogicalBounds(int ix) {
/*  96 */     float hadvance = (float)this.a.c() / getNumGlyphs();
/*  97 */     float vadvance = (float)this.a.b().e();
/*  98 */     return new Rectangle2D.Float(0.0F, 0.0F, hadvance, vadvance);
/*     */   }
/*     */   
/*     */   public void setGlyphPosition(int ix, Point2D pos) {
/* 102 */     this.c[ix] = (float)pos.getX();
/* 103 */     this.d[ix] = (float)pos.getY();
/*     */   }
/*     */   
/*     */   public Point2D getGlyphPosition(int ix) {
/* 107 */     return new Point2D.Float(this.c[ix], this.d[ix]);
/*     */   }
/*     */   
/*     */   public Rectangle2D getGeometricBounds() {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Rectangle2D getGlyphCellBounds(int ix) {
/* 115 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int[] getGlyphCodes(int begin, int num, int[] ret) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public GlyphJustificationInfo getGlyphJustificationInfo(int ix) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2d, AttributedCharacterIterator aci) {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public FontRenderContext getFontRenderContext() {
/* 131 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Shape getGlyphOutline(int ix) {
/* 135 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public AffineTransform getGlyphTransform(int ix) {
/* 139 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Shape getOutline() {
/* 143 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Shape getOutline(float x, float y) {
/* 147 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean isGlyphVisible(int ix) {
/* 151 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void setGlyphTransform(int ix, AffineTransform t) {
/* 155 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void setGlyphVisible(int ix, boolean v) {
/* 159 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReversed() {
/* 164 */     return false;
/*     */   }
/*     */   
/*     */   public void maybeReverse(boolean mirror) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */