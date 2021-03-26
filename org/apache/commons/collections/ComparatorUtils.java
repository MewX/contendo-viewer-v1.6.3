/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.collections.comparators.BooleanComparator;
/*     */ import org.apache.commons.collections.comparators.ComparableComparator;
/*     */ import org.apache.commons.collections.comparators.ComparatorChain;
/*     */ import org.apache.commons.collections.comparators.NullComparator;
/*     */ import org.apache.commons.collections.comparators.ReverseComparator;
/*     */ import org.apache.commons.collections.comparators.TransformingComparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComparatorUtils
/*     */ {
/*  56 */   public static final Comparator NATURAL_COMPARATOR = (Comparator)ComparableComparator.getInstance();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Comparator naturalComparator() {
/*  64 */     return NATURAL_COMPARATOR;
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
/*     */   public static Comparator chainedComparator(Comparator comparator1, Comparator comparator2) {
/*  79 */     return chainedComparator(new Comparator[] { comparator1, comparator2 });
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
/*     */   public static Comparator chainedComparator(Comparator[] comparators) {
/*  92 */     ComparatorChain chain = new ComparatorChain();
/*  93 */     for (int i = 0; i < comparators.length; i++) {
/*  94 */       if (comparators[i] == null) {
/*  95 */         throw new NullPointerException("Comparator cannot be null");
/*     */       }
/*  97 */       chain.addComparator(comparators[i]);
/*     */     } 
/*  99 */     return (Comparator)chain;
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
/*     */   public static Comparator chainedComparator(Collection comparators) {
/* 114 */     return chainedComparator((Comparator[])comparators.toArray((Object[])new Comparator[comparators.size()]));
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
/*     */   public static Comparator reversedComparator(Comparator comparator) {
/* 127 */     if (comparator == null) {
/* 128 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 130 */     return (Comparator)new ReverseComparator(comparator);
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
/*     */   public static Comparator booleanComparator(boolean trueFirst) {
/* 146 */     return (Comparator)BooleanComparator.getBooleanComparator(trueFirst);
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
/*     */   public static Comparator nullLowComparator(Comparator comparator) {
/* 161 */     if (comparator == null) {
/* 162 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 164 */     return (Comparator)new NullComparator(comparator, false);
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
/*     */   public static Comparator nullHighComparator(Comparator comparator) {
/* 179 */     if (comparator == null) {
/* 180 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 182 */     return (Comparator)new NullComparator(comparator, true);
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
/*     */   public static Comparator transformedComparator(Comparator comparator, Transformer transformer) {
/* 198 */     if (comparator == null) {
/* 199 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 201 */     return (Comparator)new TransformingComparator(transformer, comparator);
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
/*     */   public static Object min(Object o1, Object o2, Comparator comparator) {
/* 215 */     if (comparator == null) {
/* 216 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 218 */     int c = comparator.compare(o1, o2);
/* 219 */     return (c < 0) ? o1 : o2;
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
/*     */   public static Object max(Object o1, Object o2, Comparator comparator) {
/* 233 */     if (comparator == null) {
/* 234 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 236 */     int c = comparator.compare(o1, o2);
/* 237 */     return (c > 0) ? o1 : o2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/ComparatorUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */