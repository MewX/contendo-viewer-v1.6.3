/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.xml.utils.IntVector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DTMStringPool
/*     */ {
/*     */   Vector m_intToString;
/*     */   static final int HASHPRIME = 101;
/*  59 */   int[] m_hashStart = new int[101];
/*     */ 
/*     */   
/*     */   IntVector m_hashChain;
/*     */ 
/*     */   
/*     */   public static final int NULL = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMStringPool(int chainSize) {
/*  70 */     this.m_intToString = new Vector();
/*  71 */     this.m_hashChain = new IntVector(chainSize);
/*  72 */     removeAllElements();
/*     */ 
/*     */     
/*  75 */     stringToIndex("");
/*     */   }
/*     */ 
/*     */   
/*     */   public DTMStringPool() {
/*  80 */     this(512);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllElements() {
/*  85 */     this.m_intToString.removeAllElements();
/*  86 */     for (int i = 0; i < 101; i++)
/*  87 */       this.m_hashStart[i] = -1; 
/*  88 */     this.m_hashChain.removeAllElements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String indexToString(int i) throws ArrayIndexOutOfBoundsException {
/*  98 */     if (i == -1) return null; 
/*  99 */     return this.m_intToString.elementAt(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int stringToIndex(String s) {
/* 105 */     if (s == null) return -1;
/*     */     
/* 107 */     int hashslot = s.hashCode() % 101;
/* 108 */     if (hashslot < 0) hashslot = -hashslot;
/*     */ 
/*     */     
/* 111 */     int hashlast = this.m_hashStart[hashslot];
/* 112 */     int hashcandidate = hashlast;
/* 113 */     while (hashcandidate != -1) {
/*     */       
/* 115 */       if (this.m_intToString.elementAt(hashcandidate).equals(s)) {
/* 116 */         return hashcandidate;
/*     */       }
/* 118 */       hashlast = hashcandidate;
/* 119 */       hashcandidate = this.m_hashChain.elementAt(hashcandidate);
/*     */     } 
/*     */ 
/*     */     
/* 123 */     int newIndex = this.m_intToString.size();
/* 124 */     this.m_intToString.addElement(s);
/*     */     
/* 126 */     this.m_hashChain.addElement(-1);
/* 127 */     if (hashlast == -1) {
/* 128 */       this.m_hashStart[hashslot] = newIndex;
/*     */     } else {
/* 130 */       this.m_hashChain.setElementAt(newIndex, hashlast);
/*     */     } 
/* 132 */     return newIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 141 */     String[] word = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Twenty-One", "Twenty-Two", "Twenty-Three", "Twenty-Four", "Twenty-Five", "Twenty-Six", "Twenty-Seven", "Twenty-Eight", "Twenty-Nine", "Thirty", "Thirty-One", "Thirty-Two", "Thirty-Three", "Thirty-Four", "Thirty-Five", "Thirty-Six", "Thirty-Seven", "Thirty-Eight", "Thirty-Nine" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     DTMStringPool pool = new DTMStringPool();
/*     */     
/* 154 */     System.out.println("If no complaints are printed below, we passed initial test.");
/*     */     
/* 156 */     for (int pass = 0; pass <= 1; pass++) {
/*     */       int i;
/*     */ 
/*     */       
/* 160 */       for (i = 0; i < word.length; i++) {
/*     */         
/* 162 */         int j = pool.stringToIndex(word[i]);
/* 163 */         if (j != i) {
/* 164 */           System.out.println("\tMismatch populating pool: assigned " + j + " for create " + i);
/*     */         }
/*     */       } 
/*     */       
/* 168 */       for (i = 0; i < word.length; i++) {
/*     */         
/* 170 */         int j = pool.stringToIndex(word[i]);
/* 171 */         if (j != i) {
/* 172 */           System.out.println("\tMismatch in stringToIndex: returned " + j + " for lookup " + i);
/*     */         }
/*     */       } 
/*     */       
/* 176 */       for (i = 0; i < word.length; i++) {
/*     */         
/* 178 */         String w = pool.indexToString(i);
/* 179 */         if (!word[i].equals(w)) {
/* 180 */           System.out.println("\tMismatch in indexToString: returned" + w + " for lookup " + i);
/*     */         }
/*     */       } 
/*     */       
/* 184 */       pool.removeAllElements();
/*     */       
/* 186 */       System.out.println("\nPass " + pass + " complete\n");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMStringPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */