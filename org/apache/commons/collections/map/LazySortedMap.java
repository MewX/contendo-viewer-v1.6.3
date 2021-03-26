/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.Transformer;
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
/*     */ public class LazySortedMap
/*     */   extends LazyMap
/*     */   implements SortedMap
/*     */ {
/*     */   private static final long serialVersionUID = 2715322183617658933L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Factory factory) {
/*  69 */     return new LazySortedMap(map, factory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Transformer factory) {
/*  80 */     return new LazySortedMap(map, factory);
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
/*     */   protected LazySortedMap(SortedMap map, Factory factory) {
/*  92 */     super(map, factory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LazySortedMap(SortedMap map, Transformer factory) {
/* 103 */     super(map, factory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SortedMap getSortedMap() {
/* 113 */     return (SortedMap)this.map;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object firstKey() {
/* 118 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 122 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 126 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 130 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 131 */     return new LazySortedMap(map, this.factory);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 135 */     SortedMap map = getSortedMap().headMap(toKey);
/* 136 */     return new LazySortedMap(map, this.factory);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 140 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 141 */     return new LazySortedMap(map, this.factory);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/LazySortedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */