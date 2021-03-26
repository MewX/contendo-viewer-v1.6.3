/*      */ package org.apache.batik.swing.svg;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Shape;
/*      */ import java.awt.event.ComponentAdapter;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseWheelEvent;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Dimension2D;
/*      */ import java.awt.geom.NoninvertibleTransformException;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.net.URL;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.swing.JOptionPane;
/*      */ import org.apache.batik.anim.dom.SVGDOMImplementation;
/*      */ import org.apache.batik.anim.dom.SVGOMDocument;
/*      */ import org.apache.batik.bridge.BridgeContext;
/*      */ import org.apache.batik.bridge.BridgeException;
/*      */ import org.apache.batik.bridge.BridgeExtension;
/*      */ import org.apache.batik.bridge.DefaultFontFamilyResolver;
/*      */ import org.apache.batik.bridge.DefaultScriptSecurity;
/*      */ import org.apache.batik.bridge.DocumentLoader;
/*      */ import org.apache.batik.bridge.ExternalResourceSecurity;
/*      */ import org.apache.batik.bridge.FontFamilyResolver;
/*      */ import org.apache.batik.bridge.Mark;
/*      */ import org.apache.batik.bridge.RelaxedExternalResourceSecurity;
/*      */ import org.apache.batik.bridge.ScriptSecurity;
/*      */ import org.apache.batik.bridge.UpdateManager;
/*      */ import org.apache.batik.bridge.UpdateManagerEvent;
/*      */ import org.apache.batik.bridge.UpdateManagerListener;
/*      */ import org.apache.batik.bridge.UserAgent;
/*      */ import org.apache.batik.bridge.ViewBox;
/*      */ import org.apache.batik.bridge.svg12.SVG12BridgeContext;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.dom.util.XLinkSupport;
/*      */ import org.apache.batik.ext.awt.image.spi.ImageTagRegistry;
/*      */ import org.apache.batik.gvt.CanvasGraphicsNode;
/*      */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*      */ import org.apache.batik.gvt.GraphicsNode;
/*      */ import org.apache.batik.gvt.event.AWTEventDispatcher;
/*      */ import org.apache.batik.gvt.event.EventDispatcher;
/*      */ import org.apache.batik.gvt.renderer.ImageRenderer;
/*      */ import org.apache.batik.script.Interpreter;
/*      */ import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
/*      */ import org.apache.batik.swing.gvt.JGVTComponent;
/*      */ import org.apache.batik.swing.gvt.JGVTComponentListener;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.apache.batik.util.RunnableQueue;
/*      */ import org.apache.batik.util.SVGFeatureStrings;
/*      */ import org.apache.batik.util.XMLResourceDescriptor;
/*      */ import org.w3c.dom.DOMImplementation;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.svg.SVGAElement;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JSVGComponent
/*      */   extends JGVTComponent
/*      */ {
/*      */   public static final int AUTODETECT = 0;
/*      */   public static final int ALWAYS_DYNAMIC = 1;
/*      */   public static final int ALWAYS_STATIC = 2;
/*      */   public static final int ALWAYS_INTERACTIVE = 3;
/*      */   public static final String SCRIPT_ALERT = "script.alert";
/*      */   public static final String SCRIPT_PROMPT = "script.prompt";
/*      */   public static final String SCRIPT_CONFIRM = "script.confirm";
/*      */   public static final String BROKEN_LINK_TITLE = "broken.link.title";
/*      */   protected SVGDocumentLoader documentLoader;
/*      */   protected SVGDocumentLoader nextDocumentLoader;
/*      */   protected DocumentLoader loader;
/*      */   protected GVTTreeBuilder gvtTreeBuilder;
/*      */   protected GVTTreeBuilder nextGVTTreeBuilder;
/*      */   protected SVGLoadEventDispatcher svgLoadEventDispatcher;
/*      */   protected UpdateManager updateManager;
/*      */   protected UpdateManager nextUpdateManager;
/*      */   protected SVGDocument svgDocument;
/*  312 */   protected List svgDocumentLoaderListeners = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  317 */   protected List gvtTreeBuilderListeners = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  322 */   protected List svgLoadEventDispatcherListeners = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  327 */   protected List linkActivationListeners = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  332 */   protected List updateManagerListeners = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected UserAgent userAgent;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SVGUserAgent svgUserAgent;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BridgeContext bridgeContext;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String fragmentIdentifier;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isDynamicDocument;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isInteractiveDocument;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean selfCallingDisableInteractions = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean userSetDisableInteractions = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int documentState;
/*      */ 
/*      */ 
/*      */   
/*      */   protected Dimension prevComponentSize;
/*      */ 
/*      */ 
/*      */   
/*  382 */   protected Runnable afterStopRunnable = null;
/*      */   
/*      */   protected SVGUpdateOverlay updateOverlay;
/*      */   
/*      */   protected boolean recenterOnResize = true;
/*      */   
/*  388 */   protected AffineTransform viewingTransform = null;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int animationLimitingMode;
/*      */ 
/*      */ 
/*      */   
/*      */   protected float animationLimitingAmount;
/*      */ 
/*      */   
/*      */   protected JSVGComponentListener jsvgComponentListener;
/*      */ 
/*      */ 
/*      */   
/*      */   public JSVGComponent() {
/*  404 */     this((SVGUserAgent)null, false, false);
/*      */   }
/*      */   public void dispose() { setSVGDocument((SVGDocument)null); }
/*      */   public void setDisableInteractions(boolean b) { super.setDisableInteractions(b); if (!this.selfCallingDisableInteractions) this.userSetDisableInteractions = true;  }
/*      */   public void clearUserSetDisableInteractions() { this.userSetDisableInteractions = false; updateZoomAndPanEnable((Document)this.svgDocument); }
/*      */   public void updateZoomAndPanEnable(Document doc) { if (this.userSetDisableInteractions) return;  if (doc == null) return;  try { Element root = doc.getDocumentElement(); String znp = root.getAttributeNS((String)null, "zoomAndPan"); boolean enable = "magnify".equals(znp); this.selfCallingDisableInteractions = true; setDisableInteractions(!enable); } finally { this.selfCallingDisableInteractions = false; }  } public boolean getRecenterOnResize() { return this.recenterOnResize; } public void setRecenterOnResize(boolean recenterOnResize) { this.recenterOnResize = recenterOnResize; } public boolean isDynamic() { return this.isDynamicDocument; } public boolean isInteractive() { return this.isInteractiveDocument; } public void setDocumentState(int state) { this.documentState = state; } public UpdateManager getUpdateManager() { if (this.svgLoadEventDispatcher != null) return this.svgLoadEventDispatcher.getUpdateManager();  if (this.nextUpdateManager != null) return this.nextUpdateManager;  return this.updateManager; } public void resumeProcessing() { if (this.updateManager != null) this.updateManager.resume();  } public void suspendProcessing() { if (this.updateManager != null) this.updateManager.suspend();  } public void stopProcessing() { this.nextDocumentLoader = null; this.nextGVTTreeBuilder = null; if (this.documentLoader != null) this.documentLoader.halt();  if (this.gvtTreeBuilder != null) this.gvtTreeBuilder.halt();  if (this.svgLoadEventDispatcher != null) this.svgLoadEventDispatcher.halt();  if (this.nextUpdateManager != null) { this.nextUpdateManager.interrupt(); this.nextUpdateManager = null; }  if (this.updateManager != null) this.updateManager.interrupt();  super.stopProcessing(); } public void loadSVGDocument(String url) { String oldURI = null; if (this.svgDocument != null) oldURI = this.svgDocument.getURL();  final ParsedURL newURI = new ParsedURL(oldURI, url); stopThenRun(new Runnable() { public void run() { String url = newURI.toString(); JSVGComponent.this.fragmentIdentifier = newURI.getRef(); JSVGComponent.this.loader = new DocumentLoader(JSVGComponent.this.userAgent); JSVGComponent.this.nextDocumentLoader = new SVGDocumentLoader(url, JSVGComponent.this.loader); JSVGComponent.this.nextDocumentLoader.setPriority(1); for (Object svgDocumentLoaderListener : JSVGComponent.this.svgDocumentLoaderListeners) JSVGComponent.this.nextDocumentLoader.addSVGDocumentLoaderListener((SVGDocumentLoaderListener)svgDocumentLoaderListener);  JSVGComponent.this.startDocumentLoader(); } }
/*      */       ); } private void startDocumentLoader() { this.documentLoader = this.nextDocumentLoader; this.nextDocumentLoader = null; this.documentLoader.start(); } public void setDocument(Document doc) { if (doc != null && !(doc.getImplementation() instanceof SVGDOMImplementation)) { DOMImplementation impl = SVGDOMImplementation.getDOMImplementation(); Document d = DOMUtilities.deepCloneDocument(doc, impl); doc = d; }  setSVGDocument((SVGDocument)doc); } public void setSVGDocument(SVGDocument doc) { if (doc != null && !(doc.getImplementation() instanceof SVGDOMImplementation)) { DOMImplementation impl = SVGDOMImplementation.getDOMImplementation(); Document d = DOMUtilities.deepCloneDocument((Document)doc, impl); doc = (SVGDocument)d; }  final SVGDocument svgdoc = doc; stopThenRun(new Runnable() {
/*      */           public void run() { JSVGComponent.this.installSVGDocument(svgdoc); }
/*      */         }); } protected void stopThenRun(Runnable r) { if (this.afterStopRunnable != null) { this.afterStopRunnable = r; return; }  this.afterStopRunnable = r; stopProcessing(); if (this.documentLoader == null && this.gvtTreeBuilder == null && this.gvtTreeRenderer == null && this.svgLoadEventDispatcher == null && this.nextUpdateManager == null && this.updateManager == null) { Runnable asr = this.afterStopRunnable; this.afterStopRunnable = null; asr.run(); }  } protected void installSVGDocument(SVGDocument doc) { this.svgDocument = doc; if (this.bridgeContext != null) { this.bridgeContext.dispose(); this.bridgeContext = null; }  releaseRenderingReferences(); if (doc == null) { this.isDynamicDocument = false; this.isInteractiveDocument = false; this.disableInteractions = true; this.initialTransform = new AffineTransform(); setRenderingTransform(this.initialTransform, false); Rectangle vRect = getRenderRect(); repaint(vRect.x, vRect.y, vRect.width, vRect.height); return; }  this.bridgeContext = createBridgeContext((SVGOMDocument)doc); switch (this.documentState) { case 2: this.isDynamicDocument = false; this.isInteractiveDocument = false; break;case 3: this.isDynamicDocument = false; this.isInteractiveDocument = true; break;case 1: this.isDynamicDocument = true; this.isInteractiveDocument = true; break;case 0: this.isDynamicDocument = this.bridgeContext.isDynamicDocument((Document)doc); this.isInteractiveDocument = (this.isDynamicDocument || this.bridgeContext.isInteractiveDocument((Document)doc)); break; }  if (this.isInteractiveDocument) if (this.isDynamicDocument) { this.bridgeContext.setDynamicState(2); } else { this.bridgeContext.setDynamicState(1); }   setBridgeContextAnimationLimitingMode(); updateZoomAndPanEnable((Document)doc); this.nextGVTTreeBuilder = new GVTTreeBuilder(doc, this.bridgeContext); this.nextGVTTreeBuilder.setPriority(1); for (Object gvtTreeBuilderListener : this.gvtTreeBuilderListeners) this.nextGVTTreeBuilder.addGVTTreeBuilderListener((GVTTreeBuilderListener)gvtTreeBuilderListener);  initializeEventHandling(); if (this.gvtTreeBuilder == null && this.documentLoader == null && this.gvtTreeRenderer == null && this.svgLoadEventDispatcher == null && this.updateManager == null) startGVTTreeBuilder();  } protected void startGVTTreeBuilder() { this.gvtTreeBuilder = this.nextGVTTreeBuilder; this.nextGVTTreeBuilder = null; this.gvtTreeBuilder.start(); } public SVGDocument getSVGDocument() { return this.svgDocument; } public Dimension2D getSVGDocumentSize() { return this.bridgeContext.getDocumentSize(); } public String getFragmentIdentifier() { return this.fragmentIdentifier; } public void setFragmentIdentifier(String fi) { this.fragmentIdentifier = fi; if (computeRenderingTransform()) scheduleGVTRendering();  } public void flushImageCache() { ImageTagRegistry reg = ImageTagRegistry.getRegistry(); reg.flushCache(); } public void setGraphicsNode(GraphicsNode gn, boolean createDispatcher) { Dimension2D dim = this.bridgeContext.getDocumentSize(); Dimension mySz = new Dimension((int)dim.getWidth(), (int)dim.getHeight()); setMySize(mySz); SVGSVGElement elt = this.svgDocument.getRootElement(); this.prevComponentSize = getSize(); AffineTransform at = calculateViewingTransform(this.fragmentIdentifier, elt); CanvasGraphicsNode cgn = getCanvasGraphicsNode(gn); if (cgn != null) cgn.setViewingTransform(at);  this.viewingTransform = null; this.initialTransform = new AffineTransform(); setRenderingTransform(this.initialTransform, false); this.jsvgComponentListener.updateMatrix(this.initialTransform); addJGVTComponentListener(this.jsvgComponentListener); addComponentListener(this.jsvgComponentListener); super.setGraphicsNode(gn, createDispatcher); } protected BridgeContext createBridgeContext(SVGOMDocument doc) { BridgeContext result; if (this.loader == null) this.loader = new DocumentLoader(this.userAgent);  if (doc.isSVG12()) { SVG12BridgeContext sVG12BridgeContext = new SVG12BridgeContext(this.userAgent, this.loader); } else { result = new BridgeContext(this.userAgent, this.loader); }  return result; } protected void startSVGLoadEventDispatcher(GraphicsNode root) { UpdateManager um = new UpdateManager(this.bridgeContext, root, (Document)this.svgDocument); this.svgLoadEventDispatcher = new SVGLoadEventDispatcher(root, this.svgDocument, this.bridgeContext, um); for (Object svgLoadEventDispatcherListener : this.svgLoadEventDispatcherListeners) this.svgLoadEventDispatcher.addSVGLoadEventDispatcherListener((SVGLoadEventDispatcherListener)svgLoadEventDispatcherListener);  this.svgLoadEventDispatcher.start(); } protected ImageRenderer createImageRenderer() { if (this.isDynamicDocument) return this.rendererFactory.createDynamicImageRenderer();  return this.rendererFactory.createStaticImageRenderer(); } public CanvasGraphicsNode getCanvasGraphicsNode() { return getCanvasGraphicsNode(this.gvtRoot); } protected CanvasGraphicsNode getCanvasGraphicsNode(GraphicsNode gn) { if (!(gn instanceof CompositeGraphicsNode)) return null;  CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn; List<GraphicsNode> children = cgn.getChildren(); if (children.size() == 0) return null;  gn = children.get(0); if (!(gn instanceof CanvasGraphicsNode)) return null;  return (CanvasGraphicsNode)gn; } public AffineTransform getViewingTransform() { AffineTransform vt; synchronized (this) { vt = this.viewingTransform; if (vt == null) { CanvasGraphicsNode cgn = getCanvasGraphicsNode(); if (cgn != null) vt = cgn.getViewingTransform();  }  }  return vt; } public AffineTransform getViewBoxTransform() { AffineTransform at = getRenderingTransform(); if (at == null) { at = new AffineTransform(); } else { at = new AffineTransform(at); }  AffineTransform vt = getViewingTransform(); if (vt != null) at.concatenate(vt);  return at; } protected boolean computeRenderingTransform() { if (this.svgDocument == null || this.gvtRoot == null) return false;  boolean ret = updateRenderingTransform(); this.initialTransform = new AffineTransform(); if (!this.initialTransform.equals(getRenderingTransform())) { setRenderingTransform(this.initialTransform, false); ret = true; }  return ret; } protected AffineTransform calculateViewingTransform(String fragIdent, SVGSVGElement svgElt) { Dimension d = getSize(); if (d.width < 1) d.width = 1;  if (d.height < 1) d.height = 1;  return ViewBox.getViewTransform(fragIdent, (Element)svgElt, d.width, d.height, this.bridgeContext); } protected boolean updateRenderingTransform() { if (this.svgDocument == null || this.gvtRoot == null) return false;  try { SVGSVGElement elt = this.svgDocument.getRootElement(); Dimension d = getSize(); Dimension oldD = this.prevComponentSize; if (oldD == null) oldD = d;  this.prevComponentSize = d; if (d.width < 1) d.width = 1;  if (d.height < 1) d.height = 1;  final AffineTransform at = calculateViewingTransform(this.fragmentIdentifier, elt); AffineTransform vt = getViewingTransform(); if (at.equals(vt)) return (oldD.width != d.width || oldD.height != d.height);  if (this.recenterOnResize) { Point2D pt = new Point2D.Float(oldD.width / 2.0F, oldD.height / 2.0F); AffineTransform rendAT = getRenderingTransform(); if (rendAT != null) try { AffineTransform invRendAT = rendAT.createInverse(); pt = invRendAT.transform(pt, null); } catch (NoninvertibleTransformException noninvertibleTransformException) {}  if (vt != null) try { AffineTransform invVT = vt.createInverse(); pt = invVT.transform(pt, null); } catch (NoninvertibleTransformException noninvertibleTransformException) {}  if (at != null) pt = at.transform(pt, null);  if (rendAT != null) pt = rendAT.transform(pt, null);  float dx = (float)((d.width / 2.0F) - pt.getX()); float dy = (float)((d.height / 2.0F) - pt.getY()); dx = (int)((dx < 0.0F) ? (dx - 0.5D) : (dx + 0.5D)); dy = (int)((dy < 0.0F) ? (dy - 0.5D) : (dy + 0.5D)); if (dx != 0.0F || dy != 0.0F) { rendAT.preConcatenate(AffineTransform.getTranslateInstance(dx, dy)); setRenderingTransform(rendAT, false); }  }  synchronized (this) { this.viewingTransform = at; }  Runnable r = new Runnable() {
/*      */           AffineTransform myAT = at; CanvasGraphicsNode myCGN = JSVGComponent.this.getCanvasGraphicsNode(); public void run() { synchronized (JSVGComponent.this) { if (this.myCGN != null) this.myCGN.setViewingTransform(this.myAT);  if (JSVGComponent.this.viewingTransform == this.myAT) JSVGComponent.this.viewingTransform = null;  }  }
/*      */         }; UpdateManager um = getUpdateManager(); if (um != null) { um.getUpdateRunnableQueue().invokeLater(r); } else { r.run(); }  } catch (BridgeException e) { this.userAgent.displayError((Exception)e); }  return true; } protected void renderGVTTree() { Shape s; if (!this.isInteractiveDocument || this.updateManager == null || !this.updateManager.isRunning()) { super.renderGVTTree(); return; }  Rectangle visRect = getRenderRect(); if (this.gvtRoot == null || visRect.width <= 0 || visRect.height <= 0) return;  AffineTransform inv = null; try { inv = this.renderingTransform.createInverse(); } catch (NoninvertibleTransformException noninvertibleTransformException) {} if (inv == null) { s = visRect; } else { s = inv.createTransformedShape(visRect); }  class UpdateRenderingRunnable implements Runnable {
/*      */       AffineTransform at; boolean doubleBuf; boolean clearPaintTrans; Shape aoi; int width; int height; boolean active; public UpdateRenderingRunnable(AffineTransform at, boolean doubleBuf, boolean clearPaintTrans, Shape aoi, int width, int height) { updateInfo(at, doubleBuf, clearPaintTrans, aoi, width, height); this.active = true; } public void updateInfo(AffineTransform at, boolean doubleBuf, boolean clearPaintTrans, Shape aoi, int width, int height) { this.at = at; this.doubleBuf = doubleBuf; this.clearPaintTrans = clearPaintTrans; this.aoi = aoi; this.width = width; this.height = height; this.active = true; } public void deactivate() { this.active = false; } public void run() { if (!this.active) return;  JSVGComponent.this.updateManager.updateRendering(this.at, this.doubleBuf, this.clearPaintTrans, this.aoi, this.width, this.height); }
/*  416 */     }; RunnableQueue rq = this.updateManager.getUpdateRunnableQueue(); synchronized (rq.getIteratorLock()) { Iterator it = rq.iterator(); while (it.hasNext()) { Object next = it.next(); if (next instanceof UpdateRenderingRunnable) ((UpdateRenderingRunnable)next).deactivate();  }  }  rq.invokeLater(new UpdateRenderingRunnable(this.renderingTransform, this.doubleBufferedRendering, true, s, visRect.width, visRect.height)); } protected void handleException(Exception e) { this.userAgent.displayError(e); } public void addSVGDocumentLoaderListener(SVGDocumentLoaderListener l) { this.svgDocumentLoaderListeners.add(l); } public void removeSVGDocumentLoaderListener(SVGDocumentLoaderListener l) { this.svgDocumentLoaderListeners.remove(l); } public void addGVTTreeBuilderListener(GVTTreeBuilderListener l) { this.gvtTreeBuilderListeners.add(l); } public void removeGVTTreeBuilderListener(GVTTreeBuilderListener l) { this.gvtTreeBuilderListeners.remove(l); } public void addSVGLoadEventDispatcherListener(SVGLoadEventDispatcherListener l) { this.svgLoadEventDispatcherListeners.add(l); } public void removeSVGLoadEventDispatcherListener(SVGLoadEventDispatcherListener l) { this.svgLoadEventDispatcherListeners.remove(l); } public JSVGComponent(SVGUserAgent ua, boolean eventsEnabled, boolean selectableText) { super(eventsEnabled, selectableText);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1358 */     this.jsvgComponentListener = new JSVGComponentListener(); this.svgUserAgent = ua; this.userAgent = new BridgeUserAgentWrapper(createUserAgent()); addSVGDocumentLoaderListener((SVGListener)this.listener); addGVTTreeBuilderListener((SVGListener)this.listener); addSVGLoadEventDispatcherListener((SVGListener)this.listener); if (this.updateOverlay != null) getOverlays().add(this.updateOverlay);  }
/*      */   public void addLinkActivationListener(LinkActivationListener l) { this.linkActivationListeners.add(l); }
/*      */   public void removeLinkActivationListener(LinkActivationListener l) { this.linkActivationListeners.remove(l); }
/*      */   public void addUpdateManagerListener(UpdateManagerListener l) { this.updateManagerListeners.add(l); } public void removeUpdateManagerListener(UpdateManagerListener l) { this.updateManagerListeners.remove(l); } public void showAlert(String message) { JOptionPane.showMessageDialog((Component)this, Messages.formatMessage("script.alert", new Object[] { message })); } public String showPrompt(String message) { return JOptionPane.showInputDialog((Component)this, Messages.formatMessage("script.prompt", new Object[] { message })); } public String showPrompt(String message, String defaultValue) { return (String)JOptionPane.showInputDialog((Component)this, Messages.formatMessage("script.prompt", new Object[] { message }), null, -1, null, null, defaultValue); } public boolean showConfirm(String message) { return (JOptionPane.showConfirmDialog((Component)this, Messages.formatMessage("script.confirm", new Object[] { message }), "Confirm", 0) == 0); } public void setMySize(Dimension d) { setPreferredSize(d); invalidate(); } public void setAnimationLimitingNone() { this.animationLimitingMode = 0; if (this.bridgeContext != null) setBridgeContextAnimationLimitingMode();  } public void setAnimationLimitingCPU(float pc) { this.animationLimitingMode = 1; this.animationLimitingAmount = pc; if (this.bridgeContext != null) setBridgeContextAnimationLimitingMode();  } public void setAnimationLimitingFPS(float fps) { this.animationLimitingMode = 2; this.animationLimitingAmount = fps; if (this.bridgeContext != null) setBridgeContextAnimationLimitingMode();  } public Interpreter getInterpreter(String type) { if (this.bridgeContext != null) return this.bridgeContext.getInterpreter(type);  return null; } protected void setBridgeContextAnimationLimitingMode() { switch (this.animationLimitingMode) { case 0: this.bridgeContext.setAnimationLimitingNone(); break;case 1: this.bridgeContext.setAnimationLimitingCPU(this.animationLimitingAmount); break;case 2: this.bridgeContext.setAnimationLimitingFPS(this.animationLimitingAmount); break; }  } protected class JSVGComponentListener extends ComponentAdapter implements JGVTComponentListener
/*      */   {
/* 1363 */     float prevScale = 0.0F;
/* 1364 */     float prevTransX = 0.0F;
/* 1365 */     float prevTransY = 0.0F;
/*      */     
/*      */     public void componentResized(ComponentEvent ce) {
/* 1368 */       if (JSVGComponent.this.isDynamicDocument && JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning())
/*      */       {
/* 1370 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/*      */                 try {
/* 1374 */                   JSVGComponent.this.updateManager.dispatchSVGResizeEvent();
/* 1375 */                 } catch (InterruptedException interruptedException) {}
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */     
/*      */     public void componentTransformChanged(ComponentEvent event) {
/* 1382 */       AffineTransform at = JSVGComponent.this.getRenderingTransform();
/*      */       
/* 1384 */       float currScale = (float)Math.sqrt(at.getDeterminant());
/* 1385 */       float currTransX = (float)at.getTranslateX();
/* 1386 */       float currTransY = (float)at.getTranslateY();
/*      */       
/* 1388 */       final boolean dispatchZoom = (currScale != this.prevScale);
/* 1389 */       final boolean dispatchScroll = (currTransX != this.prevTransX || currTransY != this.prevTransY);
/*      */       
/* 1391 */       if (JSVGComponent.this.isDynamicDocument && JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning())
/*      */       {
/* 1393 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/*      */                 try {
/* 1397 */                   if (dispatchZoom)
/* 1398 */                     JSVGComponent.this.updateManager.dispatchSVGZoomEvent(); 
/* 1399 */                   if (dispatchScroll)
/* 1400 */                     JSVGComponent.this.updateManager.dispatchSVGScrollEvent(); 
/* 1401 */                 } catch (InterruptedException interruptedException) {}
/*      */               }
/*      */             });
/*      */       }
/* 1405 */       this.prevScale = currScale;
/* 1406 */       this.prevTransX = currTransX;
/* 1407 */       this.prevTransY = currTransY;
/*      */     }
/*      */     
/*      */     public void updateMatrix(AffineTransform at) {
/* 1411 */       this.prevScale = (float)Math.sqrt(at.getDeterminant());
/* 1412 */       this.prevTransX = (float)at.getTranslateX();
/* 1413 */       this.prevTransY = (float)at.getTranslateY();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JGVTComponent.Listener createListener() {
/* 1422 */     return new SVGListener();
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
/*      */   protected class SVGListener
/*      */     extends JGVTComponent.Listener
/*      */     implements UpdateManagerListener, GVTTreeBuilderListener, SVGDocumentLoaderListener, SVGLoadEventDispatcherListener
/*      */   {
/*      */     protected SVGListener() {
/* 1438 */       super(JSVGComponent.this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void documentLoadingStarted(SVGDocumentLoaderEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
/* 1453 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1454 */         JSVGComponent.this.startDocumentLoader();
/*      */         
/*      */         return;
/*      */       } 
/* 1458 */       JSVGComponent.this.documentLoader = null;
/* 1459 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1460 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1461 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1465 */       JSVGComponent.this.setSVGDocument(e.getSVGDocument());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void documentLoadingCancelled(SVGDocumentLoaderEvent e) {
/* 1472 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1473 */         JSVGComponent.this.startDocumentLoader();
/*      */         
/*      */         return;
/*      */       } 
/* 1477 */       JSVGComponent.this.documentLoader = null;
/* 1478 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1479 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1480 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1484 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1485 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         return;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void documentLoadingFailed(SVGDocumentLoaderEvent e) {
/* 1494 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1495 */         JSVGComponent.this.startDocumentLoader();
/*      */         
/*      */         return;
/*      */       } 
/* 1499 */       JSVGComponent.this.documentLoader = null;
/* 1500 */       JSVGComponent.this.userAgent.displayError(((SVGDocumentLoader)e.getSource()).getException());
/*      */ 
/*      */       
/* 1503 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1504 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1505 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         return;
/*      */       } 
/* 1508 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1509 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         return;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtBuildStarted(GVTTreeBuilderEvent e) {
/* 1521 */       JSVGComponent.this.removeJGVTComponentListener(JSVGComponent.this.jsvgComponentListener);
/* 1522 */       JSVGComponent.this.removeComponentListener(JSVGComponent.this.jsvgComponentListener);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtBuildCompleted(GVTTreeBuilderEvent e) {
/* 1529 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1530 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         
/*      */         return;
/*      */       } 
/* 1534 */       JSVGComponent.this.loader = null;
/* 1535 */       JSVGComponent.this.gvtTreeBuilder = null;
/*      */       
/* 1537 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1538 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1539 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1543 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1544 */         JSVGComponent.this.startDocumentLoader();
/*      */         
/*      */         return;
/*      */       } 
/* 1548 */       JSVGComponent.this.gvtRoot = null;
/*      */       
/* 1550 */       if (JSVGComponent.this.isDynamicDocument && JSVGComponent.this.eventsEnabled) {
/* 1551 */         JSVGComponent.this.startSVGLoadEventDispatcher(e.getGVTRoot());
/*      */       } else {
/* 1553 */         if (JSVGComponent.this.isInteractiveDocument) {
/* 1554 */           JSVGComponent.this.nextUpdateManager = new UpdateManager(JSVGComponent.this.bridgeContext, e.getGVTRoot(), (Document)JSVGComponent.this.svgDocument);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1559 */         JSVGComponent.this.setGraphicsNode(e.getGVTRoot(), false);
/* 1560 */         JSVGComponent.this.scheduleGVTRendering();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtBuildCancelled(GVTTreeBuilderEvent e) {
/* 1568 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1569 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         
/*      */         return;
/*      */       } 
/* 1573 */       JSVGComponent.this.loader = null;
/* 1574 */       JSVGComponent.this.gvtTreeBuilder = null;
/*      */       
/* 1576 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1577 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1578 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1582 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1583 */         JSVGComponent.this.startDocumentLoader();
/*      */         return;
/*      */       } 
/* 1586 */       JSVGComponent.this.image = null;
/* 1587 */       JSVGComponent.this.repaint();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtBuildFailed(GVTTreeBuilderEvent e) {
/* 1594 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1595 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         
/*      */         return;
/*      */       } 
/* 1599 */       JSVGComponent.this.loader = null;
/* 1600 */       JSVGComponent.this.gvtTreeBuilder = null;
/*      */       
/* 1602 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1603 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1604 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1608 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1609 */         JSVGComponent.this.startDocumentLoader();
/*      */         
/*      */         return;
/*      */       } 
/* 1613 */       GraphicsNode gn = e.getGVTRoot();
/* 1614 */       if (gn == null) {
/* 1615 */         JSVGComponent.this.image = null;
/* 1616 */         JSVGComponent.this.repaint();
/*      */       } else {
/* 1618 */         JSVGComponent.this.setGraphicsNode(gn, false);
/* 1619 */         JSVGComponent.this.computeRenderingTransform();
/*      */       } 
/* 1621 */       JSVGComponent.this.userAgent.displayError(((GVTTreeBuilder)e.getSource()).getException());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void svgLoadEventDispatchCompleted(SVGLoadEventDispatcherEvent e) {
/* 1639 */       JSVGComponent.this.nextUpdateManager = JSVGComponent.this.svgLoadEventDispatcher.getUpdateManager();
/* 1640 */       JSVGComponent.this.svgLoadEventDispatcher = null;
/*      */       
/* 1642 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1643 */         JSVGComponent.this.nextUpdateManager.interrupt();
/* 1644 */         JSVGComponent.this.nextUpdateManager = null;
/*      */         
/* 1646 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1647 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1651 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1652 */         JSVGComponent.this.nextUpdateManager.interrupt();
/* 1653 */         JSVGComponent.this.nextUpdateManager = null;
/*      */         
/* 1655 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         return;
/*      */       } 
/* 1658 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1659 */         JSVGComponent.this.nextUpdateManager.interrupt();
/* 1660 */         JSVGComponent.this.nextUpdateManager = null;
/*      */         
/* 1662 */         JSVGComponent.this.startDocumentLoader();
/*      */         
/*      */         return;
/*      */       } 
/* 1666 */       JSVGComponent.this.setGraphicsNode(e.getGVTRoot(), false);
/* 1667 */       JSVGComponent.this.scheduleGVTRendering();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void svgLoadEventDispatchCancelled(SVGLoadEventDispatcherEvent e) {
/* 1675 */       JSVGComponent.this.nextUpdateManager = JSVGComponent.this.svgLoadEventDispatcher.getUpdateManager();
/* 1676 */       JSVGComponent.this.svgLoadEventDispatcher = null;
/*      */       
/* 1678 */       JSVGComponent.this.nextUpdateManager.interrupt();
/* 1679 */       JSVGComponent.this.nextUpdateManager = null;
/*      */       
/* 1681 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1682 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1683 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1687 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1688 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         return;
/*      */       } 
/* 1691 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1692 */         JSVGComponent.this.startDocumentLoader();
/*      */         return;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void svgLoadEventDispatchFailed(SVGLoadEventDispatcherEvent e) {
/* 1702 */       JSVGComponent.this.nextUpdateManager = JSVGComponent.this.svgLoadEventDispatcher.getUpdateManager();
/* 1703 */       JSVGComponent.this.svgLoadEventDispatcher = null;
/*      */       
/* 1705 */       JSVGComponent.this.nextUpdateManager.interrupt();
/* 1706 */       JSVGComponent.this.nextUpdateManager = null;
/*      */       
/* 1708 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1709 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1710 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1714 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1715 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         return;
/*      */       } 
/* 1718 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1719 */         JSVGComponent.this.startDocumentLoader();
/*      */         
/*      */         return;
/*      */       } 
/* 1723 */       GraphicsNode gn = e.getGVTRoot();
/* 1724 */       if (gn == null) {
/* 1725 */         JSVGComponent.this.image = null;
/* 1726 */         JSVGComponent.this.repaint();
/*      */       } else {
/* 1728 */         JSVGComponent.this.setGraphicsNode(gn, false);
/* 1729 */         JSVGComponent.this.computeRenderingTransform();
/*      */       } 
/* 1731 */       JSVGComponent.this.userAgent.displayError(((SVGLoadEventDispatcher)e.getSource()).getException());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
/* 1741 */       super.gvtRenderingCompleted(e);
/*      */       
/* 1743 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1744 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1745 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1746 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/* 1748 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1749 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1753 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1754 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1755 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1756 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/* 1758 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         return;
/*      */       } 
/* 1761 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1762 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1763 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1764 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/* 1766 */         JSVGComponent.this.startDocumentLoader();
/*      */         
/*      */         return;
/*      */       } 
/* 1770 */       if (JSVGComponent.this.nextUpdateManager != null) {
/* 1771 */         JSVGComponent.this.updateManager = JSVGComponent.this.nextUpdateManager;
/* 1772 */         JSVGComponent.this.nextUpdateManager = null;
/* 1773 */         JSVGComponent.this.updateManager.addUpdateManagerListener(this);
/* 1774 */         JSVGComponent.this.updateManager.manageUpdates(JSVGComponent.this.renderer);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtRenderingCancelled(GVTTreeRendererEvent e) {
/* 1782 */       super.gvtRenderingCancelled(e);
/*      */       
/* 1784 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1785 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1786 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1787 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/*      */         
/* 1790 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1791 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1795 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1796 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1797 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1798 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/*      */         
/* 1801 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         return;
/*      */       } 
/* 1804 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1805 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1806 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1807 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/* 1809 */         JSVGComponent.this.startDocumentLoader();
/*      */         return;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtRenderingFailed(GVTTreeRendererEvent e) {
/* 1818 */       super.gvtRenderingFailed(e);
/*      */       
/* 1820 */       if (JSVGComponent.this.afterStopRunnable != null) {
/* 1821 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1822 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1823 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/*      */         
/* 1826 */         EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1827 */         JSVGComponent.this.afterStopRunnable = null;
/*      */         
/*      */         return;
/*      */       } 
/* 1831 */       if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1832 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1833 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1834 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/*      */         
/* 1837 */         JSVGComponent.this.startGVTTreeBuilder();
/*      */         return;
/*      */       } 
/* 1840 */       if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1841 */         if (JSVGComponent.this.nextUpdateManager != null) {
/* 1842 */           JSVGComponent.this.nextUpdateManager.interrupt();
/* 1843 */           JSVGComponent.this.nextUpdateManager = null;
/*      */         } 
/*      */         
/* 1846 */         JSVGComponent.this.startDocumentLoader();
/*      */         return;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void managerStarted(final UpdateManagerEvent e) {
/* 1857 */       EventQueue.invokeLater(new Runnable() {
/*      */             public void run() {
/* 1859 */               JSVGComponent.this.suspendInteractions = false;
/*      */               
/* 1861 */               Object[] dll = JSVGComponent.this.updateManagerListeners.toArray();
/*      */               
/* 1863 */               if (dll.length > 0) {
/* 1864 */                 for (Object aDll : dll) {
/* 1865 */                   ((UpdateManagerListener)aDll).managerStarted(e);
/*      */                 }
/*      */               }
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void managerSuspended(final UpdateManagerEvent e) {
/* 1877 */       EventQueue.invokeLater(new Runnable() {
/*      */             public void run() {
/* 1879 */               Object[] dll = JSVGComponent.this.updateManagerListeners.toArray();
/*      */               
/* 1881 */               if (dll.length > 0) {
/* 1882 */                 for (Object aDll : dll) {
/* 1883 */                   ((UpdateManagerListener)aDll).managerSuspended(e);
/*      */                 }
/*      */               }
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void managerResumed(final UpdateManagerEvent e) {
/* 1895 */       EventQueue.invokeLater(new Runnable() {
/*      */             public void run() {
/* 1897 */               Object[] dll = JSVGComponent.this.updateManagerListeners.toArray();
/*      */               
/* 1899 */               if (dll.length > 0) {
/* 1900 */                 for (Object aDll : dll) {
/* 1901 */                   ((UpdateManagerListener)aDll).managerResumed(e);
/*      */                 }
/*      */               }
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void managerStopped(final UpdateManagerEvent e) {
/* 1913 */       EventQueue.invokeLater(new Runnable() {
/*      */             public void run() {
/* 1915 */               JSVGComponent.this.updateManager = null;
/*      */ 
/*      */               
/* 1918 */               Object[] dll = JSVGComponent.this.updateManagerListeners.toArray();
/*      */               
/* 1920 */               if (dll.length > 0) {
/* 1921 */                 for (Object aDll : dll) {
/* 1922 */                   ((UpdateManagerListener)aDll).managerStopped(e);
/*      */                 }
/*      */               }
/*      */ 
/*      */               
/* 1927 */               if (JSVGComponent.this.afterStopRunnable != null) {
/* 1928 */                 EventQueue.invokeLater(JSVGComponent.this.afterStopRunnable);
/* 1929 */                 JSVGComponent.this.afterStopRunnable = null;
/*      */                 
/*      */                 return;
/*      */               } 
/* 1933 */               if (JSVGComponent.this.nextGVTTreeBuilder != null) {
/* 1934 */                 JSVGComponent.this.startGVTTreeBuilder();
/*      */                 return;
/*      */               } 
/* 1937 */               if (JSVGComponent.this.nextDocumentLoader != null) {
/* 1938 */                 JSVGComponent.this.startDocumentLoader();
/*      */                 return;
/*      */               } 
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void updateStarted(final UpdateManagerEvent e) {
/* 1949 */       EventQueue.invokeLater(new Runnable() {
/*      */             public void run() {
/* 1951 */               if (!JSVGComponent.this.doubleBufferedRendering) {
/* 1952 */                 JSVGComponent.this.image = e.getImage();
/*      */               }
/*      */               
/* 1955 */               Object[] dll = JSVGComponent.this.updateManagerListeners.toArray();
/*      */               
/* 1957 */               if (dll.length > 0) {
/* 1958 */                 for (Object aDll : dll) {
/* 1959 */                   ((UpdateManagerListener)aDll).updateStarted(e);
/*      */                 }
/*      */               }
/*      */             }
/*      */           });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void updateCompleted(final UpdateManagerEvent e) {
/*      */       try {
/* 1983 */         EventQueue.invokeAndWait(new Runnable() {
/*      */               public void run() {
/* 1985 */                 JSVGComponent.this.image = e.getImage();
/* 1986 */                 if (e.getClearPaintingTransform()) {
/* 1987 */                   JSVGComponent.this.paintingTransform = null;
/*      */                 }
/* 1989 */                 List l = e.getDirtyAreas();
/* 1990 */                 if (l != null) {
/* 1991 */                   for (Object aL : l) {
/* 1992 */                     Rectangle r = (Rectangle)aL;
/* 1993 */                     if (JSVGComponent.this.updateOverlay != null) {
/* 1994 */                       JSVGComponent.this.updateOverlay.addRect(r);
/* 1995 */                       r = JSVGComponent.this.getRenderRect();
/*      */                     } 
/*      */                     
/* 1998 */                     if (JSVGComponent.this.doubleBufferedRendering) {
/* 1999 */                       JSVGComponent.this.repaint(r); continue;
/*      */                     } 
/* 2001 */                     JSVGComponent.this.paintImmediately(r);
/*      */                   } 
/* 2003 */                   if (JSVGComponent.this.updateOverlay != null)
/* 2004 */                     JSVGComponent.this.updateOverlay.endUpdate(); 
/*      */                 } 
/* 2006 */                 JSVGComponent.this.suspendInteractions = false;
/*      */               }
/*      */             });
/* 2009 */       } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */       
/* 2013 */       EventQueue.invokeLater(new Runnable() {
/*      */             public void run() {
/* 2015 */               Object[] dll = JSVGComponent.this.updateManagerListeners.toArray();
/*      */               
/* 2017 */               if (dll.length > 0) {
/* 2018 */                 for (Object aDll : dll) {
/* 2019 */                   ((UpdateManagerListener)aDll).updateCompleted(e);
/*      */                 }
/*      */               }
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void updateFailed(final UpdateManagerEvent e) {
/* 2031 */       EventQueue.invokeLater(new Runnable() {
/*      */             public void run() {
/* 2033 */               Object[] dll = JSVGComponent.this.updateManagerListeners.toArray();
/*      */               
/* 2035 */               if (dll.length > 0) {
/* 2036 */                 for (Object aDll : dll) {
/* 2037 */                   ((UpdateManagerListener)aDll).updateFailed(e);
/*      */                 }
/*      */               }
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchKeyTyped(final KeyEvent e) {
/* 2051 */       if (!JSVGComponent.this.isDynamicDocument) {
/* 2052 */         super.dispatchKeyTyped(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2056 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2057 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2060 */                 JSVGComponent.this.eventDispatcher.keyTyped(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchKeyPressed(final KeyEvent e) {
/* 2071 */       if (!JSVGComponent.this.isDynamicDocument) {
/* 2072 */         super.dispatchKeyPressed(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2076 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2077 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2080 */                 JSVGComponent.this.eventDispatcher.keyPressed(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchKeyReleased(final KeyEvent e) {
/* 2090 */       if (!JSVGComponent.this.isDynamicDocument) {
/* 2091 */         super.dispatchKeyReleased(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2095 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2096 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2099 */                 JSVGComponent.this.eventDispatcher.keyReleased(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseClicked(final MouseEvent e) {
/* 2109 */       if (!JSVGComponent.this.isInteractiveDocument) {
/* 2110 */         super.dispatchMouseClicked(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2114 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2115 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2118 */                 JSVGComponent.this.eventDispatcher.mouseClicked(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMousePressed(final MouseEvent e) {
/* 2129 */       if (!JSVGComponent.this.isDynamicDocument) {
/* 2130 */         super.dispatchMousePressed(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2134 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2135 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2138 */                 JSVGComponent.this.eventDispatcher.mousePressed(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseReleased(final MouseEvent e) {
/* 2148 */       if (!JSVGComponent.this.isDynamicDocument) {
/* 2149 */         super.dispatchMouseReleased(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2153 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2154 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2157 */                 JSVGComponent.this.eventDispatcher.mouseReleased(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseEntered(final MouseEvent e) {
/* 2167 */       if (!JSVGComponent.this.isInteractiveDocument) {
/* 2168 */         super.dispatchMouseEntered(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2172 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2173 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2176 */                 JSVGComponent.this.eventDispatcher.mouseEntered(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseExited(final MouseEvent e) {
/* 2186 */       if (!JSVGComponent.this.isInteractiveDocument) {
/* 2187 */         super.dispatchMouseExited(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2191 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2192 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2195 */                 JSVGComponent.this.eventDispatcher.mouseExited(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseDragged(MouseEvent e) {
/* 2205 */       if (!JSVGComponent.this.isDynamicDocument) {
/* 2206 */         super.dispatchMouseDragged(e);
/*      */         return;
/*      */       } 
/*      */       class MouseDraggedRunnable implements Runnable {
/*      */         MouseEvent event;
/*      */         
/*      */         MouseDraggedRunnable(MouseEvent evt) {
/* 2213 */           this.event = evt;
/*      */         }
/*      */         public void run() {
/* 2216 */           JSVGComponent.this.eventDispatcher.mouseDragged(this.event);
/*      */         }
/*      */       };
/*      */       
/* 2220 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2221 */         RunnableQueue rq = JSVGComponent.this.updateManager.getUpdateRunnableQueue();
/*      */ 
/*      */         
/* 2224 */         synchronized (rq.getIteratorLock()) {
/* 2225 */           Iterator it = rq.iterator();
/* 2226 */           while (it.hasNext()) {
/* 2227 */             Object next = it.next();
/* 2228 */             if (next instanceof MouseDraggedRunnable) {
/*      */               
/* 2230 */               MouseDraggedRunnable mdr = (MouseDraggedRunnable)next;
/* 2231 */               MouseEvent mev = mdr.event;
/* 2232 */               if (mev.getModifiers() == e.getModifiers()) {
/* 2233 */                 mdr.event = e;
/*      */               }
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         } 
/* 2240 */         rq.invokeLater(new MouseDraggedRunnable(e));
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseMoved(MouseEvent e) {
/* 2248 */       if (!JSVGComponent.this.isInteractiveDocument) {
/* 2249 */         super.dispatchMouseMoved(e);
/*      */         return;
/*      */       } 
/*      */       class MouseMovedRunnable implements Runnable {
/*      */         MouseEvent event;
/*      */         
/*      */         MouseMovedRunnable(MouseEvent evt) {
/* 2256 */           this.event = evt;
/*      */         }
/*      */         public void run() {
/* 2259 */           JSVGComponent.this.eventDispatcher.mouseMoved(this.event);
/*      */         }
/*      */       };
/*      */       
/* 2263 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2264 */         RunnableQueue rq = JSVGComponent.this.updateManager.getUpdateRunnableQueue();
/*      */ 
/*      */         
/* 2267 */         int i = 0;
/* 2268 */         synchronized (rq.getIteratorLock()) {
/* 2269 */           Iterator it = rq.iterator();
/* 2270 */           while (it.hasNext()) {
/* 2271 */             Object next = it.next();
/* 2272 */             if (next instanceof MouseMovedRunnable) {
/*      */               
/* 2274 */               MouseMovedRunnable mmr = (MouseMovedRunnable)next;
/* 2275 */               MouseEvent mev = mmr.event;
/* 2276 */               if (mev.getModifiers() == e.getModifiers()) {
/* 2277 */                 mmr.event = e;
/*      */               }
/*      */               return;
/*      */             } 
/* 2281 */             i++;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 2286 */         rq.invokeLater(new MouseMovedRunnable(e));
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseWheelMoved(final MouseWheelEvent e) {
/* 2294 */       if (!JSVGComponent.this.isInteractiveDocument) {
/* 2295 */         super.dispatchMouseWheelMoved(e);
/*      */         
/*      */         return;
/*      */       } 
/* 2299 */       if (JSVGComponent.this.updateManager != null && JSVGComponent.this.updateManager.isRunning()) {
/* 2300 */         JSVGComponent.this.updateManager.getUpdateRunnableQueue().invokeLater(new Runnable()
/*      */             {
/*      */               public void run() {
/* 2303 */                 JSVGComponent.this.eventDispatcher.mouseWheelMoved(e);
/*      */               }
/*      */             });
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected UserAgent createUserAgent() {
/* 2314 */     return new BridgeUserAgent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class BridgeUserAgentWrapper
/*      */     implements UserAgent
/*      */   {
/*      */     protected UserAgent userAgent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BridgeUserAgentWrapper(UserAgent ua) {
/* 2331 */       this.userAgent = ua;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EventDispatcher getEventDispatcher() {
/* 2338 */       if (EventQueue.isDispatchThread())
/* 2339 */         return this.userAgent.getEventDispatcher(); 
/*      */       class Query implements Runnable {
/*      */         EventDispatcher result;
/*      */         
/*      */         public void run() {
/* 2344 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getEventDispatcher();
/*      */         }
/*      */       };
/* 2347 */       Query q = new Query();
/* 2348 */       invokeAndWait(q);
/* 2349 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Dimension2D getViewportSize() {
/* 2357 */       if (EventQueue.isDispatchThread())
/* 2358 */         return this.userAgent.getViewportSize(); 
/*      */       class Query implements Runnable {
/*      */         Dimension2D result;
/*      */         
/*      */         public void run() {
/* 2363 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getViewportSize();
/*      */         }
/*      */       };
/* 2366 */       Query q = new Query();
/* 2367 */       invokeAndWait(q);
/* 2368 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(final Exception ex) {
/* 2376 */       if (EventQueue.isDispatchThread()) {
/* 2377 */         this.userAgent.displayError(ex);
/*      */       } else {
/* 2379 */         EventQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/* 2381 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.displayError(ex);
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayMessage(final String message) {
/* 2391 */       if (EventQueue.isDispatchThread()) {
/* 2392 */         this.userAgent.displayMessage(message);
/*      */       } else {
/* 2394 */         EventQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/* 2396 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.displayMessage(message);
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void showAlert(final String message) {
/* 2406 */       if (EventQueue.isDispatchThread()) {
/* 2407 */         this.userAgent.showAlert(message);
/*      */       } else {
/* 2409 */         invokeAndWait(new Runnable() {
/*      */               public void run() {
/* 2411 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.showAlert(message);
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String showPrompt(final String message) {
/* 2421 */       if (EventQueue.isDispatchThread())
/* 2422 */         return this.userAgent.showPrompt(message); 
/*      */       class Query implements Runnable {
/*      */         String result;
/*      */         
/*      */         public void run() {
/* 2427 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.showPrompt(message);
/*      */         }
/*      */       };
/* 2430 */       Query q = new Query();
/* 2431 */       invokeAndWait(q);
/* 2432 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String showPrompt(final String message, final String defaultValue) {
/* 2441 */       if (EventQueue.isDispatchThread())
/* 2442 */         return this.userAgent.showPrompt(message, defaultValue); 
/*      */       class Query implements Runnable {
/*      */         String result;
/*      */         
/*      */         public void run() {
/* 2447 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.showPrompt(message, defaultValue);
/*      */         }
/*      */       };
/* 2450 */       Query q = new Query();
/* 2451 */       invokeAndWait(q);
/* 2452 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean showConfirm(final String message) {
/* 2460 */       if (EventQueue.isDispatchThread())
/* 2461 */         return this.userAgent.showConfirm(message); 
/*      */       class Query implements Runnable {
/*      */         boolean result;
/*      */         
/*      */         public void run() {
/* 2466 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.showConfirm(message);
/*      */         }
/*      */       };
/* 2469 */       Query q = new Query();
/* 2470 */       invokeAndWait(q);
/* 2471 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelUnitToMillimeter() {
/* 2479 */       if (EventQueue.isDispatchThread())
/* 2480 */         return this.userAgent.getPixelUnitToMillimeter(); 
/*      */       class Query implements Runnable {
/*      */         float result;
/*      */         
/*      */         public void run() {
/* 2485 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getPixelUnitToMillimeter();
/*      */         }
/*      */       };
/* 2488 */       Query q = new Query();
/* 2489 */       invokeAndWait(q);
/* 2490 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelToMM() {
/* 2499 */       return getPixelUnitToMillimeter();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getDefaultFontFamily() {
/* 2506 */       if (EventQueue.isDispatchThread())
/* 2507 */         return this.userAgent.getDefaultFontFamily(); 
/*      */       class Query implements Runnable {
/*      */         String result;
/*      */         
/*      */         public void run() {
/* 2512 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getDefaultFontFamily();
/*      */         }
/*      */       };
/* 2515 */       Query q = new Query();
/* 2516 */       invokeAndWait(q);
/* 2517 */       return q.result;
/*      */     }
/*      */ 
/*      */     
/*      */     public float getMediumFontSize() {
/* 2522 */       if (EventQueue.isDispatchThread())
/* 2523 */         return this.userAgent.getMediumFontSize(); 
/*      */       class Query implements Runnable {
/*      */         float result;
/*      */         
/*      */         public void run() {
/* 2528 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getMediumFontSize();
/*      */         }
/*      */       };
/* 2531 */       Query q = new Query();
/* 2532 */       invokeAndWait(q);
/* 2533 */       return q.result;
/*      */     }
/*      */ 
/*      */     
/*      */     public float getLighterFontWeight(float f) {
/* 2538 */       if (EventQueue.isDispatchThread()) {
/* 2539 */         return this.userAgent.getLighterFontWeight(f);
/*      */       }
/* 2541 */       final float ff = f;
/*      */       class Query implements Runnable { float result;
/*      */         
/*      */         public void run() {
/* 2545 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getLighterFontWeight(ff);
/*      */         } }
/*      */       ;
/* 2548 */       Query q = new Query();
/* 2549 */       invokeAndWait(q);
/* 2550 */       return q.result;
/*      */     }
/*      */ 
/*      */     
/*      */     public float getBolderFontWeight(float f) {
/* 2555 */       if (EventQueue.isDispatchThread()) {
/* 2556 */         return this.userAgent.getBolderFontWeight(f);
/*      */       }
/* 2558 */       final float ff = f;
/*      */       class Query implements Runnable { float result;
/*      */         
/*      */         public void run() {
/* 2562 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getBolderFontWeight(ff);
/*      */         } }
/*      */       ;
/* 2565 */       Query q = new Query();
/* 2566 */       invokeAndWait(q);
/* 2567 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getLanguages() {
/* 2575 */       if (EventQueue.isDispatchThread())
/* 2576 */         return this.userAgent.getLanguages(); 
/*      */       class Query implements Runnable {
/*      */         String result;
/*      */         
/*      */         public void run() {
/* 2581 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getLanguages();
/*      */         }
/*      */       };
/* 2584 */       Query q = new Query();
/* 2585 */       invokeAndWait(q);
/* 2586 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getUserStyleSheetURI() {
/* 2595 */       if (EventQueue.isDispatchThread())
/* 2596 */         return this.userAgent.getUserStyleSheetURI(); 
/*      */       class Query implements Runnable {
/*      */         String result;
/*      */         
/*      */         public void run() {
/* 2601 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getUserStyleSheetURI();
/*      */         }
/*      */       };
/* 2604 */       Query q = new Query();
/* 2605 */       invokeAndWait(q);
/* 2606 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void openLink(final SVGAElement elt) {
/* 2615 */       if (EventQueue.isDispatchThread()) {
/* 2616 */         this.userAgent.openLink(elt);
/*      */       } else {
/* 2618 */         EventQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/* 2620 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.openLink(elt);
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setSVGCursor(final Cursor cursor) {
/* 2631 */       if (EventQueue.isDispatchThread()) {
/* 2632 */         this.userAgent.setSVGCursor(cursor);
/*      */       } else {
/* 2634 */         EventQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/* 2636 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.setSVGCursor(cursor);
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setTextSelection(final Mark start, final Mark end) {
/* 2648 */       if (EventQueue.isDispatchThread()) {
/* 2649 */         this.userAgent.setTextSelection(start, end);
/*      */       } else {
/* 2651 */         EventQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/* 2653 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.setTextSelection(start, end);
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void deselectAll() {
/* 2663 */       if (EventQueue.isDispatchThread()) {
/* 2664 */         this.userAgent.deselectAll();
/*      */       } else {
/* 2666 */         EventQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/* 2668 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.deselectAll();
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getXMLParserClassName() {
/* 2678 */       if (EventQueue.isDispatchThread())
/* 2679 */         return this.userAgent.getXMLParserClassName(); 
/*      */       class Query implements Runnable {
/*      */         String result;
/*      */         
/*      */         public void run() {
/* 2684 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getXMLParserClassName();
/*      */         }
/*      */       };
/* 2687 */       Query q = new Query();
/* 2688 */       invokeAndWait(q);
/* 2689 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isXMLParserValidating() {
/* 2698 */       if (EventQueue.isDispatchThread())
/* 2699 */         return this.userAgent.isXMLParserValidating(); 
/*      */       class Query implements Runnable {
/*      */         boolean result;
/*      */         
/*      */         public void run() {
/* 2704 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.isXMLParserValidating();
/*      */         }
/*      */       };
/* 2707 */       Query q = new Query();
/* 2708 */       invokeAndWait(q);
/* 2709 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AffineTransform getTransform() {
/* 2718 */       if (EventQueue.isDispatchThread())
/* 2719 */         return this.userAgent.getTransform(); 
/*      */       class Query implements Runnable {
/*      */         AffineTransform result;
/*      */         
/*      */         public void run() {
/* 2724 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getTransform();
/*      */         }
/*      */       };
/* 2727 */       Query q = new Query();
/* 2728 */       invokeAndWait(q);
/* 2729 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setTransform(AffineTransform at) {
/* 2738 */       if (EventQueue.isDispatchThread()) {
/* 2739 */         this.userAgent.setTransform(at);
/*      */       } else {
/* 2741 */         final AffineTransform affine = at;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2747 */         Query q = new Query();
/* 2748 */         invokeAndWait(q);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getMedia() {
/* 2756 */       if (EventQueue.isDispatchThread())
/* 2757 */         return this.userAgent.getMedia(); 
/*      */       class Query implements Runnable {
/*      */         String result;
/*      */         
/*      */         public void run() {
/* 2762 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getMedia();
/*      */         }
/*      */       };
/* 2765 */       Query q = new Query();
/* 2766 */       invokeAndWait(q);
/* 2767 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getAlternateStyleSheet() {
/* 2775 */       if (EventQueue.isDispatchThread())
/* 2776 */         return this.userAgent.getAlternateStyleSheet(); 
/*      */       class Query implements Runnable {
/*      */         String result;
/*      */         
/*      */         public void run() {
/* 2781 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getAlternateStyleSheet();
/*      */         }
/*      */       };
/* 2784 */       Query q = new Query();
/* 2785 */       invokeAndWait(q);
/* 2786 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Point getClientAreaLocationOnScreen() {
/* 2795 */       if (EventQueue.isDispatchThread())
/* 2796 */         return this.userAgent.getClientAreaLocationOnScreen(); 
/*      */       class Query implements Runnable {
/*      */         Point result;
/*      */         
/*      */         public void run() {
/* 2801 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getClientAreaLocationOnScreen();
/*      */         }
/*      */       };
/* 2804 */       Query q = new Query();
/* 2805 */       invokeAndWait(q);
/* 2806 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasFeature(final String s) {
/* 2815 */       if (EventQueue.isDispatchThread())
/* 2816 */         return this.userAgent.hasFeature(s); 
/*      */       class Query implements Runnable {
/*      */         boolean result;
/*      */         
/*      */         public void run() {
/* 2821 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.hasFeature(s);
/*      */         }
/*      */       };
/* 2824 */       Query q = new Query();
/* 2825 */       invokeAndWait(q);
/* 2826 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean supportExtension(final String s) {
/* 2835 */       if (EventQueue.isDispatchThread())
/* 2836 */         return this.userAgent.supportExtension(s); 
/*      */       class Query implements Runnable {
/*      */         boolean result;
/*      */         
/*      */         public void run() {
/* 2841 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.supportExtension(s);
/*      */         }
/*      */       };
/* 2844 */       Query q = new Query();
/* 2845 */       invokeAndWait(q);
/* 2846 */       return q.result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void registerExtension(final BridgeExtension ext) {
/* 2855 */       if (EventQueue.isDispatchThread()) {
/* 2856 */         this.userAgent.registerExtension(ext);
/*      */       } else {
/* 2858 */         EventQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/* 2860 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.registerExtension(ext);
/*      */               }
/*      */             });
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
/*      */     public void handleElement(final Element elt, final Object data) {
/* 2874 */       if (EventQueue.isDispatchThread()) {
/* 2875 */         this.userAgent.handleElement(elt, data);
/*      */       } else {
/* 2877 */         EventQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/* 2879 */                 JSVGComponent.BridgeUserAgentWrapper.this.userAgent.handleElement(elt, data);
/*      */               }
/*      */             });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptPURL, ParsedURL docPURL) {
/* 2901 */       if (EventQueue.isDispatchThread()) {
/* 2902 */         return this.userAgent.getScriptSecurity(scriptType, scriptPURL, docPURL);
/*      */       }
/*      */ 
/*      */       
/* 2906 */       final String st = scriptType;
/* 2907 */       final ParsedURL sPURL = scriptPURL;
/* 2908 */       final ParsedURL dPURL = docPURL;
/*      */       class Query implements Runnable { ScriptSecurity result;
/*      */         
/*      */         public void run() {
/* 2912 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getScriptSecurity(st, sPURL, dPURL);
/*      */         } }
/*      */       ;
/* 2915 */       Query q = new Query();
/* 2916 */       invokeAndWait(q);
/* 2917 */       return q.result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void checkLoadScript(String scriptType, ParsedURL scriptPURL, ParsedURL docPURL) throws SecurityException {
/* 2942 */       if (EventQueue.isDispatchThread()) {
/* 2943 */         this.userAgent.checkLoadScript(scriptType, scriptPURL, docPURL);
/*      */       }
/*      */       else {
/*      */         
/* 2947 */         final String st = scriptType;
/* 2948 */         final ParsedURL sPURL = scriptPURL;
/* 2949 */         final ParsedURL dPURL = docPURL;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2960 */         Query q = new Query();
/* 2961 */         invokeAndWait(q);
/* 2962 */         if (q.se != null) {
/* 2963 */           q.se.fillInStackTrace();
/* 2964 */           throw q.se;
/*      */         } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ExternalResourceSecurity getExternalResourceSecurity(ParsedURL resourcePURL, ParsedURL docPURL) {
/* 2984 */       if (EventQueue.isDispatchThread()) {
/* 2985 */         return this.userAgent.getExternalResourceSecurity(resourcePURL, docPURL);
/*      */       }
/*      */       
/* 2988 */       final ParsedURL rPURL = resourcePURL;
/* 2989 */       final ParsedURL dPURL = docPURL;
/*      */       class Query implements Runnable { ExternalResourceSecurity result;
/*      */         
/*      */         public void run() {
/* 2993 */           this.result = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getExternalResourceSecurity(rPURL, dPURL);
/*      */         } }
/*      */       ;
/* 2996 */       Query q = new Query();
/* 2997 */       invokeAndWait(q);
/* 2998 */       return q.result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException {
/* 3021 */       if (EventQueue.isDispatchThread()) {
/* 3022 */         this.userAgent.checkLoadExternalResource(resourceURL, docURL);
/*      */       } else {
/*      */         
/* 3025 */         final ParsedURL rPURL = resourceURL;
/* 3026 */         final ParsedURL dPURL = docURL;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3037 */         Query q = new Query();
/* 3038 */         invokeAndWait(q);
/* 3039 */         if (q.se != null) {
/* 3040 */           q.se.fillInStackTrace();
/* 3041 */           throw q.se;
/*      */         } 
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
/*      */ 
/*      */ 
/*      */     
/*      */     public SVGDocument getBrokenLinkDocument(final Element e, final String url, final String msg) {
/* 3058 */       if (EventQueue.isDispatchThread())
/* 3059 */         return this.userAgent.getBrokenLinkDocument(e, url, msg); 
/*      */       class Query
/*      */         implements Runnable {
/*      */         SVGDocument doc;
/* 3063 */         RuntimeException rex = null;
/*      */         public void run() {
/*      */           
/* 3066 */           try { this.doc = JSVGComponent.BridgeUserAgentWrapper.this.userAgent.getBrokenLinkDocument(e, url, msg); }
/* 3067 */           catch (RuntimeException re) { this.rex = re = null; }
/*      */         
/*      */         } };
/* 3070 */       Query q = new Query();
/* 3071 */       invokeAndWait(q);
/* 3072 */       if (q.rex != null) throw q.rex; 
/* 3073 */       return q.doc;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void invokeAndWait(Runnable r) {
/*      */       try {
/* 3083 */         EventQueue.invokeAndWait(r);
/* 3084 */       } catch (Exception exception) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void loadDocument(String url) {
/* 3094 */       this.userAgent.loadDocument(url);
/*      */     }
/*      */     
/*      */     public FontFamilyResolver getFontFamilyResolver() {
/* 3098 */       return this.userAgent.getFontFamilyResolver();
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
/*      */   protected class BridgeUserAgent
/*      */     implements UserAgent
/*      */   {
/*      */     public Dimension2D getViewportSize() {
/* 3117 */       return JSVGComponent.this.getSize();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EventDispatcher getEventDispatcher() {
/* 3125 */       return (EventDispatcher)JSVGComponent.this.eventDispatcher;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(String message) {
/* 3132 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3133 */         JSVGComponent.this.svgUserAgent.displayError(message);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(Exception ex) {
/* 3141 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3142 */         JSVGComponent.this.svgUserAgent.displayError(ex);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayMessage(String message) {
/* 3150 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3151 */         JSVGComponent.this.svgUserAgent.displayMessage(message);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void showAlert(String message) {
/* 3159 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3160 */         JSVGComponent.this.svgUserAgent.showAlert(message);
/*      */         return;
/*      */       } 
/* 3163 */       JSVGComponent.this.showAlert(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String showPrompt(String message) {
/* 3170 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3171 */         return JSVGComponent.this.svgUserAgent.showPrompt(message);
/*      */       }
/* 3173 */       return JSVGComponent.this.showPrompt(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String showPrompt(String message, String defaultValue) {
/* 3180 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3181 */         return JSVGComponent.this.svgUserAgent.showPrompt(message, defaultValue);
/*      */       }
/* 3183 */       return JSVGComponent.this.showPrompt(message, defaultValue);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean showConfirm(String message) {
/* 3191 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3192 */         return JSVGComponent.this.svgUserAgent.showConfirm(message);
/*      */       }
/* 3194 */       return JSVGComponent.this.showConfirm(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelUnitToMillimeter() {
/* 3201 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3202 */         return JSVGComponent.this.svgUserAgent.getPixelUnitToMillimeter();
/*      */       }
/* 3204 */       return 0.26458332F;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelToMM() {
/* 3212 */       return getPixelUnitToMillimeter();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getDefaultFontFamily() {
/* 3218 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3219 */         return JSVGComponent.this.svgUserAgent.getDefaultFontFamily();
/*      */       }
/* 3221 */       return "Arial, Helvetica, sans-serif";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getMediumFontSize() {
/* 3228 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3229 */         return JSVGComponent.this.svgUserAgent.getMediumFontSize();
/*      */       }
/*      */       
/* 3232 */       return 228.59999F / 72.0F * getPixelUnitToMillimeter();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getLighterFontWeight(float f) {
/* 3239 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3240 */         return JSVGComponent.this.svgUserAgent.getLighterFontWeight(f);
/*      */       }
/*      */       
/* 3243 */       int weight = (int)((f + 50.0F) / 100.0F) * 100;
/* 3244 */       switch (weight) { case 100:
/* 3245 */           return 100.0F;
/* 3246 */         case 200: return 100.0F;
/* 3247 */         case 300: return 200.0F;
/* 3248 */         case 400: return 300.0F;
/* 3249 */         case 500: return 400.0F;
/* 3250 */         case 600: return 400.0F;
/* 3251 */         case 700: return 400.0F;
/* 3252 */         case 800: return 400.0F;
/* 3253 */         case 900: return 400.0F; }
/*      */       
/* 3255 */       throw new IllegalArgumentException("Bad Font Weight: " + f);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getBolderFontWeight(float f) {
/* 3263 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3264 */         return JSVGComponent.this.svgUserAgent.getBolderFontWeight(f);
/*      */       }
/*      */       
/* 3267 */       int weight = (int)((f + 50.0F) / 100.0F) * 100;
/* 3268 */       switch (weight) { case 100:
/* 3269 */           return 600.0F;
/* 3270 */         case 200: return 600.0F;
/* 3271 */         case 300: return 600.0F;
/* 3272 */         case 400: return 600.0F;
/* 3273 */         case 500: return 600.0F;
/* 3274 */         case 600: return 700.0F;
/* 3275 */         case 700: return 800.0F;
/* 3276 */         case 800: return 900.0F;
/* 3277 */         case 900: return 900.0F; }
/*      */       
/* 3279 */       throw new IllegalArgumentException("Bad Font Weight: " + f);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getLanguages() {
/* 3287 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3288 */         return JSVGComponent.this.svgUserAgent.getLanguages();
/*      */       }
/* 3290 */       return "en";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getUserStyleSheetURI() {
/* 3298 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3299 */         return JSVGComponent.this.svgUserAgent.getUserStyleSheetURI();
/*      */       }
/* 3301 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void openLink(SVGAElement elt) {
/* 3309 */       String show = XLinkSupport.getXLinkShow((Element)elt);
/* 3310 */       String href = elt.getHref().getAnimVal();
/* 3311 */       if (show.equals("new")) {
/* 3312 */         fireLinkActivatedEvent(elt, href);
/* 3313 */         if (JSVGComponent.this.svgUserAgent != null) {
/* 3314 */           String oldURI = JSVGComponent.this.svgDocument.getURL();
/* 3315 */           ParsedURL parsedURL = null;
/*      */           
/* 3317 */           if (elt.getOwnerDocument() != JSVGComponent.this.svgDocument) {
/* 3318 */             SVGDocument doc = (SVGDocument)elt.getOwnerDocument();
/* 3319 */             href = (new ParsedURL(doc.getURL(), href)).toString();
/*      */           } 
/* 3321 */           parsedURL = new ParsedURL(oldURI, href);
/* 3322 */           href = parsedURL.toString();
/* 3323 */           JSVGComponent.this.svgUserAgent.openLink(href, true);
/*      */         } else {
/* 3325 */           JSVGComponent.this.loadSVGDocument(href);
/*      */         } 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 3332 */       ParsedURL newURI = new ParsedURL(((SVGDocument)elt.getOwnerDocument()).getURL(), href);
/*      */ 
/*      */ 
/*      */       
/* 3336 */       href = newURI.toString();
/*      */ 
/*      */       
/* 3339 */       if (JSVGComponent.this.svgDocument != null) {
/*      */         
/* 3341 */         ParsedURL oldURI = new ParsedURL(JSVGComponent.this.svgDocument.getURL());
/*      */         
/* 3343 */         if (newURI.sameFile(oldURI)) {
/*      */           
/* 3345 */           String s = newURI.getRef();
/* 3346 */           if (JSVGComponent.this.fragmentIdentifier != s && (s == null || !s.equals(JSVGComponent.this.fragmentIdentifier))) {
/*      */ 
/*      */             
/* 3349 */             JSVGComponent.this.fragmentIdentifier = s;
/* 3350 */             if (JSVGComponent.this.computeRenderingTransform()) {
/* 3351 */               JSVGComponent.this.scheduleGVTRendering();
/*      */             }
/*      */           } 
/*      */           
/* 3355 */           fireLinkActivatedEvent(elt, href);
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/* 3360 */       fireLinkActivatedEvent(elt, href);
/* 3361 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3362 */         JSVGComponent.this.svgUserAgent.openLink(href, false);
/*      */       } else {
/* 3364 */         JSVGComponent.this.loadSVGDocument(href);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void fireLinkActivatedEvent(SVGAElement elt, String href) {
/* 3372 */       Object[] ll = JSVGComponent.this.linkActivationListeners.toArray();
/*      */       
/* 3374 */       if (ll.length > 0) {
/*      */         
/* 3376 */         LinkActivationEvent ev = new LinkActivationEvent(JSVGComponent.this, elt, href);
/*      */         
/* 3378 */         for (Object aLl : ll) {
/* 3379 */           LinkActivationListener l = (LinkActivationListener)aLl;
/* 3380 */           l.linkActivated(ev);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setSVGCursor(Cursor cursor) {
/* 3390 */       if (cursor != JSVGComponent.this.getCursor()) {
/* 3391 */         JSVGComponent.this.setCursor(cursor);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setTextSelection(Mark start, Mark end) {
/* 3400 */       JSVGComponent.this.select(start, end);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void deselectAll() {
/* 3408 */       JSVGComponent.this.deselectAll();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getXMLParserClassName() {
/* 3415 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3416 */         return JSVGComponent.this.svgUserAgent.getXMLParserClassName();
/*      */       }
/* 3418 */       return XMLResourceDescriptor.getXMLParserClassName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isXMLParserValidating() {
/* 3426 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3427 */         return JSVGComponent.this.svgUserAgent.isXMLParserValidating();
/*      */       }
/* 3429 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AffineTransform getTransform() {
/* 3437 */       return JSVGComponent.this.renderingTransform;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setTransform(AffineTransform at) {
/* 3445 */       JSVGComponent.this.setRenderingTransform(at);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getMedia() {
/* 3452 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3453 */         return JSVGComponent.this.svgUserAgent.getMedia();
/*      */       }
/* 3455 */       return "screen";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getAlternateStyleSheet() {
/* 3462 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3463 */         return JSVGComponent.this.svgUserAgent.getAlternateStyleSheet();
/*      */       }
/* 3465 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Point getClientAreaLocationOnScreen() {
/* 3473 */       return JSVGComponent.this.getLocationOnScreen();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasFeature(String s) {
/* 3481 */       return JSVGComponent.FEATURES.contains(s);
/*      */     }
/*      */     
/* 3484 */     protected Map extensions = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean supportExtension(String s) {
/* 3491 */       if (JSVGComponent.this.svgUserAgent != null && JSVGComponent.this.svgUserAgent.supportExtension(s))
/*      */       {
/* 3493 */         return true;
/*      */       }
/* 3495 */       return this.extensions.containsKey(s);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void registerExtension(BridgeExtension ext) {
/* 3503 */       Iterator i = ext.getImplementedExtensions();
/* 3504 */       while (i.hasNext()) {
/* 3505 */         this.extensions.put(i.next(), ext);
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
/*      */     public void handleElement(Element elt, Object data) {
/* 3517 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3518 */         JSVGComponent.this.svgUserAgent.handleElement(elt, data);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptURL, ParsedURL docURL) {
/* 3538 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3539 */         return JSVGComponent.this.svgUserAgent.getScriptSecurity(scriptType, scriptURL, docURL);
/*      */       }
/*      */ 
/*      */       
/* 3543 */       return (ScriptSecurity)new DefaultScriptSecurity(scriptType, scriptURL, docURL);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void checkLoadScript(String scriptType, ParsedURL scriptURL, ParsedURL docURL) throws SecurityException {
/* 3570 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3571 */         JSVGComponent.this.svgUserAgent.checkLoadScript(scriptType, scriptURL, docURL);
/*      */       }
/*      */       else {
/*      */         
/* 3575 */         ScriptSecurity s = getScriptSecurity(scriptType, scriptURL, docURL);
/*      */ 
/*      */         
/* 3578 */         if (s != null) {
/* 3579 */           s.checkLoadScript();
/*      */         }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ExternalResourceSecurity getExternalResourceSecurity(ParsedURL resourceURL, ParsedURL docURL) {
/* 3598 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3599 */         return JSVGComponent.this.svgUserAgent.getExternalResourceSecurity(resourceURL, docURL);
/*      */       }
/*      */       
/* 3602 */       return (ExternalResourceSecurity)new RelaxedExternalResourceSecurity(resourceURL, docURL);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException {
/* 3626 */       if (JSVGComponent.this.svgUserAgent != null) {
/* 3627 */         JSVGComponent.this.svgUserAgent.checkLoadExternalResource(resourceURL, docURL);
/*      */       } else {
/*      */         
/* 3630 */         ExternalResourceSecurity s = getExternalResourceSecurity(resourceURL, docURL);
/*      */ 
/*      */         
/* 3633 */         if (s != null) {
/* 3634 */           s.checkLoadExternalResource();
/*      */         }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SVGDocument getBrokenLinkDocument(Element e, String url, String message) {
/* 3654 */       Class<JSVGComponent> cls = JSVGComponent.class;
/* 3655 */       URL blURL = cls.getResource("resources/BrokenLink.svg");
/* 3656 */       if (blURL == null) {
/* 3657 */         throw new BridgeException(JSVGComponent.this.bridgeContext, e, "uri.image.broken", new Object[] { url, message });
/*      */       }
/*      */ 
/*      */       
/* 3661 */       DocumentLoader loader = JSVGComponent.this.bridgeContext.getDocumentLoader();
/* 3662 */       SVGDocument doc = null;
/*      */       
/*      */       try {
/* 3665 */         doc = (SVGDocument)loader.loadDocument(blURL.toString());
/* 3666 */         if (doc == null) return doc;
/*      */ 
/*      */         
/* 3669 */         DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
/* 3670 */         doc = (SVGDocument)DOMUtilities.deepCloneDocument((Document)doc, impl);
/*      */ 
/*      */ 
/*      */         
/* 3674 */         Element infoE = doc.getElementById("__More_About");
/* 3675 */         if (infoE == null) return doc;
/*      */         
/* 3677 */         Element titleE = doc.createElementNS("http://www.w3.org/2000/svg", "title");
/*      */         
/* 3679 */         String title = Messages.formatMessage("broken.link.title", null);
/* 3680 */         titleE.appendChild(doc.createTextNode(title));
/*      */         
/* 3682 */         Element descE = doc.createElementNS("http://www.w3.org/2000/svg", "desc");
/*      */         
/* 3684 */         descE.appendChild(doc.createTextNode(message));
/*      */         
/* 3686 */         infoE.insertBefore(descE, infoE.getFirstChild());
/* 3687 */         infoE.insertBefore(titleE, descE);
/* 3688 */       } catch (Exception ex) {
/* 3689 */         throw new BridgeException(JSVGComponent.this.bridgeContext, e, ex, "uri.image.broken", new Object[] { url, message });
/*      */       } 
/*      */ 
/*      */       
/* 3693 */       return doc;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void loadDocument(String url) {
/* 3702 */       JSVGComponent.this.loadSVGDocument(url);
/*      */     }
/*      */     
/*      */     public FontFamilyResolver getFontFamilyResolver() {
/* 3706 */       return (FontFamilyResolver)DefaultFontFamilyResolver.SINGLETON;
/*      */     }
/*      */   }
/*      */   
/* 3710 */   protected static final Set FEATURES = new HashSet();
/*      */   static {
/* 3712 */     SVGFeatureStrings.addSupportedFeatureStrings(FEATURES);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/JSVGComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */