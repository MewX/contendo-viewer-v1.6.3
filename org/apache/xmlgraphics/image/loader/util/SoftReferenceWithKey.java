/*    */ package org.apache.xmlgraphics.image.loader.util;
/*    */ 
/*    */ import java.lang.ref.ReferenceQueue;
/*    */ import java.lang.ref.SoftReference;
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
/*    */ public class SoftReferenceWithKey
/*    */   extends SoftReference
/*    */ {
/*    */   private Object key;
/*    */   
/*    */   public SoftReferenceWithKey(Object referent, Object key, ReferenceQueue<? super T> q) {
/* 40 */     super((T)referent, q);
/* 41 */     this.key = key;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getKey() {
/* 49 */     return this.key;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/util/SoftReferenceWithKey.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */