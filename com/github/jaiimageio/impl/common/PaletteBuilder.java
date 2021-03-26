/*     */ package com.github.jaiimageio.impl.common;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PaletteBuilder
/*     */ {
/*     */   protected static final int MAXLEVEL = 8;
/*     */   protected RenderedImage src;
/*     */   protected ColorModel srcColorModel;
/*     */   protected Raster srcRaster;
/*     */   protected int requiredSize;
/*     */   protected ColorNode root;
/*     */   protected int numNodes;
/*     */   protected int maxNodes;
/*     */   protected int currLevel;
/*     */   protected int currSize;
/*     */   protected ColorNode[] reduceList;
/*     */   protected ColorNode[] palette;
/*     */   protected int transparency;
/*     */   protected ColorNode transColor;
/*     */   
/*     */   public static RenderedImage createIndexedImage(RenderedImage src) {
/* 114 */     PaletteBuilder pb = new PaletteBuilder(src);
/* 115 */     pb.buildPalette();
/* 116 */     return pb.getIndexedImage();
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
/*     */   public static IndexColorModel createIndexColorModel(RenderedImage img) {
/* 137 */     PaletteBuilder pb = new PaletteBuilder(img);
/* 138 */     pb.buildPalette();
/* 139 */     return pb.getIndexColorModel();
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
/*     */   public static boolean canCreatePalette(ImageTypeSpecifier type) {
/* 156 */     if (type == null) {
/* 157 */       throw new IllegalArgumentException("type == null");
/*     */     }
/* 159 */     return true;
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
/*     */   public static boolean canCreatePalette(RenderedImage image) {
/* 176 */     if (image == null) {
/* 177 */       throw new IllegalArgumentException("image == null");
/*     */     }
/* 179 */     ImageTypeSpecifier type = new ImageTypeSpecifier(image);
/* 180 */     return canCreatePalette(type);
/*     */   }
/*     */   
/*     */   protected RenderedImage getIndexedImage() {
/* 184 */     IndexColorModel icm = getIndexColorModel();
/*     */ 
/*     */     
/* 187 */     BufferedImage dst = new BufferedImage(this.src.getWidth(), this.src.getHeight(), 13, icm);
/*     */ 
/*     */     
/* 190 */     WritableRaster wr = dst.getRaster();
/* 191 */     int minX = this.src.getMinX();
/* 192 */     int minY = this.src.getMinY();
/* 193 */     for (int y = 0; y < dst.getHeight(); y++) {
/* 194 */       for (int x = 0; x < dst.getWidth(); x++) {
/* 195 */         Color aColor = getSrcColor(x + minX, y + minY);
/* 196 */         wr.setSample(x, y, 0, findColorIndex(this.root, aColor));
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     return dst;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PaletteBuilder(RenderedImage src) {
/* 205 */     this(src, 256);
/*     */   }
/*     */   
/*     */   protected PaletteBuilder(RenderedImage src, int size) {
/* 209 */     this.src = src;
/* 210 */     this.srcColorModel = src.getColorModel();
/* 211 */     this.srcRaster = src.getData();
/*     */     
/* 213 */     this
/* 214 */       .transparency = this.srcColorModel.getTransparency();
/*     */     
/* 216 */     if (this.transparency != 1) {
/* 217 */       this.requiredSize = size - 1;
/* 218 */       this.transColor = new ColorNode();
/* 219 */       this.transColor.isLeaf = true;
/*     */     } else {
/* 221 */       this.requiredSize = size;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Color getSrcColor(int x, int y) {
/* 226 */     int argb = this.srcColorModel.getRGB(this.srcRaster.getDataElements(x, y, null));
/* 227 */     return new Color(argb, (this.transparency != 1));
/*     */   }
/*     */   
/*     */   protected int findColorIndex(ColorNode aNode, Color aColor) {
/* 231 */     if (this.transparency != 1 && aColor
/* 232 */       .getAlpha() != 255)
/*     */     {
/* 234 */       return 0;
/*     */     }
/*     */     
/* 237 */     if (aNode.isLeaf) {
/* 238 */       return aNode.paletteIndex;
/*     */     }
/* 240 */     int childIndex = getBranchIndex(aColor, aNode.level);
/*     */     
/* 242 */     return findColorIndex(aNode.children[childIndex], aColor);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buildPalette() {
/* 247 */     this.reduceList = new ColorNode[9];
/* 248 */     for (int i = 0; i < this.reduceList.length; i++) {
/* 249 */       this.reduceList[i] = null;
/*     */     }
/*     */     
/* 252 */     this.numNodes = 0;
/* 253 */     this.maxNodes = 0;
/* 254 */     this.root = null;
/* 255 */     this.currSize = 0;
/* 256 */     this.currLevel = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 263 */     int w = this.src.getWidth();
/* 264 */     int h = this.src.getHeight();
/* 265 */     int minX = this.src.getMinX();
/* 266 */     int minY = this.src.getMinY();
/* 267 */     for (int y = 0; y < h; y++) {
/* 268 */       for (int x = 0; x < w; x++) {
/*     */         
/* 270 */         Color aColor = getSrcColor(w - x + minX - 1, h - y + minY - 1);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 275 */         if (this.transparency != 1 && aColor
/* 276 */           .getAlpha() != 255) {
/*     */           
/* 278 */           this.transColor = insertNode(this.transColor, aColor, 0);
/*     */         } else {
/* 280 */           this.root = insertNode(this.root, aColor, 0);
/*     */         } 
/* 282 */         if (this.currSize > this.requiredSize) {
/* 283 */           reduceTree();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ColorNode insertNode(ColorNode aNode, Color aColor, int aLevel) {
/* 291 */     if (aNode == null) {
/* 292 */       aNode = new ColorNode();
/* 293 */       this.numNodes++;
/* 294 */       if (this.numNodes > this.maxNodes) {
/* 295 */         this.maxNodes = this.numNodes;
/*     */       }
/* 297 */       aNode.level = aLevel;
/* 298 */       aNode.isLeaf = (aLevel > 8);
/* 299 */       if (aNode.isLeaf) {
/* 300 */         this.currSize++;
/*     */       }
/*     */     } 
/* 303 */     aNode.colorCount++;
/* 304 */     aNode.red += aColor.getRed();
/* 305 */     aNode.green += aColor.getGreen();
/* 306 */     aNode.blue += aColor.getBlue();
/*     */     
/* 308 */     if (!aNode.isLeaf) {
/* 309 */       int branchIndex = getBranchIndex(aColor, aLevel);
/* 310 */       if (aNode.children[branchIndex] == null) {
/* 311 */         aNode.childCount++;
/* 312 */         if (aNode.childCount == 2) {
/* 313 */           aNode.nextReducible = this.reduceList[aLevel];
/* 314 */           this.reduceList[aLevel] = aNode;
/*     */         } 
/*     */       } 
/* 317 */       aNode.children[branchIndex] = 
/* 318 */         insertNode(aNode.children[branchIndex], aColor, aLevel + 1);
/*     */     } 
/* 320 */     return aNode;
/*     */   }
/*     */   
/*     */   protected IndexColorModel getIndexColorModel() {
/* 324 */     int size = this.currSize;
/* 325 */     if (this.transparency != 1) {
/* 326 */       size++;
/*     */     }
/*     */     
/* 329 */     byte[] red = new byte[size];
/* 330 */     byte[] green = new byte[size];
/* 331 */     byte[] blue = new byte[size];
/*     */     
/* 333 */     int index = 0;
/* 334 */     this.palette = new ColorNode[size];
/* 335 */     if (this.transparency != 1) {
/* 336 */       index++;
/*     */     }
/*     */     
/* 339 */     int lastIndex = findPaletteEntry(this.root, index, red, green, blue);
/*     */     
/* 341 */     IndexColorModel icm = null;
/* 342 */     if (this.transparency != 1) {
/* 343 */       icm = new IndexColorModel(8, size, red, green, blue, 0);
/*     */     } else {
/* 345 */       icm = new IndexColorModel(8, this.currSize, red, green, blue);
/*     */     } 
/* 347 */     return icm;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int findPaletteEntry(ColorNode aNode, int index, byte[] red, byte[] green, byte[] blue) {
/* 353 */     if (aNode.isLeaf) {
/* 354 */       red[index] = (byte)(int)(aNode.red / aNode.colorCount);
/* 355 */       green[index] = (byte)(int)(aNode.green / aNode.colorCount);
/* 356 */       blue[index] = (byte)(int)(aNode.blue / aNode.colorCount);
/* 357 */       aNode.paletteIndex = index;
/*     */       
/* 359 */       this.palette[index] = aNode;
/*     */       
/* 361 */       index++;
/*     */     } else {
/* 363 */       for (int i = 0; i < 8; i++) {
/* 364 */         if (aNode.children[i] != null) {
/* 365 */           index = findPaletteEntry(aNode.children[i], index, red, green, blue);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 370 */     return index;
/*     */   }
/*     */   
/*     */   protected int getBranchIndex(Color aColor, int aLevel) {
/* 374 */     if (aLevel > 8 || aLevel < 0) {
/* 375 */       throw new IllegalArgumentException("Invalid octree node depth: " + aLevel);
/*     */     }
/*     */ 
/*     */     
/* 379 */     int shift = 8 - aLevel;
/* 380 */     int red_index = 0x1 & (0xFF & aColor.getRed()) >> shift;
/* 381 */     int green_index = 0x1 & (0xFF & aColor.getGreen()) >> shift;
/* 382 */     int blue_index = 0x1 & (0xFF & aColor.getBlue()) >> shift;
/* 383 */     int index = red_index << 2 | green_index << 1 | blue_index;
/* 384 */     return index;
/*     */   }
/*     */   
/*     */   protected void reduceTree() {
/* 388 */     int level = this.reduceList.length - 1;
/* 389 */     while (this.reduceList[level] == null && level >= 0) {
/* 390 */       level--;
/*     */     }
/*     */     
/* 393 */     ColorNode thisNode = this.reduceList[level];
/* 394 */     if (thisNode == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 400 */     ColorNode pList = thisNode;
/* 401 */     int minColorCount = pList.colorCount;
/*     */     
/* 403 */     int cnt = 1;
/* 404 */     while (pList.nextReducible != null) {
/* 405 */       if (minColorCount > pList.nextReducible.colorCount) {
/* 406 */         thisNode = pList;
/* 407 */         minColorCount = pList.colorCount;
/*     */       } 
/* 409 */       pList = pList.nextReducible;
/* 410 */       cnt++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 415 */     if (thisNode == this.reduceList[level]) {
/* 416 */       this.reduceList[level] = thisNode.nextReducible;
/*     */     } else {
/* 418 */       pList = thisNode.nextReducible;
/* 419 */       thisNode.nextReducible = pList.nextReducible;
/* 420 */       thisNode = pList;
/*     */     } 
/*     */     
/* 423 */     if (thisNode.isLeaf) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 428 */     int leafChildCount = thisNode.getLeafChildCount();
/* 429 */     thisNode.isLeaf = true;
/* 430 */     this.currSize -= leafChildCount - 1;
/* 431 */     int aDepth = thisNode.level;
/* 432 */     for (int i = 0; i < 8; i++) {
/* 433 */       thisNode.children[i] = freeTree(thisNode.children[i]);
/*     */     }
/* 435 */     thisNode.childCount = 0;
/*     */   }
/*     */   
/*     */   protected ColorNode freeTree(ColorNode aNode) {
/* 439 */     if (aNode == null) {
/* 440 */       return null;
/*     */     }
/* 442 */     for (int i = 0; i < 8; i++) {
/* 443 */       aNode.children[i] = freeTree(aNode.children[i]);
/*     */     }
/*     */     
/* 446 */     this.numNodes--;
/* 447 */     return null;
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
/*     */   protected class ColorNode
/*     */   {
/*     */     public boolean isLeaf = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 470 */     public int level = 0;
/* 471 */     public int childCount = 0;
/* 472 */     ColorNode[] children = new ColorNode[8]; public int colorCount; public long red; public long blue; public ColorNode() {
/* 473 */       for (int i = 0; i < 8; i++) {
/* 474 */         this.children[i] = null;
/*     */       }
/*     */       
/* 477 */       this.colorCount = 0;
/* 478 */       this.red = this.green = this.blue = 0L;
/*     */       
/* 480 */       this.paletteIndex = 0;
/*     */     }
/*     */     public long green; public int paletteIndex; ColorNode nextReducible;
/*     */     public int getLeafChildCount() {
/* 484 */       if (this.isLeaf) {
/* 485 */         return 0;
/*     */       }
/* 487 */       int cnt = 0;
/* 488 */       for (int i = 0; i < this.children.length; i++) {
/* 489 */         if (this.children[i] != null) {
/* 490 */           if ((this.children[i]).isLeaf) {
/* 491 */             cnt++;
/*     */           } else {
/* 493 */             cnt += this.children[i].getLeafChildCount();
/*     */           } 
/*     */         }
/*     */       } 
/* 497 */       return cnt;
/*     */     }
/*     */     
/*     */     public int getRGB() {
/* 501 */       int r = (int)this.red / this.colorCount;
/* 502 */       int g = (int)this.green / this.colorCount;
/* 503 */       int b = (int)this.blue / this.colorCount;
/*     */       
/* 505 */       int c = 0xFF000000 | (0xFF & r) << 16 | (0xFF & g) << 8 | 0xFF & b;
/* 506 */       return c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/common/PaletteBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */