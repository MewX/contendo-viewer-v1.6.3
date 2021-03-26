/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedSortedMap
/*     */   extends PredicatedMap
/*     */   implements SortedMap
/*     */ {
/*     */   private static final long serialVersionUID = 3359846175935304332L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Predicate keyPredicate, Predicate valuePredicate) {
/*  61 */     return new PredicatedSortedMap(map, keyPredicate, valuePredicate);
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
/*     */   protected PredicatedSortedMap(SortedMap map, Predicate keyPredicate, Predicate valuePredicate) {
/*  74 */     super(map, keyPredicate, valuePredicate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SortedMap getSortedMap() {
/*  84 */     return (SortedMap)this.map;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object firstKey() {
/*  89 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/*  93 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  97 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 101 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 102 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 106 */     SortedMap map = getSortedMap().headMap(toKey);
/* 107 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 111 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 112 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/PredicatedSortedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */