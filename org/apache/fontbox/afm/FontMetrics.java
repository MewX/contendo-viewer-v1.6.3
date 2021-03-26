/*     */ package org.apache.fontbox.afm;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class FontMetrics
/*     */ {
/*     */   private float afmVersion;
/*  38 */   private int metricSets = 0;
/*     */   private String fontName;
/*     */   private String fullName;
/*     */   private String familyName;
/*     */   private String weight;
/*     */   private BoundingBox fontBBox;
/*     */   private String fontVersion;
/*     */   private String notice;
/*     */   private String encodingScheme;
/*     */   private int mappingScheme;
/*     */   private int escChar;
/*     */   private String characterSet;
/*     */   private int characters;
/*     */   private boolean isBaseFont;
/*     */   private float[] vVector;
/*     */   private boolean isFixedV;
/*     */   private float capHeight;
/*     */   private float xHeight;
/*     */   private float ascender;
/*     */   private float descender;
/*  58 */   private final List<String> comments = new ArrayList<String>();
/*     */   
/*     */   private float underlinePosition;
/*     */   
/*     */   private float underlineThickness;
/*     */   private float italicAngle;
/*     */   private float[] charWidth;
/*     */   private boolean isFixedPitch;
/*     */   private float standardHorizontalWidth;
/*     */   private float standardVerticalWidth;
/*  68 */   private List<CharMetric> charMetrics = new ArrayList<CharMetric>();
/*  69 */   private Map<String, CharMetric> charMetricsMap = new HashMap<String, CharMetric>();
/*  70 */   private List<TrackKern> trackKern = new ArrayList<TrackKern>();
/*  71 */   private List<Composite> composites = new ArrayList<Composite>();
/*  72 */   private List<KernPair> kernPairs = new ArrayList<KernPair>();
/*  73 */   private List<KernPair> kernPairs0 = new ArrayList<KernPair>();
/*  74 */   private List<KernPair> kernPairs1 = new ArrayList<KernPair>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCharacterWidth(String name) {
/*  92 */     float result = 0.0F;
/*  93 */     CharMetric metric = this.charMetricsMap.get(name);
/*  94 */     if (metric != null)
/*     */     {
/*  96 */       result = metric.getWx();
/*     */     }
/*  98 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCharacterHeight(String name) {
/* 109 */     float result = 0.0F;
/* 110 */     CharMetric metric = this.charMetricsMap.get(name);
/* 111 */     if (metric != null) {
/*     */       
/* 113 */       result = metric.getWy();
/* 114 */       if (result == 0.0F)
/*     */       {
/* 116 */         result = metric.getBoundingBox().getHeight();
/*     */       }
/*     */     } 
/* 119 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAverageCharacterWidth() {
/* 130 */     float average = 0.0F;
/* 131 */     float totalWidths = 0.0F;
/* 132 */     float characterCount = 0.0F;
/* 133 */     for (CharMetric metric : this.charMetrics) {
/*     */       
/* 135 */       if (metric.getWx() > 0.0F) {
/*     */         
/* 137 */         totalWidths += metric.getWx();
/* 138 */         characterCount++;
/*     */       } 
/*     */     } 
/* 141 */     if (totalWidths > 0.0F)
/*     */     {
/* 143 */       average = totalWidths / characterCount;
/*     */     }
/* 145 */     return average;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addComment(String comment) {
/* 155 */     this.comments.add(comment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getComments() {
/* 165 */     return Collections.unmodifiableList(this.comments);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAFMVersion() {
/* 175 */     return this.afmVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetricSets() {
/* 185 */     return this.metricSets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAFMVersion(float afmVersionValue) {
/* 195 */     this.afmVersion = afmVersionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetricSets(int metricSetsValue) {
/* 206 */     if (metricSetsValue < 0 || metricSetsValue > 2)
/*     */     {
/* 208 */       throw new IllegalArgumentException("The metricSets attribute must be in the set {0,1,2} and not '" + metricSetsValue + "'");
/*     */     }
/*     */     
/* 211 */     this.metricSets = metricSetsValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontName() {
/* 221 */     return this.fontName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontName(String name) {
/* 231 */     this.fontName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullName() {
/* 241 */     return this.fullName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFullName(String fullNameValue) {
/* 251 */     this.fullName = fullNameValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFamilyName() {
/* 261 */     return this.familyName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFamilyName(String familyNameValue) {
/* 271 */     this.familyName = familyNameValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWeight() {
/* 281 */     return this.weight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWeight(String weightValue) {
/* 291 */     this.weight = weightValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getFontBBox() {
/* 301 */     return this.fontBBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontBBox(BoundingBox bBox) {
/* 311 */     this.fontBBox = bBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNotice() {
/* 321 */     return this.notice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNotice(String noticeValue) {
/* 331 */     this.notice = noticeValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodingScheme() {
/* 341 */     return this.encodingScheme;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncodingScheme(String encodingSchemeValue) {
/* 351 */     this.encodingScheme = encodingSchemeValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMappingScheme() {
/* 361 */     return this.mappingScheme;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMappingScheme(int mappingSchemeValue) {
/* 371 */     this.mappingScheme = mappingSchemeValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEscChar() {
/* 381 */     return this.escChar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEscChar(int escCharValue) {
/* 391 */     this.escChar = escCharValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCharacterSet() {
/* 401 */     return this.characterSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharacterSet(String characterSetValue) {
/* 411 */     this.characterSet = characterSetValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharacters() {
/* 421 */     return this.characters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharacters(int charactersValue) {
/* 431 */     this.characters = charactersValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBaseFont() {
/* 441 */     return this.isBaseFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsBaseFont(boolean isBaseFontValue) {
/* 451 */     this.isBaseFont = isBaseFontValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getVVector() {
/* 461 */     return this.vVector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVVector(float[] vVectorValue) {
/* 471 */     this.vVector = vVectorValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFixedV() {
/* 481 */     return this.isFixedV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsFixedV(boolean isFixedVValue) {
/* 491 */     this.isFixedV = isFixedVValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCapHeight() {
/* 501 */     return this.capHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCapHeight(float capHeightValue) {
/* 511 */     this.capHeight = capHeightValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getXHeight() {
/* 521 */     return this.xHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXHeight(float xHeightValue) {
/* 531 */     this.xHeight = xHeightValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAscender() {
/* 541 */     return this.ascender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAscender(float ascenderValue) {
/* 551 */     this.ascender = ascenderValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDescender() {
/* 561 */     return this.descender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescender(float descenderValue) {
/* 571 */     this.descender = descenderValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontVersion() {
/* 581 */     return this.fontVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontVersion(String fontVersionValue) {
/* 591 */     this.fontVersion = fontVersionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnderlinePosition() {
/* 601 */     return this.underlinePosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnderlinePosition(float underlinePositionValue) {
/* 611 */     this.underlinePosition = underlinePositionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnderlineThickness() {
/* 621 */     return this.underlineThickness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnderlineThickness(float underlineThicknessValue) {
/* 631 */     this.underlineThickness = underlineThicknessValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getItalicAngle() {
/* 641 */     return this.italicAngle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItalicAngle(float italicAngleValue) {
/* 651 */     this.italicAngle = italicAngleValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getCharWidth() {
/* 661 */     return this.charWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharWidth(float[] charWidthValue) {
/* 671 */     this.charWidth = charWidthValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFixedPitch() {
/* 681 */     return this.isFixedPitch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFixedPitch(boolean isFixedPitchValue) {
/* 691 */     this.isFixedPitch = isFixedPitchValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<CharMetric> getCharMetrics() {
/* 699 */     return Collections.unmodifiableList(this.charMetrics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharMetrics(List<CharMetric> charMetricsValue) {
/* 707 */     this.charMetrics = charMetricsValue;
/* 708 */     this.charMetricsMap = new HashMap<String, CharMetric>(this.charMetrics.size());
/* 709 */     for (CharMetric metric : charMetricsValue)
/*     */     {
/* 711 */       this.charMetricsMap.put(metric.getName(), metric);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCharMetric(CharMetric metric) {
/* 722 */     this.charMetrics.add(metric);
/* 723 */     this.charMetricsMap.put(metric.getName(), metric);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TrackKern> getTrackKern() {
/* 731 */     return Collections.unmodifiableList(this.trackKern);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrackKern(List<TrackKern> trackKernValue) {
/* 739 */     this.trackKern = trackKernValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTrackKern(TrackKern kern) {
/* 749 */     this.trackKern.add(kern);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Composite> getComposites() {
/* 757 */     return Collections.unmodifiableList(this.composites);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComposites(List<Composite> compositesList) {
/* 765 */     this.composites = compositesList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addComposite(Composite composite) {
/* 775 */     this.composites.add(composite);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<KernPair> getKernPairs() {
/* 783 */     return Collections.unmodifiableList(this.kernPairs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addKernPair(KernPair kernPair) {
/* 793 */     this.kernPairs.add(kernPair);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKernPairs(List<KernPair> kernPairsList) {
/* 801 */     this.kernPairs = kernPairsList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<KernPair> getKernPairs0() {
/* 809 */     return Collections.unmodifiableList(this.kernPairs0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addKernPair0(KernPair kernPair) {
/* 819 */     this.kernPairs0.add(kernPair);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKernPairs0(List<KernPair> kernPairs0List) {
/* 827 */     this.kernPairs0 = kernPairs0List;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<KernPair> getKernPairs1() {
/* 835 */     return Collections.unmodifiableList(this.kernPairs1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addKernPair1(KernPair kernPair) {
/* 845 */     this.kernPairs1.add(kernPair);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKernPairs1(List<KernPair> kernPairs1List) {
/* 853 */     this.kernPairs1 = kernPairs1List;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStandardHorizontalWidth() {
/* 861 */     return this.standardHorizontalWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStandardHorizontalWidth(float standardHorizontalWidthValue) {
/* 869 */     this.standardHorizontalWidth = standardHorizontalWidthValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStandardVerticalWidth() {
/* 877 */     return this.standardVerticalWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStandardVerticalWidth(float standardVerticalWidthValue) {
/* 885 */     this.standardVerticalWidth = standardVerticalWidthValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/afm/FontMetrics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */