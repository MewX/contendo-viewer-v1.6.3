/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.BoundedMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LRUMap
/*     */   extends AbstractLinkedMap
/*     */   implements Serializable, Cloneable, BoundedMap
/*     */ {
/*     */   static final long serialVersionUID = -612114643488955218L;
/*     */   protected static final int DEFAULT_MAX_SIZE = 100;
/*     */   private transient int maxSize;
/*     */   private boolean scanUntilRemovable;
/*     */   
/*     */   public LRUMap() {
/*  69 */     this(100, 0.75F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LRUMap(int maxSize) {
/*  79 */     this(maxSize, 0.75F);
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
/*     */   public LRUMap(int maxSize, boolean scanUntilRemovable) {
/*  91 */     this(maxSize, 0.75F, scanUntilRemovable);
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
/*     */   public LRUMap(int maxSize, float loadFactor) {
/* 104 */     this(maxSize, loadFactor, false);
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
/*     */   public LRUMap(int maxSize, float loadFactor, boolean scanUntilRemovable) {
/* 119 */     super((maxSize < 1) ? 16 : maxSize, loadFactor);
/* 120 */     if (maxSize < 1) {
/* 121 */       throw new IllegalArgumentException("LRUMap max size must be greater than 0");
/*     */     }
/* 123 */     this.maxSize = maxSize;
/* 124 */     this.scanUntilRemovable = scanUntilRemovable;
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
/*     */   public LRUMap(Map map) {
/* 137 */     this(map, false);
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
/*     */   public LRUMap(Map map, boolean scanUntilRemovable) {
/* 152 */     this(map.size(), 0.75F, scanUntilRemovable);
/* 153 */     putAll(map);
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
/*     */   public Object get(Object key) {
/* 167 */     AbstractLinkedMap.LinkEntry entry = (AbstractLinkedMap.LinkEntry)getEntry(key);
/* 168 */     if (entry == null) {
/* 169 */       return null;
/*     */     }
/* 171 */     moveToMRU(entry);
/* 172 */     return entry.getValue();
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
/*     */   protected void moveToMRU(AbstractLinkedMap.LinkEntry entry) {
/* 184 */     if (entry.after != this.header) {
/* 185 */       this.modCount++;
/*     */       
/* 187 */       entry.before.after = entry.after;
/* 188 */       entry.after.before = entry.before;
/*     */       
/* 190 */       entry.after = this.header;
/* 191 */       entry.before = this.header.before;
/* 192 */       this.header.before.after = entry;
/* 193 */       this.header.before = entry;
/*     */     } 
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
/*     */   protected void updateEntry(AbstractHashedMap.HashEntry entry, Object newValue) {
/* 207 */     moveToMRU((AbstractLinkedMap.LinkEntry)entry);
/* 208 */     entry.setValue(newValue);
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
/*     */   protected void addMapping(int hashIndex, int hashCode, Object key, Object value) {
/* 227 */     if (isFull()) {
/* 228 */       AbstractLinkedMap.LinkEntry reuse = this.header.after;
/* 229 */       boolean removeLRUEntry = false;
/* 230 */       if (this.scanUntilRemovable) {
/* 231 */         while (reuse != this.header) {
/* 232 */           if (removeLRU(reuse)) {
/* 233 */             removeLRUEntry = true;
/*     */             break;
/*     */           } 
/* 236 */           reuse = reuse.after;
/*     */         } 
/*     */       } else {
/* 239 */         removeLRUEntry = removeLRU(reuse);
/*     */       } 
/*     */       
/* 242 */       if (removeLRUEntry) {
/* 243 */         reuseMapping(reuse, hashIndex, hashCode, key, value);
/*     */       } else {
/* 245 */         super.addMapping(hashIndex, hashCode, key, value);
/*     */       } 
/*     */     } else {
/* 248 */       super.addMapping(hashIndex, hashCode, key, value);
/*     */     } 
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
/*     */   protected void reuseMapping(AbstractLinkedMap.LinkEntry entry, int hashIndex, int hashCode, Object key, Object value) {
/* 267 */     int removeIndex = hashIndex(entry.hashCode, this.data.length);
/* 268 */     AbstractHashedMap.HashEntry loop = this.data[removeIndex];
/* 269 */     AbstractHashedMap.HashEntry previous = null;
/* 270 */     while (loop != entry) {
/* 271 */       previous = loop;
/* 272 */       loop = loop.next;
/*     */     } 
/*     */ 
/*     */     
/* 276 */     this.modCount++;
/* 277 */     removeEntry(entry, removeIndex, previous);
/* 278 */     reuseEntry(entry, hashIndex, hashCode, key, value);
/* 279 */     addEntry(entry, hashIndex);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean removeLRU(AbstractLinkedMap.LinkEntry entry) {
/* 316 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull() {
/* 326 */     return (this.size >= this.maxSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maxSize() {
/* 335 */     return this.maxSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isScanUntilRemovable() {
/* 346 */     return this.scanUntilRemovable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 356 */     return super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 363 */     out.defaultWriteObject();
/* 364 */     doWriteObject(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 371 */     in.defaultReadObject();
/* 372 */     doReadObject(in);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doWriteObject(ObjectOutputStream out) throws IOException {
/* 379 */     out.writeInt(this.maxSize);
/* 380 */     super.doWriteObject(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 387 */     this.maxSize = in.readInt();
/* 388 */     super.doReadObject(in);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/LRUMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */