/*    */ package jp.cssj.sakae.pdf.c.a.c;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import jp.cssj.sakae.e.e;
/*    */ import jp.cssj.sakae.pdf.c.a.c;
/*    */ import jp.cssj.sakae.pdf.c.a.f;
/*    */ import jp.cssj.sakae.pdf.c.a.l;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */   extends b
/*    */ {
/*    */   private static final long x = 0L;
/*    */   protected final Font w;
/*    */   
/*    */   public d(f hcmap, f vcmap, Font awtFont) {
/* 24 */     super(hcmap, vcmap);
/* 25 */     this.w = awtFont = awtFont.deriveFont(1000.0F);
/* 26 */     jp.cssj.sakae.pdf.c.a.b.d fs = new jp.cssj.sakae.pdf.c.a.b.d(awtFont);
/* 27 */     this.k = fs.d();
/* 28 */     this.X_ = fs.a();
/* 29 */     this.l = fs.f();
/* 30 */     this.m = fs.g();
/* 31 */     this.n = fs.i();
/* 32 */     this.o = fs.h();
/* 33 */     this.p = fs.l();
/* 34 */     this.u = fs.p();
/*    */   }
/*    */   
/*    */   public Font r() {
/* 38 */     return this.w;
/*    */   }
/*    */   
/*    */   public l q() {
/* 42 */     if (this.t == null) {
/* 43 */       jp.cssj.sakae.pdf.c.a.b.d fs = new jp.cssj.sakae.pdf.c.a.b.d(this.w);
/* 44 */       a(a(fs, this.i));
/*    */     } 
/* 46 */     return this.t;
/*    */   }
/*    */   
/*    */   private static l a(jp.cssj.sakae.pdf.c.a.b.d fs, f cmap) {
/* 50 */     e cidToAdvance = new e(-32768);
/* 51 */     c ct = cmap.a();
/* 52 */     for (int i = 0; i < ct.c(); i++) {
/* 53 */       if (ct.b(i)) {
/*    */ 
/*    */         
/* 56 */         int cid = ct.a(i);
/* 57 */         int gid = fs.b(i);
/* 58 */         short advance = fs.c(gid);
/* 59 */         cidToAdvance.a(cid, advance);
/*    */       } 
/* 61 */     }  l warray = l.a(cidToAdvance.a());
/* 62 */     return warray;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/c/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */