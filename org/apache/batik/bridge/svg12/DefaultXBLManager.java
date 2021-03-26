/*      */ package org.apache.batik.bridge.svg12;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.TreeSet;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.apache.batik.anim.dom.BindableElement;
/*      */ import org.apache.batik.anim.dom.XBLEventSupport;
/*      */ import org.apache.batik.anim.dom.XBLOMContentElement;
/*      */ import org.apache.batik.anim.dom.XBLOMDefinitionElement;
/*      */ import org.apache.batik.anim.dom.XBLOMShadowTreeElement;
/*      */ import org.apache.batik.anim.dom.XBLOMTemplateElement;
/*      */ import org.apache.batik.bridge.BridgeContext;
/*      */ import org.apache.batik.bridge.BridgeException;
/*      */ import org.apache.batik.dom.AbstractDocument;
/*      */ import org.apache.batik.dom.AbstractNode;
/*      */ import org.apache.batik.dom.events.NodeEventTarget;
/*      */ import org.apache.batik.dom.xbl.NodeXBL;
/*      */ import org.apache.batik.dom.xbl.ShadowTreeEvent;
/*      */ import org.apache.batik.dom.xbl.XBLManager;
/*      */ import org.apache.batik.dom.xbl.XBLManagerData;
/*      */ import org.apache.batik.dom.xbl.XBLShadowTreeElement;
/*      */ import org.apache.batik.util.DoublyIndexedTable;
/*      */ import org.apache.batik.util.XBLConstants;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.events.DocumentEvent;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventListener;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ import org.w3c.dom.events.MutationEvent;
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
/*      */ 
/*      */ public class DefaultXBLManager
/*      */   implements XBLManager, XBLConstants
/*      */ {
/*      */   protected boolean isProcessing;
/*      */   protected Document document;
/*      */   protected BridgeContext ctx;
/*   94 */   protected DoublyIndexedTable definitionLists = new DoublyIndexedTable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   99 */   protected DoublyIndexedTable definitions = new DoublyIndexedTable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  104 */   protected Map contentManagers = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  109 */   protected Map imports = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  114 */   protected DocInsertedListener docInsertedListener = new DocInsertedListener();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  120 */   protected DocRemovedListener docRemovedListener = new DocRemovedListener();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  126 */   protected DocSubtreeListener docSubtreeListener = new DocSubtreeListener();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  132 */   protected ImportAttrListener importAttrListener = new ImportAttrListener();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  137 */   protected RefAttrListener refAttrListener = new RefAttrListener();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  142 */   protected EventListenerList bindingListenerList = new EventListenerList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  147 */   protected EventListenerList contentSelectionChangedListenerList = new EventListenerList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DefaultXBLManager(Document doc, BridgeContext ctx) {
/*  154 */     this.document = doc;
/*  155 */     this.ctx = ctx;
/*  156 */     ImportRecord ir = new ImportRecord(null, null);
/*  157 */     this.imports.put(null, ir);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startProcessing() {
/*  164 */     if (this.isProcessing) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  169 */     NodeList nl = this.document.getElementsByTagNameNS("http://www.w3.org/2004/xbl", "definition");
/*      */     
/*  171 */     XBLOMDefinitionElement[] defs = new XBLOMDefinitionElement[nl.getLength()];
/*      */     
/*  173 */     for (int i = 0; i < defs.length; i++) {
/*  174 */       defs[i] = (XBLOMDefinitionElement)nl.item(i);
/*      */     }
/*      */ 
/*      */     
/*  178 */     nl = this.document.getElementsByTagNameNS("http://www.w3.org/2004/xbl", "import");
/*      */     
/*  180 */     Element[] imports = new Element[nl.getLength()];
/*      */     
/*  182 */     for (int j = 0; j < imports.length; j++) {
/*  183 */       imports[j] = (Element)nl.item(j);
/*      */     }
/*      */ 
/*      */     
/*  187 */     AbstractDocument doc = (AbstractDocument)this.document;
/*  188 */     XBLEventSupport es = (XBLEventSupport)doc.initializeEventSupport();
/*  189 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.docRemovedListener, true);
/*      */ 
/*      */ 
/*      */     
/*  193 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.docInsertedListener, true);
/*      */ 
/*      */ 
/*      */     
/*  197 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.docSubtreeListener, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  203 */     for (XBLOMDefinitionElement def : defs) {
/*  204 */       if (def.getAttributeNS(null, "ref").length() != 0) {
/*  205 */         addDefinitionRef((Element)def);
/*      */       } else {
/*  207 */         String ns = def.getElementNamespaceURI();
/*  208 */         String ln = def.getElementLocalName();
/*  209 */         addDefinition(ns, ln, def, null);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  214 */     for (Element anImport : imports) {
/*  215 */       addImport(anImport);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  220 */     this.isProcessing = true;
/*  221 */     bind(this.document.getDocumentElement());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stopProcessing() {
/*  228 */     if (!this.isProcessing) {
/*      */       return;
/*      */     }
/*  231 */     this.isProcessing = false;
/*      */ 
/*      */     
/*  234 */     AbstractDocument doc = (AbstractDocument)this.document;
/*  235 */     XBLEventSupport es = (XBLEventSupport)doc.initializeEventSupport();
/*  236 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.docRemovedListener, true);
/*      */ 
/*      */ 
/*      */     
/*  240 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.docInsertedListener, true);
/*      */ 
/*      */ 
/*      */     
/*  244 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.docSubtreeListener, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  250 */     int nSlots = this.imports.values().size();
/*  251 */     ImportRecord[] irs = new ImportRecord[nSlots];
/*  252 */     this.imports.values().toArray((Object[])irs);
/*  253 */     for (ImportRecord ir : irs) {
/*  254 */       if (ir.importElement.getLocalName().equals("definition")) {
/*  255 */         removeDefinitionRef(ir.importElement);
/*      */       } else {
/*  257 */         removeImport(ir.importElement);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  262 */     Object[] defRecs = this.definitions.getValuesArray();
/*  263 */     this.definitions.clear();
/*  264 */     for (Object defRec1 : defRecs) {
/*  265 */       DefinitionRecord defRec = (DefinitionRecord)defRec1;
/*  266 */       TreeSet<DefinitionRecord> defs = (TreeSet)this.definitionLists.get(defRec.namespaceURI, defRec.localName);
/*      */       
/*  268 */       if (defs != null) {
/*  269 */         while (!defs.isEmpty()) {
/*  270 */           defRec = defs.first();
/*  271 */           defs.remove(defRec);
/*  272 */           removeDefinition(defRec);
/*      */         } 
/*  274 */         this.definitionLists.put(defRec.namespaceURI, defRec.localName, null);
/*      */       } 
/*      */     } 
/*  277 */     this.definitionLists = new DoublyIndexedTable();
/*  278 */     this.contentManagers.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isProcessing() {
/*  285 */     return this.isProcessing;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addDefinitionRef(Element defRef) {
/*  293 */     String ref = defRef.getAttributeNS((String)null, "ref");
/*  294 */     Element e = this.ctx.getReferencedElement(defRef, ref);
/*  295 */     if (!"http://www.w3.org/2004/xbl".equals(e.getNamespaceURI()) || !"definition".equals(e.getLocalName()))
/*      */     {
/*  297 */       throw new BridgeException(this.ctx, defRef, "uri.badTarget", new Object[] { ref });
/*      */     }
/*      */ 
/*      */     
/*  301 */     ImportRecord ir = new ImportRecord(defRef, e);
/*  302 */     this.imports.put(defRef, ir);
/*      */     
/*  304 */     NodeEventTarget et = (NodeEventTarget)defRef;
/*  305 */     et.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.refAttrListener, false, null);
/*      */ 
/*      */ 
/*      */     
/*  309 */     XBLOMDefinitionElement d = (XBLOMDefinitionElement)defRef;
/*  310 */     String ns = d.getElementNamespaceURI();
/*  311 */     String ln = d.getElementLocalName();
/*  312 */     addDefinition(ns, ln, (XBLOMDefinitionElement)e, defRef);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeDefinitionRef(Element defRef) {
/*  320 */     ImportRecord ir = (ImportRecord)this.imports.get(defRef);
/*  321 */     NodeEventTarget et = (NodeEventTarget)defRef;
/*  322 */     et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.refAttrListener, false);
/*      */ 
/*      */     
/*  325 */     DefinitionRecord defRec = (DefinitionRecord)this.definitions.get(ir.node, defRef);
/*      */     
/*  327 */     removeDefinition(defRec);
/*  328 */     this.imports.remove(defRef);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addImport(Element imp) {
/*  335 */     String bindings = imp.getAttributeNS((String)null, "bindings");
/*  336 */     Node n = this.ctx.getReferencedNode(imp, bindings);
/*  337 */     if (n.getNodeType() == 1 && (!"http://www.w3.org/2004/xbl".equals(n.getNamespaceURI()) || !"xbl".equals(n.getLocalName())))
/*      */     {
/*      */       
/*  340 */       throw new BridgeException(this.ctx, imp, "uri.badTarget", new Object[] { n });
/*      */     }
/*      */ 
/*      */     
/*  344 */     ImportRecord ir = new ImportRecord(imp, n);
/*  345 */     this.imports.put(imp, ir);
/*      */     
/*  347 */     NodeEventTarget et = (NodeEventTarget)imp;
/*  348 */     et.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.importAttrListener, false, null);
/*      */ 
/*      */ 
/*      */     
/*  352 */     et = (NodeEventTarget)n;
/*  353 */     et.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", ir.importInsertedListener, false, null);
/*      */ 
/*      */     
/*  356 */     et.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", ir.importRemovedListener, false, null);
/*      */ 
/*      */     
/*  359 */     et.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", ir.importSubtreeListener, false, null);
/*      */ 
/*      */     
/*  362 */     addImportedDefinitions(imp, n);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addImportedDefinitions(Element imp, Node n) {
/*  369 */     if (n instanceof XBLOMDefinitionElement) {
/*  370 */       XBLOMDefinitionElement def = (XBLOMDefinitionElement)n;
/*  371 */       String ns = def.getElementNamespaceURI();
/*  372 */       String ln = def.getElementLocalName();
/*  373 */       addDefinition(ns, ln, def, imp);
/*      */     } else {
/*  375 */       n = n.getFirstChild();
/*  376 */       while (n != null) {
/*  377 */         addImportedDefinitions(imp, n);
/*  378 */         n = n.getNextSibling();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeImport(Element imp) {
/*  387 */     ImportRecord ir = (ImportRecord)this.imports.get(imp);
/*  388 */     NodeEventTarget et = (NodeEventTarget)ir.node;
/*  389 */     et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", ir.importInsertedListener, false);
/*      */ 
/*      */     
/*  392 */     et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", ir.importRemovedListener, false);
/*      */ 
/*      */     
/*  395 */     et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", ir.importSubtreeListener, false);
/*      */ 
/*      */ 
/*      */     
/*  399 */     et = (NodeEventTarget)imp;
/*  400 */     et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.importAttrListener, false);
/*      */ 
/*      */ 
/*      */     
/*  404 */     Object[] defRecs = this.definitions.getValuesArray();
/*  405 */     for (Object defRec1 : defRecs) {
/*  406 */       DefinitionRecord defRec = (DefinitionRecord)defRec1;
/*  407 */       if (defRec.importElement == imp) {
/*  408 */         removeDefinition(defRec);
/*      */       }
/*      */     } 
/*  411 */     this.imports.remove(imp);
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
/*      */   protected void addDefinition(String namespaceURI, String localName, XBLOMDefinitionElement def, Element imp) {
/*  432 */     ImportRecord ir = (ImportRecord)this.imports.get(imp);
/*  433 */     DefinitionRecord oldDefRec = null;
/*      */     
/*  435 */     TreeSet<DefinitionRecord> defs = (TreeSet)this.definitionLists.get(namespaceURI, localName);
/*  436 */     if (defs == null) {
/*  437 */       defs = new TreeSet();
/*  438 */       this.definitionLists.put(namespaceURI, localName, defs);
/*  439 */     } else if (defs.size() > 0) {
/*  440 */       oldDefRec = defs.first();
/*      */     } 
/*  442 */     XBLOMTemplateElement template = null;
/*  443 */     for (Node n = def.getFirstChild(); n != null; n = n.getNextSibling()) {
/*  444 */       if (n instanceof XBLOMTemplateElement) {
/*  445 */         template = (XBLOMTemplateElement)n;
/*      */         break;
/*      */       } 
/*      */     } 
/*  449 */     DefinitionRecord defRec = new DefinitionRecord(namespaceURI, localName, def, template, imp);
/*      */     
/*  451 */     defs.add(defRec);
/*  452 */     this.definitions.put(def, imp, defRec);
/*  453 */     addDefinitionElementListeners(def, ir);
/*  454 */     if (defs.first() != defRec) {
/*      */       return;
/*      */     }
/*  457 */     if (oldDefRec != null) {
/*  458 */       XBLOMDefinitionElement oldDef = oldDefRec.definition;
/*  459 */       XBLOMTemplateElement oldTemplate = oldDefRec.template;
/*  460 */       if (oldTemplate != null) {
/*  461 */         removeTemplateElementListeners(oldTemplate, ir);
/*      */       }
/*  463 */       removeDefinitionElementListeners(oldDef, ir);
/*      */     } 
/*  465 */     if (template != null) {
/*  466 */       addTemplateElementListeners(template, ir);
/*      */     }
/*  468 */     if (this.isProcessing) {
/*  469 */       rebind(namespaceURI, localName, this.document.getDocumentElement());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addDefinitionElementListeners(XBLOMDefinitionElement def, ImportRecord ir) {
/*  478 */     XBLEventSupport es = (XBLEventSupport)def.initializeEventSupport();
/*  479 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", ir.defAttrListener, false);
/*      */ 
/*      */ 
/*      */     
/*  483 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", ir.defNodeInsertedListener, false);
/*      */ 
/*      */ 
/*      */     
/*  487 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", ir.defNodeRemovedListener, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addTemplateElementListeners(XBLOMTemplateElement template, ImportRecord ir) {
/*  498 */     XBLEventSupport es = (XBLEventSupport)template.initializeEventSupport();
/*      */     
/*  500 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", ir.templateMutationListener, false);
/*      */ 
/*      */ 
/*      */     
/*  504 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", ir.templateMutationListener, false);
/*      */ 
/*      */ 
/*      */     
/*  508 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", ir.templateMutationListener, false);
/*      */ 
/*      */ 
/*      */     
/*  512 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", ir.templateMutationListener, false);
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
/*      */   protected void removeDefinition(DefinitionRecord defRec) {
/*  525 */     TreeSet<DefinitionRecord> defs = (TreeSet)this.definitionLists.get(defRec.namespaceURI, defRec.localName);
/*      */     
/*  527 */     if (defs == null) {
/*      */       return;
/*      */     }
/*  530 */     Element imp = defRec.importElement;
/*  531 */     ImportRecord ir = (ImportRecord)this.imports.get(imp);
/*  532 */     DefinitionRecord activeDefRec = defs.first();
/*  533 */     defs.remove(defRec);
/*  534 */     this.definitions.remove(defRec.definition, imp);
/*  535 */     removeDefinitionElementListeners(defRec.definition, ir);
/*  536 */     if (defRec != activeDefRec) {
/*      */       return;
/*      */     }
/*  539 */     if (defRec.template != null) {
/*  540 */       removeTemplateElementListeners(defRec.template, ir);
/*      */     }
/*  542 */     rebind(defRec.namespaceURI, defRec.localName, this.document.getDocumentElement());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeDefinitionElementListeners(XBLOMDefinitionElement def, ImportRecord ir) {
/*  552 */     XBLEventSupport es = (XBLEventSupport)def.initializeEventSupport();
/*  553 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", ir.defAttrListener, false);
/*      */ 
/*      */ 
/*      */     
/*  557 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", ir.defNodeInsertedListener, false);
/*      */ 
/*      */ 
/*      */     
/*  561 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", ir.defNodeRemovedListener, false);
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
/*      */   protected void removeTemplateElementListeners(XBLOMTemplateElement template, ImportRecord ir) {
/*  573 */     XBLEventSupport es = (XBLEventSupport)template.initializeEventSupport();
/*      */     
/*  575 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", ir.templateMutationListener, false);
/*      */ 
/*      */ 
/*      */     
/*  579 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", ir.templateMutationListener, false);
/*      */ 
/*      */ 
/*      */     
/*  583 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", ir.templateMutationListener, false);
/*      */ 
/*      */ 
/*      */     
/*  587 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", ir.templateMutationListener, false);
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
/*      */   protected DefinitionRecord getActiveDefinition(String namespaceURI, String localName) {
/*  599 */     TreeSet<DefinitionRecord> defs = (TreeSet)this.definitionLists.get(namespaceURI, localName);
/*  600 */     if (defs == null || defs.size() == 0) {
/*  601 */       return null;
/*      */     }
/*  603 */     return defs.first();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void unbind(Element e) {
/*  610 */     if (e instanceof BindableElement) {
/*  611 */       setActiveDefinition((BindableElement)e, null);
/*      */     } else {
/*  613 */       NodeList nl = getXblScopedChildNodes(e);
/*  614 */       for (int i = 0; i < nl.getLength(); i++) {
/*  615 */         Node n = nl.item(i);
/*  616 */         if (n.getNodeType() == 1) {
/*  617 */           unbind((Element)n);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void bind(Element e) {
/*  627 */     AbstractDocument doc = (AbstractDocument)e.getOwnerDocument();
/*  628 */     if (doc != this.document) {
/*  629 */       XBLManager xm = doc.getXBLManager();
/*  630 */       if (xm instanceof DefaultXBLManager) {
/*  631 */         ((DefaultXBLManager)xm).bind(e);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*  636 */     if (e instanceof BindableElement) {
/*  637 */       DefinitionRecord defRec = getActiveDefinition(e.getNamespaceURI(), e.getLocalName());
/*      */ 
/*      */       
/*  640 */       setActiveDefinition((BindableElement)e, defRec);
/*      */     } else {
/*  642 */       NodeList nl = getXblScopedChildNodes(e);
/*  643 */       for (int i = 0; i < nl.getLength(); i++) {
/*  644 */         Node n = nl.item(i);
/*  645 */         if (n.getNodeType() == 1) {
/*  646 */           bind((Element)n);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void rebind(String namespaceURI, String localName, Element e) {
/*  657 */     AbstractDocument doc = (AbstractDocument)e.getOwnerDocument();
/*  658 */     if (doc != this.document) {
/*  659 */       XBLManager xm = doc.getXBLManager();
/*  660 */       if (xm instanceof DefaultXBLManager) {
/*  661 */         ((DefaultXBLManager)xm).rebind(namespaceURI, localName, e);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*  666 */     if (e instanceof BindableElement && namespaceURI.equals(e.getNamespaceURI()) && localName.equals(e.getLocalName())) {
/*      */ 
/*      */       
/*  669 */       DefinitionRecord defRec = getActiveDefinition(e.getNamespaceURI(), e.getLocalName());
/*      */ 
/*      */       
/*  672 */       setActiveDefinition((BindableElement)e, defRec);
/*      */     } else {
/*  674 */       NodeList nl = getXblScopedChildNodes(e);
/*  675 */       for (int i = 0; i < nl.getLength(); i++) {
/*  676 */         Node n = nl.item(i);
/*  677 */         if (n.getNodeType() == 1) {
/*  678 */           rebind(namespaceURI, localName, (Element)n);
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
/*      */   protected void setActiveDefinition(BindableElement elt, DefinitionRecord defRec) {
/*  690 */     XBLRecord rec = getRecord((Node)elt);
/*  691 */     rec.definitionElement = (defRec == null) ? null : defRec.definition;
/*  692 */     if (defRec != null && defRec.definition != null && defRec.template != null) {
/*      */ 
/*      */       
/*  695 */       setXblShadowTree(elt, cloneTemplate(defRec.template));
/*      */     } else {
/*  697 */       setXblShadowTree(elt, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setXblShadowTree(BindableElement elt, XBLOMShadowTreeElement newShadow) {
/*  706 */     XBLOMShadowTreeElement oldShadow = (XBLOMShadowTreeElement)getXblShadowTree((Node)elt);
/*      */     
/*  708 */     if (oldShadow != null) {
/*  709 */       fireShadowTreeEvent(elt, "unbinding", (XBLShadowTreeElement)oldShadow);
/*  710 */       ContentManager cm = getContentManager((Node)oldShadow);
/*  711 */       if (cm != null) {
/*  712 */         cm.dispose();
/*      */       }
/*  714 */       elt.setShadowTree(null);
/*  715 */       XBLRecord rec = getRecord((Node)oldShadow);
/*  716 */       rec.boundElement = null;
/*  717 */       oldShadow.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.docSubtreeListener, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  722 */     if (newShadow != null) {
/*  723 */       newShadow.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.docSubtreeListener, false, null);
/*      */ 
/*      */ 
/*      */       
/*  727 */       fireShadowTreeEvent(elt, "prebind", (XBLShadowTreeElement)newShadow);
/*  728 */       elt.setShadowTree(newShadow);
/*  729 */       XBLRecord rec = getRecord((Node)newShadow);
/*  730 */       rec.boundElement = elt;
/*  731 */       AbstractDocument doc = (AbstractDocument)elt.getOwnerDocument();
/*      */       
/*  733 */       XBLManager xm = doc.getXBLManager();
/*  734 */       ContentManager cm = new ContentManager(newShadow, xm);
/*  735 */       setContentManager((Element)newShadow, cm);
/*      */     } 
/*  737 */     invalidateChildNodes((Node)elt);
/*  738 */     if (newShadow != null) {
/*  739 */       NodeList nl = getXblScopedChildNodes((Node)elt);
/*  740 */       for (int i = 0; i < nl.getLength(); i++) {
/*  741 */         Node n = nl.item(i);
/*  742 */         if (n.getNodeType() == 1) {
/*  743 */           bind((Element)n);
/*      */         }
/*      */       } 
/*  746 */       dispatchBindingChangedEvent((Element)elt, (Element)newShadow);
/*  747 */       fireShadowTreeEvent(elt, "bound", (XBLShadowTreeElement)newShadow);
/*      */     } else {
/*  749 */       dispatchBindingChangedEvent((Element)elt, (Element)newShadow);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireShadowTreeEvent(BindableElement elt, String type, XBLShadowTreeElement e) {
/*  759 */     DocumentEvent de = (DocumentEvent)elt.getOwnerDocument();
/*  760 */     ShadowTreeEvent evt = (ShadowTreeEvent)de.createEvent("ShadowTreeEvent");
/*      */     
/*  762 */     evt.initShadowTreeEventNS("http://www.w3.org/2004/xbl", type, true, false, e);
/*  763 */     elt.dispatchEvent((Event)evt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XBLOMShadowTreeElement cloneTemplate(XBLOMTemplateElement template) {
/*  771 */     XBLOMShadowTreeElement clone = (XBLOMShadowTreeElement)template.getOwnerDocument().createElementNS("http://www.w3.org/2004/xbl", "shadowTree");
/*      */ 
/*      */ 
/*      */     
/*  775 */     NamedNodeMap attrs = template.getAttributes();
/*  776 */     for (int i = 0; i < attrs.getLength(); i++) {
/*  777 */       Attr attr = (Attr)attrs.item(i);
/*  778 */       if (attr instanceof org.apache.batik.dom.AbstractAttrNS) {
/*  779 */         clone.setAttributeNodeNS(attr);
/*      */       } else {
/*  781 */         clone.setAttributeNode(attr);
/*      */       } 
/*      */     } 
/*  784 */     Node n = template.getFirstChild();
/*  785 */     for (; n != null; 
/*  786 */       n = n.getNextSibling()) {
/*  787 */       clone.appendChild(n.cloneNode(true));
/*      */     }
/*  789 */     return clone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblParentNode(Node n) {
/*  796 */     XBLOMContentElement xBLOMContentElement = getXblContentElement(n);
/*  797 */     Node parent = (xBLOMContentElement == null) ? n.getParentNode() : xBLOMContentElement.getParentNode();
/*      */ 
/*      */     
/*  800 */     if (parent instanceof XBLOMContentElement) {
/*  801 */       parent = parent.getParentNode();
/*      */     }
/*  803 */     if (parent instanceof XBLOMShadowTreeElement) {
/*  804 */       parent = getXblBoundElement(parent);
/*      */     }
/*  806 */     return parent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblChildNodes(Node n) {
/*  813 */     XBLRecord rec = getRecord(n);
/*  814 */     if (rec.childNodes == null) {
/*  815 */       rec.childNodes = new XblChildNodes(rec);
/*      */     }
/*  817 */     return rec.childNodes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblScopedChildNodes(Node n) {
/*  825 */     XBLRecord rec = getRecord(n);
/*  826 */     if (rec.scopedChildNodes == null) {
/*  827 */       rec.scopedChildNodes = new XblScopedChildNodes(rec);
/*      */     }
/*  829 */     return rec.scopedChildNodes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblFirstChild(Node n) {
/*  836 */     NodeList nl = getXblChildNodes(n);
/*  837 */     return nl.item(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblLastChild(Node n) {
/*  844 */     NodeList nl = getXblChildNodes(n);
/*  845 */     return nl.item(nl.getLength() - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblPreviousSibling(Node n) {
/*  853 */     Node p = getXblParentNode(n);
/*  854 */     if (p == null || (getRecord(p)).childNodes == null) {
/*  855 */       return n.getPreviousSibling();
/*      */     }
/*  857 */     XBLRecord rec = getRecord(n);
/*  858 */     if (!rec.linksValid) {
/*  859 */       updateLinks(n);
/*      */     }
/*  861 */     return rec.previousSibling;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getXblNextSibling(Node n) {
/*  869 */     Node p = getXblParentNode(n);
/*  870 */     if (p == null || (getRecord(p)).childNodes == null) {
/*  871 */       return n.getNextSibling();
/*      */     }
/*  873 */     XBLRecord rec = getRecord(n);
/*  874 */     if (!rec.linksValid) {
/*  875 */       updateLinks(n);
/*      */     }
/*  877 */     return rec.nextSibling;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblFirstElementChild(Node n) {
/*  884 */     n = getXblFirstChild(n);
/*  885 */     while (n != null && n.getNodeType() != 1) {
/*  886 */       n = getXblNextSibling(n);
/*      */     }
/*  888 */     return (Element)n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblLastElementChild(Node n) {
/*  895 */     n = getXblLastChild(n);
/*  896 */     while (n != null && n.getNodeType() != 1) {
/*  897 */       n = getXblPreviousSibling(n);
/*      */     }
/*  899 */     return (Element)n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblPreviousElementSibling(Node n) {
/*      */     do {
/*  908 */       n = getXblPreviousSibling(n);
/*  909 */     } while (n != null && n.getNodeType() != 1);
/*  910 */     return (Element)n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblNextElementSibling(Node n) {
/*      */     do {
/*  919 */       n = getXblNextSibling(n);
/*  920 */     } while (n != null && n.getNodeType() != 1);
/*  921 */     return (Element)n;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblBoundElement(Node n) {
/*      */     Node node;
/*  928 */     while (n != null && !(n instanceof XBLShadowTreeElement)) {
/*  929 */       XBLOMContentElement xBLOMContentElement1, content = getXblContentElement(n);
/*  930 */       if (content != null) {
/*  931 */         xBLOMContentElement1 = content;
/*      */       }
/*  933 */       node = xBLOMContentElement1.getParentNode();
/*      */     } 
/*  935 */     if (node == null) {
/*  936 */       return null;
/*      */     }
/*  938 */     return (Element)(getRecord(node)).boundElement;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getXblShadowTree(Node n) {
/*  945 */     if (n instanceof BindableElement) {
/*  946 */       BindableElement elt = (BindableElement)n;
/*  947 */       return (Element)elt.getShadowTree();
/*      */     } 
/*  949 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getXblDefinitions(Node n) {
/*  956 */     final String namespaceURI = n.getNamespaceURI();
/*  957 */     final String localName = n.getLocalName();
/*  958 */     return new NodeList() {
/*      */         public Node item(int i) {
/*  960 */           TreeSet<DefaultXBLManager.DefinitionRecord> defs = (TreeSet)DefaultXBLManager.this.definitionLists.get(namespaceURI, localName);
/*  961 */           if (defs != null && defs.size() != 0 && i == 0) {
/*  962 */             DefaultXBLManager.DefinitionRecord defRec = defs.first();
/*  963 */             return (Node)defRec.definition;
/*      */           } 
/*  965 */           return null;
/*      */         }
/*      */         public int getLength() {
/*  968 */           Set defs = (TreeSet)DefaultXBLManager.this.definitionLists.get(namespaceURI, localName);
/*  969 */           return (defs != null && defs.size() != 0) ? 1 : 0;
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XBLRecord getRecord(Node n) {
/*  978 */     XBLManagerData xmd = (XBLManagerData)n;
/*  979 */     XBLRecord rec = (XBLRecord)xmd.getManagerData();
/*  980 */     if (rec == null) {
/*  981 */       rec = new XBLRecord();
/*  982 */       rec.node = n;
/*  983 */       xmd.setManagerData(rec);
/*      */     } 
/*  985 */     return rec;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateLinks(Node n) {
/*  993 */     XBLRecord rec = getRecord(n);
/*  994 */     rec.previousSibling = null;
/*  995 */     rec.nextSibling = null;
/*  996 */     rec.linksValid = true;
/*  997 */     Node p = getXblParentNode(n);
/*  998 */     if (p != null) {
/*  999 */       NodeList xcn = getXblChildNodes(p);
/* 1000 */       if (xcn instanceof XblChildNodes) {
/* 1001 */         ((XblChildNodes)xcn).update();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XBLOMContentElement getXblContentElement(Node n) {
/* 1011 */     return (getRecord(n)).contentElement;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int computeBubbleLimit(Node from, Node to) {
/* 1021 */     ArrayList<Node> fromList = new ArrayList(10);
/* 1022 */     ArrayList<Node> toList = new ArrayList(10);
/* 1023 */     while (from != null) {
/* 1024 */       fromList.add(from);
/* 1025 */       from = ((NodeXBL)from).getXblParentNode();
/*      */     } 
/* 1027 */     while (to != null) {
/* 1028 */       toList.add(to);
/* 1029 */       to = ((NodeXBL)to).getXblParentNode();
/*      */     } 
/* 1031 */     int fromSize = fromList.size();
/* 1032 */     int toSize = toList.size();
/* 1033 */     for (int i = 0; i < fromSize && i < toSize; i++) {
/* 1034 */       Node n1 = fromList.get(fromSize - i - 1);
/* 1035 */       Node n2 = toList.get(toSize - i - 1);
/* 1036 */       if (n1 != n2) {
/* 1037 */         Node prevBoundElement = ((NodeXBL)n1).getXblBoundElement();
/* 1038 */         while (i > 0 && prevBoundElement != fromList.get(fromSize - i - 1)) {
/* 1039 */           i--;
/*      */         }
/* 1041 */         return fromSize - i - 1;
/*      */       } 
/*      */     } 
/* 1044 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContentManager getContentManager(Node n) {
/* 1052 */     Node b = getXblBoundElement(n);
/* 1053 */     if (b != null) {
/* 1054 */       Element s = getXblShadowTree(b);
/* 1055 */       if (s != null) {
/*      */         ContentManager cm;
/* 1057 */         Document doc = b.getOwnerDocument();
/* 1058 */         if (doc != this.document) {
/* 1059 */           DefaultXBLManager xm = (DefaultXBLManager)((AbstractDocument)doc).getXBLManager();
/*      */           
/* 1061 */           cm = (ContentManager)xm.contentManagers.get(s);
/*      */         } else {
/* 1063 */           cm = (ContentManager)this.contentManagers.get(s);
/*      */         } 
/* 1065 */         return cm;
/*      */       } 
/*      */     } 
/* 1068 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setContentManager(Element shadow, ContentManager cm) {
/* 1075 */     if (cm == null) {
/* 1076 */       this.contentManagers.remove(shadow);
/*      */     } else {
/* 1078 */       this.contentManagers.put(shadow, cm);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invalidateChildNodes(Node n) {
/* 1087 */     XBLRecord rec = getRecord(n);
/* 1088 */     if (rec.childNodes != null) {
/* 1089 */       rec.childNodes.invalidate();
/*      */     }
/* 1091 */     if (rec.scopedChildNodes != null) {
/* 1092 */       rec.scopedChildNodes.invalidate();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addContentSelectionChangedListener(ContentSelectionChangedListener l) {
/* 1102 */     this.contentSelectionChangedListenerList.add(ContentSelectionChangedListener.class, l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeContentSelectionChangedListener(ContentSelectionChangedListener l) {
/* 1112 */     this.contentSelectionChangedListenerList.remove(ContentSelectionChangedListener.class, l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object[] getContentSelectionChangedListeners() {
/* 1120 */     return this.contentSelectionChangedListenerList.getListenerList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void shadowTreeSelectedContentChanged(Set deselected, Set<Node> selected) {
/* 1128 */     Iterator<Node> i = deselected.iterator();
/* 1129 */     while (i.hasNext()) {
/* 1130 */       Node n = i.next();
/* 1131 */       if (n.getNodeType() == 1) {
/* 1132 */         unbind((Element)n);
/*      */       }
/*      */     } 
/* 1135 */     i = selected.iterator();
/* 1136 */     while (i.hasNext()) {
/* 1137 */       Node n = i.next();
/* 1138 */       if (n.getNodeType() == 1) {
/* 1139 */         bind((Element)n);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addBindingListener(BindingListener l) {
/* 1148 */     this.bindingListenerList.add(BindingListener.class, l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeBindingListener(BindingListener l) {
/* 1155 */     this.bindingListenerList.remove(BindingListener.class, l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dispatchBindingChangedEvent(Element bindableElement, Element shadowTree) {
/* 1165 */     Object[] ls = this.bindingListenerList.getListenerList();
/* 1166 */     for (int i = ls.length - 2; i >= 0; i -= 2) {
/* 1167 */       BindingListener l = (BindingListener)ls[i + 1];
/* 1168 */       l.bindingChanged(bindableElement, shadowTree);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isActiveDefinition(XBLOMDefinitionElement def, Element imp) {
/* 1178 */     DefinitionRecord defRec = (DefinitionRecord)this.definitions.get(def, imp);
/* 1179 */     if (defRec == null) {
/* 1180 */       return false;
/*      */     }
/* 1182 */     return (defRec == getActiveDefinition(defRec.namespaceURI, defRec.localName));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DefinitionRecord
/*      */     implements Comparable
/*      */   {
/*      */     public String namespaceURI;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String localName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XBLOMDefinitionElement definition;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XBLOMTemplateElement template;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Element importElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefinitionRecord(String ns, String ln, XBLOMDefinitionElement def, XBLOMTemplateElement t, Element imp) {
/* 1224 */       this.namespaceURI = ns;
/* 1225 */       this.localName = ln;
/* 1226 */       this.definition = def;
/* 1227 */       this.template = t;
/* 1228 */       this.importElement = imp;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object other) {
/* 1235 */       return (compareTo(other) == 0);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int compareTo(Object other) {
/*      */       AbstractNode n1, n2;
/* 1242 */       DefinitionRecord rec = (DefinitionRecord)other;
/*      */       
/* 1244 */       if (this.importElement == null) {
/* 1245 */         XBLOMDefinitionElement xBLOMDefinitionElement = this.definition;
/* 1246 */         if (rec.importElement == null) {
/* 1247 */           XBLOMDefinitionElement xBLOMDefinitionElement1 = rec.definition;
/*      */         } else {
/* 1249 */           n2 = (AbstractNode)rec.importElement;
/*      */         } 
/* 1251 */       } else if (rec.importElement == null) {
/* 1252 */         n1 = (AbstractNode)this.importElement;
/* 1253 */         XBLOMDefinitionElement xBLOMDefinitionElement = rec.definition;
/* 1254 */       } else if (this.definition.getOwnerDocument() == rec.definition.getOwnerDocument()) {
/*      */         
/* 1256 */         XBLOMDefinitionElement xBLOMDefinitionElement1 = this.definition;
/* 1257 */         XBLOMDefinitionElement xBLOMDefinitionElement2 = rec.definition;
/*      */       } else {
/* 1259 */         n1 = (AbstractNode)this.importElement;
/* 1260 */         n2 = (AbstractNode)rec.importElement;
/*      */       } 
/* 1262 */       short comp = n1.compareDocumentPosition((Node)n2);
/* 1263 */       if ((comp & 0x2) != 0) {
/* 1264 */         return -1;
/*      */       }
/* 1266 */       if ((comp & 0x4) != 0) {
/* 1267 */         return 1;
/*      */       }
/* 1269 */       return 0;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ImportRecord
/*      */   {
/*      */     public Element importElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.DefNodeInsertedListener defNodeInsertedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.DefNodeRemovedListener defNodeRemovedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.DefAttrListener defAttrListener;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.ImportInsertedListener importInsertedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.ImportRemovedListener importRemovedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.ImportSubtreeListener importSubtreeListener;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.TemplateMutationListener templateMutationListener;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ImportRecord(Element imp, Node n) {
/* 1331 */       this.importElement = imp;
/* 1332 */       this.node = n;
/* 1333 */       this.defNodeInsertedListener = new DefaultXBLManager.DefNodeInsertedListener(imp);
/* 1334 */       this.defNodeRemovedListener = new DefaultXBLManager.DefNodeRemovedListener(imp);
/* 1335 */       this.defAttrListener = new DefaultXBLManager.DefAttrListener(imp);
/* 1336 */       this.importInsertedListener = new DefaultXBLManager.ImportInsertedListener(imp);
/* 1337 */       this.importRemovedListener = new DefaultXBLManager.ImportRemovedListener();
/* 1338 */       this.importSubtreeListener = new DefaultXBLManager.ImportSubtreeListener(imp, this.importRemovedListener);
/*      */       
/* 1340 */       this.templateMutationListener = new DefaultXBLManager.TemplateMutationListener(imp);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ImportInsertedListener
/*      */     implements EventListener
/*      */   {
/*      */     protected Element importElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ImportInsertedListener(Element importElement) {
/* 1358 */       this.importElement = importElement;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1365 */       EventTarget target = evt.getTarget();
/* 1366 */       if (target instanceof XBLOMDefinitionElement) {
/* 1367 */         XBLOMDefinitionElement def = (XBLOMDefinitionElement)target;
/* 1368 */         DefaultXBLManager.this.addDefinition(def.getElementNamespaceURI(), def.getElementLocalName(), def, this.importElement);
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
/*      */   protected class ImportRemovedListener
/*      */     implements EventListener
/*      */   {
/* 1384 */     protected LinkedList toBeRemoved = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1390 */       this.toBeRemoved.add(evt.getTarget());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ImportSubtreeListener
/*      */     implements EventListener
/*      */   {
/*      */     protected Element importElement;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected DefaultXBLManager.ImportRemovedListener importRemovedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ImportSubtreeListener(Element imp, DefaultXBLManager.ImportRemovedListener irl) {
/* 1414 */       this.importElement = imp;
/* 1415 */       this.importRemovedListener = irl;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1422 */       Object[] defs = this.importRemovedListener.toBeRemoved.toArray();
/* 1423 */       this.importRemovedListener.toBeRemoved.clear();
/* 1424 */       for (Object def1 : defs) {
/* 1425 */         XBLOMDefinitionElement def = (XBLOMDefinitionElement)def1;
/* 1426 */         DefaultXBLManager.DefinitionRecord defRec = (DefaultXBLManager.DefinitionRecord)DefaultXBLManager.this.definitions.get(def, this.importElement);
/*      */         
/* 1428 */         DefaultXBLManager.this.removeDefinition(defRec);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DocInsertedListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 1442 */       EventTarget target = evt.getTarget();
/* 1443 */       if (target instanceof XBLOMDefinitionElement) {
/*      */         
/* 1445 */         if (DefaultXBLManager.this.getXblBoundElement((Node)target) == null) {
/* 1446 */           XBLOMDefinitionElement def = (XBLOMDefinitionElement)target;
/*      */           
/* 1448 */           if (def.getAttributeNS(null, "ref").length() == 0) {
/*      */             
/* 1450 */             DefaultXBLManager.this.addDefinition(def.getElementNamespaceURI(), def.getElementLocalName(), def, null);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 1455 */             DefaultXBLManager.this.addDefinitionRef((Element)def);
/*      */           } 
/*      */         } 
/* 1458 */       } else if (target instanceof org.apache.batik.anim.dom.XBLOMImportElement) {
/*      */         
/* 1460 */         if (DefaultXBLManager.this.getXblBoundElement((Node)target) == null) {
/* 1461 */           DefaultXBLManager.this.addImport((Element)target);
/*      */         }
/*      */       } else {
/* 1464 */         evt = XBLEventSupport.getUltimateOriginalEvent(evt);
/* 1465 */         target = evt.getTarget();
/* 1466 */         Node parent = DefaultXBLManager.this.getXblParentNode((Node)target);
/* 1467 */         if (parent != null) {
/* 1468 */           DefaultXBLManager.this.invalidateChildNodes(parent);
/*      */         }
/* 1470 */         if (target instanceof BindableElement) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1475 */           Node n = ((Node)target).getParentNode();
/* 1476 */           for (; n != null; 
/* 1477 */             n = n.getParentNode()) {
/* 1478 */             if (n instanceof BindableElement && (DefaultXBLManager.this.getRecord(n)).definitionElement != null) {
/*      */               return;
/*      */             }
/*      */           } 
/*      */           
/* 1483 */           DefaultXBLManager.this.bind((Element)target);
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
/*      */   protected class DocRemovedListener
/*      */     implements EventListener
/*      */   {
/* 1497 */     protected LinkedList defsToBeRemoved = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1502 */     protected LinkedList importsToBeRemoved = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1507 */     protected LinkedList nodesToBeInvalidated = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1513 */       EventTarget target = evt.getTarget();
/* 1514 */       if (target instanceof XBLOMDefinitionElement) {
/*      */         
/* 1516 */         if (DefaultXBLManager.this.getXblBoundElement((Node)target) == null) {
/* 1517 */           this.defsToBeRemoved.add(target);
/*      */         }
/* 1519 */       } else if (target instanceof org.apache.batik.anim.dom.XBLOMImportElement) {
/*      */         
/* 1521 */         if (DefaultXBLManager.this.getXblBoundElement((Node)target) == null) {
/* 1522 */           this.importsToBeRemoved.add(target);
/*      */         }
/*      */       } 
/*      */       
/* 1526 */       Node parent = DefaultXBLManager.this.getXblParentNode((Node)target);
/* 1527 */       if (parent != null) {
/* 1528 */         this.nodesToBeInvalidated.add(parent);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DocSubtreeListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 1542 */       Object[] defs = DefaultXBLManager.this.docRemovedListener.defsToBeRemoved.toArray();
/* 1543 */       DefaultXBLManager.this.docRemovedListener.defsToBeRemoved.clear();
/* 1544 */       for (Object def1 : defs) {
/* 1545 */         XBLOMDefinitionElement def = (XBLOMDefinitionElement)def1;
/* 1546 */         if (def.getAttributeNS(null, "ref").length() == 0) {
/* 1547 */           DefaultXBLManager.DefinitionRecord defRec = (DefaultXBLManager.DefinitionRecord)DefaultXBLManager.this.definitions.get(def, null);
/*      */           
/* 1549 */           DefaultXBLManager.this.removeDefinition(defRec);
/*      */         } else {
/* 1551 */           DefaultXBLManager.this.removeDefinitionRef((Element)def);
/*      */         } 
/*      */       } 
/*      */       
/* 1555 */       Object[] imps = DefaultXBLManager.this.docRemovedListener.importsToBeRemoved.toArray();
/* 1556 */       DefaultXBLManager.this.docRemovedListener.importsToBeRemoved.clear();
/* 1557 */       for (Object imp : imps) {
/* 1558 */         DefaultXBLManager.this.removeImport((Element)imp);
/*      */       }
/*      */       
/* 1561 */       Object[] nodes = DefaultXBLManager.this.docRemovedListener.nodesToBeInvalidated.toArray();
/* 1562 */       DefaultXBLManager.this.docRemovedListener.nodesToBeInvalidated.clear();
/* 1563 */       for (Object node : nodes) {
/* 1564 */         DefaultXBLManager.this.invalidateChildNodes((Node)node);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class TemplateMutationListener
/*      */     implements EventListener
/*      */   {
/*      */     protected Element importElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TemplateMutationListener(Element imp) {
/* 1583 */       this.importElement = imp;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1590 */       Node n = (Node)evt.getTarget();
/* 1591 */       while (n != null && !(n instanceof XBLOMDefinitionElement)) {
/* 1592 */         n = n.getParentNode();
/*      */       }
/*      */       
/* 1595 */       DefaultXBLManager.DefinitionRecord defRec = (DefaultXBLManager.DefinitionRecord)DefaultXBLManager.this.definitions.get(n, this.importElement);
/*      */       
/* 1597 */       if (defRec == null) {
/*      */         return;
/*      */       }
/*      */       
/* 1601 */       DefaultXBLManager.this.rebind(defRec.namespaceURI, defRec.localName, DefaultXBLManager.this.document.getDocumentElement());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DefAttrListener
/*      */     implements EventListener
/*      */   {
/*      */     protected Element importElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefAttrListener(Element imp) {
/* 1620 */       this.importElement = imp;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1627 */       EventTarget target = evt.getTarget();
/* 1628 */       if (!(target instanceof XBLOMDefinitionElement)) {
/*      */         return;
/*      */       }
/*      */       
/* 1632 */       XBLOMDefinitionElement def = (XBLOMDefinitionElement)target;
/* 1633 */       if (!DefaultXBLManager.this.isActiveDefinition(def, this.importElement)) {
/*      */         return;
/*      */       }
/*      */       
/* 1637 */       MutationEvent mevt = (MutationEvent)evt;
/* 1638 */       String attrName = mevt.getAttrName();
/* 1639 */       if (attrName.equals("element")) {
/* 1640 */         DefaultXBLManager.DefinitionRecord defRec = (DefaultXBLManager.DefinitionRecord)DefaultXBLManager.this.definitions.get(def, this.importElement);
/*      */         
/* 1642 */         DefaultXBLManager.this.removeDefinition(defRec);
/*      */         
/* 1644 */         DefaultXBLManager.this.addDefinition(def.getElementNamespaceURI(), def.getElementLocalName(), def, this.importElement);
/*      */ 
/*      */       
/*      */       }
/* 1648 */       else if (attrName.equals("ref") && 
/* 1649 */         mevt.getNewValue().length() != 0) {
/* 1650 */         DefaultXBLManager.DefinitionRecord defRec = (DefaultXBLManager.DefinitionRecord)DefaultXBLManager.this.definitions.get(def, this.importElement);
/*      */         
/* 1652 */         DefaultXBLManager.this.removeDefinition(defRec);
/* 1653 */         DefaultXBLManager.this.addDefinitionRef((Element)def);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DefNodeInsertedListener
/*      */     implements EventListener
/*      */   {
/*      */     protected Element importElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefNodeInsertedListener(Element imp) {
/* 1673 */       this.importElement = imp;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1680 */       MutationEvent mevt = (MutationEvent)evt;
/* 1681 */       Node parent = mevt.getRelatedNode();
/* 1682 */       if (!(parent instanceof XBLOMDefinitionElement)) {
/*      */         return;
/*      */       }
/*      */       
/* 1686 */       EventTarget target = evt.getTarget();
/* 1687 */       if (!(target instanceof XBLOMTemplateElement)) {
/*      */         return;
/*      */       }
/* 1690 */       XBLOMTemplateElement template = (XBLOMTemplateElement)target;
/*      */       
/* 1692 */       DefaultXBLManager.DefinitionRecord defRec = (DefaultXBLManager.DefinitionRecord)DefaultXBLManager.this.definitions.get(parent, this.importElement);
/*      */       
/* 1694 */       if (defRec == null) {
/*      */         return;
/*      */       }
/*      */       
/* 1698 */       DefaultXBLManager.ImportRecord ir = (DefaultXBLManager.ImportRecord)DefaultXBLManager.this.imports.get(this.importElement);
/*      */       
/* 1700 */       if (defRec.template != null) {
/* 1701 */         Node n = parent.getFirstChild();
/* 1702 */         for (; n != null; 
/* 1703 */           n = n.getNextSibling()) {
/* 1704 */           if (n == template) {
/* 1705 */             DefaultXBLManager.this.removeTemplateElementListeners(defRec.template, ir);
/* 1706 */             defRec.template = template; break;
/*      */           } 
/* 1708 */           if (n == defRec.template) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */       } else {
/* 1713 */         defRec.template = template;
/*      */       } 
/* 1715 */       DefaultXBLManager.this.addTemplateElementListeners(template, ir);
/* 1716 */       DefaultXBLManager.this.rebind(defRec.namespaceURI, defRec.localName, DefaultXBLManager.this.document.getDocumentElement());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DefNodeRemovedListener
/*      */     implements EventListener
/*      */   {
/*      */     protected Element importElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefNodeRemovedListener(Element imp) {
/* 1735 */       this.importElement = imp;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1742 */       MutationEvent mevt = (MutationEvent)evt;
/* 1743 */       Node parent = mevt.getRelatedNode();
/* 1744 */       if (!(parent instanceof XBLOMDefinitionElement)) {
/*      */         return;
/*      */       }
/*      */       
/* 1748 */       EventTarget target = evt.getTarget();
/* 1749 */       if (!(target instanceof XBLOMTemplateElement)) {
/*      */         return;
/*      */       }
/* 1752 */       XBLOMTemplateElement template = (XBLOMTemplateElement)target;
/*      */       
/* 1754 */       DefaultXBLManager.DefinitionRecord defRec = (DefaultXBLManager.DefinitionRecord)DefaultXBLManager.this.definitions.get(parent, this.importElement);
/*      */       
/* 1756 */       if (defRec == null || defRec.template != template) {
/*      */         return;
/*      */       }
/*      */       
/* 1760 */       DefaultXBLManager.ImportRecord ir = (DefaultXBLManager.ImportRecord)DefaultXBLManager.this.imports.get(this.importElement);
/*      */       
/* 1762 */       DefaultXBLManager.this.removeTemplateElementListeners(template, ir);
/* 1763 */       defRec.template = null;
/*      */       
/* 1765 */       Node n = template.getNextSibling();
/* 1766 */       for (; n != null; 
/* 1767 */         n = n.getNextSibling()) {
/* 1768 */         if (n instanceof XBLOMTemplateElement) {
/* 1769 */           defRec.template = (XBLOMTemplateElement)n;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 1774 */       DefaultXBLManager.this.addTemplateElementListeners(defRec.template, ir);
/* 1775 */       DefaultXBLManager.this.rebind(defRec.namespaceURI, defRec.localName, DefaultXBLManager.this.document.getDocumentElement());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ImportAttrListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 1789 */       EventTarget target = evt.getTarget();
/* 1790 */       if (target != evt.getCurrentTarget()) {
/*      */         return;
/*      */       }
/*      */       
/* 1794 */       MutationEvent mevt = (MutationEvent)evt;
/* 1795 */       if (mevt.getAttrName().equals("bindings")) {
/* 1796 */         Element imp = (Element)target;
/* 1797 */         DefaultXBLManager.this.removeImport(imp);
/* 1798 */         DefaultXBLManager.this.addImport(imp);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class RefAttrListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 1812 */       EventTarget target = evt.getTarget();
/* 1813 */       if (target != evt.getCurrentTarget()) {
/*      */         return;
/*      */       }
/*      */       
/* 1817 */       MutationEvent mevt = (MutationEvent)evt;
/* 1818 */       if (mevt.getAttrName().equals("ref")) {
/* 1819 */         Element defRef = (Element)target;
/* 1820 */         DefaultXBLManager.this.removeDefinitionRef(defRef);
/* 1821 */         if (mevt.getNewValue().length() == 0) {
/* 1822 */           XBLOMDefinitionElement def = (XBLOMDefinitionElement)defRef;
/*      */           
/* 1824 */           String ns = def.getElementNamespaceURI();
/* 1825 */           String ln = def.getElementLocalName();
/* 1826 */           DefaultXBLManager.this.addDefinition(ns, ln, (XBLOMDefinitionElement)defRef, null);
/*      */         } else {
/*      */           
/* 1829 */           DefaultXBLManager.this.addDefinitionRef(defRef);
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
/*      */   protected class XBLRecord
/*      */   {
/*      */     public Node node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.XblChildNodes childNodes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultXBLManager.XblScopedChildNodes scopedChildNodes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XBLOMContentElement contentElement;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XBLOMDefinitionElement definitionElement;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BindableElement boundElement;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean linksValid;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node nextSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node previousSibling;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class XblChildNodes
/*      */     implements NodeList
/*      */   {
/*      */     protected DefaultXBLManager.XBLRecord record;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected List nodes;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int size;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XblChildNodes(DefaultXBLManager.XBLRecord rec) {
/* 1912 */       this.record = rec;
/* 1913 */       this.nodes = new ArrayList();
/* 1914 */       this.size = -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void update() {
/* 1921 */       this.size = 0;
/* 1922 */       Node shadowTree = DefaultXBLManager.this.getXblShadowTree(this.record.node);
/* 1923 */       Node last = null;
/* 1924 */       Node m = (shadowTree == null) ? this.record.node.getFirstChild() : shadowTree.getFirstChild();
/*      */       
/* 1926 */       while (m != null) {
/* 1927 */         last = collectXblChildNodes(m, last);
/* 1928 */         m = m.getNextSibling();
/*      */       } 
/* 1930 */       if (last != null) {
/* 1931 */         DefaultXBLManager.XBLRecord rec = DefaultXBLManager.this.getRecord(last);
/* 1932 */         rec.nextSibling = null;
/* 1933 */         rec.linksValid = true;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node collectXblChildNodes(Node n, Node prev) {
/* 1941 */       boolean isChild = false;
/* 1942 */       if (n.getNodeType() == 1) {
/* 1943 */         if (!"http://www.w3.org/2004/xbl".equals(n.getNamespaceURI())) {
/* 1944 */           isChild = true;
/* 1945 */         } else if (n instanceof XBLOMContentElement) {
/* 1946 */           ContentManager cm = DefaultXBLManager.this.getContentManager(n);
/* 1947 */           if (cm != null) {
/* 1948 */             NodeList selected = cm.getSelectedContent((XBLOMContentElement)n);
/*      */             
/* 1950 */             for (int i = 0; i < selected.getLength(); i++) {
/* 1951 */               prev = collectXblChildNodes(selected.item(i), prev);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 1957 */         isChild = true;
/*      */       } 
/* 1959 */       if (isChild) {
/* 1960 */         this.nodes.add(n);
/* 1961 */         this.size++;
/* 1962 */         if (prev != null) {
/* 1963 */           DefaultXBLManager.XBLRecord xBLRecord = DefaultXBLManager.this.getRecord(prev);
/* 1964 */           xBLRecord.nextSibling = n;
/* 1965 */           xBLRecord.linksValid = true;
/*      */         } 
/* 1967 */         DefaultXBLManager.XBLRecord rec = DefaultXBLManager.this.getRecord(n);
/* 1968 */         rec.previousSibling = prev;
/* 1969 */         rec.linksValid = true;
/* 1970 */         prev = n;
/*      */       } 
/* 1972 */       return prev;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void invalidate() {
/* 1981 */       for (int i = 0; i < this.size; i++) {
/* 1982 */         DefaultXBLManager.XBLRecord rec = DefaultXBLManager.this.getRecord(this.nodes.get(i));
/* 1983 */         rec.previousSibling = null;
/* 1984 */         rec.nextSibling = null;
/* 1985 */         rec.linksValid = false;
/*      */       } 
/* 1987 */       this.nodes.clear();
/* 1988 */       this.size = -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node getFirstNode() {
/* 1995 */       if (this.size == -1) {
/* 1996 */         update();
/*      */       }
/* 1998 */       return (this.size == 0) ? null : this.nodes.get(0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node getLastNode() {
/* 2005 */       if (this.size == -1) {
/* 2006 */         update();
/*      */       }
/* 2008 */       return (this.size == 0) ? null : this.nodes.get(this.nodes.size() - 1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node item(int index) {
/* 2015 */       if (this.size == -1) {
/* 2016 */         update();
/*      */       }
/* 2018 */       if (index < 0 || index >= this.size) {
/* 2019 */         return null;
/*      */       }
/* 2021 */       return this.nodes.get(index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLength() {
/* 2028 */       if (this.size == -1) {
/* 2029 */         update();
/*      */       }
/* 2031 */       return this.size;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class XblScopedChildNodes
/*      */     extends XblChildNodes
/*      */   {
/*      */     public XblScopedChildNodes(DefaultXBLManager.XBLRecord rec) {
/* 2044 */       super(rec);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void update() {
/* 2051 */       this.size = 0;
/* 2052 */       Node shadowTree = DefaultXBLManager.this.getXblShadowTree(this.record.node);
/* 2053 */       Node n = (shadowTree == null) ? this.record.node.getFirstChild() : shadowTree.getFirstChild();
/*      */       
/* 2055 */       while (n != null) {
/* 2056 */         collectXblScopedChildNodes(n);
/* 2057 */         n = n.getNextSibling();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void collectXblScopedChildNodes(Node n) {
/* 2065 */       boolean isChild = false;
/* 2066 */       if (n.getNodeType() == 1) {
/* 2067 */         if (!n.getNamespaceURI().equals("http://www.w3.org/2004/xbl")) {
/* 2068 */           isChild = true;
/* 2069 */         } else if (n instanceof XBLOMContentElement) {
/* 2070 */           ContentManager cm = DefaultXBLManager.this.getContentManager(n);
/* 2071 */           if (cm != null) {
/* 2072 */             NodeList selected = cm.getSelectedContent((XBLOMContentElement)n);
/*      */             
/* 2074 */             for (int i = 0; i < selected.getLength(); i++) {
/* 2075 */               collectXblScopedChildNodes(selected.item(i));
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } else {
/* 2080 */         isChild = true;
/*      */       } 
/* 2082 */       if (isChild) {
/* 2083 */         this.nodes.add(n);
/* 2084 */         this.size++;
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/DefaultXBLManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */