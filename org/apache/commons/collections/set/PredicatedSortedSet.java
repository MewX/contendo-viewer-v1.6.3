/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
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
/*     */ public class PredicatedSortedSet
/*     */   extends PredicatedSet
/*     */   implements SortedSet
/*     */ {
/*     */   private static final long serialVersionUID = -9110948148132275052L;
/*     */   
/*     */   public static SortedSet decorate(SortedSet set, Predicate predicate) {
/*  59 */     return new PredicatedSortedSet(set, predicate);
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
/*     */   protected PredicatedSortedSet(SortedSet set, Predicate predicate) {
/*  75 */     super(set, predicate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SortedSet getSortedSet() {
/*  84 */     return (SortedSet)getCollection();
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/*  89 */     SortedSet sub = getSortedSet().subSet(fromElement, toElement);
/*  90 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/*  94 */     SortedSet sub = getSortedSet().headSet(toElement);
/*  95 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/*  99 */     SortedSet sub = getSortedSet().tailSet(fromElement);
/* 100 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public Object first() {
/* 104 */     return getSortedSet().first();
/*     */   }
/*     */   
/*     */   public Object last() {
/* 108 */     return getSortedSet().last();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 112 */     return getSortedSet().comparator();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/PredicatedSortedSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */