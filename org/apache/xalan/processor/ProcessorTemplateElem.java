/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
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
/*     */ public class ProcessorTemplateElem
/*     */   extends XSLTElementProcessor
/*     */ {
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/*  52 */     super.startElement(handler, uri, localName, rawName, attributes);
/*     */ 
/*     */ 
/*     */     
/*  56 */     try { XSLTElementDef def = getElemDef();
/*  57 */       Class classObject = def.getClassObject();
/*  58 */       ElemTemplateElement elem = null;
/*     */ 
/*     */ 
/*     */       
/*  62 */       try { elem = classObject.newInstance();
/*     */         
/*  64 */         elem.setDOMBackPointer(handler.getOriginatingNode());
/*  65 */         elem.setLocaterInfo((SourceLocator)handler.getLocator());
/*  66 */         elem.setPrefixes(handler.getNamespaceSupport()); } catch (InstantiationException ie)
/*     */       
/*     */       { 
/*     */         
/*  70 */         handler.error("ER_FAILED_CREATING_ELEMTMPL", null, ie); } catch (IllegalAccessException iae)
/*     */       
/*     */       { 
/*     */         
/*  74 */         handler.error("ER_FAILED_CREATING_ELEMTMPL", null, iae); }
/*     */ 
/*     */       
/*  77 */       setPropertiesFromAttributes(handler, rawName, attributes, elem);
/*  78 */       appendAndPush(handler, elem); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/*  82 */       throw new SAXException(te); }
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
/*     */   
/*     */   protected void appendAndPush(StylesheetHandler handler, ElemTemplateElement elem) throws SAXException {
/* 102 */     ElemTemplateElement parent = handler.getElemTemplateElement();
/* 103 */     if (null != parent) {
/*     */       
/* 105 */       parent.appendChild(elem);
/* 106 */       handler.pushElemTemplateElement(elem);
/*     */     } 
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
/* 125 */     super.endElement(handler, uri, localName, rawName);
/* 126 */     handler.popElemTemplateElement().setEndLocaterInfo((SourceLocator)handler.getLocator());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorTemplateElem.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */