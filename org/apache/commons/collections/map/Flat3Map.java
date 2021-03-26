/*      */ package org.apache.commons.collections.map;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.IterableMap;
/*      */ import org.apache.commons.collections.MapIterator;
/*      */ import org.apache.commons.collections.ResettableIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyMapIterator;
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
/*      */ 
/*      */ public class Flat3Map
/*      */   implements Serializable, Cloneable, IterableMap
/*      */ {
/*      */   private static final long serialVersionUID = -6701087419741928296L;
/*      */   private transient int size;
/*      */   private transient int hash1;
/*      */   private transient int hash2;
/*      */   private transient int hash3;
/*      */   private transient Object key1;
/*      */   private transient Object key2;
/*      */   private transient Object key3;
/*      */   private transient Object value1;
/*      */   private transient Object value2;
/*      */   private transient Object value3;
/*      */   private transient AbstractHashedMap delegateMap;
/*      */   
/*      */   public Flat3Map() {}
/*      */   
/*      */   public Flat3Map(Map map) {
/*  111 */     putAll(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(Object key) {
/*  122 */     if (this.delegateMap != null) {
/*  123 */       return this.delegateMap.get(key);
/*      */     }
/*  125 */     if (key == null) {
/*  126 */       switch (this.size) {
/*      */         
/*      */         case 3:
/*  129 */           if (this.key3 == null) return this.value3; 
/*      */         case 2:
/*  131 */           if (this.key2 == null) return this.value2; 
/*      */         case 1:
/*  133 */           if (this.key1 == null) return this.value1; 
/*      */           break;
/*      */       } 
/*  136 */     } else if (this.size > 0) {
/*  137 */       int hashCode = key.hashCode();
/*  138 */       switch (this.size) {
/*      */         
/*      */         case 3:
/*  141 */           if (this.hash3 == hashCode && key.equals(this.key3)) return this.value3; 
/*      */         case 2:
/*  143 */           if (this.hash2 == hashCode && key.equals(this.key2)) return this.value2; 
/*      */         case 1:
/*  145 */           if (this.hash1 == hashCode && key.equals(this.key1)) return this.value1; 
/*      */           break;
/*      */       } 
/*      */     } 
/*  149 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  158 */     if (this.delegateMap != null) {
/*  159 */       return this.delegateMap.size();
/*      */     }
/*  161 */     return this.size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  170 */     return (size() == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  181 */     if (this.delegateMap != null) {
/*  182 */       return this.delegateMap.containsKey(key);
/*      */     }
/*  184 */     if (key == null) {
/*  185 */       switch (this.size) {
/*      */         case 3:
/*  187 */           if (this.key3 == null) return true; 
/*      */         case 2:
/*  189 */           if (this.key2 == null) return true; 
/*      */         case 1:
/*  191 */           if (this.key1 == null) return true; 
/*      */           break;
/*      */       } 
/*  194 */     } else if (this.size > 0) {
/*  195 */       int hashCode = key.hashCode();
/*  196 */       switch (this.size) {
/*      */         case 3:
/*  198 */           if (this.hash3 == hashCode && key.equals(this.key3)) return true; 
/*      */         case 2:
/*  200 */           if (this.hash2 == hashCode && key.equals(this.key2)) return true; 
/*      */         case 1:
/*  202 */           if (this.hash1 == hashCode && key.equals(this.key1)) return true; 
/*      */           break;
/*      */       } 
/*      */     } 
/*  206 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  216 */     if (this.delegateMap != null) {
/*  217 */       return this.delegateMap.containsValue(value);
/*      */     }
/*  219 */     if (value == null) {
/*  220 */       switch (this.size) {
/*      */         case 3:
/*  222 */           if (this.value3 == null) return true; 
/*      */         case 2:
/*  224 */           if (this.value2 == null) return true; 
/*      */         case 1:
/*  226 */           if (this.value1 == null) return true;  break;
/*      */       } 
/*      */     } else {
/*  229 */       switch (this.size) {
/*      */         case 3:
/*  231 */           if (value.equals(this.value3)) return true; 
/*      */         case 2:
/*  233 */           if (value.equals(this.value2)) return true; 
/*      */         case 1:
/*  235 */           if (value.equals(this.value1)) return true;  break;
/*      */       } 
/*      */     } 
/*  238 */     return false;
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
/*      */   public Object put(Object key, Object value) {
/*  250 */     if (this.delegateMap != null) {
/*  251 */       return this.delegateMap.put(key, value);
/*      */     }
/*      */     
/*  254 */     if (key == null) {
/*  255 */       switch (this.size) {
/*      */         case 3:
/*  257 */           if (this.key3 == null) {
/*  258 */             Object old = this.value3;
/*  259 */             this.value3 = value;
/*  260 */             return old;
/*      */           } 
/*      */         case 2:
/*  263 */           if (this.key2 == null) {
/*  264 */             Object old = this.value2;
/*  265 */             this.value2 = value;
/*  266 */             return old;
/*      */           } 
/*      */         case 1:
/*  269 */           if (this.key1 == null) {
/*  270 */             Object old = this.value1;
/*  271 */             this.value1 = value;
/*  272 */             return old;
/*      */           } 
/*      */           break;
/*      */       } 
/*  276 */     } else if (this.size > 0) {
/*  277 */       int hashCode = key.hashCode();
/*  278 */       switch (this.size) {
/*      */         case 3:
/*  280 */           if (this.hash3 == hashCode && key.equals(this.key3)) {
/*  281 */             Object old = this.value3;
/*  282 */             this.value3 = value;
/*  283 */             return old;
/*      */           } 
/*      */         case 2:
/*  286 */           if (this.hash2 == hashCode && key.equals(this.key2)) {
/*  287 */             Object old = this.value2;
/*  288 */             this.value2 = value;
/*  289 */             return old;
/*      */           } 
/*      */         case 1:
/*  292 */           if (this.hash1 == hashCode && key.equals(this.key1)) {
/*  293 */             Object old = this.value1;
/*  294 */             this.value1 = value;
/*  295 */             return old;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/*  302 */     switch (this.size)
/*      */     { default:
/*  304 */         convertToMap();
/*  305 */         this.delegateMap.put(key, value);
/*  306 */         return null;
/*      */       case 2:
/*  308 */         this.hash3 = (key == null) ? 0 : key.hashCode();
/*  309 */         this.key3 = key;
/*  310 */         this.value3 = value;
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
/*  323 */         this.size++;
/*  324 */         return null;case 1: this.hash2 = (key == null) ? 0 : key.hashCode(); this.key2 = key; this.value2 = value; this.size++; return null;case 0: break; }  this.hash1 = (key == null) ? 0 : key.hashCode(); this.key1 = key; this.value1 = value; this.size++; return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map map) {
/*  334 */     int size = map.size();
/*  335 */     if (size == 0) {
/*      */       return;
/*      */     }
/*  338 */     if (this.delegateMap != null) {
/*  339 */       this.delegateMap.putAll(map);
/*      */       return;
/*      */     } 
/*  342 */     if (size < 4) {
/*  343 */       for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/*  344 */         Map.Entry entry = it.next();
/*  345 */         put(entry.getKey(), entry.getValue());
/*      */       } 
/*      */     } else {
/*  348 */       convertToMap();
/*  349 */       this.delegateMap.putAll(map);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void convertToMap() {
/*  357 */     this.delegateMap = createDelegateMap();
/*  358 */     switch (this.size) {
/*      */       case 3:
/*  360 */         this.delegateMap.put(this.key3, this.value3);
/*      */       case 2:
/*  362 */         this.delegateMap.put(this.key2, this.value2);
/*      */       case 1:
/*  364 */         this.delegateMap.put(this.key1, this.value1);
/*      */         break;
/*      */     } 
/*  367 */     this.size = 0;
/*  368 */     this.hash1 = this.hash2 = this.hash3 = 0;
/*  369 */     this.key1 = this.key2 = this.key3 = null;
/*  370 */     this.value1 = this.value2 = this.value3 = null;
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
/*      */   protected AbstractHashedMap createDelegateMap() {
/*  384 */     return new HashedMap();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(Object key) {
/*  394 */     if (this.delegateMap != null) {
/*  395 */       return this.delegateMap.remove(key);
/*      */     }
/*  397 */     if (this.size == 0) {
/*  398 */       return null;
/*      */     }
/*  400 */     if (key == null) {
/*  401 */       switch (this.size) {
/*      */         case 3:
/*  403 */           if (this.key3 == null) {
/*  404 */             Object old = this.value3;
/*  405 */             this.hash3 = 0;
/*  406 */             this.key3 = null;
/*  407 */             this.value3 = null;
/*  408 */             this.size = 2;
/*  409 */             return old;
/*      */           } 
/*  411 */           if (this.key2 == null) {
/*  412 */             Object old = this.value3;
/*  413 */             this.hash2 = this.hash3;
/*  414 */             this.key2 = this.key3;
/*  415 */             this.value2 = this.value3;
/*  416 */             this.hash3 = 0;
/*  417 */             this.key3 = null;
/*  418 */             this.value3 = null;
/*  419 */             this.size = 2;
/*  420 */             return old;
/*      */           } 
/*  422 */           if (this.key1 == null) {
/*  423 */             Object old = this.value3;
/*  424 */             this.hash1 = this.hash3;
/*  425 */             this.key1 = this.key3;
/*  426 */             this.value1 = this.value3;
/*  427 */             this.hash3 = 0;
/*  428 */             this.key3 = null;
/*  429 */             this.value3 = null;
/*  430 */             this.size = 2;
/*  431 */             return old;
/*      */           } 
/*  433 */           return null;
/*      */         case 2:
/*  435 */           if (this.key2 == null) {
/*  436 */             Object old = this.value2;
/*  437 */             this.hash2 = 0;
/*  438 */             this.key2 = null;
/*  439 */             this.value2 = null;
/*  440 */             this.size = 1;
/*  441 */             return old;
/*      */           } 
/*  443 */           if (this.key1 == null) {
/*  444 */             Object old = this.value2;
/*  445 */             this.hash1 = this.hash2;
/*  446 */             this.key1 = this.key2;
/*  447 */             this.value1 = this.value2;
/*  448 */             this.hash2 = 0;
/*  449 */             this.key2 = null;
/*  450 */             this.value2 = null;
/*  451 */             this.size = 1;
/*  452 */             return old;
/*      */           } 
/*  454 */           return null;
/*      */         case 1:
/*  456 */           if (this.key1 == null) {
/*  457 */             Object old = this.value1;
/*  458 */             this.hash1 = 0;
/*  459 */             this.key1 = null;
/*  460 */             this.value1 = null;
/*  461 */             this.size = 0;
/*  462 */             return old;
/*      */           } 
/*      */           break;
/*      */       } 
/*  466 */     } else if (this.size > 0) {
/*  467 */       int hashCode = key.hashCode();
/*  468 */       switch (this.size) {
/*      */         case 3:
/*  470 */           if (this.hash3 == hashCode && key.equals(this.key3)) {
/*  471 */             Object old = this.value3;
/*  472 */             this.hash3 = 0;
/*  473 */             this.key3 = null;
/*  474 */             this.value3 = null;
/*  475 */             this.size = 2;
/*  476 */             return old;
/*      */           } 
/*  478 */           if (this.hash2 == hashCode && key.equals(this.key2)) {
/*  479 */             Object old = this.value3;
/*  480 */             this.hash2 = this.hash3;
/*  481 */             this.key2 = this.key3;
/*  482 */             this.value2 = this.value3;
/*  483 */             this.hash3 = 0;
/*  484 */             this.key3 = null;
/*  485 */             this.value3 = null;
/*  486 */             this.size = 2;
/*  487 */             return old;
/*      */           } 
/*  489 */           if (this.hash1 == hashCode && key.equals(this.key1)) {
/*  490 */             Object old = this.value3;
/*  491 */             this.hash1 = this.hash3;
/*  492 */             this.key1 = this.key3;
/*  493 */             this.value1 = this.value3;
/*  494 */             this.hash3 = 0;
/*  495 */             this.key3 = null;
/*  496 */             this.value3 = null;
/*  497 */             this.size = 2;
/*  498 */             return old;
/*      */           } 
/*  500 */           return null;
/*      */         case 2:
/*  502 */           if (this.hash2 == hashCode && key.equals(this.key2)) {
/*  503 */             Object old = this.value2;
/*  504 */             this.hash2 = 0;
/*  505 */             this.key2 = null;
/*  506 */             this.value2 = null;
/*  507 */             this.size = 1;
/*  508 */             return old;
/*      */           } 
/*  510 */           if (this.hash1 == hashCode && key.equals(this.key1)) {
/*  511 */             Object old = this.value2;
/*  512 */             this.hash1 = this.hash2;
/*  513 */             this.key1 = this.key2;
/*  514 */             this.value1 = this.value2;
/*  515 */             this.hash2 = 0;
/*  516 */             this.key2 = null;
/*  517 */             this.value2 = null;
/*  518 */             this.size = 1;
/*  519 */             return old;
/*      */           } 
/*  521 */           return null;
/*      */         case 1:
/*  523 */           if (this.hash1 == hashCode && key.equals(this.key1)) {
/*  524 */             Object old = this.value1;
/*  525 */             this.hash1 = 0;
/*  526 */             this.key1 = null;
/*  527 */             this.value1 = null;
/*  528 */             this.size = 0;
/*  529 */             return old;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/*  534 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  542 */     if (this.delegateMap != null) {
/*  543 */       this.delegateMap.clear();
/*  544 */       this.delegateMap = null;
/*      */     } else {
/*  546 */       this.size = 0;
/*  547 */       this.hash1 = this.hash2 = this.hash3 = 0;
/*  548 */       this.key1 = this.key2 = this.key3 = null;
/*  549 */       this.value1 = this.value2 = this.value3 = null;
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
/*      */   public MapIterator mapIterator() {
/*  566 */     if (this.delegateMap != null) {
/*  567 */       return this.delegateMap.mapIterator();
/*      */     }
/*  569 */     if (this.size == 0) {
/*  570 */       return EmptyMapIterator.INSTANCE;
/*      */     }
/*  572 */     return new FlatMapIterator(this);
/*      */   }
/*      */ 
/*      */   
/*      */   static class FlatMapIterator
/*      */     implements MapIterator, ResettableIterator
/*      */   {
/*      */     private final Flat3Map parent;
/*  580 */     private int nextIndex = 0;
/*      */     
/*      */     private boolean canRemove = false;
/*      */     
/*      */     FlatMapIterator(Flat3Map parent) {
/*  585 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  589 */       return (this.nextIndex < this.parent.size);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  593 */       if (!hasNext()) {
/*  594 */         throw new NoSuchElementException("No next() entry in the iteration");
/*      */       }
/*  596 */       this.canRemove = true;
/*  597 */       this.nextIndex++;
/*  598 */       return getKey();
/*      */     }
/*      */     
/*      */     public void remove() {
/*  602 */       if (!this.canRemove) {
/*  603 */         throw new IllegalStateException("remove() can only be called once after next()");
/*      */       }
/*  605 */       this.parent.remove(getKey());
/*  606 */       this.nextIndex--;
/*  607 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public Object getKey() {
/*  611 */       if (!this.canRemove) {
/*  612 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*      */       }
/*  614 */       switch (this.nextIndex) {
/*      */         case 3:
/*  616 */           return this.parent.key3;
/*      */         case 2:
/*  618 */           return this.parent.key2;
/*      */         case 1:
/*  620 */           return this.parent.key1;
/*      */       } 
/*  622 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Object getValue() {
/*  626 */       if (!this.canRemove) {
/*  627 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*      */       }
/*  629 */       switch (this.nextIndex) {
/*      */         case 3:
/*  631 */           return this.parent.value3;
/*      */         case 2:
/*  633 */           return this.parent.value2;
/*      */         case 1:
/*  635 */           return this.parent.value1;
/*      */       } 
/*  637 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/*  641 */       if (!this.canRemove) {
/*  642 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*      */       }
/*  644 */       Object old = getValue();
/*  645 */       switch (this.nextIndex) {
/*      */         case 3:
/*  647 */           this.parent.value3 = value;
/*      */         case 2:
/*  649 */           this.parent.value2 = value;
/*      */         case 1:
/*  651 */           this.parent.value1 = value; break;
/*      */       } 
/*  653 */       return old;
/*      */     }
/*      */     
/*      */     public void reset() {
/*  657 */       this.nextIndex = 0;
/*  658 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  662 */       if (this.canRemove) {
/*  663 */         return "Iterator[" + getKey() + "=" + getValue() + "]";
/*      */       }
/*  665 */       return "Iterator[]";
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
/*      */   public Set entrySet() {
/*  680 */     if (this.delegateMap != null) {
/*  681 */       return this.delegateMap.entrySet();
/*      */     }
/*  683 */     return new EntrySet(this);
/*      */   }
/*      */ 
/*      */   
/*      */   static class EntrySet
/*      */     extends AbstractSet
/*      */   {
/*      */     private final Flat3Map parent;
/*      */ 
/*      */     
/*      */     EntrySet(Flat3Map parent) {
/*  694 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  698 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  702 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/*  706 */       if (!(obj instanceof Map.Entry)) {
/*  707 */         return false;
/*      */       }
/*  709 */       Map.Entry entry = (Map.Entry)obj;
/*  710 */       Object key = entry.getKey();
/*  711 */       boolean result = this.parent.containsKey(key);
/*  712 */       this.parent.remove(key);
/*  713 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  717 */       if (this.parent.delegateMap != null) {
/*  718 */         return this.parent.delegateMap.entrySet().iterator();
/*      */       }
/*  720 */       if (this.parent.size() == 0) {
/*  721 */         return EmptyIterator.INSTANCE;
/*      */       }
/*  723 */       return new Flat3Map.EntrySetIterator(this.parent);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class EntrySetIterator
/*      */     implements Iterator, Map.Entry
/*      */   {
/*      */     private final Flat3Map parent;
/*  732 */     private int nextIndex = 0;
/*      */     
/*      */     private boolean canRemove = false;
/*      */     
/*      */     EntrySetIterator(Flat3Map parent) {
/*  737 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  741 */       return (this.nextIndex < this.parent.size);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  745 */       if (!hasNext()) {
/*  746 */         throw new NoSuchElementException("No next() entry in the iteration");
/*      */       }
/*  748 */       this.canRemove = true;
/*  749 */       this.nextIndex++;
/*  750 */       return this;
/*      */     }
/*      */     
/*      */     public void remove() {
/*  754 */       if (!this.canRemove) {
/*  755 */         throw new IllegalStateException("remove() can only be called once after next()");
/*      */       }
/*  757 */       this.parent.remove(getKey());
/*  758 */       this.nextIndex--;
/*  759 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public Object getKey() {
/*  763 */       if (!this.canRemove) {
/*  764 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*      */       }
/*  766 */       switch (this.nextIndex) {
/*      */         case 3:
/*  768 */           return this.parent.key3;
/*      */         case 2:
/*  770 */           return this.parent.key2;
/*      */         case 1:
/*  772 */           return this.parent.key1;
/*      */       } 
/*  774 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Object getValue() {
/*  778 */       if (!this.canRemove) {
/*  779 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*      */       }
/*  781 */       switch (this.nextIndex) {
/*      */         case 3:
/*  783 */           return this.parent.value3;
/*      */         case 2:
/*  785 */           return this.parent.value2;
/*      */         case 1:
/*  787 */           return this.parent.value1;
/*      */       } 
/*  789 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/*  793 */       if (!this.canRemove) {
/*  794 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*      */       }
/*  796 */       Object old = getValue();
/*  797 */       switch (this.nextIndex) {
/*      */         case 3:
/*  799 */           this.parent.value3 = value;
/*      */         case 2:
/*  801 */           this.parent.value2 = value;
/*      */         case 1:
/*  803 */           this.parent.value1 = value; break;
/*      */       } 
/*  805 */       return old;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/*  809 */       if (!this.canRemove) {
/*  810 */         return false;
/*      */       }
/*  812 */       if (!(obj instanceof Map.Entry)) {
/*  813 */         return false;
/*      */       }
/*  815 */       Map.Entry other = (Map.Entry)obj;
/*  816 */       Object key = getKey();
/*  817 */       Object value = getValue();
/*  818 */       return (((key == null) ? (other.getKey() == null) : key.equals(other.getKey())) && ((value == null) ? (other.getValue() == null) : value.equals(other.getValue())));
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  823 */       if (!this.canRemove) {
/*  824 */         return 0;
/*      */       }
/*  826 */       Object key = getKey();
/*  827 */       Object value = getValue();
/*  828 */       return ((key == null) ? 0 : key.hashCode()) ^ ((value == null) ? 0 : value.hashCode());
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/*  833 */       if (this.canRemove) {
/*  834 */         return getKey() + "=" + getValue();
/*      */       }
/*  836 */       return "";
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
/*      */   public Set keySet() {
/*  849 */     if (this.delegateMap != null) {
/*  850 */       return this.delegateMap.keySet();
/*      */     }
/*  852 */     return new KeySet(this);
/*      */   }
/*      */ 
/*      */   
/*      */   static class KeySet
/*      */     extends AbstractSet
/*      */   {
/*      */     private final Flat3Map parent;
/*      */ 
/*      */     
/*      */     KeySet(Flat3Map parent) {
/*  863 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  867 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  871 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object key) {
/*  875 */       return this.parent.containsKey(key);
/*      */     }
/*      */     
/*      */     public boolean remove(Object key) {
/*  879 */       boolean result = this.parent.containsKey(key);
/*  880 */       this.parent.remove(key);
/*  881 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  885 */       if (this.parent.delegateMap != null) {
/*  886 */         return this.parent.delegateMap.keySet().iterator();
/*      */       }
/*  888 */       if (this.parent.size() == 0) {
/*  889 */         return EmptyIterator.INSTANCE;
/*      */       }
/*  891 */       return new Flat3Map.KeySetIterator(this.parent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class KeySetIterator
/*      */     extends EntrySetIterator
/*      */   {
/*      */     KeySetIterator(Flat3Map parent) {
/*  901 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  905 */       super.next();
/*  906 */       return getKey();
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
/*      */   public Collection values() {
/*  918 */     if (this.delegateMap != null) {
/*  919 */       return this.delegateMap.values();
/*      */     }
/*  921 */     return new Values(this);
/*      */   }
/*      */ 
/*      */   
/*      */   static class Values
/*      */     extends AbstractCollection
/*      */   {
/*      */     private final Flat3Map parent;
/*      */ 
/*      */     
/*      */     Values(Flat3Map parent) {
/*  932 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  936 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  940 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object value) {
/*  944 */       return this.parent.containsValue(value);
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  948 */       if (this.parent.delegateMap != null) {
/*  949 */         return this.parent.delegateMap.values().iterator();
/*      */       }
/*  951 */       if (this.parent.size() == 0) {
/*  952 */         return EmptyIterator.INSTANCE;
/*      */       }
/*  954 */       return new Flat3Map.ValuesIterator(this.parent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class ValuesIterator
/*      */     extends EntrySetIterator
/*      */   {
/*      */     ValuesIterator(Flat3Map parent) {
/*  964 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  968 */       super.next();
/*  969 */       return getValue();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  978 */     out.defaultWriteObject();
/*  979 */     out.writeInt(size());
/*  980 */     for (MapIterator it = mapIterator(); it.hasNext(); ) {
/*  981 */       out.writeObject(it.next());
/*  982 */       out.writeObject(it.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  990 */     in.defaultReadObject();
/*  991 */     int count = in.readInt();
/*  992 */     if (count > 3) {
/*  993 */       this.delegateMap = createDelegateMap();
/*      */     }
/*  995 */     for (int i = count; i > 0; i--) {
/*  996 */       put(in.readObject(), in.readObject());
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
/*      */   public Object clone() {
/*      */     try {
/* 1009 */       Flat3Map cloned = (Flat3Map)super.clone();
/* 1010 */       if (cloned.delegateMap != null) {
/* 1011 */         cloned.delegateMap = (HashedMap)cloned.delegateMap.clone();
/*      */       }
/* 1013 */       return cloned;
/* 1014 */     } catch (CloneNotSupportedException ex) {
/* 1015 */       throw new InternalError();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1026 */     if (obj == this) {
/* 1027 */       return true;
/*      */     }
/* 1029 */     if (this.delegateMap != null) {
/* 1030 */       return this.delegateMap.equals(obj);
/*      */     }
/* 1032 */     if (!(obj instanceof Map)) {
/* 1033 */       return false;
/*      */     }
/* 1035 */     Map other = (Map)obj;
/* 1036 */     if (this.size != other.size()) {
/* 1037 */       return false;
/*      */     }
/* 1039 */     if (this.size > 0) {
/* 1040 */       Object otherValue = null;
/* 1041 */       switch (this.size) {
/*      */         case 3:
/* 1043 */           if (!other.containsKey(this.key3)) {
/* 1044 */             otherValue = other.get(this.key3);
/* 1045 */             if ((this.value3 == null) ? (otherValue != null) : !this.value3.equals(otherValue)) {
/* 1046 */               return false;
/*      */             }
/*      */           } 
/*      */         case 2:
/* 1050 */           if (!other.containsKey(this.key2)) {
/* 1051 */             otherValue = other.get(this.key2);
/* 1052 */             if ((this.value2 == null) ? (otherValue != null) : !this.value2.equals(otherValue)) {
/* 1053 */               return false;
/*      */             }
/*      */           } 
/*      */         case 1:
/* 1057 */           if (!other.containsKey(this.key1)) {
/* 1058 */             otherValue = other.get(this.key1);
/* 1059 */             if ((this.value1 == null) ? (otherValue != null) : !this.value1.equals(otherValue))
/* 1060 */               return false; 
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/* 1065 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1074 */     if (this.delegateMap != null) {
/* 1075 */       return this.delegateMap.hashCode();
/*      */     }
/* 1077 */     int total = 0;
/* 1078 */     switch (this.size) {
/*      */       case 3:
/* 1080 */         total += this.hash3 ^ ((this.value3 == null) ? 0 : this.value3.hashCode());
/*      */       case 2:
/* 1082 */         total += this.hash2 ^ ((this.value2 == null) ? 0 : this.value2.hashCode());
/*      */       case 1:
/* 1084 */         total += this.hash1 ^ ((this.value1 == null) ? 0 : this.value1.hashCode()); break;
/*      */     } 
/* 1086 */     return total;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1095 */     if (this.delegateMap != null) {
/* 1096 */       return this.delegateMap.toString();
/*      */     }
/* 1098 */     if (this.size == 0) {
/* 1099 */       return "{}";
/*      */     }
/* 1101 */     StringBuffer buf = new StringBuffer(128);
/* 1102 */     buf.append('{');
/* 1103 */     switch (this.size) {
/*      */       case 3:
/* 1105 */         buf.append((this.key3 == this) ? "(this Map)" : this.key3);
/* 1106 */         buf.append('=');
/* 1107 */         buf.append((this.value3 == this) ? "(this Map)" : this.value3);
/* 1108 */         buf.append(',');
/*      */       case 2:
/* 1110 */         buf.append((this.key2 == this) ? "(this Map)" : this.key2);
/* 1111 */         buf.append('=');
/* 1112 */         buf.append((this.value2 == this) ? "(this Map)" : this.value2);
/* 1113 */         buf.append(',');
/*      */       case 1:
/* 1115 */         buf.append((this.key1 == this) ? "(this Map)" : this.key1);
/* 1116 */         buf.append('=');
/* 1117 */         buf.append((this.value1 == this) ? "(this Map)" : this.value1); break;
/*      */     } 
/* 1119 */     buf.append('}');
/* 1120 */     return buf.toString();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/Flat3Map.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */