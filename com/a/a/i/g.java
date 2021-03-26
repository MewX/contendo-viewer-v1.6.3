/*    */ package com.a.a.i;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class g
/*    */ {
/*    */   public static Method a(Class<?> clazz, String name, Class... parameters) {
/* 16 */     Method method = null;
/*    */     
/* 18 */     try { method = clazz.getMethod(name, parameters); }
/* 19 */     catch (SecurityException securityException) {  }
/* 20 */     catch (NoSuchMethodException noSuchMethodException) {}
/*    */     
/* 22 */     return method;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Object a(Method method, Object obj, Object... parameters) {
/* 33 */     Object rtn = null;
/* 34 */     if (method != null) {
/*    */       
/* 36 */       try { rtn = method.invoke(obj, parameters); }
/* 37 */       catch (IllegalArgumentException illegalArgumentException) {  }
/* 38 */       catch (IllegalAccessException illegalAccessException) {  }
/* 39 */       catch (InvocationTargetException invocationTargetException) {}
/*    */     }
/*    */     
/* 42 */     return rtn;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/i/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */