/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.SACMediaList;
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
/*    */ public class CSSSACMediaList
/*    */   implements SACMediaList
/*    */ {
/* 34 */   protected String[] list = new String[3];
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int length;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLength() {
/* 45 */     return this.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String item(int index) {
/* 53 */     if (index < 0 || index >= this.length) {
/* 54 */       return null;
/*    */     }
/* 56 */     return this.list[index];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void append(String item) {
/* 63 */     if (this.length == this.list.length) {
/*    */       
/* 65 */       String[] tmp = this.list;
/* 66 */       this.list = new String[1 + this.list.length + this.list.length / 2];
/* 67 */       System.arraycopy(tmp, 0, this.list, 0, tmp.length);
/*    */     } 
/* 69 */     this.list[this.length++] = item;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/CSSSACMediaList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */