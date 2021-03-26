/*     */ package org.apache.commons.collections.keyvalue;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.KeyValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultKeyValue
/*     */   extends AbstractKeyValue
/*     */ {
/*     */   public DefaultKeyValue() {
/*  42 */     super(null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultKeyValue(Object key, Object value) {
/*  52 */     super(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultKeyValue(KeyValue pair) {
/*  62 */     super(pair.getKey(), pair.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultKeyValue(Map.Entry entry) {
/*  72 */     super(entry.getKey(), entry.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object setKey(Object key) {
/*  84 */     if (key == this) {
/*  85 */       throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a key.");
/*     */     }
/*     */     
/*  88 */     Object old = this.key;
/*  89 */     this.key = key;
/*  90 */     return old;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object setValue(Object value) {
/* 101 */     if (value == this) {
/* 102 */       throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a value.");
/*     */     }
/*     */     
/* 105 */     Object old = this.value;
/* 106 */     this.value = value;
/* 107 */     return old;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map.Entry toMapEntry() {
/* 117 */     return new DefaultMapEntry(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 131 */     if (obj == this) {
/* 132 */       return true;
/*     */     }
/* 134 */     if (!(obj instanceof DefaultKeyValue)) {
/* 135 */       return false;
/*     */     }
/*     */     
/* 138 */     DefaultKeyValue other = (DefaultKeyValue)obj;
/* 139 */     return (((getKey() == null) ? (other.getKey() == null) : getKey().equals(other.getKey())) && ((getValue() == null) ? (other.getValue() == null) : getValue().equals(other.getValue())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 153 */     return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/keyvalue/DefaultKeyValue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */