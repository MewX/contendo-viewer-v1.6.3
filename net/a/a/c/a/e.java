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
/*    */ public final class e
/*    */   extends a
/*    */ {
/*    */   private static final long a = 1L;
/*    */   
/*    */   public e(Class<? extends Number> paramClass) {
/* 41 */     super(paramClass);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static h a(Class<? extends Number> paramClass) {
/* 51 */     return new e(paramClass);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object a(String paramString) {
/* 57 */     if (paramString == null) {
/* 58 */       return null;
/*    */     }
/*    */     try {
/* 61 */       return a().getConstructor(new Class[] { String.class
/* 62 */           }).newInstance(new Object[] { paramString });
/*    */     }
/* 64 */     catch (NoSuchMethodException noSuchMethodException) {
/* 65 */       throw new IllegalArgumentException("Failed to convert <" + paramString + "> to " + 
/* 66 */           a(), noSuchMethodException);
/* 67 */     } catch (IllegalAccessException illegalAccessException) {
/* 68 */       throw new IllegalArgumentException("Failed to convert <" + paramString + "> to " + 
/* 69 */           a(), illegalAccessException);
/* 70 */     } catch (InstantiationException instantiationException) {
/* 71 */       throw new IllegalArgumentException("Failed to convert <" + paramString + "> to " + 
/* 72 */           a(), instantiationException);
/* 73 */     } catch (InvocationTargetException invocationTargetException) {
/* 74 */       throw new IllegalArgumentException("Failed to convert <" + paramString + "> to " + 
/* 75 */           a(), invocationTargetException);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/a/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */