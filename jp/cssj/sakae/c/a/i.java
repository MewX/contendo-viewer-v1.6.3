/*    */ package jp.cssj.sakae.c.a;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import jp.cssj.sakae.c.a.a.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class i
/*    */   implements Serializable, h
/*    */ {
/*    */   private static final long p = 0L;
/*    */   private final c q;
/*    */   private final double r;
/*    */   private final byte s;
/*    */   private final short t;
/*    */   private final byte u;
/*    */   private final g v;
/*    */   
/*    */   public i(c families, double size, byte style, short weight, byte direction, g policy) {
/* 24 */     this.q = families;
/* 25 */     this.r = size;
/* 26 */     this.s = style;
/* 27 */     this.t = weight;
/* 28 */     this.u = direction;
/* 29 */     this.v = policy;
/*    */   }
/*    */   
/*    */   public c d() {
/* 33 */     return this.q;
/*    */   }
/*    */   
/*    */   public double e() {
/* 37 */     return this.r;
/*    */   }
/*    */   
/*    */   public byte c() {
/* 41 */     return this.s;
/*    */   }
/*    */   
/*    */   public short b() {
/* 45 */     return this.t;
/*    */   }
/*    */   
/*    */   public byte a() {
/* 49 */     return this.u;
/*    */   }
/*    */   
/*    */   public g f() {
/* 53 */     return this.v;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 57 */     if (o == null || !(o instanceof h)) {
/* 58 */       return false;
/*    */     }
/* 60 */     h b = (h)o;
/* 61 */     return a.a(this, b);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 65 */     return a.a(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 69 */     return super.toString() + "[families=" + d() + ",size=" + e() + ",style=" + 
/* 70 */       c() + ",weight=" + b() + ",writingMode=" + a() + ",policy=" + 
/* 71 */       f() + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */