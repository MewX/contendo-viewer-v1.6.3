/*     */ package jp.cssj.homare.xml.b;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class b<K, V>
/*     */   extends a<K, Object>
/*     */ {
/*     */   public b() {
/*  63 */     super(new HashMap<>());
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
/*     */   public void clear() {
/*  80 */     a().clear();
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
/*     */   public boolean remove(Object key, Object value) {
/*  99 */     Collection<Object> valuesForKey = a(key);
/* 100 */     if (valuesForKey == null) {
/* 101 */       return false;
/*     */     }
/* 103 */     boolean removed = valuesForKey.remove(value);
/* 104 */     if (!removed) {
/* 105 */       return false;
/*     */     }
/* 107 */     if (valuesForKey.isEmpty()) {
/* 108 */       remove(key);
/*     */     }
/* 110 */     return true;
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
/*     */   public boolean containsValue(Object value) {
/* 124 */     Set<Map.Entry<K, Object>> pairs = a().entrySet();
/* 125 */     if (pairs == null) {
/* 126 */       return false;
/*     */     }
/* 128 */     Iterator<Map.Entry<K, Object>> pairsIterator = pairs.iterator();
/* 129 */     while (pairsIterator.hasNext()) {
/* 130 */       Map.Entry<K, Object> keyValuePair = pairsIterator.next();
/*     */       
/* 132 */       Collection<Object> coll = (Collection<Object>)keyValuePair.getValue();
/* 133 */       if (coll.contains(value)) {
/* 134 */         return true;
/*     */       }
/*     */     } 
/* 137 */     return false;
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
/*     */   public Object put(K key, Object value) {
/* 153 */     boolean result = false;
/* 154 */     Collection<Object> coll = a(key);
/* 155 */     if (coll == null) {
/* 156 */       coll = new ArrayList(1);
/* 157 */       result = coll.add(value);
/* 158 */       if (coll.size() > 0) {
/*     */         
/* 160 */         a().put(key, coll);
/* 161 */         result = false;
/*     */       } 
/*     */     } else {
/* 164 */       result = coll.add(value);
/*     */     } 
/* 166 */     return result ? value : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(K key, Object value) {
/* 177 */     Collection<Object> coll = a(key);
/* 178 */     if (coll == null) {
/* 179 */       return false;
/*     */     }
/* 181 */     return coll.contains(value);
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
/*     */   public Collection<Object> a(Object key) {
/* 194 */     return (Collection<Object>)a().get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b(Object key) {
/* 205 */     Collection<Object> coll = a(key);
/* 206 */     if (coll == null) {
/* 207 */       return 0;
/*     */     }
/* 209 */     return coll.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 218 */     int total = 0;
/* 219 */     Collection<Object> values = a().values();
/* 220 */     for (Iterator<Object> it = values.iterator(); it.hasNext(); ) {
/*     */       
/* 222 */       Collection<Object> coll = (Collection<Object>)it.next();
/* 223 */       total += coll.size();
/*     */     } 
/* 225 */     return total;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */