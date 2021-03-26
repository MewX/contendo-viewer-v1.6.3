/*     */ package org.apache.batik.transcoder.wmf.tosvg;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.util.Platform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractWMFReader
/*     */ {
/*  38 */   public static final float PIXEL_PER_INCH = Platform.getScreenResolution();
/*  39 */   public static final float MM_PER_PIXEL = 25.4F / Platform.getScreenResolution(); protected int left; protected int right; protected int top;
/*     */   protected int bottom;
/*     */   protected int width;
/*     */   protected int height;
/*     */   protected int inch;
/*  44 */   protected int xSign = 1; protected float scaleX; protected float scaleY; protected float scaleXY; protected int vpW; protected int vpH; protected int vpX; protected int vpY;
/*  45 */   protected int ySign = 1; protected volatile boolean bReading = false; protected boolean isAldus = false; protected boolean isotropic = true;
/*     */   protected int mtType;
/*     */   protected int mtHeaderSize;
/*     */   protected int mtVersion;
/*     */   protected int mtSize;
/*     */   protected int mtNoObjects;
/*     */   protected int mtMaxRecord;
/*     */   protected int mtNoParameters;
/*     */   protected int windowWidth;
/*     */   protected int windowHeight;
/*     */   protected int numObjects;
/*     */   protected List objectVector;
/*     */   public int lastObjectIdx;
/*     */   
/*     */   public AbstractWMFReader() {
/*  60 */     this.scaleX = 1.0F;
/*  61 */     this.scaleY = 1.0F;
/*  62 */     this.scaleXY = 1.0F;
/*  63 */     this.left = -1;
/*  64 */     this.top = -1;
/*  65 */     this.width = -1;
/*  66 */     this.height = -1;
/*  67 */     this.right = this.left + this.width;
/*  68 */     this.bottom = this.top + this.height;
/*  69 */     this.numObjects = 0;
/*  70 */     this.objectVector = new ArrayList();
/*     */   }
/*     */   
/*     */   public AbstractWMFReader(int width, int height) {
/*  74 */     this();
/*  75 */     this.width = width;
/*  76 */     this.height = height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short readShort(DataInputStream is) throws IOException {
/*  83 */     byte[] js = new byte[2];
/*  84 */     is.readFully(js);
/*  85 */     int iTemp = (0xFF & js[1]) << 8;
/*  86 */     short i = (short)(0xFFFF & iTemp);
/*  87 */     i = (short)(i | 0xFF & js[0]);
/*  88 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int readInt(DataInputStream is) throws IOException {
/*  95 */     byte[] js = new byte[4];
/*  96 */     is.readFully(js);
/*  97 */     int i = (0xFF & js[3]) << 24;
/*  98 */     i |= (0xFF & js[2]) << 16;
/*  99 */     i |= (0xFF & js[1]) << 8;
/* 100 */     i |= 0xFF & js[0];
/* 101 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getViewportWidthUnits() {
/* 108 */     return this.vpW;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getViewportHeightUnits() {
/* 115 */     return this.vpH;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getViewportWidthInch() {
/* 122 */     return this.vpW / this.inch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getViewportHeightInch() {
/* 129 */     return this.vpH / this.inch;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelsPerUnit() {
/* 135 */     return PIXEL_PER_INCH / this.inch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVpW() {
/* 142 */     return (int)(PIXEL_PER_INCH * this.vpW / this.inch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVpH() {
/* 149 */     return (int)(PIXEL_PER_INCH * this.vpH / this.inch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLeftUnits() {
/* 156 */     return this.left;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRightUnits() {
/* 163 */     return this.right;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTopUnits() {
/* 170 */     return this.top;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidthUnits() {
/* 177 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeightUnits() {
/* 184 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBottomUnits() {
/* 191 */     return this.bottom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFileUnitsPerInch() {
/* 198 */     return this.inch;
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
/*     */   public Rectangle getRectangleUnits() {
/* 210 */     Rectangle rec = new Rectangle(this.left, this.top, this.width, this.height);
/* 211 */     return rec;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getRectanglePixel() {
/* 217 */     float _left = PIXEL_PER_INCH * this.left / this.inch;
/* 218 */     float _right = PIXEL_PER_INCH * this.right / this.inch;
/* 219 */     float _top = PIXEL_PER_INCH * this.top / this.inch;
/* 220 */     float _bottom = PIXEL_PER_INCH * this.bottom / this.inch;
/*     */     
/* 222 */     Rectangle2D.Float rec = new Rectangle2D.Float(_left, _top, _right - _left, _bottom - _top);
/*     */     
/* 224 */     return rec;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getRectangleInch() {
/* 230 */     float _left = this.left / this.inch;
/* 231 */     float _right = this.right / this.inch;
/* 232 */     float _top = this.top / this.inch;
/* 233 */     float _bottom = this.bottom / this.inch;
/*     */     
/* 235 */     Rectangle2D.Float rec = new Rectangle2D.Float(_left, _top, _right - _left, _bottom - _top);
/*     */     
/* 237 */     return rec;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidthPixels() {
/* 243 */     return (int)(PIXEL_PER_INCH * this.width / this.inch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnitsToPixels() {
/* 249 */     return PIXEL_PER_INCH / this.inch;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVpWFactor() {
/* 255 */     return PIXEL_PER_INCH * this.width / this.inch / this.vpW;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVpHFactor() {
/* 261 */     return PIXEL_PER_INCH * this.height / this.inch / this.vpH;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeightPixels() {
/* 267 */     return (int)(PIXEL_PER_INCH * this.height / this.inch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSign() {
/* 274 */     return this.xSign;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYSign() {
/* 281 */     return this.ySign;
/*     */   }
/*     */   
/*     */   protected synchronized void setReading(boolean state) {
/* 285 */     this.bReading = state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isReading() {
/* 291 */     return this.bReading;
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
/*     */   public abstract void reset();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract boolean readRecords(DataInputStream paramDataInputStream) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(DataInputStream is) throws IOException {
/* 357 */     reset();
/*     */     
/* 359 */     setReading(true);
/* 360 */     int dwIsAldus = readInt(is);
/* 361 */     if (dwIsAldus == -1698247209) {
/*     */       
/* 363 */       int key = dwIsAldus;
/* 364 */       this.isAldus = true;
/* 365 */       readShort(is);
/* 366 */       this.left = readShort(is);
/* 367 */       this.top = readShort(is);
/* 368 */       this.right = readShort(is);
/* 369 */       this.bottom = readShort(is);
/* 370 */       this.inch = readShort(is);
/* 371 */       int reserved = readInt(is);
/* 372 */       short checksum = readShort(is);
/*     */ 
/*     */       
/* 375 */       if (this.left > this.right) {
/* 376 */         int _i = this.right;
/* 377 */         this.right = this.left;
/* 378 */         this.left = _i;
/* 379 */         this.xSign = -1;
/*     */       } 
/* 381 */       if (this.top > this.bottom) {
/* 382 */         int _i = this.bottom;
/* 383 */         this.bottom = this.top;
/* 384 */         this.top = _i;
/* 385 */         this.ySign = -1;
/*     */       } 
/*     */       
/* 388 */       this.width = this.right - this.left;
/* 389 */       this.height = this.bottom - this.top;
/*     */ 
/*     */       
/* 392 */       this.mtType = readShort(is);
/* 393 */       this.mtHeaderSize = readShort(is);
/*     */     } else {
/*     */       
/* 396 */       this.mtType = dwIsAldus << 16 >> 16;
/* 397 */       this.mtHeaderSize = dwIsAldus >> 16;
/*     */     } 
/*     */     
/* 400 */     this.mtVersion = readShort(is);
/* 401 */     this.mtSize = readInt(is);
/* 402 */     this.mtNoObjects = readShort(is);
/* 403 */     this.mtMaxRecord = readInt(is);
/* 404 */     this.mtNoParameters = readShort(is);
/*     */     
/* 406 */     this.numObjects = this.mtNoObjects;
/* 407 */     List<GdiObject> tempList = new ArrayList(this.numObjects);
/* 408 */     for (int i = 0; i < this.numObjects; i++) {
/* 409 */       tempList.add(new GdiObject(i, false));
/*     */     }
/* 411 */     this.objectVector.addAll(tempList);
/*     */     
/* 413 */     boolean ret = readRecords(is);
/* 414 */     is.close();
/* 415 */     if (!ret) throw new IOException("Unhandled exception while reading records"); 
/*     */   }
/*     */   
/*     */   public int addObject(int type, Object obj) {
/* 419 */     int startIdx = 0;
/*     */ 
/*     */ 
/*     */     
/* 423 */     for (int i = startIdx; i < this.numObjects; i++) {
/* 424 */       GdiObject gdi = this.objectVector.get(i);
/* 425 */       if (!gdi.used) {
/* 426 */         gdi.Setup(type, obj);
/* 427 */         this.lastObjectIdx = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 432 */     return this.lastObjectIdx;
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
/*     */   public int addObjectAt(int type, Object obj, int idx) {
/* 444 */     if (idx == 0 || idx > this.numObjects) {
/* 445 */       addObject(type, obj);
/* 446 */       return this.lastObjectIdx;
/*     */     } 
/* 448 */     this.lastObjectIdx = idx;
/* 449 */     for (int i = 0; i < this.numObjects; i++) {
/* 450 */       GdiObject gdi = this.objectVector.get(i);
/* 451 */       if (i == idx) {
/* 452 */         gdi.Setup(type, obj);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 457 */     return idx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GdiObject getObject(int idx) {
/* 464 */     return this.objectVector.get(idx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumObjects() {
/* 471 */     return this.numObjects;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/AbstractWMFReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */