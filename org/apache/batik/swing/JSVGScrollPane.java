/*     */ package org.apache.batik.swing;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import javax.swing.BoundedRangeModel;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import org.apache.batik.bridge.UpdateManagerEvent;
/*     */ import org.apache.batik.bridge.UpdateManagerListener;
/*     */ import org.apache.batik.bridge.ViewBox;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
/*     */ import org.apache.batik.swing.gvt.GVTTreeRendererListener;
/*     */ import org.apache.batik.swing.gvt.JGVTComponentListener;
/*     */ import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
/*     */ import org.apache.batik.swing.svg.GVTTreeBuilderListener;
/*     */ import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
/*     */ import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
/*     */ import org.apache.batik.swing.svg.SVGDocumentLoaderListener;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.svg.SVGDocument;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JSVGScrollPane
/*     */   extends JPanel
/*     */ {
/*     */   protected JSVGCanvas canvas;
/*     */   protected JPanel horizontalPanel;
/*     */   protected JScrollBar vertical;
/*     */   protected JScrollBar horizontal;
/*     */   protected Component cornerBox;
/*     */   protected boolean scrollbarsAlwaysVisible = false;
/*     */   protected SBListener hsbListener;
/*     */   protected SBListener vsbListener;
/*  96 */   protected Rectangle2D viewBox = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean ignoreScrollChange = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public JSVGScrollPane(JSVGCanvas canvas) {
/* 105 */     this.canvas = canvas;
/* 106 */     canvas.setRecenterOnResize(false);
/*     */ 
/*     */     
/* 109 */     this.vertical = new JScrollBar(1, 0, 0, 0, 0);
/* 110 */     this.horizontal = new JScrollBar(0, 0, 0, 0, 0);
/*     */ 
/*     */     
/* 113 */     this.horizontalPanel = new JPanel(new BorderLayout());
/* 114 */     this.horizontalPanel.add(this.horizontal, "Center");
/* 115 */     this.cornerBox = Box.createRigidArea(new Dimension((this.vertical.getPreferredSize()).width, (this.horizontal.getPreferredSize()).height));
/*     */ 
/*     */     
/* 118 */     this.horizontalPanel.add(this.cornerBox, "East");
/*     */ 
/*     */     
/* 121 */     this.hsbListener = createScrollBarListener(false);
/* 122 */     this.horizontal.getModel().addChangeListener(this.hsbListener);
/*     */     
/* 124 */     this.vsbListener = createScrollBarListener(true);
/* 125 */     this.vertical.getModel().addChangeListener(this.vsbListener);
/*     */ 
/*     */     
/* 128 */     updateScrollbarState(false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     setLayout(new BorderLayout());
/* 134 */     add((Component)canvas, "Center");
/* 135 */     add(this.vertical, "East");
/* 136 */     add(this.horizontalPanel, "South");
/*     */ 
/*     */     
/* 139 */     canvas.addSVGDocumentLoaderListener(createLoadListener());
/*     */ 
/*     */     
/* 142 */     ScrollListener xlistener = createScrollListener();
/* 143 */     addComponentListener(xlistener);
/* 144 */     canvas.addGVTTreeRendererListener(xlistener);
/* 145 */     canvas.addJGVTComponentListener(xlistener);
/* 146 */     canvas.addGVTTreeBuilderListener(xlistener);
/* 147 */     canvas.addUpdateManagerListener(xlistener);
/*     */   }
/*     */   
/*     */   public boolean getScrollbarsAlwaysVisible() {
/* 151 */     return this.scrollbarsAlwaysVisible;
/*     */   }
/*     */   
/*     */   public void setScrollbarsAlwaysVisible(boolean vis) {
/* 155 */     this.scrollbarsAlwaysVisible = vis;
/* 156 */     resizeScrollBars();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SBListener createScrollBarListener(boolean isVertical) {
/* 164 */     return new SBListener(isVertical);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ScrollListener createScrollListener() {
/* 171 */     return new ScrollListener();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGDocumentLoaderListener createLoadListener() {
/* 179 */     return (SVGDocumentLoaderListener)new SVGScrollDocumentLoaderListener();
/*     */   }
/*     */   
/*     */   public JSVGCanvas getCanvas() {
/* 183 */     return this.canvas;
/*     */   }
/*     */   
/*     */   class SVGScrollDocumentLoaderListener
/*     */     extends SVGDocumentLoaderAdapter {
/*     */     public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
/* 189 */       NodeEventTarget root = (NodeEventTarget)e.getSVGDocument().getRootElement();
/*     */       
/* 191 */       root.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGZoom", new EventListener()
/*     */           {
/*     */             
/*     */             public void handleEvent(Event evt)
/*     */             {
/* 196 */               if (!(evt.getTarget() instanceof SVGSVGElement)) {
/*     */                 return;
/*     */               }
/*     */               
/* 200 */               SVGSVGElement svg = (SVGSVGElement)evt.getTarget();
/* 201 */               JSVGScrollPane.this.scaleChange(svg.getCurrentScale());
/*     */             }
/*     */           },  false, null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 214 */     this.viewBox = null;
/* 215 */     updateScrollbarState(false, false);
/* 216 */     revalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setScrollPosition() {
/* 225 */     checkAndSetViewBoxRect();
/* 226 */     if (this.viewBox == null)
/*     */       return; 
/* 228 */     AffineTransform crt = this.canvas.getRenderingTransform();
/* 229 */     AffineTransform vbt = this.canvas.getViewBoxTransform();
/* 230 */     if (crt == null) crt = new AffineTransform(); 
/* 231 */     if (vbt == null) vbt = new AffineTransform();
/*     */     
/* 233 */     Rectangle r2d = vbt.createTransformedShape(this.viewBox).getBounds();
/*     */     
/* 235 */     int tx = 0, ty = 0;
/* 236 */     if (r2d.x < 0) tx -= r2d.x; 
/* 237 */     if (r2d.y < 0) ty -= r2d.y;
/*     */     
/* 239 */     int deltaX = this.horizontal.getValue() - tx;
/* 240 */     int deltaY = this.vertical.getValue() - ty;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     crt.preConcatenate(AffineTransform.getTranslateInstance(-deltaX, -deltaY));
/*     */     
/* 248 */     this.canvas.setRenderingTransform(crt);
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
/*     */   protected class WheelListener
/*     */     implements MouseWheelListener
/*     */   {
/*     */     public void mouseWheelMoved(MouseWheelEvent e) {
/* 264 */       JScrollBar sb = JSVGScrollPane.this.vertical.isVisible() ? JSVGScrollPane.this.vertical : JSVGScrollPane.this.horizontal;
/*     */ 
/*     */       
/* 267 */       if (e.getScrollType() == 0) {
/* 268 */         int amt = e.getUnitsToScroll() * sb.getUnitIncrement();
/* 269 */         sb.setValue(sb.getValue() + amt);
/* 270 */       } else if (e.getScrollType() == 1) {
/* 271 */         int amt = e.getWheelRotation() * sb.getBlockIncrement();
/* 272 */         sb.setValue(sb.getValue() + amt);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class SBListener
/*     */     implements ChangeListener
/*     */   {
/*     */     protected boolean inDrag = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int startValue;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isVertical;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SBListener(boolean vertical) {
/* 302 */       this.isVertical = vertical;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void stateChanged(ChangeEvent e) {
/* 309 */       if (JSVGScrollPane.this.ignoreScrollChange)
/*     */         return; 
/* 311 */       Object src = e.getSource();
/* 312 */       if (!(src instanceof BoundedRangeModel)) {
/*     */         return;
/*     */       }
/* 315 */       int val = this.isVertical ? JSVGScrollPane.this.vertical.getValue() : JSVGScrollPane.this.horizontal.getValue();
/*     */ 
/*     */       
/* 318 */       BoundedRangeModel brm = (BoundedRangeModel)src;
/* 319 */       if (brm.getValueIsAdjusting()) {
/* 320 */         if (!this.inDrag) {
/* 321 */           this.inDrag = true;
/* 322 */           this.startValue = val;
/*     */         } else {
/*     */           AffineTransform at;
/* 325 */           if (this.isVertical) {
/* 326 */             at = AffineTransform.getTranslateInstance(0.0D, (this.startValue - val));
/*     */           } else {
/*     */             
/* 329 */             at = AffineTransform.getTranslateInstance((this.startValue - val), 0.0D);
/*     */           } 
/*     */           
/* 332 */           JSVGScrollPane.this.canvas.setPaintingTransform(at);
/*     */         } 
/*     */       } else {
/* 335 */         if (this.inDrag) {
/* 336 */           this.inDrag = false;
/* 337 */           if (val == this.startValue) {
/* 338 */             JSVGScrollPane.this.canvas.setPaintingTransform(new AffineTransform());
/*     */             return;
/*     */           } 
/*     */         } 
/* 342 */         JSVGScrollPane.this.setScrollPosition();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ScrollListener
/*     */     extends ComponentAdapter
/*     */     implements UpdateManagerListener, GVTTreeRendererListener, JGVTComponentListener, GVTTreeBuilderListener
/*     */   {
/*     */     protected boolean isReady = false;
/*     */ 
/*     */     
/*     */     public void componentTransformChanged(ComponentEvent evt) {
/* 357 */       if (this.isReady) {
/* 358 */         JSVGScrollPane.this.resizeScrollBars();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void componentResized(ComponentEvent evt) {
/* 364 */       if (this.isReady) {
/* 365 */         JSVGScrollPane.this.resizeScrollBars();
/*     */       }
/*     */     }
/*     */     
/*     */     public void gvtBuildStarted(GVTTreeBuilderEvent e) {
/* 370 */       this.isReady = false;
/*     */       
/* 372 */       JSVGScrollPane.this.updateScrollbarState(false, false);
/*     */     }
/*     */     
/*     */     public void gvtBuildCompleted(GVTTreeBuilderEvent e) {
/* 376 */       this.isReady = true;
/* 377 */       JSVGScrollPane.this.viewBox = null;
/*     */     }
/*     */     
/*     */     public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
/* 381 */       if (JSVGScrollPane.this.viewBox == null) {
/* 382 */         JSVGScrollPane.this.resizeScrollBars();
/*     */         
/*     */         return;
/*     */       } 
/* 386 */       Rectangle2D newview = JSVGScrollPane.this.getViewBoxRect();
/* 387 */       if (newview == null)
/*     */         return; 
/* 389 */       if (newview.getX() != JSVGScrollPane.this.viewBox.getX() || newview.getY() != JSVGScrollPane.this.viewBox.getY() || newview.getWidth() != JSVGScrollPane.this.viewBox.getWidth() || newview.getHeight() != JSVGScrollPane.this.viewBox.getHeight()) {
/*     */ 
/*     */ 
/*     */         
/* 393 */         JSVGScrollPane.this.viewBox = newview;
/* 394 */         JSVGScrollPane.this.resizeScrollBars();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void updateCompleted(UpdateManagerEvent e) {
/* 399 */       if (JSVGScrollPane.this.viewBox == null) {
/* 400 */         JSVGScrollPane.this.resizeScrollBars();
/*     */         
/*     */         return;
/*     */       } 
/* 404 */       Rectangle2D newview = JSVGScrollPane.this.getViewBoxRect();
/* 405 */       if (newview == null)
/*     */         return; 
/* 407 */       if (newview.getX() != JSVGScrollPane.this.viewBox.getX() || newview.getY() != JSVGScrollPane.this.viewBox.getY() || newview.getWidth() != JSVGScrollPane.this.viewBox.getWidth() || newview.getHeight() != JSVGScrollPane.this.viewBox.getHeight()) {
/*     */ 
/*     */ 
/*     */         
/* 411 */         JSVGScrollPane.this.viewBox = newview;
/* 412 */         JSVGScrollPane.this.resizeScrollBars();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void gvtBuildCancelled(GVTTreeBuilderEvent e) {}
/*     */ 
/*     */     
/*     */     public void gvtBuildFailed(GVTTreeBuilderEvent e) {}
/*     */     
/*     */     public void gvtRenderingPrepare(GVTTreeRendererEvent e) {}
/*     */     
/*     */     public void gvtRenderingStarted(GVTTreeRendererEvent e) {}
/*     */     
/*     */     public void gvtRenderingCancelled(GVTTreeRendererEvent e) {}
/*     */     
/*     */     public void gvtRenderingFailed(GVTTreeRendererEvent e) {}
/*     */     
/*     */     public void managerStarted(UpdateManagerEvent e) {}
/*     */     
/*     */     public void managerSuspended(UpdateManagerEvent e) {}
/*     */     
/*     */     public void managerResumed(UpdateManagerEvent e) {}
/*     */     
/*     */     public void managerStopped(UpdateManagerEvent e) {}
/*     */     
/*     */     public void updateStarted(UpdateManagerEvent e) {}
/*     */     
/*     */     public void updateFailed(UpdateManagerEvent e) {}
/*     */   }
/*     */   
/*     */   protected void resizeScrollBars() {
/* 444 */     this.ignoreScrollChange = true;
/*     */     
/* 446 */     checkAndSetViewBoxRect();
/* 447 */     if (this.viewBox == null)
/*     */       return; 
/* 449 */     AffineTransform vbt = this.canvas.getViewBoxTransform();
/* 450 */     if (vbt == null) vbt = new AffineTransform();
/*     */     
/* 452 */     Rectangle r2d = vbt.createTransformedShape(this.viewBox).getBounds();
/*     */ 
/*     */ 
/*     */     
/* 456 */     int maxW = r2d.width;
/* 457 */     int maxH = r2d.height;
/* 458 */     int tx = 0, ty = 0;
/* 459 */     if (r2d.x > 0) { maxW += r2d.x; }
/* 460 */     else { tx -= r2d.x; }
/* 461 */      if (r2d.y > 0) { maxH += r2d.y; }
/* 462 */     else { ty -= r2d.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 469 */     Dimension vpSize = updateScrollbarVisibility(tx, ty, maxW, maxH);
/*     */ 
/*     */     
/* 472 */     this.vertical.setValues(ty, vpSize.height, 0, maxH);
/* 473 */     this.horizontal.setValues(tx, vpSize.width, 0, maxW);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 478 */     this.vertical.setBlockIncrement((int)(0.9F * vpSize.height));
/* 479 */     this.horizontal.setBlockIncrement((int)(0.9F * vpSize.width));
/*     */ 
/*     */ 
/*     */     
/* 483 */     this.vertical.setUnitIncrement((int)(0.2F * vpSize.height));
/* 484 */     this.horizontal.setUnitIncrement((int)(0.2F * vpSize.width));
/*     */     
/* 486 */     doLayout();
/* 487 */     this.horizontalPanel.doLayout();
/* 488 */     this.horizontal.doLayout();
/* 489 */     this.vertical.doLayout();
/*     */     
/* 491 */     this.ignoreScrollChange = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Dimension updateScrollbarVisibility(int tx, int ty, int maxW, int maxH) {
/*     */     boolean hNeeded, vNeeded;
/* 503 */     Dimension vpSize = this.canvas.getSize();
/*     */ 
/*     */     
/* 506 */     int maxVPW = vpSize.width, minVPW = vpSize.width;
/* 507 */     int maxVPH = vpSize.height, minVPH = vpSize.height;
/*     */     
/* 509 */     if (this.vertical.isVisible()) {
/* 510 */       maxVPW += (this.vertical.getPreferredSize()).width;
/*     */     } else {
/* 512 */       minVPW -= (this.vertical.getPreferredSize()).width;
/*     */     } 
/* 514 */     if (this.horizontalPanel.isVisible()) {
/* 515 */       maxVPH += (this.horizontal.getPreferredSize()).height;
/*     */     } else {
/* 517 */       minVPH -= (this.horizontal.getPreferredSize()).height;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 526 */     Dimension ret = new Dimension();
/*     */     
/* 528 */     if (this.scrollbarsAlwaysVisible) {
/* 529 */       hNeeded = (maxW > minVPW);
/* 530 */       vNeeded = (maxH > minVPH);
/* 531 */       ret.width = minVPW;
/* 532 */       ret.height = minVPH;
/*     */     } else {
/* 534 */       hNeeded = (maxW > maxVPW || tx != 0);
/* 535 */       vNeeded = (maxH > maxVPH || ty != 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 543 */       if (vNeeded && !hNeeded) { hNeeded = (maxW > minVPW); }
/* 544 */       else if (hNeeded && !vNeeded) { vNeeded = (maxH > minVPH); }
/*     */       
/* 546 */       ret.width = hNeeded ? minVPW : maxVPW;
/* 547 */       ret.height = vNeeded ? minVPH : maxVPH;
/*     */     } 
/*     */     
/* 550 */     updateScrollbarState(hNeeded, vNeeded);
/*     */ 
/*     */     
/* 553 */     return ret;
/*     */   }
/*     */   
/*     */   protected void updateScrollbarState(boolean hNeeded, boolean vNeeded) {
/* 557 */     this.horizontal.setEnabled(hNeeded);
/* 558 */     this.vertical.setEnabled(vNeeded);
/*     */     
/* 560 */     if (this.scrollbarsAlwaysVisible) {
/* 561 */       this.horizontalPanel.setVisible(true);
/* 562 */       this.vertical.setVisible(true);
/* 563 */       this.cornerBox.setVisible(true);
/*     */     } else {
/* 565 */       this.horizontalPanel.setVisible(hNeeded);
/* 566 */       this.vertical.setVisible(vNeeded);
/* 567 */       this.cornerBox.setVisible((hNeeded && vNeeded));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkAndSetViewBoxRect() {
/* 577 */     if (this.viewBox != null)
/*     */       return; 
/* 579 */     Rectangle2D newview = getViewBoxRect();
/* 580 */     if (newview == null)
/*     */       return; 
/* 582 */     this.viewBox = newview;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle2D getViewBoxRect() {
/* 591 */     SVGDocument doc = this.canvas.getSVGDocument();
/* 592 */     if (doc == null) return null; 
/* 593 */     SVGSVGElement el = doc.getRootElement();
/* 594 */     if (el == null) return null;
/*     */     
/* 596 */     String viewBoxStr = el.getAttributeNS(null, "viewBox");
/*     */     
/* 598 */     if (viewBoxStr.length() != 0) {
/* 599 */       float[] rect = ViewBox.parseViewBoxAttribute((Element)el, viewBoxStr, null);
/* 600 */       return new Rectangle2D.Float(rect[0], rect[1], rect[2], rect[3]);
/*     */     } 
/*     */     
/* 603 */     GraphicsNode gn = this.canvas.getGraphicsNode();
/* 604 */     if (gn == null) return null;
/*     */     
/* 606 */     Rectangle2D bounds = gn.getBounds();
/* 607 */     if (bounds == null) return null;
/*     */     
/* 609 */     return (Rectangle2D)bounds.clone();
/*     */   }
/*     */   
/*     */   public void scaleChange(float scale) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/JSVGScrollPane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */