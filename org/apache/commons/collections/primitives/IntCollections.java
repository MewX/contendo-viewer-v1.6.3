/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableIntIterator;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableIntList;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableIntListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class IntCollections
/*     */ {
/*     */   public static IntList singletonIntList(int value) {
/*  42 */     IntList list = new ArrayIntList(1);
/*  43 */     list.add(value);
/*  44 */     return UnmodifiableIntList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntIterator singletonIntIterator(int value) {
/*  53 */     return singletonIntList(value).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntListIterator singletonIntListIterator(int value) {
/*  62 */     return singletonIntList(value).listIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntList unmodifiableIntList(IntList list) throws NullPointerException {
/*  73 */     if (null == list) {
/*  74 */       throw new NullPointerException();
/*     */     }
/*  76 */     return UnmodifiableIntList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntIterator unmodifiableIntIterator(IntIterator iter) {
/*  87 */     if (null == iter) {
/*  88 */       throw new NullPointerException();
/*     */     }
/*  90 */     return UnmodifiableIntIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntListIterator unmodifiableIntListIterator(IntListIterator iter) {
/* 101 */     if (null == iter) {
/* 102 */       throw new NullPointerException();
/*     */     }
/* 104 */     return UnmodifiableIntListIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntList getEmptyIntList() {
/* 113 */     return EMPTY_INT_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntIterator getEmptyIntIterator() {
/* 122 */     return EMPTY_INT_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntListIterator getEmptyIntListIterator() {
/* 131 */     return EMPTY_INT_LIST_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final IntList EMPTY_INT_LIST = unmodifiableIntList(new ArrayIntList(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final IntIterator EMPTY_INT_ITERATOR = unmodifiableIntIterator(EMPTY_INT_LIST.iterator());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final IntListIterator EMPTY_INT_LIST_ITERATOR = unmodifiableIntListIterator(EMPTY_INT_LIST.listIterator());
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/IntCollections.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */