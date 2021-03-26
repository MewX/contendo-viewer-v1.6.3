/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableLongIterator;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableLongList;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableLongListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LongCollections
/*     */ {
/*     */   public static LongList singletonLongList(long value) {
/*  42 */     LongList list = new ArrayLongList(1);
/*  43 */     list.add(value);
/*  44 */     return UnmodifiableLongList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LongIterator singletonLongIterator(long value) {
/*  53 */     return singletonLongList(value).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LongListIterator singletonLongListIterator(long value) {
/*  62 */     return singletonLongList(value).listIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LongList unmodifiableLongList(LongList list) throws NullPointerException {
/*  73 */     if (null == list) {
/*  74 */       throw new NullPointerException();
/*     */     }
/*  76 */     return UnmodifiableLongList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LongIterator unmodifiableLongIterator(LongIterator iter) {
/*  87 */     if (null == iter) {
/*  88 */       throw new NullPointerException();
/*     */     }
/*  90 */     return UnmodifiableLongIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LongListIterator unmodifiableLongListIterator(LongListIterator iter) {
/* 101 */     if (null == iter) {
/* 102 */       throw new NullPointerException();
/*     */     }
/* 104 */     return UnmodifiableLongListIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LongList getEmptyLongList() {
/* 113 */     return EMPTY_LONG_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LongIterator getEmptyLongIterator() {
/* 122 */     return EMPTY_LONG_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LongListIterator getEmptyLongListIterator() {
/* 131 */     return EMPTY_LONG_LIST_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final LongList EMPTY_LONG_LIST = unmodifiableLongList(new ArrayLongList(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final LongIterator EMPTY_LONG_ITERATOR = unmodifiableLongIterator(EMPTY_LONG_LIST.iterator());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final LongListIterator EMPTY_LONG_LIST_ITERATOR = unmodifiableLongListIterator(EMPTY_LONG_LIST.listIterator());
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/LongCollections.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */