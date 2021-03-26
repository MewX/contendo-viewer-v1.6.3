/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeException;
/*     */ import org.apache.batik.bridge.CSSUtilities;
/*     */ import org.apache.batik.bridge.MultiResGraphicsNode;
/*     */ import org.apache.batik.bridge.SVGImageElementBridge;
/*     */ import org.apache.batik.bridge.SVGUtilities;
/*     */ import org.apache.batik.bridge.UnitProcessor;
/*     */ import org.apache.batik.bridge.Viewport;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.ImageNode;
/*     */ import org.apache.batik.parser.UnitProcessor;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGMultiImageElementBridge
/*     */   extends SVGImageElementBridge
/*     */ {
/*     */   public String getNamespaceURI() {
/*  84 */     return "http://www.w3.org/2000/svg";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  91 */     return "multiImage";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  98 */     return (Bridge)new SVGMultiImageElementBridge();
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
/*     */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/* 111 */     if (!SVGUtilities.matchUserAgent(e, ctx.getUserAgent())) {
/* 112 */       return null;
/*     */     }
/*     */     
/* 115 */     ImageNode imgNode = (ImageNode)instantiateGraphicsNode();
/* 116 */     if (imgNode == null) {
/* 117 */       return null;
/*     */     }
/*     */     
/* 120 */     associateSVGContext(ctx, e, (GraphicsNode)imgNode);
/*     */     
/* 122 */     Rectangle2D b = getImageBounds(ctx, e);
/*     */ 
/*     */     
/* 125 */     AffineTransform at = null;
/* 126 */     String s = e.getAttribute("transform");
/* 127 */     if (s.length() != 0) {
/* 128 */       at = SVGUtilities.convertTransform(e, "transform", s, ctx);
/*     */     } else {
/*     */       
/* 131 */       at = new AffineTransform();
/*     */     } 
/*     */     
/* 134 */     at.translate(b.getX(), b.getY());
/* 135 */     imgNode.setTransform(at);
/*     */ 
/*     */     
/* 138 */     imgNode.setVisible(CSSUtilities.convertVisibility(e));
/*     */ 
/*     */     
/* 141 */     Rectangle2D clip = new Rectangle2D.Double(0.0D, 0.0D, b.getWidth(), b.getHeight());
/* 142 */     Filter filter = imgNode.getGraphicsNodeRable(true);
/* 143 */     imgNode.setClip((ClipRable)new ClipRable8Bit(filter, clip));
/*     */ 
/*     */     
/* 146 */     Rectangle2D r = CSSUtilities.convertEnableBackground(e);
/* 147 */     if (r != null) {
/* 148 */       imgNode.setBackgroundEnable(r);
/*     */     }
/* 150 */     ctx.openViewport(e, new MultiImageElementViewport((float)b.getWidth(), (float)b.getHeight()));
/*     */ 
/*     */ 
/*     */     
/* 154 */     List elems = new LinkedList();
/* 155 */     List minDim = new LinkedList();
/* 156 */     List maxDim = new LinkedList();
/*     */     
/* 158 */     for (Node n = e.getFirstChild(); n != null; n = n.getNextSibling()) {
/* 159 */       if (n.getNodeType() == 1) {
/*     */ 
/*     */         
/* 162 */         Element se = (Element)n;
/* 163 */         if (getNamespaceURI().equals(se.getNamespaceURI())) {
/*     */           
/* 165 */           if (se.getLocalName().equals("subImage")) {
/* 166 */             addInfo(se, elems, minDim, maxDim, b);
/*     */           }
/* 168 */           if (se.getLocalName().equals("subImageRef"))
/* 169 */             addRefInfo(se, elems, minDim, maxDim, b); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 173 */     Dimension[] mindary = new Dimension[elems.size()];
/* 174 */     Dimension[] maxdary = new Dimension[elems.size()];
/* 175 */     Element[] elemary = new Element[elems.size()];
/* 176 */     Iterator<Dimension> mindi = minDim.iterator();
/* 177 */     Iterator<Dimension> maxdi = maxDim.iterator();
/* 178 */     Iterator<Element> ei = elems.iterator();
/* 179 */     int i = 0;
/* 180 */     while (mindi.hasNext()) {
/* 181 */       Dimension minD = mindi.next();
/* 182 */       Dimension maxD = maxdi.next();
/* 183 */       int k = 0;
/* 184 */       if (minD != null) {
/* 185 */         for (; k < i && (
/* 186 */           mindary[k] == null || minD.width >= (mindary[k]).width); k++);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       for (int j = i; j > k; j--) {
/* 193 */         elemary[j] = elemary[j - 1];
/* 194 */         mindary[j] = mindary[j - 1];
/* 195 */         maxdary[j] = maxdary[j - 1];
/*     */       } 
/*     */       
/* 198 */       elemary[k] = ei.next();
/* 199 */       mindary[k] = minD;
/* 200 */       maxdary[k] = maxD;
/* 201 */       i++;
/*     */     } 
/*     */     
/* 204 */     MultiResGraphicsNode multiResGraphicsNode = new MultiResGraphicsNode(e, clip, elemary, mindary, maxdary, ctx);
/*     */ 
/*     */     
/* 207 */     imgNode.setImage((GraphicsNode)multiResGraphicsNode);
/*     */     
/* 209 */     return (GraphicsNode)imgNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 216 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/* 222 */     initializeDynamicSupport(ctx, e, node);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     ctx.closeViewport(e);
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
/*     */   protected void initializeDynamicSupport(BridgeContext ctx, Element e, GraphicsNode node) {
/* 239 */     if (ctx.isInteractive()) {
/*     */       
/* 241 */       ImageNode imgNode = (ImageNode)node;
/* 242 */       ctx.bind(e, imgNode.getImage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 250 */     this.ctx.removeViewport(this.e);
/* 251 */     super.dispose();
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
/*     */   protected static Rectangle2D getImageBounds(BridgeContext ctx, Element element) {
/* 263 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, element);
/*     */ 
/*     */     
/* 266 */     String s = element.getAttributeNS((String)null, "x");
/* 267 */     float x = 0.0F;
/* 268 */     if (s.length() != 0) {
/* 269 */       x = UnitProcessor.svgHorizontalCoordinateToUserSpace(s, "x", uctx);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 274 */     s = element.getAttributeNS((String)null, "y");
/* 275 */     float y = 0.0F;
/* 276 */     if (s.length() != 0) {
/* 277 */       y = UnitProcessor.svgVerticalCoordinateToUserSpace(s, "y", uctx);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 282 */     s = element.getAttributeNS((String)null, "width");
/*     */     
/* 284 */     if (s.length() == 0) {
/* 285 */       throw new BridgeException(ctx, element, "attribute.missing", new Object[] { "width" });
/*     */     }
/*     */     
/* 288 */     float w = UnitProcessor.svgHorizontalLengthToUserSpace(s, "width", uctx);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     s = element.getAttributeNS((String)null, "height");
/*     */     
/* 295 */     if (s.length() == 0) {
/* 296 */       throw new BridgeException(ctx, element, "attribute.missing", new Object[] { "height" });
/*     */     }
/*     */     
/* 299 */     float h = UnitProcessor.svgVerticalLengthToUserSpace(s, "height", uctx);
/*     */ 
/*     */ 
/*     */     
/* 303 */     return new Rectangle2D.Float(x, y, w, h);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addInfo(Element e, Collection<Element> elems, Collection<Dimension> minDim, Collection<Dimension> maxDim, Rectangle2D bounds) {
/* 309 */     Document doc = e.getOwnerDocument();
/* 310 */     Element gElem = doc.createElementNS("http://www.w3.org/2000/svg", "g");
/*     */     
/* 312 */     NamedNodeMap attrs = e.getAttributes();
/* 313 */     int len = attrs.getLength();
/* 314 */     for (int i = 0; i < len; i++) {
/* 315 */       Attr attr = (Attr)attrs.item(i);
/* 316 */       gElem.setAttributeNS(attr.getNamespaceURI(), attr.getName(), attr.getValue());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 321 */     Node n = e.getFirstChild();
/* 322 */     for (; n != null; 
/* 323 */       n = e.getFirstChild()) {
/* 324 */       gElem.appendChild(n);
/*     */     }
/* 326 */     e.appendChild(gElem);
/* 327 */     elems.add(gElem);
/* 328 */     minDim.add(getElementMinPixel(e, bounds));
/* 329 */     maxDim.add(getElementMaxPixel(e, bounds));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addRefInfo(Element e, Collection<Element> elems, Collection<Dimension> minDim, Collection<Dimension> maxDim, Rectangle2D bounds) {
/*     */     ParsedURL purl;
/* 335 */     String uriStr = XLinkSupport.getXLinkHref(e);
/* 336 */     if (uriStr.length() == 0) {
/* 337 */       throw new BridgeException(this.ctx, e, "attribute.missing", new Object[] { "xlink:href" });
/*     */     }
/*     */     
/* 340 */     String baseURI = AbstractNode.getBaseURI(e);
/*     */     
/* 342 */     if (baseURI == null) { purl = new ParsedURL(uriStr); }
/* 343 */     else { purl = new ParsedURL(baseURI, uriStr); }
/* 344 */      Document doc = e.getOwnerDocument();
/* 345 */     Element imgElem = doc.createElementNS("http://www.w3.org/2000/svg", "image");
/*     */     
/* 347 */     imgElem.setAttributeNS("http://www.w3.org/1999/xlink", "href", purl.toString());
/*     */ 
/*     */     
/* 350 */     NamedNodeMap attrs = e.getAttributes();
/* 351 */     int len = attrs.getLength();
/* 352 */     for (int i = 0; i < len; i++) {
/* 353 */       Attr attr = (Attr)attrs.item(i);
/* 354 */       imgElem.setAttributeNS(attr.getNamespaceURI(), attr.getName(), attr.getValue());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 359 */     String s = e.getAttribute("x");
/* 360 */     if (s.length() == 0) imgElem.setAttribute("x", "0"); 
/* 361 */     s = e.getAttribute("y");
/* 362 */     if (s.length() == 0) imgElem.setAttribute("y", "0"); 
/* 363 */     s = e.getAttribute("width");
/* 364 */     if (s.length() == 0) imgElem.setAttribute("width", "100%"); 
/* 365 */     s = e.getAttribute("height");
/* 366 */     if (s.length() == 0) imgElem.setAttribute("height", "100%"); 
/* 367 */     e.appendChild(imgElem);
/* 368 */     elems.add(imgElem);
/*     */     
/* 370 */     minDim.add(getElementMinPixel(e, bounds));
/* 371 */     maxDim.add(getElementMaxPixel(e, bounds));
/*     */   }
/*     */   
/*     */   protected Dimension getElementMinPixel(Element e, Rectangle2D bounds) {
/* 375 */     return getElementPixelSize(e, "max-pixel-size", bounds);
/*     */   }
/*     */   
/*     */   protected Dimension getElementMaxPixel(Element e, Rectangle2D bounds) {
/* 379 */     return getElementPixelSize(e, "min-pixel-size", bounds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Dimension getElementPixelSize(Element e, String attr, Rectangle2D bounds) {
/* 387 */     String s = e.getAttribute(attr);
/* 388 */     if (s.length() == 0) return null;
/*     */     
/* 390 */     Float[] vals = SVGUtilities.convertSVGNumberOptionalNumber(e, attr, s, this.ctx);
/*     */ 
/*     */     
/* 393 */     if (vals[0] == null) return null;
/*     */     
/* 395 */     float xPixSz = vals[0].floatValue();
/* 396 */     float yPixSz = xPixSz;
/* 397 */     if (vals[1] != null) {
/* 398 */       yPixSz = vals[1].floatValue();
/*     */     }
/* 400 */     return new Dimension((int)(bounds.getWidth() / xPixSz + 0.5D), (int)(bounds.getHeight() / yPixSz + 0.5D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class MultiImageElementViewport
/*     */     implements Viewport
/*     */   {
/*     */     private float width;
/*     */ 
/*     */     
/*     */     private float height;
/*     */ 
/*     */ 
/*     */     
/*     */     public MultiImageElementViewport(float w, float h) {
/* 417 */       this.width = w;
/* 418 */       this.height = h;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getWidth() {
/* 425 */       return this.width;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getHeight() {
/* 432 */       return this.height;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVGMultiImageElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */