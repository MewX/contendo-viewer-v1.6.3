/*     */ package org.apache.batik.css.engine.value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringMap
/*     */ {
/*     */   protected static final int INITIAL_CAPACITY = 11;
/*     */   protected Entry[] table;
/*     */   protected int count;
/*     */   
/*     */   public StringMap() {
/*  49 */     this.table = new Entry[11];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap(StringMap t) {
/*  57 */     this.count = t.count;
/*  58 */     this.table = new Entry[t.table.length];
/*  59 */     for (int i = 0; i < this.table.length; i++) {
/*  60 */       Entry e = t.table[i];
/*  61 */       Entry n = null;
/*  62 */       if (e != null) {
/*  63 */         n = new Entry(e.hash, e.key, e.value, null);
/*  64 */         this.table[i] = n;
/*  65 */         e = e.next;
/*  66 */         while (e != null) {
/*  67 */           n.next = new Entry(e.hash, e.key, e.value, null);
/*  68 */           n = n.next;
/*  69 */           e = e.next;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(String key) {
/*  80 */     int hash = key.hashCode() & Integer.MAX_VALUE;
/*  81 */     int index = hash % this.table.length;
/*     */     
/*  83 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/*  84 */       if (e.hash == hash && e.key == key) {
/*  85 */         return e.value;
/*     */       }
/*     */     } 
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object put(String key, Object value) {
/*  96 */     int hash = key.hashCode() & Integer.MAX_VALUE;
/*  97 */     int index = hash % this.table.length;
/*     */     
/*  99 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 100 */       if (e.hash == hash && e.key == key) {
/* 101 */         Object old = e.value;
/* 102 */         e.value = value;
/* 103 */         return old;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 108 */     int len = this.table.length;
/* 109 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/* 111 */       rehash();
/* 112 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/* 115 */     Entry entry1 = new Entry(hash, key, value, this.table[index]);
/* 116 */     this.table[index] = entry1;
/* 117 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rehash() {
/* 124 */     Entry[] oldTable = this.table;
/*     */     
/* 126 */     this.table = new Entry[oldTable.length * 2 + 1];
/*     */     
/* 128 */     for (int i = oldTable.length - 1; i >= 0; i--) {
/* 129 */       for (Entry old = oldTable[i]; old != null; ) {
/* 130 */         Entry e = old;
/* 131 */         old = old.next;
/*     */         
/* 133 */         int index = e.hash % this.table.length;
/* 134 */         e.next = this.table[index];
/* 135 */         this.table[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
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
/*     */     public String key;
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
/*     */     public Entry(int hash, String key, Object value, Entry next) {
/* 168 */       this.hash = hash;
/* 169 */       this.key = key;
/* 170 */       this.value = value;
/* 171 */       this.next = next;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/StringMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */