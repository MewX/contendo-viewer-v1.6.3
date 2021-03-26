/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ah
/*    */   implements ad
/*    */ {
/* 10 */   public static final ah a = new ah((byte)1);
/*    */   
/* 12 */   public static final ah b = new ah((byte)2);
/*    */   
/* 14 */   public static final ah c = new ah((byte)3);
/*    */   
/* 16 */   public static final ah d = new ah((byte)4);
/*    */   
/* 18 */   public static final ah e = new ah((byte)5);
/*    */   
/*    */   private final byte f;
/*    */   
/*    */   private ah(byte whiteSpace) {
/* 23 */     this.f = whiteSpace;
/*    */   }
/*    */   
/*    */   public short a() {
/* 27 */     return 1037;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 31 */     return this.f;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 35 */     switch (this.f) {
/*    */       case 1:
/* 37 */         return "normal";
/*    */       
/*    */       case 2:
/* 40 */         return "pre";
/*    */       
/*    */       case 3:
/* 43 */         return "nowrap";
/*    */       
/*    */       case 4:
/* 46 */         return "pre-wrap";
/*    */       
/*    */       case 5:
/* 49 */         return "pre-line";
/*    */     } 
/*    */     
/* 52 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/ah.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */