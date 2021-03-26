/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.anim.dom.SVGOMElement;
/*     */ import org.apache.batik.anim.dom.XBLEventSupport;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.CSSUtilities;
/*     */ import org.apache.batik.bridge.CursorManager;
/*     */ import org.apache.batik.bridge.FlowTextNode;
/*     */ import org.apache.batik.bridge.GVTBuilder;
/*     */ import org.apache.batik.bridge.SVGAElementBridge;
/*     */ import org.apache.batik.bridge.SVGTextElementBridge;
/*     */ import org.apache.batik.bridge.SVGUtilities;
/*     */ import org.apache.batik.bridge.TextNode;
/*     */ import org.apache.batik.bridge.TextUtilities;
/*     */ import org.apache.batik.bridge.UserAgent;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.ComputedValue;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
/*     */ import org.apache.batik.css.engine.value.svg12.LineHeightValue;
/*     */ import org.apache.batik.css.engine.value.svg12.SVG12ValueConstants;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.flow.BlockInfo;
/*     */ import org.apache.batik.gvt.flow.RegionInfo;
/*     */ import org.apache.batik.gvt.flow.TextLineBreaks;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ import org.apache.batik.gvt.text.TextPaintInfo;
/*     */ import org.apache.batik.gvt.text.TextPath;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.Event;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGFlowRootElementBridge
/*     */   extends SVG12TextElementBridge
/*     */ {
/*  89 */   public static final AttributedCharacterIterator.Attribute FLOW_PARAGRAPH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_PARAGRAPH;
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static final AttributedCharacterIterator.Attribute FLOW_EMPTY_PARAGRAPH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_EMPTY_PARAGRAPH;
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static final AttributedCharacterIterator.Attribute FLOW_LINE_BREAK = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_LINE_BREAK;
/*     */ 
/*     */ 
/*     */   
/* 101 */   public static final AttributedCharacterIterator.Attribute FLOW_REGIONS = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_REGIONS;
/*     */ 
/*     */ 
/*     */   
/* 105 */   public static final AttributedCharacterIterator.Attribute LINE_HEIGHT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.LINE_HEIGHT;
/*     */ 
/*     */ 
/*     */   
/* 109 */   public static final GVTAttributedCharacterIterator.TextAttribute TEXTPATH = GVTAttributedCharacterIterator.TextAttribute.TEXTPATH;
/*     */ 
/*     */ 
/*     */   
/* 113 */   public static final GVTAttributedCharacterIterator.TextAttribute ANCHOR_TYPE = GVTAttributedCharacterIterator.TextAttribute.ANCHOR_TYPE;
/*     */ 
/*     */ 
/*     */   
/* 117 */   public static final GVTAttributedCharacterIterator.TextAttribute LETTER_SPACING = GVTAttributedCharacterIterator.TextAttribute.LETTER_SPACING;
/*     */ 
/*     */ 
/*     */   
/* 121 */   public static final GVTAttributedCharacterIterator.TextAttribute WORD_SPACING = GVTAttributedCharacterIterator.TextAttribute.WORD_SPACING;
/*     */ 
/*     */ 
/*     */   
/* 125 */   public static final GVTAttributedCharacterIterator.TextAttribute KERNING = GVTAttributedCharacterIterator.TextAttribute.KERNING;
/*     */   
/*     */   protected Map flowRegionNodes;
/*     */   
/*     */   protected TextNode textNode;
/*     */   protected RegionChangeListener regionChangeListener;
/*     */   protected int startLen;
/*     */   int marginTopIndex;
/*     */   
/*     */   protected TextNode getTextNode() {
/* 135 */     return this.textNode;
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
/*     */   int marginRightIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int marginBottomIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int marginLeftIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int indentIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int textAlignIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int lineHeightIndex;
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
/*     */     return "http://www.w3.org/2000/svg";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*     */     return "flowRoot";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*     */     return (Bridge)new SVGFlowRootElementBridge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/*     */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/*     */     if (!SVGUtilities.matchUserAgent(e, ctx.getUserAgent())) {
/*     */       return null;
/*     */     }
/*     */     CompositeGraphicsNode cgn = new CompositeGraphicsNode();
/*     */     String s = e.getAttributeNS((String)null, "transform");
/*     */     if (s.length() != 0) {
/*     */       cgn.setTransform(SVGUtilities.convertTransform(e, "transform", s, ctx));
/*     */     }
/*     */     cgn.setVisible(CSSUtilities.convertVisibility(e));
/*     */     RenderingHints hints = null;
/*     */     hints = CSSUtilities.convertColorRendering(e, hints);
/*     */     hints = CSSUtilities.convertTextRendering(e, hints);
/*     */     if (hints != null) {
/*     */       cgn.setRenderingHints(hints);
/*     */     }
/*     */     CompositeGraphicsNode cgn2 = new CompositeGraphicsNode();
/*     */     cgn.add(cgn2);
/*     */     FlowTextNode tn = (FlowTextNode)instantiateGraphicsNode();
/*     */     tn.setLocation(getLocation(ctx, e));
/*     */     if (ctx.getTextPainter() != null) {
/*     */       tn.setTextPainter(ctx.getTextPainter());
/*     */     }
/*     */     this.textNode = (TextNode)tn;
/*     */     cgn.add(tn);
/*     */     associateSVGContext(ctx, e, (GraphicsNode)cgn);
/*     */     Node child = getFirstChild(e);
/*     */     while (child != null) {
/*     */       if (child.getNodeType() == 1) {
/*     */         addContextToChild(ctx, (Element)child);
/*     */       }
/*     */       child = getNextSibling(child);
/*     */     } 
/*     */     return (GraphicsNode)cgn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/*     */     return (GraphicsNode)new FlowTextNode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point2D getLocation(BridgeContext ctx, Element e) {
/*     */     return new Point2D.Float(0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isTextElement(Element e) {
/*     */     if (!"http://www.w3.org/2000/svg".equals(e.getNamespaceURI())) {
/*     */       return false;
/*     */     }
/*     */     String nodeName = e.getLocalName();
/*     */     return (nodeName.equals("flowDiv") || nodeName.equals("flowLine") || nodeName.equals("flowPara") || nodeName.equals("flowRegionBreak") || nodeName.equals("flowSpan"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isTextChild(Element e) {
/*     */     if (!"http://www.w3.org/2000/svg".equals(e.getNamespaceURI())) {
/*     */       return false;
/*     */     }
/*     */     String nodeName = e.getLocalName();
/*     */     return (nodeName.equals("a") || nodeName.equals("flowLine") || nodeName.equals("flowPara") || nodeName.equals("flowRegionBreak") || nodeName.equals("flowSpan"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/*     */     CompositeGraphicsNode cgn = (CompositeGraphicsNode)node;
/*     */     boolean isStatic = !ctx.isDynamic();
/*     */     if (isStatic) {
/*     */       this.flowRegionNodes = new HashMap<Object, Object>();
/*     */     } else {
/*     */       this.regionChangeListener = new RegionChangeListener();
/*     */     } 
/*     */     CompositeGraphicsNode cgn2 = (CompositeGraphicsNode)cgn.get(0);
/*     */     GVTBuilder builder = ctx.getGVTBuilder();
/*     */     for (Node n = getFirstChild(e); n != null; n = getNextSibling(n)) {
/*     */       if (n instanceof org.apache.batik.anim.dom.SVGOMFlowRegionElement) {
/*     */         Node m = getFirstChild(n);
/*     */         for (; m != null; m = getNextSibling(m)) {
/*     */           if (m.getNodeType() == 1) {
/*     */             GraphicsNode gn = builder.build(ctx, (Element)m);
/*     */             if (gn != null) {
/*     */               cgn2.add(gn);
/*     */               if (isStatic) {
/*     */                 this.flowRegionNodes.put(m, gn);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         if (!isStatic) {
/*     */           AbstractNode an = (AbstractNode)n;
/*     */           XBLEventSupport es = (XBLEventSupport)an.initializeEventSupport();
/*     */           es.addImplementationEventListenerNS("http://www.w3.org/2000/svg", "shapechange", this.regionChangeListener, false);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     GraphicsNode tn = (GraphicsNode)cgn.get(1);
/*     */     super.buildGraphicsNode(ctx, e, tn);
/*     */     this.flowRegionNodes = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void computeLaidoutText(BridgeContext ctx, Element e, GraphicsNode node) {
/*     */     super.computeLaidoutText(ctx, getFlowDivElement(e), node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addContextToChild(BridgeContext ctx, Element e) {
/*     */     if ("http://www.w3.org/2000/svg".equals(e.getNamespaceURI())) {
/*     */       String ln = e.getLocalName();
/*     */       if (ln.equals("flowDiv") || ln.equals("flowLine") || ln.equals("flowPara") || ln.equals("flowSpan")) {
/*     */         ((SVGOMElement)e).setSVGContext((SVGContext)new FlowContentBridge(ctx, this, e));
/*     */       }
/*     */     } 
/*     */     Node child = getFirstChild(e);
/*     */     while (child != null) {
/*     */       if (child.getNodeType() == 1) {
/*     */         addContextToChild(ctx, (Element)child);
/*     */       }
/*     */       child = getNextSibling(child);
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
/*     */   protected void removeContextFromChild(BridgeContext ctx, Element e) {
/*     */     if ("http://www.w3.org/2000/svg".equals(e.getNamespaceURI())) {
/*     */       String ln = e.getLocalName();
/*     */       if (ln.equals("flowDiv") || ln.equals("flowLine") || ln.equals("flowPara") || ln.equals("flowSpan")) {
/*     */         ((SVGTextElementBridge.AbstractTextChildBridgeUpdateHandler)((SVGOMElement)e).getSVGContext()).dispose();
/*     */       }
/*     */     } 
/*     */     Node child = getFirstChild(e);
/*     */     while (child != null) {
/*     */       if (child.getNodeType() == 1) {
/*     */         removeContextFromChild(ctx, (Element)child);
/*     */       }
/*     */       child = getNextSibling(child);
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
/*     */     if (element == null) {
/*     */       return null;
/*     */     }
/*     */     List rgns = getRegions(ctx, element);
/*     */     AttributedString ret = getFlowDiv(ctx, element);
/*     */     if (ret == null) {
/*     */       return ret;
/*     */     }
/*     */     ret.addAttribute(FLOW_REGIONS, rgns, 0, 1);
/*     */     TextLineBreaks.findLineBrk(ret);
/*     */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dumpACIWord(AttributedString as) {
/*     */     if (as == null) {
/*     */       return;
/*     */     }
/*     */     StringBuffer chars = new StringBuffer();
/*     */     StringBuffer brkStr = new StringBuffer();
/*     */     AttributedCharacterIterator aci = as.getIterator();
/*     */     AttributedCharacterIterator.Attribute WORD_LIMIT = TextLineBreaks.WORD_LIMIT;
/*     */     char ch = aci.current();
/*     */     for (; ch != Character.MAX_VALUE; ch = aci.next()) {
/*     */       chars.append(ch).append(' ').append(' ');
/*     */       int w = ((Integer)aci.getAttribute(WORD_LIMIT)).intValue();
/*     */       brkStr.append(w).append(' ');
/*     */       if (w < 10) {
/*     */         brkStr.append(' ');
/*     */       }
/*     */     } 
/*     */     System.out.println(chars.toString());
/*     */     System.out.println(brkStr.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Element getFlowDivElement(Element elem) {
/*     */     String eNS = elem.getNamespaceURI();
/*     */     if (!eNS.equals("http://www.w3.org/2000/svg")) {
/*     */       return null;
/*     */     }
/*     */     String nodeName = elem.getLocalName();
/*     */     if (nodeName.equals("flowDiv")) {
/*     */       return elem;
/*     */     }
/*     */     if (!nodeName.equals("flowRoot")) {
/*     */       return null;
/*     */     }
/*     */     Node n = getFirstChild(elem);
/*     */     for (; n != null; n = getNextSibling(n)) {
/*     */       if (n.getNodeType() == 1) {
/*     */         String nNS = n.getNamespaceURI();
/*     */         if ("http://www.w3.org/2000/svg".equals(nNS)) {
/*     */           Element e = (Element)n;
/*     */           String ln = e.getLocalName();
/*     */           if (ln.equals("flowDiv")) {
/*     */             return e;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributedString getFlowDiv(BridgeContext ctx, Element element) {
/*     */     Element flowDiv = getFlowDivElement(element);
/*     */     if (flowDiv == null) {
/*     */       return null;
/*     */     }
/*     */     return gatherFlowPara(ctx, flowDiv);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributedString gatherFlowPara(BridgeContext ctx, Element div) {
/*     */     TextPaintInfo divTPI = new TextPaintInfo();
/*     */     divTPI.visible = true;
/*     */     divTPI.fillPaint = Color.black;
/*     */     this.elemTPI.put(div, divTPI);
/*     */     SVGTextElementBridge.AttributedStringBuffer asb = new SVGTextElementBridge.AttributedStringBuffer();
/*     */     List<Integer> paraEnds = new ArrayList();
/*     */     List<Element> paraElems = new ArrayList();
/*     */     List lnLocs = new ArrayList();
/*     */     Node n = getFirstChild(div);
/*     */     for (; n != null; n = getNextSibling(n)) {
/*     */       if (n.getNodeType() == 1 && getNamespaceURI().equals(n.getNamespaceURI())) {
/*     */         Element e = (Element)n;
/*     */         String ln = e.getLocalName();
/*     */         if (ln.equals("flowPara")) {
/*     */           fillAttributedStringBuffer(ctx, e, true, (Integer)null, (Map)null, asb, lnLocs);
/*     */           paraElems.add(e);
/*     */           paraEnds.add(Integer.valueOf(asb.length()));
/*     */         } else if (ln.equals("flowRegionBreak")) {
/*     */           fillAttributedStringBuffer(ctx, e, true, (Integer)null, (Map)null, asb, lnLocs);
/*     */           paraElems.add(e);
/*     */           paraEnds.add(Integer.valueOf(asb.length()));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     divTPI.startChar = 0;
/*     */     divTPI.endChar = asb.length() - 1;
/*     */     AttributedString ret = asb.toAttributedString();
/*     */     if (ret == null) {
/*     */       return null;
/*     */     }
/*     */     int prevLN = 0;
/*     */     for (Object lnLoc : lnLocs) {
/*     */       int nextLN = ((Integer)lnLoc).intValue();
/*     */       if (nextLN == prevLN) {
/*     */         continue;
/*     */       }
/*     */       ret.addAttribute(FLOW_LINE_BREAK, new Object(), prevLN, nextLN);
/*     */       prevLN = nextLN;
/*     */     } 
/*     */     int start = 0;
/*     */     List<BlockInfo> emptyPara = null;
/*     */     for (int i = 0; i < paraElems.size(); i++, start = end) {
/*     */       Element elem = paraElems.get(i);
/*     */       int end = ((Integer)paraEnds.get(i)).intValue();
/*     */       if (start == end) {
/*     */         if (emptyPara == null) {
/*     */           emptyPara = new LinkedList();
/*     */         }
/*     */         emptyPara.add(makeBlockInfo(ctx, elem));
/*     */       } else {
/*     */         ret.addAttribute(FLOW_PARAGRAPH, makeBlockInfo(ctx, elem), start, end);
/*     */         if (emptyPara != null) {
/*     */           ret.addAttribute(FLOW_EMPTY_PARAGRAPH, emptyPara, start, end);
/*     */           emptyPara = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getRegions(BridgeContext ctx, Element element) {
/*     */     element = (Element)element.getParentNode();
/*     */     List ret = new LinkedList();
/*     */     Node n = getFirstChild(element);
/*     */     for (; n != null; n = getNextSibling(n)) {
/*     */       if (n.getNodeType() == 1 && "http://www.w3.org/2000/svg".equals(n.getNamespaceURI())) {
/*     */         Element e = (Element)n;
/*     */         String ln = e.getLocalName();
/*     */         if ("flowRegion".equals(ln)) {
/*     */           float verticalAlignment = 0.0F;
/*     */           gatherRegionInfo(ctx, e, verticalAlignment, ret);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void gatherRegionInfo(BridgeContext ctx, Element rgn, float verticalAlign, List<RegionInfo> regions) {
/*     */     boolean isStatic = !ctx.isDynamic();
/*     */     for (Node n = getFirstChild(rgn); n != null; n = getNextSibling(n)) {
/*     */       if (n.getNodeType() == 1) {
/*     */         GraphicsNode gn = isStatic ? (GraphicsNode)this.flowRegionNodes.get(n) : ctx.getGraphicsNode(n);
/*     */         Shape s = gn.getOutline();
/*     */         if (s != null) {
/*     */           AffineTransform at = gn.getTransform();
/*     */           if (at != null) {
/*     */             s = at.createTransformedShape(s);
/*     */           }
/*     */           regions.add(new RegionInfo(s, verticalAlign));
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
/*     */   protected void fillAttributedStringBuffer(BridgeContext ctx, Element element, boolean top, Integer bidiLevel, Map<?, ?> initialAttributes, SVGTextElementBridge.AttributedStringBuffer asb, List<Integer> lnLocs) {
/*     */     if (!SVGUtilities.matchUserAgent(element, ctx.getUserAgent()) || !CSSUtilities.convertDisplay(element)) {
/*     */       return;
/*     */     }
/*     */     String s = XMLSupport.getXMLSpace(element);
/*     */     boolean preserve = s.equals("preserve");
/*     */     Element nodeElement = element;
/*     */     int elementStartChar = asb.length();
/*     */     if (top) {
/*     */       this.endLimit = this.startLen = asb.length();
/*     */     }
/*     */     if (preserve) {
/*     */       this.endLimit = this.startLen;
/*     */     }
/*     */     Map map = (initialAttributes == null) ? new HashMap<Object, Object>() : new HashMap<Object, Object>(initialAttributes);
/*     */     initialAttributes = getAttributeMap(ctx, element, (TextPath)null, bidiLevel, map);
/*     */     Object o = map.get(TextAttribute.BIDI_EMBEDDING);
/*     */     Integer subBidiLevel = bidiLevel;
/*     */     if (o != null) {
/*     */       subBidiLevel = (Integer)o;
/*     */     }
/*     */     int lineBreak = -1;
/*     */     if (lnLocs.size() != 0) {
/*     */       lineBreak = ((Integer)lnLocs.get(lnLocs.size() - 1)).intValue();
/*     */     }
/*     */     Node n = getFirstChild(element);
/*     */     for (; n != null; n = getNextSibling(n)) {
/*     */       boolean prevEndsWithSpace;
/*     */       String ln;
/*     */       if (preserve) {
/*     */         prevEndsWithSpace = false;
/*     */       } else {
/*     */         int len = asb.length();
/*     */         if (len == this.startLen) {
/*     */           prevEndsWithSpace = true;
/*     */         } else {
/*     */           prevEndsWithSpace = (asb.getLastChar() == 32);
/*     */           int idx = lnLocs.size() - 1;
/*     */           if (!prevEndsWithSpace && idx >= 0) {
/*     */             Integer i = lnLocs.get(idx);
/*     */             if (i.intValue() == len) {
/*     */               prevEndsWithSpace = true;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       switch (n.getNodeType()) {
/*     */         case 1:
/*     */           if (!"http://www.w3.org/2000/svg".equals(n.getNamespaceURI())) {
/*     */             break;
/*     */           }
/*     */           nodeElement = (Element)n;
/*     */           ln = n.getLocalName();
/*     */           if (ln.equals("flowLine")) {
/*     */             int before = asb.length();
/*     */             fillAttributedStringBuffer(ctx, nodeElement, false, subBidiLevel, initialAttributes, asb, lnLocs);
/*     */             lineBreak = asb.length();
/*     */             lnLocs.add(Integer.valueOf(lineBreak));
/*     */             if (before != lineBreak) {
/*     */               initialAttributes = null;
/*     */             }
/*     */             break;
/*     */           } 
/*     */           if (ln.equals("flowSpan") || ln.equals("altGlyph")) {
/*     */             int before = asb.length();
/*     */             fillAttributedStringBuffer(ctx, nodeElement, false, subBidiLevel, initialAttributes, asb, lnLocs);
/*     */             if (asb.length() != before) {
/*     */               initialAttributes = null;
/*     */             }
/*     */             break;
/*     */           } 
/*     */           if (ln.equals("a")) {
/*     */             if (ctx.isInteractive()) {
/*     */               NodeEventTarget target = (NodeEventTarget)nodeElement;
/*     */               UserAgent ua = ctx.getUserAgent();
/*     */               SVGAElementBridge.CursorHolder ch = new SVGAElementBridge.CursorHolder(CursorManager.DEFAULT_CURSOR);
/*     */               target.addEventListenerNS("http://www.w3.org/2001/xml-events", "click", (EventListener)new SVGAElementBridge.AnchorListener(ua, ch), false, null);
/*     */               target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", (EventListener)new SVGAElementBridge.CursorMouseOverListener(ua, ch), false, null);
/*     */               target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", (EventListener)new SVGAElementBridge.CursorMouseOutListener(ua, ch), false, null);
/*     */             } 
/*     */             int before = asb.length();
/*     */             fillAttributedStringBuffer(ctx, nodeElement, false, subBidiLevel, initialAttributes, asb, lnLocs);
/*     */             if (asb.length() != before) {
/*     */               initialAttributes = null;
/*     */             }
/*     */             break;
/*     */           } 
/*     */           if (ln.equals("tref")) {
/*     */             String uriStr = XLinkSupport.getXLinkHref((Element)n);
/*     */             Element ref = ctx.getReferencedElement((Element)n, uriStr);
/*     */             s = TextUtilities.getElementContent(ref);
/*     */             s = normalizeString(s, preserve, prevEndsWithSpace);
/*     */             if (s.length() != 0) {
/*     */               int trefStart = asb.length();
/*     */               Map<Object, Object> m = new HashMap<Object, Object>();
/*     */               getAttributeMap(ctx, nodeElement, (TextPath)null, bidiLevel, m);
/*     */               asb.append(s, m);
/*     */               int trefEnd = asb.length() - 1;
/*     */               TextPaintInfo textPaintInfo = (TextPaintInfo)this.elemTPI.get(nodeElement);
/*     */               textPaintInfo.startChar = trefStart;
/*     */               textPaintInfo.endChar = trefEnd;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 3:
/*     */         case 4:
/*     */           s = n.getNodeValue();
/*     */           s = normalizeString(s, preserve, prevEndsWithSpace);
/*     */           if (s.length() != 0) {
/*     */             asb.append(s, map);
/*     */             if (preserve) {
/*     */               this.endLimit = asb.length();
/*     */             }
/*     */             initialAttributes = null;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/*     */     if (top) {
/*     */       boolean strippedSome = false;
/*     */       while (this.endLimit < asb.length() && asb.getLastChar() == 32) {
/*     */         int idx = lnLocs.size() - 1;
/*     */         int len = asb.length();
/*     */         if (idx >= 0) {
/*     */           Integer i = lnLocs.get(idx);
/*     */           if (i.intValue() >= len) {
/*     */             i = Integer.valueOf(len - 1);
/*     */             lnLocs.set(idx, i);
/*     */             idx--;
/*     */             while (idx >= 0) {
/*     */               i = lnLocs.get(idx);
/*     */               if (i.intValue() < len - 1) {
/*     */                 break;
/*     */               }
/*     */               lnLocs.remove(idx);
/*     */               idx--;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         asb.stripLast();
/*     */         strippedSome = true;
/*     */       } 
/*     */       if (strippedSome) {
/*     */         for (Object o1 : this.elemTPI.values()) {
/*     */           TextPaintInfo textPaintInfo = (TextPaintInfo)o1;
/*     */           if (textPaintInfo.endChar >= asb.length()) {
/*     */             textPaintInfo.endChar = asb.length() - 1;
/*     */             if (textPaintInfo.startChar > textPaintInfo.endChar) {
/*     */               textPaintInfo.startChar = textPaintInfo.endChar;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     int elementEndChar = asb.length() - 1;
/*     */     TextPaintInfo tpi = (TextPaintInfo)this.elemTPI.get(element);
/*     */     tpi.startChar = elementStartChar;
/*     */     tpi.endChar = elementEndChar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map getAttributeMap(BridgeContext ctx, Element element, TextPath textPath, Integer bidiLevel, Map<AttributedCharacterIterator.Attribute, Float> result) {
/*     */     Map inheritingMap = super.getAttributeMap(ctx, element, textPath, bidiLevel, result);
/*     */     float fontSize = TextUtilities.convertFontSize(element).floatValue();
/*     */     float lineHeight = getLineHeight(ctx, element, fontSize);
/*     */     result.put(LINE_HEIGHT, Float.valueOf(lineHeight));
/*     */     return inheritingMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkMap(Map attrs) {
/*     */     if (attrs.containsKey(TEXTPATH)) {
/*     */       return;
/*     */     }
/*     */     if (attrs.containsKey(ANCHOR_TYPE)) {
/*     */       return;
/*     */     }
/*     */     if (attrs.containsKey(LETTER_SPACING)) {
/*     */       return;
/*     */     }
/*     */     if (attrs.containsKey(WORD_SPACING)) {
/*     */       return;
/*     */     }
/*     */     if (attrs.containsKey(KERNING)) {
/*     */       return;
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
/*     */   public SVGFlowRootElementBridge() {
/* 863 */     this.marginTopIndex = -1;
/* 864 */     this.marginRightIndex = -1;
/* 865 */     this.marginBottomIndex = -1;
/* 866 */     this.marginLeftIndex = -1;
/* 867 */     this.indentIndex = -1;
/* 868 */     this.textAlignIndex = -1;
/* 869 */     this.lineHeightIndex = -1;
/*     */   }
/*     */   protected void initCSSPropertyIndexes(Element e) {
/* 872 */     CSSEngine eng = CSSUtilities.getCSSEngine(e);
/* 873 */     this.marginTopIndex = eng.getPropertyIndex("margin-top");
/* 874 */     this.marginRightIndex = eng.getPropertyIndex("margin-right");
/* 875 */     this.marginBottomIndex = eng.getPropertyIndex("margin-bottom");
/* 876 */     this.marginLeftIndex = eng.getPropertyIndex("margin-left");
/* 877 */     this.indentIndex = eng.getPropertyIndex("indent");
/* 878 */     this.textAlignIndex = eng.getPropertyIndex("text-align");
/* 879 */     this.lineHeightIndex = eng.getPropertyIndex("line-height");
/*     */   }
/*     */   public BlockInfo makeBlockInfo(BridgeContext ctx, Element element) {
/*     */     int textAlign;
/* 883 */     if (this.marginTopIndex == -1) initCSSPropertyIndexes(element);
/*     */ 
/*     */     
/* 886 */     Value v = CSSUtilities.getComputedStyle(element, this.marginTopIndex);
/* 887 */     float top = v.getFloatValue();
/*     */     
/* 889 */     v = CSSUtilities.getComputedStyle(element, this.marginRightIndex);
/* 890 */     float right = v.getFloatValue();
/*     */     
/* 892 */     v = CSSUtilities.getComputedStyle(element, this.marginBottomIndex);
/* 893 */     float bottom = v.getFloatValue();
/*     */     
/* 895 */     v = CSSUtilities.getComputedStyle(element, this.marginLeftIndex);
/* 896 */     float left = v.getFloatValue();
/*     */     
/* 898 */     v = CSSUtilities.getComputedStyle(element, this.indentIndex);
/* 899 */     float indent = v.getFloatValue();
/*     */     
/* 901 */     v = CSSUtilities.getComputedStyle(element, this.textAlignIndex);
/* 902 */     if (v == ValueConstants.INHERIT_VALUE) {
/* 903 */       v = CSSUtilities.getComputedStyle(element, 11);
/*     */       
/* 905 */       if (v == ValueConstants.LTR_VALUE) {
/* 906 */         v = SVG12ValueConstants.START_VALUE;
/*     */       } else {
/* 908 */         v = SVG12ValueConstants.END_VALUE;
/*     */       } 
/*     */     } 
/* 911 */     if (v == SVG12ValueConstants.START_VALUE) {
/* 912 */       textAlign = 0;
/* 913 */     } else if (v == SVG12ValueConstants.MIDDLE_VALUE) {
/* 914 */       textAlign = 1;
/* 915 */     } else if (v == SVG12ValueConstants.END_VALUE) {
/* 916 */       textAlign = 2;
/*     */     } else {
/* 918 */       textAlign = 3;
/*     */     } 
/* 920 */     Map<Object, Object> fontAttrs = new HashMap<Object, Object>(20);
/* 921 */     List fontList = getFontList(ctx, element, fontAttrs);
/* 922 */     Float fs = (Float)fontAttrs.get(TextAttribute.SIZE);
/* 923 */     float fontSize = fs.floatValue();
/* 924 */     float lineHeight = getLineHeight(ctx, element, fontSize);
/*     */     
/* 926 */     String ln = element.getLocalName();
/*     */     
/* 928 */     boolean rgnBr = ln.equals("flowRegionBreak");
/* 929 */     return new BlockInfo(top, right, bottom, left, indent, textAlign, lineHeight, fontList, fontAttrs, rgnBr);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getLineHeight(BridgeContext ctx, Element element, float fontSize) {
/* 935 */     if (this.lineHeightIndex == -1) initCSSPropertyIndexes(element);
/*     */     
/* 937 */     Value v = CSSUtilities.getComputedStyle(element, this.lineHeightIndex);
/* 938 */     if (v == ValueConstants.INHERIT_VALUE || v == SVG12ValueConstants.NORMAL_VALUE)
/*     */     {
/* 940 */       return fontSize * 1.1F;
/*     */     }
/*     */     
/* 943 */     float lineHeight = v.getFloatValue();
/* 944 */     if (v instanceof ComputedValue)
/* 945 */       v = ((ComputedValue)v).getComputedValue(); 
/* 946 */     if (v instanceof LineHeightValue && ((LineHeightValue)v).getFontSizeRelative())
/*     */     {
/* 948 */       lineHeight *= fontSize; } 
/* 949 */     return lineHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class FlowContentBridge
/*     */     extends SVGTextElementBridge.AbstractTextChildTextContent
/*     */   {
/*     */     public FlowContentBridge(BridgeContext ctx, SVGTextElementBridge parent, Element e) {
/* 963 */       super(SVGFlowRootElementBridge.this, ctx, parent, e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class RegionChangeListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 977 */       SVGFlowRootElementBridge.this.laidoutText = null;
/* 978 */       SVGFlowRootElementBridge.this.computeLaidoutText(SVGFlowRootElementBridge.this.ctx, SVGFlowRootElementBridge.this.e, (GraphicsNode)SVGFlowRootElementBridge.this.getTextNode());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVGFlowRootElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */