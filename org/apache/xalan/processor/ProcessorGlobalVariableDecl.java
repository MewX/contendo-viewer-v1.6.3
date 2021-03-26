/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import org.apache.xalan.templates.ElemTemplateElement;
/*    */ import org.apache.xalan.templates.ElemVariable;
/*    */ import org.xml.sax.SAXException;
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
/*    */ class ProcessorGlobalVariableDecl
/*    */   extends ProcessorTemplateElem
/*    */ {
/*    */   protected void appendAndPush(StylesheetHandler handler, ElemTemplateElement elem) throws SAXException {
/* 49 */     handler.pushElemTemplateElement(elem);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void endElement(StylesheetHandler handler, String uri, String localName, String rawName) throws SAXException {
/* 68 */     ElemVariable v = (ElemVariable)handler.getElemTemplateElement();
/*    */     
/* 70 */     handler.getStylesheet().appendChild((ElemTemplateElement)v);
/* 71 */     handler.getStylesheet().setVariable(v);
/* 72 */     super.endElement(handler, uri, localName, rawName);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorGlobalVariableDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */