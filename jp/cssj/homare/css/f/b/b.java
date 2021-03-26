/*    */ package jp.cssj.homare.css.f.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements j
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   public static final byte c = 3;
/* 12 */   public static final b d = new b((byte)1);
/*    */   
/* 14 */   public static final b e = new b((byte)2);
/*    */   
/* 16 */   public static final b f = new b((byte)3);
/*    */   
/*    */   private final byte g;
/*    */   
/*    */   private b(byte directionMode) {
/* 21 */     this.g = directionMode;
/*    */   }
/*    */   
/*    */   public short a() {
/* 25 */     return 2005;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 29 */     return this.g;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 33 */     switch (this.g) {
/*    */       case 1:
/* 35 */         return "physical";
/*    */       
/*    */       case 2:
/* 38 */         return "horizontal-tb";
/*    */       
/*    */       case 3:
/* 41 */         return "vertical-rl";
/*    */     } 
/*    */     
/* 44 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */