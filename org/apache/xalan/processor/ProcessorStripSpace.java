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
/*    */ class ProcessorStripSpace
/*    */   extends ProcessorPreserveSpace
/*    */ {
/*    */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 59 */     Stylesheet thisSheet = handler.getStylesheet();
/* 60 */     WhitespaceInfoPaths paths = new WhitespaceInfoPaths(thisSheet);
/* 61 */     setPropertiesFromAttributes(handler, rawName, attributes, (ElemTemplateElement)paths);
/*    */     
/* 63 */     Vector xpaths = paths.getElements();
/*    */     
/* 65 */     for (int i = 0; i < xpaths.size(); i++) {
/*    */       
/* 67 */       WhiteSpaceInfo wsi = new WhiteSpaceInfo(xpaths.elementAt(i), true, thisSheet);
/* 68 */       wsi.setUid(handler.nextUid());
/*    */       
/* 70 */       thisSheet.setStripSpaces(wsi);
/*    */     } 
/* 72 */     paths.clearElements();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorStripSpace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */