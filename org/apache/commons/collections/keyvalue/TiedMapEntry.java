/*     */ package org.apache.commons.collections.keyvalue;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TiedMapEntry
/*     */   implements Serializable, Map.Entry, KeyValue
/*     */ {
/*     */   private static final long serialVersionUID = -8453869361373831205L;
/*     */   private final Map map;
/*     */   private final Object key;
/*     */   
/*     */   public TiedMapEntry(Map map, Object key) {
/*  52 */     this.map = map;
/*  53 */     this.key = key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getKey() {
/*  64 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/*  73 */     return this.map.get(this.key);
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
/*  84 */     if (value == this) {
/*  85 */       throw new IllegalArgumentException("Cannot set value to this map entry");
/*     */     }
/*  87 */     return this.map.put(this.key, value);
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
/*     */   public boolean equals(Object obj) {
/*  99 */     if (obj == this) {
/* 100 */       return true;
/*     */     }
/* 102 */     if (!(obj instanceof Map.Entry)) {
/* 103 */       return false;
/*     */     }
/* 105 */     Map.Entry other = (Map.Entry)obj;
/* 106 */     Object value = getValue();
/* 107 */     return (((this.key == null) ? (other.getKey() == null) : this.key.equals(other.getKey())) && ((value == null) ? (other.getValue() == null) : value.equals(other.getValue())));
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
/*     */   public int hashCode() {
/* 120 */     Object value = getValue();
/* 121 */     return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((value == null) ? 0 : value.hashCode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 131 */     return getKey() + "=" + getValue();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/keyvalue/TiedMapEntry.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */