/*      */ package org.apache.batik.bridge;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Paint;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.Arrays;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Set;
/*      */ import org.apache.batik.anim.AnimationEngine;
/*      */ import org.apache.batik.anim.AnimationException;
/*      */ import org.apache.batik.anim.dom.AnimationTarget;
/*      */ import org.apache.batik.anim.dom.SVGOMDocument;
/*      */ import org.apache.batik.anim.dom.SVGOMElement;
/*      */ import org.apache.batik.anim.dom.SVGStylableElement;
/*      */ import org.apache.batik.anim.timing.TimedDocumentRoot;
/*      */ import org.apache.batik.anim.timing.TimedElement;
/*      */ import org.apache.batik.anim.values.AnimatableAngleOrIdentValue;
/*      */ import org.apache.batik.anim.values.AnimatableAngleValue;
/*      */ import org.apache.batik.anim.values.AnimatableBooleanValue;
/*      */ import org.apache.batik.anim.values.AnimatableColorValue;
/*      */ import org.apache.batik.anim.values.AnimatableIntegerValue;
/*      */ import org.apache.batik.anim.values.AnimatableLengthListValue;
/*      */ import org.apache.batik.anim.values.AnimatableLengthOrIdentValue;
/*      */ import org.apache.batik.anim.values.AnimatableLengthValue;
/*      */ import org.apache.batik.anim.values.AnimatableNumberListValue;
/*      */ import org.apache.batik.anim.values.AnimatableNumberOrIdentValue;
/*      */ import org.apache.batik.anim.values.AnimatableNumberOrPercentageValue;
/*      */ import org.apache.batik.anim.values.AnimatableNumberValue;
/*      */ import org.apache.batik.anim.values.AnimatablePaintValue;
/*      */ import org.apache.batik.anim.values.AnimatablePathDataValue;
/*      */ import org.apache.batik.anim.values.AnimatablePointListValue;
/*      */ import org.apache.batik.anim.values.AnimatablePreserveAspectRatioValue;
/*      */ import org.apache.batik.anim.values.AnimatableRectValue;
/*      */ import org.apache.batik.anim.values.AnimatableStringValue;
/*      */ import org.apache.batik.anim.values.AnimatableValue;
/*      */ import org.apache.batik.css.engine.CSSEngine;
/*      */ import org.apache.batik.css.engine.CSSStylableElement;
/*      */ import org.apache.batik.css.engine.StyleMap;
/*      */ import org.apache.batik.css.engine.value.FloatValue;
/*      */ import org.apache.batik.css.engine.value.Value;
/*      */ import org.apache.batik.css.engine.value.ValueManager;
/*      */ import org.apache.batik.parser.DefaultLengthHandler;
/*      */ import org.apache.batik.parser.DefaultPreserveAspectRatioHandler;
/*      */ import org.apache.batik.parser.FloatArrayProducer;
/*      */ import org.apache.batik.parser.LengthArrayProducer;
/*      */ import org.apache.batik.parser.LengthHandler;
/*      */ import org.apache.batik.parser.LengthListHandler;
/*      */ import org.apache.batik.parser.LengthListParser;
/*      */ import org.apache.batik.parser.LengthParser;
/*      */ import org.apache.batik.parser.NumberListHandler;
/*      */ import org.apache.batik.parser.NumberListParser;
/*      */ import org.apache.batik.parser.ParseException;
/*      */ import org.apache.batik.parser.PathArrayProducer;
/*      */ import org.apache.batik.parser.PathHandler;
/*      */ import org.apache.batik.parser.PathParser;
/*      */ import org.apache.batik.parser.PointsHandler;
/*      */ import org.apache.batik.parser.PointsParser;
/*      */ import org.apache.batik.parser.PreserveAspectRatioHandler;
/*      */ import org.apache.batik.parser.PreserveAspectRatioParser;
/*      */ import org.apache.batik.util.RunnableQueue;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.css.CSSStyleDeclaration;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SVGAnimationEngine
/*      */   extends AnimationEngine
/*      */ {
/*      */   protected BridgeContext ctx;
/*      */   protected CSSEngine cssEngine;
/*      */   protected boolean started;
/*      */   protected AnimationTickRunnable animationTickRunnable;
/*      */   protected float initialStartTime;
/*  132 */   protected UncomputedAnimatableStringValueFactory uncomputedAnimatableStringValueFactory = new UncomputedAnimatableStringValueFactory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   protected AnimatableLengthOrIdentFactory animatableLengthOrIdentFactory = new AnimatableLengthOrIdentFactory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  145 */   protected AnimatableNumberOrIdentFactory animatableNumberOrIdentFactory = new AnimatableNumberOrIdentFactory(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  152 */   protected Factory[] factories = new Factory[] { null, new AnimatableIntegerValueFactory(), new AnimatableNumberValueFactory(), new AnimatableLengthValueFactory(), null, new AnimatableAngleValueFactory(), new AnimatableColorValueFactory(), new AnimatablePaintValueFactory(), null, null, this.uncomputedAnimatableStringValueFactory, null, null, new AnimatableNumberListValueFactory(), new AnimatableLengthListValueFactory(), this.uncomputedAnimatableStringValueFactory, this.uncomputedAnimatableStringValueFactory, this.animatableLengthOrIdentFactory, this.uncomputedAnimatableStringValueFactory, this.uncomputedAnimatableStringValueFactory, this.uncomputedAnimatableStringValueFactory, this.uncomputedAnimatableStringValueFactory, new AnimatablePathDataFactory(), this.uncomputedAnimatableStringValueFactory, null, this.animatableNumberOrIdentFactory, this.uncomputedAnimatableStringValueFactory, null, new AnimatableNumberOrIdentFactory(true), new AnimatableAngleOrIdentFactory(), null, new AnimatablePointListValueFactory(), new AnimatablePreserveAspectRatioValueFactory(), null, this.uncomputedAnimatableStringValueFactory, null, null, null, null, this.animatableLengthOrIdentFactory, this.animatableLengthOrIdentFactory, this.animatableLengthOrIdentFactory, this.animatableLengthOrIdentFactory, this.animatableLengthOrIdentFactory, this.animatableNumberOrIdentFactory, null, null, new AnimatableNumberOrPercentageValueFactory(), null, new AnimatableBooleanValueFactory(), new AnimatableRectValueFactory() };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isSVG12;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  214 */   protected LinkedList initialBridges = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected StyleMap dummyStyleMap;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AnimationThread animationThread;
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
/*  239 */   protected static final Set animationEventNames11 = new HashSet();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  244 */   protected static final Set animationEventNames12 = new HashSet();
/*      */   
/*      */   static {
/*  247 */     String[] eventNamesCommon = { "click", "mousedown", "mouseup", "mouseover", "mousemove", "mouseout", "beginEvent", "endEvent" };
/*      */ 
/*      */ 
/*      */     
/*  251 */     String[] eventNamesSVG11 = { "DOMSubtreeModified", "DOMNodeInserted", "DOMNodeRemoved", "DOMNodeRemovedFromDocument", "DOMNodeInsertedIntoDocument", "DOMAttrModified", "DOMCharacterDataModified", "SVGLoad", "SVGUnload", "SVGAbort", "SVGError", "SVGResize", "SVGScroll", "repeatEvent" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  258 */     String[] eventNamesSVG12 = { "load", "resize", "scroll", "zoom" };
/*      */ 
/*      */     
/*  261 */     for (String anEventNamesCommon : eventNamesCommon) {
/*  262 */       animationEventNames11.add(anEventNamesCommon);
/*  263 */       animationEventNames12.add(anEventNamesCommon);
/*      */     } 
/*  265 */     for (String anEventNamesSVG11 : eventNamesSVG11) {
/*  266 */       animationEventNames11.add(anEventNamesSVG11);
/*      */     }
/*  268 */     for (String anEventNamesSVG12 : eventNamesSVG12) {
/*  269 */       animationEventNames12.add(anEventNamesSVG12);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGAnimationEngine(Document doc, BridgeContext ctx) {
/*  277 */     super(doc);
/*  278 */     this.ctx = ctx;
/*  279 */     SVGOMDocument d = (SVGOMDocument)doc;
/*  280 */     this.cssEngine = d.getCSSEngine();
/*  281 */     this.dummyStyleMap = new StyleMap(this.cssEngine.getNumberOfProperties());
/*  282 */     this.isSVG12 = d.isSVG12();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispose() {
/*  289 */     synchronized (this) {
/*  290 */       pause();
/*  291 */       super.dispose();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addInitialBridge(SVGAnimationElementBridge b) {
/*  300 */     if (this.initialBridges != null) {
/*  301 */       this.initialBridges.add(b);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasStarted() {
/*  309 */     return this.started;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnimatableValue parseAnimatableValue(Element animElt, AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/*      */     int type;
/*  320 */     SVGOMElement elt = (SVGOMElement)target.getElement();
/*      */     
/*  322 */     if (isCSS) {
/*  323 */       type = elt.getPropertyType(ln);
/*      */     } else {
/*  325 */       type = elt.getAttributeType(ns, ln);
/*      */     } 
/*  327 */     Factory factory = this.factories[type];
/*  328 */     if (factory == null) {
/*  329 */       String an = (ns == null) ? ln : ('{' + ns + '}' + ln);
/*  330 */       throw new BridgeException(this.ctx, animElt, "attribute.not.animatable", new Object[] { target.getElement().getNodeName(), an });
/*      */     } 
/*      */ 
/*      */     
/*  334 */     return this.factories[type].createValue(target, ns, ln, isCSS, s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnimatableValue getUnderlyingCSSValue(Element animElt, AnimationTarget target, String pn) {
/*  343 */     ValueManager[] vms = this.cssEngine.getValueManagers();
/*  344 */     int idx = this.cssEngine.getPropertyIndex(pn);
/*  345 */     if (idx != -1) {
/*  346 */       int type = vms[idx].getPropertyType();
/*  347 */       Factory factory = this.factories[type];
/*  348 */       if (factory == null) {
/*  349 */         throw new BridgeException(this.ctx, animElt, "attribute.not.animatable", new Object[] { target.getElement().getNodeName(), pn });
/*      */       }
/*      */ 
/*      */       
/*  353 */       SVGStylableElement e = (SVGStylableElement)target.getElement();
/*  354 */       CSSStyleDeclaration over = e.getOverrideStyle();
/*  355 */       String oldValue = over.getPropertyValue(pn);
/*  356 */       if (oldValue != null) {
/*  357 */         over.removeProperty(pn);
/*      */       }
/*  359 */       Value v = this.cssEngine.getComputedStyle((CSSStylableElement)e, null, idx);
/*  360 */       if (oldValue != null && !oldValue.equals("")) {
/*  361 */         over.setProperty(pn, oldValue, null);
/*      */       }
/*  363 */       return this.factories[type].createValue(target, pn, v);
/*      */     } 
/*      */     
/*  366 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void pause() {
/*  373 */     super.pause();
/*  374 */     UpdateManager um = this.ctx.getUpdateManager();
/*  375 */     if (um != null) {
/*  376 */       um.getUpdateRunnableQueue().setIdleRunnable(null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unpause() {
/*  384 */     super.unpause();
/*  385 */     UpdateManager um = this.ctx.getUpdateManager();
/*  386 */     if (um != null) {
/*  387 */       um.getUpdateRunnableQueue().setIdleRunnable(this.animationTickRunnable);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getCurrentTime() {
/*  395 */     boolean p = (this.pauseTime != 0L);
/*  396 */     unpause();
/*  397 */     float t = this.timedDocumentRoot.getCurrentTime();
/*  398 */     if (p) {
/*  399 */       pause();
/*      */     }
/*  401 */     return Float.isNaN(t) ? 0.0F : t;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float setCurrentTime(float t) {
/*  408 */     if (this.started) {
/*  409 */       float ret = super.setCurrentTime(t);
/*  410 */       if (this.animationTickRunnable != null) {
/*  411 */         this.animationTickRunnable.resume();
/*      */       }
/*  413 */       return ret;
/*      */     } 
/*  415 */     this.initialStartTime = t;
/*  416 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TimedDocumentRoot createDocumentRoot() {
/*  424 */     return new AnimationRoot();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void start(long documentStartTime) {
/*  431 */     if (this.started) {
/*      */       return;
/*      */     }
/*  434 */     this.started = true;
/*      */     try {
/*      */       try {
/*  437 */         Calendar cal = Calendar.getInstance();
/*  438 */         cal.setTime(new Date(documentStartTime));
/*  439 */         this.timedDocumentRoot.resetDocument(cal);
/*  440 */         Object[] bridges = this.initialBridges.toArray();
/*  441 */         this.initialBridges = null;
/*  442 */         for (Object bridge2 : bridges) {
/*  443 */           SVGAnimationElementBridge bridge = (SVGAnimationElementBridge)bridge2;
/*      */           
/*  445 */           bridge.initializeAnimation();
/*      */         } 
/*  447 */         for (Object bridge1 : bridges) {
/*  448 */           SVGAnimationElementBridge bridge = (SVGAnimationElementBridge)bridge1;
/*      */           
/*  450 */           bridge.initializeTimedElement();
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  456 */         UpdateManager um = this.ctx.getUpdateManager();
/*  457 */         if (um != null) {
/*  458 */           RunnableQueue q = um.getUpdateRunnableQueue();
/*  459 */           this.animationTickRunnable = new AnimationTickRunnable(q, this);
/*  460 */           q.setIdleRunnable(this.animationTickRunnable);
/*  461 */           if (this.initialStartTime != 0.0F) {
/*  462 */             setCurrentTime(this.initialStartTime);
/*      */           }
/*      */         } 
/*  465 */       } catch (AnimationException ex) {
/*  466 */         throw new BridgeException(this.ctx, ex.getElement().getElement(), ex.getMessage());
/*      */       }
/*      */     
/*  469 */     } catch (Exception ex) {
/*  470 */       if (this.ctx.getUserAgent() == null) {
/*  471 */         ex.printStackTrace();
/*      */       } else {
/*  473 */         this.ctx.getUserAgent().displayError(ex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAnimationLimitingNone() {
/*  482 */     this.animationLimitingMode = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAnimationLimitingCPU(float pc) {
/*  490 */     this.animationLimitingMode = 1;
/*  491 */     this.animationLimitingAmount = pc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAnimationLimitingFPS(float fps) {
/*  499 */     this.animationLimitingMode = 2;
/*  500 */     this.animationLimitingAmount = fps;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimationRoot
/*      */     extends TimedDocumentRoot
/*      */   {
/*      */     public AnimationRoot() {
/*  512 */       super(!SVGAnimationEngine.this.isSVG12, SVGAnimationEngine.this.isSVG12);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String getEventNamespaceURI(String eventName) {
/*  520 */       if (!SVGAnimationEngine.this.isSVG12) {
/*  521 */         return null;
/*      */       }
/*  523 */       if (eventName.equals("focusin") || eventName.equals("focusout") || eventName.equals("activate") || SVGAnimationEngine.animationEventNames12.contains(eventName))
/*      */       {
/*      */ 
/*      */         
/*  527 */         return "http://www.w3.org/2001/xml-events";
/*      */       }
/*  529 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String getEventType(String eventName) {
/*  537 */       if (eventName.equals("focusin"))
/*  538 */         return "DOMFocusIn"; 
/*  539 */       if (eventName.equals("focusout"))
/*  540 */         return "DOMFocusOut"; 
/*  541 */       if (eventName.equals("activate")) {
/*  542 */         return "DOMActivate";
/*      */       }
/*  544 */       if (SVGAnimationEngine.this.isSVG12) {
/*  545 */         if (SVGAnimationEngine.animationEventNames12.contains(eventName)) {
/*  546 */           return eventName;
/*      */         }
/*      */       }
/*  549 */       else if (SVGAnimationEngine.animationEventNames11.contains(eventName)) {
/*  550 */         return eventName;
/*      */       } 
/*      */       
/*  553 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String getRepeatEventName() {
/*  561 */       return "repeatEvent";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void fireTimeEvent(String eventType, Calendar time, int detail) {
/*  572 */       AnimationSupport.fireTimeEvent((EventTarget)SVGAnimationEngine.this.document, eventType, time, detail);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void toActive(float begin) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void toInactive(boolean stillActive, boolean isFrozen) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void removeFill() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void sampledAt(float simpleTime, float simpleDur, int repeatIteration) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void sampledLastValue(int repeatIteration) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected TimedElement getTimedElementById(String id) {
/*  626 */       return AnimationSupport.getTimedElementById(id, SVGAnimationEngine.this.document);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected EventTarget getEventTargetById(String id) {
/*  633 */       return AnimationSupport.getEventTargetById(id, SVGAnimationEngine.this.document);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected EventTarget getAnimationEventTarget() {
/*  641 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected EventTarget getRootEventTarget() {
/*  649 */       return (EventTarget)SVGAnimationEngine.this.document;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Element getElement() {
/*  657 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBefore(TimedElement other) {
/*  665 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void currentIntervalWillUpdate() {
/*  673 */       if (SVGAnimationEngine.this.animationTickRunnable != null) {
/*  674 */         SVGAnimationEngine.this.animationTickRunnable.resume();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class DebugAnimationTickRunnable
/*      */     extends AnimationTickRunnable
/*      */   {
/*  684 */     float t = 0.0F;
/*      */     
/*      */     public DebugAnimationTickRunnable(RunnableQueue q, SVGAnimationEngine eng) {
/*  687 */       super(q, eng);
/*  688 */       this.waitTime = Long.MAX_VALUE;
/*  689 */       (new Thread() {
/*      */           public void run() {
/*  691 */             BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
/*  692 */             System.out.println("Enter times.");
/*      */             while (true) {
/*      */               String str;
/*      */               try {
/*  696 */                 str = r.readLine();
/*  697 */               } catch (IOException e) {
/*  698 */                 str = null;
/*      */               } 
/*  700 */               if (str == null) {
/*  701 */                 System.exit(0);
/*      */               }
/*  703 */               SVGAnimationEngine.DebugAnimationTickRunnable.this.t = Float.parseFloat(str);
/*  704 */               SVGAnimationEngine.DebugAnimationTickRunnable.this.resume();
/*      */             } 
/*      */           }
/*      */         }).start();
/*      */     }
/*      */     
/*      */     public void resume() {
/*  711 */       this.waitTime = 0L;
/*  712 */       Object lock = this.q.getIteratorLock();
/*  713 */       synchronized (lock) {
/*  714 */         lock.notify();
/*      */       } 
/*      */     }
/*      */     
/*      */     public long getWaitTime() {
/*  719 */       long wt = this.waitTime;
/*  720 */       this.waitTime = Long.MAX_VALUE;
/*  721 */       return wt;
/*      */     }
/*      */     
/*      */     public void run() {
/*  725 */       SVGAnimationEngine eng = getAnimationEngine();
/*  726 */       synchronized (eng) {
/*      */         try {
/*      */           try {
/*  729 */             eng.tick(this.t, false);
/*  730 */           } catch (AnimationException ex) {
/*  731 */             throw new BridgeException(eng.ctx, ex.getElement().getElement(), ex.getMessage());
/*      */           }
/*      */         
/*      */         }
/*  735 */         catch (Exception ex) {
/*  736 */           if (eng.ctx.getUserAgent() == null) {
/*  737 */             ex.printStackTrace();
/*      */           } else {
/*  739 */             eng.ctx.getUserAgent().displayError(ex);
/*      */           } 
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
/*      */   
/*      */   protected static class AnimationTickRunnable
/*      */     implements RunnableQueue.IdleRunnable
/*      */   {
/*  756 */     protected Calendar time = Calendar.getInstance();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected long waitTime;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected RunnableQueue q;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int NUM_TIMES = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  789 */     protected long[] times = new long[8];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected long sumTime;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int timeIndex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected WeakReference engRef;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected static final int MAX_EXCEPTION_COUNT = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int exceptionCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimationTickRunnable(RunnableQueue q, SVGAnimationEngine eng) {
/*  825 */       this.q = q;
/*  826 */       this.engRef = new WeakReference<SVGAnimationEngine>(eng);
/*      */       
/*  828 */       Arrays.fill(this.times, 100L);
/*  829 */       this.sumTime = 800L;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void resume() {
/*  837 */       this.waitTime = 0L;
/*  838 */       Object lock = this.q.getIteratorLock();
/*  839 */       synchronized (lock) {
/*  840 */         lock.notify();
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
/*      */     public long getWaitTime() {
/*  853 */       return this.waitTime;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*  860 */       SVGAnimationEngine eng = getAnimationEngine();
/*  861 */       synchronized (eng) {
/*  862 */         int animationLimitingMode = eng.animationLimitingMode;
/*  863 */         float animationLimitingAmount = eng.animationLimitingAmount;
/*      */         try {
/*      */           try {
/*  866 */             long before = System.currentTimeMillis();
/*  867 */             this.time.setTime(new Date(before));
/*  868 */             float t = eng.timedDocumentRoot.convertWallclockTime(this.time);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  874 */             float t2 = eng.tick(t, false);
/*  875 */             long after = System.currentTimeMillis();
/*  876 */             long dur = after - before;
/*  877 */             if (dur == 0L) {
/*  878 */               dur = 1L;
/*      */             }
/*  880 */             this.sumTime -= this.times[this.timeIndex];
/*  881 */             this.sumTime += dur;
/*  882 */             this.times[this.timeIndex] = dur;
/*  883 */             this.timeIndex = (this.timeIndex + 1) % 8;
/*      */             
/*  885 */             if (t2 == Float.POSITIVE_INFINITY) {
/*  886 */               this.waitTime = Long.MAX_VALUE;
/*      */             } else {
/*  888 */               this.waitTime = before + (long)(t2 * 1000.0F) - 1000L;
/*  889 */               if (this.waitTime < after) {
/*  890 */                 this.waitTime = after;
/*      */               }
/*  892 */               if (animationLimitingMode != 0) {
/*  893 */                 float delay, ave = (float)this.sumTime / 8.0F;
/*      */                 
/*  895 */                 if (animationLimitingMode == 1) {
/*      */                   
/*  897 */                   delay = ave / animationLimitingAmount - ave;
/*      */                 } else {
/*      */                   
/*  900 */                   delay = 1000.0F / animationLimitingAmount - ave;
/*      */                 } 
/*  902 */                 long newWaitTime = after + (long)delay;
/*  903 */                 if (newWaitTime > this.waitTime) {
/*  904 */                   this.waitTime = newWaitTime;
/*      */                 }
/*      */               }
/*      */             
/*      */             } 
/*  909 */           } catch (AnimationException ex) {
/*  910 */             throw new BridgeException(eng.ctx, ex.getElement().getElement(), ex.getMessage());
/*      */           } 
/*      */ 
/*      */           
/*  914 */           this.exceptionCount = 0;
/*  915 */         } catch (Exception ex) {
/*  916 */           if (++this.exceptionCount < 10) {
/*  917 */             if (eng.ctx.getUserAgent() == null) {
/*  918 */               ex.printStackTrace();
/*      */             } else {
/*  920 */               eng.ctx.getUserAgent().displayError(ex);
/*      */             } 
/*      */           }
/*      */         } 
/*      */         
/*  925 */         if (animationLimitingMode == 0) {
/*      */           
/*      */           try {
/*  928 */             Thread.sleep(1L);
/*  929 */           } catch (InterruptedException interruptedException) {}
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected SVGAnimationEngine getAnimationEngine() {
/*  939 */       return this.engRef.get();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimationThread
/*      */     extends Thread
/*      */   {
/*  951 */     protected Calendar time = Calendar.getInstance();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  956 */     protected RunnableQueue runnableQueue = SVGAnimationEngine.this.ctx.getUpdateManager().getUpdateRunnableQueue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  962 */     protected Ticker ticker = new Ticker();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       while (true) {
/*  970 */         this.time.setTime(new Date());
/*  971 */         this.ticker.t = SVGAnimationEngine.this.timedDocumentRoot.convertWallclockTime(this.time);
/*      */         try {
/*  973 */           this.runnableQueue.invokeAndWait(this.ticker);
/*  974 */         } catch (InterruptedException e) {
/*      */           break;
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
/*      */     protected class Ticker
/*      */       implements Runnable
/*      */     {
/*      */       protected float t;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void run() {
/* 1009 */         SVGAnimationEngine.this.tick(this.t, false);
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
/*      */   protected static interface Factory
/*      */   {
/*      */     AnimatableValue createValue(AnimationTarget param1AnimationTarget, String param1String1, String param1String2, boolean param1Boolean, String param1String3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     AnimatableValue createValue(AnimationTarget param1AnimationTarget, String param1String, Value param1Value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract class CSSValueFactory
/*      */     implements Factory
/*      */   {
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/* 1042 */       return createValue(target, ln, createCSSValue(target, ln, s));
/*      */     }
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1047 */       CSSStylableElement elt = (CSSStylableElement)target.getElement();
/* 1048 */       v = computeValue(elt, pn, v);
/* 1049 */       return createAnimatableValue(target, pn, v);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected abstract AnimatableValue createAnimatableValue(AnimationTarget param1AnimationTarget, String param1String, Value param1Value);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Value createCSSValue(AnimationTarget t, String pn, String s) {
/* 1063 */       CSSStylableElement elt = (CSSStylableElement)t.getElement();
/* 1064 */       Value v = SVGAnimationEngine.this.cssEngine.parsePropertyValue(elt, pn, s);
/* 1065 */       return computeValue(elt, pn, v);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Value computeValue(CSSStylableElement elt, String pn, Value v) {
/* 1074 */       ValueManager[] vms = SVGAnimationEngine.this.cssEngine.getValueManagers();
/* 1075 */       int idx = SVGAnimationEngine.this.cssEngine.getPropertyIndex(pn);
/* 1076 */       if (idx != -1) {
/* 1077 */         if (v.getCssValueType() == 0) {
/* 1078 */           elt = CSSEngine.getParentCSSStylableElement((Element)elt);
/* 1079 */           if (elt != null) {
/* 1080 */             return SVGAnimationEngine.this.cssEngine.getComputedStyle(elt, null, idx);
/*      */           }
/* 1082 */           return vms[idx].getDefaultValue();
/*      */         } 
/* 1084 */         v = vms[idx].computeValue(elt, null, SVGAnimationEngine.this.cssEngine, idx, SVGAnimationEngine.this.dummyStyleMap, v);
/*      */       } 
/*      */       
/* 1087 */       return v;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableBooleanValueFactory
/*      */     implements Factory
/*      */   {
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/* 1101 */       return (AnimatableValue)new AnimatableBooleanValue(target, "true".equals(s));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1109 */       return (AnimatableValue)new AnimatableBooleanValue(target, "true".equals(v.getCssText()));
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
/*      */   protected class AnimatableIntegerValueFactory
/*      */     implements Factory
/*      */   {
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/* 1124 */       return (AnimatableValue)new AnimatableIntegerValue(target, Integer.parseInt(s));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1132 */       return (AnimatableValue)new AnimatableIntegerValue(target, Math.round(v.getFloatValue()));
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
/*      */   protected class AnimatableNumberValueFactory
/*      */     implements Factory
/*      */   {
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/* 1147 */       return (AnimatableValue)new AnimatableNumberValue(target, Float.parseFloat(s));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1155 */       return (AnimatableValue)new AnimatableNumberValue(target, v.getFloatValue());
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
/*      */   protected class AnimatableNumberOrPercentageValueFactory
/*      */     implements Factory
/*      */   {
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/*      */       float v;
/*      */       boolean pc;
/* 1172 */       if (s.charAt(s.length() - 1) == '%') {
/* 1173 */         v = Float.parseFloat(s.substring(0, s.length() - 1));
/* 1174 */         pc = true;
/*      */       } else {
/* 1176 */         v = Float.parseFloat(s);
/* 1177 */         pc = false;
/*      */       } 
/* 1179 */       return (AnimatableValue)new AnimatableNumberOrPercentageValue(target, v, pc);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1187 */       switch (v.getPrimitiveType()) {
/*      */         case 2:
/* 1189 */           return (AnimatableValue)new AnimatableNumberOrPercentageValue(target, v.getFloatValue(), true);
/*      */         
/*      */         case 1:
/* 1192 */           return (AnimatableValue)new AnimatableNumberOrPercentageValue(target, v.getFloatValue());
/*      */       } 
/*      */ 
/*      */       
/* 1196 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatablePreserveAspectRatioValueFactory
/*      */     implements Factory
/*      */   {
/*      */     protected short align;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected short meetOrSlice;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1218 */     protected PreserveAspectRatioParser parser = new PreserveAspectRatioParser();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1224 */     protected DefaultPreserveAspectRatioHandler handler = new DefaultPreserveAspectRatioHandler()
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void startPreserveAspectRatio() throws ParseException
/*      */         {
/* 1232 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 0;
/* 1233 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.meetOrSlice = 0;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void none() throws ParseException {
/* 1240 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMaxYMax() throws ParseException {
/* 1247 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 10;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMaxYMid() throws ParseException {
/* 1254 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 7;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMaxYMin() throws ParseException {
/* 1261 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 4;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMidYMax() throws ParseException {
/* 1268 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 9;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMidYMid() throws ParseException {
/* 1275 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 6;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMidYMin() throws ParseException {
/* 1282 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 3;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMinYMax() throws ParseException {
/* 1289 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 8;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMinYMid() throws ParseException {
/* 1296 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 5;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void xMinYMin() throws ParseException {
/* 1303 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.align = 2;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void meet() throws ParseException {
/* 1310 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.meetOrSlice = 1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void slice() throws ParseException {
/* 1317 */           SVGAnimationEngine.AnimatablePreserveAspectRatioValueFactory.this.meetOrSlice = 2;
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatablePreserveAspectRatioValueFactory() {
/* 1325 */       this.parser.setPreserveAspectRatioHandler((PreserveAspectRatioHandler)this.handler);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/*      */       try {
/* 1334 */         this.parser.parse(s);
/* 1335 */         return (AnimatableValue)new AnimatablePreserveAspectRatioValue(target, this.align, this.meetOrSlice);
/*      */       }
/* 1337 */       catch (ParseException e) {
/*      */         
/* 1339 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1349 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableLengthValueFactory
/*      */     implements Factory
/*      */   {
/*      */     protected short type;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected float value;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1371 */     protected LengthParser parser = new LengthParser();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1376 */     protected LengthHandler handler = (LengthHandler)new DefaultLengthHandler() {
/*      */         public void startLength() throws ParseException {
/* 1378 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 1;
/*      */         }
/*      */         public void lengthValue(float v) throws ParseException {
/* 1381 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.value = v;
/*      */         }
/*      */         public void em() throws ParseException {
/* 1384 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 3;
/*      */         }
/*      */         public void ex() throws ParseException {
/* 1387 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 4;
/*      */         }
/*      */         public void in() throws ParseException {
/* 1390 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 8;
/*      */         }
/*      */         public void cm() throws ParseException {
/* 1393 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 6;
/*      */         }
/*      */         public void mm() throws ParseException {
/* 1396 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 7;
/*      */         }
/*      */         public void pc() throws ParseException {
/* 1399 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 10;
/*      */         }
/*      */         public void pt() throws ParseException {
/* 1402 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 9;
/*      */         }
/*      */         public void px() throws ParseException {
/* 1405 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 5;
/*      */         }
/*      */         public void percentage() throws ParseException {
/* 1408 */           SVGAnimationEngine.AnimatableLengthValueFactory.this.type = 2;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void endLength() throws ParseException {}
/*      */       };
/*      */ 
/*      */     
/*      */     public AnimatableLengthValueFactory() {
/* 1418 */       this.parser.setLengthHandler(this.handler);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/* 1426 */       short pcInterp = target.getPercentageInterpretation(ns, ln, isCSS);
/*      */       try {
/* 1428 */         this.parser.parse(s);
/* 1429 */         return (AnimatableValue)new AnimatableLengthValue(target, this.type, this.value, pcInterp);
/*      */       }
/* 1431 */       catch (ParseException e) {
/*      */         
/* 1433 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1442 */       return (AnimatableValue)new AnimatableIntegerValue(target, Math.round(v.getFloatValue()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableLengthListValueFactory
/*      */     implements Factory
/*      */   {
/* 1455 */     protected LengthListParser parser = new LengthListParser();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1460 */     protected LengthArrayProducer producer = new LengthArrayProducer();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableLengthListValueFactory() {
/* 1466 */       this.parser.setLengthListHandler((LengthListHandler)this.producer);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/*      */       try {
/* 1475 */         short pcInterp = target.getPercentageInterpretation(ns, ln, isCSS);
/*      */         
/* 1477 */         this.parser.parse(s);
/* 1478 */         return (AnimatableValue)new AnimatableLengthListValue(target, this.producer.getLengthTypeArray(), this.producer.getLengthValueArray(), pcInterp);
/*      */ 
/*      */       
/*      */       }
/* 1482 */       catch (ParseException e) {
/*      */         
/* 1484 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1494 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableNumberListValueFactory
/*      */     implements Factory
/*      */   {
/* 1506 */     protected NumberListParser parser = new NumberListParser();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1511 */     protected FloatArrayProducer producer = new FloatArrayProducer();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableNumberListValueFactory() {
/* 1517 */       this.parser.setNumberListHandler((NumberListHandler)this.producer);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/*      */       try {
/* 1526 */         this.parser.parse(s);
/* 1527 */         return (AnimatableValue)new AnimatableNumberListValue(target, this.producer.getFloatArray());
/*      */       }
/* 1529 */       catch (ParseException e) {
/*      */         
/* 1531 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1541 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableRectValueFactory
/*      */     implements Factory
/*      */   {
/* 1553 */     protected NumberListParser parser = new NumberListParser();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1558 */     protected FloatArrayProducer producer = new FloatArrayProducer();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableRectValueFactory() {
/* 1564 */       this.parser.setNumberListHandler((NumberListHandler)this.producer);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/*      */       try {
/* 1573 */         this.parser.parse(s);
/* 1574 */         float[] r = this.producer.getFloatArray();
/* 1575 */         if (r.length != 4)
/*      */         {
/* 1577 */           return null;
/*      */         }
/* 1579 */         return (AnimatableValue)new AnimatableRectValue(target, r[0], r[1], r[2], r[3]);
/* 1580 */       } catch (ParseException e) {
/*      */         
/* 1582 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1592 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatablePointListValueFactory
/*      */     implements Factory
/*      */   {
/* 1604 */     protected PointsParser parser = new PointsParser();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1609 */     protected FloatArrayProducer producer = new FloatArrayProducer();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatablePointListValueFactory() {
/* 1615 */       this.parser.setPointsHandler((PointsHandler)this.producer);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/*      */       try {
/* 1624 */         this.parser.parse(s);
/* 1625 */         return (AnimatableValue)new AnimatablePointListValue(target, this.producer.getFloatArray());
/*      */       }
/* 1627 */       catch (ParseException e) {
/*      */         
/* 1629 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1639 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatablePathDataFactory
/*      */     implements Factory
/*      */   {
/* 1651 */     protected PathParser parser = new PathParser();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1656 */     protected PathArrayProducer producer = new PathArrayProducer();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatablePathDataFactory() {
/* 1662 */       this.parser.setPathHandler((PathHandler)this.producer);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/*      */       try {
/* 1671 */         this.parser.parse(s);
/* 1672 */         return (AnimatableValue)new AnimatablePathDataValue(target, this.producer.getPathCommands(), this.producer.getPathParameters());
/*      */       
/*      */       }
/* 1675 */       catch (ParseException e) {
/*      */         
/* 1677 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1687 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class UncomputedAnimatableStringValueFactory
/*      */     implements Factory
/*      */   {
/*      */     public AnimatableValue createValue(AnimationTarget target, String ns, String ln, boolean isCSS, String s) {
/* 1698 */       return (AnimatableValue)new AnimatableStringValue(target, s);
/*      */     }
/*      */ 
/*      */     
/*      */     public AnimatableValue createValue(AnimationTarget target, String pn, Value v) {
/* 1703 */       return (AnimatableValue)new AnimatableStringValue(target, v.getCssText());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableLengthOrIdentFactory
/*      */     extends CSSValueFactory
/*      */   {
/*      */     protected AnimatableValue createAnimatableValue(AnimationTarget target, String pn, Value v) {
/* 1714 */       if (v instanceof org.apache.batik.css.engine.value.StringValue) {
/* 1715 */         return (AnimatableValue)new AnimatableLengthOrIdentValue(target, v.getStringValue());
/*      */       }
/*      */       
/* 1718 */       short pcInterp = target.getPercentageInterpretation(null, pn, true);
/* 1719 */       FloatValue fv = (FloatValue)v;
/* 1720 */       return (AnimatableValue)new AnimatableLengthOrIdentValue(target, fv.getPrimitiveType(), fv.getFloatValue(), pcInterp);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableNumberOrIdentFactory
/*      */     extends CSSValueFactory
/*      */   {
/*      */     protected boolean numericIdents;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnimatableNumberOrIdentFactory(boolean numericIdents) {
/* 1737 */       this.numericIdents = numericIdents;
/*      */     }
/*      */ 
/*      */     
/*      */     protected AnimatableValue createAnimatableValue(AnimationTarget target, String pn, Value v) {
/* 1742 */       if (v instanceof org.apache.batik.css.engine.value.StringValue) {
/* 1743 */         return (AnimatableValue)new AnimatableNumberOrIdentValue(target, v.getStringValue());
/*      */       }
/*      */       
/* 1746 */       FloatValue fv = (FloatValue)v;
/* 1747 */       return (AnimatableValue)new AnimatableNumberOrIdentValue(target, fv.getFloatValue(), this.numericIdents);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableAngleValueFactory
/*      */     extends CSSValueFactory
/*      */   {
/*      */     protected AnimatableValue createAnimatableValue(AnimationTarget target, String pn, Value v) {
/*      */       short unit;
/* 1759 */       FloatValue fv = (FloatValue)v;
/*      */       
/* 1761 */       switch (fv.getPrimitiveType()) {
/*      */         case 1:
/*      */         case 11:
/* 1764 */           unit = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1776 */           return (AnimatableValue)new AnimatableAngleValue(target, fv.getFloatValue(), unit);case 12: unit = 3; return (AnimatableValue)new AnimatableAngleValue(target, fv.getFloatValue(), unit);case 13: unit = 4; return (AnimatableValue)new AnimatableAngleValue(target, fv.getFloatValue(), unit);
/*      */       } 
/*      */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   protected class AnimatableAngleOrIdentFactory
/*      */     extends CSSValueFactory
/*      */   {
/*      */     protected AnimatableValue createAnimatableValue(AnimationTarget target, String pn, Value v) {
/*      */       short unit;
/* 1787 */       if (v instanceof org.apache.batik.css.engine.value.StringValue) {
/* 1788 */         return (AnimatableValue)new AnimatableAngleOrIdentValue(target, v.getStringValue());
/*      */       }
/*      */       
/* 1791 */       FloatValue fv = (FloatValue)v;
/*      */       
/* 1793 */       switch (fv.getPrimitiveType()) {
/*      */         case 1:
/*      */         case 11:
/* 1796 */           unit = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1808 */           return (AnimatableValue)new AnimatableAngleOrIdentValue(target, fv.getFloatValue(), unit);case 12: unit = 3; return (AnimatableValue)new AnimatableAngleOrIdentValue(target, fv.getFloatValue(), unit);case 13: unit = 4; return (AnimatableValue)new AnimatableAngleOrIdentValue(target, fv.getFloatValue(), unit);
/*      */       } 
/*      */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableColorValueFactory
/*      */     extends CSSValueFactory
/*      */   {
/*      */     protected AnimatableValue createAnimatableValue(AnimationTarget target, String pn, Value v) {
/* 1820 */       Paint p = PaintServer.convertPaint(target.getElement(), null, v, 1.0F, SVGAnimationEngine.this.ctx);
/*      */       
/* 1822 */       if (p instanceof Color) {
/* 1823 */         Color c = (Color)p;
/* 1824 */         return (AnimatableValue)new AnimatableColorValue(target, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1830 */       return null;
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
/*      */   protected class AnimatablePaintValueFactory
/*      */     extends CSSValueFactory
/*      */   {
/*      */     protected AnimatablePaintValue createColorPaintValue(AnimationTarget t, Color c) {
/* 1845 */       return AnimatablePaintValue.createColorPaintValue(t, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected AnimatableValue createAnimatableValue(AnimationTarget target, String pn, Value v) {
/* 1852 */       if (v.getCssValueType() == 1) {
/* 1853 */         Paint p; switch (v.getPrimitiveType()) {
/*      */           case 21:
/* 1855 */             return (AnimatableValue)AnimatablePaintValue.createNonePaintValue(target);
/*      */           case 25:
/* 1857 */             p = PaintServer.convertPaint(target.getElement(), null, v, 1.0F, SVGAnimationEngine.this.ctx);
/*      */             
/* 1859 */             return (AnimatableValue)createColorPaintValue(target, (Color)p);
/*      */           
/*      */           case 20:
/* 1862 */             return (AnimatableValue)AnimatablePaintValue.createURIPaintValue(target, v.getStringValue());
/*      */         } 
/*      */       } else {
/*      */         Paint p; Value v2; Paint paint1;
/* 1866 */         Value v1 = v.item(0);
/* 1867 */         switch (v1.getPrimitiveType()) {
/*      */           case 25:
/* 1869 */             p = PaintServer.convertPaint(target.getElement(), null, v, 1.0F, SVGAnimationEngine.this.ctx);
/*      */             
/* 1871 */             return (AnimatableValue)createColorPaintValue(target, (Color)p);
/*      */           
/*      */           case 20:
/* 1874 */             v2 = v.item(1);
/* 1875 */             switch (v2.getPrimitiveType()) {
/*      */               case 21:
/* 1877 */                 return (AnimatableValue)AnimatablePaintValue.createURINonePaintValue(target, v1.getStringValue());
/*      */               
/*      */               case 25:
/* 1880 */                 paint1 = PaintServer.convertPaint(target.getElement(), null, v.item(1), 1.0F, SVGAnimationEngine.this.ctx);
/*      */                 
/* 1882 */                 return (AnimatableValue)createColorPaintValue(target, (Color)paint1);
/*      */             } 
/*      */             
/*      */             break;
/*      */         } 
/*      */       
/*      */       } 
/* 1889 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AnimatableStringValueFactory
/*      */     extends CSSValueFactory
/*      */   {
/*      */     protected AnimatableValue createAnimatableValue(AnimationTarget target, String pn, Value v) {
/* 1900 */       return (AnimatableValue)new AnimatableStringValue(target, v.getCssText());
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAnimationEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */