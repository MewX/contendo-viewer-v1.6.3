/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableShortIterator;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableShortList;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableShortListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ShortCollections
/*     */ {
/*     */   public static ShortList singletonShortList(short value) {
/*  42 */     ShortList list = new ArrayShortList(1);
/*  43 */     list.add(value);
/*  44 */     return UnmodifiableShortList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShortIterator singletonShortIterator(short value) {
/*  53 */     return singletonShortList(value).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShortListIterator singletonShortListIterator(short value) {
/*  62 */     return singletonShortList(value).listIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShortList unmodifiableShortList(ShortList list) throws NullPointerException {
/*  73 */     if (null == list) {
/*  74 */       throw new NullPointerException();
/*     */     }
/*  76 */     return UnmodifiableShortList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShortIterator unmodifiableShortIterator(ShortIterator iter) {
/*  87 */     if (null == iter) {
/*  88 */       throw new NullPointerException();
/*     */     }
/*  90 */     return UnmodifiableShortIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShortListIterator unmodifiableShortListIterator(ShortListIterator iter) {
/* 101 */     if (null == iter) {
/* 102 */       throw new NullPointerException();
/*     */     }
/* 104 */     return UnmodifiableShortListIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShortList getEmptyShortList() {
/* 113 */     return EMPTY_SHORT_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShortIterator getEmptyShortIterator() {
/* 122 */     return EMPTY_SHORT_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShortListIterator getEmptyShortListIterator() {
/* 131 */     return EMPTY_SHORT_LIST_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final ShortList EMPTY_SHORT_LIST = unmodifiableShortList(new ArrayShortList(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final ShortIterator EMPTY_SHORT_ITERATOR = unmodifiableShortIterator(EMPTY_SHORT_LIST.iterator());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final ShortListIterator EMPTY_SHORT_LIST_ITERATOR = unmodifiableShortListIterator(EMPTY_SHORT_LIST.listIterator());
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ShortCollections.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */