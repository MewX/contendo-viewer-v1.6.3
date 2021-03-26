/*    */ package org.apache.xml.serializer;
/*    */ 
/*    */ import java.util.Hashtable;
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
/*    */ class Utils
/*    */ {
/*    */   private static class CacheHolder
/*    */   {
/* 37 */     static final Hashtable cache = new Hashtable();
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
/*    */ 
/*    */   
/*    */   static Class ClassForName(String classname) throws ClassNotFoundException {
/*    */     Class clazz;
/* 59 */     Object o = CacheHolder.cache.get(classname);
/* 60 */     if (o == null) {
/*    */ 
/*    */       
/* 63 */       clazz = Class.forName(classname);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 68 */       CacheHolder.cache.put(classname, clazz);
/*    */     }
/*    */     else {
/*    */       
/* 72 */       clazz = (Class)o;
/*    */     } 
/* 74 */     return clazz;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/Utils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */