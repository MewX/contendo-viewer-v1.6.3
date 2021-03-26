/*    */ package net.a.a.e.d.b;
/*    */ 
/*    */ import java.util.Locale;
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
/*    */ public enum f
/*    */ {
/* 28 */   a,
/*    */   
/* 30 */   b,
/*    */   
/* 32 */   c;
/*    */ 
/*    */   
/*    */   public static f[] a() {
/*    */     return (f[])d.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public static f a(String paramString) {
/*    */     return Enum.<f>valueOf(f.class, paramString);
/*    */   }
/*    */ 
/*    */   
/*    */   public static f b(String paramString) {
/*    */     try {
/* 47 */       return a(paramString.toUpperCase(Locale.US));
/* 48 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 49 */       return b;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/b/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */