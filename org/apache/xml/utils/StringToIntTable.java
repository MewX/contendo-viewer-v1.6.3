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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringToIntTable
/*     */ {
/*     */   public static final int INVALID_KEY = -10000;
/*     */   private int m_blocksize;
/*     */   private String[] m_map;
/*     */   private int[] m_values;
/*  43 */   private int m_firstFree = 0;
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
/*     */   public StringToIntTable() {
/*  55 */     this.m_blocksize = 8;
/*  56 */     this.m_mapSize = this.m_blocksize;
/*  57 */     this.m_map = new String[this.m_blocksize];
/*  58 */     this.m_values = new int[this.m_blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringToIntTable(int blocksize) {
/*  69 */     this.m_blocksize = blocksize;
/*  70 */     this.m_mapSize = blocksize;
/*  71 */     this.m_map = new String[blocksize];
/*  72 */     this.m_values = new int[this.m_blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getLength() {
/*  82 */     return this.m_firstFree;
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
/*     */   public final void put(String key, int value) {
/*  94 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/*  96 */       this.m_mapSize += this.m_blocksize;
/*     */       
/*  98 */       String[] newMap = new String[this.m_mapSize];
/*     */       
/* 100 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/* 102 */       this.m_map = newMap;
/*     */       
/* 104 */       int[] newValues = new int[this.m_mapSize];
/*     */       
/* 106 */       System.arraycopy(this.m_values, 0, newValues, 0, this.m_firstFree + 1);
/*     */       
/* 108 */       this.m_values = newValues;
/*     */     } 
/*     */     
/* 111 */     this.m_map[this.m_firstFree] = key;
/* 112 */     this.m_values[this.m_firstFree] = value;
/*     */     
/* 114 */     this.m_firstFree++;
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
/*     */   public final int get(String key) {
/* 128 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 130 */       if (this.m_map[i].equals(key)) {
/* 131 */         return this.m_values[i];
/*     */       }
/*     */     } 
/* 134 */     return -10000;
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
/*     */   public final int getIgnoreCase(String key) {
/* 147 */     if (null == key) {
/* 148 */       return -10000;
/*     */     }
/* 150 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 152 */       if (this.m_map[i].equalsIgnoreCase(key)) {
/* 153 */         return this.m_values[i];
/*     */       }
/*     */     } 
/* 156 */     return -10000;
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
/* 169 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 171 */       if (this.m_map[i].equals(key)) {
/* 172 */         return true;
/*     */       }
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String[] keys() {
/* 185 */     String[] keysArr = new String[this.m_firstFree];
/*     */     
/* 187 */     for (int i = 0; i < this.m_firstFree; i++)
/*     */     {
/* 189 */       keysArr[i] = this.m_map[i];
/*     */     }
/*     */     
/* 192 */     return keysArr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/StringToIntTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */