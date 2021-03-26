/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.collection.PredicatedCollection;
/*      */ import org.apache.commons.collections.collection.SynchronizedCollection;
/*      */ import org.apache.commons.collections.collection.TransformedCollection;
/*      */ import org.apache.commons.collections.collection.TypedCollection;
/*      */ import org.apache.commons.collections.collection.UnmodifiableBoundedCollection;
/*      */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CollectionUtils
/*      */ {
/*   58 */   private static Integer INTEGER_ONE = new Integer(1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   66 */   public static final Collection EMPTY_COLLECTION = UnmodifiableCollection.decorate(new ArrayList());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection union(Collection a, Collection b) {
/*   88 */     ArrayList list = new ArrayList();
/*   89 */     Map mapa = getCardinalityMap(a);
/*   90 */     Map mapb = getCardinalityMap(b);
/*   91 */     Set elts = new HashSet(a);
/*   92 */     elts.addAll(b);
/*   93 */     Iterator it = elts.iterator();
/*   94 */     while (it.hasNext()) {
/*   95 */       Object obj = it.next();
/*   96 */       for (int i = 0, m = Math.max(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
/*   97 */         list.add(obj);
/*      */       }
/*      */     } 
/*  100 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection intersection(Collection a, Collection b) {
/*  118 */     ArrayList list = new ArrayList();
/*  119 */     Map mapa = getCardinalityMap(a);
/*  120 */     Map mapb = getCardinalityMap(b);
/*  121 */     Set elts = new HashSet(a);
/*  122 */     elts.addAll(b);
/*  123 */     Iterator it = elts.iterator();
/*  124 */     while (it.hasNext()) {
/*  125 */       Object obj = it.next();
/*  126 */       for (int i = 0, m = Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
/*  127 */         list.add(obj);
/*      */       }
/*      */     } 
/*  130 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection disjunction(Collection a, Collection b) {
/*  151 */     ArrayList list = new ArrayList();
/*  152 */     Map mapa = getCardinalityMap(a);
/*  153 */     Map mapb = getCardinalityMap(b);
/*  154 */     Set elts = new HashSet(a);
/*  155 */     elts.addAll(b);
/*  156 */     Iterator it = elts.iterator();
/*  157 */     while (it.hasNext()) {
/*  158 */       Object obj = it.next();
/*  159 */       for (int i = 0, m = Math.max(getFreq(obj, mapa), getFreq(obj, mapb)) - Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
/*  160 */         list.add(obj);
/*      */       }
/*      */     } 
/*  163 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection subtract(Collection a, Collection b) {
/*  178 */     ArrayList list = new ArrayList(a);
/*  179 */     for (Iterator it = b.iterator(); it.hasNext();) {
/*  180 */       list.remove(it.next());
/*      */     }
/*  182 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsAny(Collection coll1, Collection coll2) {
/*  198 */     if (coll1.size() < coll2.size()) {
/*  199 */       for (Iterator it = coll1.iterator(); it.hasNext();) {
/*  200 */         if (coll2.contains(it.next())) {
/*  201 */           return true;
/*      */         }
/*      */       } 
/*      */     } else {
/*  205 */       for (Iterator it = coll2.iterator(); it.hasNext();) {
/*  206 */         if (coll1.contains(it.next())) {
/*  207 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*  211 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map getCardinalityMap(Collection coll) {
/*  226 */     Map count = new HashMap();
/*  227 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/*  228 */       Object obj = it.next();
/*  229 */       Integer c = (Integer)count.get(obj);
/*  230 */       if (c == null) {
/*  231 */         count.put(obj, INTEGER_ONE); continue;
/*      */       } 
/*  233 */       count.put(obj, new Integer(c.intValue() + 1));
/*      */     } 
/*      */     
/*  236 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSubCollection(Collection a, Collection b) {
/*  252 */     Map mapa = getCardinalityMap(a);
/*  253 */     Map mapb = getCardinalityMap(b);
/*  254 */     Iterator it = a.iterator();
/*  255 */     while (it.hasNext()) {
/*  256 */       Object obj = it.next();
/*  257 */       if (getFreq(obj, mapa) > getFreq(obj, mapb)) {
/*  258 */         return false;
/*      */       }
/*      */     } 
/*  261 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isProperSubCollection(Collection a, Collection b) {
/*  286 */     return (a.size() < b.size() && isSubCollection(a, b));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEqualCollection(Collection a, Collection b) {
/*  302 */     if (a.size() != b.size()) {
/*  303 */       return false;
/*      */     }
/*  305 */     Map mapa = getCardinalityMap(a);
/*  306 */     Map mapb = getCardinalityMap(b);
/*  307 */     if (mapa.size() != mapb.size()) {
/*  308 */       return false;
/*      */     }
/*  310 */     Iterator it = mapa.keySet().iterator();
/*  311 */     while (it.hasNext()) {
/*  312 */       Object obj = it.next();
/*  313 */       if (getFreq(obj, mapa) != getFreq(obj, mapb)) {
/*  314 */         return false;
/*      */       }
/*      */     } 
/*  317 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int cardinality(Object obj, Collection coll) {
/*  330 */     if (coll instanceof Set) {
/*  331 */       return coll.contains(obj) ? 1 : 0;
/*      */     }
/*  333 */     if (coll instanceof Bag) {
/*  334 */       return ((Bag)coll).getCount(obj);
/*      */     }
/*  336 */     int count = 0;
/*  337 */     if (obj == null) {
/*  338 */       for (Iterator it = coll.iterator(); it.hasNext();) {
/*  339 */         if (it.next() == null) {
/*  340 */           count++;
/*      */         }
/*      */       } 
/*      */     } else {
/*  344 */       for (Iterator it = coll.iterator(); it.hasNext();) {
/*  345 */         if (obj.equals(it.next())) {
/*  346 */           count++;
/*      */         }
/*      */       } 
/*      */     } 
/*  350 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object find(Collection collection, Predicate predicate) {
/*  364 */     if (collection != null && predicate != null) {
/*  365 */       for (Iterator iter = collection.iterator(); iter.hasNext(); ) {
/*  366 */         Object item = iter.next();
/*  367 */         if (predicate.evaluate(item)) {
/*  368 */           return item;
/*      */         }
/*      */       } 
/*      */     }
/*  372 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void forAllDo(Collection collection, Closure closure) {
/*  384 */     if (collection != null && closure != null) {
/*  385 */       for (Iterator it = collection.iterator(); it.hasNext();) {
/*  386 */         closure.execute(it.next());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void filter(Collection collection, Predicate predicate) {
/*  401 */     if (collection != null && predicate != null) {
/*  402 */       for (Iterator it = collection.iterator(); it.hasNext();) {
/*  403 */         if (!predicate.evaluate(it.next())) {
/*  404 */           it.remove();
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void transform(Collection collection, Transformer transformer) {
/*  427 */     if (collection != null && transformer != null) {
/*  428 */       if (collection instanceof List) {
/*  429 */         List list = (List)collection;
/*  430 */         for (ListIterator it = list.listIterator(); it.hasNext();) {
/*  431 */           it.set(transformer.transform(it.next()));
/*      */         }
/*      */       } else {
/*  434 */         Collection resultCollection = collect(collection, transformer);
/*  435 */         collection.clear();
/*  436 */         collection.addAll(resultCollection);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int countMatches(Collection inputCollection, Predicate predicate) {
/*  451 */     int count = 0;
/*  452 */     if (inputCollection != null && predicate != null) {
/*  453 */       for (Iterator it = inputCollection.iterator(); it.hasNext();) {
/*  454 */         if (predicate.evaluate(it.next())) {
/*  455 */           count++;
/*      */         }
/*      */       } 
/*      */     }
/*  459 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean exists(Collection collection, Predicate predicate) {
/*  472 */     if (collection != null && predicate != null) {
/*  473 */       for (Iterator it = collection.iterator(); it.hasNext();) {
/*  474 */         if (predicate.evaluate(it.next())) {
/*  475 */           return true;
/*      */         }
/*      */       } 
/*      */     }
/*  479 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection select(Collection inputCollection, Predicate predicate) {
/*  494 */     ArrayList answer = new ArrayList(inputCollection.size());
/*  495 */     select(inputCollection, predicate, answer);
/*  496 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void select(Collection inputCollection, Predicate predicate, Collection outputCollection) {
/*  511 */     if (inputCollection != null && predicate != null) {
/*  512 */       for (Iterator iter = inputCollection.iterator(); iter.hasNext(); ) {
/*  513 */         Object item = iter.next();
/*  514 */         if (predicate.evaluate(item)) {
/*  515 */           outputCollection.add(item);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection selectRejected(Collection inputCollection, Predicate predicate) {
/*  533 */     ArrayList answer = new ArrayList(inputCollection.size());
/*  534 */     selectRejected(inputCollection, predicate, answer);
/*  535 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void selectRejected(Collection inputCollection, Predicate predicate, Collection outputCollection) {
/*  549 */     if (inputCollection != null && predicate != null) {
/*  550 */       for (Iterator iter = inputCollection.iterator(); iter.hasNext(); ) {
/*  551 */         Object item = iter.next();
/*  552 */         if (!predicate.evaluate(item)) {
/*  553 */           outputCollection.add(item);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection collect(Collection inputCollection, Transformer transformer) {
/*  571 */     ArrayList answer = new ArrayList(inputCollection.size());
/*  572 */     collect(inputCollection, transformer, answer);
/*  573 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection collect(Iterator inputIterator, Transformer transformer) {
/*  587 */     ArrayList answer = new ArrayList();
/*  588 */     collect(inputIterator, transformer, answer);
/*  589 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection collect(Collection inputCollection, Transformer transformer, Collection outputCollection) {
/*  606 */     if (inputCollection != null) {
/*  607 */       return collect(inputCollection.iterator(), transformer, outputCollection);
/*      */     }
/*  609 */     return outputCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection collect(Iterator inputIterator, Transformer transformer, Collection outputCollection) {
/*  626 */     if (inputIterator != null && transformer != null) {
/*  627 */       while (inputIterator.hasNext()) {
/*  628 */         Object item = inputIterator.next();
/*  629 */         Object value = transformer.transform(item);
/*  630 */         outputCollection.add(value);
/*      */       } 
/*      */     }
/*  633 */     return outputCollection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addAll(Collection collection, Iterator iterator) {
/*  644 */     while (iterator.hasNext()) {
/*  645 */       collection.add(iterator.next());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addAll(Collection collection, Enumeration enumeration) {
/*  657 */     while (enumeration.hasMoreElements()) {
/*  658 */       collection.add(enumeration.nextElement());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addAll(Collection collection, Object[] elements) {
/*  670 */     for (int i = 0, size = elements.length; i < size; i++) {
/*  671 */       collection.add(elements[i]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object index(Object obj, int idx) {
/*  698 */     return index(obj, new Integer(idx));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object index(Object obj, Object index) {
/*  725 */     if (obj instanceof Map) {
/*  726 */       Map map = (Map)obj;
/*  727 */       if (map.containsKey(index)) {
/*  728 */         return map.get(index);
/*      */       }
/*      */     } 
/*  731 */     int idx = -1;
/*  732 */     if (index instanceof Integer) {
/*  733 */       idx = ((Integer)index).intValue();
/*      */     }
/*  735 */     if (idx < 0) {
/*  736 */       return obj;
/*      */     }
/*  738 */     if (obj instanceof Map) {
/*  739 */       Map map = (Map)obj;
/*  740 */       Iterator iterator = map.keySet().iterator();
/*  741 */       return index(iterator, idx);
/*      */     } 
/*  743 */     if (obj instanceof List) {
/*  744 */       return ((List)obj).get(idx);
/*      */     }
/*  746 */     if (obj instanceof Object[]) {
/*  747 */       return ((Object[])obj)[idx];
/*      */     }
/*  749 */     if (obj instanceof Enumeration) {
/*  750 */       Enumeration it = (Enumeration)obj;
/*  751 */       while (it.hasMoreElements()) {
/*  752 */         idx--;
/*  753 */         if (idx == -1) {
/*  754 */           return it.nextElement();
/*      */         }
/*  756 */         it.nextElement();
/*      */       } 
/*      */     } else {
/*      */       
/*  760 */       if (obj instanceof Iterator) {
/*  761 */         return index((Iterator)obj, idx);
/*      */       }
/*  763 */       if (obj instanceof Collection) {
/*  764 */         Iterator iterator = ((Collection)obj).iterator();
/*  765 */         return index(iterator, idx);
/*      */       } 
/*  767 */     }  return obj;
/*      */   }
/*      */   
/*      */   private static Object index(Iterator iterator, int idx) {
/*  771 */     while (iterator.hasNext()) {
/*  772 */       idx--;
/*  773 */       if (idx == -1) {
/*  774 */         return iterator.next();
/*      */       }
/*  776 */       iterator.next();
/*      */     } 
/*      */     
/*  779 */     return iterator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object get(Object object, int index) {
/*  813 */     if (index < 0) {
/*  814 */       throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
/*      */     }
/*  816 */     if (object instanceof Map) {
/*  817 */       Map map = (Map)object;
/*  818 */       Iterator iterator = map.entrySet().iterator();
/*  819 */       return get(iterator, index);
/*  820 */     }  if (object instanceof List)
/*  821 */       return ((List)object).get(index); 
/*  822 */     if (object instanceof Object[])
/*  823 */       return ((Object[])object)[index]; 
/*  824 */     if (object instanceof Iterator) {
/*  825 */       Iterator it = (Iterator)object;
/*  826 */       while (it.hasNext()) {
/*  827 */         index--;
/*  828 */         if (index == -1) {
/*  829 */           return it.next();
/*      */         }
/*  831 */         it.next();
/*      */       } 
/*      */       
/*  834 */       throw new IndexOutOfBoundsException("Entry does not exist: " + index);
/*  835 */     }  if (object instanceof Collection) {
/*  836 */       Iterator iterator = ((Collection)object).iterator();
/*  837 */       return get(iterator, index);
/*  838 */     }  if (object instanceof Enumeration) {
/*  839 */       Enumeration it = (Enumeration)object;
/*  840 */       while (it.hasMoreElements()) {
/*  841 */         index--;
/*  842 */         if (index == -1) {
/*  843 */           return it.nextElement();
/*      */         }
/*  845 */         it.nextElement();
/*      */       } 
/*      */       
/*  848 */       throw new IndexOutOfBoundsException("Entry does not exist: " + index);
/*  849 */     }  if (object == null) {
/*  850 */       throw new IllegalArgumentException("Unsupported object type: null");
/*      */     }
/*      */     try {
/*  853 */       return Array.get(object, index);
/*  854 */     } catch (IllegalArgumentException ex) {
/*  855 */       throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int size(Object object) {
/*  878 */     int total = 0;
/*  879 */     if (object instanceof Map)
/*  880 */     { total = ((Map)object).size(); }
/*  881 */     else if (object instanceof Collection)
/*  882 */     { total = ((Collection)object).size(); }
/*  883 */     else if (object instanceof Object[])
/*  884 */     { total = ((Object[])object).length; }
/*  885 */     else if (object instanceof Iterator)
/*  886 */     { Iterator it = (Iterator)object;
/*  887 */       while (it.hasNext()) {
/*  888 */         total++;
/*  889 */         it.next();
/*      */       }  }
/*  891 */     else if (object instanceof Enumeration)
/*  892 */     { Enumeration it = (Enumeration)object;
/*  893 */       while (it.hasMoreElements()) {
/*  894 */         total++;
/*  895 */         it.nextElement();
/*      */       }  }
/*  897 */     else { if (object == null) {
/*  898 */         throw new IllegalArgumentException("Unsupported object type: null");
/*      */       }
/*      */       try {
/*  901 */         total = Array.getLength(object);
/*  902 */       } catch (IllegalArgumentException ex) {
/*  903 */         throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
/*      */       }  }
/*      */     
/*  906 */     return total;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverseArray(Object[] array) {
/*  915 */     int i = 0;
/*  916 */     int j = array.length - 1;
/*      */ 
/*      */     
/*  919 */     while (j > i) {
/*  920 */       Object tmp = array[j];
/*  921 */       array[j] = array[i];
/*  922 */       array[i] = tmp;
/*  923 */       j--;
/*  924 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final int getFreq(Object obj, Map freqMap) {
/*  929 */     Integer count = (Integer)freqMap.get(obj);
/*  930 */     if (count != null) {
/*  931 */       return count.intValue();
/*      */     }
/*  933 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFull(Collection coll) {
/*  952 */     if (coll == null) {
/*  953 */       throw new NullPointerException("The collection must not be null");
/*      */     }
/*  955 */     if (coll instanceof BoundedCollection) {
/*  956 */       return ((BoundedCollection)coll).isFull();
/*      */     }
/*      */     try {
/*  959 */       BoundedCollection bcoll = UnmodifiableBoundedCollection.decorateUsing(coll);
/*  960 */       return bcoll.isFull();
/*      */     }
/*  962 */     catch (IllegalArgumentException ex) {
/*  963 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int maxSize(Collection coll) {
/*  983 */     if (coll == null) {
/*  984 */       throw new NullPointerException("The collection must not be null");
/*      */     }
/*  986 */     if (coll instanceof BoundedCollection) {
/*  987 */       return ((BoundedCollection)coll).maxSize();
/*      */     }
/*      */     try {
/*  990 */       BoundedCollection bcoll = UnmodifiableBoundedCollection.decorateUsing(coll);
/*  991 */       return bcoll.maxSize();
/*      */     }
/*  993 */     catch (IllegalArgumentException ex) {
/*  994 */       return -1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection synchronizedCollection(Collection collection) {
/* 1022 */     return SynchronizedCollection.decorate(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection unmodifiableCollection(Collection collection) {
/* 1035 */     return UnmodifiableCollection.decorate(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection predicatedCollection(Collection collection, Predicate predicate) {
/* 1052 */     return PredicatedCollection.decorate(collection, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection typedCollection(Collection collection, Class type) {
/* 1065 */     return TypedCollection.decorate(collection, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection transformedCollection(Collection collection, Transformer transformer) {
/* 1081 */     return TransformedCollection.decorate(collection, transformer);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/CollectionUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */