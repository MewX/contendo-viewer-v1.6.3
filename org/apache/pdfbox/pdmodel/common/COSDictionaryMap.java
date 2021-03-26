/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
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
/*     */ public class COSDictionaryMap<K, V>
/*     */   implements Map<K, V>
/*     */ {
/*     */   private final COSDictionary map;
/*     */   private final Map<K, V> actuals;
/*     */   
/*     */   public COSDictionaryMap(Map<K, V> actualsMap, COSDictionary dicMap) {
/*  52 */     this.actuals = actualsMap;
/*  53 */     this.map = dicMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  63 */     return this.map.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  72 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  81 */     return this.actuals.containsKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  90 */     return this.actuals.containsValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V get(Object key) {
/*  99 */     return this.actuals.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V put(K key, V value) {
/* 108 */     COSObjectable object = (COSObjectable)value;
/*     */     
/* 110 */     this.map.setItem(COSName.getPDFName((String)key), object.getCOSObject());
/* 111 */     return this.actuals.put(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V remove(Object key) {
/* 120 */     this.map.removeItem(COSName.getPDFName((String)key));
/* 121 */     return this.actuals.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> t) {
/* 130 */     throw new RuntimeException("Not yet implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 139 */     this.map.clear();
/* 140 */     this.actuals.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<K> keySet() {
/* 149 */     return this.actuals.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<V> values() {
/* 158 */     return this.actuals.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 167 */     return Collections.unmodifiableSet(this.actuals.entrySet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 176 */     boolean retval = false;
/* 177 */     if (o instanceof COSDictionaryMap) {
/*     */       
/* 179 */       COSDictionaryMap<K, V> other = (COSDictionaryMap<K, V>)o;
/* 180 */       retval = other.map.equals(this.map);
/*     */     } 
/* 182 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 191 */     return this.actuals.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 200 */     return this.map.hashCode();
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
/*     */   public static COSDictionary convert(Map<String, ?> someMap) {
/* 213 */     COSDictionary dic = new COSDictionary();
/* 214 */     for (Map.Entry<String, ?> entry : someMap.entrySet()) {
/*     */       
/* 216 */       String name = entry.getKey();
/* 217 */       COSObjectable object = (COSObjectable)entry.getValue();
/* 218 */       dic.setItem(COSName.getPDFName(name), object.getCOSObject());
/*     */     } 
/* 220 */     return dic;
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
/*     */   public static COSDictionaryMap<String, Object> convertBasicTypesToMap(COSDictionary map) throws IOException {
/* 233 */     COSDictionaryMap<String, Object> retval = null;
/* 234 */     if (map != null) {
/*     */       
/* 236 */       Map<String, Object> actualMap = new HashMap<String, Object>();
/* 237 */       for (COSName key : map.keySet()) {
/*     */         
/* 239 */         COSBase cosObj = map.getDictionaryObject(key);
/* 240 */         Object actualObject = null;
/* 241 */         if (cosObj instanceof COSString) {
/*     */           
/* 243 */           actualObject = ((COSString)cosObj).getString();
/*     */         }
/* 245 */         else if (cosObj instanceof COSInteger) {
/*     */           
/* 247 */           actualObject = Integer.valueOf(((COSInteger)cosObj).intValue());
/*     */         }
/* 249 */         else if (cosObj instanceof COSName) {
/*     */           
/* 251 */           actualObject = ((COSName)cosObj).getName();
/*     */         }
/* 253 */         else if (cosObj instanceof COSFloat) {
/*     */           
/* 255 */           actualObject = Float.valueOf(((COSFloat)cosObj).floatValue());
/*     */         }
/* 257 */         else if (cosObj instanceof COSBoolean) {
/*     */           
/* 259 */           actualObject = ((COSBoolean)cosObj).getValue() ? Boolean.TRUE : Boolean.FALSE;
/*     */         }
/*     */         else {
/*     */           
/* 263 */           throw new IOException("Error:unknown type of object to convert:" + cosObj);
/*     */         } 
/* 265 */         actualMap.put(key.getName(), actualObject);
/*     */       } 
/* 267 */       retval = new COSDictionaryMap<String, Object>(actualMap, map);
/*     */     } 
/*     */     
/* 270 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/COSDictionaryMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */