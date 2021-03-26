/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.ElemText;
/*     */ import org.apache.xalan.templates.ElemTextLiteral;
/*     */ import org.apache.xml.utils.XMLCharacterRecognizer;
/*     */ import org.w3c.dom.Node;
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
/*     */ public class ProcessorCharacters
/*     */   extends XSLTElementProcessor
/*     */ {
/*     */   public void startNonText(StylesheetHandler handler) throws SAXException {
/*  46 */     if (this == handler.getCurrentProcessor())
/*     */     {
/*  48 */       handler.popProcessor();
/*     */     }
/*     */     
/*  51 */     int nChars = this.m_accumulator.length();
/*     */     
/*  53 */     if ((nChars > 0 && (null != this.m_xslTextElement || !XMLCharacterRecognizer.isWhiteSpace(this.m_accumulator))) || handler.isSpacePreserve()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  58 */       ElemTextLiteral elem = new ElemTextLiteral();
/*     */       
/*  60 */       elem.setDOMBackPointer(this.m_firstBackPointer);
/*  61 */       elem.setLocaterInfo((SourceLocator)handler.getLocator());
/*     */ 
/*     */       
/*  64 */       try { elem.setPrefixes(handler.getNamespaceSupport()); } catch (TransformerException te)
/*     */       
/*     */       { 
/*     */         
/*  68 */         throw new SAXException(te); }
/*     */ 
/*     */       
/*  71 */       boolean doe = (null != this.m_xslTextElement) ? this.m_xslTextElement.getDisableOutputEscaping() : false;
/*     */ 
/*     */       
/*  74 */       elem.setDisableOutputEscaping(doe);
/*  75 */       elem.setPreserveSpace(true);
/*     */       
/*  77 */       char[] chars = new char[nChars];
/*     */       
/*  79 */       this.m_accumulator.getChars(0, nChars, chars, 0);
/*  80 */       elem.setChars(chars);
/*     */       
/*  82 */       ElemTemplateElement parent = handler.getElemTemplateElement();
/*     */       
/*  84 */       parent.appendChild((ElemTemplateElement)elem);
/*     */     } 
/*     */     
/*  87 */     this.m_accumulator.setLength(0);
/*  88 */     this.m_firstBackPointer = null;
/*     */   }
/*     */   
/*  91 */   protected Node m_firstBackPointer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 111 */     this.m_accumulator.append(ch, start, length);
/*     */     
/* 113 */     if (null == this.m_firstBackPointer) {
/* 114 */       this.m_firstBackPointer = handler.getOriginatingNode();
/*     */     }
/*     */     
/* 117 */     if (this != handler.getCurrentProcessor()) {
/* 118 */       handler.pushProcessor(this);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 150 */     startNonText(handler);
/* 151 */     handler.getCurrentProcessor().endElement(handler, uri, localName, rawName);
/*     */     
/* 153 */     handler.popProcessor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   private StringBuffer m_accumulator = new StringBuffer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ElemText m_xslTextElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setXslTextElement(ElemText xslTextElement) {
/* 177 */     this.m_xslTextElement = xslTextElement;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorCharacters.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */