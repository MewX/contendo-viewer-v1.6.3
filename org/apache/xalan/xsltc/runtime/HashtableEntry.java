/*    */ package org.apache.xalan.xsltc.runtime;
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
/*    */ class HashtableEntry
/*    */ {
/*    */   int hash;
/*    */   Object key;
/*    */   Object value;
/*    */   HashtableEntry next;
/*    */   
/*    */   protected Object clone() {
/* 42 */     HashtableEntry entry = new HashtableEntry();
/* 43 */     entry.hash = this.hash;
/* 44 */     entry.key = this.key;
/* 45 */     entry.value = this.value;
/* 46 */     entry.next = (this.next != null) ? (HashtableEntry)this.next.clone() : null;
/* 47 */     return entry;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/HashtableEntry.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */