/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.IterableMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.keyvalue.MultiKey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiKeyMap
/*     */   implements Serializable, IterableMap
/*     */ {
/*     */   private static final long serialVersionUID = -1788199231038721040L;
/*     */   protected final AbstractHashedMap map;
/*     */   
/*     */   public static MultiKeyMap decorate(AbstractHashedMap map) {
/*  92 */     if (map == null) {
/*  93 */       throw new IllegalArgumentException("Map must not be null");
/*     */     }
/*  95 */     if (map.size() > 0) {
/*  96 */       throw new IllegalArgumentException("Map must be empty");
/*     */     }
/*  98 */     return new MultiKeyMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiKeyMap() {
/* 107 */     this.map = new HashedMap();
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
/*     */   protected MultiKeyMap(AbstractHashedMap map) {
/* 120 */     this.map = map;
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
/*     */   public Object get(Object key1, Object key2) {
/* 132 */     int hashCode = hash(key1, key2);
/* 133 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 134 */     while (entry != null) {
/* 135 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2)) {
/* 136 */         return entry.getValue();
/*     */       }
/* 138 */       entry = entry.next;
/*     */     } 
/* 140 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key1, Object key2) {
/* 151 */     int hashCode = hash(key1, key2);
/* 152 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 153 */     while (entry != null) {
/* 154 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2)) {
/* 155 */         return true;
/*     */       }
/* 157 */       entry = entry.next;
/*     */     } 
/* 159 */     return false;
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
/*     */   public Object put(Object key1, Object key2, Object value) {
/* 171 */     int hashCode = hash(key1, key2);
/* 172 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 173 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 174 */     while (entry != null) {
/* 175 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2)) {
/* 176 */         Object oldValue = entry.getValue();
/* 177 */         this.map.updateEntry(entry, value);
/* 178 */         return oldValue;
/*     */       } 
/* 180 */       entry = entry.next;
/*     */     } 
/*     */     
/* 183 */     this.map.addMapping(index, hashCode, new MultiKey(key1, key2), value);
/* 184 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object key1, Object key2) {
/* 195 */     int hashCode = hash(key1, key2);
/* 196 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 197 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 198 */     AbstractHashedMap.HashEntry previous = null;
/* 199 */     while (entry != null) {
/* 200 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2)) {
/* 201 */         Object oldValue = entry.getValue();
/* 202 */         this.map.removeMapping(entry, index, previous);
/* 203 */         return oldValue;
/*     */       } 
/* 205 */       previous = entry;
/* 206 */       entry = entry.next;
/*     */     } 
/* 208 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int hash(Object key1, Object key2) {
/* 219 */     int h = 0;
/* 220 */     if (key1 != null) {
/* 221 */       h ^= key1.hashCode();
/*     */     }
/* 223 */     if (key2 != null) {
/* 224 */       h ^= key2.hashCode();
/*     */     }
/* 226 */     h += h << 9 ^ 0xFFFFFFFF;
/* 227 */     h ^= h >>> 14;
/* 228 */     h += h << 4;
/* 229 */     h ^= h >>> 10;
/* 230 */     return h;
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
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2) {
/* 242 */     MultiKey multi = (MultiKey)entry.getKey();
/* 243 */     return (multi.size() == 2 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))));
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
/*     */   public Object get(Object key1, Object key2, Object key3) {
/* 259 */     int hashCode = hash(key1, key2, key3);
/* 260 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 261 */     while (entry != null) {
/* 262 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3)) {
/* 263 */         return entry.getValue();
/*     */       }
/* 265 */       entry = entry.next;
/*     */     } 
/* 267 */     return null;
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
/*     */   public boolean containsKey(Object key1, Object key2, Object key3) {
/* 279 */     int hashCode = hash(key1, key2, key3);
/* 280 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 281 */     while (entry != null) {
/* 282 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3)) {
/* 283 */         return true;
/*     */       }
/* 285 */       entry = entry.next;
/*     */     } 
/* 287 */     return false;
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
/*     */   public Object put(Object key1, Object key2, Object key3, Object value) {
/* 300 */     int hashCode = hash(key1, key2, key3);
/* 301 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 302 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 303 */     while (entry != null) {
/* 304 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3)) {
/* 305 */         Object oldValue = entry.getValue();
/* 306 */         this.map.updateEntry(entry, value);
/* 307 */         return oldValue;
/*     */       } 
/* 309 */       entry = entry.next;
/*     */     } 
/*     */     
/* 312 */     this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3), value);
/* 313 */     return null;
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
/*     */   public Object remove(Object key1, Object key2, Object key3) {
/* 325 */     int hashCode = hash(key1, key2, key3);
/* 326 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 327 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 328 */     AbstractHashedMap.HashEntry previous = null;
/* 329 */     while (entry != null) {
/* 330 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3)) {
/* 331 */         Object oldValue = entry.getValue();
/* 332 */         this.map.removeMapping(entry, index, previous);
/* 333 */         return oldValue;
/*     */       } 
/* 335 */       previous = entry;
/* 336 */       entry = entry.next;
/*     */     } 
/* 338 */     return null;
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
/*     */   protected int hash(Object key1, Object key2, Object key3) {
/* 350 */     int h = 0;
/* 351 */     if (key1 != null) {
/* 352 */       h ^= key1.hashCode();
/*     */     }
/* 354 */     if (key2 != null) {
/* 355 */       h ^= key2.hashCode();
/*     */     }
/* 357 */     if (key3 != null) {
/* 358 */       h ^= key3.hashCode();
/*     */     }
/* 360 */     h += h << 9 ^ 0xFFFFFFFF;
/* 361 */     h ^= h >>> 14;
/* 362 */     h += h << 4;
/* 363 */     h ^= h >>> 10;
/* 364 */     return h;
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
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3) {
/* 377 */     MultiKey multi = (MultiKey)entry.getKey();
/* 378 */     return (multi.size() == 3 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))));
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
/*     */   public Object get(Object key1, Object key2, Object key3, Object key4) {
/* 396 */     int hashCode = hash(key1, key2, key3, key4);
/* 397 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 398 */     while (entry != null) {
/* 399 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4)) {
/* 400 */         return entry.getValue();
/*     */       }
/* 402 */       entry = entry.next;
/*     */     } 
/* 404 */     return null;
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
/*     */   public boolean containsKey(Object key1, Object key2, Object key3, Object key4) {
/* 417 */     int hashCode = hash(key1, key2, key3, key4);
/* 418 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 419 */     while (entry != null) {
/* 420 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4)) {
/* 421 */         return true;
/*     */       }
/* 423 */       entry = entry.next;
/*     */     } 
/* 425 */     return false;
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
/*     */   public Object put(Object key1, Object key2, Object key3, Object key4, Object value) {
/* 439 */     int hashCode = hash(key1, key2, key3, key4);
/* 440 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 441 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 442 */     while (entry != null) {
/* 443 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4)) {
/* 444 */         Object oldValue = entry.getValue();
/* 445 */         this.map.updateEntry(entry, value);
/* 446 */         return oldValue;
/*     */       } 
/* 448 */       entry = entry.next;
/*     */     } 
/*     */     
/* 451 */     this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3, key4), value);
/* 452 */     return null;
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
/*     */   public Object remove(Object key1, Object key2, Object key3, Object key4) {
/* 465 */     int hashCode = hash(key1, key2, key3, key4);
/* 466 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 467 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 468 */     AbstractHashedMap.HashEntry previous = null;
/* 469 */     while (entry != null) {
/* 470 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4)) {
/* 471 */         Object oldValue = entry.getValue();
/* 472 */         this.map.removeMapping(entry, index, previous);
/* 473 */         return oldValue;
/*     */       } 
/* 475 */       previous = entry;
/* 476 */       entry = entry.next;
/*     */     } 
/* 478 */     return null;
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
/*     */   protected int hash(Object key1, Object key2, Object key3, Object key4) {
/* 491 */     int h = 0;
/* 492 */     if (key1 != null) {
/* 493 */       h ^= key1.hashCode();
/*     */     }
/* 495 */     if (key2 != null) {
/* 496 */       h ^= key2.hashCode();
/*     */     }
/* 498 */     if (key3 != null) {
/* 499 */       h ^= key3.hashCode();
/*     */     }
/* 501 */     if (key4 != null) {
/* 502 */       h ^= key4.hashCode();
/*     */     }
/* 504 */     h += h << 9 ^ 0xFFFFFFFF;
/* 505 */     h ^= h >>> 14;
/* 506 */     h += h << 4;
/* 507 */     h ^= h >>> 10;
/* 508 */     return h;
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
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3, Object key4) {
/* 522 */     MultiKey multi = (MultiKey)entry.getKey();
/* 523 */     return (multi.size() == 4 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3))));
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
/*     */   public Object get(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 543 */     int hashCode = hash(key1, key2, key3, key4, key5);
/* 544 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 545 */     while (entry != null) {
/* 546 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4, key5)) {
/* 547 */         return entry.getValue();
/*     */       }
/* 549 */       entry = entry.next;
/*     */     } 
/* 551 */     return null;
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
/*     */   public boolean containsKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 565 */     int hashCode = hash(key1, key2, key3, key4, key5);
/* 566 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 567 */     while (entry != null) {
/* 568 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4, key5)) {
/* 569 */         return true;
/*     */       }
/* 571 */       entry = entry.next;
/*     */     } 
/* 573 */     return false;
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
/*     */   public Object put(Object key1, Object key2, Object key3, Object key4, Object key5, Object value) {
/* 588 */     int hashCode = hash(key1, key2, key3, key4, key5);
/* 589 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 590 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 591 */     while (entry != null) {
/* 592 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4, key5)) {
/* 593 */         Object oldValue = entry.getValue();
/* 594 */         this.map.updateEntry(entry, value);
/* 595 */         return oldValue;
/*     */       } 
/* 597 */       entry = entry.next;
/*     */     } 
/*     */     
/* 600 */     this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3, key4, key5), value);
/* 601 */     return null;
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
/*     */   public Object remove(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 615 */     int hashCode = hash(key1, key2, key3, key4, key5);
/* 616 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 617 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 618 */     AbstractHashedMap.HashEntry previous = null;
/* 619 */     while (entry != null) {
/* 620 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4, key5)) {
/* 621 */         Object oldValue = entry.getValue();
/* 622 */         this.map.removeMapping(entry, index, previous);
/* 623 */         return oldValue;
/*     */       } 
/* 625 */       previous = entry;
/* 626 */       entry = entry.next;
/*     */     } 
/* 628 */     return null;
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
/*     */   protected int hash(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 642 */     int h = 0;
/* 643 */     if (key1 != null) {
/* 644 */       h ^= key1.hashCode();
/*     */     }
/* 646 */     if (key2 != null) {
/* 647 */       h ^= key2.hashCode();
/*     */     }
/* 649 */     if (key3 != null) {
/* 650 */       h ^= key3.hashCode();
/*     */     }
/* 652 */     if (key4 != null) {
/* 653 */       h ^= key4.hashCode();
/*     */     }
/* 655 */     if (key5 != null) {
/* 656 */       h ^= key5.hashCode();
/*     */     }
/* 658 */     h += h << 9 ^ 0xFFFFFFFF;
/* 659 */     h ^= h >>> 14;
/* 660 */     h += h << 4;
/* 661 */     h ^= h >>> 10;
/* 662 */     return h;
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
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 677 */     MultiKey multi = (MultiKey)entry.getKey();
/* 678 */     return (multi.size() == 5 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3))) && ((key5 == null) ? (multi.getKey(4) == null) : key5.equals(multi.getKey(4))));
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
/*     */   public boolean removeAll(Object key1) {
/* 698 */     boolean modified = false;
/* 699 */     MapIterator it = mapIterator();
/* 700 */     while (it.hasNext()) {
/* 701 */       MultiKey multi = (MultiKey)it.next();
/* 702 */       if (multi.size() >= 1 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0)))) {
/*     */         
/* 704 */         it.remove();
/* 705 */         modified = true;
/*     */       } 
/*     */     } 
/* 708 */     return modified;
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
/*     */   public boolean removeAll(Object key1, Object key2) {
/* 722 */     boolean modified = false;
/* 723 */     MapIterator it = mapIterator();
/* 724 */     while (it.hasNext()) {
/* 725 */       MultiKey multi = (MultiKey)it.next();
/* 726 */       if (multi.size() >= 2 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1)))) {
/*     */ 
/*     */         
/* 729 */         it.remove();
/* 730 */         modified = true;
/*     */       } 
/*     */     } 
/* 733 */     return modified;
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
/*     */   public boolean removeAll(Object key1, Object key2, Object key3) {
/* 748 */     boolean modified = false;
/* 749 */     MapIterator it = mapIterator();
/* 750 */     while (it.hasNext()) {
/* 751 */       MultiKey multi = (MultiKey)it.next();
/* 752 */       if (multi.size() >= 3 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2)))) {
/*     */ 
/*     */ 
/*     */         
/* 756 */         it.remove();
/* 757 */         modified = true;
/*     */       } 
/*     */     } 
/* 760 */     return modified;
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
/*     */   public boolean removeAll(Object key1, Object key2, Object key3, Object key4) {
/* 776 */     boolean modified = false;
/* 777 */     MapIterator it = mapIterator();
/* 778 */     while (it.hasNext()) {
/* 779 */       MultiKey multi = (MultiKey)it.next();
/* 780 */       if (multi.size() >= 4 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3)))) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 785 */         it.remove();
/* 786 */         modified = true;
/*     */       } 
/*     */     } 
/* 789 */     return modified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkKey(Object key) {
/* 799 */     if (key == null) {
/* 800 */       throw new NullPointerException("Key must not be null");
/*     */     }
/* 802 */     if (!(key instanceof MultiKey)) {
/* 803 */       throw new ClassCastException("Key must be a MultiKey");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 813 */     return new MultiKeyMap((AbstractHashedMap)this.map.clone());
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
/*     */   public Object put(Object key, Object value) {
/* 827 */     checkKey(key);
/* 828 */     return this.map.put(key, value);
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
/*     */   public void putAll(Map mapToCopy) {
/* 842 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext(); ) {
/* 843 */       Object key = it.next();
/* 844 */       checkKey(key);
/*     */     } 
/* 846 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */ 
/*     */   
/*     */   public MapIterator mapIterator() {
/* 851 */     return this.map.mapIterator();
/*     */   }
/*     */   
/*     */   public int size() {
/* 855 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 859 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 863 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 867 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 871 */     return this.map.get(key);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 875 */     return this.map.remove(key);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 879 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 883 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 887 */     return this.map.values();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 891 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 895 */     if (obj == this) {
/* 896 */       return true;
/*     */     }
/* 898 */     return this.map.equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 902 */     return this.map.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 906 */     return this.map.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/MultiKeyMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */