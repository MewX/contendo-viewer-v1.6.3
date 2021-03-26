/*    */ package net.a.a.c.a;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
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
/*    */ public final class d
/*    */   extends a
/*    */ {
/*    */   private static final String a = "Failed to retrieve values of enum class ";
/*    */   private static final long d = 1L;
/*    */   
/*    */   private d(Class<? extends Enum<?>> paramClass) {
/* 43 */     super(paramClass);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static h a(Class<? extends Enum<?>> paramClass) {
/* 53 */     return new d(paramClass);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object a(String paramString) {
/* 60 */     if (paramString == null) {
/* 61 */       return null;
/*    */     }
/* 63 */     return Enum.valueOf(a(), paramString);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object[] b() {
/*    */     try {
/* 73 */       return (Object[])a().getMethod("values", new Class[0]).invoke(null, new Object[0]);
/*    */     }
/* 75 */     catch (InvocationTargetException invocationTargetException) {
/* 76 */       throw new RuntimeException("Failed to retrieve values of enum class " + 
/*    */           
/* 78 */           a(), invocationTargetException);
/* 79 */     } catch (IllegalAccessException illegalAccessException) {
/* 80 */       throw new RuntimeException("Failed to retrieve values of enum class " + 
/*    */           
/* 82 */           a(), illegalAccessException);
/* 83 */     } catch (NoSuchMethodException noSuchMethodException) {
/* 84 */       throw new RuntimeException("Failed to retrieve values of enum class " + 
/*    */           
/* 86 */           a(), noSuchMethodException);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/a/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */