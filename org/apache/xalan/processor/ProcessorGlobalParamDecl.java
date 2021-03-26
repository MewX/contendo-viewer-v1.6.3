/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import org.apache.xalan.templates.ElemParam;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ class ProcessorGlobalParamDecl
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
/* 68 */     ElemParam v = (ElemParam)handler.getElemTemplateElement();
/*    */     
/* 70 */     handler.getStylesheet().appendChild((ElemTemplateElement)v);
/* 71 */     handler.getStylesheet().setParam(v);
/* 72 */     super.endElement(handler, uri, localName, rawName);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorGlobalParamDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */