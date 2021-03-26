/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableCharIterator;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableCharList;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableCharListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CharCollections
/*     */ {
/*     */   public static CharList singletonCharList(char value) {
/*  42 */     CharList list = new ArrayCharList(1);
/*  43 */     list.add(value);
/*  44 */     return UnmodifiableCharList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CharIterator singletonCharIterator(char value) {
/*  53 */     return singletonCharList(value).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CharListIterator singletonCharListIterator(char value) {
/*  62 */     return singletonCharList(value).listIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CharList unmodifiableCharList(CharList list) throws NullPointerException {
/*  73 */     if (null == list) {
/*  74 */       throw new NullPointerException();
/*     */     }
/*  76 */     return UnmodifiableCharList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CharIterator unmodifiableCharIterator(CharIterator iter) {
/*  87 */     if (null == iter) {
/*  88 */       throw new NullPointerException();
/*     */     }
/*  90 */     return UnmodifiableCharIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CharListIterator unmodifiableCharListIterator(CharListIterator iter) {
/* 101 */     if (null == iter) {
/* 102 */       throw new NullPointerException();
/*     */     }
/* 104 */     return UnmodifiableCharListIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CharList getEmptyCharList() {
/* 113 */     return EMPTY_CHAR_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CharIterator getEmptyCharIterator() {
/* 122 */     return EMPTY_CHAR_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CharListIterator getEmptyCharListIterator() {
/* 131 */     return EMPTY_CHAR_LIST_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final CharList EMPTY_CHAR_LIST = unmodifiableCharList(new ArrayCharList(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final CharIterator EMPTY_CHAR_ITERATOR = unmodifiableCharIterator(EMPTY_CHAR_LIST.iterator());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final CharListIterator EMPTY_CHAR_LIST_ITERATOR = unmodifiableCharListIterator(EMPTY_CHAR_LIST.listIterator());
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/CharCollections.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */