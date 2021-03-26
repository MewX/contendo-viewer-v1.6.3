/*    */ package jp.cssj.homare.b.b;
/*    */ 
/*    */ import jp.cssj.homare.b.a.k;
/*    */ import jp.cssj.homare.b.f.e;
/*    */ 
/*    */ public interface c extends d {
/*    */   int g();
/*    */   
/*    */   b a(int paramInt);
/*    */   
/*    */   public static class a {
/*    */     public final k a;
/*    */     public final double b;
/*    */     public final double c;
/*    */     public final double d;
/*    */     public final double e;
/*    */     
/*    */     public a(k box, double lineStart, double pageStart, byte progression) {
/* 19 */       this.a = box;
/* 20 */       this.b = lineStart;
/* 21 */       this.c = pageStart;
/* 22 */       if (e.a(progression)) {
/*    */         
/* 24 */         this.d = lineStart + box.q();
/* 25 */         this.e = pageStart + box.p();
/*    */       } else {
/*    */         
/* 28 */         this.d = lineStart + box.p();
/* 29 */         this.e = pageStart + box.q();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class b
/*    */   {
/*    */     public final jp.cssj.homare.b.a.c a;
/*    */     
/*    */     public final double b;
/*    */     
/*    */     public final double c;
/*    */ 
/*    */     
/*    */     public b(jp.cssj.homare.b.a.c container, double lineAxis, double pageAxis) {
/* 46 */       this.a = container;
/* 47 */       this.b = lineAxis;
/* 48 */       this.c = pageAxis;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */