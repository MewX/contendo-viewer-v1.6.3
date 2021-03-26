/*      */ package org.apache.batik.transcoder.wmf.tosvg;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.TexturePaint;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.TextLayout;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.RoundRectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.text.AttributedCharacterIterator;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Stack;
/*      */ import org.apache.batik.ext.awt.geom.Polygon2D;
/*      */ import org.apache.batik.ext.awt.geom.Polyline2D;
/*      */ import org.apache.batik.util.Platform;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WMFPainter
/*      */   extends AbstractWMFPainter
/*      */ {
/*      */   private static final int INPUT_BUFFER_SIZE = 30720;
/*   68 */   private static final Integer INTEGER_0 = Integer.valueOf(0); private float scale; private float scaleX; private float scaleY; private float conv;
/*      */   private float xOffset;
/*      */   private float yOffset;
/*      */   private float vpX;
/*      */   private float vpY;
/*      */   private float vpW;
/*      */   private float vpH;
/*      */   private Color frgdColor;
/*      */   private Color bkgdColor;
/*      */   private boolean opaque = false;
/*      */   private transient boolean firstEffectivePaint = true;
/*   79 */   private static BasicStroke solid = new BasicStroke(1.0F, 0, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   84 */   private static BasicStroke textSolid = new BasicStroke(1.0F, 0, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   89 */   private transient ImageObserver observer = new ImageObserver()
/*      */     {
/*      */       public boolean imageUpdate(Image img, int flags, int x, int y, int width, int height) {
/*   92 */         return false;
/*      */       }
/*      */     };
/*      */ 
/*      */   
/*      */   private transient BufferedInputStream bufStream;
/*      */ 
/*      */   
/*      */   public WMFPainter(WMFRecordStore currentStore, float scale) {
/*  101 */     this(currentStore, 0, 0, scale);
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
/*      */   public void paint(Graphics g) {
/*  133 */     float fontHeight = 10.0F;
/*  134 */     float fontAngle = 0.0F;
/*  135 */     float penWidth = 0.0F;
/*  136 */     float startX = 0.0F;
/*  137 */     float startY = 0.0F;
/*  138 */     int brushObject = -1;
/*  139 */     int penObject = -1;
/*  140 */     int fontObject = -1;
/*  141 */     Font font = null;
/*      */     
/*  143 */     Stack<Float> dcStack = new Stack();
/*      */     
/*  145 */     int numRecords = this.currentStore.getNumRecords();
/*  146 */     int numObjects = this.currentStore.getNumObjects();
/*  147 */     this.vpX = this.currentStore.getVpX() * this.scale;
/*  148 */     this.vpY = this.currentStore.getVpY() * this.scale;
/*  149 */     this.vpW = this.currentStore.getVpW() * this.scale;
/*  150 */     this.vpH = this.currentStore.getVpH() * this.scale;
/*      */     
/*  152 */     if (!this.currentStore.isReading()) {
/*      */ 
/*      */       
/*  155 */       g.setPaintMode();
/*      */ 
/*      */ 
/*      */       
/*  159 */       Graphics2D g2d = (Graphics2D)g;
/*  160 */       g2d.setStroke(solid);
/*      */       
/*  162 */       brushObject = -1;
/*  163 */       penObject = -1;
/*  164 */       fontObject = -1;
/*  165 */       this.frgdColor = null;
/*  166 */       this.bkgdColor = Color.white;
/*  167 */       for (int i = 0; i < numObjects; i++) {
/*  168 */         GdiObject gdiObj = this.currentStore.getObject(i);
/*  169 */         gdiObj.clear();
/*      */       } 
/*      */       
/*  172 */       float w = this.vpW;
/*  173 */       float h = this.vpH;
/*      */       
/*  175 */       g2d.setColor(Color.black);
/*      */       
/*  177 */       for (int iRec = 0; iRec < numRecords; iRec++) {
/*  178 */         GdiObject gdiObj; int gdiIndex, i1; float size; int n; Font f; int numPolygons, m; float endX; int count; float x1; double left; int mode; float f1; int height, rop, objIndex, penStyle, brushStyle, charset, pts[]; float arrayOfFloat1[], endY, _xpts[], y1, x2; Paint paint; float f3; int width; float f2; byte[] bitmap; Color newClr, clr; int italic, ip, offset; float[] arrayOfFloat2; Line2D.Float line; float _ypts[], f7, f6; double top; boolean ok; float f5; int sy; float f4; int weight; List<Polygon2D> v; int i3; Polygon2D polygon2D; int k; Polyline2D pol; float y2, f8; int sx, i2, style, j; Rectangle2D.Float rec; float x3; Ellipse2D.Float el; double right; float f9, dy; int i4; String face; float y3; Paint paint1; float dx, f10; int d; RoundRectangle2D roundRectangle2D; double bottom; boolean bool1; float heightDst, f11; int i5; float widthDst; Font font1; double xstart; byte[] arrayOfByte1; int underline; BufferedImage img; int strikeOut; double ystart; int orient, escape; double xend; WMFFont wf; double yend, cx, cy, startAngle, endAngle, extentAngle; Arc2D.Double arc; MetaRecord mr = this.currentStore.getRecord(iRec);
/*      */         
/*  180 */         switch (mr.functionId) {
/*      */           case 523:
/*  182 */             this.currentStore.setVpX(this.vpX = -(mr.elementAt(0)));
/*  183 */             this.currentStore.setVpY(this.vpY = -(mr.elementAt(1)));
/*  184 */             this.vpX *= this.scale;
/*  185 */             this.vpY *= this.scale;
/*      */             break;
/*      */           
/*      */           case 0:
/*      */           case 524:
/*  190 */             this.vpW = mr.elementAt(0);
/*  191 */             this.vpH = mr.elementAt(1);
/*      */ 
/*      */             
/*  194 */             this.scaleX = this.scale;
/*  195 */             this.scaleY = this.scale;
/*  196 */             solid = new BasicStroke(this.scaleX * 2.0F, 0, 1);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 762:
/*  216 */             i1 = 0;
/*  217 */             penStyle = mr.elementAt(0);
/*      */             
/*  219 */             if (penStyle == 5) {
/*  220 */               Color color = Color.white;
/*      */               
/*  222 */               i1 = addObjectAt(this.currentStore, 4, color, i1); break;
/*      */             } 
/*  224 */             penWidth = mr.elementAt(4);
/*  225 */             setStroke(g2d, penStyle, penWidth, this.scaleX);
/*  226 */             newClr = new Color(mr.elementAt(1), mr.elementAt(2), mr.elementAt(3));
/*      */ 
/*      */             
/*  229 */             i1 = addObjectAt(this.currentStore, 1, newClr, i1);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 764:
/*  236 */             i1 = 0;
/*  237 */             brushStyle = mr.elementAt(0);
/*  238 */             clr = new Color(mr.elementAt(1), mr.elementAt(2), mr.elementAt(3));
/*      */ 
/*      */             
/*  241 */             if (brushStyle == 0) {
/*  242 */               i1 = addObjectAt(this.currentStore, 2, clr, i1); break;
/*  243 */             }  if (brushStyle == 2) {
/*  244 */               Paint paint2; int hatch = mr.elementAt(4);
/*      */               
/*  246 */               if (!this.opaque) {
/*  247 */                 paint2 = TextureFactory.getInstance().getTexture(hatch, clr);
/*      */               } else {
/*  249 */                 paint2 = TextureFactory.getInstance().getTexture(hatch, clr, this.bkgdColor);
/*      */               } 
/*  251 */               if (paint2 != null) {
/*  252 */                 i1 = addObjectAt(this.currentStore, 2, paint2, i1); break;
/*      */               } 
/*  254 */               clr = Color.black;
/*  255 */               i1 = addObjectAt(this.currentStore, 5, clr, i1);
/*      */               break;
/*      */             } 
/*  258 */             clr = Color.black;
/*  259 */             i1 = addObjectAt(this.currentStore, 5, clr, i1);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 763:
/*  266 */             size = (int)(this.scaleY * mr.elementAt(0));
/*  267 */             charset = mr.elementAt(3);
/*      */             
/*  269 */             italic = mr.elementAt(1);
/*  270 */             weight = mr.elementAt(2);
/*  271 */             style = (italic > 0) ? 2 : 0;
/*  272 */             style |= (weight > 400) ? 1 : 0;
/*      */             
/*  274 */             face = ((MetaRecord.StringRecord)mr).text;
/*      */             
/*  276 */             d = 0;
/*  277 */             while (d < face.length() && (Character.isLetterOrDigit(face.charAt(d)) || Character.isWhitespace(face.charAt(d))))
/*      */             {
/*      */               
/*  280 */               d++;
/*      */             }
/*  282 */             if (d > 0) {
/*  283 */               face = face.substring(0, d);
/*      */             } else {
/*  285 */               face = "System";
/*      */             } 
/*      */             
/*  288 */             if (size < 0.0F) {
/*  289 */               size = -size;
/*      */             }
/*  291 */             i5 = 0;
/*      */             
/*  293 */             fontHeight = size;
/*      */             
/*  295 */             font1 = new Font(face, style, (int)size);
/*  296 */             font1 = font1.deriveFont(size);
/*      */             
/*  298 */             underline = mr.elementAt(4);
/*  299 */             strikeOut = mr.elementAt(5);
/*  300 */             orient = mr.elementAt(6);
/*  301 */             escape = mr.elementAt(7);
/*      */             
/*  303 */             wf = new WMFFont(font1, charset, underline, strikeOut, italic, weight, orient, escape);
/*      */             
/*  305 */             i5 = addObjectAt(this.currentStore, 3, wf, i5);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 248:
/*      */           case 505:
/*      */           case 765:
/*      */           case 1790:
/*      */           case 1791:
/*  314 */             n = addObjectAt(this.currentStore, 6, INTEGER_0, 0);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 247:
/*  319 */             n = addObjectAt(this.currentStore, 8, INTEGER_0, 0);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 301:
/*  331 */             gdiIndex = mr.elementAt(0);
/*  332 */             if ((gdiIndex & Integer.MIN_VALUE) != 0) {
/*      */               break;
/*      */             }
/*  335 */             if (gdiIndex >= numObjects) {
/*  336 */               gdiIndex -= numObjects;
/*      */               
/*  338 */               switch (gdiIndex) {
/*      */                 case 5:
/*  340 */                   brushObject = -1;
/*      */                   break;
/*      */                 case 8:
/*  343 */                   penObject = -1;
/*      */                   break;
/*      */               } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*  363 */             gdiObj = this.currentStore.getObject(gdiIndex);
/*  364 */             if (!gdiObj.used) {
/*      */               break;
/*      */             }
/*  367 */             switch (gdiObj.type) {
/*      */               case 1:
/*  369 */                 g2d.setColor((Color)gdiObj.obj);
/*  370 */                 penObject = gdiIndex;
/*      */                 break;
/*      */               case 2:
/*  373 */                 if (gdiObj.obj instanceof Color) {
/*  374 */                   g2d.setColor((Color)gdiObj.obj);
/*  375 */                 } else if (gdiObj.obj instanceof Paint) {
/*  376 */                   g2d.setPaint((Paint)gdiObj.obj);
/*      */                 } else {
/*  378 */                   g2d.setPaint(getPaint((byte[])gdiObj.obj));
/*      */                 } 
/*  380 */                 brushObject = gdiIndex;
/*      */                 break;
/*      */               case 3:
/*  383 */                 this.wmfFont = (WMFFont)gdiObj.obj;
/*  384 */                 f = this.wmfFont.font;
/*  385 */                 g2d.setFont(f);
/*  386 */                 fontObject = gdiIndex;
/*      */                 break;
/*      */               
/*      */               case 4:
/*  390 */                 penObject = -1;
/*      */                 break;
/*      */               case 5:
/*  393 */                 brushObject = -1;
/*      */                 break;
/*      */             } 
/*      */             
/*      */             break;
/*      */           case 496:
/*  399 */             gdiIndex = mr.elementAt(0);
/*  400 */             gdiObj = this.currentStore.getObject(gdiIndex);
/*  401 */             if (gdiIndex == brushObject) {
/*  402 */               brushObject = -1;
/*  403 */             } else if (gdiIndex == penObject) {
/*  404 */               penObject = -1;
/*  405 */             } else if (gdiIndex == fontObject) {
/*  406 */               fontObject = -1;
/*      */             } 
/*  408 */             gdiObj.clear();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 1336:
/*  413 */             numPolygons = mr.elementAt(0);
/*  414 */             pts = new int[numPolygons];
/*  415 */             for (ip = 0; ip < numPolygons; ip++) {
/*  416 */               pts[ip] = mr.elementAt(ip + 1);
/*      */             }
/*      */             
/*  419 */             offset = numPolygons + 1;
/*  420 */             v = new ArrayList(numPolygons);
/*  421 */             for (j = 0; j < numPolygons; j++) {
/*  422 */               int i6 = pts[j];
/*  423 */               float[] xpts = new float[i6];
/*  424 */               float[] ypts = new float[i6];
/*  425 */               for (int i7 = 0; i7 < i6; i7++) {
/*  426 */                 xpts[i7] = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(offset + i7 * 2));
/*  427 */                 ypts[i7] = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(offset + i7 * 2 + 1));
/*      */               } 
/*      */               
/*  430 */               offset += i6 * 2;
/*  431 */               Polygon2D polygon2D1 = new Polygon2D(xpts, ypts, i6);
/*  432 */               v.add(polygon2D1);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  438 */             if (brushObject >= 0) {
/*  439 */               setBrushPaint(this.currentStore, g2d, brushObject);
/*  440 */               fillPolyPolygon(g2d, v);
/*  441 */               this.firstEffectivePaint = false;
/*      */             } 
/*      */             
/*  444 */             if (penObject >= 0) {
/*  445 */               setPenColor(this.currentStore, g2d, penObject);
/*  446 */               drawPolyPolygon(g2d, v);
/*  447 */               this.firstEffectivePaint = false;
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 804:
/*  454 */             m = mr.elementAt(0);
/*  455 */             arrayOfFloat1 = new float[m];
/*  456 */             arrayOfFloat2 = new float[m];
/*  457 */             for (i3 = 0; i3 < m; i3++) {
/*  458 */               arrayOfFloat1[i3] = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(i3 * 2 + 1));
/*  459 */               arrayOfFloat2[i3] = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(i3 * 2 + 2));
/*      */             } 
/*  461 */             polygon2D = new Polygon2D(arrayOfFloat1, arrayOfFloat2, m);
/*  462 */             paint(brushObject, penObject, (Shape)polygon2D, g2d);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 532:
/*  467 */             startX = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(0));
/*  468 */             startY = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(1));
/*      */             break;
/*      */ 
/*      */           
/*      */           case 531:
/*  473 */             endX = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(0));
/*  474 */             endY = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(1));
/*      */             
/*  476 */             line = new Line2D.Float(startX, startY, endX, endY);
/*  477 */             paintWithPen(penObject, line, g2d);
/*  478 */             startX = endX;
/*  479 */             startY = endY;
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 805:
/*  485 */             count = mr.elementAt(0);
/*  486 */             _xpts = new float[count];
/*  487 */             _ypts = new float[count];
/*  488 */             for (k = 0; k < count; k++) {
/*  489 */               _xpts[k] = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(k * 2 + 1));
/*  490 */               _ypts[k] = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(k * 2 + 2));
/*      */             } 
/*  492 */             pol = new Polyline2D(_xpts, _ypts, count);
/*  493 */             paintWithPen(penObject, (Shape)pol, g2d);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 1051:
/*  500 */             x1 = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(0));
/*  501 */             f7 = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(2));
/*  502 */             y1 = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(1));
/*  503 */             y2 = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(3));
/*      */             
/*  505 */             rec = new Rectangle2D.Float(x1, y1, f7 - x1, y2 - y1);
/*  506 */             paint(brushObject, penObject, rec, g2d);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 1564:
/*  513 */             x1 = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(0));
/*  514 */             f7 = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(2));
/*  515 */             x3 = this.scaleX * mr.elementAt(4);
/*  516 */             y1 = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(1));
/*  517 */             y2 = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(3));
/*  518 */             y3 = this.scaleY * mr.elementAt(5);
/*      */             
/*  520 */             roundRectangle2D = new RoundRectangle2D.Float(x1, y1, f7 - x1, y2 - y1, x3, y3);
/*      */ 
/*      */             
/*  523 */             paint(brushObject, penObject, roundRectangle2D, g2d);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 1048:
/*  529 */             x1 = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(0));
/*  530 */             x2 = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(2));
/*  531 */             f6 = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(1));
/*  532 */             y2 = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(3));
/*      */             
/*  534 */             el = new Ellipse2D.Float(x1, f6, x2 - x1, y2 - f6);
/*  535 */             paint(brushObject, penObject, el, g2d);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 302:
/*  540 */             this.currentHorizAlign = WMFUtilities.getHorizontalAlignment(mr.elementAt(0));
/*      */             
/*  542 */             this.currentVertAlign = WMFUtilities.getVerticalAlignment(mr.elementAt(0));
/*      */             break;
/*      */ 
/*      */           
/*      */           case 521:
/*  547 */             this.frgdColor = new Color(mr.elementAt(0), mr.elementAt(1), mr.elementAt(2));
/*      */ 
/*      */             
/*  550 */             g2d.setColor(this.frgdColor);
/*      */             break;
/*      */           
/*      */           case 513:
/*  554 */             this.bkgdColor = new Color(mr.elementAt(0), mr.elementAt(1), mr.elementAt(2));
/*      */ 
/*      */             
/*  557 */             g2d.setColor(this.bkgdColor);
/*      */             break;
/*      */           
/*      */           case 2610:
/*      */             try {
/*  562 */               byte[] bstr = ((MetaRecord.ByteRecord)mr).bstr;
/*  563 */               String sr = WMFUtilities.decodeString(this.wmfFont, bstr);
/*      */               
/*  565 */               float x = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(0));
/*  566 */               float y = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(1));
/*  567 */               if (this.frgdColor != null) {
/*  568 */                 g2d.setColor(this.frgdColor);
/*      */               } else {
/*  570 */                 g2d.setColor(Color.black);
/*      */               } 
/*      */               
/*  573 */               FontRenderContext frc = g2d.getFontRenderContext();
/*      */               
/*  575 */               Point2D.Double pen = new Point2D.Double(0.0D, 0.0D);
/*  576 */               GeneralPath gp = new GeneralPath(1);
/*  577 */               TextLayout layout = new TextLayout(sr, g2d.getFont(), frc);
/*      */               
/*  579 */               int flag = mr.elementAt(2);
/*  580 */               int i6 = 0, i7 = 0, i8 = 0, i9 = 0;
/*  581 */               boolean clipped = false;
/*  582 */               Shape clip = null;
/*      */               
/*  584 */               if ((flag & 0x4) != 0) {
/*  585 */                 clipped = true;
/*  586 */                 i6 = mr.elementAt(3);
/*  587 */                 i7 = mr.elementAt(4);
/*  588 */                 i8 = mr.elementAt(5);
/*  589 */                 i9 = mr.elementAt(6);
/*  590 */                 clip = g2d.getClip();
/*  591 */                 g2d.setClip(i6, i7, i8, i9);
/*      */               } 
/*      */               
/*  594 */               this.firstEffectivePaint = false;
/*  595 */               y += getVerticalAlignmentValue(layout, this.currentVertAlign);
/*      */               
/*  597 */               drawString(flag, g2d, getCharacterIterator(g2d, sr, this.wmfFont, this.currentHorizAlign), x, y, layout, this.wmfFont, this.currentHorizAlign);
/*      */ 
/*      */               
/*  600 */               if (clipped) {
/*  601 */                 g2d.setClip(clip);
/*      */               }
/*  603 */             } catch (Exception exception) {}
/*      */             break;
/*      */ 
/*      */           
/*      */           case 1313:
/*      */           case 1583:
/*      */             try {
/*  610 */               byte[] bstr = ((MetaRecord.ByteRecord)mr).bstr;
/*  611 */               String sr = WMFUtilities.decodeString(this.wmfFont, bstr);
/*      */               
/*  613 */               float x = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(0));
/*  614 */               float y = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(1));
/*  615 */               if (this.frgdColor != null) {
/*  616 */                 g2d.setColor(this.frgdColor);
/*      */               } else {
/*  618 */                 g2d.setColor(Color.black);
/*      */               } 
/*      */               
/*  621 */               FontRenderContext frc = g2d.getFontRenderContext();
/*      */               
/*  623 */               Point2D.Double pen = new Point2D.Double(0.0D, 0.0D);
/*  624 */               GeneralPath gp = new GeneralPath(1);
/*  625 */               TextLayout layout = new TextLayout(sr, g2d.getFont(), frc);
/*      */               
/*  627 */               this.firstEffectivePaint = false;
/*  628 */               y += getVerticalAlignmentValue(layout, this.currentVertAlign);
/*      */               
/*  630 */               drawString(-1, g2d, getCharacterIterator(g2d, sr, this.wmfFont), x, y, layout, this.wmfFont, this.currentHorizAlign);
/*      */             
/*      */             }
/*  633 */             catch (Exception exception) {}
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 2071:
/*      */           case 2074:
/*      */           case 2096:
/*  643 */             left = (this.scaleX * (this.vpX + this.xOffset + mr.elementAt(0)));
/*  644 */             top = (this.scaleY * (this.vpY + this.yOffset + mr.elementAt(1)));
/*  645 */             right = (this.scaleX * (this.vpX + this.xOffset + mr.elementAt(2)));
/*  646 */             bottom = (this.scaleY * (this.vpY + this.yOffset + mr.elementAt(3)));
/*  647 */             xstart = (this.scaleX * (this.vpX + this.xOffset + mr.elementAt(4)));
/*  648 */             ystart = (this.scaleY * (this.vpY + this.yOffset + mr.elementAt(5)));
/*  649 */             xend = (this.scaleX * (this.vpX + this.xOffset + mr.elementAt(6)));
/*  650 */             yend = (this.scaleY * (this.vpY + this.yOffset + mr.elementAt(7)));
/*  651 */             setBrushPaint(this.currentStore, g2d, brushObject);
/*      */             
/*  653 */             cx = left + (right - left) / 2.0D;
/*  654 */             cy = top + (bottom - top) / 2.0D;
/*  655 */             startAngle = -Math.toDegrees(Math.atan2(ystart - cy, xstart - cx));
/*  656 */             endAngle = -Math.toDegrees(Math.atan2(yend - cy, xend - cx));
/*      */             
/*  658 */             extentAngle = endAngle - startAngle;
/*  659 */             if (extentAngle < 0.0D) {
/*  660 */               extentAngle += 360.0D;
/*      */             }
/*  662 */             if (startAngle < 0.0D) {
/*  663 */               startAngle += 360.0D;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  668 */             switch (mr.functionId) {
/*      */               case 2071:
/*  670 */                 arc = new Arc2D.Double(left, top, right - left, bottom - top, startAngle, extentAngle, 0);
/*      */ 
/*      */ 
/*      */                 
/*  674 */                 g2d.draw(arc);
/*      */                 break;
/*      */               case 2074:
/*  677 */                 arc = new Arc2D.Double(left, top, right - left, bottom - top, startAngle, extentAngle, 2);
/*      */ 
/*      */ 
/*      */                 
/*  681 */                 paint(brushObject, penObject, arc, g2d);
/*      */                 break;
/*      */               
/*      */               case 2096:
/*  685 */                 arc = new Arc2D.Double(left, top, right - left, bottom - top, startAngle, extentAngle, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  690 */                 paint(brushObject, penObject, arc, g2d); break;
/*      */             } 
/*  692 */             this.firstEffectivePaint = false;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 30:
/*  697 */             dcStack.push(Float.valueOf(penWidth));
/*  698 */             dcStack.push(Float.valueOf(startX));
/*  699 */             dcStack.push(Float.valueOf(startY));
/*  700 */             dcStack.push(Integer.valueOf(brushObject));
/*  701 */             dcStack.push(Integer.valueOf(penObject));
/*  702 */             dcStack.push(Integer.valueOf(fontObject));
/*  703 */             dcStack.push(this.frgdColor);
/*  704 */             dcStack.push(this.bkgdColor);
/*      */             break;
/*      */           
/*      */           case 295:
/*  708 */             this.bkgdColor = (Color)dcStack.pop();
/*  709 */             this.frgdColor = (Color)dcStack.pop();
/*  710 */             fontObject = ((Integer)dcStack.pop()).intValue();
/*  711 */             penObject = ((Integer)dcStack.pop()).intValue();
/*  712 */             brushObject = ((Integer)dcStack.pop()).intValue();
/*  713 */             startY = ((Float)dcStack.pop()).floatValue();
/*  714 */             startX = ((Float)dcStack.pop()).floatValue();
/*  715 */             penWidth = ((Float)dcStack.pop()).floatValue();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 4096:
/*      */             try {
/*  721 */               setPenColor(this.currentStore, g2d, penObject);
/*      */               
/*  723 */               int pointCount = mr.elementAt(0);
/*  724 */               int bezierCount = (pointCount - 1) / 3;
/*  725 */               float _startX = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(1));
/*  726 */               float _startY = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(2));
/*      */               
/*  728 */               GeneralPath gp = new GeneralPath(1);
/*  729 */               gp.moveTo(_startX, _startY);
/*      */               
/*  731 */               for (int i6 = 0; i6 < bezierCount; i6++) {
/*  732 */                 int j6 = i6 * 6;
/*  733 */                 float cp1X = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(j6 + 3));
/*  734 */                 float cp1Y = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(j6 + 4));
/*      */                 
/*  736 */                 float cp2X = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(j6 + 5));
/*  737 */                 float cp2Y = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(j6 + 6));
/*      */                 
/*  739 */                 float f12 = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(j6 + 7));
/*  740 */                 float f13 = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(j6 + 8));
/*      */                 
/*  742 */                 gp.curveTo(cp1X, cp1Y, cp2X, cp2Y, f12, f13);
/*  743 */                 _startX = f12;
/*  744 */                 _startY = f13;
/*      */               } 
/*      */               
/*  747 */               g2d.setStroke(solid);
/*  748 */               g2d.draw(gp);
/*  749 */               this.firstEffectivePaint = false;
/*      */             }
/*  751 */             catch (Exception exception) {}
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 258:
/*  771 */             mode = mr.elementAt(0);
/*  772 */             this.opaque = (mode == 2);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 260:
/*  779 */             f1 = mr.ElementAt(0).intValue();
/*  780 */             paint = null;
/*  781 */             ok = false;
/*  782 */             if (f1 == 66.0F) {
/*  783 */               paint = Color.black;
/*  784 */               ok = true;
/*  785 */             } else if (f1 == 1.6711778E7F) {
/*  786 */               paint = Color.white;
/*  787 */               ok = true;
/*  788 */             } else if (f1 == 1.5728673E7F && 
/*  789 */               brushObject >= 0) {
/*  790 */               paint = getStoredPaint(this.currentStore, brushObject);
/*  791 */               ok = true;
/*      */             } 
/*      */ 
/*      */             
/*  795 */             if (ok) {
/*  796 */               if (paint != null) {
/*  797 */                 g2d.setPaint(paint); break;
/*      */               } 
/*  799 */               setBrushPaint(this.currentStore, g2d, brushObject);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 1565:
/*  806 */             f1 = mr.elementAt(0);
/*  807 */             f3 = this.scaleY * mr.elementAt(1);
/*  808 */             f5 = this.scaleX * mr.elementAt(2);
/*  809 */             f8 = this.scaleX * (this.vpX + this.xOffset + mr.elementAt(3));
/*  810 */             f9 = this.scaleY * (this.vpY + this.yOffset + mr.elementAt(4));
/*      */             
/*  812 */             paint1 = null;
/*  813 */             bool1 = false;
/*  814 */             if (f1 == 66.0F) {
/*  815 */               paint1 = Color.black;
/*  816 */               bool1 = true;
/*  817 */             } else if (f1 == 1.6711778E7F) {
/*  818 */               paint1 = Color.white;
/*  819 */               bool1 = true;
/*  820 */             } else if (f1 == 1.5728673E7F && 
/*  821 */               brushObject >= 0) {
/*  822 */               paint1 = getStoredPaint(this.currentStore, brushObject);
/*  823 */               bool1 = true;
/*      */             } 
/*      */             
/*  826 */             if (bool1) {
/*  827 */               Color oldClr = g2d.getColor();
/*  828 */               if (paint1 != null) {
/*  829 */                 g2d.setPaint(paint1);
/*      */               } else {
/*  831 */                 setBrushPaint(this.currentStore, g2d, brushObject);
/*      */               } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  838 */               Rectangle2D.Float float_ = new Rectangle2D.Float(f8, f9, f5, f3);
/*  839 */               g2d.fill(float_);
/*  840 */               g2d.setColor(oldClr);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case 2881:
/*  846 */             height = mr.elementAt(1);
/*  847 */             width = mr.elementAt(2);
/*  848 */             sy = mr.elementAt(3);
/*  849 */             sx = mr.elementAt(4);
/*  850 */             dy = this.conv * this.currentStore.getVpWFactor() * (this.vpY + this.yOffset + mr.elementAt(7));
/*  851 */             dx = this.conv * this.currentStore.getVpHFactor() * (this.vpX + this.xOffset + mr.elementAt(8));
/*  852 */             heightDst = mr.elementAt(5);
/*  853 */             widthDst = mr.elementAt(6);
/*  854 */             widthDst = widthDst * this.conv * this.currentStore.getVpWFactor();
/*  855 */             heightDst = heightDst * this.conv * this.currentStore.getVpHFactor();
/*  856 */             arrayOfByte1 = ((MetaRecord.ByteRecord)mr).bstr;
/*      */             
/*  858 */             img = getImage(arrayOfByte1, width, height);
/*  859 */             if (img != null) {
/*  860 */               g2d.drawImage(img, (int)dx, (int)dy, (int)(dx + widthDst), (int)(dy + heightDst), sx, sy, sx + width, sy + height, this.bkgdColor, this.observer);
/*      */             }
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 3907:
/*  867 */             height = mr.elementAt(1);
/*  868 */             width = mr.elementAt(2);
/*  869 */             sy = mr.elementAt(3);
/*  870 */             sx = mr.elementAt(4);
/*  871 */             dy = this.conv * this.currentStore.getVpWFactor() * (this.vpY + this.yOffset + mr.elementAt(7));
/*      */             
/*  873 */             dx = this.conv * this.currentStore.getVpHFactor() * (this.vpX + this.xOffset + mr.elementAt(8));
/*      */             
/*  875 */             heightDst = mr.elementAt(5);
/*  876 */             widthDst = mr.elementAt(6);
/*  877 */             widthDst = widthDst * this.conv * this.currentStore.getVpWFactor();
/*  878 */             heightDst = heightDst * this.conv * this.currentStore.getVpHFactor();
/*  879 */             arrayOfByte1 = ((MetaRecord.ByteRecord)mr).bstr;
/*      */             
/*  881 */             img = getImage(arrayOfByte1, width, height);
/*  882 */             if (img != null) {
/*  883 */               if (this.opaque) {
/*  884 */                 g2d.drawImage(img, (int)dx, (int)dy, (int)(dx + widthDst), (int)(dy + heightDst), sx, sy, sx + width, sy + height, this.bkgdColor, this.observer);
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/*  889 */               g2d.drawImage(img, (int)dx, (int)dy, (int)(dx + widthDst), (int)(dy + heightDst), sx, sy, sx + width, sy + height, this.observer);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 2368:
/*  898 */             rop = mr.ElementAt(0).intValue();
/*  899 */             f2 = mr.ElementAt(1).intValue() * this.conv * this.currentStore.getVpWFactor();
/*      */             
/*  901 */             f4 = mr.ElementAt(2).intValue() * this.conv * this.currentStore.getVpHFactor();
/*      */             
/*  903 */             i2 = mr.ElementAt(3).intValue();
/*  904 */             i4 = mr.ElementAt(4).intValue();
/*  905 */             f10 = this.conv * this.currentStore.getVpWFactor() * (this.vpY + this.yOffset + mr.ElementAt(5).intValue());
/*      */ 
/*      */             
/*  908 */             f11 = this.conv * this.currentStore.getVpHFactor() * (this.vpX + this.xOffset + mr.ElementAt(6).intValue());
/*      */ 
/*      */             
/*  911 */             if (mr instanceof MetaRecord.ByteRecord) {
/*  912 */               byte[] arrayOfByte = ((MetaRecord.ByteRecord)mr).bstr;
/*      */               
/*  914 */               BufferedImage bufferedImage = getImage(arrayOfByte);
/*  915 */               if (bufferedImage != null) {
/*  916 */                 int withSrc = bufferedImage.getWidth();
/*  917 */                 int heightSrc = bufferedImage.getHeight();
/*  918 */                 if (this.opaque) {
/*  919 */                   g2d.drawImage(bufferedImage, (int)f11, (int)f10, (int)(f11 + f4), (int)(f10 + f2), i4, i2, i4 + withSrc, i2 + heightSrc, this.bkgdColor, this.observer);
/*      */ 
/*      */ 
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/*  928 */                 g2d.drawImage(bufferedImage, (int)f11, (int)f10, (int)(f11 + f4), (int)(f10 + f2), i4, i2, i4 + withSrc, i2 + heightSrc, this.observer);
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */ 
/*      */             
/*  937 */             if (this.opaque) {
/*  938 */               Color col = g2d.getColor();
/*  939 */               g2d.setColor(this.bkgdColor);
/*  940 */               g2d.fill(new Rectangle2D.Float(f11, f10, f4, f2));
/*      */               
/*  942 */               g2d.setColor(col);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 322:
/*  950 */             objIndex = 0;
/*  951 */             bitmap = ((MetaRecord.ByteRecord)mr).bstr;
/*  952 */             objIndex = addObjectAt(this.currentStore, 2, bitmap, objIndex);
/*      */             break;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Paint getPaint(byte[] bit) {
/*  978 */     Dimension d = getImageDimension(bit);
/*  979 */     BufferedImage img = getImage(bit);
/*  980 */     Rectangle2D rec = new Rectangle2D.Float(0.0F, 0.0F, d.width, d.height);
/*  981 */     TexturePaint paint = new TexturePaint(img, rec);
/*  982 */     return paint;
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
/*      */   private void drawString(int flag, Graphics2D g2d, AttributedCharacterIterator ati, float x, float y, TextLayout layout, WMFFont wmfFont, int align) {
/*  996 */     if (wmfFont.escape == 0) {
/*  997 */       if (flag != -1) {
/*  998 */         fillTextBackground(-1, flag, g2d, x, y, 0.0F, layout);
/*      */       }
/* 1000 */       float width = (float)layout.getBounds().getWidth();
/* 1001 */       if (align == 6) {
/* 1002 */         g2d.drawString(ati, x - width / 2.0F, y);
/* 1003 */       } else if (align == 2) {
/* 1004 */         g2d.drawString(ati, x - width, y);
/*      */       } else {
/* 1006 */         g2d.drawString(ati, x, y);
/*      */       } 
/*      */     } else {
/* 1009 */       AffineTransform tr = g2d.getTransform();
/* 1010 */       float angle = -((float)(wmfFont.escape * Math.PI / 1800.0D));
/*      */       
/* 1012 */       float width = (float)layout.getBounds().getWidth();
/* 1013 */       float height = (float)layout.getBounds().getHeight();
/* 1014 */       if (align == 6) {
/* 1015 */         g2d.translate((-width / 2.0F), (height / 2.0F));
/* 1016 */         g2d.rotate(angle, (x - width / 2.0F), y);
/* 1017 */       } else if (align == 2) {
/* 1018 */         g2d.translate((-width / 2.0F), (height / 2.0F));
/* 1019 */         g2d.rotate(angle, (x - width), y);
/*      */       } else {
/* 1021 */         g2d.translate(0.0D, (height / 2.0F));
/* 1022 */         g2d.rotate(angle, x, y);
/*      */       } 
/* 1024 */       if (flag != -1) {
/* 1025 */         fillTextBackground(align, flag, g2d, x, y, width, layout);
/*      */       }
/* 1027 */       Stroke _st = g2d.getStroke();
/* 1028 */       g2d.setStroke(textSolid);
/* 1029 */       g2d.drawString(ati, x, y);
/* 1030 */       g2d.setStroke(_st);
/* 1031 */       g2d.setTransform(tr);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void fillTextBackground(int align, int flag, Graphics2D g2d, float x, float y, float width, TextLayout layout) {
/* 1038 */     float _x = x;
/* 1039 */     if (align == 6) {
/* 1040 */       _x = x - width / 2.0F;
/* 1041 */     } else if (align == 2) {
/* 1042 */       _x = x - width;
/*      */     } 
/*      */     
/* 1045 */     if ((flag & 0x2) != 0) {
/* 1046 */       Color c = g2d.getColor();
/* 1047 */       AffineTransform tr = g2d.getTransform();
/* 1048 */       g2d.setColor(this.bkgdColor);
/* 1049 */       g2d.translate(_x, y);
/* 1050 */       g2d.fill(layout.getBounds());
/* 1051 */       g2d.setColor(c);
/* 1052 */       g2d.setTransform(tr);
/* 1053 */     } else if (this.opaque) {
/* 1054 */       Color c = g2d.getColor();
/* 1055 */       AffineTransform tr = g2d.getTransform();
/* 1056 */       g2d.setColor(this.bkgdColor);
/* 1057 */       g2d.translate(_x, y);
/* 1058 */       g2d.fill(layout.getBounds());
/* 1059 */       g2d.setColor(c);
/* 1060 */       g2d.setTransform(tr);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPolyPolygon(Graphics2D g2d, List pols) {
/* 1067 */     for (Object pol1 : pols) {
/* 1068 */       Polygon2D pol = (Polygon2D)pol1;
/* 1069 */       g2d.draw((Shape)pol);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fillPolyPolygon(Graphics2D g2d, List<Polygon2D> pols) {
/* 1078 */     if (pols.size() == 1) {
/* 1079 */       g2d.fill((Shape)pols.get(0));
/*      */     } else {
/* 1081 */       GeneralPath path = new GeneralPath(0);
/* 1082 */       for (Polygon2D pol1 : pols) {
/* 1083 */         Polygon2D pol = pol1;
/* 1084 */         path.append((Shape)pol, false);
/*      */       } 
/* 1086 */       g2d.fill(path);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setStroke(Graphics2D g2d, int penStyle, float penWidth, float scale) {
/* 1092 */     if (penWidth == 0.0F) {
/* 1093 */       _width = 1.0F;
/*      */     } else {
/* 1095 */       _width = penWidth;
/*      */     } 
/* 1097 */     float _scale = Platform.getScreenResolution() / this.currentStore.getMetaFileUnitsPerInch();
/*      */ 
/*      */     
/* 1100 */     float factor = scale / _scale;
/* 1101 */     float _width = _width * _scale * factor;
/* 1102 */     _scale = this.currentStore.getWidthPixels() * 1.0F / 350.0F;
/*      */ 
/*      */     
/* 1105 */     if (penStyle == 0) {
/* 1106 */       BasicStroke stroke = new BasicStroke(_width, 0, 1);
/* 1107 */       g2d.setStroke(stroke);
/* 1108 */     } else if (penStyle == 2) {
/* 1109 */       float[] dash = { 1.0F * _scale, 5.0F * _scale };
/* 1110 */       BasicStroke stroke = new BasicStroke(_width, 0, 1, 10.0F * _scale, dash, 0.0F);
/* 1111 */       g2d.setStroke(stroke);
/* 1112 */     } else if (penStyle == 1) {
/* 1113 */       float[] dash = { 5.0F * _scale, 2.0F * _scale };
/* 1114 */       BasicStroke stroke = new BasicStroke(_width, 0, 1, 10.0F * _scale, dash, 0.0F);
/* 1115 */       g2d.setStroke(stroke);
/* 1116 */     } else if (penStyle == 3) {
/* 1117 */       float[] dash = { 5.0F * _scale, 2.0F * _scale, 1.0F * _scale, 2.0F * _scale };
/* 1118 */       BasicStroke stroke = new BasicStroke(_width, 0, 1, 10.0F * _scale, dash, 0.0F);
/* 1119 */       g2d.setStroke(stroke);
/* 1120 */     } else if (penStyle == 4) {
/* 1121 */       float[] dash = { 5.0F * _scale, 2.0F * _scale, 1.0F * _scale, 2.0F * _scale, 1.0F * _scale, 2.0F * _scale };
/* 1122 */       BasicStroke stroke = new BasicStroke(_width, 0, 1, 15.0F * _scale, dash, 0.0F);
/* 1123 */       g2d.setStroke(stroke);
/*      */     } else {
/* 1125 */       BasicStroke stroke = new BasicStroke(_width, 0, 1);
/* 1126 */       g2d.setStroke(stroke);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setPenColor(WMFRecordStore currentStore, Graphics2D g2d, int penObject) {
/* 1131 */     if (penObject >= 0) {
/* 1132 */       GdiObject gdiObj = currentStore.getObject(penObject);
/* 1133 */       g2d.setColor((Color)gdiObj.obj);
/* 1134 */       penObject = -1;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int getHorizontalAlignement(int align) {
/* 1139 */     int v = align;
/* 1140 */     v %= 24;
/* 1141 */     v %= 8;
/* 1142 */     if (v >= 6) return 6; 
/* 1143 */     if (v >= 2) return 2; 
/* 1144 */     return 0;
/*      */   }
/*      */   
/*      */   private void setBrushPaint(WMFRecordStore currentStore, Graphics2D g2d, int brushObject) {
/* 1148 */     if (brushObject >= 0) {
/* 1149 */       GdiObject gdiObj = currentStore.getObject(brushObject);
/* 1150 */       if (gdiObj.obj instanceof Color) {
/* 1151 */         g2d.setColor((Color)gdiObj.obj);
/* 1152 */       } else if (gdiObj.obj instanceof Paint) {
/* 1153 */         g2d.setPaint((Paint)gdiObj.obj);
/*      */       } else {
/* 1155 */         g2d.setPaint(getPaint((byte[])gdiObj.obj));
/*      */       } 
/* 1157 */       brushObject = -1;
/*      */     } 
/*      */   }
/*      */   
/*      */   private Paint getStoredPaint(WMFRecordStore currentStore, int object) {
/* 1162 */     if (object >= 0) {
/* 1163 */       GdiObject gdiObj = currentStore.getObject(object);
/* 1164 */       if (gdiObj.obj instanceof Paint) return (Paint)gdiObj.obj; 
/* 1165 */       return getPaint((byte[])gdiObj.obj);
/* 1166 */     }  return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void paint(int brushObject, int penObject, Shape shape, Graphics2D g2d) {
/* 1172 */     if (brushObject >= 0) {
/* 1173 */       Paint paint = getStoredPaint(this.currentStore, brushObject);
/* 1174 */       if (!this.firstEffectivePaint || !paint.equals(Color.white)) {
/* 1175 */         setBrushPaint(this.currentStore, g2d, brushObject);
/* 1176 */         g2d.fill(shape);
/* 1177 */         this.firstEffectivePaint = false;
/*      */       } 
/*      */     } 
/*      */     
/* 1181 */     if (penObject >= 0) {
/* 1182 */       Paint paint = getStoredPaint(this.currentStore, penObject);
/* 1183 */       if (!this.firstEffectivePaint || !paint.equals(Color.white)) {
/* 1184 */         setPenColor(this.currentStore, g2d, penObject);
/* 1185 */         g2d.draw(shape);
/* 1186 */         this.firstEffectivePaint = false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void paintWithPen(int penObject, Shape shape, Graphics2D g2d) {
/* 1194 */     if (penObject >= 0) {
/* 1195 */       Paint paint = getStoredPaint(this.currentStore, penObject);
/* 1196 */       if (!this.firstEffectivePaint || !paint.equals(Color.white)) {
/* 1197 */         setPenColor(this.currentStore, g2d, penObject);
/* 1198 */         g2d.draw(shape);
/* 1199 */         this.firstEffectivePaint = false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private float getVerticalAlignmentValue(TextLayout layout, int vertAlign) {
/* 1205 */     if (vertAlign == 8) return -layout.getDescent(); 
/* 1206 */     if (vertAlign == 0) return layout.getAscent(); 
/* 1207 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WMFRecordStore getRecordStore() {
/* 1214 */     return this.currentStore;
/*      */   }
/*      */   public WMFPainter(WMFRecordStore currentStore, int xOffset, int yOffset, float scale) {
/* 1217 */     this.bufStream = null;
/*      */     setRecordStore(currentStore);
/*      */     TextureFactory.getInstance().reset();
/*      */     this.conv = scale;
/*      */     this.xOffset = -xOffset;
/*      */     this.yOffset = -yOffset;
/*      */     this.scale = currentStore.getWidthPixels() / currentStore.getWidthUnits() * scale;
/*      */     this.scale = this.scale * currentStore.getWidthPixels() / currentStore.getVpW();
/*      */     float xfactor = currentStore.getVpW() / currentStore.getWidthPixels() * currentStore.getWidthUnits() / currentStore.getWidthPixels();
/*      */     float yfactor = currentStore.getVpH() / currentStore.getHeightPixels() * currentStore.getHeightUnits() / currentStore.getHeightPixels();
/*      */     this.xOffset *= xfactor;
/*      */     this.yOffset *= yfactor;
/*      */     this.scaleX = this.scale;
/*      */     this.scaleY = this.scale;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/WMFPainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */