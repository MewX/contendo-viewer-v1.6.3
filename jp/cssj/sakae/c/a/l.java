/*    */ package jp.cssj.sakae.c.a;
/*    */ 
/*    */ public class l {
/*    */   private final k[] a;
/*    */   
/*    */   public l(k[] includes) {
/*  7 */     if (includes == null) {
/*  8 */       throw new NullPointerException();
/*    */     }
/* 10 */     this.a = includes;
/*    */   }
/*    */   
/*    */   public boolean a(int c) {
/* 14 */     if (this.a.length == 0) {
/* 15 */       return true;
/*    */     }
/* 17 */     for (int i = 0; i < this.a.length; i++) {
/* 18 */       if (this.a[i].a(c)) {
/* 19 */         return true;
/*    */       }
/*    */     } 
/* 22 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     StringBuffer buff = new StringBuffer();
/* 27 */     for (int i = 0; i < this.a.length; i++) {
/* 28 */       if (i > 0) {
/* 29 */         buff.append(", ");
/*    */       }
/* 31 */       buff.append(this.a[i]);
/*    */     } 
/* 33 */     return buff.toString();
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 37 */     return (this.a.length == 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */