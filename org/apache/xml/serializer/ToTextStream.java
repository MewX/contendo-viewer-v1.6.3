/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.apache.xml.res.XMLMessages;
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
/*     */ public class ToTextStream
/*     */   extends ToStream
/*     */ {
/*     */   protected void startDocumentInternal() throws SAXException {
/*  59 */     super.startDocumentInternal();
/*     */     
/*  61 */     this.m_needToCallStartDocument = false;
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
/*     */   public void endDocument() throws SAXException {
/*  82 */     flushPending();
/*  83 */     flushWriter();
/*  84 */     if (this.m_tracer != null) {
/*  85 */       fireEndDoc();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String name, Attributes atts) throws SAXException {
/* 125 */     if (this.m_tracer != null) {
/* 126 */       fireStartElem(name);
/* 127 */       firePseudoAttributes();
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
/*     */ 
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String name) throws SAXException {
/* 161 */     if (this.m_tracer != null) {
/* 162 */       fireEndElem(name);
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
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 195 */     flushPending();
/*     */ 
/*     */ 
/*     */     
/* 199 */     try { writeNormalizedChars(ch, start, length, false, this.m_lineSepUse);
/* 200 */       if (this.m_tracer != null)
/* 201 */         fireCharEvent(ch, start, length);  } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 205 */       throw new SAXException(ioe); }
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
/*     */   public void charactersRaw(char[] ch, int start, int length) throws SAXException {
/*     */     
/* 226 */     try { writeNormalizedChars(ch, start, length, false, this.m_lineSepUse); } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 230 */       throw new SAXException(ioe); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void writeNormalizedChars(char[] ch, int start, int length, boolean isCData, boolean useLineSep) throws IOException, SAXException {
/* 257 */     Writer writer = this.m_writer;
/* 258 */     int end = start + length;
/*     */ 
/*     */     
/* 261 */     char S_LINEFEED = '\n';
/* 262 */     int M_MAXCHARACTER = this.m_maxCharacter;
/*     */     
/* 264 */     if (isCData) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 269 */       for (int i = start; i < end; i++)
/*     */       {
/* 271 */         char c = ch[i];
/*     */         
/* 273 */         if ('\n' == c && useLineSep) {
/*     */           
/* 275 */           writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*     */         }
/* 277 */         else if (c > M_MAXCHARACTER) {
/*     */           
/* 279 */           if (i != 0) {
/* 280 */             closeCDATA();
/*     */           }
/*     */           
/* 283 */           if (ToStream.isUTF16Surrogate(c)) {
/*     */             
/* 285 */             writeUTF16Surrogate(c, ch, i, end);
/* 286 */             i++;
/*     */           }
/*     */           else {
/*     */             
/* 290 */             writer.write(c);
/*     */           } 
/*     */           
/* 293 */           if (i != 0 && i < end - 1)
/*     */           {
/* 295 */             writer.write("<![CDATA[");
/* 296 */             this.m_cdataTagOpen = true;
/*     */           }
/*     */         
/* 299 */         } else if (i < end - 2 && ']' == c && ']' == ch[i + 1] && '>' == ch[i + 2]) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 305 */           writer.write("]]]]><![CDATA[>");
/* 306 */           i += 2;
/*     */ 
/*     */         
/*     */         }
/* 310 */         else if (c <= M_MAXCHARACTER) {
/*     */           
/* 312 */           writer.write(c);
/*     */         
/*     */         }
/* 315 */         else if (ToStream.isUTF16Surrogate(c)) {
/*     */           
/* 317 */           writeUTF16Surrogate(c, ch, i, end);
/* 318 */           i++;
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */           
/* 326 */           String encoding = getEncoding();
/* 327 */           if (encoding != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 332 */             String integralValue = Integer.toString(c);
/* 333 */             throw new SAXException(XMLMessages.createXMLMessage("ER_ILLEGAL_CHARACTER", new Object[] { integralValue, encoding }));
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 342 */           writer.write(c);
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 351 */       for (int i = start; i < end; i++) {
/*     */         
/* 353 */         char c = ch[i];
/*     */         
/* 355 */         if ('\n' == c && useLineSep) {
/*     */           
/* 357 */           writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*     */         }
/* 359 */         else if (c <= M_MAXCHARACTER) {
/*     */           
/* 361 */           writer.write(c);
/*     */         }
/* 363 */         else if (ToStream.isUTF16Surrogate(c)) {
/*     */           
/* 365 */           writeUTF16Surrogate(c, ch, i, end);
/* 366 */           i++;
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */           
/* 374 */           String encoding = getEncoding();
/* 375 */           if (encoding != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 380 */             String integralValue = Integer.toString(c);
/* 381 */             throw new SAXException(XMLMessages.createXMLMessage("ER_ILLEGAL_CHARACTER", new Object[] { integralValue, encoding }));
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 390 */           writer.write(c);
/*     */         } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cdata(char[] ch, int start, int length) throws SAXException {
/*     */     
/* 427 */     try { writeNormalizedChars(ch, start, length, false, this.m_lineSepUse);
/* 428 */       if (this.m_tracer != null)
/* 429 */         fireCDATAEvent(ch, start, length);  } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 433 */       throw new SAXException(ioe); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*     */     
/* 469 */     try { writeNormalizedChars(ch, start, length, false, this.m_lineSepUse); } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 473 */       throw new SAXException(ioe); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 500 */     flushPending();
/*     */     
/* 502 */     if (this.m_tracer != null) {
/* 503 */       fireEscapingEvent(target, data);
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
/*     */   public void comment(String data) throws SAXException {
/* 517 */     int length = data.length();
/* 518 */     if (length > this.m_charsBuff.length)
/*     */     {
/* 520 */       this.m_charsBuff = new char[length * 2 + 1];
/*     */     }
/* 522 */     data.getChars(0, length, this.m_charsBuff, 0);
/* 523 */     comment(this.m_charsBuff, 0, length);
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
/*     */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 542 */     flushPending();
/* 543 */     if (this.m_tracer != null) {
/* 544 */       fireCommentEvent(ch, start, length);
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
/* 556 */     if (this.m_tracer != null) {
/* 557 */       fireEntityReference(name);
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
/*     */   public void addAttribute(String uri, String localName, String rawName, String type, String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCDATA() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String elemName) throws SAXException {
/* 586 */     if (this.m_tracer != null) {
/* 587 */       fireEndElem(elemName);
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
/*     */   public void startElement(String elementNamespaceURI, String elementLocalName, String elementName) throws SAXException {
/* 599 */     if (this.m_needToCallStartDocument) {
/* 600 */       startDocumentInternal();
/*     */     }
/* 602 */     if (this.m_tracer != null) {
/* 603 */       fireStartElem(elementName);
/* 604 */       firePseudoAttributes();
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
/*     */   public void characters(String characters) throws SAXException {
/* 617 */     int length = characters.length();
/* 618 */     if (length > this.m_charsBuff.length)
/*     */     {
/* 620 */       this.m_charsBuff = new char[length * 2 + 1];
/*     */     }
/* 622 */     characters.getChars(0, length, this.m_charsBuff, 0);
/* 623 */     characters(this.m_charsBuff, 0, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(String name, String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUniqueAttribute(String qName, String value, int flags) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startPrefixMapping(String prefix, String uri, boolean shouldFlush) throws SAXException {
/* 651 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void namespaceAfterStartElement(String prefix, String uri) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flushPending() throws SAXException {
/* 672 */     if (this.m_needToCallStartDocument) {
/*     */       
/* 674 */       startDocumentInternal();
/* 675 */       this.m_needToCallStartDocument = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToTextStream.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */