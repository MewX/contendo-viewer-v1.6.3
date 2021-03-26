/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
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
/*     */ public class AWTGlyphGeometryCache
/*     */ {
/*     */   protected static final int INITIAL_CAPACITY = 71;
/*     */   protected Entry[] table;
/*     */   protected int count;
/*  54 */   protected ReferenceQueue referenceQueue = new ReferenceQueue();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AWTGlyphGeometryCache() {
/*  60 */     this.table = new Entry[71];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AWTGlyphGeometryCache(int c) {
/*  68 */     this.table = new Entry[c];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  75 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value get(char c) {
/*  83 */     int hash = hashCode(c) & Integer.MAX_VALUE;
/*  84 */     int index = hash % this.table.length;
/*     */     
/*  86 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/*  87 */       if (e.hash == hash && e.match(c)) {
/*  88 */         return (Value)e.get();
/*     */       }
/*     */     } 
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value put(char c, Value value) {
/*  99 */     removeClearedEntries();
/*     */     
/* 101 */     int hash = hashCode(c) & Integer.MAX_VALUE;
/* 102 */     int index = hash % this.table.length;
/*     */     
/* 104 */     Entry e = this.table[index];
/* 105 */     if (e != null) {
/* 106 */       if (e.hash == hash && e.match(c)) {
/* 107 */         Object old = e.get();
/* 108 */         this.table[index] = new Entry(hash, c, value, e.next);
/* 109 */         return (Value)old;
/*     */       } 
/* 111 */       Entry o = e;
/* 112 */       e = e.next;
/* 113 */       while (e != null) {
/* 114 */         if (e.hash == hash && e.match(c)) {
/* 115 */           Object old = e.get();
/* 116 */           e = new Entry(hash, c, value, e.next);
/* 117 */           o.next = e;
/* 118 */           return (Value)old;
/*     */         } 
/*     */         
/* 121 */         o = e;
/* 122 */         e = e.next;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 127 */     int len = this.table.length;
/* 128 */     if (this.count++ >= len - (len >> 2)) {
/*     */       
/* 130 */       rehash();
/* 131 */       index = hash % this.table.length;
/*     */     } 
/*     */     
/* 134 */     this.table[index] = new Entry(hash, c, value, this.table[index]);
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 142 */     this.table = new Entry[71];
/* 143 */     this.count = 0;
/* 144 */     this.referenceQueue = new ReferenceQueue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rehash() {
/* 151 */     Entry[] oldTable = this.table;
/*     */     
/* 153 */     this.table = new Entry[oldTable.length * 2 + 1];
/*     */     
/* 155 */     for (int i = oldTable.length - 1; i >= 0; i--) {
/* 156 */       for (Entry old = oldTable[i]; old != null; ) {
/* 157 */         Entry e = old;
/* 158 */         old = old.next;
/*     */         
/* 160 */         int index = e.hash % this.table.length;
/* 161 */         e.next = this.table[index];
/* 162 */         this.table[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int hashCode(char c) {
/* 171 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeClearedEntries() {
/*     */     Entry e;
/* 179 */     while ((e = (Entry)this.referenceQueue.poll()) != null) {
/* 180 */       int index = e.hash % this.table.length;
/* 181 */       Entry t = this.table[index];
/* 182 */       if (t == e) {
/* 183 */         this.table[index] = e.next;
/*     */       } else {
/* 185 */         while (t != null) {
/* 186 */           Entry c = t.next;
/* 187 */           if (c == e) {
/* 188 */             t.next = e.next;
/*     */             break;
/*     */           } 
/* 191 */           t = c;
/*     */         } 
/*     */       } 
/* 194 */       this.count--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Value
/*     */   {
/*     */     protected Shape outline;
/*     */ 
/*     */     
/*     */     protected Rectangle2D gmB;
/*     */     
/*     */     protected Rectangle2D outlineBounds;
/*     */ 
/*     */     
/*     */     public Value(Shape outline, Rectangle2D gmB) {
/* 211 */       this.outline = outline;
/* 212 */       this.outlineBounds = outline.getBounds2D();
/* 213 */       this.gmB = gmB;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Shape getOutline() {
/* 220 */       return this.outline;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Rectangle2D getBounds2D() {
/* 227 */       return this.gmB;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Rectangle2D getOutlineBounds2D() {
/* 234 */       return this.outlineBounds;
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
/*     */     public char c;
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
/*     */     public Entry(int hash, char c, AWTGlyphGeometryCache.Value value, Entry next) {
/* 262 */       super((T)value, AWTGlyphGeometryCache.this.referenceQueue);
/* 263 */       this.hash = hash;
/* 264 */       this.c = c;
/* 265 */       this.next = next;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean match(char o2) {
/* 272 */       return (this.c == o2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/AWTGlyphGeometryCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */