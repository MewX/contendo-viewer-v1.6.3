/*    */ package jp.cssj.b;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */   extends IOException
/*    */ {
/*    */   private static final long c = 0L;
/*    */   private final short d;
/*    */   private final String[] e;
/*    */   private final byte f;
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   
/*    */   public d(byte state, short code, String[] args, String message) {
/* 31 */     super(message);
/* 32 */     this.d = code;
/* 33 */     this.e = args;
/* 34 */     this.f = state;
/*    */   }
/*    */   
/*    */   public d(short code, String[] args, String message) {
/* 38 */     this((byte)2, code, args, message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short a() {
/* 47 */     return this.d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] b() {
/* 56 */     return this.e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte c() {
/* 65 */     return this.f;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */