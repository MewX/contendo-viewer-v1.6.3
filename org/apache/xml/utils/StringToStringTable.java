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
/*     */ public class StringToStringTable
/*     */ {
/*     */   private int m_blocksize;
/*     */   private String[] m_map;
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
/*     */   public StringToStringTable() {
/*  48 */     this.m_blocksize = 16;
/*  49 */     this.m_mapSize = this.m_blocksize;
/*  50 */     this.m_map = new String[this.m_blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringToStringTable(int blocksize) {
/*  61 */     this.m_blocksize = blocksize;
/*  62 */     this.m_mapSize = blocksize;
/*  63 */     this.m_map = new String[blocksize];
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void put(String key, String value) {
/*  87 */     if (this.m_firstFree + 2 >= this.m_mapSize) {
/*     */       
/*  89 */       this.m_mapSize += this.m_blocksize;
/*     */       
/*  91 */       String[] newMap = new String[this.m_mapSize];
/*     */       
/*  93 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/*  95 */       this.m_map = newMap;
/*     */     } 
/*     */     
/*  98 */     this.m_map[this.m_firstFree] = key;
/*     */     
/* 100 */     this.m_firstFree++;
/*     */     
/* 102 */     this.m_map[this.m_firstFree] = value;
/*     */     
/* 104 */     this.m_firstFree++;
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
/*     */   public final String get(String key) {
/* 117 */     for (int i = 0; i < this.m_firstFree; i += 2) {
/*     */       
/* 119 */       if (this.m_map[i].equals(key)) {
/* 120 */         return this.m_map[i + 1];
/*     */       }
/*     */     } 
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void remove(String key) {
/* 134 */     for (int i = 0; i < this.m_firstFree; i += 2) {
/*     */       
/* 136 */       if (this.m_map[i].equals(key)) {
/*     */         
/* 138 */         if (i + 2 < this.m_firstFree) {
/* 139 */           System.arraycopy(this.m_map, i + 2, this.m_map, i, this.m_firstFree - i + 2);
/*     */         }
/* 141 */         this.m_firstFree -= 2;
/* 142 */         this.m_map[this.m_firstFree] = null;
/* 143 */         this.m_map[this.m_firstFree + 1] = null;
/*     */         break;
/*     */       } 
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
/*     */   public final String getIgnoreCase(String key) {
/* 160 */     if (null == key) {
/* 161 */       return null;
/*     */     }
/* 163 */     for (int i = 0; i < this.m_firstFree; i += 2) {
/*     */       
/* 165 */       if (this.m_map[i].equalsIgnoreCase(key)) {
/* 166 */         return this.m_map[i + 1];
/*     */       }
/*     */     } 
/* 169 */     return null;
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
/*     */   public final String getByValue(String val) {
/* 182 */     for (int i = 1; i < this.m_firstFree; i += 2) {
/*     */       
/* 184 */       if (this.m_map[i].equals(val)) {
/* 185 */         return this.m_map[i - 1];
/*     */       }
/*     */     } 
/* 188 */     return null;
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
/*     */   public final String elementAt(int i) {
/* 200 */     return this.m_map[i];
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
/*     */   public final boolean contains(String key) {
/* 213 */     for (int i = 0; i < this.m_firstFree; i += 2) {
/*     */       
/* 215 */       if (this.m_map[i].equals(key)) {
/* 216 */         return true;
/*     */       }
/*     */     } 
/* 219 */     return false;
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
/*     */   public final boolean containsValue(String val) {
/* 232 */     for (int i = 1; i < this.m_firstFree; i += 2) {
/*     */       
/* 234 */       if (this.m_map[i].equals(val)) {
/* 235 */         return true;
/*     */       }
/*     */     } 
/* 238 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/StringToStringTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */