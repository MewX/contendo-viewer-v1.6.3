/*      */ package org.apache.batik.bridge;
/*      */ 
/*      */ import java.awt.Cursor;
/*      */ import java.awt.geom.Dimension2D;
/*      */ import java.io.IOException;
/*      */ import java.io.InterruptedIOException;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.net.MalformedURLException;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.WeakHashMap;
/*      */ import org.apache.batik.anim.dom.AnimatedAttributeListener;
/*      */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*      */ import org.apache.batik.anim.dom.SVGDOMImplementation;
/*      */ import org.apache.batik.anim.dom.SVGOMDocument;
/*      */ import org.apache.batik.anim.dom.SVGOMElement;
/*      */ import org.apache.batik.anim.dom.SVGStylableElement;
/*      */ import org.apache.batik.bridge.svg12.SVG12BridgeContext;
/*      */ import org.apache.batik.bridge.svg12.SVG12BridgeExtension;
/*      */ import org.apache.batik.css.engine.CSSContext;
/*      */ import org.apache.batik.css.engine.CSSEngine;
/*      */ import org.apache.batik.css.engine.CSSEngineEvent;
/*      */ import org.apache.batik.css.engine.CSSEngineListener;
/*      */ import org.apache.batik.css.engine.CSSEngineUserAgent;
/*      */ import org.apache.batik.css.engine.CSSStylableElement;
/*      */ import org.apache.batik.css.engine.SystemColorSupport;
/*      */ import org.apache.batik.css.engine.value.Value;
/*      */ import org.apache.batik.dom.AbstractNode;
/*      */ import org.apache.batik.dom.AbstractStylableDocument;
/*      */ import org.apache.batik.dom.events.NodeEventTarget;
/*      */ import org.apache.batik.dom.svg.SVGContext;
/*      */ import org.apache.batik.dom.xbl.XBLManager;
/*      */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*      */ import org.apache.batik.gvt.GraphicsNode;
/*      */ import org.apache.batik.script.Interpreter;
/*      */ import org.apache.batik.script.InterpreterPool;
/*      */ import org.apache.batik.util.CleanerThread;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.apache.batik.util.Service;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventListener;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ import org.w3c.dom.events.MouseEvent;
/*      */ import org.w3c.dom.events.MutationEvent;
/*      */ import org.w3c.dom.svg.SVGDocument;
/*      */ import org.w3c.dom.svg.SVGSVGElement;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BridgeContext
/*      */   implements ErrorConstants, CSSContext
/*      */ {
/*      */   protected Document document;
/*      */   protected boolean isSVG12;
/*      */   protected GVTBuilder gvtBuilder;
/*  114 */   protected Map interpreterMap = new HashMap<Object, Object>(7);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map fontFamilyMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   protected Map viewportMap = new WeakHashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   protected List viewportStack = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected UserAgent userAgent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map elementNodeMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map nodeElementMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map namespaceURIMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Bridge defaultBridge;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Set reservedNamespaceSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map elementDataMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected InterpreterPool interpreterPool;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected DocumentLoader documentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Dimension2D documentSize;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TextPainter textPainter;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int STATIC = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INTERACTIVE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DYNAMIC = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  235 */   protected int dynamicStatus = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected UpdateManager updateManager;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XBLManager xblManager;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BridgeContext primaryContext;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  256 */   protected HashSet childContexts = new HashSet();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SVGAnimationEngine animationEngine;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int animationLimitingMode;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float animationLimitingAmount;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  276 */   private static InterpreterPool sharedPool = new InterpreterPool(); protected Set eventListenerSet; protected EventListener domCharacterDataModifiedEventListener; protected EventListener domAttrModifiedEventListener; protected EventListener domNodeInsertedEventListener; protected EventListener domNodeRemovedEventListener;
/*      */   protected CSSEngineListener cssPropertiesChangedListener;
/*      */   protected AnimatedAttributeListener animatedAttributeListener;
/*      */   protected FocusManager focusManager;
/*      */   protected CursorManager cursorManager;
/*      */   protected List extensions;
/*      */   
/*      */   public final FontFamilyResolver getFontFamilyResolver() {
/*  284 */     return this.userAgent.getFontFamilyResolver();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BridgeContext(UserAgent userAgent) {
/*  292 */     this(userAgent, sharedPool, new DocumentLoader(userAgent));
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
/*      */   public BridgeContext(UserAgent userAgent, DocumentLoader loader) {
/*  304 */     this(userAgent, sharedPool, loader);
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
/*      */   protected void finalize() {
/*  326 */     if (this.primaryContext != null) {
/*  327 */       dispose();
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
/*      */   public BridgeContext createSubBridgeContext(SVGOMDocument newDoc) {
/*  341 */     CSSEngine eng = newDoc.getCSSEngine();
/*  342 */     if (eng != null) {
/*  343 */       BridgeContext bridgeContext = (BridgeContext)newDoc.getCSSEngine().getCSSContext();
/*  344 */       return bridgeContext;
/*      */     } 
/*      */     
/*  347 */     BridgeContext subCtx = createBridgeContext(newDoc);
/*  348 */     subCtx.primaryContext = (this.primaryContext != null) ? this.primaryContext : this;
/*  349 */     subCtx.primaryContext.childContexts.add(new WeakReference<BridgeContext>(subCtx));
/*  350 */     subCtx.dynamicStatus = this.dynamicStatus;
/*  351 */     subCtx.setGVTBuilder(getGVTBuilder());
/*  352 */     subCtx.setTextPainter(getTextPainter());
/*  353 */     subCtx.setDocument((Document)newDoc);
/*  354 */     subCtx.initializeDocument((Document)newDoc);
/*  355 */     if (isInteractive())
/*  356 */       subCtx.addUIEventListeners((Document)newDoc); 
/*  357 */     return subCtx;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BridgeContext createBridgeContext(SVGOMDocument doc) {
/*  366 */     if (doc.isSVG12()) {
/*  367 */       return (BridgeContext)new SVG12BridgeContext(getUserAgent(), getDocumentLoader());
/*      */     }
/*  369 */     return new BridgeContext(getUserAgent(), getDocumentLoader());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initializeDocument(Document document) {
/*  376 */     SVGOMDocument doc = (SVGOMDocument)document;
/*  377 */     CSSEngine eng = doc.getCSSEngine();
/*  378 */     if (eng == null) {
/*      */       
/*  380 */       SVGDOMImplementation impl = (SVGDOMImplementation)doc.getImplementation();
/*  381 */       eng = impl.createCSSEngine((AbstractStylableDocument)doc, this);
/*  382 */       eng.setCSSEngineUserAgent(new CSSEngineUserAgentWrapper(this.userAgent));
/*  383 */       doc.setCSSEngine(eng);
/*  384 */       eng.setMedia(this.userAgent.getMedia());
/*  385 */       String uri = this.userAgent.getUserStyleSheetURI();
/*  386 */       if (uri != null) {
/*      */         try {
/*  388 */           ParsedURL url = new ParsedURL(uri);
/*  389 */           eng.setUserAgentStyleSheet(eng.parseStyleSheet(url, "all"));
/*      */         }
/*  391 */         catch (Exception e) {
/*  392 */           this.userAgent.displayError(e);
/*      */         } 
/*      */       }
/*  395 */       eng.setAlternateStyleSheet(this.userAgent.getAlternateStyleSheet());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSEngine getCSSEngineForElement(Element e) {
/*  403 */     SVGOMDocument doc = (SVGOMDocument)e.getOwnerDocument();
/*  404 */     return doc.getCSSEngine();
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
/*      */   public void setTextPainter(TextPainter textPainter) {
/*  417 */     this.textPainter = textPainter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextPainter getTextPainter() {
/*  424 */     return this.textPainter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Document getDocument() {
/*  431 */     return this.document;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setDocument(Document document) {
/*  440 */     if (this.document != document) {
/*  441 */       this.fontFamilyMap = null;
/*      */     }
/*  443 */     this.document = document;
/*  444 */     this.isSVG12 = ((SVGOMDocument)document).isSVG12();
/*  445 */     registerSVGBridges();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map getFontFamilyMap() {
/*  452 */     if (this.fontFamilyMap == null) {
/*  453 */       this.fontFamilyMap = new HashMap<Object, Object>();
/*      */     }
/*  455 */     return this.fontFamilyMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setFontFamilyMap(Map fontFamilyMap) {
/*  464 */     this.fontFamilyMap = fontFamilyMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setElementData(Node n, Object data) {
/*  473 */     if (this.elementDataMap == null) {
/*  474 */       this.elementDataMap = new WeakHashMap<Object, Object>();
/*      */     }
/*  476 */     this.elementDataMap.put(n, new SoftReference(data));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getElementData(Node n) {
/*  483 */     if (this.elementDataMap == null)
/*  484 */       return null; 
/*  485 */     Object o = this.elementDataMap.get(n);
/*  486 */     if (o == null) return null; 
/*  487 */     SoftReference sr = (SoftReference)o;
/*  488 */     o = sr.get();
/*  489 */     if (o == null) {
/*  490 */       this.elementDataMap.remove(n);
/*      */     }
/*  492 */     return o;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public UserAgent getUserAgent() {
/*  499 */     return this.userAgent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setUserAgent(UserAgent userAgent) {
/*  507 */     this.userAgent = userAgent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GVTBuilder getGVTBuilder() {
/*  514 */     return this.gvtBuilder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setGVTBuilder(GVTBuilder gvtBuilder) {
/*  521 */     this.gvtBuilder = gvtBuilder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InterpreterPool getInterpreterPool() {
/*  528 */     return this.interpreterPool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FocusManager getFocusManager() {
/*  535 */     return this.focusManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CursorManager getCursorManager() {
/*  542 */     return this.cursorManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setInterpreterPool(InterpreterPool interpreterPool) {
/*  551 */     this.interpreterPool = interpreterPool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Interpreter getInterpreter(String language) {
/*  560 */     if (this.document == null) {
/*  561 */       throw new RuntimeException("Unknown document");
/*      */     }
/*  563 */     Interpreter interpreter = (Interpreter)this.interpreterMap.get(language);
/*  564 */     if (interpreter == null) {
/*      */       try {
/*  566 */         interpreter = this.interpreterPool.createInterpreter(this.document, language, null);
/*      */ 
/*      */         
/*  569 */         String[] mimeTypes = interpreter.getMimeTypes();
/*  570 */         for (String mimeType : mimeTypes) {
/*  571 */           this.interpreterMap.put(mimeType, interpreter);
/*      */         }
/*  573 */       } catch (Exception e) {
/*  574 */         if (this.userAgent != null) {
/*  575 */           this.userAgent.displayError(e);
/*  576 */           return null;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  581 */     if (interpreter == null && 
/*  582 */       this.userAgent != null) {
/*  583 */       this.userAgent.displayError(new Exception("Unknown language: " + language));
/*      */     }
/*      */ 
/*      */     
/*  587 */     return interpreter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DocumentLoader getDocumentLoader() {
/*  594 */     return this.documentLoader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setDocumentLoader(DocumentLoader newDocumentLoader) {
/*  602 */     this.documentLoader = newDocumentLoader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension2D getDocumentSize() {
/*  610 */     return this.documentSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setDocumentSize(Dimension2D d) {
/*  619 */     this.documentSize = d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDynamic() {
/*  626 */     return (this.dynamicStatus == 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInteractive() {
/*  633 */     return (this.dynamicStatus != 0);
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
/*      */   public void setDynamicState(int status) {
/*  645 */     this.dynamicStatus = status;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDynamic(boolean dynamic) {
/*  653 */     if (dynamic) {
/*  654 */       setDynamicState(2);
/*      */     } else {
/*  656 */       setDynamicState(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInteractive(boolean interactive) {
/*  664 */     if (interactive) {
/*  665 */       setDynamicState(1);
/*      */     } else {
/*  667 */       setDynamicState(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public UpdateManager getUpdateManager() {
/*  674 */     return this.updateManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setUpdateManager(UpdateManager um) {
/*  681 */     this.updateManager = um;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setUpdateManager(BridgeContext ctx, UpdateManager um) {
/*  688 */     ctx.setUpdateManager(um);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setXBLManager(BridgeContext ctx, XBLManager xm) {
/*  695 */     ctx.xblManager = xm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSVG12() {
/*  702 */     return this.isSVG12;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BridgeContext getPrimaryBridgeContext() {
/*  709 */     if (this.primaryContext != null) {
/*  710 */       return this.primaryContext;
/*      */     }
/*  712 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BridgeContext[] getChildContexts() {
/*  719 */     BridgeContext[] res = new BridgeContext[this.childContexts.size()];
/*  720 */     Iterator<WeakReference> it = this.childContexts.iterator();
/*  721 */     for (int i = 0; i < res.length; i++) {
/*  722 */       WeakReference<BridgeContext> wr = it.next();
/*  723 */       res[i] = wr.get();
/*      */     } 
/*  725 */     return res;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGAnimationEngine getAnimationEngine() {
/*  733 */     if (this.animationEngine == null) {
/*  734 */       this.animationEngine = new SVGAnimationEngine(this.document, this);
/*  735 */       setAnimationLimitingMode();
/*      */     } 
/*  737 */     return this.animationEngine;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URIResolver createURIResolver(SVGDocument doc, DocumentLoader dl) {
/*  746 */     return new URIResolver(doc, dl);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getReferencedNode(Element e, String uri) {
/*      */     try {
/*  758 */       SVGDocument document = (SVGDocument)e.getOwnerDocument();
/*  759 */       URIResolver ur = createURIResolver(document, this.documentLoader);
/*  760 */       Node ref = ur.getNode(uri, e);
/*  761 */       if (ref == null) {
/*  762 */         throw new BridgeException(this, e, "uri.badTarget", new Object[] { uri });
/*      */       }
/*      */       
/*  765 */       SVGOMDocument refDoc = (ref.getNodeType() == 9) ? (SVGOMDocument)ref : (SVGOMDocument)ref.getOwnerDocument();
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
/*  776 */       if (refDoc != document) {
/*  777 */         createSubBridgeContext(refDoc);
/*      */       }
/*  779 */       return ref;
/*      */     }
/*  781 */     catch (MalformedURLException ex) {
/*  782 */       throw new BridgeException(this, e, ex, "uri.malformed", new Object[] { uri });
/*      */     }
/*  784 */     catch (InterruptedIOException ex) {
/*  785 */       throw new InterruptedBridgeException();
/*  786 */     } catch (IOException ex) {
/*      */       
/*  788 */       throw new BridgeException(this, e, ex, "uri.io", new Object[] { uri });
/*      */     }
/*  790 */     catch (SecurityException ex) {
/*  791 */       throw new BridgeException(this, e, ex, "uri.unsecure", new Object[] { uri });
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
/*      */   public Element getReferencedElement(Element e, String uri) {
/*  804 */     Node ref = getReferencedNode(e, uri);
/*  805 */     if (ref != null && ref.getNodeType() != 1) {
/*  806 */       throw new BridgeException(this, e, "uri.referenceDocument", new Object[] { uri });
/*      */     }
/*      */     
/*  809 */     return (Element)ref;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Viewport getViewport(Element e) {
/*  820 */     if (this.viewportStack != null) {
/*      */       
/*  822 */       if (this.viewportStack.size() == 0)
/*      */       {
/*  824 */         return (Viewport)this.viewportMap.get(this.userAgent);
/*      */       }
/*      */       
/*  827 */       return this.viewportStack.get(0);
/*      */     } 
/*      */ 
/*      */     
/*  831 */     e = SVGUtilities.getParentElement(e);
/*  832 */     while (e != null) {
/*  833 */       Viewport viewport = (Viewport)this.viewportMap.get(e);
/*  834 */       if (viewport != null) {
/*  835 */         return viewport;
/*      */       }
/*  837 */       e = SVGUtilities.getParentElement(e);
/*      */     } 
/*  839 */     return (Viewport)this.viewportMap.get(this.userAgent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void openViewport(Element e, Viewport viewport) {
/*  850 */     this.viewportMap.put(e, viewport);
/*  851 */     if (this.viewportStack == null) {
/*  852 */       this.viewportStack = new LinkedList();
/*      */     }
/*  854 */     this.viewportStack.add(0, viewport);
/*      */   }
/*      */   
/*      */   public void removeViewport(Element e) {
/*  858 */     this.viewportMap.remove(e);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeViewport(Element e) {
/*  867 */     this.viewportStack.remove(0);
/*  868 */     if (this.viewportStack.size() == 0) {
/*  869 */       this.viewportStack = null;
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
/*      */   public void bind(Node node, GraphicsNode gn) {
/*  884 */     if (this.elementNodeMap == null) {
/*  885 */       this.elementNodeMap = new WeakHashMap<Object, Object>();
/*  886 */       this.nodeElementMap = new WeakHashMap<Object, Object>();
/*      */     } 
/*  888 */     this.elementNodeMap.put(node, new SoftReference<GraphicsNode>(gn));
/*  889 */     this.nodeElementMap.put(gn, new SoftReference<Node>(node));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unbind(Node node) {
/*  898 */     if (this.elementNodeMap == null) {
/*      */       return;
/*      */     }
/*  901 */     GraphicsNode gn = null;
/*  902 */     SoftReference<GraphicsNode> sr = (SoftReference)this.elementNodeMap.get(node);
/*  903 */     if (sr != null)
/*  904 */       gn = sr.get(); 
/*  905 */     this.elementNodeMap.remove(node);
/*  906 */     if (gn != null) {
/*  907 */       this.nodeElementMap.remove(gn);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GraphicsNode getGraphicsNode(Node node) {
/*  917 */     if (this.elementNodeMap != null) {
/*  918 */       SoftReference<GraphicsNode> sr = (SoftReference)this.elementNodeMap.get(node);
/*  919 */       if (sr != null)
/*  920 */         return sr.get(); 
/*      */     } 
/*  922 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getElement(GraphicsNode gn) {
/*  932 */     if (this.nodeElementMap != null) {
/*  933 */       SoftReference<Node> sr = (SoftReference)this.nodeElementMap.get(gn);
/*  934 */       if (sr != null) {
/*  935 */         Node n = sr.get();
/*  936 */         if (n.getNodeType() == 1) {
/*  937 */           return (Element)n;
/*      */         }
/*      */       } 
/*      */     } 
/*  941 */     return null;
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
/*      */   public boolean hasGraphicsNodeBridge(Element element) {
/*  953 */     if (this.namespaceURIMap == null || element == null) {
/*  954 */       return false;
/*      */     }
/*  956 */     String localName = element.getLocalName();
/*  957 */     String namespaceURI = element.getNamespaceURI();
/*  958 */     namespaceURI = (namespaceURI == null) ? "" : namespaceURI;
/*  959 */     HashMap localNameMap = (HashMap)this.namespaceURIMap.get(namespaceURI);
/*  960 */     if (localNameMap == null) {
/*  961 */       return false;
/*      */     }
/*  963 */     return localNameMap.get(localName) instanceof GraphicsNodeBridge;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DocumentBridge getDocumentBridge() {
/*  970 */     return new SVGDocumentBridge();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Bridge getBridge(Element element) {
/*  979 */     if (this.namespaceURIMap == null || element == null) {
/*  980 */       return null;
/*      */     }
/*  982 */     String localName = element.getLocalName();
/*  983 */     String namespaceURI = element.getNamespaceURI();
/*  984 */     namespaceURI = (namespaceURI == null) ? "" : namespaceURI;
/*  985 */     return getBridge(namespaceURI, localName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Bridge getBridge(String namespaceURI, String localName) {
/*  996 */     Bridge bridge = null;
/*  997 */     if (this.namespaceURIMap != null) {
/*  998 */       HashMap localNameMap = (HashMap)this.namespaceURIMap.get(namespaceURI);
/*  999 */       if (localNameMap != null) {
/* 1000 */         bridge = (Bridge)localNameMap.get(localName);
/*      */       }
/*      */     } 
/* 1003 */     if (bridge == null && (this.reservedNamespaceSet == null || !this.reservedNamespaceSet.contains(namespaceURI)))
/*      */     {
/*      */       
/* 1006 */       bridge = this.defaultBridge;
/*      */     }
/* 1008 */     if (isDynamic()) {
/* 1009 */       return (bridge == null) ? null : bridge.getInstance();
/*      */     }
/* 1011 */     return bridge;
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
/*      */   public void putBridge(String namespaceURI, String localName, Bridge bridge) {
/* 1024 */     if (!namespaceURI.equals(bridge.getNamespaceURI()) || !localName.equals(bridge.getLocalName()))
/*      */     {
/* 1026 */       throw new RuntimeException("Invalid Bridge: " + namespaceURI + "/" + bridge.getNamespaceURI() + " " + localName + "/" + bridge.getLocalName() + " " + bridge.getClass());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1032 */     if (this.namespaceURIMap == null) {
/* 1033 */       this.namespaceURIMap = new HashMap<Object, Object>();
/*      */     }
/* 1035 */     namespaceURI = (namespaceURI == null) ? "" : namespaceURI;
/* 1036 */     HashMap<Object, Object> localNameMap = (HashMap)this.namespaceURIMap.get(namespaceURI);
/* 1037 */     if (localNameMap == null) {
/* 1038 */       localNameMap = new HashMap<Object, Object>();
/* 1039 */       this.namespaceURIMap.put(namespaceURI, localNameMap);
/*      */     } 
/* 1041 */     localNameMap.put(localName, bridge);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putBridge(Bridge bridge) {
/* 1051 */     putBridge(bridge.getNamespaceURI(), bridge.getLocalName(), bridge);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeBridge(String namespaceURI, String localName) {
/* 1062 */     if (this.namespaceURIMap == null) {
/*      */       return;
/*      */     }
/* 1065 */     namespaceURI = (namespaceURI == null) ? "" : namespaceURI;
/* 1066 */     HashMap localNameMap = (HashMap)this.namespaceURIMap.get(namespaceURI);
/* 1067 */     if (localNameMap != null) {
/* 1068 */       localNameMap.remove(localName);
/* 1069 */       if (localNameMap.isEmpty()) {
/* 1070 */         this.namespaceURIMap.remove(namespaceURI);
/* 1071 */         if (this.namespaceURIMap.isEmpty()) {
/* 1072 */           this.namespaceURIMap = null;
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
/*      */   public void setDefaultBridge(Bridge bridge) {
/* 1085 */     this.defaultBridge = bridge;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putReservedNamespaceURI(String namespaceURI) {
/* 1092 */     if (namespaceURI == null) {
/* 1093 */       namespaceURI = "";
/*      */     }
/* 1095 */     if (this.reservedNamespaceSet == null) {
/* 1096 */       this.reservedNamespaceSet = new HashSet();
/*      */     }
/* 1098 */     this.reservedNamespaceSet.add(namespaceURI);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeReservedNamespaceURI(String namespaceURI) {
/* 1105 */     if (namespaceURI == null) {
/* 1106 */       namespaceURI = "";
/*      */     }
/* 1108 */     if (this.reservedNamespaceSet != null) {
/* 1109 */       this.reservedNamespaceSet.remove(namespaceURI);
/* 1110 */       if (this.reservedNamespaceSet.isEmpty()) {
/* 1111 */         this.reservedNamespaceSet = null;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BridgeContext()
/*      */   {
/* 1122 */     this.eventListenerSet = new HashSet();
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
/* 1162 */     this.cursorManager = new CursorManager(this);
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
/* 2037 */     this.extensions = null; } public void addUIEventListeners(Document doc) { NodeEventTarget evtTarget = (NodeEventTarget)doc.getDocumentElement(); DOMMouseOverEventListener domMouseOverListener = new DOMMouseOverEventListener(); evtTarget.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", domMouseOverListener, true, null); storeEventListenerNS((EventTarget)evtTarget, "http://www.w3.org/2001/xml-events", "mouseover", domMouseOverListener, true); DOMMouseOutEventListener domMouseOutListener = new DOMMouseOutEventListener(); evtTarget.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", domMouseOutListener, true, null); storeEventListenerNS((EventTarget)evtTarget, "http://www.w3.org/2001/xml-events", "mouseout", domMouseOutListener, true); } public void removeUIEventListeners(Document doc) { EventTarget evtTarget = (EventTarget)doc.getDocumentElement(); synchronized (this.eventListenerSet) { for (Object anEventListenerSet : this.eventListenerSet) { EventListenerMememto elm = (EventListenerMememto)anEventListenerSet; NodeEventTarget et = elm.getTarget(); if (et == evtTarget) { EventListener el = elm.getListener(); boolean uc = elm.getUseCapture(); String t = elm.getEventType(); boolean n = elm.getNamespaced(); if (et == null || el == null || t == null) continue;  if (n) { String ns = elm.getNamespaceURI(); et.removeEventListenerNS(ns, t, el, uc); continue; }  et.removeEventListener(t, el, uc); }  }  }  } public void addDOMListeners() { SVGOMDocument doc = (SVGOMDocument)this.document; this.domAttrModifiedEventListener = new DOMAttrModifiedEventListener(); doc.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedEventListener, true, null); this.domNodeInsertedEventListener = new DOMNodeInsertedEventListener(); doc.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedEventListener, true, null); this.domNodeRemovedEventListener = new DOMNodeRemovedEventListener(); doc.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedEventListener, true, null); this.domCharacterDataModifiedEventListener = new DOMCharacterDataModifiedEventListener(); doc.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.domCharacterDataModifiedEventListener, true, null); this.animatedAttributeListener = new AnimatedAttrListener(); doc.addAnimatedAttributeListener(this.animatedAttributeListener); this.focusManager = new FocusManager(this.document); CSSEngine cssEngine = doc.getCSSEngine(); this.cssPropertiesChangedListener = new CSSPropertiesChangedListener(); cssEngine.addCSSEngineListener(this.cssPropertiesChangedListener); } protected void removeDOMListeners() { SVGOMDocument doc = (SVGOMDocument)this.document; doc.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedEventListener, true); doc.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedEventListener, true); doc.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedEventListener, true); doc.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.domCharacterDataModifiedEventListener, true); doc.removeAnimatedAttributeListener(this.animatedAttributeListener); CSSEngine cssEngine = doc.getCSSEngine(); if (cssEngine != null) { cssEngine.removeCSSEngineListener(this.cssPropertiesChangedListener); cssEngine.dispose(); doc.setCSSEngine(null); }  } protected void storeEventListener(EventTarget t, String s, EventListener l, boolean b) { synchronized (this.eventListenerSet) { this.eventListenerSet.add(new EventListenerMememto(t, s, l, b, this)); }  } protected void storeEventListenerNS(EventTarget t, String n, String s, EventListener l, boolean b) { synchronized (this.eventListenerSet) { this.eventListenerSet.add(new EventListenerMememto(t, n, s, l, b, this)); }  } public static class SoftReferenceMememto extends CleanerThread.SoftReferenceCleared { Object mememto; Set set; SoftReferenceMememto(Object ref, Object mememto, Set set) { super(ref); this.mememto = mememto; this.set = set; } public void cleared() { synchronized (this.set) { this.set.remove(this.mememto); this.mememto = null; this.set = null; }  } } protected static class EventListenerMememto { public SoftReference target; public SoftReference listener; public boolean useCapture; public String namespaceURI; public String eventType; public boolean namespaced; public EventListenerMememto(EventTarget t, String s, EventListener l, boolean b, BridgeContext ctx) { Set set = ctx.eventListenerSet; this.target = (SoftReference)new BridgeContext.SoftReferenceMememto(t, this, set); this.listener = (SoftReference)new BridgeContext.SoftReferenceMememto(l, this, set); this.eventType = s; this.useCapture = b; } public EventListenerMememto(EventTarget t, String n, String s, EventListener l, boolean b, BridgeContext ctx) { this(t, s, l, b, ctx); this.namespaceURI = n; this.namespaced = true; } public EventListener getListener() { return this.listener.get(); } public NodeEventTarget getTarget() { return this.target.get(); } public boolean getUseCapture() { return this.useCapture; } public String getNamespaceURI() { return this.namespaceURI; } public String getEventType() { return this.eventType; } public boolean getNamespaced() { return this.namespaced; } } public void addGVTListener(Document doc) { BridgeEventSupport.addGVTListener(this, doc); } protected void clearChildContexts() { this.childContexts.clear(); } public BridgeContext(UserAgent userAgent, InterpreterPool interpreterPool, DocumentLoader documentLoader) { this.eventListenerSet = new HashSet(); this.cursorManager = new CursorManager(this); this.extensions = null; this.userAgent = userAgent; this.viewportMap.put(userAgent, new UserAgentViewport(userAgent)); this.interpreterPool = interpreterPool; this.documentLoader = documentLoader; } public void dispose() { clearChildContexts(); synchronized (this.eventListenerSet) { for (Object anEventListenerSet : this.eventListenerSet) { EventListenerMememto m = (EventListenerMememto)anEventListenerSet; NodeEventTarget et = m.getTarget(); EventListener el = m.getListener(); boolean uc = m.getUseCapture(); String t = m.getEventType(); boolean n = m.getNamespaced(); if (et == null || el == null || t == null) continue;  if (n) { String ns = m.getNamespaceURI(); et.removeEventListenerNS(ns, t, el, uc); continue; }  et.removeEventListener(t, el, uc); }  }  if (this.document != null) { removeDOMListeners(); AbstractGraphicsNodeBridge.disposeTree(this.document); }  if (this.animationEngine != null) { this.animationEngine.dispose(); this.animationEngine = null; }  for (Object o : this.interpreterMap.values()) { Interpreter interpreter = (Interpreter)o; if (interpreter != null) interpreter.dispose();  }  this.interpreterMap.clear(); if (this.focusManager != null) this.focusManager.dispose();  if (this.elementDataMap != null) this.elementDataMap.clear();  if (this.nodeElementMap != null) this.nodeElementMap.clear();  if (this.elementNodeMap != null) this.elementNodeMap.clear();  } protected static SVGContext getSVGContext(Node node) { if (node instanceof SVGOMElement) return ((SVGOMElement)node).getSVGContext();  if (node instanceof SVGOMDocument) return ((SVGOMDocument)node).getSVGContext();  return null; } protected static BridgeUpdateHandler getBridgeUpdateHandler(Node node) { SVGContext ctx = getSVGContext(node); return (ctx == null) ? null : (BridgeUpdateHandler)ctx; } protected class DOMAttrModifiedEventListener implements EventListener {
/*      */     public void handleEvent(Event evt) { Node node = (Node)evt.getTarget(); BridgeUpdateHandler h = BridgeContext.getBridgeUpdateHandler(node); if (h != null) try { h.handleDOMAttrModifiedEvent((MutationEvent)evt); } catch (Exception e) { BridgeContext.this.userAgent.displayError(e); }   } } protected class DOMMouseOutEventListener implements EventListener {
/*      */     public void handleEvent(Event evt) { MouseEvent me = (MouseEvent)evt; Element newTarget = (Element)me.getRelatedTarget(); Cursor cursor = CursorManager.DEFAULT_CURSOR; if (newTarget != null) cursor = CSSUtilities.convertCursor(newTarget, BridgeContext.this);  if (cursor == null) cursor = CursorManager.DEFAULT_CURSOR;  BridgeContext.this.userAgent.setSVGCursor(cursor); } } protected class DOMMouseOverEventListener implements EventListener {
/*      */     public void handleEvent(Event evt) { Element target = (Element)evt.getTarget(); Cursor cursor = CSSUtilities.convertCursor(target, BridgeContext.this); if (cursor != null) BridgeContext.this.userAgent.setSVGCursor(cursor);  } } protected class DOMNodeInsertedEventListener implements EventListener {
/*      */     public void handleEvent(Event evt) { MutationEvent me = (MutationEvent)evt; BridgeUpdateHandler h = BridgeContext.getBridgeUpdateHandler(me.getRelatedNode()); if (h != null) try { h.handleDOMNodeInsertedEvent(me); } catch (InterruptedBridgeException interruptedBridgeException) {  } catch (Exception e) { BridgeContext.this.userAgent.displayError(e); }   } } protected class DOMNodeRemovedEventListener implements EventListener {
/*      */     public void handleEvent(Event evt) { Node node = (Node)evt.getTarget(); BridgeUpdateHandler h = BridgeContext.getBridgeUpdateHandler(node); if (h != null) try { h.handleDOMNodeRemovedEvent((MutationEvent)evt); } catch (Exception e) { BridgeContext.this.userAgent.displayError(e); }   } } protected class DOMCharacterDataModifiedEventListener implements EventListener {
/* 2043 */     public void handleEvent(Event evt) { Node node = (Node)evt.getTarget(); while (node != null && !(node instanceof SVGOMElement)) node = (Node)((AbstractNode)node).getParentNodeEventTarget();  BridgeUpdateHandler h = BridgeContext.getBridgeUpdateHandler(node); if (h != null) try { h.handleDOMCharacterDataModified((MutationEvent)evt); } catch (Exception e) { BridgeContext.this.userAgent.displayError(e); }   } } public void registerSVGBridges() { UserAgent ua = getUserAgent();
/* 2044 */     List ext = getBridgeExtensions(this.document);
/*      */     
/* 2046 */     for (Object anExt : ext)
/* 2047 */     { BridgeExtension be = (BridgeExtension)anExt;
/* 2048 */       be.registerTags(this);
/* 2049 */       ua.registerExtension(be); }  } protected class CSSPropertiesChangedListener implements CSSEngineListener {
/*      */     public void propertiesChanged(CSSEngineEvent evt) { Element elem = evt.getElement(); SVGContext ctx = BridgeContext.getSVGContext(elem); if (ctx == null) { GraphicsNode pgn = BridgeContext.this.getGraphicsNode(elem.getParentNode()); if (pgn == null || !(pgn instanceof CompositeGraphicsNode)) return;  CompositeGraphicsNode parent = (CompositeGraphicsNode)pgn; int[] properties = evt.getProperties(); for (int property : properties) { if (property == 12) { if (!CSSUtilities.convertDisplay(elem)) break;  GVTBuilder builder = BridgeContext.this.getGVTBuilder(); GraphicsNode childNode = builder.build(BridgeContext.this, elem); if (childNode == null) break;  int idx = -1; for (Node ps = elem.getPreviousSibling(); ps != null; ps = ps.getPreviousSibling()) { if (ps.getNodeType() != 1) continue;  Element pse = (Element)ps; GraphicsNode gn = BridgeContext.this.getGraphicsNode(pse); if (gn == null) continue;  idx = parent.indexOf(gn); if (idx == -1) continue;  }  idx++; parent.add(idx, childNode); break; }  }  }  if (ctx != null && ctx instanceof BridgeUpdateHandler) ((BridgeUpdateHandler)ctx).handleCSSEngineEvent(evt);  } } protected class AnimatedAttrListener implements AnimatedAttributeListener {
/*      */     public void animatedAttributeChanged(Element e, AnimatedLiveAttributeValue alav) { BridgeUpdateHandler h = BridgeContext.getBridgeUpdateHandler(e); if (h != null) try { h.handleAnimatedAttributeChanged(alav); } catch (Exception ex) { BridgeContext.this.userAgent.displayError(ex); }   } public void otherAnimationChanged(Element e, String type) { BridgeUpdateHandler h = BridgeContext.getBridgeUpdateHandler(e); if (h != null) try { h.handleOtherAnimationChanged(type); } catch (Exception ex) { BridgeContext.this.userAgent.displayError(ex); }   } } public Value getSystemColor(String ident) { return SystemColorSupport.getSystemColor(ident); } public Value getDefaultFontFamily() { SVGOMDocument doc = (SVGOMDocument)this.document; SVGStylableElement root = (SVGStylableElement)doc.getRootElement(); String str = this.userAgent.getDefaultFontFamily(); return doc.getCSSEngine().parsePropertyValue((CSSStylableElement)root, "font-family", str); } public float getLighterFontWeight(float f) { return this.userAgent.getLighterFontWeight(f); } public float getBolderFontWeight(float f) { return this.userAgent.getBolderFontWeight(f); } public float getPixelUnitToMillimeter() { return this.userAgent.getPixelUnitToMillimeter(); } public float getPixelToMillimeter() { return getPixelUnitToMillimeter(); } public float getMediumFontSize() { return this.userAgent.getMediumFontSize(); } public float getBlockWidth(Element elt) { return getViewport(elt).getWidth(); } public float getBlockHeight(Element elt) { return getViewport(elt).getHeight(); } public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException { this.userAgent.checkLoadExternalResource(resourceURL, docURL); } public boolean isDynamicDocument(Document doc) { return BaseScriptingEnvironment.isDynamicDocument(this, doc); } public boolean isInteractiveDocument(Document doc) { SVGSVGElement sVGSVGElement = ((SVGDocument)doc).getRootElement(); if (!"http://www.w3.org/2000/svg".equals(sVGSVGElement.getNamespaceURI())) return false;  return checkInteractiveElement((Element)sVGSVGElement); } public boolean checkInteractiveElement(Element e) { return checkInteractiveElement((SVGDocument)e.getOwnerDocument(), e); } public boolean checkInteractiveElement(SVGDocument doc, Element e) { String tag = e.getLocalName(); if ("a".equals(tag)) return true;  if ("title".equals(tag)) return (e.getParentNode() != doc.getRootElement());  if ("desc".equals(tag)) return (e.getParentNode() != doc.getRootElement());  if ("cursor".equals(tag)) return true;  if (e.getAttribute("cursor").length() > 0) return true;  String svg_ns = "http://www.w3.org/2000/svg"; Node n = e.getFirstChild(); for (; n != null; n = n.getNextSibling()) { if (n.getNodeType() == 1) { Element child = (Element)n; if ("http://www.w3.org/2000/svg".equals(child.getNamespaceURI()) && checkInteractiveElement(child)) return true;  }  }  return false; } public void setAnimationLimitingNone() { this.animationLimitingMode = 0; if (this.animationEngine != null) setAnimationLimitingMode();  } public void setAnimationLimitingCPU(float pc) { this.animationLimitingMode = 1; this.animationLimitingAmount = pc; if (this.animationEngine != null) setAnimationLimitingMode();  }
/*      */   public void setAnimationLimitingFPS(float fps) { this.animationLimitingMode = 2; this.animationLimitingAmount = fps; if (this.animationEngine != null) setAnimationLimitingMode();  }
/*      */   protected void setAnimationLimitingMode() { switch (this.animationLimitingMode) { case 0: this.animationEngine.setAnimationLimitingNone(); break;case 1: this.animationEngine.setAnimationLimitingCPU(this.animationLimitingAmount); break;case 2: this.animationEngine.setAnimationLimitingFPS(this.animationLimitingAmount); break; }  }
/* 2054 */   public List getBridgeExtensions(Document doc) { SVG12BridgeExtension sVG12BridgeExtension; SVGSVGElement sVGSVGElement = ((SVGOMDocument)doc).getRootElement();
/* 2055 */     String ver = sVGSVGElement.getAttributeNS((String)null, "version");
/*      */ 
/*      */     
/* 2058 */     if (ver.length() == 0 || ver.equals("1.0") || ver.equals("1.1")) {
/* 2059 */       BridgeExtension svgBE = new SVGBridgeExtension();
/*      */     } else {
/* 2061 */       sVG12BridgeExtension = new SVG12BridgeExtension();
/*      */     } 
/* 2063 */     float priority = sVG12BridgeExtension.getPriority();
/* 2064 */     this.extensions = new LinkedList(getGlobalBridgeExtensions());
/*      */     
/* 2066 */     ListIterator<SVG12BridgeExtension> li = this.extensions.listIterator();
/*      */     while (true) {
/* 2068 */       if (!li.hasNext()) {
/* 2069 */         li.add(sVG12BridgeExtension);
/*      */         break;
/*      */       } 
/* 2072 */       BridgeExtension lbe = (BridgeExtension)li.next();
/* 2073 */       if (lbe.getPriority() > priority) {
/* 2074 */         li.previous();
/* 2075 */         li.add(sVG12BridgeExtension);
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 2080 */     return this.extensions; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2086 */   protected static List globalExtensions = null;
/*      */   
/*      */   public static synchronized List getGlobalBridgeExtensions() {
/* 2089 */     if (globalExtensions != null) {
/* 2090 */       return globalExtensions;
/*      */     }
/* 2092 */     globalExtensions = new LinkedList();
/*      */     
/* 2094 */     Iterator<BridgeExtension> iter = Service.providers(BridgeExtension.class);
/*      */     
/* 2096 */     label18: while (iter.hasNext()) {
/* 2097 */       BridgeExtension be = iter.next();
/* 2098 */       float priority = be.getPriority();
/* 2099 */       ListIterator<BridgeExtension> li = globalExtensions.listIterator();
/*      */       while (true) {
/* 2101 */         if (!li.hasNext()) {
/* 2102 */           li.add(be);
/*      */           continue label18;
/*      */         } 
/* 2105 */         BridgeExtension lbe = li.next();
/* 2106 */         if (lbe.getPriority() > priority) {
/* 2107 */           li.previous();
/* 2108 */           li.add(be);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2113 */     return globalExtensions;
/*      */   }
/*      */   
/*      */   public static class CSSEngineUserAgentWrapper implements CSSEngineUserAgent { UserAgent ua;
/*      */     
/*      */     CSSEngineUserAgentWrapper(UserAgent ua) {
/* 2119 */       this.ua = ua;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(Exception ex) {
/* 2125 */       this.ua.displayError(ex);
/*      */     }
/*      */ 
/*      */     
/*      */     public void displayMessage(String message) {
/* 2130 */       this.ua.displayMessage(message);
/*      */     } }
/*      */ 
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/BridgeContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */