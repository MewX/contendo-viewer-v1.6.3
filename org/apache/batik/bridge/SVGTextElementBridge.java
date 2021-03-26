/*      */ package org.apache.batik.bridge;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.Color;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.font.TextAttribute;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.text.AttributedCharacterIterator;
/*      */ import java.text.AttributedString;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.WeakHashMap;
/*      */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*      */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*      */ import org.apache.batik.anim.dom.SVGOMAnimatedEnumeration;
/*      */ import org.apache.batik.anim.dom.SVGOMAnimatedLengthList;
/*      */ import org.apache.batik.anim.dom.SVGOMAnimatedNumberList;
/*      */ import org.apache.batik.anim.dom.SVGOMElement;
/*      */ import org.apache.batik.anim.dom.SVGOMTextPositioningElement;
/*      */ import org.apache.batik.css.engine.CSSEngineEvent;
/*      */ import org.apache.batik.css.engine.CSSStylableElement;
/*      */ import org.apache.batik.css.engine.StyleMap;
/*      */ import org.apache.batik.css.engine.value.ListValue;
/*      */ import org.apache.batik.css.engine.value.Value;
/*      */ import org.apache.batik.dom.events.NodeEventTarget;
/*      */ import org.apache.batik.dom.svg.LiveAttributeException;
/*      */ import org.apache.batik.dom.svg.SVGContext;
/*      */ import org.apache.batik.dom.svg.SVGTextContent;
/*      */ import org.apache.batik.dom.util.XLinkSupport;
/*      */ import org.apache.batik.dom.util.XMLSupport;
/*      */ import org.apache.batik.gvt.GraphicsNode;
/*      */ import org.apache.batik.gvt.font.GVTFont;
/*      */ import org.apache.batik.gvt.font.GVTFontFamily;
/*      */ import org.apache.batik.gvt.font.GVTGlyphMetrics;
/*      */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*      */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*      */ import org.apache.batik.gvt.text.TextPaintInfo;
/*      */ import org.apache.batik.gvt.text.TextPath;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventListener;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ import org.w3c.dom.events.MutationEvent;
/*      */ import org.w3c.dom.svg.SVGLengthList;
/*      */ import org.w3c.dom.svg.SVGNumberList;
/*      */ import org.w3c.dom.svg.SVGTextContentElement;
/*      */ import org.w3c.dom.svg.SVGTextPositioningElement;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SVGTextElementBridge
/*      */   extends AbstractGraphicsNodeBridge
/*      */   implements SVGTextContent
/*      */ {
/*   95 */   protected static final Integer ZERO = Integer.valueOf(0);
/*      */ 
/*      */   
/*   98 */   public static final AttributedCharacterIterator.Attribute TEXT_COMPOUND_DELIMITER = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXT_COMPOUND_DELIMITER;
/*      */ 
/*      */ 
/*      */   
/*  102 */   public static final AttributedCharacterIterator.Attribute TEXT_COMPOUND_ID = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXT_COMPOUND_ID;
/*      */ 
/*      */   
/*  105 */   public static final AttributedCharacterIterator.Attribute PAINT_INFO = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;
/*      */ 
/*      */ 
/*      */   
/*  109 */   public static final AttributedCharacterIterator.Attribute ALT_GLYPH_HANDLER = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ALT_GLYPH_HANDLER;
/*      */ 
/*      */ 
/*      */   
/*  113 */   public static final AttributedCharacterIterator.Attribute TEXTPATH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXTPATH;
/*      */ 
/*      */ 
/*      */   
/*  117 */   public static final AttributedCharacterIterator.Attribute ANCHOR_TYPE = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ANCHOR_TYPE;
/*      */ 
/*      */ 
/*      */   
/*  121 */   public static final AttributedCharacterIterator.Attribute GVT_FONT_FAMILIES = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.GVT_FONT_FAMILIES;
/*      */ 
/*      */ 
/*      */   
/*  125 */   public static final AttributedCharacterIterator.Attribute GVT_FONTS = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.GVT_FONTS;
/*      */ 
/*      */ 
/*      */   
/*  129 */   public static final AttributedCharacterIterator.Attribute BASELINE_SHIFT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.BASELINE_SHIFT;
/*      */ 
/*      */ 
/*      */   
/*      */   protected AttributedString laidoutText;
/*      */ 
/*      */   
/*  136 */   protected WeakHashMap elemTPI = new WeakHashMap<Object, Object>();
/*      */ 
/*      */   
/*      */   protected boolean usingComplexSVGFont = false;
/*      */ 
/*      */   
/*      */   protected DOMChildNodeRemovedEventListener childNodeRemovedEventListener;
/*      */ 
/*      */   
/*      */   protected DOMSubtreeModifiedEventListener subtreeModifiedEventListener;
/*      */   
/*      */   private boolean hasNewACI;
/*      */   
/*      */   private Element cssProceedElement;
/*      */   
/*      */   protected int endLimit;
/*      */ 
/*      */   
/*      */   public String getLocalName() {
/*  155 */     return "text";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Bridge getInstance() {
/*  162 */     return new SVGTextElementBridge();
/*      */   }
/*      */   
/*      */   protected TextNode getTextNode() {
/*  166 */     return (TextNode)this.node;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/*  176 */     TextNode node = (TextNode)super.createGraphicsNode(ctx, e);
/*  177 */     if (node == null) {
/*  178 */       return null;
/*      */     }
/*  180 */     associateSVGContext(ctx, e, (GraphicsNode)node);
/*      */ 
/*      */ 
/*      */     
/*  184 */     Node child = getFirstChild(e);
/*  185 */     while (child != null) {
/*  186 */       if (child.getNodeType() == 1) {
/*  187 */         addContextToChild(ctx, (Element)child);
/*      */       }
/*  189 */       child = getNextSibling(child);
/*      */     } 
/*      */ 
/*      */     
/*  193 */     if (ctx.getTextPainter() != null) {
/*  194 */       node.setTextPainter(ctx.getTextPainter());
/*      */     }
/*      */     
/*  197 */     RenderingHints hints = null;
/*  198 */     hints = CSSUtilities.convertColorRendering(e, hints);
/*  199 */     hints = CSSUtilities.convertTextRendering(e, hints);
/*  200 */     if (hints != null) {
/*  201 */       node.setRenderingHints(hints);
/*      */     }
/*  203 */     node.setLocation(getLocation(ctx, e));
/*      */     
/*  205 */     return (GraphicsNode)node;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected GraphicsNode instantiateGraphicsNode() {
/*  213 */     return (GraphicsNode)new TextNode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Point2D getLocation(BridgeContext ctx, Element e) {
/*      */     try {
/*  225 */       SVGOMTextPositioningElement te = (SVGOMTextPositioningElement)e;
/*      */ 
/*      */       
/*  228 */       SVGOMAnimatedLengthList _x = (SVGOMAnimatedLengthList)te.getX();
/*  229 */       _x.check();
/*  230 */       SVGLengthList xs = _x.getAnimVal();
/*  231 */       float x = 0.0F;
/*  232 */       if (xs.getNumberOfItems() > 0) {
/*  233 */         x = xs.getItem(0).getValue();
/*      */       }
/*      */ 
/*      */       
/*  237 */       SVGOMAnimatedLengthList _y = (SVGOMAnimatedLengthList)te.getY();
/*  238 */       _y.check();
/*  239 */       SVGLengthList ys = _y.getAnimVal();
/*  240 */       float y = 0.0F;
/*  241 */       if (ys.getNumberOfItems() > 0) {
/*  242 */         y = ys.getItem(0).getValue();
/*      */       }
/*      */       
/*  245 */       return new Point2D.Float(x, y);
/*  246 */     } catch (LiveAttributeException ex) {
/*  247 */       throw new BridgeException(ctx, ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean isTextElement(Element e) {
/*  252 */     if (!"http://www.w3.org/2000/svg".equals(e.getNamespaceURI()))
/*  253 */       return false; 
/*  254 */     String nodeName = e.getLocalName();
/*  255 */     return (nodeName.equals("text") || nodeName.equals("tspan") || nodeName.equals("altGlyph") || nodeName.equals("a") || nodeName.equals("textPath") || nodeName.equals("tref"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isTextChild(Element e) {
/*  264 */     if (!"http://www.w3.org/2000/svg".equals(e.getNamespaceURI()))
/*  265 */       return false; 
/*  266 */     String nodeName = e.getLocalName();
/*  267 */     return (nodeName.equals("tspan") || nodeName.equals("altGlyph") || nodeName.equals("a") || nodeName.equals("textPath") || nodeName.equals("tref"));
/*      */   }
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
/*      */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/*  285 */     e.normalize();
/*  286 */     computeLaidoutText(ctx, e, node);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  292 */     node.setComposite(CSSUtilities.convertOpacity(e));
/*      */     
/*  294 */     node.setFilter(CSSUtilities.convertFilter(e, node, ctx));
/*      */     
/*  296 */     node.setMask(CSSUtilities.convertMask(e, node, ctx));
/*      */     
/*  298 */     node.setClip(CSSUtilities.convertClipPath(e, node, ctx));
/*      */     
/*  300 */     node.setPointerEventType(CSSUtilities.convertPointerEvents(e));
/*      */     
/*  302 */     initializeDynamicSupport(ctx, e, node);
/*  303 */     if (!ctx.isDynamic()) {
/*  304 */       this.elemTPI.clear();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isComposite() {
/*  312 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node getFirstChild(Node n) {
/*  322 */     return n.getFirstChild();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node getNextSibling(Node n) {
/*  330 */     return n.getNextSibling();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node getParentNode(Node n) {
/*  338 */     return n.getParentNode();
/*      */   }
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
/*      */   protected class DOMChildNodeRemovedEventListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/*  357 */       SVGTextElementBridge.this.handleDOMChildNodeRemovedEvent((MutationEvent)evt);
/*      */     }
/*      */   }
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
/*      */   protected class DOMSubtreeModifiedEventListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/*  375 */       SVGTextElementBridge.this.handleDOMSubtreeModifiedEvent((MutationEvent)evt);
/*      */     }
/*      */   }
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
/*      */   protected void initializeDynamicSupport(BridgeContext ctx, Element e, GraphicsNode node) {
/*  389 */     super.initializeDynamicSupport(ctx, e, node);
/*      */     
/*  391 */     if (ctx.isDynamic())
/*      */     {
/*  393 */       addTextEventListeners(ctx, (NodeEventTarget)e);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addTextEventListeners(BridgeContext ctx, NodeEventTarget e) {
/*  401 */     if (this.childNodeRemovedEventListener == null) {
/*  402 */       this.childNodeRemovedEventListener = new DOMChildNodeRemovedEventListener();
/*      */     }
/*      */     
/*  405 */     if (this.subtreeModifiedEventListener == null) {
/*  406 */       this.subtreeModifiedEventListener = new DOMSubtreeModifiedEventListener();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  412 */     e.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.childNodeRemovedEventListener, true, null);
/*      */ 
/*      */     
/*  415 */     ctx.storeEventListenerNS((EventTarget)e, "http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.childNodeRemovedEventListener, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  421 */     e.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.subtreeModifiedEventListener, false, null);
/*      */ 
/*      */     
/*  424 */     ctx.storeEventListenerNS((EventTarget)e, "http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.subtreeModifiedEventListener, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeTextEventListeners(BridgeContext ctx, NodeEventTarget e) {
/*  434 */     e.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.childNodeRemovedEventListener, true);
/*      */ 
/*      */     
/*  437 */     e.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.subtreeModifiedEventListener, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispose() {
/*  447 */     removeTextEventListeners(this.ctx, (NodeEventTarget)this.e);
/*  448 */     super.dispose();
/*      */   }
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
/*      */   protected void addContextToChild(BridgeContext ctx, Element e) {
/*  464 */     if ("http://www.w3.org/2000/svg".equals(e.getNamespaceURI())) {
/*  465 */       if (e.getLocalName().equals("tspan")) {
/*  466 */         ((SVGOMElement)e).setSVGContext((SVGContext)new TspanBridge(ctx, this, e));
/*      */       }
/*  468 */       else if (e.getLocalName().equals("textPath")) {
/*  469 */         ((SVGOMElement)e).setSVGContext((SVGContext)new TextPathBridge(ctx, this, e));
/*      */       }
/*  471 */       else if (e.getLocalName().equals("tref")) {
/*  472 */         ((SVGOMElement)e).setSVGContext((SVGContext)new TRefBridge(ctx, this, e));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  477 */     Node child = getFirstChild(e);
/*  478 */     while (child != null) {
/*  479 */       if (child.getNodeType() == 1) {
/*  480 */         addContextToChild(ctx, (Element)child);
/*      */       }
/*  482 */       child = getNextSibling(child);
/*      */     } 
/*      */   }
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
/*      */   protected void removeContextFromChild(BridgeContext ctx, Element e) {
/*  497 */     if ("http://www.w3.org/2000/svg".equals(e.getNamespaceURI())) {
/*  498 */       if (e.getLocalName().equals("tspan")) {
/*  499 */         ((AbstractTextChildBridgeUpdateHandler)((SVGOMElement)e).getSVGContext()).dispose();
/*      */       }
/*  501 */       else if (e.getLocalName().equals("textPath")) {
/*  502 */         ((AbstractTextChildBridgeUpdateHandler)((SVGOMElement)e).getSVGContext()).dispose();
/*      */       }
/*  504 */       else if (e.getLocalName().equals("tref")) {
/*  505 */         ((AbstractTextChildBridgeUpdateHandler)((SVGOMElement)e).getSVGContext()).dispose();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  510 */     Node child = getFirstChild(e);
/*  511 */     while (child != null) {
/*  512 */       if (child.getNodeType() == 1) {
/*  513 */         removeContextFromChild(ctx, (Element)child);
/*      */       }
/*  515 */       child = getNextSibling(child);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {
/*      */     Element childElement;
/*  523 */     Node childNode = (Node)evt.getTarget();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  528 */     switch (childNode.getNodeType()) {
/*      */       case 3:
/*      */       case 4:
/*  531 */         this.laidoutText = null;
/*      */         break;
/*      */       case 1:
/*  534 */         childElement = (Element)childNode;
/*  535 */         if (isTextChild(childElement)) {
/*  536 */           addContextToChild(this.ctx, childElement);
/*  537 */           this.laidoutText = null;
/*      */         } 
/*      */         break;
/*      */     } 
/*      */     
/*  542 */     if (this.laidoutText == null) {
/*  543 */       computeLaidoutText(this.ctx, this.e, (GraphicsNode)getTextNode());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleDOMChildNodeRemovedEvent(MutationEvent evt) {
/*      */     Element childElt;
/*  551 */     Node childNode = (Node)evt.getTarget();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  556 */     switch (childNode.getNodeType()) {
/*      */       
/*      */       case 3:
/*      */       case 4:
/*  560 */         if (isParentDisplayed(childNode)) {
/*  561 */           this.laidoutText = null;
/*      */         }
/*      */         break;
/*      */       case 1:
/*  565 */         childElt = (Element)childNode;
/*  566 */         if (isTextChild(childElt)) {
/*  567 */           this.laidoutText = null;
/*  568 */           removeContextFromChild(this.ctx, childElt);
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
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
/*      */   public void handleDOMSubtreeModifiedEvent(MutationEvent evt) {
/*  584 */     if (this.laidoutText == null) {
/*  585 */       computeLaidoutText(this.ctx, this.e, (GraphicsNode)getTextNode());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleDOMCharacterDataModified(MutationEvent evt) {
/*  594 */     Node childNode = (Node)evt.getTarget();
/*      */     
/*  596 */     if (isParentDisplayed(childNode)) {
/*  597 */       this.laidoutText = null;
/*      */     }
/*      */   }
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
/*      */   protected boolean isParentDisplayed(Node childNode) {
/*  612 */     Node parentNode = getParentNode(childNode);
/*  613 */     return isTextElement((Element)parentNode);
/*      */   }
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
/*      */   protected void computeLaidoutText(BridgeContext ctx, Element e, GraphicsNode node) {
/*  627 */     TextNode tn = (TextNode)node;
/*  628 */     this.elemTPI.clear();
/*      */     
/*  630 */     AttributedString as = buildAttributedString(ctx, e);
/*  631 */     if (as == null) {
/*  632 */       tn.setAttributedCharacterIterator((AttributedCharacterIterator)null);
/*      */       
/*      */       return;
/*      */     } 
/*  636 */     addGlyphPositionAttributes(as, e, ctx);
/*  637 */     if (ctx.isDynamic()) {
/*  638 */       this.laidoutText = new AttributedString(as.getIterator());
/*      */     }
/*      */ 
/*      */     
/*  642 */     tn.setAttributedCharacterIterator(as.getIterator());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  647 */     TextPaintInfo pi = new TextPaintInfo();
/*  648 */     setBaseTextPaintInfo(pi, e, node, ctx);
/*      */     
/*  650 */     setDecorationTextPaintInfo(pi, e);
/*      */     
/*  652 */     addPaintAttributes(as, e, tn, pi, ctx);
/*      */     
/*  654 */     if (this.usingComplexSVGFont)
/*      */     {
/*  656 */       tn.setAttributedCharacterIterator(as.getIterator());
/*      */     }
/*      */     
/*  659 */     if (ctx.isDynamic()) {
/*  660 */       checkBBoxChange();
/*      */     }
/*      */   }
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
/*      */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {
/*  681 */     if (alav.getNamespaceURI() == null) {
/*  682 */       String ln = alav.getLocalName();
/*  683 */       if (ln.equals("x") || ln.equals("y") || ln.equals("dx") || ln.equals("dy") || ln.equals("rotate") || ln.equals("textLength") || ln.equals("lengthAdjust")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  690 */         char c = ln.charAt(0);
/*  691 */         if (c == 'x' || c == 'y') {
/*  692 */           getTextNode().setLocation(getLocation(this.ctx, this.e));
/*      */         }
/*  694 */         computeLaidoutText(this.ctx, this.e, (GraphicsNode)getTextNode());
/*      */         return;
/*      */       } 
/*      */     } 
/*  698 */     super.handleAnimatedAttributeChanged(alav);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleCSSEngineEvent(CSSEngineEvent evt) {
/*  707 */     this.hasNewACI = false;
/*  708 */     int[] properties = evt.getProperties();
/*      */     
/*  710 */     for (int property : properties) {
/*  711 */       switch (property) {
/*      */         case 1:
/*      */         case 11:
/*      */         case 12:
/*      */         case 21:
/*      */         case 22:
/*      */         case 24:
/*      */         case 25:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 31:
/*      */         case 32:
/*      */         case 53:
/*      */         case 56:
/*      */         case 58:
/*      */         case 59:
/*  728 */           if (!this.hasNewACI) {
/*  729 */             this.hasNewACI = true;
/*  730 */             computeLaidoutText(this.ctx, this.e, (GraphicsNode)getTextNode());
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  739 */     this.cssProceedElement = evt.getElement();
/*      */     
/*  741 */     super.handleCSSEngineEvent(evt);
/*  742 */     this.cssProceedElement = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleCSSPropertyChanged(int property) {
/*      */     RenderingHints hints;
/*  749 */     switch (property) {
/*      */       case 15:
/*      */       case 16:
/*      */       case 45:
/*      */       case 46:
/*      */       case 47:
/*      */       case 48:
/*      */       case 49:
/*      */       case 50:
/*      */       case 51:
/*      */       case 52:
/*      */       case 54:
/*  761 */         rebuildACI();
/*      */         return;
/*      */       
/*      */       case 57:
/*  765 */         rebuildACI();
/*  766 */         super.handleCSSPropertyChanged(property);
/*      */         return;
/*      */       case 55:
/*  769 */         hints = this.node.getRenderingHints();
/*  770 */         hints = CSSUtilities.convertTextRendering(this.e, hints);
/*  771 */         if (hints != null) {
/*  772 */           this.node.setRenderingHints(hints);
/*      */         }
/*      */         return;
/*      */       
/*      */       case 9:
/*  777 */         hints = this.node.getRenderingHints();
/*  778 */         hints = CSSUtilities.convertColorRendering(this.e, hints);
/*  779 */         if (hints != null) {
/*  780 */           this.node.setRenderingHints(hints);
/*      */         }
/*      */         return;
/*      */     } 
/*      */     
/*  785 */     super.handleCSSPropertyChanged(property);
/*      */   }
/*      */   
/*      */   protected void rebuildACI() {
/*      */     TextPaintInfo pi, oldPI;
/*  790 */     if (this.hasNewACI) {
/*      */       return;
/*      */     }
/*  793 */     TextNode textNode = getTextNode();
/*  794 */     if (textNode.getAttributedCharacterIterator() == null) {
/*      */       return;
/*      */     }
/*      */     
/*  798 */     if (this.cssProceedElement == this.e) {
/*  799 */       pi = new TextPaintInfo();
/*  800 */       setBaseTextPaintInfo(pi, this.e, this.node, this.ctx);
/*  801 */       setDecorationTextPaintInfo(pi, this.e);
/*  802 */       oldPI = (TextPaintInfo)this.elemTPI.get(this.e);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  809 */       TextPaintInfo parentPI = getParentTextPaintInfo(this.cssProceedElement);
/*  810 */       pi = getTextPaintInfo(this.cssProceedElement, (GraphicsNode)textNode, parentPI, this.ctx);
/*  811 */       oldPI = (TextPaintInfo)this.elemTPI.get(this.cssProceedElement);
/*      */     } 
/*  813 */     if (oldPI == null)
/*      */       return; 
/*  815 */     textNode.swapTextPaintInfo(pi, oldPI);
/*  816 */     if (this.usingComplexSVGFont)
/*      */     {
/*  818 */       textNode.setAttributedCharacterIterator(textNode.getAttributedCharacterIterator());
/*      */     }
/*      */   }
/*      */   
/*      */   int getElementStartIndex(Element element) {
/*  823 */     TextPaintInfo tpi = (TextPaintInfo)this.elemTPI.get(element);
/*  824 */     if (tpi == null) return -1; 
/*  825 */     return tpi.startChar;
/*      */   }
/*      */   
/*      */   int getElementEndIndex(Element element) {
/*  829 */     TextPaintInfo tpi = (TextPaintInfo)this.elemTPI.get(element);
/*  830 */     if (tpi == null) return -1; 
/*  831 */     return tpi.endChar;
/*      */   }
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
/*      */   protected AttributedString buildAttributedString(BridgeContext ctx, Element element) {
/*  849 */     AttributedStringBuffer asb = new AttributedStringBuffer();
/*  850 */     fillAttributedStringBuffer(ctx, element, true, (TextPath)null, (Integer)null, (Map)null, asb);
/*  851 */     return asb.toAttributedString();
/*      */   }
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
/*      */   protected void fillAttributedStringBuffer(BridgeContext ctx, Element element, boolean top, TextPath textPath, Integer bidiLevel, Map<?, ?> initialAttributes, AttributedStringBuffer asb) {
/*  875 */     if (!SVGUtilities.matchUserAgent(element, ctx.getUserAgent()) || !CSSUtilities.convertDisplay(element)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  880 */     String s = XMLSupport.getXMLSpace(element);
/*  881 */     boolean preserve = s.equals("preserve");
/*      */     
/*  883 */     Element nodeElement = element;
/*  884 */     int elementStartChar = asb.length();
/*      */     
/*  886 */     if (top) {
/*  887 */       this.endLimit = 0;
/*      */     }
/*  889 */     if (preserve) {
/*  890 */       this.endLimit = asb.length();
/*      */     }
/*      */     
/*  893 */     Map map = (initialAttributes == null) ? new HashMap<Object, Object>() : new HashMap<Object, Object>(initialAttributes);
/*      */ 
/*      */     
/*  896 */     initialAttributes = getAttributeMap(ctx, element, textPath, bidiLevel, map);
/*      */     
/*  898 */     Object o = map.get(TextAttribute.BIDI_EMBEDDING);
/*  899 */     Integer subBidiLevel = bidiLevel;
/*  900 */     if (o != null) {
/*  901 */       subBidiLevel = (Integer)o;
/*      */     }
/*      */     
/*  904 */     Node n = getFirstChild(element);
/*  905 */     for (; n != null; 
/*  906 */       n = getNextSibling(n)) {
/*      */       boolean prevEndsWithSpace; String ln;
/*  908 */       if (preserve) {
/*  909 */         prevEndsWithSpace = false;
/*      */       }
/*  911 */       else if (asb.length() == 0) {
/*  912 */         prevEndsWithSpace = true;
/*      */       } else {
/*  914 */         prevEndsWithSpace = (asb.getLastChar() == 32);
/*      */       } 
/*      */ 
/*      */       
/*  918 */       switch (n.getNodeType()) {
/*      */         case 1:
/*  920 */           if (!"http://www.w3.org/2000/svg".equals(n.getNamespaceURI())) {
/*      */             break;
/*      */           }
/*  923 */           nodeElement = (Element)n;
/*      */           
/*  925 */           ln = n.getLocalName();
/*      */           
/*  927 */           if (ln.equals("tspan") || ln.equals("altGlyph")) {
/*      */             
/*  929 */             int before = asb.count;
/*  930 */             fillAttributedStringBuffer(ctx, nodeElement, false, textPath, subBidiLevel, initialAttributes, asb);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  937 */             if (asb.count != before)
/*  938 */               initialAttributes = null;  break;
/*      */           } 
/*  940 */           if (ln.equals("textPath")) {
/*  941 */             SVGTextPathElementBridge textPathBridge = (SVGTextPathElementBridge)ctx.getBridge(nodeElement);
/*      */             
/*  943 */             TextPath newTextPath = textPathBridge.createTextPath(ctx, nodeElement);
/*      */             
/*  945 */             if (newTextPath != null) {
/*  946 */               int before = asb.count;
/*  947 */               fillAttributedStringBuffer(ctx, nodeElement, false, newTextPath, subBidiLevel, initialAttributes, asb);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  954 */               if (asb.count != before)
/*  955 */                 initialAttributes = null; 
/*      */             }  break;
/*      */           } 
/*  958 */           if (ln.equals("tref")) {
/*  959 */             String uriStr = XLinkSupport.getXLinkHref((Element)n);
/*  960 */             Element ref = ctx.getReferencedElement((Element)n, uriStr);
/*  961 */             s = TextUtilities.getElementContent(ref);
/*  962 */             s = normalizeString(s, preserve, prevEndsWithSpace);
/*  963 */             if (s.length() != 0) {
/*  964 */               int trefStart = asb.length();
/*  965 */               Map m = (initialAttributes == null) ? new HashMap<Object, Object>() : new HashMap<Object, Object>(initialAttributes);
/*      */ 
/*      */               
/*  968 */               getAttributeMap(ctx, nodeElement, textPath, bidiLevel, m);
/*      */               
/*  970 */               asb.append(s, m);
/*  971 */               int trefEnd = asb.length() - 1;
/*      */               
/*  973 */               TextPaintInfo textPaintInfo = (TextPaintInfo)this.elemTPI.get(nodeElement);
/*  974 */               textPaintInfo.startChar = trefStart;
/*  975 */               textPaintInfo.endChar = trefEnd;
/*  976 */               initialAttributes = null;
/*      */             }  break;
/*  978 */           }  if (ln.equals("a")) {
/*  979 */             NodeEventTarget target = (NodeEventTarget)nodeElement;
/*  980 */             UserAgent ua = ctx.getUserAgent();
/*      */             
/*  982 */             SVGAElementBridge.CursorHolder ch = new SVGAElementBridge.CursorHolder(CursorManager.DEFAULT_CURSOR);
/*      */ 
/*      */             
/*  985 */             EventListener l = new SVGAElementBridge.AnchorListener(ua, ch);
/*  986 */             target.addEventListenerNS("http://www.w3.org/2001/xml-events", "click", l, false, null);
/*      */ 
/*      */             
/*  989 */             ctx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "click", l, false);
/*      */ 
/*      */ 
/*      */             
/*  993 */             int before = asb.count;
/*  994 */             fillAttributedStringBuffer(ctx, nodeElement, false, textPath, subBidiLevel, initialAttributes, asb);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1001 */             if (asb.count != before) {
/* 1002 */               initialAttributes = null;
/*      */             }
/*      */           } 
/*      */           break;
/*      */         case 3:
/*      */         case 4:
/* 1008 */           s = n.getNodeValue();
/* 1009 */           s = normalizeString(s, preserve, prevEndsWithSpace);
/* 1010 */           if (s.length() != 0) {
/* 1011 */             asb.append(s, map);
/* 1012 */             if (preserve) {
/* 1013 */               this.endLimit = asb.length();
/*      */             }
/* 1015 */             initialAttributes = null;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/* 1020 */     if (top) {
/* 1021 */       boolean strippedSome = false;
/* 1022 */       while (this.endLimit < asb.length() && asb.getLastChar() == 32) {
/* 1023 */         asb.stripLast();
/* 1024 */         strippedSome = true;
/*      */       } 
/* 1026 */       if (strippedSome)
/* 1027 */         for (Object o1 : this.elemTPI.values()) {
/* 1028 */           TextPaintInfo textPaintInfo = (TextPaintInfo)o1;
/* 1029 */           if (textPaintInfo.endChar >= asb.length()) {
/* 1030 */             textPaintInfo.endChar = asb.length() - 1;
/* 1031 */             if (textPaintInfo.startChar > textPaintInfo.endChar) {
/* 1032 */               textPaintInfo.startChar = textPaintInfo.endChar;
/*      */             }
/*      */           } 
/*      */         }  
/*      */     } 
/* 1037 */     int elementEndChar = asb.length() - 1;
/* 1038 */     TextPaintInfo tpi = (TextPaintInfo)this.elemTPI.get(element);
/* 1039 */     tpi.startChar = elementStartChar;
/* 1040 */     tpi.endChar = elementEndChar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String normalizeString(String s, boolean preserve, boolean stripfirst) {
/* 1049 */     StringBuffer sb = new StringBuffer(s.length());
/* 1050 */     if (preserve) {
/* 1051 */       for (int j = 0; j < s.length(); j++) {
/* 1052 */         char c = s.charAt(j);
/* 1053 */         switch (c) {
/*      */           case '\t':
/*      */           case '\n':
/*      */           case '\r':
/* 1057 */             sb.append(' ');
/*      */             break;
/*      */           default:
/* 1060 */             sb.append(c); break;
/*      */         } 
/*      */       } 
/* 1063 */       return sb.toString();
/*      */     } 
/*      */     
/* 1066 */     int idx = 0;
/* 1067 */     if (stripfirst) {
/* 1068 */       while (idx < s.length()) {
/* 1069 */         switch (s.charAt(idx)) { default:
/*      */             break;
/*      */           case '\t':
/*      */           case '\n':
/*      */           case '\r':
/*      */           case ' ':
/*      */             break; }
/* 1076 */          idx++;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1081 */     boolean space = false;
/* 1082 */     for (int i = idx; i < s.length(); i++) {
/* 1083 */       char c = s.charAt(i);
/* 1084 */       switch (c) {
/*      */         case '\n':
/*      */         case '\r':
/*      */           break;
/*      */         case '\t':
/*      */         case ' ':
/* 1090 */           if (!space) {
/* 1091 */             sb.append(' ');
/* 1092 */             space = true;
/*      */           } 
/*      */           break;
/*      */         default:
/* 1096 */           sb.append(c);
/* 1097 */           space = false;
/*      */           break;
/*      */       } 
/*      */     } 
/* 1101 */     return sb.toString();
/*      */   }
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
/*      */   protected static class AttributedStringBuffer
/*      */   {
/* 1133 */     protected List strings = new ArrayList();
/* 1134 */     protected List attributes = new ArrayList();
/* 1135 */     protected int count = 0;
/* 1136 */     protected int length = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isEmpty() {
/* 1143 */       return (this.count == 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int length() {
/* 1150 */       return this.length;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void append(String s, Map m) {
/* 1157 */       if (s.length() == 0)
/* 1158 */         return;  this.strings.add(s);
/* 1159 */       this.attributes.add(m);
/* 1160 */       this.count++;
/* 1161 */       this.length += s.length();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLastChar() {
/* 1168 */       if (this.count == 0) {
/* 1169 */         return -1;
/*      */       }
/* 1171 */       String s = this.strings.get(this.count - 1);
/* 1172 */       return s.charAt(s.length() - 1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void stripFirst() {
/* 1179 */       String s = this.strings.get(0);
/* 1180 */       if (s.charAt(s.length() - 1) != ' ') {
/*      */         return;
/*      */       }
/* 1183 */       this.length--;
/*      */       
/* 1185 */       if (s.length() == 1) {
/* 1186 */         this.attributes.remove(0);
/* 1187 */         this.strings.remove(0);
/* 1188 */         this.count--;
/*      */         
/*      */         return;
/*      */       } 
/* 1192 */       this.strings.set(0, s.substring(1));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void stripLast() {
/* 1199 */       String s = this.strings.get(this.count - 1);
/* 1200 */       if (s.charAt(s.length() - 1) != ' ') {
/*      */         return;
/*      */       }
/* 1203 */       this.length--;
/*      */       
/* 1205 */       if (s.length() == 1) {
/* 1206 */         this.attributes.remove(--this.count);
/* 1207 */         this.strings.remove(this.count);
/*      */         
/*      */         return;
/*      */       } 
/* 1211 */       this.strings.set(this.count - 1, s.substring(0, s.length() - 1));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AttributedString toAttributedString() {
/* 1219 */       switch (this.count) {
/*      */         case 0:
/* 1221 */           return null;
/*      */         case 1:
/* 1223 */           return new AttributedString(this.strings.get(0), this.attributes.get(0));
/*      */       } 
/*      */ 
/*      */       
/* 1227 */       StringBuffer sb = new StringBuffer(this.strings.size() * 5);
/* 1228 */       for (Object string : this.strings) {
/* 1229 */         sb.append((String)string);
/*      */       }
/*      */       
/* 1232 */       AttributedString result = new AttributedString(sb.toString());
/*      */ 
/*      */ 
/*      */       
/* 1236 */       Iterator<String> sit = this.strings.iterator();
/* 1237 */       Iterator<Map> ait = this.attributes.iterator();
/* 1238 */       int idx = 0;
/* 1239 */       while (sit.hasNext()) {
/* 1240 */         String s = sit.next();
/* 1241 */         int nidx = idx + s.length();
/* 1242 */         Map m = ait.next();
/* 1243 */         Iterator<AttributedCharacterIterator.Attribute> kit = m.keySet().iterator();
/* 1244 */         Iterator vit = m.values().iterator();
/* 1245 */         while (kit.hasNext()) {
/* 1246 */           AttributedCharacterIterator.Attribute attr = kit.next();
/* 1247 */           Object val = vit.next();
/* 1248 */           result.addAttribute(attr, val, idx, nidx);
/*      */         } 
/* 1250 */         idx = nidx;
/*      */       } 
/*      */       
/* 1253 */       return result;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1257 */       switch (this.count) {
/*      */         case 0:
/* 1259 */           return "";
/*      */         case 1:
/* 1261 */           return this.strings.get(0);
/*      */       } 
/*      */       
/* 1264 */       StringBuffer sb = new StringBuffer(this.strings.size() * 5);
/* 1265 */       for (Object string : this.strings) {
/* 1266 */         sb.append((String)string);
/*      */       }
/* 1268 */       return sb.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean nodeAncestorOf(Node node1, Node node2) {
/* 1276 */     if (node2 == null || node1 == null) {
/* 1277 */       return false;
/*      */     }
/* 1279 */     Node parent = getParentNode(node2);
/* 1280 */     while (parent != null && parent != node1) {
/* 1281 */       parent = getParentNode(parent);
/*      */     }
/* 1283 */     return (parent == node1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addGlyphPositionAttributes(AttributedString as, Element element, BridgeContext ctx) {
/* 1295 */     if (!SVGUtilities.matchUserAgent(element, ctx.getUserAgent()) || !CSSUtilities.convertDisplay(element)) {
/*      */       return;
/*      */     }
/*      */     
/* 1299 */     if (element.getLocalName().equals("textPath")) {
/*      */       
/* 1301 */       addChildGlyphPositionAttributes(as, element, ctx);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1306 */     int firstChar = getElementStartIndex(element);
/*      */     
/* 1308 */     if (firstChar == -1)
/*      */       return; 
/* 1310 */     int lastChar = getElementEndIndex(element);
/*      */ 
/*      */ 
/*      */     
/* 1314 */     if (!(element instanceof SVGTextPositioningElement)) {
/* 1315 */       addChildGlyphPositionAttributes(as, element, ctx);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1320 */     SVGTextPositioningElement te = (SVGTextPositioningElement)element;
/*      */     
/*      */     try {
/* 1323 */       SVGOMAnimatedLengthList _x = (SVGOMAnimatedLengthList)te.getX();
/*      */       
/* 1325 */       _x.check();
/* 1326 */       SVGOMAnimatedLengthList _y = (SVGOMAnimatedLengthList)te.getY();
/*      */       
/* 1328 */       _y.check();
/* 1329 */       SVGOMAnimatedLengthList _dx = (SVGOMAnimatedLengthList)te.getDx();
/*      */       
/* 1331 */       _dx.check();
/* 1332 */       SVGOMAnimatedLengthList _dy = (SVGOMAnimatedLengthList)te.getDy();
/*      */       
/* 1334 */       _dy.check();
/* 1335 */       SVGOMAnimatedNumberList _rotate = (SVGOMAnimatedNumberList)te.getRotate();
/*      */       
/* 1337 */       _rotate.check();
/*      */       
/* 1339 */       SVGLengthList xs = _x.getAnimVal();
/* 1340 */       SVGLengthList ys = _y.getAnimVal();
/* 1341 */       SVGLengthList dxs = _dx.getAnimVal();
/* 1342 */       SVGLengthList dys = _dy.getAnimVal();
/* 1343 */       SVGNumberList rs = _rotate.getAnimVal();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1348 */       int len = xs.getNumberOfItems(); int i;
/* 1349 */       for (i = 0; i < len && firstChar + i <= lastChar; i++) {
/* 1350 */         as.addAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.X, Float.valueOf(xs.getItem(i).getValue()), firstChar + i, firstChar + i + 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1357 */       len = ys.getNumberOfItems();
/* 1358 */       for (i = 0; i < len && firstChar + i <= lastChar; i++) {
/* 1359 */         as.addAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.Y, Float.valueOf(ys.getItem(i).getValue()), firstChar + i, firstChar + i + 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1366 */       len = dxs.getNumberOfItems();
/* 1367 */       for (i = 0; i < len && firstChar + i <= lastChar; i++) {
/* 1368 */         as.addAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.DX, Float.valueOf(dxs.getItem(i).getValue()), firstChar + i, firstChar + i + 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1375 */       len = dys.getNumberOfItems();
/* 1376 */       for (i = 0; i < len && firstChar + i <= lastChar; i++) {
/* 1377 */         as.addAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.DY, Float.valueOf(dys.getItem(i).getValue()), firstChar + i, firstChar + i + 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1384 */       len = rs.getNumberOfItems();
/* 1385 */       if (len == 1) {
/*      */         
/* 1387 */         Float rad = Float.valueOf((float)Math.toRadians(rs.getItem(0).getValue()));
/* 1388 */         as.addAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ROTATION, rad, firstChar, lastChar + 1);
/*      */ 
/*      */       
/*      */       }
/* 1392 */       else if (len > 1) {
/*      */         
/* 1394 */         for (i = 0; i < len && firstChar + i <= lastChar; i++) {
/* 1395 */           Float rad = Float.valueOf((float)Math.toRadians(rs.getItem(i).getValue()));
/* 1396 */           as.addAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ROTATION, rad, firstChar + i, firstChar + i + 1);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1402 */       addChildGlyphPositionAttributes(as, element, ctx);
/* 1403 */     } catch (LiveAttributeException ex) {
/* 1404 */       throw new BridgeException(ctx, ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addChildGlyphPositionAttributes(AttributedString as, Element element, BridgeContext ctx) {
/* 1412 */     Node child = getFirstChild(element);
/* 1413 */     for (; child != null; 
/* 1414 */       child = getNextSibling(child)) {
/* 1415 */       if (child.getNodeType() == 1) {
/*      */         
/* 1417 */         Element childElement = (Element)child;
/* 1418 */         if (isTextChild(childElement)) {
/* 1419 */           addGlyphPositionAttributes(as, childElement, ctx);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addPaintAttributes(AttributedString as, Element element, TextNode node, TextPaintInfo pi, BridgeContext ctx) {
/* 1433 */     if (!SVGUtilities.matchUserAgent(element, ctx.getUserAgent()) || !CSSUtilities.convertDisplay(element)) {
/*      */       return;
/*      */     }
/*      */     
/* 1437 */     Object o = this.elemTPI.get(element);
/* 1438 */     if (o != null) {
/* 1439 */       node.swapTextPaintInfo(pi, (TextPaintInfo)o);
/*      */     }
/* 1441 */     addChildPaintAttributes(as, element, node, pi, ctx);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addChildPaintAttributes(AttributedString as, Element element, TextNode node, TextPaintInfo parentPI, BridgeContext ctx) {
/* 1450 */     Node child = getFirstChild(element);
/* 1451 */     for (; child != null; 
/* 1452 */       child = getNextSibling(child)) {
/* 1453 */       if (child.getNodeType() == 1) {
/*      */ 
/*      */         
/* 1456 */         Element childElement = (Element)child;
/* 1457 */         if (isTextChild(childElement)) {
/* 1458 */           TextPaintInfo pi = getTextPaintInfo(childElement, (GraphicsNode)node, parentPI, ctx);
/*      */           
/* 1460 */           addPaintAttributes(as, childElement, node, pi, ctx);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List getFontList(BridgeContext ctx, Element element, Map<TextAttribute, Float> result) {
/* 1474 */     result.put(TEXT_COMPOUND_ID, new SoftReference<Element>(element));
/*      */     
/* 1476 */     Float fsFloat = TextUtilities.convertFontSize(element);
/* 1477 */     float fontSize = fsFloat.floatValue();
/*      */     
/* 1479 */     result.put(TextAttribute.SIZE, fsFloat);
/*      */ 
/*      */     
/* 1482 */     result.put(TextAttribute.WIDTH, TextUtilities.convertFontStretch(element));
/*      */ 
/*      */ 
/*      */     
/* 1486 */     result.put(TextAttribute.POSTURE, TextUtilities.convertFontStyle(element));
/*      */ 
/*      */ 
/*      */     
/* 1490 */     result.put(TextAttribute.WEIGHT, TextUtilities.convertFontWeight(element));
/*      */ 
/*      */ 
/*      */     
/* 1494 */     Value v = CSSUtilities.getComputedStyle(element, 27);
/*      */     
/* 1496 */     String fontWeightString = v.getCssText();
/*      */ 
/*      */     
/* 1499 */     String fontStyleString = CSSUtilities.getComputedStyle(element, 25).getStringValue();
/*      */ 
/*      */ 
/*      */     
/* 1503 */     result.put(TEXT_COMPOUND_DELIMITER, element);
/*      */ 
/*      */     
/* 1506 */     Value val = CSSUtilities.getComputedStyle(element, 21);
/*      */     
/* 1508 */     List<GVTFontFamily> fontFamilyList = new ArrayList();
/* 1509 */     List<GVTFont> fontList = new ArrayList();
/* 1510 */     int len = val.getLength();
/* 1511 */     for (int i = 0; i < len; i++) {
/* 1512 */       Value it = val.item(i);
/* 1513 */       String fontFamilyName = it.getStringValue();
/*      */       
/* 1515 */       GVTFontFamily fontFamily = SVGFontUtilities.getFontFamily(element, ctx, fontFamilyName, fontWeightString, fontStyleString);
/*      */       
/* 1517 */       if (fontFamily != null && fontFamily instanceof org.apache.batik.gvt.font.UnresolvedFontFamily) {
/* 1518 */         fontFamily = ctx.getFontFamilyResolver().resolve(fontFamily.getFamilyName());
/*      */       }
/* 1520 */       if (fontFamily != null) {
/*      */ 
/*      */         
/* 1523 */         fontFamilyList.add(fontFamily);
/* 1524 */         if (fontFamily.isComplex()) {
/* 1525 */           this.usingComplexSVGFont = true;
/*      */         }
/* 1527 */         GVTFont ft = fontFamily.deriveFont(fontSize, result);
/* 1528 */         fontList.add(ft);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1533 */     result.put(GVT_FONT_FAMILIES, fontFamilyList);
/*      */     
/* 1535 */     if (!ctx.isDynamic())
/*      */     {
/*      */ 
/*      */       
/* 1539 */       result.remove(TEXT_COMPOUND_DELIMITER);
/*      */     }
/* 1541 */     return fontList;
/*      */   }
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
/*      */   protected Map getAttributeMap(BridgeContext ctx, Element element, TextPath textPath, Integer bidiLevel, Map<AttributedCharacterIterator.Attribute, SVGAltGlyphHandler> result) {
/* 1563 */     SVGTextContentElement tce = null;
/* 1564 */     if (element instanceof SVGTextContentElement)
/*      */     {
/*      */       
/* 1567 */       tce = (SVGTextContentElement)element;
/*      */     }
/*      */     
/* 1570 */     Map<Object, Object> inheritMap = null;
/*      */ 
/*      */     
/* 1573 */     if ("http://www.w3.org/2000/svg".equals(element.getNamespaceURI()) && element.getLocalName().equals("altGlyph"))
/*      */     {
/* 1575 */       result.put(ALT_GLYPH_HANDLER, new SVGAltGlyphHandler(ctx, element));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1581 */     TextPaintInfo pi = new TextPaintInfo();
/*      */     
/* 1583 */     pi.visible = true;
/* 1584 */     pi.fillPaint = Color.black;
/* 1585 */     result.put(PAINT_INFO, pi);
/* 1586 */     this.elemTPI.put(element, pi);
/*      */     
/* 1588 */     if (textPath != null) {
/* 1589 */       result.put(TEXTPATH, textPath);
/*      */     }
/*      */ 
/*      */     
/* 1593 */     TextNode.Anchor a = TextUtilities.convertTextAnchor(element);
/* 1594 */     result.put(ANCHOR_TYPE, a);
/*      */ 
/*      */     
/* 1597 */     List fontList = getFontList(ctx, element, result);
/* 1598 */     result.put(GVT_FONTS, fontList);
/*      */ 
/*      */ 
/*      */     
/* 1602 */     Object bs = TextUtilities.convertBaselineShift(element);
/* 1603 */     if (bs != null) {
/* 1604 */       result.put(BASELINE_SHIFT, bs);
/*      */     }
/*      */ 
/*      */     
/* 1608 */     Value val = CSSUtilities.getComputedStyle(element, 56);
/*      */     
/* 1610 */     String s = val.getStringValue();
/* 1611 */     if (s.charAt(0) == 'n') {
/* 1612 */       if (bidiLevel != null) {
/* 1613 */         result.put(TextAttribute.BIDI_EMBEDDING, bidiLevel);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 1626 */       val = CSSUtilities.getComputedStyle(element, 11);
/*      */       
/* 1628 */       String rs = val.getStringValue();
/* 1629 */       int cbidi = 0;
/* 1630 */       if (bidiLevel != null) cbidi = bidiLevel.intValue();
/*      */ 
/*      */ 
/*      */       
/* 1634 */       if (cbidi < 0) cbidi = -cbidi;
/*      */       
/* 1636 */       switch (rs.charAt(0)) {
/*      */         case 'l':
/* 1638 */           result.put(TextAttribute.RUN_DIRECTION, TextAttribute.RUN_DIRECTION_LTR);
/*      */           
/* 1640 */           if ((cbidi & 0x1) == 1) { cbidi++; break; }
/* 1641 */            cbidi += 2;
/*      */           break;
/*      */         case 'r':
/* 1644 */           result.put(TextAttribute.RUN_DIRECTION, TextAttribute.RUN_DIRECTION_RTL);
/*      */           
/* 1646 */           if ((cbidi & 0x1) == 1) { cbidi += 2; break; }
/* 1647 */            cbidi++;
/*      */           break;
/*      */       } 
/*      */       
/* 1651 */       switch (s.charAt(0)) {
/*      */         case 'b':
/* 1653 */           cbidi = -cbidi;
/*      */           break;
/*      */       } 
/*      */       
/* 1657 */       result.put(TextAttribute.BIDI_EMBEDDING, Integer.valueOf(cbidi));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1662 */     val = CSSUtilities.getComputedStyle(element, 59);
/*      */     
/* 1664 */     s = val.getStringValue();
/* 1665 */     switch (s.charAt(0)) {
/*      */       case 'l':
/* 1667 */         result.put(GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE, GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE_LTR);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 'r':
/* 1673 */         result.put(GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE, GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE_RTL);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 't':
/* 1679 */         result.put(GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE, GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE_TTB);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1688 */     val = CSSUtilities.getComputedStyle(element, 29);
/*      */     
/* 1690 */     int primitiveType = val.getPrimitiveType();
/* 1691 */     switch (primitiveType) {
/*      */       case 21:
/* 1693 */         result.put(GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION, GVTAttributedCharacterIterator.TextAttribute.ORIENTATION_AUTO);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/* 1699 */         result.put(GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION, GVTAttributedCharacterIterator.TextAttribute.ORIENTATION_ANGLE);
/*      */ 
/*      */ 
/*      */         
/* 1703 */         result.put(GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION_ANGLE, Float.valueOf(val.getFloatValue()));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 12:
/* 1708 */         result.put(GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION, GVTAttributedCharacterIterator.TextAttribute.ORIENTATION_ANGLE);
/*      */ 
/*      */ 
/*      */         
/* 1712 */         result.put(GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION_ANGLE, Float.valueOf((float)Math.toDegrees(val.getFloatValue())));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 13:
/* 1717 */         result.put(GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION, GVTAttributedCharacterIterator.TextAttribute.ORIENTATION_ANGLE);
/*      */ 
/*      */ 
/*      */         
/* 1721 */         result.put(GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION_ANGLE, Float.valueOf(val.getFloatValue() * 9.0F / 5.0F));
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/* 1727 */         throw new IllegalStateException("unexpected primitiveType (V):" + primitiveType);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1732 */     val = CSSUtilities.getComputedStyle(element, 28);
/*      */     
/* 1734 */     primitiveType = val.getPrimitiveType();
/* 1735 */     switch (primitiveType) {
/*      */       case 11:
/* 1737 */         result.put(GVTAttributedCharacterIterator.TextAttribute.HORIZONTAL_ORIENTATION_ANGLE, Float.valueOf(val.getFloatValue()));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 12:
/* 1742 */         result.put(GVTAttributedCharacterIterator.TextAttribute.HORIZONTAL_ORIENTATION_ANGLE, Float.valueOf((float)Math.toDegrees(val.getFloatValue())));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 13:
/* 1747 */         result.put(GVTAttributedCharacterIterator.TextAttribute.HORIZONTAL_ORIENTATION_ANGLE, Float.valueOf(val.getFloatValue() * 9.0F / 5.0F));
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/* 1753 */         throw new IllegalStateException("unexpected primitiveType (H):" + primitiveType);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1759 */     Float sp = TextUtilities.convertLetterSpacing(element);
/* 1760 */     if (sp != null) {
/* 1761 */       result.put(GVTAttributedCharacterIterator.TextAttribute.LETTER_SPACING, sp);
/*      */ 
/*      */       
/* 1764 */       result.put(GVTAttributedCharacterIterator.TextAttribute.CUSTOM_SPACING, Boolean.TRUE);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1770 */     sp = TextUtilities.convertWordSpacing(element);
/* 1771 */     if (sp != null) {
/* 1772 */       result.put(GVTAttributedCharacterIterator.TextAttribute.WORD_SPACING, sp);
/*      */ 
/*      */       
/* 1775 */       result.put(GVTAttributedCharacterIterator.TextAttribute.CUSTOM_SPACING, Boolean.TRUE);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1781 */     sp = TextUtilities.convertKerning(element);
/* 1782 */     if (sp != null) {
/* 1783 */       result.put(GVTAttributedCharacterIterator.TextAttribute.KERNING, sp);
/*      */       
/* 1785 */       result.put(GVTAttributedCharacterIterator.TextAttribute.CUSTOM_SPACING, Boolean.TRUE);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1790 */     if (tce == null) {
/* 1791 */       return inheritMap;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1796 */       AbstractSVGAnimatedLength textLength = (AbstractSVGAnimatedLength)tce.getTextLength();
/*      */       
/* 1798 */       if (textLength.isSpecified()) {
/* 1799 */         if (inheritMap == null) {
/* 1800 */           inheritMap = new HashMap<Object, Object>();
/*      */         }
/*      */         
/* 1803 */         Object value = Float.valueOf(textLength.getCheckedValue());
/* 1804 */         result.put(GVTAttributedCharacterIterator.TextAttribute.BBOX_WIDTH, value);
/*      */ 
/*      */         
/* 1807 */         inheritMap.put(GVTAttributedCharacterIterator.TextAttribute.BBOX_WIDTH, value);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1812 */         SVGOMAnimatedEnumeration _lengthAdjust = (SVGOMAnimatedEnumeration)tce.getLengthAdjust();
/*      */         
/* 1814 */         if (_lengthAdjust.getCheckedVal() == 2)
/*      */         {
/* 1816 */           result.put(GVTAttributedCharacterIterator.TextAttribute.LENGTH_ADJUST, GVTAttributedCharacterIterator.TextAttribute.ADJUST_ALL);
/*      */ 
/*      */ 
/*      */           
/* 1820 */           inheritMap.put(GVTAttributedCharacterIterator.TextAttribute.LENGTH_ADJUST, GVTAttributedCharacterIterator.TextAttribute.ADJUST_ALL);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/* 1825 */           result.put(GVTAttributedCharacterIterator.TextAttribute.LENGTH_ADJUST, GVTAttributedCharacterIterator.TextAttribute.ADJUST_SPACING);
/*      */ 
/*      */ 
/*      */           
/* 1829 */           inheritMap.put(GVTAttributedCharacterIterator.TextAttribute.LENGTH_ADJUST, GVTAttributedCharacterIterator.TextAttribute.ADJUST_SPACING);
/*      */ 
/*      */ 
/*      */           
/* 1833 */           result.put(GVTAttributedCharacterIterator.TextAttribute.CUSTOM_SPACING, Boolean.TRUE);
/*      */ 
/*      */           
/* 1836 */           inheritMap.put(GVTAttributedCharacterIterator.TextAttribute.CUSTOM_SPACING, Boolean.TRUE);
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1841 */     } catch (LiveAttributeException ex) {
/* 1842 */       throw new BridgeException(ctx, ex);
/*      */     } 
/*      */     
/* 1845 */     return inheritMap;
/*      */   }
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
/*      */   protected TextPaintInfo getParentTextPaintInfo(Element child) {
/* 1858 */     Node parent = getParentNode(child);
/* 1859 */     while (parent != null) {
/* 1860 */       TextPaintInfo tpi = (TextPaintInfo)this.elemTPI.get(parent);
/* 1861 */       if (tpi != null) return tpi; 
/* 1862 */       parent = getParentNode(parent);
/*      */     } 
/* 1864 */     return null;
/*      */   }
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
/*      */   protected TextPaintInfo getTextPaintInfo(Element element, GraphicsNode node, TextPaintInfo parentTPI, BridgeContext ctx) {
/* 1877 */     CSSUtilities.getComputedStyle(element, 54);
/*      */ 
/*      */     
/* 1880 */     TextPaintInfo pi = new TextPaintInfo(parentTPI);
/*      */ 
/*      */     
/* 1883 */     StyleMap sm = ((CSSStylableElement)element).getComputedStyleMap(null);
/* 1884 */     if (sm.isNullCascaded(54) && sm.isNullCascaded(15) && sm.isNullCascaded(45) && sm.isNullCascaded(52) && sm.isNullCascaded(38))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1890 */       return pi;
/*      */     }
/*      */     
/* 1893 */     setBaseTextPaintInfo(pi, element, node, ctx);
/*      */     
/* 1895 */     if (!sm.isNullCascaded(54)) {
/* 1896 */       setDecorationTextPaintInfo(pi, element);
/*      */     }
/* 1898 */     return pi;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBaseTextPaintInfo(TextPaintInfo pi, Element element, GraphicsNode node, BridgeContext ctx) {
/* 1903 */     if (!element.getLocalName().equals("text")) {
/* 1904 */       pi.composite = CSSUtilities.convertOpacity(element);
/*      */     } else {
/* 1906 */       pi.composite = AlphaComposite.SrcOver;
/*      */     } 
/* 1908 */     pi.visible = CSSUtilities.convertVisibility(element);
/* 1909 */     pi.fillPaint = PaintServer.convertFillPaint(element, node, ctx);
/* 1910 */     pi.strokePaint = PaintServer.convertStrokePaint(element, node, ctx);
/* 1911 */     pi.strokeStroke = PaintServer.convertStroke(element);
/*      */   } public void setDecorationTextPaintInfo(TextPaintInfo pi, Element element) {
/*      */     ListValue lst;
/*      */     int len, i;
/* 1915 */     Value val = CSSUtilities.getComputedStyle(element, 54);
/*      */ 
/*      */     
/* 1918 */     switch (val.getCssValueType()) {
/*      */       case 2:
/* 1920 */         lst = (ListValue)val;
/*      */         
/* 1922 */         len = lst.getLength();
/* 1923 */         for (i = 0; i < len; i++) {
/* 1924 */           Value v = lst.item(i);
/* 1925 */           String s = v.getStringValue();
/* 1926 */           switch (s.charAt(0)) {
/*      */             case 'u':
/* 1928 */               if (pi.fillPaint != null) {
/* 1929 */                 pi.underlinePaint = pi.fillPaint;
/*      */               }
/* 1931 */               if (pi.strokePaint != null) {
/* 1932 */                 pi.underlineStrokePaint = pi.strokePaint;
/*      */               }
/* 1934 */               if (pi.strokeStroke != null) {
/* 1935 */                 pi.underlineStroke = pi.strokeStroke;
/*      */               }
/*      */               break;
/*      */             case 'o':
/* 1939 */               if (pi.fillPaint != null) {
/* 1940 */                 pi.overlinePaint = pi.fillPaint;
/*      */               }
/* 1942 */               if (pi.strokePaint != null) {
/* 1943 */                 pi.overlineStrokePaint = pi.strokePaint;
/*      */               }
/* 1945 */               if (pi.strokeStroke != null) {
/* 1946 */                 pi.overlineStroke = pi.strokeStroke;
/*      */               }
/*      */               break;
/*      */             case 'l':
/* 1950 */               if (pi.fillPaint != null) {
/* 1951 */                 pi.strikethroughPaint = pi.fillPaint;
/*      */               }
/* 1953 */               if (pi.strokePaint != null) {
/* 1954 */                 pi.strikethroughStrokePaint = pi.strokePaint;
/*      */               }
/* 1956 */               if (pi.strokeStroke != null) {
/* 1957 */                 pi.strikethroughStroke = pi.strokeStroke;
/*      */               }
/*      */               break;
/*      */           } 
/*      */         
/*      */         } 
/*      */         return;
/*      */     } 
/* 1965 */     pi.underlinePaint = null;
/* 1966 */     pi.underlineStrokePaint = null;
/* 1967 */     pi.underlineStroke = null;
/*      */     
/* 1969 */     pi.overlinePaint = null;
/* 1970 */     pi.overlineStrokePaint = null;
/* 1971 */     pi.overlineStroke = null;
/*      */     
/* 1973 */     pi.strikethroughPaint = null;
/* 1974 */     pi.strikethroughStrokePaint = null;
/* 1975 */     pi.strikethroughStroke = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract class AbstractTextChildSVGContext
/*      */     extends AnimatableSVGBridge
/*      */   {
/*      */     protected SVGTextElementBridge textBridge;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AbstractTextChildSVGContext(BridgeContext ctx, SVGTextElementBridge parent, Element e) {
/* 1998 */       this.ctx = ctx;
/* 1999 */       this.textBridge = parent;
/* 2000 */       this.e = e;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getNamespaceURI() {
/* 2008 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getLocalName() {
/* 2016 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Bridge getInstance() {
/* 2023 */       return null;
/*      */     }
/*      */     public SVGTextElementBridge getTextBridge() {
/* 2026 */       return this.textBridge;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelUnitToMillimeter() {
/* 2032 */       return this.ctx.getUserAgent().getPixelUnitToMillimeter();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelToMM() {
/* 2041 */       return getPixelUnitToMillimeter();
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
/*      */     public Rectangle2D getBBox() {
/* 2053 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AffineTransform getCTM() {
/* 2064 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AffineTransform getGlobalTransform() {
/* 2073 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AffineTransform getScreenTransform() {
/* 2082 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setScreenTransform(AffineTransform at) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getViewportWidth() {
/* 2099 */       return this.ctx.getBlockWidth(this.e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getViewportHeight() {
/* 2107 */       return this.ctx.getBlockHeight(this.e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getFontSize() {
/* 2114 */       return CSSUtilities.getComputedStyle(this.e, 22).getFloatValue();
/*      */     }
/*      */   }
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
/*      */   protected abstract class AbstractTextChildBridgeUpdateHandler
/*      */     extends AbstractTextChildSVGContext
/*      */     implements BridgeUpdateHandler
/*      */   {
/*      */     protected AbstractTextChildBridgeUpdateHandler(BridgeContext ctx, SVGTextElementBridge parent, Element e) {
/* 2140 */       super(ctx, parent, e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleDOMAttrModifiedEvent(MutationEvent evt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleDOMNodeInsertedEvent(MutationEvent evt) {
/* 2154 */       this.textBridge.handleDOMNodeInsertedEvent(evt);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleDOMNodeRemovedEvent(MutationEvent evt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleDOMCharacterDataModified(MutationEvent evt) {
/* 2168 */       this.textBridge.handleDOMCharacterDataModified(evt);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleCSSEngineEvent(CSSEngineEvent evt) {
/* 2175 */       this.textBridge.handleCSSEngineEvent(evt);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleOtherAnimationChanged(String type) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dispose() {
/* 2196 */       ((SVGOMElement)this.e).setSVGContext(null);
/* 2197 */       SVGTextElementBridge.this.elemTPI.remove(this.e);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AbstractTextChildTextContent
/*      */     extends AbstractTextChildBridgeUpdateHandler
/*      */     implements SVGTextContent
/*      */   {
/*      */     protected AbstractTextChildTextContent(BridgeContext ctx, SVGTextElementBridge parent, Element e) {
/* 2213 */       super(ctx, parent, e);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNumberOfChars() {
/* 2219 */       return this.textBridge.getNumberOfChars(this.e);
/*      */     }
/*      */     
/*      */     public Rectangle2D getExtentOfChar(int charnum) {
/* 2223 */       return this.textBridge.getExtentOfChar(this.e, charnum);
/*      */     }
/*      */     
/*      */     public Point2D getStartPositionOfChar(int charnum) {
/* 2227 */       return this.textBridge.getStartPositionOfChar(this.e, charnum);
/*      */     }
/*      */     
/*      */     public Point2D getEndPositionOfChar(int charnum) {
/* 2231 */       return this.textBridge.getEndPositionOfChar(this.e, charnum);
/*      */     }
/*      */     
/*      */     public void selectSubString(int charnum, int nchars) {
/* 2235 */       this.textBridge.selectSubString(this.e, charnum, nchars);
/*      */     }
/*      */     
/*      */     public float getRotationOfChar(int charnum) {
/* 2239 */       return this.textBridge.getRotationOfChar(this.e, charnum);
/*      */     }
/*      */     
/*      */     public float getComputedTextLength() {
/* 2243 */       return this.textBridge.getComputedTextLength(this.e);
/*      */     }
/*      */     
/*      */     public float getSubStringLength(int charnum, int nchars) {
/* 2247 */       return this.textBridge.getSubStringLength(this.e, charnum, nchars);
/*      */     }
/*      */     
/*      */     public int getCharNumAtPosition(float x, float y) {
/* 2251 */       return this.textBridge.getCharNumAtPosition(this.e, x, y);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class TRefBridge
/*      */     extends AbstractTextChildTextContent
/*      */   {
/*      */     protected TRefBridge(BridgeContext ctx, SVGTextElementBridge parent, Element e) {
/* 2264 */       super(ctx, parent, e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {
/* 2273 */       if (alav.getNamespaceURI() == null) {
/* 2274 */         String ln = alav.getLocalName();
/* 2275 */         if (ln.equals("x") || ln.equals("y") || ln.equals("dx") || ln.equals("dy") || ln.equals("rotate") || ln.equals("textLength") || ln.equals("lengthAdjust")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2283 */           this.textBridge.computeLaidoutText(this.ctx, this.textBridge.e, (GraphicsNode)this.textBridge.getTextNode());
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/* 2288 */       super.handleAnimatedAttributeChanged(alav);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class TextPathBridge
/*      */     extends AbstractTextChildTextContent
/*      */   {
/*      */     protected TextPathBridge(BridgeContext ctx, SVGTextElementBridge parent, Element e) {
/* 2301 */       super(ctx, parent, e);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class TspanBridge
/*      */     extends AbstractTextChildTextContent
/*      */   {
/*      */     protected TspanBridge(BridgeContext ctx, SVGTextElementBridge parent, Element e) {
/* 2314 */       super(ctx, parent, e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {
/* 2323 */       if (alav.getNamespaceURI() == null) {
/* 2324 */         String ln = alav.getLocalName();
/* 2325 */         if (ln.equals("x") || ln.equals("y") || ln.equals("dx") || ln.equals("dy") || ln.equals("rotate") || ln.equals("textLength") || ln.equals("lengthAdjust")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2333 */           this.textBridge.computeLaidoutText(this.ctx, this.textBridge.e, (GraphicsNode)this.textBridge.getTextNode());
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/* 2338 */       super.handleAnimatedAttributeChanged(alav);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNumberOfChars() {
/* 2344 */     return getNumberOfChars(this.e);
/*      */   }
/*      */   
/*      */   public Rectangle2D getExtentOfChar(int charnum) {
/* 2348 */     return getExtentOfChar(this.e, charnum);
/*      */   }
/*      */   
/*      */   public Point2D getStartPositionOfChar(int charnum) {
/* 2352 */     return getStartPositionOfChar(this.e, charnum);
/*      */   }
/*      */   
/*      */   public Point2D getEndPositionOfChar(int charnum) {
/* 2356 */     return getEndPositionOfChar(this.e, charnum);
/*      */   }
/*      */   
/*      */   public void selectSubString(int charnum, int nchars) {
/* 2360 */     selectSubString(this.e, charnum, nchars);
/*      */   }
/*      */   
/*      */   public float getRotationOfChar(int charnum) {
/* 2364 */     return getRotationOfChar(this.e, charnum);
/*      */   }
/*      */   
/*      */   public float getComputedTextLength() {
/* 2368 */     return getComputedTextLength(this.e);
/*      */   }
/*      */   
/*      */   public float getSubStringLength(int charnum, int nchars) {
/* 2372 */     return getSubStringLength(this.e, charnum, nchars);
/*      */   }
/*      */   
/*      */   public int getCharNumAtPosition(float x, float y) {
/* 2376 */     return getCharNumAtPosition(this.e, x, y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getNumberOfChars(Element element) {
/* 2386 */     AttributedCharacterIterator aci = getTextNode().getAttributedCharacterIterator();
/* 2387 */     if (aci == null) {
/* 2388 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 2392 */     int firstChar = getElementStartIndex(element);
/*      */     
/* 2394 */     if (firstChar == -1) {
/* 2395 */       return 0;
/*      */     }
/* 2397 */     int lastChar = getElementEndIndex(element);
/*      */     
/* 2399 */     return lastChar - firstChar + 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Rectangle2D getExtentOfChar(Element element, int charnum) {
/* 2408 */     TextNode textNode = getTextNode();
/*      */ 
/*      */     
/* 2411 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 2412 */     if (aci == null) return null;
/*      */     
/* 2414 */     int firstChar = getElementStartIndex(element);
/*      */     
/* 2416 */     if (firstChar == -1) {
/* 2417 */       return null;
/*      */     }
/*      */     
/* 2420 */     List list = getTextRuns(textNode);
/*      */ 
/*      */ 
/*      */     
/* 2424 */     CharacterInformation info = getCharacterInformation(list, firstChar, charnum, aci);
/*      */     
/* 2426 */     if (info == null) {
/* 2427 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2431 */     GVTGlyphVector it = info.layout.getGlyphVector();
/*      */     
/* 2433 */     Shape b = null;
/*      */     
/* 2435 */     if (info.glyphIndexStart == info.glyphIndexEnd) {
/* 2436 */       if (it.isGlyphVisible(info.glyphIndexStart)) {
/* 2437 */         b = it.getGlyphCellBounds(info.glyphIndexStart);
/*      */       }
/*      */     } else {
/* 2440 */       GeneralPath path = null;
/* 2441 */       for (int k = info.glyphIndexStart; k <= info.glyphIndexEnd; k++) {
/* 2442 */         if (it.isGlyphVisible(k)) {
/* 2443 */           Rectangle2D gb = it.getGlyphCellBounds(k);
/* 2444 */           if (path == null) {
/* 2445 */             path = new GeneralPath(gb);
/*      */           } else {
/* 2447 */             path.append(gb, false);
/*      */           } 
/*      */         } 
/*      */       } 
/* 2451 */       b = path;
/*      */     } 
/*      */     
/* 2454 */     if (b == null) {
/* 2455 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2459 */     return b.getBounds2D();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Point2D getStartPositionOfChar(Element element, int charnum) {
/* 2468 */     TextNode textNode = getTextNode();
/*      */ 
/*      */     
/* 2471 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 2472 */     if (aci == null) {
/* 2473 */       return null;
/*      */     }
/* 2475 */     int firstChar = getElementStartIndex(element);
/* 2476 */     if (firstChar == -1) {
/* 2477 */       return null;
/*      */     }
/*      */     
/* 2480 */     List list = getTextRuns(textNode);
/*      */ 
/*      */ 
/*      */     
/* 2484 */     CharacterInformation info = getCharacterInformation(list, firstChar, charnum, aci);
/*      */     
/* 2486 */     if (info == null) {
/* 2487 */       return null;
/*      */     }
/* 2489 */     return getStartPoint(info);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Point2D getStartPoint(CharacterInformation info) {
/* 2494 */     GVTGlyphVector it = info.layout.getGlyphVector();
/* 2495 */     if (!it.isGlyphVisible(info.glyphIndexStart)) {
/* 2496 */       return null;
/*      */     }
/* 2498 */     Point2D b = it.getGlyphPosition(info.glyphIndexStart);
/*      */ 
/*      */     
/* 2501 */     AffineTransform glyphTransform = it.getGlyphTransform(info.glyphIndexStart);
/*      */ 
/*      */ 
/*      */     
/* 2505 */     Point2D.Float result = new Point2D.Float(0.0F, 0.0F);
/* 2506 */     if (glyphTransform != null)
/*      */     {
/* 2508 */       glyphTransform.transform(result, result);
/*      */     }
/* 2510 */     result.x = (float)(result.x + b.getX());
/* 2511 */     result.y = (float)(result.y + b.getY());
/* 2512 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Point2D getEndPositionOfChar(Element element, int charnum) {
/* 2520 */     TextNode textNode = getTextNode();
/*      */ 
/*      */     
/* 2523 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 2524 */     if (aci == null) {
/* 2525 */       return null;
/*      */     }
/* 2527 */     int firstChar = getElementStartIndex(element);
/* 2528 */     if (firstChar == -1) {
/* 2529 */       return null;
/*      */     }
/*      */     
/* 2532 */     List list = getTextRuns(textNode);
/*      */ 
/*      */ 
/*      */     
/* 2536 */     CharacterInformation info = getCharacterInformation(list, firstChar, charnum, aci);
/*      */     
/* 2538 */     if (info == null)
/* 2539 */       return null; 
/* 2540 */     return getEndPoint(info);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Point2D getEndPoint(CharacterInformation info) {
/* 2545 */     GVTGlyphVector it = info.layout.getGlyphVector();
/* 2546 */     if (!it.isGlyphVisible(info.glyphIndexEnd)) {
/* 2547 */       return null;
/*      */     }
/* 2549 */     Point2D b = it.getGlyphPosition(info.glyphIndexEnd);
/*      */ 
/*      */     
/* 2552 */     AffineTransform glyphTransform = it.getGlyphTransform(info.glyphIndexEnd);
/*      */     
/* 2554 */     GVTGlyphMetrics metrics = it.getGlyphMetrics(info.glyphIndexEnd);
/*      */ 
/*      */     
/* 2557 */     Point2D.Float result = new Point2D.Float(metrics.getHorizontalAdvance(), 0.0F);
/*      */ 
/*      */     
/* 2560 */     if (glyphTransform != null) {
/* 2561 */       glyphTransform.transform(result, result);
/*      */     }
/* 2563 */     result.x = (float)(result.x + b.getX());
/* 2564 */     result.y = (float)(result.y + b.getY());
/* 2565 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getRotationOfChar(Element element, int charnum) {
/* 2573 */     TextNode textNode = getTextNode();
/*      */ 
/*      */     
/* 2576 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 2577 */     if (aci == null) {
/* 2578 */       return 0.0F;
/*      */     }
/*      */     
/* 2581 */     int firstChar = getElementStartIndex(element);
/* 2582 */     if (firstChar == -1) {
/* 2583 */       return 0.0F;
/*      */     }
/*      */     
/* 2586 */     List list = getTextRuns(textNode);
/*      */ 
/*      */ 
/*      */     
/* 2590 */     CharacterInformation info = getCharacterInformation(list, firstChar, charnum, aci);
/*      */     
/* 2592 */     double angle = 0.0D;
/* 2593 */     int nbGlyphs = 0;
/*      */     
/* 2595 */     if (info != null) {
/* 2596 */       GVTGlyphVector it = info.layout.getGlyphVector();
/*      */       
/* 2598 */       int k = info.glyphIndexStart;
/* 2599 */       for (; k <= info.glyphIndexEnd; 
/* 2600 */         k++) {
/* 2601 */         if (it.isGlyphVisible(k)) {
/*      */           
/* 2603 */           nbGlyphs++;
/*      */ 
/*      */           
/* 2606 */           AffineTransform glyphTransform = it.getGlyphTransform(k);
/* 2607 */           if (glyphTransform != null)
/*      */           
/* 2609 */           { double glyphAngle = 0.0D;
/* 2610 */             double cosTheta = glyphTransform.getScaleX();
/* 2611 */             double sinTheta = glyphTransform.getShearX();
/*      */ 
/*      */             
/* 2614 */             if (cosTheta == 0.0D)
/* 2615 */             { if (sinTheta > 0.0D) { glyphAngle = Math.PI; }
/* 2616 */               else { glyphAngle = -3.141592653589793D; }
/*      */                }
/* 2618 */             else { glyphAngle = Math.atan(sinTheta / cosTheta);
/* 2619 */               if (cosTheta < 0.0D) {
/* 2620 */                 glyphAngle += Math.PI;
/*      */               } }
/*      */ 
/*      */ 
/*      */             
/* 2625 */             glyphAngle = Math.toDegrees(-glyphAngle) % 360.0D;
/*      */ 
/*      */             
/* 2628 */             angle += glyphAngle - info.getComputedOrientationAngle(); } 
/*      */         } 
/*      */       } 
/* 2631 */     }  if (nbGlyphs == 0) return 0.0F; 
/* 2632 */     return (float)(angle / nbGlyphs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getComputedTextLength(Element e) {
/* 2640 */     return getSubStringLength(e, 0, getNumberOfChars(e));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getSubStringLength(Element element, int charnum, int nchars) {
/* 2650 */     if (nchars == 0) {
/* 2651 */       return 0.0F;
/*      */     }
/*      */     
/* 2654 */     float length = 0.0F;
/*      */     
/* 2656 */     TextNode textNode = getTextNode();
/*      */ 
/*      */     
/* 2659 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 2660 */     if (aci == null) {
/* 2661 */       return -1.0F;
/*      */     }
/* 2663 */     int firstChar = getElementStartIndex(element);
/*      */     
/* 2665 */     if (firstChar == -1) {
/* 2666 */       return -1.0F;
/*      */     }
/* 2668 */     List list = getTextRuns(textNode);
/*      */ 
/*      */     
/* 2671 */     CharacterInformation currentInfo = getCharacterInformation(list, firstChar, charnum, aci);
/* 2672 */     CharacterInformation lastCharacterInRunInfo = null;
/* 2673 */     int chIndex = currentInfo.characterIndex + 1;
/* 2674 */     GVTGlyphVector vector = currentInfo.layout.getGlyphVector();
/* 2675 */     float[] advs = currentInfo.layout.getGlyphAdvances();
/* 2676 */     boolean[] glyphTrack = new boolean[advs.length];
/* 2677 */     for (int k = charnum + 1; k < charnum + nchars; k++) {
/* 2678 */       if (currentInfo.layout.isOnATextPath()) {
/* 2679 */         int gi = currentInfo.glyphIndexStart;
/* 2680 */         for (; gi <= currentInfo.glyphIndexEnd; gi++) {
/* 2681 */           if (vector.isGlyphVisible(gi) && !glyphTrack[gi])
/* 2682 */             length += advs[gi + 1] - advs[gi]; 
/* 2683 */           glyphTrack[gi] = true;
/*      */         } 
/*      */         
/* 2686 */         CharacterInformation newInfo = getCharacterInformation(list, firstChar, k, aci);
/* 2687 */         if (newInfo.layout != currentInfo.layout) {
/* 2688 */           vector = newInfo.layout.getGlyphVector();
/* 2689 */           advs = newInfo.layout.getGlyphAdvances();
/* 2690 */           glyphTrack = new boolean[advs.length];
/* 2691 */           chIndex = currentInfo.characterIndex + 1;
/*      */         } 
/* 2693 */         currentInfo = newInfo;
/*      */       
/*      */       }
/* 2696 */       else if (currentInfo.layout.hasCharacterIndex(chIndex)) {
/* 2697 */         chIndex++;
/*      */       }
/*      */       else {
/*      */         
/* 2701 */         lastCharacterInRunInfo = getCharacterInformation(list, firstChar, k - 1, aci);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2706 */         length += distanceFirstLastCharacterInRun(currentInfo, lastCharacterInRunInfo);
/*      */ 
/*      */         
/* 2709 */         currentInfo = getCharacterInformation(list, firstChar, k, aci);
/* 2710 */         chIndex = currentInfo.characterIndex + 1;
/* 2711 */         vector = currentInfo.layout.getGlyphVector();
/* 2712 */         advs = currentInfo.layout.getGlyphAdvances();
/* 2713 */         glyphTrack = new boolean[advs.length];
/* 2714 */         lastCharacterInRunInfo = null;
/*      */       } 
/*      */     } 
/*      */     
/* 2718 */     if (currentInfo.layout.isOnATextPath()) {
/* 2719 */       int gi = currentInfo.glyphIndexStart;
/* 2720 */       for (; gi <= currentInfo.glyphIndexEnd; gi++) {
/* 2721 */         if (vector.isGlyphVisible(gi) && !glyphTrack[gi])
/* 2722 */           length += advs[gi + 1] - advs[gi]; 
/* 2723 */         glyphTrack[gi] = true;
/*      */       } 
/*      */     } else {
/* 2726 */       if (lastCharacterInRunInfo == null) {
/* 2727 */         lastCharacterInRunInfo = getCharacterInformation(list, firstChar, charnum + nchars - 1, aci);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2732 */       length += distanceFirstLastCharacterInRun(currentInfo, lastCharacterInRunInfo);
/*      */     } 
/*      */ 
/*      */     
/* 2736 */     return length;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected float distanceFirstLastCharacterInRun(CharacterInformation first, CharacterInformation last) {
/* 2742 */     float[] advs = first.layout.getGlyphAdvances();
/*      */     
/* 2744 */     int firstStart = first.glyphIndexStart;
/* 2745 */     int firstEnd = first.glyphIndexEnd;
/* 2746 */     int lastStart = last.glyphIndexStart;
/* 2747 */     int lastEnd = last.glyphIndexEnd;
/*      */     
/* 2749 */     int start = (firstStart < lastStart) ? firstStart : lastStart;
/* 2750 */     int end = (firstEnd < lastEnd) ? lastEnd : firstEnd;
/* 2751 */     return advs[end + 1] - advs[start];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float distanceBetweenRun(CharacterInformation last, CharacterInformation first) {
/*      */     float distance;
/* 2760 */     CharacterInformation info = new CharacterInformation();
/*      */ 
/*      */ 
/*      */     
/* 2764 */     info.layout = last.layout;
/* 2765 */     info.glyphIndexEnd = last.layout.getGlyphCount() - 1;
/*      */     
/* 2767 */     Point2D startPoint = getEndPoint(info);
/*      */ 
/*      */     
/* 2770 */     info.layout = first.layout;
/* 2771 */     info.glyphIndexStart = 0;
/*      */     
/* 2773 */     Point2D endPoint = getStartPoint(info);
/*      */     
/* 2775 */     if (first.isVertical()) {
/* 2776 */       distance = (float)(endPoint.getY() - startPoint.getY());
/*      */     } else {
/*      */       
/* 2779 */       distance = (float)(endPoint.getX() - startPoint.getX());
/*      */     } 
/*      */     
/* 2782 */     return distance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void selectSubString(Element element, int charnum, int nchars) {
/*      */     Mark lastMark;
/* 2793 */     TextNode textNode = getTextNode();
/*      */ 
/*      */     
/* 2796 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 2797 */     if (aci == null) {
/*      */       return;
/*      */     }
/* 2800 */     int firstChar = getElementStartIndex(element);
/*      */     
/* 2802 */     if (firstChar == -1) {
/*      */       return;
/*      */     }
/* 2805 */     List list = getTextRuns(textNode);
/*      */     
/* 2807 */     int lastChar = getElementEndIndex(element);
/*      */ 
/*      */     
/* 2810 */     CharacterInformation firstInfo = getCharacterInformation(list, firstChar, charnum, aci);
/* 2811 */     CharacterInformation lastInfo = getCharacterInformation(list, firstChar, charnum + nchars - 1, aci);
/*      */ 
/*      */     
/* 2814 */     Mark firstMark = textNode.getMarkerForChar(firstInfo.characterIndex, true);
/*      */     
/* 2816 */     if (lastInfo != null && lastInfo.characterIndex <= lastChar) {
/* 2817 */       lastMark = textNode.getMarkerForChar(lastInfo.characterIndex, false);
/*      */     } else {
/*      */       
/* 2820 */       lastMark = textNode.getMarkerForChar(lastChar, false);
/*      */     } 
/*      */     
/* 2823 */     this.ctx.getUserAgent().setTextSelection(firstMark, lastMark);
/*      */   }
/*      */   
/*      */   protected int getCharNumAtPosition(Element e, float x, float y) {
/* 2827 */     TextNode textNode = getTextNode();
/*      */ 
/*      */     
/* 2830 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 2831 */     if (aci == null) {
/* 2832 */       return -1;
/*      */     }
/*      */     
/* 2835 */     List<StrokingTextPainter.TextRun> list = getTextRuns(textNode);
/*      */ 
/*      */ 
/*      */     
/* 2839 */     TextHit hit = null;
/*      */     
/* 2841 */     for (int i = list.size() - 1; i >= 0 && hit == null; i--) {
/*      */       
/* 2843 */       StrokingTextPainter.TextRun textRun = list.get(i);
/* 2844 */       hit = textRun.getLayout().hitTestChar(x, y);
/*      */     } 
/*      */     
/* 2847 */     if (hit == null) {
/* 2848 */       return -1;
/*      */     }
/*      */ 
/*      */     
/* 2852 */     int first = getElementStartIndex(e);
/* 2853 */     int last = getElementEndIndex(e);
/*      */     
/* 2855 */     int hitIndex = hit.getCharIndex();
/*      */     
/* 2857 */     if (hitIndex >= first && hitIndex <= last) {
/* 2858 */       return hitIndex - first;
/*      */     }
/* 2860 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List getTextRuns(TextNode node) {
/* 2869 */     if (node.getTextRuns() == null)
/*      */     {
/*      */       
/* 2872 */       node.getPrimitiveBounds();
/*      */     }
/*      */     
/* 2875 */     return node.getTextRuns();
/*      */   }
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
/*      */   protected CharacterInformation getCharacterInformation(List list, int startIndex, int charnum, AttributedCharacterIterator aci) {
/* 2898 */     CharacterInformation info = new CharacterInformation();
/* 2899 */     info.characterIndex = startIndex + charnum;
/*      */     
/* 2901 */     for (Object aList : list) {
/*      */       
/* 2903 */       StrokingTextPainter.TextRun run = (StrokingTextPainter.TextRun)aList;
/*      */       
/* 2905 */       if (!run.getLayout().hasCharacterIndex(info.characterIndex)) {
/*      */         continue;
/*      */       }
/* 2908 */       info.layout = run.getLayout();
/*      */       
/* 2910 */       aci.setIndex(info.characterIndex);
/*      */ 
/*      */       
/* 2913 */       if (aci.getAttribute(ALT_GLYPH_HANDLER) != null) {
/* 2914 */         info.glyphIndexStart = 0;
/* 2915 */         info.glyphIndexEnd = info.layout.getGlyphCount() - 1;
/*      */       } else {
/* 2917 */         info.glyphIndexStart = info.layout.getGlyphIndex(info.characterIndex);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2922 */         if (info.glyphIndexStart == -1) {
/* 2923 */           info.glyphIndexStart = 0;
/* 2924 */           info.glyphIndexEnd = info.layout.getGlyphCount() - 1;
/*      */         } else {
/* 2926 */           info.glyphIndexEnd = info.glyphIndexStart;
/*      */         } 
/*      */       } 
/* 2929 */       return info;
/*      */     } 
/* 2931 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class CharacterInformation
/*      */   {
/*      */     TextSpanLayout layout;
/*      */ 
/*      */     
/*      */     int glyphIndexStart;
/*      */ 
/*      */     
/*      */     int glyphIndexEnd;
/*      */ 
/*      */     
/*      */     int characterIndex;
/*      */ 
/*      */     
/*      */     public boolean isVertical() {
/* 2951 */       return this.layout.isVertical();
/*      */     }
/*      */     
/*      */     public double getComputedOrientationAngle() {
/* 2955 */       return this.layout.getComputedOrientationAngle(this.characterIndex);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Set getTextIntersectionSet(AffineTransform at, Rectangle2D rect) {
/* 2962 */     Set<Element> elems = new HashSet();
/*      */     
/* 2964 */     TextNode tn = getTextNode();
/*      */     
/* 2966 */     List list = tn.getTextRuns();
/* 2967 */     if (list == null) {
/* 2968 */       return elems;
/*      */     }
/* 2970 */     for (Object aList : list) {
/*      */       
/* 2972 */       StrokingTextPainter.TextRun run = (StrokingTextPainter.TextRun)aList;
/* 2973 */       TextSpanLayout layout = run.getLayout();
/* 2974 */       AttributedCharacterIterator aci = run.getACI();
/* 2975 */       aci.first();
/*      */       
/* 2977 */       SoftReference<Element> sr = (SoftReference)aci.getAttribute(TEXT_COMPOUND_ID);
/* 2978 */       Element elem = sr.get();
/*      */       
/* 2980 */       if (elem == null || 
/* 2981 */         elems.contains(elem) || 
/* 2982 */         !isTextSensitive(elem))
/*      */         continue; 
/* 2984 */       Rectangle2D glBounds = layout.getBounds2D();
/* 2985 */       if (glBounds != null) {
/* 2986 */         glBounds = at.createTransformedShape(glBounds).getBounds2D();
/*      */         
/* 2988 */         if (!rect.intersects(glBounds)) {
/*      */           continue;
/*      */         }
/*      */       } 
/*      */       
/* 2993 */       GVTGlyphVector gv = layout.getGlyphVector();
/* 2994 */       for (int g = 0; g < gv.getNumGlyphs(); g++) {
/* 2995 */         Shape gBounds = gv.getGlyphLogicalBounds(g);
/* 2996 */         if (gBounds != null) {
/* 2997 */           gBounds = at.createTransformedShape(gBounds).getBounds2D();
/*      */ 
/*      */           
/* 3000 */           if (gBounds.intersects(rect)) {
/* 3001 */             elems.add(elem);
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 3007 */     return elems;
/*      */   }
/*      */ 
/*      */   
/*      */   public Set getTextEnclosureSet(AffineTransform at, Rectangle2D rect) {
/* 3012 */     TextNode tn = getTextNode();
/*      */     
/* 3014 */     Set<Element> elems = new HashSet();
/* 3015 */     List list = tn.getTextRuns();
/* 3016 */     if (list == null) {
/* 3017 */       return elems;
/*      */     }
/* 3019 */     Set<Element> reject = new HashSet();
/* 3020 */     for (Object aList : list) {
/*      */       
/* 3022 */       StrokingTextPainter.TextRun run = (StrokingTextPainter.TextRun)aList;
/* 3023 */       TextSpanLayout layout = run.getLayout();
/* 3024 */       AttributedCharacterIterator aci = run.getACI();
/* 3025 */       aci.first();
/*      */       
/* 3027 */       SoftReference<Element> sr = (SoftReference)aci.getAttribute(TEXT_COMPOUND_ID);
/* 3028 */       Element elem = sr.get();
/*      */       
/* 3030 */       if (elem == null || 
/* 3031 */         reject.contains(elem))
/* 3032 */         continue;  if (!isTextSensitive(elem)) {
/* 3033 */         reject.add(elem);
/*      */         
/*      */         continue;
/*      */       } 
/* 3037 */       Rectangle2D glBounds = layout.getBounds2D();
/* 3038 */       if (glBounds == null) {
/*      */         continue;
/*      */       }
/*      */       
/* 3042 */       glBounds = at.createTransformedShape(glBounds).getBounds2D();
/*      */       
/* 3044 */       if (rect.contains(glBounds)) {
/* 3045 */         elems.add(elem); continue;
/*      */       } 
/* 3047 */       reject.add(elem);
/* 3048 */       elems.remove(elem);
/*      */     } 
/*      */ 
/*      */     
/* 3052 */     return elems;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getTextIntersection(BridgeContext ctx, Element elem, AffineTransform ati, Rectangle2D rect, boolean checkSensitivity) {
/* 3060 */     SVGContext svgCtx = null;
/* 3061 */     if (elem instanceof SVGOMElement)
/* 3062 */       svgCtx = ((SVGOMElement)elem).getSVGContext(); 
/* 3063 */     if (svgCtx == null) return false;
/*      */     
/* 3065 */     SVGTextElementBridge txtBridge = null;
/* 3066 */     if (svgCtx instanceof SVGTextElementBridge) {
/* 3067 */       txtBridge = (SVGTextElementBridge)svgCtx;
/* 3068 */     } else if (svgCtx instanceof AbstractTextChildSVGContext) {
/*      */       
/* 3070 */       AbstractTextChildSVGContext childCtx = (AbstractTextChildSVGContext)svgCtx;
/* 3071 */       txtBridge = childCtx.getTextBridge();
/*      */     } 
/* 3073 */     if (txtBridge == null) return false;
/*      */     
/* 3075 */     TextNode tn = txtBridge.getTextNode();
/* 3076 */     List list = tn.getTextRuns();
/* 3077 */     if (list == null) {
/* 3078 */       return false;
/*      */     }
/* 3080 */     Element txtElem = txtBridge.e;
/*      */     
/* 3082 */     AffineTransform at = tn.getGlobalTransform();
/* 3083 */     at.preConcatenate(ati);
/*      */ 
/*      */     
/* 3086 */     Rectangle2D tnRect = tn.getBounds();
/* 3087 */     tnRect = at.createTransformedShape(tnRect).getBounds2D();
/* 3088 */     if (!rect.intersects(tnRect)) return false;
/*      */     
/* 3090 */     for (Object aList : list) {
/*      */       
/* 3092 */       StrokingTextPainter.TextRun run = (StrokingTextPainter.TextRun)aList;
/* 3093 */       TextSpanLayout layout = run.getLayout();
/* 3094 */       AttributedCharacterIterator aci = run.getACI();
/* 3095 */       aci.first();
/*      */       
/* 3097 */       SoftReference<Element> sr = (SoftReference)aci.getAttribute(TEXT_COMPOUND_ID);
/* 3098 */       Element runElem = sr.get();
/* 3099 */       if (runElem == null) {
/*      */         continue;
/*      */       }
/* 3102 */       if (checkSensitivity && !isTextSensitive(runElem))
/*      */         continue; 
/* 3104 */       Element p = runElem;
/* 3105 */       while (p != null && p != txtElem && p != elem) {
/* 3106 */         p = (Element)txtBridge.getParentNode(p);
/*      */       }
/* 3108 */       if (p != elem) {
/*      */         continue;
/*      */       }
/* 3111 */       Rectangle2D glBounds = layout.getBounds2D();
/* 3112 */       if (glBounds == null)
/* 3113 */         continue;  glBounds = at.createTransformedShape(glBounds).getBounds2D();
/* 3114 */       if (!rect.intersects(glBounds))
/*      */         continue; 
/* 3116 */       GVTGlyphVector gv = layout.getGlyphVector();
/* 3117 */       for (int g = 0; g < gv.getNumGlyphs(); g++) {
/* 3118 */         Shape gBounds = gv.getGlyphLogicalBounds(g);
/* 3119 */         if (gBounds != null) {
/* 3120 */           gBounds = at.createTransformedShape(gBounds).getBounds2D();
/*      */ 
/*      */           
/* 3123 */           if (gBounds.intersects(rect)) {
/* 3124 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 3129 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Rectangle2D getTextBounds(BridgeContext ctx, Element elem, boolean checkSensitivity) {
/* 3134 */     SVGContext svgCtx = null;
/* 3135 */     if (elem instanceof SVGOMElement)
/* 3136 */       svgCtx = ((SVGOMElement)elem).getSVGContext(); 
/* 3137 */     if (svgCtx == null) return null;
/*      */     
/* 3139 */     SVGTextElementBridge txtBridge = null;
/* 3140 */     if (svgCtx instanceof SVGTextElementBridge) {
/* 3141 */       txtBridge = (SVGTextElementBridge)svgCtx;
/* 3142 */     } else if (svgCtx instanceof AbstractTextChildSVGContext) {
/*      */       
/* 3144 */       AbstractTextChildSVGContext childCtx = (AbstractTextChildSVGContext)svgCtx;
/* 3145 */       txtBridge = childCtx.getTextBridge();
/*      */     } 
/* 3147 */     if (txtBridge == null) return null;
/*      */     
/* 3149 */     TextNode tn = txtBridge.getTextNode();
/* 3150 */     List list = tn.getTextRuns();
/* 3151 */     if (list == null) {
/* 3152 */       return null;
/*      */     }
/* 3154 */     Element txtElem = txtBridge.e;
/* 3155 */     Rectangle2D ret = null;
/*      */     
/* 3157 */     for (Object aList : list) {
/*      */       
/* 3159 */       StrokingTextPainter.TextRun run = (StrokingTextPainter.TextRun)aList;
/* 3160 */       TextSpanLayout layout = run.getLayout();
/* 3161 */       AttributedCharacterIterator aci = run.getACI();
/* 3162 */       aci.first();
/*      */       
/* 3164 */       SoftReference<Element> sr = (SoftReference)aci.getAttribute(TEXT_COMPOUND_ID);
/* 3165 */       Element runElem = sr.get();
/* 3166 */       if (runElem == null) {
/*      */         continue;
/*      */       }
/* 3169 */       if (checkSensitivity && !isTextSensitive(runElem))
/*      */         continue; 
/* 3171 */       Element p = runElem;
/* 3172 */       while (p != null && p != txtElem && p != elem) {
/* 3173 */         p = (Element)txtBridge.getParentNode(p);
/*      */       }
/* 3175 */       if (p != elem) {
/*      */         continue;
/*      */       }
/* 3178 */       Rectangle2D glBounds = layout.getBounds2D();
/* 3179 */       if (glBounds != null) {
/* 3180 */         if (ret == null) { ret = (Rectangle2D)glBounds.clone(); continue; }
/* 3181 */          ret.add(glBounds);
/*      */       } 
/*      */     } 
/* 3184 */     return ret;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTextSensitive(Element e) {
/* 3189 */     int ptrEvts = CSSUtilities.convertPointerEvents(e);
/* 3190 */     switch (ptrEvts) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/* 3195 */         return CSSUtilities.convertVisibility(e);
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/* 3200 */         return true;
/*      */     } 
/*      */     
/* 3203 */     return false;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGTextElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */