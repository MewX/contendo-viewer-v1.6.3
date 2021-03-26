/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import org.apache.batik.anim.dom.XBLEventSupport;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.DocumentLoader;
/*     */ import org.apache.batik.bridge.Messages;
/*     */ import org.apache.batik.bridge.SVGUtilities;
/*     */ import org.apache.batik.bridge.ScriptingEnvironment;
/*     */ import org.apache.batik.bridge.Window;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.AbstractElement;
/*     */ import org.apache.batik.dom.events.EventSupport;
/*     */ import org.apache.batik.dom.svg12.SVGGlobal;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.dom.util.TriplyIndexedTable;
/*     */ import org.apache.batik.script.Interpreter;
/*     */ import org.w3c.dom.Element;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVG12ScriptingEnvironment
/*     */   extends ScriptingEnvironment
/*     */ {
/*     */   public static final String HANDLER_SCRIPT_DESCRIPTION = "SVG12ScriptingEnvironment.constant.handler.script.description";
/*     */   protected TriplyIndexedTable handlerScriptingListeners;
/*     */   
/*     */   public SVG12ScriptingEnvironment(BridgeContext ctx) {
/*  66 */     super(ctx);
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
/*     */   protected void addDocumentListeners() {
/*  79 */     this.domNodeInsertedListener = (EventListener)new DOMNodeInsertedListener();
/*  80 */     this.domNodeRemovedListener = (EventListener)new DOMNodeRemovedListener();
/*  81 */     this.domAttrModifiedListener = (EventListener)new DOMAttrModifiedListener();
/*  82 */     AbstractDocument doc = (AbstractDocument)this.document;
/*  83 */     XBLEventSupport es = (XBLEventSupport)doc.initializeEventSupport();
/*  84 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedListener, false);
/*     */ 
/*     */ 
/*     */     
/*  88 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedListener, false);
/*     */ 
/*     */ 
/*     */     
/*  92 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedListener, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeDocumentListeners() {
/* 102 */     AbstractDocument doc = (AbstractDocument)this.document;
/* 103 */     XBLEventSupport es = (XBLEventSupport)doc.initializeEventSupport();
/* 104 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedListener, false);
/*     */ 
/*     */ 
/*     */     
/* 108 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedListener, false);
/*     */ 
/*     */ 
/*     */     
/* 112 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedListener, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMNodeInsertedListener
/*     */     extends ScriptingEnvironment.DOMNodeInsertedListener
/*     */   {
/*     */     protected DOMNodeInsertedListener() {
/* 121 */       super(SVG12ScriptingEnvironment.this);
/*     */     }
/*     */     public void handleEvent(Event evt) {
/* 124 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     }
/*     */   }
/*     */   
/*     */   protected class DOMNodeRemovedListener
/*     */     extends ScriptingEnvironment.DOMNodeRemovedListener {
/*     */     protected DOMNodeRemovedListener() {
/* 131 */       super(SVG12ScriptingEnvironment.this);
/*     */     }
/*     */     public void handleEvent(Event evt) {
/* 134 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     } }
/*     */   
/*     */   protected class DOMAttrModifiedListener extends ScriptingEnvironment.DOMAttrModifiedListener { protected DOMAttrModifiedListener() {
/* 138 */       super(SVG12ScriptingEnvironment.this);
/*     */     }
/*     */     public void handleEvent(Event evt) {
/* 141 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addScriptingListenersOn(Element elt) {
/* 149 */     String eltNS = elt.getNamespaceURI();
/* 150 */     String eltLN = elt.getLocalName();
/* 151 */     if ("http://www.w3.org/2000/svg".equals(eltNS) && "handler".equals(eltLN)) {
/*     */ 
/*     */ 
/*     */       
/* 155 */       AbstractElement tgt = (AbstractElement)elt.getParentNode();
/* 156 */       String eventType = elt.getAttributeNS("http://www.w3.org/2001/xml-events", "event");
/*     */ 
/*     */       
/* 159 */       String eventNamespaceURI = "http://www.w3.org/2001/xml-events";
/* 160 */       if (eventType.indexOf(':') != -1) {
/* 161 */         String prefix = DOMUtilities.getPrefix(eventType);
/* 162 */         eventType = DOMUtilities.getLocalName(eventType);
/* 163 */         eventNamespaceURI = ((AbstractElement)elt).lookupNamespaceURI(prefix);
/*     */       } 
/*     */ 
/*     */       
/* 167 */       EventListener listener = new HandlerScriptingEventListener(eventNamespaceURI, eventType, (AbstractElement)elt);
/*     */       
/* 169 */       tgt.addEventListenerNS(eventNamespaceURI, eventType, listener, false, null);
/*     */       
/* 171 */       if (this.handlerScriptingListeners == null) {
/* 172 */         this.handlerScriptingListeners = new TriplyIndexedTable();
/*     */       }
/* 174 */       this.handlerScriptingListeners.put(eventNamespaceURI, eventType, elt, listener);
/*     */     } 
/*     */ 
/*     */     
/* 178 */     super.addScriptingListenersOn(elt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeScriptingListenersOn(Element elt) {
/* 185 */     String eltNS = elt.getNamespaceURI();
/* 186 */     String eltLN = elt.getLocalName();
/* 187 */     if ("http://www.w3.org/2000/svg".equals(eltNS) && "handler".equals(eltLN)) {
/*     */ 
/*     */ 
/*     */       
/* 191 */       AbstractElement tgt = (AbstractElement)elt.getParentNode();
/* 192 */       String eventType = elt.getAttributeNS("http://www.w3.org/2001/xml-events", "event");
/*     */ 
/*     */       
/* 195 */       String eventNamespaceURI = "http://www.w3.org/2001/xml-events";
/* 196 */       if (eventType.indexOf(':') != -1) {
/* 197 */         String prefix = DOMUtilities.getPrefix(eventType);
/* 198 */         eventType = DOMUtilities.getLocalName(eventType);
/* 199 */         eventNamespaceURI = ((AbstractElement)elt).lookupNamespaceURI(prefix);
/*     */       } 
/*     */ 
/*     */       
/* 203 */       EventListener listener = (EventListener)this.handlerScriptingListeners.put(eventNamespaceURI, eventType, elt, null);
/*     */ 
/*     */       
/* 206 */       tgt.removeEventListenerNS(eventNamespaceURI, eventType, listener, false);
/*     */     } 
/*     */ 
/*     */     
/* 210 */     super.removeScriptingListenersOn(elt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class HandlerScriptingEventListener
/*     */     implements EventListener
/*     */   {
/*     */     protected String eventNamespaceURI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String eventType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected AbstractElement handlerElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public HandlerScriptingEventListener(String ns, String et, AbstractElement e) {
/* 242 */       this.eventNamespaceURI = ns;
/* 243 */       this.eventType = et;
/* 244 */       this.handlerElement = e;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 251 */       Element elt = (Element)evt.getCurrentTarget();
/*     */       
/* 253 */       String script = this.handlerElement.getTextContent();
/* 254 */       if (script.length() == 0) {
/*     */         return;
/*     */       }
/* 257 */       DocumentLoader dl = SVG12ScriptingEnvironment.this.bridgeContext.getDocumentLoader();
/* 258 */       AbstractDocument d = (AbstractDocument)this.handlerElement.getOwnerDocument();
/*     */       
/* 260 */       int line = dl.getLineNumber((Element)this.handlerElement);
/* 261 */       String desc = Messages.formatMessage("SVG12ScriptingEnvironment.constant.handler.script.description", new Object[] { d.getDocumentURI(), this.eventNamespaceURI, this.eventType, Integer.valueOf(line) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 269 */       String lang = this.handlerElement.getAttributeNS(null, "contentScriptType");
/*     */       
/* 271 */       if (lang.length() == 0) {
/* 272 */         Element e = elt;
/* 273 */         while (e != null && (!"http://www.w3.org/2000/svg".equals(e.getNamespaceURI()) || !"svg".equals(e.getLocalName())))
/*     */         {
/*     */ 
/*     */           
/* 277 */           e = SVGUtilities.getParentElement(e);
/*     */         }
/* 279 */         if (e == null) {
/*     */           return;
/*     */         }
/* 282 */         lang = e.getAttributeNS((String)null, "contentScriptType");
/*     */       } 
/*     */ 
/*     */       
/* 286 */       SVG12ScriptingEnvironment.this.runEventHandler(script, evt, lang, desc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Window createWindow(Interpreter interp, String lang) {
/* 295 */     return (Window)new Global(interp, lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class Global
/*     */     extends ScriptingEnvironment.Window
/*     */     implements SVGGlobal
/*     */   {
/*     */     public Global(Interpreter interp, String lang) {
/* 309 */       super(SVG12ScriptingEnvironment.this, interp, lang);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startMouseCapture(EventTarget target, boolean sendAll, boolean autoRelease) {
/* 320 */       ((SVG12BridgeContext)SVG12ScriptingEnvironment.this.bridgeContext.getPrimaryBridgeContext()).startMouseCapture(target, sendAll, autoRelease);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void stopMouseCapture() {
/* 330 */       ((SVG12BridgeContext)SVG12ScriptingEnvironment.this.bridgeContext.getPrimaryBridgeContext()).stopMouseCapture();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVG12ScriptingEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */