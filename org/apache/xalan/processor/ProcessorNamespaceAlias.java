/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import org.apache.xalan.templates.ElemTemplateElement;
/*    */ import org.apache.xalan.templates.NamespaceAlias;
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
/*    */ class ProcessorNamespaceAlias
/*    */   extends XSLTElementProcessor
/*    */ {
/*    */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 62 */     NamespaceAlias na = new NamespaceAlias(handler.nextUid());
/*    */     
/* 64 */     setPropertiesFromAttributes(handler, rawName, attributes, (ElemTemplateElement)na);
/* 65 */     String prefix = na.getStylesheetPrefix();
/* 66 */     if (prefix.equals("#default")) {
/*    */       
/* 68 */       prefix = "";
/* 69 */       na.setStylesheetPrefix(prefix);
/*    */     } 
/* 71 */     String stylesheetNS = handler.getNamespaceForPrefix(prefix);
/* 72 */     na.setStylesheetNamespace(stylesheetNS);
/* 73 */     prefix = na.getResultPrefix();
/* 74 */     if (prefix.equals("#default")) {
/*    */       
/* 76 */       prefix = "";
/* 77 */       na.setResultPrefix(prefix);
/*    */     } 
/* 79 */     String resultNS = handler.getNamespaceForPrefix(prefix);
/* 80 */     na.setResultNamespace(resultNS);
/* 81 */     handler.getStylesheet().setNamespaceAlias(na);
/* 82 */     handler.getStylesheet().appendChild((ElemTemplateElement)na);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorNamespaceAlias.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */