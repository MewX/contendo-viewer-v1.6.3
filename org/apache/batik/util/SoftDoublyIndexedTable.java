/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SoftDoublyIndexedTable
/*     */ {
/*     */   protected static final int INITIAL_CAPACITY = 11;
/*     */   protected Entry[] table;
/*     */   protected int count;
/*  52 */   protected ReferenceQueue referenceQueue = new ReferenceQueue();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SoftDoublyIndexedTable() {
/*  58 */     this.table = new Entry[11];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SoftDoublyIndexedTable(int c) {
/*  66 */     this.table = new Entry[c];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  73 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object o1, Object o2) {
/*  81 */     int hash = hashCode(o1, o2) & Integer.MAX_VALUE;
/*  82 */     int index = hash % this.table.length;
/*     */     
/*  84 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/*  85 */       if (e.hash == hash && e.match(o1, o2)) {
/*  86 */         return e.get();
/*     */       }
/*     */     } 
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object put(Object o1, Object o2, Object value) {
/*  97 */     removeClearedEntries();
/*     */     
/*  99 */     int hash = hashCode(o1, o2) & Integer.MAX_VALUE;
/* 100 */     int index = hash % this.table.length;
/*     */     
/* 102 */     Entry e = this.table[index];
/* 103 */     if (e != null) {
/* 104 */       if (e.hash == hash && e.match(o1, o2)) {
/* 105 */         Object old = e.get();
/* 106 */         this.table[index] = new Entry(hash, o1, o2, value, e.next);
/* 107 */         return old;
/*     */       } 
/* 109 */       Entry o = e;
/* 110 */       e = e.next;
/* 111 */       while (e != null) {
/* 112 */         if (e.hash == hash && e.match(o1, o2)) {
/* 113 */           Object old = e.get();
/* 114 */           e = new Entry(hash, o1, o2, value, e.next);
/* 115 */           o.next = e;
/* 116 */           return old;
/*     */         } 
/*     */         
/* 119 */         o = e;
/* 120 */         e = e.next;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 125 */     int len = this.table.length;
/* 126 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/* 128 */       rehash();
/* 129 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/* 132 */     this.table[index] = new Entry(hash, o1, o2, value, this.table[index]);
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 140 */     this.table = new Entry[11];
/* 141 */     this.count = 0;
/* 142 */     this.referenceQueue = new ReferenceQueue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rehash() {
/* 149 */     Entry[] oldTable = this.table;
/*     */     
/* 151 */     this.table = new Entry[oldTable.length * 2 + 1];
/*     */     
/* 153 */     for (int i = oldTable.length - 1; i >= 0; i--) {
/* 154 */       for (Entry old = oldTable[i]; old != null; ) {
/* 155 */         Entry e = old;
/* 156 */         old = old.next;
/*     */         
/* 158 */         int index = e.hash % this.table.length;
/* 159 */         e.next = this.table[index];
/* 160 */         this.table[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int hashCode(Object o1, Object o2) {
/* 169 */     int result = (o1 == null) ? 0 : o1.hashCode();
/* 170 */     return result ^ ((o2 == null) ? 0 : o2.hashCode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeClearedEntries() {
/*     */     Entry e;
/* 178 */     while ((e = (Entry)this.referenceQueue.poll()) != null) {
/* 179 */       int index = e.hash % this.table.length;
/* 180 */       Entry t = this.table[index];
/* 181 */       if (t == e) {
/* 182 */         this.table[index] = e.next;
/*     */       } else {
/* 184 */         while (t != null) {
/* 185 */           Entry c = t.next;
/* 186 */           if (c == e) {
/* 187 */             t.next = e.next;
/*     */             break;
/*     */           } 
/* 190 */           t = c;
/*     */         } 
/*     */       } 
/* 193 */       this.count--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class Entry
/*     */     extends SoftReference
/*     */   {
/*     */     public int hash;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object key1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object key2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Entry next;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Entry(int hash, Object key1, Object key2, Object value, Entry next) {
/* 226 */       super((T)value, SoftDoublyIndexedTable.this.referenceQueue);
/* 227 */       this.hash = hash;
/* 228 */       this.key1 = key1;
/* 229 */       this.key2 = key2;
/* 230 */       this.next = next;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean match(Object o1, Object o2) {
/* 237 */       if (this.key1 != null) {
/* 238 */         if (!this.key1.equals(o1)) {
/* 239 */           return false;
/*     */         }
/* 241 */       } else if (o1 != null) {
/* 242 */         return false;
/*     */       } 
/* 244 */       if (this.key2 != null) {
/* 245 */         return this.key2.equals(o2);
/*     */       }
/* 247 */       return (o2 == null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/SoftDoublyIndexedTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */