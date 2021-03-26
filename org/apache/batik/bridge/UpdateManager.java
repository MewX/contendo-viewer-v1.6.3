/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.bridge.svg12.DefaultXBLManager;
/*     */ import org.apache.batik.bridge.svg12.SVG12BridgeContext;
/*     */ import org.apache.batik.bridge.svg12.SVG12ScriptingEnvironment;
/*     */ import org.apache.batik.dom.events.AbstractEvent;
/*     */ import org.apache.batik.dom.xbl.XBLManager;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.RootGraphicsNode;
/*     */ import org.apache.batik.gvt.UpdateTracker;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeChangeListener;
/*     */ import org.apache.batik.gvt.renderer.ImageRenderer;
/*     */ import org.apache.batik.util.EventDispatcher;
/*     */ import org.apache.batik.util.RunnableQueue;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.events.DocumentEvent;
/*     */ import org.w3c.dom.events.Event;
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
/*     */ public class UpdateManager
/*     */ {
/*     */   static final int MIN_REPAINT_TIME;
/*     */   protected BridgeContext bridgeContext;
/*     */   protected Document document;
/*     */   protected RunnableQueue updateRunnableQueue;
/*     */   protected RunnableQueue.RunHandler runHandler;
/*     */   protected volatile boolean running;
/*     */   protected volatile boolean suspendCalled;
/*     */   
/*     */   static {
/*  59 */     int value = 20;
/*     */     
/*  61 */     try { String s = System.getProperty("org.apache.batik.min_repaint_time", "20");
/*     */       
/*  63 */       value = Integer.parseInt(s); }
/*  64 */     catch (SecurityException securityException) {  }
/*  65 */     catch (NumberFormatException numberFormatException) {  }
/*     */     finally
/*  67 */     { MIN_REPAINT_TIME = value; }
/*     */   
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   protected List listeners = Collections.synchronizedList(new LinkedList());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ScriptingEnvironment scriptingEnvironment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RepaintManager repaintManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected UpdateTracker updateTracker;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode graphicsNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean started;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BridgeContext[] secondaryBridgeContexts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ScriptingEnvironment[] secondaryScriptingEnvironments;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int minRepaintTime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long outOfDateTime;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   List suspensionList;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int nextSuspensionIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long allResumeTime;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Timer repaintTriggerTimer;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TimerTask repaintTimerTask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinRepaintTime() {
/* 190 */     return this.minRepaintTime;
/*     */   }
/*     */   
/*     */   public void setMinRepaintTime(int minRepaintTime) {
/* 194 */     this.minRepaintTime = minRepaintTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ScriptingEnvironment initializeScriptingEnvironment(BridgeContext ctx) {
/*     */     ScriptingEnvironment se;
/* 203 */     SVGOMDocument d = (SVGOMDocument)ctx.getDocument();
/*     */     
/* 205 */     if (d.isSVG12()) {
/* 206 */       SVG12ScriptingEnvironment sVG12ScriptingEnvironment = new SVG12ScriptingEnvironment(ctx);
/* 207 */       ctx.xblManager = (XBLManager)new DefaultXBLManager((Document)d, ctx);
/* 208 */       d.setXBLManager(ctx.xblManager);
/*     */     } else {
/* 210 */       se = new ScriptingEnvironment(ctx);
/*     */     } 
/* 212 */     return se;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void dispatchSVGLoadEvent() throws InterruptedException {
/* 220 */     dispatchSVGLoadEvent(this.bridgeContext, this.scriptingEnvironment);
/* 221 */     for (int i = 0; i < this.secondaryScriptingEnvironments.length; i++) {
/* 222 */       BridgeContext ctx = this.secondaryBridgeContexts[i];
/* 223 */       if (((SVGOMDocument)ctx.getDocument()).isSVG12()) {
/*     */ 
/*     */         
/* 226 */         ScriptingEnvironment se = this.secondaryScriptingEnvironments[i];
/* 227 */         dispatchSVGLoadEvent(ctx, se);
/*     */       } 
/* 229 */     }  this.secondaryBridgeContexts = null;
/* 230 */     this.secondaryScriptingEnvironments = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchSVGLoadEvent(BridgeContext ctx, ScriptingEnvironment se) {
/* 238 */     se.loadScripts();
/* 239 */     se.dispatchSVGLoadEvent();
/* 240 */     if (ctx.isSVG12() && ctx.xblManager != null) {
/* 241 */       SVG12BridgeContext ctx12 = (SVG12BridgeContext)ctx;
/* 242 */       ctx12.addBindingListener();
/* 243 */       ctx12.xblManager.startProcessing();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatchSVGZoomEvent() throws InterruptedException {
/* 252 */     this.scriptingEnvironment.dispatchSVGZoomEvent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatchSVGScrollEvent() throws InterruptedException {
/* 260 */     this.scriptingEnvironment.dispatchSVGScrollEvent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatchSVGResizeEvent() throws InterruptedException {
/* 268 */     this.scriptingEnvironment.dispatchSVGResizeEvent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void manageUpdates(final ImageRenderer r) {
/* 275 */     this.updateRunnableQueue.preemptLater(new Runnable() {
/*     */           public void run() {
/* 277 */             synchronized (UpdateManager.this) {
/* 278 */               UpdateManager.this.running = true;
/*     */               
/* 280 */               UpdateManager.this.updateTracker = new UpdateTracker();
/* 281 */               RootGraphicsNode root = UpdateManager.this.graphicsNode.getRoot();
/* 282 */               if (root != null) {
/* 283 */                 root.addTreeGraphicsNodeChangeListener((GraphicsNodeChangeListener)UpdateManager.this.updateTracker);
/*     */               }
/*     */ 
/*     */               
/* 287 */               UpdateManager.this.repaintManager = new RepaintManager(r);
/*     */ 
/*     */               
/* 290 */               UpdateManagerEvent ev = new UpdateManagerEvent(UpdateManager.this, null, null);
/*     */               
/* 292 */               UpdateManager.this.fireEvent(UpdateManager.startedDispatcher, ev);
/* 293 */               UpdateManager.this.started = true;
/*     */             } 
/*     */           }
/*     */         });
/* 297 */     resume();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BridgeContext getBridgeContext() {
/* 305 */     return this.bridgeContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RunnableQueue getUpdateRunnableQueue() {
/* 312 */     return this.updateRunnableQueue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RepaintManager getRepaintManager() {
/* 319 */     return this.repaintManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UpdateTracker getUpdateTracker() {
/* 326 */     return this.updateTracker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document getDocument() {
/* 333 */     return this.document;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ScriptingEnvironment getScriptingEnvironment() {
/* 340 */     return this.scriptingEnvironment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isRunning() {
/* 347 */     return this.running;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void suspend() {
/* 355 */     if (this.updateRunnableQueue.getQueueState() == RunnableQueue.RUNNING) {
/* 356 */       this.updateRunnableQueue.suspendExecution(false);
/*     */     }
/* 358 */     this.suspendCalled = true;
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
/*     */   public synchronized void resume() {
/* 374 */     if (this.updateRunnableQueue.getQueueState() != RunnableQueue.RUNNING) {
/* 375 */       this.updateRunnableQueue.resumeExecution();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void interrupt() {
/* 383 */     Runnable r = new Runnable() {
/*     */         public void run() {
/* 385 */           synchronized (UpdateManager.this) {
/* 386 */             if (UpdateManager.this.started) {
/* 387 */               UpdateManager.this.dispatchSVGUnLoadEvent();
/*     */             } else {
/* 389 */               UpdateManager.this.running = false;
/* 390 */               UpdateManager.this.scriptingEnvironment.interrupt();
/* 391 */               UpdateManager.this.updateRunnableQueue.getThread().halt();
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/*     */     try {
/* 398 */       this.updateRunnableQueue.preemptLater(r);
/* 399 */       this.updateRunnableQueue.resumeExecution();
/* 400 */     } catch (IllegalStateException illegalStateException) {}
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
/*     */   public void dispatchSVGUnLoadEvent() {
/* 412 */     if (!this.started) {
/* 413 */       throw new IllegalStateException("UpdateManager not started.");
/*     */     }
/*     */ 
/*     */     
/* 417 */     this.updateRunnableQueue.preemptLater(new Runnable() {
/*     */           public void run() {
/* 419 */             synchronized (UpdateManager.this) {
/* 420 */               String type; AbstractEvent evt = (AbstractEvent)((DocumentEvent)UpdateManager.this.document).createEvent("SVGEvents");
/*     */ 
/*     */               
/* 423 */               if (UpdateManager.this.bridgeContext.isSVG12()) {
/* 424 */                 type = "unload";
/*     */               } else {
/* 426 */                 type = "SVGUnload";
/*     */               } 
/* 428 */               evt.initEventNS("http://www.w3.org/2001/xml-events", type, false, false);
/*     */ 
/*     */ 
/*     */               
/* 432 */               ((EventTarget)UpdateManager.this.document.getDocumentElement()).dispatchEvent((Event)evt);
/*     */               
/* 434 */               UpdateManager.this.running = false;
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 439 */               UpdateManager.this.scriptingEnvironment.interrupt();
/* 440 */               UpdateManager.this.updateRunnableQueue.getThread().halt();
/* 441 */               UpdateManager.this.bridgeContext.dispose();
/*     */ 
/*     */               
/* 444 */               UpdateManagerEvent ev = new UpdateManagerEvent(UpdateManager.this, null, null);
/*     */               
/* 446 */               UpdateManager.this.fireEvent(UpdateManager.stoppedDispatcher, ev);
/*     */             } 
/*     */           }
/*     */         });
/* 450 */     resume();
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
/*     */   public void updateRendering(AffineTransform u2d, boolean dbr, Shape aoi, int width, int height) {
/* 467 */     this.repaintManager.setupRenderer(u2d, dbr, aoi, width, height);
/* 468 */     List<Shape> l = new ArrayList(1);
/* 469 */     l.add(aoi);
/* 470 */     updateRendering(l, false);
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
/*     */   public void updateRendering(AffineTransform u2d, boolean dbr, boolean cpt, Shape aoi, int width, int height) {
/* 490 */     this.repaintManager.setupRenderer(u2d, dbr, aoi, width, height);
/* 491 */     List<Shape> l = new ArrayList(1);
/* 492 */     l.add(aoi);
/* 493 */     updateRendering(l, cpt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateRendering(List areas, boolean clearPaintingTransform) {
/*     */     
/* 505 */     try { UpdateManagerEvent ev = new UpdateManagerEvent(this, this.repaintManager.getOffScreen(), null);
/*     */       
/* 507 */       fireEvent(updateStartedDispatcher, ev);
/*     */       
/* 509 */       Collection<?> c = this.repaintManager.updateRendering(areas);
/* 510 */       List l = new ArrayList(c);
/*     */       
/* 512 */       ev = new UpdateManagerEvent(this, this.repaintManager.getOffScreen(), l, clearPaintingTransform);
/*     */ 
/*     */       
/* 515 */       fireEvent(updateCompletedDispatcher, ev); }
/* 516 */     catch (ThreadDeath td)
/* 517 */     { UpdateManagerEvent ev = new UpdateManagerEvent(this, null, null);
/*     */       
/* 519 */       fireEvent(updateFailedDispatcher, ev);
/* 520 */       throw td; }
/* 521 */     catch (Throwable t)
/* 522 */     { UpdateManagerEvent ev = new UpdateManagerEvent(this, null, null);
/*     */       
/* 524 */       fireEvent(updateFailedDispatcher, ev); } 
/*     */   }
/*     */   protected void repaint() { if (!this.updateTracker.hasChanged()) { this.outOfDateTime = 0L; return; }  long ctime = System.currentTimeMillis(); if (ctime < this.allResumeTime) { createRepaintTimer(); return; }  if (this.allResumeTime > 0L)
/*     */       releaseAllRedrawSuspension();  if (ctime - this.outOfDateTime < this.minRepaintTime)
/*     */       synchronized (this.updateRunnableQueue.getIteratorLock()) { Iterator i = this.updateRunnableQueue.iterator(); while (i.hasNext()) { if (!(i.next() instanceof NoRepaintRunnable))
/*     */             return;  }  }   List dirtyAreas = this.updateTracker.getDirtyAreas(); this.updateTracker.clear(); if (dirtyAreas != null)
/*     */       updateRendering(dirtyAreas, false);  this.outOfDateTime = 0L; } public void forceRepaint() { if (!this.updateTracker.hasChanged()) { this.outOfDateTime = 0L; return; }
/*     */      List dirtyAreas = this.updateTracker.getDirtyAreas(); this.updateTracker.clear(); if (dirtyAreas != null)
/* 532 */       updateRendering(dirtyAreas, false);  this.outOfDateTime = 0L; } public UpdateManager(BridgeContext ctx, GraphicsNode gn, Document doc) { this.outOfDateTime = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 638 */     this.suspensionList = new ArrayList();
/* 639 */     this.nextSuspensionIndex = 1;
/* 640 */     this.allResumeTime = -1L;
/* 641 */     this.repaintTriggerTimer = null;
/* 642 */     this.repaintTimerTask = null; this.bridgeContext = ctx; this.bridgeContext.setUpdateManager(this); this.document = doc; this.updateRunnableQueue = RunnableQueue.createRunnableQueue(); this.runHandler = createRunHandler(); this.updateRunnableQueue.setRunHandler(this.runHandler); this.graphicsNode = gn; this.scriptingEnvironment = initializeScriptingEnvironment(this.bridgeContext); this.secondaryBridgeContexts = (BridgeContext[])ctx.getChildContexts().clone(); this.secondaryScriptingEnvironments = new ScriptingEnvironment[this.secondaryBridgeContexts.length]; for (int i = 0; i < this.secondaryBridgeContexts.length; i++) { BridgeContext resCtx = this.secondaryBridgeContexts[i]; if (((SVGOMDocument)resCtx.getDocument()).isSVG12()) {
/*     */         resCtx.setUpdateManager(this); ScriptingEnvironment se = initializeScriptingEnvironment(resCtx); this.secondaryScriptingEnvironments[i] = se;
/*     */       }  }
/* 645 */      this.minRepaintTime = MIN_REPAINT_TIME; } void createRepaintTimer() { if (this.repaintTimerTask != null)
/* 646 */       return;  if (this.allResumeTime < 0L)
/* 647 */       return;  if (this.repaintTriggerTimer == null) {
/* 648 */       this.repaintTriggerTimer = new Timer(true);
/*     */     }
/* 650 */     long delay = this.allResumeTime - System.currentTimeMillis();
/* 651 */     if (delay < 0L) delay = 0L; 
/* 652 */     this.repaintTimerTask = new RepaintTimerTask(this);
/* 653 */     this.repaintTriggerTimer.schedule(this.repaintTimerTask, delay); } protected class SuspensionInfo {
/*     */     int index; long resumeMilli; public SuspensionInfo(int index, long resumeMilli) { this.index = index; this.resumeMilli = resumeMilli; } public int getIndex() { return this.index; } public long getResumeMilli() { return this.resumeMilli; } }
/*     */   protected class RepaintTimerTask extends TimerTask {
/*     */     UpdateManager um;
/*     */     RepaintTimerTask(UpdateManager um) { this.um = um; }
/*     */     public void run() { RunnableQueue rq = this.um.getUpdateRunnableQueue();
/*     */       if (rq == null)
/*     */         return; 
/*     */       rq.invokeLater(new Runnable() { public void run() {} }
/*     */         ); } }
/* 663 */   void resetRepaintTimer() { if (this.repaintTimerTask == null)
/* 664 */       return;  if (this.allResumeTime < 0L)
/* 665 */       return;  if (this.repaintTriggerTimer == null) {
/* 666 */       this.repaintTriggerTimer = new Timer(true);
/*     */     }
/* 668 */     long delay = this.allResumeTime - System.currentTimeMillis();
/* 669 */     if (delay < 0L) delay = 0L; 
/* 670 */     this.repaintTimerTask = new RepaintTimerTask(this);
/* 671 */     this.repaintTriggerTimer.schedule(this.repaintTimerTask, delay); }
/*     */ 
/*     */ 
/*     */   
/*     */   int addRedrawSuspension(int max_wait_milliseconds) {
/* 676 */     long resumeTime = System.currentTimeMillis() + max_wait_milliseconds;
/* 677 */     SuspensionInfo si = new SuspensionInfo(this.nextSuspensionIndex++, resumeTime);
/*     */     
/* 679 */     if (resumeTime > this.allResumeTime) {
/* 680 */       this.allResumeTime = resumeTime;
/*     */       
/* 682 */       resetRepaintTimer();
/*     */     } 
/* 684 */     this.suspensionList.add(si);
/* 685 */     return si.getIndex();
/*     */   }
/*     */   
/*     */   void releaseAllRedrawSuspension() {
/* 689 */     this.suspensionList.clear();
/* 690 */     this.allResumeTime = -1L;
/* 691 */     resetRepaintTimer();
/*     */   }
/*     */   
/*     */   boolean releaseRedrawSuspension(int index) {
/* 695 */     if (index > this.nextSuspensionIndex) return false; 
/* 696 */     if (this.suspensionList.size() == 0) return true;
/*     */     
/* 698 */     int lo = 0, hi = this.suspensionList.size() - 1;
/* 699 */     while (lo < hi) {
/* 700 */       int mid = lo + hi >> 1;
/* 701 */       SuspensionInfo suspensionInfo = this.suspensionList.get(mid);
/* 702 */       int i = suspensionInfo.getIndex();
/* 703 */       if (i == index) { lo = hi = mid; continue; }
/* 704 */        if (i < index) { lo = mid + 1; continue; }
/* 705 */        hi = mid - 1;
/*     */     } 
/*     */     
/* 708 */     SuspensionInfo si = this.suspensionList.get(lo);
/* 709 */     int idx = si.getIndex();
/* 710 */     if (idx != index) {
/* 711 */       return true;
/*     */     }
/* 713 */     this.suspensionList.remove(lo);
/* 714 */     if (this.suspensionList.size() == 0) {
/*     */       
/* 716 */       this.allResumeTime = -1L;
/* 717 */       resetRepaintTimer();
/*     */     } else {
/*     */       
/* 720 */       long resumeTime = si.getResumeMilli();
/* 721 */       if (resumeTime == this.allResumeTime) {
/* 722 */         this.allResumeTime = findNewAllResumeTime();
/*     */         
/* 724 */         resetRepaintTimer();
/*     */       } 
/*     */     } 
/* 727 */     return true;
/*     */   }
/*     */   
/*     */   long findNewAllResumeTime() {
/* 731 */     long ret = -1L;
/* 732 */     for (Object aSuspensionList : this.suspensionList) {
/* 733 */       SuspensionInfo si = (SuspensionInfo)aSuspensionList;
/* 734 */       long t = si.getResumeMilli();
/* 735 */       if (t > ret) ret = t; 
/*     */     } 
/* 737 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUpdateManagerListener(UpdateManagerListener l) {
/* 744 */     this.listeners.add(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUpdateManagerListener(UpdateManagerListener l) {
/* 751 */     this.listeners.remove(l);
/*     */   }
/*     */   
/*     */   protected void fireEvent(EventDispatcher.Dispatcher dispatcher, Object event) {
/* 755 */     EventDispatcher.fireEvent(dispatcher, this.listeners, event, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 763 */   static EventDispatcher.Dispatcher startedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 766 */         ((UpdateManagerListener)listener).managerStarted((UpdateManagerEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 775 */   static EventDispatcher.Dispatcher stoppedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 778 */         ((UpdateManagerListener)listener).managerStopped((UpdateManagerEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 787 */   static EventDispatcher.Dispatcher suspendedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 790 */         ((UpdateManagerListener)listener).managerSuspended((UpdateManagerEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 799 */   static EventDispatcher.Dispatcher resumedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 802 */         ((UpdateManagerListener)listener).managerResumed((UpdateManagerEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 811 */   static EventDispatcher.Dispatcher updateStartedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 814 */         ((UpdateManagerListener)listener).updateStarted((UpdateManagerEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 823 */   static EventDispatcher.Dispatcher updateCompletedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 826 */         ((UpdateManagerListener)listener).updateCompleted((UpdateManagerEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 835 */   static EventDispatcher.Dispatcher updateFailedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 838 */         ((UpdateManagerListener)listener).updateFailed((UpdateManagerEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RunnableQueue.RunHandler createRunHandler() {
/* 847 */     return (RunnableQueue.RunHandler)new UpdateManagerRunHander();
/*     */   }
/*     */   
/*     */   protected class UpdateManagerRunHander
/*     */     extends RunnableQueue.RunHandlerAdapter
/*     */   {
/*     */     public void runnableStart(RunnableQueue rq, Runnable r) {
/* 854 */       if (UpdateManager.this.running && !(r instanceof NoRepaintRunnable))
/*     */       {
/*     */         
/* 857 */         if (UpdateManager.this.outOfDateTime == 0L) {
/* 858 */           UpdateManager.this.outOfDateTime = System.currentTimeMillis();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void runnableInvoked(RunnableQueue rq, Runnable r) {
/* 868 */       if (UpdateManager.this.running && !(r instanceof NoRepaintRunnable)) {
/* 869 */         UpdateManager.this.repaint();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void executionSuspended(RunnableQueue rq) {
/* 877 */       synchronized (UpdateManager.this) {
/*     */         
/* 879 */         if (UpdateManager.this.suspendCalled) {
/* 880 */           UpdateManager.this.running = false;
/* 881 */           UpdateManagerEvent ev = new UpdateManagerEvent(this, null, null);
/*     */           
/* 883 */           UpdateManager.this.fireEvent(UpdateManager.suspendedDispatcher, ev);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void executionResumed(RunnableQueue rq) {
/* 892 */       synchronized (UpdateManager.this) {
/*     */ 
/*     */         
/* 895 */         if (UpdateManager.this.suspendCalled && !UpdateManager.this.running) {
/* 896 */           UpdateManager.this.running = true;
/* 897 */           UpdateManager.this.suspendCalled = false;
/*     */           
/* 899 */           UpdateManagerEvent ev = new UpdateManagerEvent(this, null, null);
/*     */           
/* 901 */           UpdateManager.this.fireEvent(UpdateManager.resumedDispatcher, ev);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/UpdateManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */