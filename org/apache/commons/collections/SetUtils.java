/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.collections.set.ListOrderedSet;
/*     */ import org.apache.commons.collections.set.PredicatedSet;
/*     */ import org.apache.commons.collections.set.PredicatedSortedSet;
/*     */ import org.apache.commons.collections.set.SynchronizedSet;
/*     */ import org.apache.commons.collections.set.SynchronizedSortedSet;
/*     */ import org.apache.commons.collections.set.TransformedSet;
/*     */ import org.apache.commons.collections.set.TransformedSortedSet;
/*     */ import org.apache.commons.collections.set.TypedSet;
/*     */ import org.apache.commons.collections.set.TypedSortedSet;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ import org.apache.commons.collections.set.UnmodifiableSortedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SetUtils
/*     */ {
/*  56 */   public static final Set EMPTY_SET = Collections.EMPTY_SET;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public static final SortedSet EMPTY_SORTED_SET = UnmodifiableSortedSet.decorate(new TreeSet());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEqualSet(Collection set1, Collection set2) {
/*  99 */     if (set1 == set2) {
/* 100 */       return true;
/*     */     }
/* 102 */     if (set1 == null || set2 == null || set1.size() != set2.size()) {
/* 103 */       return false;
/*     */     }
/*     */     
/* 106 */     return set1.containsAll(set2);
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
/*     */   public static int hashCodeForSet(Collection set) {
/* 122 */     if (set == null) {
/* 123 */       return 0;
/*     */     }
/* 125 */     int hashCode = 0;
/* 126 */     Iterator it = set.iterator();
/* 127 */     Object obj = null;
/*     */     
/* 129 */     while (it.hasNext()) {
/* 130 */       obj = it.next();
/* 131 */       if (obj != null) {
/* 132 */         hashCode += obj.hashCode();
/*     */       }
/*     */     } 
/* 135 */     return hashCode;
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
/*     */   public static Set synchronizedSet(Set set) {
/* 162 */     return SynchronizedSet.decorate(set);
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
/*     */   public static Set unmodifiableSet(Set set) {
/* 175 */     return UnmodifiableSet.decorate(set);
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
/*     */   public static Set predicatedSet(Set set, Predicate predicate) {
/* 192 */     return PredicatedSet.decorate(set, predicate);
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
/*     */   public static Set typedSet(Set set, Class type) {
/* 205 */     return TypedSet.decorate(set, type);
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
/*     */   public static Set transformedSet(Set set, Transformer transformer) {
/* 221 */     return TransformedSet.decorate(set, transformer);
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
/*     */   public static Set orderedSet(Set set) {
/* 236 */     return (Set)ListOrderedSet.decorate(set);
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
/*     */   public static SortedSet synchronizedSortedSet(SortedSet set) {
/* 263 */     return SynchronizedSortedSet.decorate(set);
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
/*     */   public static SortedSet unmodifiableSortedSet(SortedSet set) {
/* 276 */     return UnmodifiableSortedSet.decorate(set);
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
/*     */   public static SortedSet predicatedSortedSet(SortedSet set, Predicate predicate) {
/* 293 */     return PredicatedSortedSet.decorate(set, predicate);
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
/*     */   public static SortedSet typedSortedSet(SortedSet set, Class type) {
/* 306 */     return TypedSortedSet.decorate(set, type);
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
/*     */   public static SortedSet transformedSortedSet(SortedSet set, Transformer transformer) {
/* 322 */     return TransformedSortedSet.decorate(set, transformer);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/SetUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */