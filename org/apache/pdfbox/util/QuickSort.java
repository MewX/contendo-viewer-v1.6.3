/*     */ package org.apache.pdfbox.util;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Comparator;
/*     */ import java.util.Deque;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class QuickSort
/*     */ {
/*  37 */   private static final Comparator<? extends Comparable> OBJCOMP = new Comparator<Comparable>()
/*     */     {
/*     */       
/*     */       public int compare(Comparable<Comparable> object1, Comparable object2)
/*     */       {
/*  42 */         return object1.compareTo(object2);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> void sort(List<T> list, Comparator<T> cmp) {
/*  55 */     int size = list.size();
/*  56 */     if (size < 2) {
/*     */       return;
/*     */     }
/*     */     
/*  60 */     quicksort(list, cmp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends Comparable> void sort(List<T> list) {
/*  71 */     sort(list, (Comparator)OBJCOMP);
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> void quicksort(List<T> list, Comparator<T> cmp) {
/*  76 */     Deque<Integer> stack = new ArrayDeque<Integer>();
/*  77 */     stack.push(Integer.valueOf(0));
/*  78 */     stack.push(Integer.valueOf(list.size()));
/*  79 */     while (!stack.isEmpty()) {
/*     */       
/*  81 */       int right = ((Integer)stack.pop()).intValue();
/*  82 */       int left = ((Integer)stack.pop()).intValue();
/*  83 */       if (right - left < 2) {
/*     */         continue;
/*     */       }
/*     */       
/*  87 */       int p = left + (right - left) / 2;
/*  88 */       p = partition(list, cmp, p, left, right);
/*     */       
/*  90 */       stack.push(Integer.valueOf(p + 1));
/*  91 */       stack.push(Integer.valueOf(right));
/*     */       
/*  93 */       stack.push(Integer.valueOf(left));
/*  94 */       stack.push(Integer.valueOf(p));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> int partition(List<T> list, Comparator<T> cmp, int p, int start, int end) {
/* 100 */     int l = start;
/* 101 */     int h = end - 2;
/* 102 */     T piv = list.get(p);
/* 103 */     swap(list, p, end - 1);
/*     */     
/* 105 */     while (l < h) {
/*     */       
/* 107 */       if (cmp.compare(list.get(l), piv) <= 0) {
/*     */         
/* 109 */         l++; continue;
/*     */       } 
/* 111 */       if (cmp.compare(piv, list.get(h)) <= 0) {
/*     */         
/* 113 */         h--;
/*     */         
/*     */         continue;
/*     */       } 
/* 117 */       swap(list, l, h);
/*     */     } 
/*     */     
/* 120 */     int idx = h;
/* 121 */     if (cmp.compare(list.get(h), piv) < 0)
/*     */     {
/* 123 */       idx++;
/*     */     }
/* 125 */     swap(list, end - 1, idx);
/* 126 */     return idx;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> void swap(List<T> list, int i, int j) {
/* 131 */     T tmp = list.get(i);
/* 132 */     list.set(i, list.get(j));
/* 133 */     list.set(j, tmp);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/QuickSort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */