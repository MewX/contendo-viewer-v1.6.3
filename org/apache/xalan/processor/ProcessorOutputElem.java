/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.OutputProperties;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xml.utils.SystemIDResolver;
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
/*     */ class ProcessorOutputElem
/*     */   extends XSLTElementProcessor
/*     */ {
/*     */   private OutputProperties m_outputProperties;
/*     */   
/*     */   public void setCdataSectionElements(Vector newValue) {
/*  51 */     this.m_outputProperties.setQNameProperties("cdata-section-elements", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoctypePublic(String newValue) {
/*  61 */     this.m_outputProperties.setProperty("doctype-public", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoctypeSystem(String newValue) {
/*  71 */     this.m_outputProperties.setProperty("doctype-system", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(String newValue) {
/*  81 */     this.m_outputProperties.setProperty("encoding", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndent(boolean newValue) {
/*  91 */     this.m_outputProperties.setBooleanProperty("indent", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMediaType(String newValue) {
/* 101 */     this.m_outputProperties.setProperty("media-type", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMethod(QName newValue) {
/* 111 */     this.m_outputProperties.setQNameProperty("method", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOmitXmlDeclaration(boolean newValue) {
/* 121 */     this.m_outputProperties.setBooleanProperty("omit-xml-declaration", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStandalone(boolean newValue) {
/* 131 */     this.m_outputProperties.setBooleanProperty("standalone", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(String newValue) {
/* 141 */     this.m_outputProperties.setProperty("version", newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForeignAttr(String attrUri, String attrLocalName, String attrRawName, String attrValue) {
/* 150 */     QName key = new QName(attrUri, attrLocalName);
/* 151 */     this.m_outputProperties.setProperty(key, attrValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLiteralResultAttribute(String attrUri, String attrLocalName, String attrRawName, String attrValue) {
/* 160 */     QName key = new QName(attrUri, attrLocalName);
/* 161 */     this.m_outputProperties.setProperty(key, attrValue);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/* 187 */     this.m_outputProperties = new OutputProperties();
/*     */     
/* 189 */     this.m_outputProperties.setDOMBackPointer(handler.getOriginatingNode());
/* 190 */     this.m_outputProperties.setLocaterInfo((SourceLocator)handler.getLocator());
/* 191 */     this.m_outputProperties.setUid(handler.nextUid());
/* 192 */     setPropertiesFromAttributes(handler, rawName, attributes, this);
/*     */ 
/*     */ 
/*     */     
/* 196 */     String entitiesFileName = (String)this.m_outputProperties.getProperties().get("{http://xml.apache.org/xalan}entities");
/*     */ 
/*     */     
/* 199 */     if (null != entitiesFileName) {
/*     */ 
/*     */       
/*     */       try { 
/* 203 */         String absURL = SystemIDResolver.getAbsoluteURI(entitiesFileName, handler.getBaseIdentifier());
/*     */         
/* 205 */         this.m_outputProperties.getProperties().put("{http://xml.apache.org/xalan}entities", absURL); } catch (TransformerException te)
/*     */       
/*     */       { 
/*     */         
/* 209 */         handler.error(te.getMessage(), te); }
/*     */     
/*     */     }
/*     */     
/* 213 */     handler.getStylesheet().setOutput(this.m_outputProperties);
/*     */     
/* 215 */     ElemTemplateElement parent = handler.getElemTemplateElement();
/* 216 */     parent.appendChild((ElemTemplateElement)this.m_outputProperties);
/*     */     
/* 218 */     this.m_outputProperties = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorOutputElem.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */