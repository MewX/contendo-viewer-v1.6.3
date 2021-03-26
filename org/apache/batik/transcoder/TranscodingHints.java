/*     */ package org.apache.batik.transcoder;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class TranscodingHints
/*     */   extends HashMap
/*     */ {
/*     */   public TranscodingHints() {
/*  37 */     this(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TranscodingHints(Map init) {
/*  48 */     super(7);
/*  49 */     if (init != null) {
/*  50 */       putAll(init);
/*     */     }
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
/*     */   public boolean containsKey(Object key) {
/*  64 */     return super.containsKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/*  75 */     return super.get(key);
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
/*     */   public Object put(Object key, Object value) {
/*  90 */     if (!((Key)key).isCompatibleValue(value)) {
/*  91 */       throw new IllegalArgumentException(value + " incompatible with " + key);
/*     */     }
/*     */ 
/*     */     
/*  95 */     return super.put(key, value);
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
/*     */   public Object remove(Object key) {
/* 107 */     return super.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAll(TranscodingHints hints) {
/* 116 */     super.putAll(hints);
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
/*     */   public void putAll(Map m) {
/* 128 */     if (m instanceof TranscodingHints) {
/* 129 */       putAll((TranscodingHints)m);
/*     */     } else {
/* 131 */       for (Object o : m.entrySet()) {
/* 132 */         Map.Entry entry = (Map.Entry)o;
/* 133 */         put(entry.getKey(), entry.getValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static abstract class Key {
/*     */     public abstract boolean isCompatibleValue(Object param1Object);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/TranscodingHints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */