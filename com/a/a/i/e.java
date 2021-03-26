/*    */ package com.a.a.i;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class e {
/*    */   public static <K, V> void a(Map<K, V> dest, Map<K, V> source, Object[] keys1, Object[] keys2) {
/*  7 */     int max = Math.min(keys1.length, keys2.length);
/*    */     
/*  9 */     for (int i = 0; i < max; i++) {
/* 10 */       K key = (K)keys2[i];
/* 11 */       if (source.containsKey(key)) {
/* 12 */         V obj = source.get(key);
/* 13 */         dest.put((K)keys1[i], obj);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static <K, V> void a(Map<K, V> dest, Map<K, V> source, Object[] keys) {
/* 19 */     a(dest, source, (K[])keys, (K[])keys);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/i/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */