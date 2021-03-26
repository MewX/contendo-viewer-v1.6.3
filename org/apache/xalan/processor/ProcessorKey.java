/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.KeyDeclaration;
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
/*     */ class ProcessorKey
/*     */   extends XSLTElementProcessor
/*     */ {
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/*  66 */     KeyDeclaration kd = new KeyDeclaration(handler.getStylesheet(), handler.nextUid());
/*     */     
/*  68 */     kd.setDOMBackPointer(handler.getOriginatingNode());
/*  69 */     kd.setLocaterInfo((SourceLocator)handler.getLocator());
/*  70 */     setPropertiesFromAttributes(handler, rawName, attributes, (ElemTemplateElement)kd);
/*  71 */     handler.getStylesheet().setKey(kd);
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
/*     */   void setPropertiesFromAttributes(StylesheetHandler handler, String rawName, Attributes attributes, ElemTemplateElement target) throws SAXException {
/*  89 */     XSLTElementDef def = getElemDef();
/*     */ 
/*     */ 
/*     */     
/*  93 */     Vector processedDefs = new Vector();
/*  94 */     int nAttrs = attributes.getLength();
/*     */     
/*  96 */     for (int i = 0; i < nAttrs; i++) {
/*     */       
/*  98 */       String attrUri = attributes.getURI(i);
/*  99 */       String attrLocalName = attributes.getLocalName(i);
/* 100 */       XSLTAttributeDef attrDef = def.getAttributeDef(attrUri, attrLocalName);
/*     */       
/* 102 */       if (null == attrDef) {
/*     */ 
/*     */ 
/*     */         
/* 106 */         handler.error(attributes.getQName(i) + "attribute is not allowed on the " + rawName + " element!", null);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 112 */         String valueString = attributes.getValue(i);
/*     */         
/* 114 */         if (valueString.indexOf("key(") >= 0)
/*     */         {
/* 116 */           handler.error(XSLMessages.createMessage("ER_INVALID_KEY_CALL", null), null);
/*     */         }
/*     */ 
/*     */         
/* 120 */         processedDefs.addElement(attrDef);
/* 121 */         attrDef.setAttrValue(handler, attrUri, attrLocalName, attributes.getQName(i), attributes.getValue(i), target);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 127 */     XSLTAttributeDef[] attrDefs = def.getAttributes();
/* 128 */     int nAttrDefs = attrDefs.length;
/*     */     
/* 130 */     for (int j = 0; j < nAttrDefs; j++) {
/*     */       
/* 132 */       XSLTAttributeDef attrDef = attrDefs[j];
/* 133 */       String defVal = attrDef.getDefault();
/*     */       
/* 135 */       if (null != defVal)
/*     */       {
/* 137 */         if (!processedDefs.contains(attrDef))
/*     */         {
/* 139 */           attrDef.setDefAttrValue(handler, target);
/*     */         }
/*     */       }
/*     */       
/* 143 */       if (attrDef.getRequired())
/*     */       {
/* 145 */         if (!processedDefs.contains(attrDef))
/* 146 */           handler.error(XSLMessages.createMessage("ER_REQUIRES_ATTRIB", new Object[] { rawName, attrDef.getName() }), null); 
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorKey.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */