/*    */ package org.apache.xml.utils;
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
/*    */ public class StringBufferPool
/*    */ {
/* 31 */   private static ObjectPool m_stringBufPool = new ObjectPool(FastStringBuffer.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized FastStringBuffer get() {
/* 42 */     return (FastStringBuffer)m_stringBufPool.getInstance();
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
/*    */   public static synchronized void free(FastStringBuffer sb) {
/* 55 */     sb.setLength(0);
/* 56 */     m_stringBufPool.freeInstance(sb);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/StringBufferPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */