/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.list.FixedSizeList;
/*     */ import org.apache.commons.collections.list.LazyList;
/*     */ import org.apache.commons.collections.list.PredicatedList;
/*     */ import org.apache.commons.collections.list.SynchronizedList;
/*     */ import org.apache.commons.collections.list.TransformedList;
/*     */ import org.apache.commons.collections.list.TypedList;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListUtils
/*     */ {
/*  52 */   public static final List EMPTY_LIST = Collections.EMPTY_LIST;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List intersection(List list1, List list2) {
/*  71 */     ArrayList result = new ArrayList();
/*  72 */     Iterator iterator = list2.iterator();
/*     */     
/*  74 */     while (iterator.hasNext()) {
/*  75 */       Object o = iterator.next();
/*     */       
/*  77 */       if (list1.contains(o)) {
/*  78 */         result.add(o);
/*     */       }
/*     */     } 
/*     */     
/*  82 */     return result;
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
/*     */   public static List subtract(List list1, List list2) {
/* 101 */     ArrayList result = new ArrayList(list1);
/* 102 */     Iterator iterator = list2.iterator();
/*     */     
/* 104 */     while (iterator.hasNext()) {
/* 105 */       result.remove(iterator.next());
/*     */     }
/*     */     
/* 108 */     return result;
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
/*     */   public static List sum(List list1, List list2) {
/* 121 */     return subtract(union(list1, list2), intersection(list1, list2));
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
/*     */   public static List union(List list1, List list2) {
/* 135 */     ArrayList result = new ArrayList(list1);
/* 136 */     result.addAll(list2);
/* 137 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEqualList(Collection list1, Collection list2) {
/* 170 */     if (list1 == list2) {
/* 171 */       return true;
/*     */     }
/* 173 */     if (list1 == null || list2 == null || list1.size() != list2.size()) {
/* 174 */       return false;
/*     */     }
/*     */     
/* 177 */     Iterator it1 = list1.iterator();
/* 178 */     Iterator it2 = list2.iterator();
/* 179 */     Object obj1 = null;
/* 180 */     Object obj2 = null;
/*     */     
/* 182 */     while (it1.hasNext() && it2.hasNext()) {
/* 183 */       obj1 = it1.next();
/* 184 */       obj2 = it2.next();
/*     */       
/* 186 */       if ((obj1 == null) ? (obj2 == null) : obj1.equals(obj2))
/* 187 */         continue;  return false;
/*     */     } 
/*     */ 
/*     */     
/* 191 */     return (!it1.hasNext() && !it2.hasNext());
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
/*     */   public static int hashCodeForList(Collection list) {
/* 207 */     if (list == null) {
/* 208 */       return 0;
/*     */     }
/* 210 */     int hashCode = 1;
/* 211 */     Iterator it = list.iterator();
/* 212 */     Object obj = null;
/*     */     
/* 214 */     while (it.hasNext()) {
/* 215 */       obj = it.next();
/* 216 */       hashCode = 31 * hashCode + ((obj == null) ? 0 : obj.hashCode());
/*     */     } 
/* 218 */     return hashCode;
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
/*     */   public static List synchronizedList(List list) {
/* 245 */     return SynchronizedList.decorate(list);
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
/*     */   public static List unmodifiableList(List list) {
/* 258 */     return UnmodifiableList.decorate(list);
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
/*     */   public static List predicatedList(List list, Predicate predicate) {
/* 275 */     return PredicatedList.decorate(list, predicate);
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
/*     */   public static List typedList(List list, Class type) {
/* 288 */     return TypedList.decorate(list, type);
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
/*     */   public static List transformedList(List list, Transformer transformer) {
/* 304 */     return TransformedList.decorate(list, transformer);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List lazyList(List list, Factory factory) {
/* 337 */     return LazyList.decorate(list, factory);
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
/*     */   public static List fixedSizeList(List list) {
/* 351 */     return FixedSizeList.decorate(list);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/ListUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */