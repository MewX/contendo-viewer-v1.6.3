/*      */ package org.apache.batik.dom;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import org.apache.batik.dom.events.DOMMutationEvent;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.w3c.dom.ElementTraversal;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.TypeInfo;
/*      */ import org.w3c.dom.events.Event;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class AbstractElement
/*      */   extends AbstractParentChildNode
/*      */   implements ElementTraversal, Element
/*      */ {
/*      */   protected NamedNodeMap attributes;
/*      */   protected TypeInfo typeInfo;
/*      */   
/*      */   protected AbstractElement() {}
/*      */   
/*      */   protected AbstractElement(String name, AbstractDocument owner) {
/*   70 */     this.ownerDocument = owner;
/*   71 */     if (owner.getStrictErrorChecking() && !DOMUtilities.isValidName(name)) {
/*   72 */       throw createDOMException((short)5, "xml.name", new Object[] { name });
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getNodeType() {
/*   84 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAttributes() {
/*   91 */     return (this.attributes != null && this.attributes.getLength() != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NamedNodeMap getAttributes() {
/*   98 */     return (this.attributes == null) ? (this.attributes = createAttributes()) : this.attributes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTagName() {
/*  109 */     return getNodeName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAttribute(String name) {
/*  116 */     return (this.attributes != null && this.attributes.getNamedItem(name) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAttribute(String name) {
/*  123 */     if (this.attributes == null) {
/*  124 */       return "";
/*      */     }
/*  126 */     Attr attr = (Attr)this.attributes.getNamedItem(name);
/*  127 */     return (attr == null) ? "" : attr.getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttribute(String name, String value) throws DOMException {
/*  135 */     if (this.attributes == null) {
/*  136 */       this.attributes = createAttributes();
/*      */     }
/*  138 */     Attr attr = getAttributeNode(name);
/*  139 */     if (attr == null) {
/*  140 */       attr = getOwnerDocument().createAttribute(name);
/*  141 */       attr.setValue(value);
/*  142 */       this.attributes.setNamedItem(attr);
/*      */     } else {
/*  144 */       attr.setValue(value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeAttribute(String name) throws DOMException {
/*  153 */     if (!hasAttribute(name)) {
/*      */       return;
/*      */     }
/*  156 */     this.attributes.removeNamedItem(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Attr getAttributeNode(String name) {
/*  164 */     if (this.attributes == null) {
/*  165 */       return null;
/*      */     }
/*  167 */     return (Attr)this.attributes.getNamedItem(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Attr setAttributeNode(Attr newAttr) throws DOMException {
/*  175 */     if (newAttr == null) {
/*  176 */       return null;
/*      */     }
/*  178 */     if (this.attributes == null) {
/*  179 */       this.attributes = createAttributes();
/*      */     }
/*  181 */     return (Attr)this.attributes.setNamedItemNS(newAttr);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
/*  189 */     if (oldAttr == null) {
/*  190 */       return null;
/*      */     }
/*  192 */     if (this.attributes == null) {
/*  193 */       throw createDOMException((short)8, "attribute.missing", new Object[] { oldAttr.getName() });
/*      */     }
/*      */ 
/*      */     
/*  197 */     String nsURI = oldAttr.getNamespaceURI();
/*  198 */     return (Attr)this.attributes.removeNamedItemNS(nsURI, (nsURI == null) ? oldAttr.getNodeName() : oldAttr.getLocalName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void normalize() {
/*  208 */     super.normalize();
/*  209 */     if (this.attributes != null) {
/*  210 */       NamedNodeMap map = getAttributes();
/*  211 */       for (int i = map.getLength() - 1; i >= 0; i--) {
/*  212 */         map.item(i).normalize();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAttributeNS(String namespaceURI, String localName) {
/*  222 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/*  223 */       namespaceURI = null;
/*      */     }
/*  225 */     return (this.attributes != null && this.attributes.getNamedItemNS(namespaceURI, localName) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAttributeNS(String namespaceURI, String localName) {
/*  234 */     if (this.attributes == null) {
/*  235 */       return "";
/*      */     }
/*  237 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/*  238 */       namespaceURI = null;
/*      */     }
/*  240 */     Attr attr = (Attr)this.attributes.getNamedItemNS(namespaceURI, localName);
/*  241 */     return (attr == null) ? "" : attr.getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
/*  252 */     if (this.attributes == null) {
/*  253 */       this.attributes = createAttributes();
/*      */     }
/*  255 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/*  256 */       namespaceURI = null;
/*      */     }
/*  258 */     Attr attr = getAttributeNodeNS(namespaceURI, qualifiedName);
/*  259 */     if (attr == null) {
/*  260 */       attr = getOwnerDocument().createAttributeNS(namespaceURI, qualifiedName);
/*      */       
/*  262 */       attr.setValue(value);
/*  263 */       this.attributes.setNamedItemNS(attr);
/*      */     } else {
/*  265 */       attr.setValue(value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
/*  275 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/*  276 */       namespaceURI = null;
/*      */     }
/*  278 */     if (!hasAttributeNS(namespaceURI, localName)) {
/*      */       return;
/*      */     }
/*  281 */     this.attributes.removeNamedItemNS(namespaceURI, localName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Attr getAttributeNodeNS(String namespaceURI, String localName) {
/*  290 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/*  291 */       namespaceURI = null;
/*      */     }
/*  293 */     if (this.attributes == null) {
/*  294 */       return null;
/*      */     }
/*  296 */     return (Attr)this.attributes.getNamedItemNS(namespaceURI, localName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
/*  304 */     if (newAttr == null) {
/*  305 */       return null;
/*      */     }
/*  307 */     if (this.attributes == null) {
/*  308 */       this.attributes = createAttributes();
/*      */     }
/*  310 */     return (Attr)this.attributes.setNamedItemNS(newAttr);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TypeInfo getSchemaTypeInfo() {
/*  317 */     if (this.typeInfo == null) {
/*  318 */       this.typeInfo = new ElementTypeInfo();
/*      */     }
/*  320 */     return this.typeInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIdAttribute(String name, boolean isId) throws DOMException {
/*  328 */     AbstractAttr a = (AbstractAttr)getAttributeNode(name);
/*  329 */     if (a == null) {
/*  330 */       throw createDOMException((short)8, "attribute.missing", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  334 */     if (a.isReadonly()) {
/*  335 */       throw createDOMException((short)7, "readonly.node", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  339 */     updateIdEntry(a, isId);
/*  340 */     a.isIdAttr = isId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIdAttributeNS(String ns, String ln, boolean isId) throws DOMException {
/*  349 */     if (ns != null && ns.length() == 0) {
/*  350 */       ns = null;
/*      */     }
/*  352 */     AbstractAttr a = (AbstractAttr)getAttributeNodeNS(ns, ln);
/*  353 */     if (a == null) {
/*  354 */       throw createDOMException((short)8, "attribute.missing", new Object[] { ns, ln });
/*      */     }
/*      */ 
/*      */     
/*  358 */     if (a.isReadonly()) {
/*  359 */       throw createDOMException((short)7, "readonly.node", new Object[] { a.getNodeName() });
/*      */     }
/*      */ 
/*      */     
/*  363 */     updateIdEntry(a, isId);
/*  364 */     a.isIdAttr = isId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIdAttributeNode(Attr attr, boolean isId) throws DOMException {
/*  373 */     AbstractAttr a = (AbstractAttr)attr;
/*  374 */     if (a.isReadonly()) {
/*  375 */       throw createDOMException((short)7, "readonly.node", new Object[] { a.getNodeName() });
/*      */     }
/*      */ 
/*      */     
/*  379 */     updateIdEntry(a, isId);
/*  380 */     a.isIdAttr = isId;
/*      */   }
/*      */   
/*      */   private void updateIdEntry(AbstractAttr a, boolean isId) {
/*  384 */     if (a.isIdAttr) {
/*  385 */       if (!isId) {
/*  386 */         this.ownerDocument.removeIdEntry(this, a.getValue());
/*      */       }
/*  388 */     } else if (isId) {
/*  389 */       this.ownerDocument.addIdEntry(this, a.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Attr getIdAttribute() {
/*  397 */     NamedNodeMap nnm = getAttributes();
/*  398 */     if (nnm == null) {
/*  399 */       return null;
/*      */     }
/*  401 */     int len = nnm.getLength();
/*  402 */     for (int i = 0; i < len; i++) {
/*  403 */       AbstractAttr a = (AbstractAttr)nnm.item(i);
/*  404 */       if (a.isId()) {
/*  405 */         return a;
/*      */       }
/*      */     } 
/*  408 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getId() {
/*  415 */     Attr a = getIdAttribute();
/*  416 */     if (a != null) {
/*  417 */       String id = a.getNodeValue();
/*  418 */       if (id.length() > 0) {
/*  419 */         return id;
/*      */       }
/*      */     } 
/*  422 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void nodeAdded(Node node) {
/*  429 */     invalidateElementsByTagName(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void nodeToBeRemoved(Node node) {
/*  436 */     invalidateElementsByTagName(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void invalidateElementsByTagName(Node node) {
/*  443 */     if (node.getNodeType() != 1) {
/*      */       return;
/*      */     }
/*  446 */     AbstractDocument ad = getCurrentDocument();
/*  447 */     String ns = node.getNamespaceURI();
/*  448 */     String nm = node.getNodeName();
/*  449 */     String ln = (ns == null) ? node.getNodeName() : node.getLocalName();
/*  450 */     for (Node n = this; n != null; n = n.getParentNode()) {
/*  451 */       AbstractParentNode.ElementsByTagName l; AbstractParentNode.ElementsByTagNameNS lns; switch (n.getNodeType()) {
/*      */         case 1:
/*      */         case 9:
/*  454 */           l = ad.getElementsByTagName(n, nm);
/*  455 */           if (l != null) {
/*  456 */             l.invalidate();
/*      */           }
/*  458 */           l = ad.getElementsByTagName(n, "*");
/*  459 */           if (l != null) {
/*  460 */             l.invalidate();
/*      */           }
/*  462 */           lns = ad.getElementsByTagNameNS(n, ns, ln);
/*      */           
/*  464 */           if (lns != null) {
/*  465 */             lns.invalidate();
/*      */           }
/*  467 */           lns = ad.getElementsByTagNameNS(n, "*", ln);
/*  468 */           if (lns != null) {
/*  469 */             lns.invalidate();
/*      */           }
/*  471 */           lns = ad.getElementsByTagNameNS(n, ns, "*");
/*  472 */           if (lns != null) {
/*  473 */             lns.invalidate();
/*      */           }
/*  475 */           lns = ad.getElementsByTagNameNS(n, "*", "*");
/*  476 */           if (lns != null) {
/*  477 */             lns.invalidate();
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  485 */     Node c = node.getFirstChild();
/*  486 */     while (c != null) {
/*  487 */       invalidateElementsByTagName(c);
/*  488 */       c = c.getNextSibling();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NamedNodeMap createAttributes() {
/*  497 */     return new NamedNodeHashMap();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node export(Node n, AbstractDocument d) {
/*  506 */     super.export(n, d);
/*  507 */     AbstractElement ae = (AbstractElement)n;
/*  508 */     if (this.attributes != null) {
/*  509 */       NamedNodeMap map = this.attributes;
/*  510 */       for (int i = map.getLength() - 1; i >= 0; i--) {
/*  511 */         AbstractAttr aa = (AbstractAttr)map.item(i);
/*  512 */         if (aa.getSpecified()) {
/*  513 */           Attr attr = (Attr)aa.deepExport(aa.cloneNode(false), d);
/*  514 */           if (aa instanceof AbstractAttrNS) {
/*  515 */             ae.setAttributeNodeNS(attr);
/*      */           } else {
/*  517 */             ae.setAttributeNode(attr);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  522 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node deepExport(Node n, AbstractDocument d) {
/*  531 */     super.deepExport(n, d);
/*  532 */     AbstractElement ae = (AbstractElement)n;
/*  533 */     if (this.attributes != null) {
/*  534 */       NamedNodeMap map = this.attributes;
/*  535 */       for (int i = map.getLength() - 1; i >= 0; i--) {
/*  536 */         AbstractAttr aa = (AbstractAttr)map.item(i);
/*  537 */         if (aa.getSpecified()) {
/*  538 */           Attr attr = (Attr)aa.deepExport(aa.cloneNode(false), d);
/*  539 */           if (aa instanceof AbstractAttrNS) {
/*  540 */             ae.setAttributeNodeNS(attr);
/*      */           } else {
/*  542 */             ae.setAttributeNode(attr);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  547 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node copyInto(Node n) {
/*  555 */     super.copyInto(n);
/*  556 */     AbstractElement ae = (AbstractElement)n;
/*  557 */     if (this.attributes != null) {
/*  558 */       NamedNodeMap map = this.attributes;
/*  559 */       for (int i = map.getLength() - 1; i >= 0; i--) {
/*  560 */         AbstractAttr aa = (AbstractAttr)map.item(i).cloneNode(true);
/*  561 */         if (aa instanceof AbstractAttrNS) {
/*  562 */           ae.setAttributeNodeNS(aa);
/*      */         } else {
/*  564 */           ae.setAttributeNode(aa);
/*      */         } 
/*      */       } 
/*      */     } 
/*  568 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node deepCopyInto(Node n) {
/*  576 */     super.deepCopyInto(n);
/*  577 */     AbstractElement ae = (AbstractElement)n;
/*  578 */     if (this.attributes != null) {
/*  579 */       NamedNodeMap map = this.attributes;
/*  580 */       for (int i = map.getLength() - 1; i >= 0; i--) {
/*  581 */         AbstractAttr aa = (AbstractAttr)map.item(i).cloneNode(true);
/*  582 */         if (aa instanceof AbstractAttrNS) {
/*  583 */           ae.setAttributeNodeNS(aa);
/*      */         } else {
/*  585 */           ae.setAttributeNode(aa);
/*      */         } 
/*      */       } 
/*      */     } 
/*  589 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkChildType(Node n, boolean replace) {
/*  597 */     switch (n.getNodeType()) {
/*      */       case 1:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 7:
/*      */       case 8:
/*      */       case 11:
/*      */         return;
/*      */     } 
/*  607 */     throw createDOMException((short)3, "child.type", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), Integer.valueOf(n.getNodeType()), n.getNodeName() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fireDOMAttrModifiedEvent(String name, Attr node, String oldv, String newv, short change) {
/*  630 */     switch (change) {
/*      */       case 2:
/*  632 */         if (((AbstractAttr)node).isId())
/*  633 */           this.ownerDocument.addIdEntry(this, newv); 
/*  634 */         attrAdded(node, newv);
/*      */         break;
/*      */       
/*      */       case 1:
/*  638 */         if (((AbstractAttr)node).isId())
/*  639 */           this.ownerDocument.updateIdEntry(this, oldv, newv); 
/*  640 */         attrModified(node, oldv, newv);
/*      */         break;
/*      */       
/*      */       default:
/*  644 */         if (((AbstractAttr)node).isId())
/*  645 */           this.ownerDocument.removeIdEntry(this, oldv); 
/*  646 */         attrRemoved(node, oldv); break;
/*      */     } 
/*  648 */     AbstractDocument doc = getCurrentDocument();
/*  649 */     if (doc.getEventsEnabled() && !oldv.equals(newv)) {
/*  650 */       DOMMutationEvent ev = (DOMMutationEvent)doc.createEvent("MutationEvents");
/*      */       
/*  652 */       ev.initMutationEventNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", true, false, node, oldv, newv, name, change);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  661 */       dispatchEvent((Event)ev);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void attrAdded(Attr node, String newv) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void attrModified(Attr node, String oldv, String newv) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void attrRemoved(Attr node, String oldv) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getFirstElementChild() {
/*  689 */     Node n = getFirstChild();
/*  690 */     while (n != null) {
/*  691 */       if (n.getNodeType() == 1) {
/*  692 */         return (Element)n;
/*      */       }
/*  694 */       n = n.getNextSibling();
/*      */     } 
/*  696 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getLastElementChild() {
/*  703 */     Node n = getLastChild();
/*  704 */     while (n != null) {
/*  705 */       if (n.getNodeType() == 1) {
/*  706 */         return (Element)n;
/*      */       }
/*  708 */       n = n.getPreviousSibling();
/*      */     } 
/*  710 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getNextElementSibling() {
/*  717 */     Node n = getNextSibling();
/*  718 */     while (n != null) {
/*  719 */       if (n.getNodeType() == 1) {
/*  720 */         return (Element)n;
/*      */       }
/*  722 */       n = n.getNextSibling();
/*      */     } 
/*  724 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getPreviousElementSibling() {
/*  731 */     Node n = getPreviousSibling();
/*  732 */     while (n != null) {
/*  733 */       if (n.getNodeType() == 1) {
/*  734 */         return (Element)n;
/*      */       }
/*  736 */       n = n.getPreviousSibling();
/*      */     } 
/*  738 */     return (Element)n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getChildElementCount() {
/*  745 */     getChildNodes();
/*  746 */     return this.childNodes.elementChildren;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class NamedNodeHashMap
/*      */     implements Serializable, NamedNodeMap
/*      */   {
/*      */     protected static final int INITIAL_CAPACITY = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  776 */     protected AbstractElement.Entry[] table = new AbstractElement.Entry[3];
/*      */ 
/*      */     
/*      */     protected int count;
/*      */ 
/*      */     
/*      */     public Node getNamedItem(String name) {
/*  783 */       if (name == null) {
/*  784 */         return null;
/*      */       }
/*  786 */       return get(null, name);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node setNamedItem(Node arg) throws DOMException {
/*  793 */       if (arg == null) {
/*  794 */         return null;
/*      */       }
/*  796 */       checkNode(arg);
/*      */       
/*  798 */       return setNamedItem(null, arg.getNodeName(), arg);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node removeNamedItem(String name) throws DOMException {
/*  805 */       return removeNamedItemNS(null, name);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node item(int index) {
/*  812 */       if (index < 0 || index >= this.count) {
/*  813 */         return null;
/*      */       }
/*  815 */       int j = 0;
/*  816 */       for (AbstractElement.Entry aTable : this.table) {
/*  817 */         AbstractElement.Entry e = aTable;
/*  818 */         if (e != null)
/*      */           
/*      */           do {
/*      */             
/*  822 */             if (j++ == index) {
/*  823 */               return e.value;
/*      */             }
/*  825 */             e = e.next;
/*  826 */           } while (e != null); 
/*      */       } 
/*  828 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLength() {
/*  835 */       return this.count;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node getNamedItemNS(String namespaceURI, String localName) {
/*  843 */       if (namespaceURI != null && namespaceURI.length() == 0) {
/*  844 */         namespaceURI = null;
/*      */       }
/*  846 */       return get(namespaceURI, localName);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node setNamedItemNS(Node arg) throws DOMException {
/*  853 */       if (arg == null) {
/*  854 */         return null;
/*      */       }
/*  856 */       String nsURI = arg.getNamespaceURI();
/*  857 */       return setNamedItem(nsURI, (nsURI == null) ? arg.getNodeName() : arg.getLocalName(), arg);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
/*  868 */       if (AbstractElement.this.isReadonly()) {
/*  869 */         throw AbstractElement.this.createDOMException((short)7, "readonly.node.map", new Object[0]);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  874 */       if (localName == null) {
/*  875 */         throw AbstractElement.this.createDOMException((short)8, "attribute.missing", new Object[] { "" });
/*      */       }
/*      */ 
/*      */       
/*  879 */       if (namespaceURI != null && namespaceURI.length() == 0) {
/*  880 */         namespaceURI = null;
/*      */       }
/*  882 */       AbstractAttr n = (AbstractAttr)remove(namespaceURI, localName);
/*  883 */       if (n == null) {
/*  884 */         throw AbstractElement.this.createDOMException((short)8, "attribute.missing", new Object[] { localName });
/*      */       }
/*      */ 
/*      */       
/*  888 */       n.setOwnerElement((AbstractElement)null);
/*      */ 
/*      */       
/*  891 */       AbstractElement.this.fireDOMAttrModifiedEvent(n.getNodeName(), n, n.getNodeValue(), "", (short)3);
/*      */       
/*  893 */       return n;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node setNamedItem(String ns, String name, Node arg) throws DOMException {
/*  902 */       if (ns != null && ns.length() == 0) {
/*  903 */         ns = null;
/*      */       }
/*  905 */       ((AbstractAttr)arg).setOwnerElement(AbstractElement.this);
/*  906 */       AbstractAttr result = (AbstractAttr)put(ns, name, arg);
/*      */       
/*  908 */       if (result != null) {
/*  909 */         result.setOwnerElement((AbstractElement)null);
/*  910 */         AbstractElement.this.fireDOMAttrModifiedEvent(name, result, result.getNodeValue(), "", (short)3);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  916 */       AbstractElement.this.fireDOMAttrModifiedEvent(name, (Attr)arg, "", arg.getNodeValue(), (short)2);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  921 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void checkNode(Node arg) {
/*  928 */       if (AbstractElement.this.isReadonly()) {
/*  929 */         throw AbstractElement.this.createDOMException((short)7, "readonly.node.map", new Object[0]);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  934 */       if (AbstractElement.this.getOwnerDocument() != arg.getOwnerDocument()) {
/*  935 */         throw AbstractElement.this.createDOMException((short)4, "node.from.wrong.document", new Object[] { Integer.valueOf(arg.getNodeType()), arg.getNodeName() });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  940 */       if (arg.getNodeType() == 2 && ((Attr)arg).getOwnerElement() != null)
/*      */       {
/*  942 */         throw AbstractElement.this.createDOMException((short)4, "inuse.attribute", new Object[] { arg.getNodeName() });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node get(String ns, String nm) {
/*  954 */       int hash = hashCode(ns, nm) & Integer.MAX_VALUE;
/*  955 */       int index = hash % this.table.length;
/*      */       
/*  957 */       for (AbstractElement.Entry e = this.table[index]; e != null; e = e.next) {
/*  958 */         if (e.hash == hash && e.match(ns, nm)) {
/*  959 */           return e.value;
/*      */         }
/*      */       } 
/*  962 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node put(String ns, String nm, Node value) {
/*  971 */       int hash = hashCode(ns, nm) & Integer.MAX_VALUE;
/*  972 */       int index = hash % this.table.length;
/*      */       
/*  974 */       for (AbstractElement.Entry e = this.table[index]; e != null; e = e.next) {
/*  975 */         if (e.hash == hash && e.match(ns, nm)) {
/*  976 */           Node old = e.value;
/*  977 */           e.value = value;
/*  978 */           return old;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  983 */       int len = this.table.length;
/*  984 */       if (this.count++ >= len - (len >> 2)) {
/*      */         
/*  986 */         rehash();
/*  987 */         index = hash % this.table.length;
/*      */       } 
/*      */       
/*  990 */       AbstractElement.Entry entry1 = new AbstractElement.Entry(hash, ns, nm, value, this.table[index]);
/*  991 */       this.table[index] = entry1;
/*  992 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node remove(String ns, String nm) {
/* 1001 */       int hash = hashCode(ns, nm) & Integer.MAX_VALUE;
/* 1002 */       int index = hash % this.table.length;
/*      */       
/* 1004 */       AbstractElement.Entry p = null;
/* 1005 */       for (AbstractElement.Entry e = this.table[index]; e != null; e = e.next) {
/* 1006 */         if (e.hash == hash && e.match(ns, nm)) {
/* 1007 */           Node result = e.value;
/* 1008 */           if (p == null) {
/* 1009 */             this.table[index] = e.next;
/*      */           } else {
/* 1011 */             p.next = e.next;
/*      */           } 
/* 1013 */           this.count--;
/* 1014 */           return result;
/*      */         } 
/* 1016 */         p = e;
/*      */       } 
/* 1018 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void rehash() {
/* 1025 */       AbstractElement.Entry[] oldTable = this.table;
/*      */       
/* 1027 */       this.table = new AbstractElement.Entry[oldTable.length * 2 + 1];
/*      */       
/* 1029 */       for (int i = oldTable.length - 1; i >= 0; i--) {
/* 1030 */         for (AbstractElement.Entry old = oldTable[i]; old != null; ) {
/* 1031 */           AbstractElement.Entry e = old;
/* 1032 */           old = old.next;
/*      */           
/* 1034 */           int index = e.hash % this.table.length;
/* 1035 */           e.next = this.table[index];
/* 1036 */           this.table[index] = e;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int hashCode(String ns, String nm) {
/* 1045 */       int result = (ns == null) ? 0 : ns.hashCode();
/* 1046 */       return result ^ nm.hashCode();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class Entry
/*      */     implements Serializable
/*      */   {
/*      */     public int hash;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String namespaceURI;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String name;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node value;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Entry next;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Entry(int hash, String ns, String nm, Node value, Entry next) {
/* 1085 */       this.hash = hash;
/* 1086 */       this.namespaceURI = ns;
/* 1087 */       this.name = nm;
/* 1088 */       this.value = value;
/* 1089 */       this.next = next;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean match(String ns, String nm) {
/* 1096 */       if (this.namespaceURI != null) {
/* 1097 */         if (!this.namespaceURI.equals(ns)) {
/* 1098 */           return false;
/*      */         }
/* 1100 */       } else if (ns != null) {
/* 1101 */         return false;
/*      */       } 
/* 1103 */       return this.name.equals(nm);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class ElementTypeInfo
/*      */     implements TypeInfo
/*      */   {
/*      */     public String getTypeNamespace() {
/* 1116 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getTypeName() {
/* 1123 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isDerivedFrom(String ns, String name, int method) {
/* 1130 */       return false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */