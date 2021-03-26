/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.value.ListValue;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.ext.awt.MultipleGradientPaint;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.filter.Mask;
/*     */ import org.apache.batik.util.CSSConstants;
/*     */ import org.apache.batik.util.XMLConstants;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.css.CSSPrimitiveValue;
/*     */ import org.w3c.dom.css.CSSValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CSSUtilities
/*     */   implements ErrorConstants, CSSConstants, XMLConstants
/*     */ {
/*     */   public static CSSEngine getCSSEngine(Element e) {
/*  72 */     return ((SVGOMDocument)e.getOwnerDocument()).getCSSEngine();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Value getComputedStyle(Element e, int property) {
/*  79 */     CSSEngine engine = getCSSEngine(e);
/*  80 */     if (engine == null) return null; 
/*  81 */     return engine.getComputedStyle((CSSStylableElement)e, null, property);
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
/*     */   public static int convertPointerEvents(Element e) {
/* 103 */     Value v = getComputedStyle(e, 40);
/* 104 */     String s = v.getStringValue();
/* 105 */     switch (s.charAt(0)) {
/*     */       case 'v':
/* 107 */         if (s.length() == 7) {
/* 108 */           return 3;
/*     */         }
/* 110 */         switch (s.charAt(7)) {
/*     */           case 'p':
/* 112 */             return 0;
/*     */           case 'f':
/* 114 */             return 1;
/*     */           case 's':
/* 116 */             return 2;
/*     */         } 
/*     */         
/* 119 */         throw new IllegalStateException("unexpected event, must be one of (p,f,s) is:" + s.charAt(7));
/*     */ 
/*     */       
/*     */       case 'p':
/* 123 */         return 4;
/*     */       case 'f':
/* 125 */         return 5;
/*     */       case 's':
/* 127 */         return 6;
/*     */       case 'a':
/* 129 */         return 7;
/*     */       case 'n':
/* 131 */         return 8;
/*     */     } 
/*     */     
/* 134 */     throw new IllegalStateException("unexpected event, must be one of (v,p,f,s,a,n) is:" + s.charAt(0));
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
/*     */   public static Rectangle2D convertEnableBackground(Element e) {
/*     */     float x, y, w, h;
/* 150 */     Value v = getComputedStyle(e, 14);
/* 151 */     if (v.getCssValueType() != 2) {
/* 152 */       return null;
/*     */     }
/* 154 */     ListValue lv = (ListValue)v;
/* 155 */     int length = lv.getLength();
/* 156 */     switch (length) {
/*     */       case 1:
/* 158 */         return CompositeGraphicsNode.VIEWPORT;
/*     */       case 5:
/* 160 */         x = lv.item(1).getFloatValue();
/* 161 */         y = lv.item(2).getFloatValue();
/* 162 */         w = lv.item(3).getFloatValue();
/* 163 */         h = lv.item(4).getFloatValue();
/* 164 */         return new Rectangle2D.Float(x, y, w, h);
/*     */     } 
/*     */     
/* 167 */     throw new IllegalStateException("Unexpected length:" + length);
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
/*     */   public static boolean convertColorInterpolationFilters(Element e) {
/* 183 */     Value v = getComputedStyle(e, 7);
/*     */     
/* 185 */     return ("linearrgb" == v.getStringValue());
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
/*     */   public static MultipleGradientPaint.ColorSpaceEnum convertColorInterpolation(Element e) {
/* 200 */     Value v = getComputedStyle(e, 6);
/* 201 */     return ("linearrgb" == v.getStringValue()) ? MultipleGradientPaint.LINEAR_RGB : MultipleGradientPaint.SRGB;
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
/*     */   public static boolean isAutoCursor(Element e) {
/* 214 */     Value cursorValue = getComputedStyle(e, 10);
/*     */ 
/*     */ 
/*     */     
/* 218 */     boolean isAuto = false;
/* 219 */     if (cursorValue != null) {
/* 220 */       if (cursorValue.getCssValueType() == 1 && cursorValue.getPrimitiveType() == 21 && cursorValue.getStringValue().charAt(0) == 'a') {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 227 */         isAuto = true;
/* 228 */       } else if (cursorValue.getCssValueType() == 2 && cursorValue.getLength() == 1) {
/*     */ 
/*     */ 
/*     */         
/* 232 */         Value lValue = cursorValue.item(0);
/* 233 */         if (lValue != null && lValue.getCssValueType() == 1 && lValue.getPrimitiveType() == 21 && lValue.getStringValue().charAt(0) == 'a')
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 240 */           isAuto = true;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 245 */     return isAuto;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Cursor convertCursor(Element e, BridgeContext ctx) {
/* 256 */     return ctx.getCursorManager().convertCursor(e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RenderingHints convertShapeRendering(Element e, RenderingHints hints) {
/* 302 */     Value v = getComputedStyle(e, 42);
/* 303 */     String s = v.getStringValue();
/* 304 */     int len = s.length();
/* 305 */     if (len == 4 && s.charAt(0) == 'a')
/* 306 */       return hints; 
/* 307 */     if (len < 10) return hints;
/*     */     
/* 309 */     if (hints == null) {
/* 310 */       hints = new RenderingHints(null);
/*     */     }
/* 312 */     switch (s.charAt(0)) {
/*     */       case 'o':
/* 314 */         hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
/*     */         
/* 316 */         hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*     */         break;
/*     */       
/*     */       case 'c':
/* 320 */         hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
/*     */         
/* 322 */         hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*     */         break;
/*     */       
/*     */       case 'g':
/* 326 */         hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*     */         
/* 328 */         hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */         
/* 330 */         hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/*     */         break;
/*     */     } 
/*     */     
/* 334 */     return hints;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RenderingHints convertTextRendering(Element e, RenderingHints hints) {
/* 386 */     Value v = getComputedStyle(e, 55);
/* 387 */     String s = v.getStringValue();
/* 388 */     int len = s.length();
/* 389 */     if (len == 4 && s.charAt(0) == 'a')
/* 390 */       return hints; 
/* 391 */     if (len < 13) return hints;
/*     */     
/* 393 */     if (hints == null) {
/* 394 */       hints = new RenderingHints(null);
/*     */     }
/* 396 */     switch (s.charAt(8)) {
/*     */       case 's':
/* 398 */         hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
/*     */         
/* 400 */         hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*     */         
/* 402 */         hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 'l':
/* 408 */         hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*     */         
/* 410 */         hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*     */         
/* 412 */         hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 'c':
/* 418 */         hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*     */         
/* 420 */         hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */         
/* 422 */         hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */         
/* 424 */         hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
/*     */         
/* 426 */         hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/*     */         break;
/*     */     } 
/*     */     
/* 430 */     return hints;
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
/*     */   public static RenderingHints convertImageRendering(Element e, RenderingHints hints) {
/* 464 */     Value v = getComputedStyle(e, 30);
/* 465 */     String s = v.getStringValue();
/* 466 */     int len = s.length();
/* 467 */     if (len == 4 && s.charAt(0) == 'a')
/* 468 */       return hints; 
/* 469 */     if (len < 13) return hints;
/*     */     
/* 471 */     if (hints == null) {
/* 472 */       hints = new RenderingHints(null);
/*     */     }
/* 474 */     switch (s.charAt(8)) {
/*     */       case 's':
/* 476 */         hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
/*     */         
/* 478 */         hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
/*     */         break;
/*     */       
/*     */       case 'q':
/* 482 */         hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*     */         
/* 484 */         hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
/*     */         break;
/*     */     } 
/*     */     
/* 488 */     return hints;
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
/*     */   public static RenderingHints convertColorRendering(Element e, RenderingHints hints) {
/* 522 */     Value v = getComputedStyle(e, 9);
/* 523 */     String s = v.getStringValue();
/* 524 */     int len = s.length();
/* 525 */     if (len == 4 && s.charAt(0) == 'a')
/* 526 */       return hints; 
/* 527 */     if (len < 13) return hints;
/*     */     
/* 529 */     if (hints == null) {
/* 530 */       hints = new RenderingHints(null);
/*     */     }
/* 532 */     switch (s.charAt(8)) {
/*     */       case 's':
/* 534 */         hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
/*     */         
/* 536 */         hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
/*     */         break;
/*     */       
/*     */       case 'q':
/* 540 */         hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
/*     */         
/* 542 */         hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
/*     */         break;
/*     */     } 
/*     */     
/* 546 */     return hints;
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
/*     */   public static boolean convertDisplay(Element e) {
/* 560 */     if (!(e instanceof CSSStylableElement)) {
/* 561 */       return true;
/*     */     }
/* 563 */     Value v = getComputedStyle(e, 12);
/* 564 */     return (v.getStringValue().charAt(0) != 'n');
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
/*     */   public static boolean convertVisibility(Element e) {
/* 578 */     Value v = getComputedStyle(e, 57);
/* 579 */     return (v.getStringValue().charAt(0) == 'v');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 586 */   public static final Composite TRANSPARENT = AlphaComposite.getInstance(3, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Composite convertOpacity(Element e) {
/* 596 */     Value v = getComputedStyle(e, 38);
/* 597 */     float f = v.getFloatValue();
/* 598 */     if (f <= 0.0F)
/* 599 */       return TRANSPARENT; 
/* 600 */     if (f >= 1.0F) {
/* 601 */       return AlphaComposite.SrcOver;
/*     */     }
/* 603 */     return AlphaComposite.getInstance(3, f);
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
/*     */   public static boolean convertOverflow(Element e) {
/* 620 */     Value v = getComputedStyle(e, 39);
/* 621 */     String s = v.getStringValue();
/* 622 */     return (s.charAt(0) == 'h' || s.charAt(0) == 's');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] convertClip(Element e) {
/*     */     float[] off;
/* 633 */     Value v = getComputedStyle(e, 2);
/* 634 */     int primitiveType = v.getPrimitiveType();
/* 635 */     switch (primitiveType) {
/*     */       case 24:
/* 637 */         off = new float[4];
/* 638 */         off[0] = v.getTop().getFloatValue();
/* 639 */         off[1] = v.getRight().getFloatValue();
/* 640 */         off[2] = v.getBottom().getFloatValue();
/* 641 */         off[3] = v.getLeft().getFloatValue();
/* 642 */         return off;
/*     */       case 21:
/* 644 */         return null;
/*     */     } 
/*     */     
/* 647 */     throw new IllegalStateException("Unexpected primitiveType:" + primitiveType);
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
/*     */   public static Filter convertFilter(Element filteredElement, GraphicsNode filteredNode, BridgeContext ctx) {
/*     */     String uri;
/*     */     Element filter;
/*     */     Bridge bridge;
/* 668 */     Value v = getComputedStyle(filteredElement, 18);
/* 669 */     int primitiveType = v.getPrimitiveType();
/* 670 */     switch (primitiveType) {
/*     */       case 21:
/* 672 */         return null;
/*     */       
/*     */       case 20:
/* 675 */         uri = v.getStringValue();
/* 676 */         filter = ctx.getReferencedElement(filteredElement, uri);
/* 677 */         bridge = ctx.getBridge(filter);
/* 678 */         if (bridge == null || !(bridge instanceof FilterBridge)) {
/* 679 */           throw new BridgeException(ctx, filteredElement, "css.uri.badTarget", new Object[] { uri });
/*     */         }
/*     */ 
/*     */         
/* 683 */         return ((FilterBridge)bridge).createFilter(ctx, filter, filteredElement, filteredNode);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 688 */     throw new IllegalStateException("Unexpected primitive type:" + primitiveType);
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
/*     */   public static ClipRable convertClipPath(Element clippedElement, GraphicsNode clippedNode, BridgeContext ctx) {
/*     */     String uri;
/*     */     Element cp;
/*     */     Bridge bridge;
/* 709 */     Value v = getComputedStyle(clippedElement, 3);
/*     */     
/* 711 */     int primitiveType = v.getPrimitiveType();
/* 712 */     switch (primitiveType) {
/*     */       case 21:
/* 714 */         return null;
/*     */       
/*     */       case 20:
/* 717 */         uri = v.getStringValue();
/* 718 */         cp = ctx.getReferencedElement(clippedElement, uri);
/* 719 */         bridge = ctx.getBridge(cp);
/* 720 */         if (bridge == null || !(bridge instanceof ClipBridge)) {
/* 721 */           throw new BridgeException(ctx, clippedElement, "css.uri.badTarget", new Object[] { uri });
/*     */         }
/*     */ 
/*     */         
/* 725 */         return ((ClipBridge)bridge).createClip(ctx, cp, clippedElement, clippedNode);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 730 */     throw new IllegalStateException("Unexpected primitive type:" + primitiveType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertClipRule(Element e) {
/* 741 */     Value v = getComputedStyle(e, 4);
/* 742 */     return (v.getStringValue().charAt(0) == 'n') ? 1 : 0;
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
/*     */   public static Mask convertMask(Element maskedElement, GraphicsNode maskedNode, BridgeContext ctx) {
/*     */     String uri;
/*     */     Element m;
/*     */     Bridge bridge;
/* 763 */     Value v = getComputedStyle(maskedElement, 37);
/* 764 */     int primitiveType = v.getPrimitiveType();
/* 765 */     switch (primitiveType) {
/*     */       case 21:
/* 767 */         return null;
/*     */       
/*     */       case 20:
/* 770 */         uri = v.getStringValue();
/* 771 */         m = ctx.getReferencedElement(maskedElement, uri);
/* 772 */         bridge = ctx.getBridge(m);
/* 773 */         if (bridge == null || !(bridge instanceof MaskBridge)) {
/* 774 */           throw new BridgeException(ctx, maskedElement, "css.uri.badTarget", new Object[] { uri });
/*     */         }
/*     */ 
/*     */         
/* 778 */         return ((MaskBridge)bridge).createMask(ctx, m, maskedElement, maskedNode);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 783 */     throw new IllegalStateException("Unexpected primitive type:" + primitiveType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertFillRule(Element e) {
/* 794 */     Value v = getComputedStyle(e, 17);
/* 795 */     return (v.getStringValue().charAt(0) == 'n') ? 1 : 0;
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
/*     */   public static Color convertLightingColor(Element e, BridgeContext ctx) {
/* 812 */     Value v = getComputedStyle(e, 33);
/* 813 */     if (v.getCssValueType() == 1) {
/* 814 */       return PaintServer.convertColor(v, 1.0F);
/*     */     }
/* 816 */     return PaintServer.convertRGBICCColor(e, v.item(0), v.item(1), 1.0F, ctx);
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
/*     */   public static Color convertFloodColor(Element e, BridgeContext ctx) {
/* 833 */     Value v = getComputedStyle(e, 19);
/* 834 */     Value o = getComputedStyle(e, 20);
/* 835 */     float f = PaintServer.convertOpacity(o);
/* 836 */     if (v.getCssValueType() == 1) {
/* 837 */       return PaintServer.convertColor(v, f);
/*     */     }
/* 839 */     return PaintServer.convertRGBICCColor(e, v.item(0), v.item(1), f, ctx);
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
/*     */   public static Color convertStopColor(Element e, float opacity, BridgeContext ctx) {
/* 859 */     Value v = getComputedStyle(e, 43);
/* 860 */     Value o = getComputedStyle(e, 44);
/* 861 */     opacity *= PaintServer.convertOpacity(o);
/* 862 */     if (v.getCssValueType() == 1) {
/* 863 */       return PaintServer.convertColor(v, opacity);
/*     */     }
/* 865 */     return PaintServer.convertRGBICCColor(e, v.item(0), v.item(1), opacity, ctx);
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
/*     */   public static void computeStyleAndURIs(Element refElement, Element localRefElement, String uri) {
/* 887 */     int idx = uri.indexOf('#');
/* 888 */     if (idx != -1) {
/* 889 */       uri = uri.substring(0, idx);
/*     */     }
/*     */     
/* 892 */     if (uri.length() != 0) {
/* 893 */       localRefElement.setAttributeNS("http://www.w3.org/XML/1998/namespace", "base", uri);
/*     */     }
/*     */ 
/*     */     
/* 897 */     CSSEngine engine = getCSSEngine(localRefElement);
/* 898 */     CSSEngine refEngine = getCSSEngine(refElement);
/*     */     
/* 900 */     engine.importCascadedStyleMaps(refElement, refEngine, localRefElement);
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
/*     */   protected static int rule(CSSValue v) {
/* 914 */     return (((CSSPrimitiveValue)v).getStringValue().charAt(0) == 'n') ? 1 : 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/CSSUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */