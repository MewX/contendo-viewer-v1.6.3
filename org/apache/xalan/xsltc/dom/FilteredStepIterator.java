/*    */ package org.apache.xalan.xsltc.dom;
/*    */ 
/*    */ import org.apache.xml.dtm.DTMAxisIterator;
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
/*    */ public final class FilteredStepIterator
/*    */   extends StepIterator
/*    */ {
/*    */   private Filter _filter;
/*    */   
/*    */   public FilteredStepIterator(DTMAxisIterator source, DTMAxisIterator iterator, Filter filter) {
/* 38 */     super(source, iterator);
/* 39 */     this._filter = filter;
/*    */   }
/*    */   
/*    */   public int next() {
/*    */     int node;
/* 44 */     while ((node = super.next()) != -1) {
/* 45 */       if (this._filter.test(node)) {
/* 46 */         return returnNode(node);
/*    */       }
/*    */     } 
/* 49 */     return node;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/FilteredStepIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */