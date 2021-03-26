/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimatedRect;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.parser.AWTTransformProducer;
/*     */ import org.apache.batik.parser.FragmentIdentifierHandler;
/*     */ import org.apache.batik.parser.FragmentIdentifierParser;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PreserveAspectRatioHandler;
/*     */ import org.apache.batik.parser.PreserveAspectRatioParser;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGAnimatedRect;
/*     */ import org.w3c.dom.svg.SVGPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGRect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ViewBox
/*     */   implements ErrorConstants, SVGConstants
/*     */ {
/*     */   public static AffineTransform getViewTransform(String ref, Element e, float w, float h, BridgeContext ctx) {
/*     */     float[] vb;
/*     */     short align;
/*     */     boolean meet;
/*  75 */     if (ref == null || ref.length() == 0) {
/*  76 */       return getPreserveAspectRatioTransform(e, w, h, ctx);
/*     */     }
/*     */     
/*  79 */     ViewHandler vh = new ViewHandler();
/*  80 */     FragmentIdentifierParser p = new FragmentIdentifierParser();
/*  81 */     p.setFragmentIdentifierHandler(vh);
/*  82 */     p.parse(ref);
/*     */ 
/*     */     
/*  85 */     Element viewElement = e;
/*  86 */     if (vh.hasId) {
/*  87 */       Document document = e.getOwnerDocument();
/*  88 */       viewElement = document.getElementById(vh.id);
/*     */     } 
/*  90 */     if (viewElement == null) {
/*  91 */       throw new BridgeException(ctx, e, "uri.malformed", new Object[] { ref });
/*     */     }
/*     */ 
/*     */     
/*  95 */     Element ancestorSVG = getClosestAncestorSVGElement(e);
/*  96 */     if (!viewElement.getNamespaceURI().equals("http://www.w3.org/2000/svg") || !viewElement.getLocalName().equals("view"))
/*     */     {
/*  98 */       viewElement = ancestorSVG;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (vh.hasViewBox) {
/* 104 */       vb = vh.viewBox;
/*     */     } else {
/*     */       Element elt;
/* 107 */       if (DOMUtilities.isAttributeSpecifiedNS(viewElement, null, "viewBox")) {
/*     */         
/* 109 */         elt = viewElement;
/*     */       } else {
/* 111 */         elt = ancestorSVG;
/*     */       } 
/* 113 */       String viewBoxStr = elt.getAttributeNS((String)null, "viewBox");
/* 114 */       vb = parseViewBoxAttribute(elt, viewBoxStr, ctx);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     if (vh.hasPreserveAspectRatio) {
/* 121 */       align = vh.align;
/* 122 */       meet = vh.meet;
/*     */     } else {
/*     */       Element elt;
/* 125 */       if (DOMUtilities.isAttributeSpecifiedNS(viewElement, null, "preserveAspectRatio")) {
/*     */         
/* 127 */         elt = viewElement;
/*     */       } else {
/* 129 */         elt = ancestorSVG;
/*     */       } 
/* 131 */       String aspectRatio = elt.getAttributeNS((String)null, "preserveAspectRatio");
/*     */       
/* 133 */       PreserveAspectRatioParser pp = new PreserveAspectRatioParser();
/* 134 */       ViewHandler ph = new ViewHandler();
/* 135 */       pp.setPreserveAspectRatioHandler((PreserveAspectRatioHandler)ph);
/*     */       try {
/* 137 */         pp.parse(aspectRatio);
/* 138 */       } catch (ParseException pEx) {
/* 139 */         throw new BridgeException(ctx, elt, pEx, "attribute.malformed", new Object[] { "preserveAspectRatio", aspectRatio, pEx });
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 144 */       align = ph.align;
/* 145 */       meet = ph.meet;
/*     */     } 
/*     */ 
/*     */     
/* 149 */     AffineTransform transform = getPreserveAspectRatioTransform(vb, align, meet, w, h);
/*     */     
/* 151 */     if (vh.hasTransform) {
/* 152 */       transform.concatenate(vh.getAffineTransform());
/*     */     }
/* 154 */     return transform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Element getClosestAncestorSVGElement(Element e) {
/* 163 */     Node n = e;
/* 164 */     for (; n != null && n.getNodeType() == 1; 
/* 165 */       n = n.getParentNode()) {
/* 166 */       Element tmp = (Element)n;
/* 167 */       if (tmp.getNamespaceURI().equals("http://www.w3.org/2000/svg") && tmp.getLocalName().equals("svg"))
/*     */       {
/* 169 */         return tmp;
/*     */       }
/*     */     } 
/* 172 */     return null;
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
/*     */   public static AffineTransform getPreserveAspectRatioTransform(Element e, float w, float h) {
/* 189 */     return getPreserveAspectRatioTransform(e, w, h, null);
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
/*     */   public static AffineTransform getPreserveAspectRatioTransform(Element e, float w, float h, BridgeContext ctx) {
/* 203 */     String viewBox = e.getAttributeNS((String)null, "viewBox");
/*     */ 
/*     */     
/* 206 */     String aspectRatio = e.getAttributeNS((String)null, "preserveAspectRatio");
/*     */ 
/*     */     
/* 209 */     return getPreserveAspectRatioTransform(e, viewBox, aspectRatio, w, h, ctx);
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
/*     */   public static AffineTransform getPreserveAspectRatioTransform(Element e, String viewBox, String aspectRatio, float w, float h, BridgeContext ctx) {
/* 232 */     if (viewBox.length() == 0) {
/* 233 */       return new AffineTransform();
/*     */     }
/* 235 */     float[] vb = parseViewBoxAttribute(e, viewBox, ctx);
/*     */ 
/*     */     
/* 238 */     PreserveAspectRatioParser p = new PreserveAspectRatioParser();
/* 239 */     ViewHandler ph = new ViewHandler();
/* 240 */     p.setPreserveAspectRatioHandler((PreserveAspectRatioHandler)ph);
/*     */     try {
/* 242 */       p.parse(aspectRatio);
/* 243 */     } catch (ParseException pEx) {
/* 244 */       throw new BridgeException(ctx, e, pEx, "attribute.malformed", new Object[] { "preserveAspectRatio", aspectRatio, pEx });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     return getPreserveAspectRatioTransform(vb, ph.align, ph.meet, w, h);
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
/*     */   public static AffineTransform getPreserveAspectRatioTransform(Element e, float[] vb, float w, float h, BridgeContext ctx) {
/* 270 */     String aspectRatio = e.getAttributeNS((String)null, "preserveAspectRatio");
/*     */ 
/*     */ 
/*     */     
/* 274 */     PreserveAspectRatioParser p = new PreserveAspectRatioParser();
/* 275 */     ViewHandler ph = new ViewHandler();
/* 276 */     p.setPreserveAspectRatioHandler((PreserveAspectRatioHandler)ph);
/*     */     try {
/* 278 */       p.parse(aspectRatio);
/* 279 */     } catch (ParseException pEx) {
/* 280 */       throw new BridgeException(ctx, e, pEx, "attribute.malformed", new Object[] { "preserveAspectRatio", aspectRatio, pEx });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     return getPreserveAspectRatioTransform(vb, ph.align, ph.meet, w, h);
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
/*     */   public static AffineTransform getPreserveAspectRatioTransform(Element e, float[] vb, float w, float h, SVGAnimatedPreserveAspectRatio aPAR, BridgeContext ctx) {
/*     */     try {
/* 306 */       SVGPreserveAspectRatio pAR = aPAR.getAnimVal();
/* 307 */       short align = pAR.getAlign();
/* 308 */       boolean meet = (pAR.getMeetOrSlice() == 1);
/*     */       
/* 310 */       return getPreserveAspectRatioTransform(vb, align, meet, w, h);
/* 311 */     } catch (LiveAttributeException ex) {
/* 312 */       throw new BridgeException(ctx, ex);
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
/*     */   public static AffineTransform getPreserveAspectRatioTransform(Element e, SVGAnimatedRect aViewBox, SVGAnimatedPreserveAspectRatio aPAR, float w, float h, BridgeContext ctx) {
/* 332 */     if (!((SVGOMAnimatedRect)aViewBox).isSpecified())
/*     */     {
/* 334 */       return new AffineTransform();
/*     */     }
/* 336 */     SVGRect viewBox = aViewBox.getAnimVal();
/* 337 */     float[] vb = { viewBox.getX(), viewBox.getY(), viewBox.getWidth(), viewBox.getHeight() };
/*     */ 
/*     */     
/* 340 */     return getPreserveAspectRatioTransform(e, vb, w, h, aPAR, ctx);
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
/*     */   public static float[] parseViewBoxAttribute(Element e, String value, BridgeContext ctx) {
/* 353 */     if (value.length() == 0) {
/* 354 */       return null;
/*     */     }
/* 356 */     int i = 0;
/* 357 */     float[] vb = new float[4];
/* 358 */     StringTokenizer st = new StringTokenizer(value, " ,");
/*     */     try {
/* 360 */       while (i < 4 && st.hasMoreTokens()) {
/* 361 */         vb[i] = Float.parseFloat(st.nextToken());
/* 362 */         i++;
/*     */       } 
/* 364 */     } catch (NumberFormatException nfEx) {
/* 365 */       throw new BridgeException(ctx, e, nfEx, "attribute.malformed", new Object[] { "viewBox", value, nfEx });
/*     */     } 
/*     */ 
/*     */     
/* 369 */     if (i != 4) {
/* 370 */       throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { "viewBox", value });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 375 */     if (vb[2] < 0.0F || vb[3] < 0.0F) {
/* 376 */       throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { "viewBox", value });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 381 */     if (vb[2] == 0.0F || vb[3] == 0.0F) {
/* 382 */       return null;
/*     */     }
/* 384 */     return vb;
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
/*     */   public static AffineTransform getPreserveAspectRatioTransform(float[] vb, short align, boolean meet, float w, float h) {
/* 403 */     if (vb == null) {
/* 404 */       return new AffineTransform();
/*     */     }
/*     */     
/* 407 */     AffineTransform result = new AffineTransform();
/* 408 */     float vpar = vb[2] / vb[3];
/* 409 */     float svgar = w / h;
/*     */     
/* 411 */     if (align == 1)
/* 412 */     { result.scale((w / vb[2]), (h / vb[3]));
/* 413 */       result.translate(-vb[0], -vb[1]); }
/* 414 */     else if ((vpar < svgar && meet) || (vpar >= svgar && !meet))
/* 415 */     { float sf = h / vb[3];
/* 416 */       result.scale(sf, sf);
/* 417 */       switch (align)
/*     */       { case 2:
/*     */         case 5:
/*     */         case 8:
/* 421 */           result.translate(-vb[0], -vb[1]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 449 */           return result;case 3: case 6: case 9: result.translate((-vb[0] - (vb[2] - w * vb[3] / h) / 2.0F), -vb[1]); return result; }  result.translate((-vb[0] - vb[2] - w * vb[3] / h), -vb[1]); } else { float sf = w / vb[2]; result.scale(sf, sf); switch (align) { case 2: case 3: case 4: result.translate(-vb[0], -vb[1]); return result;case 5: case 6: case 7: result.translate(-vb[0], (-vb[1] - (vb[3] - h * vb[2] / w) / 2.0F)); return result; }  result.translate(-vb[0], (-vb[1] - vb[3] - h * vb[2] / w)); }  return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class ViewHandler
/*     */     extends AWTTransformProducer
/*     */     implements FragmentIdentifierHandler
/*     */   {
/*     */     public boolean hasTransform;
/*     */     
/*     */     public boolean hasId;
/*     */     
/*     */     public boolean hasViewBox;
/*     */     
/*     */     public boolean hasViewTargetParams;
/*     */     public boolean hasZoomAndPanParams;
/*     */     public String id;
/*     */     public float[] viewBox;
/*     */     public String viewTargetParams;
/*     */     public boolean isMagnify;
/*     */     public boolean hasPreserveAspectRatio;
/*     */     public short align;
/*     */     
/*     */     public void endTransformList() throws ParseException {
/* 473 */       super.endTransformList();
/* 474 */       this.hasTransform = true;
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
/*     */     public void startFragmentIdentifier() throws ParseException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void idReference(String s) throws ParseException {
/* 505 */       this.id = s;
/* 506 */       this.hasId = true;
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
/*     */     public void viewBox(float x, float y, float width, float height) throws ParseException {
/* 521 */       this.hasViewBox = true;
/* 522 */       this.viewBox = new float[4];
/* 523 */       this.viewBox[0] = x;
/* 524 */       this.viewBox[1] = y;
/* 525 */       this.viewBox[2] = width;
/* 526 */       this.viewBox[3] = height;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startViewTarget() throws ParseException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void viewTarget(String name) throws ParseException {
/* 544 */       this.viewTargetParams = name;
/* 545 */       this.hasViewTargetParams = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endViewTarget() throws ParseException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void zoomAndPan(boolean magnify) {
/* 562 */       this.isMagnify = magnify;
/* 563 */       this.hasZoomAndPanParams = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endFragmentIdentifier() throws ParseException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean meet = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startPreserveAspectRatio() throws ParseException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void none() throws ParseException {
/* 595 */       this.align = 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMaxYMax() throws ParseException {
/* 604 */       this.align = 10;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMaxYMid() throws ParseException {
/* 613 */       this.align = 7;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMaxYMin() throws ParseException {
/* 622 */       this.align = 4;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMidYMax() throws ParseException {
/* 631 */       this.align = 9;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMidYMid() throws ParseException {
/* 640 */       this.align = 6;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMidYMin() throws ParseException {
/* 649 */       this.align = 3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMinYMax() throws ParseException {
/* 658 */       this.align = 8;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMinYMid() throws ParseException {
/* 667 */       this.align = 5;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void xMinYMin() throws ParseException {
/* 676 */       this.align = 2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void meet() throws ParseException {
/* 685 */       this.meet = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void slice() throws ParseException {
/* 694 */       this.meet = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endPreserveAspectRatio() throws ParseException {
/* 703 */       this.hasPreserveAspectRatio = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/ViewBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */