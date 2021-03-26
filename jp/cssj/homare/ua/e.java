/*    */ package jp.cssj.homare.ua;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */ {
/* 10 */   private c[] a = null;
/*    */   
/*    */   public void a(String name, int value) {
/* 13 */     c counter = c(name);
/* 14 */     if (counter == null) {
/* 15 */       b(name, value);
/*    */     } else {
/* 17 */       counter.b = value;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void b(String name, int delta) {
/* 22 */     c counter = c(name);
/* 23 */     if (counter == null) {
/* 24 */       counter = new c(name);
/* 25 */       if (this.a == null) {
/* 26 */         this.a = new c[] { counter };
/*    */       } else {
/* 28 */         c[] counters = new c[this.a.length + 1];
/* 29 */         System.arraycopy(this.a, 0, counters, 1, this.a.length);
/* 30 */         counters[0] = counter;
/* 31 */         this.a = counters;
/*    */       } 
/*    */     } 
/* 34 */     counter.b += delta;
/*    */   }
/*    */   
/*    */   public boolean a(String name) {
/* 38 */     return (c(name) != null);
/*    */   }
/*    */   
/*    */   public int b(String name) {
/* 42 */     c counter = c(name);
/* 43 */     if (counter == null) {
/* 44 */       return 0;
/*    */     }
/* 46 */     return counter.b;
/*    */   }
/*    */   
/*    */   private c c(String name) {
/* 50 */     if (this.a == null) {
/* 51 */       return null;
/*    */     }
/* 53 */     for (int i = 0; i < this.a.length; i++) {
/* 54 */       c counter = this.a[i];
/* 55 */       if (counter.a.equalsIgnoreCase(name)) {
/* 56 */         return counter;
/*    */       }
/*    */     } 
/* 59 */     return null;
/*    */   }
/*    */   
/*    */   public c[] a() {
/* 63 */     if (this.a == null) {
/* 64 */       return null;
/*    */     }
/* 66 */     c[] counters = new c[this.a.length];
/* 67 */     for (int i = 0; i < counters.length; i++) {
/* 68 */       c counter = this.a[i];
/* 69 */       counters[i] = new c(counter.a, counter.b);
/*    */     } 
/* 71 */     return counters;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */