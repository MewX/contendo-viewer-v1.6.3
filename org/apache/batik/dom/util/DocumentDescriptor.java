/*     */ package org.apache.batik.dom.util;
/*     */ 
/*     */ import org.apache.batik.util.CleanerThread;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DocumentDescriptor
/*     */ {
/*     */   protected static final int INITIAL_CAPACITY = 101;
/*  52 */   protected Entry[] table = new Entry[101];
/*     */ 
/*     */   
/*     */   protected int count;
/*     */ 
/*     */   
/*     */   public int getNumberOfElements() {
/*  59 */     synchronized (this) {
/*  60 */       return this.count;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLocationLine(Element elt) {
/*  69 */     synchronized (this) {
/*  70 */       int hash = elt.hashCode() & Integer.MAX_VALUE;
/*  71 */       int index = hash % this.table.length;
/*     */       
/*  73 */       for (Entry e = this.table[index]; e != null; e = e.next) {
/*  74 */         if (e.hash == hash) {
/*     */           
/*  76 */           Object o = e.get();
/*  77 */           if (o == elt)
/*  78 */             return e.locationLine; 
/*     */         } 
/*     */       } 
/*  81 */     }  return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLocationColumn(Element elt) {
/*  89 */     synchronized (this) {
/*  90 */       int hash = elt.hashCode() & Integer.MAX_VALUE;
/*  91 */       int index = hash % this.table.length;
/*     */       
/*  93 */       for (Entry e = this.table[index]; e != null; e = e.next) {
/*  94 */         if (e.hash == hash) {
/*     */           
/*  96 */           Object o = e.get();
/*  97 */           if (o == elt)
/*  98 */             return e.locationColumn; 
/*     */         } 
/*     */       } 
/* 101 */     }  return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(Element elt, int line, int col) {
/* 108 */     synchronized (this) {
/* 109 */       int hash = elt.hashCode() & Integer.MAX_VALUE;
/* 110 */       int index = hash % this.table.length;
/*     */       
/* 112 */       for (Entry e = this.table[index]; e != null; e = e.next) {
/* 113 */         if (e.hash == hash) {
/*     */           
/* 115 */           Object o = e.get();
/* 116 */           if (o == elt) {
/* 117 */             e.locationLine = line;
/*     */           }
/*     */         } 
/*     */       } 
/* 121 */       int len = this.table.length;
/* 122 */       if (this.count++ >= len - (len >> 2)) {
/*     */         
/* 124 */         rehash();
/* 125 */         index = hash % this.table.length;
/*     */       } 
/*     */       
/* 128 */       Entry entry1 = new Entry(hash, elt, line, col, this.table[index]);
/* 129 */       this.table[index] = entry1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rehash() {
/* 137 */     Entry[] oldTable = this.table;
/*     */     
/* 139 */     this.table = new Entry[oldTable.length * 2 + 1];
/*     */     
/* 141 */     for (int i = oldTable.length - 1; i >= 0; i--) {
/* 142 */       for (Entry old = oldTable[i]; old != null; ) {
/* 143 */         Entry e = old;
/* 144 */         old = old.next;
/*     */         
/* 146 */         int index = e.hash % this.table.length;
/* 147 */         e.next = this.table[index];
/* 148 */         this.table[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void removeEntry(Entry e) {
/* 154 */     synchronized (this) {
/* 155 */       int hash = e.hash;
/* 156 */       int index = hash % this.table.length;
/* 157 */       Entry curr = this.table[index];
/* 158 */       Entry prev = null;
/* 159 */       while (curr != e) {
/* 160 */         prev = curr;
/* 161 */         curr = curr.next;
/*     */       } 
/* 163 */       if (curr == null)
/*     */         return; 
/* 165 */       if (prev == null) {
/*     */         
/* 167 */         this.table[index] = curr.next;
/*     */       } else {
/* 169 */         prev.next = curr.next;
/* 170 */       }  this.count--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class Entry
/*     */     extends CleanerThread.WeakReferenceCleared
/*     */   {
/*     */     public int hash;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int locationLine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int locationColumn;
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
/*     */     public Entry(int hash, Element element, int locationLine, int locationColumn, Entry next) {
/* 206 */       super(element);
/* 207 */       this.hash = hash;
/* 208 */       this.locationLine = locationLine;
/* 209 */       this.locationColumn = locationColumn;
/* 210 */       this.next = next;
/*     */     }
/*     */     
/*     */     public void cleared() {
/* 214 */       DocumentDescriptor.this.removeEntry(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/DocumentDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */