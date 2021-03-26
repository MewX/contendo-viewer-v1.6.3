/*    */ package org.apache.commons.io.input;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectStreamClass;
/*    */ import java.io.StreamCorruptedException;
/*    */ import java.lang.reflect.Proxy;
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
/*    */ public class ClassLoaderObjectInputStream
/*    */   extends ObjectInputStream
/*    */ {
/*    */   private final ClassLoader classLoader;
/*    */   
/*    */   public ClassLoaderObjectInputStream(ClassLoader classLoader, InputStream inputStream) throws IOException, StreamCorruptedException {
/* 50 */     super(inputStream);
/* 51 */     this.classLoader = classLoader;
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
/*    */   protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
/*    */     try {
/* 68 */       return Class.forName(objectStreamClass.getName(), false, this.classLoader);
/* 69 */     } catch (ClassNotFoundException cnfe) {
/*    */       
/* 71 */       return super.resolveClass(objectStreamClass);
/*    */     } 
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
/*    */   protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException {
/* 89 */     Class<?>[] interfaceClasses = new Class[interfaces.length];
/* 90 */     for (int i = 0; i < interfaces.length; i++) {
/* 91 */       interfaceClasses[i] = Class.forName(interfaces[i], false, this.classLoader);
/*    */     }
/*    */     try {
/* 94 */       return Proxy.getProxyClass(this.classLoader, interfaceClasses);
/* 95 */     } catch (IllegalArgumentException e) {
/* 96 */       return super.resolveProxyClass(interfaces);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/ClassLoaderObjectInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */