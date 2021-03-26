/*    */ package jp.cssj.sakae.a.b;
/*    */ 
/*    */ import jp.cssj.sakae.e.a;
/*    */ 
/*    */ public class b
/*    */   extends a {
/*    */   private static final long h = 1L;
/*  8 */   protected a g = new a();
/*    */   
/*    */   public b(c source) {
/* 11 */     super(source);
/*    */   }
/*    */   
/*    */   public int a(int c) {
/* 15 */     int gid = super.a(c);
/* 16 */     this.g.a(gid, (char)c);
/* 17 */     return gid;
/*    */   }
/*    */   
/*    */   protected int g(int gid) {
/* 21 */     return this.g.a(gid);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */