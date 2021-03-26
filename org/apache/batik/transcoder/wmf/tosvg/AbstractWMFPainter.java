/*     */ package org.apache.batik.transcoder.wmf.tosvg;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractWMFPainter
/*     */ {
/*     */   public static final String WMF_FILE_EXTENSION = ".wmf";
/*  40 */   protected WMFFont wmfFont = null;
/*  41 */   protected int currentHorizAlign = 0;
/*  42 */   protected int currentVertAlign = 0;
/*     */   
/*     */   public static final int PEN = 1;
/*     */   
/*     */   public static final int BRUSH = 2;
/*     */   public static final int FONT = 3;
/*     */   public static final int NULL_PEN = 4;
/*     */   public static final int NULL_BRUSH = 5;
/*     */   public static final int PALETTE = 6;
/*     */   public static final int OBJ_BITMAP = 7;
/*     */   public static final int OBJ_REGION = 8;
/*     */   protected WMFRecordStore currentStore;
/*     */   protected transient boolean bReadingWMF = true;
/*  55 */   protected transient BufferedInputStream bufStream = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BufferedImage getImage(byte[] bit, int width, int height) {
/*  67 */     int _width = (bit[7] & 0xFF) << 24 | (bit[6] & 0xFF) << 16 | (bit[5] & 0xFF) << 8 | bit[4] & 0xFF;
/*     */     
/*  69 */     int _height = (bit[11] & 0xFF) << 24 | (bit[10] & 0xFF) << 16 | (bit[9] & 0xFF) << 8 | bit[8] & 0xFF;
/*     */ 
/*     */ 
/*     */     
/*  73 */     if (width != _width || height != _height) return null; 
/*  74 */     return getImage(bit);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getImageDimension(byte[] bit) {
/*  79 */     int _width = (bit[7] & 0xFF) << 24 | (bit[6] & 0xFF) << 16 | (bit[5] & 0xFF) << 8 | bit[4] & 0xFF;
/*     */     
/*  81 */     int _height = (bit[11] & 0xFF) << 24 | (bit[10] & 0xFF) << 16 | (bit[9] & 0xFF) << 8 | bit[8] & 0xFF;
/*     */     
/*  83 */     return new Dimension(_width, _height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BufferedImage getImage(byte[] bit) {
/*  94 */     int _width = (bit[7] & 0xFF) << 24 | (bit[6] & 0xFF) << 16 | (bit[5] & 0xFF) << 8 | bit[4] & 0xFF;
/*     */     
/*  96 */     int _height = (bit[11] & 0xFF) << 24 | (bit[10] & 0xFF) << 16 | (bit[9] & 0xFF) << 8 | bit[8] & 0xFF;
/*     */ 
/*     */ 
/*     */     
/* 100 */     int[] bitI = new int[_width * _height];
/* 101 */     BufferedImage img = new BufferedImage(_width, _height, 1);
/* 102 */     WritableRaster raster = img.getRaster();
/*     */ 
/*     */ 
/*     */     
/* 106 */     int _headerSize = (bit[3] & 0xFF) << 24 | (bit[2] & 0xFF) << 16 | (bit[1] & 0xFF) << 8 | bit[0] & 0xFF;
/*     */ 
/*     */     
/* 109 */     int _planes = (bit[13] & 0xFF) << 8 | bit[12] & 0xFF;
/*     */     
/* 111 */     int _nbit = (bit[15] & 0xFF) << 8 | bit[14] & 0xFF;
/*     */ 
/*     */     
/* 114 */     int _size = (bit[23] & 0xFF) << 24 | (bit[22] & 0xFF) << 16 | (bit[21] & 0xFF) << 8 | bit[20] & 0xFF;
/*     */ 
/*     */     
/* 117 */     if (_size == 0) _size = ((_width * _nbit + 31 & 0xFFFFFFE0) >> 3) * _height;
/*     */ 
/*     */     
/* 120 */     int _clrused = (bit[35] & 0xFF) << 24 | (bit[34] & 0xFF) << 16 | (bit[33] & 0xFF) << 8 | bit[32] & 0xFF;
/*     */ 
/*     */ 
/*     */     
/* 124 */     if (_nbit == 24) {
/*     */       
/* 126 */       int pad = _size / _height - _width * 3;
/* 127 */       int offset = _headerSize;
/*     */       
/* 129 */       for (int j = 0; j < _height; j++) {
/* 130 */         for (int i = 0; i < _width; i++) {
/* 131 */           bitI[_width * (_height - j - 1) + i] = 0xFF000000 | (bit[offset + 2] & 0xFF) << 16 | (bit[offset + 1] & 0xFF) << 8 | bit[offset] & 0xFF;
/*     */ 
/*     */           
/* 134 */           offset += 3;
/*     */         } 
/* 136 */         offset += pad;
/*     */       }
/*     */     
/* 139 */     } else if (_nbit == 8) {
/*     */       
/* 141 */       int nbColors = 0;
/* 142 */       if (_clrused > 0) { nbColors = _clrused; }
/* 143 */       else { nbColors = 256; }
/*     */       
/* 145 */       int offset = _headerSize;
/* 146 */       int[] palette = new int[nbColors];
/* 147 */       for (int i = 0; i < nbColors; i++) {
/* 148 */         palette[i] = 0xFF000000 | (bit[offset + 2] & 0xFF) << 16 | (bit[offset + 1] & 0xFF) << 8 | bit[offset] & 0xFF;
/*     */ 
/*     */         
/* 151 */         offset += 4;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 158 */       _size = bit.length - offset;
/* 159 */       int pad = _size / _height - _width;
/* 160 */       for (int j = 0; j < _height; j++) {
/* 161 */         for (int k = 0; k < _width; k++) {
/* 162 */           bitI[_width * (_height - j - 1) + k] = palette[bit[offset] & 0xFF];
/* 163 */           offset++;
/*     */         } 
/* 165 */         offset += pad;
/*     */       }
/*     */     
/* 168 */     } else if (_nbit == 1) {
/*     */       
/* 170 */       int nbColors = 2;
/*     */       
/* 172 */       int offset = _headerSize;
/* 173 */       int[] palette = new int[nbColors];
/* 174 */       for (int i = 0; i < nbColors; i++) {
/* 175 */         palette[i] = 0xFF000000 | (bit[offset + 2] & 0xFF) << 16 | (bit[offset + 1] & 0xFF) << 8 | bit[offset] & 0xFF;
/*     */ 
/*     */         
/* 178 */         offset += 4;
/*     */       } 
/*     */ 
/*     */       
/* 182 */       int pos = 7;
/* 183 */       byte currentByte = bit[offset];
/*     */       
/* 185 */       int pad = _size / _height - _width / 8;
/* 186 */       for (int j = 0; j < _height; j++) {
/* 187 */         for (int k = 0; k < _width; k++) {
/* 188 */           if ((currentByte & 1 << pos) != 0) { bitI[_width * (_height - j - 1) + k] = palette[1]; }
/* 189 */           else { bitI[_width * (_height - j - 1) + k] = palette[0]; }
/* 190 */            pos--;
/* 191 */           if (pos == -1) {
/* 192 */             pos = 7;
/* 193 */             offset++;
/* 194 */             if (offset < bit.length)
/* 195 */               currentByte = bit[offset]; 
/*     */           } 
/*     */         } 
/* 198 */         offset += pad;
/* 199 */         pos = 7;
/* 200 */         if (offset < bit.length) currentByte = bit[offset]; 
/*     */       } 
/*     */     } 
/* 203 */     raster.setDataElements(0, 0, _width, _height, bitI);
/* 204 */     return img;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributedCharacterIterator getCharacterIterator(Graphics2D g2d, String sr, WMFFont wmffont) {
/* 211 */     return getAttributedString(g2d, sr, wmffont).getIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributedCharacterIterator getCharacterIterator(Graphics2D g2d, String sr, WMFFont wmffont, int align) {
/* 219 */     AttributedString ats = getAttributedString(g2d, sr, wmffont);
/*     */     
/* 221 */     return ats.getIterator();
/*     */   }
/*     */   
/*     */   protected AttributedString getAttributedString(Graphics2D g2d, String sr, WMFFont wmffont) {
/* 225 */     AttributedString ats = new AttributedString(sr);
/* 226 */     Font font = g2d.getFont();
/* 227 */     ats.addAttribute(TextAttribute.SIZE, Float.valueOf(font.getSize2D()));
/* 228 */     ats.addAttribute(TextAttribute.FONT, font);
/* 229 */     if (this.wmfFont.underline != 0)
/* 230 */       ats.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON); 
/* 231 */     if (this.wmfFont.italic != 0)
/* 232 */     { ats.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE); }
/* 233 */     else { ats.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_REGULAR); }
/* 234 */      if (this.wmfFont.weight > 400)
/* 235 */     { ats.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD); }
/* 236 */     else { ats.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR); }
/*     */     
/* 238 */     return ats;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecordStore(WMFRecordStore currentStore) {
/* 245 */     if (currentStore == null) {
/* 246 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 249 */     this.currentStore = currentStore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMFRecordStore getRecordStore() {
/* 256 */     return this.currentStore;
/*     */   }
/*     */   
/*     */   protected int addObject(WMFRecordStore store, int type, Object obj) {
/* 260 */     return this.currentStore.addObject(type, obj);
/*     */   }
/*     */   
/*     */   protected int addObjectAt(WMFRecordStore store, int type, Object obj, int idx) {
/* 264 */     return this.currentStore.addObjectAt(type, obj, idx);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/AbstractWMFPainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */