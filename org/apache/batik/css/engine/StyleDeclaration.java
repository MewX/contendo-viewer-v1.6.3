/*     */ package org.apache.batik.css.engine;
/*     */ 
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StyleDeclaration
/*     */ {
/*     */   protected static final int INITIAL_LENGTH = 8;
/*  36 */   protected Value[] values = new Value[8];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   protected int[] indexes = new int[8];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected boolean[] priorities = new boolean[8];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int count;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  57 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getValue(int idx) {
/*  64 */     return this.values[idx];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex(int idx) {
/*  71 */     return this.indexes[idx];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPriority(int idx) {
/*  78 */     return this.priorities[idx];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int idx) {
/*  85 */     this.count--;
/*  86 */     int from = idx + 1;
/*  87 */     int to = idx;
/*  88 */     int nCopy = this.count - idx;
/*     */     
/*  90 */     System.arraycopy(this.values, from, this.values, to, nCopy);
/*  91 */     System.arraycopy(this.indexes, from, this.indexes, to, nCopy);
/*  92 */     System.arraycopy(this.priorities, from, this.priorities, to, nCopy);
/*     */     
/*  94 */     this.values[this.count] = null;
/*  95 */     this.indexes[this.count] = 0;
/*  96 */     this.priorities[this.count] = false;
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
/*     */   public void put(int idx, Value v, int i, boolean prio) {
/* 109 */     this.values[idx] = v;
/* 110 */     this.indexes[idx] = i;
/* 111 */     this.priorities[idx] = prio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(Value v, int idx, boolean prio) {
/* 118 */     if (this.values.length == this.count) {
/* 119 */       Value[] newval = new Value[this.count * 2];
/* 120 */       int[] newidx = new int[this.count * 2];
/* 121 */       boolean[] newprio = new boolean[this.count * 2];
/*     */       
/* 123 */       System.arraycopy(this.values, 0, newval, 0, this.count);
/* 124 */       System.arraycopy(this.indexes, 0, newidx, 0, this.count);
/* 125 */       System.arraycopy(this.priorities, 0, newprio, 0, this.count);
/*     */       
/* 127 */       this.values = newval;
/* 128 */       this.indexes = newidx;
/* 129 */       this.priorities = newprio;
/*     */     } 
/* 131 */     for (int i = 0; i < this.count; i++) {
/* 132 */       if (this.indexes[i] == idx) {
/*     */ 
/*     */         
/* 135 */         if (prio || this.priorities[i] == prio) {
/* 136 */           this.values[i] = v;
/* 137 */           this.priorities[i] = prio;
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/* 142 */     this.values[this.count] = v;
/* 143 */     this.indexes[this.count] = idx;
/* 144 */     this.priorities[this.count] = prio;
/* 145 */     this.count++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(CSSEngine eng) {
/* 152 */     StringBuffer sb = new StringBuffer(this.count * 8);
/* 153 */     for (int i = 0; i < this.count; i++) {
/* 154 */       sb.append(eng.getPropertyName(this.indexes[i]));
/* 155 */       sb.append(": ");
/* 156 */       sb.append(this.values[i]);
/* 157 */       sb.append(";\n");
/*     */     } 
/* 159 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/StyleDeclaration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */