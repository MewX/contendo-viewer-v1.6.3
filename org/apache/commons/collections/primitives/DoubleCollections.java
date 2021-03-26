/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableDoubleIterator;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableDoubleList;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableDoubleListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DoubleCollections
/*     */ {
/*     */   public static DoubleList singletonDoubleList(double value) {
/*  42 */     DoubleList list = new ArrayDoubleList(1);
/*  43 */     list.add(value);
/*  44 */     return UnmodifiableDoubleList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DoubleIterator singletonDoubleIterator(double value) {
/*  53 */     return singletonDoubleList(value).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DoubleListIterator singletonDoubleListIterator(double value) {
/*  62 */     return singletonDoubleList(value).listIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DoubleList unmodifiableDoubleList(DoubleList list) throws NullPointerException {
/*  73 */     if (null == list) {
/*  74 */       throw new NullPointerException();
/*     */     }
/*  76 */     return UnmodifiableDoubleList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DoubleIterator unmodifiableDoubleIterator(DoubleIterator iter) {
/*  87 */     if (null == iter) {
/*  88 */       throw new NullPointerException();
/*     */     }
/*  90 */     return UnmodifiableDoubleIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DoubleListIterator unmodifiableDoubleListIterator(DoubleListIterator iter) {
/* 101 */     if (null == iter) {
/* 102 */       throw new NullPointerException();
/*     */     }
/* 104 */     return UnmodifiableDoubleListIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DoubleList getEmptyDoubleList() {
/* 113 */     return EMPTY_DOUBLE_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DoubleIterator getEmptyDoubleIterator() {
/* 122 */     return EMPTY_DOUBLE_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DoubleListIterator getEmptyDoubleListIterator() {
/* 131 */     return EMPTY_DOUBLE_LIST_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final DoubleList EMPTY_DOUBLE_LIST = unmodifiableDoubleList(new ArrayDoubleList(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final DoubleIterator EMPTY_DOUBLE_ITERATOR = unmodifiableDoubleIterator(EMPTY_DOUBLE_LIST.iterator());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final DoubleListIterator EMPTY_DOUBLE_LIST_ITERATOR = unmodifiableDoubleListIterator(EMPTY_DOUBLE_LIST.listIterator());
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/DoubleCollections.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */