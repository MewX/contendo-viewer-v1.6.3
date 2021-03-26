/*    */ package de.codecentric.centerdevice.a;
/*    */ 
/*    */ public class b
/*    */   extends RuntimeException {
/*    */   private static final long a = 7315344041984700277L;
/*    */   
/*    */   public b(Throwable e) {
/*  8 */     super(a(e), e);
/*    */   }
/*    */   
/*    */   private static String a(Throwable e) {
/*    */     String description;
/* 13 */     if (e instanceof NoSuchFieldException) {
/* 14 */       description = "Unable to find field \"" + e.getMessage() + "\"";
/* 15 */     } else if (e instanceof NoSuchMethodException) {
/* 16 */       description = "Unable to find method \"" + e.getMessage() + "\"";
/*    */     } else {
/* 18 */       description = e.getMessage();
/*    */     } 
/*    */     
/* 21 */     return description + " (" + a() + ")";
/*    */   }
/*    */   
/*    */   private static String a() {
/* 25 */     return "Using Java " + System.getProperty("java.version") + " on " + System.getProperty("os.name");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */