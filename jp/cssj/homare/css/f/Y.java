/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Y
/*    */   implements ad
/*    */ {
/* 10 */   public static final Y a = new Y((byte)0);
/*    */   
/*    */   public static final byte b = 1;
/*    */   
/*    */   public static final byte c = 2;
/*    */   
/*    */   public static final byte d = 4;
/*    */   
/*    */   public static final byte e = 8;
/*    */   
/*    */   private final byte f;
/*    */   
/* 22 */   private static final Y[] g = new Y[16];
/*    */   
/*    */   static {
/* 25 */     for (byte i = 0; i < g.length; i = (byte)(i + 1)) {
/* 26 */       g[i] = new Y(i);
/*    */     }
/*    */   }
/*    */   
/*    */   public static Y a(byte flags) {
/* 31 */     return g[flags];
/*    */   }
/*    */   
/*    */   private Y(byte flags) {
/* 35 */     this.f = flags;
/*    */   }
/*    */   
/*    */   public short a() {
/* 39 */     return 1034;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 43 */     return this.f;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 47 */     if (this.f == 0) {
/* 48 */       return "none";
/*    */     }
/* 50 */     StringBuffer buff = new StringBuffer();
/* 51 */     if ((this.f & 0x1) != 0) {
/* 52 */       buff.append("underline ");
/*    */     }
/* 54 */     if ((this.f & 0x2) != 0) {
/* 55 */       buff.append("overline ");
/*    */     }
/* 57 */     if ((this.f & 0x4) != 0) {
/* 58 */       buff.append("line-through ");
/*    */     }
/* 60 */     if ((this.f & 0x8) != 0) {
/* 61 */       buff.append("blink ");
/*    */     }
/* 63 */     buff.deleteCharAt(buff.length() - 1);
/* 64 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/Y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */