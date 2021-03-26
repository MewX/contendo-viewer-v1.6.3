/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformedSortedSet
/*     */   extends TransformedSet
/*     */   implements SortedSet
/*     */ {
/*     */   private static final long serialVersionUID = -1675486811351124386L;
/*     */   
/*     */   public static SortedSet decorate(SortedSet set, Transformer transformer) {
/*  54 */     return new TransformedSortedSet(set, transformer);
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
/*     */   protected TransformedSortedSet(SortedSet set, Transformer transformer) {
/*  69 */     super(set, transformer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SortedSet getSortedSet() {
/*  78 */     return (SortedSet)((AbstractCollectionDecorator)this).collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object first() {
/*  83 */     return getSortedSet().first();
/*     */   }
/*     */   
/*     */   public Object last() {
/*  87 */     return getSortedSet().last();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  91 */     return getSortedSet().comparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/*  96 */     SortedSet set = getSortedSet().subSet(fromElement, toElement);
/*  97 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/* 101 */     SortedSet set = getSortedSet().headSet(toElement);
/* 102 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 106 */     SortedSet set = getSortedSet().tailSet(fromElement);
/* 107 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/TransformedSortedSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */