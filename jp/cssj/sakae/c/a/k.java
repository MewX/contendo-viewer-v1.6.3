/*    */ package jp.cssj.sakae.c.a;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class k implements Serializable {
/*    */   private static final long c = 2L;
/*    */   public final int a;
/*    */   public final int b;
/*    */   
/*    */   public k(int first, int last) {
/* 11 */     this.a = first;
/* 12 */     this.b = last;
/*    */   }
/*    */ 
/*    */   
/*    */   public static k a(String s) throws NumberFormatException {
/* 17 */     int first, last, hyph = s.indexOf('-');
/* 18 */     if (hyph != -1) {
/* 19 */       String u1 = s.substring(2, hyph);
/* 20 */       String u2 = s.substring(hyph + 1);
/* 21 */       if (u2.startsWith("U+")) {
/* 22 */         u2 = u2.substring(2);
/*    */       }
/* 24 */       first = Integer.parseInt(u1, 16);
/* 25 */       last = Integer.parseInt(u2, 16);
/*    */     } else {
/* 27 */       String u = s.substring(2);
/* 28 */       if (u.indexOf('?') != -1) {
/* 29 */         first = Integer.parseInt(u.replace('?', '0'), 16);
/* 30 */         last = Integer.parseInt(u.replace('?', 'F'), 16);
/*    */       } else {
/* 32 */         first = last = Integer.parseInt(u, 16);
/*    */       } 
/*    */     } 
/* 35 */     return new k(first, last);
/*    */   }
/*    */   
/*    */   public boolean a(int c) {
/* 39 */     return (c >= this.a && c <= this.b);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     if (this.a == this.b) {
/* 44 */       return "U+" + Integer.toHexString(this.a);
/*    */     }
/* 46 */     return "U+" + Integer.toHexString(this.a) + "-" + Integer.toHexString(this.b);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */