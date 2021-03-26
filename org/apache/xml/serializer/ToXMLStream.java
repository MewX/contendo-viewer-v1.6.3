/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.res.XMLMessages;
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
/*     */ public class ToXMLStream
/*     */   extends ToStream
/*     */ {
/*     */   boolean m_cdataTagOpen = false;
/*  49 */   protected static CharInfo m_xmlcharInfo = CharInfo.getCharInfo(CharInfo.XML_ENTITIES_RESOURCE, "xml");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ToXMLStream() {
/*  58 */     this.m_charInfo = m_xmlcharInfo;
/*     */     
/*  60 */     initCDATA();
/*     */     
/*  62 */     this.m_prefixMap = new NamespaceMappings();
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
/*     */   public void CopyFrom(ToXMLStream xmlListener) {
/*  74 */     this.m_writer = xmlListener.m_writer;
/*     */ 
/*     */ 
/*     */     
/*  78 */     String encoding = xmlListener.getEncoding();
/*  79 */     setEncoding(encoding);
/*     */     
/*  81 */     setOmitXMLDeclaration(xmlListener.getOmitXMLDeclaration());
/*     */     
/*  83 */     this.m_ispreserve = xmlListener.m_ispreserve;
/*  84 */     this.m_preserves = xmlListener.m_preserves;
/*  85 */     this.m_isprevtext = xmlListener.m_isprevtext;
/*  86 */     this.m_doIndent = xmlListener.m_doIndent;
/*  87 */     setIndentAmount(xmlListener.getIndentAmount());
/*  88 */     this.m_startNewLine = xmlListener.m_startNewLine;
/*  89 */     this.m_needToOutputDocTypeDecl = xmlListener.m_needToOutputDocTypeDecl;
/*  90 */     setDoctypeSystem(xmlListener.getDoctypeSystem());
/*  91 */     setDoctypePublic(xmlListener.getDoctypePublic());
/*  92 */     setStandalone(xmlListener.getStandalone());
/*  93 */     setMediaType(xmlListener.getMediaType());
/*  94 */     this.m_maxCharacter = xmlListener.m_maxCharacter;
/*  95 */     this.m_spaceBeforeClose = xmlListener.m_spaceBeforeClose;
/*  96 */     this.m_cdataStartCalled = xmlListener.m_cdataStartCalled;
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
/*     */   public void startDocumentInternal() throws SAXException {
/* 111 */     if (this.m_needToCallStartDocument) {
/*     */       
/* 113 */       super.startDocumentInternal();
/* 114 */       this.m_needToCallStartDocument = false;
/*     */       
/* 116 */       if (this.m_inEntityRef) {
/*     */         return;
/*     */       }
/* 119 */       this.m_needToOutputDocTypeDecl = true;
/* 120 */       this.m_startNewLine = false;
/*     */       
/* 122 */       if (!getOmitXMLDeclaration()) {
/*     */         
/* 124 */         String str1, encoding = Encodings.getMimeEncoding(getEncoding());
/* 125 */         String version = getVersion();
/* 126 */         if (version == null) {
/* 127 */           version = "1.0";
/*     */         }
/*     */         
/* 130 */         if (this.m_standaloneWasSpecified) {
/*     */           
/* 132 */           str1 = " standalone=\"" + getStandalone() + "\"";
/*     */         }
/*     */         else {
/*     */           
/* 136 */           str1 = "";
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 141 */         try { Writer writer = this.m_writer;
/* 142 */           writer.write("<?xml version=\"");
/* 143 */           writer.write(version);
/* 144 */           writer.write("\" encoding=\"");
/* 145 */           writer.write(encoding);
/* 146 */           writer.write(34);
/* 147 */           writer.write(str1);
/* 148 */           writer.write("?>");
/* 149 */           if (this.m_doIndent)
/* 150 */             writer.write(this.m_lineSep, 0, this.m_lineSepLen);  } catch (IOException e)
/*     */         
/*     */         { 
/*     */           
/* 154 */           throw new SAXException(e); }
/*     */       
/*     */       } 
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
/*     */   public void endDocument() throws SAXException {
/* 171 */     flushPending();
/* 172 */     if (this.m_doIndent && !this.m_isprevtext) {
/*     */ 
/*     */       
/*     */       try { 
/* 176 */         outputLineSep(); } catch (IOException e)
/*     */       
/*     */       { 
/*     */         
/* 180 */         throw new SAXException(e); }
/*     */     
/*     */     }
/*     */     
/* 184 */     flushWriter();
/*     */     
/* 186 */     if (this.m_tracer != null) {
/* 187 */       fireEndDoc();
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
/*     */   public void startPreserving() throws SAXException {
/* 206 */     this.m_preserves.push(true);
/*     */     
/* 208 */     this.m_ispreserve = true;
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
/*     */   public void endPreserving() throws SAXException {
/* 222 */     this.m_ispreserve = this.m_preserves.isEmpty() ? false : this.m_preserves.pop();
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
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 239 */     if (this.m_inEntityRef) {
/*     */       return;
/*     */     }
/* 242 */     flushPending();
/*     */     
/* 244 */     if (target.equals("javax.xml.transform.disable-output-escaping")) {
/*     */       
/* 246 */       startNonEscaping();
/*     */     }
/* 248 */     else if (target.equals("javax.xml.transform.enable-output-escaping")) {
/*     */       
/* 250 */       endNonEscaping();
/*     */     } else {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/* 256 */         if (this.m_elemContext.m_startTagOpen) {
/*     */           
/* 258 */           closeStartTag();
/* 259 */           this.m_elemContext.m_startTagOpen = false;
/*     */         } 
/*     */         
/* 262 */         if (shouldIndent()) {
/* 263 */           indent();
/*     */         }
/* 265 */         Writer writer = this.m_writer;
/* 266 */         writer.write("<?");
/* 267 */         writer.write(target);
/*     */         
/* 269 */         if (data.length() > 0 && !Character.isSpaceChar(data.charAt(0)))
/*     */         {
/* 271 */           writer.write(32);
/*     */         }
/* 273 */         int indexOfQLT = data.indexOf("?>");
/*     */         
/* 275 */         if (indexOfQLT >= 0) {
/*     */ 
/*     */ 
/*     */           
/* 279 */           if (indexOfQLT > 0)
/*     */           {
/* 281 */             writer.write(data.substring(0, indexOfQLT));
/*     */           }
/*     */           
/* 284 */           writer.write("? >");
/*     */           
/* 286 */           if (indexOfQLT + 2 < data.length())
/*     */           {
/* 288 */             writer.write(data.substring(indexOfQLT + 2));
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 293 */           writer.write(data);
/*     */         } 
/*     */         
/* 296 */         writer.write(63);
/* 297 */         writer.write(62);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 302 */         if (this.m_elemContext.m_currentElemDepth <= 0) {
/* 303 */           writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*     */         }
/* 305 */         this.m_startNewLine = true; } catch (IOException e)
/*     */       
/*     */       { 
/*     */         
/* 309 */         throw new SAXException(e); }
/*     */     
/*     */     } 
/*     */     
/* 313 */     if (this.m_tracer != null) {
/* 314 */       fireEscapingEvent(target, data);
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
/*     */   public void entityReference(String name) throws SAXException {
/* 326 */     if (this.m_elemContext.m_startTagOpen) {
/*     */       
/* 328 */       closeStartTag();
/* 329 */       this.m_elemContext.m_startTagOpen = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 334 */     try { if (shouldIndent()) {
/* 335 */         indent();
/*     */       }
/* 337 */       Writer writer = this.m_writer;
/* 338 */       writer.write(38);
/* 339 */       writer.write(name);
/* 340 */       writer.write(59); } catch (IOException e)
/*     */     
/*     */     { 
/*     */       
/* 344 */       throw new SAXException(e); }
/*     */ 
/*     */     
/* 347 */     if (this.m_tracer != null) {
/* 348 */       fireEntityReference(name);
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
/*     */   public void addUniqueAttribute(String name, String value, int flags) throws SAXException {
/* 364 */     if (this.m_elemContext.m_startTagOpen) {
/*     */ 
/*     */       
/*     */       try { 
/*     */         
/* 369 */         String patchedName = patchName(name);
/* 370 */         Writer writer = this.m_writer;
/* 371 */         if ((flags & 0x1) > 0 && m_xmlcharInfo.onlyQuotAmpLtGt)
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 379 */           writer.write(32);
/* 380 */           writer.write(patchedName);
/* 381 */           writer.write("=\"");
/* 382 */           writer.write(value);
/* 383 */           writer.write(34); }
/*     */         
/*     */         else
/*     */         
/* 387 */         { writer.write(32);
/* 388 */           writer.write(patchedName);
/* 389 */           writer.write("=\"");
/* 390 */           writeAttrString(writer, value, getEncoding());
/* 391 */           writer.write(34); }  } catch (IOException e)
/*     */       
/*     */       { 
/* 394 */         throw new SAXException(e); }
/*     */     
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
/*     */   public void addAttribute(String uri, String localName, String rawName, String type, String value) throws SAXException {
/* 407 */     if (this.m_elemContext.m_startTagOpen) {
/*     */       
/* 409 */       if (!rawName.startsWith("xmlns")) {
/*     */         
/* 411 */         String prefixUsed = ensureAttributesNamespaceIsDeclared(uri, localName, rawName);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 416 */         if (prefixUsed != null && rawName != null && !rawName.startsWith(prefixUsed))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 422 */           rawName = prefixUsed + ":" + localName;
/*     */         }
/*     */       } 
/*     */       
/* 426 */       addAttributeAlways(uri, localName, rawName, type, value);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 444 */       String msg = XMLMessages.createXMLMessage("ER_ILLEGAL_ATTRIBUTE_POSITION", new Object[] { localName });
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 449 */       try { Transformer tran = getTransformer();
/* 450 */         ErrorListener errHandler = tran.getErrorListener();
/*     */ 
/*     */ 
/*     */         
/* 454 */         if (null != errHandler && this.m_sourceLocator != null)
/* 455 */         { errHandler.warning(new TransformerException(msg, this.m_sourceLocator)); }
/*     */         else
/* 457 */         { System.out.println(msg); }  } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String elemName) throws SAXException {
/* 468 */     endElement(null, null, elemName);
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
/*     */   public void namespaceAfterStartElement(String prefix, String uri) throws SAXException {
/* 482 */     if (this.m_elemContext.m_elementURI == null) {
/*     */       
/* 484 */       String prefix1 = SerializerBase.getPrefixPart(this.m_elemContext.m_elementName);
/* 485 */       if (prefix1 == null && "".equals(prefix))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 491 */         this.m_elemContext.m_elementURI = uri;
/*     */       }
/*     */     } 
/* 494 */     startPrefixMapping(prefix, uri, false);
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
/*     */   protected boolean pushNamespace(String prefix, String uri) {
/*     */     
/* 508 */     try { if (this.m_prefixMap.pushNamespace(prefix, uri, this.m_elemContext.m_currentElemDepth))
/*     */       
/*     */       { 
/* 511 */         startPrefixMapping(prefix, uri);
/* 512 */         return true; }  } catch (SAXException sAXException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 519 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset() {
/* 530 */     boolean wasReset = false;
/* 531 */     if (super.reset()) {
/*     */       
/* 533 */       resetToXMLStream();
/* 534 */       wasReset = true;
/*     */     } 
/* 536 */     return wasReset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetToXMLStream() {
/* 545 */     this.m_cdataTagOpen = false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToXMLStream.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */