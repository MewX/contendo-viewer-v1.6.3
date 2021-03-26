/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import org.apache.xalan.templates.ElemTemplate;
/*    */ import org.apache.xalan.templates.ElemTemplateElement;
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
/*    */ class ProcessorTemplate
/*    */   extends ProcessorTemplateElem
/*    */ {
/*    */   protected void appendAndPush(StylesheetHandler handler, ElemTemplateElement elem) throws SAXException {
/* 46 */     super.appendAndPush(handler, elem);
/* 47 */     elem.setDOMBackPointer(handler.getOriginatingNode());
/* 48 */     handler.getStylesheet().setTemplate((ElemTemplate)elem);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorTemplate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */