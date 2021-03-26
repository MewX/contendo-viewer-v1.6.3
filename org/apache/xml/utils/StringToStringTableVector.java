/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringToStringTableVector
/*     */ {
/*     */   private int m_blocksize;
/*     */   private StringToStringTable[] m_map;
/*  36 */   private int m_firstFree = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int m_mapSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringToStringTableVector() {
/*  48 */     this.m_blocksize = 8;
/*  49 */     this.m_mapSize = this.m_blocksize;
/*  50 */     this.m_map = new StringToStringTable[this.m_blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringToStringTableVector(int blocksize) {
/*  61 */     this.m_blocksize = blocksize;
/*  62 */     this.m_mapSize = blocksize;
/*  63 */     this.m_map = new StringToStringTable[blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getLength() {
/*  73 */     return this.m_firstFree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int size() {
/*  83 */     return this.m_firstFree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void addElement(StringToStringTable value) {
/*  94 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/*  96 */       this.m_mapSize += this.m_blocksize;
/*     */       
/*  98 */       StringToStringTable[] newMap = new StringToStringTable[this.m_mapSize];
/*     */       
/* 100 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/* 102 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 105 */     this.m_map[this.m_firstFree] = value;
/*     */     
/* 107 */     this.m_firstFree++;
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
/*     */   public final String get(String key) {
/* 122 */     for (int i = this.m_firstFree - 1; i >= 0; i--) {
/*     */       
/* 124 */       String nsuri = this.m_map[i].get(key);
/*     */       
/* 126 */       if (nsuri != null) {
/* 127 */         return nsuri;
/*     */       }
/*     */     } 
/* 130 */     return null;
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
/*     */   public final boolean containsKey(String key) {
/* 144 */     for (int i = this.m_firstFree - 1; i >= 0; i--) {
/*     */       
/* 146 */       if (this.m_map[i].get(key) != null) {
/* 147 */         return true;
/*     */       }
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void removeLastElem() {
/* 159 */     if (this.m_firstFree > 0) {
/*     */       
/* 161 */       this.m_map[this.m_firstFree] = null;
/*     */       
/* 163 */       this.m_firstFree--;
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
/*     */   public final StringToStringTable elementAt(int i) {
/* 176 */     return this.m_map[i];
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
/*     */   public final boolean contains(StringToStringTable s) {
/* 189 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 191 */       if (this.m_map[i].equals(s)) {
/* 192 */         return true;
/*     */       }
/*     */     } 
/* 195 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/StringToStringTableVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */