/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import org.apache.batik.css.engine.CSSNavigableDocument;
/*     */ import org.apache.batik.css.engine.CSSNavigableDocumentListener;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.AbstractStylableDocument;
/*     */ import org.apache.batik.dom.GenericAttr;
/*     */ import org.apache.batik.dom.GenericAttrNS;
/*     */ import org.apache.batik.dom.GenericCDATASection;
/*     */ import org.apache.batik.dom.GenericComment;
/*     */ import org.apache.batik.dom.GenericDocumentFragment;
/*     */ import org.apache.batik.dom.GenericElement;
/*     */ import org.apache.batik.dom.GenericEntityReference;
/*     */ import org.apache.batik.dom.GenericProcessingInstruction;
/*     */ import org.apache.batik.dom.GenericText;
/*     */ import org.apache.batik.dom.StyleSheetFactory;
/*     */ import org.apache.batik.dom.events.EventSupport;
/*     */ import org.apache.batik.dom.svg.IdContainer;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.i18n.LocalizableSupport;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.w3c.dom.Text;
/*     */ import org.w3c.dom.css.CSSStyleDeclaration;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.MutationEvent;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ import org.w3c.dom.svg.SVGLangSpace;
/*     */ import org.w3c.dom.svg.SVGSVGElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMDocument
/*     */   extends AbstractStylableDocument
/*     */   implements CSSNavigableDocument, IdContainer, SVGConstants, SVGDocument
/*     */ {
/*     */   protected static final String RESOURCES = "org.apache.batik.dom.svg.resources.Messages";
/*  98 */   protected transient LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.dom.svg.resources.Messages", getClass().getClassLoader());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   protected String referrer = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ParsedURL url;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected transient boolean readonly;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSVG12;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   protected HashMap cssNavigableDocumentListeners = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   protected AnimatedAttributeListener mainAnimatedAttributeListener = new AnimAttrListener();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   protected LinkedList animatedAttributeListeners = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected transient SVGContext svgContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMDocument() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMDocument(DocumentType dt, DOMImplementation impl) {
/* 154 */     super(dt, impl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale l) {
/* 161 */     super.setLocale(l);
/* 162 */     this.localizableSupport.setLocale(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatMessage(String key, Object[] args) throws MissingResourceException {
/*     */     try {
/* 171 */       return super.formatMessage(key, args);
/* 172 */     } catch (MissingResourceException e) {
/* 173 */       return this.localizableSupport.formatMessage(key, args);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 181 */     StringBuffer sb = new StringBuffer();
/* 182 */     boolean preserve = false;
/*     */     
/* 184 */     Node n = getDocumentElement().getFirstChild();
/* 185 */     for (; n != null; 
/* 186 */       n = n.getNextSibling()) {
/* 187 */       String ns = n.getNamespaceURI();
/* 188 */       if (ns != null && ns.equals("http://www.w3.org/2000/svg") && 
/* 189 */         n.getLocalName().equals("title")) {
/* 190 */         preserve = ((SVGLangSpace)n).getXMLspace().equals("preserve");
/* 191 */         n = n.getFirstChild();
/* 192 */         for (; n != null; 
/* 193 */           n = n.getNextSibling()) {
/* 194 */           if (n.getNodeType() == 3) {
/* 195 */             sb.append(n.getNodeValue());
/*     */           }
/*     */         } 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 203 */     String s = sb.toString();
/* 204 */     return preserve ? XMLSupport.preserveXMLSpace(s) : XMLSupport.defaultXMLSpace(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReferrer() {
/* 213 */     return this.referrer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReferrer(String s) {
/* 220 */     this.referrer = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDomain() {
/* 227 */     return (this.url == null) ? null : this.url.getHost();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGSVGElement getRootElement() {
/* 234 */     return (SVGSVGElement)getDocumentElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURL() {
/* 241 */     return this.documentURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getURLObject() {
/*     */     try {
/* 251 */       return new URL(this.documentURI);
/* 252 */     } catch (MalformedURLException e) {
/* 253 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURL getParsedURL() {
/* 261 */     return this.url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURLObject(URL url) {
/* 268 */     setParsedURL(new ParsedURL(url));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParsedURL(ParsedURL url) {
/* 275 */     this.url = url;
/* 276 */     this.documentURI = (url == null) ? null : url.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentURI(String uri) {
/* 283 */     this.documentURI = uri;
/* 284 */     this.url = (uri == null) ? null : new ParsedURL(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElement(String tagName) throws DOMException {
/* 291 */     return (Element)new GenericElement(tagName.intern(), (AbstractDocument)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentFragment createDocumentFragment() {
/* 298 */     return (DocumentFragment)new GenericDocumentFragment((AbstractDocument)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Text createTextNode(String data) {
/* 305 */     return (Text)new GenericText(data, (AbstractDocument)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Comment createComment(String data) {
/* 312 */     return (Comment)new GenericComment(data, (AbstractDocument)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CDATASection createCDATASection(String data) throws DOMException {
/* 319 */     return (CDATASection)new GenericCDATASection(data, (AbstractDocument)this);
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
/*     */   public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
/* 331 */     if ("xml-stylesheet".equals(target)) {
/* 332 */       return (ProcessingInstruction)new SVGStyleSheetProcessingInstruction(data, (AbstractDocument)this, (StyleSheetFactory)getImplementation());
/*     */     }
/*     */     
/* 335 */     return (ProcessingInstruction)new GenericProcessingInstruction(target, data, (AbstractDocument)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attr createAttribute(String name) throws DOMException {
/* 342 */     return (Attr)new GenericAttr(name.intern(), (AbstractDocument)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityReference createEntityReference(String name) throws DOMException {
/* 350 */     return (EntityReference)new GenericEntityReference(name, (AbstractDocument)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
/* 358 */     if (namespaceURI == null) {
/* 359 */       return (Attr)new GenericAttr(qualifiedName.intern(), (AbstractDocument)this);
/*     */     }
/* 361 */     return (Attr)new GenericAttrNS(namespaceURI.intern(), qualifiedName.intern(), (AbstractDocument)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
/* 372 */     SVGDOMImplementation impl = (SVGDOMImplementation)this.implementation;
/* 373 */     return impl.createElementNS((AbstractDocument)this, namespaceURI, qualifiedName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSVG12() {
/* 380 */     return this.isSVG12;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsSVG12(boolean b) {
/* 387 */     this.isSVG12 = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isId(Attr node) {
/* 395 */     if (node.getNamespaceURI() == null) {
/* 396 */       return "id".equals(node.getNodeName());
/*     */     }
/* 398 */     return node.getNodeName().equals("xml:id");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSVGContext(SVGContext ctx) {
/* 407 */     this.svgContext = ctx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGContext getSVGContext() {
/* 414 */     return this.svgContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCSSNavigableDocumentListener(CSSNavigableDocumentListener l) {
/* 425 */     if (this.cssNavigableDocumentListeners.containsKey(l)) {
/*     */       return;
/*     */     }
/*     */     
/* 429 */     DOMNodeInsertedListenerWrapper nodeInserted = new DOMNodeInsertedListenerWrapper(l);
/*     */     
/* 431 */     DOMNodeRemovedListenerWrapper nodeRemoved = new DOMNodeRemovedListenerWrapper(l);
/*     */     
/* 433 */     DOMSubtreeModifiedListenerWrapper subtreeModified = new DOMSubtreeModifiedListenerWrapper(l);
/*     */     
/* 435 */     DOMCharacterDataModifiedListenerWrapper cdataModified = new DOMCharacterDataModifiedListenerWrapper(l);
/*     */     
/* 437 */     DOMAttrModifiedListenerWrapper attrModified = new DOMAttrModifiedListenerWrapper(l);
/*     */ 
/*     */     
/* 440 */     this.cssNavigableDocumentListeners.put(l, new EventListener[] { nodeInserted, nodeRemoved, subtreeModified, cdataModified, attrModified });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 447 */     addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", nodeInserted, false, null);
/*     */     
/* 449 */     addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", nodeRemoved, false, null);
/*     */     
/* 451 */     addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", subtreeModified, false, null);
/*     */     
/* 453 */     addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", cdataModified, false, null);
/*     */ 
/*     */     
/* 456 */     addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", attrModified, false, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCSSNavigableDocumentListener(CSSNavigableDocumentListener l) {
/* 466 */     EventListener[] listeners = (EventListener[])this.cssNavigableDocumentListeners.get(l);
/*     */     
/* 468 */     if (listeners == null) {
/*     */       return;
/*     */     }
/*     */     
/* 472 */     removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", listeners[0], false);
/*     */     
/* 474 */     removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", listeners[1], false);
/*     */     
/* 476 */     removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", listeners[2], false);
/*     */     
/* 478 */     removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", listeners[3], false);
/*     */     
/* 480 */     removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", listeners[4], false);
/*     */ 
/*     */     
/* 483 */     this.cssNavigableDocumentListeners.remove(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatedAttributeListener getAnimatedAttributeListener() {
/* 490 */     return this.mainAnimatedAttributeListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void overrideStyleTextChanged(CSSStylableElement e, String text) {
/* 498 */     for (Object o : this.cssNavigableDocumentListeners.keySet()) {
/* 499 */       CSSNavigableDocumentListener l = (CSSNavigableDocumentListener)o;
/*     */       
/* 501 */       l.overrideStyleTextChanged(e, text);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void overrideStylePropertyRemoved(CSSStylableElement e, String name) {
/* 510 */     for (Object o : this.cssNavigableDocumentListeners.keySet()) {
/* 511 */       CSSNavigableDocumentListener l = (CSSNavigableDocumentListener)o;
/*     */       
/* 513 */       l.overrideStylePropertyRemoved(e, name);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void overrideStylePropertyChanged(CSSStylableElement e, String name, String value, String prio) {
/* 522 */     for (Object o : this.cssNavigableDocumentListeners.keySet()) {
/* 523 */       CSSNavigableDocumentListener l = (CSSNavigableDocumentListener)o;
/*     */       
/* 525 */       l.overrideStylePropertyChanged(e, name, value, prio);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAnimatedAttributeListener(AnimatedAttributeListener aal) {
/* 535 */     if (this.animatedAttributeListeners.contains(aal)) {
/*     */       return;
/*     */     }
/* 538 */     this.animatedAttributeListeners.add(aal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAnimatedAttributeListener(AnimatedAttributeListener aal) {
/* 546 */     this.animatedAttributeListeners.remove(aal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMNodeInsertedListenerWrapper
/*     */     implements EventListener
/*     */   {
/*     */     protected CSSNavigableDocumentListener listener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DOMNodeInsertedListenerWrapper(CSSNavigableDocumentListener l) {
/* 563 */       this.listener = l;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 570 */       evt = EventSupport.getUltimateOriginalEvent(evt);
/* 571 */       this.listener.nodeInserted((Node)evt.getTarget());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMNodeRemovedListenerWrapper
/*     */     implements EventListener
/*     */   {
/*     */     protected CSSNavigableDocumentListener listener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DOMNodeRemovedListenerWrapper(CSSNavigableDocumentListener l) {
/* 589 */       this.listener = l;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 596 */       evt = EventSupport.getUltimateOriginalEvent(evt);
/* 597 */       this.listener.nodeToBeRemoved((Node)evt.getTarget());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMSubtreeModifiedListenerWrapper
/*     */     implements EventListener
/*     */   {
/*     */     protected CSSNavigableDocumentListener listener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DOMSubtreeModifiedListenerWrapper(CSSNavigableDocumentListener l) {
/* 616 */       this.listener = l;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 623 */       evt = EventSupport.getUltimateOriginalEvent(evt);
/* 624 */       this.listener.subtreeModified((Node)evt.getTarget());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMCharacterDataModifiedListenerWrapper
/*     */     implements EventListener
/*     */   {
/*     */     protected CSSNavigableDocumentListener listener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DOMCharacterDataModifiedListenerWrapper(CSSNavigableDocumentListener l) {
/* 644 */       this.listener = l;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 651 */       evt = EventSupport.getUltimateOriginalEvent(evt);
/* 652 */       this.listener.characterDataModified((Node)evt.getTarget());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMAttrModifiedListenerWrapper
/*     */     implements EventListener
/*     */   {
/*     */     protected CSSNavigableDocumentListener listener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DOMAttrModifiedListenerWrapper(CSSNavigableDocumentListener l) {
/* 670 */       this.listener = l;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 677 */       evt = EventSupport.getUltimateOriginalEvent(evt);
/* 678 */       MutationEvent mevt = (MutationEvent)evt;
/* 679 */       this.listener.attrModified((Element)evt.getTarget(), (Attr)mevt.getRelatedNode(), mevt.getAttrChange(), mevt.getPrevValue(), mevt.getNewValue());
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
/*     */   protected class AnimAttrListener
/*     */     implements AnimatedAttributeListener
/*     */   {
/*     */     public void animatedAttributeChanged(Element e, AnimatedLiveAttributeValue alav) {
/* 700 */       for (Object animatedAttributeListener : SVGOMDocument.this.animatedAttributeListeners) {
/* 701 */         AnimatedAttributeListener aal = (AnimatedAttributeListener)animatedAttributeListener;
/*     */         
/* 703 */         aal.animatedAttributeChanged(e, alav);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void otherAnimationChanged(Element e, String type) {
/* 714 */       for (Object animatedAttributeListener : SVGOMDocument.this.animatedAttributeListeners) {
/* 715 */         AnimatedAttributeListener aal = (AnimatedAttributeListener)animatedAttributeListener;
/*     */         
/* 717 */         aal.otherAnimationChanged(e, type);
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
/*     */   public CSSStyleDeclaration getOverrideStyle(Element elt, String pseudoElt) {
/* 730 */     if (elt instanceof SVGStylableElement && pseudoElt == null) {
/* 731 */       return ((SVGStylableElement)elt).getOverrideStyle();
/*     */     }
/* 733 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadonly() {
/* 742 */     return this.readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean v) {
/* 749 */     this.readonly = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 756 */     return (Node)new SVGOMDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 764 */     super.copyInto(n);
/* 765 */     SVGOMDocument sd = (SVGOMDocument)n;
/* 766 */     sd.localizableSupport = new LocalizableSupport("org.apache.batik.dom.svg.resources.Messages", getClass().getClassLoader());
/*     */     
/* 768 */     sd.referrer = this.referrer;
/* 769 */     sd.url = this.url;
/* 770 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 778 */     super.deepCopyInto(n);
/* 779 */     SVGOMDocument sd = (SVGOMDocument)n;
/* 780 */     sd.localizableSupport = new LocalizableSupport("org.apache.batik.dom.svg.resources.Messages", getClass().getClassLoader());
/*     */     
/* 782 */     sd.referrer = this.referrer;
/* 783 */     sd.url = this.url;
/* 784 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 794 */     s.defaultReadObject();
/*     */     
/* 796 */     this.localizableSupport = new LocalizableSupport("org.apache.batik.dom.svg.resources.Messages", getClass().getClassLoader());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */