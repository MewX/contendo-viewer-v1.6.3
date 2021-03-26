/*    */ package org.apache.batik.dom.svg;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class ListBuilder
/*    */   implements ListHandler
/*    */ {
/*    */   private final AbstractSVGList abstractSVGList;
/*    */   protected List list;
/*    */   
/*    */   public ListBuilder(AbstractSVGList abstractSVGList) {
/* 38 */     this.abstractSVGList = abstractSVGList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getList() {
/* 50 */     return this.list;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startList() {
/* 57 */     this.list = new ArrayList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void item(SVGItem item) {
/* 64 */     item.setParent(this.abstractSVGList);
/* 65 */     this.list.add(item);
/*    */   }
/*    */   
/*    */   public void endList() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/ListBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */