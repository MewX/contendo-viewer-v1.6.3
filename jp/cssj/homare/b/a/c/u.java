/*    */ package jp.cssj.homare.b.a.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class u
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   public static final byte c = 3;
/* 14 */   public static final u d = new u(0.0D, (byte)1);
/* 15 */   public static final u e = new u(0.0D, (byte)3);
/*    */   
/*    */   private final double f;
/*    */   private final byte g;
/*    */   
/*    */   public static u a(double length, byte type) {
/* 21 */     if (type == 3) {
/* 22 */       return e;
/*    */     }
/* 24 */     if (type != 3 && length == 0.0D) {
/* 25 */       return d;
/*    */     }
/* 27 */     return new u(length, type);
/*    */   }
/*    */   
/*    */   private u(double length, byte type) {
/* 31 */     this.f = length;
/* 32 */     this.g = type;
/*    */   }
/*    */   
/*    */   public byte a() {
/* 36 */     return this.g;
/*    */   }
/*    */   
/*    */   public double b() {
/* 40 */     return this.f;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     StringBuffer buff = new StringBuffer();
/* 45 */     buff.append("[length=");
/* 46 */     switch (a()) {
/*    */       case 1:
/* 48 */         buff.append(this.f);
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
/* 59 */         buff.append(']');
/* 60 */         return buff.toString();case 2: buff.append(this.f * 100.0D).append('%'); buff.append(']'); return buff.toString();case 3: buff.append("auto"); buff.append(']'); return buff.toString();
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/u.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */