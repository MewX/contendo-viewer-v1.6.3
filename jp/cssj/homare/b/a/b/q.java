/*    */ package jp.cssj.homare.b.a.b;
/*    */ 
/*    */ import jp.cssj.homare.b.a.a.i;
/*    */ import jp.cssj.homare.b.a.c;
/*    */ import jp.cssj.homare.b.f.e;
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
/*    */ class q
/*    */   extends i
/*    */ {
/*    */   double l() {
/* 34 */     i.a flow = b();
/* 35 */     if (flow == null) {
/* 36 */       return 0.0D;
/*    */     }
/* 38 */     c containerBox = (c)flow.b;
/* 39 */     return containerBox.n() + containerBox.o();
/*    */   }
/*    */   
/*    */   public double g() {
/* 43 */     i.a flow = b();
/* 44 */     if (flow == null) {
/* 45 */       return 1.722773839210782E308D;
/*    */     }
/* 47 */     if (flow.b.a() != 5) {
/* 48 */       return super.g();
/*    */     }
/*    */     
/* 51 */     c containerBox = (c)flow.b;
/* 52 */     double firstAscent = containerBox.n();
/* 53 */     if (e.a(firstAscent)) {
/* 54 */       return firstAscent;
/*    */     }
/* 56 */     double descent = firstAscent;
/* 57 */     descent += flow.c;
/*    */     
/* 59 */     switch ((this.a.c_()).D) {
/*    */       
/*    */       case 1:
/* 62 */         descent = this.a.t() - descent;
/* 63 */         descent += this.a.m().c();
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
/*    */ 
/*    */         
/* 78 */         return descent;case 2: descent = this.a.s() - descent; descent += this.a.m().b(); return descent;case 3: descent = this.a.s() - descent; descent += this.a.m().d(); return descent;
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */