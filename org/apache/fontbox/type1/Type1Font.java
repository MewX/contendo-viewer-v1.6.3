/*     */ package org.apache.fontbox.type1;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.fontbox.EncodedFont;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.cff.Type1CharString;
/*     */ import org.apache.fontbox.cff.Type1CharStringParser;
/*     */ import org.apache.fontbox.encoding.Encoding;
/*     */ import org.apache.fontbox.pfb.PfbParser;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Type1Font
/*     */   implements EncodedFont, FontBoxFont, Type1CharStringReader
/*     */ {
/*     */   public static Type1Font createWithPFB(InputStream pfbStream) throws IOException {
/*  54 */     PfbParser pfb = new PfbParser(pfbStream);
/*  55 */     Type1Parser parser = new Type1Parser();
/*  56 */     return parser.parse(pfb.getSegment1(), pfb.getSegment2());
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
/*     */   public static Type1Font createWithPFB(byte[] pfbBytes) throws IOException {
/*  69 */     PfbParser pfb = new PfbParser(pfbBytes);
/*  70 */     Type1Parser parser = new Type1Parser();
/*  71 */     return parser.parse(pfb.getSegment1(), pfb.getSegment2());
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
/*     */   public static Type1Font createWithSegments(byte[] segment1, byte[] segment2) throws IOException {
/*  84 */     Type1Parser parser = new Type1Parser();
/*  85 */     return parser.parse(segment1, segment2);
/*     */   }
/*     */ 
/*     */   
/*  89 */   String fontName = "";
/*  90 */   Encoding encoding = null;
/*     */   int paintType;
/*     */   int fontType;
/*  93 */   List<Number> fontMatrix = new ArrayList<Number>();
/*  94 */   List<Number> fontBBox = new ArrayList<Number>();
/*     */   int uniqueID;
/*     */   float strokeWidth;
/*  97 */   String fontID = "";
/*     */ 
/*     */   
/* 100 */   String version = "";
/* 101 */   String notice = "";
/* 102 */   String fullName = "";
/* 103 */   String familyName = "";
/* 104 */   String weight = "";
/*     */   
/*     */   float italicAngle;
/*     */   
/*     */   boolean isFixedPitch;
/*     */   float underlinePosition;
/*     */   float underlineThickness;
/* 111 */   List<Number> blueValues = new ArrayList<Number>();
/* 112 */   List<Number> otherBlues = new ArrayList<Number>();
/* 113 */   List<Number> familyBlues = new ArrayList<Number>();
/* 114 */   List<Number> familyOtherBlues = new ArrayList<Number>();
/*     */   
/*     */   float blueScale;
/* 117 */   List<Number> stdHW = new ArrayList<Number>(); int blueShift; int blueFuzz;
/* 118 */   List<Number> stdVW = new ArrayList<Number>();
/* 119 */   List<Number> stemSnapH = new ArrayList<Number>();
/* 120 */   List<Number> stemSnapV = new ArrayList<Number>();
/*     */   
/*     */   boolean forceBold;
/*     */   
/*     */   int languageGroup;
/* 125 */   final List<byte[]> subrs = (List)new ArrayList<byte>();
/* 126 */   final Map<String, byte[]> charstrings = (Map)new LinkedHashMap<String, byte>();
/*     */ 
/*     */   
/* 129 */   private final Map<String, Type1CharString> charStringCache = new ConcurrentHashMap<String, Type1CharString>();
/*     */ 
/*     */   
/*     */   private final byte[] segment1;
/*     */ 
/*     */   
/*     */   private final byte[] segment2;
/*     */ 
/*     */ 
/*     */   
/*     */   Type1Font(byte[] segment1, byte[] segment2) {
/* 140 */     this.segment1 = segment1;
/* 141 */     this.segment2 = segment2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<byte[]> getSubrsArray() {
/* 151 */     return (List)Collections.unmodifiableList((List)this.subrs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, byte[]> getCharStringsDict() {
/* 161 */     return (Map)Collections.unmodifiableMap((Map)this.charstrings);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 167 */     return this.fontName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(String name) throws IOException {
/* 173 */     return getType1CharString(name).getPath();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth(String name) throws IOException {
/* 179 */     return getType1CharString(name).getWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(String name) {
/* 185 */     return (this.charstrings.get(name) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type1CharString getType1CharString(String name) throws IOException {
/* 191 */     Type1CharString type1 = this.charStringCache.get(name);
/* 192 */     if (type1 == null) {
/*     */       
/* 194 */       byte[] bytes = this.charstrings.get(name);
/* 195 */       if (bytes == null)
/*     */       {
/* 197 */         bytes = this.charstrings.get(".notdef");
/*     */       }
/* 199 */       Type1CharStringParser parser = new Type1CharStringParser(this.fontName, name);
/* 200 */       List<Object> sequence = parser.parse(bytes, this.subrs);
/* 201 */       type1 = new Type1CharString(this, this.fontName, name, sequence);
/* 202 */       this.charStringCache.put(name, type1);
/*     */     } 
/* 204 */     return type1;
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
/*     */   public String getFontName() {
/* 216 */     return this.fontName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Encoding getEncoding() {
/* 226 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPaintType() {
/* 236 */     return this.paintType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFontType() {
/* 246 */     return this.fontType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getFontMatrix() {
/* 256 */     return Collections.unmodifiableList(this.fontMatrix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getFontBBox() {
/* 267 */     return new BoundingBox(this.fontBBox);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUniqueID() {
/* 277 */     return this.uniqueID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrokeWidth() {
/* 287 */     return this.strokeWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontID() {
/* 297 */     return this.fontID;
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
/*     */   public String getVersion() {
/* 309 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNotice() {
/* 319 */     return this.notice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullName() {
/* 329 */     return this.fullName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFamilyName() {
/* 339 */     return this.familyName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWeight() {
/* 349 */     return this.weight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getItalicAngle() {
/* 359 */     return this.italicAngle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFixedPitch() {
/* 369 */     return this.isFixedPitch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnderlinePosition() {
/* 379 */     return this.underlinePosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnderlineThickness() {
/* 389 */     return this.underlineThickness;
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
/*     */   public List<Number> getBlueValues() {
/* 401 */     return Collections.unmodifiableList(this.blueValues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getOtherBlues() {
/* 411 */     return Collections.unmodifiableList(this.otherBlues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getFamilyBlues() {
/* 421 */     return Collections.unmodifiableList(this.familyBlues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getFamilyOtherBlues() {
/* 431 */     return Collections.unmodifiableList(this.familyOtherBlues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlueScale() {
/* 441 */     return this.blueScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlueShift() {
/* 451 */     return this.blueShift;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlueFuzz() {
/* 461 */     return this.blueFuzz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getStdHW() {
/* 471 */     return Collections.unmodifiableList(this.stdHW);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getStdVW() {
/* 481 */     return Collections.unmodifiableList(this.stdVW);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getStemSnapH() {
/* 491 */     return Collections.unmodifiableList(this.stemSnapH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getStemSnapV() {
/* 501 */     return Collections.unmodifiableList(this.stemSnapV);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isForceBold() {
/* 511 */     return this.forceBold;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLanguageGroup() {
/* 521 */     return this.languageGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getASCIISegment() {
/* 531 */     return this.segment1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBinarySegment() {
/* 541 */     return this.segment2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 550 */     return getClass().getName() + "[fontName=" + this.fontName + ", fullName=" + this.fullName + ", encoding=" + this.encoding + ", charStringsDict=" + this.charstrings + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/type1/Type1Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */