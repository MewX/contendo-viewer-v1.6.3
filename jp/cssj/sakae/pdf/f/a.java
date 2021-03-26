/*    */ package jp.cssj.sakae.pdf.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class a
/*    */ {
/*    */   public static final short a = 1;
/*    */   public static final short b = 2;
/*    */   public static final short c = 4;
/* 10 */   private String d = "", e = "";
/*    */   
/*    */   public abstract short a();
/*    */   
/*    */   public String b() {
/* 15 */     return this.e;
/*    */   }
/*    */   
/*    */   public void a(String ownerPassword) {
/* 19 */     if (ownerPassword == null) {
/* 20 */       ownerPassword = "";
/*    */     }
/* 22 */     this.e = ownerPassword;
/*    */   }
/*    */   
/*    */   public String c() {
/* 26 */     return this.d;
/*    */   }
/*    */   
/*    */   public void b(String userPassword) {
/* 30 */     if (userPassword == null) {
/* 31 */       userPassword = "";
/*    */     }
/* 33 */     this.d = userPassword;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/f/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */