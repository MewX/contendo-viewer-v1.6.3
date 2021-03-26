/*     */ package org.apache.xml.utils;
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
/*     */ public class StringVector
/*     */   implements Serializable
/*     */ {
/*     */   protected int m_blocksize;
/*     */   protected String[] m_map;
/*  36 */   protected int m_firstFree = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_mapSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringVector() {
/*  48 */     this.m_blocksize = 8;
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
/*     */   public StringVector(int blocksize) {
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
/*     */   public int getLength() {
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
/*     */   public final void addElement(String value) {
/*  94 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/*  96 */       this.m_mapSize += this.m_blocksize;
/*     */       
/*  98 */       String[] newMap = new String[this.m_mapSize];
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
/*     */   public final String elementAt(int i) {
/* 119 */     return this.m_map[i];
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
/*     */   public final boolean contains(String s) {
/* 132 */     if (null == s) {
/* 133 */       return false;
/*     */     }
/* 135 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 137 */       if (this.m_map[i].equals(s)) {
/* 138 */         return true;
/*     */       }
/*     */     } 
/* 141 */     return false;
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
/*     */   public final boolean containsIgnoreCase(String s) {
/* 154 */     if (null == s) {
/* 155 */       return false;
/*     */     }
/* 157 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 159 */       if (this.m_map[i].equalsIgnoreCase(s)) {
/* 160 */         return true;
/*     */       }
/*     */     } 
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void push(String s) {
/* 174 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/* 176 */       this.m_mapSize += this.m_blocksize;
/*     */       
/* 178 */       String[] newMap = new String[this.m_mapSize];
/*     */       
/* 180 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/* 182 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 185 */     this.m_map[this.m_firstFree] = s;
/*     */     
/* 187 */     this.m_firstFree++;
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
/*     */   public final String pop() {
/* 199 */     if (this.m_firstFree <= 0) {
/* 200 */       return null;
/*     */     }
/* 202 */     this.m_firstFree--;
/*     */     
/* 204 */     String s = this.m_map[this.m_firstFree];
/*     */     
/* 206 */     this.m_map[this.m_firstFree] = null;
/*     */     
/* 208 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String peek() {
/* 218 */     return (this.m_firstFree <= 0) ? null : this.m_map[this.m_firstFree - 1];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/StringVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */