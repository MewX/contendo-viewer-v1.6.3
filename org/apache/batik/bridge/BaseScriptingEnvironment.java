/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.jar.Manifest;
/*     */ import org.apache.batik.dom.AbstractElement;
/*     */ import org.apache.batik.dom.events.AbstractEvent;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.script.Interpreter;
/*     */ import org.apache.batik.script.InterpreterException;
/*     */ import org.apache.batik.script.ScriptEventWrapper;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.w3c.dom.Location;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.events.DocumentEvent;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.svg.EventListenerInitializer;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ import org.w3c.dom.svg.SVGSVGElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BaseScriptingEnvironment
/*     */ {
/*     */   public static final String INLINE_SCRIPT_DESCRIPTION = "BaseScriptingEnvironment.constant.inline.script.description";
/*     */   public static final String EVENT_SCRIPT_DESCRIPTION = "BaseScriptingEnvironment.constant.event.script.description";
/*     */   protected static final String EVENT_NAME = "event";
/*     */   protected static final String ALTERNATE_EVENT_NAME = "evt";
/*     */   protected static final String APPLICATION_ECMASCRIPT = "application/ecmascript";
/*     */   protected BridgeContext bridgeContext;
/*     */   protected UserAgent userAgent;
/*     */   protected Document document;
/*     */   protected ParsedURL docPURL;
/*     */   
/*     */   public static boolean isDynamicDocument(BridgeContext ctx, Document doc) {
/*  95 */     Element elt = doc.getDocumentElement();
/*  96 */     if (elt != null && "http://www.w3.org/2000/svg".equals(elt.getNamespaceURI())) {
/*     */       
/*  98 */       if (elt.getAttributeNS((String)null, "onabort").length() > 0)
/*     */       {
/* 100 */         return true;
/*     */       }
/* 102 */       if (elt.getAttributeNS((String)null, "onerror").length() > 0)
/*     */       {
/* 104 */         return true;
/*     */       }
/* 106 */       if (elt.getAttributeNS((String)null, "onresize").length() > 0)
/*     */       {
/* 108 */         return true;
/*     */       }
/* 110 */       if (elt.getAttributeNS((String)null, "onunload").length() > 0)
/*     */       {
/* 112 */         return true;
/*     */       }
/* 114 */       if (elt.getAttributeNS((String)null, "onscroll").length() > 0)
/*     */       {
/* 116 */         return true;
/*     */       }
/* 118 */       if (elt.getAttributeNS((String)null, "onzoom").length() > 0)
/*     */       {
/* 120 */         return true;
/*     */       }
/* 122 */       return isDynamicElement(ctx, doc.getDocumentElement());
/*     */     } 
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isDynamicElement(BridgeContext ctx, Element elt) {
/* 128 */     List bridgeExtensions = ctx.getBridgeExtensions(elt.getOwnerDocument());
/* 129 */     return isDynamicElement(elt, ctx, bridgeExtensions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isDynamicElement(Element elt, BridgeContext ctx, List bridgeExtensions) {
/* 137 */     for (Object bridgeExtension1 : bridgeExtensions) {
/* 138 */       BridgeExtension bridgeExtension = (BridgeExtension)bridgeExtension1;
/* 139 */       if (bridgeExtension.isDynamicElement(elt)) {
/* 140 */         return true;
/*     */       }
/*     */     } 
/* 143 */     if ("http://www.w3.org/2000/svg".equals(elt.getNamespaceURI())) {
/* 144 */       if (elt.getAttributeNS((String)null, "onkeyup").length() > 0)
/*     */       {
/* 146 */         return true;
/*     */       }
/* 148 */       if (elt.getAttributeNS((String)null, "onkeydown").length() > 0)
/*     */       {
/* 150 */         return true;
/*     */       }
/* 152 */       if (elt.getAttributeNS((String)null, "onkeypress").length() > 0)
/*     */       {
/* 154 */         return true;
/*     */       }
/* 156 */       if (elt.getAttributeNS((String)null, "onload").length() > 0)
/*     */       {
/* 158 */         return true;
/*     */       }
/* 160 */       if (elt.getAttributeNS((String)null, "onerror").length() > 0)
/*     */       {
/* 162 */         return true;
/*     */       }
/* 164 */       if (elt.getAttributeNS((String)null, "onactivate").length() > 0)
/*     */       {
/* 166 */         return true;
/*     */       }
/* 168 */       if (elt.getAttributeNS((String)null, "onclick").length() > 0)
/*     */       {
/* 170 */         return true;
/*     */       }
/* 172 */       if (elt.getAttributeNS((String)null, "onfocusin").length() > 0)
/*     */       {
/* 174 */         return true;
/*     */       }
/* 176 */       if (elt.getAttributeNS((String)null, "onfocusout").length() > 0)
/*     */       {
/* 178 */         return true;
/*     */       }
/* 180 */       if (elt.getAttributeNS((String)null, "onmousedown").length() > 0)
/*     */       {
/* 182 */         return true;
/*     */       }
/* 184 */       if (elt.getAttributeNS((String)null, "onmousemove").length() > 0)
/*     */       {
/* 186 */         return true;
/*     */       }
/* 188 */       if (elt.getAttributeNS((String)null, "onmouseout").length() > 0)
/*     */       {
/* 190 */         return true;
/*     */       }
/* 192 */       if (elt.getAttributeNS((String)null, "onmouseover").length() > 0)
/*     */       {
/* 194 */         return true;
/*     */       }
/* 196 */       if (elt.getAttributeNS((String)null, "onmouseup").length() > 0)
/*     */       {
/* 198 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 202 */     Node n = elt.getFirstChild();
/* 203 */     for (; n != null; 
/* 204 */       n = n.getNextSibling()) {
/* 205 */       if (n.getNodeType() == 1 && 
/* 206 */         isDynamicElement(ctx, (Element)n)) {
/* 207 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 211 */     return false;
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
/* 244 */   protected Set languages = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Interpreter interpreter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   protected Map windowObjects = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   protected WeakHashMap executedScripts = new WeakHashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseScriptingEnvironment(BridgeContext ctx) {
/* 267 */     this.bridgeContext = ctx;
/* 268 */     this.document = ctx.getDocument();
/* 269 */     this.docPURL = new ParsedURL(((SVGDocument)this.document).getURL());
/* 270 */     this.userAgent = this.bridgeContext.getUserAgent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Window getWindow(Interpreter interp, String lang) {
/* 278 */     Window w = (Window)this.windowObjects.get(interp);
/*     */     
/* 280 */     if (w == null) {
/* 281 */       w = (interp == null) ? new Window(null, null) : createWindow(interp, lang);
/*     */       
/* 283 */       this.windowObjects.put(interp, w);
/*     */     } 
/* 285 */     return w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Window getWindow() {
/* 293 */     return getWindow(null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Window createWindow(Interpreter interp, String lang) {
/* 302 */     return new Window(interp, lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Interpreter getInterpreter() {
/* 309 */     if (this.interpreter != null) {
/* 310 */       return this.interpreter;
/*     */     }
/* 312 */     SVGSVGElement root = (SVGSVGElement)this.document.getDocumentElement();
/* 313 */     String lang = root.getContentScriptType();
/* 314 */     return getInterpreter(lang);
/*     */   }
/*     */   
/*     */   public Interpreter getInterpreter(String lang) {
/* 318 */     this.interpreter = this.bridgeContext.getInterpreter(lang);
/* 319 */     if (this.interpreter == null) {
/* 320 */       if (this.languages.contains(lang))
/*     */       {
/* 322 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 326 */       this.languages.add(lang);
/* 327 */       return null;
/*     */     } 
/*     */     
/* 330 */     if (!this.languages.contains(lang)) {
/* 331 */       this.languages.add(lang);
/* 332 */       initializeEnvironment(this.interpreter, lang);
/*     */     } 
/* 334 */     return this.interpreter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeEnvironment(Interpreter interp, String lang) {
/* 341 */     interp.bindObject("window", getWindow(interp, lang));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadScripts() {
/* 348 */     NodeList scripts = this.document.getElementsByTagNameNS("http://www.w3.org/2000/svg", "script");
/*     */ 
/*     */     
/* 351 */     int len = scripts.getLength();
/* 352 */     for (int i = 0; i < len; i++) {
/* 353 */       AbstractElement script = (AbstractElement)scripts.item(i);
/* 354 */       loadScript(script);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void loadScript(AbstractElement script) {
/*     */     Node node;
/* 364 */     if (this.executedScripts.containsKey(script)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 371 */     AbstractElement abstractElement = script;
/*     */     do {
/* 373 */       node = abstractElement.getParentNode();
/* 374 */       if (node == null) {
/*     */         return;
/*     */       }
/* 377 */     } while (node.getNodeType() != 9);
/*     */ 
/*     */     
/* 380 */     String type = script.getAttributeNS(null, "type");
/*     */ 
/*     */     
/* 383 */     if (type.length() == 0) {
/* 384 */       type = "text/ecmascript";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 390 */     if (type.equals("application/java-archive")) {
/*     */       try {
/* 392 */         String href = XLinkSupport.getXLinkHref((Element)script);
/* 393 */         ParsedURL purl = new ParsedURL(script.getBaseURI(), href);
/*     */         
/* 395 */         checkCompatibleScriptURL(type, purl);
/*     */ 
/*     */         
/* 398 */         URL docURL = null;
/*     */         try {
/* 400 */           docURL = new URL(this.docPURL.toString());
/* 401 */         } catch (MalformedURLException malformedURLException) {}
/*     */ 
/*     */         
/* 404 */         DocumentJarClassLoader cll = new DocumentJarClassLoader(new URL(purl.toString()), docURL);
/*     */ 
/*     */ 
/*     */         
/* 408 */         URL url = cll.findResource("META-INF/MANIFEST.MF");
/* 409 */         if (url == null) {
/*     */           return;
/*     */         }
/* 412 */         Manifest man = new Manifest(url.openStream());
/*     */ 
/*     */ 
/*     */         
/* 416 */         this.executedScripts.put(script, null);
/*     */         
/* 418 */         String sh = man.getMainAttributes().getValue("Script-Handler");
/* 419 */         if (sh != null) {
/*     */ 
/*     */           
/* 422 */           ScriptHandler h = cll.loadClass(sh).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*     */           
/* 424 */           h.run(this.document, getWindow());
/*     */         } 
/*     */         
/* 427 */         sh = man.getMainAttributes().getValue("SVG-Handler-Class");
/* 428 */         if (sh != null)
/*     */         {
/*     */           
/* 431 */           EventListenerInitializer initializer = cll.loadClass(sh).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*     */ 
/*     */           
/* 434 */           getWindow();
/*     */           
/* 436 */           initializer.initializeEventListeners((SVGDocument)this.document);
/*     */         }
/*     */       
/* 439 */       } catch (Exception e) {
/* 440 */         if (this.userAgent != null) {
/* 441 */           this.userAgent.displayError(e);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 450 */     Interpreter interpreter = getInterpreter(type);
/* 451 */     if (interpreter == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 457 */       String href = XLinkSupport.getXLinkHref((Element)script);
/* 458 */       String desc = null;
/* 459 */       Reader reader = null;
/*     */       
/* 461 */       if (href.length() > 0) {
/* 462 */         desc = href;
/*     */ 
/*     */         
/* 465 */         ParsedURL purl = new ParsedURL(script.getBaseURI(), href);
/*     */         
/* 467 */         checkCompatibleScriptURL(type, purl);
/* 468 */         InputStream is = purl.openStream();
/* 469 */         String mediaType = purl.getContentTypeMediaType();
/* 470 */         String enc = purl.getContentTypeCharset();
/* 471 */         if (enc != null) {
/*     */           try {
/* 473 */             reader = new InputStreamReader(is, enc);
/* 474 */           } catch (UnsupportedEncodingException uee) {
/* 475 */             enc = null;
/*     */           } 
/*     */         }
/* 478 */         if (reader == null) {
/* 479 */           if ("application/ecmascript".equals(mediaType)) {
/*     */ 
/*     */             
/* 482 */             if (purl.hasContentTypeParameter("version")) {
/*     */               return;
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 489 */             PushbackInputStream pbis = new PushbackInputStream(is, 8);
/*     */             
/* 491 */             byte[] buf = new byte[4];
/* 492 */             int read = pbis.read(buf);
/* 493 */             if (read > 0) {
/* 494 */               pbis.unread(buf, 0, read);
/* 495 */               if (read >= 2) {
/* 496 */                 if (buf[0] == -1 && buf[1] == -2) {
/*     */                   
/* 498 */                   if (read >= 4 && buf[2] == 0 && buf[3] == 0) {
/*     */                     
/* 500 */                     enc = "UTF32-LE";
/* 501 */                     pbis.skip(4L);
/*     */                   } else {
/* 503 */                     enc = "UTF-16LE";
/* 504 */                     pbis.skip(2L);
/*     */                   } 
/* 506 */                 } else if (buf[0] == -2 && buf[1] == -1) {
/*     */                   
/* 508 */                   enc = "UTF-16BE";
/* 509 */                   pbis.skip(2L);
/* 510 */                 } else if (read >= 3 && buf[0] == -17 && buf[1] == -69 && buf[2] == -65) {
/*     */ 
/*     */ 
/*     */                   
/* 514 */                   enc = "UTF-8";
/* 515 */                   pbis.skip(3L);
/* 516 */                 } else if (read >= 4 && buf[0] == 0 && buf[1] == 0 && buf[2] == -2 && buf[3] == -1) {
/*     */ 
/*     */ 
/*     */                   
/* 520 */                   enc = "UTF-32BE";
/* 521 */                   pbis.skip(4L);
/*     */                 } 
/*     */               }
/* 524 */               if (enc == null) {
/* 525 */                 enc = "UTF-8";
/*     */               }
/*     */             } 
/* 528 */             reader = new InputStreamReader(pbis, enc);
/*     */           } else {
/* 530 */             reader = new InputStreamReader(is);
/*     */           } 
/*     */         }
/*     */       } else {
/* 534 */         checkCompatibleScriptURL(type, this.docPURL);
/* 535 */         DocumentLoader dl = this.bridgeContext.getDocumentLoader();
/* 536 */         AbstractElement abstractElement1 = script;
/* 537 */         SVGDocument d = (SVGDocument)abstractElement1.getOwnerDocument();
/* 538 */         int line = dl.getLineNumber((Element)script);
/* 539 */         desc = Messages.formatMessage("BaseScriptingEnvironment.constant.inline.script.description", new Object[] { d.getURL(), "<" + script.getNodeName() + ">", Integer.valueOf(line) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 545 */         Node n = script.getFirstChild();
/* 546 */         if (n != null) {
/* 547 */           StringBuffer sb = new StringBuffer();
/* 548 */           while (n != null) {
/* 549 */             if (n.getNodeType() == 4 || n.getNodeType() == 3)
/*     */             {
/* 551 */               sb.append(n.getNodeValue()); } 
/* 552 */             n = n.getNextSibling();
/*     */           } 
/* 554 */           reader = new StringReader(sb.toString());
/*     */         } else {
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 560 */       this.executedScripts.put(script, null);
/* 561 */       interpreter.evaluate(reader, desc);
/*     */     }
/* 563 */     catch (IOException e) {
/* 564 */       if (this.userAgent != null) {
/* 565 */         this.userAgent.displayError(e);
/*     */       }
/*     */       return;
/* 568 */     } catch (InterpreterException e) {
/* 569 */       System.err.println("InterpExcept: " + e);
/* 570 */       handleInterpreterException(e);
/*     */       return;
/* 572 */     } catch (SecurityException e) {
/* 573 */       if (this.userAgent != null) {
/* 574 */         this.userAgent.displayError(e);
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
/*     */   protected void checkCompatibleScriptURL(String scriptType, ParsedURL scriptPURL) {
/* 586 */     this.userAgent.checkLoadScript(scriptType, scriptPURL, this.docPURL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatchSVGLoadEvent() {
/* 593 */     SVGSVGElement root = (SVGSVGElement)this.document.getDocumentElement();
/* 594 */     String lang = root.getContentScriptType();
/* 595 */     long documentStartTime = System.currentTimeMillis();
/* 596 */     this.bridgeContext.getAnimationEngine().start(documentStartTime);
/* 597 */     dispatchSVGLoad((Element)root, true, lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchSVGLoad(Element elt, boolean checkCanRun, String lang) {
/*     */     String type;
/* 606 */     Node n = elt.getFirstChild();
/* 607 */     for (; n != null; 
/* 608 */       n = n.getNextSibling()) {
/* 609 */       if (n.getNodeType() == 1) {
/* 610 */         dispatchSVGLoad((Element)n, checkCanRun, lang);
/*     */       }
/*     */     } 
/*     */     
/* 614 */     DocumentEvent de = (DocumentEvent)elt.getOwnerDocument();
/* 615 */     AbstractEvent ev = (AbstractEvent)de.createEvent("SVGEvents");
/*     */     
/* 617 */     if (this.bridgeContext.isSVG12()) {
/* 618 */       type = "load";
/*     */     } else {
/* 620 */       type = "SVGLoad";
/*     */     } 
/* 622 */     ev.initEventNS("http://www.w3.org/2001/xml-events", type, false, false);
/*     */ 
/*     */ 
/*     */     
/* 626 */     NodeEventTarget t = (NodeEventTarget)elt;
/*     */     
/* 628 */     final String s = elt.getAttributeNS((String)null, "onload");
/*     */     
/* 630 */     if (s.length() == 0) {
/*     */ 
/*     */       
/* 633 */       t.dispatchEvent((Event)ev);
/*     */       
/*     */       return;
/*     */     } 
/* 637 */     final Interpreter interp = getInterpreter();
/* 638 */     if (interp == null) {
/*     */ 
/*     */       
/* 641 */       t.dispatchEvent((Event)ev);
/*     */       
/*     */       return;
/*     */     } 
/* 645 */     if (checkCanRun) {
/*     */       
/* 647 */       checkCompatibleScriptURL(lang, this.docPURL);
/* 648 */       checkCanRun = false;
/*     */     } 
/*     */     
/* 651 */     DocumentLoader dl = this.bridgeContext.getDocumentLoader();
/* 652 */     SVGDocument d = (SVGDocument)elt.getOwnerDocument();
/* 653 */     int line = dl.getLineNumber(elt);
/* 654 */     final String desc = Messages.formatMessage("BaseScriptingEnvironment.constant.event.script.description", new Object[] { d.getURL(), "onload", Integer.valueOf(line) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 660 */     EventListener l = new EventListener() {
/*     */         public void handleEvent(Event evt) {
/*     */           
/*     */           try { Object event;
/* 664 */             if (evt instanceof ScriptEventWrapper) {
/* 665 */               event = ((ScriptEventWrapper)evt).getEventObject();
/*     */             } else {
/* 667 */               event = evt;
/*     */             } 
/* 669 */             interp.bindObject("event", event);
/* 670 */             interp.bindObject("evt", event);
/* 671 */             interp.evaluate(new StringReader(s), desc); }
/* 672 */           catch (IOException iOException) {  }
/* 673 */           catch (InterpreterException e)
/* 674 */           { BaseScriptingEnvironment.this.handleInterpreterException(e); }
/*     */         
/*     */         }
/*     */       };
/* 678 */     t.addEventListenerNS("http://www.w3.org/2001/xml-events", type, l, false, null);
/*     */ 
/*     */     
/* 681 */     t.dispatchEvent((Event)ev);
/* 682 */     t.removeEventListenerNS("http://www.w3.org/2001/xml-events", type, l, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchSVGZoomEvent() {
/* 691 */     if (this.bridgeContext.isSVG12()) {
/* 692 */       dispatchSVGDocEvent("zoom");
/*     */     } else {
/* 694 */       dispatchSVGDocEvent("SVGZoom");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchSVGScrollEvent() {
/* 702 */     if (this.bridgeContext.isSVG12()) {
/* 703 */       dispatchSVGDocEvent("scroll");
/*     */     } else {
/* 705 */       dispatchSVGDocEvent("SVGScroll");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchSVGResizeEvent() {
/* 713 */     if (this.bridgeContext.isSVG12()) {
/* 714 */       dispatchSVGDocEvent("resize");
/*     */     } else {
/* 716 */       dispatchSVGDocEvent("SVGResize");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void dispatchSVGDocEvent(String eventType) {
/* 721 */     SVGSVGElement root = (SVGSVGElement)this.document.getDocumentElement();
/*     */ 
/*     */     
/* 724 */     SVGSVGElement sVGSVGElement1 = root;
/*     */     
/* 726 */     DocumentEvent de = (DocumentEvent)this.document;
/* 727 */     AbstractEvent ev = (AbstractEvent)de.createEvent("SVGEvents");
/* 728 */     ev.initEventNS("http://www.w3.org/2001/xml-events", eventType, false, false);
/*     */ 
/*     */ 
/*     */     
/* 732 */     sVGSVGElement1.dispatchEvent((Event)ev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleInterpreterException(InterpreterException ie) {
/* 739 */     if (this.userAgent != null) {
/* 740 */       Exception ex = ie.getException();
/* 741 */       this.userAgent.displayError((ex == null) ? (Exception)ie : ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleSecurityException(SecurityException se) {
/* 749 */     if (this.userAgent != null) {
/* 750 */       this.userAgent.displayError(se);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class Window
/*     */     implements Window
/*     */   {
/*     */     protected Interpreter interpreter;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String language;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Window(Interpreter interp, String lang) {
/* 773 */       this.interpreter = interp;
/* 774 */       this.language = lang;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object setInterval(String script, long interval) {
/* 782 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object setInterval(Runnable r, long interval) {
/* 790 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void clearInterval(Object interval) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object setTimeout(String script, long timeout) {
/* 805 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object setTimeout(Runnable r, long timeout) {
/* 813 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void clearTimeout(Object timeout) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node parseXML(String text, Document doc) {
/* 830 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String printNode(Node n) {
/* 837 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void getURL(String uri, Window.URLResponseHandler h) {
/* 846 */       getURL(uri, h, "UTF8");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void getURL(String uri, Window.URLResponseHandler h, String enc) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void postURL(String uri, String content, Window.URLResponseHandler h) {
/* 862 */       postURL(uri, content, h, "text/plain", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void postURL(String uri, String content, Window.URLResponseHandler h, String mimeType) {
/* 868 */       postURL(uri, content, h, mimeType, null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void postURL(String uri, String content, Window.URLResponseHandler h, String mimeType, String fEnc) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void alert(String message) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean confirm(String message) {
/* 890 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String prompt(String message) {
/* 897 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String prompt(String message, String defVal) {
/* 904 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BridgeContext getBridgeContext() {
/* 911 */       return BaseScriptingEnvironment.this.bridgeContext;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Interpreter getInterpreter() {
/* 918 */       return this.interpreter;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Location getLocation() {
/* 925 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public org.apache.batik.w3c.dom.Window getParent() {
/* 932 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/BaseScriptingEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */