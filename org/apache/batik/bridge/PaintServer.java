/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.io.IOException;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.svg.ICCColor;
/*     */ import org.apache.batik.css.engine.value.svg12.CIELabColor;
/*     */ import org.apache.batik.css.engine.value.svg12.DeviceColor;
/*     */ import org.apache.batik.css.engine.value.svg12.ICCNamedColor;
/*     */ import org.apache.batik.gvt.CompositeShapePainter;
/*     */ import org.apache.batik.gvt.FillShapePainter;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.Marker;
/*     */ import org.apache.batik.gvt.MarkerShapePainter;
/*     */ import org.apache.batik.gvt.ShapeNode;
/*     */ import org.apache.batik.gvt.ShapePainter;
/*     */ import org.apache.batik.gvt.StrokeShapePainter;
/*     */ import org.apache.batik.util.CSSConstants;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.apache.xmlgraphics.java2d.color.CIELabColorSpace;
/*     */ import org.apache.xmlgraphics.java2d.color.ColorSpaces;
/*     */ import org.apache.xmlgraphics.java2d.color.ColorWithAlternatives;
/*     */ import org.apache.xmlgraphics.java2d.color.DeviceCMYKColorSpace;
/*     */ import org.apache.xmlgraphics.java2d.color.ICCColorSpaceWithIntent;
/*     */ import org.apache.xmlgraphics.java2d.color.NamedColorSpace;
/*     */ import org.apache.xmlgraphics.java2d.color.profile.NamedColorProfile;
/*     */ import org.apache.xmlgraphics.java2d.color.profile.NamedColorProfileParser;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PaintServer
/*     */   implements ErrorConstants, CSSConstants, SVGConstants
/*     */ {
/*     */   public static ShapePainter convertMarkers(Element e, ShapeNode node, BridgeContext ctx) {
/*  93 */     Value v = CSSUtilities.getComputedStyle(e, 36);
/*  94 */     Marker startMarker = convertMarker(e, v, ctx);
/*  95 */     v = CSSUtilities.getComputedStyle(e, 35);
/*  96 */     Marker midMarker = convertMarker(e, v, ctx);
/*  97 */     v = CSSUtilities.getComputedStyle(e, 34);
/*  98 */     Marker endMarker = convertMarker(e, v, ctx);
/*     */     
/* 100 */     if (startMarker != null || midMarker != null || endMarker != null) {
/* 101 */       MarkerShapePainter p = new MarkerShapePainter(node.getShape());
/* 102 */       p.setStartMarker(startMarker);
/* 103 */       p.setMiddleMarker(midMarker);
/* 104 */       p.setEndMarker(endMarker);
/* 105 */       return (ShapePainter)p;
/*     */     } 
/* 107 */     return null;
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
/*     */   public static Marker convertMarker(Element e, Value v, BridgeContext ctx) {
/* 127 */     if (v.getPrimitiveType() == 21) {
/* 128 */       return null;
/*     */     }
/* 130 */     String uri = v.getStringValue();
/* 131 */     Element markerElement = ctx.getReferencedElement(e, uri);
/* 132 */     Bridge bridge = ctx.getBridge(markerElement);
/* 133 */     if (bridge == null || !(bridge instanceof MarkerBridge)) {
/* 134 */       throw new BridgeException(ctx, e, "css.uri.badTarget", new Object[] { uri });
/*     */     }
/*     */     
/* 137 */     return ((MarkerBridge)bridge).createMarker(ctx, markerElement, e);
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
/*     */   public static ShapePainter convertFillAndStroke(Element e, ShapeNode node, BridgeContext ctx) {
/* 157 */     Shape shape = node.getShape();
/* 158 */     if (shape == null) return null;
/*     */     
/* 160 */     Paint fillPaint = convertFillPaint(e, (GraphicsNode)node, ctx);
/* 161 */     FillShapePainter fp = new FillShapePainter(shape);
/* 162 */     fp.setPaint(fillPaint);
/*     */     
/* 164 */     Stroke stroke = convertStroke(e);
/* 165 */     if (stroke == null) {
/* 166 */       return (ShapePainter)fp;
/*     */     }
/* 168 */     Paint strokePaint = convertStrokePaint(e, (GraphicsNode)node, ctx);
/* 169 */     StrokeShapePainter sp = new StrokeShapePainter(shape);
/* 170 */     sp.setStroke(stroke);
/* 171 */     sp.setPaint(strokePaint);
/*     */     
/* 173 */     CompositeShapePainter cp = new CompositeShapePainter(shape);
/* 174 */     cp.addShapePainter((ShapePainter)fp);
/* 175 */     cp.addShapePainter((ShapePainter)sp);
/* 176 */     return (ShapePainter)cp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShapePainter convertStrokePainter(Element e, ShapeNode node, BridgeContext ctx) {
/* 183 */     Shape shape = node.getShape();
/* 184 */     if (shape == null) return null;
/*     */     
/* 186 */     Stroke stroke = convertStroke(e);
/* 187 */     if (stroke == null) {
/* 188 */       return null;
/*     */     }
/* 190 */     Paint strokePaint = convertStrokePaint(e, (GraphicsNode)node, ctx);
/* 191 */     StrokeShapePainter sp = new StrokeShapePainter(shape);
/* 192 */     sp.setStroke(stroke);
/* 193 */     sp.setPaint(strokePaint);
/* 194 */     return (ShapePainter)sp;
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
/*     */   public static Paint convertStrokePaint(Element strokedElement, GraphicsNode strokedNode, BridgeContext ctx) {
/* 212 */     Value v = CSSUtilities.getComputedStyle(strokedElement, 51);
/*     */     
/* 214 */     float opacity = convertOpacity(v);
/* 215 */     v = CSSUtilities.getComputedStyle(strokedElement, 45);
/*     */ 
/*     */     
/* 218 */     return convertPaint(strokedElement, strokedNode, v, opacity, ctx);
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
/*     */   public static Paint convertFillPaint(Element filledElement, GraphicsNode filledNode, BridgeContext ctx) {
/* 236 */     Value v = CSSUtilities.getComputedStyle(filledElement, 16);
/*     */     
/* 238 */     float opacity = convertOpacity(v);
/* 239 */     v = CSSUtilities.getComputedStyle(filledElement, 15);
/*     */ 
/*     */     
/* 242 */     return convertPaint(filledElement, filledNode, v, opacity, ctx);
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
/*     */   public static Paint convertPaint(Element paintedElement, GraphicsNode paintedNode, Value paintDef, float opacity, BridgeContext ctx) {
/*     */     Paint result;
/* 264 */     if (paintDef.getCssValueType() == 1) {
/* 265 */       switch (paintDef.getPrimitiveType()) {
/*     */         case 21:
/* 267 */           return null;
/*     */         
/*     */         case 25:
/* 270 */           return convertColor(paintDef, opacity);
/*     */         
/*     */         case 20:
/* 273 */           return convertURIPaint(paintedElement, paintedNode, paintDef, opacity, ctx);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 280 */       throw new IllegalArgumentException("Paint argument is not an appropriate CSS value");
/*     */     } 
/*     */ 
/*     */     
/* 284 */     Value v = paintDef.item(0);
/* 285 */     switch (v.getPrimitiveType()) {
/*     */       case 25:
/* 287 */         return convertRGBICCColor(paintedElement, v, paintDef.item(1), opacity, ctx);
/*     */ 
/*     */ 
/*     */       
/*     */       case 20:
/* 292 */         result = silentConvertURIPaint(paintedElement, paintedNode, v, opacity, ctx);
/*     */ 
/*     */         
/* 295 */         if (result != null) return result;
/*     */         
/* 297 */         v = paintDef.item(1);
/* 298 */         switch (v.getPrimitiveType()) {
/*     */           case 21:
/* 300 */             return null;
/*     */           
/*     */           case 25:
/* 303 */             if (paintDef.getLength() == 2) {
/* 304 */               return convertColor(v, opacity);
/*     */             }
/* 306 */             return convertRGBICCColor(paintedElement, v, paintDef.item(2), opacity, ctx);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 311 */         throw new IllegalArgumentException("Paint argument is not an appropriate CSS value");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     throw new IllegalArgumentException("Paint argument is not an appropriate CSS value");
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
/*     */   public static Paint silentConvertURIPaint(Element paintedElement, GraphicsNode paintedNode, Value paintDef, float opacity, BridgeContext ctx) {
/* 340 */     Paint paint = null;
/*     */     try {
/* 342 */       paint = convertURIPaint(paintedElement, paintedNode, paintDef, opacity, ctx);
/*     */     }
/* 344 */     catch (BridgeException bridgeException) {}
/*     */     
/* 346 */     return paint;
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
/*     */   public static Paint convertURIPaint(Element paintedElement, GraphicsNode paintedNode, Value paintDef, float opacity, BridgeContext ctx) {
/* 364 */     String uri = paintDef.getStringValue();
/* 365 */     Element paintElement = ctx.getReferencedElement(paintedElement, uri);
/*     */     
/* 367 */     Bridge bridge = ctx.getBridge(paintElement);
/* 368 */     if (bridge == null || !(bridge instanceof PaintBridge)) {
/* 369 */       throw new BridgeException(ctx, paintedElement, "css.uri.badTarget", new Object[] { uri });
/*     */     }
/*     */ 
/*     */     
/* 373 */     return ((PaintBridge)bridge).createPaint(ctx, paintElement, paintedElement, paintedNode, opacity);
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
/*     */   public static Color convertRGBICCColor(Element paintedElement, Value colorDef, Value iccColor, float opacity, BridgeContext ctx) {
/* 396 */     Color color = null;
/* 397 */     if (iccColor != null) {
/* 398 */       if (iccColor instanceof ICCColor) {
/* 399 */         color = convertICCColor(paintedElement, (ICCColor)iccColor, opacity, ctx);
/* 400 */       } else if (iccColor instanceof ICCNamedColor) {
/* 401 */         color = convertICCNamedColor(paintedElement, (ICCNamedColor)iccColor, opacity, ctx);
/* 402 */       } else if (iccColor instanceof CIELabColor) {
/* 403 */         color = convertCIELabColor(paintedElement, (CIELabColor)iccColor, opacity, ctx);
/* 404 */       } else if (iccColor instanceof DeviceColor) {
/* 405 */         color = convertDeviceColor(paintedElement, colorDef, (DeviceColor)iccColor, opacity, ctx);
/*     */       } 
/*     */     }
/* 408 */     if (color == null) {
/* 409 */       color = convertColor(colorDef, opacity);
/*     */     }
/* 411 */     return color;
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
/*     */   public static Color convertICCColor(Element e, ICCColor c, float opacity, BridgeContext ctx) {
/* 429 */     String iccProfileName = c.getColorProfile();
/* 430 */     if (iccProfileName == null) {
/* 431 */       return null;
/*     */     }
/*     */     
/* 434 */     SVGColorProfileElementBridge profileBridge = (SVGColorProfileElementBridge)ctx.getBridge("http://www.w3.org/2000/svg", "color-profile");
/*     */ 
/*     */     
/* 437 */     if (profileBridge == null) {
/* 438 */       return null;
/*     */     }
/*     */     
/* 441 */     ICCColorSpaceWithIntent profileCS = profileBridge.createICCColorSpaceWithIntent(ctx, e, iccProfileName);
/*     */     
/* 443 */     if (profileCS == null) {
/* 444 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 448 */     int n = c.getNumberOfColors();
/* 449 */     float[] colorValue = new float[n];
/* 450 */     if (n == 0) {
/* 451 */       return null;
/*     */     }
/* 453 */     for (int i = 0; i < n; i++) {
/* 454 */       colorValue[i] = c.getColor(i);
/*     */     }
/*     */ 
/*     */     
/* 458 */     float[] rgb = profileCS.intendedToRGB(colorValue);
/*     */     
/* 460 */     return new Color(rgb[0], rgb[1], rgb[2], opacity);
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
/*     */   public static Color convertICCNamedColor(Element e, ICCNamedColor c, float opacity, BridgeContext ctx) {
/* 478 */     String iccProfileName = c.getColorProfile();
/* 479 */     if (iccProfileName == null) {
/* 480 */       return null;
/*     */     }
/*     */     
/* 483 */     SVGColorProfileElementBridge profileBridge = (SVGColorProfileElementBridge)ctx.getBridge("http://www.w3.org/2000/svg", "color-profile");
/*     */ 
/*     */     
/* 486 */     if (profileBridge == null) {
/* 487 */       return null;
/*     */     }
/*     */     
/* 490 */     ICCColorSpaceWithIntent profileCS = profileBridge.createICCColorSpaceWithIntent(ctx, e, iccProfileName);
/*     */     
/* 492 */     if (profileCS == null) {
/* 493 */       return null;
/*     */     }
/* 495 */     ICC_Profile iccProfile = profileCS.getProfile();
/*     */     
/* 497 */     String iccProfileSrc = null;
/*     */     
/* 499 */     if (NamedColorProfileParser.isNamedColorProfile(iccProfile)) {
/* 500 */       NamedColorProfile ncp; NamedColorProfileParser parser = new NamedColorProfileParser();
/*     */       
/*     */       try {
/* 503 */         ncp = parser.parseProfile(iccProfile, iccProfileName, iccProfileSrc);
/* 504 */       } catch (IOException ioe) {
/* 505 */         return null;
/*     */       } 
/* 507 */       NamedColorSpace ncs = ncp.getNamedColor(c.getColorName());
/* 508 */       if (ncs != null) {
/* 509 */         return (Color)new ColorWithAlternatives((ColorSpace)ncs, new float[] { 1.0F }, opacity, null);
/*     */       }
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
/* 522 */     return null;
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
/*     */   public static Color convertCIELabColor(Element e, CIELabColor c, float opacity, BridgeContext ctx) {
/* 538 */     CIELabColorSpace cs = new CIELabColorSpace(c.getWhitePoint());
/* 539 */     float[] lab = c.getColorValues();
/* 540 */     Color specColor = cs.toColor(lab[0], lab[1], lab[2], opacity);
/* 541 */     return specColor;
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
/*     */   public static Color convertDeviceColor(Element e, Value srgb, DeviceColor c, float opacity, BridgeContext ctx) {
/* 559 */     int r = resolveColorComponent(srgb.getRed());
/* 560 */     int g = resolveColorComponent(srgb.getGreen());
/* 561 */     int b = resolveColorComponent(srgb.getBlue());
/* 562 */     if (c.isNChannel()) {
/* 563 */       return convertColor(srgb, opacity);
/*     */     }
/* 565 */     if (c.getNumberOfColors() == 4) {
/* 566 */       DeviceCMYKColorSpace cmykCs = ColorSpaces.getDeviceCMYKColorSpace();
/* 567 */       float[] comps = new float[4];
/* 568 */       for (int i = 0; i < 4; i++) {
/* 569 */         comps[i] = c.getColor(i);
/*     */       }
/* 571 */       ColorWithAlternatives colorWithAlternatives = new ColorWithAlternatives((ColorSpace)cmykCs, comps, opacity, null);
/* 572 */       return (Color)new ColorWithAlternatives(r, g, b, Math.round(opacity * 255.0F), new Color[] { (Color)colorWithAlternatives });
/*     */     } 
/*     */ 
/*     */     
/* 576 */     return convertColor(srgb, opacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color convertColor(Value c, float opacity) {
/* 587 */     int r = resolveColorComponent(c.getRed());
/* 588 */     int g = resolveColorComponent(c.getGreen());
/* 589 */     int b = resolveColorComponent(c.getBlue());
/* 590 */     return new Color(r, g, b, Math.round(opacity * 255.0F));
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
/*     */   public static Stroke convertStroke(Element e) {
/* 604 */     Value v = CSSUtilities.getComputedStyle(e, 52);
/*     */     
/* 606 */     float width = v.getFloatValue();
/* 607 */     if (width == 0.0F) {
/* 608 */       return null;
/*     */     }
/* 610 */     v = CSSUtilities.getComputedStyle(e, 48);
/*     */     
/* 612 */     int linecap = convertStrokeLinecap(v);
/* 613 */     v = CSSUtilities.getComputedStyle(e, 49);
/*     */     
/* 615 */     int linejoin = convertStrokeLinejoin(v);
/* 616 */     v = CSSUtilities.getComputedStyle(e, 50);
/*     */     
/* 618 */     float miterlimit = convertStrokeMiterlimit(v);
/* 619 */     v = CSSUtilities.getComputedStyle(e, 46);
/*     */     
/* 621 */     float[] dasharray = convertStrokeDasharray(v);
/*     */     
/* 623 */     float dashoffset = 0.0F;
/* 624 */     if (dasharray != null) {
/* 625 */       v = CSSUtilities.getComputedStyle(e, 47);
/*     */       
/* 627 */       dashoffset = v.getFloatValue();
/*     */ 
/*     */ 
/*     */       
/* 631 */       if (dashoffset < 0.0F) {
/* 632 */         float dashpatternlength = 0.0F;
/* 633 */         for (float aDasharray : dasharray) {
/* 634 */           dashpatternlength += aDasharray;
/*     */         }
/*     */ 
/*     */         
/* 638 */         if (dasharray.length % 2 != 0) {
/* 639 */           dashpatternlength *= 2.0F;
/*     */         }
/* 641 */         if (dashpatternlength == 0.0F) {
/* 642 */           dashoffset = 0.0F;
/*     */         } else {
/* 644 */           while (dashoffset < 0.0F)
/* 645 */             dashoffset += dashpatternlength; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 649 */     return new BasicStroke(width, linecap, linejoin, miterlimit, dasharray, dashoffset);
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
/*     */   public static float[] convertStrokeDasharray(Value v) {
/* 668 */     float[] dasharray = null;
/* 669 */     if (v.getCssValueType() == 2) {
/* 670 */       int length = v.getLength();
/* 671 */       dasharray = new float[length];
/* 672 */       float sum = 0.0F;
/* 673 */       for (int i = 0; i < dasharray.length; i++) {
/* 674 */         dasharray[i] = v.item(i).getFloatValue();
/* 675 */         sum += dasharray[i];
/*     */       } 
/* 677 */       if (sum == 0.0F)
/*     */       {
/*     */ 
/*     */         
/* 681 */         dasharray = null;
/*     */       }
/*     */     } 
/* 684 */     return dasharray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float convertStrokeMiterlimit(Value v) {
/* 692 */     float miterlimit = v.getFloatValue();
/* 693 */     return (miterlimit < 1.0F) ? 1.0F : miterlimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertStrokeLinecap(Value v) {
/* 701 */     String s = v.getStringValue();
/* 702 */     switch (s.charAt(0)) {
/*     */       case 'b':
/* 704 */         return 0;
/*     */       case 'r':
/* 706 */         return 1;
/*     */       case 's':
/* 708 */         return 2;
/*     */     } 
/* 710 */     throw new IllegalArgumentException("Linecap argument is not an appropriate CSS value");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertStrokeLinejoin(Value v) {
/* 721 */     String s = v.getStringValue();
/* 722 */     switch (s.charAt(0)) {
/*     */       case 'm':
/* 724 */         return 0;
/*     */       case 'r':
/* 726 */         return 1;
/*     */       case 'b':
/* 728 */         return 2;
/*     */     } 
/* 730 */     throw new IllegalArgumentException("Linejoin argument is not an appropriate CSS value");
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
/*     */   public static int resolveColorComponent(Value v) {
/*     */     float f;
/* 745 */     switch (v.getPrimitiveType()) {
/*     */       case 2:
/* 747 */         f = v.getFloatValue();
/* 748 */         f = (f > 100.0F) ? 100.0F : ((f < 0.0F) ? 0.0F : f);
/* 749 */         return Math.round(255.0F * f / 100.0F);
/*     */       case 1:
/* 751 */         f = v.getFloatValue();
/* 752 */         f = (f > 255.0F) ? 255.0F : ((f < 0.0F) ? 0.0F : f);
/* 753 */         return Math.round(f);
/*     */     } 
/* 755 */     throw new IllegalArgumentException("Color component argument is not an appropriate CSS value");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float convertOpacity(Value v) {
/* 766 */     float r = v.getFloatValue();
/* 767 */     return (r < 0.0F) ? 0.0F : ((r > 1.0F) ? 1.0F : r);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/PaintServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */