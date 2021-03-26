/*    */ package org.apache.batik.util;
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
/*    */ public class DoublyIndexedSet
/*    */ {
/* 33 */   protected DoublyIndexedTable table = new DoublyIndexedTable();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   protected static Object value = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int size() {
/* 44 */     return this.table.size();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(Object o1, Object o2) {
/* 51 */     this.table.put(o1, o2, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(Object o1, Object o2) {
/* 58 */     this.table.remove(o1, o2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean contains(Object o1, Object o2) {
/* 65 */     return (this.table.get(o1, o2) != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void clear() {
/* 72 */     this.table.clear();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/DoublyIndexedSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */