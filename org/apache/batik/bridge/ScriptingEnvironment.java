/*      */ package org.apache.batik.bridge;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.Reader;
/*      */ import java.io.StringReader;
/*      */ import java.io.StringWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Map;
/*      */ import java.util.Timer;
/*      */ import java.util.TimerTask;
/*      */ import java.util.zip.DeflaterOutputStream;
/*      */ import java.util.zip.GZIPOutputStream;
/*      */ import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
/*      */ import org.apache.batik.anim.dom.SVGOMDocument;
/*      */ import org.apache.batik.dom.GenericDOMImplementation;
/*      */ import org.apache.batik.dom.events.NodeEventTarget;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.dom.util.SAXDocumentFactory;
/*      */ import org.apache.batik.script.Interpreter;
/*      */ import org.apache.batik.script.InterpreterException;
/*      */ import org.apache.batik.script.ScriptEventWrapper;
/*      */ import org.apache.batik.util.EncodingUtilities;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.apache.batik.util.RunnableQueue;
/*      */ import org.apache.batik.util.XMLResourceDescriptor;
/*      */ import org.apache.batik.w3c.dom.Location;
/*      */ import org.w3c.dom.DOMImplementation;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventListener;
/*      */ import org.w3c.dom.events.MutationEvent;
/*      */ import org.w3c.dom.svg.SVGDocument;
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
/*      */ public class ScriptingEnvironment
/*      */   extends BaseScriptingEnvironment
/*      */ {
/*   77 */   public static final String[] SVG_EVENT_ATTRS = new String[] { "onabort", "onerror", "onresize", "onscroll", "onunload", "onzoom", "onbegin", "onend", "onrepeat", "onfocusin", "onfocusout", "onactivate", "onclick", "onmousedown", "onmouseup", "onmouseover", "onmouseout", "onmousemove", "onkeypress", "onkeydown", "onkeyup" };
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
/*  105 */   public static final String[] SVG_DOM_EVENT = new String[] { "SVGAbort", "SVGError", "SVGResize", "SVGScroll", "SVGUnload", "SVGZoom", "beginEvent", "endEvent", "repeatEvent", "DOMFocusIn", "DOMFocusOut", "DOMActivate", "click", "mousedown", "mouseup", "mouseover", "mouseout", "mousemove", "keypress", "keydown", "keyup" };
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
/*  134 */   protected Timer timer = new Timer(true);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected UpdateManager updateManager;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected RunnableQueue updateRunnableQueue;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EventListener domNodeInsertedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EventListener domNodeRemovedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EventListener domAttrModifiedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  164 */   protected EventListener svgAbortListener = new ScriptingEventListener("onabort");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  170 */   protected EventListener svgErrorListener = new ScriptingEventListener("onerror");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  176 */   protected EventListener svgResizeListener = new ScriptingEventListener("onresize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  182 */   protected EventListener svgScrollListener = new ScriptingEventListener("onscroll");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  188 */   protected EventListener svgUnloadListener = new ScriptingEventListener("onunload");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  194 */   protected EventListener svgZoomListener = new ScriptingEventListener("onzoom");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  200 */   protected EventListener beginListener = new ScriptingEventListener("onbegin");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  206 */   protected EventListener endListener = new ScriptingEventListener("onend");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  212 */   protected EventListener repeatListener = new ScriptingEventListener("onrepeat");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  218 */   protected EventListener focusinListener = new ScriptingEventListener("onfocusin");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  224 */   protected EventListener focusoutListener = new ScriptingEventListener("onfocusout");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  230 */   protected EventListener activateListener = new ScriptingEventListener("onactivate");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  236 */   protected EventListener clickListener = new ScriptingEventListener("onclick");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  242 */   protected EventListener mousedownListener = new ScriptingEventListener("onmousedown");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  248 */   protected EventListener mouseupListener = new ScriptingEventListener("onmouseup");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  254 */   protected EventListener mouseoverListener = new ScriptingEventListener("onmouseover");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  260 */   protected EventListener mouseoutListener = new ScriptingEventListener("onmouseout");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  266 */   protected EventListener mousemoveListener = new ScriptingEventListener("onmousemove");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  272 */   protected EventListener keypressListener = new ScriptingEventListener("onkeypress");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  278 */   protected EventListener keydownListener = new ScriptingEventListener("onkeydown");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  284 */   protected EventListener keyupListener = new ScriptingEventListener("onkeyup");
/*      */ 
/*      */ 
/*      */   
/*  288 */   protected EventListener[] listeners = new EventListener[] { this.svgAbortListener, this.svgErrorListener, this.svgResizeListener, this.svgScrollListener, this.svgUnloadListener, this.svgZoomListener, this.beginListener, this.endListener, this.repeatListener, this.focusinListener, this.focusoutListener, this.activateListener, this.clickListener, this.mousedownListener, this.mouseupListener, this.mouseoverListener, this.mouseoutListener, this.mousemoveListener, this.keypressListener, this.keydownListener, this.keyupListener };
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
/*  316 */   Map attrToDOMEvent = new HashMap<Object, Object>(SVG_EVENT_ATTRS.length);
/*  317 */   Map attrToListener = new HashMap<Object, Object>(SVG_EVENT_ATTRS.length);
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
/*      */   public ScriptingEnvironment(BridgeContext ctx) {
/*  330 */     super(ctx); for (int i = 0; i < SVG_EVENT_ATTRS.length; i++) { this.attrToDOMEvent.put(SVG_EVENT_ATTRS[i], SVG_DOM_EVENT[i]); this.attrToListener.put(SVG_EVENT_ATTRS[i], this.listeners[i]); }
/*  331 */      this.updateManager = ctx.getUpdateManager();
/*  332 */     this.updateRunnableQueue = this.updateManager.getUpdateRunnableQueue();
/*      */ 
/*      */     
/*  335 */     addScriptingListeners(this.document.getDocumentElement());
/*      */ 
/*      */     
/*  338 */     addDocumentListeners();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addDocumentListeners() {
/*  345 */     this.domNodeInsertedListener = new DOMNodeInsertedListener();
/*  346 */     this.domNodeRemovedListener = new DOMNodeRemovedListener();
/*  347 */     this.domAttrModifiedListener = new DOMAttrModifiedListener();
/*  348 */     NodeEventTarget et = (NodeEventTarget)this.document;
/*  349 */     et.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedListener, false, null);
/*      */ 
/*      */     
/*  352 */     et.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedListener, false, null);
/*      */ 
/*      */     
/*  355 */     et.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedListener, false, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeDocumentListeners() {
/*  364 */     NodeEventTarget et = (NodeEventTarget)this.document;
/*  365 */     et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedListener, false);
/*      */ 
/*      */     
/*  368 */     et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedListener, false);
/*      */ 
/*      */     
/*  371 */     et.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedListener, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Window createWindow(Interpreter interp, String lang) {
/*  381 */     return new Window(interp, lang);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void runEventHandler(String script, Event evt, String lang, String desc) {
/*  389 */     Interpreter interpreter = getInterpreter(lang);
/*  390 */     if (interpreter == null)
/*      */       return; 
/*      */     try {
/*      */       Object event;
/*  394 */       checkCompatibleScriptURL(lang, this.docPURL);
/*      */ 
/*      */       
/*  397 */       if (evt instanceof ScriptEventWrapper) {
/*  398 */         event = ((ScriptEventWrapper)evt).getEventObject();
/*      */       } else {
/*  400 */         event = evt;
/*      */       } 
/*  402 */       interpreter.bindObject("event", event);
/*  403 */       interpreter.bindObject("evt", event);
/*  404 */       interpreter.evaluate(new StringReader(script), desc);
/*  405 */     } catch (IOException iOException) {
/*      */     
/*  407 */     } catch (InterpreterException ie) {
/*  408 */       handleInterpreterException(ie);
/*  409 */     } catch (SecurityException se) {
/*  410 */       handleSecurityException(se);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void interrupt() {
/*  418 */     this.timer.cancel();
/*      */     
/*  420 */     removeScriptingListeners(this.document.getDocumentElement());
/*      */ 
/*      */     
/*  423 */     removeDocumentListeners();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addScriptingListeners(Node node) {
/*  431 */     if (node.getNodeType() == 1) {
/*  432 */       addScriptingListenersOn((Element)node);
/*      */     }
/*      */ 
/*      */     
/*  436 */     Node n = node.getFirstChild();
/*  437 */     for (; n != null; 
/*  438 */       n = n.getNextSibling()) {
/*  439 */       addScriptingListeners(n);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addScriptingListenersOn(Element elt) {
/*  448 */     NodeEventTarget target = (NodeEventTarget)elt;
/*  449 */     if ("http://www.w3.org/2000/svg".equals(elt.getNamespaceURI())) {
/*  450 */       if ("svg".equals(elt.getLocalName())) {
/*      */         
/*  452 */         if (elt.hasAttributeNS((String)null, "onabort")) {
/*  453 */           target.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGAbort", this.svgAbortListener, false, null);
/*      */         }
/*      */ 
/*      */         
/*  457 */         if (elt.hasAttributeNS((String)null, "onerror")) {
/*  458 */           target.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGError", this.svgErrorListener, false, null);
/*      */         }
/*      */ 
/*      */         
/*  462 */         if (elt.hasAttributeNS((String)null, "onresize")) {
/*  463 */           target.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGResize", this.svgResizeListener, false, null);
/*      */         }
/*      */ 
/*      */         
/*  467 */         if (elt.hasAttributeNS((String)null, "onscroll")) {
/*  468 */           target.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGScroll", this.svgScrollListener, false, null);
/*      */         }
/*      */ 
/*      */         
/*  472 */         if (elt.hasAttributeNS((String)null, "onunload")) {
/*  473 */           target.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGUnload", this.svgUnloadListener, false, null);
/*      */         }
/*      */ 
/*      */         
/*  477 */         if (elt.hasAttributeNS((String)null, "onzoom")) {
/*  478 */           target.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGZoom", this.svgZoomListener, false, null);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  483 */         String name = elt.getLocalName();
/*  484 */         if (name.equals("set") || name.startsWith("animate")) {
/*      */ 
/*      */           
/*  487 */           if (elt.hasAttributeNS((String)null, "onbegin")) {
/*  488 */             target.addEventListenerNS("http://www.w3.org/2001/xml-events", "beginEvent", this.beginListener, false, null);
/*      */           }
/*      */ 
/*      */           
/*  492 */           if (elt.hasAttributeNS((String)null, "onend")) {
/*  493 */             target.addEventListenerNS("http://www.w3.org/2001/xml-events", "endEvent", this.endListener, false, null);
/*      */           }
/*      */ 
/*      */           
/*  497 */           if (elt.hasAttributeNS((String)null, "onrepeat")) {
/*  498 */             target.addEventListenerNS("http://www.w3.org/2001/xml-events", "repeatEvent", this.repeatListener, false, null);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  508 */     if (elt.hasAttributeNS((String)null, "onfocusin")) {
/*  509 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusIn", this.focusinListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  513 */     if (elt.hasAttributeNS((String)null, "onfocusout")) {
/*  514 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusOut", this.focusoutListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  518 */     if (elt.hasAttributeNS((String)null, "onactivate")) {
/*  519 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMActivate", this.activateListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  523 */     if (elt.hasAttributeNS((String)null, "onclick")) {
/*  524 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.clickListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  528 */     if (elt.hasAttributeNS((String)null, "onmousedown")) {
/*  529 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mousedown", this.mousedownListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  533 */     if (elt.hasAttributeNS((String)null, "onmouseup")) {
/*  534 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseup", this.mouseupListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  538 */     if (elt.hasAttributeNS((String)null, "onmouseover")) {
/*  539 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.mouseoverListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  543 */     if (elt.hasAttributeNS((String)null, "onmouseout")) {
/*  544 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.mouseoutListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  548 */     if (elt.hasAttributeNS((String)null, "onmousemove")) {
/*  549 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mousemove", this.mousemoveListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  553 */     if (elt.hasAttributeNS((String)null, "onkeypress")) {
/*  554 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "keypress", this.keypressListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  558 */     if (elt.hasAttributeNS((String)null, "onkeydown")) {
/*  559 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "keydown", this.keydownListener, false, null);
/*      */     }
/*      */ 
/*      */     
/*  563 */     if (elt.hasAttributeNS((String)null, "onkeyup")) {
/*  564 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "keyup", this.keyupListener, false, null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeScriptingListeners(Node node) {
/*  575 */     if (node.getNodeType() == 1)
/*      */     {
/*  577 */       removeScriptingListenersOn((Element)node);
/*      */     }
/*      */ 
/*      */     
/*  581 */     Node n = node.getFirstChild();
/*  582 */     for (; n != null; 
/*  583 */       n = n.getNextSibling()) {
/*  584 */       removeScriptingListeners(n);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeScriptingListenersOn(Element elt) {
/*  592 */     NodeEventTarget target = (NodeEventTarget)elt;
/*  593 */     if ("http://www.w3.org/2000/svg".equals(elt.getNamespaceURI())) {
/*  594 */       if ("svg".equals(elt.getLocalName())) {
/*      */         
/*  596 */         target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "SVGAbort", this.svgAbortListener, false);
/*      */ 
/*      */         
/*  599 */         target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "SVGError", this.svgErrorListener, false);
/*      */ 
/*      */         
/*  602 */         target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "SVGResize", this.svgResizeListener, false);
/*      */ 
/*      */         
/*  605 */         target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "SVGScroll", this.svgScrollListener, false);
/*      */ 
/*      */         
/*  608 */         target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "SVGUnload", this.svgUnloadListener, false);
/*      */ 
/*      */         
/*  611 */         target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "SVGZoom", this.svgZoomListener, false);
/*      */       }
/*      */       else {
/*      */         
/*  615 */         String name = elt.getLocalName();
/*  616 */         if (name.equals("set") || name.startsWith("animate")) {
/*      */ 
/*      */           
/*  619 */           target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "beginEvent", this.beginListener, false);
/*      */ 
/*      */           
/*  622 */           target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "endEvent", this.endListener, false);
/*      */ 
/*      */           
/*  625 */           target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "repeatEvent", this.repeatListener, false);
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  634 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusIn", this.focusinListener, false);
/*      */ 
/*      */     
/*  637 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusOut", this.focusoutListener, false);
/*      */ 
/*      */     
/*  640 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMActivate", this.activateListener, false);
/*      */ 
/*      */     
/*  643 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.clickListener, false);
/*      */ 
/*      */     
/*  646 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mousedown", this.mousedownListener, false);
/*      */ 
/*      */     
/*  649 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseup", this.mouseupListener, false);
/*      */ 
/*      */     
/*  652 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.mouseoverListener, false);
/*      */ 
/*      */     
/*  655 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.mouseoutListener, false);
/*      */ 
/*      */     
/*  658 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mousemove", this.mousemoveListener, false);
/*      */ 
/*      */     
/*  661 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keypress", this.keypressListener, false);
/*      */ 
/*      */     
/*  664 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keydown", this.keydownListener, false);
/*      */ 
/*      */     
/*  667 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keyup", this.keyupListener, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateScriptingListeners(Element elt, String attr) {
/*  676 */     String domEvt = (String)this.attrToDOMEvent.get(attr);
/*  677 */     if (domEvt == null) {
/*      */       return;
/*      */     }
/*  680 */     EventListener listener = (EventListener)this.attrToListener.get(attr);
/*  681 */     NodeEventTarget target = (NodeEventTarget)elt;
/*  682 */     if (elt.hasAttributeNS((String)null, attr)) {
/*  683 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", domEvt, listener, false, null);
/*      */     }
/*      */     else {
/*      */       
/*  687 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", domEvt, listener, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected class EvaluateRunnable
/*      */     implements Runnable
/*      */   {
/*      */     protected Interpreter interpreter;
/*      */     
/*      */     protected String script;
/*      */ 
/*      */     
/*      */     public EvaluateRunnable(String s, Interpreter interp) {
/*  701 */       this.interpreter = interp;
/*  702 */       this.script = s;
/*      */     }
/*      */     public void run() {
/*      */       try {
/*  706 */         this.interpreter.evaluate(this.script);
/*  707 */       } catch (InterpreterException ie) {
/*  708 */         ScriptingEnvironment.this.handleInterpreterException(ie);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class EvaluateIntervalRunnable
/*      */     implements Runnable
/*      */   {
/*      */     public int count;
/*      */     
/*      */     public boolean error;
/*      */     
/*      */     protected Interpreter interpreter;
/*      */     
/*      */     protected String script;
/*      */ 
/*      */     
/*      */     public EvaluateIntervalRunnable(String s, Interpreter interp) {
/*  727 */       this.interpreter = interp;
/*  728 */       this.script = s;
/*      */     }
/*      */     
/*      */     public void run() {
/*  732 */       synchronized (this) {
/*  733 */         if (this.error)
/*      */           return; 
/*  735 */         this.count--;
/*      */       } 
/*      */       try {
/*  738 */         this.interpreter.evaluate(this.script);
/*  739 */       } catch (InterpreterException ie) {
/*  740 */         ScriptingEnvironment.this.handleInterpreterException(ie);
/*  741 */         synchronized (this) {
/*  742 */           this.error = true;
/*      */         } 
/*  744 */       } catch (Exception e) {
/*  745 */         if (ScriptingEnvironment.this.userAgent != null) {
/*  746 */           ScriptingEnvironment.this.userAgent.displayError(e);
/*      */         } else {
/*  748 */           e.printStackTrace();
/*      */         } 
/*  750 */         synchronized (this) {
/*  751 */           this.error = true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class EvaluateRunnableRunnable
/*      */     implements Runnable
/*      */   {
/*      */     public int count;
/*      */     
/*      */     public boolean error;
/*      */     
/*      */     protected Runnable runnable;
/*      */ 
/*      */     
/*      */     public EvaluateRunnableRunnable(Runnable r) {
/*  770 */       this.runnable = r;
/*      */     }
/*      */     
/*      */     public void run() {
/*  774 */       synchronized (this) {
/*  775 */         if (this.error)
/*      */           return; 
/*  777 */         this.count--;
/*      */       } 
/*      */       try {
/*  780 */         this.runnable.run();
/*  781 */       } catch (Exception e) {
/*  782 */         if (ScriptingEnvironment.this.userAgent != null) {
/*  783 */           ScriptingEnvironment.this.userAgent.displayError(e);
/*      */         } else {
/*  785 */           e.printStackTrace();
/*      */         } 
/*  787 */         synchronized (this) {
/*  788 */           this.error = true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   protected class Window
/*      */     implements Window
/*      */   {
/*      */     protected Interpreter interpreter;
/*      */     protected String language;
/*      */     protected Location location;
/*      */     static final String DEFLATE = "deflate";
/*      */     static final String GZIP = "gzip";
/*      */     static final String UTF_8 = "UTF-8";
/*      */     
/*      */     protected class IntervalScriptTimerTask
/*      */       extends TimerTask {
/*      */       protected ScriptingEnvironment.EvaluateIntervalRunnable eir;
/*      */       
/*      */       public IntervalScriptTimerTask(String script) {
/*  809 */         this.eir = new ScriptingEnvironment.EvaluateIntervalRunnable(script, ScriptingEnvironment.Window.this.interpreter);
/*      */       }
/*      */       
/*      */       public void run() {
/*  813 */         synchronized (this.eir) {
/*  814 */           if (this.eir.count > 1)
/*      */             return; 
/*  816 */           this.eir.count++;
/*      */         } 
/*  818 */         synchronized (ScriptingEnvironment.this.updateRunnableQueue.getIteratorLock()) {
/*  819 */           if (ScriptingEnvironment.this.updateRunnableQueue.getThread() == null) {
/*  820 */             cancel();
/*      */             return;
/*      */           } 
/*  823 */           ScriptingEnvironment.this.updateRunnableQueue.invokeLater(this.eir);
/*      */         } 
/*  825 */         synchronized (this.eir) {
/*  826 */           if (this.eir.error) {
/*  827 */             cancel();
/*      */           }
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected class IntervalRunnableTimerTask
/*      */       extends TimerTask
/*      */     {
/*      */       protected ScriptingEnvironment.EvaluateRunnableRunnable eihr;
/*      */ 
/*      */       
/*      */       public IntervalRunnableTimerTask(Runnable r) {
/*  842 */         this.eihr = new ScriptingEnvironment.EvaluateRunnableRunnable(r);
/*      */       }
/*      */       
/*      */       public void run() {
/*  846 */         synchronized (this.eihr) {
/*  847 */           if (this.eihr.count > 1)
/*      */             return; 
/*  849 */           this.eihr.count++;
/*      */         } 
/*      */ 
/*      */         
/*  853 */         ScriptingEnvironment.this.updateRunnableQueue.invokeLater(this.eihr);
/*  854 */         synchronized (this.eihr) {
/*  855 */           if (this.eihr.error) {
/*  856 */             cancel();
/*      */           }
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected class TimeoutScriptTimerTask
/*      */       extends TimerTask
/*      */     {
/*      */       private String script;
/*      */ 
/*      */       
/*      */       public TimeoutScriptTimerTask(String script) {
/*  871 */         this.script = script;
/*      */       }
/*      */       
/*      */       public void run() {
/*  875 */         ScriptingEnvironment.this.updateRunnableQueue.invokeLater(new ScriptingEnvironment.EvaluateRunnable(this.script, ScriptingEnvironment.Window.this.interpreter));
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class TimeoutRunnableTimerTask
/*      */       extends TimerTask
/*      */     {
/*      */       private Runnable r;
/*      */ 
/*      */ 
/*      */       
/*      */       public TimeoutRunnableTimerTask(Runnable r) {
/*  890 */         this.r = r;
/*      */       }
/*      */       
/*      */       public void run() {
/*  894 */         ScriptingEnvironment.this.updateRunnableQueue.invokeLater(new Runnable() {
/*      */               public void run() {
/*      */                 try {
/*  897 */                   ScriptingEnvironment.Window.TimeoutRunnableTimerTask.this.r.run();
/*  898 */                 } catch (Exception e) {
/*  899 */                   if (ScriptingEnvironment.this.userAgent != null) {
/*  900 */                     ScriptingEnvironment.this.userAgent.displayError(e);
/*      */                   }
/*      */                 } 
/*      */               }
/*      */             });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Window(Interpreter interp, String lang) {
/*  927 */       this.interpreter = interp;
/*  928 */       this.language = lang;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object setInterval(String script, long interval) {
/*  936 */       IntervalScriptTimerTask tt = new IntervalScriptTimerTask(script);
/*  937 */       ScriptingEnvironment.this.timer.schedule(tt, interval, interval);
/*  938 */       return tt;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object setInterval(Runnable r, long interval) {
/*  946 */       IntervalRunnableTimerTask tt = new IntervalRunnableTimerTask(r);
/*  947 */       ScriptingEnvironment.this.timer.schedule(tt, interval, interval);
/*  948 */       return tt;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void clearInterval(Object interval) {
/*  956 */       if (interval == null)
/*  957 */         return;  ((TimerTask)interval).cancel();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object setTimeout(String script, long timeout) {
/*  965 */       TimeoutScriptTimerTask tt = new TimeoutScriptTimerTask(script);
/*  966 */       ScriptingEnvironment.this.timer.schedule(tt, timeout);
/*  967 */       return tt;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object setTimeout(Runnable r, long timeout) {
/*  975 */       TimeoutRunnableTimerTask tt = new TimeoutRunnableTimerTask(r);
/*  976 */       ScriptingEnvironment.this.timer.schedule(tt, timeout);
/*  977 */       return tt;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void clearTimeout(Object timeout) {
/*  985 */       if (timeout == null)
/*  986 */         return;  ((TimerTask)timeout).cancel();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node parseXML(String text, Document doc) {
/*      */       SAXDocumentFactory sdf;
/*  995 */       SAXSVGDocumentFactory df = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
/*      */       
/*  997 */       URL urlObj = null;
/*  998 */       if (doc instanceof SVGOMDocument) {
/*  999 */         urlObj = ((SVGOMDocument)doc).getURLObject();
/*      */       }
/* 1001 */       if (urlObj == null) {
/* 1002 */         urlObj = ((SVGOMDocument)ScriptingEnvironment.this.bridgeContext.getDocument()).getURLObject();
/*      */       }
/*      */       
/* 1005 */       String uri = (urlObj == null) ? "" : urlObj.toString();
/* 1006 */       Node res = DOMUtilities.parseXML(text, doc, uri, null, null, (SAXDocumentFactory)df);
/* 1007 */       if (res != null) {
/* 1008 */         return res;
/*      */       }
/* 1010 */       if (doc instanceof SVGOMDocument) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1018 */         Map<Object, Object> prefixes = new HashMap<Object, Object>();
/* 1019 */         prefixes.put("xmlns", "http://www.w3.org/2000/xmlns/");
/*      */         
/* 1021 */         prefixes.put("xmlns:xlink", "http://www.w3.org/1999/xlink");
/*      */ 
/*      */         
/* 1024 */         res = DOMUtilities.parseXML(text, doc, uri, prefixes, "svg", (SAXDocumentFactory)df);
/*      */         
/* 1026 */         if (res != null) {
/* 1027 */           return res;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1032 */       if (doc != null) {
/* 1033 */         sdf = new SAXDocumentFactory(doc.getImplementation(), XMLResourceDescriptor.getXMLParserClassName());
/*      */       } else {
/*      */         
/* 1036 */         sdf = new SAXDocumentFactory((DOMImplementation)new GenericDOMImplementation(), XMLResourceDescriptor.getXMLParserClassName());
/*      */       } 
/*      */       
/* 1039 */       return DOMUtilities.parseXML(text, doc, uri, null, null, sdf);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String printNode(Node n) {
/*      */       try {
/* 1047 */         Writer writer = new StringWriter();
/* 1048 */         DOMUtilities.writeNode(n, writer);
/* 1049 */         writer.close();
/* 1050 */         return writer.toString();
/* 1051 */       } catch (IOException ex) {
/* 1052 */         throw new RuntimeException(ex);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void getURL(String uri, Window.URLResponseHandler h) {
/* 1061 */       getURL(uri, h, null);
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
/*      */     public void getURL(final String uri, final Window.URLResponseHandler h, final String enc) {
/* 1074 */       Thread t = new Thread()
/*      */         {
/*      */           public void run() {
/*      */             try {
/* 1078 */               ParsedURL burl = ((SVGOMDocument)ScriptingEnvironment.this.document).getParsedURL();
/* 1079 */               final ParsedURL purl = new ParsedURL(burl, uri);
/* 1080 */               String e = null;
/* 1081 */               if (enc != null) {
/* 1082 */                 e = EncodingUtilities.javaEncoding(enc);
/* 1083 */                 e = (e == null) ? enc : e;
/*      */               } 
/*      */               
/* 1086 */               InputStream is = purl.openStream();
/*      */               
/* 1088 */               if (e == null) {
/*      */                 
/* 1090 */                 reader = new InputStreamReader(is);
/*      */               } else {
/*      */                 try {
/* 1093 */                   reader = new InputStreamReader(is, e);
/* 1094 */                 } catch (UnsupportedEncodingException uee) {
/*      */                   
/* 1096 */                   reader = new InputStreamReader(is);
/*      */                 } 
/*      */               } 
/* 1099 */               Reader reader = new BufferedReader(reader);
/* 1100 */               final StringBuffer sb = new StringBuffer();
/*      */               
/* 1102 */               char[] buf = new char[4096]; int read;
/* 1103 */               while ((read = reader.read(buf, 0, buf.length)) != -1) {
/* 1104 */                 sb.append(buf, 0, read);
/*      */               }
/* 1106 */               reader.close();
/*      */               
/* 1108 */               ScriptingEnvironment.this.updateRunnableQueue.invokeLater(new Runnable() {
/*      */                     public void run() {
/*      */                       try {
/* 1111 */                         h.getURLDone(true, purl.getContentType(), sb.toString());
/*      */                       
/*      */                       }
/* 1114 */                       catch (Exception e) {
/* 1115 */                         if (ScriptingEnvironment.this.userAgent != null) {
/* 1116 */                           ScriptingEnvironment.this.userAgent.displayError(e);
/*      */                         }
/*      */                       } 
/*      */                     }
/*      */                   });
/* 1121 */             } catch (Exception e) {
/* 1122 */               if (e instanceof SecurityException) {
/* 1123 */                 ScriptingEnvironment.this.userAgent.displayError(e);
/*      */               }
/* 1125 */               ScriptingEnvironment.this.updateRunnableQueue.invokeLater(new Runnable() {
/*      */                     public void run() {
/*      */                       try {
/* 1128 */                         h.getURLDone(false, null, null);
/* 1129 */                       } catch (Exception e) {
/* 1130 */                         if (ScriptingEnvironment.this.userAgent != null) {
/* 1131 */                           ScriptingEnvironment.this.userAgent.displayError(e);
/*      */                         }
/*      */                       } 
/*      */                     }
/*      */                   });
/*      */             } 
/*      */           }
/*      */         };
/*      */       
/* 1140 */       t.setPriority(1);
/* 1141 */       t.start();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void postURL(String uri, String content, Window.URLResponseHandler h) {
/* 1147 */       postURL(uri, content, h, "text/plain", null);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void postURL(String uri, String content, Window.URLResponseHandler h, String mimeType) {
/* 1153 */       postURL(uri, content, h, mimeType, null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void postURL(final String uri, final String content, final Window.URLResponseHandler h, final String mimeType, final String fEnc) {
/* 1161 */       Thread t = new Thread() { public void run() { try {
/*      */               URL url;
/*      */               Writer w;
/* 1164 */               String base = ((SVGOMDocument)ScriptingEnvironment.this.document).getDocumentURI();
/*      */ 
/*      */               
/* 1167 */               if (base == null) {
/* 1168 */                 url = new URL(uri);
/*      */               } else {
/* 1170 */                 url = new URL(new URL(base), uri);
/*      */               } 
/*      */               
/* 1173 */               final URLConnection conn = url.openConnection();
/* 1174 */               conn.setDoOutput(true);
/* 1175 */               conn.setDoInput(true);
/* 1176 */               conn.setUseCaches(false);
/* 1177 */               conn.setRequestProperty("Content-Type", mimeType);
/*      */               
/* 1179 */               OutputStream os = conn.getOutputStream();
/* 1180 */               String e = null, enc = fEnc;
/* 1181 */               if (enc != null) {
/* 1182 */                 if (enc.startsWith("deflate")) {
/* 1183 */                   os = new DeflaterOutputStream(os);
/*      */                   
/* 1185 */                   if (enc.length() > "deflate".length()) {
/* 1186 */                     enc = enc.substring("deflate".length() + 1);
/*      */                   } else {
/* 1188 */                     enc = "";
/* 1189 */                   }  conn.setRequestProperty("Content-Encoding", "deflate");
/*      */                 } 
/*      */                 
/* 1192 */                 if (enc.startsWith("gzip")) {
/* 1193 */                   os = new GZIPOutputStream(os);
/* 1194 */                   if (enc.length() > "gzip".length()) {
/* 1195 */                     enc = enc.substring("gzip".length() + 1);
/*      */                   } else {
/* 1197 */                     enc = "";
/* 1198 */                   }  conn.setRequestProperty("Content-Encoding", "deflate");
/*      */                 } 
/*      */                 
/* 1201 */                 if (enc.length() != 0) {
/* 1202 */                   e = EncodingUtilities.javaEncoding(enc);
/* 1203 */                   if (e == null) e = "UTF-8"; 
/*      */                 } else {
/* 1205 */                   e = "UTF-8";
/*      */                 } 
/*      */               } 
/*      */               
/* 1209 */               if (e == null) {
/* 1210 */                 w = new OutputStreamWriter(os);
/*      */               } else {
/* 1212 */                 w = new OutputStreamWriter(os, e);
/* 1213 */               }  w.write(content);
/* 1214 */               w.flush();
/* 1215 */               w.close();
/* 1216 */               os.close();
/*      */               
/* 1218 */               InputStream is = conn.getInputStream();
/*      */               
/* 1220 */               e = "UTF-8";
/* 1221 */               if (e == null) {
/* 1222 */                 r = new InputStreamReader(is);
/*      */               } else {
/* 1224 */                 r = new InputStreamReader(is, e);
/* 1225 */               }  Reader r = new BufferedReader(r);
/*      */               
/* 1227 */               final StringBuffer sb = new StringBuffer();
/*      */               
/* 1229 */               char[] buf = new char[4096]; int read;
/* 1230 */               while ((read = r.read(buf, 0, buf.length)) != -1) {
/* 1231 */                 sb.append(buf, 0, read);
/*      */               }
/* 1233 */               r.close();
/*      */               
/* 1235 */               ScriptingEnvironment.this.updateRunnableQueue.invokeLater(new Runnable() {
/*      */                     public void run() {
/*      */                       try {
/* 1238 */                         h.getURLDone(true, conn.getContentType(), sb.toString());
/*      */                       
/*      */                       }
/* 1241 */                       catch (Exception e) {
/* 1242 */                         if (ScriptingEnvironment.this.userAgent != null) {
/* 1243 */                           ScriptingEnvironment.this.userAgent.displayError(e);
/*      */                         }
/*      */                       } 
/*      */                     }
/*      */                   });
/* 1248 */             } catch (Exception e) {
/* 1249 */               if (e instanceof SecurityException) {
/* 1250 */                 ScriptingEnvironment.this.userAgent.displayError(e);
/*      */               }
/* 1252 */               ScriptingEnvironment.this.updateRunnableQueue.invokeLater(new Runnable() {
/*      */                     public void run() {
/*      */                       try {
/* 1255 */                         h.getURLDone(false, null, null);
/* 1256 */                       } catch (Exception e) {
/* 1257 */                         if (ScriptingEnvironment.this.userAgent != null) {
/* 1258 */                           ScriptingEnvironment.this.userAgent.displayError(e);
/*      */                         }
/*      */                       } 
/*      */                     }
/*      */                   });
/*      */             }  }
/*      */            }
/*      */         ;
/*      */       
/* 1267 */       t.setPriority(1);
/* 1268 */       t.start();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void alert(String message) {
/* 1275 */       if (ScriptingEnvironment.this.userAgent != null) {
/* 1276 */         ScriptingEnvironment.this.userAgent.showAlert(message);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean confirm(String message) {
/* 1284 */       if (ScriptingEnvironment.this.userAgent != null) {
/* 1285 */         return ScriptingEnvironment.this.userAgent.showConfirm(message);
/*      */       }
/* 1287 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String prompt(String message) {
/* 1294 */       if (ScriptingEnvironment.this.userAgent != null) {
/* 1295 */         return ScriptingEnvironment.this.userAgent.showPrompt(message);
/*      */       }
/* 1297 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String prompt(String message, String defVal) {
/* 1304 */       if (ScriptingEnvironment.this.userAgent != null) {
/* 1305 */         return ScriptingEnvironment.this.userAgent.showPrompt(message, defVal);
/*      */       }
/* 1307 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BridgeContext getBridgeContext() {
/* 1314 */       return ScriptingEnvironment.this.bridgeContext;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Interpreter getInterpreter() {
/* 1321 */       return this.interpreter;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public org.apache.batik.w3c.dom.Window getParent() {
/* 1328 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Location getLocation() {
/* 1335 */       if (this.location == null) {
/* 1336 */         this.location = new Location(ScriptingEnvironment.this.bridgeContext);
/*      */       }
/* 1338 */       return this.location;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class DOMNodeInsertedListener
/*      */     implements EventListener
/*      */   {
/* 1346 */     protected LinkedList toExecute = new LinkedList();
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1349 */       Node n = (Node)evt.getTarget();
/* 1350 */       ScriptingEnvironment.this.addScriptingListeners(n);
/* 1351 */       gatherScriptElements(n);
/* 1352 */       while (!this.toExecute.isEmpty()) {
/* 1353 */         ScriptingEnvironment.this.loadScript(this.toExecute.removeFirst());
/*      */       }
/*      */     }
/*      */     
/*      */     protected void gatherScriptElements(Node n) {
/* 1358 */       if (n.getNodeType() == 1) {
/* 1359 */         if (n instanceof org.apache.batik.anim.dom.SVGOMScriptElement) {
/* 1360 */           this.toExecute.add(n);
/*      */         } else {
/* 1362 */           n = n.getFirstChild();
/* 1363 */           while (n != null) {
/* 1364 */             gatherScriptElements(n);
/* 1365 */             n = n.getNextSibling();
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class DOMNodeRemovedListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 1377 */       ScriptingEnvironment.this.removeScriptingListeners((Node)evt.getTarget());
/*      */     }
/*      */   }
/*      */   
/*      */   protected class DOMAttrModifiedListener implements EventListener {
/*      */     public void handleEvent(Event evt) {
/* 1383 */       MutationEvent me = (MutationEvent)evt;
/* 1384 */       if (me.getAttrChange() != 1) {
/* 1385 */         ScriptingEnvironment.this.updateScriptingListeners((Element)me.getTarget(), me.getAttrName());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ScriptingEventListener
/*      */     implements EventListener
/*      */   {
/*      */     protected String attribute;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ScriptingEventListener(String attr) {
/* 1404 */       this.attribute = attr;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleEvent(Event evt) {
/* 1411 */       Element elt = (Element)evt.getCurrentTarget();
/*      */       
/* 1413 */       String script = elt.getAttributeNS((String)null, this.attribute);
/* 1414 */       if (script.length() == 0) {
/*      */         return;
/*      */       }
/* 1417 */       DocumentLoader dl = ScriptingEnvironment.this.bridgeContext.getDocumentLoader();
/* 1418 */       SVGDocument d = (SVGDocument)elt.getOwnerDocument();
/* 1419 */       int line = dl.getLineNumber(elt);
/* 1420 */       String desc = Messages.formatMessage("BaseScriptingEnvironment.constant.event.script.description", new Object[] { d.getURL(), this.attribute, Integer.valueOf(line) });
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1425 */       Element e = elt;
/* 1426 */       while (e != null && (!"http://www.w3.org/2000/svg".equals(e.getNamespaceURI()) || !"svg".equals(e.getLocalName())))
/*      */       {
/*      */ 
/*      */         
/* 1430 */         e = SVGUtilities.getParentElement(e);
/*      */       }
/* 1432 */       if (e == null) {
/*      */         return;
/*      */       }
/* 1435 */       String lang = e.getAttributeNS((String)null, "contentScriptType");
/*      */ 
/*      */       
/* 1438 */       ScriptingEnvironment.this.runEventHandler(script, evt, lang, desc);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/ScriptingEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */