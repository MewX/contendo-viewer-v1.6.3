/*     */ package org.apache.batik.gvt.event;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.InputEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.EventListener;
/*     */ import java.util.EventObject;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.apache.batik.gvt.GraphicsNode;
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
/*     */ public class AWTEventDispatcher
/*     */   implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, EventDispatcher
/*     */ {
/*     */   protected GraphicsNode root;
/*     */   protected AffineTransform baseTransform;
/*     */   protected EventListenerList glisteners;
/*     */   protected GraphicsNode lastHit;
/*     */   protected GraphicsNode currentKeyEventTarget;
/*  94 */   protected List eventQueue = new LinkedList();
/*     */   protected boolean eventDispatchEnabled = true;
/*  96 */   protected int eventQueueMaxSize = 10;
/*     */ 
/*     */   
/*     */   static final int MAX_QUEUE_SIZE = 10;
/*     */ 
/*     */   
/* 102 */   private int nodeIncrementEventID = 401;
/* 103 */   private int nodeIncrementEventCode = 9;
/* 104 */   private int nodeIncrementEventModifiers = 0;
/* 105 */   private int nodeDecrementEventID = 401;
/* 106 */   private int nodeDecrementEventCode = 9;
/* 107 */   private int nodeDecrementEventModifiers = 1;
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
/*     */   public void setRootNode(GraphicsNode root) {
/* 121 */     if (this.root != root)
/* 122 */       this.eventQueue.clear(); 
/* 123 */     this.root = root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getRootNode() {
/* 131 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseTransform(AffineTransform t) {
/* 140 */     if (this.baseTransform != t && (this.baseTransform == null || !this.baseTransform.equals(t)))
/*     */     {
/*     */ 
/*     */       
/* 144 */       this.eventQueue.clear(); } 
/* 145 */     this.baseTransform = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getBaseTransform() {
/* 153 */     return new AffineTransform(this.baseTransform);
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
/*     */   public void mousePressed(MouseEvent evt) {
/* 166 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent evt) {
/* 175 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent evt) {
/* 184 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent evt) {
/* 193 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent evt) {
/* 202 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseMoved(MouseEvent evt) {
/* 211 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseDragged(MouseEvent evt) {
/* 220 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseWheelMoved(MouseWheelEvent evt) {
/* 229 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent evt) {
/* 238 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent evt) {
/* 247 */     dispatchEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent evt) {
/* 256 */     dispatchEvent(evt);
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
/*     */   public void addGraphicsNodeMouseListener(GraphicsNodeMouseListener l) {
/* 269 */     if (this.glisteners == null) {
/* 270 */       this.glisteners = new EventListenerList();
/*     */     }
/* 272 */     this.glisteners.add(GraphicsNodeMouseListener.class, l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeGraphicsNodeMouseListener(GraphicsNodeMouseListener l) {
/* 281 */     if (this.glisteners != null) {
/* 282 */       this.glisteners.remove(GraphicsNodeMouseListener.class, l);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGraphicsNodeMouseWheelListener(GraphicsNodeMouseWheelListener l) {
/* 293 */     if (this.glisteners == null) {
/* 294 */       this.glisteners = new EventListenerList();
/*     */     }
/* 296 */     this.glisteners.add(GraphicsNodeMouseWheelListener.class, l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeGraphicsNodeMouseWheelListener(GraphicsNodeMouseWheelListener l) {
/* 306 */     if (this.glisteners != null) {
/* 307 */       this.glisteners.remove(GraphicsNodeMouseWheelListener.class, l);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGraphicsNodeKeyListener(GraphicsNodeKeyListener l) {
/* 317 */     if (this.glisteners == null) {
/* 318 */       this.glisteners = new EventListenerList();
/*     */     }
/* 320 */     this.glisteners.add(GraphicsNodeKeyListener.class, l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeGraphicsNodeKeyListener(GraphicsNodeKeyListener l) {
/* 329 */     if (this.glisteners != null) {
/* 330 */       this.glisteners.remove(GraphicsNodeKeyListener.class, l);
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
/*     */   public EventListener[] getListeners(Class<?> listenerType) {
/* 344 */     Object array = Array.newInstance(listenerType, this.glisteners.getListenerCount(listenerType));
/*     */ 
/*     */     
/* 347 */     Object[] pairElements = this.glisteners.getListenerList();
/* 348 */     for (int i = 0, j = 0; i < pairElements.length - 1; i += 2) {
/* 349 */       if (pairElements[i].equals(listenerType)) {
/* 350 */         Array.set(array, j, pairElements[i + 1]);
/* 351 */         j++;
/*     */       } 
/*     */     } 
/* 354 */     return (EventListener[])array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEventDispatchEnabled(boolean b) {
/* 362 */     this.eventDispatchEnabled = b;
/* 363 */     if (this.eventDispatchEnabled)
/*     */     {
/* 365 */       while (this.eventQueue.size() > 0) {
/* 366 */         EventObject evt = this.eventQueue.remove(0);
/* 367 */         dispatchEvent(evt);
/*     */       }  } 
/*     */   }
/*     */   
/*     */   public void setEventQueueMaxSize(int n) {
/* 372 */     this.eventQueueMaxSize = n;
/* 373 */     if (n == 0) this.eventQueue.clear();
/*     */     
/* 375 */     while (this.eventQueue.size() > this.eventQueueMaxSize) {
/* 376 */       this.eventQueue.remove(0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatchEvent(EventObject evt) {
/* 384 */     if (this.root == null)
/*     */       return; 
/* 386 */     if (!this.eventDispatchEnabled) {
/* 387 */       if (this.eventQueueMaxSize > 0) {
/* 388 */         this.eventQueue.add(evt);
/* 389 */         while (this.eventQueue.size() > this.eventQueueMaxSize)
/*     */         {
/*     */           
/* 392 */           this.eventQueue.remove(0); } 
/*     */       } 
/*     */       return;
/*     */     } 
/* 396 */     if (evt instanceof MouseWheelEvent) {
/* 397 */       dispatchMouseWheelEvent((MouseWheelEvent)evt);
/* 398 */     } else if (evt instanceof MouseEvent) {
/* 399 */       dispatchMouseEvent((MouseEvent)evt);
/* 400 */     } else if (evt instanceof KeyEvent) {
/* 401 */       InputEvent e = (InputEvent)evt;
/* 402 */       if (isNodeIncrementEvent(e)) {
/* 403 */         incrementKeyTarget();
/* 404 */       } else if (isNodeDecrementEvent(e)) {
/* 405 */         decrementKeyTarget();
/*     */       } else {
/* 407 */         dispatchKeyEvent((KeyEvent)evt);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getCurrentLockState() {
/* 416 */     Toolkit t = Toolkit.getDefaultToolkit();
/* 417 */     int lockState = 0;
/*     */     try {
/* 419 */       if (t.getLockingKeyState(262)) {
/* 420 */         lockState++;
/*     */       }
/* 422 */     } catch (UnsupportedOperationException unsupportedOperationException) {}
/*     */     
/* 424 */     lockState <<= 1;
/*     */     try {
/* 426 */       if (t.getLockingKeyState(145)) {
/* 427 */         lockState++;
/*     */       }
/* 429 */     } catch (UnsupportedOperationException unsupportedOperationException) {}
/*     */     
/* 431 */     lockState <<= 1;
/*     */     try {
/* 433 */       if (t.getLockingKeyState(144)) {
/* 434 */         lockState++;
/*     */       }
/* 436 */     } catch (UnsupportedOperationException unsupportedOperationException) {}
/*     */     
/* 438 */     lockState <<= 1;
/*     */     try {
/* 440 */       if (t.getLockingKeyState(20)) {
/* 441 */         lockState++;
/*     */       }
/* 443 */     } catch (UnsupportedOperationException unsupportedOperationException) {}
/*     */     
/* 445 */     return lockState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchKeyEvent(KeyEvent evt) {
/* 453 */     this.currentKeyEventTarget = this.lastHit;
/* 454 */     GraphicsNode target = (this.currentKeyEventTarget == null) ? this.root : this.currentKeyEventTarget;
/*     */     
/* 456 */     processKeyEvent(new GraphicsNodeKeyEvent(target, evt.getID(), evt.getWhen(), evt.getModifiersEx(), getCurrentLockState(), evt.getKeyCode(), evt.getKeyChar(), evt.getKeyLocation()));
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
/*     */   protected void dispatchMouseEvent(MouseEvent evt) {
/*     */     Point screenPos;
/* 473 */     Point2D p = new Point2D.Float(evt.getX(), evt.getY());
/* 474 */     Point2D gnp = p;
/* 475 */     if (this.baseTransform != null) {
/* 476 */       gnp = this.baseTransform.transform(p, null);
/*     */     }
/*     */     
/* 479 */     GraphicsNode node = this.root.nodeHitAt(gnp);
/* 480 */     if (node != null) {
/*     */       try {
/* 482 */         node.getGlobalTransform().createInverse().transform(gnp, gnp);
/* 483 */       } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 490 */     if (!evt.getComponent().isShowing()) {
/* 491 */       screenPos = new Point(0, 0);
/*     */     } else {
/* 493 */       screenPos = evt.getComponent().getLocationOnScreen();
/* 494 */       screenPos.x += evt.getX();
/* 495 */       screenPos.y += evt.getY();
/*     */     } 
/*     */     
/* 498 */     int currentLockState = getCurrentLockState();
/*     */     
/* 500 */     if (this.lastHit != node) {
/*     */       
/* 502 */       if (this.lastHit != null) {
/* 503 */         GraphicsNodeMouseEvent gvtevt = new GraphicsNodeMouseEvent(this.lastHit, 505, evt.getWhen(), evt.getModifiersEx(), currentLockState, evt.getButton(), (float)gnp.getX(), (float)gnp.getY(), (int)Math.floor(p.getX()), (int)Math.floor(p.getY()), screenPos.x, screenPos.y, evt.getClickCount(), node);
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
/* 518 */         processMouseEvent(gvtevt);
/*     */       } 
/*     */ 
/*     */       
/* 522 */       if (node != null) {
/* 523 */         GraphicsNodeMouseEvent gvtevt = new GraphicsNodeMouseEvent(node, 504, evt.getWhen(), evt.getModifiersEx(), currentLockState, evt.getButton(), (float)gnp.getX(), (float)gnp.getY(), (int)Math.floor(p.getX()), (int)Math.floor(p.getY()), screenPos.x, screenPos.y, evt.getClickCount(), this.lastHit);
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
/* 538 */         processMouseEvent(gvtevt);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 543 */     if (node != null) {
/* 544 */       GraphicsNodeMouseEvent gvtevt = new GraphicsNodeMouseEvent(node, evt.getID(), evt.getWhen(), evt.getModifiersEx(), currentLockState, evt.getButton(), (float)gnp.getX(), (float)gnp.getY(), (int)Math.floor(p.getX()), (int)Math.floor(p.getY()), screenPos.x, screenPos.y, evt.getClickCount(), null);
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
/* 560 */       processMouseEvent(gvtevt);
/*     */     }
/*     */     else {
/*     */       
/* 564 */       GraphicsNodeMouseEvent gvtevt = new GraphicsNodeMouseEvent(this.root, evt.getID(), evt.getWhen(), evt.getModifiersEx(), currentLockState, evt.getButton(), (float)gnp.getX(), (float)gnp.getY(), (int)Math.floor(p.getX()), (int)Math.floor(p.getY()), screenPos.x, screenPos.y, evt.getClickCount(), null);
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
/* 579 */       processMouseEvent(gvtevt);
/*     */     } 
/* 581 */     this.lastHit = node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchMouseWheelEvent(MouseWheelEvent evt) {
/* 589 */     if (this.lastHit != null) {
/* 590 */       processMouseWheelEvent(new GraphicsNodeMouseWheelEvent(this.lastHit, evt.getID(), evt.getWhen(), evt.getModifiersEx(), getCurrentLockState(), evt.getWheelRotation()));
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
/*     */   protected void processMouseEvent(GraphicsNodeMouseEvent evt) {
/* 606 */     if (this.glisteners != null) {
/* 607 */       GraphicsNodeMouseListener[] listeners = (GraphicsNodeMouseListener[])getListeners(GraphicsNodeMouseListener.class);
/*     */ 
/*     */       
/* 610 */       switch (evt.getID()) {
/*     */         case 503:
/* 612 */           for (GraphicsNodeMouseListener listener6 : listeners) {
/* 613 */             listener6.mouseMoved(evt);
/*     */           }
/*     */           return;
/*     */         case 506:
/* 617 */           for (GraphicsNodeMouseListener listener5 : listeners) {
/* 618 */             listener5.mouseDragged(evt);
/*     */           }
/*     */           return;
/*     */         case 504:
/* 622 */           for (GraphicsNodeMouseListener listener4 : listeners) {
/* 623 */             listener4.mouseEntered(evt);
/*     */           }
/*     */           return;
/*     */         case 505:
/* 627 */           for (GraphicsNodeMouseListener listener3 : listeners) {
/* 628 */             listener3.mouseExited(evt);
/*     */           }
/*     */           return;
/*     */         case 500:
/* 632 */           for (GraphicsNodeMouseListener listener2 : listeners) {
/* 633 */             listener2.mouseClicked(evt);
/*     */           }
/*     */           return;
/*     */         case 501:
/* 637 */           for (GraphicsNodeMouseListener listener1 : listeners) {
/* 638 */             listener1.mousePressed(evt);
/*     */           }
/*     */           return;
/*     */         case 502:
/* 642 */           for (GraphicsNodeMouseListener listener : listeners) {
/* 643 */             listener.mouseReleased(evt);
/*     */           }
/*     */           return;
/*     */       } 
/* 647 */       throw new IllegalArgumentException("Unknown Mouse Event type: " + evt.getID());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processMouseWheelEvent(GraphicsNodeMouseWheelEvent evt) {
/* 658 */     if (this.glisteners != null) {
/* 659 */       GraphicsNodeMouseWheelListener[] listeners = (GraphicsNodeMouseWheelListener[])getListeners(GraphicsNodeMouseWheelListener.class);
/*     */ 
/*     */       
/* 662 */       for (GraphicsNodeMouseWheelListener listener : listeners) {
/* 663 */         listener.mouseWheelMoved(evt);
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
/*     */   public void processKeyEvent(GraphicsNodeKeyEvent evt) {
/* 675 */     if (this.glisteners != null) {
/* 676 */       GraphicsNodeKeyListener[] listeners = (GraphicsNodeKeyListener[])getListeners(GraphicsNodeKeyListener.class);
/*     */ 
/*     */ 
/*     */       
/* 680 */       switch (evt.getID()) {
/*     */         case 401:
/* 682 */           for (GraphicsNodeKeyListener listener2 : listeners) {
/* 683 */             listener2.keyPressed(evt);
/*     */           }
/*     */           break;
/*     */         case 402:
/* 687 */           for (GraphicsNodeKeyListener listener1 : listeners) {
/* 688 */             listener1.keyReleased(evt);
/*     */           }
/*     */           break;
/*     */         case 400:
/* 692 */           for (GraphicsNodeKeyListener listener : listeners) {
/* 693 */             listener.keyTyped(evt);
/*     */           }
/*     */           break;
/*     */         default:
/* 697 */           throw new IllegalArgumentException("Unknown Key Event type: " + evt.getID());
/*     */       } 
/*     */     } 
/* 700 */     evt.consume();
/*     */   }
/*     */ 
/*     */   
/*     */   private void incrementKeyTarget() {
/* 705 */     throw new UnsupportedOperationException("Increment not implemented.");
/*     */   }
/*     */ 
/*     */   
/*     */   private void decrementKeyTarget() {
/* 710 */     throw new UnsupportedOperationException("Decrement not implemented.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeIncrementEvent(InputEvent e) {
/* 718 */     this.nodeIncrementEventID = e.getID();
/* 719 */     if (e instanceof KeyEvent) {
/* 720 */       this.nodeIncrementEventCode = ((KeyEvent)e).getKeyCode();
/*     */     }
/* 722 */     this.nodeIncrementEventModifiers = e.getModifiers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeDecrementEvent(InputEvent e) {
/* 732 */     this.nodeDecrementEventID = e.getID();
/* 733 */     if (e instanceof KeyEvent) {
/* 734 */       this.nodeDecrementEventCode = ((KeyEvent)e).getKeyCode();
/*     */     }
/* 736 */     this.nodeDecrementEventModifiers = e.getModifiers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isNodeIncrementEvent(InputEvent e) {
/* 745 */     if (e.getID() != this.nodeIncrementEventID)
/*     */     {
/* 747 */       return false;
/*     */     }
/*     */     
/* 750 */     if (e instanceof KeyEvent && (
/* 751 */       (KeyEvent)e).getKeyCode() != this.nodeIncrementEventCode)
/*     */     {
/* 753 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 758 */     if ((e.getModifiers() & this.nodeIncrementEventModifiers) == 0)
/*     */     {
/* 760 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 764 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isNodeDecrementEvent(InputEvent e) {
/* 772 */     if (e.getID() != this.nodeDecrementEventID)
/*     */     {
/* 774 */       return false;
/*     */     }
/*     */     
/* 777 */     if (e instanceof KeyEvent && (
/* 778 */       (KeyEvent)e).getKeyCode() != this.nodeDecrementEventCode)
/*     */     {
/* 780 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 785 */     if ((e.getModifiers() & this.nodeDecrementEventModifiers) == 0)
/*     */     {
/* 787 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 791 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean isMetaDown(int modifiers) {
/* 799 */     return ((modifiers & 0x4) != 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/AWTEventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */