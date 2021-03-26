/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import org.apache.xalan.templates.ElemTemplateElement;
/*    */ import org.xml.sax.Attributes;
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
/*    */ public class ProcessorExsltFuncResult
/*    */   extends ProcessorTemplateElem
/*    */ {
/*    */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 46 */     String msg = "";
/*    */     
/* 48 */     super.startElement(handler, uri, localName, rawName, attributes);
/* 49 */     ElemTemplateElement ancestor = handler.getElemTemplateElement().getParentElem();
/* 50 */     while (ancestor != null && !(ancestor instanceof org.apache.xalan.templates.ElemExsltFunction)) {
/*    */       
/* 52 */       if (ancestor instanceof org.apache.xalan.templates.ElemVariable || ancestor instanceof org.apache.xalan.templates.ElemParam || ancestor instanceof org.apache.xalan.templates.ElemExsltFuncResult) {
/*    */ 
/*    */ 
/*    */         
/* 56 */         msg = "func:result cannot appear within a variable, parameter, or another func:result.";
/* 57 */         handler.error(msg, new SAXException(msg));
/*    */       } 
/* 59 */       ancestor = ancestor.getParentElem();
/*    */     } 
/* 61 */     if (ancestor == null) {
/*    */       
/* 63 */       msg = "func:result must appear in a func:function element";
/* 64 */       handler.error(msg, new SAXException(msg));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorExsltFuncResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */