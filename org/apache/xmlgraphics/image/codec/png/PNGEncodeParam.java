/*      */ package org.apache.xmlgraphics.image.codec.png;
/*      */ 
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import org.apache.xmlgraphics.image.codec.util.ImageEncodeParam;
/*      */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
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
/*      */ public abstract class PNGEncodeParam
/*      */   implements ImageEncodeParam
/*      */ {
/*      */   private static final long serialVersionUID = -7851509538552141263L;
/*      */   public static final int INTENT_PERCEPTUAL = 0;
/*      */   public static final int INTENT_RELATIVE = 1;
/*      */   public static final int INTENT_SATURATION = 2;
/*      */   public static final int INTENT_ABSOLUTE = 3;
/*      */   public static final int PNG_FILTER_NONE = 0;
/*      */   public static final int PNG_FILTER_SUB = 1;
/*      */   public static final int PNG_FILTER_UP = 2;
/*      */   public static final int PNG_FILTER_AVERAGE = 3;
/*      */   public static final int PNG_FILTER_PAETH = 4;
/*      */   protected int bitDepth;
/*      */   protected boolean bitDepthSet;
/*      */   private boolean useInterlacing;
/*      */   private float[] chromaticity;
/*      */   private boolean chromaticitySet;
/*      */   private float gamma;
/*      */   private boolean gammaSet;
/*      */   private int[] paletteHistogram;
/*      */   private boolean paletteHistogramSet;
/*      */   private byte[] iccProfileData;
/*      */   private boolean iccProfileDataSet;
/*      */   private int[] physicalDimension;
/*      */   private boolean physicalDimensionSet;
/*      */   private PNGSuggestedPaletteEntry[] suggestedPalette;
/*      */   private boolean suggestedPaletteSet;
/*      */   private int[] significantBits;
/*      */   private boolean significantBitsSet;
/*      */   private int srgbIntent;
/*      */   private boolean srgbIntentSet;
/*      */   private String[] text;
/*      */   private boolean textSet;
/*      */   private Date modificationTime;
/*      */   private boolean modificationTimeSet;
/*      */   boolean transparencySet;
/*      */   private String[] zText;
/*      */   private boolean zTextSet;
/*      */   
/*      */   public static PNGEncodeParam getDefaultEncodeParam(RenderedImage im) {
/*   97 */     ColorModel colorModel = im.getColorModel();
/*   98 */     if (colorModel instanceof java.awt.image.IndexColorModel) {
/*   99 */       return new Palette();
/*      */     }
/*      */     
/*  102 */     SampleModel sampleModel = im.getSampleModel();
/*  103 */     int numBands = sampleModel.getNumBands();
/*      */     
/*  105 */     if (numBands == 1 || numBands == 2) {
/*  106 */       return new Gray();
/*      */     }
/*  108 */     return new RGB();
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract void setBitDepth(int paramInt);
/*      */ 
/*      */   
/*      */   public static class Palette
/*      */     extends PNGEncodeParam
/*      */   {
/*      */     private static final long serialVersionUID = -5181545170427733891L;
/*      */     
/*      */     private boolean backgroundSet;
/*      */     private int[] palette;
/*      */     private boolean paletteSet;
/*      */     private int backgroundPaletteIndex;
/*      */     private int[] transparency;
/*      */     
/*      */     public void unsetBackground() {
/*  127 */       this.backgroundSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBackgroundSet() {
/*  134 */       return this.backgroundSet;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setBitDepth(int bitDepth) {
/*  143 */       if (bitDepth != 1 && bitDepth != 2 && bitDepth != 4 && bitDepth != 8)
/*      */       {
/*  145 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam2"));
/*      */       }
/*  147 */       this.bitDepth = bitDepth;
/*  148 */       this.bitDepthSet = true;
/*      */     }
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
/*      */     public void setPalette(int[] rgb) {
/*  168 */       if (rgb.length < 3 || rgb.length > 768) {
/*  169 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam0"));
/*      */       }
/*      */       
/*  172 */       if (rgb.length % 3 != 0) {
/*  173 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam1"));
/*      */       }
/*      */ 
/*      */       
/*  177 */       this.palette = (int[])rgb.clone();
/*  178 */       this.paletteSet = true;
/*      */     }
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
/*      */     public int[] getPalette() {
/*  192 */       if (!this.paletteSet) {
/*  193 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam3"));
/*      */       }
/*  195 */       return (int[])this.palette.clone();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void unsetPalette() {
/*  202 */       this.palette = null;
/*  203 */       this.paletteSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isPaletteSet() {
/*  210 */       return this.paletteSet;
/*      */     }
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
/*      */     public void setBackgroundPaletteIndex(int index) {
/*  223 */       this.backgroundPaletteIndex = index;
/*  224 */       this.backgroundSet = true;
/*      */     }
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
/*      */     public int getBackgroundPaletteIndex() {
/*  237 */       if (!this.backgroundSet) {
/*  238 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam4"));
/*      */       }
/*  240 */       return this.backgroundPaletteIndex;
/*      */     }
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
/*      */     public void setPaletteTransparency(byte[] alpha) {
/*  255 */       this.transparency = new int[alpha.length];
/*  256 */       for (int i = 0; i < alpha.length; i++) {
/*  257 */         this.transparency[i] = alpha[i] & 0xFF;
/*      */       }
/*  259 */       this.transparencySet = true;
/*      */     }
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
/*      */     public byte[] getPaletteTransparency() {
/*  273 */       if (!this.transparencySet) {
/*  274 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam5"));
/*      */       }
/*  276 */       byte[] alpha = new byte[this.transparency.length];
/*  277 */       for (int i = 0; i < alpha.length; i++) {
/*  278 */         alpha[i] = (byte)this.transparency[i];
/*      */       }
/*  280 */       return alpha;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class Gray
/*      */     extends PNGEncodeParam
/*      */   {
/*      */     private static final long serialVersionUID = -2055439792025795274L;
/*      */     
/*      */     private boolean backgroundSet;
/*      */     
/*      */     private int backgroundPaletteGray;
/*      */     
/*      */     private int[] transparency;
/*      */     private int bitShift;
/*      */     private boolean bitShiftSet;
/*      */     
/*      */     public void unsetBackground() {
/*  299 */       this.backgroundSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBackgroundSet() {
/*  306 */       return this.backgroundSet;
/*      */     }
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
/*      */     public void setBitDepth(int bitDepth) {
/*  320 */       if (bitDepth != 1 && bitDepth != 2 && bitDepth != 4 && bitDepth != 8 && bitDepth != 16)
/*      */       {
/*  322 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam2"));
/*      */       }
/*  324 */       this.bitDepth = bitDepth;
/*  325 */       this.bitDepthSet = true;
/*      */     }
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
/*      */     public void setBackgroundGray(int gray) {
/*  338 */       this.backgroundPaletteGray = gray;
/*  339 */       this.backgroundSet = true;
/*      */     }
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
/*      */     public int getBackgroundGray() {
/*  353 */       if (!this.backgroundSet) {
/*  354 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam6"));
/*      */       }
/*  356 */       return this.backgroundPaletteGray;
/*      */     }
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
/*      */     public void setTransparentGray(int transparentGray) {
/*  372 */       this.transparency = new int[1];
/*  373 */       this.transparency[0] = transparentGray;
/*  374 */       this.transparencySet = true;
/*      */     }
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
/*      */     public int getTransparentGray() {
/*  388 */       if (!this.transparencySet) {
/*  389 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam7"));
/*      */       }
/*  391 */       int gray = this.transparency[0];
/*  392 */       return gray;
/*      */     }
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
/*      */     public void setBitShift(int bitShift) {
/*  405 */       if (bitShift < 0) {
/*  406 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam25"));
/*      */       }
/*  408 */       this.bitShift = bitShift;
/*  409 */       this.bitShiftSet = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getBitShift() {
/*  421 */       if (!this.bitShiftSet) {
/*  422 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam8"));
/*      */       }
/*  424 */       return this.bitShift;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void unsetBitShift() {
/*  432 */       this.bitShiftSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBitShiftSet() {
/*  439 */       return this.bitShiftSet;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBitDepthSet() {
/*  446 */       return this.bitDepthSet;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class RGB
/*      */     extends PNGEncodeParam
/*      */   {
/*      */     private static final long serialVersionUID = -8918762026006670891L;
/*      */     
/*      */     private boolean backgroundSet;
/*      */     
/*      */     private int[] backgroundRGB;
/*      */     
/*      */     private int[] transparency;
/*      */ 
/*      */     
/*      */     public void unsetBackground() {
/*  465 */       this.backgroundSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBackgroundSet() {
/*  472 */       return this.backgroundSet;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setBitDepth(int bitDepth) {
/*  480 */       if (bitDepth != 8 && bitDepth != 16) {
/*  481 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam26"));
/*      */       }
/*  483 */       this.bitDepth = bitDepth;
/*  484 */       this.bitDepthSet = true;
/*      */     }
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
/*      */     public void setBackgroundRGB(int[] rgb) {
/*  498 */       if (rgb.length != 3) {
/*  499 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam27"));
/*      */       }
/*  501 */       this.backgroundRGB = rgb;
/*  502 */       this.backgroundSet = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int[] getBackgroundRGB() {
/*  514 */       if (!this.backgroundSet) {
/*  515 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam9"));
/*      */       }
/*  517 */       return this.backgroundRGB;
/*      */     }
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
/*      */     public void setTransparentRGB(int[] transparentRGB) {
/*  533 */       this.transparency = (int[])transparentRGB.clone();
/*  534 */       this.transparencySet = true;
/*      */     }
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
/*      */     public int[] getTransparentRGB() {
/*  547 */       if (!this.transparencySet) {
/*  548 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam10"));
/*      */       }
/*  550 */       return (int[])this.transparency.clone();
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
/*      */   public int getBitDepth() {
/*  571 */     if (!this.bitDepthSet) {
/*  572 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam11"));
/*      */     }
/*  574 */     return this.bitDepth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetBitDepth() {
/*  584 */     this.bitDepthSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInterlacing(boolean useInterlacing) {
/*  593 */     this.useInterlacing = useInterlacing;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getInterlacing() {
/*  600 */     return this.useInterlacing;
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
/*      */   public void unsetBackground() {
/*  623 */     throw new RuntimeException(PropertyUtil.getString("PNGEncodeParam23"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBackgroundSet() {
/*  633 */     throw new RuntimeException(PropertyUtil.getString("PNGEncodeParam24"));
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
/*      */   public void setChromaticity(float[] chromaticity) {
/*  653 */     if (chromaticity.length != 8) {
/*  654 */       throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam28"));
/*      */     }
/*  656 */     this.chromaticity = (float[])chromaticity.clone();
/*  657 */     this.chromaticitySet = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChromaticity(float whitePointX, float whitePointY, float redX, float redY, float greenX, float greenY, float blueX, float blueY) {
/*  667 */     float[] chroma = new float[8];
/*  668 */     chroma[0] = whitePointX;
/*  669 */     chroma[1] = whitePointY;
/*  670 */     chroma[2] = redX;
/*  671 */     chroma[3] = redY;
/*  672 */     chroma[4] = greenX;
/*  673 */     chroma[5] = greenY;
/*  674 */     chroma[6] = blueX;
/*  675 */     chroma[7] = blueY;
/*  676 */     setChromaticity(chroma);
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
/*      */   public float[] getChromaticity() {
/*  692 */     if (!this.chromaticitySet) {
/*  693 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam12"));
/*      */     }
/*  695 */     return (float[])this.chromaticity.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetChromaticity() {
/*  702 */     this.chromaticity = null;
/*  703 */     this.chromaticitySet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChromaticitySet() {
/*  710 */     return this.chromaticitySet;
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
/*      */   public void setGamma(float gamma) {
/*  724 */     this.gamma = gamma;
/*  725 */     this.gammaSet = true;
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
/*      */   public float getGamma() {
/*  737 */     if (!this.gammaSet) {
/*  738 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam13"));
/*      */     }
/*  740 */     return this.gamma;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetGamma() {
/*  747 */     this.gammaSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isGammaSet() {
/*  754 */     return this.gammaSet;
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
/*      */   public void setPaletteHistogram(int[] paletteHistogram) {
/*  770 */     this.paletteHistogram = (int[])paletteHistogram.clone();
/*  771 */     this.paletteHistogramSet = true;
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
/*      */   public int[] getPaletteHistogram() {
/*  783 */     if (!this.paletteHistogramSet) {
/*  784 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam14"));
/*      */     }
/*  786 */     return this.paletteHistogram;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetPaletteHistogram() {
/*  793 */     this.paletteHistogram = null;
/*  794 */     this.paletteHistogramSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPaletteHistogramSet() {
/*  801 */     return this.paletteHistogramSet;
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
/*      */   public void setICCProfileData(byte[] iccProfileData) {
/*  816 */     this.iccProfileData = (byte[])iccProfileData.clone();
/*  817 */     this.iccProfileDataSet = true;
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
/*      */   public byte[] getICCProfileData() {
/*  829 */     if (!this.iccProfileDataSet) {
/*  830 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam15"));
/*      */     }
/*  832 */     return (byte[])this.iccProfileData.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetICCProfileData() {
/*  839 */     this.iccProfileData = null;
/*  840 */     this.iccProfileDataSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isICCProfileDataSet() {
/*  847 */     return this.iccProfileDataSet;
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
/*      */   public void setPhysicalDimension(int[] physicalDimension) {
/*  865 */     this.physicalDimension = (int[])physicalDimension.clone();
/*  866 */     this.physicalDimensionSet = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPhysicalDimension(int xPixelsPerUnit, int yPixelsPerUnit, int unitSpecifier) {
/*  875 */     int[] pd = new int[3];
/*  876 */     pd[0] = xPixelsPerUnit;
/*  877 */     pd[1] = yPixelsPerUnit;
/*  878 */     pd[2] = unitSpecifier;
/*      */     
/*  880 */     setPhysicalDimension(pd);
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
/*      */   public int[] getPhysicalDimension() {
/*  895 */     if (!this.physicalDimensionSet) {
/*  896 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam16"));
/*      */     }
/*  898 */     return (int[])this.physicalDimension.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetPhysicalDimension() {
/*  905 */     this.physicalDimension = null;
/*  906 */     this.physicalDimensionSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPhysicalDimensionSet() {
/*  913 */     return this.physicalDimensionSet;
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
/*      */   public void setSuggestedPalette(PNGSuggestedPaletteEntry[] palette) {
/*  929 */     this.suggestedPalette = (PNGSuggestedPaletteEntry[])palette.clone();
/*  930 */     this.suggestedPaletteSet = true;
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
/*      */   public PNGSuggestedPaletteEntry[] getSuggestedPalette() {
/*  945 */     if (!this.suggestedPaletteSet) {
/*  946 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam17"));
/*      */     }
/*  948 */     return (PNGSuggestedPaletteEntry[])this.suggestedPalette.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetSuggestedPalette() {
/*  955 */     this.suggestedPalette = null;
/*  956 */     this.suggestedPaletteSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSuggestedPaletteSet() {
/*  963 */     return this.suggestedPaletteSet;
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
/*      */   public void setSignificantBits(int[] significantBits) {
/*  982 */     this.significantBits = (int[])significantBits.clone();
/*  983 */     this.significantBitsSet = true;
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
/*      */   public int[] getSignificantBits() {
/*  997 */     if (!this.significantBitsSet) {
/*  998 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam18"));
/*      */     }
/* 1000 */     return (int[])this.significantBits.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetSignificantBits() {
/* 1007 */     this.significantBits = null;
/* 1008 */     this.significantBitsSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSignificantBitsSet() {
/* 1015 */     return this.significantBitsSet;
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
/*      */   public void setSRGBIntent(int srgbIntent) {
/* 1032 */     this.srgbIntent = srgbIntent;
/* 1033 */     this.srgbIntentSet = true;
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
/*      */   public int getSRGBIntent() {
/* 1045 */     if (!this.srgbIntentSet) {
/* 1046 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam19"));
/*      */     }
/* 1048 */     return this.srgbIntent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetSRGBIntent() {
/* 1055 */     this.srgbIntentSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSRGBIntentSet() {
/* 1062 */     return this.srgbIntentSet;
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
/*      */   public void setText(String[] text) {
/* 1078 */     this.text = text;
/* 1079 */     this.textSet = true;
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
/*      */   public String[] getText() {
/* 1092 */     if (!this.textSet) {
/* 1093 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam20"));
/*      */     }
/* 1095 */     return this.text;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetText() {
/* 1102 */     this.text = null;
/* 1103 */     this.textSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTextSet() {
/* 1110 */     return this.textSet;
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
/*      */   public void setModificationTime(Date modificationTime) {
/* 1127 */     this.modificationTime = modificationTime;
/* 1128 */     this.modificationTimeSet = true;
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
/*      */   public Date getModificationTime() {
/* 1140 */     if (!this.modificationTimeSet) {
/* 1141 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam21"));
/*      */     }
/* 1143 */     return this.modificationTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetModificationTime() {
/* 1150 */     this.modificationTime = null;
/* 1151 */     this.modificationTimeSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isModificationTimeSet() {
/* 1158 */     return this.modificationTimeSet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetTransparency() {
/* 1169 */     this.transparencySet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTransparencySet() {
/* 1176 */     return this.transparencySet;
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
/*      */   public void setCompressedText(String[] text) {
/* 1192 */     this.zText = text;
/* 1193 */     this.zTextSet = true;
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
/*      */   public String[] getCompressedText() {
/* 1208 */     if (!this.zTextSet) {
/* 1209 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam22"));
/*      */     }
/* 1211 */     return this.zText;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetCompressedText() {
/* 1218 */     this.zText = null;
/* 1219 */     this.zTextSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCompressedTextSet() {
/* 1226 */     return this.zTextSet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1231 */   List chunkType = new ArrayList();
/* 1232 */   List chunkData = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void addPrivateChunk(String type, byte[] data) {
/* 1243 */     this.chunkType.add(type);
/* 1244 */     this.chunkData.add(data.clone());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized int getNumPrivateChunks() {
/* 1252 */     return this.chunkType.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized String getPrivateChunkType(int index) {
/* 1261 */     return this.chunkType.get(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized byte[] getPrivateChunkData(int index) {
/* 1271 */     return this.chunkData.get(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void removeUnsafeToCopyPrivateChunks() {
/* 1280 */     List<String> newChunkType = new ArrayList();
/* 1281 */     List<byte[]> newChunkData = new ArrayList();
/*      */     
/* 1283 */     int len = getNumPrivateChunks();
/* 1284 */     for (int i = 0; i < len; i++) {
/* 1285 */       String type = getPrivateChunkType(i);
/* 1286 */       char lastChar = type.charAt(3);
/* 1287 */       if (lastChar >= 'a' && lastChar <= 'z') {
/* 1288 */         newChunkType.add(type);
/* 1289 */         newChunkData.add(getPrivateChunkData(i));
/*      */       } 
/*      */     } 
/*      */     
/* 1293 */     this.chunkType = newChunkType;
/* 1294 */     this.chunkData = newChunkData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void removeAllPrivateChunks() {
/* 1301 */     this.chunkType = new ArrayList();
/* 1302 */     this.chunkData = new ArrayList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int abs(int x) {
/* 1309 */     return (x < 0) ? -x : x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int paethPredictor(int a, int b, int c) {
/* 1318 */     int p = a + b - c;
/* 1319 */     int pa = abs(p - a);
/* 1320 */     int pb = abs(p - b);
/* 1321 */     int pc = abs(p - c);
/*      */     
/* 1323 */     if (pa <= pb && pa <= pc)
/* 1324 */       return a; 
/* 1325 */     if (pb <= pc) {
/* 1326 */       return b;
/*      */     }
/* 1328 */     return c;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int filterRow(byte[] currRow, byte[] prevRow, byte[][] scratchRows, int bytesPerRow, int bytesPerPixel) {
/* 1395 */     int[] badness = { 0, 0, 0, 0, 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1404 */     for (int i = bytesPerPixel; i < bytesPerRow + bytesPerPixel; i++) {
/* 1405 */       int curr = currRow[i] & 0xFF;
/* 1406 */       int left = currRow[i - bytesPerPixel] & 0xFF;
/* 1407 */       int up = prevRow[i] & 0xFF;
/* 1408 */       int upleft = prevRow[i - bytesPerPixel] & 0xFF;
/*      */ 
/*      */       
/* 1411 */       badness[0] = badness[0] + curr;
/*      */ 
/*      */       
/* 1414 */       int diff = curr - left;
/* 1415 */       scratchRows[1][i] = (byte)diff;
/* 1416 */       badness[1] = badness[1] + ((diff > 0) ? diff : -diff);
/*      */ 
/*      */       
/* 1419 */       diff = curr - up;
/* 1420 */       scratchRows[2][i] = (byte)diff;
/* 1421 */       badness[2] = badness[2] + ((diff >= 0) ? diff : -diff);
/*      */ 
/*      */       
/* 1424 */       diff = curr - (left + up >> 1);
/* 1425 */       scratchRows[3][i] = (byte)diff;
/* 1426 */       badness[3] = badness[3] + ((diff >= 0) ? diff : -diff);
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
/* 1446 */       int pa = up - upleft;
/* 1447 */       int pb = left - upleft;
/* 1448 */       if (pa < 0) {
/* 1449 */         if (pb < 0) {
/*      */ 
/*      */           
/* 1452 */           if (pa >= pb) {
/* 1453 */             diff = curr - left;
/*      */           } else {
/* 1455 */             diff = curr - up;
/*      */           } 
/*      */         } else {
/*      */           
/* 1459 */           int pc = pa + pb;
/* 1460 */           pa = -pa;
/* 1461 */           if (pa <= pb) {
/* 1462 */             if (pa <= pc) {
/* 1463 */               diff = curr - left;
/*      */             } else {
/* 1465 */               diff = curr - upleft;
/*      */             
/*      */             }
/*      */           
/*      */           }
/* 1470 */           else if (pb <= -pc) {
/* 1471 */             diff = curr - up;
/*      */           } else {
/* 1473 */             diff = curr - upleft;
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1478 */       } else if (pb < 0) {
/* 1479 */         pb = -pb;
/* 1480 */         if (pa <= pb) {
/*      */           
/* 1482 */           int pc = pb - pa;
/* 1483 */           if (pa <= pc) {
/* 1484 */             diff = curr - left;
/* 1485 */           } else if (pb == pc) {
/*      */ 
/*      */             
/* 1488 */             diff = curr - up;
/*      */           } else {
/* 1490 */             diff = curr - upleft;
/*      */           } 
/*      */         } else {
/*      */           
/* 1494 */           int pc = pa - pb;
/* 1495 */           if (pb <= pc) {
/* 1496 */             diff = curr - up;
/*      */           } else {
/* 1498 */             diff = curr - upleft;
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1503 */       } else if (pa <= pb) {
/* 1504 */         diff = curr - left;
/*      */       } else {
/* 1506 */         diff = curr - up;
/*      */       } 
/*      */ 
/*      */       
/* 1510 */       scratchRows[4][i] = (byte)diff;
/* 1511 */       badness[4] = badness[4] + ((diff >= 0) ? diff : -diff);
/*      */     } 
/* 1513 */     int filterType = 0;
/* 1514 */     int minBadness = badness[0];
/*      */     
/* 1516 */     for (int j = 1; j < 5; j++) {
/* 1517 */       if (badness[j] < minBadness) {
/* 1518 */         minBadness = badness[j];
/* 1519 */         filterType = j;
/*      */       } 
/*      */     } 
/*      */     
/* 1523 */     if (filterType == 0) {
/* 1524 */       System.arraycopy(currRow, bytesPerPixel, scratchRows[0], bytesPerPixel, bytesPerRow);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1529 */     return filterType;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/PNGEncodeParam.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */