/*    */ package net.a.a.e.d;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class a
/*    */ {
/*    */   private static final class a
/*    */   {
/* 29 */     private static final a a = new a();
/*    */   }
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
/*    */   public static a a() {
/* 48 */     return a.a();
/*    */   }
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
/*    */   public Class<?> a(String paramString) throws ClassNotFoundException {
/*    */     Class<?> clazz;
/*    */     try {
/* 65 */       clazz = Thread.currentThread().getContextClassLoader().loadClass(paramString);
/*    */     }
/* 67 */     catch (ClassNotFoundException classNotFoundException) {
/* 68 */       clazz = a.class.getClassLoader().loadClass(paramString);
/*    */     } 
/*    */     
/* 71 */     return clazz;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */