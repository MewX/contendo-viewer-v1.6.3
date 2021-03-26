/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends SequencedHashMap
/*     */   implements Externalizable
/*     */ {
/*  54 */   private int maximumSize = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long serialVersionUID = 2197433140769957051L;
/*     */ 
/*     */ 
/*     */   
/*     */   public LRUMap() {
/*  63 */     this(100);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LRUMap(int i) {
/*  74 */     super(i);
/*  75 */     this.maximumSize = i;
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
/*     */   public Object get(Object key) {
/*  91 */     if (!containsKey(key)) return null;
/*     */     
/*  93 */     Object value = remove(key);
/*  94 */     super.put(key, value);
/*  95 */     return value;
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
/*     */   public Object put(Object key, Object value) {
/* 112 */     int mapSize = size();
/* 113 */     Object retval = null;
/*     */     
/* 115 */     if (mapSize >= this.maximumSize)
/*     */     {
/*     */ 
/*     */       
/* 119 */       if (!containsKey(key))
/*     */       {
/* 121 */         removeLRU();
/*     */       }
/*     */     }
/*     */     
/* 125 */     retval = super.put(key, value);
/*     */     
/* 127 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeLRU() {
/* 135 */     Object key = getFirstKey();
/*     */ 
/*     */     
/* 138 */     Object value = super.get(key);
/*     */     
/* 140 */     remove(key);
/*     */     
/* 142 */     processRemovedLRU(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processRemovedLRU(Object key, Object value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 160 */     this.maximumSize = in.readInt();
/* 161 */     int size = in.readInt();
/*     */     
/* 163 */     for (int i = 0; i < size; i++) {
/* 164 */       Object key = in.readObject();
/* 165 */       Object value = in.readObject();
/* 166 */       put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 171 */     out.writeInt(this.maximumSize);
/* 172 */     out.writeInt(size());
/* 173 */     for (Iterator iterator = keySet().iterator(); iterator.hasNext(); ) {
/* 174 */       Object key = iterator.next();
/* 175 */       out.writeObject(key);
/*     */ 
/*     */       
/* 178 */       Object value = super.get(key);
/* 179 */       out.writeObject(value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaximumSize() {
/* 190 */     return this.maximumSize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaximumSize(int maximumSize) {
/* 196 */     this.maximumSize = maximumSize;
/* 197 */     while (size() > maximumSize)
/* 198 */       removeLRU(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/LRUMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */