/*    */ package org.apache.xalan.processor;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.xalan.templates.ElemTemplateElement;
/*    */ import org.apache.xalan.templates.Stylesheet;
/*    */ import org.apache.xalan.templates.WhiteSpaceInfo;
/*    */ import org.apache.xpath.XPath;
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
/*    */ class ProcessorPreserveSpace
/*    */   extends XSLTElementProcessor
/*    */ {
/*    */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 60 */     Stylesheet thisSheet = handler.getStylesheet();
/* 61 */     WhitespaceInfoPaths paths = new WhitespaceInfoPaths(thisSheet);
/* 62 */     setPropertiesFromAttributes(handler, rawName, attributes, (ElemTemplateElement)paths);
/*    */     
/* 64 */     Vector xpaths = paths.getElements();
/*    */     
/* 66 */     for (int i = 0; i < xpaths.size(); i++) {
/*    */       
/* 68 */       WhiteSpaceInfo wsi = new WhiteSpaceInfo(xpaths.elementAt(i), false, thisSheet);
/* 69 */       wsi.setUid(handler.nextUid());
/*    */       
/* 71 */       thisSheet.setPreserveSpaces(wsi);
/*    */     } 
/* 73 */     paths.clearElements();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorPreserveSpace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */