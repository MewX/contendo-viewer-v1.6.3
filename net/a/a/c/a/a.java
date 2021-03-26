/*    */ package net.a.a.c.a;
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
/*    */ public abstract class a
/*    */   implements h
/*    */ {
/*    */   private static final long a = 1L;
/*    */   private final Class<?> d;
/*    */   
/*    */   protected a(Class<?> paramClass) {
/* 42 */     this.d = paramClass;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<?> a() {
/* 47 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Object paramObject) {
/* 52 */     return this.d.isInstance(paramObject);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object a(String paramString) {
/* 57 */     if (paramString == null) {
/* 58 */       return null;
/*    */     }
/* 60 */     throw new IllegalArgumentException("Failed to convert <" + paramString + "> to " + this.d);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String b(Object paramObject) {
/* 66 */     if (paramObject == null) {
/* 67 */       return null;
/*    */     }
/* 69 */     return paramObject.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */