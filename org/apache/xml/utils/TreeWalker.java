/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import org.apache.xml.dtm.ref.dom2dtm.DOM2DTM;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.w3c.dom.Text;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ import org.xml.sax.helpers.LocatorImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeWalker
/*     */ {
/*  46 */   private ContentHandler m_contentHandler = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DOMHelper m_dh;
/*     */ 
/*     */ 
/*     */   
/*  55 */   private LocatorImpl m_locator = new LocatorImpl();
/*     */ 
/*     */ 
/*     */   
/*     */   boolean nextIsRaw;
/*     */ 
/*     */ 
/*     */   
/*     */   public ContentHandler getContentHandler() {
/*  64 */     return this.m_contentHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentHandler(ContentHandler ch) {
/*  74 */     this.m_contentHandler = ch;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void traverse(Node pos) throws SAXException {
/* 153 */     this.m_contentHandler.startDocument();
/*     */     
/* 155 */     Node top = pos;
/*     */     
/* 157 */     while (null != pos) {
/*     */       
/* 159 */       startNode(pos);
/*     */       
/* 161 */       Node nextNode = pos.getFirstChild();
/*     */       
/* 163 */       while (null == nextNode) {
/*     */         
/* 165 */         endNode(pos);
/*     */         
/* 167 */         if (top.equals(pos)) {
/*     */           break;
/*     */         }
/* 170 */         nextNode = pos.getNextSibling();
/*     */         
/* 172 */         if (null == nextNode) {
/*     */           
/* 174 */           pos = pos.getParentNode();
/*     */           
/* 176 */           if (null == pos || top.equals(pos)) {
/*     */             
/* 178 */             if (null != pos) {
/* 179 */               endNode(pos);
/*     */             }
/* 181 */             nextNode = null;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 188 */       pos = nextNode;
/*     */     } 
/* 190 */     this.m_contentHandler.endDocument();
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
/*     */   public void traverse(Node pos, Node top) throws SAXException {
/* 209 */     this.m_contentHandler.startDocument();
/*     */     
/* 211 */     while (null != pos) {
/*     */       
/* 213 */       startNode(pos);
/*     */       
/* 215 */       Node nextNode = pos.getFirstChild();
/*     */       
/* 217 */       while (null == nextNode) {
/*     */         
/* 219 */         endNode(pos);
/*     */         
/* 221 */         if (null != top && top.equals(pos)) {
/*     */           break;
/*     */         }
/* 224 */         nextNode = pos.getNextSibling();
/*     */         
/* 226 */         if (null == nextNode) {
/*     */           
/* 228 */           pos = pos.getParentNode();
/*     */           
/* 230 */           if (null == pos || (null != top && top.equals(pos))) {
/*     */             
/* 232 */             nextNode = null;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 239 */       pos = nextNode;
/*     */     } 
/* 241 */     this.m_contentHandler.endDocument();
/*     */   }
/*     */   
/*     */   public TreeWalker(ContentHandler contentHandler, DOMHelper dh, String systemId) {
/* 245 */     this.nextIsRaw = false; this.m_contentHandler = contentHandler; this.m_contentHandler.setDocumentLocator(this.m_locator); if (systemId != null) { this.m_locator.setSystemId(systemId); } else { try { this.m_locator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl"); } catch (SecurityException securityException) {} }  this.m_dh = dh; } public TreeWalker(ContentHandler contentHandler, DOMHelper dh) { this.nextIsRaw = false; this.m_contentHandler = contentHandler; this.m_contentHandler.setDocumentLocator(this.m_locator); try { this.m_locator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl"); } catch (SecurityException securityException) {} this.m_dh = dh; } public TreeWalker(ContentHandler contentHandler) { this.nextIsRaw = false;
/*     */     this.m_contentHandler = contentHandler;
/*     */     if (this.m_contentHandler != null)
/*     */       this.m_contentHandler.setDocumentLocator(this.m_locator); 
/*     */     try {
/*     */       this.m_locator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl");
/*     */     } catch (SecurityException securityException) {}
/*     */     this.m_dh = new DOM2Helper(); } private final void dispatachChars(Node node) throws SAXException {
/* 253 */     if (this.m_contentHandler instanceof DOM2DTM.CharacterNodeHandler) {
/*     */       
/* 255 */       ((DOM2DTM.CharacterNodeHandler)this.m_contentHandler).characters(node);
/*     */     }
/*     */     else {
/*     */       
/* 259 */       String data = ((Text)node).getData();
/* 260 */       this.m_contentHandler.characters(data.toCharArray(), 0, data.length());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void startNode(Node node) throws SAXException {
/*     */     String data;
/*     */     NamedNodeMap atts;
/*     */     int nAttrs;
/*     */     int i;
/*     */     String ns;
/*     */     ProcessingInstruction pi;
/*     */     boolean isLexH;
/*     */     EntityReference eref;
/*     */     String name;
/*     */     LexicalHandler lh;
/* 275 */     if (this.m_contentHandler instanceof NodeConsumer)
/*     */     {
/* 277 */       ((NodeConsumer)this.m_contentHandler).setOriginatingNode(node);
/*     */     }
/*     */     
/* 280 */     if (node instanceof Locator) {
/*     */       
/* 282 */       Locator loc = (Locator)node;
/* 283 */       this.m_locator.setColumnNumber(loc.getColumnNumber());
/* 284 */       this.m_locator.setLineNumber(loc.getLineNumber());
/* 285 */       this.m_locator.setPublicId(loc.getPublicId());
/* 286 */       this.m_locator.setSystemId(loc.getSystemId());
/*     */     }
/*     */     else {
/*     */       
/* 290 */       this.m_locator.setColumnNumber(0);
/* 291 */       this.m_locator.setLineNumber(0);
/*     */     } 
/*     */     
/* 294 */     switch (node.getNodeType()) {
/*     */ 
/*     */       
/*     */       case 8:
/* 298 */         data = ((Comment)node).getData();
/*     */         
/* 300 */         if (this.m_contentHandler instanceof LexicalHandler) {
/*     */           
/* 302 */           LexicalHandler lexicalHandler = (LexicalHandler)this.m_contentHandler;
/*     */           
/* 304 */           lexicalHandler.comment(data.toCharArray(), 0, data.length());
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 316 */         atts = ((Element)node).getAttributes();
/* 317 */         nAttrs = atts.getLength();
/*     */ 
/*     */         
/* 320 */         for (i = 0; i < nAttrs; i++) {
/*     */           
/* 322 */           Node attr = atts.item(i);
/* 323 */           String attrName = attr.getNodeName();
/*     */ 
/*     */           
/* 326 */           if (attrName.equals("xmlns") || attrName.startsWith("xmlns:")) {
/*     */             int index;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 333 */             String prefix = ((index = attrName.indexOf(":")) < 0) ? "" : attrName.substring(index + 1);
/*     */ 
/*     */             
/* 336 */             this.m_contentHandler.startPrefixMapping(prefix, attr.getNodeValue());
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 344 */         ns = this.m_dh.getNamespaceOfNode(node);
/* 345 */         if (null == ns)
/* 346 */           ns = ""; 
/* 347 */         this.m_contentHandler.startElement(ns, this.m_dh.getLocalNameOfNode(node), node.getNodeName(), new AttList(atts, this.m_dh));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 7:
/* 354 */         pi = (ProcessingInstruction)node;
/* 355 */         name = pi.getNodeName();
/*     */ 
/*     */         
/* 358 */         if (name.equals("xslt-next-is-raw")) {
/*     */           
/* 360 */           this.nextIsRaw = true;
/*     */           
/*     */           break;
/*     */         } 
/* 364 */         this.m_contentHandler.processingInstruction(pi.getNodeName(), pi.getData());
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 371 */         isLexH = this.m_contentHandler instanceof LexicalHandler;
/* 372 */         lh = isLexH ? (LexicalHandler)this.m_contentHandler : null;
/*     */ 
/*     */         
/* 375 */         if (isLexH)
/*     */         {
/* 377 */           lh.startCDATA();
/*     */         }
/*     */         
/* 380 */         dispatachChars(node);
/*     */ 
/*     */         
/* 383 */         if (isLexH)
/*     */         {
/* 385 */           lh.endCDATA();
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 394 */         if (this.nextIsRaw) {
/*     */           
/* 396 */           this.nextIsRaw = false;
/*     */           
/* 398 */           this.m_contentHandler.processingInstruction("javax.xml.transform.disable-output-escaping", "");
/* 399 */           dispatachChars(node);
/* 400 */           this.m_contentHandler.processingInstruction("javax.xml.transform.enable-output-escaping", "");
/*     */           
/*     */           break;
/*     */         } 
/* 404 */         dispatachChars(node);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 410 */         eref = (EntityReference)node;
/*     */         
/* 412 */         if (this.m_contentHandler instanceof LexicalHandler)
/*     */         {
/* 414 */           ((LexicalHandler)this.m_contentHandler).startEntity(eref.getNodeName());
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
/*     */   protected void endNode(Node node) throws SAXException {
/*     */     String ns;
/*     */     NamedNodeMap atts;
/*     */     int nAttrs;
/*     */     int i;
/*     */     EntityReference eref;
/* 439 */     switch (node.getNodeType()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 445 */         ns = this.m_dh.getNamespaceOfNode(node);
/* 446 */         if (null == ns)
/* 447 */           ns = ""; 
/* 448 */         this.m_contentHandler.endElement(ns, this.m_dh.getLocalNameOfNode(node), node.getNodeName());
/*     */ 
/*     */ 
/*     */         
/* 452 */         atts = ((Element)node).getAttributes();
/* 453 */         nAttrs = atts.getLength();
/*     */         
/* 455 */         for (i = 0; i < nAttrs; i++) {
/*     */           
/* 457 */           Node attr = atts.item(i);
/* 458 */           String attrName = attr.getNodeName();
/*     */           
/* 460 */           if (attrName.equals("xmlns") || attrName.startsWith("xmlns:")) {
/*     */             int index;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 466 */             String prefix = ((index = attrName.indexOf(":")) < 0) ? "" : attrName.substring(index + 1);
/*     */ 
/*     */             
/* 469 */             this.m_contentHandler.endPrefixMapping(prefix);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 477 */         eref = (EntityReference)node;
/*     */         
/* 479 */         if (this.m_contentHandler instanceof LexicalHandler) {
/*     */           
/* 481 */           LexicalHandler lh = (LexicalHandler)this.m_contentHandler;
/*     */           
/* 483 */           lh.endEntity(eref.getNodeName());
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/TreeWalker.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */