/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DoublyIndexedTable
/*     */ {
/*     */   protected int initialCapacity;
/*     */   protected Entry[] table;
/*     */   protected int count;
/*     */   
/*     */   public DoublyIndexedTable() {
/*  51 */     this(16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoublyIndexedTable(int c) {
/*  59 */     this.initialCapacity = c;
/*  60 */     this.table = new Entry[c];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoublyIndexedTable(DoublyIndexedTable other) {
/*  68 */     this.initialCapacity = other.initialCapacity;
/*  69 */     this.table = new Entry[other.table.length];
/*  70 */     for (int i = 0; i < other.table.length; i++) {
/*  71 */       Entry newE = null;
/*  72 */       Entry e = other.table[i];
/*  73 */       while (e != null) {
/*  74 */         newE = new Entry(e.hash, e.key1, e.key2, e.value, newE);
/*  75 */         e = e.next;
/*     */       } 
/*  77 */       this.table[i] = newE;
/*     */     } 
/*  79 */     this.count = other.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  86 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object put(Object o1, Object o2, Object value) {
/*  94 */     int hash = hashCode(o1, o2) & Integer.MAX_VALUE;
/*  95 */     int index = hash % this.table.length;
/*     */     
/*  97 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/*  98 */       if (e.hash == hash && e.match(o1, o2)) {
/*  99 */         Object old = e.value;
/* 100 */         e.value = value;
/* 101 */         return old;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 106 */     int len = this.table.length;
/* 107 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/* 109 */       rehash();
/* 110 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/* 113 */     Entry entry1 = new Entry(hash, o1, o2, value, this.table[index]);
/* 114 */     this.table[index] = entry1;
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object o1, Object o2) {
/* 123 */     int hash = hashCode(o1, o2) & Integer.MAX_VALUE;
/* 124 */     int index = hash % this.table.length;
/*     */     
/* 126 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 127 */       if (e.hash == hash && e.match(o1, o2)) {
/* 128 */         return e.value;
/*     */       }
/*     */     } 
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object o1, Object o2) {
/* 139 */     int hash = hashCode(o1, o2) & Integer.MAX_VALUE;
/* 140 */     int index = hash % this.table.length;
/*     */     
/* 142 */     Entry e = this.table[index];
/* 143 */     if (e == null) {
/* 144 */       return null;
/*     */     }
/*     */     
/* 147 */     if (e.hash == hash && e.match(o1, o2)) {
/* 148 */       this.table[index] = e.next;
/* 149 */       this.count--;
/* 150 */       return e.value;
/*     */     } 
/*     */     
/* 153 */     Entry prev = e;
/* 154 */     for (e = e.next; e != null; prev = e, e = e.next) {
/* 155 */       if (e.hash == hash && e.match(o1, o2)) {
/* 156 */         prev.next = e.next;
/* 157 */         this.count--;
/* 158 */         return e.value;
/*     */       } 
/*     */     } 
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] getValuesArray() {
/* 168 */     Object[] values = new Object[this.count];
/* 169 */     int i = 0;
/*     */     
/* 171 */     for (Entry aTable : this.table) {
/* 172 */       for (Entry e = aTable; e != null; e = e.next) {
/* 173 */         values[i++] = e.value;
/*     */       }
/*     */     } 
/* 176 */     return values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 183 */     this.table = new Entry[this.initialCapacity];
/* 184 */     this.count = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 191 */     return new TableIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rehash() {
/* 198 */     Entry[] oldTable = this.table;
/*     */     
/* 200 */     this.table = new Entry[oldTable.length * 2 + 1];
/*     */     
/* 202 */     for (int i = oldTable.length - 1; i >= 0; i--) {
/* 203 */       for (Entry old = oldTable[i]; old != null; ) {
/* 204 */         Entry e = old;
/* 205 */         old = old.next;
/*     */         
/* 207 */         int index = e.hash % this.table.length;
/* 208 */         e.next = this.table[index];
/* 209 */         this.table[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int hashCode(Object o1, Object o2) {
/* 218 */     int result = (o1 == null) ? 0 : o1.hashCode();
/* 219 */     return result ^ ((o2 == null) ? 0 : o2.hashCode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Entry
/*     */   {
/*     */     protected int hash;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object key1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object key2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Entry next;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Entry(int hash, Object key1, Object key2, Object value, Entry next) {
/* 257 */       this.hash = hash;
/* 258 */       this.key1 = key1;
/* 259 */       this.key2 = key2;
/* 260 */       this.value = value;
/* 261 */       this.next = next;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getKey1() {
/* 268 */       return this.key1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getKey2() {
/* 275 */       return this.key2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getValue() {
/* 282 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean match(Object o1, Object o2) {
/* 289 */       if (this.key1 != null) {
/* 290 */         if (!this.key1.equals(o1)) {
/* 291 */           return false;
/*     */         }
/* 293 */       } else if (o1 != null) {
/* 294 */         return false;
/*     */       } 
/* 296 */       if (this.key2 != null) {
/* 297 */         return this.key2.equals(o2);
/*     */       }
/* 299 */       return (o2 == null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class TableIterator
/*     */     implements Iterator
/*     */   {
/*     */     private int nextIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private DoublyIndexedTable.Entry nextEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean finished;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public TableIterator() {
/* 327 */       while (this.nextIndex < DoublyIndexedTable.this.table.length) {
/* 328 */         this.nextEntry = DoublyIndexedTable.this.table[this.nextIndex];
/* 329 */         if (this.nextEntry != null) {
/*     */           break;
/*     */         }
/* 332 */         this.nextIndex++;
/*     */       } 
/* 334 */       this.finished = (this.nextEntry == null);
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 338 */       return !this.finished;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 342 */       if (this.finished) {
/* 343 */         throw new NoSuchElementException();
/*     */       }
/* 345 */       DoublyIndexedTable.Entry ret = this.nextEntry;
/* 346 */       findNext();
/* 347 */       return ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void findNext() {
/* 354 */       this.nextEntry = this.nextEntry.next;
/* 355 */       if (this.nextEntry == null) {
/* 356 */         this.nextIndex++;
/* 357 */         while (this.nextIndex < DoublyIndexedTable.this.table.length) {
/* 358 */           this.nextEntry = DoublyIndexedTable.this.table[this.nextIndex];
/* 359 */           if (this.nextEntry != null) {
/*     */             break;
/*     */           }
/* 362 */           this.nextIndex++;
/*     */         } 
/*     */       } 
/* 365 */       this.finished = (this.nextEntry == null);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 369 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/DoublyIndexedTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */