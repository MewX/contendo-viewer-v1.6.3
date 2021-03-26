/*     */ package org.apache.xalan.trace;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.TooManyListenersException;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraceManager
/*     */ {
/*     */   private TransformerImpl m_transformer;
/*     */   private Vector m_traceListeners;
/*     */   
/*     */   public TraceManager(TransformerImpl transformer) {
/*  55 */     this.m_traceListeners = null;
/*     */     this.m_transformer = transformer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTraceListener(TraceListener tl) throws TooManyListenersException {
/*  67 */     TransformerImpl.S_DEBUG = true;
/*     */     
/*  69 */     if (null == this.m_traceListeners) {
/*  70 */       this.m_traceListeners = new Vector();
/*     */     }
/*  72 */     this.m_traceListeners.addElement(tl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTraceListener(TraceListener tl) {
/*  82 */     if (null != this.m_traceListeners) {
/*     */       
/*  84 */       this.m_traceListeners.removeElement(tl);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       if (0 == this.m_traceListeners.size()) this.m_traceListeners = null;
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireGenerateEvent(GenerateEvent te) {
/* 101 */     if (null != this.m_traceListeners) {
/*     */       
/* 103 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 105 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 107 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/*     */         
/* 109 */         tl.generated(te);
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
/*     */   public boolean hasTraceListeners() {
/* 121 */     return (null != this.m_traceListeners);
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
/*     */   public void fireTraceEvent(ElemTemplateElement styleNode) {
/* 134 */     if (hasTraceListeners()) {
/*     */       
/* 136 */       int sourceNode = this.m_transformer.getXPathContext().getCurrentNode();
/* 137 */       Node source = this.m_transformer.getXPathContext().getDTM(sourceNode).getNode(sourceNode);
/*     */ 
/*     */       
/* 140 */       fireTraceEvent(new TracerEvent(this.m_transformer, source, this.m_transformer.getMode(), styleNode));
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
/*     */   
/*     */   public void fireTraceEndEvent(ElemTemplateElement styleNode) {
/* 157 */     if (hasTraceListeners()) {
/*     */       
/* 159 */       int sourceNode = this.m_transformer.getXPathContext().getCurrentNode();
/* 160 */       Node source = this.m_transformer.getXPathContext().getDTM(sourceNode).getNode(sourceNode);
/*     */ 
/*     */       
/* 163 */       fireTraceEndEvent(new TracerEvent(this.m_transformer, source, this.m_transformer.getMode(), styleNode));
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
/*     */   public void fireTraceEndEvent(TracerEvent te) {
/* 177 */     if (hasTraceListeners()) {
/*     */       
/* 179 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 181 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 183 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/* 184 */         if (tl instanceof TraceListenerEx2)
/*     */         {
/* 186 */           ((TraceListenerEx2)tl).traceEnd(te);
/*     */         }
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
/*     */   public void fireTraceEvent(TracerEvent te) {
/* 202 */     if (hasTraceListeners()) {
/*     */       
/* 204 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 206 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 208 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/*     */         
/* 210 */         tl.trace(te);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireSelectedEvent(int sourceNode, ElemTemplateElement styleNode, String attributeName, XPath xpath, XObject selection) throws TransformerException {
/* 232 */     if (hasTraceListeners()) {
/*     */       
/* 234 */       Node source = this.m_transformer.getXPathContext().getDTM(sourceNode).getNode(sourceNode);
/*     */ 
/*     */       
/* 237 */       fireSelectedEvent(new SelectionEvent(this.m_transformer, source, styleNode, attributeName, xpath, selection));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireSelectedEndEvent(int sourceNode, ElemTemplateElement styleNode, String attributeName, XPath xpath, XObject selection) throws TransformerException {
/* 259 */     if (hasTraceListeners()) {
/*     */       
/* 261 */       Node source = this.m_transformer.getXPathContext().getDTM(sourceNode).getNode(sourceNode);
/*     */ 
/*     */       
/* 264 */       fireSelectedEndEvent(new EndSelectionEvent(this.m_transformer, source, styleNode, attributeName, xpath, selection));
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
/*     */   public void fireSelectedEndEvent(EndSelectionEvent se) throws TransformerException {
/* 280 */     if (hasTraceListeners()) {
/*     */       
/* 282 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 284 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 286 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/*     */         
/* 288 */         if (tl instanceof TraceListenerEx) {
/* 289 */           ((TraceListenerEx)tl).selectEnd(se);
/*     */         }
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
/*     */   public void fireSelectedEvent(SelectionEvent se) throws TransformerException {
/* 305 */     if (hasTraceListeners()) {
/*     */       
/* 307 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 309 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 311 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/*     */         
/* 313 */         tl.selected(se);
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
/*     */   public void fireExtensionEndEvent(Method method, Object instance, Object[] arguments) {
/* 330 */     ExtensionEvent ee = new ExtensionEvent(this.m_transformer, method, instance, arguments);
/*     */     
/* 332 */     if (hasTraceListeners()) {
/*     */       
/* 334 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 336 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 338 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/* 339 */         if (tl instanceof TraceListenerEx3)
/*     */         {
/* 341 */           ((TraceListenerEx3)tl).extensionEnd(ee);
/*     */         }
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
/*     */   public void fireExtensionEvent(Method method, Object instance, Object[] arguments) {
/* 358 */     ExtensionEvent ee = new ExtensionEvent(this.m_transformer, method, instance, arguments);
/*     */     
/* 360 */     if (hasTraceListeners()) {
/*     */       
/* 362 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 364 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 366 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/* 367 */         if (tl instanceof TraceListenerEx3)
/*     */         {
/* 369 */           ((TraceListenerEx3)tl).extension(ee);
/*     */         }
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
/*     */   public void fireExtensionEndEvent(ExtensionEvent ee) {
/* 384 */     if (hasTraceListeners()) {
/*     */       
/* 386 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 388 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 390 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/* 391 */         if (tl instanceof TraceListenerEx3)
/*     */         {
/* 393 */           ((TraceListenerEx3)tl).extensionEnd(ee);
/*     */         }
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
/*     */   public void fireExtensionEvent(ExtensionEvent ee) {
/* 409 */     if (hasTraceListeners()) {
/*     */       
/* 411 */       int nListeners = this.m_traceListeners.size();
/*     */       
/* 413 */       for (int i = 0; i < nListeners; i++) {
/*     */         
/* 415 */         TraceListener tl = this.m_traceListeners.elementAt(i);
/* 416 */         if (tl instanceof TraceListenerEx3)
/*     */         {
/* 418 */           ((TraceListenerEx3)tl).extension(ee);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/trace/TraceManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */