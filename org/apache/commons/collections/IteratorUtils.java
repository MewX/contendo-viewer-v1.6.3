/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.iterators.ArrayIterator;
/*     */ import org.apache.commons.collections.iterators.ArrayListIterator;
/*     */ import org.apache.commons.collections.iterators.CollatingIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyListIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyMapIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyOrderedIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyOrderedMapIterator;
/*     */ import org.apache.commons.collections.iterators.EnumerationIterator;
/*     */ import org.apache.commons.collections.iterators.FilterIterator;
/*     */ import org.apache.commons.collections.iterators.FilterListIterator;
/*     */ import org.apache.commons.collections.iterators.IteratorChain;
/*     */ import org.apache.commons.collections.iterators.IteratorEnumeration;
/*     */ import org.apache.commons.collections.iterators.ListIteratorWrapper;
/*     */ import org.apache.commons.collections.iterators.LoopingIterator;
/*     */ import org.apache.commons.collections.iterators.ObjectArrayIterator;
/*     */ import org.apache.commons.collections.iterators.ObjectArrayListIterator;
/*     */ import org.apache.commons.collections.iterators.ObjectGraphIterator;
/*     */ import org.apache.commons.collections.iterators.SingletonIterator;
/*     */ import org.apache.commons.collections.iterators.SingletonListIterator;
/*     */ import org.apache.commons.collections.iterators.TransformIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableListIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableMapIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IteratorUtils
/*     */ {
/*  82 */   public static final ResettableIterator EMPTY_ITERATOR = EmptyIterator.RESETTABLE_INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final ResettableListIterator EMPTY_LIST_ITERATOR = EmptyListIterator.RESETTABLE_INSTANCE;
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static final OrderedIterator EMPTY_ORDERED_ITERATOR = EmptyOrderedIterator.INSTANCE;
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static final MapIterator EMPTY_MAP_ITERATOR = EmptyMapIterator.INSTANCE;
/*     */ 
/*     */ 
/*     */   
/* 101 */   public static final OrderedMapIterator EMPTY_ORDERED_MAP_ITERATOR = EmptyOrderedMapIterator.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ResettableIterator emptyIterator() {
/* 123 */     return EMPTY_ITERATOR;
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
/*     */   public static ResettableListIterator emptyListIterator() {
/* 138 */     return EMPTY_LIST_ITERATOR;
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
/*     */   public static OrderedIterator emptyOrderedIterator() {
/* 150 */     return EMPTY_ORDERED_ITERATOR;
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
/*     */   public static MapIterator emptyMapIterator() {
/* 162 */     return EMPTY_MAP_ITERATOR;
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
/*     */   public static OrderedMapIterator emptyOrderedMapIterator() {
/* 174 */     return EMPTY_ORDERED_MAP_ITERATOR;
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
/*     */   public static ResettableIterator singletonIterator(Object object) {
/* 192 */     return (ResettableIterator)new SingletonIterator(object);
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
/*     */   public static ListIterator singletonListIterator(Object object) {
/* 205 */     return (ListIterator)new SingletonListIterator(object);
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
/*     */   public static ResettableIterator arrayIterator(Object[] array) {
/* 221 */     return (ResettableIterator)new ObjectArrayIterator(array);
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
/*     */   public static ResettableIterator arrayIterator(Object array) {
/* 236 */     return (ResettableIterator)new ArrayIterator(array);
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
/*     */   public static ResettableIterator arrayIterator(Object[] array, int start) {
/* 253 */     return (ResettableIterator)new ObjectArrayIterator(array, start);
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
/*     */   public static ResettableIterator arrayIterator(Object array, int start) {
/* 271 */     return (ResettableIterator)new ArrayIterator(array, start);
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
/*     */   public static ResettableIterator arrayIterator(Object[] array, int start, int end) {
/* 289 */     return (ResettableIterator)new ObjectArrayIterator(array, start, end);
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
/*     */   public static ResettableIterator arrayIterator(Object array, int start, int end) {
/* 308 */     return (ResettableIterator)new ArrayIterator(array, start, end);
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
/*     */   public static ResettableListIterator arrayListIterator(Object[] array) {
/* 320 */     return (ResettableListIterator)new ObjectArrayListIterator(array);
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
/*     */   public static ResettableListIterator arrayListIterator(Object array) {
/* 335 */     return (ResettableListIterator)new ArrayListIterator(array);
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
/*     */   public static ResettableListIterator arrayListIterator(Object[] array, int start) {
/* 348 */     return (ResettableListIterator)new ObjectArrayListIterator(array, start);
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
/*     */   public static ResettableListIterator arrayListIterator(Object array, int start) {
/* 365 */     return (ResettableListIterator)new ArrayListIterator(array, start);
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
/*     */   public static ResettableListIterator arrayListIterator(Object[] array, int start, int end) {
/* 380 */     return (ResettableListIterator)new ObjectArrayListIterator(array, start, end);
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
/*     */   public static ResettableListIterator arrayListIterator(Object array, int start, int end) {
/* 399 */     return (ResettableListIterator)new ArrayListIterator(array, start, end);
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
/*     */   public static Iterator unmodifiableIterator(Iterator iterator) {
/* 413 */     return UnmodifiableIterator.decorate(iterator);
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
/*     */   public static ListIterator unmodifiableListIterator(ListIterator listIterator) {
/* 426 */     return UnmodifiableListIterator.decorate(listIterator);
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
/*     */   public static MapIterator unmodifiableMapIterator(MapIterator mapIterator) {
/* 438 */     return UnmodifiableMapIterator.decorate(mapIterator);
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
/*     */   public static Iterator chainedIterator(Iterator iterator1, Iterator iterator2) {
/* 453 */     return (Iterator)new IteratorChain(iterator1, iterator2);
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
/*     */   public static Iterator chainedIterator(Iterator[] iterators) {
/* 465 */     return (Iterator)new IteratorChain(iterators);
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
/*     */   public static Iterator chainedIterator(Collection iterators) {
/* 478 */     return (Iterator)new IteratorChain(iterators);
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
/*     */   public static Iterator collatedIterator(Comparator comparator, Iterator iterator1, Iterator iterator2) {
/* 500 */     return (Iterator)new CollatingIterator(comparator, iterator1, iterator2);
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
/*     */   public static Iterator collatedIterator(Comparator comparator, Iterator[] iterators) {
/* 519 */     return (Iterator)new CollatingIterator(comparator, iterators);
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
/*     */   public static Iterator collatedIterator(Comparator comparator, Collection iterators) {
/* 539 */     return (Iterator)new CollatingIterator(comparator, iterators);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterator objectGraphIterator(Object root, Transformer transformer) {
/* 598 */     return (Iterator)new ObjectGraphIterator(root, transformer);
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
/*     */   public static Iterator transformedIterator(Iterator iterator, Transformer transform) {
/* 615 */     if (iterator == null) {
/* 616 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 618 */     if (transform == null) {
/* 619 */       throw new NullPointerException("Transformer must not be null");
/*     */     }
/* 621 */     return (Iterator)new TransformIterator(iterator, transform);
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
/*     */   public static Iterator filteredIterator(Iterator iterator, Predicate predicate) {
/* 638 */     if (iterator == null) {
/* 639 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 641 */     if (predicate == null) {
/* 642 */       throw new NullPointerException("Predicate must not be null");
/*     */     }
/* 644 */     return (Iterator)new FilterIterator(iterator, predicate);
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
/*     */   public static ListIterator filteredListIterator(ListIterator listIterator, Predicate predicate) {
/* 659 */     if (listIterator == null) {
/* 660 */       throw new NullPointerException("ListIterator must not be null");
/*     */     }
/* 662 */     if (predicate == null) {
/* 663 */       throw new NullPointerException("Predicate must not be null");
/*     */     }
/* 665 */     return (ListIterator)new FilterListIterator(listIterator, predicate);
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
/*     */   public static ResettableIterator loopingIterator(Collection coll) {
/* 682 */     if (coll == null) {
/* 683 */       throw new NullPointerException("Collection must not be null");
/*     */     }
/* 685 */     return (ResettableIterator)new LoopingIterator(coll);
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
/*     */   public static Iterator asIterator(Enumeration enumeration) {
/* 697 */     if (enumeration == null) {
/* 698 */       throw new NullPointerException("Enumeration must not be null");
/*     */     }
/* 700 */     return (Iterator)new EnumerationIterator(enumeration);
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
/*     */   public static Iterator asIterator(Enumeration enumeration, Collection removeCollection) {
/* 712 */     if (enumeration == null) {
/* 713 */       throw new NullPointerException("Enumeration must not be null");
/*     */     }
/* 715 */     if (removeCollection == null) {
/* 716 */       throw new NullPointerException("Collection must not be null");
/*     */     }
/* 718 */     return (Iterator)new EnumerationIterator(enumeration, removeCollection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Enumeration asEnumeration(Iterator iterator) {
/* 729 */     if (iterator == null) {
/* 730 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 732 */     return (Enumeration)new IteratorEnumeration(iterator);
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
/*     */   public static ListIterator toListIterator(Iterator iterator) {
/* 746 */     if (iterator == null) {
/* 747 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 749 */     return (ListIterator)new ListIteratorWrapper(iterator);
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
/*     */   public static Object[] toArray(Iterator iterator) {
/* 763 */     if (iterator == null) {
/* 764 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 766 */     List list = toList(iterator, 100);
/* 767 */     return list.toArray();
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
/*     */   public static Object[] toArray(Iterator iterator, Class arrayClass) {
/* 784 */     if (iterator == null) {
/* 785 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 787 */     if (arrayClass == null) {
/* 788 */       throw new NullPointerException("Array class must not be null");
/*     */     }
/* 790 */     List list = toList(iterator, 100);
/* 791 */     return list.toArray((Object[])Array.newInstance(arrayClass, list.size()));
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
/*     */   public static List toList(Iterator iterator) {
/* 805 */     return toList(iterator, 10);
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
/*     */   public static List toList(Iterator iterator, int estimatedSize) {
/* 821 */     if (iterator == null) {
/* 822 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 824 */     if (estimatedSize < 1) {
/* 825 */       throw new IllegalArgumentException("Estimated size must be greater than 0");
/*     */     }
/* 827 */     List list = new ArrayList(estimatedSize);
/* 828 */     while (iterator.hasNext()) {
/* 829 */       list.add(iterator.next());
/*     */     }
/* 831 */     return list;
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
/*     */   public static Iterator getIterator(Object obj) {
/* 854 */     if (obj == null) {
/* 855 */       return emptyIterator();
/*     */     }
/* 857 */     if (obj instanceof Iterator) {
/* 858 */       return (Iterator)obj;
/*     */     }
/* 860 */     if (obj instanceof Collection) {
/* 861 */       return ((Collection)obj).iterator();
/*     */     }
/* 863 */     if (obj instanceof Object[]) {
/* 864 */       return (Iterator)new ObjectArrayIterator((Object[])obj);
/*     */     }
/* 866 */     if (obj instanceof Enumeration) {
/* 867 */       return (Iterator)new EnumerationIterator((Enumeration)obj);
/*     */     }
/* 869 */     if (obj instanceof Map) {
/* 870 */       return ((Map)obj).values().iterator();
/*     */     }
/* 872 */     if (obj instanceof Dictionary) {
/* 873 */       return (Iterator)new EnumerationIterator(((Dictionary)obj).elements());
/*     */     }
/* 875 */     if (obj != null && obj.getClass().isArray()) {
/* 876 */       return (Iterator)new ArrayIterator(obj);
/*     */     }
/*     */     
/*     */     try {
/* 880 */       Method method = obj.getClass().getMethod("iterator", null);
/* 881 */       if (Iterator.class.isAssignableFrom(method.getReturnType())) {
/* 882 */         Iterator it = (Iterator)method.invoke(obj, null);
/* 883 */         if (it != null) {
/* 884 */           return it;
/*     */         }
/*     */       } 
/* 887 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 890 */     return singletonIterator(obj);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/IteratorUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */