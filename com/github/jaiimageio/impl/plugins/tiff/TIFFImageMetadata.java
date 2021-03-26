/*      */ package com.github.jaiimageio.impl.plugins.tiff;
/*      */ 
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFTag;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
/*      */ import java.io.IOException;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
/*      */ import javax.imageio.metadata.IIOInvalidTreeException;
/*      */ import javax.imageio.metadata.IIOMetadata;
/*      */ import javax.imageio.metadata.IIOMetadataNode;
/*      */ import javax.imageio.stream.ImageInputStream;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TIFFImageMetadata
/*      */   extends IIOMetadata
/*      */ {
/*      */   public static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_tiff_image_1.0";
/*      */   public static final String nativeMetadataFormatClassName = "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormat";
/*      */   List tagSets;
/*      */   TIFFIFD rootIFD;
/*      */   
/*      */   public TIFFImageMetadata(List tagSets) {
/*   92 */     super(true, "com_sun_media_imageio_plugins_tiff_image_1.0", "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormat", null, null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   97 */     this.tagSets = tagSets;
/*   98 */     this.rootIFD = new TIFFIFD(tagSets);
/*      */   }
/*      */   
/*      */   public TIFFImageMetadata(TIFFIFD ifd) {
/*  102 */     super(true, "com_sun_media_imageio_plugins_tiff_image_1.0", "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormat", null, null);
/*      */ 
/*      */ 
/*      */     
/*  106 */     this.tagSets = ifd.getTagSetList();
/*  107 */     this.rootIFD = ifd;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void initializeFromStream(ImageInputStream stream, boolean ignoreUnknownFields) throws IOException {
/*  113 */     this.rootIFD.initialize(stream, ignoreUnknownFields);
/*      */   }
/*      */   
/*      */   public void addShortOrLongField(int tagNumber, int value) {
/*  117 */     TIFFField field = new TIFFField(this.rootIFD.getTag(tagNumber), value);
/*  118 */     this.rootIFD.addTIFFField(field);
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
/*      */   public boolean isReadOnly() {
/*  139 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private Node getIFDAsTree(TIFFIFD ifd, String parentTagName, int parentTagNumber) {
/*  144 */     IIOMetadataNode IFDRoot = new IIOMetadataNode("TIFFIFD");
/*  145 */     if (parentTagNumber != 0) {
/*  146 */       IFDRoot.setAttribute("parentTagNumber", 
/*  147 */           Integer.toString(parentTagNumber));
/*      */     }
/*  149 */     if (parentTagName != null) {
/*  150 */       IFDRoot.setAttribute("parentTagName", parentTagName);
/*      */     }
/*      */     
/*  153 */     List tagSets = ifd.getTagSetList();
/*  154 */     if (tagSets.size() > 0) {
/*  155 */       Iterator<TIFFTagSet> iterator = tagSets.iterator();
/*  156 */       String tagSetNames = "";
/*  157 */       while (iterator.hasNext()) {
/*  158 */         TIFFTagSet tagSet = iterator.next();
/*  159 */         tagSetNames = tagSetNames + tagSet.getClass().getName();
/*  160 */         if (iterator.hasNext()) {
/*  161 */           tagSetNames = tagSetNames + ",";
/*      */         }
/*      */       } 
/*      */       
/*  165 */       IFDRoot.setAttribute("tagSets", tagSetNames);
/*      */     } 
/*      */     
/*  168 */     Iterator<TIFFField> iter = ifd.iterator();
/*  169 */     while (iter.hasNext()) {
/*  170 */       TIFFField f = iter.next();
/*  171 */       int tagNumber = f.getTagNumber();
/*  172 */       TIFFTag tag = TIFFIFD.getTag(tagNumber, tagSets);
/*      */       
/*  174 */       Node node = null;
/*  175 */       if (tag == null) {
/*  176 */         node = f.getAsNativeNode();
/*  177 */       } else if (tag.isIFDPointer()) {
/*  178 */         TIFFIFD subIFD = (TIFFIFD)f.getData();
/*      */ 
/*      */         
/*  181 */         node = getIFDAsTree(subIFD, tag.getName(), tag.getNumber());
/*      */       } else {
/*  183 */         node = f.getAsNativeNode();
/*      */       } 
/*      */       
/*  186 */       if (node != null) {
/*  187 */         IFDRoot.appendChild(node);
/*      */       }
/*      */     } 
/*      */     
/*  191 */     return IFDRoot;
/*      */   }
/*      */   
/*      */   public Node getAsTree(String formatName) {
/*  195 */     if (formatName.equals("com_sun_media_imageio_plugins_tiff_image_1.0"))
/*  196 */       return getNativeTree(); 
/*  197 */     if (formatName
/*  198 */       .equals("javax_imageio_1.0")) {
/*  199 */       return getStandardTree();
/*      */     }
/*  201 */     throw new IllegalArgumentException("Not a recognized format!");
/*      */   }
/*      */ 
/*      */   
/*      */   private Node getNativeTree() {
/*  206 */     IIOMetadataNode root = new IIOMetadataNode("com_sun_media_imageio_plugins_tiff_image_1.0");
/*      */     
/*  208 */     Node IFDNode = getIFDAsTree(this.rootIFD, null, 0);
/*  209 */     root.appendChild(IFDNode);
/*      */     
/*  211 */     return root;
/*      */   }
/*      */   
/*  214 */   private static final String[] colorSpaceNames = new String[] { "GRAY", "GRAY", "RGB", "RGB", "GRAY", "CMYK", "YCbCr", "Lab", "Lab" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IIOMetadataNode getStandardChromaNode() {
/*  227 */     IIOMetadataNode chroma_node = new IIOMetadataNode("Chroma");
/*  228 */     IIOMetadataNode node = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  233 */     int photometricInterpretation = -1;
/*  234 */     boolean isPaletteColor = false;
/*  235 */     TIFFField f = getTIFFField(262);
/*  236 */     if (f != null) {
/*  237 */       photometricInterpretation = f.getAsInt(0);
/*      */       
/*  239 */       isPaletteColor = (photometricInterpretation == 3);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  245 */     int numChannels = -1;
/*  246 */     if (isPaletteColor) {
/*  247 */       numChannels = 3;
/*      */     } else {
/*  249 */       f = getTIFFField(277);
/*  250 */       if (f != null) {
/*  251 */         numChannels = f.getAsInt(0);
/*      */       } else {
/*  253 */         f = getTIFFField(258);
/*  254 */         if (f != null) {
/*  255 */           numChannels = f.getCount();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  260 */     if (photometricInterpretation != -1) {
/*  261 */       if (photometricInterpretation >= 0 && photometricInterpretation < colorSpaceNames.length) {
/*      */         String csName;
/*  263 */         node = new IIOMetadataNode("ColorSpaceType");
/*      */         
/*  265 */         if (photometricInterpretation == 5 && numChannels == 3) {
/*      */ 
/*      */           
/*  268 */           csName = "CMY";
/*      */         } else {
/*  270 */           csName = colorSpaceNames[photometricInterpretation];
/*      */         } 
/*  272 */         node.setAttribute("name", csName);
/*  273 */         chroma_node.appendChild(node);
/*      */       } 
/*      */       
/*  276 */       node = new IIOMetadataNode("BlackIsZero");
/*  277 */       node.setAttribute("value", (photometricInterpretation == 0) ? "FALSE" : "TRUE");
/*      */ 
/*      */ 
/*      */       
/*  281 */       chroma_node.appendChild(node);
/*      */     } 
/*      */     
/*  284 */     if (numChannels != -1) {
/*  285 */       node = new IIOMetadataNode("NumChannels");
/*  286 */       node.setAttribute("value", Integer.toString(numChannels));
/*  287 */       chroma_node.appendChild(node);
/*      */     } 
/*      */     
/*  290 */     f = getTIFFField(320);
/*  291 */     if (f != null) {
/*      */ 
/*      */ 
/*      */       
/*  295 */       boolean hasAlpha = false;
/*      */       
/*  297 */       node = new IIOMetadataNode("Palette");
/*  298 */       int len = f.getCount() / (hasAlpha ? 4 : 3);
/*  299 */       for (int i = 0; i < len; i++) {
/*  300 */         IIOMetadataNode entry = new IIOMetadataNode("PaletteEntry");
/*      */         
/*  302 */         entry.setAttribute("index", Integer.toString(i));
/*      */         
/*  304 */         int r = f.getAsInt(i) * 255 / 65535;
/*  305 */         int g = f.getAsInt(len + i) * 255 / 65535;
/*  306 */         int b = f.getAsInt(2 * len + i) * 255 / 65535;
/*      */         
/*  308 */         entry.setAttribute("red", Integer.toString(r));
/*  309 */         entry.setAttribute("green", Integer.toString(g));
/*  310 */         entry.setAttribute("blue", Integer.toString(b));
/*  311 */         if (hasAlpha) {
/*  312 */           int alpha = 0;
/*  313 */           entry.setAttribute("alpha", Integer.toString(alpha));
/*      */         } 
/*  315 */         node.appendChild(entry);
/*      */       } 
/*  317 */       chroma_node.appendChild(node);
/*      */     } 
/*      */     
/*  320 */     return chroma_node;
/*      */   }
/*      */   
/*      */   public IIOMetadataNode getStandardCompressionNode() {
/*  324 */     IIOMetadataNode compression_node = new IIOMetadataNode("Compression");
/*  325 */     IIOMetadataNode node = null;
/*      */ 
/*      */ 
/*      */     
/*  329 */     TIFFField f = getTIFFField(259);
/*  330 */     if (f != null) {
/*  331 */       String compressionTypeName = null;
/*  332 */       int compression = f.getAsInt(0);
/*  333 */       boolean isLossless = true;
/*  334 */       if (compression == 1) {
/*  335 */         compressionTypeName = "None";
/*  336 */         isLossless = true;
/*      */       } else {
/*  338 */         int[] compressionNumbers = TIFFImageWriter.compressionNumbers;
/*  339 */         for (int i = 0; i < compressionNumbers.length; i++) {
/*  340 */           if (compression == compressionNumbers[i]) {
/*  341 */             compressionTypeName = TIFFImageWriter.compressionTypes[i];
/*      */             
/*  343 */             isLossless = TIFFImageWriter.isCompressionLossless[i];
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  350 */       if (compressionTypeName != null) {
/*  351 */         node = new IIOMetadataNode("CompressionTypeName");
/*  352 */         node.setAttribute("value", compressionTypeName);
/*  353 */         compression_node.appendChild(node);
/*      */         
/*  355 */         node = new IIOMetadataNode("Lossless");
/*  356 */         node.setAttribute("value", isLossless ? "TRUE" : "FALSE");
/*  357 */         compression_node.appendChild(node);
/*      */       } 
/*      */     } 
/*      */     
/*  361 */     node = new IIOMetadataNode("NumProgressiveScans");
/*  362 */     node.setAttribute("value", "1");
/*  363 */     compression_node.appendChild(node);
/*      */     
/*  365 */     return compression_node;
/*      */   }
/*      */   
/*      */   private String repeat(String s, int times) {
/*  369 */     if (times == 1) {
/*  370 */       return s;
/*      */     }
/*  372 */     StringBuffer sb = new StringBuffer((s.length() + 1) * times - 1);
/*  373 */     sb.append(s);
/*  374 */     for (int i = 1; i < times; i++) {
/*  375 */       sb.append(" ");
/*  376 */       sb.append(s);
/*      */     } 
/*  378 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public IIOMetadataNode getStandardDataNode() {
/*  382 */     IIOMetadataNode data_node = new IIOMetadataNode("Data");
/*  383 */     IIOMetadataNode node = null;
/*      */ 
/*      */ 
/*      */     
/*  387 */     boolean isPaletteColor = false;
/*  388 */     TIFFField f = getTIFFField(262);
/*  389 */     if (f != null)
/*      */     {
/*  391 */       isPaletteColor = (f.getAsInt(0) == 3);
/*      */     }
/*      */ 
/*      */     
/*  395 */     f = getTIFFField(284);
/*  396 */     String planarConfiguration = "PixelInterleaved";
/*  397 */     if (f != null && f
/*  398 */       .getAsInt(0) == 2) {
/*  399 */       planarConfiguration = "PlaneInterleaved";
/*      */     }
/*      */     
/*  402 */     node = new IIOMetadataNode("PlanarConfiguration");
/*  403 */     node.setAttribute("value", planarConfiguration);
/*  404 */     data_node.appendChild(node);
/*      */     
/*  406 */     f = getTIFFField(262);
/*  407 */     if (f != null) {
/*  408 */       int photometricInterpretation = f.getAsInt(0);
/*  409 */       String sampleFormat = "UnsignedIntegral";
/*      */       
/*  411 */       if (photometricInterpretation == 3) {
/*      */         
/*  413 */         sampleFormat = "Index";
/*      */       } else {
/*  415 */         f = getTIFFField(339);
/*  416 */         if (f != null) {
/*  417 */           int format = f.getAsInt(0);
/*  418 */           if (format == 2) {
/*      */             
/*  420 */             sampleFormat = "SignedIntegral";
/*  421 */           } else if (format == 1) {
/*      */             
/*  423 */             sampleFormat = "UnsignedIntegral";
/*  424 */           } else if (format == 3) {
/*      */             
/*  426 */             sampleFormat = "Real";
/*      */           } else {
/*  428 */             sampleFormat = null;
/*      */           } 
/*      */         } 
/*      */       } 
/*  432 */       if (sampleFormat != null) {
/*  433 */         node = new IIOMetadataNode("SampleFormat");
/*  434 */         node.setAttribute("value", sampleFormat);
/*  435 */         data_node.appendChild(node);
/*      */       } 
/*      */     } 
/*      */     
/*  439 */     f = getTIFFField(258);
/*  440 */     int[] bitsPerSample = null;
/*  441 */     if (f != null) {
/*  442 */       bitsPerSample = f.getAsInts();
/*      */     } else {
/*  444 */       f = getTIFFField(259);
/*      */       
/*  446 */       int compression = (f != null) ? f.getAsInt(0) : 1;
/*  447 */       if (getTIFFField(34665) != null || compression == 7 || compression == 6 || 
/*      */ 
/*      */ 
/*      */         
/*  451 */         getTIFFField(513) != null) {
/*      */         
/*  453 */         f = getTIFFField(262);
/*  454 */         if (f != null && (f
/*  455 */           .getAsInt(0) == 0 || f
/*      */           
/*  457 */           .getAsInt(0) == 1)) {
/*      */           
/*  459 */           bitsPerSample = new int[] { 8 };
/*      */         } else {
/*  461 */           bitsPerSample = new int[] { 8, 8, 8 };
/*      */         } 
/*      */       } else {
/*  464 */         bitsPerSample = new int[] { 1 };
/*      */       } 
/*      */     } 
/*  467 */     StringBuffer sb = new StringBuffer();
/*  468 */     for (int i = 0; i < bitsPerSample.length; i++) {
/*  469 */       if (i > 0) {
/*  470 */         sb.append(" ");
/*      */       }
/*  472 */       sb.append(Integer.toString(bitsPerSample[i]));
/*      */     } 
/*  474 */     node = new IIOMetadataNode("BitsPerSample");
/*  475 */     if (isPaletteColor) {
/*  476 */       node.setAttribute("value", repeat(sb.toString(), 3));
/*      */     } else {
/*  478 */       node.setAttribute("value", sb.toString());
/*      */     } 
/*  480 */     data_node.appendChild(node);
/*      */ 
/*      */     
/*  483 */     f = getTIFFField(266);
/*      */     
/*  485 */     int fillOrder = (f != null) ? f.getAsInt(0) : 1;
/*  486 */     sb = new StringBuffer();
/*  487 */     for (int j = 0; j < bitsPerSample.length; j++) {
/*  488 */       if (j > 0) {
/*  489 */         sb.append(" ");
/*      */       }
/*  491 */       int maxBitIndex = (bitsPerSample[j] == 1) ? 7 : (bitsPerSample[j] - 1);
/*      */       
/*  493 */       int msb = (fillOrder == 1) ? maxBitIndex : 0;
/*      */ 
/*      */       
/*  496 */       sb.append(Integer.toString(msb));
/*      */     } 
/*  498 */     node = new IIOMetadataNode("SampleMSB");
/*  499 */     if (isPaletteColor) {
/*  500 */       node.setAttribute("value", repeat(sb.toString(), 3));
/*      */     } else {
/*  502 */       node.setAttribute("value", sb.toString());
/*      */     } 
/*  504 */     data_node.appendChild(node);
/*      */     
/*  506 */     return data_node;
/*      */   }
/*      */   
/*  509 */   private static final String[] orientationNames = new String[] { null, "Normal", "FlipH", "Rotate180", "FlipV", "FlipHRotate90", "Rotate270", "FlipVRotate90", "Rotate90" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IIOMetadataNode getStandardDimensionNode() {
/*  522 */     IIOMetadataNode dimension_node = new IIOMetadataNode("Dimension");
/*  523 */     IIOMetadataNode node = null;
/*      */ 
/*      */ 
/*      */     
/*  527 */     long[] xres = null;
/*  528 */     long[] yres = null;
/*      */     
/*  530 */     TIFFField f = getTIFFField(282);
/*  531 */     if (f != null) {
/*  532 */       xres = (long[])f.getAsRational(0).clone();
/*      */     }
/*      */     
/*  535 */     f = getTIFFField(283);
/*  536 */     if (f != null) {
/*  537 */       yres = (long[])f.getAsRational(0).clone();
/*      */     }
/*      */     
/*  540 */     if (xres != null && yres != null) {
/*  541 */       node = new IIOMetadataNode("PixelAspectRatio");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  547 */       float ratio = (float)(xres[1] * yres[0]) / (float)(xres[0] * yres[1]);
/*  548 */       node.setAttribute("value", Float.toString(ratio));
/*  549 */       dimension_node.appendChild(node);
/*      */     } 
/*      */     
/*  552 */     if (xres != null || yres != null) {
/*      */       
/*  554 */       f = getTIFFField(296);
/*      */ 
/*      */ 
/*      */       
/*  558 */       int i = (f != null) ? f.getAsInt(0) : 2;
/*      */ 
/*      */       
/*  561 */       boolean gotPixelSize = (i != 1);
/*      */ 
/*      */ 
/*      */       
/*  565 */       if (i == 2) {
/*      */         
/*  567 */         if (xres != null) {
/*  568 */           xres[0] = xres[0] * 100L;
/*  569 */           xres[1] = xres[1] * 254L;
/*      */         } 
/*      */ 
/*      */         
/*  573 */         if (yres != null) {
/*  574 */           yres[0] = yres[0] * 100L;
/*  575 */           yres[1] = yres[1] * 254L;
/*      */         } 
/*      */       } 
/*      */       
/*  579 */       if (gotPixelSize) {
/*  580 */         if (xres != null) {
/*  581 */           float horizontalPixelSize = (float)(10.0D * xres[1] / xres[0]);
/*  582 */           node = new IIOMetadataNode("HorizontalPixelSize");
/*  583 */           node.setAttribute("value", 
/*  584 */               Float.toString(horizontalPixelSize));
/*  585 */           dimension_node.appendChild(node);
/*      */         } 
/*      */         
/*  588 */         if (yres != null) {
/*  589 */           float verticalPixelSize = (float)(10.0D * yres[1] / yres[0]);
/*  590 */           node = new IIOMetadataNode("VerticalPixelSize");
/*  591 */           node.setAttribute("value", 
/*  592 */               Float.toString(verticalPixelSize));
/*  593 */           dimension_node.appendChild(node);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  598 */     f = getTIFFField(296);
/*      */     
/*  600 */     int resolutionUnit = (f != null) ? f.getAsInt(0) : 2;
/*  601 */     if (resolutionUnit == 2 || resolutionUnit == 3) {
/*      */       
/*  603 */       f = getTIFFField(286);
/*  604 */       if (f != null) {
/*  605 */         long[] xpos = f.getAsRational(0);
/*  606 */         float xPosition = (float)xpos[0] / (float)xpos[1];
/*      */         
/*  608 */         if (resolutionUnit == 2) {
/*  609 */           xPosition *= 254.0F;
/*      */         } else {
/*  611 */           xPosition *= 10.0F;
/*      */         } 
/*  613 */         node = new IIOMetadataNode("HorizontalPosition");
/*  614 */         node.setAttribute("value", 
/*  615 */             Float.toString(xPosition));
/*  616 */         dimension_node.appendChild(node);
/*      */       } 
/*      */       
/*  619 */       f = getTIFFField(287);
/*  620 */       if (f != null) {
/*  621 */         long[] ypos = f.getAsRational(0);
/*  622 */         float yPosition = (float)ypos[0] / (float)ypos[1];
/*      */         
/*  624 */         if (resolutionUnit == 2) {
/*  625 */           yPosition *= 254.0F;
/*      */         } else {
/*  627 */           yPosition *= 10.0F;
/*      */         } 
/*  629 */         node = new IIOMetadataNode("VerticalPosition");
/*  630 */         node.setAttribute("value", 
/*  631 */             Float.toString(yPosition));
/*  632 */         dimension_node.appendChild(node);
/*      */       } 
/*      */     } 
/*      */     
/*  636 */     f = getTIFFField(274);
/*  637 */     if (f != null) {
/*  638 */       int o = f.getAsInt(0);
/*  639 */       if (o >= 0 && o < orientationNames.length) {
/*  640 */         node = new IIOMetadataNode("ImageOrientation");
/*  641 */         node.setAttribute("value", orientationNames[o]);
/*  642 */         dimension_node.appendChild(node);
/*      */       } 
/*      */     } 
/*      */     
/*  646 */     return dimension_node;
/*      */   }
/*      */   
/*      */   public IIOMetadataNode getStandardDocumentNode() {
/*  650 */     IIOMetadataNode document_node = new IIOMetadataNode("Document");
/*  651 */     IIOMetadataNode node = null;
/*      */ 
/*      */ 
/*      */     
/*  655 */     node = new IIOMetadataNode("FormatVersion");
/*  656 */     node.setAttribute("value", "6.0");
/*  657 */     document_node.appendChild(node);
/*      */     
/*  659 */     TIFFField f = getTIFFField(254);
/*  660 */     if (f != null) {
/*  661 */       int newSubFileType = f.getAsInt(0);
/*  662 */       String value = null;
/*  663 */       if ((newSubFileType & 0x4) != 0) {
/*      */         
/*  665 */         value = "TransparencyMask";
/*  666 */       } else if ((newSubFileType & 0x1) != 0) {
/*      */         
/*  668 */         value = "ReducedResolution";
/*  669 */       } else if ((newSubFileType & 0x2) != 0) {
/*      */         
/*  671 */         value = "SinglePage";
/*      */       } 
/*  673 */       if (value != null) {
/*  674 */         node = new IIOMetadataNode("SubimageInterpretation");
/*  675 */         node.setAttribute("value", value);
/*  676 */         document_node.appendChild(node);
/*      */       } 
/*      */     } 
/*      */     
/*  680 */     f = getTIFFField(306);
/*  681 */     if (f != null) {
/*  682 */       String s = f.getAsString(0);
/*      */ 
/*      */       
/*  685 */       if (s.length() == 19) {
/*  686 */         boolean appendNode; node = new IIOMetadataNode("ImageCreationTime");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  694 */           node.setAttribute("year", s.substring(0, 4));
/*  695 */           node.setAttribute("month", s.substring(5, 7));
/*  696 */           node.setAttribute("day", s.substring(8, 10));
/*  697 */           node.setAttribute("hour", s.substring(11, 13));
/*  698 */           node.setAttribute("minute", s.substring(14, 16));
/*  699 */           node.setAttribute("second", s.substring(17, 19));
/*  700 */           appendNode = true;
/*  701 */         } catch (IndexOutOfBoundsException e) {
/*  702 */           appendNode = false;
/*      */         } 
/*      */         
/*  705 */         if (appendNode) {
/*  706 */           document_node.appendChild(node);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  711 */     return document_node;
/*      */   }
/*      */   
/*      */   public IIOMetadataNode getStandardTextNode() {
/*  715 */     IIOMetadataNode text_node = null;
/*  716 */     IIOMetadataNode node = null;
/*      */ 
/*      */ 
/*      */     
/*  720 */     int[] textFieldTagNumbers = { 269, 270, 271, 272, 285, 305, 315, 316, 333, 33432 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  733 */     for (int i = 0; i < textFieldTagNumbers.length; i++) {
/*  734 */       TIFFField f = getTIFFField(textFieldTagNumbers[i]);
/*  735 */       if (f != null) {
/*  736 */         String value = f.getAsString(0);
/*  737 */         if (text_node == null) {
/*  738 */           text_node = new IIOMetadataNode("Text");
/*      */         }
/*  740 */         node = new IIOMetadataNode("TextEntry");
/*  741 */         node.setAttribute("keyword", f.getTag().getName());
/*  742 */         node.setAttribute("value", value);
/*  743 */         text_node.appendChild(node);
/*      */       } 
/*      */     } 
/*      */     
/*  747 */     return text_node;
/*      */   }
/*      */   
/*      */   public IIOMetadataNode getStandardTransparencyNode() {
/*  751 */     IIOMetadataNode transparency_node = new IIOMetadataNode("Transparency");
/*      */     
/*  753 */     IIOMetadataNode node = null;
/*      */ 
/*      */ 
/*      */     
/*  757 */     node = new IIOMetadataNode("Alpha");
/*  758 */     String value = "none";
/*      */     
/*  760 */     TIFFField f = getTIFFField(338);
/*  761 */     if (f != null) {
/*  762 */       int[] extraSamples = f.getAsInts();
/*  763 */       for (int i = 0; i < extraSamples.length; i++) {
/*  764 */         if (extraSamples[i] == 1) {
/*      */           
/*  766 */           value = "premultiplied"; break;
/*      */         } 
/*  768 */         if (extraSamples[i] == 2) {
/*      */           
/*  770 */           value = "nonpremultiplied";
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  776 */     node.setAttribute("value", value);
/*  777 */     transparency_node.appendChild(node);
/*      */     
/*  779 */     return transparency_node;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void fatal(Node node, String reason) throws IIOInvalidTreeException {
/*  785 */     throw new IIOInvalidTreeException(reason, node);
/*      */   }
/*      */   
/*      */   private int[] listToIntArray(String list) {
/*  789 */     StringTokenizer st = new StringTokenizer(list, " ");
/*  790 */     ArrayList<Integer> intList = new ArrayList();
/*  791 */     while (st.hasMoreTokens()) {
/*  792 */       String nextInteger = st.nextToken();
/*  793 */       Integer nextInt = new Integer(nextInteger);
/*  794 */       intList.add(nextInt);
/*      */     } 
/*      */     
/*  797 */     int[] intArray = new int[intList.size()];
/*  798 */     for (int i = 0; i < intArray.length; i++) {
/*  799 */       intArray[i] = ((Integer)intList.get(i)).intValue();
/*      */     }
/*      */     
/*  802 */     return intArray;
/*      */   }
/*      */   
/*      */   private char[] listToCharArray(String list) {
/*  806 */     StringTokenizer st = new StringTokenizer(list, " ");
/*  807 */     ArrayList<Integer> intList = new ArrayList();
/*  808 */     while (st.hasMoreTokens()) {
/*  809 */       String nextInteger = st.nextToken();
/*  810 */       Integer nextInt = new Integer(nextInteger);
/*  811 */       intList.add(nextInt);
/*      */     } 
/*      */     
/*  814 */     char[] charArray = new char[intList.size()];
/*  815 */     for (int i = 0; i < charArray.length; i++) {
/*  816 */       charArray[i] = (char)((Integer)intList.get(i)).intValue();
/*      */     }
/*      */     
/*  819 */     return charArray;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/*  827 */     Node node = root;
/*      */     
/*  829 */     if (!node.getNodeName().equals("javax_imageio_1.0")) {
/*  830 */       fatal(node, "Root must be javax_imageio_1.0");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  835 */     String sampleFormat = null;
/*  836 */     Node dataNode = getChildNode(root, "Data");
/*  837 */     boolean isPaletteColor = false;
/*  838 */     if (dataNode != null) {
/*  839 */       Node sampleFormatNode = getChildNode(dataNode, "SampleFormat");
/*  840 */       if (sampleFormatNode != null) {
/*  841 */         sampleFormat = getAttribute(sampleFormatNode, "value");
/*  842 */         isPaletteColor = sampleFormat.equals("Index");
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  847 */     if (!isPaletteColor) {
/*  848 */       Node chromaNode = getChildNode(root, "Chroma");
/*  849 */       if (chromaNode != null && 
/*  850 */         getChildNode(chromaNode, "Palette") != null) {
/*  851 */         isPaletteColor = true;
/*      */       }
/*      */     } 
/*      */     
/*  855 */     node = node.getFirstChild();
/*  856 */     while (node != null) {
/*  857 */       String name = node.getNodeName();
/*      */       
/*  859 */       if (name.equals("Chroma")) {
/*  860 */         String colorSpaceType = null;
/*  861 */         String blackIsZero = null;
/*  862 */         boolean gotPalette = false;
/*  863 */         Node child = node.getFirstChild();
/*  864 */         while (child != null) {
/*  865 */           String childName = child.getNodeName();
/*  866 */           if (childName.equals("ColorSpaceType")) {
/*  867 */             colorSpaceType = getAttribute(child, "name");
/*  868 */           } else if (childName.equals("NumChannels")) {
/*  869 */             TIFFTag tag = this.rootIFD.getTag(277);
/*      */             
/*  871 */             int samplesPerPixel = isPaletteColor ? 1 : Integer.parseInt(getAttribute(child, "value"));
/*  872 */             TIFFField f = new TIFFField(tag, samplesPerPixel);
/*  873 */             this.rootIFD.addTIFFField(f);
/*  874 */           } else if (childName.equals("BlackIsZero")) {
/*  875 */             blackIsZero = getAttribute(child, "value");
/*  876 */           } else if (childName.equals("Palette")) {
/*  877 */             Node entry = child.getFirstChild();
/*  878 */             HashMap<Object, Object> palette = new HashMap<Object, Object>();
/*  879 */             int maxIndex = -1;
/*  880 */             while (entry != null) {
/*  881 */               String entryName = entry.getNodeName();
/*  882 */               if (entryName.equals("PaletteEntry")) {
/*  883 */                 String idx = getAttribute(entry, "index");
/*  884 */                 int id = Integer.parseInt(idx);
/*  885 */                 if (id > maxIndex) {
/*  886 */                   maxIndex = id;
/*      */                 }
/*      */                 
/*  889 */                 char red = (char)Integer.parseInt(getAttribute(entry, "red"));
/*      */ 
/*      */                 
/*  892 */                 char green = (char)Integer.parseInt(getAttribute(entry, "green"));
/*      */ 
/*      */                 
/*  895 */                 char blue = (char)Integer.parseInt(getAttribute(entry, "blue"));
/*      */                 
/*  897 */                 palette.put(new Integer(id), new char[] { red, green, blue });
/*      */ 
/*      */                 
/*  900 */                 gotPalette = true;
/*      */               } 
/*  902 */               entry = entry.getNextSibling();
/*      */             } 
/*      */             
/*  905 */             if (gotPalette) {
/*  906 */               int mapSize = maxIndex + 1;
/*  907 */               int paletteLength = 3 * mapSize;
/*  908 */               char[] paletteEntries = new char[paletteLength];
/*  909 */               Iterator<Integer> paletteIter = palette.keySet().iterator();
/*  910 */               while (paletteIter.hasNext()) {
/*  911 */                 Integer index = paletteIter.next();
/*  912 */                 char[] rgb = (char[])palette.get(index);
/*  913 */                 int idx = index.intValue();
/*  914 */                 paletteEntries[idx] = (char)(rgb[0] * 65535 / 255);
/*      */                 
/*  916 */                 paletteEntries[mapSize + idx] = (char)(rgb[1] * 65535 / 255);
/*      */                 
/*  918 */                 paletteEntries[2 * mapSize + idx] = (char)(rgb[2] * 65535 / 255);
/*      */               } 
/*      */ 
/*      */               
/*  922 */               TIFFTag tag = this.rootIFD.getTag(320);
/*  923 */               TIFFField f = new TIFFField(tag, 3, paletteLength, paletteEntries);
/*      */               
/*  925 */               this.rootIFD.addTIFFField(f);
/*      */             } 
/*      */           } 
/*      */           
/*  929 */           child = child.getNextSibling();
/*      */         } 
/*      */         
/*  932 */         int photometricInterpretation = -1;
/*  933 */         if ((colorSpaceType == null || colorSpaceType.equals("GRAY")) && blackIsZero != null && blackIsZero
/*      */           
/*  935 */           .equalsIgnoreCase("FALSE")) {
/*  936 */           photometricInterpretation = 0;
/*      */         }
/*  938 */         else if (colorSpaceType != null) {
/*  939 */           if (colorSpaceType.equals("GRAY")) {
/*  940 */             boolean isTransparency = false;
/*  941 */             if (root instanceof IIOMetadataNode) {
/*  942 */               IIOMetadataNode iioRoot = (IIOMetadataNode)root;
/*      */               
/*  944 */               NodeList siNodeList = iioRoot.getElementsByTagName("SubimageInterpretation");
/*  945 */               if (siNodeList.getLength() == 1) {
/*  946 */                 Node siNode = siNodeList.item(0);
/*  947 */                 String value = getAttribute(siNode, "value");
/*  948 */                 if (value.equals("TransparencyMask")) {
/*  949 */                   isTransparency = true;
/*      */                 }
/*      */               } 
/*      */             } 
/*  953 */             if (isTransparency) {
/*  954 */               photometricInterpretation = 4;
/*      */             } else {
/*      */               
/*  957 */               photometricInterpretation = 1;
/*      */             }
/*      */           
/*  960 */           } else if (colorSpaceType.equals("RGB")) {
/*  961 */             photometricInterpretation = gotPalette ? 3 : 2;
/*      */ 
/*      */           
/*      */           }
/*  965 */           else if (colorSpaceType.equals("YCbCr")) {
/*  966 */             photometricInterpretation = 6;
/*      */           }
/*  968 */           else if (colorSpaceType.equals("CMYK")) {
/*  969 */             photometricInterpretation = 5;
/*      */           }
/*  971 */           else if (colorSpaceType.equals("Lab")) {
/*  972 */             photometricInterpretation = 8;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  977 */         if (photometricInterpretation != -1) {
/*  978 */           TIFFTag tag = this.rootIFD.getTag(262);
/*  979 */           TIFFField f = new TIFFField(tag, photometricInterpretation);
/*  980 */           this.rootIFD.addTIFFField(f);
/*      */         } 
/*  982 */       } else if (name.equals("Compression")) {
/*  983 */         Node child = node.getFirstChild();
/*  984 */         while (child != null) {
/*  985 */           String childName = child.getNodeName();
/*  986 */           if (childName.equals("CompressionTypeName")) {
/*  987 */             int compression = -1;
/*      */             
/*  989 */             String compressionTypeName = getAttribute(child, "value");
/*  990 */             if (compressionTypeName.equalsIgnoreCase("None")) {
/*  991 */               compression = 1;
/*      */             } else {
/*      */               
/*  994 */               String[] compressionNames = TIFFImageWriter.compressionTypes;
/*      */               
/*  996 */               for (int i = 0; i < compressionNames.length; i++) {
/*  997 */                 if (compressionNames[i].equalsIgnoreCase(compressionTypeName)) {
/*  998 */                   compression = TIFFImageWriter.compressionNumbers[i];
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */             
/* 1005 */             if (compression != -1) {
/* 1006 */               TIFFTag tag = this.rootIFD.getTag(259);
/* 1007 */               TIFFField f = new TIFFField(tag, compression);
/* 1008 */               this.rootIFD.addTIFFField(f);
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1014 */           child = child.getNextSibling();
/*      */         } 
/* 1016 */       } else if (name.equals("Data")) {
/* 1017 */         Node child = node.getFirstChild();
/* 1018 */         while (child != null) {
/* 1019 */           String childName = child.getNodeName();
/*      */           
/* 1021 */           if (childName.equals("PlanarConfiguration")) {
/* 1022 */             String pc = getAttribute(child, "value");
/* 1023 */             int planarConfiguration = -1;
/* 1024 */             if (pc.equals("PixelInterleaved")) {
/* 1025 */               planarConfiguration = 1;
/*      */             }
/* 1027 */             else if (pc.equals("PlaneInterleaved")) {
/* 1028 */               planarConfiguration = 2;
/*      */             } 
/*      */             
/* 1031 */             if (planarConfiguration != -1) {
/* 1032 */               TIFFTag tag = this.rootIFD.getTag(284);
/* 1033 */               TIFFField f = new TIFFField(tag, planarConfiguration);
/* 1034 */               this.rootIFD.addTIFFField(f);
/*      */             } 
/* 1036 */           } else if (childName.equals("BitsPerSample")) {
/* 1037 */             TIFFField f; String bps = getAttribute(child, "value");
/* 1038 */             char[] bitsPerSample = listToCharArray(bps);
/* 1039 */             TIFFTag tag = this.rootIFD.getTag(258);
/* 1040 */             if (isPaletteColor) {
/* 1041 */               f = new TIFFField(tag, 3, 1, new char[] { bitsPerSample[0] });
/*      */             } else {
/*      */               
/* 1044 */               f = new TIFFField(tag, 3, bitsPerSample.length, bitsPerSample);
/*      */             } 
/*      */ 
/*      */             
/* 1048 */             this.rootIFD.addTIFFField(f);
/* 1049 */           } else if (childName.equals("SampleMSB")) {
/*      */ 
/*      */ 
/*      */             
/* 1053 */             String sMSB = getAttribute(child, "value");
/* 1054 */             int[] sampleMSB = listToIntArray(sMSB);
/* 1055 */             boolean isRightToLeft = true;
/* 1056 */             for (int i = 0; i < sampleMSB.length; i++) {
/* 1057 */               if (sampleMSB[i] != 0) {
/* 1058 */                 isRightToLeft = false;
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1062 */             int fillOrder = isRightToLeft ? 2 : 1;
/*      */ 
/*      */ 
/*      */             
/* 1066 */             TIFFTag tag = this.rootIFD.getTag(266);
/* 1067 */             TIFFField f = new TIFFField(tag, fillOrder);
/* 1068 */             this.rootIFD.addTIFFField(f);
/*      */           } 
/*      */           
/* 1071 */           child = child.getNextSibling();
/*      */         } 
/* 1073 */       } else if (name.equals("Dimension")) {
/* 1074 */         float pixelAspectRatio = -1.0F;
/* 1075 */         boolean gotPixelAspectRatio = false;
/*      */         
/* 1077 */         float horizontalPixelSize = -1.0F;
/* 1078 */         boolean gotHorizontalPixelSize = false;
/*      */         
/* 1080 */         float verticalPixelSize = -1.0F;
/* 1081 */         boolean gotVerticalPixelSize = false;
/*      */         
/* 1083 */         boolean sizeIsAbsolute = false;
/*      */         
/* 1085 */         float horizontalPosition = -1.0F;
/* 1086 */         boolean gotHorizontalPosition = false;
/*      */         
/* 1088 */         float verticalPosition = -1.0F;
/* 1089 */         boolean gotVerticalPosition = false;
/*      */         
/* 1091 */         Node child = node.getFirstChild();
/* 1092 */         while (child != null) {
/* 1093 */           String childName = child.getNodeName();
/* 1094 */           if (childName.equals("PixelAspectRatio")) {
/* 1095 */             String par = getAttribute(child, "value");
/* 1096 */             pixelAspectRatio = Float.parseFloat(par);
/* 1097 */             gotPixelAspectRatio = true;
/* 1098 */           } else if (childName.equals("ImageOrientation")) {
/* 1099 */             String orientation = getAttribute(child, "value");
/* 1100 */             for (int i = 0; i < orientationNames.length; i++) {
/* 1101 */               if (orientation.equals(orientationNames[i])) {
/* 1102 */                 char[] oData = new char[1];
/* 1103 */                 oData[0] = (char)i;
/*      */ 
/*      */                 
/* 1106 */                 TIFFField tIFFField = new TIFFField(this.rootIFD.getTag(274), 3, 1, oData);
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1111 */                 this.rootIFD.addTIFFField(tIFFField);
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1116 */           } else if (childName.equals("HorizontalPixelSize")) {
/* 1117 */             String hps = getAttribute(child, "value");
/* 1118 */             horizontalPixelSize = Float.parseFloat(hps);
/* 1119 */             gotHorizontalPixelSize = true;
/* 1120 */           } else if (childName.equals("VerticalPixelSize")) {
/* 1121 */             String vps = getAttribute(child, "value");
/* 1122 */             verticalPixelSize = Float.parseFloat(vps);
/* 1123 */             gotVerticalPixelSize = true;
/* 1124 */           } else if (childName.equals("HorizontalPosition")) {
/* 1125 */             String hp = getAttribute(child, "value");
/* 1126 */             horizontalPosition = Float.parseFloat(hp);
/* 1127 */             gotHorizontalPosition = true;
/* 1128 */           } else if (childName.equals("VerticalPosition")) {
/* 1129 */             String vp = getAttribute(child, "value");
/* 1130 */             verticalPosition = Float.parseFloat(vp);
/* 1131 */             gotVerticalPosition = true;
/*      */           } 
/*      */           
/* 1134 */           child = child.getNextSibling();
/*      */         } 
/*      */         
/* 1137 */         sizeIsAbsolute = (gotHorizontalPixelSize || gotVerticalPixelSize);
/*      */ 
/*      */ 
/*      */         
/* 1141 */         if (gotPixelAspectRatio) {
/* 1142 */           if (gotHorizontalPixelSize && !gotVerticalPixelSize) {
/* 1143 */             verticalPixelSize = horizontalPixelSize / pixelAspectRatio;
/*      */             
/* 1145 */             gotVerticalPixelSize = true;
/* 1146 */           } else if (gotVerticalPixelSize && !gotHorizontalPixelSize) {
/*      */             
/* 1148 */             horizontalPixelSize = verticalPixelSize * pixelAspectRatio;
/*      */             
/* 1150 */             gotHorizontalPixelSize = true;
/* 1151 */           } else if (!gotHorizontalPixelSize && !gotVerticalPixelSize) {
/*      */             
/* 1153 */             horizontalPixelSize = pixelAspectRatio;
/* 1154 */             verticalPixelSize = 1.0F;
/* 1155 */             gotHorizontalPixelSize = true;
/* 1156 */             gotVerticalPixelSize = true;
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 1161 */         if (gotHorizontalPixelSize) {
/* 1162 */           float xResolution = (sizeIsAbsolute ? 10.0F : 1.0F) / horizontalPixelSize;
/*      */           
/* 1164 */           long[][] hData = new long[1][2];
/* 1165 */           hData[0] = new long[2];
/* 1166 */           hData[0][0] = (long)(xResolution * 10000.0F);
/* 1167 */           hData[0][1] = 10000L;
/*      */ 
/*      */           
/* 1170 */           TIFFField tIFFField = new TIFFField(this.rootIFD.getTag(282), 5, 1, hData);
/*      */ 
/*      */ 
/*      */           
/* 1174 */           this.rootIFD.addTIFFField(tIFFField);
/*      */         } 
/*      */         
/* 1177 */         if (gotVerticalPixelSize) {
/* 1178 */           float yResolution = (sizeIsAbsolute ? 10.0F : 1.0F) / verticalPixelSize;
/*      */           
/* 1180 */           long[][] vData = new long[1][2];
/* 1181 */           vData[0] = new long[2];
/* 1182 */           vData[0][0] = (long)(yResolution * 10000.0F);
/* 1183 */           vData[0][1] = 10000L;
/*      */ 
/*      */           
/* 1186 */           TIFFField tIFFField = new TIFFField(this.rootIFD.getTag(283), 5, 1, vData);
/*      */ 
/*      */ 
/*      */           
/* 1190 */           this.rootIFD.addTIFFField(tIFFField);
/*      */         } 
/*      */ 
/*      */         
/* 1194 */         char[] res = new char[1];
/* 1195 */         res[0] = (char)(sizeIsAbsolute ? '\003' : '\001');
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1200 */         TIFFField f = new TIFFField(this.rootIFD.getTag(296), 3, 1, res);
/*      */ 
/*      */ 
/*      */         
/* 1204 */         this.rootIFD.addTIFFField(f);
/*      */ 
/*      */         
/* 1207 */         if (sizeIsAbsolute) {
/* 1208 */           if (gotHorizontalPosition) {
/*      */ 
/*      */             
/* 1211 */             long[][] hData = new long[1][2];
/* 1212 */             hData[0][0] = (long)(horizontalPosition * 10000.0F);
/* 1213 */             hData[0][1] = 100000L;
/*      */ 
/*      */             
/* 1216 */             f = new TIFFField(this.rootIFD.getTag(286), 5, 1, hData);
/*      */ 
/*      */ 
/*      */             
/* 1220 */             this.rootIFD.addTIFFField(f);
/*      */           } 
/*      */           
/* 1223 */           if (gotVerticalPosition) {
/*      */ 
/*      */             
/* 1226 */             long[][] vData = new long[1][2];
/* 1227 */             vData[0][0] = (long)(verticalPosition * 10000.0F);
/* 1228 */             vData[0][1] = 100000L;
/*      */ 
/*      */             
/* 1231 */             f = new TIFFField(this.rootIFD.getTag(287), 5, 1, vData);
/*      */ 
/*      */ 
/*      */             
/* 1235 */             this.rootIFD.addTIFFField(f);
/*      */           } 
/*      */         } 
/* 1238 */       } else if (name.equals("Document")) {
/* 1239 */         Node child = node.getFirstChild();
/* 1240 */         while (child != null) {
/* 1241 */           String childName = child.getNodeName();
/*      */           
/* 1243 */           if (childName.equals("SubimageInterpretation")) {
/* 1244 */             String si = getAttribute(child, "value");
/* 1245 */             int newSubFileType = -1;
/* 1246 */             if (si.equals("TransparencyMask")) {
/* 1247 */               newSubFileType = 4;
/*      */             }
/* 1249 */             else if (si.equals("ReducedResolution")) {
/* 1250 */               newSubFileType = 1;
/*      */             }
/* 1252 */             else if (si.equals("SinglePage")) {
/* 1253 */               newSubFileType = 2;
/*      */             } 
/*      */             
/* 1256 */             if (newSubFileType != -1) {
/*      */               
/* 1258 */               TIFFTag tag = this.rootIFD.getTag(254);
/* 1259 */               TIFFField f = new TIFFField(tag, newSubFileType);
/* 1260 */               this.rootIFD.addTIFFField(f);
/*      */             } 
/*      */           } 
/*      */           
/* 1264 */           if (childName.equals("ImageCreationTime")) {
/* 1265 */             String year = getAttribute(child, "year");
/* 1266 */             String month = getAttribute(child, "month");
/* 1267 */             String day = getAttribute(child, "day");
/* 1268 */             String hour = getAttribute(child, "hour");
/* 1269 */             String minute = getAttribute(child, "minute");
/* 1270 */             String second = getAttribute(child, "second");
/*      */             
/* 1272 */             StringBuffer sb = new StringBuffer();
/* 1273 */             sb.append(year);
/* 1274 */             sb.append(":");
/* 1275 */             if (month.length() == 1) {
/* 1276 */               sb.append("0");
/*      */             }
/* 1278 */             sb.append(month);
/* 1279 */             sb.append(":");
/* 1280 */             if (day.length() == 1) {
/* 1281 */               sb.append("0");
/*      */             }
/* 1283 */             sb.append(day);
/* 1284 */             sb.append(" ");
/* 1285 */             if (hour.length() == 1) {
/* 1286 */               sb.append("0");
/*      */             }
/* 1288 */             sb.append(hour);
/* 1289 */             sb.append(":");
/* 1290 */             if (minute.length() == 1) {
/* 1291 */               sb.append("0");
/*      */             }
/* 1293 */             sb.append(minute);
/* 1294 */             sb.append(":");
/* 1295 */             if (second.length() == 1) {
/* 1296 */               sb.append("0");
/*      */             }
/* 1298 */             sb.append(second);
/*      */             
/* 1300 */             String[] dt = new String[1];
/* 1301 */             dt[0] = sb.toString();
/*      */ 
/*      */             
/* 1304 */             TIFFField f = new TIFFField(this.rootIFD.getTag(306), 2, 1, dt);
/*      */ 
/*      */ 
/*      */             
/* 1308 */             this.rootIFD.addTIFFField(f);
/*      */           } 
/*      */           
/* 1311 */           child = child.getNextSibling();
/*      */         } 
/* 1313 */       } else if (name.equals("Text")) {
/* 1314 */         Node child = node.getFirstChild();
/* 1315 */         String theAuthor = null;
/* 1316 */         String theDescription = null;
/* 1317 */         String theTitle = null;
/* 1318 */         while (child != null) {
/* 1319 */           String childName = child.getNodeName();
/* 1320 */           if (childName.equals("TextEntry")) {
/* 1321 */             int tagNumber = -1;
/* 1322 */             NamedNodeMap childAttrs = child.getAttributes();
/* 1323 */             Node keywordNode = childAttrs.getNamedItem("keyword");
/* 1324 */             if (keywordNode != null) {
/* 1325 */               String keyword = keywordNode.getNodeValue();
/* 1326 */               String value = getAttribute(child, "value");
/* 1327 */               if (!keyword.equals("") && !value.equals("")) {
/* 1328 */                 if (keyword.equalsIgnoreCase("DocumentName")) {
/* 1329 */                   tagNumber = 269;
/*      */                 }
/* 1331 */                 else if (keyword.equalsIgnoreCase("ImageDescription")) {
/* 1332 */                   tagNumber = 270;
/*      */                 }
/* 1334 */                 else if (keyword.equalsIgnoreCase("Make")) {
/* 1335 */                   tagNumber = 271;
/*      */                 }
/* 1337 */                 else if (keyword.equalsIgnoreCase("Model")) {
/* 1338 */                   tagNumber = 272;
/*      */                 }
/* 1340 */                 else if (keyword.equalsIgnoreCase("PageName")) {
/* 1341 */                   tagNumber = 285;
/*      */                 }
/* 1343 */                 else if (keyword.equalsIgnoreCase("Software")) {
/* 1344 */                   tagNumber = 305;
/*      */                 }
/* 1346 */                 else if (keyword.equalsIgnoreCase("Artist")) {
/* 1347 */                   tagNumber = 315;
/*      */                 }
/* 1349 */                 else if (keyword.equalsIgnoreCase("HostComputer")) {
/* 1350 */                   tagNumber = 316;
/*      */                 }
/* 1352 */                 else if (keyword.equalsIgnoreCase("InkNames")) {
/* 1353 */                   tagNumber = 333;
/*      */                 }
/* 1355 */                 else if (keyword.equalsIgnoreCase("Copyright")) {
/* 1356 */                   tagNumber = 33432;
/*      */                 }
/* 1358 */                 else if (keyword.equalsIgnoreCase("author")) {
/* 1359 */                   theAuthor = value;
/* 1360 */                 } else if (keyword.equalsIgnoreCase("description")) {
/* 1361 */                   theDescription = value;
/* 1362 */                 } else if (keyword.equalsIgnoreCase("title")) {
/* 1363 */                   theTitle = value;
/*      */                 } 
/* 1365 */                 if (tagNumber != -1) {
/* 1366 */                   TIFFField f = new TIFFField(this.rootIFD.getTag(tagNumber), 2, 1, new String[] { value });
/*      */ 
/*      */ 
/*      */                   
/* 1370 */                   this.rootIFD.addTIFFField(f);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/* 1375 */           child = child.getNextSibling();
/*      */         } 
/* 1377 */         if (theAuthor != null && 
/* 1378 */           getTIFFField(315) == null) {
/* 1379 */           TIFFField f = new TIFFField(this.rootIFD.getTag(315), 2, 1, new String[] { theAuthor });
/*      */ 
/*      */ 
/*      */           
/* 1383 */           this.rootIFD.addTIFFField(f);
/*      */         } 
/* 1385 */         if (theDescription != null && 
/* 1386 */           getTIFFField(270) == null) {
/* 1387 */           TIFFField f = new TIFFField(this.rootIFD.getTag(270), 2, 1, new String[] { theDescription });
/*      */ 
/*      */ 
/*      */           
/* 1391 */           this.rootIFD.addTIFFField(f);
/*      */         } 
/* 1393 */         if (theTitle != null && 
/* 1394 */           getTIFFField(269) == null) {
/* 1395 */           TIFFField f = new TIFFField(this.rootIFD.getTag(269), 2, 1, new String[] { theTitle });
/*      */ 
/*      */ 
/*      */           
/* 1399 */           this.rootIFD.addTIFFField(f);
/*      */         } 
/* 1401 */       } else if (name.equals("Transparency")) {
/* 1402 */         Node child = node.getFirstChild();
/* 1403 */         while (child != null) {
/* 1404 */           String childName = child.getNodeName();
/*      */           
/* 1406 */           if (childName.equals("Alpha")) {
/* 1407 */             String alpha = getAttribute(child, "value");
/*      */             
/* 1409 */             TIFFField f = null;
/* 1410 */             if (alpha.equals("premultiplied")) {
/*      */               
/* 1412 */               f = new TIFFField(this.rootIFD.getTag(338), 1);
/*      */             }
/* 1414 */             else if (alpha.equals("nonpremultiplied")) {
/*      */               
/* 1416 */               f = new TIFFField(this.rootIFD.getTag(338), 2);
/*      */             } 
/*      */             
/* 1419 */             if (f != null) {
/* 1420 */               this.rootIFD.addTIFFField(f);
/*      */             }
/*      */           } 
/*      */           
/* 1424 */           child = child.getNextSibling();
/*      */         } 
/*      */       } 
/*      */       
/* 1428 */       node = node.getNextSibling();
/*      */     } 
/*      */ 
/*      */     
/* 1432 */     if (sampleFormat != null) {
/*      */       
/* 1434 */       int sf = -1;
/* 1435 */       if (sampleFormat.equals("SignedIntegral")) {
/* 1436 */         sf = 2;
/* 1437 */       } else if (sampleFormat.equals("UnsignedIntegral")) {
/* 1438 */         sf = 1;
/* 1439 */       } else if (sampleFormat.equals("Real")) {
/* 1440 */         sf = 3;
/* 1441 */       } else if (sampleFormat.equals("Index")) {
/* 1442 */         sf = 1;
/*      */       } 
/*      */       
/* 1445 */       if (sf != -1) {
/*      */         
/* 1447 */         int count = 1;
/*      */ 
/*      */         
/* 1450 */         TIFFField f = getTIFFField(277);
/* 1451 */         if (f != null) {
/* 1452 */           count = f.getAsInt(0);
/*      */         } else {
/*      */           
/* 1455 */           f = getTIFFField(258);
/* 1456 */           if (f != null) {
/* 1457 */             count = f.getCount();
/*      */           }
/*      */         } 
/*      */         
/* 1461 */         char[] sampleFormatArray = new char[count];
/* 1462 */         Arrays.fill(sampleFormatArray, (char)sf);
/*      */ 
/*      */         
/* 1465 */         TIFFTag tag = this.rootIFD.getTag(339);
/* 1466 */         f = new TIFFField(tag, 3, sampleFormatArray.length, sampleFormatArray);
/*      */         
/* 1468 */         this.rootIFD.addTIFFField(f);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static String getAttribute(Node node, String attrName) {
/* 1474 */     NamedNodeMap attrs = node.getAttributes();
/* 1475 */     Node attr = attrs.getNamedItem(attrName);
/* 1476 */     return (attr != null) ? attr.getNodeValue() : null;
/*      */   }
/*      */   
/*      */   private Node getChildNode(Node node, String childName) {
/* 1480 */     Node childNode = null;
/* 1481 */     if (node.hasChildNodes()) {
/* 1482 */       NodeList childNodes = node.getChildNodes();
/* 1483 */       int length = childNodes.getLength();
/* 1484 */       for (int i = 0; i < length; i++) {
/* 1485 */         Node item = childNodes.item(i);
/* 1486 */         if (item.getNodeName().equals(childName)) {
/* 1487 */           childNode = item;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1492 */     return childNode;
/*      */   }
/*      */   
/*      */   public static TIFFIFD parseIFD(Node node) throws IIOInvalidTreeException {
/* 1496 */     if (!node.getNodeName().equals("TIFFIFD")) {
/* 1497 */       fatal(node, "Expected \"TIFFIFD\" node");
/*      */     }
/*      */     
/* 1500 */     String tagSetNames = getAttribute(node, "tagSets");
/* 1501 */     List<TIFFTagSet> tagSets = new ArrayList(5);
/*      */     
/* 1503 */     if (tagSetNames != null) {
/* 1504 */       StringTokenizer st = new StringTokenizer(tagSetNames, ",");
/* 1505 */       while (st.hasMoreTokens()) {
/* 1506 */         String className = st.nextToken();
/*      */         
/* 1508 */         Object o = null;
/*      */         try {
/* 1510 */           Class<?> setClass = Class.forName(className);
/*      */           
/* 1512 */           Method getInstanceMethod = setClass.getMethod("getInstance", (Class[])null);
/* 1513 */           o = getInstanceMethod.invoke(null, (Object[])null);
/* 1514 */         } catch (NoSuchMethodException e) {
/* 1515 */           throw new RuntimeException(e);
/* 1516 */         } catch (IllegalAccessException e) {
/* 1517 */           throw new RuntimeException(e);
/* 1518 */         } catch (InvocationTargetException e) {
/* 1519 */           throw new RuntimeException(e);
/* 1520 */         } catch (ClassNotFoundException e) {
/* 1521 */           throw new RuntimeException(e);
/*      */         } 
/*      */         
/* 1524 */         if (!(o instanceof TIFFTagSet)) {
/* 1525 */           fatal(node, "Specified tag set class \"" + className + "\" is not an instance of TIFFTagSet");
/*      */           
/*      */           continue;
/*      */         } 
/* 1529 */         tagSets.add((TIFFTagSet)o);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1534 */     TIFFIFD ifd = new TIFFIFD(tagSets);
/*      */     
/* 1536 */     node = node.getFirstChild();
/* 1537 */     while (node != null) {
/* 1538 */       String name = node.getNodeName();
/*      */       
/* 1540 */       TIFFField f = null;
/* 1541 */       if (name.equals("TIFFIFD")) {
/* 1542 */         int type; TIFFIFD subIFD = parseIFD(node);
/* 1543 */         String parentTagName = getAttribute(node, "parentTagName");
/* 1544 */         String parentTagNumber = getAttribute(node, "parentTagNumber");
/* 1545 */         TIFFTag tag = null;
/* 1546 */         if (parentTagName != null) {
/* 1547 */           tag = TIFFIFD.getTag(parentTagName, tagSets);
/* 1548 */         } else if (parentTagNumber != null) {
/*      */           
/* 1550 */           int tagNumber = Integer.valueOf(parentTagNumber).intValue();
/* 1551 */           tag = TIFFIFD.getTag(tagNumber, tagSets);
/*      */         } 
/*      */         
/* 1554 */         if (tag == null) {
/* 1555 */           tag = new TIFFTag("unknown", 0, 0, null);
/*      */         }
/*      */ 
/*      */         
/* 1559 */         if (tag.isDataTypeOK(13)) {
/* 1560 */           type = 13;
/*      */         } else {
/* 1562 */           type = 4;
/*      */         } 
/*      */         
/* 1565 */         f = new TIFFField(tag, type, 1, subIFD);
/* 1566 */       } else if (name.equals("TIFFField")) {
/* 1567 */         int number = Integer.parseInt(getAttribute(node, "number"));
/*      */         
/* 1569 */         TIFFTagSet tagSet = null;
/* 1570 */         Iterator<TIFFTagSet> iter = tagSets.iterator();
/* 1571 */         while (iter.hasNext()) {
/* 1572 */           TIFFTagSet t = iter.next();
/* 1573 */           if (t.getTag(number) != null) {
/* 1574 */             tagSet = t;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 1579 */         f = TIFFField.createFromMetadataNode(tagSet, node);
/*      */       } else {
/* 1581 */         fatal(node, "Expected either \"TIFFIFD\" or \"TIFFField\" node, got " + name);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1586 */       ifd.addTIFFField(f);
/* 1587 */       node = node.getNextSibling();
/*      */     } 
/*      */     
/* 1590 */     return ifd;
/*      */   }
/*      */   
/*      */   private void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/* 1594 */     Node node = root;
/* 1595 */     if (!node.getNodeName().equals("com_sun_media_imageio_plugins_tiff_image_1.0")) {
/* 1596 */       fatal(node, "Root must be com_sun_media_imageio_plugins_tiff_image_1.0");
/*      */     }
/*      */     
/* 1599 */     node = node.getFirstChild();
/* 1600 */     if (node == null || !node.getNodeName().equals("TIFFIFD")) {
/* 1601 */       fatal(root, "Root must have \"TIFFIFD\" child");
/*      */     }
/* 1603 */     TIFFIFD ifd = parseIFD(node);
/*      */     
/* 1605 */     List rootIFDTagSets = this.rootIFD.getTagSetList();
/* 1606 */     Iterator tagSetIter = ifd.getTagSetList().iterator();
/* 1607 */     while (tagSetIter.hasNext()) {
/* 1608 */       Object o = tagSetIter.next();
/* 1609 */       if (o instanceof TIFFTagSet && !rootIFDTagSets.contains(o)) {
/* 1610 */         this.rootIFD.addTagSet((TIFFTagSet)o);
/*      */       }
/*      */     } 
/*      */     
/* 1614 */     Iterator<TIFFField> ifdIter = ifd.iterator();
/* 1615 */     while (ifdIter.hasNext()) {
/* 1616 */       TIFFField field = ifdIter.next();
/* 1617 */       this.rootIFD.addTIFFField(field);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 1623 */     if (formatName.equals("com_sun_media_imageio_plugins_tiff_image_1.0")) {
/* 1624 */       if (root == null) {
/* 1625 */         throw new IllegalArgumentException("root == null!");
/*      */       }
/* 1627 */       mergeNativeTree(root);
/* 1628 */     } else if (formatName
/* 1629 */       .equals("javax_imageio_1.0")) {
/* 1630 */       if (root == null) {
/* 1631 */         throw new IllegalArgumentException("root == null!");
/*      */       }
/* 1633 */       mergeStandardTree(root);
/*      */     } else {
/* 1635 */       throw new IllegalArgumentException("Not a recognized format!");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void reset() {
/* 1640 */     this.rootIFD = new TIFFIFD(this.tagSets);
/*      */   }
/*      */   
/*      */   public TIFFIFD getRootIFD() {
/* 1644 */     return this.rootIFD;
/*      */   }
/*      */   
/*      */   public TIFFField getTIFFField(int tagNumber) {
/* 1648 */     return this.rootIFD.getTIFFField(tagNumber);
/*      */   }
/*      */   
/*      */   public void removeTIFFField(int tagNumber) {
/* 1652 */     this.rootIFD.removeTIFFField(tagNumber);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TIFFImageMetadata getShallowClone() {
/* 1661 */     return new TIFFImageMetadata(this.rootIFD.getShallowClone());
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFImageMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */