/*    */ package jp.cssj.homare.b.a.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class m
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   public static final byte c = 3;
/* 14 */   public static final m d = new m(0.0D, 0.0D, (byte)1, (byte)1);
/* 15 */   public static final m e = new m(0.0D, 0.0D, (byte)3, (byte)3);
/*    */   
/*    */   private final double f;
/*    */   private final double g;
/*    */   private final byte h;
/*    */   
/*    */   public static m a(double width, double height, byte widthType, byte heightType) {
/* 22 */     if (widthType == 3 && heightType == 3) {
/* 23 */       return e;
/*    */     }
/* 25 */     if (widthType != 3 && heightType != 3 && width == 0.0D && height == 0.0D) {
/* 26 */       return d;
/*    */     }
/* 28 */     return new m(width, height, widthType, heightType);
/*    */   }
/*    */   
/*    */   private m(double width, double height, byte widthType, byte heightType) {
/* 32 */     this.f = width;
/* 33 */     this.g = height;
/* 34 */     this.h = (byte)(widthType | heightType << 2);
/*    */   }
/*    */   
/*    */   public byte a() {
/* 38 */     return (byte)(this.h & 0x3);
/*    */   }
/*    */   
/*    */   public byte b() {
/* 42 */     return (byte)(this.h >> 2 & 0x3);
/*    */   }
/*    */   
/*    */   public double c() {
/* 46 */     return this.f;
/*    */   }
/*    */   
/*    */   public double d() {
/* 50 */     return this.g;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 54 */     StringBuffer buff = new StringBuffer();
/* 55 */     buff.append("[width=");
/* 56 */     switch (a()) {
/*    */       case 1:
/* 58 */         buff.append(this.f);
/*    */         break;
/*    */       case 2:
/* 61 */         buff.append(this.f * 100.0D).append('%');
/*    */         break;
/*    */       case 3:
/* 64 */         buff.append("auto");
/*    */         break;
/*    */       default:
/* 67 */         throw new IllegalStateException();
/*    */     } 
/* 69 */     buff.append(",height=");
/* 70 */     switch (b()) {
/*    */       case 1:
/* 72 */         buff.append(this.g);
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
/* 83 */         buff.append(']');
/* 84 */         return buff.toString();case 2: buff.append(this.g * 100.0D).append('%'); buff.append(']'); return buff.toString();case 3: buff.append("auto"); buff.append(']'); return buff.toString();
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */