/*     */ package jp.cssj.homare.impl.ua.svg;
/*     */ 
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.text.CharacterIterator;
/*     */ import java.text.StringCharacterIterator;
/*     */ import jp.cssj.sakae.a.e;
/*     */ import jp.cssj.sakae.a.f;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.c.d.i;
/*     */ import org.apache.batik.gvt.font.GVTFont;
/*     */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*     */ import org.apache.batik.gvt.font.GVTLineMetrics;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   implements GVTFont
/*     */ {
/*     */   protected final f a;
/*     */   protected final h b;
/*     */   protected final e c;
/*     */   protected final g d;
/*     */   final char[] e;
/*     */   
/*     */   public b(f m, h fontStyle) {
/*  90 */     this.e = new char[3]; this.a = (f)m; this.b = fontStyle; this.c = this.a.a(); this.d = this.a.b();
/*     */   }
/*     */   public boolean canDisplay(char c) { return this.d.a(c); }
/*  93 */   public int canDisplayUpTo(CharacterIterator text, int start, int limit) { char c = text.setIndex(start); while (c != Character.MAX_VALUE && text.getIndex() < limit) { if (!this.d.a(c)) return text.getIndex();  c = text.next(); }  return -1; } public int canDisplayUpTo(char[] text, int start, int limit) { return canDisplayUpTo(new StringCharacterIterator(new String(text)), start, limit); } public int canDisplayUpTo(String text) { return canDisplayUpTo(new StringCharacterIterator(text), 0, text.length()); } public GVTGlyphVector createGlyphVector(FontRenderContext frc, CharacterIterator text) { i ti = new i(-1, this.b, (f)this.a);
/*  94 */     char c = text.first();
/*  95 */     int sgid = -1;
/*  96 */     byte clen = 0;
/*  97 */     while (c != Character.MAX_VALUE) {
/*  98 */       int gid = this.c.a(c);
/*  99 */       if (gid == -1) {
/*     */         break;
/*     */       }
/* 102 */       if (sgid != -1) {
/* 103 */         int lgid = this.c.b(sgid, gid);
/* 104 */         if (lgid == -1) {
/* 105 */           ti.a(this.e, 0, clen, sgid);
/* 106 */           sgid = gid;
/* 107 */           clen = 0;
/*     */         } else {
/* 109 */           sgid = lgid;
/*     */         } 
/*     */       } else {
/* 112 */         sgid = gid;
/*     */       } 
/* 114 */       this.e[clen] = c;
/* 115 */       clen = (byte)(clen + 1);
/* 116 */       c = text.next();
/*     */     } 
/* 118 */     if (clen > 0) {
/* 119 */       ti.a(this.e, 0, clen, sgid);
/*     */     }
/* 121 */     return new c((h)ti, this); }
/*     */   public GVTLineMetrics getLineMetrics(CharacterIterator text, int start, int limit, FontRenderContext frc) { float ascent = (float)this.a.c(); int baselineIndex = 0; float leading = 0.0F; float[] baselineOffsets = null; float descent = (float)this.a.d(); float height = (float)this.b.e(); int numChars = limit - start; float strikethroughOffset = -height / 2.0F; float strikethroughThickness = height / 12.0F; float underlineOffset = 0.0F; float underlineThickness = strikethroughThickness; float overlineOffset = -height; float overlineThickness = strikethroughThickness; return new GVTLineMetrics(ascent, baselineIndex, baselineOffsets, descent, height, leading, numChars, strikethroughOffset, strikethroughThickness, underlineOffset, underlineThickness, overlineOffset, overlineThickness); }
/*     */   public GVTLineMetrics getLineMetrics(char[] text, int start, int limit, FontRenderContext frc) { return getLineMetrics(new String(text), start, limit, frc); }
/*     */   public GVTLineMetrics getLineMetrics(String text, FontRenderContext frc) { return getLineMetrics(text, 0, text.length(), frc); }
/* 125 */   public GVTLineMetrics getLineMetrics(String text, int start, int limit, FontRenderContext frc) { return getLineMetrics(new StringCharacterIterator(text), start, limit, frc); } public GVTGlyphVector createGlyphVector(FontRenderContext frc, int[] glyphCodes, CharacterIterator text) { throw new UnsupportedOperationException(); } public GVTGlyphVector createGlyphVector(FontRenderContext frc, char[] text) { return createGlyphVector(frc, new String(text)); }
/*     */ 
/*     */   
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, String text) {
/* 129 */     return createGlyphVector(frc, new StringCharacterIterator(text));
/*     */   }
/*     */   
/*     */   public GVTFont deriveFont(float size) {
/* 133 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public String getFamilyName() {
/* 137 */     return this.d.d();
/*     */   }
/*     */   
/*     */   public float getHKern(int g1, int g2) {
/* 141 */     return (float)this.a.a(g1, g2);
/*     */   }
/*     */   
/*     */   public float getSize() {
/* 145 */     return (float)this.b.e();
/*     */   }
/*     */   
/*     */   public float getVKern(int g1, int g2) {
/* 149 */     return (float)this.a.a(g1, g2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */