/*    */ package de.codecentric.centerdevice.e;
/*    */ 
/*    */ import java.lang.invoke.MethodHandle;
/*    */ import java.lang.invoke.MethodHandles;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ public class b
/*    */ {
/*    */   public static MethodHandle a(Object obj, String name, Class<?>... args) throws ReflectiveOperationException {
/* 11 */     return MethodHandles.lookup().unreflect(b(obj, name, args));
/*    */   }
/*    */   
/*    */   public static Method b(Object obj, String name, Class<?>... args) throws NoSuchMethodException {
/* 15 */     Method method = obj.getClass().getDeclaredMethod(name, args);
/* 16 */     method.setAccessible(true);
/* 17 */     return method;
/*    */   }
/*    */   
/*    */   public static Field a(Object obj, String name) throws NoSuchFieldException, SecurityException {
/* 21 */     Field field = obj.getClass().getDeclaredField(name);
/* 22 */     field.setAccessible(true);
/* 23 */     return field;
/*    */   }
/*    */   
/*    */   public static void a(MethodHandle handle, Object instance) {
/*    */     try {
/* 28 */       handle.invoke(instance);
/* 29 */     } catch (Throwable e) {
/* 30 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/e/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */