/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.batik.dom.events.DOMMutationEvent;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.events.Event;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractParentNode
/*     */   extends AbstractNode
/*     */ {
/*     */   protected ChildNodes childNodes;
/*     */   
/*     */   public NodeList getChildNodes() {
/*  49 */     return (this.childNodes == null) ? (this.childNodes = new ChildNodes()) : this.childNodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getFirstChild() {
/*  59 */     return (this.childNodes == null) ? null : this.childNodes.firstChild;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getLastChild() {
/*  67 */     return (this.childNodes == null) ? null : this.childNodes.lastChild;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException {
/*  76 */     if (refChild != null && (this.childNodes == null || refChild.getParentNode() != this))
/*     */     {
/*  78 */       throw createDOMException((short)8, "child.missing", new Object[] { Integer.valueOf(refChild.getNodeType()), refChild.getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     checkAndRemove(newChild, false);
/*     */     
/*  86 */     if (newChild.getNodeType() == 11) {
/*  87 */       Node node = newChild.getFirstChild();
/*  88 */       while (node != null) {
/*  89 */         Node ns = node.getNextSibling();
/*  90 */         insertBefore(node, refChild);
/*  91 */         node = ns;
/*     */       } 
/*  93 */       return newChild;
/*     */     } 
/*     */     
/*  96 */     if (this.childNodes == null) {
/*  97 */       this.childNodes = new ChildNodes();
/*     */     }
/*  99 */     ExtendedNode n = this.childNodes.insert((ExtendedNode)newChild, (ExtendedNode)refChild);
/*     */     
/* 101 */     n.setParentNode(this);
/*     */     
/* 103 */     nodeAdded(n);
/*     */ 
/*     */     
/* 106 */     fireDOMNodeInsertedEvent(n);
/* 107 */     fireDOMSubtreeModifiedEvent();
/* 108 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
/* 118 */     if (this.childNodes == null || oldChild.getParentNode() != this) {
/* 119 */       throw createDOMException((short)8, "child.missing", new Object[] { Integer.valueOf(oldChild.getNodeType()), oldChild.getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     checkAndRemove(newChild, true);
/*     */     
/* 127 */     if (newChild.getNodeType() == 11) {
/* 128 */       Node node1 = newChild.getLastChild();
/* 129 */       if (node1 == null) {
/* 130 */         return newChild;
/*     */       }
/* 132 */       Node ps = node1.getPreviousSibling();
/* 133 */       replaceChild(node1, oldChild);
/* 134 */       Node ns = node1;
/* 135 */       node1 = ps;
/* 136 */       while (node1 != null) {
/* 137 */         ps = node1.getPreviousSibling();
/* 138 */         insertBefore(node1, ns);
/* 139 */         ns = node1;
/* 140 */         node1 = ps;
/*     */       } 
/*     */       
/* 143 */       return newChild;
/*     */     } 
/*     */ 
/*     */     
/* 147 */     fireDOMNodeRemovedEvent(oldChild);
/*     */     
/* 149 */     getCurrentDocument().nodeToBeRemoved(oldChild);
/* 150 */     nodeToBeRemoved(oldChild);
/*     */ 
/*     */     
/* 153 */     ExtendedNode n = (ExtendedNode)newChild;
/* 154 */     ExtendedNode o = this.childNodes.replace(n, (ExtendedNode)oldChild);
/* 155 */     n.setParentNode(this);
/* 156 */     o.setParentNode((Node)null);
/*     */     
/* 158 */     nodeAdded(n);
/*     */ 
/*     */     
/* 161 */     fireDOMNodeInsertedEvent(n);
/* 162 */     fireDOMSubtreeModifiedEvent();
/* 163 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node removeChild(Node oldChild) throws DOMException {
/* 170 */     if (this.childNodes == null || oldChild.getParentNode() != this) {
/* 171 */       throw createDOMException((short)8, "child.missing", new Object[] { Integer.valueOf(oldChild.getNodeType()), oldChild.getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     if (isReadonly()) {
/* 178 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     fireDOMNodeRemovedEvent(oldChild);
/*     */     
/* 187 */     getCurrentDocument().nodeToBeRemoved(oldChild);
/* 188 */     nodeToBeRemoved(oldChild);
/*     */ 
/*     */     
/* 191 */     ExtendedNode result = this.childNodes.remove((ExtendedNode)oldChild);
/* 192 */     result.setParentNode((Node)null);
/*     */ 
/*     */     
/* 195 */     fireDOMSubtreeModifiedEvent();
/* 196 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node appendChild(Node newChild) throws DOMException {
/* 203 */     checkAndRemove(newChild, false);
/*     */     
/* 205 */     if (newChild.getNodeType() == 11) {
/* 206 */       Node node = newChild.getFirstChild();
/* 207 */       while (node != null) {
/* 208 */         Node ns = node.getNextSibling();
/* 209 */         appendChild(node);
/* 210 */         node = ns;
/*     */       } 
/* 212 */       return newChild;
/*     */     } 
/* 214 */     if (this.childNodes == null) {
/* 215 */       this.childNodes = new ChildNodes();
/*     */     }
/*     */     
/* 218 */     ExtendedNode n = this.childNodes.append((ExtendedNode)newChild);
/* 219 */     n.setParentNode(this);
/*     */     
/* 221 */     nodeAdded(n);
/*     */ 
/*     */     
/* 224 */     fireDOMNodeInsertedEvent(n);
/* 225 */     fireDOMSubtreeModifiedEvent();
/* 226 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasChildNodes() {
/* 235 */     return (this.childNodes != null && this.childNodes.getLength() != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void normalize() {
/* 242 */     Node p = getFirstChild();
/* 243 */     if (p != null) {
/* 244 */       p.normalize();
/* 245 */       Node n = p.getNextSibling();
/* 246 */       while (n != null) {
/* 247 */         if (p.getNodeType() == 3 && n.getNodeType() == 3) {
/*     */           
/* 249 */           String s = p.getNodeValue() + n.getNodeValue();
/* 250 */           AbstractText at = (AbstractText)p;
/* 251 */           at.setNodeValue(s);
/* 252 */           removeChild(n);
/* 253 */           n = p.getNextSibling(); continue;
/*     */         } 
/* 255 */         n.normalize();
/* 256 */         p = n;
/* 257 */         n = n.getNextSibling();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getElementsByTagName(String name) {
/* 268 */     if (name == null) {
/* 269 */       return EMPTY_NODE_LIST;
/*     */     }
/* 271 */     AbstractDocument ad = getCurrentDocument();
/* 272 */     ElementsByTagName result = ad.getElementsByTagName(this, name);
/* 273 */     if (result == null) {
/* 274 */       result = new ElementsByTagName(name);
/* 275 */       ad.putElementsByTagName(this, name, result);
/*     */     } 
/* 277 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
/* 286 */     if (localName == null) {
/* 287 */       return EMPTY_NODE_LIST;
/*     */     }
/* 289 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/* 290 */       namespaceURI = null;
/*     */     }
/* 292 */     AbstractDocument ad = getCurrentDocument();
/* 293 */     ElementsByTagNameNS result = ad.getElementsByTagNameNS(this, namespaceURI, localName);
/*     */ 
/*     */     
/* 296 */     if (result == null) {
/* 297 */       result = new ElementsByTagNameNS(namespaceURI, localName);
/* 298 */       ad.putElementsByTagNameNS(this, namespaceURI, localName, result);
/*     */     } 
/* 300 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTextContent() {
/* 307 */     StringBuffer sb = new StringBuffer();
/* 308 */     for (Node n = getFirstChild(); n != null; n = n.getNextSibling()) {
/* 309 */       switch (n.getNodeType()) {
/*     */         case 7:
/*     */         case 8:
/*     */           break;
/*     */         default:
/* 314 */           sb.append(((AbstractNode)n).getTextContent()); break;
/*     */       } 
/*     */     } 
/* 317 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireDOMNodeInsertedIntoDocumentEvent() {
/* 324 */     AbstractDocument doc = getCurrentDocument();
/* 325 */     if (doc.getEventsEnabled()) {
/* 326 */       super.fireDOMNodeInsertedIntoDocumentEvent();
/* 327 */       for (Node n = getFirstChild(); n != null; n = n.getNextSibling()) {
/* 328 */         ((AbstractNode)n).fireDOMNodeInsertedIntoDocumentEvent();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireDOMNodeRemovedFromDocumentEvent() {
/* 337 */     AbstractDocument doc = getCurrentDocument();
/* 338 */     if (doc.getEventsEnabled()) {
/* 339 */       super.fireDOMNodeRemovedFromDocumentEvent();
/* 340 */       for (Node n = getFirstChild(); n != null; n = n.getNextSibling()) {
/* 341 */         ((AbstractNode)n).fireDOMNodeRemovedFromDocumentEvent();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void nodeAdded(Node n) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void nodeToBeRemoved(Node n) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 362 */     super.deepExport(n, d);
/* 363 */     for (Node p = getFirstChild(); p != null; p = p.getNextSibling()) {
/* 364 */       Node t = ((AbstractNode)p).deepExport(p.cloneNode(false), d);
/* 365 */       n.appendChild(t);
/*     */     } 
/* 367 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 375 */     super.deepCopyInto(n);
/* 376 */     for (Node p = getFirstChild(); p != null; p = p.getNextSibling()) {
/* 377 */       Node t = p.cloneNode(true);
/* 378 */       n.appendChild(t);
/*     */     } 
/* 380 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireDOMSubtreeModifiedEvent() {
/* 387 */     AbstractDocument doc = getCurrentDocument();
/* 388 */     if (doc.getEventsEnabled()) {
/* 389 */       DOMMutationEvent ev = (DOMMutationEvent)doc.createEvent("MutationEvents");
/*     */       
/* 391 */       ev.initMutationEventNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", true, false, null, null, null, null, (short)1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 400 */       dispatchEvent((Event)ev);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireDOMNodeInsertedEvent(Node node) {
/* 408 */     AbstractDocument doc = getCurrentDocument();
/* 409 */     if (doc.getEventsEnabled()) {
/* 410 */       DOMMutationEvent ev = (DOMMutationEvent)doc.createEvent("MutationEvents");
/*     */       
/* 412 */       ev.initMutationEventNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", true, false, this, null, null, null, (short)2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 421 */       AbstractNode n = (AbstractNode)node;
/* 422 */       n.dispatchEvent((Event)ev);
/* 423 */       n.fireDOMNodeInsertedIntoDocumentEvent();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireDOMNodeRemovedEvent(Node node) {
/* 431 */     AbstractDocument doc = getCurrentDocument();
/* 432 */     if (doc.getEventsEnabled()) {
/* 433 */       DOMMutationEvent ev = (DOMMutationEvent)doc.createEvent("MutationEvents");
/*     */       
/* 435 */       ev.initMutationEventNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", true, false, this, null, null, null, (short)3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 444 */       AbstractNode n = (AbstractNode)node;
/* 445 */       n.dispatchEvent((Event)ev);
/* 446 */       n.fireDOMNodeRemovedFromDocumentEvent();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkAndRemove(Node n, boolean replace) {
/* 455 */     checkChildType(n, replace);
/*     */     
/* 457 */     if (isReadonly()) {
/* 458 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 463 */     if (n.getOwnerDocument() != getCurrentDocument()) {
/* 464 */       throw createDOMException((short)4, "node.from.wrong.document", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */     
/* 468 */     if (this == n) {
/* 469 */       throw createDOMException((short)3, "add.self", new Object[] { getNodeName() });
/*     */     }
/*     */ 
/*     */     
/* 473 */     Node np = n.getParentNode();
/* 474 */     if (np == null) {
/*     */       return;
/*     */     }
/* 477 */     for (Node pn = this; pn != null; pn = pn.getParentNode()) {
/* 478 */       if (pn == n) {
/* 479 */         throw createDOMException((short)3, "add.ancestor", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 487 */     np.removeChild(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ElementsByTagName
/*     */     implements NodeList
/*     */   {
/*     */     protected Node[] table;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 503 */     protected int size = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String name;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ElementsByTagName(String n) {
/* 514 */       this.name = n;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node item(int index) {
/* 521 */       if (this.size == -1) {
/* 522 */         initialize();
/*     */       }
/* 524 */       if (this.table == null || index < 0 || index >= this.size) {
/* 525 */         return null;
/*     */       }
/* 527 */       return this.table[index];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLength() {
/* 535 */       if (this.size == -1) {
/* 536 */         initialize();
/*     */       }
/* 538 */       return this.size;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void invalidate() {
/* 545 */       this.size = -1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void append(Node n) {
/* 552 */       if (this.table == null) {
/* 553 */         this.table = new Node[11];
/* 554 */       } else if (this.size == this.table.length - 1) {
/* 555 */         Node[] t = new Node[this.table.length * 2 + 1];
/* 556 */         System.arraycopy(this.table, 0, t, 0, this.size);
/* 557 */         this.table = t;
/*     */       } 
/* 559 */       this.table[this.size++] = n;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void initialize() {
/* 566 */       this.size = 0;
/* 567 */       Node n = AbstractParentNode.this.getFirstChild();
/* 568 */       for (; n != null; 
/* 569 */         n = n.getNextSibling()) {
/* 570 */         initialize(n);
/*     */       }
/*     */     }
/*     */     
/*     */     private void initialize(Node node) {
/* 575 */       if (node.getNodeType() == 1) {
/* 576 */         String nm = node.getNodeName();
/* 577 */         if (this.name.equals("*") || this.name.equals(nm)) {
/* 578 */           append(node);
/*     */         }
/*     */       } 
/* 581 */       Node n = node.getFirstChild();
/* 582 */       for (; n != null; 
/* 583 */         n = n.getNextSibling()) {
/* 584 */         initialize(n);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ElementsByTagNameNS
/*     */     implements NodeList
/*     */   {
/*     */     protected Node[] table;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 602 */     protected int size = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String namespaceURI;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String localName;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ElementsByTagNameNS(String ns, String ln) {
/* 618 */       this.namespaceURI = ns;
/* 619 */       this.localName = ln;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node item(int index) {
/* 626 */       if (this.size == -1) {
/* 627 */         initialize();
/*     */       }
/* 629 */       if (this.table == null || index < 0 || index > this.size) {
/* 630 */         return null;
/*     */       }
/* 632 */       return this.table[index];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLength() {
/* 640 */       if (this.size == -1) {
/* 641 */         initialize();
/*     */       }
/* 643 */       return this.size;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void invalidate() {
/* 650 */       this.size = -1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void append(Node n) {
/* 657 */       if (this.table == null) {
/* 658 */         this.table = new Node[11];
/* 659 */       } else if (this.size == this.table.length - 1) {
/* 660 */         Node[] t = new Node[this.table.length * 2 + 1];
/* 661 */         System.arraycopy(this.table, 0, t, 0, this.size);
/* 662 */         this.table = t;
/*     */       } 
/* 664 */       this.table[this.size++] = n;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void initialize() {
/* 671 */       this.size = 0;
/* 672 */       Node n = AbstractParentNode.this.getFirstChild();
/* 673 */       for (; n != null; 
/* 674 */         n = n.getNextSibling()) {
/* 675 */         initialize(n);
/*     */       }
/*     */     }
/*     */     
/*     */     private void initialize(Node node) {
/* 680 */       if (node.getNodeType() == 1) {
/* 681 */         String ns = node.getNamespaceURI();
/* 682 */         String nm = (ns == null) ? node.getNodeName() : node.getLocalName();
/*     */ 
/*     */         
/* 685 */         if (nsMatch(this.namespaceURI, node.getNamespaceURI()) && (this.localName.equals("*") || this.localName.equals(nm)))
/*     */         {
/* 687 */           append(node);
/*     */         }
/*     */       } 
/* 690 */       Node n = node.getFirstChild();
/* 691 */       for (; n != null; 
/* 692 */         n = n.getNextSibling()) {
/* 693 */         initialize(n);
/*     */       }
/*     */     }
/*     */     
/*     */     private boolean nsMatch(String s1, String s2) {
/* 698 */       if (s1 == null && s2 == null) {
/* 699 */         return true;
/*     */       }
/* 701 */       if (s1 == null || s2 == null) {
/* 702 */         return false;
/*     */       }
/* 704 */       if (s1.equals("*")) {
/* 705 */         return true;
/*     */       }
/* 707 */       return s1.equals(s2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ChildNodes
/*     */     implements Serializable, NodeList
/*     */   {
/*     */     protected ExtendedNode firstChild;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected ExtendedNode lastChild;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int children;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int elementChildren;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node item(int index) {
/* 745 */       if (index < 0 || index >= this.children) {
/* 746 */         return null;
/*     */       }
/* 748 */       if (index < this.children >> 1) {
/* 749 */         Node node = this.firstChild;
/* 750 */         for (int j = 0; j < index; j++) {
/* 751 */           node = node.getNextSibling();
/*     */         }
/* 753 */         return node;
/*     */       } 
/* 755 */       Node n = this.lastChild;
/* 756 */       for (int i = this.children - 1; i > index; i--) {
/* 757 */         n = n.getPreviousSibling();
/*     */       }
/* 759 */       return n;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLength() {
/* 768 */       return this.children;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ExtendedNode append(ExtendedNode n) {
/* 776 */       if (this.lastChild == null) {
/* 777 */         this.firstChild = n;
/*     */       } else {
/* 779 */         this.lastChild.setNextSibling(n);
/* 780 */         n.setPreviousSibling(this.lastChild);
/*     */       } 
/* 782 */       this.lastChild = n;
/* 783 */       this.children++;
/* 784 */       if (n.getNodeType() == 1) {
/* 785 */         this.elementChildren++;
/*     */       }
/* 787 */       return n;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ExtendedNode insert(ExtendedNode n, ExtendedNode r) {
/* 794 */       if (r == null) {
/* 795 */         return append(n);
/*     */       }
/*     */       
/* 798 */       if (r == this.firstChild) {
/* 799 */         this.firstChild.setPreviousSibling(n);
/* 800 */         n.setNextSibling(this.firstChild);
/* 801 */         this.firstChild = n;
/* 802 */         this.children++;
/* 803 */         if (n.getNodeType() == 1) {
/* 804 */           this.elementChildren++;
/*     */         }
/* 806 */         return n;
/*     */       } 
/* 808 */       if (r == this.lastChild) {
/* 809 */         ExtendedNode extendedNode = (ExtendedNode)r.getPreviousSibling();
/* 810 */         extendedNode.setNextSibling(n);
/* 811 */         r.setPreviousSibling(n);
/* 812 */         n.setNextSibling(r);
/* 813 */         n.setPreviousSibling(extendedNode);
/* 814 */         this.children++;
/* 815 */         if (n.getNodeType() == 1) {
/* 816 */           this.elementChildren++;
/*     */         }
/* 818 */         return n;
/*     */       } 
/*     */       
/* 821 */       ExtendedNode ps = (ExtendedNode)r.getPreviousSibling();
/* 822 */       if (ps.getNextSibling() == r && ps.getParentNode() == r.getParentNode()) {
/*     */         
/* 824 */         ps.setNextSibling(n);
/* 825 */         n.setPreviousSibling(ps);
/* 826 */         n.setNextSibling(r);
/* 827 */         r.setPreviousSibling(n);
/* 828 */         this.children++;
/* 829 */         if (n.getNodeType() == 1) {
/* 830 */           this.elementChildren++;
/*     */         }
/* 832 */         return n;
/*     */       } 
/*     */       
/* 835 */       throw AbstractParentNode.this.createDOMException((short)8, "child.missing", new Object[] { Integer.valueOf(r.getNodeType()), r.getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ExtendedNode replace(ExtendedNode n, ExtendedNode o) {
/* 846 */       if (o == this.firstChild) {
/* 847 */         ExtendedNode t = (ExtendedNode)this.firstChild.getNextSibling();
/* 848 */         n.setNextSibling(t);
/* 849 */         if (o == this.lastChild) {
/* 850 */           this.lastChild = n;
/*     */         } else {
/* 852 */           t.setPreviousSibling(n);
/*     */         } 
/* 854 */         this.firstChild.setNextSibling((Node)null);
/* 855 */         this.firstChild = n;
/* 856 */         if (o.getNodeType() == 1) {
/* 857 */           this.elementChildren--;
/*     */         }
/* 859 */         if (n.getNodeType() == 1) {
/* 860 */           this.elementChildren++;
/*     */         }
/* 862 */         return o;
/*     */       } 
/*     */       
/* 865 */       if (o == this.lastChild) {
/* 866 */         ExtendedNode t = (ExtendedNode)this.lastChild.getPreviousSibling();
/* 867 */         n.setPreviousSibling(t);
/* 868 */         t.setNextSibling(n);
/* 869 */         this.lastChild.setPreviousSibling((Node)null);
/* 870 */         this.lastChild = n;
/* 871 */         if (o.getNodeType() == 1) {
/* 872 */           this.elementChildren--;
/*     */         }
/* 874 */         if (n.getNodeType() == 1) {
/* 875 */           this.elementChildren++;
/*     */         }
/* 877 */         return o;
/*     */       } 
/*     */       
/* 880 */       ExtendedNode ps = (ExtendedNode)o.getPreviousSibling();
/* 881 */       ExtendedNode ns = (ExtendedNode)o.getNextSibling();
/* 882 */       if (ps.getNextSibling() == o && ns.getPreviousSibling() == o && ps.getParentNode() == o.getParentNode() && ns.getParentNode() == o.getParentNode()) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 887 */         ps.setNextSibling(n);
/* 888 */         n.setPreviousSibling(ps);
/* 889 */         n.setNextSibling(ns);
/* 890 */         ns.setPreviousSibling(n);
/* 891 */         o.setPreviousSibling((Node)null);
/* 892 */         o.setNextSibling((Node)null);
/* 893 */         if (o.getNodeType() == 1) {
/* 894 */           this.elementChildren--;
/*     */         }
/* 896 */         if (n.getNodeType() == 1) {
/* 897 */           this.elementChildren++;
/*     */         }
/* 899 */         return o;
/*     */       } 
/* 901 */       throw AbstractParentNode.this.createDOMException((short)8, "child.missing", new Object[] { Integer.valueOf(o.getNodeType()), o.getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ExtendedNode remove(ExtendedNode n) {
/* 912 */       if (n == this.firstChild) {
/* 913 */         if (n == this.lastChild) {
/* 914 */           this.firstChild = null;
/* 915 */           this.lastChild = null;
/* 916 */           this.children--;
/* 917 */           if (n.getNodeType() == 1) {
/* 918 */             this.elementChildren--;
/*     */           }
/* 920 */           return n;
/*     */         } 
/* 922 */         this.firstChild = (ExtendedNode)this.firstChild.getNextSibling();
/* 923 */         this.firstChild.setPreviousSibling((Node)null);
/* 924 */         n.setNextSibling((Node)null);
/* 925 */         if (n.getNodeType() == 1) {
/* 926 */           this.elementChildren--;
/*     */         }
/* 928 */         this.children--;
/* 929 */         return n;
/*     */       } 
/*     */       
/* 932 */       if (n == this.lastChild) {
/* 933 */         this.lastChild = (ExtendedNode)this.lastChild.getPreviousSibling();
/* 934 */         this.lastChild.setNextSibling((Node)null);
/* 935 */         n.setPreviousSibling((Node)null);
/* 936 */         this.children--;
/* 937 */         if (n.getNodeType() == 1) {
/* 938 */           this.elementChildren--;
/*     */         }
/* 940 */         return n;
/*     */       } 
/*     */       
/* 943 */       ExtendedNode ps = (ExtendedNode)n.getPreviousSibling();
/* 944 */       ExtendedNode ns = (ExtendedNode)n.getNextSibling();
/* 945 */       if (ps.getNextSibling() == n && ns.getPreviousSibling() == n && ps.getParentNode() == n.getParentNode() && ns.getParentNode() == n.getParentNode()) {
/*     */ 
/*     */ 
/*     */         
/* 949 */         ps.setNextSibling(ns);
/* 950 */         ns.setPreviousSibling(ps);
/* 951 */         n.setPreviousSibling((Node)null);
/* 952 */         n.setNextSibling((Node)null);
/* 953 */         this.children--;
/* 954 */         if (n.getNodeType() == 1) {
/* 955 */           this.elementChildren--;
/*     */         }
/* 957 */         return n;
/*     */       } 
/* 959 */       throw AbstractParentNode.this.createDOMException((short)8, "child.missing", new Object[] { Integer.valueOf(n.getNodeType()), n.getNodeName() });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractParentNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */