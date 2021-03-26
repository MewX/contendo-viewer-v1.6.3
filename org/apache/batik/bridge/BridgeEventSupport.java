/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.dom.events.DOMKeyEvent;
/*     */ import org.apache.batik.dom.events.DOMMouseEvent;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.event.EventDispatcher;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeKeyEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeKeyListener;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseListener;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.events.DocumentEvent;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BridgeEventSupport
/*     */   implements SVGConstants
/*     */ {
/*  60 */   public static final AttributedCharacterIterator.Attribute TEXT_COMPOUND_ID = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXT_COMPOUND_ID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addGVTListener(BridgeContext ctx, Document doc) {
/*  70 */     UserAgent ua = ctx.getUserAgent();
/*  71 */     if (ua != null) {
/*  72 */       EventDispatcher dispatcher = ua.getEventDispatcher();
/*  73 */       if (dispatcher != null) {
/*  74 */         Listener listener = new Listener(ctx, ua);
/*  75 */         dispatcher.addGraphicsNodeMouseListener(listener);
/*  76 */         dispatcher.addGraphicsNodeKeyListener(listener);
/*     */ 
/*     */         
/*  79 */         EventListener l = new GVTUnloadListener(dispatcher, listener);
/*  80 */         NodeEventTarget target = (NodeEventTarget)doc;
/*  81 */         target.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGUnload", l, false, null);
/*     */ 
/*     */ 
/*     */         
/*  85 */         storeEventListenerNS(ctx, (EventTarget)target, "http://www.w3.org/2001/xml-events", "SVGUnload", l, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void storeEventListener(BridgeContext ctx, EventTarget e, String t, EventListener l, boolean c) {
/* 102 */     ctx.storeEventListener(e, t, l, c);
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
/*     */   protected static void storeEventListenerNS(BridgeContext ctx, EventTarget e, String n, String t, EventListener l, boolean c) {
/* 114 */     ctx.storeEventListenerNS(e, n, t, l, c);
/*     */   }
/*     */   
/*     */   protected static class GVTUnloadListener
/*     */     implements EventListener
/*     */   {
/*     */     protected EventDispatcher dispatcher;
/*     */     protected BridgeEventSupport.Listener listener;
/*     */     
/*     */     public GVTUnloadListener(EventDispatcher dispatcher, BridgeEventSupport.Listener listener) {
/* 124 */       this.dispatcher = dispatcher;
/* 125 */       this.listener = listener;
/*     */     }
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 129 */       this.dispatcher.removeGraphicsNodeMouseListener(this.listener);
/* 130 */       this.dispatcher.removeGraphicsNodeKeyListener(this.listener);
/* 131 */       NodeEventTarget et = (NodeEventTarget)evt.getTarget();
/* 132 */       et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "SVGUnload", this, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class Listener
/*     */     implements GraphicsNodeKeyListener, GraphicsNodeMouseListener
/*     */   {
/*     */     protected BridgeContext context;
/*     */     
/*     */     protected UserAgent ua;
/*     */     
/*     */     protected Element lastTargetElement;
/*     */     
/*     */     protected boolean isDown;
/*     */ 
/*     */     
/*     */     public Listener(BridgeContext ctx, UserAgent u) {
/* 150 */       this.context = ctx;
/* 151 */       this.ua = u;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyPressed(GraphicsNodeKeyEvent evt) {
/* 162 */       if (!this.isDown) {
/* 163 */         this.isDown = true;
/* 164 */         dispatchKeyEvent("keydown", evt);
/*     */       } 
/* 166 */       if (evt.getKeyChar() == Character.MAX_VALUE)
/*     */       {
/*     */         
/* 169 */         dispatchKeyEvent("keypress", evt);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyReleased(GraphicsNodeKeyEvent evt) {
/* 178 */       dispatchKeyEvent("keyup", evt);
/* 179 */       this.isDown = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyTyped(GraphicsNodeKeyEvent evt) {
/* 187 */       dispatchKeyEvent("keypress", evt);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void dispatchKeyEvent(String eventType, GraphicsNodeKeyEvent evt) {
/* 195 */       FocusManager fmgr = this.context.getFocusManager();
/* 196 */       if (fmgr == null)
/*     */         return; 
/* 198 */       Element targetElement = (Element)fmgr.getCurrentEventTarget();
/* 199 */       if (targetElement == null) {
/* 200 */         targetElement = this.context.getDocument().getDocumentElement();
/*     */       }
/* 202 */       DocumentEvent d = (DocumentEvent)targetElement.getOwnerDocument();
/* 203 */       DOMKeyEvent keyEvt = (DOMKeyEvent)d.createEvent("KeyEvents");
/* 204 */       keyEvt.initKeyEvent(eventType, true, true, evt.isControlDown(), evt.isAltDown(), evt.isShiftDown(), evt.isMetaDown(), mapKeyCode(evt.getKeyCode()), evt.getKeyChar(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 216 */         ((EventTarget)targetElement).dispatchEvent((Event)keyEvt);
/* 217 */       } catch (RuntimeException e) {
/* 218 */         this.ua.displayError(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected final int mapKeyCode(int keyCode) {
/* 229 */       switch (keyCode) {
/*     */         case 10:
/* 231 */           return 13;
/*     */         case 262:
/* 233 */           return 0;
/*     */         case 263:
/* 235 */           return 0;
/*     */       } 
/* 237 */       return keyCode;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseClicked(GraphicsNodeMouseEvent evt) {
/* 244 */       dispatchMouseEvent("click", evt, true);
/*     */     }
/*     */     
/*     */     public void mousePressed(GraphicsNodeMouseEvent evt) {
/* 248 */       dispatchMouseEvent("mousedown", evt, true);
/*     */     }
/*     */     
/*     */     public void mouseReleased(GraphicsNodeMouseEvent evt) {
/* 252 */       dispatchMouseEvent("mouseup", evt, true);
/*     */     }
/*     */     
/*     */     public void mouseEntered(GraphicsNodeMouseEvent evt) {
/* 256 */       Point clientXY = evt.getClientPoint();
/* 257 */       GraphicsNode node = evt.getGraphicsNode();
/* 258 */       Element targetElement = getEventTarget(node, evt.getPoint2D());
/* 259 */       Element relatedElement = getRelatedElement(evt);
/* 260 */       dispatchMouseEvent("mouseover", targetElement, relatedElement, clientXY, evt, true);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseExited(GraphicsNodeMouseEvent evt) {
/* 269 */       Point clientXY = evt.getClientPoint();
/*     */       
/* 271 */       GraphicsNode node = evt.getRelatedNode();
/* 272 */       Element targetElement = getEventTarget(node, evt.getPoint2D());
/* 273 */       if (this.lastTargetElement != null) {
/* 274 */         dispatchMouseEvent("mouseout", this.lastTargetElement, targetElement, clientXY, evt, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 280 */         this.lastTargetElement = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseDragged(GraphicsNodeMouseEvent evt) {
/* 285 */       dispatchMouseEvent("mousemove", evt, false);
/*     */     }
/*     */     
/*     */     public void mouseMoved(GraphicsNodeMouseEvent evt) {
/* 289 */       Point clientXY = evt.getClientPoint();
/* 290 */       GraphicsNode node = evt.getGraphicsNode();
/* 291 */       Element targetElement = getEventTarget(node, evt.getPoint2D());
/* 292 */       Element holdLTE = this.lastTargetElement;
/* 293 */       if (holdLTE != targetElement) {
/* 294 */         if (holdLTE != null) {
/* 295 */           dispatchMouseEvent("mouseout", holdLTE, targetElement, clientXY, evt, true);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 302 */         if (targetElement != null) {
/* 303 */           dispatchMouseEvent("mouseover", targetElement, holdLTE, clientXY, evt, true);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 311 */       dispatchMouseEvent("mousemove", targetElement, null, clientXY, evt, false);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void dispatchMouseEvent(String eventType, GraphicsNodeMouseEvent evt, boolean cancelable) {
/* 330 */       Point clientXY = evt.getClientPoint();
/* 331 */       GraphicsNode node = evt.getGraphicsNode();
/* 332 */       Element targetElement = getEventTarget(node, evt.getPoint2D());
/* 333 */       Element relatedElement = getRelatedElement(evt);
/* 334 */       dispatchMouseEvent(eventType, targetElement, relatedElement, clientXY, evt, cancelable);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void dispatchMouseEvent(String eventType, Element targetElement, Element relatedElement, Point clientXY, GraphicsNodeMouseEvent evt, boolean cancelable) {
/* 359 */       if (targetElement == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 375 */       Point screenXY = evt.getScreenPoint();
/*     */       
/* 377 */       DocumentEvent d = (DocumentEvent)targetElement.getOwnerDocument();
/* 378 */       DOMMouseEvent mouseEvt = (DOMMouseEvent)d.createEvent("MouseEvents");
/*     */       
/* 380 */       String modifiers = DOMUtilities.getModifiersList(evt.getLockState(), evt.getModifiers());
/*     */ 
/*     */       
/* 383 */       mouseEvt.initMouseEventNS("http://www.w3.org/2001/xml-events", eventType, true, cancelable, null, evt.getClickCount(), screenXY.x, screenXY.y, clientXY.x, clientXY.y, (short)(evt.getButton() - 1), (EventTarget)relatedElement, modifiers);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 398 */         ((EventTarget)targetElement).dispatchEvent((Event)mouseEvt);
/* 399 */       } catch (RuntimeException e) {
/* 400 */         this.ua.displayError(e);
/*     */       } finally {
/* 402 */         this.lastTargetElement = targetElement;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Element getRelatedElement(GraphicsNodeMouseEvent evt) {
/* 412 */       GraphicsNode relatedNode = evt.getRelatedNode();
/* 413 */       Element relatedElement = null;
/* 414 */       if (relatedNode != null) {
/* 415 */         relatedElement = this.context.getElement(relatedNode);
/*     */       }
/* 417 */       return relatedElement;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Element getEventTarget(GraphicsNode node, Point2D pt) {
/* 428 */       Element target = this.context.getElement(node);
/*     */ 
/*     */       
/* 431 */       if (target != null && node instanceof TextNode) {
/* 432 */         TextNode textNode = (TextNode)node;
/* 433 */         List list = textNode.getTextRuns();
/* 434 */         if (list != null) {
/* 435 */           float x = (float)pt.getX();
/* 436 */           float y = (float)pt.getY();
/* 437 */           for (Object aList : list) {
/* 438 */             StrokingTextPainter.TextRun run = (StrokingTextPainter.TextRun)aList;
/*     */             
/* 440 */             AttributedCharacterIterator aci = run.getACI();
/* 441 */             TextSpanLayout layout = run.getLayout();
/* 442 */             TextHit textHit = layout.hitTestChar(x, y);
/* 443 */             Rectangle2D bounds = layout.getBounds2D();
/* 444 */             if (textHit != null && bounds != null && bounds.contains(x, y)) {
/*     */ 
/*     */               
/* 447 */               SoftReference sr = (SoftReference)aci.getAttribute(BridgeEventSupport.TEXT_COMPOUND_ID);
/*     */               
/* 449 */               Object delimiter = sr.get();
/* 450 */               if (delimiter instanceof Element) {
/* 451 */                 return (Element)delimiter;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 457 */       return target;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/BridgeEventSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */