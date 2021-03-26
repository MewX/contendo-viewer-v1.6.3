/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xml.utils.IntStack;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
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
/*     */ public class XSLTElementProcessor
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   private IntStack m_savedLastOrder;
/*     */   private XSLTElementDef m_elemDef;
/*     */   
/*     */   XSLTElementDef getElemDef() {
/*  60 */     return this.m_elemDef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setElemDef(XSLTElementDef def) {
/*  70 */     this.m_elemDef = def;
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
/*     */   public InputSource resolveEntity(StylesheetHandler handler, String publicId, String systemId) throws SAXException {
/*  89 */     return null;
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
/*     */   public void notationDecl(StylesheetHandler handler, String name, String publicId, String systemId) {}
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
/*     */   public void unparsedEntityDecl(StylesheetHandler handler, String name, String publicId, String systemId, String notationName) {}
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
/*     */   public void startNonText(StylesheetHandler handler) throws SAXException {}
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
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 158 */     if (this.m_savedLastOrder == null)
/* 159 */       this.m_savedLastOrder = new IntStack(); 
/* 160 */     this.m_savedLastOrder.push(getElemDef().getLastOrder());
/* 161 */     getElemDef().setLastOrder(-1);
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
/* 179 */     if (this.m_savedLastOrder != null && !this.m_savedLastOrder.empty()) {
/* 180 */       getElemDef().setLastOrder(this.m_savedLastOrder.pop());
/*     */     }
/* 182 */     if (!getElemDef().getRequiredFound()) {
/* 183 */       handler.error("ER_REQUIRED_ELEM_NOT_FOUND", new Object[] { getElemDef().getRequiredElem() }, null);
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
/*     */   public void characters(StylesheetHandler handler, char[] ch, int start, int length) throws SAXException {
/* 200 */     handler.error("ER_CHARS_NOT_ALLOWED", null, null);
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
/*     */   public void ignorableWhitespace(StylesheetHandler handler, char[] ch, int start, int length) throws SAXException {}
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
/*     */   public void processingInstruction(StylesheetHandler handler, String target, String data) throws SAXException {}
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
/*     */   public void skippedEntity(StylesheetHandler handler, String name) throws SAXException {}
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
/*     */   void setPropertiesFromAttributes(StylesheetHandler handler, String rawName, Attributes attributes, ElemTemplateElement target) throws SAXException {
/* 267 */     setPropertiesFromAttributes(handler, rawName, attributes, target, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Attributes setPropertiesFromAttributes(StylesheetHandler handler, String rawName, Attributes attributes, ElemTemplateElement target, boolean throwError) throws SAXException {
/* 290 */     XSLTElementDef def = getElemDef();
/* 291 */     AttributesImpl undefines = null;
/* 292 */     boolean isCompatibleMode = ((null != handler.getStylesheet() && handler.getStylesheet().getCompatibleMode()) || !throwError);
/*     */ 
/*     */     
/* 295 */     if (isCompatibleMode) {
/* 296 */       undefines = new AttributesImpl();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 301 */     Vector processedDefs = new Vector();
/*     */ 
/*     */     
/* 304 */     Vector errorDefs = new Vector();
/* 305 */     int nAttrs = attributes.getLength();
/*     */     
/* 307 */     for (int i = 0; i < nAttrs; i++) {
/*     */       
/* 309 */       String attrUri = attributes.getURI(i);
/*     */       
/* 311 */       if (null != attrUri && attrUri.length() == 0 && (attributes.getQName(i).startsWith("xmlns:") || attributes.getQName(i).equals("xmlns")))
/*     */       {
/*     */ 
/*     */         
/* 315 */         attrUri = "http://www.w3.org/XML/1998/namespace";
/*     */       }
/* 317 */       String attrLocalName = attributes.getLocalName(i);
/* 318 */       XSLTAttributeDef attrDef = def.getAttributeDef(attrUri, attrLocalName);
/*     */       
/* 320 */       if (null == attrDef) {
/*     */         
/* 322 */         if (!isCompatibleMode)
/*     */         {
/*     */ 
/*     */           
/* 326 */           handler.error("ER_ATTR_NOT_ALLOWED", new Object[] { attributes.getQName(i), rawName }, null);
/*     */         
/*     */         }
/*     */         else
/*     */         {
/*     */           
/* 332 */           undefines.addAttribute(attrUri, attrLocalName, attributes.getQName(i), attributes.getType(i), attributes.getValue(i));
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 342 */         boolean success = attrDef.setAttrValue(handler, attrUri, attrLocalName, attributes.getQName(i), attributes.getValue(i), target);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 347 */         if (success) {
/* 348 */           processedDefs.addElement(attrDef);
/*     */         } else {
/* 350 */           errorDefs.addElement(attrDef);
/*     */         } 
/*     */       } 
/*     */     } 
/* 354 */     XSLTAttributeDef[] attrDefs = def.getAttributes();
/* 355 */     int nAttrDefs = attrDefs.length;
/*     */     
/* 357 */     for (int j = 0; j < nAttrDefs; j++) {
/*     */       
/* 359 */       XSLTAttributeDef attrDef = attrDefs[j];
/* 360 */       String defVal = attrDef.getDefault();
/*     */       
/* 362 */       if (null != defVal)
/*     */       {
/* 364 */         if (!processedDefs.contains(attrDef))
/*     */         {
/* 366 */           attrDef.setDefAttrValue(handler, target);
/*     */         }
/*     */       }
/*     */       
/* 370 */       if (attrDef.getRequired())
/*     */       {
/* 372 */         if (!processedDefs.contains(attrDef) && !errorDefs.contains(attrDef)) {
/* 373 */           handler.error(XSLMessages.createMessage("ER_REQUIRES_ATTRIB", new Object[] { rawName, attrDef.getName() }), null);
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 380 */     return undefines;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/XSLTElementProcessor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */