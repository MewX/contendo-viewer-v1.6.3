/*    */ package jp.cssj.homare.b.c;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.b.f.e;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.c;
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
/*    */ public class g
/*    */   implements Comparable<g>
/*    */ {
/*    */   protected static class a
/*    */   {
/*    */     private final f b;
/*    */     private final double c;
/*    */     private final double d;
/*    */     
/*    */     public a(f drawable, double x, double y) {
/* 29 */       if (!a && e.a(x)) throw new AssertionError("Undefined x"); 
/* 30 */       if (!a && e.a(y)) throw new AssertionError("Undefined y"); 
/* 31 */       this.b = drawable;
/* 32 */       this.c = x;
/* 33 */       this.d = y;
/*    */     }
/*    */     
/*    */     public void a(b gc) throws c {
/* 37 */       this.b.b(gc, this.c, this.d);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 42 */   protected List<a> b = null;
/* 43 */   protected List<g> c = null; protected final int a;
/*    */   
/*    */   public g(int z) {
/* 46 */     this.a = z;
/*    */   }
/*    */   
/*    */   public void a(f drawable, double x, double y) {
/* 50 */     if (!d && e.a(x)) throw new AssertionError("Undefined x"); 
/* 51 */     if (!d && e.a(y)) throw new AssertionError("Undefined y"); 
/* 52 */     if (this.b == null) {
/* 53 */       this.b = new ArrayList<>();
/*    */     }
/* 55 */     this.b.add(new a(drawable, x, y));
/*    */   }
/*    */   
/*    */   public void a(g drawer) {
/* 59 */     if (this.c == null) {
/* 60 */       this.c = new ArrayList<>();
/*    */     }
/* 62 */     this.c.add(drawer);
/*    */   }
/*    */   
/*    */   public void a(b gc) throws c {
/* 66 */     if (this.b != null) {
/* 67 */       for (int i = 0; i < this.b.size(); i++) {
/* 68 */         a drawable = this.b.get(i);
/* 69 */         drawable.a(gc);
/*    */       } 
/*    */     }
/*    */     
/* 73 */     if (this.c != null) {
/*    */ 
/*    */       
/* 76 */       Collections.sort(this.c);
/* 77 */       for (int i = 0; i < this.c.size(); i++) {
/* 78 */         g drawer = this.c.get(i);
/* 79 */         drawer.a(gc);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public int b(g o) {
/* 85 */     g a1 = this;
/* 86 */     g a2 = o;
/* 87 */     long s1 = a1.a;
/* 88 */     long s2 = a2.a;
/* 89 */     return (s1 < s2) ? -1 : ((s1 > s2) ? 1 : 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/c/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */