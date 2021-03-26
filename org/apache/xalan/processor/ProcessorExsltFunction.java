/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import org.apache.xalan.templates.ElemTemplate;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xml.utils.SAXSourceLocator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProcessorExsltFunction
/*     */   extends ProcessorTemplateElem
/*     */ {
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/*  66 */     String msg = "";
/*  67 */     if (!(handler.getElemTemplateElement() instanceof org.apache.xalan.templates.Stylesheet)) {
/*     */       
/*  69 */       msg = "func:function element must be top level.";
/*  70 */       handler.error(msg, new SAXException(msg));
/*     */     } 
/*  72 */     super.startElement(handler, uri, localName, rawName, attributes);
/*     */     
/*  74 */     String val = attributes.getValue("name");
/*  75 */     int indexOfColon = val.indexOf(":");
/*  76 */     if (indexOfColon > 0) {
/*     */       
/*  78 */       String prefix = val.substring(0, indexOfColon);
/*  79 */       String localVal = val.substring(indexOfColon + 1);
/*  80 */       String str1 = handler.getNamespaceSupport().getURI(prefix);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  86 */       msg = "func:function name must have namespace";
/*  87 */       handler.error(msg, new SAXException(msg));
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
/*     */   protected void appendAndPush(StylesheetHandler handler, ElemTemplateElement elem) throws SAXException {
/*  99 */     super.appendAndPush(handler, elem);
/*     */     
/* 101 */     elem.setDOMBackPointer(handler.getOriginatingNode());
/* 102 */     handler.getStylesheet().setTemplate((ElemTemplate)elem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(StylesheetHandler handler, String uri, String localName, String rawName) throws SAXException {
/* 112 */     ElemTemplateElement function = handler.getElemTemplateElement();
/* 113 */     SAXSourceLocator sAXSourceLocator = handler.getLocator();
/* 114 */     validate(function, handler);
/* 115 */     super.endElement(handler, uri, localName, rawName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validate(ElemTemplateElement elem, StylesheetHandler handler) throws SAXException {
/* 126 */     String msg = "";
/* 127 */     while (elem != null) {
/*     */ 
/*     */       
/* 130 */       if (elem instanceof org.apache.xalan.templates.ElemExsltFuncResult && elem.getNextSiblingElem() != null && !(elem.getNextSiblingElem() instanceof org.apache.xalan.templates.ElemFallback)) {
/*     */ 
/*     */ 
/*     */         
/* 134 */         msg = "func:result has an illegal following sibling (only xsl:fallback allowed)";
/* 135 */         handler.error(msg, new SAXException(msg));
/*     */       } 
/*     */       
/* 138 */       if ((elem instanceof org.apache.xalan.templates.ElemApplyImport || elem instanceof org.apache.xalan.templates.ElemApplyTemplates || elem instanceof org.apache.xalan.templates.ElemAttribute || elem instanceof org.apache.xalan.templates.ElemCallTemplate || elem instanceof org.apache.xalan.templates.ElemComment || elem instanceof org.apache.xalan.templates.ElemCopy || elem instanceof org.apache.xalan.templates.ElemCopyOf || elem instanceof org.apache.xalan.templates.ElemElement || elem instanceof org.apache.xalan.templates.ElemLiteralResult || elem instanceof org.apache.xalan.templates.ElemNumber || elem instanceof org.apache.xalan.templates.ElemPI || elem instanceof org.apache.xalan.templates.ElemText || elem instanceof org.apache.xalan.templates.ElemTextLiteral || elem instanceof org.apache.xalan.templates.ElemValueOf) && !ancestorIsOk(elem)) {
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
/* 154 */         msg = "misplaced literal result in a func:function container.";
/* 155 */         handler.error(msg, new SAXException(msg));
/*     */       } 
/* 157 */       ElemTemplateElement nextElem = elem.getFirstChildElem();
/* 158 */       while (nextElem == null) {
/*     */         
/* 160 */         nextElem = elem.getNextSiblingElem();
/* 161 */         if (nextElem == null)
/* 162 */           elem = elem.getParentElem(); 
/* 163 */         if (elem == null || elem instanceof org.apache.xalan.templates.ElemExsltFunction)
/*     */           return; 
/*     */       } 
/* 166 */       elem = nextElem;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean ancestorIsOk(ElemTemplateElement child) {
/* 177 */     while (child.getParentElem() != null && !(child.getParentElem() instanceof org.apache.xalan.templates.ElemExsltFunction)) {
/*     */       
/* 179 */       ElemTemplateElement parent = child.getParentElem();
/* 180 */       if (parent instanceof org.apache.xalan.templates.ElemExsltFuncResult || parent instanceof org.apache.xalan.templates.ElemVariable || parent instanceof org.apache.xalan.templates.ElemParam || parent instanceof org.apache.xalan.templates.ElemMessage)
/*     */       {
/*     */ 
/*     */         
/* 184 */         return true; } 
/* 185 */       child = parent;
/*     */     } 
/* 187 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorExsltFunction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */