/*     */ package org.apache.xalan.xsltc.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class IntegerArray
/*     */ {
/*     */   private static final int InitialSize = 32;
/*     */   private int[] _array;
/*     */   private int _size;
/*  30 */   private int _free = 0;
/*     */   
/*     */   public IntegerArray() {
/*  33 */     this(32);
/*     */   }
/*     */   
/*     */   public IntegerArray(int size) {
/*  37 */     this._array = new int[this._size = size];
/*     */   }
/*     */   
/*     */   public IntegerArray(int[] array) {
/*  41 */     this(array.length);
/*  42 */     System.arraycopy(array, 0, this._array, 0, this._free = this._size);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  46 */     this._free = 0;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  50 */     IntegerArray clone = new IntegerArray((this._free > 0) ? this._free : 1);
/*  51 */     System.arraycopy(this._array, 0, clone._array, 0, this._free);
/*  52 */     clone._free = this._free;
/*  53 */     return clone;
/*     */   }
/*     */   
/*     */   public int[] toIntArray() {
/*  57 */     int[] result = new int[cardinality()];
/*  58 */     System.arraycopy(this._array, 0, result, 0, cardinality());
/*  59 */     return result;
/*     */   }
/*     */   
/*     */   public final int at(int index) {
/*  63 */     return this._array[index];
/*     */   }
/*     */   
/*     */   public final void set(int index, int value) {
/*  67 */     this._array[index] = value;
/*     */   }
/*     */   
/*     */   public int indexOf(int n) {
/*  71 */     for (int i = 0; i < this._free; i++) {
/*  72 */       if (n == this._array[i]) return i; 
/*     */     } 
/*  74 */     return -1;
/*     */   }
/*     */   
/*     */   public final void add(int value) {
/*  78 */     if (this._free == this._size) {
/*  79 */       growArray(this._size * 2);
/*     */     }
/*  81 */     this._array[this._free++] = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNew(int value) {
/*  88 */     for (int i = 0; i < this._free; i++) {
/*  89 */       if (this._array[i] == value)
/*     */         return; 
/*  91 */     }  add(value);
/*     */   }
/*     */   
/*     */   public void reverse() {
/*  95 */     int left = 0;
/*  96 */     int right = this._free - 1;
/*     */     
/*  98 */     while (left < right) {
/*  99 */       int temp = this._array[left];
/* 100 */       this._array[left++] = this._array[right];
/* 101 */       this._array[right--] = temp;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(IntegerArray other) {
/* 109 */     int newSize = this._free + other._free;
/*     */     
/* 111 */     int[] newArray = new int[newSize];
/*     */ 
/*     */     
/* 114 */     int i = 0, j = 0; int k;
/* 115 */     for (k = 0; i < this._free && j < other._free; k++) {
/* 116 */       int x = this._array[i];
/* 117 */       int y = other._array[j];
/*     */       
/* 119 */       if (x < y) {
/* 120 */         newArray[k] = x;
/* 121 */         i++;
/*     */       }
/* 123 */       else if (x > y) {
/* 124 */         newArray[k] = y;
/* 125 */         j++;
/*     */       } else {
/*     */         
/* 128 */         newArray[k] = x;
/* 129 */         i++; j++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 134 */     if (i >= this._free) {
/* 135 */       while (j < other._free) {
/* 136 */         newArray[k++] = other._array[j++];
/*     */       }
/*     */     } else {
/*     */       
/* 140 */       while (i < this._free) {
/* 141 */         newArray[k++] = this._array[i++];
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 146 */     this._array = newArray;
/* 147 */     this._free = this._size = newSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sort() {
/* 152 */     quicksort(this._array, 0, this._free - 1);
/*     */   }
/*     */   
/*     */   private static void quicksort(int[] array, int p, int r) {
/* 156 */     if (p < r) {
/* 157 */       int q = partition(array, p, r);
/* 158 */       quicksort(array, p, q);
/* 159 */       quicksort(array, q + 1, r);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int partition(int[] array, int p, int r) {
/* 164 */     int x = array[p + r >>> 1];
/* 165 */     int i = p - 1, j = r + 1;
/*     */     
/*     */     while (true) {
/* 168 */       if (x >= array[--j]) { do {  }
/* 169 */         while (x > array[++i]);
/* 170 */         if (i < j) {
/* 171 */           int temp = array[i];
/* 172 */           array[i] = array[j];
/* 173 */           array[j] = temp; continue;
/*     */         }  break; }
/*     */     
/* 176 */     }  return j;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void growArray(int size) {
/* 182 */     int[] newArray = new int[this._size = size];
/* 183 */     System.arraycopy(this._array, 0, newArray, 0, this._free);
/* 184 */     this._array = newArray;
/*     */   }
/*     */   
/*     */   public int popLast() {
/* 188 */     return this._array[--this._free];
/*     */   }
/*     */   
/*     */   public int last() {
/* 192 */     return this._array[this._free - 1];
/*     */   }
/*     */   
/*     */   public void setLast(int n) {
/* 196 */     this._array[this._free - 1] = n;
/*     */   }
/*     */   
/*     */   public void pop() {
/* 200 */     this._free--;
/*     */   }
/*     */   
/*     */   public void pop(int n) {
/* 204 */     this._free -= n;
/*     */   }
/*     */   
/*     */   public final int cardinality() {
/* 208 */     return this._free;
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 212 */     if (this._free > 0) {
/* 213 */       for (int i = 0; i < this._free - 1; i++) {
/* 214 */         out.print(this._array[i]);
/* 215 */         out.print(' ');
/*     */       } 
/* 217 */       out.println(this._array[this._free - 1]);
/*     */     } else {
/*     */       
/* 220 */       out.println("IntegerArray: empty");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/util/IntegerArray.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */