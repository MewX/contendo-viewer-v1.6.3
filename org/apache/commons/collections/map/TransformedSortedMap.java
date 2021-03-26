/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
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
/*     */ public class TransformedSortedMap
/*     */   extends TransformedMap
/*     */   implements SortedMap
/*     */ {
/*     */   private static final long serialVersionUID = -8751771676410385778L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Transformer keyTransformer, Transformer valueTransformer) {
/*  57 */     return new TransformedSortedMap(map, keyTransformer, valueTransformer);
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
/*     */   protected TransformedSortedMap(SortedMap map, Transformer keyTransformer, Transformer valueTransformer) {
/*  73 */     super(map, keyTransformer, valueTransformer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SortedMap getSortedMap() {
/*  83 */     return (SortedMap)this.map;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object firstKey() {
/*  88 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/*  92 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  96 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 100 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 101 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 105 */     SortedMap map = getSortedMap().headMap(toKey);
/* 106 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 110 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 111 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/TransformedSortedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */