/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableFloatIterator;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableFloatList;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableFloatListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FloatCollections
/*     */ {
/*     */   public static FloatList singletonFloatList(float value) {
/*  42 */     FloatList list = new ArrayFloatList(1);
/*  43 */     list.add(value);
/*  44 */     return UnmodifiableFloatList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatIterator singletonFloatIterator(float value) {
/*  53 */     return singletonFloatList(value).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatListIterator singletonFloatListIterator(float value) {
/*  62 */     return singletonFloatList(value).listIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatList unmodifiableFloatList(FloatList list) throws NullPointerException {
/*  73 */     if (null == list) {
/*  74 */       throw new NullPointerException();
/*     */     }
/*  76 */     return UnmodifiableFloatList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatIterator unmodifiableFloatIterator(FloatIterator iter) {
/*  87 */     if (null == iter) {
/*  88 */       throw new NullPointerException();
/*     */     }
/*  90 */     return UnmodifiableFloatIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatListIterator unmodifiableFloatListIterator(FloatListIterator iter) {
/* 101 */     if (null == iter) {
/* 102 */       throw new NullPointerException();
/*     */     }
/* 104 */     return UnmodifiableFloatListIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatList getEmptyFloatList() {
/* 113 */     return EMPTY_FLOAT_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatIterator getEmptyFloatIterator() {
/* 122 */     return EMPTY_FLOAT_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatListIterator getEmptyFloatListIterator() {
/* 131 */     return EMPTY_FLOAT_LIST_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final FloatList EMPTY_FLOAT_LIST = unmodifiableFloatList(new ArrayFloatList(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final FloatIterator EMPTY_FLOAT_ITERATOR = unmodifiableFloatIterator(EMPTY_FLOAT_LIST.iterator());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final FloatListIterator EMPTY_FLOAT_LIST_ITERATOR = unmodifiableFloatListIterator(EMPTY_FLOAT_LIST.listIterator());
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/FloatCollections.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */