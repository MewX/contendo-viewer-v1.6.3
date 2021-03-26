/*     */ package org.apache.batik.swing.svg;
/*     */ 
/*     */ import java.io.InterruptedIOException;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.bridge.DocumentLoader;
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
/*     */ public class SVGDocumentLoader
/*     */   extends HaltingThread
/*     */ {
/*     */   protected String url;
/*     */   protected DocumentLoader loader;
/*     */   protected Exception exception;
/*  59 */   protected List listeners = Collections.synchronizedList(new LinkedList());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGDocumentLoader(String u, DocumentLoader l) {
/*  67 */     this.url = u;
/*  68 */     this.loader = l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  76 */     SVGDocumentLoaderEvent evt = new SVGDocumentLoaderEvent(this, null);
/*     */     try {
/*  78 */       fireEvent(startedDispatcher, evt);
/*  79 */       if (isHalted()) {
/*  80 */         fireEvent(cancelledDispatcher, evt);
/*     */         
/*     */         return;
/*     */       } 
/*  84 */       SVGDocument svgDocument = (SVGDocument)this.loader.loadDocument(this.url);
/*     */       
/*  86 */       if (isHalted()) {
/*  87 */         fireEvent(cancelledDispatcher, evt);
/*     */         
/*     */         return;
/*     */       } 
/*  91 */       evt = new SVGDocumentLoaderEvent(this, svgDocument);
/*  92 */       fireEvent(completedDispatcher, evt);
/*  93 */     } catch (InterruptedIOException e) {
/*  94 */       fireEvent(cancelledDispatcher, evt);
/*  95 */     } catch (Exception e) {
/*  96 */       this.exception = e;
/*  97 */       fireEvent(failedDispatcher, evt);
/*  98 */     } catch (ThreadDeath td) {
/*  99 */       this.exception = new Exception(td.getMessage());
/* 100 */       fireEvent(failedDispatcher, evt);
/* 101 */       throw td;
/* 102 */     } catch (Throwable t) {
/* 103 */       t.printStackTrace();
/* 104 */       this.exception = new Exception(t.getMessage());
/* 105 */       fireEvent(failedDispatcher, evt);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 113 */     return this.exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSVGDocumentLoaderListener(SVGDocumentLoaderListener l) {
/* 120 */     this.listeners.add(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSVGDocumentLoaderListener(SVGDocumentLoaderListener l) {
/* 127 */     this.listeners.remove(l);
/*     */   }
/*     */   
/*     */   public void fireEvent(EventDispatcher.Dispatcher dispatcher, Object event) {
/* 131 */     EventDispatcher.fireEvent(dispatcher, this.listeners, event, true);
/*     */   }
/*     */   
/* 134 */   static EventDispatcher.Dispatcher startedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 137 */         ((SVGDocumentLoaderListener)listener).documentLoadingStarted((SVGDocumentLoaderEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 142 */   static EventDispatcher.Dispatcher completedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 145 */         ((SVGDocumentLoaderListener)listener).documentLoadingCompleted((SVGDocumentLoaderEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 150 */   static EventDispatcher.Dispatcher cancelledDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 153 */         ((SVGDocumentLoaderListener)listener).documentLoadingCancelled((SVGDocumentLoaderEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 158 */   static EventDispatcher.Dispatcher failedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 161 */         ((SVGDocumentLoaderListener)listener).documentLoadingFailed((SVGDocumentLoaderEvent)event);
/*     */       }
/*     */     };
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGDocumentLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */