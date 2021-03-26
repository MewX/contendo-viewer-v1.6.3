/*     */ package jp.cssj.sakae.pdf.c.a.b;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.sakae.a.e;
/*     */ import jp.cssj.sakae.e.c;
/*     */ import jp.cssj.sakae.e.e;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c.a.i;
/*     */ import jp.cssj.sakae.pdf.c.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   extends i
/*     */ {
/*  21 */   private static final Logger n = Logger.getLogger(d.class.getName());
/*     */   
/*     */   private static final long o = 2L;
/*     */   
/*  25 */   protected transient WeakReference<c> l = null;
/*     */   
/*  27 */   protected transient WeakReference<e> m = null; private final char[] p;
/*     */   private final int[] q;
/*     */   
/*  30 */   public d(Font font) { super(font);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  37 */     this.p = new char[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     this.q = new int[1]; }
/*     */   public byte h_() { return 3; }
/*     */   private c s() { c charToGid = null; if (this.l != null) { charToGid = this.l.get(); if (charToGid != null) return charToGid;  }  if (n.isLoggable(Level.FINE))
/*  93 */       n.fine("build charToGid: " + d());  charToGid = new c(); this.l = new WeakReference<>(charToGid); return charToGid; } public synchronized short c(int gid) { e advances = t();
/*  94 */     short advance = advances.a(gid);
/*  95 */     if (advance == 0) {
/*  96 */       this.q[0] = gid;
/*  97 */       GlyphVector gv = this.i.createGlyphVector(r(), this.q);
/*  98 */       advance = (short)(int)gv.getGlyphMetrics(0).getAdvance();
/*  99 */       advances.a(gid, advance);
/*     */     } 
/* 101 */     return advance; }
/*     */   private e t() { e advances = null; if (this.m != null) { advances = this.m.get(); if (advances != null)
/*     */         return advances;  }  if (n.isLoggable(Level.FINE))
/*     */       n.fine("build advances: " + d());  advances = new e(-32768); this.m = new WeakReference<>(advances); return advances; }
/* 105 */   public synchronized int b(int c) { int gid; c charToGid = s(); if (charToGid.b(c) == 0) { this.p[0] = (char)c; GlyphVector gv = this.i.createGlyphVector(r(), this.p); gid = gv.getGlyphCode(0); if (gid < 0 || gid > 65535) { n.warning("16ビットを超えるGIDの文字は使用できません:" + c + "(gid=" + gid + ")"); gid = this.i.getMissingGlyphCode(); }  charToGid.a(c, (gid == 0) ? -1 : gid); } else { gid = charToGid.b(c); }  return (gid == -1) ? 0 : gid; } public e a(String name, b fontRef) { return (e)new c(this, name, fontRef); }
/*     */ 
/*     */   
/*     */   public e n() {
/* 109 */     return (e)a((String)null, (b)null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/b/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */