/*      */ package org.apache.batik.swing;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.InputEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseMotionAdapter;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.beans.PropertyChangeSupport;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.WeakHashMap;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.ActionMap;
/*      */ import javax.swing.InputMap;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.KeyStroke;
/*      */ import javax.swing.ToolTipManager;
/*      */ import org.apache.batik.bridge.UserAgent;
/*      */ import org.apache.batik.css.engine.CSSEngine;
/*      */ import org.apache.batik.css.engine.CSSStylableElement;
/*      */ import org.apache.batik.dom.events.NodeEventTarget;
/*      */ import org.apache.batik.gvt.GraphicsNode;
/*      */ import org.apache.batik.swing.gvt.AbstractImageZoomInteractor;
/*      */ import org.apache.batik.swing.gvt.AbstractPanInteractor;
/*      */ import org.apache.batik.swing.gvt.AbstractResetTransformInteractor;
/*      */ import org.apache.batik.swing.gvt.AbstractRotateInteractor;
/*      */ import org.apache.batik.swing.gvt.AbstractZoomInteractor;
/*      */ import org.apache.batik.swing.gvt.Interactor;
/*      */ import org.apache.batik.swing.gvt.JGVTComponent;
/*      */ import org.apache.batik.swing.svg.JSVGComponent;
/*      */ import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
/*      */ import org.apache.batik.swing.svg.SVGUserAgent;
/*      */ import org.apache.batik.util.XMLConstants;
/*      */ import org.apache.batik.util.gui.JErrorPane;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventListener;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ import org.w3c.dom.events.MouseEvent;
/*      */ import org.w3c.dom.svg.SVGDocument;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JSVGCanvas
/*      */   extends JSVGComponent
/*      */ {
/*      */   public static final String SCROLL_RIGHT_ACTION = "ScrollRight";
/*      */   public static final String SCROLL_LEFT_ACTION = "ScrollLeft";
/*      */   public static final String SCROLL_UP_ACTION = "ScrollUp";
/*      */   public static final String SCROLL_DOWN_ACTION = "ScrollDown";
/*      */   public static final String FAST_SCROLL_RIGHT_ACTION = "FastScrollRight";
/*      */   public static final String FAST_SCROLL_LEFT_ACTION = "FastScrollLeft";
/*      */   public static final String FAST_SCROLL_UP_ACTION = "FastScrollUp";
/*      */   public static final String FAST_SCROLL_DOWN_ACTION = "FastScrollDown";
/*      */   public static final String ZOOM_IN_ACTION = "ZoomIn";
/*      */   public static final String ZOOM_OUT_ACTION = "ZoomOut";
/*      */   public static final String RESET_TRANSFORM_ACTION = "ResetTransform";
/*      */   private boolean isZoomInteractorEnabled = true;
/*      */   private boolean isImageZoomInteractorEnabled = true;
/*      */   private boolean isPanInteractorEnabled = true;
/*      */   private boolean isRotateInteractorEnabled = true;
/*      */   private boolean isResetTransformInteractorEnabled = true;
/*  177 */   protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String uri;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  188 */   protected LocationListener locationListener = new LocationListener();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  194 */   protected Map toolTipMap = null;
/*  195 */   protected EventListener toolTipListener = new ToolTipModifier();
/*  196 */   protected EventTarget lastTarget = null;
/*  197 */   protected Map toolTipDocs = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  202 */   protected static final Object MAP_TOKEN = new Object();
/*      */   
/*      */   protected long lastToolTipEventTimeStamp;
/*      */   
/*      */   protected EventTarget lastToolTipEventTarget;
/*      */   
/*      */   protected Interactor zoomInteractor;
/*      */   
/*      */   protected Interactor imageZoomInteractor;
/*      */   
/*      */   protected Interactor panInteractor;
/*      */   
/*      */   protected Interactor rotateInteractor;
/*      */   
/*      */   protected Interactor resetTransformInteractor;
/*      */   
/*      */   public JSVGCanvas() {
/*  219 */     this((SVGUserAgent)null, true, true);
/*  220 */     addMouseMotionListener(this.locationListener);
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
/*      */   public JSVGCanvas(SVGUserAgent ua, boolean eventsEnabled, boolean selectableText) {
/*  235 */     super(ua, eventsEnabled, selectableText);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  775 */     this.zoomInteractor = (Interactor)new AbstractZoomInteractor() {
/*      */         public boolean startInteraction(InputEvent ie) {
/*  777 */           int mods = ie.getModifiers();
/*  778 */           return (ie.getID() == 501 && (mods & 0x10) != 0 && (mods & 0x2) != 0);
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  789 */     this.imageZoomInteractor = (Interactor)new AbstractImageZoomInteractor()
/*      */       {
/*      */         public boolean startInteraction(InputEvent ie) {
/*  792 */           int mods = ie.getModifiers();
/*  793 */           return (ie.getID() == 501 && (mods & 0x4) != 0 && (mods & 0x1) != 0);
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  804 */     this.panInteractor = (Interactor)new AbstractPanInteractor() {
/*      */         public boolean startInteraction(InputEvent ie) {
/*  806 */           int mods = ie.getModifiers();
/*  807 */           return (ie.getID() == 501 && (mods & 0x10) != 0 && (mods & 0x1) != 0);
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  818 */     this.rotateInteractor = (Interactor)new AbstractRotateInteractor() {
/*      */         public boolean startInteraction(InputEvent ie) {
/*  820 */           int mods = ie.getModifiers();
/*  821 */           return (ie.getID() == 501 && (mods & 0x4) != 0 && (mods & 0x2) != 0);
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  832 */     this.resetTransformInteractor = (Interactor)new AbstractResetTransformInteractor() { public void mousePressed(MouseEvent evt) { JSVGCanvas.this.requestFocus(); } }
/*      */       ; setPreferredSize(new Dimension(200, 200)); setMinimumSize(new Dimension(100, 100)); List<Interactor> intl = getInteractors(); intl.add(this.zoomInteractor); intl.add(this.imageZoomInteractor); intl.add(this.panInteractor); intl.add(this.rotateInteractor); intl.add(this.resetTransformInteractor); installActions();
/*      */     if (eventsEnabled)
/*  835 */     { addMouseListener(new MouseAdapter() { public boolean startInteraction(InputEvent ie) { int mods = ie.getModifiers();
/*  836 */               return (ie.getID() == 500 && (mods & 0x4) != 0 && (mods & 0x1) != 0 && (mods & 0x2) != 0); } }); installKeyboardActions(); }  addMouseMotionListener(this.locationListener);
/*      */   }
/*      */   protected void installActions() { ActionMap actionMap = getActionMap(); actionMap.put("ScrollRight", new ScrollRightAction(10)); actionMap.put("ScrollLeft", new ScrollLeftAction(10)); actionMap.put("ScrollUp", new ScrollUpAction(10)); actionMap.put("ScrollDown", new ScrollDownAction(10)); actionMap.put("FastScrollRight", new ScrollRightAction(30)); actionMap.put("FastScrollLeft", new ScrollLeftAction(30)); actionMap.put("FastScrollUp", new ScrollUpAction(30)); actionMap.put("FastScrollDown", new ScrollDownAction(30)); actionMap.put("ZoomIn", new ZoomInAction()); actionMap.put("ZoomOut", new ZoomOutAction()); actionMap.put("ResetTransform", new ResetTransformAction()); }
/*      */   public void setDisableInteractions(boolean b) { super.setDisableInteractions(b); ActionMap actionMap = getActionMap(); actionMap.get("ScrollRight").setEnabled(!b); actionMap.get("ScrollLeft").setEnabled(!b); actionMap.get("ScrollUp").setEnabled(!b); actionMap.get("ScrollDown").setEnabled(!b); actionMap.get("FastScrollRight").setEnabled(!b); actionMap.get("FastScrollLeft").setEnabled(!b); actionMap.get("FastScrollUp").setEnabled(!b); actionMap.get("FastScrollDown").setEnabled(!b); actionMap.get("ZoomIn").setEnabled(!b); actionMap.get("ZoomOut").setEnabled(!b); actionMap.get("ResetTransform").setEnabled(!b); }
/*      */   protected void installKeyboardActions() { InputMap inputMap = getInputMap(0); KeyStroke key = KeyStroke.getKeyStroke(39, 0); inputMap.put(key, "ScrollRight"); key = KeyStroke.getKeyStroke(37, 0); inputMap.put(key, "ScrollLeft"); key = KeyStroke.getKeyStroke(38, 0); inputMap.put(key, "ScrollUp"); key = KeyStroke.getKeyStroke(40, 0); inputMap.put(key, "ScrollDown"); key = KeyStroke.getKeyStroke(39, 1); inputMap.put(key, "FastScrollRight"); key = KeyStroke.getKeyStroke(37, 1); inputMap.put(key, "FastScrollLeft"); key = KeyStroke.getKeyStroke(38, 1); inputMap.put(key, "FastScrollUp"); key = KeyStroke.getKeyStroke(40, 1); inputMap.put(key, "FastScrollDown"); key = KeyStroke.getKeyStroke(73, 2); inputMap.put(key, "ZoomIn"); key = KeyStroke.getKeyStroke(79, 2); inputMap.put(key, "ZoomOut"); key = KeyStroke.getKeyStroke(84, 2); inputMap.put(key, "ResetTransform"); } public void addPropertyChangeListener(PropertyChangeListener pcl) { this.pcs.addPropertyChangeListener(pcl); } public void removePropertyChangeListener(PropertyChangeListener pcl) { this.pcs.removePropertyChangeListener(pcl); } public void addPropertyChangeListener(String propertyName, PropertyChangeListener pcl) { this.pcs.addPropertyChangeListener(propertyName, pcl); } public void removePropertyChangeListener(String propertyName, PropertyChangeListener pcl) { this.pcs.removePropertyChangeListener(propertyName, pcl); } public void setEnableZoomInteractor(boolean b) { if (this.isZoomInteractorEnabled != b) { boolean oldValue = this.isZoomInteractorEnabled; this.isZoomInteractorEnabled = b; if (this.isZoomInteractorEnabled) { getInteractors().add(this.zoomInteractor); } else { getInteractors().remove(this.zoomInteractor); }  this.pcs.firePropertyChange("enableZoomInteractor", oldValue, b); }  } public boolean getEnableZoomInteractor() { return this.isZoomInteractorEnabled; } public void setEnableImageZoomInteractor(boolean b) { if (this.isImageZoomInteractorEnabled != b) { boolean oldValue = this.isImageZoomInteractorEnabled; this.isImageZoomInteractorEnabled = b; if (this.isImageZoomInteractorEnabled) { getInteractors().add(this.imageZoomInteractor); } else { getInteractors().remove(this.imageZoomInteractor); }  this.pcs.firePropertyChange("enableImageZoomInteractor", oldValue, b); }  } public boolean getEnableImageZoomInteractor() { return this.isImageZoomInteractorEnabled; } public void setEnablePanInteractor(boolean b) { if (this.isPanInteractorEnabled != b) { boolean oldValue = this.isPanInteractorEnabled; this.isPanInteractorEnabled = b; if (this.isPanInteractorEnabled) { getInteractors().add(this.panInteractor); } else { getInteractors().remove(this.panInteractor); }  this.pcs.firePropertyChange("enablePanInteractor", oldValue, b); }  } public boolean getEnablePanInteractor() { return this.isPanInteractorEnabled; } public void setEnableRotateInteractor(boolean b) { if (this.isRotateInteractorEnabled != b) { boolean oldValue = this.isRotateInteractorEnabled; this.isRotateInteractorEnabled = b; if (this.isRotateInteractorEnabled) { getInteractors().add(this.rotateInteractor); } else { getInteractors().remove(this.rotateInteractor); }  this.pcs.firePropertyChange("enableRotateInteractor", oldValue, b); }  } public boolean getEnableRotateInteractor() { return this.isRotateInteractorEnabled; } public void setEnableResetTransformInteractor(boolean b) { if (this.isResetTransformInteractorEnabled != b) { boolean oldValue = this.isResetTransformInteractorEnabled; this.isResetTransformInteractorEnabled = b; if (this.isResetTransformInteractorEnabled) { getInteractors().add(this.resetTransformInteractor); } else { getInteractors().remove(this.resetTransformInteractor); }  this.pcs.firePropertyChange("enableResetTransformInteractor", oldValue, b); }  } public boolean getEnableResetTransformInteractor() { return this.isResetTransformInteractorEnabled; } public String getURI() { return this.uri; } public void setURI(String newURI) { String oldValue = this.uri; this.uri = newURI; if (this.uri != null) { loadSVGDocument(this.uri); } else { setSVGDocument(null); }  this.pcs.firePropertyChange("URI", oldValue, this.uri); } protected UserAgent createUserAgent() { return (UserAgent)new CanvasUserAgent(); } protected JGVTComponent.Listener createListener() { return (JGVTComponent.Listener)new CanvasSVGListener(); } protected class CanvasSVGListener extends JSVGComponent.SVGListener {
/*      */     protected CanvasSVGListener() { super(JSVGCanvas.this); } public void documentLoadingStarted(SVGDocumentLoaderEvent e) { super.documentLoadingStarted(e); JSVGCanvas.this.setToolTipText(null); } } protected void installSVGDocument(SVGDocument doc) { if (this.toolTipDocs != null) { for (Object o : this.toolTipDocs.keySet()) { SVGDocument ttdoc = (SVGDocument)o; if (ttdoc == null) continue;  NodeEventTarget root = (NodeEventTarget)ttdoc.getRootElement(); if (root == null) continue;  root.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.toolTipListener, false); root.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.toolTipListener, false); }  this.toolTipDocs = null; }  this.lastTarget = null; if (this.toolTipMap != null) this.toolTipMap.clear();  super.installSVGDocument(doc); } public class ResetTransformAction extends AbstractAction {
/*      */     public void actionPerformed(ActionEvent evt) { JSVGCanvas.this.fragmentIdentifier = null; JSVGCanvas.this.resetRenderingTransform(); } } public class AffineAction extends AbstractAction {
/*      */     AffineTransform at; public AffineAction(AffineTransform at) { this.at = at; } public void actionPerformed(ActionEvent evt) { if (JSVGCanvas.this.gvtRoot == null) return;  AffineTransform rat = JSVGCanvas.this.getRenderingTransform(); if (this.at != null) { Dimension dim = JSVGCanvas.this.getSize(); int x = dim.width / 2; int y = dim.height / 2; AffineTransform t = AffineTransform.getTranslateInstance(x, y); t.concatenate(this.at); t.translate(-x, -y); t.concatenate(rat); JSVGCanvas.this.setRenderingTransform(t); }  } } public class ZoomAction extends AffineAction {
/*      */     public ZoomAction(double scale) { super(AffineTransform.getScaleInstance(scale, scale)); } public ZoomAction(double scaleX, double scaleY) { super(AffineTransform.getScaleInstance(scaleX, scaleY)); } } public class ZoomInAction extends ZoomAction {
/*      */     ZoomInAction() { super(2.0D); } } public class ZoomOutAction extends ZoomAction {
/*      */     ZoomOutAction() { super(0.5D); } } public class RotateAction extends AffineAction {
/*      */     public RotateAction(double theta) { super(AffineTransform.getRotateInstance(theta)); } } public class ScrollAction extends AffineAction {
/*      */     public ScrollAction(double tx, double ty) { super(AffineTransform.getTranslateInstance(tx, ty)); } } public class ScrollRightAction extends ScrollAction {
/*      */     public ScrollRightAction(int inc) { super(-inc, 0.0D); } } public class ScrollLeftAction extends ScrollAction {
/*      */     public ScrollLeftAction(int inc) { super(inc, 0.0D); } } public class ScrollUpAction extends ScrollAction {
/*      */     public ScrollUpAction(int inc) { super(0.0D, inc); }
/*      */   } public class ScrollDownAction extends ScrollAction {
/*      */     public ScrollDownAction(int inc) { super(0.0D, -inc); }
/*  854 */   } protected class CanvasUserAgent extends JSVGComponent.BridgeUserAgent implements XMLConstants { protected CanvasUserAgent() { super(JSVGCanvas.this);
/*      */ 
/*      */ 
/*      */       
/*  858 */       this.TOOLTIP_TITLE_ONLY = "JSVGCanvas.CanvasUserAgent.ToolTip.titleOnly";
/*      */       
/*  860 */       this.TOOLTIP_DESC_ONLY = "JSVGCanvas.CanvasUserAgent.ToolTip.descOnly";
/*      */       
/*  862 */       this.TOOLTIP_TITLE_AND_TEXT = "JSVGCanvas.CanvasUserAgent.ToolTip.titleAndDesc"; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final String TOOLTIP_TITLE_ONLY = "JSVGCanvas.CanvasUserAgent.ToolTip.titleOnly";
/*      */ 
/*      */ 
/*      */     
/*      */     final String TOOLTIP_DESC_ONLY = "JSVGCanvas.CanvasUserAgent.ToolTip.descOnly";
/*      */ 
/*      */ 
/*      */     
/*      */     final String TOOLTIP_TITLE_AND_TEXT = "JSVGCanvas.CanvasUserAgent.ToolTip.titleAndDesc";
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleElement(Element elt, Object data) {
/*      */       Element parent;
/*      */       final String toolTip;
/*  884 */       super.handleElement(elt, data);
/*      */ 
/*      */       
/*  887 */       if (!JSVGCanvas.this.isInteractive())
/*      */         return; 
/*  889 */       if (!"http://www.w3.org/2000/svg".equals(elt.getNamespaceURI())) {
/*      */         return;
/*      */       }
/*      */       
/*  893 */       if (elt.getParentNode() == elt.getOwnerDocument().getDocumentElement()) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  901 */       if (data instanceof Element) { parent = (Element)data; }
/*  902 */       else { parent = (Element)elt.getParentNode(); }
/*      */       
/*  904 */       Element descPeer = null;
/*  905 */       Element titlePeer = null;
/*  906 */       if (elt.getLocalName().equals("title")) {
/*  907 */         if (data == Boolean.TRUE)
/*  908 */           titlePeer = elt; 
/*  909 */         descPeer = getPeerWithTag(parent, "http://www.w3.org/2000/svg", "desc");
/*      */       
/*      */       }
/*  912 */       else if (elt.getLocalName().equals("desc")) {
/*  913 */         if (data == Boolean.TRUE)
/*  914 */           descPeer = elt; 
/*  915 */         titlePeer = getPeerWithTag(parent, "http://www.w3.org/2000/svg", "title");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  920 */       String titleTip = null;
/*  921 */       if (titlePeer != null) {
/*  922 */         titlePeer.normalize();
/*  923 */         if (titlePeer.getFirstChild() != null) {
/*  924 */           titleTip = titlePeer.getFirstChild().getNodeValue();
/*      */         }
/*      */       } 
/*  927 */       String descTip = null;
/*  928 */       if (descPeer != null) {
/*  929 */         descPeer.normalize();
/*  930 */         if (descPeer.getFirstChild() != null) {
/*  931 */           descTip = descPeer.getFirstChild().getNodeValue();
/*      */         }
/*      */       } 
/*      */       
/*  935 */       if (titleTip != null && titleTip.length() != 0) {
/*  936 */         if (descTip != null && descTip.length() != 0) {
/*  937 */           toolTip = Messages.formatMessage("JSVGCanvas.CanvasUserAgent.ToolTip.titleAndDesc", new Object[] { toFormattedHTML(titleTip), toFormattedHTML(descTip) });
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  942 */           toolTip = Messages.formatMessage("JSVGCanvas.CanvasUserAgent.ToolTip.titleOnly", new Object[] { toFormattedHTML(titleTip) });
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  947 */       else if (descTip != null && descTip.length() != 0) {
/*  948 */         toolTip = Messages.formatMessage("JSVGCanvas.CanvasUserAgent.ToolTip.descOnly", new Object[] { toFormattedHTML(descTip) });
/*      */       }
/*      */       else {
/*      */         
/*  952 */         toolTip = null;
/*      */       } 
/*      */ 
/*      */       
/*  956 */       if (toolTip == null) {
/*  957 */         removeToolTip(parent);
/*      */         
/*      */         return;
/*      */       } 
/*  961 */       if (JSVGCanvas.this.lastTarget != parent) {
/*  962 */         setToolTip(parent, toolTip);
/*      */       } else {
/*      */         
/*  965 */         Object o = null;
/*  966 */         if (JSVGCanvas.this.toolTipMap != null) {
/*  967 */           o = JSVGCanvas.this.toolTipMap.get(parent);
/*  968 */           JSVGCanvas.this.toolTipMap.put(parent, toolTip);
/*      */         } 
/*      */         
/*  971 */         if (o != null) {
/*      */           
/*  973 */           EventQueue.invokeLater(new Runnable() {
/*      */                 public void run() {
/*  975 */                   JSVGCanvas.this.setToolTipText(toolTip);
/*  976 */                   MouseEvent e = new MouseEvent((Component)JSVGCanvas.this, 503, System.currentTimeMillis(), 0, JSVGCanvas.this.locationListener.getLastX(), JSVGCanvas.this.locationListener.getLastY(), 0, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*  985 */                   ToolTipManager.sharedInstance().mouseMoved(e);
/*      */                 }
/*      */               });
/*      */         } else {
/*  989 */           EventQueue.invokeLater(new JSVGCanvas.ToolTipRunnable(toolTip));
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toFormattedHTML(String str) {
/*  999 */       StringBuffer sb = new StringBuffer(str);
/* 1000 */       replace(sb, '&', "&amp;");
/* 1001 */       replace(sb, '<', "&lt;");
/* 1002 */       replace(sb, '>', "&gt;");
/* 1003 */       replace(sb, '"', "&quot;");
/*      */ 
/*      */ 
/*      */       
/* 1007 */       replace(sb, '\n', "<br>");
/* 1008 */       return sb.toString();
/*      */     }
/*      */     
/*      */     protected void replace(StringBuffer sb, char c, String r) {
/* 1012 */       String v = sb.toString();
/* 1013 */       int i = v.length();
/*      */       
/* 1015 */       while ((i = v.lastIndexOf(c, i - 1)) != -1) {
/* 1016 */         sb.deleteCharAt(i);
/* 1017 */         sb.insert(i, r);
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
/*      */     public Element getPeerWithTag(Element parent, String nameSpaceURI, String localName) {
/* 1029 */       Element p = parent;
/* 1030 */       if (p == null) {
/* 1031 */         return null;
/*      */       }
/*      */       
/* 1034 */       for (Node n = p.getFirstChild(); n != null; n = n.getNextSibling()) {
/* 1035 */         if (nameSpaceURI.equals(n.getNamespaceURI()))
/*      */         {
/*      */           
/* 1038 */           if (localName.equals(n.getLocalName()))
/*      */           {
/*      */             
/* 1041 */             if (n.getNodeType() == 1)
/* 1042 */               return (Element)n;  } 
/*      */         }
/*      */       } 
/* 1045 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasPeerWithTag(Element elt, String nameSpaceURI, String localName) {
/* 1056 */       return (getPeerWithTag(elt, nameSpaceURI, localName) != null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setToolTip(Element elt, String toolTip) {
/* 1063 */       if (JSVGCanvas.this.toolTipMap == null) {
/* 1064 */         JSVGCanvas.this.toolTipMap = new WeakHashMap<Object, Object>();
/*      */       }
/* 1066 */       if (JSVGCanvas.this.toolTipDocs == null) {
/* 1067 */         JSVGCanvas.this.toolTipDocs = new WeakHashMap<Object, Object>();
/*      */       }
/* 1069 */       SVGDocument doc = (SVGDocument)elt.getOwnerDocument();
/* 1070 */       if (JSVGCanvas.this.toolTipDocs.put(doc, JSVGCanvas.MAP_TOKEN) == null) {
/*      */         
/* 1072 */         NodeEventTarget root = (NodeEventTarget)doc.getRootElement();
/*      */         
/* 1074 */         root.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", JSVGCanvas.this.toolTipListener, false, null);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1079 */         root.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", JSVGCanvas.this.toolTipListener, false, null);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1085 */       JSVGCanvas.this.toolTipMap.put(elt, toolTip);
/*      */       
/* 1087 */       if (elt == JSVGCanvas.this.lastTarget)
/* 1088 */         EventQueue.invokeLater(new JSVGCanvas.ToolTipRunnable(toolTip)); 
/*      */     }
/*      */     
/*      */     public void removeToolTip(Element elt) {
/* 1092 */       if (JSVGCanvas.this.toolTipMap != null)
/* 1093 */         JSVGCanvas.this.toolTipMap.remove(elt); 
/* 1094 */       if (JSVGCanvas.this.lastTarget == elt) {
/* 1095 */         EventQueue.invokeLater(new JSVGCanvas.ToolTipRunnable(null));
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(String message) {
/* 1103 */       if (JSVGCanvas.this.svgUserAgent != null) {
/* 1104 */         super.displayError(message);
/*      */       } else {
/* 1106 */         JOptionPane pane = new JOptionPane(message, 0);
/*      */         
/* 1108 */         JDialog dialog = pane.createDialog((Component)JSVGCanvas.this, "ERROR");
/*      */         
/* 1110 */         dialog.setModal(false);
/* 1111 */         dialog.setVisible(true);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(Exception ex) {
/* 1119 */       if (JSVGCanvas.this.svgUserAgent != null) {
/* 1120 */         super.displayError(ex);
/*      */       } else {
/* 1122 */         JErrorPane pane = new JErrorPane(ex, 0);
/*      */         
/* 1124 */         JDialog dialog = pane.createDialog((Component)JSVGCanvas.this, "ERROR");
/* 1125 */         dialog.setModal(false);
/* 1126 */         dialog.setVisible(true);
/*      */       } 
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLastToolTipEvent(long t, EventTarget et) {
/* 1139 */     this.lastToolTipEventTimeStamp = t;
/* 1140 */     this.lastToolTipEventTarget = et;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean matchLastToolTipEvent(long t, EventTarget et) {
/* 1148 */     return (this.lastToolTipEventTimeStamp == t && this.lastToolTipEventTarget == et);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class LocationListener
/*      */     extends MouseMotionAdapter
/*      */   {
/* 1161 */     protected int lastX = 0; protected int lastY = 0;
/*      */ 
/*      */     
/*      */     public void mouseMoved(MouseEvent evt) {
/* 1165 */       this.lastX = evt.getX();
/* 1166 */       this.lastY = evt.getY();
/*      */     }
/*      */     
/*      */     public int getLastX() {
/* 1170 */       return this.lastX;
/*      */     }
/*      */     
/*      */     public int getLastY() {
/* 1174 */       return this.lastY;
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
/*      */   protected class ToolTipModifier
/*      */     implements EventListener
/*      */   {
/*      */     protected JSVGCanvas.CanvasUserAgent canvasUserAgent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1204 */       if (JSVGCanvas.this.matchLastToolTipEvent(evt.getTimeStamp(), evt.getTarget())) {
/*      */         return;
/*      */       }
/* 1207 */       JSVGCanvas.this.setLastToolTipEvent(evt.getTimeStamp(), evt.getTarget());
/* 1208 */       EventTarget prevLastTarget = JSVGCanvas.this.lastTarget;
/* 1209 */       if ("mouseover".equals(evt.getType())) {
/* 1210 */         JSVGCanvas.this.lastTarget = evt.getTarget();
/* 1211 */       } else if ("mouseout".equals(evt.getType())) {
/*      */ 
/*      */         
/* 1214 */         MouseEvent mouseEvt = (MouseEvent)evt;
/* 1215 */         JSVGCanvas.this.lastTarget = mouseEvt.getRelatedTarget();
/*      */       } 
/*      */       
/* 1218 */       if (JSVGCanvas.this.toolTipMap != null) {
/* 1219 */         Element e = (Element)JSVGCanvas.this.lastTarget;
/* 1220 */         Object o = null;
/* 1221 */         while (e != null) {
/*      */           
/* 1223 */           o = JSVGCanvas.this.toolTipMap.get(e);
/* 1224 */           if (o != null) {
/*      */             break;
/*      */           }
/* 1227 */           CSSStylableElement cSSStylableElement = CSSEngine.getParentCSSStylableElement(e);
/*      */         } 
/* 1229 */         String theToolTip = (String)o;
/* 1230 */         if (prevLastTarget != JSVGCanvas.this.lastTarget)
/* 1231 */           EventQueue.invokeLater(new JSVGCanvas.ToolTipRunnable(theToolTip)); 
/*      */       } 
/*      */     } }
/*      */   
/*      */   protected class ToolTipRunnable implements Runnable {
/*      */     String theToolTip;
/*      */     
/*      */     public ToolTipRunnable(String toolTip) {
/* 1239 */       this.theToolTip = toolTip;
/*      */     }
/*      */     
/*      */     public void run() {
/* 1243 */       JSVGCanvas.this.setToolTipText(this.theToolTip);
/*      */ 
/*      */       
/* 1246 */       if (this.theToolTip != null) {
/* 1247 */         MouseEvent e = new MouseEvent((Component)JSVGCanvas.this, 504, System.currentTimeMillis(), 0, JSVGCanvas.this.locationListener.getLastX(), JSVGCanvas.this.locationListener.getLastY(), 0, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1256 */         ToolTipManager.sharedInstance().mouseEntered(e);
/* 1257 */         e = new MouseEvent((Component)JSVGCanvas.this, 503, System.currentTimeMillis(), 0, JSVGCanvas.this.locationListener.getLastX(), JSVGCanvas.this.locationListener.getLastY(), 0, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1266 */         ToolTipManager.sharedInstance().mouseMoved(e);
/*      */       } else {
/* 1268 */         MouseEvent e = new MouseEvent((Component)JSVGCanvas.this, 503, System.currentTimeMillis(), 0, JSVGCanvas.this.locationListener.getLastX(), JSVGCanvas.this.locationListener.getLastY(), 0, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1277 */         ToolTipManager.sharedInstance().mouseMoved(e);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/JSVGCanvas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */