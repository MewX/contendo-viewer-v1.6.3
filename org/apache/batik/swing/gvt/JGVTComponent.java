/*      */ package org.apache.batik.swing.gvt;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.datatransfer.Clipboard;
/*      */ import java.awt.datatransfer.StringSelection;
/*      */ import java.awt.event.ComponentAdapter;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.InputEvent;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.MouseMotionListener;
/*      */ import java.awt.event.MouseWheelEvent;
/*      */ import java.awt.event.MouseWheelListener;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.NoninvertibleTransformException;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.text.CharacterIterator;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import javax.swing.JComponent;
/*      */ import org.apache.batik.bridge.Mark;
/*      */ import org.apache.batik.gvt.GraphicsNode;
/*      */ import org.apache.batik.gvt.event.AWTEventDispatcher;
/*      */ import org.apache.batik.gvt.event.EventDispatcher;
/*      */ import org.apache.batik.gvt.event.SelectionAdapter;
/*      */ import org.apache.batik.gvt.event.SelectionEvent;
/*      */ import org.apache.batik.gvt.event.SelectionListener;
/*      */ import org.apache.batik.gvt.renderer.ConcreteImageRendererFactory;
/*      */ import org.apache.batik.gvt.renderer.ImageRenderer;
/*      */ import org.apache.batik.gvt.renderer.ImageRendererFactory;
/*      */ import org.apache.batik.util.HaltingThread;
/*      */ import org.apache.batik.util.Platform;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JGVTComponent
/*      */   extends JComponent
/*      */ {
/*      */   protected Listener listener;
/*      */   protected GVTTreeRenderer gvtTreeRenderer;
/*      */   protected GraphicsNode gvtRoot;
/*   92 */   protected ImageRendererFactory rendererFactory = (ImageRendererFactory)new ConcreteImageRendererFactory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ImageRenderer renderer;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  103 */   protected List gvtTreeRendererListeners = Collections.synchronizedList(new LinkedList());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean needRender;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean progressivePaint;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected HaltingThread progressivePaintThread;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BufferedImage image;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  129 */   protected AffineTransform initialTransform = new AffineTransform();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   protected AffineTransform renderingTransform = new AffineTransform();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AffineTransform paintingTransform;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  144 */   protected List interactors = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Interactor interactor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  154 */   protected List overlays = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  159 */   protected List jgvtListeners = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AWTEventDispatcher eventDispatcher;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TextSelectionManager textSelectionManager;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doubleBufferedRendering;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean eventsEnabled;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean selectableText;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean useUnixTextSelection = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean suspendInteractions;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean disableInteractions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JGVTComponent() {
/*  209 */     this(false, false);
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
/*      */   public JGVTComponent(boolean eventsEnabled, boolean selectableText) {
/*  221 */     setBackground(Color.white);
/*      */ 
/*      */     
/*  224 */     this.eventsEnabled = eventsEnabled;
/*  225 */     this.selectableText = selectableText;
/*      */     
/*  227 */     this.listener = createListener();
/*      */     
/*  229 */     addAWTListeners();
/*      */     
/*  231 */     addGVTTreeRendererListener(this.listener);
/*      */     
/*  233 */     addComponentListener(new ComponentAdapter() {
/*      */           public void componentResized(ComponentEvent e) {
/*  235 */             if (JGVTComponent.this.updateRenderingTransform()) {
/*  236 */               JGVTComponent.this.scheduleGVTRendering();
/*      */             }
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Listener createListener() {
/*  246 */     return new Listener();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addAWTListeners() {
/*  253 */     addKeyListener(this.listener);
/*  254 */     addMouseListener(this.listener);
/*  255 */     addMouseMotionListener(this.listener);
/*  256 */     addMouseWheelListener(this.listener);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDisableInteractions(boolean b) {
/*  264 */     this.disableInteractions = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDisableInteractions() {
/*  272 */     return this.disableInteractions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseUnixTextSelection(boolean b) {
/*  283 */     this.useUnixTextSelection = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getUseUnixTextSelection(boolean b) {
/*  291 */     this.useUnixTextSelection = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getInteractors() {
/*  298 */     return this.interactors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getOverlays() {
/*  305 */     return this.overlays;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BufferedImage getOffScreen() {
/*  312 */     return this.image;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addJGVTComponentListener(JGVTComponentListener listener) {
/*  317 */     if (this.jgvtListeners == null)
/*  318 */       this.jgvtListeners = new LinkedList(); 
/*  319 */     this.jgvtListeners.add(listener);
/*      */   }
/*      */   
/*      */   public void removeJGVTComponentListener(JGVTComponentListener listener) {
/*  323 */     if (this.jgvtListeners == null)
/*  324 */       return;  this.jgvtListeners.remove(listener);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetRenderingTransform() {
/*  331 */     setRenderingTransform(this.initialTransform);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stopProcessing() {
/*  338 */     if (this.gvtTreeRenderer != null) {
/*  339 */       this.needRender = false;
/*  340 */       this.gvtTreeRenderer.halt();
/*  341 */       haltProgressivePaintThread();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GraphicsNode getGraphicsNode() {
/*  349 */     return this.gvtRoot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGraphicsNode(GraphicsNode gn) {
/*  356 */     setGraphicsNode(gn, true);
/*  357 */     this.initialTransform = new AffineTransform();
/*  358 */     updateRenderingTransform();
/*  359 */     setRenderingTransform(this.initialTransform, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setGraphicsNode(GraphicsNode gn, boolean createDispatcher) {
/*  366 */     this.gvtRoot = gn;
/*  367 */     if (gn != null && createDispatcher) {
/*  368 */       initializeEventHandling();
/*      */     }
/*  370 */     if (this.eventDispatcher != null) {
/*  371 */       this.eventDispatcher.setRootNode(gn);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initializeEventHandling() {
/*  379 */     if (this.eventsEnabled) {
/*  380 */       this.eventDispatcher = createEventDispatcher();
/*  381 */       if (this.selectableText) {
/*  382 */         this.textSelectionManager = createTextSelectionManager((EventDispatcher)this.eventDispatcher);
/*      */         
/*  384 */         this.textSelectionManager.addSelectionListener((SelectionListener)new UnixTextSelectionListener());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected AWTEventDispatcher createEventDispatcher() {
/*  391 */     return new AWTEventDispatcher();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TextSelectionManager createTextSelectionManager(EventDispatcher ed) {
/*  401 */     return new TextSelectionManager(this, ed);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextSelectionManager getTextSelectionManager() {
/*  410 */     return this.textSelectionManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSelectionOverlayColor(Color color) {
/*  419 */     if (this.textSelectionManager != null) {
/*  420 */       this.textSelectionManager.setSelectionOverlayColor(color);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Color getSelectionOverlayColor() {
/*  428 */     if (this.textSelectionManager != null) {
/*  429 */       return this.textSelectionManager.getSelectionOverlayColor();
/*      */     }
/*  431 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSelectionOverlayStrokeColor(Color color) {
/*  442 */     if (this.textSelectionManager != null) {
/*  443 */       this.textSelectionManager.setSelectionOverlayStrokeColor(color);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Color getSelectionOverlayStrokeColor() {
/*  451 */     if (this.textSelectionManager != null) {
/*  452 */       return this.textSelectionManager.getSelectionOverlayStrokeColor();
/*      */     }
/*  454 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSelectionOverlayXORMode(boolean state) {
/*  465 */     if (this.textSelectionManager != null) {
/*  466 */       this.textSelectionManager.setSelectionOverlayXORMode(state);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSelectionOverlayXORMode() {
/*  475 */     if (this.textSelectionManager != null) {
/*  476 */       return this.textSelectionManager.isSelectionOverlayXORMode();
/*      */     }
/*  478 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void select(Mark start, Mark end) {
/*  489 */     if (this.textSelectionManager != null) {
/*  490 */       this.textSelectionManager.setSelection(start, end);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deselectAll() {
/*  498 */     if (this.textSelectionManager != null) {
/*  499 */       this.textSelectionManager.clearSelection();
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
/*      */   public void setProgressivePaint(boolean b) {
/*  511 */     if (this.progressivePaint != b) {
/*  512 */       this.progressivePaint = b;
/*  513 */       haltProgressivePaintThread();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getProgressivePaint() {
/*  521 */     return this.progressivePaint;
/*      */   }
/*      */   
/*      */   public Rectangle getRenderRect() {
/*  525 */     Dimension d = getSize();
/*  526 */     return new Rectangle(0, 0, d.width, d.height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void immediateRepaint() {
/*  533 */     if (EventQueue.isDispatchThread()) {
/*  534 */       Rectangle visRect = getRenderRect();
/*  535 */       if (this.doubleBufferedRendering) {
/*  536 */         repaint(visRect.x, visRect.y, visRect.width, visRect.height);
/*      */       } else {
/*      */         
/*  539 */         paintImmediately(visRect.x, visRect.y, visRect.width, visRect.height);
/*      */       } 
/*      */     } else {
/*      */       try {
/*  543 */         EventQueue.invokeAndWait(new Runnable() {
/*      */               public void run() {
/*  545 */                 Rectangle visRect = JGVTComponent.this.getRenderRect();
/*  546 */                 if (JGVTComponent.this.doubleBufferedRendering) {
/*  547 */                   JGVTComponent.this.repaint(visRect.x, visRect.y, visRect.width, visRect.height);
/*      */                 } else {
/*      */                   
/*  550 */                   JGVTComponent.this.paintImmediately(visRect.x, visRect.y, visRect.width, visRect.height);
/*      */                 } 
/*      */               }
/*      */             });
/*  554 */       } catch (Exception exception) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void paintComponent(Graphics g) {
/*  563 */     super.paintComponent(g);
/*      */     
/*  565 */     Graphics2D g2d = (Graphics2D)g;
/*      */     
/*  567 */     Rectangle visRect = getRenderRect();
/*  568 */     g2d.setComposite(AlphaComposite.SrcOver);
/*  569 */     g2d.setPaint(getBackground());
/*  570 */     g2d.fillRect(visRect.x, visRect.y, visRect.width, visRect.height);
/*      */ 
/*      */     
/*  573 */     if (this.image != null) {
/*  574 */       if (this.paintingTransform != null) {
/*  575 */         g2d.transform(this.paintingTransform);
/*      */       }
/*  577 */       g2d.drawRenderedImage(this.image, (AffineTransform)null);
/*  578 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*      */       
/*  580 */       for (Object overlay : this.overlays) {
/*  581 */         ((Overlay)overlay).paint(g);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPaintingTransform(AffineTransform at) {
/*  592 */     this.paintingTransform = at;
/*  593 */     immediateRepaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AffineTransform getPaintingTransform() {
/*  600 */     return this.paintingTransform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRenderingTransform(AffineTransform at) {
/*  608 */     setRenderingTransform(at, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRenderingTransform(AffineTransform at, boolean performRedraw) {
/*  613 */     this.renderingTransform = new AffineTransform(at);
/*  614 */     this.suspendInteractions = true;
/*  615 */     if (this.eventDispatcher != null) {
/*      */       try {
/*  617 */         this.eventDispatcher.setBaseTransform(this.renderingTransform.createInverse());
/*      */       }
/*  619 */       catch (NoninvertibleTransformException e) {
/*  620 */         handleException(e);
/*      */       } 
/*      */     }
/*  623 */     if (this.jgvtListeners != null) {
/*  624 */       Iterator<JGVTComponentListener> iter = this.jgvtListeners.iterator();
/*  625 */       ComponentEvent ce = new ComponentEvent(this, 1337);
/*      */       
/*  627 */       while (iter.hasNext()) {
/*  628 */         JGVTComponentListener l = iter.next();
/*  629 */         l.componentTransformChanged(ce);
/*      */       } 
/*      */     } 
/*      */     
/*  633 */     if (performRedraw) {
/*  634 */       scheduleGVTRendering();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public AffineTransform getInitialTransform() {
/*  641 */     return new AffineTransform(this.initialTransform);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AffineTransform getRenderingTransform() {
/*  648 */     return new AffineTransform(this.renderingTransform);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoubleBufferedRendering(boolean b) {
/*  657 */     this.doubleBufferedRendering = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDoubleBufferedRendering() {
/*  665 */     return this.doubleBufferedRendering;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addGVTTreeRendererListener(GVTTreeRendererListener l) {
/*  672 */     this.gvtTreeRendererListeners.add(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeGVTTreeRendererListener(GVTTreeRendererListener l) {
/*  679 */     this.gvtTreeRendererListeners.remove(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flush() {
/*  687 */     this.renderer.flush();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flush(Rectangle r) {
/*  695 */     this.renderer.flush(r);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ImageRenderer createImageRenderer() {
/*  702 */     return this.rendererFactory.createStaticImageRenderer();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void renderGVTTree() {
/*      */     AffineTransform inv;
/*  709 */     Rectangle visRect = getRenderRect();
/*  710 */     if (this.gvtRoot == null || visRect.width <= 0 || visRect.height <= 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  715 */     if (this.renderer == null || this.renderer.getTree() != this.gvtRoot) {
/*  716 */       this.renderer = createImageRenderer();
/*  717 */       this.renderer.setTree(this.gvtRoot);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  723 */       inv = this.renderingTransform.createInverse();
/*  724 */     } catch (NoninvertibleTransformException e) {
/*  725 */       throw new IllegalStateException("NoninvertibleTransformEx:" + e.getMessage());
/*      */     } 
/*  727 */     Shape s = inv.createTransformedShape(visRect);
/*      */ 
/*      */     
/*  730 */     this.gvtTreeRenderer = new GVTTreeRenderer(this.renderer, this.renderingTransform, this.doubleBufferedRendering, s, visRect.width, visRect.height);
/*      */ 
/*      */     
/*  733 */     this.gvtTreeRenderer.setPriority(1);
/*      */     
/*  735 */     for (Object gvtTreeRendererListener : this.gvtTreeRendererListeners) {
/*  736 */       this.gvtTreeRenderer.addGVTTreeRendererListener((GVTTreeRendererListener)gvtTreeRendererListener);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  742 */     if (this.eventDispatcher != null) {
/*  743 */       this.eventDispatcher.setEventDispatchEnabled(false);
/*      */     }
/*      */     
/*  746 */     this.gvtTreeRenderer.start();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean computeRenderingTransform() {
/*  754 */     this.initialTransform = new AffineTransform();
/*  755 */     if (!this.initialTransform.equals(this.renderingTransform)) {
/*  756 */       setRenderingTransform(this.initialTransform, false);
/*  757 */       return true;
/*      */     } 
/*  759 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean updateRenderingTransform() {
/*  768 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleException(Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void releaseRenderingReferences() {
/*  782 */     this.eventDispatcher = null;
/*  783 */     if (this.textSelectionManager != null) {
/*  784 */       this.overlays.remove(this.textSelectionManager.getSelectionOverlay());
/*  785 */       this.textSelectionManager = null;
/*      */     } 
/*  787 */     this.renderer = null;
/*  788 */     this.image = null;
/*  789 */     this.gvtRoot = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void scheduleGVTRendering() {
/*  796 */     if (this.gvtTreeRenderer != null) {
/*  797 */       this.needRender = true;
/*  798 */       this.gvtTreeRenderer.halt();
/*      */     } else {
/*  800 */       renderGVTTree();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void haltProgressivePaintThread() {
/*  805 */     if (this.progressivePaintThread != null) {
/*  806 */       this.progressivePaintThread.halt();
/*  807 */       this.progressivePaintThread = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected class Listener
/*      */     implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, GVTTreeRendererListener
/*      */   {
/*      */     boolean checkClick = false;
/*      */     
/*      */     boolean hadDrag = false;
/*      */     
/*      */     int startX;
/*      */     
/*      */     int startY;
/*      */     long startTime;
/*      */     long fakeClickTime;
/*  824 */     int MAX_DISP = 16;
/*  825 */     long CLICK_TIME = 200L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtRenderingPrepare(GVTTreeRendererEvent e) {
/*  839 */       JGVTComponent.this.suspendInteractions = true;
/*  840 */       if (!JGVTComponent.this.progressivePaint && !JGVTComponent.this.doubleBufferedRendering) {
/*  841 */         JGVTComponent.this.image = null;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtRenderingStarted(GVTTreeRendererEvent e) {
/*  849 */       if (JGVTComponent.this.progressivePaint && !JGVTComponent.this.doubleBufferedRendering) {
/*  850 */         JGVTComponent.this.image = e.getImage();
/*  851 */         JGVTComponent.this.progressivePaintThread = new HaltingThread() {
/*      */             public void run() {
/*  853 */               final null thisThread = this;
/*      */               
/*  855 */               try { while (!hasBeenHalted()) {
/*  856 */                   EventQueue.invokeLater(new Runnable() {
/*      */                         public void run() {
/*  858 */                           if (JGVTComponent.this.progressivePaintThread == thisThread) {
/*      */                             
/*  860 */                             Rectangle vRect = JGVTComponent.this.getRenderRect();
/*  861 */                             JGVTComponent.this.repaint(vRect.x, vRect.y, vRect.width, vRect.height);
/*      */                           } 
/*      */                         }
/*      */                       });
/*      */                   
/*  866 */                   sleep(200L);
/*      */                 }  }
/*  868 */               catch (InterruptedException interruptedException) {  }
/*  869 */               catch (ThreadDeath td)
/*  870 */               { throw td; }
/*  871 */               catch (Throwable t)
/*  872 */               { t.printStackTrace(); }
/*      */             
/*      */             }
/*      */           };
/*  876 */         JGVTComponent.this.progressivePaintThread.setPriority(2);
/*  877 */         JGVTComponent.this.progressivePaintThread.start();
/*      */       } 
/*  879 */       if (!JGVTComponent.this.doubleBufferedRendering) {
/*  880 */         JGVTComponent.this.paintingTransform = null;
/*  881 */         JGVTComponent.this.suspendInteractions = false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
/*  889 */       JGVTComponent.this.haltProgressivePaintThread();
/*      */       
/*  891 */       if (JGVTComponent.this.doubleBufferedRendering) {
/*  892 */         JGVTComponent.this.paintingTransform = null;
/*  893 */         JGVTComponent.this.suspendInteractions = false;
/*      */       } 
/*      */       
/*  896 */       JGVTComponent.this.gvtTreeRenderer = null;
/*  897 */       if (JGVTComponent.this.needRender) {
/*  898 */         JGVTComponent.this.renderGVTTree();
/*  899 */         JGVTComponent.this.needRender = false;
/*      */       } else {
/*  901 */         JGVTComponent.this.image = e.getImage();
/*  902 */         JGVTComponent.this.immediateRepaint();
/*      */       } 
/*  904 */       if (JGVTComponent.this.eventDispatcher != null) {
/*  905 */         JGVTComponent.this.eventDispatcher.setEventDispatchEnabled(true);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtRenderingCancelled(GVTTreeRendererEvent e) {
/*  913 */       renderingStopped();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gvtRenderingFailed(GVTTreeRendererEvent e) {
/*  920 */       renderingStopped();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void renderingStopped() {
/*  928 */       JGVTComponent.this.haltProgressivePaintThread();
/*      */       
/*  930 */       if (JGVTComponent.this.doubleBufferedRendering) {
/*  931 */         JGVTComponent.this.suspendInteractions = false;
/*      */       }
/*      */       
/*  934 */       JGVTComponent.this.gvtTreeRenderer = null;
/*  935 */       if (JGVTComponent.this.needRender) {
/*  936 */         JGVTComponent.this.renderGVTTree();
/*  937 */         JGVTComponent.this.needRender = false;
/*      */       } else {
/*  939 */         JGVTComponent.this.immediateRepaint();
/*      */       } 
/*      */       
/*  942 */       if (JGVTComponent.this.eventDispatcher != null) {
/*  943 */         JGVTComponent.this.eventDispatcher.setEventDispatchEnabled(true);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void keyTyped(KeyEvent e) {
/*  954 */       selectInteractor(e);
/*  955 */       if (JGVTComponent.this.interactor != null) {
/*  956 */         JGVTComponent.this.interactor.keyTyped(e);
/*  957 */         deselectInteractor();
/*  958 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/*  959 */         dispatchKeyTyped(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchKeyTyped(KeyEvent e) {
/*  967 */       JGVTComponent.this.eventDispatcher.keyTyped(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void keyPressed(KeyEvent e) {
/*  974 */       selectInteractor(e);
/*  975 */       if (JGVTComponent.this.interactor != null) {
/*  976 */         JGVTComponent.this.interactor.keyPressed(e);
/*  977 */         deselectInteractor();
/*  978 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/*  979 */         dispatchKeyPressed(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchKeyPressed(KeyEvent e) {
/*  987 */       JGVTComponent.this.eventDispatcher.keyPressed(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void keyReleased(KeyEvent e) {
/*  994 */       selectInteractor(e);
/*  995 */       if (JGVTComponent.this.interactor != null) {
/*  996 */         JGVTComponent.this.interactor.keyReleased(e);
/*  997 */         deselectInteractor();
/*  998 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/*  999 */         dispatchKeyReleased(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchKeyReleased(KeyEvent e) {
/* 1007 */       JGVTComponent.this.eventDispatcher.keyReleased(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseClicked(MouseEvent e) {
/* 1018 */       if (this.fakeClickTime != e.getWhen())
/* 1019 */         handleMouseClicked(e); 
/*      */     }
/*      */     
/*      */     public void handleMouseClicked(MouseEvent e) {
/* 1023 */       selectInteractor(e);
/* 1024 */       if (JGVTComponent.this.interactor != null) {
/* 1025 */         JGVTComponent.this.interactor.mouseClicked(e);
/* 1026 */         deselectInteractor();
/* 1027 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/* 1028 */         dispatchMouseClicked(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseClicked(MouseEvent e) {
/* 1036 */       JGVTComponent.this.eventDispatcher.mouseClicked(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent e) {
/* 1043 */       this.startX = e.getX();
/* 1044 */       this.startY = e.getY();
/* 1045 */       this.startTime = e.getWhen();
/*      */       
/* 1047 */       this.checkClick = true;
/*      */       
/* 1049 */       selectInteractor(e);
/* 1050 */       if (JGVTComponent.this.interactor != null) {
/* 1051 */         JGVTComponent.this.interactor.mousePressed(e);
/* 1052 */         deselectInteractor();
/* 1053 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/* 1054 */         dispatchMousePressed(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMousePressed(MouseEvent e) {
/* 1062 */       JGVTComponent.this.eventDispatcher.mousePressed(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseReleased(MouseEvent e) {
/* 1069 */       if (this.checkClick && this.hadDrag) {
/* 1070 */         int dx = this.startX - e.getX();
/* 1071 */         int dy = this.startY - e.getY();
/* 1072 */         long cTime = e.getWhen();
/* 1073 */         if (dx * dx + dy * dy < this.MAX_DISP && cTime - this.startTime < this.CLICK_TIME) {
/*      */ 
/*      */ 
/*      */           
/* 1077 */           MouseEvent click = new MouseEvent(e.getComponent(), 500, e.getWhen(), e.getModifiers(), e.getX(), e.getY(), e.getClickCount(), e.isPopupTrigger());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1087 */           this.fakeClickTime = click.getWhen();
/* 1088 */           handleMouseClicked(click);
/*      */         } 
/*      */       } 
/* 1091 */       this.checkClick = false;
/* 1092 */       this.hadDrag = false;
/*      */       
/* 1094 */       selectInteractor(e);
/* 1095 */       if (JGVTComponent.this.interactor != null) {
/* 1096 */         JGVTComponent.this.interactor.mouseReleased(e);
/* 1097 */         deselectInteractor();
/* 1098 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/* 1099 */         dispatchMouseReleased(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseReleased(MouseEvent e) {
/* 1107 */       JGVTComponent.this.eventDispatcher.mouseReleased(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseEntered(MouseEvent e) {
/* 1115 */       selectInteractor(e);
/* 1116 */       if (JGVTComponent.this.interactor != null) {
/* 1117 */         JGVTComponent.this.interactor.mouseEntered(e);
/* 1118 */         deselectInteractor();
/* 1119 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/* 1120 */         dispatchMouseEntered(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseEntered(MouseEvent e) {
/* 1128 */       JGVTComponent.this.eventDispatcher.mouseEntered(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseExited(MouseEvent e) {
/* 1135 */       selectInteractor(e);
/* 1136 */       if (JGVTComponent.this.interactor != null) {
/* 1137 */         JGVTComponent.this.interactor.mouseExited(e);
/* 1138 */         deselectInteractor();
/* 1139 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/* 1140 */         dispatchMouseExited(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseExited(MouseEvent e) {
/* 1148 */       JGVTComponent.this.eventDispatcher.mouseExited(e);
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
/*      */     public void mouseDragged(MouseEvent e) {
/* 1161 */       this.hadDrag = true;
/* 1162 */       int dx = this.startX - e.getX();
/* 1163 */       int dy = this.startY - e.getY();
/* 1164 */       if (dx * dx + dy * dy > this.MAX_DISP) {
/* 1165 */         this.checkClick = false;
/*      */       }
/* 1167 */       selectInteractor(e);
/* 1168 */       if (JGVTComponent.this.interactor != null) {
/* 1169 */         JGVTComponent.this.interactor.mouseDragged(e);
/* 1170 */         deselectInteractor();
/* 1171 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/* 1172 */         dispatchMouseDragged(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseDragged(MouseEvent e) {
/* 1180 */       JGVTComponent.this.eventDispatcher.mouseDragged(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseMoved(MouseEvent e) {
/* 1188 */       selectInteractor(e);
/* 1189 */       if (JGVTComponent.this.interactor != null) {
/*      */         
/* 1191 */         if (Platform.isOSX && JGVTComponent.this.interactor instanceof AbstractZoomInteractor) {
/*      */           
/* 1193 */           mouseDragged(e);
/*      */         } else {
/* 1195 */           JGVTComponent.this.interactor.mouseMoved(e);
/* 1196 */         }  deselectInteractor();
/* 1197 */       } else if (JGVTComponent.this.eventDispatcher != null) {
/* 1198 */         dispatchMouseMoved(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseMoved(MouseEvent e) {
/* 1206 */       JGVTComponent.this.eventDispatcher.mouseMoved(e);
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
/*      */     public void mouseWheelMoved(MouseWheelEvent e) {
/* 1219 */       if (JGVTComponent.this.eventDispatcher != null) {
/* 1220 */         dispatchMouseWheelMoved(e);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void dispatchMouseWheelMoved(MouseWheelEvent e) {
/* 1228 */       JGVTComponent.this.eventDispatcher.mouseWheelMoved(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void selectInteractor(InputEvent ie) {
/* 1235 */       if (!JGVTComponent.this.disableInteractions && !JGVTComponent.this.suspendInteractions && JGVTComponent.this.interactor == null && JGVTComponent.this.gvtRoot != null)
/*      */       {
/*      */ 
/*      */         
/* 1239 */         for (Object interactor1 : JGVTComponent.this.interactors) {
/* 1240 */           Interactor i = (Interactor)interactor1;
/* 1241 */           if (i.startInteraction(ie)) {
/* 1242 */             JGVTComponent.this.interactor = i;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void deselectInteractor() {
/* 1253 */       if (JGVTComponent.this.interactor.endInteraction()) {
/* 1254 */         JGVTComponent.this.interactor = null;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected class UnixTextSelectionListener
/*      */     extends SelectionAdapter
/*      */   {
/*      */     public void selectionDone(SelectionEvent evt) {
/* 1263 */       if (!JGVTComponent.this.useUnixTextSelection)
/*      */         return; 
/* 1265 */       Object o = evt.getSelection();
/* 1266 */       if (!(o instanceof CharacterIterator))
/*      */         return; 
/* 1268 */       CharacterIterator iter = (CharacterIterator)o;
/*      */ 
/*      */ 
/*      */       
/* 1272 */       SecurityManager securityManager = System.getSecurityManager();
/* 1273 */       if (securityManager != null) {
/*      */         try {
/* 1275 */           securityManager.checkSystemClipboardAccess();
/* 1276 */         } catch (SecurityException e) {
/*      */           return;
/*      */         } 
/*      */       }
/*      */       
/* 1281 */       int sz = iter.getEndIndex() - iter.getBeginIndex();
/* 1282 */       if (sz == 0)
/*      */         return; 
/* 1284 */       char[] cbuff = new char[sz];
/* 1285 */       cbuff[0] = iter.first();
/* 1286 */       for (int i = 1; i < cbuff.length; i++) {
/* 1287 */         cbuff[i] = iter.next();
/*      */       }
/* 1289 */       final String strSel = new String(cbuff);
/*      */ 
/*      */ 
/*      */       
/* 1293 */       (new Thread()
/*      */         {
/*      */           public void run() {
/* 1296 */             Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
/*      */             
/* 1298 */             StringSelection sel = new StringSelection(strSel);
/* 1299 */             cb.setContents(sel, sel);
/*      */           }
/*      */         }).start();
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/JGVTComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */