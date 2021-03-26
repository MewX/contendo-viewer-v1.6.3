/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.Selector;
/*    */ import org.w3c.css.sac.SelectorList;
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
/*    */ public class CSSSelectorList
/*    */   implements SelectorList
/*    */ {
/* 35 */   protected Selector[] list = new Selector[3];
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
/* 46 */     return this.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Selector item(int index) {
/* 54 */     if (index < 0 || index >= this.length) {
/* 55 */       return null;
/*    */     }
/* 57 */     return this.list[index];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void append(Selector item) {
/* 64 */     if (this.length == this.list.length) {
/*    */       
/* 66 */       Selector[] tmp = this.list;
/* 67 */       this.list = new Selector[1 + this.list.length + this.list.length / 2];
/* 68 */       System.arraycopy(tmp, 0, this.list, 0, tmp.length);
/*    */     } 
/* 70 */     this.list[this.length++] = item;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/CSSSelectorList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */