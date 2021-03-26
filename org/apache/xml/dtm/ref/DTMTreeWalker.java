/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
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
/*     */ public class DTMTreeWalker
/*     */ {
/*  41 */   private ContentHandler m_contentHandler = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected DTM m_dtm;
/*     */ 
/*     */   
/*     */   boolean nextIsRaw;
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDTM(DTM dtm) {
/*  53 */     this.m_dtm = dtm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContentHandler getcontentHandler() {
/*  63 */     return this.m_contentHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setcontentHandler(ContentHandler ch) {
/*  73 */     this.m_contentHandler = ch;
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
/*     */   public void traverse(int pos) throws SAXException {
/* 110 */     int top = pos;
/*     */     
/* 112 */     while (-1 != pos) {
/*     */       
/* 114 */       startNode(pos);
/* 115 */       int nextNode = this.m_dtm.getFirstChild(pos);
/* 116 */       while (-1 == nextNode) {
/*     */         
/* 118 */         endNode(pos);
/*     */         
/* 120 */         if (top == pos) {
/*     */           break;
/*     */         }
/* 123 */         nextNode = this.m_dtm.getNextSibling(pos);
/*     */         
/* 125 */         if (-1 == nextNode) {
/*     */           
/* 127 */           pos = this.m_dtm.getParent(pos);
/*     */           
/* 129 */           if (-1 == pos || top == pos) {
/*     */ 
/*     */ 
/*     */             
/* 133 */             if (-1 != pos) {
/* 134 */               endNode(pos);
/*     */             }
/* 136 */             nextNode = -1;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 143 */       pos = nextNode;
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
/*     */   public void traverse(int pos, int top) throws SAXException {
/* 165 */     while (-1 != pos) {
/*     */       
/* 167 */       startNode(pos);
/* 168 */       int nextNode = this.m_dtm.getFirstChild(pos);
/* 169 */       while (-1 == nextNode) {
/*     */         
/* 171 */         endNode(pos);
/*     */         
/* 173 */         if (-1 != top && top == pos) {
/*     */           break;
/*     */         }
/* 176 */         nextNode = this.m_dtm.getNextSibling(pos);
/*     */         
/* 178 */         if (-1 == nextNode) {
/*     */           
/* 180 */           pos = this.m_dtm.getParent(pos);
/*     */           
/* 182 */           if (-1 == pos || (-1 != top && top == pos)) {
/*     */             
/* 184 */             nextNode = -1;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 191 */       pos = nextNode;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DTMTreeWalker() {
/* 196 */     this.nextIsRaw = false; } public DTMTreeWalker(ContentHandler contentHandler, DTM dtm) { this.nextIsRaw = false;
/*     */     this.m_contentHandler = contentHandler;
/*     */     this.m_dtm = dtm; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void dispatachChars(int node) throws SAXException {
/* 204 */     this.m_dtm.dispatchCharactersEvents(node, this.m_contentHandler, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void startNode(int node) throws SAXException {
/*     */     XMLString data;
/*     */     DTM dtm;
/*     */     int nsn;
/*     */     String ns;
/*     */     AttributesImpl attrs;
/*     */     int i;
/*     */     String name;
/*     */     boolean isLexH;
/*     */     LexicalHandler lh;
/* 218 */     if (this.m_contentHandler instanceof org.apache.xml.utils.NodeConsumer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     switch (this.m_dtm.getNodeType(node)) {
/*     */ 
/*     */       
/*     */       case 8:
/* 228 */         data = this.m_dtm.getStringValue(node);
/*     */         
/* 230 */         if (this.m_contentHandler instanceof LexicalHandler) {
/*     */           
/* 232 */           LexicalHandler lexicalHandler = (LexicalHandler)this.m_contentHandler;
/* 233 */           data.dispatchAsComment(lexicalHandler);
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/* 242 */         this.m_contentHandler.startDocument();
/*     */         break;
/*     */       case 1:
/* 245 */         dtm = this.m_dtm;
/*     */         
/* 247 */         for (nsn = dtm.getFirstNamespaceNode(node, true); -1 != nsn; 
/* 248 */           nsn = dtm.getNextNamespaceNode(node, nsn, true)) {
/*     */ 
/*     */           
/* 251 */           String prefix = dtm.getNodeNameX(nsn);
/*     */           
/* 253 */           this.m_contentHandler.startPrefixMapping(prefix, dtm.getNodeValue(nsn));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 259 */         ns = dtm.getNamespaceURI(node);
/* 260 */         if (null == ns) {
/* 261 */           ns = "";
/*     */         }
/*     */         
/* 264 */         attrs = new AttributesImpl();
/*     */ 
/*     */         
/* 267 */         i = dtm.getFirstAttribute(node);
/* 268 */         for (; i != -1; 
/* 269 */           i = dtm.getNextAttribute(i))
/*     */         {
/* 271 */           attrs.addAttribute(dtm.getNamespaceURI(i), dtm.getLocalName(i), dtm.getNodeName(i), "CDATA", dtm.getNodeValue(i));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 279 */         this.m_contentHandler.startElement(ns, this.m_dtm.getLocalName(node), this.m_dtm.getNodeName(node), attrs);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 7:
/* 286 */         name = this.m_dtm.getNodeName(node);
/*     */ 
/*     */         
/* 289 */         if (name.equals("xslt-next-is-raw")) {
/*     */           
/* 291 */           this.nextIsRaw = true;
/*     */           
/*     */           break;
/*     */         } 
/* 295 */         this.m_contentHandler.processingInstruction(name, this.m_dtm.getNodeValue(node));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 302 */         isLexH = this.m_contentHandler instanceof LexicalHandler;
/* 303 */         lh = isLexH ? (LexicalHandler)this.m_contentHandler : null;
/*     */ 
/*     */         
/* 306 */         if (isLexH)
/*     */         {
/* 308 */           lh.startCDATA();
/*     */         }
/*     */         
/* 311 */         dispatachChars(node);
/*     */ 
/*     */         
/* 314 */         if (isLexH)
/*     */         {
/* 316 */           lh.endCDATA();
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 323 */         if (this.nextIsRaw) {
/*     */           
/* 325 */           this.nextIsRaw = false;
/*     */           
/* 327 */           this.m_contentHandler.processingInstruction("javax.xml.transform.disable-output-escaping", "");
/* 328 */           dispatachChars(node);
/* 329 */           this.m_contentHandler.processingInstruction("javax.xml.transform.enable-output-escaping", "");
/*     */           
/*     */           break;
/*     */         } 
/* 333 */         dispatachChars(node);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 339 */         if (this.m_contentHandler instanceof LexicalHandler)
/*     */         {
/* 341 */           ((LexicalHandler)this.m_contentHandler).startEntity(this.m_dtm.getNodeName(node));
/*     */         }
/*     */         break;
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
/*     */   protected void endNode(int node) throws SAXException {
/*     */     String ns;
/*     */     int nsn;
/* 366 */     switch (this.m_dtm.getNodeType(node)) {
/*     */       
/*     */       case 9:
/* 369 */         this.m_contentHandler.endDocument();
/*     */         break;
/*     */       case 1:
/* 372 */         ns = this.m_dtm.getNamespaceURI(node);
/* 373 */         if (null == ns)
/* 374 */           ns = ""; 
/* 375 */         this.m_contentHandler.endElement(ns, this.m_dtm.getLocalName(node), this.m_dtm.getNodeName(node));
/*     */ 
/*     */ 
/*     */         
/* 379 */         for (nsn = this.m_dtm.getFirstNamespaceNode(node, true); -1 != nsn; 
/* 380 */           nsn = this.m_dtm.getNextNamespaceNode(node, nsn, true)) {
/*     */ 
/*     */           
/* 383 */           String prefix = this.m_dtm.getNodeNameX(nsn);
/*     */           
/* 385 */           this.m_contentHandler.endPrefixMapping(prefix);
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 392 */         if (this.m_contentHandler instanceof LexicalHandler) {
/*     */           
/* 394 */           LexicalHandler lh = (LexicalHandler)this.m_contentHandler;
/*     */           
/* 396 */           lh.endEntity(this.m_dtm.getNodeName(node));
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMTreeWalker.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */