/*     */ package org.apache.batik.dom.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TriplyIndexedTable
/*     */ {
/*     */   protected static final int INITIAL_CAPACITY = 11;
/*     */   protected Entry[] table;
/*     */   protected int count;
/*     */   
/*     */   public TriplyIndexedTable() {
/*  49 */     this.table = new Entry[11];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TriplyIndexedTable(int c) {
/*  57 */     this.table = new Entry[c];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  64 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object put(Object o1, Object o2, Object o3, Object value) {
/*  72 */     int hash = hashCode(o1, o2, o3) & Integer.MAX_VALUE;
/*  73 */     int index = hash % this.table.length;
/*     */     
/*  75 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/*  76 */       if (e.hash == hash && e.match(o1, o2, o3)) {
/*  77 */         Object old = e.value;
/*  78 */         e.value = value;
/*  79 */         return old;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  84 */     int len = this.table.length;
/*  85 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/*  87 */       rehash();
/*  88 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/*  91 */     Entry entry1 = new Entry(hash, o1, o2, o3, value, this.table[index]);
/*  92 */     this.table[index] = entry1;
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object o1, Object o2, Object o3) {
/* 101 */     int hash = hashCode(o1, o2, o3) & Integer.MAX_VALUE;
/* 102 */     int index = hash % this.table.length;
/*     */     
/* 104 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 105 */       if (e.hash == hash && e.match(o1, o2, o3)) {
/* 106 */         return e.value;
/*     */       }
/*     */     } 
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rehash() {
/* 116 */     Entry[] oldTable = this.table;
/*     */     
/* 118 */     this.table = new Entry[oldTable.length * 2 + 1];
/*     */     
/* 120 */     for (int i = oldTable.length - 1; i >= 0; i--) {
/* 121 */       for (Entry old = oldTable[i]; old != null; ) {
/* 122 */         Entry e = old;
/* 123 */         old = old.next;
/*     */         
/* 125 */         int index = e.hash % this.table.length;
/* 126 */         e.next = this.table[index];
/* 127 */         this.table[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int hashCode(Object o1, Object o2, Object o3) {
/* 136 */     return ((o1 == null) ? 0 : o1.hashCode()) ^ ((o2 == null) ? 0 : o2.hashCode()) ^ ((o3 == null) ? 0 : o3.hashCode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class Entry
/*     */   {
/*     */     public int hash;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object key1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object key2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object key3;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object value;
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
/*     */     public Entry(int hash, Object key1, Object key2, Object key3, Object value, Entry next) {
/* 180 */       this.hash = hash;
/* 181 */       this.key1 = key1;
/* 182 */       this.key2 = key2;
/* 183 */       this.key3 = key3;
/* 184 */       this.value = value;
/* 185 */       this.next = next;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean match(Object o1, Object o2, Object o3) {
/* 192 */       if (this.key1 != null) {
/* 193 */         if (!this.key1.equals(o1)) {
/* 194 */           return false;
/*     */         }
/* 196 */       } else if (o1 != null) {
/* 197 */         return false;
/*     */       } 
/* 199 */       if (this.key2 != null) {
/* 200 */         if (!this.key2.equals(o2)) {
/* 201 */           return false;
/*     */         }
/* 203 */       } else if (o2 != null) {
/* 204 */         return false;
/*     */       } 
/* 206 */       if (this.key3 != null) {
/* 207 */         return this.key3.equals(o3);
/*     */       }
/* 209 */       return (o3 == null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/TriplyIndexedTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */