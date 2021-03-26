/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NullComparator
/*     */   implements Serializable, Comparator
/*     */ {
/*     */   private static final long serialVersionUID = -5820772575483504339L;
/*     */   private Comparator nonNullComparator;
/*     */   private boolean nullsAreHigh;
/*     */   
/*     */   public NullComparator() {
/*  54 */     this(ComparableComparator.getInstance(), true);
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
/*     */   public NullComparator(Comparator nonNullComparator) {
/*  71 */     this(nonNullComparator, true);
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
/*     */   public NullComparator(boolean nullsAreHigh) {
/*  87 */     this(ComparableComparator.getInstance(), nullsAreHigh);
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
/*     */   public NullComparator(Comparator nonNullComparator, boolean nullsAreHigh) {
/* 110 */     this.nonNullComparator = nonNullComparator;
/* 111 */     this.nullsAreHigh = nullsAreHigh;
/*     */     
/* 113 */     if (nonNullComparator == null) {
/* 114 */       throw new NullPointerException("null nonNullComparator");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compare(Object o1, Object o2) {
/* 136 */     if (o1 == o2) return 0; 
/* 137 */     if (o1 == null) return this.nullsAreHigh ? 1 : -1; 
/* 138 */     if (o2 == null) return this.nullsAreHigh ? -1 : 1; 
/* 139 */     return this.nonNullComparator.compare(o1, o2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 150 */     return (this.nullsAreHigh ? -1 : 1) * this.nonNullComparator.hashCode();
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
/*     */   public boolean equals(Object obj) {
/* 165 */     if (obj == null) return false; 
/* 166 */     if (obj == this) return true; 
/* 167 */     if (!obj.getClass().equals(getClass())) return false;
/*     */     
/* 169 */     NullComparator other = (NullComparator)obj;
/*     */     
/* 171 */     return (this.nullsAreHigh == other.nullsAreHigh && this.nonNullComparator.equals(other.nonNullComparator));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/comparators/NullComparator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */