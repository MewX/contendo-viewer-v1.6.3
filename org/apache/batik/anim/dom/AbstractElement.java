/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSNavigableNode;
/*     */ import org.apache.batik.dom.AbstractAttr;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.AbstractElement;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.svg.LiveAttributeValue;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractElement
/*     */   extends AbstractElement
/*     */   implements CSSNavigableNode, NodeEventTarget, SVGConstants
/*     */ {
/*  49 */   protected transient DoublyIndexedTable liveAttributeValues = new DoublyIndexedTable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractElement(String prefix, AbstractDocument owner) {
/*  64 */     this.ownerDocument = owner;
/*  65 */     setPrefix(prefix);
/*  66 */     initializeAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSParentNode() {
/*  75 */     return getXblParentNode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSPreviousSibling() {
/*  82 */     return getXblPreviousSibling();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSNextSibling() {
/*  89 */     return getXblNextSibling();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSFirstChild() {
/*  96 */     return getXblFirstChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSLastChild() {
/* 103 */     return getXblLastChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHiddenFromSelectors() {
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireDOMAttrModifiedEvent(String name, Attr node, String oldv, String newv, short change) {
/* 118 */     super.fireDOMAttrModifiedEvent(name, node, oldv, newv, change);
/*     */ 
/*     */     
/* 121 */     if (((SVGOMDocument)this.ownerDocument).isSVG12 && (change == 2 || change == 1))
/*     */     {
/*     */       
/* 124 */       if (node.getNamespaceURI() == null && node.getNodeName().equals("id")) {
/*     */         
/* 126 */         Attr a = getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "id");
/*     */         
/* 128 */         if (a == null) {
/* 129 */           setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:id", newv);
/* 130 */         } else if (!a.getNodeValue().equals(newv)) {
/* 131 */           a.setNodeValue(newv);
/*     */         } 
/* 133 */       } else if (node.getNodeName().equals("xml:id")) {
/* 134 */         Attr a = getAttributeNodeNS(null, "id");
/* 135 */         if (a == null) {
/* 136 */           setAttributeNS(null, "id", newv);
/* 137 */         } else if (!a.getNodeValue().equals(newv)) {
/* 138 */           a.setNodeValue(newv);
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
/*     */   public LiveAttributeValue getLiveAttributeValue(String ns, String ln) {
/* 154 */     return (LiveAttributeValue)this.liveAttributeValues.get(ns, ln);
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
/*     */   public void putLiveAttributeValue(String ns, String ln, LiveAttributeValue val) {
/* 168 */     this.liveAttributeValues.put(ns, ln, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAttributes() {
/* 183 */     AttributeInitializer ai = getAttributeInitializer();
/* 184 */     if (ai != null) {
/* 185 */       ai.initializeAttributes(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean resetAttribute(String ns, String prefix, String ln) {
/* 194 */     AttributeInitializer ai = getAttributeInitializer();
/* 195 */     if (ai == null) {
/* 196 */       return false;
/*     */     }
/* 198 */     return ai.resetAttribute(this, ns, prefix, ln);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected NamedNodeMap createAttributes() {
/* 205 */     return (NamedNodeMap)new ExtendedNamedNodeHashMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnspecifiedAttribute(String nsURI, String name, String value) {
/* 216 */     if (this.attributes == null) {
/* 217 */       this.attributes = createAttributes();
/*     */     }
/* 219 */     ((ExtendedNamedNodeHashMap)this.attributes).setUnspecifiedAttribute(nsURI, name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attrAdded(Attr node, String newv) {
/* 227 */     LiveAttributeValue lav = getLiveAttributeValue(node);
/* 228 */     if (lav != null) {
/* 229 */       lav.attrAdded(node, newv);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attrModified(Attr node, String oldv, String newv) {
/* 237 */     LiveAttributeValue lav = getLiveAttributeValue(node);
/* 238 */     if (lav != null) {
/* 239 */       lav.attrModified(node, oldv, newv);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attrRemoved(Attr node, String oldv) {
/* 247 */     LiveAttributeValue lav = getLiveAttributeValue(node);
/* 248 */     if (lav != null) {
/* 249 */       lav.attrRemoved(node, oldv);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LiveAttributeValue getLiveAttributeValue(Attr node) {
/* 258 */     String ns = node.getNamespaceURI();
/* 259 */     return getLiveAttributeValue(ns, (ns == null) ? node.getNodeName() : node.getLocalName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 270 */     super.export(n, d);
/* 271 */     ((AbstractElement)n).initializeAttributes();
/*     */     
/* 273 */     super.export(n, d);
/* 274 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 281 */     super.export(n, d);
/* 282 */     ((AbstractElement)n).initializeAttributes();
/*     */     
/* 284 */     super.deepExport(n, d);
/* 285 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ExtendedNamedNodeHashMap
/*     */     extends AbstractElement.NamedNodeHashMap
/*     */   {
/*     */     public ExtendedNamedNodeHashMap() {
/* 296 */       super(AbstractElement.this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setUnspecifiedAttribute(String nsURI, String name, String value) {
/* 308 */       Attr attr = AbstractElement.this.getOwnerDocument().createAttributeNS(nsURI, name);
/* 309 */       attr.setValue(value);
/* 310 */       ((AbstractAttr)attr).setSpecified(false);
/* 311 */       setNamedItemNS(attr);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
/* 319 */       if (AbstractElement.this.isReadonly()) {
/* 320 */         throw AbstractElement.this.createDOMException((short)7, "readonly.node.map", new Object[0]);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 325 */       if (localName == null) {
/* 326 */         throw AbstractElement.this.createDOMException((short)8, "attribute.missing", new Object[] { "" });
/*     */       }
/*     */ 
/*     */       
/* 330 */       AbstractAttr n = (AbstractAttr)remove(namespaceURI, localName);
/* 331 */       if (n == null) {
/* 332 */         throw AbstractElement.this.createDOMException((short)8, "attribute.missing", new Object[] { localName });
/*     */       }
/*     */ 
/*     */       
/* 336 */       n.setOwnerElement(null);
/* 337 */       String prefix = n.getPrefix();
/*     */ 
/*     */       
/* 340 */       if (!AbstractElement.this.resetAttribute(namespaceURI, prefix, localName))
/*     */       {
/* 342 */         AbstractElement.this.fireDOMAttrModifiedEvent(n.getNodeName(), (Attr)n, n.getNodeValue(), "", (short)3);
/*     */       }
/*     */ 
/*     */       
/* 346 */       return (Node)n;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AbstractElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */