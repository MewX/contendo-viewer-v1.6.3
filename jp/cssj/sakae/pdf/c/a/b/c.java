/*    */ package jp.cssj.sakae.pdf.c.a.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import jp.cssj.sakae.a.g;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.d.h;
/*    */ import jp.cssj.sakae.e.e;
/*    */ import jp.cssj.sakae.pdf.b;
/*    */ import jp.cssj.sakae.pdf.c.a.a;
/*    */ import jp.cssj.sakae.pdf.c.a.b;
/*    */ import jp.cssj.sakae.pdf.c.a.e;
/*    */ import jp.cssj.sakae.pdf.c.d.b;
/*    */ import jp.cssj.sakae.pdf.d.a;
/*    */ import jp.cssj.sakae.pdf.k;
/*    */ 
/*    */ 
/*    */ class c
/*    */   extends a
/*    */ {
/*    */   private static final long f = 0L;
/* 21 */   protected final e d = new e(-32768);
/*    */   
/* 23 */   protected final jp.cssj.sakae.e.c e = new jp.cssj.sakae.e.c();
/*    */   
/*    */   protected c(d source, String name, b fontRef) {
/* 26 */     super((g)source, name, fontRef);
/*    */   }
/*    */   
/*    */   public int a(int i) {
/* 30 */     d source = (d)this.a;
/* 31 */     int gid = source.b(i);
/* 32 */     return gid;
/*    */   }
/*    */   
/*    */   public short b(int cid) {
/* 36 */     d source = (d)this.a;
/* 37 */     return source.c(cid);
/*    */   }
/*    */   
/*    */   public short c(int gid) {
/* 41 */     return b(gid);
/*    */   }
/*    */   
/*    */   public void a(b gc, h text) throws IOException, jp.cssj.sakae.c.c {
/* 45 */     if (gc instanceof a) {
/* 46 */       b.a(((a)gc).b(), text, false);
/*    */     } else {
/* 48 */       d source = (d)a();
/* 49 */       b.a(gc, (g)source, source.q(), text);
/*    */     } 
/*    */     
/* 52 */     int glen = text.l();
/* 53 */     int[] gids = text.j();
/* 54 */     char[] chars = text.h();
/* 55 */     for (int i = 0; i < glen; i++) {
/* 56 */       int gid = gids[i];
/* 57 */       short nadvance = b(gid);
/* 58 */       this.d.a(gid, nadvance);
/* 59 */       this.e.a(gid, chars[i]);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a(jp.cssj.sakae.pdf.c out, k xref) throws IOException {
/* 64 */     d source = (d)this.a;
/* 65 */     e.a(out, xref, (b)source, this.c, this.d.a(), null, this.e
/* 66 */         .a());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */