/*    */ package jp.cssj.homare.b.b.a;
/*    */ 
/*    */ import jp.cssj.homare.b.a.a.d;
/*    */ import jp.cssj.homare.b.a.c;
/*    */ import jp.cssj.homare.b.b.d;
/*    */ 
/*    */ public class e
/*    */   extends b {
/*    */   public e(d layoutStack, c contextBox) {
/* 10 */     super(layoutStack, contextBox, (byte)1);
/*    */   }
/*    */   
/*    */   protected final boolean a(d mode, byte flags) {
/*    */     int depth;
/* 15 */     if (this.c != null && !this.c.isEmpty()) {
/* 16 */       depth = this.c.size() + 1;
/*    */     } else {
/* 18 */       depth = 1;
/*    */     } 
/* 20 */     flags = (byte)(flags | 0x2);
/* 21 */     double lastFrame = a(this.b, depth);
/* 22 */     return a(this.b, mode, flags, lastFrame, depth);
/*    */   }
/*    */   
/*    */   public final void x() {
/* 26 */     if (!C && this.c != null && !this.c.isEmpty()) throw new AssertionError(); 
/* 27 */     if (!C && this.d != null) throw new AssertionError(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */