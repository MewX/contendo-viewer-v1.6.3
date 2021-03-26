/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import org.apache.xalan.templates.ElemTemplateElement;
/*    */ import org.apache.xalan.templates.ElemText;
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
/*    */ public class ProcessorText
/*    */   extends ProcessorTemplateElem
/*    */ {
/*    */   protected void appendAndPush(StylesheetHandler handler, ElemTemplateElement elem) throws SAXException {
/* 49 */     ProcessorCharacters charProcessor = (ProcessorCharacters)handler.getProcessorFor(null, "text()", "text");
/*    */ 
/*    */     
/* 52 */     charProcessor.setXslTextElement((ElemText)elem);
/*    */     
/* 54 */     ElemTemplateElement parent = handler.getElemTemplateElement();
/*    */     
/* 56 */     parent.appendChild(elem);
/* 57 */     elem.setDOMBackPointer(handler.getOriginatingNode());
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
/* 76 */     ProcessorCharacters charProcessor = (ProcessorCharacters)handler.getProcessorFor(null, "text()", "text");
/*    */ 
/*    */     
/* 79 */     charProcessor.setXslTextElement(null);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorText.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */