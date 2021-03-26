/*    */ package org.apache.bcel.verifier.statics;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntList
/*    */ {
/* 70 */   private ArrayList theList = new ArrayList();
/*    */ 
/*    */   
/*    */   void add(int i) {
/* 74 */     this.theList.add(new Integer(i));
/*    */   }
/*    */   
/*    */   boolean contains(int i) {
/* 78 */     Integer[] ints = new Integer[this.theList.size()];
/* 79 */     this.theList.toArray((Object[])ints);
/* 80 */     for (int j = 0; j < ints.length; j++) {
/* 81 */       if (i == ints[j].intValue()) return true; 
/*    */     } 
/* 83 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/statics/IntList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */