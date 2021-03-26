/*    */ package jp.cssj.sakae.a;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements Serializable
/*    */ {
/*    */   private static final long e = 0L;
/*    */   public final short a;
/*    */   public final short b;
/*    */   public final short c;
/*    */   public final short d;
/*    */   
/*    */   public b(short llx, short lly, short urx, short ury) {
/* 23 */     this.a = llx;
/* 24 */     this.b = lly;
/* 25 */     this.c = urx;
/* 26 */     this.d = ury;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 30 */     return "[llx=" + this.a + ",lly=" + this.b + ",urx=" + this.c + ",ury=" + this.d + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */