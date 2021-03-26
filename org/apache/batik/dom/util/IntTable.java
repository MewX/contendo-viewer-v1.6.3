/*     */ package org.apache.batik.dom.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntTable
/*     */   implements Serializable
/*     */ {
/*     */   protected static final int INITIAL_CAPACITY = 11;
/*     */   protected Entry[] table;
/*     */   protected int count;
/*     */   
/*     */   public IntTable() {
/*  52 */     this.table = new Entry[11];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntTable(int c) {
/*  60 */     this.table = new Entry[c];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntTable(IntTable t) {
/*  68 */     this.count = t.count;
/*  69 */     this.table = new Entry[t.table.length];
/*  70 */     for (int i = 0; i < this.table.length; i++) {
/*  71 */       Entry e = t.table[i];
/*  72 */       Entry n = null;
/*  73 */       if (e != null) {
/*  74 */         n = new Entry(e.hash, e.key, e.value, null);
/*  75 */         this.table[i] = n;
/*  76 */         e = e.next;
/*  77 */         while (e != null) {
/*  78 */           n.next = new Entry(e.hash, e.key, e.value, null);
/*  79 */           n = n.next;
/*  80 */           e = e.next;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  90 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Entry find(Object key) {
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int get(Object key) {
/* 104 */     int hash = (key == null) ? 0 : (key.hashCode() & Integer.MAX_VALUE);
/* 105 */     int index = hash % this.table.length;
/*     */     
/* 107 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 108 */       if (e.hash == hash && ((e.key == null && key == null) || (e.key != null && e.key.equals(key))))
/*     */       {
/*     */         
/* 111 */         return e.value;
/*     */       }
/*     */     } 
/*     */     
/* 115 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int put(Object key, int value) {
/* 122 */     int hash = (key == null) ? 0 : (key.hashCode() & Integer.MAX_VALUE);
/* 123 */     int index = hash % this.table.length;
/*     */     
/* 125 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 126 */       if (e.hash == hash && ((e.key == null && key == null) || (e.key != null && e.key.equals(key)))) {
/*     */ 
/*     */         
/* 129 */         int old = e.value;
/* 130 */         e.value = value;
/* 131 */         return old;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 136 */     int len = this.table.length;
/* 137 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/* 139 */       rehash();
/* 140 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/* 143 */     this.table[index] = new Entry(hash, key, value, this.table[index]);
/* 144 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int inc(Object key) {
/* 151 */     int hash = (key == null) ? 0 : (key.hashCode() & Integer.MAX_VALUE);
/* 152 */     int index = hash % this.table.length;
/*     */     
/* 154 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 155 */       if (e.hash == hash && ((e.key == null && key == null) || (e.key != null && e.key.equals(key))))
/*     */       {
/*     */         
/* 158 */         return e.value++;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 163 */     int len = this.table.length;
/* 164 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/* 166 */       rehash();
/* 167 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/* 170 */     this.table[index] = new Entry(hash, key, 1, this.table[index]);
/* 171 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dec(Object key) {
/* 178 */     int hash = (key == null) ? 0 : (key.hashCode() & Integer.MAX_VALUE);
/* 179 */     int index = hash % this.table.length;
/*     */     
/* 181 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 182 */       if (e.hash == hash && ((e.key == null && key == null) || (e.key != null && e.key.equals(key))))
/*     */       {
/*     */         
/* 185 */         return e.value--;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 190 */     int len = this.table.length;
/* 191 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/* 193 */       rehash();
/* 194 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/* 197 */     this.table[index] = new Entry(hash, key, -1, this.table[index]);
/* 198 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int remove(Object key) {
/* 205 */     int hash = (key == null) ? 0 : (key.hashCode() & Integer.MAX_VALUE);
/* 206 */     int index = hash % this.table.length;
/*     */     
/* 208 */     Entry p = null;
/* 209 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 210 */       if (e.hash == hash && ((e.key == null && key == null) || (e.key != null && e.key.equals(key)))) {
/*     */ 
/*     */         
/* 213 */         int result = e.value;
/* 214 */         if (p == null) {
/* 215 */           this.table[index] = e.next;
/*     */         } else {
/* 217 */           p.next = e.next;
/*     */         } 
/* 219 */         this.count--;
/* 220 */         return result;
/*     */       } 
/* 222 */       p = e;
/*     */     } 
/* 224 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 231 */     for (int i = 0; i < this.table.length; i++) {
/* 232 */       this.table[i] = null;
/*     */     }
/* 234 */     this.count = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rehash() {
/* 241 */     Entry[] oldTable = this.table;
/*     */     
/* 243 */     this.table = new Entry[oldTable.length * 2 + 1];
/*     */     
/* 245 */     for (int i = oldTable.length - 1; i >= 0; i--) {
/* 246 */       for (Entry old = oldTable[i]; old != null; ) {
/* 247 */         Entry e = old;
/* 248 */         old = old.next;
/*     */         
/* 250 */         int index = e.hash % this.table.length;
/* 251 */         e.next = this.table[index];
/* 252 */         this.table[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class Entry
/*     */     implements Serializable
/*     */   {
/*     */     public int hash;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object key;
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
/*     */     public Entry(int hash, Object key, int value, Entry next) {
/* 286 */       this.hash = hash;
/* 287 */       this.key = key;
/* 288 */       this.value = value;
/* 289 */       this.next = next;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/IntTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */