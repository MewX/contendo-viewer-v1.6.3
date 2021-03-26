/*    */ package jp.cssj.homare.css.c;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import jp.cssj.homare.impl.a.c.K;
/*    */ import jp.cssj.homare.impl.a.c.a.j;
/*    */ import jp.cssj.homare.impl.a.c.a.v;
/*    */ import jp.cssj.homare.impl.a.c.w;
/*    */ import jp.cssj.homare.impl.a.c.x;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class g
/*    */   extends n
/*    */ {
/*    */   private void a(m p) {
/* 21 */     this.a.put(p.b(), p);
/*    */   }
/*    */ 
/*    */   
/* 25 */   private Map<String, m> a = new HashMap<>(); private g() {
/* 26 */     a(w.a);
/* 27 */     a(v.a);
/* 28 */     a(K.a);
/* 29 */     a(x.a);
/* 30 */     a(j.a);
/*    */     
/* 32 */     this.a = Collections.unmodifiableMap(this.a);
/*    */   }
/*    */   
/* 35 */   private static final n b = new g();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected m a(String name) {
/* 42 */     return this.a.get(name);
/*    */   }
/*    */   
/*    */   public static n a() {
/* 46 */     return b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/c/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */