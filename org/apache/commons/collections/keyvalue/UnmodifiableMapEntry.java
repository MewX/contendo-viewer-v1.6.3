/*    */ package org.apache.commons.collections.keyvalue;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.KeyValue;
/*    */ import org.apache.commons.collections.Unmodifiable;
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
/*    */ public final class UnmodifiableMapEntry
/*    */   extends AbstractMapEntry
/*    */   implements Unmodifiable
/*    */ {
/*    */   public UnmodifiableMapEntry(Object key, Object value) {
/* 41 */     super(key, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UnmodifiableMapEntry(KeyValue pair) {
/* 51 */     super(pair.getKey(), pair.getValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UnmodifiableMapEntry(Map.Entry entry) {
/* 61 */     super(entry.getKey(), entry.getValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object setValue(Object value) {
/* 72 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/keyvalue/UnmodifiableMapEntry.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */