/*     */ package org.apache.batik.swing.svg;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.InterruptedBridgeException;
/*     */ import org.apache.batik.bridge.UpdateManager;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.EventDispatcher;
/*     */ import org.apache.batik.util.HaltingThread;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGLoadEventDispatcher
/*     */   extends HaltingThread
/*     */ {
/*     */   protected SVGDocument svgDocument;
/*     */   protected GraphicsNode root;
/*     */   protected BridgeContext bridgeContext;
/*     */   protected UpdateManager updateManager;
/*  66 */   protected List listeners = Collections.synchronizedList(new LinkedList());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Exception exception;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLoadEventDispatcher(GraphicsNode gn, SVGDocument doc, BridgeContext bc, UpdateManager um) {
/*  80 */     this.svgDocument = doc;
/*  81 */     this.root = gn;
/*  82 */     this.bridgeContext = bc;
/*  83 */     this.updateManager = um;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  91 */     SVGLoadEventDispatcherEvent ev = new SVGLoadEventDispatcherEvent(this, this.root);
/*     */     try {
/*  93 */       fireEvent(startedDispatcher, ev);
/*     */       
/*  95 */       if (isHalted()) {
/*  96 */         fireEvent(cancelledDispatcher, ev);
/*     */         
/*     */         return;
/*     */       } 
/* 100 */       this.updateManager.dispatchSVGLoadEvent();
/*     */       
/* 102 */       if (isHalted()) {
/* 103 */         fireEvent(cancelledDispatcher, ev);
/*     */         
/*     */         return;
/*     */       } 
/* 107 */       fireEvent(completedDispatcher, ev);
/* 108 */     } catch (InterruptedException e) {
/* 109 */       fireEvent(cancelledDispatcher, ev);
/* 110 */     } catch (InterruptedBridgeException e) {
/* 111 */       fireEvent(cancelledDispatcher, ev);
/* 112 */     } catch (Exception e) {
/* 113 */       this.exception = e;
/* 114 */       fireEvent(failedDispatcher, ev);
/* 115 */     } catch (ThreadDeath td) {
/* 116 */       this.exception = new Exception(td.getMessage());
/* 117 */       fireEvent(failedDispatcher, ev);
/* 118 */       throw td;
/* 119 */     } catch (Throwable t) {
/* 120 */       t.printStackTrace();
/* 121 */       this.exception = new Exception(t.getMessage());
/* 122 */       fireEvent(failedDispatcher, ev);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UpdateManager getUpdateManager() {
/* 130 */     return this.updateManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 137 */     return this.exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSVGLoadEventDispatcherListener(SVGLoadEventDispatcherListener l) {
/* 145 */     this.listeners.add(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSVGLoadEventDispatcherListener(SVGLoadEventDispatcherListener l) {
/* 154 */     this.listeners.remove(l);
/*     */   }
/*     */   
/*     */   public void fireEvent(EventDispatcher.Dispatcher dispatcher, Object event) {
/* 158 */     EventDispatcher.fireEvent(dispatcher, this.listeners, event, true);
/*     */   }
/*     */   
/* 161 */   static EventDispatcher.Dispatcher startedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 164 */         ((SVGLoadEventDispatcherListener)listener).svgLoadEventDispatchStarted((SVGLoadEventDispatcherEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 170 */   static EventDispatcher.Dispatcher completedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 173 */         ((SVGLoadEventDispatcherListener)listener).svgLoadEventDispatchCompleted((SVGLoadEventDispatcherEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 179 */   static EventDispatcher.Dispatcher cancelledDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 182 */         ((SVGLoadEventDispatcherListener)listener).svgLoadEventDispatchCancelled((SVGLoadEventDispatcherEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 188 */   static EventDispatcher.Dispatcher failedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 191 */         ((SVGLoadEventDispatcherListener)listener).svgLoadEventDispatchFailed((SVGLoadEventDispatcherEvent)event);
/*     */       }
/*     */     };
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGLoadEventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */