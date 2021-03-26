/*    */ package org.apache.batik.dom.util;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
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
/*    */ public class ListNodeList
/*    */   implements NodeList
/*    */ {
/*    */   protected List list;
/*    */   
/*    */   public ListNodeList(List list) {
/* 38 */     this.list = list;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Node item(int index) {
/* 45 */     if (index < 0 || index > this.list.size())
/* 46 */       return null; 
/* 47 */     return this.list.get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLength() {
/* 54 */     return this.list.size();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/ListNodeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */