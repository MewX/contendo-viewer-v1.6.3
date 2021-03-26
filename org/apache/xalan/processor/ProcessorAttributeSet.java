/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemAttributeSet;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ProcessorAttributeSet
/*     */   extends XSLTElementProcessor
/*     */ {
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/*  62 */     ElemAttributeSet eat = new ElemAttributeSet();
/*     */     
/*  64 */     eat.setLocaterInfo((SourceLocator)handler.getLocator());
/*     */ 
/*     */     
/*  67 */     try { eat.setPrefixes(handler.getNamespaceSupport()); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/*  71 */       throw new SAXException(te); }
/*     */ 
/*     */     
/*  74 */     eat.setDOMBackPointer(handler.getOriginatingNode());
/*  75 */     setPropertiesFromAttributes(handler, rawName, attributes, (ElemTemplateElement)eat);
/*  76 */     handler.getStylesheet().setAttributeSet(eat);
/*     */ 
/*     */     
/*  79 */     ElemTemplateElement parent = handler.getElemTemplateElement();
/*     */     
/*  81 */     parent.appendChild((ElemTemplateElement)eat);
/*  82 */     handler.pushElemTemplateElement((ElemTemplateElement)eat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(StylesheetHandler handler, String uri, String localName, String rawName) throws SAXException {
/* 100 */     handler.popElemTemplateElement();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorAttributeSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */