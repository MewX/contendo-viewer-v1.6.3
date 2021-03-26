/*    */ package jp.cssj.sakae.c.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   implements b
/*    */ {
/*  8 */   public static final c a = new c(1.0F);
/*    */   
/* 10 */   public static final c b = new c(0.0F);
/*    */   
/*    */   protected final float c;
/*    */   
/*    */   public static c a(float gray) {
/* 15 */     if (gray <= 0.0F) {
/* 16 */       return b;
/*    */     }
/* 18 */     if (gray >= 1.0F) {
/* 19 */       return a;
/*    */     }
/*    */     
/* 22 */     return new c(gray);
/*    */   }
/*    */   
/*    */   protected c(float gray) {
/* 26 */     this.c = Math.min(1.0F, Math.max(0.0F, gray));
/*    */   }
/*    */   
/*    */   public short b() {
/* 30 */     return 1;
/*    */   }
/*    */   
/*    */   public short c() {
/* 34 */     return 3;
/*    */   }
/*    */   
/*    */   public float a(int i) {
/* 38 */     switch (i) {
/*    */       case 0:
/* 40 */         return this.c;
/*    */     } 
/* 42 */     throw new IllegalArgumentException();
/*    */   }
/*    */   
/*    */   public float d() {
/* 46 */     return this.c;
/*    */   }
/*    */   
/*    */   public float e() {
/* 50 */     return this.c;
/*    */   }
/*    */   
/*    */   public float f() {
/* 54 */     return this.c;
/*    */   }
/*    */   
/*    */   public float g() {
/* 58 */     return 1.0F;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 62 */     if (o instanceof c) {
/* 63 */       c color = (c)o;
/* 64 */       return (color.c == this.c);
/*    */     } 
/* 66 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 70 */     return "-cssj-gray(" + this.c + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/c/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */