/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import javax.xml.transform.SourceLocator;
/*    */ import org.apache.xalan.templates.DecimalFormatProperties;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ProcessorDecimalFormat
/*    */   extends XSLTElementProcessor
/*    */ {
/*    */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 62 */     DecimalFormatProperties dfp = new DecimalFormatProperties(handler.nextUid());
/*    */     
/* 64 */     dfp.setDOMBackPointer(handler.getOriginatingNode());
/* 65 */     dfp.setLocaterInfo((SourceLocator)handler.getLocator());
/*    */     
/* 67 */     setPropertiesFromAttributes(handler, rawName, attributes, (ElemTemplateElement)dfp);
/* 68 */     handler.getStylesheet().setDecimalFormat(dfp);
/*    */     
/* 70 */     handler.getStylesheet().appendChild((ElemTemplateElement)dfp);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorDecimalFormat.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */