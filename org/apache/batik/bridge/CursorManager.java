/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.AffineRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.spi.BrokenLinkProvider;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageTagRegistry;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.parser.UnitProcessor;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.util.Platform;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.apache.batik.util.SoftReferenceCache;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
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
/*     */ public class CursorManager
/*     */   implements ErrorConstants, SVGConstants
/*     */ {
/*     */   protected static Map cursorMap;
/*  77 */   public static final Cursor DEFAULT_CURSOR = Cursor.getPredefinedCursor(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final Cursor ANCHOR_CURSOR = Cursor.getPredefinedCursor(12);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final Cursor TEXT_CURSOR = Cursor.getPredefinedCursor(2);
/*     */   
/*     */   public static final int DEFAULT_PREFERRED_WIDTH = 32;
/*     */   
/*     */   public static final int DEFAULT_PREFERRED_HEIGHT = 32;
/*     */   
/*     */   protected BridgeContext ctx;
/*     */   
/*     */   static {
/*     */     Cursor cursor1;
/*     */   }
/*     */   
/*     */   static {
/* 102 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 103 */     cursorMap = new HashMap<Object, Object>();
/* 104 */     cursorMap.put("crosshair", Cursor.getPredefinedCursor(1));
/*     */     
/* 106 */     cursorMap.put("default", Cursor.getPredefinedCursor(0));
/*     */     
/* 108 */     cursorMap.put("pointer", Cursor.getPredefinedCursor(12));
/*     */     
/* 110 */     cursorMap.put("e-resize", Cursor.getPredefinedCursor(11));
/*     */     
/* 112 */     cursorMap.put("ne-resize", Cursor.getPredefinedCursor(7));
/*     */     
/* 114 */     cursorMap.put("nw-resize", Cursor.getPredefinedCursor(6));
/*     */     
/* 116 */     cursorMap.put("n-resize", Cursor.getPredefinedCursor(8));
/*     */     
/* 118 */     cursorMap.put("se-resize", Cursor.getPredefinedCursor(5));
/*     */     
/* 120 */     cursorMap.put("sw-resize", Cursor.getPredefinedCursor(4));
/*     */     
/* 122 */     cursorMap.put("s-resize", Cursor.getPredefinedCursor(9));
/*     */     
/* 124 */     cursorMap.put("w-resize", Cursor.getPredefinedCursor(10));
/*     */     
/* 126 */     cursorMap.put("text", Cursor.getPredefinedCursor(2));
/*     */     
/* 128 */     cursorMap.put("wait", Cursor.getPredefinedCursor(3));
/*     */     
/* 130 */     Cursor moveCursor = Cursor.getPredefinedCursor(13);
/* 131 */     if (Platform.isOSX) {
/*     */       try {
/* 133 */         Image img = toolkit.createImage(CursorManager.class.getResource("resources/move.gif"));
/*     */         
/* 135 */         moveCursor = toolkit.createCustomCursor(img, new Point(11, 11), "move");
/*     */       }
/* 137 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 140 */     cursorMap.put("move", moveCursor);
/*     */     
/*     */     try {
/* 143 */       Image img = toolkit.createImage(CursorManager.class.getResource("resources/help.gif"));
/*     */       
/* 145 */       cursor1 = toolkit.createCustomCursor(img, new Point(1, 3), "help");
/*     */     }
/* 147 */     catch (Exception ex) {
/* 148 */       cursor1 = Cursor.getPredefinedCursor(12);
/*     */     } 
/* 150 */     cursorMap.put("help", cursor1);
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
/* 161 */   protected CursorCache cursorCache = new CursorCache();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CursorManager(BridgeContext ctx) {
/* 169 */     this.ctx = ctx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Cursor getPredefinedCursor(String cursorName) {
/* 178 */     return (Cursor)cursorMap.get(cursorName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cursor convertCursor(Element e) {
/* 187 */     Value cursorValue = CSSUtilities.getComputedStyle(e, 10);
/*     */ 
/*     */     
/* 190 */     String cursorStr = "auto";
/*     */     
/* 192 */     if (cursorValue != null) {
/* 193 */       if (cursorValue.getCssValueType() == 1 && cursorValue.getPrimitiveType() == 21) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 198 */         cursorStr = cursorValue.getStringValue();
/* 199 */         return convertBuiltInCursor(e, cursorStr);
/* 200 */       }  if (cursorValue.getCssValueType() == 2) {
/*     */         
/* 202 */         int nValues = cursorValue.getLength();
/* 203 */         if (nValues == 1) {
/* 204 */           cursorValue = cursorValue.item(0);
/* 205 */           if (cursorValue.getPrimitiveType() == 21) {
/*     */             
/* 207 */             cursorStr = cursorValue.getStringValue();
/* 208 */             return convertBuiltInCursor(e, cursorStr);
/*     */           } 
/* 210 */         } else if (nValues > 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 215 */           return convertSVGCursor(e, cursorValue);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     return convertBuiltInCursor(e, cursorStr);
/*     */   }
/*     */   
/*     */   public Cursor convertBuiltInCursor(Element e, String cursorStr) {
/* 224 */     Cursor cursor = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     if (cursorStr.charAt(0) == 'a') {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 259 */       String nameSpaceURI = e.getNamespaceURI();
/* 260 */       if ("http://www.w3.org/2000/svg".equals(nameSpaceURI)) {
/* 261 */         String tag = e.getLocalName();
/* 262 */         if ("a".equals(tag))
/* 263 */         { cursor = ANCHOR_CURSOR; }
/* 264 */         else if ("text".equals(tag) || "tspan".equals(tag) || "tref".equals(tag))
/*     */         
/*     */         { 
/* 267 */           cursor = TEXT_CURSOR; }
/* 268 */         else { if ("image".equals(tag))
/*     */           {
/* 270 */             return null;
/*     */           }
/* 272 */           cursor = DEFAULT_CURSOR; }
/*     */       
/*     */       } else {
/* 275 */         cursor = DEFAULT_CURSOR;
/*     */       } 
/*     */     } else {
/*     */       
/* 279 */       cursor = getPredefinedCursor(cursorStr);
/*     */     } 
/*     */     
/* 282 */     return cursor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cursor convertSVGCursor(Element e, Value l) {
/* 293 */     int nValues = l.getLength();
/* 294 */     Element cursorElement = null;
/* 295 */     for (int i = 0; i < nValues - 1; i++) {
/* 296 */       Value value = l.item(i);
/* 297 */       if (value.getPrimitiveType() == 20) {
/* 298 */         String uri = value.getStringValue();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 304 */           cursorElement = this.ctx.getReferencedElement(e, uri);
/* 305 */         } catch (BridgeException be) {
/*     */ 
/*     */ 
/*     */           
/* 309 */           if (!"uri.badTarget".equals(be.getCode())) {
/* 310 */             throw be;
/*     */           }
/*     */         } 
/*     */         
/* 314 */         if (cursorElement != null) {
/*     */           
/* 316 */           String cursorNS = cursorElement.getNamespaceURI();
/* 317 */           if ("http://www.w3.org/2000/svg".equals(cursorNS) && "cursor".equals(cursorElement.getLocalName())) {
/*     */ 
/*     */             
/* 320 */             Cursor c = convertSVGCursorElement(cursorElement);
/* 321 */             if (c != null) {
/* 322 */               return c;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 333 */     Value cursorValue = l.item(nValues - 1);
/* 334 */     String cursorStr = "auto";
/* 335 */     if (cursorValue.getPrimitiveType() == 21) {
/* 336 */       cursorStr = cursorValue.getStringValue();
/*     */     }
/*     */     
/* 339 */     return convertBuiltInCursor(e, cursorStr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cursor convertSVGCursorElement(Element cursorElement) {
/*     */     ParsedURL purl;
/* 348 */     String uriStr = XLinkSupport.getXLinkHref(cursorElement);
/* 349 */     if (uriStr.length() == 0) {
/* 350 */       throw new BridgeException(this.ctx, cursorElement, "attribute.missing", new Object[] { "xlink:href" });
/*     */     }
/*     */ 
/*     */     
/* 354 */     String baseURI = AbstractNode.getBaseURI(cursorElement);
/*     */     
/* 356 */     if (baseURI == null) {
/* 357 */       purl = new ParsedURL(uriStr);
/*     */     } else {
/* 359 */       purl = new ParsedURL(baseURI, uriStr);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 365 */     UnitProcessor.Context uctx = UnitProcessor.createContext(this.ctx, cursorElement);
/*     */ 
/*     */     
/* 368 */     String s = cursorElement.getAttributeNS((String)null, "x");
/* 369 */     float x = 0.0F;
/* 370 */     if (s.length() != 0) {
/* 371 */       x = UnitProcessor.svgHorizontalCoordinateToUserSpace(s, "x", uctx);
/*     */     }
/*     */ 
/*     */     
/* 375 */     s = cursorElement.getAttributeNS((String)null, "y");
/* 376 */     float y = 0.0F;
/* 377 */     if (s.length() != 0) {
/* 378 */       y = UnitProcessor.svgVerticalCoordinateToUserSpace(s, "y", uctx);
/*     */     }
/*     */ 
/*     */     
/* 382 */     CursorDescriptor desc = new CursorDescriptor(purl, x, y);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 387 */     Cursor cachedCursor = this.cursorCache.getCursor(desc);
/*     */     
/* 389 */     if (cachedCursor != null) {
/* 390 */       return cachedCursor;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 397 */     Point2D.Float hotSpot = new Point2D.Float(x, y);
/* 398 */     Filter f = cursorHrefToFilter(cursorElement, purl, hotSpot);
/*     */ 
/*     */     
/* 401 */     if (f == null) {
/* 402 */       this.cursorCache.clearCursor(desc);
/* 403 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 408 */     Rectangle cursorSize = f.getBounds2D().getBounds();
/* 409 */     RenderedImage ri = f.createScaledRendering(cursorSize.width, cursorSize.height, null);
/*     */ 
/*     */     
/* 412 */     Image img = null;
/*     */     
/* 414 */     if (ri instanceof Image) {
/* 415 */       img = (Image)ri;
/*     */     } else {
/* 417 */       img = renderedImageToImage(ri);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 422 */     hotSpot.x = (hotSpot.x < 0.0F) ? 0.0F : hotSpot.x;
/* 423 */     hotSpot.y = (hotSpot.y < 0.0F) ? 0.0F : hotSpot.y;
/* 424 */     hotSpot.x = (hotSpot.x > (cursorSize.width - 1)) ? (cursorSize.width - 1) : hotSpot.x;
/* 425 */     hotSpot.y = (hotSpot.y > (cursorSize.height - 1)) ? (cursorSize.height - 1) : hotSpot.y;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 430 */     Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(Math.round(hotSpot.x), Math.round(hotSpot.y)), purl.toString());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 436 */     this.cursorCache.putCursor(desc, c);
/* 437 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Filter cursorHrefToFilter(Element cursorElement, ParsedURL purl, Point2D hotSpot) {
/* 448 */     AffineRable8Bit f = null;
/* 449 */     String uriStr = purl.toString();
/* 450 */     Dimension cursorSize = null;
/*     */ 
/*     */     
/* 453 */     DocumentLoader loader = this.ctx.getDocumentLoader();
/* 454 */     SVGDocument svgDoc = (SVGDocument)cursorElement.getOwnerDocument();
/* 455 */     URIResolver resolver = this.ctx.createURIResolver(svgDoc, loader); try {
/*     */       SVGSVGElement sVGSVGElement;
/* 457 */       Element rootElement = null;
/* 458 */       Node n = resolver.getNode(uriStr, cursorElement);
/* 459 */       if (n.getNodeType() == 9) {
/* 460 */         SVGDocument doc = (SVGDocument)n;
/*     */         
/* 462 */         this.ctx.initializeDocument((Document)doc);
/* 463 */         sVGSVGElement = doc.getRootElement();
/*     */       } else {
/* 465 */         throw new BridgeException(this.ctx, cursorElement, "uri.image.invalid", new Object[] { uriStr });
/*     */       } 
/*     */ 
/*     */       
/* 469 */       GraphicsNode node = this.ctx.getGVTBuilder().build(this.ctx, (Element)sVGSVGElement);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 476 */       float width = 32.0F;
/* 477 */       float height = 32.0F;
/* 478 */       UnitProcessor.Context uctx = UnitProcessor.createContext(this.ctx, (Element)sVGSVGElement);
/*     */ 
/*     */       
/* 481 */       String s = sVGSVGElement.getAttribute("width");
/* 482 */       if (s.length() != 0) {
/* 483 */         width = UnitProcessor.svgHorizontalLengthToUserSpace(s, "width", uctx);
/*     */       }
/*     */ 
/*     */       
/* 487 */       s = sVGSVGElement.getAttribute("height");
/* 488 */       if (s.length() != 0) {
/* 489 */         height = UnitProcessor.svgVerticalLengthToUserSpace(s, "height", uctx);
/*     */       }
/*     */ 
/*     */       
/* 493 */       cursorSize = Toolkit.getDefaultToolkit().getBestCursorSize(Math.round(width), Math.round(height));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 498 */       AffineTransform affineTransform = ViewBox.getPreserveAspectRatioTransform((Element)sVGSVGElement, cursorSize.width, cursorSize.height, this.ctx);
/*     */       
/* 500 */       Filter filter = node.getGraphicsNodeRable(true);
/* 501 */       f = new AffineRable8Bit(filter, affineTransform);
/* 502 */     } catch (BridgeException ex) {
/* 503 */       throw ex;
/* 504 */     } catch (SecurityException ex) {
/* 505 */       throw new BridgeException(this.ctx, cursorElement, ex, "uri.unsecure", new Object[] { uriStr });
/*     */     }
/* 507 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 514 */     if (f == null) {
/* 515 */       ImageTagRegistry reg = ImageTagRegistry.getRegistry();
/* 516 */       Filter filter = reg.readURL(purl);
/* 517 */       if (filter == null) {
/* 518 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 522 */       if (BrokenLinkProvider.hasBrokenLinkProperty(filter)) {
/* 523 */         return null;
/*     */       }
/*     */       
/* 526 */       Rectangle preferredSize = filter.getBounds2D().getBounds();
/* 527 */       cursorSize = Toolkit.getDefaultToolkit().getBestCursorSize(preferredSize.width, preferredSize.height);
/*     */ 
/*     */       
/* 530 */       if (preferredSize != null && preferredSize.width > 0 && preferredSize.height > 0) {
/*     */         
/* 532 */         AffineTransform affineTransform = new AffineTransform();
/* 533 */         if (preferredSize.width > cursorSize.width || preferredSize.height > cursorSize.height)
/*     */         {
/*     */           
/* 536 */           affineTransform = ViewBox.getPreserveAspectRatioTransform(new float[] { 0.0F, 0.0F, preferredSize.width, preferredSize.height }, (short)2, true, cursorSize.width, cursorSize.height);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 543 */         f = new AffineRable8Bit(filter, affineTransform);
/*     */       } else {
/*     */         
/* 546 */         return null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 554 */     AffineTransform at = f.getAffine();
/* 555 */     at.transform(hotSpot, hotSpot);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 560 */     Rectangle cursorViewport = new Rectangle(0, 0, cursorSize.width, cursorSize.height);
/*     */ 
/*     */     
/* 563 */     PadRable8Bit cursorImage = new PadRable8Bit((Filter)f, cursorViewport, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/* 567 */     return (Filter)cursorImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Image renderedImageToImage(RenderedImage ri) {
/* 576 */     int x = ri.getMinX();
/* 577 */     int y = ri.getMinY();
/* 578 */     SampleModel sm = ri.getSampleModel();
/* 579 */     ColorModel cm = ri.getColorModel();
/* 580 */     WritableRaster wr = Raster.createWritableRaster(sm, new Point(x, y));
/* 581 */     ri.copyData(wr);
/*     */     
/* 583 */     return new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
/*     */   }
/*     */ 
/*     */   
/*     */   static class CursorDescriptor
/*     */   {
/*     */     ParsedURL purl;
/*     */     
/*     */     float x;
/*     */     
/*     */     float y;
/*     */     
/*     */     String desc;
/*     */ 
/*     */     
/*     */     public CursorDescriptor(ParsedURL purl, float x, float y) {
/* 599 */       if (purl == null) {
/* 600 */         throw new IllegalArgumentException();
/*     */       }
/*     */       
/* 603 */       this.purl = purl;
/* 604 */       this.x = x;
/* 605 */       this.y = y;
/*     */ 
/*     */       
/* 608 */       this.desc = getClass().getName() + "\n\t:[" + this.purl + "]\n\t:[" + x + "]:[" + y + "]";
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 613 */       if (obj == null || !(obj instanceof CursorDescriptor))
/*     */       {
/*     */         
/* 616 */         return false;
/*     */       }
/*     */       
/* 619 */       CursorDescriptor desc = (CursorDescriptor)obj;
/* 620 */       boolean isEqual = (this.purl.equals(desc.purl) && this.x == desc.x && this.y == desc.y);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 627 */       return isEqual;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 631 */       return this.desc;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 635 */       return this.desc.hashCode();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class CursorCache
/*     */     extends SoftReferenceCache
/*     */   {
/*     */     public Cursor getCursor(CursorManager.CursorDescriptor desc) {
/* 649 */       return (Cursor)requestImpl(desc);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putCursor(CursorManager.CursorDescriptor desc, Cursor cursor) {
/* 654 */       putImpl(desc, cursor);
/*     */     }
/*     */     
/*     */     public void clearCursor(CursorManager.CursorDescriptor desc) {
/* 658 */       clearImpl(desc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/CursorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */