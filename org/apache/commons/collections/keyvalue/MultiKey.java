/*     */ package org.apache.commons.collections.keyvalue;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
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
/*     */ public class MultiKey
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4465448607415788805L;
/*     */   private final Object[] keys;
/*     */   private final int hashCode;
/*     */   
/*     */   public MultiKey(Object key1, Object key2) {
/*  68 */     this(new Object[] { key1, key2 }, false);
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
/*     */   public MultiKey(Object key1, Object key2, Object key3) {
/*  82 */     this(new Object[] { key1, key2, key3 }, false);
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
/*     */   
/*     */   public MultiKey(Object key1, Object key2, Object key3, Object key4) {
/*  97 */     this(new Object[] { key1, key2, key3, key4 }, false);
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
/*     */ 
/*     */   
/*     */   public MultiKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 113 */     this(new Object[] { key1, key2, key3, key4, key5 }, false);
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
/*     */   
/*     */   public MultiKey(Object[] keys) {
/* 128 */     this(keys, true);
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
/*     */   public MultiKey(Object[] keys, boolean makeClone) {
/* 157 */     if (keys == null) {
/* 158 */       throw new IllegalArgumentException("The array of keys must not be null");
/*     */     }
/* 160 */     if (makeClone) {
/* 161 */       this.keys = (Object[])keys.clone();
/*     */     } else {
/* 163 */       this.keys = keys;
/*     */     } 
/*     */     
/* 166 */     int total = 0;
/* 167 */     for (int i = 0; i < keys.length; i++) {
/* 168 */       if (keys[i] != null) {
/* 169 */         total ^= keys[i].hashCode();
/*     */       }
/*     */     } 
/* 172 */     this.hashCode = total;
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
/*     */   public Object[] getKeys() {
/* 185 */     return (Object[])this.keys.clone();
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
/*     */   
/*     */   public Object getKey(int index) {
/* 200 */     return this.keys[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 210 */     return this.keys.length;
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
/*     */   public boolean equals(Object other) {
/* 224 */     if (other == this) {
/* 225 */       return true;
/*     */     }
/* 227 */     if (other instanceof MultiKey) {
/* 228 */       MultiKey otherMulti = (MultiKey)other;
/* 229 */       return Arrays.equals(this.keys, otherMulti.keys);
/*     */     } 
/* 231 */     return false;
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
/* 245 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 254 */     return "MultiKey" + Arrays.<Object>asList(this.keys).toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/keyvalue/MultiKey.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */