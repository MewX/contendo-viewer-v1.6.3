/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeEventSupport;
/*     */ import org.apache.batik.bridge.FocusManager;
/*     */ import org.apache.batik.bridge.UserAgent;
/*     */ import org.apache.batik.dom.events.DOMKeyboardEvent;
/*     */ import org.apache.batik.dom.events.DOMMouseEvent;
/*     */ import org.apache.batik.dom.events.DOMTextEvent;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.svg12.SVGOMWheelEvent;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.event.EventDispatcher;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeKeyEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeKeyListener;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseListener;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseWheelEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseWheelListener;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.events.DocumentEvent;
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
/*     */ public abstract class SVG12BridgeEventSupport
/*     */   extends BridgeEventSupport
/*     */ {
/*     */   public static void addGVTListener(BridgeContext ctx, Document doc) {
/*  67 */     UserAgent ua = ctx.getUserAgent();
/*  68 */     if (ua != null) {
/*  69 */       EventDispatcher dispatcher = ua.getEventDispatcher();
/*  70 */       if (dispatcher != null) {
/*  71 */         Listener listener = new Listener(ctx, ua);
/*  72 */         dispatcher.addGraphicsNodeMouseListener((GraphicsNodeMouseListener)listener);
/*  73 */         dispatcher.addGraphicsNodeMouseWheelListener(listener);
/*  74 */         dispatcher.addGraphicsNodeKeyListener((GraphicsNodeKeyListener)listener);
/*     */ 
/*     */         
/*  77 */         BridgeEventSupport.GVTUnloadListener gVTUnloadListener = new BridgeEventSupport.GVTUnloadListener(dispatcher, listener);
/*  78 */         NodeEventTarget target = (NodeEventTarget)doc;
/*  79 */         target.addEventListenerNS("http://www.w3.org/2001/xml-events", "SVGUnload", (EventListener)gVTUnloadListener, false, null);
/*     */ 
/*     */ 
/*     */         
/*  83 */         storeEventListenerNS(ctx, (EventTarget)target, "http://www.w3.org/2001/xml-events", "SVGUnload", (EventListener)gVTUnloadListener, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class Listener
/*     */     extends BridgeEventSupport.Listener
/*     */     implements GraphicsNodeMouseWheelListener
/*     */   {
/*     */     protected SVG12BridgeContext ctx12;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Listener(BridgeContext ctx, UserAgent u) {
/* 105 */       super(ctx, u);
/* 106 */       this.ctx12 = (SVG12BridgeContext)ctx;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyPressed(GraphicsNodeKeyEvent evt) {
/* 117 */       if (!this.isDown) {
/* 118 */         this.isDown = true;
/* 119 */         dispatchKeyboardEvent("keydown", evt);
/*     */       } 
/* 121 */       if (evt.getKeyChar() == Character.MAX_VALUE)
/*     */       {
/*     */         
/* 124 */         dispatchTextEvent(evt);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyReleased(GraphicsNodeKeyEvent evt) {
/* 133 */       dispatchKeyboardEvent("keyup", evt);
/* 134 */       this.isDown = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyTyped(GraphicsNodeKeyEvent evt) {
/* 142 */       dispatchTextEvent(evt);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void dispatchKeyboardEvent(String eventType, GraphicsNodeKeyEvent evt) {
/* 150 */       FocusManager fmgr = this.context.getFocusManager();
/* 151 */       if (fmgr == null) {
/*     */         return;
/*     */       }
/*     */       
/* 155 */       Element targetElement = (Element)fmgr.getCurrentEventTarget();
/* 156 */       if (targetElement == null) {
/* 157 */         targetElement = this.context.getDocument().getDocumentElement();
/*     */       }
/* 159 */       DocumentEvent d = (DocumentEvent)targetElement.getOwnerDocument();
/* 160 */       DOMKeyboardEvent keyEvt = (DOMKeyboardEvent)d.createEvent("KeyboardEvent");
/*     */       
/* 162 */       String modifiers = DOMUtilities.getModifiersList(evt.getLockState(), evt.getModifiers());
/*     */ 
/*     */       
/* 165 */       keyEvt.initKeyboardEventNS("http://www.w3.org/2001/xml-events", eventType, true, true, null, mapKeyCodeToIdentifier(evt.getKeyCode()), mapKeyLocation(evt.getKeyLocation()), modifiers);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 175 */         ((EventTarget)targetElement).dispatchEvent((Event)keyEvt);
/* 176 */       } catch (RuntimeException e) {
/* 177 */         this.ua.displayError(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void dispatchTextEvent(GraphicsNodeKeyEvent evt) {
/* 185 */       FocusManager fmgr = this.context.getFocusManager();
/* 186 */       if (fmgr == null) {
/*     */         return;
/*     */       }
/*     */       
/* 190 */       Element targetElement = (Element)fmgr.getCurrentEventTarget();
/* 191 */       if (targetElement == null) {
/* 192 */         targetElement = this.context.getDocument().getDocumentElement();
/*     */       }
/* 194 */       DocumentEvent d = (DocumentEvent)targetElement.getOwnerDocument();
/* 195 */       DOMTextEvent textEvt = (DOMTextEvent)d.createEvent("TextEvent");
/* 196 */       textEvt.initTextEventNS("http://www.w3.org/2001/xml-events", "textInput", true, true, null, String.valueOf(evt.getKeyChar()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 204 */         ((EventTarget)targetElement).dispatchEvent((Event)textEvt);
/* 205 */       } catch (RuntimeException e) {
/* 206 */         this.ua.displayError(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int mapKeyLocation(int location) {
/* 214 */       return location - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     protected static String[][] IDENTIFIER_KEY_CODES = new String[256][];
/*     */     static {
/* 222 */       putIdentifierKeyCode("U+0030", 48);
/*     */       
/* 224 */       putIdentifierKeyCode("U+0031", 49);
/*     */       
/* 226 */       putIdentifierKeyCode("U+0032", 50);
/*     */       
/* 228 */       putIdentifierKeyCode("U+0033", 51);
/*     */       
/* 230 */       putIdentifierKeyCode("U+0034", 52);
/*     */       
/* 232 */       putIdentifierKeyCode("U+0035", 53);
/*     */       
/* 234 */       putIdentifierKeyCode("U+0036", 54);
/*     */       
/* 236 */       putIdentifierKeyCode("U+0037", 55);
/*     */       
/* 238 */       putIdentifierKeyCode("U+0038", 56);
/*     */       
/* 240 */       putIdentifierKeyCode("U+0039", 57);
/*     */       
/* 242 */       putIdentifierKeyCode("Accept", 30);
/*     */       
/* 244 */       putIdentifierKeyCode("Again", 65481);
/*     */       
/* 246 */       putIdentifierKeyCode("U+0041", 65);
/*     */       
/* 248 */       putIdentifierKeyCode("AllCandidates", 256);
/*     */       
/* 250 */       putIdentifierKeyCode("Alphanumeric", 240);
/*     */       
/* 252 */       putIdentifierKeyCode("AltGraph", 65406);
/*     */       
/* 254 */       putIdentifierKeyCode("Alt", 18);
/*     */       
/* 256 */       putIdentifierKeyCode("U+0026", 150);
/*     */       
/* 258 */       putIdentifierKeyCode("U+0027", 222);
/*     */       
/* 260 */       putIdentifierKeyCode("U+002A", 151);
/*     */       
/* 262 */       putIdentifierKeyCode("U+0040", 512);
/*     */       
/* 264 */       putIdentifierKeyCode("U+005C", 92);
/*     */       
/* 266 */       putIdentifierKeyCode("U+0008", 8);
/*     */       
/* 268 */       putIdentifierKeyCode("U+0042", 66);
/*     */       
/* 270 */       putIdentifierKeyCode("U+0018", 3);
/*     */       
/* 272 */       putIdentifierKeyCode("CapsLock", 20);
/*     */       
/* 274 */       putIdentifierKeyCode("U+005E", 514);
/*     */       
/* 276 */       putIdentifierKeyCode("U+0043", 67);
/*     */       
/* 278 */       putIdentifierKeyCode("Clear", 12);
/*     */       
/* 280 */       putIdentifierKeyCode("CodeInput", 258);
/*     */       
/* 282 */       putIdentifierKeyCode("U+003A", 513);
/*     */       
/* 284 */       putIdentifierKeyCode("U+0301", 129);
/*     */       
/* 286 */       putIdentifierKeyCode("U+0306", 133);
/*     */       
/* 288 */       putIdentifierKeyCode("U+030C", 138);
/*     */       
/* 290 */       putIdentifierKeyCode("U+0327", 139);
/*     */       
/* 292 */       putIdentifierKeyCode("U+0302", 130);
/*     */       
/* 294 */       putIdentifierKeyCode("U+0308", 135);
/*     */       
/* 296 */       putIdentifierKeyCode("U+0307", 134);
/*     */       
/* 298 */       putIdentifierKeyCode("U+030B", 137);
/*     */       
/* 300 */       putIdentifierKeyCode("U+0300", 128);
/*     */       
/* 302 */       putIdentifierKeyCode("U+0345", 141);
/*     */       
/* 304 */       putIdentifierKeyCode("U+0304", 132);
/*     */       
/* 306 */       putIdentifierKeyCode("U+0328", 140);
/*     */       
/* 308 */       putIdentifierKeyCode("U+030A", 136);
/*     */       
/* 310 */       putIdentifierKeyCode("U+0303", 131);
/*     */       
/* 312 */       putIdentifierKeyCode("U+002C", 44);
/*     */       
/* 314 */       putIdentifierKeyCode("Compose", 65312);
/*     */       
/* 316 */       putIdentifierKeyCode("Control", 17);
/*     */       
/* 318 */       putIdentifierKeyCode("Convert", 28);
/*     */       
/* 320 */       putIdentifierKeyCode("Copy", 65485);
/*     */       
/* 322 */       putIdentifierKeyCode("Cut", 65489);
/*     */       
/* 324 */       putIdentifierKeyCode("U+007F", 127);
/*     */       
/* 326 */       putIdentifierKeyCode("U+0044", 68);
/*     */       
/* 328 */       putIdentifierKeyCode("U+0024", 515);
/*     */       
/* 330 */       putIdentifierKeyCode("Down", 40);
/*     */       
/* 332 */       putIdentifierKeyCode("U+0045", 69);
/*     */       
/* 334 */       putIdentifierKeyCode("End", 35);
/*     */       
/* 336 */       putIdentifierKeyCode("Enter", 10);
/*     */       
/* 338 */       putIdentifierKeyCode("U+003D", 61);
/*     */       
/* 340 */       putIdentifierKeyCode("U+001B", 27);
/*     */       
/* 342 */       putIdentifierKeyCode("U+20AC", 516);
/*     */       
/* 344 */       putIdentifierKeyCode("U+0021", 517);
/*     */       
/* 346 */       putIdentifierKeyCode("F10", 121);
/*     */       
/* 348 */       putIdentifierKeyCode("F11", 122);
/*     */       
/* 350 */       putIdentifierKeyCode("F12", 123);
/*     */       
/* 352 */       putIdentifierKeyCode("F13", 61440);
/*     */       
/* 354 */       putIdentifierKeyCode("F14", 61441);
/*     */       
/* 356 */       putIdentifierKeyCode("F15", 61442);
/*     */       
/* 358 */       putIdentifierKeyCode("F16", 61443);
/*     */       
/* 360 */       putIdentifierKeyCode("F17", 61444);
/*     */       
/* 362 */       putIdentifierKeyCode("F18", 61445);
/*     */       
/* 364 */       putIdentifierKeyCode("F19", 61446);
/*     */       
/* 366 */       putIdentifierKeyCode("F1", 112);
/*     */       
/* 368 */       putIdentifierKeyCode("F20", 61447);
/*     */       
/* 370 */       putIdentifierKeyCode("F21", 61448);
/*     */       
/* 372 */       putIdentifierKeyCode("F22", 61449);
/*     */       
/* 374 */       putIdentifierKeyCode("F23", 61450);
/*     */       
/* 376 */       putIdentifierKeyCode("F24", 61451);
/*     */       
/* 378 */       putIdentifierKeyCode("F2", 113);
/*     */       
/* 380 */       putIdentifierKeyCode("F3", 114);
/*     */       
/* 382 */       putIdentifierKeyCode("F4", 115);
/*     */       
/* 384 */       putIdentifierKeyCode("F5", 116);
/*     */       
/* 386 */       putIdentifierKeyCode("F6", 117);
/*     */       
/* 388 */       putIdentifierKeyCode("F7", 118);
/*     */       
/* 390 */       putIdentifierKeyCode("F8", 119);
/*     */       
/* 392 */       putIdentifierKeyCode("F9", 120);
/*     */       
/* 394 */       putIdentifierKeyCode("FinalMode", 24);
/*     */       
/* 396 */       putIdentifierKeyCode("Find", 65488);
/*     */       
/* 398 */       putIdentifierKeyCode("U+0046", 70);
/*     */       
/* 400 */       putIdentifierKeyCode("U+002E", 46);
/*     */       
/* 402 */       putIdentifierKeyCode("FullWidth", 243);
/*     */       
/* 404 */       putIdentifierKeyCode("U+0047", 71);
/*     */       
/* 406 */       putIdentifierKeyCode("U+0060", 192);
/*     */       
/* 408 */       putIdentifierKeyCode("U+003E", 160);
/*     */       
/* 410 */       putIdentifierKeyCode("HalfWidth", 244);
/*     */       
/* 412 */       putIdentifierKeyCode("U+0023", 520);
/*     */       
/* 414 */       putIdentifierKeyCode("Help", 156);
/*     */       
/* 416 */       putIdentifierKeyCode("Hiragana", 242);
/*     */       
/* 418 */       putIdentifierKeyCode("U+0048", 72);
/*     */       
/* 420 */       putIdentifierKeyCode("Home", 36);
/*     */       
/* 422 */       putIdentifierKeyCode("U+0049", 73);
/*     */       
/* 424 */       putIdentifierKeyCode("Insert", 155);
/*     */       
/* 426 */       putIdentifierKeyCode("U+00A1", 518);
/*     */       
/* 428 */       putIdentifierKeyCode("JapaneseHiragana", 260);
/*     */       
/* 430 */       putIdentifierKeyCode("JapaneseKatakana", 259);
/*     */       
/* 432 */       putIdentifierKeyCode("JapaneseRomaji", 261);
/*     */       
/* 434 */       putIdentifierKeyCode("U+004A", 74);
/*     */       
/* 436 */       putIdentifierKeyCode("KanaMode", 262);
/*     */       
/* 438 */       putIdentifierKeyCode("KanjiMode", 25);
/*     */       
/* 440 */       putIdentifierKeyCode("Katakana", 241);
/*     */       
/* 442 */       putIdentifierKeyCode("U+004B", 75);
/*     */       
/* 444 */       putIdentifierKeyCode("U+007B", 161);
/*     */       
/* 446 */       putIdentifierKeyCode("Left", 37);
/*     */       
/* 448 */       putIdentifierKeyCode("U+0028", 519);
/*     */       
/* 450 */       putIdentifierKeyCode("U+005B", 91);
/*     */       
/* 452 */       putIdentifierKeyCode("U+003C", 153);
/*     */       
/* 454 */       putIdentifierKeyCode("U+004C", 76);
/*     */       
/* 456 */       putIdentifierKeyCode("Meta", 157);
/*     */       
/* 458 */       putIdentifierKeyCode("Meta", 157);
/*     */       
/* 460 */       putIdentifierKeyCode("U+002D", 45);
/*     */       
/* 462 */       putIdentifierKeyCode("U+004D", 77);
/*     */       
/* 464 */       putIdentifierKeyCode("ModeChange", 31);
/*     */       
/* 466 */       putIdentifierKeyCode("U+004E", 78);
/*     */       
/* 468 */       putIdentifierKeyCode("Nonconvert", 29);
/*     */       
/* 470 */       putIdentifierKeyCode("NumLock", 144);
/*     */       
/* 472 */       putIdentifierKeyCode("NumLock", 144);
/*     */       
/* 474 */       putIdentifierKeyCode("U+004F", 79);
/*     */       
/* 476 */       putIdentifierKeyCode("PageDown", 34);
/*     */       
/* 478 */       putIdentifierKeyCode("PageUp", 33);
/*     */       
/* 480 */       putIdentifierKeyCode("Paste", 65487);
/*     */       
/* 482 */       putIdentifierKeyCode("Pause", 19);
/*     */       
/* 484 */       putIdentifierKeyCode("U+0050", 80);
/*     */       
/* 486 */       putIdentifierKeyCode("U+002B", 521);
/*     */       
/* 488 */       putIdentifierKeyCode("PreviousCandidate", 257);
/*     */       
/* 490 */       putIdentifierKeyCode("PrintScreen", 154);
/*     */       
/* 492 */       putIdentifierKeyCode("Props", 65482);
/*     */       
/* 494 */       putIdentifierKeyCode("U+0051", 81);
/*     */       
/* 496 */       putIdentifierKeyCode("U+0022", 152);
/*     */       
/* 498 */       putIdentifierKeyCode("U+007D", 162);
/*     */       
/* 500 */       putIdentifierKeyCode("Right", 39);
/*     */       
/* 502 */       putIdentifierKeyCode("U+0029", 522);
/*     */       
/* 504 */       putIdentifierKeyCode("U+005D", 93);
/*     */       
/* 506 */       putIdentifierKeyCode("U+0052", 82);
/*     */       
/* 508 */       putIdentifierKeyCode("RomanCharacters", 245);
/*     */       
/* 510 */       putIdentifierKeyCode("Scroll", 145);
/*     */       
/* 512 */       putIdentifierKeyCode("Scroll", 145);
/*     */       
/* 514 */       putIdentifierKeyCode("U+003B", 59);
/*     */       
/* 516 */       putIdentifierKeyCode("U+309A", 143);
/*     */       
/* 518 */       putIdentifierKeyCode("Shift", 16);
/*     */       
/* 520 */       putIdentifierKeyCode("Shift", 16);
/*     */       
/* 522 */       putIdentifierKeyCode("U+0053", 83);
/*     */       
/* 524 */       putIdentifierKeyCode("U+002F", 47);
/*     */       
/* 526 */       putIdentifierKeyCode("U+0020", 32);
/*     */       
/* 528 */       putIdentifierKeyCode("Stop", 65480);
/*     */       
/* 530 */       putIdentifierKeyCode("U+0009", 9);
/*     */       
/* 532 */       putIdentifierKeyCode("U+0054", 84);
/*     */       
/* 534 */       putIdentifierKeyCode("U+0055", 85);
/*     */       
/* 536 */       putIdentifierKeyCode("U+005F", 523);
/*     */       
/* 538 */       putIdentifierKeyCode("Undo", 65483);
/*     */       
/* 540 */       putIdentifierKeyCode("Unidentified", 0);
/*     */       
/* 542 */       putIdentifierKeyCode("Up", 38);
/*     */       
/* 544 */       putIdentifierKeyCode("U+0056", 86);
/*     */       
/* 546 */       putIdentifierKeyCode("U+3099", 142);
/*     */       
/* 548 */       putIdentifierKeyCode("U+0057", 87);
/*     */       
/* 550 */       putIdentifierKeyCode("U+0058", 88);
/*     */       
/* 552 */       putIdentifierKeyCode("U+0059", 89);
/*     */       
/* 554 */       putIdentifierKeyCode("U+005A", 90);
/*     */ 
/*     */       
/* 557 */       putIdentifierKeyCode("U+0030", 96);
/*     */       
/* 559 */       putIdentifierKeyCode("U+0031", 97);
/*     */       
/* 561 */       putIdentifierKeyCode("U+0032", 98);
/*     */       
/* 563 */       putIdentifierKeyCode("U+0033", 99);
/*     */       
/* 565 */       putIdentifierKeyCode("U+0034", 100);
/*     */       
/* 567 */       putIdentifierKeyCode("U+0035", 101);
/*     */       
/* 569 */       putIdentifierKeyCode("U+0036", 102);
/*     */       
/* 571 */       putIdentifierKeyCode("U+0037", 103);
/*     */       
/* 573 */       putIdentifierKeyCode("U+0038", 104);
/*     */       
/* 575 */       putIdentifierKeyCode("U+0039", 105);
/*     */       
/* 577 */       putIdentifierKeyCode("U+002A", 106);
/*     */       
/* 579 */       putIdentifierKeyCode("Down", 225);
/*     */       
/* 581 */       putIdentifierKeyCode("U+002E", 110);
/*     */       
/* 583 */       putIdentifierKeyCode("Left", 226);
/*     */       
/* 585 */       putIdentifierKeyCode("U+002D", 109);
/*     */       
/* 587 */       putIdentifierKeyCode("U+002B", 107);
/*     */       
/* 589 */       putIdentifierKeyCode("Right", 227);
/*     */       
/* 591 */       putIdentifierKeyCode("U+002F", 111);
/*     */       
/* 593 */       putIdentifierKeyCode("Up", 224);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static void putIdentifierKeyCode(String keyIdentifier, int keyCode) {
/* 603 */       if (IDENTIFIER_KEY_CODES[keyCode / 256] == null) {
/* 604 */         IDENTIFIER_KEY_CODES[keyCode / 256] = new String[256];
/*     */       }
/* 606 */       IDENTIFIER_KEY_CODES[keyCode / 256][keyCode % 256] = keyIdentifier;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String mapKeyCodeToIdentifier(int keyCode) {
/* 613 */       String[] a = IDENTIFIER_KEY_CODES[keyCode / 256];
/* 614 */       if (a == null) {
/* 615 */         return "Unidentified";
/*     */       }
/* 617 */       return a[keyCode % 256];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseWheelMoved(GraphicsNodeMouseWheelEvent evt) {
/* 623 */       Document doc = this.context.getPrimaryBridgeContext().getDocument();
/* 624 */       Element targetElement = doc.getDocumentElement();
/* 625 */       DocumentEvent d = (DocumentEvent)doc;
/* 626 */       SVGOMWheelEvent wheelEvt = (SVGOMWheelEvent)d.createEvent("WheelEvent");
/*     */       
/* 628 */       wheelEvt.initWheelEventNS("http://www.w3.org/2001/xml-events", "wheel", true, true, null, evt.getWheelDelta());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 636 */         ((EventTarget)targetElement).dispatchEvent((Event)wheelEvt);
/* 637 */       } catch (RuntimeException e) {
/* 638 */         this.ua.displayError(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseEntered(GraphicsNodeMouseEvent evt) {
/* 645 */       Point clientXY = evt.getClientPoint();
/* 646 */       GraphicsNode node = evt.getGraphicsNode();
/* 647 */       Element targetElement = getEventTarget(node, evt.getPoint2D());
/* 648 */       Element relatedElement = getRelatedElement(evt);
/* 649 */       int n = 0;
/* 650 */       if (relatedElement != null && targetElement != null) {
/* 651 */         n = DefaultXBLManager.computeBubbleLimit(targetElement, relatedElement);
/*     */       }
/*     */       
/* 654 */       dispatchMouseEvent("mouseover", targetElement, relatedElement, clientXY, evt, true, n);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseExited(GraphicsNodeMouseEvent evt) {
/* 664 */       Point clientXY = evt.getClientPoint();
/*     */       
/* 666 */       GraphicsNode node = evt.getRelatedNode();
/* 667 */       Element targetElement = getEventTarget(node, evt.getPoint2D());
/* 668 */       if (this.lastTargetElement != null) {
/* 669 */         int n = 0;
/* 670 */         if (targetElement != null)
/*     */         {
/* 672 */           n = DefaultXBLManager.computeBubbleLimit(this.lastTargetElement, targetElement);
/*     */         }
/*     */         
/* 675 */         dispatchMouseEvent("mouseout", this.lastTargetElement, targetElement, clientXY, evt, true, n);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 682 */         this.lastTargetElement = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseMoved(GraphicsNodeMouseEvent evt) {
/* 687 */       Point clientXY = evt.getClientPoint();
/* 688 */       GraphicsNode node = evt.getGraphicsNode();
/* 689 */       Element targetElement = getEventTarget(node, evt.getPoint2D());
/* 690 */       Element holdLTE = this.lastTargetElement;
/* 691 */       if (holdLTE != targetElement) {
/* 692 */         if (holdLTE != null) {
/* 693 */           int n = 0;
/* 694 */           if (targetElement != null) {
/* 695 */             n = DefaultXBLManager.computeBubbleLimit(holdLTE, targetElement);
/*     */           }
/*     */           
/* 698 */           dispatchMouseEvent("mouseout", holdLTE, targetElement, clientXY, evt, true, n);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 706 */         if (targetElement != null) {
/* 707 */           int n = 0;
/* 708 */           if (holdLTE != null) {
/* 709 */             n = DefaultXBLManager.computeBubbleLimit(targetElement, holdLTE);
/*     */           }
/*     */           
/* 712 */           dispatchMouseEvent("mouseover", targetElement, holdLTE, clientXY, evt, true, n);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 721 */       dispatchMouseEvent("mousemove", targetElement, (Element)null, clientXY, evt, false, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void dispatchMouseEvent(String eventType, Element targetElement, Element relatedElement, Point clientXY, GraphicsNodeMouseEvent evt, boolean cancelable) {
/* 748 */       dispatchMouseEvent(eventType, targetElement, relatedElement, clientXY, evt, cancelable, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void dispatchMouseEvent(String eventType, Element targetElement, Element relatedElement, Point clientXY, GraphicsNodeMouseEvent evt, boolean cancelable, int bubbleLimit) {
/* 772 */       if (this.ctx12.mouseCaptureTarget != null) {
/* 773 */         NodeEventTarget net = null;
/* 774 */         if (targetElement != null) {
/* 775 */           net = (NodeEventTarget)targetElement;
/* 776 */           while (net != null && net != this.ctx12.mouseCaptureTarget) {
/* 777 */             net = net.getParentNodeEventTarget();
/*     */           }
/*     */         } 
/* 780 */         if (net == null) {
/* 781 */           if (this.ctx12.mouseCaptureSendAll) {
/* 782 */             targetElement = (Element)this.ctx12.mouseCaptureTarget;
/*     */           } else {
/* 784 */             targetElement = null;
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 789 */       if (targetElement != null) {
/* 790 */         Point screenXY = evt.getScreenPoint();
/*     */         
/* 792 */         DocumentEvent d = (DocumentEvent)targetElement.getOwnerDocument();
/*     */         
/* 794 */         DOMMouseEvent mouseEvt = (DOMMouseEvent)d.createEvent("MouseEvents");
/*     */         
/* 796 */         String modifiers = DOMUtilities.getModifiersList(evt.getLockState(), evt.getModifiers());
/*     */ 
/*     */         
/* 799 */         mouseEvt.initMouseEventNS("http://www.w3.org/2001/xml-events", eventType, true, cancelable, null, evt.getClickCount(), screenXY.x, screenXY.y, clientXY.x, clientXY.y, (short)(evt.getButton() - 1), (EventTarget)relatedElement, modifiers);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 813 */         mouseEvt.setBubbleLimit(bubbleLimit);
/*     */         
/*     */         try {
/* 816 */           ((EventTarget)targetElement).dispatchEvent((Event)mouseEvt);
/* 817 */         } catch (RuntimeException e) {
/* 818 */           this.ua.displayError(e);
/*     */         } finally {
/* 820 */           this.lastTargetElement = targetElement;
/*     */         } 
/*     */       } 
/*     */       
/* 824 */       if (this.ctx12.mouseCaptureTarget != null && this.ctx12.mouseCaptureAutoRelease && "mouseup".equals(eventType))
/*     */       {
/*     */         
/* 827 */         this.ctx12.stopMouseCapture();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVG12BridgeEventSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */