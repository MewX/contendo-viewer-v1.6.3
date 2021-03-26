/*    */ package org.apache.xalan.xsltc.runtime;
/*    */ 
/*    */ import org.apache.xalan.xsltc.DOM;
/*    */ import org.xml.sax.AttributeList;
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
/*    */ public final class Attributes
/*    */   implements AttributeList
/*    */ {
/*    */   private int _element;
/*    */   private DOM _document;
/*    */   
/*    */   public Attributes(DOM document, int element) {
/* 34 */     this._element = element;
/* 35 */     this._document = document;
/*    */   }
/*    */   
/*    */   public int getLength() {
/* 39 */     return 0;
/*    */   }
/*    */   
/*    */   public String getName(int i) {
/* 43 */     return null;
/*    */   }
/*    */   
/*    */   public String getType(int i) {
/* 47 */     return null;
/*    */   }
/*    */   
/*    */   public String getType(String name) {
/* 51 */     return null;
/*    */   }
/*    */   
/*    */   public String getValue(int i) {
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   public String getValue(String name) {
/* 59 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/Attributes.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */