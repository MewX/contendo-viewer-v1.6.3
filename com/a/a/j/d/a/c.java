/*    */ package com.a.a.j.d.a;
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends Exception
/*    */ {
/*    */   private static final long a = 1L;
/*    */   
/*    */   public static c a(Throwable e) {
/* 10 */     if (e instanceof java.io.FileNotFoundException) return new b(e); 
/* 11 */     return new c(e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c(String message) {
/* 25 */     super(message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c(Throwable cause) {
/* 33 */     super(cause);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c(String message, Throwable cause) {
/* 42 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/d/a/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */