/*    */ package org.apache.commons.collections.keyvalue;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.KeyValue;
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
/*    */ public abstract class AbstractMapEntryDecorator
/*    */   implements Map.Entry, KeyValue
/*    */ {
/*    */   protected final Map.Entry entry;
/*    */   
/*    */   public AbstractMapEntryDecorator(Map.Entry entry) {
/* 43 */     if (entry == null) {
/* 44 */       throw new IllegalArgumentException("Map Entry must not be null");
/*    */     }
/* 46 */     this.entry = entry;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Map.Entry getMapEntry() {
/* 55 */     return this.entry;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getKey() {
/* 60 */     return this.entry.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 64 */     return this.entry.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object object) {
/* 68 */     return this.entry.setValue(object);
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 72 */     if (object == this) {
/* 73 */       return true;
/*    */     }
/* 75 */     return this.entry.equals(object);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 79 */     return this.entry.hashCode();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 83 */     return this.entry.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/keyvalue/AbstractMapEntryDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */