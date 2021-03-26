/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.primitives.ByteCollection;
/*     */ import org.apache.commons.collections.primitives.ByteIterator;
/*     */ import org.apache.commons.collections.primitives.ByteList;
/*     */ import org.apache.commons.collections.primitives.ByteListIterator;
/*     */ import org.apache.commons.collections.primitives.CharCollection;
/*     */ import org.apache.commons.collections.primitives.CharIterator;
/*     */ import org.apache.commons.collections.primitives.CharList;
/*     */ import org.apache.commons.collections.primitives.CharListIterator;
/*     */ import org.apache.commons.collections.primitives.DoubleCollection;
/*     */ import org.apache.commons.collections.primitives.DoubleIterator;
/*     */ import org.apache.commons.collections.primitives.DoubleList;
/*     */ import org.apache.commons.collections.primitives.DoubleListIterator;
/*     */ import org.apache.commons.collections.primitives.FloatCollection;
/*     */ import org.apache.commons.collections.primitives.FloatIterator;
/*     */ import org.apache.commons.collections.primitives.FloatList;
/*     */ import org.apache.commons.collections.primitives.FloatListIterator;
/*     */ import org.apache.commons.collections.primitives.IntCollection;
/*     */ import org.apache.commons.collections.primitives.IntIterator;
/*     */ import org.apache.commons.collections.primitives.IntList;
/*     */ import org.apache.commons.collections.primitives.IntListIterator;
/*     */ import org.apache.commons.collections.primitives.LongCollection;
/*     */ import org.apache.commons.collections.primitives.LongIterator;
/*     */ import org.apache.commons.collections.primitives.LongList;
/*     */ import org.apache.commons.collections.primitives.LongListIterator;
/*     */ import org.apache.commons.collections.primitives.ShortCollection;
/*     */ import org.apache.commons.collections.primitives.ShortIterator;
/*     */ import org.apache.commons.collections.primitives.ShortList;
/*     */ import org.apache.commons.collections.primitives.ShortListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Adapt
/*     */ {
/*     */   public static final Collection toCollection(ByteCollection c) {
/*  67 */     return ByteCollectionCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Collection toCollection(CharCollection c) {
/*  71 */     return CharCollectionCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Collection toCollection(DoubleCollection c) {
/*  75 */     return DoubleCollectionCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Collection toCollection(FloatCollection c) {
/*  79 */     return FloatCollectionCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Collection toCollection(IntCollection c) {
/*  83 */     return IntCollectionCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Collection toCollection(LongCollection c) {
/*  87 */     return LongCollectionCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Collection toCollection(ShortCollection c) {
/*  91 */     return ShortCollectionCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final List toList(ByteList c) {
/*  95 */     return ByteListList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final List toList(CharList c) {
/*  99 */     return CharListList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final List toList(DoubleList c) {
/* 103 */     return DoubleListList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final List toList(FloatList c) {
/* 107 */     return FloatListList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final List toList(IntList c) {
/* 111 */     return IntListList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final List toList(LongList c) {
/* 115 */     return LongListList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final List toList(ShortList c) {
/* 119 */     return ShortListList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Iterator toIterator(ByteIterator c) {
/* 123 */     return ByteIteratorIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Iterator toIterator(CharIterator c) {
/* 127 */     return CharIteratorIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Iterator toIterator(DoubleIterator c) {
/* 131 */     return DoubleIteratorIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Iterator toIterator(FloatIterator c) {
/* 135 */     return FloatIteratorIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Iterator toIterator(IntIterator c) {
/* 139 */     return IntIteratorIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Iterator toIterator(LongIterator c) {
/* 143 */     return LongIteratorIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final Iterator toIterator(ShortIterator c) {
/* 147 */     return ShortIteratorIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ListIterator toListIterator(ByteListIterator c) {
/* 151 */     return ByteListIteratorListIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ListIterator toListIterator(CharListIterator c) {
/* 155 */     return CharListIteratorListIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ListIterator toListIterator(DoubleListIterator c) {
/* 159 */     return DoubleListIteratorListIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ListIterator toListIterator(FloatListIterator c) {
/* 163 */     return FloatListIteratorListIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ListIterator toListIterator(IntListIterator c) {
/* 167 */     return IntListIteratorListIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ListIterator toListIterator(LongListIterator c) {
/* 171 */     return LongListIteratorListIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ListIterator toListIterator(ShortListIterator c) {
/* 175 */     return ShortListIteratorListIterator.wrap(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final ByteCollection toByteCollection(Collection c) {
/* 182 */     return CollectionByteCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ByteList toByteList(List c) {
/* 186 */     return ListByteList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ByteIterator toByteIterator(Iterator c) {
/* 190 */     return IteratorByteIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ByteListIterator toByteListIterator(ListIterator c) {
/* 194 */     return ListIteratorByteListIterator.wrap(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final CharCollection toCharCollection(Collection c) {
/* 201 */     return CollectionCharCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final CharList toCharList(List c) {
/* 205 */     return ListCharList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final CharIterator toCharIterator(Iterator c) {
/* 209 */     return IteratorCharIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final CharListIterator toCharListIterator(ListIterator c) {
/* 213 */     return ListIteratorCharListIterator.wrap(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final DoubleCollection toDoubleCollection(Collection c) {
/* 220 */     return CollectionDoubleCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final DoubleList toDoubleList(List c) {
/* 224 */     return ListDoubleList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final DoubleIterator toDoubleIterator(Iterator c) {
/* 228 */     return IteratorDoubleIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final DoubleListIterator toDoubleListIterator(ListIterator c) {
/* 232 */     return ListIteratorDoubleListIterator.wrap(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final FloatCollection toFloatCollection(Collection c) {
/* 239 */     return CollectionFloatCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final FloatList toFloatList(List c) {
/* 243 */     return ListFloatList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final FloatIterator toFloatIterator(Iterator c) {
/* 247 */     return IteratorFloatIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final FloatListIterator toFloatListIterator(ListIterator c) {
/* 251 */     return ListIteratorFloatListIterator.wrap(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final IntCollection toIntCollection(Collection c) {
/* 258 */     return CollectionIntCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final IntList toIntList(List c) {
/* 262 */     return ListIntList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final IntIterator toIntIterator(Iterator c) {
/* 266 */     return IteratorIntIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final IntListIterator toIntListIterator(ListIterator c) {
/* 270 */     return ListIteratorIntListIterator.wrap(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final LongCollection toLongCollection(Collection c) {
/* 277 */     return CollectionLongCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final LongList toLongList(List c) {
/* 281 */     return ListLongList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final LongIterator toLongIterator(Iterator c) {
/* 285 */     return IteratorLongIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final LongListIterator toLongListIterator(ListIterator c) {
/* 289 */     return ListIteratorLongListIterator.wrap(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final ShortCollection toShortCollection(Collection c) {
/* 296 */     return CollectionShortCollection.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ShortList toShortList(List c) {
/* 300 */     return ListShortList.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ShortIterator toShortIterator(Iterator c) {
/* 304 */     return IteratorShortIterator.wrap(c);
/*     */   }
/*     */   
/*     */   public static final ShortListIterator toShortListIterator(ListIterator c) {
/* 308 */     return ListIteratorShortListIterator.wrap(c);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/Adapt.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */