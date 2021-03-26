/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.Stylesheet;
/*     */ import org.apache.xalan.templates.StylesheetComposed;
/*     */ import org.apache.xalan.templates.StylesheetRoot;
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
/*     */ class ProcessorStylesheetElement
/*     */   extends XSLTElementProcessor
/*     */ {
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/*  59 */     super.startElement(handler, uri, localName, rawName, attributes);
/*     */     
/*     */     try { Stylesheet stylesheet;
/*  62 */       int stylesheetType = handler.getStylesheetType();
/*     */ 
/*     */       
/*  65 */       if (stylesheetType == 1) {
/*     */ 
/*     */ 
/*     */         
/*  69 */         try { StylesheetRoot stylesheetRoot = new StylesheetRoot(handler.getSchema(), handler.getStylesheetProcessor().getErrorListener()); } catch (TransformerConfigurationException tfe)
/*     */         
/*     */         { 
/*     */           
/*  73 */           throw new TransformerException(tfe); }
/*     */ 
/*     */       
/*     */       } else {
/*     */         
/*  78 */         Stylesheet parent = handler.getStylesheet();
/*     */         
/*  80 */         if (stylesheetType == 3) {
/*     */           
/*  82 */           StylesheetComposed sc = new StylesheetComposed(parent);
/*     */           
/*  84 */           parent.setImport(sc);
/*     */           
/*  86 */           StylesheetComposed stylesheetComposed1 = sc;
/*     */         }
/*     */         else {
/*     */           
/*  90 */           stylesheet = new Stylesheet(parent);
/*     */           
/*  92 */           parent.setInclude(stylesheet);
/*     */         } 
/*     */       } 
/*     */       
/*  96 */       stylesheet.setDOMBackPointer(handler.getOriginatingNode());
/*  97 */       stylesheet.setLocaterInfo((SourceLocator)handler.getLocator());
/*     */       
/*  99 */       stylesheet.setPrefixes(handler.getNamespaceSupport());
/* 100 */       handler.pushStylesheet(stylesheet);
/* 101 */       setPropertiesFromAttributes(handler, rawName, attributes, (ElemTemplateElement)handler.getStylesheet());
/*     */       
/* 103 */       handler.pushElemTemplateElement((ElemTemplateElement)handler.getStylesheet()); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/* 107 */       throw new SAXException(te); }
/*     */   
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
/* 126 */     super.endElement(handler, uri, localName, rawName);
/* 127 */     handler.popElemTemplateElement();
/* 128 */     handler.popStylesheet();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorStylesheetElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */