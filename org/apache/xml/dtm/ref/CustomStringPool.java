/*    */ package org.apache.xml.dtm.ref;
/*    */ 
/*    */ import java.util.Hashtable;
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
/*    */ public class CustomStringPool
/*    */   extends DTMStringPool
/*    */ {
/* 44 */   final Hashtable m_stringToInt = new Hashtable();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final int NULL = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void removeAllElements() {
/* 59 */     this.m_intToString.removeAllElements();
/* 60 */     if (this.m_stringToInt != null) {
/* 61 */       this.m_stringToInt.clear();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String indexToString(int i) throws ArrayIndexOutOfBoundsException {
/* 71 */     return this.m_intToString.elementAt(i);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int stringToIndex(String s) {
/* 77 */     if (s == null) return -1; 
/* 78 */     Integer iobj = (Integer)this.m_stringToInt.get(s);
/* 79 */     if (iobj == null) {
/* 80 */       this.m_intToString.addElement(s);
/* 81 */       iobj = new Integer(this.m_intToString.size());
/* 82 */       this.m_stringToInt.put(s, iobj);
/*    */     } 
/* 84 */     return iobj.intValue();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/CustomStringPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */