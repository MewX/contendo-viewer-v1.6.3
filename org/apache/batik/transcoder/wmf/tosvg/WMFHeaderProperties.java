/*     */ package org.apache.batik.transcoder.wmf.tosvg;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.batik.ext.awt.geom.Polygon2D;
/*     */ import org.apache.batik.ext.awt.geom.Polyline2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMFHeaderProperties
/*     */   extends AbstractWMFReader
/*     */ {
/*  48 */   private static final Integer INTEGER_0 = Integer.valueOf(0); protected DataInputStream stream; private int _bleft; private int _bright; private int _btop; private int _bbottom; private int _bwidth; private int _bheight;
/*     */   private int _ileft;
/*     */   private int _iright;
/*     */   private int _itop;
/*     */   private int _ibottom;
/*  53 */   private float scale = 1.0F;
/*  54 */   private int startX = 0;
/*  55 */   private int startY = 0;
/*  56 */   private int currentHorizAlign = 0;
/*  57 */   private int currentVertAlign = 0;
/*  58 */   private WMFFont wf = null;
/*  59 */   private static final FontRenderContext fontCtx = new FontRenderContext(new AffineTransform(), false, true);
/*     */   
/*     */   private transient boolean firstEffectivePaint = true;
/*     */   
/*     */   public static final int PEN = 1;
/*     */   
/*     */   public static final int BRUSH = 2;
/*     */   
/*     */   public static final int FONT = 3;
/*     */   
/*     */   public static final int NULL_PEN = 4;
/*     */   public static final int NULL_BRUSH = 5;
/*     */   public static final int PALETTE = 6;
/*     */   public static final int OBJ_BITMAP = 7;
/*     */   public static final int OBJ_REGION = 8;
/*     */   
/*     */   public WMFHeaderProperties(File wmffile) throws IOException {
/*  76 */     reset();
/*  77 */     this.stream = new DataInputStream(new BufferedInputStream(new FileInputStream(wmffile)));
/*  78 */     read(this.stream);
/*  79 */     this.stream.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WMFHeaderProperties() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeResource() {
/*     */     try {
/*  90 */       if (this.stream != null) this.stream.close(); 
/*  91 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFile(File wmffile) throws IOException {
/*  98 */     this.stream = new DataInputStream(new BufferedInputStream(new FileInputStream(wmffile)));
/*  99 */     read(this.stream);
/* 100 */     this.stream.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 107 */     this.left = 0;
/* 108 */     this.right = 0;
/* 109 */     this.top = 1000;
/* 110 */     this.bottom = 1000;
/* 111 */     this.inch = 84;
/* 112 */     this._bleft = -1;
/* 113 */     this._bright = -1;
/* 114 */     this._btop = -1;
/* 115 */     this._bbottom = -1;
/* 116 */     this._ileft = -1;
/* 117 */     this._iright = -1;
/* 118 */     this._itop = -1;
/* 119 */     this._ibottom = -1;
/* 120 */     this._bwidth = -1;
/* 121 */     this._bheight = -1;
/* 122 */     this.vpW = -1;
/* 123 */     this.vpH = -1;
/* 124 */     this.vpX = 0;
/* 125 */     this.vpY = 0;
/* 126 */     this.startX = 0;
/* 127 */     this.startY = 0;
/* 128 */     this.scaleXY = 1.0F;
/* 129 */     this.firstEffectivePaint = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DataInputStream getStream() {
/* 135 */     return this.stream;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean readRecords(DataInputStream is) throws IOException {
/* 140 */     short functionId = 1;
/* 141 */     int recSize = 0;
/*     */     
/* 143 */     int brushObject = -1;
/* 144 */     int penObject = -1;
/* 145 */     int fontObject = -1;
/*     */ 
/*     */     
/* 148 */     while (functionId > 0) {
/* 149 */       int gdiIndex; GdiObject gdiObj; int mapmode, objIndex, align, penStyle, brushStyle, n, len, lfHeight, m, y, count, bot, k; float heightDst, height; int colorref, i3, read; float size; int i2, x, pts[]; float[] _xpts; int right, i1; float widthDst, width; int red, lenText; byte[] bstr; int lfWidth, ptCount; float[] _ypts; int top, left; float dy; int green, flag, i8; String sr; int escape, i7, offset, i6; Polygon2D polygon2D; int i; Polyline2D pol; int i5, i4; float dx; int blue, i12, i11, orient, i10; Rectangle2D.Float rec; int i9; Color color; boolean clipped; int i14, weight, i13; byte[] bitmap; int x1; TextLayout layout; int italic, i15, y1, i16, underline, x2, i17, strikeOut, y2, charset, lfOutPrecision; byte[] arrayOfByte1; int lfClipPrecision, i18, lfQuality; String str1; int lfPitchAndFamily; TextLayout textLayout1; int style, i20, i19, i21; byte[] lfFaceName; int i22; String face; int d, i23; Font f; WMFFont wf; recSize = readInt(is);
/*     */       
/* 151 */       recSize -= 3;
/*     */       
/* 153 */       functionId = readShort(is);
/* 154 */       if (functionId <= 0) {
/*     */         break;
/*     */       }
/* 157 */       switch (functionId) {
/*     */         case 259:
/* 159 */           mapmode = readShort(is);
/*     */           
/* 161 */           if (mapmode == 8) this.isotropic = false;
/*     */           
/*     */           continue;
/*     */         case 523:
/* 165 */           this.vpY = readShort(is);
/* 166 */           this.vpX = readShort(is);
/*     */           continue;
/*     */         
/*     */         case 524:
/* 170 */           this.vpH = readShort(is);
/* 171 */           this.vpW = readShort(is);
/* 172 */           if (!this.isotropic) this.scaleXY = this.vpW / this.vpH; 
/* 173 */           this.vpW = (int)(this.vpW * this.scaleXY);
/*     */           continue;
/*     */ 
/*     */ 
/*     */         
/*     */         case 762:
/* 179 */           objIndex = 0;
/* 180 */           penStyle = readShort(is);
/*     */           
/* 182 */           readInt(is);
/*     */           
/* 184 */           colorref = readInt(is);
/* 185 */           red = colorref & 0xFF;
/* 186 */           green = (colorref & 0xFF00) >> 8;
/* 187 */           blue = (colorref & 0xFF0000) >> 16;
/* 188 */           color = new Color(red, green, blue);
/*     */           
/* 190 */           if (recSize == 6) readShort(is); 
/* 191 */           if (penStyle == 5) {
/* 192 */             objIndex = addObjectAt(4, color, objIndex); continue;
/*     */           } 
/* 194 */           objIndex = addObjectAt(1, color, objIndex);
/*     */           continue;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 764:
/* 201 */           objIndex = 0;
/* 202 */           brushStyle = readShort(is);
/*     */           
/* 204 */           colorref = readInt(is);
/* 205 */           red = colorref & 0xFF;
/* 206 */           green = (colorref & 0xFF00) >> 8;
/* 207 */           blue = (colorref & 0xFF0000) >> 16;
/* 208 */           color = new Color(red, green, blue);
/*     */           
/* 210 */           readShort(is);
/* 211 */           if (brushStyle == 5) {
/* 212 */             objIndex = addObjectAt(5, color, objIndex); continue;
/*     */           } 
/* 214 */           objIndex = addObjectAt(2, color, objIndex);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 302:
/* 219 */           align = readShort(is);
/*     */           
/* 221 */           if (recSize > 1) for (int i24 = 1; i24 < recSize; ) { readShort(is); i24++; }
/* 222 */               this.currentHorizAlign = WMFUtilities.getHorizontalAlignment(align);
/* 223 */           this.currentVertAlign = WMFUtilities.getVerticalAlignment(align);
/*     */           continue;
/*     */         
/*     */         case 2610:
/* 227 */           n = readShort(is);
/* 228 */           i3 = (int)(readShort(is) * this.scaleXY);
/* 229 */           lenText = readShort(is);
/* 230 */           flag = readShort(is);
/* 231 */           i12 = 4;
/* 232 */           clipped = false;
/* 233 */           x1 = 0; y1 = 0; x2 = 0; y2 = 0;
/*     */ 
/*     */           
/* 236 */           if ((flag & 0x4) != 0) {
/* 237 */             x1 = (int)(readShort(is) * this.scaleXY);
/* 238 */             y1 = readShort(is);
/* 239 */             x2 = (int)(readShort(is) * this.scaleXY);
/* 240 */             y2 = readShort(is);
/* 241 */             i12 += 4;
/* 242 */             clipped = true;
/*     */           } 
/* 244 */           arrayOfByte1 = new byte[lenText];
/* 245 */           i18 = 0;
/* 246 */           for (; i18 < lenText; i18++) {
/* 247 */             arrayOfByte1[i18] = is.readByte();
/*     */           }
/* 249 */           str1 = WMFUtilities.decodeString(this.wf, arrayOfByte1);
/*     */           
/* 251 */           i12 += (lenText + 1) / 2;
/*     */ 
/*     */ 
/*     */           
/* 255 */           if (lenText % 2 != 0) is.readByte();
/*     */           
/* 257 */           if (i12 < recSize) for (int i24 = i12; i24 < recSize; ) { readShort(is); i24++; }
/* 258 */               textLayout1 = new TextLayout(str1, this.wf.font, fontCtx);
/*     */           
/* 260 */           i20 = (int)textLayout1.getBounds().getWidth();
/* 261 */           i3 = (int)textLayout1.getBounds().getX();
/* 262 */           i21 = (int)getVerticalAlignmentValue(textLayout1, this.currentVertAlign);
/*     */ 
/*     */           
/* 265 */           resizeBounds(i3, n);
/* 266 */           resizeBounds(i3 + i20, n + i21);
/* 267 */           this.firstEffectivePaint = false;
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 1313:
/*     */         case 1583:
/* 273 */           len = readShort(is);
/* 274 */           read = 1;
/* 275 */           bstr = new byte[len];
/* 276 */           for (i8 = 0; i8 < len; i8++) {
/* 277 */             bstr[i8] = is.readByte();
/*     */           }
/* 279 */           sr = WMFUtilities.decodeString(this.wf, bstr);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 284 */           if (len % 2 != 0) is.readByte(); 
/* 285 */           read += (len + 1) / 2;
/*     */           
/* 287 */           i11 = readShort(is);
/* 288 */           i14 = (int)(readShort(is) * this.scaleXY);
/* 289 */           read += 2;
/*     */           
/* 291 */           if (read < recSize) for (int i24 = read; i24 < recSize; ) { readShort(is); i24++; }
/* 292 */               layout = new TextLayout(sr, this.wf.font, fontCtx);
/* 293 */           i16 = (int)layout.getBounds().getWidth();
/* 294 */           i14 = (int)layout.getBounds().getX();
/* 295 */           i17 = (int)getVerticalAlignmentValue(layout, this.currentVertAlign);
/*     */ 
/*     */           
/* 298 */           resizeBounds(i14, i11);
/* 299 */           resizeBounds(i14 + i16, i11 + i17);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 763:
/* 304 */           lfHeight = readShort(is);
/* 305 */           size = (int)(this.scaleY * lfHeight);
/* 306 */           lfWidth = readShort(is);
/* 307 */           escape = readShort(is);
/* 308 */           orient = readShort(is);
/* 309 */           weight = readShort(is);
/*     */           
/* 311 */           italic = is.readByte();
/* 312 */           underline = is.readByte();
/* 313 */           strikeOut = is.readByte();
/* 314 */           charset = is.readByte() & 0xFF;
/* 315 */           lfOutPrecision = is.readByte();
/* 316 */           lfClipPrecision = is.readByte();
/* 317 */           lfQuality = is.readByte();
/* 318 */           lfPitchAndFamily = is.readByte();
/*     */           
/* 320 */           style = (italic > 0) ? 2 : 0;
/* 321 */           style |= (weight > 400) ? 1 : 0;
/*     */ 
/*     */ 
/*     */           
/* 325 */           i19 = 2 * (recSize - 9);
/* 326 */           lfFaceName = new byte[i19];
/*     */           
/* 328 */           for (i22 = 0; i22 < i19; ) { lfFaceName[i22] = is.readByte(); i22++; }
/* 329 */            face = new String(lfFaceName);
/*     */ 
/*     */           
/* 332 */           d = 0;
/* 333 */           while (d < face.length() && (Character.isLetterOrDigit(face.charAt(d)) || Character.isWhitespace(face.charAt(d))))
/*     */           {
/* 335 */             d++; } 
/* 336 */           if (d > 0) { face = face.substring(0, d); }
/* 337 */           else { face = "System"; }
/*     */           
/* 339 */           if (size < 0.0F) size = -size; 
/* 340 */           i23 = 0;
/*     */           
/* 342 */           f = new Font(face, style, (int)size);
/* 343 */           f = f.deriveFont(size);
/* 344 */           wf = new WMFFont(f, charset, underline, strikeOut, italic, weight, orient, escape);
/*     */ 
/*     */           
/* 347 */           i23 = addObjectAt(3, wf, i23);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 1791:
/* 352 */           m = 0;
/* 353 */           for (i2 = 0; i2 < recSize; ) { readShort(is); i2++; }
/* 354 */            m = addObjectAt(6, INTEGER_0, 0);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 247:
/* 359 */           m = 0;
/* 360 */           for (i2 = 0; i2 < recSize; ) { readShort(is); i2++; }
/* 361 */            m = addObjectAt(8, INTEGER_0, 0);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 301:
/* 366 */           gdiIndex = readShort(is);
/* 367 */           if ((gdiIndex & Integer.MIN_VALUE) != 0) {
/*     */             continue;
/*     */           }
/* 370 */           gdiObj = getObject(gdiIndex);
/* 371 */           if (!gdiObj.used)
/*     */             continue; 
/* 373 */           switch (gdiObj.type) {
/*     */             case 1:
/* 375 */               penObject = gdiIndex;
/*     */               continue;
/*     */             case 2:
/* 378 */               brushObject = gdiIndex;
/*     */               continue;
/*     */             case 3:
/* 381 */               this.wf = (WMFFont)gdiObj.obj;
/* 382 */               fontObject = gdiIndex;
/*     */               continue;
/*     */             
/*     */             case 4:
/* 386 */               penObject = -1;
/*     */               continue;
/*     */             case 5:
/* 389 */               brushObject = -1;
/*     */               continue;
/*     */           } 
/*     */           
/*     */           continue;
/*     */         case 496:
/* 395 */           gdiIndex = readShort(is);
/* 396 */           gdiObj = getObject(gdiIndex);
/* 397 */           if (gdiIndex == brushObject) { brushObject = -1; }
/* 398 */           else if (gdiIndex == penObject) { penObject = -1; }
/* 399 */           else if (gdiIndex == fontObject) { fontObject = -1; }
/* 400 */            gdiObj.clear();
/*     */           continue;
/*     */         
/*     */         case 531:
/* 404 */           y = readShort(is);
/* 405 */           x = (int)(readShort(is) * this.scaleXY);
/* 406 */           if (penObject >= 0) {
/* 407 */             resizeBounds(this.startX, this.startY);
/* 408 */             resizeBounds(x, y);
/* 409 */             this.firstEffectivePaint = false;
/*     */           } 
/* 411 */           this.startX = x;
/* 412 */           this.startY = y;
/*     */           continue;
/*     */         
/*     */         case 532:
/* 416 */           this.startY = readShort(is);
/* 417 */           this.startX = (int)(readShort(is) * this.scaleXY);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 1336:
/* 422 */           count = readShort(is);
/* 423 */           pts = new int[count];
/* 424 */           ptCount = 0;
/* 425 */           for (i7 = 0; i7 < count; i7++) {
/* 426 */             pts[i7] = readShort(is);
/* 427 */             ptCount += pts[i7];
/*     */           } 
/*     */           
/* 430 */           offset = count + 1;
/* 431 */           for (i10 = 0; i10 < count; i10++) {
/* 432 */             for (int i24 = 0; i24 < pts[i10]; i24++) {
/*     */               
/* 434 */               int i25 = (int)(readShort(is) * this.scaleXY);
/* 435 */               int i26 = readShort(is);
/* 436 */               if (brushObject >= 0 || penObject >= 0) resizeBounds(i25, i26); 
/*     */             } 
/*     */           } 
/* 439 */           this.firstEffectivePaint = false;
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 804:
/* 444 */           count = readShort(is);
/* 445 */           _xpts = new float[count + 1];
/* 446 */           _ypts = new float[count + 1];
/* 447 */           for (i6 = 0; i6 < count; i6++) {
/* 448 */             _xpts[i6] = readShort(is) * this.scaleXY;
/* 449 */             _ypts[i6] = readShort(is);
/*     */           } 
/* 451 */           _xpts[count] = _xpts[0];
/* 452 */           _ypts[count] = _ypts[0];
/* 453 */           polygon2D = new Polygon2D(_xpts, _ypts, count);
/* 454 */           paint(brushObject, penObject, (Shape)polygon2D);
/*     */           continue;
/*     */ 
/*     */ 
/*     */         
/*     */         case 805:
/* 460 */           count = readShort(is);
/* 461 */           _xpts = new float[count];
/* 462 */           _ypts = new float[count];
/* 463 */           for (i = 0; i < count; i++) {
/* 464 */             _xpts[i] = readShort(is) * this.scaleXY;
/* 465 */             _ypts[i] = readShort(is);
/*     */           } 
/* 467 */           pol = new Polyline2D(_xpts, _ypts, count);
/* 468 */           paintWithPen(penObject, (Shape)pol);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 1046:
/*     */         case 1048:
/*     */         case 1051:
/* 475 */           bot = readShort(is);
/* 476 */           right = (int)(readShort(is) * this.scaleXY);
/* 477 */           top = readShort(is);
/* 478 */           i5 = (int)(readShort(is) * this.scaleXY);
/* 479 */           rec = new Rectangle2D.Float(i5, top, (right - i5), (bot - top));
/* 480 */           paint(brushObject, penObject, rec);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 1564:
/* 485 */           readShort(is);
/* 486 */           readShort(is);
/* 487 */           bot = readShort(is);
/* 488 */           right = (int)(readShort(is) * this.scaleXY);
/* 489 */           top = readShort(is);
/* 490 */           i5 = (int)(readShort(is) * this.scaleXY);
/* 491 */           rec = new Rectangle2D.Float(i5, top, (right - i5), (bot - top));
/* 492 */           paint(brushObject, penObject, rec);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 2071:
/*     */         case 2074:
/*     */         case 2096:
/* 499 */           readShort(is);
/* 500 */           readShort(is);
/* 501 */           readShort(is);
/* 502 */           readShort(is);
/* 503 */           bot = readShort(is);
/* 504 */           right = (int)(readShort(is) * this.scaleXY);
/* 505 */           top = readShort(is);
/* 506 */           i5 = (int)(readShort(is) * this.scaleXY);
/* 507 */           rec = new Rectangle2D.Float(i5, top, (right - i5), (bot - top));
/* 508 */           paint(brushObject, penObject, rec);
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 1565:
/* 513 */           readInt(is);
/* 514 */           k = readShort(is);
/* 515 */           i1 = (int)(readShort(is) * this.scaleXY);
/* 516 */           left = (int)(readShort(is) * this.scaleXY);
/* 517 */           i4 = readShort(is);
/* 518 */           if (penObject >= 0) resizeBounds(left, i4); 
/* 519 */           if (penObject >= 0) resizeBounds(left + i1, i4 + k);
/*     */           
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 2881:
/* 525 */           is.readInt();
/* 526 */           readShort(is);
/* 527 */           readShort(is);
/* 528 */           readShort(is);
/* 529 */           readShort(is);
/* 530 */           heightDst = readShort(is);
/* 531 */           widthDst = readShort(is) * this.scaleXY;
/* 532 */           dy = readShort(is) * getVpWFactor() * this.inch / PIXEL_PER_INCH;
/* 533 */           dx = readShort(is) * getVpWFactor() * this.inch / PIXEL_PER_INCH * this.scaleXY;
/* 534 */           widthDst = widthDst * getVpWFactor() * this.inch / PIXEL_PER_INCH;
/* 535 */           heightDst = heightDst * getVpHFactor() * this.inch / PIXEL_PER_INCH;
/* 536 */           resizeImageBounds((int)dx, (int)dy);
/* 537 */           resizeImageBounds((int)(dx + widthDst), (int)(dy + heightDst));
/*     */           
/* 539 */           i9 = 2 * recSize - 20;
/* 540 */           for (i13 = 0; i13 < i9; ) { is.readByte(); i13++; }
/*     */           
/*     */           continue;
/*     */         case 3907:
/* 544 */           is.readInt();
/* 545 */           readShort(is);
/* 546 */           readShort(is);
/* 547 */           readShort(is);
/* 548 */           readShort(is);
/* 549 */           readShort(is);
/* 550 */           heightDst = readShort(is);
/* 551 */           widthDst = readShort(is) * this.scaleXY;
/* 552 */           dy = readShort(is) * getVpHFactor() * this.inch / PIXEL_PER_INCH;
/* 553 */           dx = readShort(is) * getVpHFactor() * this.inch / PIXEL_PER_INCH * this.scaleXY;
/* 554 */           widthDst = widthDst * getVpWFactor() * this.inch / PIXEL_PER_INCH;
/* 555 */           heightDst = heightDst * getVpHFactor() * this.inch / PIXEL_PER_INCH;
/* 556 */           resizeImageBounds((int)dx, (int)dy);
/* 557 */           resizeImageBounds((int)(dx + widthDst), (int)(dy + heightDst));
/*     */           
/* 559 */           i9 = 2 * recSize - 22;
/* 560 */           bitmap = new byte[i9];
/* 561 */           for (i15 = 0; i15 < i9; ) { bitmap[i15] = is.readByte(); i15++; }
/*     */           
/*     */           continue;
/*     */         case 2368:
/* 565 */           is.readInt();
/* 566 */           readShort(is);
/* 567 */           readShort(is);
/* 568 */           readShort(is);
/* 569 */           height = readShort(is) * this.inch / PIXEL_PER_INCH * getVpHFactor();
/*     */           
/* 571 */           width = readShort(is) * this.inch / PIXEL_PER_INCH * getVpWFactor() * this.scaleXY;
/*     */           
/* 573 */           dy = this.inch / PIXEL_PER_INCH * getVpHFactor() * readShort(is);
/*     */           
/* 575 */           dx = this.inch / PIXEL_PER_INCH * getVpWFactor() * readShort(is) * this.scaleXY;
/*     */           
/* 577 */           resizeImageBounds((int)dx, (int)dy);
/* 578 */           resizeImageBounds((int)(dx + width), (int)(dy + height));
/*     */           continue;
/*     */       } 
/*     */       
/* 582 */       for (int j = 0; j < recSize; j++) {
/* 583 */         readShort(is);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 590 */     if (!this.isAldus) {
/* 591 */       this.width = this.vpW;
/* 592 */       this.height = this.vpH;
/* 593 */       this.right = this.vpX;
/* 594 */       this.left = this.vpX + this.vpW;
/* 595 */       this.top = this.vpY;
/* 596 */       this.bottom = this.vpY + this.vpH;
/*     */     } 
/* 598 */     resetBounds();
/* 599 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidthBoundsPixels() {
/* 606 */     return this._bwidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeightBoundsPixels() {
/* 613 */     return this._bheight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidthBoundsUnits() {
/* 620 */     return (int)(this.inch * this._bwidth / PIXEL_PER_INCH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeightBoundsUnits() {
/* 627 */     return (int)(this.inch * this._bheight / PIXEL_PER_INCH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXOffset() {
/* 634 */     return this._bleft;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYOffset() {
/* 641 */     return this._btop;
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetBounds() {
/* 646 */     this.scale = getWidthPixels() / this.vpW;
/* 647 */     if (this._bright != -1) {
/* 648 */       this._bright = (int)(this.scale * (this.vpX + this._bright));
/* 649 */       this._bleft = (int)(this.scale * (this.vpX + this._bleft));
/* 650 */       this._bbottom = (int)(this.scale * (this.vpY + this._bbottom));
/* 651 */       this._btop = (int)(this.scale * (this.vpY + this._btop));
/*     */     } 
/*     */ 
/*     */     
/* 655 */     if (this._iright != -1) {
/* 656 */       this._iright = (int)(this._iright * getWidthPixels() / this.width);
/* 657 */       this._ileft = (int)(this._ileft * getWidthPixels() / this.width);
/* 658 */       this._ibottom = (int)(this._ibottom * getWidthPixels() / this.width);
/* 659 */       this._itop = (int)(this._itop * getWidthPixels() / this.width);
/*     */ 
/*     */       
/* 662 */       if (this._bright == -1 || this._iright > this._bright) this._bright = this._iright; 
/* 663 */       if (this._bleft == -1 || this._ileft < this._bleft) this._bleft = this._ileft; 
/* 664 */       if (this._btop == -1 || this._itop < this._btop) this._btop = this._itop; 
/* 665 */       if (this._bbottom == -1 || this._ibottom > this._bbottom) this._bbottom = this._ibottom;
/*     */     
/*     */     } 
/* 668 */     if (this._bleft != -1 && this._bright != -1) this._bwidth = this._bright - this._bleft; 
/* 669 */     if (this._btop != -1 && this._bbottom != -1) this._bheight = this._bbottom - this._btop;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void resizeBounds(int x, int y) {
/* 676 */     if (this._bleft == -1) { this._bleft = x; }
/* 677 */     else if (x < this._bleft) { this._bleft = x; }
/* 678 */      if (this._bright == -1) { this._bright = x; }
/* 679 */     else if (x > this._bright) { this._bright = x; }
/*     */     
/* 681 */     if (this._btop == -1) { this._btop = y; }
/* 682 */     else if (y < this._btop) { this._btop = y; }
/* 683 */      if (this._bbottom == -1) { this._bbottom = y; }
/* 684 */     else if (y > this._bbottom) { this._bbottom = y; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void resizeImageBounds(int x, int y) {
/* 691 */     if (this._ileft == -1) { this._ileft = x; }
/* 692 */     else if (x < this._ileft) { this._ileft = x; }
/* 693 */      if (this._iright == -1) { this._iright = x; }
/* 694 */     else if (x > this._iright) { this._iright = x; }
/*     */     
/* 696 */     if (this._itop == -1) { this._itop = y; }
/* 697 */     else if (y < this._itop) { this._itop = y; }
/* 698 */      if (this._ibottom == -1) { this._ibottom = y; }
/* 699 */     else if (y > this._ibottom) { this._ibottom = y; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private Color getColorFromObject(int brushObject) {
/* 705 */     Color color = null;
/* 706 */     if (brushObject >= 0) {
/* 707 */       GdiObject gdiObj = getObject(brushObject);
/* 708 */       return (Color)gdiObj.obj;
/* 709 */     }  return null;
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
/*     */   private void paint(int brushObject, int penObject, Shape shape) {
/* 721 */     if (brushObject >= 0 || penObject >= 0) {
/*     */       Color col;
/* 723 */       if (brushObject >= 0) { col = getColorFromObject(brushObject); }
/* 724 */       else { col = getColorFromObject(penObject); }
/*     */       
/* 726 */       if (!this.firstEffectivePaint || !col.equals(Color.white)) {
/* 727 */         Rectangle rec = shape.getBounds();
/* 728 */         resizeBounds((int)rec.getMinX(), (int)rec.getMinY());
/* 729 */         resizeBounds((int)rec.getMaxX(), (int)rec.getMaxY());
/* 730 */         this.firstEffectivePaint = false;
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
/*     */   
/*     */   private void paintWithPen(int penObject, Shape shape) {
/* 744 */     if (penObject >= 0) {
/* 745 */       Color col = getColorFromObject(penObject);
/*     */       
/* 747 */       if (!this.firstEffectivePaint || !col.equals(Color.white)) {
/* 748 */         Rectangle rec = shape.getBounds();
/* 749 */         resizeBounds((int)rec.getMinX(), (int)rec.getMinY());
/* 750 */         resizeBounds((int)rec.getMaxX(), (int)rec.getMaxY());
/* 751 */         this.firstEffectivePaint = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float getVerticalAlignmentValue(TextLayout layout, int vertAlign) {
/* 759 */     if (vertAlign == 24) return -layout.getAscent(); 
/* 760 */     if (vertAlign == 0) return layout.getAscent() + layout.getDescent(); 
/* 761 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/WMFHeaderProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */