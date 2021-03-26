/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.apache.batik.ext.awt.RenderingHintsKeyExt;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeChangeEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeChangeListener;
/*     */ import org.apache.batik.gvt.filter.GraphicsNodeRable;
/*     */ import org.apache.batik.gvt.filter.GraphicsNodeRable8Bit;
/*     */ import org.apache.batik.gvt.filter.Mask;
/*     */ import org.apache.batik.util.HaltingThread;
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
/*     */ public abstract class AbstractGraphicsNode
/*     */   implements GraphicsNode
/*     */ {
/*     */   protected EventListenerList listeners;
/*     */   protected AffineTransform transform;
/*     */   protected AffineTransform inverseTransform;
/*     */   protected Composite composite;
/*     */   protected boolean isVisible = true;
/*     */   protected ClipRable clip;
/*     */   protected RenderingHints hints;
/*     */   protected CompositeGraphicsNode parent;
/*     */   protected RootGraphicsNode root;
/*     */   protected Mask mask;
/*     */   protected Filter filter;
/* 118 */   protected int pointerEventType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected WeakReference graphicsNodeRable;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected WeakReference enableBackgroundGraphicsNodeRable;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected WeakReference weakRef;
/*     */ 
/*     */ 
/*     */   
/*     */   private Rectangle2D bounds;
/*     */ 
/*     */ 
/*     */   
/* 141 */   protected GraphicsNodeChangeEvent changeStartedEvent = null;
/* 142 */   protected GraphicsNodeChangeEvent changeCompletedEvent = null;
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
/*     */   public WeakReference getWeakReference() {
/* 154 */     if (this.weakRef == null)
/* 155 */       this.weakRef = new WeakReference<AbstractGraphicsNode>(this); 
/* 156 */     return this.weakRef;
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
/*     */   public int getPointerEventType() {
/* 170 */     return this.pointerEventType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPointerEventType(int pointerEventType) {
/* 180 */     this.pointerEventType = pointerEventType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransform(AffineTransform newTransform) {
/* 189 */     fireGraphicsNodeChangeStarted();
/* 190 */     this.transform = newTransform;
/* 191 */     if (this.transform.getDeterminant() != 0.0D) {
/*     */       try {
/* 193 */         this.inverseTransform = this.transform.createInverse();
/* 194 */       } catch (NoninvertibleTransformException e) {
/*     */         
/* 196 */         throw new RuntimeException(e.getMessage());
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 201 */       this.inverseTransform = this.transform;
/*     */     } 
/* 203 */     if (this.parent != null)
/* 204 */       this.parent.invalidateGeometryCache(); 
/* 205 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/* 212 */     return this.transform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getInverseTransform() {
/* 219 */     return this.inverseTransform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getGlobalTransform() {
/* 227 */     AffineTransform ctm = new AffineTransform();
/* 228 */     GraphicsNode node = this;
/* 229 */     while (node != null) {
/* 230 */       if (node.getTransform() != null) {
/* 231 */         ctm.preConcatenate(node.getTransform());
/*     */       }
/* 233 */       node = node.getParent();
/*     */     } 
/* 235 */     return ctm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComposite(Composite newComposite) {
/* 244 */     fireGraphicsNodeChangeStarted();
/* 245 */     invalidateGeometryCache();
/* 246 */     this.composite = newComposite;
/* 247 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Composite getComposite() {
/* 254 */     return this.composite;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisible(boolean isVisible) {
/* 263 */     fireGraphicsNodeChangeStarted();
/* 264 */     this.isVisible = isVisible;
/* 265 */     invalidateGeometryCache();
/* 266 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 273 */     return this.isVisible;
/*     */   }
/*     */   
/*     */   public void setClip(ClipRable newClipper) {
/* 277 */     if (newClipper == null && this.clip == null) {
/*     */       return;
/*     */     }
/* 280 */     fireGraphicsNodeChangeStarted();
/* 281 */     invalidateGeometryCache();
/* 282 */     this.clip = newClipper;
/* 283 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClipRable getClip() {
/* 290 */     return this.clip;
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
/*     */   public void setRenderingHint(RenderingHints.Key key, Object value) {
/* 302 */     fireGraphicsNodeChangeStarted();
/* 303 */     if (this.hints == null) {
/* 304 */       this.hints = new RenderingHints(key, value);
/*     */     } else {
/* 306 */       this.hints.put(key, value);
/*     */     } 
/* 308 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingHints(Map<RenderingHints.Key, ?> hints) {
/* 318 */     fireGraphicsNodeChangeStarted();
/* 319 */     if (this.hints == null) {
/* 320 */       this.hints = new RenderingHints(hints);
/*     */     } else {
/* 322 */       this.hints.putAll(hints);
/*     */     } 
/* 324 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingHints(RenderingHints newHints) {
/* 333 */     fireGraphicsNodeChangeStarted();
/* 334 */     this.hints = newHints;
/* 335 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 342 */     return this.hints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMask(Mask newMask) {
/* 351 */     if (newMask == null && this.mask == null) {
/*     */       return;
/*     */     }
/* 354 */     fireGraphicsNodeChangeStarted();
/* 355 */     invalidateGeometryCache();
/* 356 */     this.mask = newMask;
/* 357 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Mask getMask() {
/* 364 */     return this.mask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilter(Filter newFilter) {
/* 373 */     if (newFilter == null && this.filter == null) {
/*     */       return;
/*     */     }
/* 376 */     fireGraphicsNodeChangeStarted();
/* 377 */     invalidateGeometryCache();
/* 378 */     this.filter = newFilter;
/* 379 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getFilter() {
/* 386 */     return this.filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getGraphicsNodeRable(boolean createIfNeeded) {
/*     */     GraphicsNodeRable8Bit graphicsNodeRable8Bit;
/* 395 */     GraphicsNodeRable ret = null;
/* 396 */     if (this.graphicsNodeRable != null) {
/* 397 */       ret = this.graphicsNodeRable.get();
/* 398 */       if (ret != null) return (Filter)ret; 
/*     */     } 
/* 400 */     if (createIfNeeded) {
/* 401 */       graphicsNodeRable8Bit = new GraphicsNodeRable8Bit(this);
/* 402 */       this.graphicsNodeRable = new WeakReference<GraphicsNodeRable8Bit>(graphicsNodeRable8Bit);
/*     */     } 
/* 404 */     return (Filter)graphicsNodeRable8Bit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getEnableBackgroundGraphicsNodeRable(boolean createIfNeeded) {
/*     */     GraphicsNodeRable8Bit graphicsNodeRable8Bit;
/* 414 */     GraphicsNodeRable ret = null;
/* 415 */     if (this.enableBackgroundGraphicsNodeRable != null) {
/* 416 */       ret = this.enableBackgroundGraphicsNodeRable.get();
/* 417 */       if (ret != null) return (Filter)ret; 
/*     */     } 
/* 419 */     if (createIfNeeded) {
/* 420 */       graphicsNodeRable8Bit = new GraphicsNodeRable8Bit(this);
/* 421 */       graphicsNodeRable8Bit.setUsePrimitivePaint(false);
/* 422 */       this.enableBackgroundGraphicsNodeRable = new WeakReference<GraphicsNodeRable8Bit>(graphicsNodeRable8Bit);
/*     */     } 
/* 424 */     return (Filter)graphicsNodeRable8Bit;
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
/*     */   public void paint(Graphics2D g2d) {
/* 437 */     if (this.composite != null && this.composite instanceof AlphaComposite) {
/*     */       
/* 439 */       AlphaComposite ac = (AlphaComposite)this.composite;
/* 440 */       if (ac.getAlpha() < 0.001D)
/*     */         return; 
/*     */     } 
/* 443 */     Rectangle2D bounds = getBounds();
/* 444 */     if (bounds == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 449 */     Composite defaultComposite = null;
/* 450 */     AffineTransform defaultTransform = null;
/* 451 */     RenderingHints defaultHints = null;
/* 452 */     Graphics2D baseG2d = null;
/*     */     
/* 454 */     if (this.clip != null) {
/* 455 */       baseG2d = g2d;
/* 456 */       g2d = (Graphics2D)g2d.create();
/* 457 */       if (this.hints != null)
/* 458 */         g2d.addRenderingHints(this.hints); 
/* 459 */       if (this.transform != null)
/* 460 */         g2d.transform(this.transform); 
/* 461 */       if (this.composite != null)
/* 462 */         g2d.setComposite(this.composite); 
/* 463 */       g2d.clip(this.clip.getClipPath());
/*     */     } else {
/* 465 */       if (this.hints != null) {
/* 466 */         defaultHints = g2d.getRenderingHints();
/* 467 */         g2d.addRenderingHints(this.hints);
/*     */       } 
/* 469 */       if (this.transform != null) {
/* 470 */         defaultTransform = g2d.getTransform();
/* 471 */         g2d.transform(this.transform);
/*     */       } 
/* 473 */       if (this.composite != null) {
/* 474 */         defaultComposite = g2d.getComposite();
/* 475 */         g2d.setComposite(this.composite);
/*     */       } 
/*     */     } 
/*     */     
/* 479 */     Shape curClip = g2d.getClip();
/* 480 */     g2d.setRenderingHint(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, curClip);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 485 */     boolean paintNeeded = true;
/* 486 */     Shape g2dClip = curClip;
/* 487 */     if (g2dClip != null) {
/* 488 */       Rectangle2D cb = g2dClip.getBounds2D();
/* 489 */       if (!bounds.intersects(cb.getX(), cb.getY(), cb.getWidth(), cb.getHeight()))
/*     */       {
/* 491 */         paintNeeded = false;
/*     */       }
/*     */     } 
/*     */     
/* 495 */     if (paintNeeded) {
/* 496 */       boolean antialiasedClip = false;
/* 497 */       if (this.clip != null && this.clip.getUseAntialiasedClip()) {
/* 498 */         antialiasedClip = isAntialiasedClip(g2d.getTransform(), g2d.getRenderingHints(), this.clip.getClipPath());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 503 */       boolean useOffscreen = isOffscreenBufferNeeded();
/*     */       
/* 505 */       useOffscreen |= antialiasedClip;
/*     */       
/* 507 */       if (!useOffscreen) {
/*     */         
/* 509 */         primitivePaint(g2d);
/*     */       } else {
/* 511 */         Mask mask; ClipRable clipRable; Filter filteredImage = null;
/*     */         
/* 513 */         if (this.filter == null) {
/* 514 */           filteredImage = getGraphicsNodeRable(true);
/*     */         }
/*     */         else {
/*     */           
/* 518 */           filteredImage = this.filter;
/*     */         } 
/*     */         
/* 521 */         if (this.mask != null) {
/* 522 */           if (this.mask.getSource() != filteredImage) {
/* 523 */             this.mask.setSource(filteredImage);
/*     */           }
/* 525 */           mask = this.mask;
/*     */         } 
/*     */         
/* 528 */         if (this.clip != null && antialiasedClip) {
/* 529 */           if (this.clip.getSource() != mask) {
/* 530 */             this.clip.setSource((Filter)mask);
/*     */           }
/* 532 */           clipRable = this.clip;
/*     */         } 
/*     */         
/* 535 */         baseG2d = g2d;
/*     */ 
/*     */ 
/*     */         
/* 539 */         g2d = (Graphics2D)g2d.create();
/*     */         
/* 541 */         if (antialiasedClip)
/*     */         {
/* 543 */           g2d.setClip(null);
/*     */         }
/*     */         
/* 546 */         Rectangle2D filterBounds = clipRable.getBounds2D();
/* 547 */         g2d.clip(filterBounds);
/*     */         
/* 549 */         GraphicsUtil.drawImage(g2d, (RenderableImage)clipRable);
/*     */ 
/*     */         
/* 552 */         g2d.dispose();
/* 553 */         g2d = baseG2d;
/* 554 */         baseG2d = null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 559 */     if (baseG2d != null) {
/* 560 */       g2d.dispose();
/*     */     } else {
/* 562 */       if (defaultHints != null)
/* 563 */         g2d.setRenderingHints(defaultHints); 
/* 564 */       if (defaultTransform != null)
/* 565 */         g2d.setTransform(defaultTransform); 
/* 566 */       if (defaultComposite != null) {
/* 567 */         g2d.setComposite(defaultComposite);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void traceFilter(Filter filter, String prefix) {
/* 576 */     System.out.println(prefix + filter.getClass().getName());
/* 577 */     System.out.println(prefix + filter.getBounds2D());
/* 578 */     List<Filter> sources = filter.getSources();
/* 579 */     int nSources = (sources != null) ? sources.size() : 0;
/* 580 */     prefix = prefix + "\t";
/* 581 */     for (int i = 0; i < nSources; i++) {
/* 582 */       Filter source = sources.get(i);
/* 583 */       traceFilter(source, prefix);
/*     */     } 
/*     */     
/* 586 */     System.out.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isOffscreenBufferNeeded() {
/* 594 */     return (this.filter != null || this.mask != null || (this.composite != null && !AlphaComposite.SrcOver.equals(this.composite)));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAntialiasedClip(AffineTransform usr2dev, RenderingHints hints, Shape clip) {
/* 618 */     if (clip == null) return false;
/*     */     
/* 620 */     Object val = hints.get(RenderingHintsKeyExt.KEY_TRANSCODING);
/* 621 */     if (val == "Printing" || val == "Vector")
/*     */     {
/* 623 */       return false;
/*     */     }
/* 625 */     if (!(clip instanceof Rectangle2D) || usr2dev.getShearX() != 0.0D || usr2dev.getShearY() != 0.0D)
/*     */     {
/*     */       
/* 628 */       return true;
/*     */     }
/* 630 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireGraphicsNodeChangeStarted(GraphicsNode changeSrc) {
/* 637 */     if (this.changeStartedEvent == null) {
/* 638 */       this.changeStartedEvent = new GraphicsNodeChangeEvent(this, 9800);
/*     */     }
/* 640 */     this.changeStartedEvent.setChangeSrc(changeSrc);
/* 641 */     fireGraphicsNodeChangeStarted(this.changeStartedEvent);
/* 642 */     this.changeStartedEvent.setChangeSrc(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireGraphicsNodeChangeStarted() {
/* 649 */     if (this.changeStartedEvent == null) {
/* 650 */       this.changeStartedEvent = new GraphicsNodeChangeEvent(this, 9800);
/*     */     } else {
/*     */       
/* 653 */       this.changeStartedEvent.setChangeSrc(null);
/*     */     } 
/* 655 */     fireGraphicsNodeChangeStarted(this.changeStartedEvent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireGraphicsNodeChangeStarted(GraphicsNodeChangeEvent changeStartedEvent) {
/* 662 */     RootGraphicsNode rootGN = getRoot();
/* 663 */     if (rootGN == null)
/*     */       return; 
/* 665 */     List l = rootGN.getTreeGraphicsNodeChangeListeners();
/* 666 */     if (l == null)
/*     */       return; 
/* 668 */     Iterator<GraphicsNodeChangeListener> i = l.iterator();
/*     */     
/* 670 */     while (i.hasNext()) {
/* 671 */       GraphicsNodeChangeListener gncl = i.next();
/* 672 */       gncl.changeStarted(changeStartedEvent);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fireGraphicsNodeChangeCompleted() {
/* 677 */     if (this.changeCompletedEvent == null) {
/* 678 */       this.changeCompletedEvent = new GraphicsNodeChangeEvent(this, 9801);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 684 */     RootGraphicsNode rootGN = getRoot();
/* 685 */     if (rootGN == null)
/*     */       return; 
/* 687 */     List l = rootGN.getTreeGraphicsNodeChangeListeners();
/* 688 */     if (l == null)
/*     */       return; 
/* 690 */     Iterator<GraphicsNodeChangeListener> i = l.iterator();
/*     */     
/* 692 */     while (i.hasNext()) {
/* 693 */       GraphicsNodeChangeListener gncl = i.next();
/* 694 */       gncl.changeCompleted(this.changeCompletedEvent);
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
/*     */   public CompositeGraphicsNode getParent() {
/* 707 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RootGraphicsNode getRoot() {
/* 715 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setRoot(RootGraphicsNode newRoot) {
/* 724 */     this.root = newRoot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setParent(CompositeGraphicsNode newParent) {
/* 733 */     this.parent = newParent;
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
/*     */   protected void invalidateGeometryCache() {
/* 750 */     if (this.parent != null) {
/* 751 */       this.parent.invalidateGeometryCache();
/*     */     }
/* 753 */     this.bounds = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds() {
/* 763 */     if (this.bounds == null) {
/*     */ 
/*     */ 
/*     */       
/* 767 */       if (this.filter == null) {
/* 768 */         this.bounds = getPrimitiveBounds();
/*     */       } else {
/* 770 */         this.bounds = this.filter.getBounds2D();
/*     */       } 
/*     */       
/* 773 */       if (this.bounds != null) {
/* 774 */         if (this.clip != null) {
/* 775 */           Rectangle2D clipR = this.clip.getClipPath().getBounds2D();
/* 776 */           if (clipR.intersects(this.bounds)) {
/* 777 */             Rectangle2D.intersect(this.bounds, clipR, this.bounds);
/*     */           }
/*     */         } 
/* 780 */         if (this.mask != null) {
/* 781 */           Rectangle2D maskR = this.mask.getBounds2D();
/* 782 */           if (maskR.intersects(this.bounds)) {
/* 783 */             Rectangle2D.intersect(this.bounds, maskR, this.bounds);
/*     */           }
/*     */         } 
/*     */       } 
/* 787 */       this.bounds = normalizeRectangle(this.bounds);
/*     */ 
/*     */       
/* 790 */       if (HaltingThread.hasBeenHalted())
/*     */       {
/*     */         
/* 793 */         invalidateGeometryCache();
/*     */       }
/*     */     } 
/*     */     
/* 797 */     return this.bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getTransformedBounds(AffineTransform txf) {
/* 808 */     AffineTransform t = txf;
/* 809 */     if (this.transform != null) {
/* 810 */       t = new AffineTransform(txf);
/* 811 */       t.concatenate(this.transform);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 817 */     Rectangle2D tBounds = null;
/* 818 */     if (this.filter == null) {
/*     */       
/* 820 */       tBounds = getTransformedPrimitiveBounds(txf);
/*     */     } else {
/* 822 */       tBounds = t.createTransformedShape(this.filter.getBounds2D()).getBounds2D();
/*     */     } 
/*     */ 
/*     */     
/* 826 */     if (tBounds != null) {
/* 827 */       if (this.clip != null) {
/* 828 */         Rectangle2D.intersect(tBounds, t.createTransformedShape(this.clip.getClipPath()).getBounds2D(), tBounds);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 835 */       if (this.mask != null) {
/* 836 */         Rectangle2D.intersect(tBounds, t.createTransformedShape(this.mask.getBounds2D()).getBounds2D(), tBounds);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 843 */     return tBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getTransformedPrimitiveBounds(AffineTransform txf) {
/* 854 */     Rectangle2D tpBounds = getPrimitiveBounds();
/* 855 */     if (tpBounds == null) {
/* 856 */       return null;
/*     */     }
/* 858 */     AffineTransform t = txf;
/* 859 */     if (this.transform != null) {
/* 860 */       t = new AffineTransform(txf);
/* 861 */       t.concatenate(this.transform);
/*     */     } 
/*     */     
/* 864 */     return t.createTransformedShape(tpBounds).getBounds2D();
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
/*     */   public Rectangle2D getTransformedGeometryBounds(AffineTransform txf) {
/* 878 */     Rectangle2D tpBounds = getGeometryBounds();
/* 879 */     if (tpBounds == null) {
/* 880 */       return null;
/*     */     }
/* 882 */     AffineTransform t = txf;
/* 883 */     if (this.transform != null) {
/* 884 */       t = new AffineTransform(txf);
/* 885 */       t.concatenate(this.transform);
/*     */     } 
/*     */     
/* 888 */     return t.createTransformedShape(tpBounds).getBounds2D();
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
/*     */   public Rectangle2D getTransformedSensitiveBounds(AffineTransform txf) {
/* 902 */     Rectangle2D sBounds = getSensitiveBounds();
/* 903 */     if (sBounds == null) {
/* 904 */       return null;
/*     */     }
/* 906 */     AffineTransform t = txf;
/* 907 */     if (this.transform != null) {
/* 908 */       t = new AffineTransform(txf);
/* 909 */       t.concatenate(this.transform);
/*     */     } 
/*     */     
/* 912 */     return t.createTransformedShape(sBounds).getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 922 */     Rectangle2D b = getSensitiveBounds();
/* 923 */     if (b == null || !b.contains(p)) {
/* 924 */       return false;
/*     */     }
/* 926 */     switch (this.pointerEventType) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 931 */         return this.isVisible;
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/* 936 */         return true;
/*     */     } 
/*     */     
/* 939 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/* 950 */     Rectangle2D b = getBounds();
/* 951 */     if (b == null) return false;
/*     */     
/* 953 */     return b.intersects(r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode nodeHitAt(Point2D p) {
/* 963 */     return contains(p) ? this : null;
/*     */   }
/*     */   
/* 966 */   static double EPSILON = 1.0E-6D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle2D normalizeRectangle(Rectangle2D bounds) {
/* 974 */     if (bounds == null) return null;
/*     */     
/* 976 */     if (bounds.getWidth() < EPSILON) {
/* 977 */       if (bounds.getHeight() < EPSILON) {
/* 978 */         AffineTransform gt = getGlobalTransform();
/* 979 */         double det = Math.sqrt(gt.getDeterminant());
/* 980 */         return new Rectangle2D.Double(bounds.getX(), bounds.getY(), EPSILON / det, EPSILON / det);
/*     */       } 
/*     */       
/* 983 */       double tmpW = bounds.getHeight() * EPSILON;
/* 984 */       if (tmpW < bounds.getWidth())
/* 985 */         tmpW = bounds.getWidth(); 
/* 986 */       return new Rectangle2D.Double(bounds.getX(), bounds.getY(), tmpW, bounds.getHeight());
/*     */     } 
/*     */ 
/*     */     
/* 990 */     if (bounds.getHeight() < EPSILON) {
/* 991 */       double tmpH = bounds.getWidth() * EPSILON;
/* 992 */       if (tmpH < bounds.getHeight())
/* 993 */         tmpH = bounds.getHeight(); 
/* 994 */       return new Rectangle2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), tmpH);
/*     */     } 
/*     */ 
/*     */     
/* 998 */     return bounds;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/AbstractGraphicsNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */