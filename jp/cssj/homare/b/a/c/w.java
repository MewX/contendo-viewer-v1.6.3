/*    */ package jp.cssj.homare.b.a.c;
/*    */ public class w {
/*    */   public static final short a = 1;
/*    */   public static final short b = 2;
/*    */   public static final short c = 3;
/*    */   public static final w d;
/*    */   public static final w e;
/*    */   public static final w f;
/*    */   private final double h;
/*    */   private final double i;
/*    */   private final byte j;
/*    */   
/*    */   static {
/* 14 */     d = new w(0.0D, 0.0D, (short)1, (short)1);
/* 15 */     e = new w(0.5D, 0.5D, (short)2, (short)2);
/* 16 */     f = new w(0.0D, 0.0D, (short)3, (short)3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static w a(double x, double y, short xType, short yType) {
/* 23 */     if (xType == 3 && yType == 3) {
/* 24 */       return f;
/*    */     }
/* 26 */     if (xType != 3 && yType != 3 && x == 0.0D && y == 0.0D) {
/* 27 */       return d;
/*    */     }
/* 29 */     return new w(x, y, xType, yType);
/*    */   }
/*    */   
/*    */   private w(double x, double y, short xType, short yType) {
/* 33 */     if (!g && (xType < 1 || xType > 3)) throw new AssertionError(); 
/* 34 */     if (!g && (yType < 1 || yType > 3)) throw new AssertionError(); 
/* 35 */     this.h = x;
/* 36 */     this.i = y;
/* 37 */     this.j = (byte)(xType | yType << 4);
/*    */   }
/*    */   
/*    */   public short a() {
/* 41 */     return (short)(this.j & 0x3);
/*    */   }
/*    */   
/*    */   public short b() {
/* 45 */     return (short)(this.j >> 4 & 0x3);
/*    */   }
/*    */   
/*    */   public double c() {
/* 49 */     return this.h;
/*    */   }
/*    */   
/*    */   public double d() {
/* 53 */     return this.i;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 57 */     StringBuffer buff = new StringBuffer();
/* 58 */     buff.append("[x=");
/* 59 */     switch (a()) {
/*    */       case 1:
/* 61 */         buff.append(this.h);
/*    */         break;
/*    */       case 2:
/* 64 */         buff.append(this.h * 100.0D).append('%');
/*    */         break;
/*    */       case 3:
/* 67 */         buff.append("auto");
/*    */         break;
/*    */       default:
/* 70 */         throw new IllegalStateException();
/*    */     } 
/* 72 */     buff.append(",y=");
/* 73 */     switch (b()) {
/*    */       case 1:
/* 75 */         buff.append(this.i);
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
/* 86 */         buff.append(']');
/* 87 */         return buff.toString();case 2: buff.append(this.i * 100.0D).append('%'); buff.append(']'); return buff.toString();case 3: buff.append("auto"); buff.append(']'); return buff.toString();
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/w.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */