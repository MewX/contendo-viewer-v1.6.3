/*     */ package org.apache.batik.swing.svg;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeException;
/*     */ import org.apache.batik.bridge.DynamicGVTBuilder;
/*     */ import org.apache.batik.bridge.GVTBuilder;
/*     */ import org.apache.batik.bridge.InterruptedBridgeException;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.EventDispatcher;
/*     */ import org.apache.batik.util.HaltingThread;
/*     */ import org.w3c.dom.Document;
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
/*     */ public class GVTTreeBuilder
/*     */   extends HaltingThread
/*     */ {
/*     */   protected SVGDocument svgDocument;
/*     */   protected BridgeContext bridgeContext;
/*  60 */   protected List listeners = Collections.synchronizedList(new LinkedList());
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
/*     */   public GVTTreeBuilder(SVGDocument doc, BridgeContext bc) {
/*  72 */     this.svgDocument = doc;
/*  73 */     this.bridgeContext = bc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  81 */     GVTTreeBuilderEvent ev = new GVTTreeBuilderEvent(this, null);
/*     */     try {
/*  83 */       fireEvent(startedDispatcher, ev);
/*     */       
/*  85 */       if (isHalted()) {
/*  86 */         fireEvent(cancelledDispatcher, ev);
/*     */         return;
/*     */       } 
/*  89 */       GVTBuilder builder = null;
/*     */       
/*  91 */       if (this.bridgeContext.isDynamic()) {
/*  92 */         DynamicGVTBuilder dynamicGVTBuilder = new DynamicGVTBuilder();
/*     */       } else {
/*  94 */         builder = new GVTBuilder();
/*     */       } 
/*  96 */       GraphicsNode gvtRoot = builder.build(this.bridgeContext, (Document)this.svgDocument);
/*     */       
/*  98 */       if (isHalted()) {
/*  99 */         fireEvent(cancelledDispatcher, ev);
/*     */         
/*     */         return;
/*     */       } 
/* 103 */       ev = new GVTTreeBuilderEvent(this, gvtRoot);
/* 104 */       fireEvent(completedDispatcher, ev);
/* 105 */     } catch (InterruptedBridgeException e) {
/* 106 */       fireEvent(cancelledDispatcher, ev);
/* 107 */     } catch (BridgeException e) {
/* 108 */       this.exception = (Exception)e;
/* 109 */       ev = new GVTTreeBuilderEvent(this, e.getGraphicsNode());
/* 110 */       fireEvent(failedDispatcher, ev);
/* 111 */     } catch (Exception e) {
/* 112 */       this.exception = e;
/* 113 */       fireEvent(failedDispatcher, ev);
/* 114 */     } catch (ThreadDeath td) {
/* 115 */       this.exception = new Exception(td.getMessage());
/* 116 */       fireEvent(failedDispatcher, ev);
/* 117 */       throw td;
/* 118 */     } catch (Throwable t) {
/* 119 */       t.printStackTrace();
/* 120 */       this.exception = new Exception(t.getMessage());
/* 121 */       fireEvent(failedDispatcher, ev);
/*     */     } finally {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 131 */     return this.exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGVTTreeBuilderListener(GVTTreeBuilderListener l) {
/* 138 */     this.listeners.add(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeGVTTreeBuilderListener(GVTTreeBuilderListener l) {
/* 145 */     this.listeners.remove(l);
/*     */   }
/*     */   
/*     */   public void fireEvent(EventDispatcher.Dispatcher dispatcher, Object event) {
/* 149 */     EventDispatcher.fireEvent(dispatcher, this.listeners, event, true);
/*     */   }
/*     */   
/* 152 */   static EventDispatcher.Dispatcher startedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 155 */         ((GVTTreeBuilderListener)listener).gvtBuildStarted((GVTTreeBuilderEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 160 */   static EventDispatcher.Dispatcher completedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 163 */         ((GVTTreeBuilderListener)listener).gvtBuildCompleted((GVTTreeBuilderEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 168 */   static EventDispatcher.Dispatcher cancelledDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 171 */         ((GVTTreeBuilderListener)listener).gvtBuildCancelled((GVTTreeBuilderEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 176 */   static EventDispatcher.Dispatcher failedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 179 */         ((GVTTreeBuilderListener)listener).gvtBuildFailed((GVTTreeBuilderEvent)event);
/*     */       }
/*     */     };
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/GVTTreeBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */