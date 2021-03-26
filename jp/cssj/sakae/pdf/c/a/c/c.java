/*    */ package jp.cssj.sakae.pdf.c.a.c;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import jp.cssj.sakae.e.e;
/*    */ import jp.cssj.sakae.pdf.c.a.b.b;
/*    */ import jp.cssj.sakae.pdf.c.a.f;
/*    */ import jp.cssj.sakae.pdf.c.a.l;
/*    */ import net.zamasoft.a.b.ah;
/*    */ import net.zamasoft.a.d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends b
/*    */ {
/*    */   private static final long y = 1L;
/*    */   protected final File w;
/*    */   protected final int x;
/*    */   
/*    */   public c(f hcmap, f vcmap, File otFile, int index) throws IOException {
/* 31 */     super(hcmap, vcmap);
/* 32 */     this.w = otFile;
/* 33 */     this.x = index;
/*    */     
/* 35 */     b fs = new b(this.w, this.x, e());
/* 36 */     this.k = fs.d();
/* 37 */     this.X_ = fs.a();
/* 38 */     this.l = fs.f();
/* 39 */     this.m = fs.g();
/* 40 */     this.n = fs.i();
/* 41 */     this.o = fs.h();
/* 42 */     this.p = fs.l();
/* 43 */     this.q = fs.j();
/* 44 */     this.r = fs.k();
/* 45 */     this.u = fs.p();
/*    */   }
/*    */   
/*    */   public l q() {
/* 49 */     if (this.t == null) {
/*    */       
/*    */       try {
/* 52 */         b fs = new b(this.w, this.x, e());
/* 53 */         a(a(fs, this.i));
/* 54 */       } catch (IOException e) {
/* 55 */         throw new RuntimeException(e);
/*    */       } 
/*    */     }
/* 58 */     return this.t;
/*    */   }
/*    */   
/*    */   private static l a(b fs, f cmap) {
/* 62 */     d otFont = fs.o();
/* 63 */     ah hmtx = (ah)otFont.a(1752003704);
/* 64 */     short upm = fs.q();
/*    */     
/* 66 */     e cidToAdvance = new e(-32768);
/* 67 */     jp.cssj.sakae.pdf.c.a.c ct = cmap.a();
/* 68 */     for (int i = 0; i < ct.c(); i++) {
/* 69 */       if (ct.b(i)) {
/*    */ 
/*    */         
/* 72 */         int cid = ct.a(i);
/* 73 */         int gid = fs.r().a(i);
/* 74 */         short advance = (short)(hmtx.a(gid) * 1000 / upm);
/*    */         
/* 76 */         if (advance > cidToAdvance.a(cid))
/* 77 */           cidToAdvance.a(cid, advance); 
/*    */       } 
/*    */     } 
/* 80 */     l warray = l.a(cidToAdvance.a());
/* 81 */     return warray;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/c/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */