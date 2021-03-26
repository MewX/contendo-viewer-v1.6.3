/*   */ package jp.cssj.b.a;
/*   */ 
/*   */ import jp.cssj.b.c;
/*   */ 
/*   */ public abstract class a implements c {
/*   */   protected void finalize() throws Throwable {
/* 7 */     close();
/* 8 */     super.finalize();
/*   */   }
/*   */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */