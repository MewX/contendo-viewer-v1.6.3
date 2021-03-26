/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ import java.util.Vector;
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
/*    */ public final class MultiHashtable
/*    */   extends Hashtable
/*    */ {
/*    */   public Object put(Object key, Object value) {
/* 31 */     Vector vector = (Vector)get(key);
/* 32 */     if (vector == null)
/* 33 */       super.put(key, vector = new Vector()); 
/* 34 */     vector.add(value);
/* 35 */     return vector;
/*    */   }
/*    */   
/*    */   public Object maps(Object from, Object to) {
/* 39 */     if (from == null) return null; 
/* 40 */     Vector vector = (Vector)get(from);
/* 41 */     if (vector != null) {
/* 42 */       int n = vector.size();
/* 43 */       for (int i = 0; i < n; i++) {
/* 44 */         Object item = vector.elementAt(i);
/* 45 */         if (item.equals(to)) {
/* 46 */           return item;
/*    */         }
/*    */       } 
/*    */     } 
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/MultiHashtable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */