/*    */ package jp.cssj.homare.css.f.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class g
/*    */   implements j
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   private final byte c;
/*    */   private final String d;
/*    */   private final String e;
/*    */   private final String f;
/*    */   private final short g;
/*    */   
/*    */   public g(byte type, String ref, String counter, short numberStyleType, String separator) {
/* 18 */     this.c = type;
/* 19 */     this.d = ref;
/* 20 */     this.e = counter;
/* 21 */     this.g = numberStyleType;
/* 22 */     this.f = separator;
/*    */   }
/*    */   
/*    */   public short a() {
/* 26 */     return 2004;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 30 */     return this.c;
/*    */   }
/*    */   
/*    */   public String c() {
/* 34 */     return this.d;
/*    */   }
/*    */   
/*    */   public String d() {
/* 38 */     return this.e;
/*    */   }
/*    */   
/*    */   public short e() {
/* 42 */     return this.g;
/*    */   }
/*    */   
/*    */   public String f() {
/* 46 */     return this.f;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     return "-cssj-page-ref(" + this.d + "," + this.e + "," + this.g + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/b/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */