/*    */ package c.a;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */ {
/*    */   public static void a(Throwable e) {
/* 81 */     e.fillInStackTrace();
/* 82 */     e.printStackTrace();
/*    */     
/* 84 */     System.err.println("The Thread is being terminated bacause an Exception (shown above)\nhas been thrown and no special action was defined for this Thread.");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 90 */     throw new ThreadDeath();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */