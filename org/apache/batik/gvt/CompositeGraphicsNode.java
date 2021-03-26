/*      */ package org.apache.batik.gvt;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import org.apache.batik.util.HaltingThread;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CompositeGraphicsNode
/*      */   extends AbstractGraphicsNode
/*      */   implements List
/*      */ {
/*   48 */   public static final Rectangle2D VIEWPORT = new Rectangle();
/*   49 */   public static final Rectangle2D NULL_RECT = new Rectangle();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected GraphicsNode[] children;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected volatile int count;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected volatile int modCount;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   70 */   protected Rectangle2D backgroundEnableRgn = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile Rectangle2D geometryBounds;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile Rectangle2D primitiveBounds;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile Rectangle2D sensitiveBounds;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Shape outline;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getChildren() {
/*  106 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackgroundEnable(Rectangle2D bgRgn) {
/*  115 */     this.backgroundEnableRgn = bgRgn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Rectangle2D getBackgroundEnable() {
/*  122 */     return this.backgroundEnableRgn;
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
/*      */   public void setVisible(boolean isVisible) {
/*  134 */     this.isVisible = isVisible;
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
/*      */   public void primitivePaint(Graphics2D g2d) {
/*  149 */     if (this.count == 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  154 */     Thread currentThread = Thread.currentThread();
/*      */ 
/*      */     
/*  157 */     for (int i = 0; i < this.count; i++) {
/*  158 */       if (HaltingThread.hasBeenHalted(currentThread)) {
/*      */         return;
/*      */       }
/*  161 */       GraphicsNode node = this.children[i];
/*  162 */       if (node != null)
/*      */       {
/*      */         
/*  165 */         node.paint(g2d);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void invalidateGeometryCache() {
/*  185 */     super.invalidateGeometryCache();
/*  186 */     this.geometryBounds = null;
/*  187 */     this.primitiveBounds = null;
/*  188 */     this.sensitiveBounds = null;
/*  189 */     this.outline = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Rectangle2D getPrimitiveBounds() {
/*  196 */     if (this.primitiveBounds != null) {
/*  197 */       if (this.primitiveBounds == NULL_RECT) return null; 
/*  198 */       return this.primitiveBounds;
/*      */     } 
/*      */ 
/*      */     
/*  202 */     Thread currentThread = Thread.currentThread();
/*      */     
/*  204 */     int i = 0;
/*  205 */     Rectangle2D bounds = null;
/*  206 */     while (bounds == null && i < this.count) {
/*  207 */       bounds = this.children[i++].getTransformedBounds(IDENTITY);
/*  208 */       if ((i & 0xF) == 0 && HaltingThread.hasBeenHalted(currentThread))
/*      */         break; 
/*      */     } 
/*  211 */     if (HaltingThread.hasBeenHalted(currentThread)) {
/*  212 */       invalidateGeometryCache();
/*  213 */       return null;
/*      */     } 
/*      */     
/*  216 */     if (bounds == null) {
/*  217 */       this.primitiveBounds = NULL_RECT;
/*  218 */       return null;
/*      */     } 
/*      */     
/*  221 */     this.primitiveBounds = bounds;
/*      */     
/*  223 */     while (i < this.count) {
/*  224 */       Rectangle2D ctb = this.children[i++].getTransformedBounds(IDENTITY);
/*  225 */       if (ctb != null) {
/*  226 */         if (this.primitiveBounds == null)
/*      */         {
/*      */           
/*  229 */           return null;
/*      */         }
/*  231 */         this.primitiveBounds.add(ctb);
/*      */       } 
/*      */ 
/*      */       
/*  235 */       if ((i & 0xF) == 0 && HaltingThread.hasBeenHalted(currentThread)) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/*  240 */     if (HaltingThread.hasBeenHalted(currentThread))
/*      */     {
/*      */       
/*  243 */       invalidateGeometryCache();
/*      */     }
/*  245 */     return this.primitiveBounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Rectangle2D getTransformedBBox(Rectangle2D r2d, AffineTransform t) {
/*  254 */     if (t == null || r2d == null) return r2d;
/*      */     
/*  256 */     double x = r2d.getX();
/*  257 */     double w = r2d.getWidth();
/*  258 */     double y = r2d.getY();
/*  259 */     double h = r2d.getHeight();
/*      */     
/*  261 */     double sx = t.getScaleX();
/*  262 */     double sy = t.getScaleY();
/*  263 */     if (sx < 0.0D) {
/*  264 */       x = -(x + w);
/*  265 */       sx = -sx;
/*      */     } 
/*  267 */     if (sy < 0.0D) {
/*  268 */       y = -(y + h);
/*  269 */       sy = -sy;
/*      */     } 
/*      */     
/*  272 */     return new Rectangle2D.Float((float)(x * sx + t.getTranslateX()), (float)(y * sy + t.getTranslateY()), (float)(w * sx), (float)(h * sy));
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
/*      */   public Rectangle2D getTransformedPrimitiveBounds(AffineTransform txf) {
/*  287 */     AffineTransform t = txf;
/*  288 */     if (this.transform != null) {
/*  289 */       t = new AffineTransform(txf);
/*  290 */       t.concatenate(this.transform);
/*      */     } 
/*      */     
/*  293 */     if (t == null || (t.getShearX() == 0.0D && t.getShearY() == 0.0D))
/*      */     {
/*  295 */       return getTransformedBBox(getPrimitiveBounds(), t);
/*      */     }
/*      */     
/*  298 */     int i = 0;
/*  299 */     Rectangle2D tpb = null;
/*  300 */     while (tpb == null && i < this.count) {
/*  301 */       tpb = this.children[i++].getTransformedBounds(t);
/*      */     }
/*      */     
/*  304 */     while (i < this.count) {
/*  305 */       Rectangle2D ctb = this.children[i++].getTransformedBounds(t);
/*  306 */       if (ctb != null) {
/*  307 */         tpb.add(ctb);
/*      */       }
/*      */     } 
/*      */     
/*  311 */     return tpb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Rectangle2D getGeometryBounds() {
/*  321 */     if (this.geometryBounds == null) {
/*      */       
/*  323 */       int i = 0;
/*  324 */       while (this.geometryBounds == null && i < this.count) {
/*  325 */         this.geometryBounds = this.children[i++].getTransformedGeometryBounds(IDENTITY);
/*      */       }
/*      */ 
/*      */       
/*  329 */       while (i < this.count) {
/*  330 */         Rectangle2D cgb = this.children[i++].getTransformedGeometryBounds(IDENTITY);
/*  331 */         if (cgb != null) {
/*  332 */           if (this.geometryBounds == null)
/*      */           {
/*      */             
/*  335 */             return getGeometryBounds();
/*      */           }
/*  337 */           this.geometryBounds.add(cgb);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  343 */     return this.geometryBounds;
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
/*      */   public Rectangle2D getTransformedGeometryBounds(AffineTransform txf) {
/*  357 */     AffineTransform t = txf;
/*  358 */     if (this.transform != null) {
/*  359 */       t = new AffineTransform(txf);
/*  360 */       t.concatenate(this.transform);
/*      */     } 
/*      */     
/*  363 */     if (t == null || (t.getShearX() == 0.0D && t.getShearY() == 0.0D))
/*      */     {
/*  365 */       return getTransformedBBox(getGeometryBounds(), t);
/*      */     }
/*      */     
/*  368 */     Rectangle2D gb = null;
/*  369 */     int i = 0;
/*  370 */     while (gb == null && i < this.count) {
/*  371 */       gb = this.children[i++].getTransformedGeometryBounds(t);
/*      */     }
/*      */     
/*  374 */     Rectangle2D cgb = null;
/*  375 */     while (i < this.count) {
/*  376 */       cgb = this.children[i++].getTransformedGeometryBounds(t);
/*  377 */       if (cgb != null) {
/*  378 */         gb.add(cgb);
/*      */       }
/*      */     } 
/*      */     
/*  382 */     return gb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Rectangle2D getSensitiveBounds() {
/*  391 */     if (this.sensitiveBounds != null) {
/*  392 */       return this.sensitiveBounds;
/*      */     }
/*      */     
/*  395 */     int i = 0;
/*  396 */     while (this.sensitiveBounds == null && i < this.count) {
/*  397 */       this.sensitiveBounds = this.children[i++].getTransformedSensitiveBounds(IDENTITY);
/*      */     }
/*      */ 
/*      */     
/*  401 */     while (i < this.count) {
/*  402 */       Rectangle2D cgb = this.children[i++].getTransformedSensitiveBounds(IDENTITY);
/*  403 */       if (cgb != null) {
/*  404 */         if (this.sensitiveBounds == null)
/*      */         {
/*      */           
/*  407 */           return getSensitiveBounds();
/*      */         }
/*  409 */         this.sensitiveBounds.add(cgb);
/*      */       } 
/*      */     } 
/*      */     
/*  413 */     return this.sensitiveBounds;
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
/*      */   public Rectangle2D getTransformedSensitiveBounds(AffineTransform txf) {
/*  427 */     AffineTransform t = txf;
/*  428 */     if (this.transform != null) {
/*  429 */       t = new AffineTransform(txf);
/*  430 */       t.concatenate(this.transform);
/*      */     } 
/*      */     
/*  433 */     if (t == null || (t.getShearX() == 0.0D && t.getShearY() == 0.0D))
/*      */     {
/*  435 */       return getTransformedBBox(getSensitiveBounds(), t);
/*      */     }
/*      */     
/*  438 */     Rectangle2D sb = null;
/*  439 */     int i = 0;
/*  440 */     while (sb == null && i < this.count) {
/*  441 */       sb = this.children[i++].getTransformedSensitiveBounds(t);
/*      */     }
/*      */     
/*  444 */     while (i < this.count) {
/*  445 */       Rectangle2D csb = this.children[i++].getTransformedSensitiveBounds(t);
/*  446 */       if (csb != null) {
/*  447 */         sb.add(csb);
/*      */       }
/*      */     } 
/*      */     
/*  451 */     return sb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(Point2D p) {
/*  461 */     Rectangle2D bounds = getSensitiveBounds();
/*  462 */     if (this.count > 0 && bounds != null && bounds.contains(p)) {
/*  463 */       Point2D pt = null;
/*  464 */       Point2D cp = null;
/*  465 */       for (int i = 0; i < this.count; i++) {
/*  466 */         AffineTransform t = this.children[i].getInverseTransform();
/*  467 */         if (t != null) {
/*  468 */           pt = t.transform(p, pt);
/*  469 */           cp = pt;
/*      */         } else {
/*  471 */           cp = p;
/*      */         } 
/*  473 */         if (this.children[i].contains(cp)) {
/*  474 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  479 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GraphicsNode nodeHitAt(Point2D p) {
/*  490 */     Rectangle2D bounds = getSensitiveBounds();
/*  491 */     if (this.count > 0 && bounds != null && bounds.contains(p)) {
/*      */       
/*  493 */       Point2D pt = null;
/*  494 */       Point2D cp = null;
/*  495 */       for (int i = this.count - 1; i >= 0; i--) {
/*  496 */         AffineTransform t = this.children[i].getInverseTransform();
/*  497 */         if (t != null) {
/*  498 */           pt = t.transform(p, pt);
/*  499 */           cp = pt;
/*      */         } else {
/*  501 */           cp = p;
/*      */         } 
/*  503 */         GraphicsNode node = this.children[i].nodeHitAt(cp);
/*  504 */         if (node != null) {
/*  505 */           return node;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  510 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Shape getOutline() {
/*  517 */     if (this.outline != null) {
/*  518 */       return this.outline;
/*      */     }
/*  520 */     this.outline = new GeneralPath();
/*  521 */     for (int i = 0; i < this.count; i++) {
/*  522 */       Shape childOutline = this.children[i].getOutline();
/*  523 */       if (childOutline != null) {
/*  524 */         AffineTransform tr = this.children[i].getTransform();
/*  525 */         if (tr != null) {
/*  526 */           ((GeneralPath)this.outline).append(tr.createTransformedShape(childOutline), false);
/*      */         } else {
/*  528 */           ((GeneralPath)this.outline).append(childOutline, false);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  533 */     return this.outline;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setRoot(RootGraphicsNode newRoot) {
/*  544 */     super.setRoot(newRoot);
/*  545 */     for (int i = 0; i < this.count; i++) {
/*  546 */       GraphicsNode node = this.children[i];
/*  547 */       ((AbstractGraphicsNode)node).setRoot(newRoot);
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
/*      */   public int size() {
/*  559 */     return this.count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  567 */     return (this.count == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(Object node) {
/*  576 */     return (indexOf(node) >= 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator iterator() {
/*  583 */     return new Itr();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] toArray() {
/*  591 */     GraphicsNode[] result = new GraphicsNode[this.count];
/*      */     
/*  593 */     System.arraycopy(this.children, 0, result, 0, this.count);
/*      */     
/*  595 */     return (Object[])result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] toArray(Object[] a) {
/*      */     GraphicsNode[] arrayOfGraphicsNode;
/*  607 */     if (a.length < this.count) {
/*  608 */       arrayOfGraphicsNode = new GraphicsNode[this.count];
/*      */     }
/*  610 */     System.arraycopy(this.children, 0, arrayOfGraphicsNode, 0, this.count);
/*  611 */     if (arrayOfGraphicsNode.length > this.count) {
/*  612 */       arrayOfGraphicsNode[this.count] = null;
/*      */     }
/*  614 */     return (Object[])arrayOfGraphicsNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(int index) {
/*  624 */     checkRange(index);
/*  625 */     return this.children[index];
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
/*      */   public Object set(int index, Object o) {
/*  643 */     if (!(o instanceof GraphicsNode)) {
/*  644 */       throw new IllegalArgumentException(o + " is not a GraphicsNode");
/*      */     }
/*  646 */     checkRange(index);
/*  647 */     GraphicsNode node = (GraphicsNode)o;
/*      */     
/*  649 */     fireGraphicsNodeChangeStarted(node);
/*      */ 
/*      */     
/*  652 */     if (node.getParent() != null) {
/*  653 */       node.getParent().getChildren().remove(node);
/*      */     }
/*      */     
/*  656 */     GraphicsNode oldNode = this.children[index];
/*  657 */     this.children[index] = node;
/*      */     
/*  659 */     ((AbstractGraphicsNode)node).setParent(this);
/*  660 */     ((AbstractGraphicsNode)oldNode).setParent((CompositeGraphicsNode)null);
/*      */     
/*  662 */     ((AbstractGraphicsNode)node).setRoot(getRoot());
/*  663 */     ((AbstractGraphicsNode)oldNode).setRoot((RootGraphicsNode)null);
/*      */     
/*  665 */     invalidateGeometryCache();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  671 */     fireGraphicsNodeChangeCompleted();
/*  672 */     return oldNode;
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
/*      */   public boolean add(Object o) {
/*  685 */     if (!(o instanceof GraphicsNode)) {
/*  686 */       throw new IllegalArgumentException(o + " is not a GraphicsNode");
/*      */     }
/*  688 */     GraphicsNode node = (GraphicsNode)o;
/*      */     
/*  690 */     fireGraphicsNodeChangeStarted(node);
/*      */ 
/*      */     
/*  693 */     if (node.getParent() != null) {
/*  694 */       node.getParent().getChildren().remove(node);
/*      */     }
/*      */     
/*  697 */     ensureCapacity(this.count + 1);
/*  698 */     this.children[this.count++] = node;
/*      */     
/*  700 */     ((AbstractGraphicsNode)node).setParent(this);
/*      */     
/*  702 */     ((AbstractGraphicsNode)node).setRoot(getRoot());
/*      */     
/*  704 */     invalidateGeometryCache();
/*      */ 
/*      */ 
/*      */     
/*  708 */     fireGraphicsNodeChangeCompleted();
/*  709 */     return true;
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
/*      */   public void add(int index, Object o) {
/*  727 */     if (!(o instanceof GraphicsNode)) {
/*  728 */       throw new IllegalArgumentException(o + " is not a GraphicsNode");
/*      */     }
/*  730 */     if (index > this.count || index < 0) {
/*  731 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.count);
/*      */     }
/*      */     
/*  734 */     GraphicsNode node = (GraphicsNode)o;
/*      */     
/*  736 */     fireGraphicsNodeChangeStarted(node);
/*      */ 
/*      */     
/*  739 */     if (node.getParent() != null) {
/*  740 */       node.getParent().getChildren().remove(node);
/*      */     }
/*      */     
/*  743 */     ensureCapacity(this.count + 1);
/*  744 */     System.arraycopy(this.children, index, this.children, index + 1, this.count - index);
/*  745 */     this.children[index] = node;
/*  746 */     this.count++;
/*      */     
/*  748 */     ((AbstractGraphicsNode)node).setParent(this);
/*      */     
/*  750 */     ((AbstractGraphicsNode)node).setRoot(getRoot());
/*      */     
/*  752 */     invalidateGeometryCache();
/*      */ 
/*      */ 
/*      */     
/*  756 */     fireGraphicsNodeChangeCompleted();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(Collection c) {
/*  764 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(int index, Collection c) {
/*  772 */     throw new UnsupportedOperationException();
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
/*      */   public boolean remove(Object o) {
/*  786 */     if (!(o instanceof GraphicsNode)) {
/*  787 */       throw new IllegalArgumentException(o + " is not a GraphicsNode");
/*      */     }
/*  789 */     GraphicsNode node = (GraphicsNode)o;
/*  790 */     if (node.getParent() != this) {
/*  791 */       return false;
/*      */     }
/*      */     
/*  794 */     int index = 0;
/*  795 */     for (; node != this.children[index]; index++);
/*      */ 
/*      */     
/*  798 */     remove(index);
/*  799 */     return true;
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
/*      */   public Object remove(int index) {
/*  813 */     checkRange(index);
/*  814 */     GraphicsNode oldNode = this.children[index];
/*      */     
/*  816 */     fireGraphicsNodeChangeStarted(oldNode);
/*      */ 
/*      */     
/*  819 */     this.modCount++;
/*  820 */     int numMoved = this.count - index - 1;
/*  821 */     if (numMoved > 0) {
/*  822 */       System.arraycopy(this.children, index + 1, this.children, index, numMoved);
/*      */     }
/*  824 */     this.children[--this.count] = null;
/*  825 */     if (this.count == 0) {
/*  826 */       this.children = null;
/*      */     }
/*      */     
/*  829 */     ((AbstractGraphicsNode)oldNode).setParent((CompositeGraphicsNode)null);
/*      */     
/*  831 */     ((AbstractGraphicsNode)oldNode).setRoot((RootGraphicsNode)null);
/*      */     
/*  833 */     invalidateGeometryCache();
/*      */ 
/*      */ 
/*      */     
/*  837 */     fireGraphicsNodeChangeCompleted();
/*  838 */     return oldNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeAll(Collection c) {
/*  846 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean retainAll(Collection c) {
/*  854 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  862 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsAll(Collection c) {
/*  872 */     for (Object aC : c) {
/*  873 */       if (!contains(aC)) {
/*  874 */         return false;
/*      */       }
/*      */     } 
/*  877 */     return true;
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
/*      */   public int indexOf(Object node) {
/*  889 */     if (node == null || !(node instanceof GraphicsNode)) {
/*  890 */       return -1;
/*      */     }
/*  892 */     if (((GraphicsNode)node).getParent() == this) {
/*  893 */       int iCount = this.count;
/*  894 */       GraphicsNode[] workList = this.children;
/*  895 */       for (int i = 0; i < iCount; i++) {
/*  896 */         if (node == workList[i]) {
/*  897 */           return i;
/*      */         }
/*      */       } 
/*      */     } 
/*  901 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int lastIndexOf(Object node) {
/*  912 */     if (node == null || !(node instanceof GraphicsNode)) {
/*  913 */       return -1;
/*      */     }
/*  915 */     if (((GraphicsNode)node).getParent() == this) {
/*  916 */       for (int i = this.count - 1; i >= 0; i--) {
/*  917 */         if (node == this.children[i]) {
/*  918 */           return i;
/*      */         }
/*      */       } 
/*      */     }
/*  922 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ListIterator listIterator() {
/*  931 */     return listIterator(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ListIterator listIterator(int index) {
/*  942 */     if (index < 0 || index > this.count) {
/*  943 */       throw new IndexOutOfBoundsException("Index: " + index);
/*      */     }
/*  945 */     return new ListItr(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List subList(int fromIndex, int toIndex) {
/*  955 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkRange(int index) {
/*  965 */     if (index >= this.count || index < 0) {
/*  966 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.count);
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
/*      */   public void ensureCapacity(int minCapacity) {
/*  979 */     if (this.children == null) {
/*  980 */       this.children = new GraphicsNode[4];
/*      */     }
/*  982 */     this.modCount++;
/*  983 */     int oldCapacity = this.children.length;
/*  984 */     if (minCapacity > oldCapacity) {
/*  985 */       GraphicsNode[] oldData = this.children;
/*  986 */       int newCapacity = oldCapacity + oldCapacity / 2 + 1;
/*  987 */       if (newCapacity < minCapacity) {
/*  988 */         newCapacity = minCapacity;
/*      */       }
/*  990 */       this.children = new GraphicsNode[newCapacity];
/*  991 */       System.arraycopy(oldData, 0, this.children, 0, this.count);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class Itr
/*      */     implements Iterator
/*      */   {
/* 1003 */     int cursor = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1010 */     int lastRet = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1017 */     int expectedModCount = CompositeGraphicsNode.this.modCount;
/*      */     
/*      */     public boolean hasNext() {
/* 1020 */       return (this.cursor != CompositeGraphicsNode.this.count);
/*      */     }
/*      */     
/*      */     public Object next() {
/*      */       try {
/* 1025 */         Object next = CompositeGraphicsNode.this.get(this.cursor);
/* 1026 */         checkForComodification();
/* 1027 */         this.lastRet = this.cursor++;
/* 1028 */         return next;
/* 1029 */       } catch (IndexOutOfBoundsException e) {
/* 1030 */         checkForComodification();
/* 1031 */         throw new NoSuchElementException();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1036 */       if (this.lastRet == -1) {
/* 1037 */         throw new IllegalStateException();
/*      */       }
/* 1039 */       checkForComodification();
/*      */       
/*      */       try {
/* 1042 */         CompositeGraphicsNode.this.remove(this.lastRet);
/* 1043 */         if (this.lastRet < this.cursor) {
/* 1044 */           this.cursor--;
/*      */         }
/* 1046 */         this.lastRet = -1;
/* 1047 */         this.expectedModCount = CompositeGraphicsNode.this.modCount;
/* 1048 */       } catch (IndexOutOfBoundsException e) {
/* 1049 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */     
/*      */     final void checkForComodification() {
/* 1054 */       if (CompositeGraphicsNode.this.modCount != this.expectedModCount) {
/* 1055 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */     
/*      */     private Itr() {}
/*      */   }
/*      */   
/*      */   private class ListItr
/*      */     extends Itr
/*      */     implements ListIterator
/*      */   {
/*      */     ListItr(int index) {
/* 1067 */       this.cursor = index;
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1071 */       return (this.cursor != 0);
/*      */     }
/*      */     
/*      */     public Object previous() {
/*      */       try {
/* 1076 */         Object previous = CompositeGraphicsNode.this.get(--this.cursor);
/* 1077 */         checkForComodification();
/* 1078 */         this.lastRet = this.cursor;
/* 1079 */         return previous;
/* 1080 */       } catch (IndexOutOfBoundsException e) {
/* 1081 */         checkForComodification();
/* 1082 */         throw new NoSuchElementException();
/*      */       } 
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1087 */       return this.cursor;
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1091 */       return this.cursor - 1;
/*      */     }
/*      */     
/*      */     public void set(Object o) {
/* 1095 */       if (this.lastRet == -1) {
/* 1096 */         throw new IllegalStateException();
/*      */       }
/* 1098 */       checkForComodification();
/*      */       try {
/* 1100 */         CompositeGraphicsNode.this.set(this.lastRet, o);
/* 1101 */         this.expectedModCount = CompositeGraphicsNode.this.modCount;
/* 1102 */       } catch (IndexOutOfBoundsException e) {
/* 1103 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/* 1108 */       checkForComodification();
/*      */       try {
/* 1110 */         CompositeGraphicsNode.this.add(this.cursor++, o);
/* 1111 */         this.lastRet = -1;
/* 1112 */         this.expectedModCount = CompositeGraphicsNode.this.modCount;
/* 1113 */       } catch (IndexOutOfBoundsException e) {
/* 1114 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/CompositeGraphicsNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */