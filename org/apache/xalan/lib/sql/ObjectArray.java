/*     */ package org.apache.xalan.lib.sql;
/*     */ 
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectArray
/*     */ {
/*  34 */   private int m_minArraySize = 10;
/*     */ 
/*     */ 
/*     */   
/*  38 */   private Vector m_Arrays = new Vector(200);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private _ObjectArray m_currentArray;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int m_nextSlot;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectArray() {
/*  60 */     init(10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectArray(int minArraySize) {
/*  68 */     init(minArraySize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init(int size) {
/*  77 */     this.m_minArraySize = size;
/*  78 */     this.m_currentArray = new _ObjectArray(this, this.m_minArraySize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getAt(int idx) {
/*  87 */     int arrayIndx = idx / this.m_minArraySize;
/*  88 */     int arrayOffset = idx - arrayIndx * this.m_minArraySize;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     if (arrayIndx < this.m_Arrays.size()) {
/*     */       
/*  95 */       _ObjectArray a = this.m_Arrays.elementAt(arrayIndx);
/*  96 */       return a.objects[arrayOffset];
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     return this.m_currentArray.objects[arrayOffset];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAt(int idx, Object obj) {
/* 116 */     int arrayIndx = idx / this.m_minArraySize;
/* 117 */     int arrayOffset = idx - arrayIndx * this.m_minArraySize;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (arrayIndx < this.m_Arrays.size()) {
/*     */       
/* 124 */       _ObjectArray a = this.m_Arrays.elementAt(arrayIndx);
/* 125 */       a.objects[arrayOffset] = obj;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 134 */       this.m_currentArray.objects[arrayOffset] = obj;
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
/*     */   public int append(Object o) {
/* 146 */     if (this.m_nextSlot >= this.m_minArraySize) {
/*     */       
/* 148 */       this.m_Arrays.addElement(this.m_currentArray);
/* 149 */       this.m_nextSlot = 0;
/* 150 */       this.m_currentArray = new _ObjectArray(this, this.m_minArraySize);
/*     */     } 
/*     */     
/* 153 */     this.m_currentArray.objects[this.m_nextSlot] = o;
/*     */     
/* 155 */     int pos = this.m_Arrays.size() * this.m_minArraySize + this.m_nextSlot;
/*     */     
/* 157 */     this.m_nextSlot++;
/*     */     
/* 159 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class _ObjectArray
/*     */   {
/*     */     public Object[] objects;
/*     */ 
/*     */     
/*     */     private final ObjectArray this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     public _ObjectArray(ObjectArray this$0, int size) {
/* 174 */       this.this$0 = this$0;
/* 175 */       this.objects = new Object[size];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 185 */     String[] word = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Twenty-One", "Twenty-Two", "Twenty-Three", "Twenty-Four", "Twenty-Five", "Twenty-Six", "Twenty-Seven", "Twenty-Eight", "Twenty-Nine", "Thirty", "Thirty-One", "Thirty-Two", "Thirty-Three", "Thirty-Four", "Thirty-Five", "Thirty-Six", "Thirty-Seven", "Thirty-Eight", "Thirty-Nine" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     ObjectArray m_ObjectArray = new ObjectArray();
/*     */     
/* 198 */     for (int x = 0; x < word.length; x++)
/*     */     {
/* 200 */       System.out.print(" - " + m_ObjectArray.append(word[x]));
/*     */     }
/*     */     
/* 203 */     System.out.println("\n");
/*     */     
/* 205 */     for (int i = 0; i < word.length; i++) {
/*     */       
/* 207 */       String s = (String)m_ObjectArray.getAt(i);
/* 208 */       System.out.println(s);
/*     */     } 
/*     */ 
/*     */     
/* 212 */     System.out.println((String)m_ObjectArray.getAt(5));
/* 213 */     System.out.println((String)m_ObjectArray.getAt(10));
/* 214 */     System.out.println((String)m_ObjectArray.getAt(20));
/* 215 */     System.out.println((String)m_ObjectArray.getAt(2));
/* 216 */     System.out.println((String)m_ObjectArray.getAt(15));
/* 217 */     System.out.println((String)m_ObjectArray.getAt(30));
/* 218 */     System.out.println((String)m_ObjectArray.getAt(6));
/* 219 */     System.out.println((String)m_ObjectArray.getAt(8));
/*     */ 
/*     */     
/* 222 */     System.out.println((String)m_ObjectArray.getAt(40));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/ObjectArray.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */