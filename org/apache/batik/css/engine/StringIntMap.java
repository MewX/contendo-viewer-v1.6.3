/*     */ package org.apache.batik.css.engine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringIntMap
/*     */ {
/*     */   protected Entry[] table;
/*     */   protected int count;
/*     */   
/*     */   public StringIntMap(int c) {
/*  46 */     this.table = new Entry[c - (c >> 2) + 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int get(String key) {
/*  54 */     int hash = key.hashCode() & Integer.MAX_VALUE;
/*  55 */     int index = hash % this.table.length;
/*     */     
/*  57 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/*  58 */       if (e.hash == hash && e.key.equals(key)) {
/*  59 */         return e.value;
/*     */       }
/*     */     } 
/*  62 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(String key, int value) {
/*  69 */     int hash = key.hashCode() & Integer.MAX_VALUE;
/*  70 */     int index = hash % this.table.length;
/*     */     
/*  72 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/*  73 */       if (e.hash == hash && e.key.equals(key)) {
/*  74 */         e.value = value;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/*  80 */     int len = this.table.length;
/*  81 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/*  83 */       rehash();
/*  84 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/*  87 */     Entry entry1 = new Entry(hash, key, value, this.table[index]);
/*  88 */     this.table[index] = entry1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rehash() {
/*  95 */     Entry[] oldTable = this.table;
/*     */     
/*  97 */     this.table = new Entry[oldTable.length * 2 + 1];
/*     */     
/*  99 */     for (int i = oldTable.length - 1; i >= 0; i--) {
/* 100 */       for (Entry old = oldTable[i]; old != null; ) {
/* 101 */         Entry e = old;
/* 102 */         old = old.next;
/*     */         
/* 104 */         int index = e.hash % this.table.length;
/* 105 */         e.next = this.table[index];
/* 106 */         this.table[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class Entry
/*     */   {
/*     */     public final int hash;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String key;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int value;
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
/*     */     public Entry(int hash, String key, int value, Entry next) {
/* 140 */       this.hash = hash;
/* 141 */       this.key = key;
/* 142 */       this.value = value;
/* 143 */       this.next = next;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/StringIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */