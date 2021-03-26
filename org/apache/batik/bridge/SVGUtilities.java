/*      */ package org.apache.batik.bridge;
/*      */ 
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
/*      */ import org.apache.batik.css.engine.CSSEngine;
/*      */ import org.apache.batik.dom.AbstractNode;
/*      */ import org.apache.batik.dom.util.XLinkSupport;
/*      */ import org.apache.batik.dom.util.XMLSupport;
/*      */ import org.apache.batik.gvt.GraphicsNode;
/*      */ import org.apache.batik.parser.AWTTransformProducer;
/*      */ import org.apache.batik.parser.ClockHandler;
/*      */ import org.apache.batik.parser.ClockParser;
/*      */ import org.apache.batik.parser.ParseException;
/*      */ import org.apache.batik.parser.UnitProcessor;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.apache.batik.util.SVGConstants;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.svg.SVGDocument;
/*      */ import org.w3c.dom.svg.SVGElement;
/*      */ import org.w3c.dom.svg.SVGLangSpace;
/*      */ import org.w3c.dom.svg.SVGNumberList;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class SVGUtilities
/*      */   implements ErrorConstants, SVGConstants
/*      */ {
/*      */   public static final short USER_SPACE_ON_USE = 1;
/*      */   public static final short OBJECT_BOUNDING_BOX = 2;
/*      */   public static final short STROKE_WIDTH = 3;
/*      */   
/*      */   public static Element getParentElement(Element elt) {
/*   72 */     Node n = CSSEngine.getCSSParentNode(elt);
/*   73 */     while (n != null && n.getNodeType() != 1) {
/*   74 */       n = CSSEngine.getCSSParentNode(n);
/*      */     }
/*   76 */     return (Element)n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float[] convertSVGNumberList(SVGNumberList l) {
/*   84 */     int n = l.getNumberOfItems();
/*   85 */     if (n == 0) {
/*   86 */       return null;
/*      */     }
/*   88 */     float[] fl = new float[n];
/*   89 */     for (int i = 0; i < n; i++) {
/*   90 */       fl[i] = l.getItem(i).getValue();
/*      */     }
/*   92 */     return fl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float convertSVGNumber(String s) {
/*  100 */     return Float.parseFloat(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int convertSVGInteger(String s) {
/*  108 */     return Integer.parseInt(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float convertRatio(String v) {
/*  118 */     float d = 1.0F;
/*  119 */     if (v.endsWith("%")) {
/*  120 */       v = v.substring(0, v.length() - 1);
/*  121 */       d = 100.0F;
/*      */     } 
/*  123 */     float r = Float.parseFloat(v) / d;
/*  124 */     if (r < 0.0F) {
/*  125 */       r = 0.0F;
/*  126 */     } else if (r > 1.0F) {
/*  127 */       r = 1.0F;
/*      */     } 
/*  129 */     return r;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getDescription(SVGElement elt) {
/*  136 */     String result = "";
/*  137 */     boolean preserve = false;
/*  138 */     Node n = elt.getFirstChild();
/*  139 */     if (n != null && n.getNodeType() == 1) {
/*  140 */       String name = (n.getPrefix() == null) ? n.getNodeName() : n.getLocalName();
/*      */       
/*  142 */       if (name.equals("desc")) {
/*  143 */         preserve = ((SVGLangSpace)n).getXMLspace().equals("preserve");
/*      */         
/*  145 */         n = n.getFirstChild();
/*  146 */         for (; n != null; 
/*  147 */           n = n.getNextSibling()) {
/*  148 */           if (n.getNodeType() == 3) {
/*  149 */             result = result + n.getNodeValue();
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  154 */     return preserve ? XMLSupport.preserveXMLSpace(result) : XMLSupport.defaultXMLSpace(result);
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
/*      */   public static boolean matchUserAgent(Element elt, UserAgent ua) {
/*  166 */     if (elt.hasAttributeNS((String)null, "systemLanguage")) {
/*      */       
/*  168 */       String sl = elt.getAttributeNS((String)null, "systemLanguage");
/*      */       
/*  170 */       if (sl.length() == 0)
/*  171 */         return false; 
/*  172 */       StringTokenizer st = new StringTokenizer(sl, ", "); while (true) {
/*  173 */         if (st.hasMoreTokens()) {
/*  174 */           String s = st.nextToken();
/*  175 */           if (matchUserLanguage(s, ua.getLanguages()))
/*      */             break; 
/*      */           continue;
/*      */         } 
/*  179 */         return false;
/*      */       } 
/*  181 */     }  if (elt.hasAttributeNS((String)null, "requiredFeatures")) {
/*      */       
/*  183 */       String rf = elt.getAttributeNS((String)null, "requiredFeatures");
/*      */       
/*  185 */       if (rf.length() == 0)
/*  186 */         return false; 
/*  187 */       StringTokenizer st = new StringTokenizer(rf, " ");
/*  188 */       while (st.hasMoreTokens()) {
/*  189 */         String s = st.nextToken();
/*  190 */         if (!ua.hasFeature(s)) {
/*  191 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*  195 */     if (elt.hasAttributeNS((String)null, "requiredExtensions")) {
/*      */       
/*  197 */       String re = elt.getAttributeNS((String)null, "requiredExtensions");
/*      */       
/*  199 */       if (re.length() == 0)
/*  200 */         return false; 
/*  201 */       StringTokenizer st = new StringTokenizer(re, " ");
/*  202 */       while (st.hasMoreTokens()) {
/*  203 */         String s = st.nextToken();
/*  204 */         if (!ua.supportExtension(s)) {
/*  205 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*  209 */     return true;
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
/*      */   protected static boolean matchUserLanguage(String s, String userLanguages) {
/*  221 */     StringTokenizer st = new StringTokenizer(userLanguages, ", ");
/*  222 */     while (st.hasMoreTokens()) {
/*  223 */       String t = st.nextToken();
/*  224 */       if (s.startsWith(t)) {
/*  225 */         if (s.length() > t.length()) {
/*  226 */           return (s.charAt(t.length()) == '-');
/*      */         }
/*  228 */         return true;
/*      */       } 
/*      */     } 
/*  231 */     return false;
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
/*      */   public static String getChainableAttributeNS(Element element, String namespaceURI, String attrName, BridgeContext ctx) {
/*  250 */     DocumentLoader loader = ctx.getDocumentLoader();
/*  251 */     Element e = element;
/*  252 */     List<ParsedURL> refs = new LinkedList();
/*      */     while (true) {
/*  254 */       String v = e.getAttributeNS(namespaceURI, attrName);
/*  255 */       if (v.length() > 0) {
/*  256 */         return v;
/*      */       }
/*  258 */       String uriStr = XLinkSupport.getXLinkHref(e);
/*  259 */       if (uriStr.length() == 0) {
/*  260 */         return "";
/*      */       }
/*  262 */       String baseURI = ((AbstractNode)e).getBaseURI();
/*  263 */       ParsedURL purl = new ParsedURL(baseURI, uriStr);
/*      */       
/*  265 */       for (Object ref : refs) {
/*  266 */         if (purl.equals(ref)) {
/*  267 */           throw new BridgeException(ctx, e, "xlink.href.circularDependencies", new Object[] { uriStr });
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  273 */         SVGDocument svgDoc = (SVGDocument)e.getOwnerDocument();
/*  274 */         URIResolver resolver = ctx.createURIResolver(svgDoc, loader);
/*  275 */         e = resolver.getElement(purl.toString(), e);
/*  276 */         refs.add(purl);
/*  277 */       } catch (IOException ioEx) {
/*  278 */         throw new BridgeException(ctx, e, ioEx, "uri.io", new Object[] { uriStr });
/*      */       }
/*  280 */       catch (SecurityException secEx) {
/*  281 */         throw new BridgeException(ctx, e, secEx, "uri.unsecure", new Object[] { uriStr });
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
/*      */   public static Point2D convertPoint(String xStr, String xAttr, String yStr, String yAttr, short unitsType, UnitProcessor.Context uctx) {
/*      */     float x;
/*      */     float y;
/*  309 */     switch (unitsType) {
/*      */       case 2:
/*  311 */         x = UnitProcessor.svgHorizontalCoordinateToObjectBoundingBox(xStr, xAttr, uctx);
/*      */         
/*  313 */         y = UnitProcessor.svgVerticalCoordinateToObjectBoundingBox(yStr, yAttr, uctx);
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
/*  325 */         return new Point2D.Float(x, y);case 1: x = UnitProcessor.svgHorizontalCoordinateToUserSpace(xStr, xAttr, uctx); y = UnitProcessor.svgVerticalCoordinateToUserSpace(yStr, yAttr, uctx); return new Point2D.Float(x, y);
/*      */     } 
/*      */     throw new IllegalArgumentException("Invalid unit type");
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
/*      */   public static float convertLength(String length, String attr, short unitsType, UnitProcessor.Context uctx) {
/*  341 */     switch (unitsType) {
/*      */       case 2:
/*  343 */         return UnitProcessor.svgOtherLengthToObjectBoundingBox(length, attr, uctx);
/*      */       
/*      */       case 1:
/*  346 */         return UnitProcessor.svgOtherLengthToUserSpace(length, attr, uctx);
/*      */     } 
/*  348 */     throw new IllegalArgumentException("Invalid unit type");
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
/*      */   public static Rectangle2D convertMaskRegion(Element maskElement, Element maskedElement, GraphicsNode maskedNode, BridgeContext ctx) {
/*      */     short unitsType;
/*  371 */     String xStr = maskElement.getAttributeNS((String)null, "x");
/*  372 */     if (xStr.length() == 0) {
/*  373 */       xStr = "-10%";
/*      */     }
/*      */     
/*  376 */     String yStr = maskElement.getAttributeNS((String)null, "y");
/*  377 */     if (yStr.length() == 0) {
/*  378 */       yStr = "-10%";
/*      */     }
/*      */     
/*  381 */     String wStr = maskElement.getAttributeNS((String)null, "width");
/*  382 */     if (wStr.length() == 0) {
/*  383 */       wStr = "120%";
/*      */     }
/*      */     
/*  386 */     String hStr = maskElement.getAttributeNS((String)null, "height");
/*  387 */     if (hStr.length() == 0) {
/*  388 */       hStr = "120%";
/*      */     }
/*      */ 
/*      */     
/*  392 */     String units = maskElement.getAttributeNS((String)null, "maskUnits");
/*      */     
/*  394 */     if (units.length() == 0) {
/*  395 */       unitsType = 2;
/*      */     } else {
/*  397 */       unitsType = parseCoordinateSystem(maskElement, "maskUnits", units, ctx);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  402 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, maskedElement);
/*      */ 
/*      */     
/*  405 */     return convertRegion(xStr, yStr, wStr, hStr, unitsType, maskedNode, uctx);
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
/*      */   public static Rectangle2D convertPatternRegion(Element patternElement, Element paintedElement, GraphicsNode paintedNode, BridgeContext ctx) {
/*      */     short unitsType;
/*  433 */     String xStr = getChainableAttributeNS(patternElement, null, "x", ctx);
/*      */     
/*  435 */     if (xStr.length() == 0) {
/*  436 */       xStr = "0";
/*      */     }
/*      */     
/*  439 */     String yStr = getChainableAttributeNS(patternElement, null, "y", ctx);
/*      */     
/*  441 */     if (yStr.length() == 0) {
/*  442 */       yStr = "0";
/*      */     }
/*      */     
/*  445 */     String wStr = getChainableAttributeNS(patternElement, null, "width", ctx);
/*      */     
/*  447 */     if (wStr.length() == 0) {
/*  448 */       throw new BridgeException(ctx, patternElement, "attribute.missing", new Object[] { "width" });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  453 */     String hStr = getChainableAttributeNS(patternElement, null, "height", ctx);
/*      */     
/*  455 */     if (hStr.length() == 0) {
/*  456 */       throw new BridgeException(ctx, patternElement, "attribute.missing", new Object[] { "height" });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  462 */     String units = getChainableAttributeNS(patternElement, null, "patternUnits", ctx);
/*      */     
/*  464 */     if (units.length() == 0) {
/*  465 */       unitsType = 2;
/*      */     } else {
/*  467 */       unitsType = parseCoordinateSystem(patternElement, "patternUnits", units, ctx);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  472 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, paintedElement);
/*      */ 
/*      */     
/*  475 */     return convertRegion(xStr, yStr, wStr, hStr, unitsType, paintedNode, uctx);
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
/*      */   public static float[] convertFilterRes(Element filterElement, BridgeContext ctx) {
/*  498 */     float[] filterRes = new float[2];
/*  499 */     String s = getChainableAttributeNS(filterElement, null, "filterRes", ctx);
/*      */     
/*  501 */     Float[] vals = convertSVGNumberOptionalNumber(filterElement, "filterRes", s, ctx);
/*      */ 
/*      */     
/*  504 */     if (filterRes[0] < 0.0F || filterRes[1] < 0.0F) {
/*  505 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "filterRes", s });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  510 */     if (vals[0] == null) {
/*  511 */       filterRes[0] = -1.0F;
/*      */     } else {
/*  513 */       filterRes[0] = vals[0].floatValue();
/*  514 */       if (filterRes[0] < 0.0F) {
/*  515 */         throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "filterRes", s });
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  520 */     if (vals[1] == null) {
/*  521 */       filterRes[1] = filterRes[0];
/*      */     } else {
/*  523 */       filterRes[1] = vals[1].floatValue();
/*  524 */       if (filterRes[1] < 0.0F) {
/*  525 */         throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "filterRes", s });
/*      */       }
/*      */     } 
/*      */     
/*  529 */     return filterRes;
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
/*      */   public static Float[] convertSVGNumberOptionalNumber(Element elem, String attrName, String attrValue, BridgeContext ctx) {
/*  542 */     Float[] ret = new Float[2];
/*  543 */     if (attrValue.length() == 0) {
/*  544 */       return ret;
/*      */     }
/*      */     try {
/*  547 */       StringTokenizer tokens = new StringTokenizer(attrValue, " ");
/*  548 */       ret[0] = Float.valueOf(Float.parseFloat(tokens.nextToken()));
/*  549 */       if (tokens.hasMoreTokens()) {
/*  550 */         ret[1] = Float.valueOf(Float.parseFloat(tokens.nextToken()));
/*      */       }
/*      */       
/*  553 */       if (tokens.hasMoreTokens()) {
/*  554 */         throw new BridgeException(ctx, elem, "attribute.malformed", new Object[] { attrName, attrValue });
/*      */       
/*      */       }
/*      */     }
/*  558 */     catch (NumberFormatException nfEx) {
/*  559 */       throw new BridgeException(ctx, elem, nfEx, "attribute.malformed", new Object[] { attrName, attrValue, nfEx });
/*      */     } 
/*      */ 
/*      */     
/*  563 */     return ret;
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
/*      */   public static Rectangle2D convertFilterChainRegion(Element filterElement, Element filteredElement, GraphicsNode filteredNode, BridgeContext ctx) {
/*      */     short unitsType;
/*  583 */     String xStr = getChainableAttributeNS(filterElement, null, "x", ctx);
/*      */     
/*  585 */     if (xStr.length() == 0) {
/*  586 */       xStr = "-10%";
/*      */     }
/*      */     
/*  589 */     String yStr = getChainableAttributeNS(filterElement, null, "y", ctx);
/*      */     
/*  591 */     if (yStr.length() == 0) {
/*  592 */       yStr = "-10%";
/*      */     }
/*      */     
/*  595 */     String wStr = getChainableAttributeNS(filterElement, null, "width", ctx);
/*      */     
/*  597 */     if (wStr.length() == 0) {
/*  598 */       wStr = "120%";
/*      */     }
/*      */     
/*  601 */     String hStr = getChainableAttributeNS(filterElement, null, "height", ctx);
/*      */     
/*  603 */     if (hStr.length() == 0) {
/*  604 */       hStr = "120%";
/*      */     }
/*      */ 
/*      */     
/*  608 */     String units = getChainableAttributeNS(filterElement, null, "filterUnits", ctx);
/*      */     
/*  610 */     if (units.length() == 0) {
/*  611 */       unitsType = 2;
/*      */     } else {
/*  613 */       unitsType = parseCoordinateSystem(filterElement, "filterUnits", units, ctx);
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
/*  632 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, filteredElement);
/*      */ 
/*      */     
/*  635 */     Rectangle2D region = convertRegion(xStr, yStr, wStr, hStr, unitsType, filteredNode, uctx);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  645 */     units = getChainableAttributeNS(filterElement, null, "filterMarginsUnits", ctx);
/*      */ 
/*      */     
/*  648 */     if (units.length() == 0) {
/*      */       
/*  650 */       unitsType = 1;
/*      */     } else {
/*  652 */       unitsType = parseCoordinateSystem(filterElement, "filterMarginsUnits", units, ctx);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  658 */     String dxStr = filterElement.getAttributeNS((String)null, "mx");
/*      */     
/*  660 */     if (dxStr.length() == 0) {
/*  661 */       dxStr = "0";
/*      */     }
/*      */     
/*  664 */     String dyStr = filterElement.getAttributeNS((String)null, "my");
/*  665 */     if (dyStr.length() == 0) {
/*  666 */       dyStr = "0";
/*      */     }
/*      */     
/*  669 */     String dwStr = filterElement.getAttributeNS((String)null, "mw");
/*  670 */     if (dwStr.length() == 0) {
/*  671 */       dwStr = "0";
/*      */     }
/*      */     
/*  674 */     String dhStr = filterElement.getAttributeNS((String)null, "mh");
/*  675 */     if (dhStr.length() == 0) {
/*  676 */       dhStr = "0";
/*      */     }
/*      */     
/*  679 */     return extendRegion(dxStr, dyStr, dwStr, dhStr, unitsType, filteredNode, region, uctx);
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
/*      */   protected static Rectangle2D extendRegion(String dxStr, String dyStr, String dwStr, String dhStr, short unitsType, GraphicsNode filteredNode, Rectangle2D region, UnitProcessor.Context uctx) {
/*      */     float dx;
/*      */     float dy;
/*      */     float dw;
/*      */     float dh;
/*      */     Rectangle2D bounds;
/*  712 */     switch (unitsType) {
/*      */       case 1:
/*  714 */         dx = UnitProcessor.svgHorizontalCoordinateToUserSpace(dxStr, "mx", uctx);
/*      */         
/*  716 */         dy = UnitProcessor.svgVerticalCoordinateToUserSpace(dyStr, "my", uctx);
/*      */         
/*  718 */         dw = UnitProcessor.svgHorizontalCoordinateToUserSpace(dwStr, "mw", uctx);
/*      */         
/*  720 */         dh = UnitProcessor.svgVerticalCoordinateToUserSpace(dhStr, "mh", uctx);
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
/*  749 */         region.setRect(region.getX() + dx, region.getY() + dy, region.getWidth() + dw, region.getHeight() + dh);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  754 */         return region;case 2: bounds = filteredNode.getGeometryBounds(); if (bounds == null) { dx = dy = dw = dh = 0.0F; } else { dx = UnitProcessor.svgHorizontalCoordinateToObjectBoundingBox(dxStr, "mx", uctx); dx = (float)(dx * bounds.getWidth()); dy = UnitProcessor.svgVerticalCoordinateToObjectBoundingBox(dyStr, "my", uctx); dy = (float)(dy * bounds.getHeight()); dw = UnitProcessor.svgHorizontalCoordinateToObjectBoundingBox(dwStr, "mw", uctx); dw = (float)(dw * bounds.getWidth()); dh = UnitProcessor.svgVerticalCoordinateToObjectBoundingBox(dhStr, "mh", uctx); dh = (float)(dh * bounds.getHeight()); }  region.setRect(region.getX() + dx, region.getY() + dy, region.getWidth() + dw, region.getHeight() + dh); return region;
/*      */     } 
/*      */     throw new IllegalArgumentException("Invalid unit type");
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
/*      */   public static Rectangle2D getBaseFilterPrimitiveRegion(Element filterPrimitiveElement, Element filteredElement, GraphicsNode filteredNode, Rectangle2D defaultRegion, BridgeContext ctx) {
/*  769 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, filteredElement);
/*      */ 
/*      */     
/*  772 */     double x = defaultRegion.getX();
/*  773 */     String s = filterPrimitiveElement.getAttributeNS((String)null, "x");
/*  774 */     if (s.length() != 0) {
/*  775 */       x = UnitProcessor.svgHorizontalCoordinateToUserSpace(s, "x", uctx);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  780 */     double y = defaultRegion.getY();
/*  781 */     s = filterPrimitiveElement.getAttributeNS((String)null, "y");
/*  782 */     if (s.length() != 0) {
/*  783 */       y = UnitProcessor.svgVerticalCoordinateToUserSpace(s, "y", uctx);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  788 */     double w = defaultRegion.getWidth();
/*  789 */     s = filterPrimitiveElement.getAttributeNS((String)null, "width");
/*  790 */     if (s.length() != 0) {
/*  791 */       w = UnitProcessor.svgHorizontalLengthToUserSpace(s, "width", uctx);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  796 */     double h = defaultRegion.getHeight();
/*  797 */     s = filterPrimitiveElement.getAttributeNS((String)null, "height");
/*  798 */     if (s.length() != 0) {
/*  799 */       h = UnitProcessor.svgVerticalLengthToUserSpace(s, "height", uctx);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  806 */     return new Rectangle2D.Double(x, y, w, h);
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
/*      */   public static Rectangle2D convertFilterPrimitiveRegion(Element filterPrimitiveElement, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Rectangle2D defaultRegion, Rectangle2D filterRegion, BridgeContext ctx) {
/*      */     short unitsType;
/*      */     Rectangle2D bounds;
/*  832 */     String units = "";
/*  833 */     if (filterElement != null) {
/*  834 */       units = getChainableAttributeNS(filterElement, null, "primitiveUnits", ctx);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  840 */     if (units.length() == 0) {
/*  841 */       unitsType = 1;
/*      */     } else {
/*  843 */       unitsType = parseCoordinateSystem(filterElement, "filterUnits", units, ctx);
/*      */     } 
/*      */ 
/*      */     
/*  847 */     String xStr = "", yStr = "", wStr = "", hStr = "";
/*      */     
/*  849 */     if (filterPrimitiveElement != null) {
/*      */       
/*  851 */       xStr = filterPrimitiveElement.getAttributeNS((String)null, "x");
/*      */ 
/*      */ 
/*      */       
/*  855 */       yStr = filterPrimitiveElement.getAttributeNS((String)null, "y");
/*      */ 
/*      */ 
/*      */       
/*  859 */       wStr = filterPrimitiveElement.getAttributeNS((String)null, "width");
/*      */ 
/*      */ 
/*      */       
/*  863 */       hStr = filterPrimitiveElement.getAttributeNS((String)null, "height");
/*      */     } 
/*      */ 
/*      */     
/*  867 */     double x = defaultRegion.getX();
/*  868 */     double y = defaultRegion.getY();
/*  869 */     double w = defaultRegion.getWidth();
/*  870 */     double h = defaultRegion.getHeight();
/*      */ 
/*      */     
/*  873 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, filteredElement);
/*      */ 
/*      */     
/*  876 */     switch (unitsType) {
/*      */       case 2:
/*  878 */         bounds = filteredNode.getGeometryBounds();
/*  879 */         if (bounds != null) {
/*  880 */           if (xStr.length() != 0) {
/*  881 */             x = UnitProcessor.svgHorizontalCoordinateToObjectBoundingBox(xStr, "x", uctx);
/*      */             
/*  883 */             x = bounds.getX() + x * bounds.getWidth();
/*      */           } 
/*  885 */           if (yStr.length() != 0) {
/*  886 */             y = UnitProcessor.svgVerticalCoordinateToObjectBoundingBox(yStr, "y", uctx);
/*      */             
/*  888 */             y = bounds.getY() + y * bounds.getHeight();
/*      */           } 
/*  890 */           if (wStr.length() != 0) {
/*  891 */             w = UnitProcessor.svgHorizontalLengthToObjectBoundingBox(wStr, "width", uctx);
/*      */             
/*  893 */             w *= bounds.getWidth();
/*      */           } 
/*  895 */           if (hStr.length() != 0) {
/*  896 */             h = UnitProcessor.svgVerticalLengthToObjectBoundingBox(hStr, "height", uctx);
/*      */             
/*  898 */             h *= bounds.getHeight();
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 1:
/*  903 */         if (xStr.length() != 0) {
/*  904 */           x = UnitProcessor.svgHorizontalCoordinateToUserSpace(xStr, "x", uctx);
/*      */         }
/*      */         
/*  907 */         if (yStr.length() != 0) {
/*  908 */           y = UnitProcessor.svgVerticalCoordinateToUserSpace(yStr, "y", uctx);
/*      */         }
/*      */         
/*  911 */         if (wStr.length() != 0) {
/*  912 */           w = UnitProcessor.svgHorizontalLengthToUserSpace(wStr, "width", uctx);
/*      */         }
/*      */         
/*  915 */         if (hStr.length() != 0) {
/*  916 */           h = UnitProcessor.svgVerticalLengthToUserSpace(hStr, "height", uctx);
/*      */         }
/*      */         break;
/*      */       
/*      */       default:
/*  921 */         throw new RuntimeException("invalid unitsType:" + unitsType);
/*      */     } 
/*      */     
/*  924 */     Rectangle2D region = new Rectangle2D.Double(x, y, w, h);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  930 */     units = "";
/*  931 */     if (filterElement != null) {
/*  932 */       units = getChainableAttributeNS(filterElement, null, "filterPrimitiveMarginsUnits", ctx);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  938 */     if (units.length() == 0) {
/*  939 */       unitsType = 1;
/*      */     } else {
/*  941 */       unitsType = parseCoordinateSystem(filterElement, "filterPrimitiveMarginsUnits", units, ctx);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  947 */     String dxStr = "", dyStr = "", dwStr = "", dhStr = "";
/*      */     
/*  949 */     if (filterPrimitiveElement != null) {
/*      */       
/*  951 */       dxStr = filterPrimitiveElement.getAttributeNS((String)null, "mx");
/*      */ 
/*      */ 
/*      */       
/*  955 */       dyStr = filterPrimitiveElement.getAttributeNS((String)null, "my");
/*      */ 
/*      */ 
/*      */       
/*  959 */       dwStr = filterPrimitiveElement.getAttributeNS((String)null, "mw");
/*      */ 
/*      */ 
/*      */       
/*  963 */       dhStr = filterPrimitiveElement.getAttributeNS((String)null, "mh");
/*      */     } 
/*      */     
/*  966 */     if (dxStr.length() == 0) {
/*  967 */       dxStr = "0";
/*      */     }
/*  969 */     if (dyStr.length() == 0) {
/*  970 */       dyStr = "0";
/*      */     }
/*  972 */     if (dwStr.length() == 0) {
/*  973 */       dwStr = "0";
/*      */     }
/*  975 */     if (dhStr.length() == 0) {
/*  976 */       dhStr = "0";
/*      */     }
/*      */     
/*  979 */     region = extendRegion(dxStr, dyStr, dwStr, dhStr, unitsType, filteredNode, region, uctx);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  988 */     Rectangle2D.intersect(region, filterRegion, region);
/*      */     
/*  990 */     return region;
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
/*      */   public static Rectangle2D convertFilterPrimitiveRegion(Element filterPrimitiveElement, Element filteredElement, GraphicsNode filteredNode, Rectangle2D defaultRegion, Rectangle2D filterRegion, BridgeContext ctx) {
/* 1013 */     Node parentNode = filterPrimitiveElement.getParentNode();
/* 1014 */     Element filterElement = null;
/* 1015 */     if (parentNode != null && parentNode.getNodeType() == 1)
/*      */     {
/* 1017 */       filterElement = (Element)parentNode;
/*      */     }
/* 1019 */     return convertFilterPrimitiveRegion(filterPrimitiveElement, filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short parseCoordinateSystem(Element e, String attr, String coordinateSystem, BridgeContext ctx) {
/* 1055 */     if ("userSpaceOnUse".equals(coordinateSystem))
/* 1056 */       return 1; 
/* 1057 */     if ("objectBoundingBox".equals(coordinateSystem)) {
/* 1058 */       return 2;
/*      */     }
/* 1060 */     throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { attr, coordinateSystem });
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
/*      */   public static short parseMarkerCoordinateSystem(Element e, String attr, String coordinateSystem, BridgeContext ctx) {
/* 1079 */     if ("userSpaceOnUse".equals(coordinateSystem))
/* 1080 */       return 1; 
/* 1081 */     if ("strokeWidth".equals(coordinateSystem)) {
/* 1082 */       return 3;
/*      */     }
/* 1084 */     throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { attr, coordinateSystem });
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
/*      */   protected static Rectangle2D convertRegion(String xStr, String yStr, String wStr, String hStr, short unitsType, GraphicsNode targetNode, UnitProcessor.Context uctx) {
/*      */     double x;
/*      */     double y;
/*      */     double w;
/*      */     double h;
/*      */     Rectangle2D bounds;
/* 1110 */     switch (unitsType) {
/*      */       case 2:
/* 1112 */         x = UnitProcessor.svgHorizontalCoordinateToObjectBoundingBox(xStr, "x", uctx);
/*      */         
/* 1114 */         y = UnitProcessor.svgVerticalCoordinateToObjectBoundingBox(yStr, "y", uctx);
/*      */         
/* 1116 */         w = UnitProcessor.svgHorizontalLengthToObjectBoundingBox(wStr, "width", uctx);
/*      */         
/* 1118 */         h = UnitProcessor.svgVerticalLengthToObjectBoundingBox(hStr, "height", uctx);
/*      */ 
/*      */         
/* 1121 */         bounds = targetNode.getGeometryBounds();
/* 1122 */         if (bounds != null) {
/* 1123 */           x = bounds.getX() + x * bounds.getWidth();
/* 1124 */           y = bounds.getY() + y * bounds.getHeight();
/* 1125 */           w *= bounds.getWidth();
/* 1126 */           h *= bounds.getHeight();
/*      */         } else {
/* 1128 */           x = y = w = h = 0.0D;
/*      */         } 
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
/* 1144 */         return new Rectangle2D.Double(x, y, w, h);case 1: x = UnitProcessor.svgHorizontalCoordinateToUserSpace(xStr, "x", uctx); y = UnitProcessor.svgVerticalCoordinateToUserSpace(yStr, "y", uctx); w = UnitProcessor.svgHorizontalLengthToUserSpace(wStr, "width", uctx); h = UnitProcessor.svgVerticalLengthToUserSpace(hStr, "height", uctx); return new Rectangle2D.Double(x, y, w, h);
/*      */     } 
/*      */     throw new RuntimeException("invalid unitsType:" + unitsType);
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
/*      */   public static AffineTransform convertTransform(Element e, String attr, String transform, BridgeContext ctx) {
/*      */     try {
/* 1164 */       return AWTTransformProducer.createAffineTransform(transform);
/* 1165 */     } catch (ParseException pEx) {
/* 1166 */       throw new BridgeException(ctx, e, pEx, "attribute.malformed", new Object[] { attr, transform, pEx });
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
/*      */   public static AffineTransform toObjectBBox(AffineTransform Tx, GraphicsNode node) {
/* 1182 */     AffineTransform Mx = new AffineTransform();
/* 1183 */     Rectangle2D bounds = node.getGeometryBounds();
/* 1184 */     if (bounds != null) {
/* 1185 */       Mx.translate(bounds.getX(), bounds.getY());
/* 1186 */       Mx.scale(bounds.getWidth(), bounds.getHeight());
/*      */     } 
/* 1188 */     Mx.concatenate(Tx);
/* 1189 */     return Mx;
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
/*      */   public static Rectangle2D toObjectBBox(Rectangle2D r, GraphicsNode node) {
/* 1203 */     Rectangle2D bounds = node.getGeometryBounds();
/* 1204 */     if (bounds != null) {
/* 1205 */       return new Rectangle2D.Double(bounds.getX() + r.getX() * bounds.getWidth(), bounds.getY() + r.getY() * bounds.getHeight(), r.getWidth() * bounds.getWidth(), r.getHeight() * bounds.getHeight());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1211 */     return new Rectangle2D.Double();
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
/*      */   public static float convertSnapshotTime(Element e, BridgeContext ctx) {
/* 1224 */     if (!e.hasAttributeNS((String)null, "snapshotTime")) {
/* 1225 */       return 0.0F;
/*      */     }
/* 1227 */     String t = e.getAttributeNS((String)null, "snapshotTime");
/* 1228 */     if (t.equals("none"))
/* 1229 */       return 0.0F; 
/*      */     class Handler
/*      */       implements ClockHandler {
/*      */       float time;
/*      */       
/*      */       public void clockValue(float t) {
/* 1235 */         this.time = t;
/*      */       }
/*      */     };
/* 1238 */     ClockParser p = new ClockParser(false);
/* 1239 */     Handler h = new Handler();
/* 1240 */     p.setClockHandler(h);
/*      */     try {
/* 1242 */       p.parse(t);
/* 1243 */     } catch (ParseException pEx) {
/* 1244 */       throw new BridgeException(null, e, pEx, "attribute.malformed", new Object[] { "snapshotTime", t, pEx });
/*      */     } 
/*      */ 
/*      */     
/* 1248 */     return h.time;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */