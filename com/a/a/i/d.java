/*    */ package com.a.a.i;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.ListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */ {
/*    */   public static <T> List<T> a(List<T> list, T entry) {
/* 20 */     if (entry != null && 
/* 21 */       !list.contains(entry)) {
/* 22 */       list.add(entry);
/*    */     }
/*    */     
/* 25 */     return list;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static <T extends Comparable<? super T>> List<T> a(List<T> list) {
/* 35 */     Collections.sort(list);
/* 36 */     ListIterator<T> it = list.listIterator();
/* 37 */     T prev = null;
/* 38 */     while (it.hasNext()) {
/* 39 */       Comparable comparable2 = (Comparable)it.next();
/* 40 */       if (comparable2.equals(prev)) {
/* 41 */         it.remove();
/*    */       }
/* 43 */       Comparable comparable1 = comparable2;
/*    */     } 
/* 45 */     return list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/i/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */