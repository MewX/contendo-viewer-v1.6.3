/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.anim.dom.SVGOMElement;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeException;
/*     */ import org.apache.batik.bridge.CSSUtilities;
/*     */ import org.apache.batik.bridge.CursorManager;
/*     */ import org.apache.batik.bridge.SVGAElementBridge;
/*     */ import org.apache.batik.bridge.SVGTextElementBridge;
/*     */ import org.apache.batik.bridge.SVGUtilities;
/*     */ import org.apache.batik.bridge.TextNode;
/*     */ import org.apache.batik.bridge.TextUtilities;
/*     */ import org.apache.batik.bridge.UnitProcessor;
/*     */ import org.apache.batik.bridge.UserAgent;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ import org.apache.batik.gvt.text.TextPaintInfo;
/*     */ import org.apache.batik.gvt.text.TextPath;
/*     */ import org.apache.batik.parser.UnitProcessor;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BatikFlowTextElementBridge
/*     */   extends SVGTextElementBridge
/*     */   implements BatikExtConstants
/*     */ {
/*  69 */   public static final AttributedCharacterIterator.Attribute FLOW_PARAGRAPH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_PARAGRAPH;
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static final AttributedCharacterIterator.Attribute FLOW_EMPTY_PARAGRAPH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_EMPTY_PARAGRAPH;
/*     */ 
/*     */   
/*  76 */   public static final AttributedCharacterIterator.Attribute FLOW_LINE_BREAK = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_LINE_BREAK;
/*     */ 
/*     */   
/*  79 */   public static final AttributedCharacterIterator.Attribute FLOW_REGIONS = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_REGIONS;
/*     */ 
/*     */   
/*  82 */   public static final AttributedCharacterIterator.Attribute PREFORMATTED = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PREFORMATTED;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  94 */     return "http://xml.apache.org/batik/ext";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 101 */     return "flowText";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/* 108 */     return (Bridge)new BatikFlowTextElementBridge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/* 119 */     return (GraphicsNode)new FlowExtTextNode();
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
/*     */   protected Point2D getLocation(BridgeContext ctx, Element e) {
/* 131 */     return new Point2D.Float(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   protected void addContextToChild(BridgeContext ctx, Element e) {
/* 135 */     if (getNamespaceURI().equals(e.getNamespaceURI())) {
/* 136 */       String ln = e.getLocalName();
/* 137 */       if (ln.equals("flowPara") || ln.equals("flowRegionBreak") || ln.equals("flowLine") || ln.equals("flowSpan") || ln.equals("a") || ln.equals("tref"))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 143 */         ((SVGOMElement)e).setSVGContext((SVGContext)new BatikFlowContentBridge(ctx, this, e));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 149 */     Node child = getFirstChild(e);
/* 150 */     while (child != null) {
/* 151 */       if (child.getNodeType() == 1) {
/* 152 */         addContextToChild(ctx, (Element)child);
/*     */       }
/* 154 */       child = getNextSibling(child);
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
/*     */   protected AttributedString buildAttributedString(BridgeContext ctx, Element element) {
/* 166 */     List rgns = getRegions(ctx, element);
/* 167 */     AttributedString ret = getFlowDiv(ctx, element);
/* 168 */     if (ret == null) return ret;
/*     */     
/* 170 */     ret.addAttribute(FLOW_REGIONS, rgns, 0, 1);
/* 171 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addGlyphPositionAttributes(AttributedString as, Element element, BridgeContext ctx) {
/* 180 */     if (element.getNodeType() != 1)
/* 181 */       return;  String eNS = element.getNamespaceURI();
/* 182 */     if (!eNS.equals(getNamespaceURI()) && !eNS.equals("http://www.w3.org/2000/svg")) {
/*     */       return;
/*     */     }
/* 185 */     if (element.getLocalName() != "flowText") {
/*     */       
/* 187 */       super.addGlyphPositionAttributes(as, element, ctx);
/*     */       
/*     */       return;
/*     */     } 
/* 191 */     Node n = element.getFirstChild();
/* 192 */     for (; n != null; n = n.getNextSibling()) {
/* 193 */       if (n.getNodeType() == 1) {
/* 194 */         String nNS = n.getNamespaceURI();
/* 195 */         if (getNamespaceURI().equals(nNS) || "http://www.w3.org/2000/svg".equals(nNS)) {
/*     */ 
/*     */ 
/*     */           
/* 199 */           Element e = (Element)n;
/* 200 */           String ln = e.getLocalName();
/* 201 */           if (ln.equals("flowDiv")) {
/*     */             
/* 203 */             super.addGlyphPositionAttributes(as, e, ctx);
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addChildGlyphPositionAttributes(AttributedString as, Element element, BridgeContext ctx) {
/* 213 */     Node child = element.getFirstChild();
/* 214 */     for (; child != null; 
/* 215 */       child = child.getNextSibling()) {
/* 216 */       if (child.getNodeType() == 1) {
/*     */ 
/*     */         
/* 219 */         String cNS = child.getNamespaceURI();
/* 220 */         if (getNamespaceURI().equals(cNS) || "http://www.w3.org/2000/svg".equals(cNS)) {
/*     */ 
/*     */ 
/*     */           
/* 224 */           String ln = child.getLocalName();
/* 225 */           if (ln.equals("flowPara") || ln.equals("flowRegionBreak") || ln.equals("flowLine") || ln.equals("flowSpan") || ln.equals("a") || ln.equals("tref"))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 231 */             addGlyphPositionAttributes(as, (Element)child, ctx);
/*     */           }
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
/*     */   protected void addPaintAttributes(AttributedString as, Element element, TextNode node, TextPaintInfo parentPI, BridgeContext ctx) {
/* 244 */     if (element.getNodeType() != 1)
/* 245 */       return;  String eNS = element.getNamespaceURI();
/* 246 */     if (!eNS.equals(getNamespaceURI()) && !eNS.equals("http://www.w3.org/2000/svg")) {
/*     */       return;
/*     */     }
/* 249 */     if (element.getLocalName() != "flowText") {
/*     */       
/* 251 */       super.addPaintAttributes(as, element, node, parentPI, ctx);
/*     */       
/*     */       return;
/*     */     } 
/* 255 */     Node n = element.getFirstChild();
/* 256 */     for (; n != null; n = n.getNextSibling()) {
/* 257 */       if (n.getNodeType() == 1 && 
/* 258 */         getNamespaceURI().equals(n.getNamespaceURI())) {
/* 259 */         Element e = (Element)n;
/* 260 */         String ln = e.getLocalName();
/* 261 */         if (ln.equals("flowDiv")) {
/*     */           
/* 263 */           super.addPaintAttributes(as, e, node, parentPI, ctx);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addChildPaintAttributes(AttributedString as, Element element, TextNode node, TextPaintInfo parentPI, BridgeContext ctx) {
/* 275 */     Node child = element.getFirstChild();
/* 276 */     for (; child != null; 
/* 277 */       child = child.getNextSibling()) {
/* 278 */       if (child.getNodeType() == 1) {
/*     */ 
/*     */         
/* 281 */         String cNS = child.getNamespaceURI();
/* 282 */         if (getNamespaceURI().equals(cNS) || "http://www.w3.org/2000/svg".equals(cNS)) {
/*     */ 
/*     */ 
/*     */           
/* 286 */           String ln = child.getLocalName();
/* 287 */           if (ln.equals("flowPara") || ln.equals("flowRegionBreak") || ln.equals("flowLine") || ln.equals("flowSpan") || ln.equals("a") || ln.equals("tref")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 293 */             Element childElement = (Element)child;
/* 294 */             TextPaintInfo pi = getTextPaintInfo(childElement, (GraphicsNode)node, parentPI, ctx);
/*     */             
/* 296 */             addPaintAttributes(as, childElement, node, pi, ctx);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   protected AttributedString getFlowDiv(BridgeContext ctx, Element element) {
/* 303 */     Node n = element.getFirstChild();
/* 304 */     for (; n != null; n = n.getNextSibling()) {
/* 305 */       if (n.getNodeType() == 1 && 
/* 306 */         getNamespaceURI().equals(n.getNamespaceURI())) {
/* 307 */         Element e = (Element)n;
/*     */         
/* 309 */         String ln = n.getLocalName();
/* 310 */         if (ln.equals("flowDiv"))
/* 311 */           return gatherFlowPara(ctx, e); 
/*     */       } 
/*     */     } 
/* 314 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected AttributedString gatherFlowPara(BridgeContext ctx, Element div) {
/* 319 */     TextPaintInfo divTPI = new TextPaintInfo();
/*     */     
/* 321 */     divTPI.visible = true;
/* 322 */     divTPI.fillPaint = Color.black;
/* 323 */     this.elemTPI.put(div, divTPI);
/*     */     
/* 325 */     SVGTextElementBridge.AttributedStringBuffer asb = new SVGTextElementBridge.AttributedStringBuffer();
/* 326 */     List<Integer> paraEnds = new ArrayList();
/* 327 */     List<Element> paraElems = new ArrayList();
/* 328 */     List lnLocs = new ArrayList();
/* 329 */     Node n = div.getFirstChild();
/* 330 */     for (; n != null; 
/* 331 */       n = n.getNextSibling()) {
/*     */       
/* 333 */       if (n.getNodeType() == 1 && getNamespaceURI().equals(n.getNamespaceURI())) {
/*     */ 
/*     */ 
/*     */         
/* 337 */         Element e = (Element)n;
/*     */         
/* 339 */         String ln = e.getLocalName();
/* 340 */         if (ln.equals("flowPara")) {
/* 341 */           fillAttributedStringBuffer(ctx, e, true, (Integer)null, (Map)null, asb, lnLocs);
/*     */ 
/*     */           
/* 344 */           paraElems.add(e);
/* 345 */           paraEnds.add(Integer.valueOf(asb.length()));
/* 346 */         } else if (ln.equals("flowRegionBreak")) {
/* 347 */           fillAttributedStringBuffer(ctx, e, true, (Integer)null, (Map)null, asb, lnLocs);
/*     */ 
/*     */           
/* 350 */           paraElems.add(e);
/* 351 */           paraEnds.add(Integer.valueOf(asb.length()));
/*     */         } 
/*     */       } 
/* 354 */     }  divTPI.startChar = 0;
/* 355 */     divTPI.endChar = asb.length() - 1;
/*     */ 
/*     */ 
/*     */     
/* 359 */     AttributedString ret = asb.toAttributedString();
/*     */ 
/*     */ 
/*     */     
/* 363 */     int prevLN = 0;
/* 364 */     Iterator<Integer> lnIter = lnLocs.iterator();
/* 365 */     while (lnIter.hasNext()) {
/* 366 */       int nextLN = ((Integer)lnIter.next()).intValue();
/* 367 */       if (nextLN == prevLN)
/*     */         continue; 
/* 369 */       ret.addAttribute(FLOW_LINE_BREAK, new Object(), prevLN, nextLN);
/*     */ 
/*     */ 
/*     */       
/* 373 */       prevLN = nextLN;
/*     */     } 
/*     */     
/* 376 */     int start = 0;
/*     */     
/* 378 */     List<MarginInfo> emptyPara = null;
/* 379 */     for (int i = 0; i < paraElems.size(); i++, start = end) {
/* 380 */       Element elem = paraElems.get(i);
/* 381 */       int end = ((Integer)paraEnds.get(i)).intValue();
/* 382 */       if (start == end) {
/* 383 */         if (emptyPara == null)
/* 384 */           emptyPara = new LinkedList(); 
/* 385 */         emptyPara.add(makeMarginInfo(elem));
/*     */       }
/*     */       else {
/*     */         
/* 389 */         ret.addAttribute(FLOW_PARAGRAPH, makeMarginInfo(elem), start, end);
/* 390 */         if (emptyPara != null) {
/* 391 */           ret.addAttribute(FLOW_EMPTY_PARAGRAPH, emptyPara, start, end);
/* 392 */           emptyPara = null;
/*     */         } 
/*     */       } 
/*     */     } 
/* 396 */     return ret;
/*     */   }
/*     */   
/*     */   protected List getRegions(BridgeContext ctx, Element element) {
/* 400 */     List ret = new LinkedList();
/* 401 */     Node n = element.getFirstChild();
/* 402 */     for (; n != null; n = n.getNextSibling()) {
/*     */       
/* 404 */       if (n.getNodeType() == 1 && 
/* 405 */         getNamespaceURI().equals(n.getNamespaceURI())) {
/*     */         
/* 407 */         Element e = (Element)n;
/*     */         
/* 409 */         String ln = e.getLocalName();
/* 410 */         if ("flowRegion".equals(ln)) {
/*     */ 
/*     */           
/* 413 */           float verticalAlignment = 0.0F;
/* 414 */           String verticalAlignmentAttribute = e.getAttribute("vertical-align");
/*     */ 
/*     */           
/* 417 */           if (verticalAlignmentAttribute != null && verticalAlignmentAttribute.length() > 0)
/*     */           {
/* 419 */             if ("top".equals(verticalAlignmentAttribute)) {
/*     */               
/* 421 */               verticalAlignment = 0.0F;
/* 422 */             } else if ("middle".equals(verticalAlignmentAttribute)) {
/*     */               
/* 424 */               verticalAlignment = 0.5F;
/* 425 */             } else if ("bottom".equals(verticalAlignmentAttribute)) {
/*     */               
/* 427 */               verticalAlignment = 1.0F;
/*     */             } 
/*     */           }
/*     */           
/* 431 */           gatherRegionInfo(ctx, e, verticalAlignment, ret);
/*     */         } 
/*     */       } 
/* 434 */     }  return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void gatherRegionInfo(BridgeContext ctx, Element rgn, float verticalAlign, List<RegionInfo> regions) {
/* 440 */     Node n = rgn.getFirstChild();
/* 441 */     for (; n != null; n = n.getNextSibling()) {
/*     */       
/* 443 */       if (n.getNodeType() == 1 && 
/* 444 */         getNamespaceURI().equals(n.getNamespaceURI())) {
/* 445 */         Element e = (Element)n;
/* 446 */         String ln = n.getLocalName();
/* 447 */         if (ln.equals("rect")) {
/*     */           
/* 449 */           UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, e);
/*     */           
/* 451 */           RegionInfo ri = buildRegion(uctx, e, verticalAlign);
/* 452 */           if (ri != null) {
/* 453 */             regions.add(ri);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected RegionInfo buildRegion(UnitProcessor.Context uctx, Element e, float verticalAlignment) {
/*     */     float w, h;
/* 464 */     String s = e.getAttribute("x");
/* 465 */     float x = 0.0F;
/* 466 */     if (s.length() != 0) {
/* 467 */       x = UnitProcessor.svgHorizontalCoordinateToUserSpace(s, "x", uctx);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 472 */     s = e.getAttribute("y");
/* 473 */     float y = 0.0F;
/* 474 */     if (s.length() != 0) {
/* 475 */       y = UnitProcessor.svgVerticalCoordinateToUserSpace(s, "y", uctx);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 480 */     s = e.getAttribute("width");
/*     */     
/* 482 */     if (s.length() != 0) {
/* 483 */       w = UnitProcessor.svgHorizontalLengthToUserSpace(s, "width", uctx);
/*     */     } else {
/*     */       
/* 486 */       throw new BridgeException(this.ctx, e, "attribute.missing", new Object[] { "width", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 491 */     if (w == 0.0F) {
/* 492 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 496 */     s = e.getAttribute("height");
/*     */     
/* 498 */     if (s.length() != 0) {
/* 499 */       h = UnitProcessor.svgVerticalLengthToUserSpace(s, "height", uctx);
/*     */     } else {
/*     */       
/* 502 */       throw new BridgeException(this.ctx, e, "attribute.missing", new Object[] { "height", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 507 */     if (h == 0.0F) {
/* 508 */       return null;
/*     */     }
/*     */     
/* 511 */     return new RegionInfo(x, y, w, h, verticalAlignment);
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
/*     */   protected void fillAttributedStringBuffer(BridgeContext ctx, Element element, boolean top, Integer bidiLevel, Map<?, ?> initialAttributes, SVGTextElementBridge.AttributedStringBuffer asb, List<Integer> lnLocs) {
/* 526 */     if (!SVGUtilities.matchUserAgent(element, ctx.getUserAgent()) || !CSSUtilities.convertDisplay(element)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 531 */     String s = XMLSupport.getXMLSpace(element);
/* 532 */     boolean preserve = s.equals("preserve");
/*     */     
/* 534 */     Element nodeElement = element;
/* 535 */     int elementStartChar = asb.length();
/*     */     
/* 537 */     if (top)
/* 538 */       this.endLimit = 0; 
/* 539 */     if (preserve) {
/* 540 */       this.endLimit = asb.length();
/*     */     }
/* 542 */     Map map = (initialAttributes == null) ? new HashMap<Object, Object>() : new HashMap<Object, Object>(initialAttributes);
/*     */ 
/*     */     
/* 545 */     initialAttributes = getAttributeMap(ctx, element, (TextPath)null, bidiLevel, map);
/* 546 */     Object o = map.get(TextAttribute.BIDI_EMBEDDING);
/* 547 */     Integer subBidiLevel = bidiLevel;
/* 548 */     if (o != null) {
/* 549 */       subBidiLevel = (Integer)o;
/*     */     }
/*     */     
/* 552 */     Node n = element.getFirstChild();
/* 553 */     for (; n != null; 
/* 554 */       n = n.getNextSibling()) {
/*     */       boolean prevEndsWithSpace; String ln;
/* 556 */       if (preserve) {
/* 557 */         prevEndsWithSpace = false;
/*     */       }
/* 559 */       else if (asb.length() == 0) {
/* 560 */         prevEndsWithSpace = true;
/*     */       } else {
/* 562 */         prevEndsWithSpace = (asb.getLastChar() == 32);
/*     */       } 
/*     */ 
/*     */       
/* 566 */       switch (n.getNodeType()) {
/*     */         
/*     */         case 1:
/* 569 */           if (!getNamespaceURI().equals(n.getNamespaceURI()) && !"http://www.w3.org/2000/svg".equals(n.getNamespaceURI())) {
/*     */             break;
/*     */           }
/*     */           
/* 573 */           nodeElement = (Element)n;
/*     */           
/* 575 */           ln = n.getLocalName();
/*     */           
/* 577 */           if (ln.equals("flowLine")) {
/* 578 */             int before = asb.length();
/* 579 */             fillAttributedStringBuffer(ctx, nodeElement, false, subBidiLevel, initialAttributes, asb, lnLocs);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 584 */             lnLocs.add(Integer.valueOf(asb.length()));
/* 585 */             if (asb.length() != before)
/* 586 */               initialAttributes = null;  break;
/*     */           } 
/* 588 */           if (ln.equals("flowSpan") || ln.equals("altGlyph")) {
/*     */             
/* 590 */             int before = asb.length();
/* 591 */             fillAttributedStringBuffer(ctx, nodeElement, false, subBidiLevel, initialAttributes, asb, lnLocs);
/*     */ 
/*     */             
/* 594 */             if (asb.length() != before)
/* 595 */               initialAttributes = null;  break;
/*     */           } 
/* 597 */           if (ln.equals("a")) {
/* 598 */             if (ctx.isInteractive()) {
/* 599 */               NodeEventTarget target = (NodeEventTarget)nodeElement;
/* 600 */               UserAgent ua = ctx.getUserAgent();
/*     */               
/* 602 */               SVGAElementBridge.CursorHolder ch = new SVGAElementBridge.CursorHolder(CursorManager.DEFAULT_CURSOR);
/*     */               
/* 604 */               target.addEventListenerNS("http://www.w3.org/2001/xml-events", "click", (EventListener)new SVGAElementBridge.AnchorListener(ua, ch), false, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 610 */               target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", (EventListener)new SVGAElementBridge.CursorMouseOverListener(ua, ch), false, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 616 */               target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", (EventListener)new SVGAElementBridge.CursorMouseOutListener(ua, ch), false, null);
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 622 */             int before = asb.length();
/* 623 */             fillAttributedStringBuffer(ctx, nodeElement, false, subBidiLevel, initialAttributes, asb, lnLocs);
/*     */ 
/*     */             
/* 626 */             if (asb.length() != before)
/* 627 */               initialAttributes = null;  break;
/*     */           } 
/* 629 */           if (ln.equals("tref")) {
/* 630 */             String uriStr = XLinkSupport.getXLinkHref((Element)n);
/* 631 */             Element ref = ctx.getReferencedElement((Element)n, uriStr);
/* 632 */             s = TextUtilities.getElementContent(ref);
/* 633 */             s = normalizeString(s, preserve, prevEndsWithSpace);
/* 634 */             if (s.length() != 0) {
/* 635 */               int trefStart = asb.length();
/* 636 */               HashMap m = (initialAttributes == null) ? new HashMap<Object, Object>() : new HashMap<Object, Object>(initialAttributes);
/*     */ 
/*     */               
/* 639 */               getAttributeMap(ctx, nodeElement, (TextPath)null, bidiLevel, m);
/* 640 */               asb.append(s, m);
/* 641 */               int trefEnd = asb.length() - 1;
/*     */               
/* 643 */               TextPaintInfo textPaintInfo = (TextPaintInfo)this.elemTPI.get(nodeElement);
/* 644 */               textPaintInfo.startChar = trefStart;
/* 645 */               textPaintInfo.endChar = trefEnd;
/* 646 */               initialAttributes = null;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 3:
/*     */         case 4:
/* 653 */           s = n.getNodeValue();
/* 654 */           s = normalizeString(s, preserve, prevEndsWithSpace);
/* 655 */           if (s.length() != 0) {
/* 656 */             asb.append(s, map);
/* 657 */             if (preserve) {
/* 658 */               this.endLimit = asb.length();
/*     */             }
/* 660 */             initialAttributes = null;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/* 665 */     if (top) {
/* 666 */       boolean strippedSome = false;
/* 667 */       while (this.endLimit < asb.length() && asb.getLastChar() == 32) {
/* 668 */         asb.stripLast();
/* 669 */         strippedSome = true;
/*     */       } 
/* 671 */       if (strippedSome) {
/* 672 */         Iterator<TextPaintInfo> iter = this.elemTPI.values().iterator();
/* 673 */         while (iter.hasNext()) {
/* 674 */           TextPaintInfo textPaintInfo = iter.next();
/* 675 */           if (textPaintInfo.endChar >= asb.length()) {
/* 676 */             textPaintInfo.endChar = asb.length() - 1;
/* 677 */             if (textPaintInfo.startChar > textPaintInfo.endChar)
/* 678 */               textPaintInfo.startChar = textPaintInfo.endChar; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 683 */     int elementEndChar = asb.length() - 1;
/* 684 */     TextPaintInfo tpi = (TextPaintInfo)this.elemTPI.get(element);
/* 685 */     tpi.startChar = elementStartChar;
/* 686 */     tpi.endChar = elementEndChar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map getAttributeMap(BridgeContext ctx, Element element, TextPath textPath, Integer bidiLevel, Map<AttributedCharacterIterator.Attribute, Boolean> result) {
/* 694 */     Map initialMap = super.getAttributeMap(ctx, element, textPath, bidiLevel, result);
/*     */ 
/*     */     
/* 697 */     String s = element.getAttribute("preformatted");
/* 698 */     if (s.length() != 0 && 
/* 699 */       s.equals("true")) {
/* 700 */       result.put(PREFORMATTED, Boolean.TRUE);
/*     */     }
/*     */     
/* 703 */     return initialMap;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void checkMap(Map attrs) {
/* 708 */     if (attrs.containsKey(TEXTPATH)) {
/*     */       return;
/*     */     }
/*     */     
/* 712 */     if (attrs.containsKey(ANCHOR_TYPE)) {
/*     */       return;
/*     */     }
/*     */     
/* 716 */     if (attrs.containsKey(LETTER_SPACING)) {
/*     */       return;
/*     */     }
/*     */     
/* 720 */     if (attrs.containsKey(WORD_SPACING)) {
/*     */       return;
/*     */     }
/*     */     
/* 724 */     if (attrs.containsKey(KERNING)) {
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 730 */   protected static final GVTAttributedCharacterIterator.TextAttribute TEXTPATH = GVTAttributedCharacterIterator.TextAttribute.TEXTPATH;
/*     */ 
/*     */ 
/*     */   
/* 734 */   protected static final GVTAttributedCharacterIterator.TextAttribute ANCHOR_TYPE = GVTAttributedCharacterIterator.TextAttribute.ANCHOR_TYPE;
/*     */ 
/*     */ 
/*     */   
/* 738 */   protected static final GVTAttributedCharacterIterator.TextAttribute LETTER_SPACING = GVTAttributedCharacterIterator.TextAttribute.LETTER_SPACING;
/*     */ 
/*     */ 
/*     */   
/* 742 */   protected static final GVTAttributedCharacterIterator.TextAttribute WORD_SPACING = GVTAttributedCharacterIterator.TextAttribute.WORD_SPACING;
/*     */ 
/*     */ 
/*     */   
/* 746 */   protected static final GVTAttributedCharacterIterator.TextAttribute KERNING = GVTAttributedCharacterIterator.TextAttribute.KERNING;
/*     */ 
/*     */ 
/*     */   
/*     */   public static class LineBreakInfo
/*     */   {
/*     */     int breakIdx;
/*     */     
/*     */     float lineAdvAdj;
/*     */     
/*     */     boolean relative;
/*     */ 
/*     */     
/*     */     public LineBreakInfo(int breakIdx, float lineAdvAdj, boolean relative) {
/* 760 */       this.breakIdx = breakIdx;
/* 761 */       this.lineAdvAdj = lineAdvAdj;
/* 762 */       this.relative = relative;
/*     */     }
/* 764 */     public int getBreakIdx() { return this.breakIdx; }
/* 765 */     public boolean isRelative() { return this.relative; } public float getLineAdvAdj() {
/* 766 */       return this.lineAdvAdj;
/*     */     }
/*     */   }
/*     */   
/*     */   public MarginInfo makeMarginInfo(Element e) {
/* 771 */     float top = 0.0F, right = 0.0F, bottom = 0.0F, left = 0.0F;
/*     */     
/* 773 */     String s = e.getAttribute("margin");
/*     */     try {
/* 775 */       if (s.length() != 0) {
/* 776 */         float f = Float.parseFloat(s);
/* 777 */         top = right = bottom = left = f;
/*     */       } 
/* 779 */     } catch (NumberFormatException numberFormatException) {}
/*     */     
/* 781 */     s = e.getAttribute("top-margin");
/*     */     try {
/* 783 */       if (s.length() != 0) {
/* 784 */         float f = Float.parseFloat(s);
/* 785 */         top = f;
/*     */       } 
/* 787 */     } catch (NumberFormatException numberFormatException) {}
/* 788 */     s = e.getAttribute("right-margin");
/*     */     try {
/* 790 */       if (s.length() != 0) {
/* 791 */         float f = Float.parseFloat(s);
/* 792 */         right = f;
/*     */       } 
/* 794 */     } catch (NumberFormatException numberFormatException) {}
/* 795 */     s = e.getAttribute("bottom-margin");
/*     */     try {
/* 797 */       if (s.length() != 0) {
/* 798 */         float f = Float.parseFloat(s);
/* 799 */         bottom = f;
/*     */       } 
/* 801 */     } catch (NumberFormatException numberFormatException) {}
/* 802 */     s = e.getAttribute("left-margin");
/*     */     try {
/* 804 */       if (s.length() != 0) {
/* 805 */         float f = Float.parseFloat(s);
/* 806 */         left = f;
/*     */       } 
/* 808 */     } catch (NumberFormatException numberFormatException) {}
/*     */     
/* 810 */     float indent = 0.0F;
/* 811 */     s = e.getAttribute("indent");
/*     */     try {
/* 813 */       if (s.length() != 0) {
/* 814 */         float f = Float.parseFloat(s);
/* 815 */         indent = f;
/*     */       } 
/* 817 */     } catch (NumberFormatException numberFormatException) {}
/*     */     
/* 819 */     int justification = 0;
/* 820 */     s = e.getAttribute("justification");
/*     */     try {
/* 822 */       if (s.length() != 0) {
/* 823 */         if ("start".equals(s)) {
/* 824 */           justification = 0;
/* 825 */         } else if ("middle".equals(s)) {
/* 826 */           justification = 1;
/* 827 */         } else if ("end".equals(s)) {
/* 828 */           justification = 2;
/* 829 */         } else if ("full".equals(s)) {
/* 830 */           justification = 3;
/*     */         } 
/*     */       }
/* 833 */     } catch (NumberFormatException numberFormatException) {}
/*     */     
/* 835 */     String ln = e.getLocalName();
/* 836 */     boolean rgnBr = ln.equals("flowRegionBreak");
/* 837 */     return new MarginInfo(top, right, bottom, left, indent, justification, rgnBr);
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
/*     */   protected class BatikFlowContentBridge
/*     */     extends SVGTextElementBridge.AbstractTextChildTextContent
/*     */   {
/*     */     public BatikFlowContentBridge(BridgeContext ctx, SVGTextElementBridge parent, Element e) {
/* 853 */       super(BatikFlowTextElementBridge.this, ctx, parent, e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikFlowTextElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */