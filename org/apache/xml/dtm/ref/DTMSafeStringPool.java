/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DTMSafeStringPool
/*     */   extends DTMStringPool
/*     */ {
/*     */   public synchronized void removeAllElements() {
/*  35 */     super.removeAllElements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String indexToString(int i) throws ArrayIndexOutOfBoundsException {
/*  45 */     return super.indexToString(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int stringToIndex(String s) {
/*  51 */     return super.stringToIndex(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  60 */     String[] word = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Twenty-One", "Twenty-Two", "Twenty-Three", "Twenty-Four", "Twenty-Five", "Twenty-Six", "Twenty-Seven", "Twenty-Eight", "Twenty-Nine", "Thirty", "Thirty-One", "Thirty-Two", "Thirty-Three", "Thirty-Four", "Thirty-Five", "Thirty-Six", "Thirty-Seven", "Thirty-Eight", "Thirty-Nine" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     DTMStringPool pool = new DTMSafeStringPool();
/*     */     
/*  73 */     System.out.println("If no complaints are printed below, we passed initial test.");
/*     */     
/*  75 */     for (int pass = 0; pass <= 1; pass++) {
/*     */       int i;
/*     */ 
/*     */       
/*  79 */       for (i = 0; i < word.length; i++) {
/*     */         
/*  81 */         int j = pool.stringToIndex(word[i]);
/*  82 */         if (j != i) {
/*  83 */           System.out.println("\tMismatch populating pool: assigned " + j + " for create " + i);
/*     */         }
/*     */       } 
/*     */       
/*  87 */       for (i = 0; i < word.length; i++) {
/*     */         
/*  89 */         int j = pool.stringToIndex(word[i]);
/*  90 */         if (j != i) {
/*  91 */           System.out.println("\tMismatch in stringToIndex: returned " + j + " for lookup " + i);
/*     */         }
/*     */       } 
/*     */       
/*  95 */       for (i = 0; i < word.length; i++) {
/*     */         
/*  97 */         String w = pool.indexToString(i);
/*  98 */         if (!word[i].equals(w)) {
/*  99 */           System.out.println("\tMismatch in indexToString: returned" + w + " for lookup " + i);
/*     */         }
/*     */       } 
/*     */       
/* 103 */       pool.removeAllElements();
/*     */       
/* 105 */       System.out.println("\nPass " + pass + " complete\n");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMSafeStringPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */