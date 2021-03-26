/*      */ package org.apache.batik.dom;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.WeakHashMap;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.batik.dom.events.DocumentEventSupport;
/*      */ import org.apache.batik.dom.events.EventSupport;
/*      */ import org.apache.batik.dom.traversal.TraversalSupport;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.dom.xbl.GenericXBLManager;
/*      */ import org.apache.batik.dom.xbl.XBLManager;
/*      */ import org.apache.batik.i18n.Localizable;
/*      */ import org.apache.batik.i18n.LocalizableSupport;
/*      */ import org.apache.batik.util.CleanerThread;
/*      */ import org.apache.batik.util.SoftDoublyIndexedTable;
/*      */ import org.apache.batik.w3c.dom.events.MutationNameEvent;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xpath.XPath;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.apache.xpath.objects.XObject;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.DOMConfiguration;
/*      */ import org.w3c.dom.DOMError;
/*      */ import org.w3c.dom.DOMErrorHandler;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.DOMImplementation;
/*      */ import org.w3c.dom.DOMLocator;
/*      */ import org.w3c.dom.DOMStringList;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentType;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.events.DocumentEvent;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.traversal.DocumentTraversal;
/*      */ import org.w3c.dom.traversal.NodeFilter;
/*      */ import org.w3c.dom.traversal.NodeIterator;
/*      */ import org.w3c.dom.traversal.TreeWalker;
/*      */ import org.w3c.dom.xpath.XPathEvaluator;
/*      */ import org.w3c.dom.xpath.XPathException;
/*      */ import org.w3c.dom.xpath.XPathExpression;
/*      */ import org.w3c.dom.xpath.XPathNSResolver;
/*      */ import org.w3c.dom.xpath.XPathResult;
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
/*      */ public abstract class AbstractDocument
/*      */   extends AbstractParentNode
/*      */   implements Localizable, Document, DocumentEvent, DocumentTraversal, XPathEvaluator
/*      */ {
/*      */   protected static final String RESOURCES = "org.apache.batik.dom.resources.Messages";
/*  105 */   protected transient LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.dom.resources.Messages", getClass().getClassLoader());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient DOMImplementation implementation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient TraversalSupport traversalSupport;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient DocumentEventSupport documentEventSupport;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient boolean eventsEnabled;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient WeakHashMap elementsByTagNames;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient WeakHashMap elementsByTagNamesNS;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String inputEncoding;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String xmlEncoding;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  152 */   protected String xmlVersion = "1.0";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean xmlStandalone;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String documentURI;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean strictErrorChecking = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected DocumentConfiguration domConfig;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  177 */   protected transient XBLManager xblManager = (XBLManager)new GenericXBLManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient Map elementsById;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractDocument() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AbstractDocument(DocumentType dt, DOMImplementation impl) {
/*  198 */     this.implementation = impl;
/*  199 */     if (dt != null) {
/*  200 */       if (dt instanceof GenericDocumentType) {
/*  201 */         GenericDocumentType gdt = (GenericDocumentType)dt;
/*  202 */         if (gdt.getOwnerDocument() == null)
/*  203 */           gdt.setOwnerDocument(this); 
/*      */       } 
/*  205 */       appendChild(dt);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentInputEncoding(String ie) {
/*  214 */     this.inputEncoding = ie;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentXmlEncoding(String xe) {
/*  221 */     this.xmlEncoding = xe;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocale(Locale l) {
/*  228 */     this.localizableSupport.setLocale(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Locale getLocale() {
/*  235 */     return this.localizableSupport.getLocale();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String formatMessage(String key, Object[] args) throws MissingResourceException {
/*  244 */     return this.localizableSupport.formatMessage(key, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getEventsEnabled() {
/*  251 */     return this.eventsEnabled;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEventsEnabled(boolean b) {
/*  258 */     this.eventsEnabled = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName() {
/*  266 */     return "#document";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getNodeType() {
/*  274 */     return 9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DocumentType getDoctype() {
/*  281 */     for (Node n = getFirstChild(); n != null; n = n.getNextSibling()) {
/*  282 */       if (n.getNodeType() == 10) {
/*  283 */         return (DocumentType)n;
/*      */       }
/*      */     } 
/*  286 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoctype(DocumentType dt) {
/*  293 */     if (dt != null) {
/*  294 */       appendChild(dt);
/*  295 */       ((ExtendedNode)dt).setReadonly(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMImplementation getImplementation() {
/*  304 */     return this.implementation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getDocumentElement() {
/*  312 */     for (Node n = getFirstChild(); n != null; n = n.getNextSibling()) {
/*  313 */       if (n.getNodeType() == 1) {
/*  314 */         return (Element)n;
/*      */       }
/*      */     } 
/*  317 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node importNode(Node importedNode, boolean deep) throws DOMException {
/*  326 */     return importNode(importedNode, deep, false);
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
/*      */   public Node importNode(Node importedNode, boolean deep, boolean trimId) {
/*      */     Node result;
/*      */     Element e;
/*      */     DocumentType docType;
/*      */     GenericDocumentType copy;
/*  346 */     switch (importedNode.getNodeType()) {
/*      */       case 1:
/*  348 */         e = createElementNS(importedNode.getNamespaceURI(), importedNode.getNodeName());
/*      */         
/*  350 */         result = e;
/*  351 */         if (importedNode.hasAttributes()) {
/*  352 */           NamedNodeMap attr = importedNode.getAttributes();
/*  353 */           int len = attr.getLength();
/*  354 */           for (int i = 0; i < len; i++) {
/*  355 */             Attr a = (Attr)attr.item(i);
/*  356 */             if (a.getSpecified()) {
/*  357 */               AbstractAttr aa = (AbstractAttr)importNode(a, true);
/*  358 */               if (trimId && aa.isId())
/*  359 */                 aa.setIsId(false); 
/*  360 */               e.setAttributeNodeNS(aa);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 2:
/*  366 */         result = createAttributeNS(importedNode.getNamespaceURI(), importedNode.getNodeName());
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*  371 */         result = createTextNode(importedNode.getNodeValue());
/*  372 */         deep = false;
/*      */         break;
/*      */       
/*      */       case 4:
/*  376 */         result = createCDATASection(importedNode.getNodeValue());
/*  377 */         deep = false;
/*      */         break;
/*      */       
/*      */       case 5:
/*  381 */         result = createEntityReference(importedNode.getNodeName());
/*      */         break;
/*      */       
/*      */       case 7:
/*  385 */         result = createProcessingInstruction(importedNode.getNodeName(), importedNode.getNodeValue());
/*      */ 
/*      */         
/*  388 */         deep = false;
/*      */         break;
/*      */       
/*      */       case 8:
/*  392 */         result = createComment(importedNode.getNodeValue());
/*  393 */         deep = false;
/*      */         break;
/*      */       
/*      */       case 11:
/*  397 */         result = createDocumentFragment();
/*      */         break;
/*      */       
/*      */       case 10:
/*  401 */         docType = (DocumentType)importedNode;
/*  402 */         copy = new GenericDocumentType(docType.getName(), docType.getPublicId(), docType.getSystemId());
/*      */         
/*  404 */         copy.ownerDocument = this;
/*  405 */         result = copy;
/*      */         break;
/*      */       
/*      */       default:
/*  409 */         throw createDOMException((short)9, "import.node", new Object[0]);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  414 */     if (importedNode instanceof AbstractNode)
/*      */     {
/*      */       
/*  417 */       fireUserDataHandlers((short)2, importedNode, result);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  422 */     if (deep) {
/*  423 */       Node n = importedNode.getFirstChild();
/*  424 */       for (; n != null; 
/*  425 */         n = n.getNextSibling()) {
/*  426 */         result.appendChild(importNode(n, true));
/*      */       }
/*      */     } 
/*  429 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  436 */     Document n = (Document)newNode();
/*  437 */     copyInto(n);
/*  438 */     fireUserDataHandlers((short)1, this, n);
/*  439 */     if (deep) {
/*  440 */       Node c = getFirstChild();
/*  441 */       for (; c != null; 
/*  442 */         c = c.getNextSibling()) {
/*  443 */         n.appendChild(n.importNode(c, deep));
/*      */       }
/*      */     } 
/*  446 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract boolean isId(Attr paramAttr);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getElementById(String id) {
/*  459 */     return getChildElementById(getDocumentElement(), id);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getChildElementById(Node requestor, String id) {
/*  467 */     if (id == null || id.length() == 0) return null; 
/*  468 */     if (this.elementsById == null) return null;
/*      */     
/*  470 */     Node root = getRoot(requestor);
/*      */     
/*  472 */     Object o = this.elementsById.get(id);
/*  473 */     if (o == null) return null; 
/*  474 */     if (o instanceof IdSoftRef) {
/*  475 */       o = ((IdSoftRef)o).get();
/*  476 */       if (o == null) {
/*  477 */         this.elementsById.remove(id);
/*  478 */         return null;
/*      */       } 
/*  480 */       Element e = (Element)o;
/*  481 */       if (getRoot(e) == root)
/*  482 */         return e; 
/*  483 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  487 */     List l = (List)o;
/*  488 */     Iterator<IdSoftRef> li = l.iterator();
/*  489 */     while (li.hasNext()) {
/*  490 */       IdSoftRef sr = li.next();
/*  491 */       o = sr.get();
/*  492 */       if (o == null) {
/*  493 */         li.remove(); continue;
/*      */       } 
/*  495 */       Element e = (Element)o;
/*  496 */       if (getRoot(e) == root) {
/*  497 */         return e;
/*      */       }
/*      */     } 
/*  500 */     return null;
/*      */   }
/*      */   
/*      */   protected Node getRoot(Node n) {
/*  504 */     Node r = n;
/*  505 */     while (n != null) {
/*  506 */       r = n;
/*  507 */       n = n.getParentNode();
/*      */     } 
/*  509 */     return r;
/*      */   }
/*      */   
/*      */   protected class IdSoftRef extends CleanerThread.SoftReferenceCleared { String id;
/*      */     List list;
/*      */     
/*      */     IdSoftRef(Object o, String id) {
/*  516 */       super(o);
/*  517 */       this.id = id;
/*      */     }
/*      */     IdSoftRef(Object o, String id, List list) {
/*  520 */       super(o);
/*  521 */       this.id = id;
/*  522 */       this.list = list;
/*      */     }
/*      */     public void setList(List list) {
/*  525 */       this.list = list;
/*      */     }
/*      */     public void cleared() {
/*  528 */       if (AbstractDocument.this.elementsById == null)
/*  529 */         return;  synchronized (AbstractDocument.this.elementsById) {
/*  530 */         if (this.list != null) {
/*  531 */           this.list.remove(this);
/*      */         } else {
/*  533 */           Object o = AbstractDocument.this.elementsById.remove(this.id);
/*  534 */           if (o != this) {
/*  535 */             AbstractDocument.this.elementsById.put(this.id, o);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeIdEntry(Element e, String id) {
/*  546 */     if (id == null)
/*  547 */       return;  if (this.elementsById == null)
/*      */       return; 
/*  549 */     synchronized (this.elementsById) {
/*  550 */       Object o = this.elementsById.get(id);
/*  551 */       if (o == null)
/*      */         return; 
/*  553 */       if (o instanceof IdSoftRef) {
/*  554 */         this.elementsById.remove(id);
/*      */         
/*      */         return;
/*      */       } 
/*  558 */       List l = (List)o;
/*  559 */       Iterator<IdSoftRef> li = l.iterator();
/*  560 */       while (li.hasNext()) {
/*  561 */         IdSoftRef ip = li.next();
/*  562 */         o = ip.get();
/*  563 */         if (o == null) {
/*  564 */           li.remove(); continue;
/*  565 */         }  if (e == o) {
/*  566 */           li.remove();
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  571 */       if (l.size() == 0)
/*  572 */         this.elementsById.remove(id); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addIdEntry(Element e, String id) {
/*  577 */     if (id == null)
/*      */       return; 
/*  579 */     if (this.elementsById == null) {
/*  580 */       Map<Object, Object> tmp = new HashMap<Object, Object>();
/*  581 */       tmp.put(id, new IdSoftRef(e, id));
/*  582 */       this.elementsById = tmp;
/*      */       
/*      */       return;
/*      */     } 
/*  586 */     synchronized (this.elementsById) {
/*      */       
/*  588 */       Object o = this.elementsById.get(id);
/*  589 */       if (o == null) {
/*  590 */         this.elementsById.put(id, new IdSoftRef(e, id));
/*      */         return;
/*      */       } 
/*  593 */       if (o instanceof IdSoftRef) {
/*  594 */         IdSoftRef ip = (IdSoftRef)o;
/*  595 */         Object r = ip.get();
/*  596 */         if (r == null) {
/*  597 */           this.elementsById.put(id, new IdSoftRef(e, id));
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  602 */         List<IdSoftRef> list = new ArrayList(4);
/*  603 */         ip.setList(list);
/*  604 */         list.add(ip);
/*  605 */         list.add(new IdSoftRef(e, id, list));
/*  606 */         this.elementsById.put(id, list);
/*      */         
/*      */         return;
/*      */       } 
/*  610 */       List<IdSoftRef> l = (List)o;
/*  611 */       l.add(new IdSoftRef(e, id, l));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void updateIdEntry(Element e, String oldId, String newId) {
/*  616 */     if (oldId == newId || (oldId != null && oldId.equals(newId))) {
/*      */       return;
/*      */     }
/*      */     
/*  620 */     removeIdEntry(e, oldId);
/*      */     
/*  622 */     addIdEntry(e, newId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AbstractParentNode.ElementsByTagName getElementsByTagName(Node n, String ln) {
/*  630 */     if (this.elementsByTagNames == null) {
/*  631 */       return null;
/*      */     }
/*      */     
/*  634 */     SoftDoublyIndexedTable t = (SoftDoublyIndexedTable)this.elementsByTagNames.get(n);
/*  635 */     if (t == null) {
/*  636 */       return null;
/*      */     }
/*  638 */     return (AbstractParentNode.ElementsByTagName)t.get(null, ln);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putElementsByTagName(Node n, String ln, AbstractParentNode.ElementsByTagName l) {
/*  645 */     if (this.elementsByTagNames == null) {
/*  646 */       this.elementsByTagNames = new WeakHashMap<Object, Object>(11);
/*      */     }
/*      */     
/*  649 */     SoftDoublyIndexedTable t = (SoftDoublyIndexedTable)this.elementsByTagNames.get(n);
/*  650 */     if (t == null) {
/*  651 */       this.elementsByTagNames.put(n, t = new SoftDoublyIndexedTable());
/*      */     }
/*  653 */     t.put(null, ln, l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AbstractParentNode.ElementsByTagNameNS getElementsByTagNameNS(Node n, String ns, String ln) {
/*  662 */     if (this.elementsByTagNamesNS == null) {
/*  663 */       return null;
/*      */     }
/*      */     
/*  666 */     SoftDoublyIndexedTable t = (SoftDoublyIndexedTable)this.elementsByTagNamesNS.get(n);
/*  667 */     if (t == null) {
/*  668 */       return null;
/*      */     }
/*  670 */     return (AbstractParentNode.ElementsByTagNameNS)t.get(ns, ln);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putElementsByTagNameNS(Node n, String ns, String ln, AbstractParentNode.ElementsByTagNameNS l) {
/*  678 */     if (this.elementsByTagNamesNS == null) {
/*  679 */       this.elementsByTagNamesNS = new WeakHashMap<Object, Object>(11);
/*      */     }
/*      */     
/*  682 */     SoftDoublyIndexedTable t = (SoftDoublyIndexedTable)this.elementsByTagNamesNS.get(n);
/*  683 */     if (t == null) {
/*  684 */       this.elementsByTagNamesNS.put(n, t = new SoftDoublyIndexedTable());
/*      */     }
/*  686 */     t.put(ns, ln, l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Event createEvent(String eventType) throws DOMException {
/*  696 */     if (this.documentEventSupport == null) {
/*  697 */       this.documentEventSupport = ((AbstractDOMImplementation)this.implementation).createDocumentEventSupport();
/*      */     }
/*      */ 
/*      */     
/*  701 */     return this.documentEventSupport.createEvent(eventType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canDispatch(String ns, String eventType) {
/*  709 */     if (eventType == null) {
/*  710 */       return false;
/*      */     }
/*  712 */     if (ns != null && ns.length() == 0) {
/*  713 */       ns = null;
/*      */     }
/*  715 */     if (ns == null || ns.equals("http://www.w3.org/2001/xml-events")) {
/*  716 */       return (eventType.equals("Event") || eventType.equals("MutationEvent") || eventType.equals("MutationNameEvent") || eventType.equals("UIEvent") || eventType.equals("MouseEvent") || eventType.equals("KeyEvent") || eventType.equals("KeyboardEvent") || eventType.equals("TextEvent") || eventType.equals("CustomEvent"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  726 */     return false;
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
/*      */   public NodeIterator createNodeIterator(Node root, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion) throws DOMException {
/*  740 */     if (this.traversalSupport == null) {
/*  741 */       this.traversalSupport = new TraversalSupport();
/*      */     }
/*  743 */     return this.traversalSupport.createNodeIterator(this, root, whatToShow, filter, entityReferenceExpansion);
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
/*      */   public TreeWalker createTreeWalker(Node root, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion) throws DOMException {
/*  757 */     return TraversalSupport.createTreeWalker(this, root, whatToShow, filter, entityReferenceExpansion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void detachNodeIterator(NodeIterator it) {
/*  766 */     this.traversalSupport.detachNodeIterator(it);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nodeToBeRemoved(Node node) {
/*  773 */     if (this.traversalSupport != null) {
/*  774 */       this.traversalSupport.nodeToBeRemoved(node);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractDocument getCurrentDocument() {
/*  782 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node export(Node n, Document d) {
/*  791 */     throw createDOMException((short)9, "import.document", new Object[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node deepExport(Node n, Document d) {
/*  802 */     throw createDOMException((short)9, "import.document", new Object[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node copyInto(Node n) {
/*  812 */     super.copyInto(n);
/*  813 */     AbstractDocument ad = (AbstractDocument)n;
/*  814 */     ad.implementation = this.implementation;
/*  815 */     ad.localizableSupport = new LocalizableSupport("org.apache.batik.dom.resources.Messages", getClass().getClassLoader());
/*      */     
/*  817 */     ad.inputEncoding = this.inputEncoding;
/*  818 */     ad.xmlEncoding = this.xmlEncoding;
/*  819 */     ad.xmlVersion = this.xmlVersion;
/*  820 */     ad.xmlStandalone = this.xmlStandalone;
/*  821 */     ad.documentURI = this.documentURI;
/*  822 */     ad.strictErrorChecking = this.strictErrorChecking;
/*      */     
/*  824 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node deepCopyInto(Node n) {
/*  832 */     super.deepCopyInto(n);
/*  833 */     AbstractDocument ad = (AbstractDocument)n;
/*  834 */     ad.implementation = this.implementation;
/*  835 */     ad.localizableSupport = new LocalizableSupport("org.apache.batik.dom.resources.Messages", getClass().getClassLoader());
/*      */     
/*  837 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkChildType(Node n, boolean replace) {
/*  844 */     short t = n.getNodeType();
/*  845 */     switch (t) {
/*      */       case 1:
/*      */       case 7:
/*      */       case 8:
/*      */       case 10:
/*      */       case 11:
/*      */         break;
/*      */       default:
/*  853 */         throw createDOMException((short)3, "child.type", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), Integer.valueOf(t), n.getNodeName() });
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  860 */     if ((!replace && t == 1 && getDocumentElement() != null) || (t == 10 && getDoctype() != null))
/*      */     {
/*      */       
/*  863 */       throw createDOMException((short)9, "document.child.already.exists", new Object[] { Integer.valueOf(t), n.getNodeName() });
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getInputEncoding() {
/*  874 */     return this.inputEncoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getXmlEncoding() {
/*  881 */     return this.xmlEncoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getXmlStandalone() {
/*  888 */     return this.xmlStandalone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setXmlStandalone(boolean b) throws DOMException {
/*  895 */     this.xmlStandalone = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getXmlVersion() {
/*  902 */     return this.xmlVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setXmlVersion(String v) throws DOMException {
/*  909 */     if (v == null || (!v.equals("1.0") && !v.equals("1.1")))
/*      */     {
/*      */       
/*  912 */       throw createDOMException((short)9, "xml.version", new Object[] { v });
/*      */     }
/*      */ 
/*      */     
/*  916 */     this.xmlVersion = v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getStrictErrorChecking() {
/*  923 */     return this.strictErrorChecking;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrictErrorChecking(boolean b) {
/*  930 */     this.strictErrorChecking = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentURI() {
/*  937 */     return this.documentURI;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentURI(String uri) {
/*  944 */     this.documentURI = uri;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMConfiguration getDomConfig() {
/*  951 */     if (this.domConfig == null) {
/*  952 */       this.domConfig = new DocumentConfiguration();
/*      */     }
/*  954 */     return this.domConfig;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node adoptNode(Node n) throws DOMException {
/*  961 */     if (!(n instanceof AbstractNode)) {
/*  962 */       return null;
/*      */     }
/*  964 */     switch (n.getNodeType()) {
/*      */       case 9:
/*  966 */         throw createDOMException((short)9, "adopt.document", new Object[0]);
/*      */ 
/*      */       
/*      */       case 10:
/*  970 */         throw createDOMException((short)9, "adopt.document.type", new Object[0]);
/*      */ 
/*      */       
/*      */       case 6:
/*      */       case 12:
/*  975 */         return null;
/*      */     } 
/*  977 */     AbstractNode an = (AbstractNode)n;
/*  978 */     if (an.isReadonly()) {
/*  979 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(an.getNodeType()), an.getNodeName() });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  985 */     Node parent = n.getParentNode();
/*  986 */     if (parent != null) {
/*  987 */       parent.removeChild(n);
/*      */     }
/*  989 */     adoptNode1((AbstractNode)n);
/*  990 */     return n;
/*      */   }
/*      */   
/*      */   protected void adoptNode1(AbstractNode n) {
/*      */     AbstractAttr attr;
/*      */     NamedNodeMap nnm;
/*      */     int len, i;
/*  997 */     n.ownerDocument = this;
/*  998 */     switch (n.getNodeType()) {
/*      */       case 2:
/* 1000 */         attr = (AbstractAttr)n;
/* 1001 */         attr.ownerElement = null;
/* 1002 */         attr.unspecified = false;
/*      */         break;
/*      */       case 1:
/* 1005 */         nnm = n.getAttributes();
/* 1006 */         len = nnm.getLength();
/* 1007 */         for (i = 0; i < len; i++) {
/* 1008 */           attr = (AbstractAttr)nnm.item(i);
/* 1009 */           if (attr.getSpecified()) {
/* 1010 */             adoptNode1(attr);
/*      */           }
/*      */         } 
/*      */         break;
/*      */       case 5:
/* 1015 */         while (n.getFirstChild() != null) {
/* 1016 */           n.removeChild(n.getFirstChild());
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 1021 */     fireUserDataHandlers((short)5, n, (Node)null);
/*      */     
/* 1023 */     for (Node m = n.getFirstChild(); m != null; m = m.getNextSibling()) {
/* 1024 */       switch (m.getNodeType()) {
/*      */         case 6:
/*      */         case 10:
/*      */         case 12:
/*      */           return;
/*      */       } 
/* 1030 */       adoptNode1((AbstractNode)m);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node renameNode(Node n, String ns, String qn) {
/* 1038 */     AbstractNode an = (AbstractNode)n;
/* 1039 */     if (an == getDocumentElement()) {
/* 1040 */       throw createDOMException((short)9, "rename.document.element", new Object[0]);
/*      */     }
/*      */ 
/*      */     
/* 1044 */     int nt = n.getNodeType();
/* 1045 */     if (nt != 1 && nt != 2) {
/* 1046 */       throw createDOMException((short)9, "rename.node", new Object[] { Integer.valueOf(nt), n.getNodeName() });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1051 */     if ((this.xmlVersion.equals("1.1") && !DOMUtilities.isValidName11(qn)) || !DOMUtilities.isValidName(qn))
/*      */     {
/*      */       
/* 1054 */       throw createDOMException((short)9, "wf.invalid.name", new Object[] { qn });
/*      */     }
/*      */ 
/*      */     
/* 1058 */     if (n.getOwnerDocument() != this) {
/* 1059 */       throw createDOMException((short)9, "node.from.wrong.document", new Object[] { Integer.valueOf(nt), n.getNodeName() });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1064 */     int i = qn.indexOf(':');
/* 1065 */     if (i == 0 || i == qn.length() - 1) {
/* 1066 */       throw createDOMException((short)14, "qname", new Object[] { Integer.valueOf(nt), n.getNodeName(), qn });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1072 */     String prefix = DOMUtilities.getPrefix(qn);
/* 1073 */     if (ns != null && ns.length() == 0) {
/* 1074 */       ns = null;
/*      */     }
/* 1076 */     if (prefix != null && ns == null) {
/* 1077 */       throw createDOMException((short)14, "prefix", new Object[] { Integer.valueOf(nt), n.getNodeName(), prefix });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1083 */     if (this.strictErrorChecking && ((
/* 1084 */       "xml".equals(prefix) && !"http://www.w3.org/XML/1998/namespace".equals(ns)) || ("xmlns".equals(prefix) && !"http://www.w3.org/2000/xmlns/".equals(ns))))
/*      */     {
/*      */ 
/*      */       
/* 1088 */       throw createDOMException((short)14, "namespace", new Object[] { Integer.valueOf(nt), n.getNodeName(), ns });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1096 */     String prevNamespaceURI = n.getNamespaceURI();
/* 1097 */     String prevNodeName = n.getNodeName();
/* 1098 */     if (nt == 1) {
/* 1099 */       Node parent = n.getParentNode();
/* 1100 */       AbstractElement abstractElement = (AbstractElement)createElementNS(ns, qn);
/*      */ 
/*      */       
/* 1103 */       EventSupport es1 = an.getEventSupport();
/* 1104 */       if (es1 != null) {
/* 1105 */         EventSupport es2 = abstractElement.getEventSupport();
/* 1106 */         if (es2 == null) {
/* 1107 */           AbstractDOMImplementation di = (AbstractDOMImplementation)this.implementation;
/*      */           
/* 1109 */           es2 = di.createEventSupport(abstractElement);
/* 1110 */           setEventsEnabled(true);
/* 1111 */           abstractElement.eventSupport = es2;
/*      */         } 
/* 1113 */         es1.moveEventListeners(abstractElement.getEventSupport());
/*      */       } 
/*      */ 
/*      */       
/* 1117 */       abstractElement.userData = (abstractElement.userData == null) ? null : (HashMap)an.userData.clone();
/*      */ 
/*      */       
/* 1120 */       abstractElement.userDataHandlers = (abstractElement.userDataHandlers == null) ? null : (HashMap)an.userDataHandlers.clone();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1125 */       Node next = null;
/* 1126 */       if (parent != null) {
/* 1127 */         n.getNextSibling();
/* 1128 */         parent.removeChild(n);
/*      */       } 
/*      */ 
/*      */       
/* 1132 */       while (n.getFirstChild() != null) {
/* 1133 */         abstractElement.appendChild(n.getFirstChild());
/*      */       }
/*      */ 
/*      */       
/* 1137 */       NamedNodeMap nnm = n.getAttributes();
/* 1138 */       for (int j = 0; j < nnm.getLength(); j++) {
/* 1139 */         Attr attr = (Attr)nnm.item(j);
/* 1140 */         abstractElement.setAttributeNodeNS(attr);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1148 */       if (parent != null) {
/* 1149 */         if (next == null) {
/* 1150 */           parent.appendChild(abstractElement);
/*      */         } else {
/* 1152 */           parent.insertBefore(next, abstractElement);
/*      */         } 
/*      */       }
/*      */       
/* 1156 */       fireUserDataHandlers((short)4, n, abstractElement);
/* 1157 */       if (getEventsEnabled()) {
/* 1158 */         MutationNameEvent ev = (MutationNameEvent)createEvent("MutationNameEvent");
/*      */         
/* 1160 */         ev.initMutationNameEventNS("http://www.w3.org/2001/xml-events", "DOMElementNameChanged", true, false, null, prevNamespaceURI, prevNodeName);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1167 */         dispatchEvent((Event)ev);
/*      */       } 
/* 1169 */       return abstractElement;
/*      */     } 
/* 1171 */     if (n instanceof AbstractAttrNS) {
/* 1172 */       AbstractAttrNS abstractAttrNS = (AbstractAttrNS)n;
/* 1173 */       Element element = abstractAttrNS.getOwnerElement();
/*      */ 
/*      */       
/* 1176 */       if (element != null) {
/* 1177 */         element.removeAttributeNode(abstractAttrNS);
/*      */       }
/*      */ 
/*      */       
/* 1181 */       abstractAttrNS.namespaceURI = ns;
/* 1182 */       abstractAttrNS.nodeName = qn;
/*      */ 
/*      */       
/* 1185 */       if (element != null) {
/* 1186 */         element.setAttributeNodeNS(abstractAttrNS);
/*      */       }
/*      */       
/* 1189 */       fireUserDataHandlers((short)4, abstractAttrNS, (Node)null);
/* 1190 */       if (getEventsEnabled()) {
/* 1191 */         MutationNameEvent ev = (MutationNameEvent)createEvent("MutationNameEvent");
/*      */         
/* 1193 */         ev.initMutationNameEventNS("http://www.w3.org/2001/xml-events", "DOMAttrNameChanged", true, false, abstractAttrNS, prevNamespaceURI, prevNodeName);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1200 */         dispatchEvent((Event)ev);
/*      */       } 
/* 1202 */       return abstractAttrNS;
/*      */     } 
/* 1204 */     AbstractAttr a = (AbstractAttr)n;
/* 1205 */     Element e = a.getOwnerElement();
/*      */ 
/*      */     
/* 1208 */     if (e != null) {
/* 1209 */       e.removeAttributeNode(a);
/*      */     }
/* 1211 */     AbstractAttr a2 = (AbstractAttr)createAttributeNS(ns, qn);
/*      */ 
/*      */     
/* 1214 */     a2.setNodeValue(a.getNodeValue());
/*      */ 
/*      */     
/* 1217 */     a2.userData = (a.userData == null) ? null : (HashMap)a.userData.clone();
/*      */ 
/*      */     
/* 1220 */     a2.userDataHandlers = (a.userDataHandlers == null) ? null : (HashMap)a.userDataHandlers.clone();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1225 */     if (e != null) {
/* 1226 */       e.setAttributeNodeNS(a2);
/*      */     }
/*      */     
/* 1229 */     fireUserDataHandlers((short)4, a, a2);
/* 1230 */     if (getEventsEnabled()) {
/* 1231 */       MutationNameEvent ev = (MutationNameEvent)createEvent("MutationNameEvent");
/*      */       
/* 1233 */       ev.initMutationNameEventNS("http://www.w3.org/2001/xml-events", "DOMAttrNameChanged", true, false, a2, prevNamespaceURI, prevNodeName);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1240 */       dispatchEvent((Event)ev);
/*      */     } 
/* 1242 */     return a2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void normalizeDocument() {
/* 1252 */     if (this.domConfig == null) {
/* 1253 */       this.domConfig = new DocumentConfiguration();
/*      */     }
/* 1255 */     boolean cdataSections = this.domConfig.getBooleanParameter("cdata-sections");
/*      */     
/* 1257 */     boolean comments = this.domConfig.getBooleanParameter("comments");
/*      */     
/* 1259 */     boolean elementContentWhitespace = this.domConfig.getBooleanParameter("element-content-whitespace");
/*      */     
/* 1261 */     boolean namespaceDeclarations = this.domConfig.getBooleanParameter("namespace-declarations");
/*      */     
/* 1263 */     boolean namespaces = this.domConfig.getBooleanParameter("namespaces");
/*      */     
/* 1265 */     boolean splitCdataSections = this.domConfig.getBooleanParameter("split-cdata-sections");
/*      */     
/* 1267 */     DOMErrorHandler errorHandler = (DOMErrorHandler)this.domConfig.getParameter("error-handler");
/*      */     
/* 1269 */     normalizeDocument(getDocumentElement(), cdataSections, comments, elementContentWhitespace, namespaceDeclarations, namespaces, splitCdataSections, errorHandler);
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
/*      */   protected boolean normalizeDocument(Element e, boolean cdataSections, boolean comments, boolean elementContentWhitepace, boolean namespaceDeclarations, boolean namespaces, boolean splitCdataSections, DOMErrorHandler errorHandler) {
/* 1290 */     AbstractElement ae = (AbstractElement)e;
/* 1291 */     Node n = e.getFirstChild();
/* 1292 */     while (n != null) {
/* 1293 */       int nt = n.getNodeType();
/* 1294 */       if (nt == 3 || (!cdataSections && nt == 4)) {
/*      */ 
/*      */         
/* 1297 */         Node t = n;
/* 1298 */         StringBuffer sb = new StringBuffer();
/* 1299 */         sb.append(t.getNodeValue());
/* 1300 */         n = n.getNextSibling();
/* 1301 */         while (n != null && (n.getNodeType() == 3 || (!cdataSections && n.getNodeType() == 4))) {
/*      */           
/* 1303 */           sb.append(n.getNodeValue());
/* 1304 */           Node next = n.getNextSibling();
/* 1305 */           e.removeChild(n);
/* 1306 */           n = next;
/*      */         } 
/* 1308 */         String s = sb.toString();
/* 1309 */         if (s.length() == 0) {
/* 1310 */           Node next = n.getNextSibling();
/* 1311 */           e.removeChild(n);
/* 1312 */           n = next;
/*      */           continue;
/*      */         } 
/* 1315 */         if (!s.equals(t.getNodeValue())) {
/* 1316 */           if (!cdataSections && nt == 3) {
/* 1317 */             n = createTextNode(s);
/* 1318 */             e.replaceChild(n, t);
/*      */           } else {
/* 1320 */             n = t;
/* 1321 */             t.setNodeValue(s);
/*      */           } 
/*      */         } else {
/* 1324 */           n = t;
/*      */         } 
/* 1326 */         if (!elementContentWhitepace) {
/*      */           
/* 1328 */           nt = n.getNodeType();
/* 1329 */           if (nt == 3) {
/* 1330 */             AbstractText tn = (AbstractText)n;
/* 1331 */             if (tn.isElementContentWhitespace()) {
/* 1332 */               Node next = n.getNextSibling();
/* 1333 */               e.removeChild(n);
/* 1334 */               n = next;
/*      */               continue;
/*      */             } 
/*      */           } 
/*      */         } 
/* 1339 */         if (nt == 4 && splitCdataSections && 
/* 1340 */           !splitCdata(e, n, errorHandler)) {
/* 1341 */           return false;
/*      */         }
/*      */       }
/* 1344 */       else if (nt == 4 && splitCdataSections) {
/*      */         
/* 1346 */         if (!splitCdata(e, n, errorHandler)) {
/* 1347 */           return false;
/*      */         }
/* 1349 */       } else if (nt == 8 && !comments) {
/*      */         
/* 1351 */         Node next = n.getPreviousSibling();
/* 1352 */         if (next == null) {
/* 1353 */           next = n.getNextSibling();
/*      */         }
/* 1355 */         e.removeChild(n);
/* 1356 */         n = next;
/*      */         
/*      */         continue;
/*      */       } 
/* 1360 */       n = n.getNextSibling();
/*      */     } 
/*      */     
/* 1363 */     NamedNodeMap nnm = e.getAttributes();
/* 1364 */     LinkedList<Attr> toRemove = new LinkedList();
/* 1365 */     HashMap<Object, Object> names = new HashMap<Object, Object>(); int i;
/* 1366 */     for (i = 0; i < nnm.getLength(); i++) {
/* 1367 */       Attr a = (Attr)nnm.item(i);
/* 1368 */       String prefix = a.getPrefix();
/* 1369 */       if ((a != null && "xmlns".equals(prefix)) || a.getNodeName().equals("xmlns"))
/*      */       {
/* 1371 */         if (!namespaceDeclarations) {
/*      */           
/* 1373 */           toRemove.add(a);
/*      */         } else {
/*      */           
/* 1376 */           String ns = a.getNodeValue();
/* 1377 */           if (!a.getNodeValue().equals("http://www.w3.org/2000/xmlns/") && ns.equals("http://www.w3.org/2000/xmlns/"))
/*      */           {
/*      */ 
/*      */             
/* 1381 */             names.put(prefix, ns);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1387 */     if (!namespaceDeclarations) {
/*      */       
/* 1389 */       for (Attr aToRemove : toRemove) {
/* 1390 */         e.removeAttributeNode(aToRemove);
/*      */       }
/*      */     }
/* 1393 */     else if (namespaces) {
/*      */       
/* 1395 */       String ens = e.getNamespaceURI();
/* 1396 */       if (ens != null) {
/* 1397 */         String eprefix = e.getPrefix();
/* 1398 */         if (!compareStrings(ae.lookupNamespaceURI(eprefix), ens)) {
/* 1399 */           e.setAttributeNS("http://www.w3.org/2000/xmlns/", (eprefix == null) ? "xmlns" : ("xmlns:" + eprefix), ens);
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1405 */       else if (e.getLocalName() != null) {
/*      */ 
/*      */         
/* 1408 */         if (ae.lookupNamespaceURI((String)null) == null) {
/* 1409 */           e.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", "");
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1417 */       nnm = e.getAttributes();
/* 1418 */       for (int j = 0; j < nnm.getLength(); j++) {
/* 1419 */         Attr a = (Attr)nnm.item(j);
/* 1420 */         String ans = a.getNamespaceURI();
/* 1421 */         if (ans != null) {
/* 1422 */           String apre = a.getPrefix();
/* 1423 */           if ((apre == null || (!apre.equals("xml") && !apre.equals("xmlns"))) && !ans.equals("http://www.w3.org/2000/xmlns/")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1429 */             String aprens = (apre == null) ? null : ae.lookupNamespaceURI(apre);
/* 1430 */             if (apre == null || aprens == null || !aprens.equals(ans)) {
/*      */ 
/*      */               
/* 1433 */               String newpre = ae.lookupPrefix(ans);
/* 1434 */               if (newpre != null) {
/* 1435 */                 a.setPrefix(newpre);
/*      */               }
/* 1437 */               else if (apre != null && ae.lookupNamespaceURI(apre) == null) {
/*      */                 
/* 1439 */                 e.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + apre, ans);
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/* 1444 */                 int index = 1;
/*      */                 while (true) {
/* 1446 */                   newpre = "NS" + index;
/* 1447 */                   if (ae.lookupPrefix(newpre) == null) {
/* 1448 */                     e.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + newpre, ans);
/*      */ 
/*      */ 
/*      */                     
/* 1452 */                     a.setPrefix(newpre);
/*      */                     
/*      */                     break;
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/* 1460 */         } else if (a.getLocalName() == null) {
/*      */         
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1469 */     nnm = e.getAttributes();
/* 1470 */     for (i = 0; i < nnm.getLength(); i++) {
/* 1471 */       Attr a = (Attr)nnm.item(i);
/* 1472 */       if (!checkName(a.getNodeName()) && 
/* 1473 */         errorHandler != null && 
/* 1474 */         !errorHandler.handleError(createDOMError("wf-invalid-character-in-node-name", (short)2, "wf.invalid.name", new Object[] { a.getNodeName() }, a, (Exception)null)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1481 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1485 */       if (!checkChars(a.getNodeValue()) && 
/* 1486 */         errorHandler != null && 
/* 1487 */         !errorHandler.handleError(createDOMError("wf-invalid-character", (short)2, "wf.invalid.character", new Object[] { Integer.valueOf(2), a.getNodeName(), a.getNodeValue() }, a, (Exception)null)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1496 */         return false;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1501 */     for (Node m = e.getFirstChild(); m != null; m = m.getNextSibling()) {
/* 1502 */       String s; int nt = m.getNodeType();
/*      */       
/* 1504 */       switch (nt) {
/*      */         case 3:
/* 1506 */           s = m.getNodeValue();
/* 1507 */           if (!checkChars(s) && 
/* 1508 */             errorHandler != null && 
/* 1509 */             !errorHandler.handleError(createDOMError("wf-invalid-character", (short)2, "wf.invalid.character", new Object[] { Integer.valueOf(m.getNodeType()), m.getNodeName(), s }, m, (Exception)null)))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1518 */             return false;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case 8:
/* 1524 */           s = m.getNodeValue();
/* 1525 */           if (!checkChars(s) || s.indexOf("--") != -1 || s.charAt(s.length() - 1) == '-')
/*      */           {
/*      */             
/* 1528 */             if (errorHandler != null && 
/* 1529 */               !errorHandler.handleError(createDOMError("wf-invalid-character", (short)2, "wf.invalid.character", new Object[] { Integer.valueOf(m.getNodeType()), m.getNodeName(), s }, m, (Exception)null)))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1538 */               return false;
/*      */             }
/*      */           }
/*      */           break;
/*      */         
/*      */         case 4:
/* 1544 */           s = m.getNodeValue();
/* 1545 */           if (!checkChars(s) || s.indexOf("]]>") != -1)
/*      */           {
/* 1547 */             if (errorHandler != null && 
/* 1548 */               !errorHandler.handleError(createDOMError("wf-invalid-character", (short)2, "wf.invalid.character", new Object[] { Integer.valueOf(m.getNodeType()), m.getNodeName(), s }, m, (Exception)null)))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1557 */               return false;
/*      */             }
/*      */           }
/*      */           break;
/*      */         
/*      */         case 7:
/* 1563 */           if (m.getNodeName().equalsIgnoreCase("xml"))
/*      */           {
/* 1565 */             if (errorHandler != null && 
/* 1566 */               !errorHandler.handleError(createDOMError("wf-invalid-character-in-node-name", (short)2, "wf.invalid.name", new Object[] { m.getNodeName() }, m, (Exception)null)))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1573 */               return false;
/*      */             }
/*      */           }
/*      */           
/* 1577 */           s = m.getNodeValue();
/* 1578 */           if (!checkChars(s) || s.indexOf("?>") != -1)
/*      */           {
/*      */             
/* 1581 */             if (errorHandler != null && 
/* 1582 */               !errorHandler.handleError(createDOMError("wf-invalid-character", (short)2, "wf.invalid.character", new Object[] { Integer.valueOf(m.getNodeType()), m.getNodeName(), s }, m, (Exception)null)))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1591 */               return false;
/*      */             }
/*      */           }
/*      */           break;
/*      */         
/*      */         case 1:
/* 1597 */           if (!checkName(m.getNodeName()) && 
/* 1598 */             errorHandler != null && 
/* 1599 */             !errorHandler.handleError(createDOMError("wf-invalid-character-in-node-name", (short)2, "wf.invalid.name", new Object[] { m.getNodeName() }, m, (Exception)null)))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1606 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1610 */           if (!normalizeDocument((Element)m, cdataSections, comments, elementContentWhitepace, namespaceDeclarations, namespaces, splitCdataSections, errorHandler))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1618 */             return false;
/*      */           }
/*      */           break;
/*      */       } 
/*      */     } 
/* 1623 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean splitCdata(Element e, Node n, DOMErrorHandler errorHandler) {
/* 1632 */     String s2 = n.getNodeValue();
/* 1633 */     int index = s2.indexOf("]]>");
/* 1634 */     if (index != -1) {
/* 1635 */       String before = s2.substring(0, index + 2);
/* 1636 */       String after = s2.substring(index + 2);
/* 1637 */       n.setNodeValue(before);
/* 1638 */       Node next = n.getNextSibling();
/* 1639 */       if (next == null) {
/* 1640 */         e.appendChild(createCDATASection(after));
/*      */       } else {
/* 1642 */         e.insertBefore(createCDATASection(after), next);
/*      */       } 
/*      */       
/* 1645 */       if (errorHandler != null && 
/* 1646 */         !errorHandler.handleError(createDOMError("cdata-sections-splitted", (short)1, "cdata.section.split", new Object[0], n, (Exception)null)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1653 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1657 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean checkChars(String s) {
/* 1665 */     int len = s.length();
/* 1666 */     if (this.xmlVersion.equals("1.1")) {
/* 1667 */       for (int i = 0; i < len; i++) {
/* 1668 */         if (!DOMUtilities.isXML11Character(s.charAt(i))) {
/* 1669 */           return false;
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 1674 */       for (int i = 0; i < len; i++) {
/* 1675 */         if (!DOMUtilities.isXMLCharacter(s.charAt(i))) {
/* 1676 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/* 1680 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean checkName(String s) {
/* 1687 */     if (this.xmlVersion.equals("1.1")) {
/* 1688 */       return DOMUtilities.isValidName11(s);
/*      */     }
/*      */     
/* 1691 */     return DOMUtilities.isValidName(s);
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
/*      */   protected DOMError createDOMError(String type, short severity, String key, Object[] args, Node related, Exception e) {
/*      */     try {
/* 1704 */       return new DocumentError(type, severity, getCurrentDocument().formatMessage(key, args), related, e);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1709 */     catch (Exception ex) {
/* 1710 */       return new DocumentError(type, severity, key, related, e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextContent(String s) throws DOMException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setXBLManager(XBLManager m) {
/*      */     GenericXBLManager genericXBLManager;
/* 1728 */     boolean wasProcessing = this.xblManager.isProcessing();
/* 1729 */     this.xblManager.stopProcessing();
/* 1730 */     if (m == null) {
/* 1731 */       genericXBLManager = new GenericXBLManager();
/*      */     }
/* 1733 */     this.xblManager = (XBLManager)genericXBLManager;
/* 1734 */     if (wasProcessing) {
/* 1735 */       this.xblManager.startProcessing();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XBLManager getXBLManager() {
/* 1743 */     return this.xblManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DocumentError
/*      */     implements DOMError
/*      */   {
/*      */     protected String type;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected short severity;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String message;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node relatedNode;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object relatedException;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected DOMLocator domLocator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DocumentError(String type, short severity, String message, Node relatedNode, Exception relatedException) {
/* 1789 */       this.type = type;
/* 1790 */       this.severity = severity;
/* 1791 */       this.message = message;
/* 1792 */       this.relatedNode = relatedNode;
/* 1793 */       this.relatedException = relatedException;
/*      */     }
/*      */     
/*      */     public String getType() {
/* 1797 */       return this.type;
/*      */     }
/*      */     
/*      */     public short getSeverity() {
/* 1801 */       return this.severity;
/*      */     }
/*      */     
/*      */     public String getMessage() {
/* 1805 */       return this.message;
/*      */     }
/*      */     
/*      */     public Object getRelatedData() {
/* 1809 */       return this.relatedNode;
/*      */     }
/*      */     
/*      */     public Object getRelatedException() {
/* 1813 */       return this.relatedException;
/*      */     }
/*      */     
/*      */     public DOMLocator getLocation() {
/* 1817 */       if (this.domLocator == null) {
/* 1818 */         this.domLocator = new ErrorLocation(this.relatedNode);
/*      */       }
/* 1820 */       return this.domLocator;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class ErrorLocation
/*      */       implements DOMLocator
/*      */     {
/*      */       protected Node node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public ErrorLocation(Node n) {
/* 1837 */         this.node = n;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getLineNumber() {
/* 1844 */         return -1;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getColumnNumber() {
/* 1851 */         return -1;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getByteOffset() {
/* 1858 */         return -1;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getUtf16Offset() {
/* 1865 */         return -1;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Node getRelatedNode() {
/* 1872 */         return this.node;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public String getUri() {
/* 1879 */         AbstractDocument doc = (AbstractDocument)this.node.getOwnerDocument();
/*      */         
/* 1881 */         return doc.getDocumentURI();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DocumentConfiguration
/*      */     implements DOMConfiguration
/*      */   {
/* 1894 */     protected String[] booleanParamNames = new String[] { "canonical-form", "cdata-sections", "check-character-normalization", "comments", "datatype-normalization", "element-content-whitespace", "entities", "infoset", "namespaces", "namespace-declarations", "normalize-characters", "split-cdata-sections", "validate", "validate-if-schema", "well-formed" };
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
/* 1915 */     protected boolean[] booleanParamValues = new boolean[] { 
/*      */         false, true, false, true, false, false, true, false, true, true, 
/*      */         false, true, false, false, true };
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
/* 1936 */     protected boolean[] booleanParamReadOnly = new boolean[] { 
/*      */         true, false, true, false, true, false, false, false, false, false, 
/*      */         true, false, true, true, false };
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
/* 1957 */     protected Map booleanParamIndexes = new HashMap<Object, Object>(); protected Object errorHandler;
/*      */     protected DocumentConfiguration() {
/* 1959 */       for (int i = 0; i < this.booleanParamNames.length; i++) {
/* 1960 */         this.booleanParamIndexes.put(this.booleanParamNames[i], Integer.valueOf(i));
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ParameterNameList paramNameList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setParameter(String name, Object value) {
/* 1978 */       if ("error-handler".equals(name)) {
/* 1979 */         if (value != null && !(value instanceof DOMErrorHandler)) {
/* 1980 */           throw AbstractDocument.this.createDOMException((short)17, "domconfig.param.type", new Object[] { name });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1985 */         this.errorHandler = value;
/*      */         return;
/*      */       } 
/* 1988 */       Integer i = (Integer)this.booleanParamIndexes.get(name);
/* 1989 */       if (i == null) {
/* 1990 */         throw AbstractDocument.this.createDOMException((short)8, "domconfig.param.not.found", new Object[] { name });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1995 */       if (value == null) {
/* 1996 */         throw AbstractDocument.this.createDOMException((short)9, "domconfig.param.value", new Object[] { name });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2001 */       if (!(value instanceof Boolean)) {
/* 2002 */         throw AbstractDocument.this.createDOMException((short)17, "domconfig.param.type", new Object[] { name });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2007 */       int index = i.intValue();
/* 2008 */       boolean val = ((Boolean)value).booleanValue();
/* 2009 */       if (this.booleanParamReadOnly[index] && this.booleanParamValues[index] != val)
/*      */       {
/* 2011 */         throw AbstractDocument.this.createDOMException((short)9, "domconfig.param.value", new Object[] { name });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2016 */       this.booleanParamValues[index] = val;
/* 2017 */       if (name.equals("infoset")) {
/* 2018 */         setParameter("validate-if-schema", Boolean.FALSE);
/* 2019 */         setParameter("entities", Boolean.FALSE);
/* 2020 */         setParameter("datatype-normalization", Boolean.FALSE);
/* 2021 */         setParameter("cdata-sections", Boolean.FALSE);
/* 2022 */         setParameter("well-formed", Boolean.TRUE);
/* 2023 */         setParameter("element-content-whitespace", Boolean.TRUE);
/* 2024 */         setParameter("comments", Boolean.TRUE);
/* 2025 */         setParameter("namespaces", Boolean.TRUE);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getParameter(String name) {
/* 2033 */       if ("error-handler".equals(name)) {
/* 2034 */         return this.errorHandler;
/*      */       }
/* 2036 */       Integer index = (Integer)this.booleanParamIndexes.get(name);
/* 2037 */       if (index == null) {
/* 2038 */         throw AbstractDocument.this.createDOMException((short)8, "domconfig.param.not.found", new Object[] { name });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2043 */       return this.booleanParamValues[index.intValue()] ? Boolean.TRUE : Boolean.FALSE;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean getBooleanParameter(String name) {
/* 2051 */       Boolean b = (Boolean)getParameter(name);
/* 2052 */       return b.booleanValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean canSetParameter(String name, Object value) {
/* 2059 */       if (name.equals("error-handler")) {
/* 2060 */         return (value == null || value instanceof DOMErrorHandler);
/*      */       }
/* 2062 */       Integer i = (Integer)this.booleanParamIndexes.get(name);
/* 2063 */       if (i == null || value == null || !(value instanceof Boolean)) {
/* 2064 */         return false;
/*      */       }
/* 2066 */       int index = i.intValue();
/* 2067 */       boolean val = ((Boolean)value).booleanValue();
/* 2068 */       return (!this.booleanParamReadOnly[index] || this.booleanParamValues[index] == val);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DOMStringList getParameterNames() {
/* 2076 */       if (this.paramNameList == null) {
/* 2077 */         this.paramNameList = new ParameterNameList();
/*      */       }
/* 2079 */       return this.paramNameList;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class ParameterNameList
/*      */       implements DOMStringList
/*      */     {
/*      */       public String item(int index) {
/* 2091 */         if (index < 0) {
/* 2092 */           return null;
/*      */         }
/* 2094 */         if (index < AbstractDocument.DocumentConfiguration.this.booleanParamNames.length) {
/* 2095 */           return AbstractDocument.DocumentConfiguration.this.booleanParamNames[index];
/*      */         }
/* 2097 */         if (index == AbstractDocument.DocumentConfiguration.this.booleanParamNames.length) {
/* 2098 */           return "error-handler";
/*      */         }
/* 2100 */         return null;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getLength() {
/* 2107 */         return AbstractDocument.DocumentConfiguration.this.booleanParamNames.length + 1;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean contains(String s) {
/* 2114 */         if ("error-handler".equals(s)) {
/* 2115 */           return true;
/*      */         }
/* 2117 */         for (String booleanParamName : AbstractDocument.DocumentConfiguration.this.booleanParamNames) {
/* 2118 */           if (booleanParamName.equals(s)) {
/* 2119 */             return true;
/*      */           }
/*      */         } 
/* 2122 */         return false;
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
/*      */   public XPathExpression createExpression(String expression, XPathNSResolver resolver) throws DOMException, XPathException {
/* 2134 */     return new XPathExpr(expression, resolver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XPathNSResolver createNSResolver(Node n) {
/* 2142 */     return new XPathNodeNSResolver(n);
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
/*      */   public Object evaluate(String expression, Node contextNode, XPathNSResolver resolver, short type, Object result) throws XPathException, DOMException {
/* 2155 */     XPathExpression xpath = createExpression(expression, resolver);
/* 2156 */     return xpath.evaluate(contextNode, type, result);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XPathException createXPathException(short type, String key, Object[] args) {
/*      */     try {
/* 2166 */       return new XPathException(type, formatMessage(key, args));
/* 2167 */     } catch (Exception e) {
/* 2168 */       return new XPathException(type, key);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class XPathExpr
/*      */     implements XPathExpression
/*      */   {
/*      */     protected XPath xpath;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XPathNSResolver resolver;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected NSPrefixResolver prefixResolver;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XPathContext context;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XPathExpr(String expr, XPathNSResolver res) throws DOMException, XPathException {
/* 2202 */       this.resolver = res;
/* 2203 */       this.prefixResolver = new NSPrefixResolver();
/*      */       try {
/* 2205 */         this.xpath = new XPath(expr, null, this.prefixResolver, 0);
/* 2206 */         this.context = new XPathContext();
/* 2207 */       } catch (TransformerException te) {
/* 2208 */         throw AbstractDocument.this.createXPathException((short)51, "xpath.invalid.expression", new Object[] { expr, te.getMessage() });
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
/*      */     
/*      */     public Object evaluate(Node contextNode, short type, Object res) throws XPathException, DOMException {
/* 2221 */       if ((contextNode.getNodeType() != 9 && contextNode.getOwnerDocument() != AbstractDocument.this) || (contextNode.getNodeType() == 9 && contextNode != AbstractDocument.this))
/*      */       {
/*      */ 
/*      */         
/* 2225 */         throw AbstractDocument.this.createDOMException((short)4, "node.from.wrong.document", new Object[] { Integer.valueOf(contextNode.getNodeType()), contextNode.getNodeName() });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2231 */       if (type < 0 || type > 9) {
/* 2232 */         throw AbstractDocument.this.createDOMException((short)9, "xpath.invalid.result.type", new Object[] { Integer.valueOf(type) });
/*      */       }
/*      */ 
/*      */       
/* 2236 */       switch (contextNode.getNodeType()) {
/*      */         case 5:
/*      */         case 6:
/*      */         case 10:
/*      */         case 11:
/*      */         case 12:
/* 2242 */           throw AbstractDocument.this.createDOMException((short)9, "xpath.invalid.context.node", new Object[] { Integer.valueOf(contextNode.getNodeType()), contextNode.getNodeName() });
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2248 */       this.context.reset();
/* 2249 */       XObject result = null;
/*      */       try {
/* 2251 */         result = this.xpath.execute(this.context, contextNode, this.prefixResolver);
/* 2252 */       } catch (TransformerException te) {
/* 2253 */         throw AbstractDocument.this.createXPathException((short)51, "xpath.error", new Object[] { this.xpath.getPatternString(), te.getMessage() });
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2260 */         switch (type) {
/*      */           case 8:
/*      */           case 9:
/* 2263 */             return convertSingleNode(result, type);
/*      */           case 3:
/* 2265 */             return convertBoolean(result);
/*      */           case 1:
/* 2267 */             return convertNumber(result);
/*      */           case 4:
/*      */           case 5:
/*      */           case 6:
/*      */           case 7:
/* 2272 */             return convertNodeIterator(result, type);
/*      */           case 2:
/* 2274 */             return convertString(result);
/*      */           case 0:
/* 2276 */             switch (result.getType()) {
/*      */               case 1:
/* 2278 */                 return convertBoolean(result);
/*      */               case 2:
/* 2280 */                 return convertNumber(result);
/*      */               case 3:
/* 2282 */                 return convertString(result);
/*      */               case 4:
/* 2284 */                 return convertNodeIterator(result, (short)4);
/*      */             } 
/*      */             
/*      */             break;
/*      */         } 
/* 2289 */       } catch (TransformerException te) {
/* 2290 */         throw AbstractDocument.this.createXPathException((short)52, "xpath.cannot.convert.result", new Object[] { Integer.valueOf(type), te.getMessage() });
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2296 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Result convertSingleNode(XObject xo, short type) throws TransformerException {
/* 2304 */       return new Result(xo.nodelist().item(0), type);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Result convertBoolean(XObject xo) throws TransformerException {
/* 2312 */       return new Result(xo.bool());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Result convertNumber(XObject xo) throws TransformerException {
/* 2320 */       return new Result(xo.num());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Result convertString(XObject xo) {
/* 2327 */       return new Result(xo.str());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Result convertNodeIterator(XObject xo, short type) throws TransformerException {
/* 2335 */       return new Result(xo.nodelist(), type);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public class Result
/*      */       implements XPathResult
/*      */     {
/*      */       protected short resultType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected double numberValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected String stringValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected boolean booleanValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected Node singleNodeValue;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected NodeList iterator;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected int iteratorPosition;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Result(Node n, short type) {
/* 2387 */         this.resultType = type;
/* 2388 */         this.singleNodeValue = n;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Result(boolean b) throws TransformerException {
/* 2396 */         this.resultType = 3;
/* 2397 */         this.booleanValue = b;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Result(double d) throws TransformerException {
/* 2405 */         this.resultType = 1;
/* 2406 */         this.numberValue = d;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Result(String s) {
/* 2413 */         this.resultType = 2;
/* 2414 */         this.stringValue = s;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Result(NodeList nl, short type) {
/* 2421 */         this.resultType = type;
/* 2422 */         this.iterator = nl;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public short getResultType() {
/* 2429 */         return this.resultType;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean getBooleanValue() {
/* 2436 */         if (this.resultType != 3) {
/* 2437 */           throw AbstractDocument.this.createXPathException((short)52, "xpath.invalid.result.type", new Object[] { Integer.valueOf(this.resultType) });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2442 */         return this.booleanValue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public double getNumberValue() {
/* 2449 */         if (this.resultType != 1) {
/* 2450 */           throw AbstractDocument.this.createXPathException((short)52, "xpath.invalid.result.type", new Object[] { Integer.valueOf(this.resultType) });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2455 */         return this.numberValue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public String getStringValue() {
/* 2462 */         if (this.resultType != 2) {
/* 2463 */           throw AbstractDocument.this.createXPathException((short)52, "xpath.invalid.result.type", new Object[] { Integer.valueOf(this.resultType) });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2468 */         return this.stringValue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Node getSingleNodeValue() {
/* 2475 */         if (this.resultType != 8 && this.resultType != 9)
/*      */         {
/* 2477 */           throw AbstractDocument.this.createXPathException((short)52, "xpath.invalid.result.type", new Object[] { Integer.valueOf(this.resultType) });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2482 */         return this.singleNodeValue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean getInvalidIteratorState() {
/* 2490 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getSnapshotLength() {
/* 2497 */         if (this.resultType != 6 && this.resultType != 7)
/*      */         {
/* 2499 */           throw AbstractDocument.this.createXPathException((short)52, "xpath.invalid.result.type", new Object[] { Integer.valueOf(this.resultType) });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2504 */         return this.iterator.getLength();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Node iterateNext() {
/* 2512 */         if (this.resultType != 4 && this.resultType != 5)
/*      */         {
/* 2514 */           throw AbstractDocument.this.createXPathException((short)52, "xpath.invalid.result.type", new Object[] { Integer.valueOf(this.resultType) });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2519 */         return this.iterator.item(this.iteratorPosition++);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Node snapshotItem(int i) {
/* 2526 */         if (this.resultType != 6 && this.resultType != 7)
/*      */         {
/* 2528 */           throw AbstractDocument.this.createXPathException((short)52, "xpath.invalid.result.type", new Object[] { Integer.valueOf(this.resultType) });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2533 */         return this.iterator.item(i);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class NSPrefixResolver
/*      */       implements PrefixResolver
/*      */     {
/*      */       public String getBaseIdentifier() {
/* 2547 */         return null;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public String getNamespaceForPrefix(String prefix) {
/* 2554 */         if (AbstractDocument.XPathExpr.this.resolver == null) {
/* 2555 */           return null;
/*      */         }
/* 2557 */         return AbstractDocument.XPathExpr.this.resolver.lookupNamespaceURI(prefix);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public String getNamespaceForPrefix(String prefix, Node context) {
/* 2565 */         if (AbstractDocument.XPathExpr.this.resolver == null) {
/* 2566 */           return null;
/*      */         }
/* 2568 */         return AbstractDocument.XPathExpr.this.resolver.lookupNamespaceURI(prefix);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean handlesNullPrefixes() {
/* 2575 */         return false;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class XPathNodeNSResolver
/*      */     implements XPathNSResolver
/*      */   {
/*      */     protected Node contextNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XPathNodeNSResolver(Node n) {
/* 2594 */       this.contextNode = n;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String lookupNamespaceURI(String prefix) {
/* 2602 */       return ((AbstractNode)this.contextNode).lookupNamespaceURI(prefix);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblParentNode() {
/* 2612 */     return this.xblManager.getXblParentNode(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblChildNodes() {
/* 2619 */     return this.xblManager.getXblChildNodes(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblScopedChildNodes() {
/* 2627 */     return this.xblManager.getXblScopedChildNodes(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblFirstChild() {
/* 2634 */     return this.xblManager.getXblFirstChild(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblLastChild() {
/* 2641 */     return this.xblManager.getXblLastChild(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblPreviousSibling() {
/* 2649 */     return this.xblManager.getXblPreviousSibling(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblNextSibling() {
/* 2657 */     return this.xblManager.getXblNextSibling(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblFirstElementChild() {
/* 2664 */     return this.xblManager.getXblFirstElementChild(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblLastElementChild() {
/* 2671 */     return this.xblManager.getXblLastElementChild(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblPreviousElementSibling() {
/* 2679 */     return this.xblManager.getXblPreviousElementSibling(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblNextElementSibling() {
/* 2687 */     return this.xblManager.getXblNextElementSibling(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblBoundElement() {
/* 2694 */     return this.xblManager.getXblBoundElement(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblShadowTree() {
/* 2701 */     return this.xblManager.getXblShadowTree(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblDefinitions() {
/* 2708 */     return this.xblManager.getXblDefinitions(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 2714 */     s.defaultWriteObject();
/*      */     
/* 2716 */     s.writeObject(this.implementation.getClass().getName());
/*      */   }
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 2721 */     s.defaultReadObject();
/*      */     
/* 2723 */     this.localizableSupport = new LocalizableSupport("org.apache.batik.dom.resources.Messages", getClass().getClassLoader());
/*      */ 
/*      */     
/* 2726 */     Class<?> c = Class.forName((String)s.readObject());
/*      */     
/*      */     try {
/* 2729 */       Method m = c.getMethod("getDOMImplementation", (Class[])null);
/* 2730 */       this.implementation = (DOMImplementation)m.invoke(null, (Object[])null);
/* 2731 */     } catch (Exception e) {
/* 2732 */       if (DOMImplementation.class.isAssignableFrom(c)) {
/*      */         try {
/* 2734 */           this.implementation = c.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/* 2735 */         } catch (Exception exception) {}
/*      */       } else {
/*      */         
/* 2738 */         throw new SecurityException("Trying to create object that is not a DOMImplementation.");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */