/*     */ package org.apache.batik.swing.gvt;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.bridge.InterruptedBridgeException;
/*     */ import org.apache.batik.gvt.renderer.ImageRenderer;
/*     */ import org.apache.batik.util.EventDispatcher;
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
/*     */ public class GVTTreeRenderer
/*     */   extends HaltingThread
/*     */ {
/*     */   protected ImageRenderer renderer;
/*     */   protected Shape areaOfInterest;
/*     */   protected int width;
/*     */   protected int height;
/*     */   protected AffineTransform user2DeviceTransform;
/*     */   protected boolean doubleBuffering;
/*  75 */   protected List listeners = Collections.synchronizedList(new LinkedList());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTTreeRenderer(ImageRenderer r, AffineTransform usr2dev, boolean dbuffer, Shape aoi, int width, int height) {
/*  89 */     this.renderer = r;
/*  90 */     this.areaOfInterest = aoi;
/*  91 */     this.user2DeviceTransform = usr2dev;
/*  92 */     this.doubleBuffering = dbuffer;
/*  93 */     this.width = width;
/*  94 */     this.height = height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 101 */     GVTTreeRendererEvent ev = new GVTTreeRendererEvent(this, null);
/*     */     try {
/* 103 */       fireEvent(prepareDispatcher, ev);
/*     */       
/* 105 */       this.renderer.setTransform(this.user2DeviceTransform);
/* 106 */       this.renderer.setDoubleBuffered(this.doubleBuffering);
/* 107 */       this.renderer.updateOffScreen(this.width, this.height);
/* 108 */       this.renderer.clearOffScreen();
/*     */       
/* 110 */       if (isHalted()) {
/* 111 */         fireEvent(cancelledDispatcher, ev);
/*     */         
/*     */         return;
/*     */       } 
/* 115 */       ev = new GVTTreeRendererEvent(this, this.renderer.getOffScreen());
/* 116 */       fireEvent(startedDispatcher, ev);
/*     */       
/* 118 */       if (isHalted()) {
/* 119 */         fireEvent(cancelledDispatcher, ev);
/*     */         
/*     */         return;
/*     */       } 
/* 123 */       this.renderer.repaint(this.areaOfInterest);
/*     */       
/* 125 */       if (isHalted()) {
/* 126 */         fireEvent(cancelledDispatcher, ev);
/*     */         
/*     */         return;
/*     */       } 
/* 130 */       ev = new GVTTreeRendererEvent(this, this.renderer.getOffScreen());
/* 131 */       fireEvent(completedDispatcher, ev);
/* 132 */     } catch (NoClassDefFoundError noClassDefFoundError) {
/*     */ 
/*     */     
/* 135 */     } catch (InterruptedBridgeException e) {
/*     */ 
/*     */       
/* 138 */       fireEvent(cancelledDispatcher, ev);
/* 139 */     } catch (ThreadDeath td) {
/* 140 */       fireEvent(failedDispatcher, ev);
/* 141 */       throw td;
/* 142 */     } catch (Throwable t) {
/* 143 */       t.printStackTrace();
/* 144 */       fireEvent(failedDispatcher, ev);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fireEvent(EventDispatcher.Dispatcher dispatcher, Object event) {
/* 149 */     EventDispatcher.fireEvent(dispatcher, this.listeners, event, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGVTTreeRendererListener(GVTTreeRendererListener l) {
/* 156 */     this.listeners.add(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeGVTTreeRendererListener(GVTTreeRendererListener l) {
/* 163 */     this.listeners.remove(l);
/*     */   }
/*     */   
/* 166 */   static EventDispatcher.Dispatcher prepareDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 169 */         ((GVTTreeRendererListener)listener).gvtRenderingPrepare((GVTTreeRendererEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 174 */   static EventDispatcher.Dispatcher startedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 177 */         ((GVTTreeRendererListener)listener).gvtRenderingStarted((GVTTreeRendererEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 182 */   static EventDispatcher.Dispatcher cancelledDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 185 */         ((GVTTreeRendererListener)listener).gvtRenderingCancelled((GVTTreeRendererEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 190 */   static EventDispatcher.Dispatcher completedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 193 */         ((GVTTreeRendererListener)listener).gvtRenderingCompleted((GVTTreeRendererEvent)event);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 198 */   static EventDispatcher.Dispatcher failedDispatcher = new EventDispatcher.Dispatcher()
/*     */     {
/*     */       public void dispatch(Object listener, Object event) {
/* 201 */         ((GVTTreeRendererListener)listener).gvtRenderingFailed((GVTTreeRendererEvent)event);
/*     */       }
/*     */     };
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/GVTTreeRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */