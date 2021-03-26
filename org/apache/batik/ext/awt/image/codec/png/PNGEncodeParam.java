/*      */ package org.apache.batik.ext.awt.image.codec.png;
/*      */ 
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import org.apache.batik.ext.awt.image.codec.util.ImageEncodeParam;
/*      */ import org.apache.batik.ext.awt.image.codec.util.PropertyUtil;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   
/*      */   public static PNGEncodeParam getDefaultEncodeParam(RenderedImage im) {
/*   90 */     ColorModel colorModel = im.getColorModel();
/*   91 */     if (colorModel instanceof java.awt.image.IndexColorModel) {
/*   92 */       return new Palette();
/*      */     }
/*      */     
/*   95 */     SampleModel sampleModel = im.getSampleModel();
/*   96 */     int numBands = sampleModel.getNumBands();
/*      */     
/*   98 */     if (numBands == 1 || numBands == 2) {
/*   99 */       return new Gray();
/*      */     }
/*  101 */     return new RGB();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class Palette
/*      */     extends PNGEncodeParam
/*      */   {
/*      */     private boolean backgroundSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void unsetBackground() {
/*  118 */       this.backgroundSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBackgroundSet() {
/*  125 */       return this.backgroundSet;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setBitDepth(int bitDepth) {
/*  134 */       if (bitDepth != 1 && bitDepth != 2 && bitDepth != 4 && bitDepth != 8)
/*      */       {
/*  136 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam2"));
/*      */       }
/*  138 */       this.bitDepth = bitDepth;
/*  139 */       this.bitDepthSet = true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  144 */     private int[] palette = null;
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean paletteSet = false;
/*      */ 
/*      */     
/*      */     private int backgroundPaletteIndex;
/*      */ 
/*      */     
/*      */     private int[] transparency;
/*      */ 
/*      */ 
/*      */     
/*      */     public void setPalette(int[] rgb) {
/*  159 */       if (rgb.length < 3 || rgb.length > 768) {
/*  160 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam0"));
/*      */       }
/*      */       
/*  163 */       if (rgb.length % 3 != 0) {
/*  164 */         throw new IllegalArgumentException(PropertyUtil.getString("PNGEncodeParam1"));
/*      */       }
/*      */ 
/*      */       
/*  168 */       this.palette = (int[])rgb.clone();
/*  169 */       this.paletteSet = true;
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
/*  183 */       if (!this.paletteSet) {
/*  184 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam3"));
/*      */       }
/*  186 */       return (int[])this.palette.clone();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void unsetPalette() {
/*  193 */       this.palette = null;
/*  194 */       this.paletteSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isPaletteSet() {
/*  201 */       return this.paletteSet;
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
/*  214 */       this.backgroundPaletteIndex = index;
/*  215 */       this.backgroundSet = true;
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
/*  228 */       if (!this.backgroundSet) {
/*  229 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam4"));
/*      */       }
/*  231 */       return this.backgroundPaletteIndex;
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
/*  246 */       this.transparency = new int[alpha.length];
/*  247 */       for (int i = 0; i < alpha.length; i++) {
/*  248 */         this.transparency[i] = alpha[i] & 0xFF;
/*      */       }
/*  250 */       this.transparencySet = true;
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
/*  264 */       if (!this.transparencySet) {
/*  265 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam5"));
/*      */       }
/*  267 */       byte[] alpha = new byte[this.transparency.length];
/*  268 */       for (int i = 0; i < alpha.length; i++) {
/*  269 */         alpha[i] = (byte)this.transparency[i];
/*      */       }
/*  271 */       return alpha;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class Gray
/*      */     extends PNGEncodeParam
/*      */   {
/*      */     private boolean backgroundSet = false;
/*      */     
/*      */     private int backgroundPaletteGray;
/*      */     
/*      */     private int[] transparency;
/*      */     
/*      */     private int bitShift;
/*      */     
/*      */     public void unsetBackground() {
/*  288 */       this.backgroundSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBackgroundSet() {
/*  295 */       return this.backgroundSet;
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
/*  309 */       if (bitDepth != 1 && bitDepth != 2 && bitDepth != 4 && bitDepth != 8 && bitDepth != 16)
/*      */       {
/*  311 */         throw new IllegalArgumentException();
/*      */       }
/*  313 */       this.bitDepth = bitDepth;
/*  314 */       this.bitDepthSet = true;
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
/*  327 */       this.backgroundPaletteGray = gray;
/*  328 */       this.backgroundSet = true;
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
/*  342 */       if (!this.backgroundSet) {
/*  343 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam6"));
/*      */       }
/*  345 */       return this.backgroundPaletteGray;
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
/*  361 */       this.transparency = new int[1];
/*  362 */       this.transparency[0] = transparentGray;
/*  363 */       this.transparencySet = true;
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
/*  377 */       if (!this.transparencySet) {
/*  378 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam7"));
/*      */       }
/*  380 */       int gray = this.transparency[0];
/*  381 */       return gray;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean bitShiftSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setBitShift(int bitShift) {
/*  394 */       if (bitShift < 0) {
/*  395 */         throw new RuntimeException();
/*      */       }
/*  397 */       this.bitShift = bitShift;
/*  398 */       this.bitShiftSet = true;
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
/*  410 */       if (!this.bitShiftSet) {
/*  411 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam8"));
/*      */       }
/*  413 */       return this.bitShift;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void unsetBitShift() {
/*  421 */       this.bitShiftSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBitShiftSet() {
/*  428 */       return this.bitShiftSet;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBitDepthSet() {
/*  435 */       return this.bitDepthSet;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class RGB
/*      */     extends PNGEncodeParam
/*      */   {
/*      */     private boolean backgroundSet = false;
/*      */     
/*      */     private int[] backgroundRGB;
/*      */     
/*      */     private int[] transparency;
/*      */ 
/*      */     
/*      */     public void unsetBackground() {
/*  452 */       this.backgroundSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isBackgroundSet() {
/*  459 */       return this.backgroundSet;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setBitDepth(int bitDepth) {
/*  467 */       if (bitDepth != 8 && bitDepth != 16) {
/*  468 */         throw new RuntimeException();
/*      */       }
/*  470 */       this.bitDepth = bitDepth;
/*  471 */       this.bitDepthSet = true;
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
/*  485 */       if (rgb.length != 3) {
/*  486 */         throw new RuntimeException();
/*      */       }
/*  488 */       this.backgroundRGB = rgb;
/*  489 */       this.backgroundSet = true;
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
/*  501 */       if (!this.backgroundSet) {
/*  502 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam9"));
/*      */       }
/*  504 */       return this.backgroundRGB;
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
/*  520 */       this.transparency = (int[])transparentRGB.clone();
/*  521 */       this.transparencySet = true;
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
/*  534 */       if (!this.transparencySet) {
/*  535 */         throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam10"));
/*      */       }
/*  537 */       return (int[])this.transparency.clone();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean bitDepthSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void setBitDepth(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBitDepth() {
/*  558 */     if (!this.bitDepthSet) {
/*  559 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam11"));
/*      */     }
/*  561 */     return this.bitDepth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetBitDepth() {
/*  571 */     this.bitDepthSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean useInterlacing = false;
/*      */ 
/*      */   
/*      */   public void setInterlacing(boolean useInterlacing) {
/*  580 */     this.useInterlacing = useInterlacing;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getInterlacing() {
/*  587 */     return this.useInterlacing;
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
/*  610 */     throw new RuntimeException(PropertyUtil.getString("PNGEncodeParam23"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBackgroundSet() {
/*  620 */     throw new RuntimeException(PropertyUtil.getString("PNGEncodeParam24"));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  625 */   private float[] chromaticity = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean chromaticitySet = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private float gamma;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChromaticity(float[] chromaticity) {
/*  640 */     if (chromaticity.length != 8) {
/*  641 */       throw new IllegalArgumentException();
/*      */     }
/*  643 */     this.chromaticity = (float[])chromaticity.clone();
/*  644 */     this.chromaticitySet = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChromaticity(float whitePointX, float whitePointY, float redX, float redY, float greenX, float greenY, float blueX, float blueY) {
/*  654 */     float[] chroma = new float[8];
/*  655 */     chroma[0] = whitePointX;
/*  656 */     chroma[1] = whitePointY;
/*  657 */     chroma[2] = redX;
/*  658 */     chroma[3] = redY;
/*  659 */     chroma[4] = greenX;
/*  660 */     chroma[5] = greenY;
/*  661 */     chroma[6] = blueX;
/*  662 */     chroma[7] = blueY;
/*  663 */     setChromaticity(chroma);
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
/*  679 */     if (!this.chromaticitySet) {
/*  680 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam12"));
/*      */     }
/*  682 */     return (float[])this.chromaticity.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetChromaticity() {
/*  689 */     this.chromaticity = null;
/*  690 */     this.chromaticitySet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChromaticitySet() {
/*  697 */     return this.chromaticitySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean gammaSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGamma(float gamma) {
/*  711 */     this.gamma = gamma;
/*  712 */     this.gammaSet = true;
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
/*  724 */     if (!this.gammaSet) {
/*  725 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam13"));
/*      */     }
/*  727 */     return this.gamma;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetGamma() {
/*  734 */     this.gammaSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isGammaSet() {
/*  741 */     return this.gammaSet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  746 */   private int[] paletteHistogram = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean paletteHistogramSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPaletteHistogram(int[] paletteHistogram) {
/*  757 */     this.paletteHistogram = (int[])paletteHistogram.clone();
/*  758 */     this.paletteHistogramSet = true;
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
/*  770 */     if (!this.paletteHistogramSet) {
/*  771 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam14"));
/*      */     }
/*  773 */     return this.paletteHistogram;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetPaletteHistogram() {
/*  780 */     this.paletteHistogram = null;
/*  781 */     this.paletteHistogramSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPaletteHistogramSet() {
/*  788 */     return this.paletteHistogramSet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  793 */   private byte[] ICCProfileData = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean ICCProfileDataSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setICCProfileData(byte[] ICCProfileData) {
/*  803 */     this.ICCProfileData = (byte[])ICCProfileData.clone();
/*  804 */     this.ICCProfileDataSet = true;
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
/*  816 */     if (!this.ICCProfileDataSet) {
/*  817 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam15"));
/*      */     }
/*  819 */     return (byte[])this.ICCProfileData.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetICCProfileData() {
/*  826 */     this.ICCProfileData = null;
/*  827 */     this.ICCProfileDataSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isICCProfileDataSet() {
/*  834 */     return this.ICCProfileDataSet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  839 */   private int[] physicalDimension = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean physicalDimensionSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPhysicalDimension(int[] physicalDimension) {
/*  852 */     this.physicalDimension = (int[])physicalDimension.clone();
/*  853 */     this.physicalDimensionSet = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPhysicalDimension(int xPixelsPerUnit, int yPixelsPerUnit, int unitSpecifier) {
/*  862 */     int[] pd = new int[3];
/*  863 */     pd[0] = xPixelsPerUnit;
/*  864 */     pd[1] = yPixelsPerUnit;
/*  865 */     pd[2] = unitSpecifier;
/*      */     
/*  867 */     setPhysicalDimension(pd);
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
/*  882 */     if (!this.physicalDimensionSet) {
/*  883 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam16"));
/*      */     }
/*  885 */     return (int[])this.physicalDimension.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetPhysicalDimension() {
/*  892 */     this.physicalDimension = null;
/*  893 */     this.physicalDimensionSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPhysicalDimensionSet() {
/*  900 */     return this.physicalDimensionSet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  905 */   private PNGSuggestedPaletteEntry[] suggestedPalette = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean suggestedPaletteSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSuggestedPalette(PNGSuggestedPaletteEntry[] palette) {
/*  916 */     this.suggestedPalette = (PNGSuggestedPaletteEntry[])palette.clone();
/*  917 */     this.suggestedPaletteSet = true;
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
/*  932 */     if (!this.suggestedPaletteSet) {
/*  933 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam17"));
/*      */     }
/*  935 */     return (PNGSuggestedPaletteEntry[])this.suggestedPalette.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetSuggestedPalette() {
/*  942 */     this.suggestedPalette = null;
/*  943 */     this.suggestedPaletteSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSuggestedPaletteSet() {
/*  950 */     return this.suggestedPaletteSet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  955 */   private int[] significantBits = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean significantBitsSet = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private int SRGBIntent;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSignificantBits(int[] significantBits) {
/*  969 */     this.significantBits = (int[])significantBits.clone();
/*  970 */     this.significantBitsSet = true;
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
/*  984 */     if (!this.significantBitsSet) {
/*  985 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam18"));
/*      */     }
/*  987 */     return (int[])this.significantBits.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetSignificantBits() {
/*  994 */     this.significantBits = null;
/*  995 */     this.significantBitsSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSignificantBitsSet() {
/* 1002 */     return this.significantBitsSet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean SRGBIntentSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSRGBIntent(int SRGBIntent) {
/* 1019 */     this.SRGBIntent = SRGBIntent;
/* 1020 */     this.SRGBIntentSet = true;
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
/* 1032 */     if (!this.SRGBIntentSet) {
/* 1033 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam19"));
/*      */     }
/* 1035 */     return this.SRGBIntent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetSRGBIntent() {
/* 1042 */     this.SRGBIntentSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSRGBIntentSet() {
/* 1049 */     return this.SRGBIntentSet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1054 */   private String[] text = null;
/*      */ 
/*      */   
/*      */   private boolean textSet = false;
/*      */ 
/*      */   
/*      */   private Date modificationTime;
/*      */ 
/*      */ 
/*      */   
/*      */   public void setText(String[] text) {
/* 1065 */     this.text = text;
/* 1066 */     this.textSet = true;
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
/* 1079 */     if (!this.textSet) {
/* 1080 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam20"));
/*      */     }
/* 1082 */     return this.text;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetText() {
/* 1089 */     this.text = null;
/* 1090 */     this.textSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTextSet() {
/* 1097 */     return this.textSet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean modificationTimeSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setModificationTime(Date modificationTime) {
/* 1114 */     this.modificationTime = modificationTime;
/* 1115 */     this.modificationTimeSet = true;
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
/* 1127 */     if (!this.modificationTimeSet) {
/* 1128 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam21"));
/*      */     }
/* 1130 */     return this.modificationTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetModificationTime() {
/* 1137 */     this.modificationTime = null;
/* 1138 */     this.modificationTimeSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isModificationTimeSet() {
/* 1145 */     return this.modificationTimeSet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean transparencySet = false;
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetTransparency() {
/* 1156 */     this.transparencySet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTransparencySet() {
/* 1163 */     return this.transparencySet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1168 */   private String[] zText = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean zTextSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCompressedText(String[] text) {
/* 1179 */     this.zText = text;
/* 1180 */     this.zTextSet = true;
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
/* 1195 */     if (!this.zTextSet) {
/* 1196 */       throw new IllegalStateException(PropertyUtil.getString("PNGEncodeParam22"));
/*      */     }
/* 1198 */     return this.zText;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetCompressedText() {
/* 1205 */     this.zText = null;
/* 1206 */     this.zTextSet = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCompressedTextSet() {
/* 1213 */     return this.zTextSet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1218 */   List chunkType = new ArrayList();
/* 1219 */   List chunkData = new ArrayList();
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
/* 1230 */     this.chunkType.add(type);
/* 1231 */     this.chunkData.add(data.clone());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized int getNumPrivateChunks() {
/* 1239 */     return this.chunkType.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized String getPrivateChunkType(int index) {
/* 1248 */     return this.chunkType.get(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized byte[] getPrivateChunkData(int index) {
/* 1258 */     return this.chunkData.get(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void removeUnsafeToCopyPrivateChunks() {
/* 1267 */     List<String> newChunkType = new ArrayList();
/* 1268 */     List<byte[]> newChunkData = new ArrayList();
/*      */     
/* 1270 */     int len = getNumPrivateChunks();
/* 1271 */     for (int i = 0; i < len; i++) {
/* 1272 */       String type = getPrivateChunkType(i);
/* 1273 */       char lastChar = type.charAt(3);
/* 1274 */       if (lastChar >= 'a' && lastChar <= 'z') {
/* 1275 */         newChunkType.add(type);
/* 1276 */         newChunkData.add(getPrivateChunkData(i));
/*      */       } 
/*      */     } 
/*      */     
/* 1280 */     this.chunkType = newChunkType;
/* 1281 */     this.chunkData = newChunkData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void removeAllPrivateChunks() {
/* 1288 */     this.chunkType = new ArrayList();
/* 1289 */     this.chunkData = new ArrayList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int abs(int x) {
/* 1296 */     return (x < 0) ? -x : x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int paethPredictor(int a, int b, int c) {
/* 1305 */     int p = a + b - c;
/* 1306 */     int pa = abs(p - a);
/* 1307 */     int pb = abs(p - b);
/* 1308 */     int pc = abs(p - c);
/*      */     
/* 1310 */     if (pa <= pb && pa <= pc)
/* 1311 */       return a; 
/* 1312 */     if (pb <= pc) {
/* 1313 */       return b;
/*      */     }
/* 1315 */     return c;
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
/* 1382 */     int[] badness = { 0, 0, 0, 0, 0 };
/*      */ 
/*      */     
/* 1385 */     for (int i = bytesPerPixel; i < bytesPerRow + bytesPerPixel; i++) {
/* 1386 */       int curr = currRow[i] & 0xFF;
/* 1387 */       int left = currRow[i - bytesPerPixel] & 0xFF;
/* 1388 */       int up = prevRow[i] & 0xFF;
/* 1389 */       int upleft = prevRow[i - bytesPerPixel] & 0xFF;
/*      */ 
/*      */       
/* 1392 */       badness[0] = badness[0] + curr;
/*      */ 
/*      */       
/* 1395 */       int diff = curr - left;
/* 1396 */       scratchRows[1][i] = (byte)diff;
/* 1397 */       badness[1] = badness[1] + ((diff > 0) ? diff : -diff);
/*      */ 
/*      */       
/* 1400 */       diff = curr - up;
/* 1401 */       scratchRows[2][i] = (byte)diff;
/* 1402 */       badness[2] = badness[2] + ((diff >= 0) ? diff : -diff);
/*      */ 
/*      */       
/* 1405 */       diff = curr - (left + up >> 1);
/* 1406 */       scratchRows[3][i] = (byte)diff;
/* 1407 */       badness[3] = badness[3] + ((diff >= 0) ? diff : -diff);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1427 */       int pa = up - upleft;
/* 1428 */       int pb = left - upleft;
/* 1429 */       if (pa < 0) {
/* 1430 */         if (pb < 0) {
/*      */ 
/*      */           
/* 1433 */           if (pa >= pb) {
/* 1434 */             diff = curr - left;
/*      */           } else {
/* 1436 */             diff = curr - up;
/*      */           } 
/*      */         } else {
/* 1439 */           int pc = pa + pb;
/* 1440 */           pa = -pa;
/* 1441 */           if (pa <= pb) {
/* 1442 */             if (pa <= pc) {
/* 1443 */               diff = curr - left;
/*      */             } else {
/* 1445 */               diff = curr - upleft;
/*      */             }
/*      */           
/*      */           }
/* 1449 */           else if (pb <= -pc) {
/* 1450 */             diff = curr - up;
/*      */           } else {
/* 1452 */             diff = curr - upleft;
/*      */           } 
/*      */         } 
/* 1455 */       } else if (pb < 0) {
/* 1456 */         pb = -pb;
/* 1457 */         if (pa <= pb) {
/*      */           
/* 1459 */           int pc = pb - pa;
/* 1460 */           if (pa <= pc) {
/* 1461 */             diff = curr - left;
/* 1462 */           } else if (pb == pc) {
/*      */ 
/*      */             
/* 1465 */             diff = curr - up;
/*      */           } else {
/* 1467 */             diff = curr - upleft;
/*      */           } 
/*      */         } else {
/* 1470 */           int pc = pa - pb;
/* 1471 */           if (pb <= pc) {
/* 1472 */             diff = curr - up;
/*      */           } else {
/* 1474 */             diff = curr - upleft;
/*      */           }
/*      */         
/*      */         } 
/* 1478 */       } else if (pa <= pb) {
/* 1479 */         diff = curr - left;
/*      */       } else {
/* 1481 */         diff = curr - up;
/*      */       } 
/*      */       
/* 1484 */       scratchRows[4][i] = (byte)diff;
/* 1485 */       badness[4] = badness[4] + ((diff >= 0) ? diff : -diff);
/*      */     } 
/* 1487 */     int filterType = 0;
/* 1488 */     int minBadness = badness[0];
/*      */     
/* 1490 */     for (int j = 1; j < 5; j++) {
/* 1491 */       if (badness[j] < minBadness) {
/* 1492 */         minBadness = badness[j];
/* 1493 */         filterType = j;
/*      */       } 
/*      */     } 
/*      */     
/* 1497 */     if (filterType == 0) {
/* 1498 */       System.arraycopy(currRow, bytesPerPixel, scratchRows[0], bytesPerPixel, bytesPerRow);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1503 */     return filterType;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGEncodeParam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */