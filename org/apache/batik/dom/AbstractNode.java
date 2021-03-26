/*      */ package org.apache.batik.dom;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import org.apache.batik.dom.events.DOMMutationEvent;
/*      */ import org.apache.batik.dom.events.EventSupport;
/*      */ import org.apache.batik.dom.events.NodeEventTarget;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.dom.xbl.NodeXBL;
/*      */ import org.apache.batik.dom.xbl.XBLManagerData;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentType;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.UserDataHandler;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventException;
/*      */ import org.w3c.dom.events.EventListener;
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
/*      */ public abstract class AbstractNode
/*      */   implements Serializable, ExtendedNode, NodeXBL, XBLManagerData
/*      */ {
/*   65 */   public static final NodeList EMPTY_NODE_LIST = new NodeList() {
/*   66 */       public Node item(int i) { return null; } public int getLength() {
/*   67 */         return 0;
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractDocument ownerDocument;
/*      */ 
/*      */   
/*      */   protected transient EventSupport eventSupport;
/*      */ 
/*      */   
/*      */   protected HashMap userData;
/*      */ 
/*      */   
/*      */   protected HashMap userDataHandlers;
/*      */ 
/*      */   
/*      */   protected Object managerData;
/*      */ 
/*      */   
/*      */   public static final short DOCUMENT_POSITION_DISCONNECTED = 1;
/*      */ 
/*      */   
/*      */   public static final short DOCUMENT_POSITION_PRECEDING = 2;
/*      */   
/*      */   public static final short DOCUMENT_POSITION_FOLLOWING = 4;
/*      */   
/*      */   public static final short DOCUMENT_POSITION_CONTAINS = 8;
/*      */   
/*      */   public static final short DOCUMENT_POSITION_CONTAINED_BY = 16;
/*      */   
/*      */   public static final short DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC = 32;
/*      */ 
/*      */   
/*      */   public void setNodeName(String v) {}
/*      */ 
/*      */   
/*      */   public void setOwnerDocument(Document doc) {
/*  106 */     this.ownerDocument = (AbstractDocument)doc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpecified(boolean v) {
/*  114 */     throw createDOMException((short)11, "node.type", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeValue() throws DOMException {
/*  125 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNodeValue(String nodeValue) throws DOMException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getParentNode() {
/*  140 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParentNode(Node v) {
/*  148 */     throw createDOMException((short)3, "parent.not.allowed", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getChildNodes() {
/*  159 */     return EMPTY_NODE_LIST;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getFirstChild() {
/*  167 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getLastChild() {
/*  175 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPreviousSibling(Node n) {
/*  183 */     throw createDOMException((short)3, "sibling.not.allowed", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getPreviousSibling() {
/*  194 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNextSibling(Node n) {
/*  202 */     throw createDOMException((short)3, "sibling.not.allowed", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getNextSibling() {
/*  213 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAttributes() {
/*  221 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NamedNodeMap getAttributes() {
/*  229 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Document getOwnerDocument() {
/*  237 */     return this.ownerDocument;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceURI() {
/*  245 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node insertBefore(Node newChild, Node refChild) throws DOMException {
/*  255 */     throw createDOMException((short)3, "children.not.allowed", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
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
/*      */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
/*  268 */     throw createDOMException((short)3, "children.not.allowed", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node removeChild(Node oldChild) throws DOMException {
/*  279 */     throw createDOMException((short)3, "children.not.allowed", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node appendChild(Node newChild) throws DOMException {
/*  290 */     throw createDOMException((short)3, "children.not.allowed", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasChildNodes() {
/*  301 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  308 */     Node n = deep ? deepCopyInto(newNode()) : copyInto(newNode());
/*  309 */     fireUserDataHandlers((short)1, this, n);
/*  310 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void normalize() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSupported(String feature, String version) {
/*  325 */     return getCurrentDocument().getImplementation().hasFeature(feature, version);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPrefix() {
/*  333 */     return (getNamespaceURI() == null) ? null : DOMUtilities.getPrefix(getNodeName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPrefix(String prefix) throws DOMException {
/*  342 */     if (isReadonly()) {
/*  343 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  348 */     String uri = getNamespaceURI();
/*  349 */     if (uri == null) {
/*  350 */       throw createDOMException((short)14, "namespace", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  356 */     String name = getLocalName();
/*  357 */     if (prefix == null) {
/*      */       
/*  359 */       setNodeName(name);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  364 */     if (!prefix.equals("") && !DOMUtilities.isValidName(prefix)) {
/*  365 */       throw createDOMException((short)5, "prefix", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), prefix });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  371 */     if (!DOMUtilities.isValidPrefix(prefix)) {
/*  372 */       throw createDOMException((short)14, "prefix", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), prefix });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  378 */     if ((prefix.equals("xml") && !"http://www.w3.org/XML/1998/namespace".equals(uri)) || (prefix.equals("xmlns") && !"http://www.w3.org/2000/xmlns/".equals(uri)))
/*      */     {
/*      */ 
/*      */       
/*  382 */       throw createDOMException((short)14, "namespace.uri", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), uri });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  388 */     setNodeName(prefix + ':' + name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalName() {
/*  395 */     return (getNamespaceURI() == null) ? null : DOMUtilities.getLocalName(getNodeName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMException createDOMException(short type, String key, Object[] args) {
/*      */     try {
/*  407 */       return new DOMException(type, getCurrentDocument().formatMessage(key, args));
/*      */     }
/*  409 */     catch (Exception e) {
/*  410 */       return new DOMException(type, key);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getCascadedXMLBase(Node node) {
/*  419 */     String base = null;
/*  420 */     Node n = node.getParentNode();
/*  421 */     while (n != null) {
/*  422 */       if (n.getNodeType() == 1) {
/*  423 */         base = getCascadedXMLBase(n);
/*      */         break;
/*      */       } 
/*  426 */       n = n.getParentNode();
/*      */     } 
/*  428 */     if (base == null) {
/*      */       AbstractDocument doc;
/*  430 */       if (node.getNodeType() == 9) {
/*  431 */         doc = (AbstractDocument)node;
/*      */       } else {
/*  433 */         doc = (AbstractDocument)node.getOwnerDocument();
/*      */       } 
/*  435 */       base = doc.getDocumentURI();
/*      */     } 
/*  437 */     while (node != null && node.getNodeType() != 1) {
/*  438 */       node = node.getParentNode();
/*      */     }
/*  440 */     if (node == null) {
/*  441 */       return base;
/*      */     }
/*  443 */     Element e = (Element)node;
/*  444 */     Attr attr = e.getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "base");
/*      */     
/*  446 */     if (attr != null) {
/*  447 */       if (base == null) {
/*  448 */         base = attr.getNodeValue();
/*      */       } else {
/*  450 */         base = (new ParsedURL(base, attr.getNodeValue())).toString();
/*      */       } 
/*      */     }
/*  453 */     return base;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getBaseURI() {
/*  460 */     return getCascadedXMLBase(this);
/*      */   }
/*      */   
/*      */   public static String getBaseURI(Node n) {
/*  464 */     return ((AbstractNode)n).getBaseURI();
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
/*      */   public short compareDocumentPosition(Node other) throws DOMException {
/*  483 */     if (this == other) {
/*  484 */       return 0;
/*      */     }
/*  486 */     ArrayList<AbstractNode> a1 = new ArrayList(10);
/*  487 */     ArrayList<Node> a2 = new ArrayList(10);
/*  488 */     int c1 = 0;
/*  489 */     int c2 = 0;
/*      */     
/*  491 */     if (getNodeType() == 2) {
/*  492 */       a1.add(this);
/*  493 */       c1++;
/*  494 */       n = ((Attr)this).getOwnerElement();
/*  495 */       if (other.getNodeType() == 2) {
/*  496 */         Attr otherAttr = (Attr)other;
/*  497 */         if (n == otherAttr.getOwnerElement()) {
/*  498 */           if (hashCode() < ((Attr)other).hashCode()) {
/*  499 */             return 34;
/*      */           }
/*      */           
/*  502 */           return 36;
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  508 */       n = this;
/*      */     } 
/*  510 */     while (n != null) {
/*  511 */       if (n == other) {
/*  512 */         return 20;
/*      */       }
/*      */       
/*  515 */       a1.add(n);
/*  516 */       c1++;
/*  517 */       n = n.getParentNode();
/*      */     } 
/*  519 */     if (other.getNodeType() == 2) {
/*  520 */       a2.add(other);
/*  521 */       c2++;
/*  522 */       n = ((Attr)other).getOwnerElement();
/*      */     } else {
/*  524 */       n = other;
/*      */     } 
/*  526 */     while (n != null) {
/*  527 */       if (n == this) {
/*  528 */         return 10;
/*      */       }
/*      */       
/*  531 */       a2.add(n);
/*  532 */       c2++;
/*  533 */       n = n.getParentNode();
/*      */     } 
/*  535 */     int i1 = c1 - 1;
/*  536 */     int i2 = c2 - 1;
/*  537 */     if (a1.get(i1) != a2.get(i2)) {
/*  538 */       if (hashCode() < other.hashCode()) {
/*  539 */         return 35;
/*      */       }
/*      */ 
/*      */       
/*  543 */       return 37;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  548 */     Object n1 = a1.get(i1);
/*  549 */     Object n2 = a2.get(i2);
/*  550 */     while (n1 == n2) {
/*  551 */       n = (Node)n1;
/*  552 */       n1 = a1.get(--i1);
/*  553 */       n2 = a2.get(--i2);
/*      */     } 
/*  555 */     for (Node n = n.getFirstChild(); n != null; n = n.getNextSibling()) {
/*  556 */       if (n == n1)
/*  557 */         return 2; 
/*  558 */       if (n == n2) {
/*  559 */         return 4;
/*      */       }
/*      */     } 
/*  562 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTextContent() {
/*  569 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextContent(String s) throws DOMException {
/*  576 */     if (isReadonly()) {
/*  577 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  582 */     if (getNodeType() != 10) {
/*  583 */       while (getFirstChild() != null) {
/*  584 */         removeChild(getFirstChild());
/*      */       }
/*  586 */       appendChild(getOwnerDocument().createTextNode(s));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSameNode(Node other) {
/*  594 */     return (this == other);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String lookupPrefix(String namespaceURI) {
/*      */     AbstractNode de, ownerElement;
/*  601 */     if (namespaceURI == null || namespaceURI.length() == 0) {
/*  602 */       return null;
/*      */     }
/*  604 */     int type = getNodeType();
/*  605 */     switch (type) {
/*      */       case 1:
/*  607 */         return lookupNamespacePrefix(namespaceURI, (Element)this);
/*      */       case 9:
/*  609 */         de = (AbstractNode)((Document)this).getDocumentElement();
/*      */         
/*  611 */         return de.lookupPrefix(namespaceURI);
/*      */       case 6:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*  616 */         return null;
/*      */       case 2:
/*  618 */         ownerElement = (AbstractNode)((Attr)this).getOwnerElement();
/*      */         
/*  620 */         if (ownerElement != null) {
/*  621 */           return ownerElement.lookupPrefix(namespaceURI);
/*      */         }
/*  623 */         return null;
/*      */     } 
/*  625 */     Node n = getParentNode();
/*  626 */     for (; n != null; 
/*  627 */       n = n.getParentNode()) {
/*  628 */       if (n.getNodeType() == 1) {
/*  629 */         return ((AbstractNode)n).lookupPrefix(namespaceURI);
/*      */       }
/*      */     } 
/*  632 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String lookupNamespacePrefix(String namespaceURI, Element originalElement) {
/*  641 */     String ns = originalElement.getNamespaceURI();
/*  642 */     String prefix = originalElement.getPrefix();
/*  643 */     if (ns != null && ns.equals(namespaceURI) && prefix != null) {
/*      */ 
/*      */       
/*  646 */       String pns = ((AbstractNode)originalElement).lookupNamespaceURI(prefix);
/*      */       
/*  648 */       if (pns != null && pns.equals(namespaceURI)) {
/*  649 */         return prefix;
/*      */       }
/*      */     } 
/*  652 */     NamedNodeMap nnm = originalElement.getAttributes();
/*  653 */     if (nnm != null) {
/*  654 */       for (int i = 0; i < nnm.getLength(); i++) {
/*  655 */         Node attr = nnm.item(i);
/*  656 */         if ("xmlns".equals(attr.getPrefix()) && attr.getNodeValue().equals(namespaceURI)) {
/*      */           
/*  658 */           String ln = attr.getLocalName();
/*  659 */           AbstractNode oe = (AbstractNode)originalElement;
/*  660 */           String pns = oe.lookupNamespaceURI(ln);
/*  661 */           if (pns != null && pns.equals(namespaceURI)) {
/*  662 */             return ln;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*  667 */     for (Node n = getParentNode(); n != null; n = n.getParentNode()) {
/*  668 */       if (n.getNodeType() == 1) {
/*  669 */         return ((AbstractNode)n).lookupNamespacePrefix(namespaceURI, originalElement);
/*      */       }
/*      */     } 
/*      */     
/*  673 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDefaultNamespace(String namespaceURI) {
/*      */     AbstractNode de;
/*      */     AbstractNode owner;
/*      */     NamedNodeMap nnm;
/*  681 */     switch (getNodeType()) {
/*      */       case 9:
/*  683 */         de = (AbstractNode)((Document)this).getDocumentElement();
/*      */         
/*  685 */         return de.isDefaultNamespace(namespaceURI);
/*      */       case 6:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*  690 */         return false;
/*      */       case 2:
/*  692 */         owner = (AbstractNode)((Attr)this).getOwnerElement();
/*      */         
/*  694 */         if (owner != null) {
/*  695 */           return owner.isDefaultNamespace(namespaceURI);
/*      */         }
/*  697 */         return false;
/*      */       case 1:
/*  699 */         if (getPrefix() == null) {
/*  700 */           String ns = getNamespaceURI();
/*  701 */           return ((ns == null && namespaceURI == null) || (ns != null && ns.equals(namespaceURI)));
/*      */         } 
/*      */         
/*  704 */         nnm = getAttributes();
/*  705 */         if (nnm != null) {
/*  706 */           for (int i = 0; i < nnm.getLength(); i++) {
/*  707 */             Node attr = nnm.item(i);
/*  708 */             if ("xmlns".equals(attr.getLocalName()))
/*      */             {
/*  710 */               return attr.getNodeValue().equals(namespaceURI);
/*      */             }
/*      */           } 
/*      */         }
/*      */         break;
/*      */     } 
/*  716 */     for (Node n = this; n != null; n = n.getParentNode()) {
/*  717 */       if (n.getNodeType() == 1) {
/*  718 */         AbstractNode an = (AbstractNode)n;
/*  719 */         return an.isDefaultNamespace(namespaceURI);
/*      */       } 
/*      */     } 
/*  722 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String lookupNamespaceURI(String prefix) {
/*      */     AbstractNode de;
/*      */     AbstractNode owner;
/*      */     NamedNodeMap nnm;
/*  731 */     switch (getNodeType()) {
/*      */       case 9:
/*  733 */         de = (AbstractNode)((Document)this).getDocumentElement();
/*      */         
/*  735 */         return de.lookupNamespaceURI(prefix);
/*      */       case 6:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*  740 */         return null;
/*      */       case 2:
/*  742 */         owner = (AbstractNode)((Attr)this).getOwnerElement();
/*      */         
/*  744 */         if (owner != null) {
/*  745 */           return owner.lookupNamespaceURI(prefix);
/*      */         }
/*  747 */         return null;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*  753 */         nnm = getAttributes();
/*  754 */         if (nnm != null) {
/*  755 */           for (int i = 0; i < nnm.getLength(); i++) {
/*  756 */             Node attr = nnm.item(i);
/*  757 */             String attrPrefix = attr.getPrefix();
/*  758 */             String localName = attr.getLocalName();
/*  759 */             if (localName == null) {
/*  760 */               localName = attr.getNodeName();
/*      */             }
/*  762 */             if (("xmlns".equals(attrPrefix) && compareStrings(localName, prefix)) || ("xmlns".equals(localName) && prefix == null)) {
/*      */ 
/*      */ 
/*      */               
/*  766 */               String value = attr.getNodeValue();
/*  767 */               if (value.length() > 0) {
/*  768 */                 return value;
/*      */               }
/*  770 */               return null;
/*      */             } 
/*      */           } 
/*      */         }
/*      */         break;
/*      */     } 
/*  776 */     for (Node n = getParentNode(); n != null; n = n.getParentNode()) {
/*  777 */       if (n.getNodeType() == 1) {
/*  778 */         AbstractNode an = (AbstractNode)n;
/*  779 */         return an.lookupNamespaceURI(prefix);
/*      */       } 
/*      */     } 
/*  782 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEqualNode(Node other) {
/*  790 */     if (other == null) {
/*  791 */       return false;
/*      */     }
/*  793 */     int nt = other.getNodeType();
/*  794 */     if (nt != getNodeType() || !compareStrings(getNodeName(), other.getNodeName()) || !compareStrings(getLocalName(), other.getLocalName()) || !compareStrings(getPrefix(), other.getPrefix()) || !compareStrings(getNodeValue(), other.getNodeValue()) || !compareStrings(getNodeValue(), other.getNodeValue()) || !compareNamedNodeMaps(getAttributes(), other.getAttributes()))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  802 */       return false;
/*      */     }
/*  804 */     if (nt == 10) {
/*  805 */       DocumentType dt1 = (DocumentType)this;
/*  806 */       DocumentType dt2 = (DocumentType)other;
/*  807 */       if (!compareStrings(dt1.getPublicId(), dt2.getPublicId()) || !compareStrings(dt1.getSystemId(), dt2.getSystemId()) || !compareStrings(dt1.getInternalSubset(), dt2.getInternalSubset()) || !compareNamedNodeMaps(dt1.getEntities(), dt2.getEntities()) || !compareNamedNodeMaps(dt1.getNotations(), dt2.getNotations()))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  815 */         return false;
/*      */       }
/*      */     } 
/*  818 */     Node n = getFirstChild();
/*  819 */     Node m = other.getFirstChild();
/*  820 */     if (n != null && m != null && 
/*  821 */       !((AbstractNode)n).isEqualNode(m)) {
/*  822 */       return false;
/*      */     }
/*      */     
/*  825 */     return (n == m);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean compareStrings(String s1, String s2) {
/*  832 */     return ((s1 != null && s1.equals(s2)) || (s1 == null && s2 == null));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean compareNamedNodeMaps(NamedNodeMap nnm1, NamedNodeMap nnm2) {
/*  840 */     if ((nnm1 == null && nnm2 != null) || (nnm1 != null && nnm2 == null))
/*      */     {
/*  842 */       return false;
/*      */     }
/*  844 */     if (nnm1 != null) {
/*  845 */       int len = nnm1.getLength();
/*  846 */       if (len != nnm2.getLength()) {
/*  847 */         return false;
/*      */       }
/*  849 */       for (int i = 0; i < len; i++) {
/*  850 */         Node n2, n1 = nnm1.item(i);
/*  851 */         String n1ln = n1.getLocalName();
/*      */         
/*  853 */         if (n1ln != null) {
/*  854 */           n2 = nnm2.getNamedItemNS(n1.getNamespaceURI(), n1ln);
/*      */         } else {
/*  856 */           n2 = nnm2.getNamedItem(n1.getNodeName());
/*      */         } 
/*  858 */         if (!((AbstractNode)n1).isEqualNode(n2)) {
/*  859 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*  863 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getFeature(String feature, String version) {
/*  871 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getUserData(String key) {
/*  878 */     if (this.userData == null) {
/*  879 */       return null;
/*      */     }
/*  881 */     return this.userData.get(key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object setUserData(String key, Object data, UserDataHandler handler) {
/*  889 */     if (this.userData == null) {
/*  890 */       this.userData = new HashMap<Object, Object>();
/*  891 */       this.userDataHandlers = new HashMap<Object, Object>();
/*      */     } 
/*  893 */     if (data == null) {
/*  894 */       this.userData.remove(key);
/*  895 */       return this.userDataHandlers.remove(key);
/*      */     } 
/*  897 */     this.userDataHandlers.put(key, handler);
/*  898 */     return this.userData.put(key, data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireUserDataHandlers(short type, Node oldNode, Node newNode) {
/*  907 */     AbstractNode an = (AbstractNode)oldNode;
/*  908 */     if (an.userData != null) {
/*  909 */       for (Object o : an.userData.entrySet()) {
/*  910 */         Map.Entry e = (Map.Entry)o;
/*  911 */         UserDataHandler h = (UserDataHandler)an.userDataHandlers.get(e.getKey());
/*      */         
/*  913 */         if (h != null) {
/*  914 */           h.handle(type, (String)e.getKey(), e.getValue(), oldNode, newNode);
/*      */         }
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addEventListener(String type, EventListener listener, boolean useCapture) {
/*  934 */     if (this.eventSupport == null) {
/*  935 */       initializeEventSupport();
/*      */     }
/*  937 */     this.eventSupport.addEventListener(type, listener, useCapture);
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
/*      */   public void addEventListenerNS(String namespaceURI, String type, EventListener listener, boolean useCapture, Object evtGroup) {
/*  950 */     if (this.eventSupport == null) {
/*  951 */       initializeEventSupport();
/*      */     }
/*  953 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/*  954 */       namespaceURI = null;
/*      */     }
/*  956 */     this.eventSupport.addEventListenerNS(namespaceURI, type, listener, useCapture, evtGroup);
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
/*      */   public void removeEventListener(String type, EventListener listener, boolean useCapture) {
/*  971 */     if (this.eventSupport != null) {
/*  972 */       this.eventSupport.removeEventListener(type, listener, useCapture);
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
/*      */   
/*      */   public void removeEventListenerNS(String namespaceURI, String type, EventListener listener, boolean useCapture) {
/*  985 */     if (this.eventSupport != null) {
/*  986 */       if (namespaceURI != null && namespaceURI.length() == 0) {
/*  987 */         namespaceURI = null;
/*      */       }
/*  989 */       this.eventSupport.removeEventListenerNS(namespaceURI, type, listener, useCapture);
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
/*      */   public NodeEventTarget getParentNodeEventTarget() {
/* 1001 */     return (NodeEventTarget)getXblParentNode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean dispatchEvent(Event evt) throws EventException {
/* 1009 */     if (this.eventSupport == null) {
/* 1010 */       initializeEventSupport();
/*      */     }
/* 1012 */     return this.eventSupport.dispatchEvent(this, evt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean willTriggerNS(String namespaceURI, String type) {
/* 1021 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasEventListenerNS(String namespaceURI, String type) {
/* 1030 */     if (this.eventSupport == null) {
/* 1031 */       return false;
/*      */     }
/* 1033 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/* 1034 */       namespaceURI = null;
/*      */     }
/* 1036 */     return this.eventSupport.hasEventListenerNS(namespaceURI, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EventSupport getEventSupport() {
/* 1043 */     return this.eventSupport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EventSupport initializeEventSupport() {
/* 1051 */     if (this.eventSupport == null) {
/* 1052 */       AbstractDocument doc = getCurrentDocument();
/* 1053 */       AbstractDOMImplementation di = (AbstractDOMImplementation)doc.getImplementation();
/*      */       
/* 1055 */       this.eventSupport = di.createEventSupport(this);
/* 1056 */       doc.setEventsEnabled(true);
/*      */     } 
/* 1058 */     return this.eventSupport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fireDOMNodeInsertedIntoDocumentEvent() {
/* 1065 */     AbstractDocument doc = getCurrentDocument();
/* 1066 */     if (doc.getEventsEnabled()) {
/* 1067 */       DOMMutationEvent ev = (DOMMutationEvent)doc.createEvent("MutationEvents");
/*      */       
/* 1069 */       ev.initMutationEventNS("http://www.w3.org/2001/xml-events", "DOMNodeInsertedIntoDocument", true, false, null, null, null, null, (short)2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1078 */       dispatchEvent((Event)ev);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fireDOMNodeRemovedFromDocumentEvent() {
/* 1086 */     AbstractDocument doc = getCurrentDocument();
/* 1087 */     if (doc.getEventsEnabled()) {
/* 1088 */       DOMMutationEvent ev = (DOMMutationEvent)doc.createEvent("MutationEvents");
/*      */       
/* 1090 */       ev.initMutationEventNS("http://www.w3.org/2001/xml-events", "DOMNodeRemovedFromDocument", true, false, null, null, null, null, (short)3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1099 */       dispatchEvent((Event)ev);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireDOMCharacterDataModifiedEvent(String oldv, String newv) {
/* 1108 */     AbstractDocument doc = getCurrentDocument();
/* 1109 */     if (doc.getEventsEnabled()) {
/* 1110 */       DOMMutationEvent ev = (DOMMutationEvent)doc.createEvent("MutationEvents");
/*      */       
/* 1112 */       ev.initMutationEventNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", true, false, null, oldv, newv, null, (short)1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1121 */       dispatchEvent((Event)ev);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractDocument getCurrentDocument() {
/* 1129 */     return this.ownerDocument;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract Node newNode();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node export(Node n, AbstractDocument d) {
/* 1141 */     AbstractNode p = (AbstractNode)n;
/* 1142 */     p.ownerDocument = d;
/* 1143 */     p.setReadonly(false);
/* 1144 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node deepExport(Node n, AbstractDocument d) {
/* 1151 */     AbstractNode p = (AbstractNode)n;
/* 1152 */     p.ownerDocument = d;
/* 1153 */     p.setReadonly(false);
/* 1154 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node copyInto(Node n) {
/* 1162 */     AbstractNode an = (AbstractNode)n;
/* 1163 */     an.ownerDocument = this.ownerDocument;
/* 1164 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node deepCopyInto(Node n) {
/* 1172 */     AbstractNode an = (AbstractNode)n;
/* 1173 */     an.ownerDocument = this.ownerDocument;
/* 1174 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkChildType(Node n, boolean replace) {
/* 1181 */     throw createDOMException((short)3, "children.not.allowed", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
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
/*      */   public Node getXblParentNode() {
/* 1193 */     return this.ownerDocument.getXBLManager().getXblParentNode(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblChildNodes() {
/* 1200 */     return this.ownerDocument.getXBLManager().getXblChildNodes(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblScopedChildNodes() {
/* 1208 */     return this.ownerDocument.getXBLManager().getXblScopedChildNodes(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblFirstChild() {
/* 1215 */     return this.ownerDocument.getXBLManager().getXblFirstChild(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblLastChild() {
/* 1222 */     return this.ownerDocument.getXBLManager().getXblLastChild(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblPreviousSibling() {
/* 1230 */     return this.ownerDocument.getXBLManager().getXblPreviousSibling(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblNextSibling() {
/* 1238 */     return this.ownerDocument.getXBLManager().getXblNextSibling(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblFirstElementChild() {
/* 1245 */     return this.ownerDocument.getXBLManager().getXblFirstElementChild(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblLastElementChild() {
/* 1252 */     return this.ownerDocument.getXBLManager().getXblLastElementChild(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblPreviousElementSibling() {
/* 1260 */     return this.ownerDocument.getXBLManager().getXblPreviousElementSibling(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblNextElementSibling() {
/* 1268 */     return this.ownerDocument.getXBLManager().getXblNextElementSibling(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblBoundElement() {
/* 1275 */     return this.ownerDocument.getXBLManager().getXblBoundElement(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblShadowTree() {
/* 1282 */     return this.ownerDocument.getXBLManager().getXblShadowTree(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblDefinitions() {
/* 1289 */     return this.ownerDocument.getXBLManager().getXblDefinitions(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getManagerData() {
/* 1298 */     return this.managerData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setManagerData(Object data) {
/* 1305 */     this.managerData = data;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */