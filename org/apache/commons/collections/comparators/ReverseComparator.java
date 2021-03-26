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
/*     */ public class ReverseComparator
/*     */   implements Serializable, Comparator
/*     */ {
/*     */   private static final long serialVersionUID = 2858887242028539265L;
/*     */   private Comparator comparator;
/*     */   
/*     */   public ReverseComparator() {
/*  51 */     this(null);
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
/*     */   public ReverseComparator(Comparator comparator) {
/*  64 */     if (comparator != null) {
/*  65 */       this.comparator = comparator;
/*     */     } else {
/*  67 */       this.comparator = ComparableComparator.getInstance();
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
/*     */   public int compare(Object obj1, Object obj2) {
/*  80 */     return this.comparator.compare(obj2, obj1);
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
/*     */   public int hashCode() {
/*  92 */     return "ReverseComparator".hashCode() ^ this.comparator.hashCode();
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
/*     */   public boolean equals(Object object) {
/* 112 */     if (this == object)
/* 113 */       return true; 
/* 114 */     if (null == object)
/* 115 */       return false; 
/* 116 */     if (object.getClass().equals(getClass())) {
/* 117 */       ReverseComparator thatrc = (ReverseComparator)object;
/* 118 */       return this.comparator.equals(thatrc.comparator);
/*     */     } 
/* 120 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/comparators/ReverseComparator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */