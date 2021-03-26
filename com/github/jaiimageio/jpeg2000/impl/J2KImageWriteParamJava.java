/*      */ package com.github.jaiimageio.jpeg2000.impl;
/*      */ 
/*      */ import c.a.a;
/*      */ import c.a.c.a;
/*      */ import c.a.c.b.h;
/*      */ import c.a.c.c;
/*      */ import c.a.c.e;
/*      */ import c.a.e.a.b;
/*      */ import c.a.g;
/*      */ import c.a.g.a;
/*      */ import c.a.g.b;
/*      */ import c.a.g.c;
/*      */ import c.a.h.a;
/*      */ import c.a.j.a.f;
/*      */ import com.github.jaiimageio.jpeg2000.J2KImageWriteParam;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.util.Locale;
/*      */ import javax.imageio.IIOImage;
/*      */ import javax.imageio.ImageWriteParam;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class J2KImageWriteParamJava
/*      */   extends ImageWriteParam
/*      */ {
/*      */   private boolean packPacketHeaderInTile;
/*      */   private boolean packPacketHeaderInMain;
/*      */   private int packetPerTilePart;
/*      */   private double encodingRate;
/*      */   private boolean lossless;
/*      */   private b componentTransformation;
/*      */   private boolean enableCT;
/*      */   private f filters;
/*      */   private a decompositionLevel;
/*      */   private a guardBits;
/*      */   private b quantizationStep;
/*      */   private c quantizationType;
/*      */   private int startLevelROI;
/*      */   private boolean alignROI;
/*      */   private a ROIs;
/*      */   private a codeBlockSize;
/*      */   private g bypass;
/*      */   private g resetMQ;
/*      */   private g terminateOnByte;
/*      */   private g causalCXInfo;
/*      */   private g codeSegSymbol;
/*      */   private g methodForMQTermination;
/*      */   private g methodForMQLengthCalc;
/*      */   private c precinctPartition;
/*      */   private e progressionType;
/*      */   private String progressionName;
/*      */   private String layers;
/*      */   private g EPH;
/*      */   private g SOP;
/*      */   private int numTiles;
/*      */   private int numComponents;
/*      */   private RenderedImage imgsrc;
/*      */   private Raster raster;
/*      */   private int minX;
/*      */   private int minY;
/*      */   
/*      */   public J2KImageWriteParamJava(RenderedImage imgsrc, Locale locale) {
/*  631 */     super(locale); this.packPacketHeaderInTile = false; this.packPacketHeaderInMain = false; this.packetPerTilePart = 0; this.encodingRate = Double.MAX_VALUE; this.lossless = true; this.componentTransformation = null; this.enableCT = true; this.filters = null; this.decompositionLevel = null; this.guardBits = null; this.quantizationStep = null; this.quantizationType = null; this.startLevelROI = -1; this.alignROI = false; this.ROIs = null; this.codeBlockSize = null; this.bypass = null; this.resetMQ = null; this.terminateOnByte = null; this.causalCXInfo = null; this.codeSegSymbol = null; this.methodForMQTermination = null; this.methodForMQLengthCalc = null; this.precinctPartition = null; this.progressionType = null; this.progressionName = null; this.layers = "0.015 +20 2.0 +10"; this.EPH = null; this.SOP = null;
/*  632 */     setDefaults(imgsrc);
/*      */   }
/*      */ 
/*      */   
/*      */   public J2KImageWriteParamJava(IIOImage image, ImageWriteParam param) {
/*  637 */     super(param.getLocale()); J2KImageWriteParam j2kParam; this.packPacketHeaderInTile = false; this.packPacketHeaderInMain = false; this.packetPerTilePart = 0; this.encodingRate = Double.MAX_VALUE; this.lossless = true; this.componentTransformation = null; this.enableCT = true; this.filters = null; this.decompositionLevel = null; this.guardBits = null; this.quantizationStep = null; this.quantizationType = null; this.startLevelROI = -1; this.alignROI = false; this.ROIs = null; this.codeBlockSize = null; this.bypass = null; this.resetMQ = null; this.terminateOnByte = null; this.causalCXInfo = null; this.codeSegSymbol = null; this.methodForMQTermination = null; this.methodForMQLengthCalc = null; this.precinctPartition = null; this.progressionType = null; this.progressionName = null; this.layers = "0.015 +20 2.0 +10"; this.EPH = null; this.SOP = null;
/*  638 */     if (image != null) {
/*  639 */       if (image.hasRaster()) {
/*  640 */         setDefaults(image.getRaster());
/*      */       } else {
/*  642 */         setDefaults(image.getRenderedImage());
/*      */       } 
/*      */     }
/*  645 */     setSourceRegion(param.getSourceRegion());
/*  646 */     setSourceBands(param.getSourceBands());
/*      */     try {
/*  648 */       setTiling(param.getTileWidth(), param.getTileHeight(), param
/*  649 */           .getTileGridXOffset(), param.getTileGridYOffset());
/*  650 */     } catch (IllegalStateException illegalStateException) {}
/*      */ 
/*      */ 
/*      */     
/*  654 */     setDestinationOffset(param.getDestinationOffset());
/*  655 */     setSourceSubsampling(param.getSourceXSubsampling(), param
/*  656 */         .getSourceYSubsampling(), param
/*  657 */         .getSubsamplingXOffset(), param
/*  658 */         .getSubsamplingYOffset());
/*  659 */     setDestinationType(param.getDestinationType());
/*      */ 
/*      */     
/*  662 */     if (param instanceof J2KImageWriteParam) {
/*  663 */       j2kParam = (J2KImageWriteParam)param;
/*      */     } else {
/*  665 */       j2kParam = new J2KImageWriteParam();
/*      */     } 
/*      */     
/*  668 */     setDecompositionLevel("" + j2kParam.getNumDecompositionLevels());
/*  669 */     setEncodingRate(j2kParam.getEncodingRate());
/*  670 */     setLossless(j2kParam.getLossless());
/*  671 */     setFilters(j2kParam.getFilter());
/*  672 */     setEPH("" + j2kParam.getEPH());
/*  673 */     setSOP("" + j2kParam.getSOP());
/*  674 */     setProgressionName(j2kParam.getProgressionType());
/*  675 */     int[] size = j2kParam.getCodeBlockSize();
/*  676 */     setCodeBlockSize("" + size[0] + " " + size[1]);
/*  677 */     this.enableCT = j2kParam.getComponentTransformation();
/*  678 */     setComponentTransformation("" + this.enableCT); } public J2KImageWriteParamJava() { this.packPacketHeaderInTile = false; this.packPacketHeaderInMain = false; this.packetPerTilePart = 0; this.encodingRate = Double.MAX_VALUE; this.lossless = true; this.componentTransformation = null; this.enableCT = true; this.filters = null; this.decompositionLevel = null; this.guardBits = null; this.quantizationStep = null; this.quantizationType = null; this.startLevelROI = -1; this.alignROI = false; this.ROIs = null; this.codeBlockSize = null; this.bypass = null; this.resetMQ = null; this.terminateOnByte = null; this.causalCXInfo = null;
/*      */     this.codeSegSymbol = null;
/*      */     this.methodForMQTermination = null;
/*      */     this.methodForMQLengthCalc = null;
/*      */     this.precinctPartition = null;
/*      */     this.progressionType = null;
/*      */     this.progressionName = null;
/*      */     this.layers = "0.015 +20 2.0 +10";
/*      */     this.EPH = null;
/*      */     this.SOP = null;
/*  688 */     setSuperProperties(); } public J2KImageWriteParamJava(RenderedImage imgsrc) { this.packPacketHeaderInTile = false; this.packPacketHeaderInMain = false; this.packetPerTilePart = 0; this.encodingRate = Double.MAX_VALUE; this.lossless = true; this.componentTransformation = null; this.enableCT = true; this.filters = null; this.decompositionLevel = null; this.guardBits = null; this.quantizationStep = null; this.quantizationType = null; this.startLevelROI = -1; this.alignROI = false; this.ROIs = null; this.codeBlockSize = null; this.bypass = null; this.resetMQ = null; this.terminateOnByte = null; this.causalCXInfo = null; this.codeSegSymbol = null;
/*      */     this.methodForMQTermination = null;
/*      */     this.methodForMQLengthCalc = null;
/*      */     this.precinctPartition = null;
/*      */     this.progressionType = null;
/*      */     this.progressionName = null;
/*      */     this.layers = "0.015 +20 2.0 +10";
/*      */     this.EPH = null;
/*      */     this.SOP = null;
/*  697 */     setDefaults(imgsrc); } public J2KImageWriteParamJava(Raster raster) { this.packPacketHeaderInTile = false; this.packPacketHeaderInMain = false; this.packetPerTilePart = 0; this.encodingRate = Double.MAX_VALUE; this.lossless = true; this.componentTransformation = null; this.enableCT = true; this.filters = null; this.decompositionLevel = null; this.guardBits = null; this.quantizationStep = null; this.quantizationType = null; this.startLevelROI = -1; this.alignROI = false; this.ROIs = null; this.codeBlockSize = null; this.bypass = null; this.resetMQ = null; this.terminateOnByte = null; this.causalCXInfo = null; this.codeSegSymbol = null;
/*      */     this.methodForMQTermination = null;
/*      */     this.methodForMQLengthCalc = null;
/*      */     this.precinctPartition = null;
/*      */     this.progressionType = null;
/*      */     this.progressionName = null;
/*      */     this.layers = "0.015 +20 2.0 +10";
/*      */     this.EPH = null;
/*      */     this.SOP = null;
/*  706 */     setDefaults(raster); }
/*      */ 
/*      */   
/*      */   private void setSuperProperties() {
/*  710 */     this.canOffsetTiles = true;
/*  711 */     this.canWriteTiles = true;
/*  712 */     this.canOffsetTiles = true;
/*  713 */     this.canWriteProgressive = true;
/*  714 */     this.tilingMode = 2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setDefaults(Raster raster) {
/*  720 */     setSuperProperties();
/*      */     
/*  722 */     if (raster != null) {
/*  723 */       this.raster = raster;
/*  724 */       this.tileGridXOffset = raster.getMinX();
/*  725 */       this.tileGridYOffset = raster.getMinY();
/*  726 */       this.tileWidth = raster.getWidth();
/*  727 */       this.tileHeight = raster.getHeight();
/*  728 */       this.tilingSet = true;
/*      */       
/*  730 */       this.numTiles = 1;
/*  731 */       this.numComponents = raster.getSampleModel().getNumBands();
/*      */     } 
/*  733 */     setDefaults();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setDefaults(RenderedImage imgsrc) {
/*  739 */     setSuperProperties();
/*      */     
/*  741 */     this.tilingMode = 2;
/*      */     
/*  743 */     if (imgsrc != null) {
/*  744 */       this.imgsrc = imgsrc;
/*  745 */       this.tileGridXOffset = imgsrc.getTileGridXOffset();
/*  746 */       this.tileGridYOffset = imgsrc.getTileGridYOffset();
/*  747 */       this.tileWidth = imgsrc.getTileWidth();
/*  748 */       this.tileHeight = imgsrc.getTileHeight();
/*  749 */       this.tilingSet = true;
/*      */       
/*  751 */       this.numTiles = imgsrc.getNumXTiles() * imgsrc.getNumYTiles();
/*  752 */       this.numComponents = imgsrc.getSampleModel().getNumBands();
/*      */     } 
/*  754 */     setDefaults();
/*      */   }
/*      */   
/*      */   private void setDefaults() {
/*  758 */     setROIs((String)null);
/*  759 */     setQuantizationType((String)null);
/*  760 */     setQuantizationStep((String)null);
/*  761 */     setGuardBits((String)null);
/*  762 */     setFilters((String)null);
/*  763 */     setDecompositionLevel((String)null);
/*  764 */     setComponentTransformation((String)null);
/*  765 */     setMethodForMQLengthCalc((String)null);
/*  766 */     setMethodForMQTermination((String)null);
/*  767 */     setCodeSegSymbol((String)null);
/*  768 */     setCausalCXInfo((String)null);
/*  769 */     setTerminateOnByte((String)null);
/*  770 */     setResetMQ((String)null);
/*  771 */     setBypass((String)null);
/*  772 */     setCodeBlockSize((String)null);
/*  773 */     setPrecinctPartition((String)null);
/*  774 */     setSOP((String)null);
/*  775 */     setEPH((String)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEncodingRate(double rate) {
/*  780 */     this.encodingRate = rate;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getEncodingRate() {
/*  785 */     return this.encodingRate;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLossless(boolean lossless) {
/*  790 */     this.lossless = lossless;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getLossless() {
/*  795 */     return this.lossless;
/*      */   }
/*      */   
/*      */   public void setPacketPerTilePart(int packetPerTilePart) {
/*  799 */     if (packetPerTilePart < 0) {
/*  800 */       throw new IllegalArgumentException(I18N.getString("J2KImageWriteParamJava0"));
/*      */     }
/*  802 */     this.packetPerTilePart = packetPerTilePart;
/*  803 */     if (packetPerTilePart > 0) {
/*  804 */       setSOP("true");
/*  805 */       setEPH("true");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPacketPerTilePart() {
/*  811 */     return this.packetPerTilePart;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPackPacketHeaderInTile(boolean packPacketHeaderInTile) {
/*  816 */     this.packPacketHeaderInTile = packPacketHeaderInTile;
/*  817 */     if (packPacketHeaderInTile) {
/*  818 */       setSOP("true");
/*  819 */       setEPH("true");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getPackPacketHeaderInTile() {
/*  825 */     return this.packPacketHeaderInTile;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPackPacketHeaderInMain(boolean packPacketHeaderInMain) {
/*  830 */     this.packPacketHeaderInMain = packPacketHeaderInMain;
/*  831 */     if (packPacketHeaderInMain) {
/*  832 */       setSOP("true");
/*  833 */       setEPH("true");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getPackPacketHeaderInMain() {
/*  839 */     return this.packPacketHeaderInMain;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAlignROI(boolean align) {
/*  844 */     this.alignROI = align;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlignROI() {
/*  849 */     return this.alignROI;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setROIs(String values) {
/*  854 */     this.ROIs = new a(this.numTiles, this.numComponents, (byte)2, values);
/*      */   }
/*      */ 
/*      */   
/*      */   public a getROIs() {
/*  859 */     return this.ROIs;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setQuantizationType(String values) {
/*  864 */     this.quantizationType = new c(this.numTiles, this.numComponents, (byte)2, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public c getQuantizationType() {
/*  870 */     return this.quantizationType;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setQuantizationStep(String values) {
/*  875 */     this.quantizationStep = new b(this.numTiles, this.numComponents, (byte)2, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public b getQuantizationStep() {
/*  884 */     return this.quantizationStep;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGuardBits(String values) {
/*  889 */     this.guardBits = new a(this.numTiles, this.numComponents, (byte)2, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public a getGuardBits() {
/*  898 */     return this.guardBits;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFilters(String values) {
/*  904 */     if ("w9x7".equals(values)) {
/*  905 */       setQuantizationType("expounded");
/*      */     } else {
/*  907 */       setQuantizationType("reversible");
/*      */     } 
/*  909 */     this.filters = new f(this.numTiles, this.numComponents, (byte)2, this.quantizationType, this, values);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  915 */     setComponentTransformation("" + this.enableCT);
/*      */   }
/*      */ 
/*      */   
/*      */   public f getFilters() {
/*  920 */     return this.filters;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDecompositionLevel(String values) {
/*  925 */     this.decompositionLevel = new a(this.numTiles, this.numComponents, (byte)2, this, values, "5");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  935 */     setPrecinctPartition((String)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public a getDecompositionLevel() {
/*  940 */     return this.decompositionLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setComponentTransformation(String values) {
/*  946 */     this.componentTransformation = new b(this.numTiles, this.numComponents, (byte)1, this.filters, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public b getComponentTransformation() {
/*  957 */     return this.componentTransformation;
/*      */   }
/*      */   
/*      */   public void setMethodForMQLengthCalc(String values) {
/*  961 */     String[] strLcs = { "near_opt", "lazy_good", "lazy" };
/*  962 */     this.methodForMQLengthCalc = new g(this.numTiles, this.numComponents, (byte)2, "near_opt", strLcs, this, values);
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
/*      */   public g getMethodForMQLengthCalc() {
/*  974 */     return this.methodForMQLengthCalc;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMethodForMQTermination(String values) {
/*  979 */     String[] strTerm = { "near_opt", "easy", "predict", "full" };
/*  980 */     this.methodForMQTermination = new g(this.numTiles, this.numComponents, (byte)2, "near_opt", strTerm, this, values);
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
/*      */   public g getMethodForMQTermination() {
/*  992 */     return this.methodForMQTermination;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCodeSegSymbol(String values) {
/*  997 */     String[] strBoolean = { "true", "false" };
/*  998 */     this.codeSegSymbol = new g(this.numTiles, this.numComponents, (byte)2, "false", strBoolean, this, values);
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
/*      */   public g getCodeSegSymbol() {
/* 1010 */     return this.codeSegSymbol;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCausalCXInfo(String values) {
/* 1015 */     String[] strBoolean = { "true", "false" };
/* 1016 */     this.causalCXInfo = new g(this.numTiles, this.numComponents, (byte)2, "false", strBoolean, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public g getCausalCXInfo() {
/* 1027 */     return this.causalCXInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTerminateOnByte(String values) {
/* 1032 */     String[] strBoolean = { "true", "false" };
/* 1033 */     this.terminateOnByte = new g(this.numTiles, this.numComponents, (byte)2, "false", strBoolean, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public g getTerminateOnByte() {
/* 1044 */     return this.terminateOnByte;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setResetMQ(String values) {
/* 1049 */     String[] strBoolean = { "true", "false" };
/* 1050 */     this.resetMQ = new g(this.numTiles, this.numComponents, (byte)2, "false", strBoolean, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public g getResetMQ() {
/* 1061 */     return this.resetMQ;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBypass(String values) {
/* 1066 */     String[] strBoolean = { "true", "false" };
/* 1067 */     this.bypass = new g(this.numTiles, this.numComponents, (byte)2, "false", strBoolean, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public g getBypass() {
/* 1078 */     return this.bypass;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCodeBlockSize(String values) {
/* 1083 */     this.codeBlockSize = new a(this.numTiles, this.numComponents, (byte)2, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public a getCodeBlockSize() {
/* 1092 */     return this.codeBlockSize;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPrecinctPartition(String values) {
/* 1097 */     String[] strBoolean = { "true", "false" };
/* 1098 */     if (this.imgsrc != null) {
/* 1099 */       this.precinctPartition = new c(this.numTiles, this.numComponents, (byte)2, new RenderedImageSrc(this.imgsrc, this, null), this.decompositionLevel, this, values);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1107 */     else if (this.raster != null) {
/* 1108 */       this.precinctPartition = new c(this.numTiles, this.numComponents, (byte)2, new RenderedImageSrc(this.raster, this, null), this.decompositionLevel, this, values);
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
/*      */   public c getPrecinctPartition() {
/* 1120 */     return this.precinctPartition;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSOP(String values) {
/* 1125 */     String[] strBoolean = { "true", "false" };
/* 1126 */     this.SOP = new g(this.numTiles, this.numComponents, (byte)2, "false", strBoolean, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public g getSOP() {
/* 1137 */     return this.SOP;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEPH(String values) {
/* 1142 */     String[] strBoolean = { "true", "false" };
/* 1143 */     this.EPH = new g(this.numTiles, this.numComponents, (byte)2, "false", strBoolean, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public g getEPH() {
/* 1154 */     return this.EPH;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProgressionName(String values) {
/* 1159 */     this.progressionName = values;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getProgressionName() {
/* 1164 */     return this.progressionName;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProgressionType(h lyrs, String values) {
/* 1169 */     String[] strBoolean = { "true", "false" };
/* 1170 */     this
/*      */       
/* 1172 */       .progressionType = new e(this.numTiles, this.numComponents, lyrs.b(), this.decompositionLevel, (byte)2, this, values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public e getProgressionType() {
/* 1181 */     return this.progressionType;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStartLevelROI(int value) {
/* 1186 */     this.startLevelROI = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getStartLevelROI() {
/* 1191 */     return this.startLevelROI;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLayers(String value) {
/* 1196 */     this.layers = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLayers() {
/* 1201 */     return this.layers;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMinX(int minX) {
/* 1206 */     this.minX = minX;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinX() {
/* 1211 */     return this.minX;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMinY(int minY) {
/* 1216 */     this.minY = minY;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinY() {
/* 1221 */     return this.minY;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNumTiles() {
/* 1226 */     Rectangle sourceRegion = getSourceRegion();
/* 1227 */     if (sourceRegion == null)
/* 1228 */     { if (this.imgsrc != null)
/*      */       
/*      */       { 
/*      */         
/* 1232 */         sourceRegion = new Rectangle(this.imgsrc.getMinX(), this.imgsrc.getMinY(), this.imgsrc.getWidth(), this.imgsrc.getHeight()); }
/* 1233 */       else { sourceRegion = this.raster.getBounds(); }
/*      */        }
/* 1235 */     else if (this.imgsrc != null)
/*      */     
/* 1237 */     { sourceRegion = sourceRegion.intersection(new Rectangle(this.imgsrc.getMinX(), this.imgsrc
/* 1238 */             .getMinY(), this.imgsrc
/* 1239 */             .getWidth(), this.imgsrc
/* 1240 */             .getHeight())); }
/* 1241 */     else { sourceRegion = sourceRegion.intersection(this.raster.getBounds()); }
/*      */ 
/*      */     
/* 1244 */     int scaleX = getSourceXSubsampling();
/* 1245 */     int scaleY = getSourceYSubsampling();
/* 1246 */     int xOffset = getSubsamplingXOffset();
/* 1247 */     int yOffset = getSubsamplingYOffset();
/*      */     
/* 1249 */     int w = (sourceRegion.width - xOffset + scaleX - 1) / scaleX;
/* 1250 */     int h = (sourceRegion.height - yOffset + scaleY - 1) / scaleY;
/*      */     
/* 1252 */     this.minX = (sourceRegion.x + xOffset) / scaleX;
/* 1253 */     this.minY = (sourceRegion.y + yOffset) / scaleY;
/*      */     
/* 1255 */     this
/*      */ 
/*      */       
/* 1258 */       .numTiles = (int)((Math.floor(((this.minX + w + this.tileWidth) - 1.0D) / this.tileWidth) - Math.floor(this.minX / this.tileWidth)) * (Math.floor(((this.minY + h + this.tileHeight) - 1.0D) / this.tileHeight) - Math.floor(this.minY / this.tileHeight)));
/* 1259 */     this.tileGridXOffset += (this.minX - this.tileGridXOffset) / this.tileWidth * this.tileWidth;
/* 1260 */     this.tileGridYOffset += (this.minY - this.tileGridYOffset) / this.tileHeight * this.tileHeight;
/*      */     
/* 1262 */     return this.numTiles;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNumComponents() {
/* 1267 */     return this.numComponents;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSourceBands(int[] bands) {
/* 1275 */     super.setSourceBands(bands);
/* 1276 */     if (bands != null) {
/* 1277 */       this.numComponents = bands.length;
/* 1278 */       setDefaults();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTiling(int tw, int th, int xOff, int yOff) {
/* 1287 */     super.setTiling(tw, th, xOff, yOff);
/* 1288 */     getNumTiles();
/* 1289 */     setDefaults();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSourceSubsampling(int sx, int sy, int xOff, int yOff) {
/* 1297 */     super.setSourceSubsampling(sx, sy, xOff, yOff);
/* 1298 */     getNumTiles();
/* 1299 */     setDefaults();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KImageWriteParamJava.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */