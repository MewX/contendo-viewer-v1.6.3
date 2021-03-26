/*      */ package com.github.jaiimageio.impl.plugins.bmp;
/*      */ 
/*      */ import com.github.jaiimageio.impl.common.ImageUtil;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
/*      */ import javax.imageio.ImageWriteParam;
/*      */ import javax.imageio.metadata.IIOInvalidTreeException;
/*      */ import javax.imageio.metadata.IIOMetadata;
/*      */ import javax.imageio.metadata.IIOMetadataNode;
/*      */ import org.w3c.dom.Node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BMPMetadata
/*      */   extends IIOMetadata
/*      */   implements BMPConstants, Cloneable
/*      */ {
/*      */   public static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_bmp_image_1.0";
/*      */   public String bmpVersion;
/*      */   public int width;
/*      */   public int height;
/*      */   public short bitsPerPixel;
/*      */   public int compression;
/*      */   public int imageSize;
/*      */   public int xPixelsPerMeter;
/*      */   public int yPixelsPerMeter;
/*      */   public int colorsUsed;
/*      */   public int colorsImportant;
/*      */   public int redMask;
/*      */   public int greenMask;
/*      */   public int blueMask;
/*      */   public int alphaMask;
/*      */   public int colorSpace;
/*      */   public double redX;
/*      */   public double redY;
/*      */   public double redZ;
/*      */   public double greenX;
/*      */   public double greenY;
/*      */   public double greenZ;
/*      */   public double blueX;
/*      */   public double blueY;
/*      */   public double blueZ;
/*      */   public int gammaRed;
/*      */   public int gammaGreen;
/*      */   public int gammaBlue;
/*      */   public int intent;
/*  119 */   public byte[] palette = null;
/*      */   
/*      */   public int paletteSize;
/*      */   
/*      */   public int red;
/*      */   
/*      */   public int green;
/*      */   public int blue;
/*  127 */   public List comments = null;
/*      */   
/*      */   public BMPMetadata() {
/*  130 */     super(true, "com_sun_media_imageio_plugins_bmp_image_1.0", "com.github.jaiimageio.impl.bmp.BMPMetadataFormat", (String[])null, (String[])null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BMPMetadata(IIOMetadata metadata) throws IIOInvalidTreeException {
/*  139 */     this();
/*      */     
/*  141 */     if (metadata != null) {
/*  142 */       List<String> formats = Arrays.asList(metadata.getMetadataFormatNames());
/*      */       
/*  144 */       if (formats.contains("com_sun_media_imageio_plugins_bmp_image_1.0")) {
/*      */         
/*  146 */         setFromTree("com_sun_media_imageio_plugins_bmp_image_1.0", metadata
/*  147 */             .getAsTree("com_sun_media_imageio_plugins_bmp_image_1.0"));
/*  148 */       } else if (metadata.isStandardMetadataFormatSupported()) {
/*      */         
/*  150 */         String format = "javax_imageio_1.0";
/*      */         
/*  152 */         setFromTree(format, metadata.getAsTree(format));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() {
/*  158 */     return false;
/*      */   }
/*      */   
/*      */   public Object clone() {
/*      */     BMPMetadata metadata;
/*      */     try {
/*  164 */       metadata = (BMPMetadata)super.clone();
/*  165 */     } catch (CloneNotSupportedException e) {
/*  166 */       return null;
/*      */     } 
/*      */     
/*  169 */     return metadata;
/*      */   }
/*      */   
/*      */   public Node getAsTree(String formatName) {
/*  173 */     if (formatName.equals("com_sun_media_imageio_plugins_bmp_image_1.0"))
/*  174 */       return getNativeTree(); 
/*  175 */     if (formatName
/*  176 */       .equals("javax_imageio_1.0")) {
/*  177 */       return getStandardTree();
/*      */     }
/*  179 */     throw new IllegalArgumentException(I18N.getString("BMPMetadata0"));
/*      */   }
/*      */ 
/*      */   
/*      */   private Node getNativeTree() {
/*  184 */     IIOMetadataNode root = new IIOMetadataNode("com_sun_media_imageio_plugins_bmp_image_1.0");
/*      */ 
/*      */     
/*  187 */     addChildNode(root, "BMPVersion", this.bmpVersion);
/*  188 */     addChildNode(root, "Width", new Integer(this.width));
/*  189 */     addChildNode(root, "Height", new Integer(this.height));
/*  190 */     addChildNode(root, "BitsPerPixel", new Short(this.bitsPerPixel));
/*  191 */     addChildNode(root, "Compression", new Integer(this.compression));
/*  192 */     addChildNode(root, "ImageSize", new Integer(this.imageSize));
/*      */ 
/*      */     
/*  195 */     if (this.xPixelsPerMeter > 0 && this.yPixelsPerMeter > 0) {
/*  196 */       IIOMetadataNode node = addChildNode(root, "PixelsPerMeter", (Object)null);
/*  197 */       addChildNode(node, "X", new Integer(this.xPixelsPerMeter));
/*  198 */       addChildNode(node, "Y", new Integer(this.yPixelsPerMeter));
/*      */     } 
/*      */     
/*  201 */     addChildNode(root, "ColorsUsed", new Integer(this.colorsUsed));
/*  202 */     addChildNode(root, "ColorsImportant", new Integer(this.colorsImportant));
/*      */     
/*  204 */     int version = 0;
/*  205 */     for (int i = 0; i < this.bmpVersion.length(); i++) {
/*  206 */       if (Character.isDigit(this.bmpVersion.charAt(i)))
/*  207 */         version = this.bmpVersion.charAt(i) - 48; 
/*      */     } 
/*  209 */     if (version >= 4) {
/*  210 */       IIOMetadataNode node = addChildNode(root, "Mask", (Object)null);
/*  211 */       addChildNode(node, "Red", new Integer(this.redMask));
/*  212 */       addChildNode(node, "Green", new Integer(this.greenMask));
/*  213 */       addChildNode(node, "Blue", new Integer(this.blueMask));
/*  214 */       addChildNode(node, "Alpha", new Integer(this.alphaMask));
/*      */       
/*  216 */       addChildNode(root, "ColorSpaceType", new Integer(this.colorSpace));
/*      */       
/*  218 */       node = addChildNode(root, "CIEXYZEndpoints", (Object)null);
/*  219 */       addXYZPoints(node, "Red", this.redX, this.redY, this.redZ);
/*  220 */       addXYZPoints(node, "Green", this.greenX, this.greenY, this.greenZ);
/*  221 */       addXYZPoints(node, "Blue", this.blueX, this.blueY, this.blueZ);
/*      */       
/*  223 */       node = addChildNode(root, "Gamma", (Object)null);
/*  224 */       addChildNode(node, "Red", new Integer(this.gammaRed));
/*  225 */       addChildNode(node, "Green", new Integer(this.gammaGreen));
/*  226 */       addChildNode(node, "Blue", new Integer(this.gammaBlue));
/*      */       
/*  228 */       node = addChildNode(root, "Intent", new Integer(this.intent));
/*      */     } 
/*      */ 
/*      */     
/*  232 */     if (this.palette != null && this.paletteSize > 0) {
/*  233 */       IIOMetadataNode node = addChildNode(root, "Palette", (Object)null);
/*      */       
/*  235 */       boolean isVersion2 = (this.bmpVersion != null && this.bmpVersion.equals("BMP v. 2.x"));
/*      */       
/*  237 */       for (int k = 0, j = 0; k < this.paletteSize; k++) {
/*      */         
/*  239 */         IIOMetadataNode entry = addChildNode(node, "PaletteEntry", (Object)null);
/*  240 */         this.blue = this.palette[j++] & 0xFF;
/*  241 */         this.green = this.palette[j++] & 0xFF;
/*  242 */         this.red = this.palette[j++] & 0xFF;
/*  243 */         addChildNode(entry, "Red", new Integer(this.red));
/*  244 */         addChildNode(entry, "Green", new Integer(this.green));
/*  245 */         addChildNode(entry, "Blue", new Integer(this.blue));
/*  246 */         if (!isVersion2) j++;
/*      */       
/*      */       } 
/*      */     } 
/*  250 */     return root;
/*      */   }
/*      */ 
/*      */   
/*      */   protected IIOMetadataNode getStandardChromaNode() {
/*      */     String colorSpaceType, numChannels;
/*  256 */     IIOMetadataNode node = new IIOMetadataNode("Chroma");
/*      */     
/*  258 */     IIOMetadataNode subNode = new IIOMetadataNode("ColorSpaceType");
/*      */     
/*  260 */     if ((this.palette != null && this.paletteSize > 0) || this.redMask != 0 || this.greenMask != 0 || this.blueMask != 0 || this.bitsPerPixel > 8) {
/*      */ 
/*      */       
/*  263 */       colorSpaceType = "RGB";
/*      */     } else {
/*  265 */       colorSpaceType = "GRAY";
/*      */     } 
/*  267 */     subNode.setAttribute("name", colorSpaceType);
/*  268 */     node.appendChild(subNode);
/*      */     
/*  270 */     subNode = new IIOMetadataNode("NumChannels");
/*      */     
/*  272 */     if ((this.palette != null && this.paletteSize > 0) || this.redMask != 0 || this.greenMask != 0 || this.blueMask != 0 || this.bitsPerPixel > 8) {
/*      */ 
/*      */       
/*  275 */       if (this.alphaMask != 0) {
/*  276 */         numChannels = "4";
/*      */       } else {
/*  278 */         numChannels = "3";
/*      */       } 
/*      */     } else {
/*  281 */       numChannels = "1";
/*      */     } 
/*  283 */     subNode.setAttribute("value", numChannels);
/*  284 */     node.appendChild(subNode);
/*      */     
/*  286 */     if (this.gammaRed != 0 && this.gammaGreen != 0 && this.gammaBlue != 0) {
/*  287 */       subNode = new IIOMetadataNode("Gamma");
/*  288 */       Double gamma = new Double((this.gammaRed + this.gammaGreen + this.gammaBlue) / 3.0D);
/*  289 */       subNode.setAttribute("value", gamma.toString());
/*  290 */       node.appendChild(subNode);
/*      */     } 
/*      */     
/*  293 */     if (numChannels.equals("1") && (this.palette == null || this.paletteSize == 0)) {
/*      */       
/*  295 */       subNode = new IIOMetadataNode("BlackIsZero");
/*  296 */       subNode.setAttribute("value", "TRUE");
/*  297 */       node.appendChild(subNode);
/*      */     } 
/*      */     
/*  300 */     if (this.palette != null && this.paletteSize > 0) {
/*  301 */       subNode = new IIOMetadataNode("Palette");
/*      */       
/*  303 */       boolean isVersion2 = (this.bmpVersion != null && this.bmpVersion.equals("BMP v. 2.x"));
/*      */       
/*  305 */       for (int i = 0, j = 0; i < this.paletteSize; i++) {
/*  306 */         IIOMetadataNode subNode1 = new IIOMetadataNode("PaletteEntry");
/*      */         
/*  308 */         subNode1.setAttribute("index", "" + i);
/*  309 */         subNode1.setAttribute("blue", "" + (this.palette[j++] & 0xFF));
/*  310 */         subNode1.setAttribute("green", "" + (this.palette[j++] & 0xFF));
/*  311 */         subNode1.setAttribute("red", "" + (this.palette[j++] & 0xFF));
/*  312 */         if (!isVersion2) j++; 
/*  313 */         subNode.appendChild(subNode1);
/*      */       } 
/*  315 */       node.appendChild(subNode);
/*      */     } 
/*      */     
/*  318 */     return node;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardCompressionNode() {
/*  322 */     IIOMetadataNode node = new IIOMetadataNode("Compression");
/*      */ 
/*      */     
/*  325 */     IIOMetadataNode subNode = new IIOMetadataNode("CompressionTypeName");
/*  326 */     subNode.setAttribute("value", compressionTypeNames[this.compression]);
/*  327 */     node.appendChild(subNode);
/*      */     
/*  329 */     subNode = new IIOMetadataNode("Lossless");
/*  330 */     subNode.setAttribute("value", (this.compression == 4) ? "FALSE" : "TRUE");
/*      */     
/*  332 */     node.appendChild(subNode);
/*      */     
/*  334 */     return node;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardDataNode() {
/*  338 */     IIOMetadataNode node = new IIOMetadataNode("Data");
/*      */     
/*  340 */     String sampleFormat = (this.palette != null && this.paletteSize > 0) ? "Index" : "UnsignedIntegral";
/*      */     
/*  342 */     IIOMetadataNode subNode = new IIOMetadataNode("SampleFormat");
/*  343 */     subNode.setAttribute("value", sampleFormat);
/*  344 */     node.appendChild(subNode);
/*      */     
/*  346 */     String bits = "";
/*  347 */     if (this.redMask != 0 || this.greenMask != 0 || this.blueMask != 0) {
/*      */ 
/*      */ 
/*      */       
/*  351 */       bits = countBits(this.redMask) + " " + countBits(this.greenMask) + " " + countBits(this.blueMask);
/*  352 */       if (this.alphaMask != 0) {
/*  353 */         bits = bits + " " + countBits(this.alphaMask);
/*      */       }
/*  355 */     } else if (this.palette != null && this.paletteSize > 0) {
/*  356 */       for (int i = 1; i <= 3; i++) {
/*  357 */         bits = bits + this.bitsPerPixel;
/*  358 */         if (i != 3) {
/*  359 */           bits = bits + " ";
/*      */         }
/*      */       }
/*      */     
/*  363 */     } else if (this.bitsPerPixel == 1) {
/*  364 */       bits = "1";
/*  365 */     } else if (this.bitsPerPixel == 4) {
/*  366 */       bits = "4";
/*  367 */     } else if (this.bitsPerPixel == 8) {
/*  368 */       bits = "8";
/*  369 */     } else if (this.bitsPerPixel == 16) {
/*  370 */       bits = "5 6 5";
/*  371 */     } else if (this.bitsPerPixel == 24) {
/*  372 */       bits = "8 8 8";
/*  373 */     } else if (this.bitsPerPixel == 32) {
/*  374 */       bits = "8 8 8 8";
/*      */     } 
/*      */ 
/*      */     
/*  378 */     if (!bits.equals("")) {
/*  379 */       subNode = new IIOMetadataNode("BitsPerSample");
/*  380 */       subNode.setAttribute("value", bits);
/*  381 */       node.appendChild(subNode);
/*      */     } 
/*      */     
/*  384 */     return node;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardDimensionNode() {
/*  388 */     if (this.yPixelsPerMeter > 0 && this.xPixelsPerMeter > 0) {
/*  389 */       IIOMetadataNode node = new IIOMetadataNode("Dimension");
/*  390 */       float ratio = this.yPixelsPerMeter / this.xPixelsPerMeter;
/*  391 */       IIOMetadataNode subNode = new IIOMetadataNode("PixelAspectRatio");
/*  392 */       subNode.setAttribute("value", "" + ratio);
/*  393 */       node.appendChild(subNode);
/*      */       
/*  395 */       subNode = new IIOMetadataNode("HorizontalPixelSize");
/*  396 */       subNode.setAttribute("value", "" + (1000.0F / this.xPixelsPerMeter));
/*  397 */       node.appendChild(subNode);
/*      */       
/*  399 */       subNode = new IIOMetadataNode("VerticalPixelSize");
/*  400 */       subNode.setAttribute("value", "" + (1000.0F / this.yPixelsPerMeter));
/*  401 */       node.appendChild(subNode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  407 */       subNode = new IIOMetadataNode("HorizontalPhysicalPixelSpacing");
/*  408 */       subNode.setAttribute("value", "" + (1000.0F / this.xPixelsPerMeter));
/*  409 */       node.appendChild(subNode);
/*      */       
/*  411 */       subNode = new IIOMetadataNode("VerticalPhysicalPixelSpacing");
/*  412 */       subNode.setAttribute("value", "" + (1000.0F / this.yPixelsPerMeter));
/*  413 */       node.appendChild(subNode);
/*      */       
/*  415 */       return node;
/*      */     } 
/*  417 */     return null;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardDocumentNode() {
/*  421 */     if (this.bmpVersion != null) {
/*  422 */       IIOMetadataNode node = new IIOMetadataNode("Document");
/*  423 */       IIOMetadataNode subNode = new IIOMetadataNode("FormatVersion");
/*  424 */       subNode.setAttribute("value", this.bmpVersion);
/*  425 */       node.appendChild(subNode);
/*  426 */       return node;
/*      */     } 
/*  428 */     return null;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardTextNode() {
/*  432 */     if (this.comments != null) {
/*  433 */       IIOMetadataNode node = new IIOMetadataNode("Text");
/*  434 */       Iterator<String> iter = this.comments.iterator();
/*  435 */       while (iter.hasNext()) {
/*  436 */         String comment = iter.next();
/*  437 */         IIOMetadataNode subNode = new IIOMetadataNode("TextEntry");
/*  438 */         subNode.setAttribute("keyword", "comment");
/*  439 */         subNode.setAttribute("value", comment);
/*  440 */         node.appendChild(subNode);
/*      */       } 
/*  442 */       return node;
/*      */     } 
/*  444 */     return null;
/*      */   }
/*      */   protected IIOMetadataNode getStandardTransparencyNode() {
/*      */     String alpha;
/*  448 */     IIOMetadataNode node = new IIOMetadataNode("Transparency");
/*  449 */     IIOMetadataNode subNode = new IIOMetadataNode("Alpha");
/*      */     
/*  451 */     if (this.alphaMask != 0) {
/*  452 */       alpha = "nonpremultiplied";
/*      */     } else {
/*  454 */       alpha = "none";
/*      */     } 
/*      */     
/*  457 */     subNode.setAttribute("value", alpha);
/*  458 */     node.appendChild(subNode);
/*  459 */     return node;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void fatal(Node node, String reason) throws IIOInvalidTreeException {
/*  465 */     throw new IIOInvalidTreeException(reason, node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getIntAttribute(Node node, String name, int defaultValue, boolean required) throws IIOInvalidTreeException {
/*  472 */     String value = getAttribute(node, name, (String)null, required);
/*  473 */     if (value == null) {
/*  474 */       return defaultValue;
/*      */     }
/*  476 */     return Integer.parseInt(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double getDoubleAttribute(Node node, String name, double defaultValue, boolean required) throws IIOInvalidTreeException {
/*  483 */     String value = getAttribute(node, name, (String)null, required);
/*  484 */     if (value == null) {
/*  485 */       return defaultValue;
/*      */     }
/*  487 */     return Double.parseDouble(value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getIntAttribute(Node node, String name) throws IIOInvalidTreeException {
/*  493 */     return getIntAttribute(node, name, -1, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private double getDoubleAttribute(Node node, String name) throws IIOInvalidTreeException {
/*  499 */     return getDoubleAttribute(node, name, -1.0D, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getAttribute(Node node, String name, String defaultValue, boolean required) throws IIOInvalidTreeException {
/*  506 */     Node attr = node.getAttributes().getNamedItem(name);
/*  507 */     if (attr == null) {
/*  508 */       if (!required) {
/*  509 */         return defaultValue;
/*      */       }
/*  511 */       fatal(node, "Required attribute " + name + " not present!");
/*      */     } 
/*      */     
/*  514 */     return attr.getNodeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String getAttribute(Node node, String name) throws IIOInvalidTreeException {
/*  520 */     return getAttribute(node, name, (String)null, true);
/*      */   }
/*      */ 
/*      */   
/*      */   void initialize(ColorModel cm, SampleModel sm, ImageWriteParam param) {
/*  525 */     if (param != null) {
/*  526 */       this.bmpVersion = "BMP v. 3.x";
/*      */       
/*  528 */       if (param.getCompressionMode() == 2) {
/*  529 */         String compressionType = param.getCompressionType();
/*  530 */         this
/*  531 */           .compression = BMPImageWriter.getCompressionType(compressionType);
/*      */       } 
/*      */     } else {
/*  534 */       this.bmpVersion = "BMP v. 3.x";
/*  535 */       this.compression = BMPImageWriter.getPreferredCompressionType(cm, sm);
/*      */     } 
/*      */ 
/*      */     
/*  539 */     this.width = sm.getWidth();
/*  540 */     this.height = sm.getHeight();
/*      */ 
/*      */     
/*  543 */     this.bitsPerPixel = (short)cm.getPixelSize();
/*      */ 
/*      */     
/*  546 */     if (cm instanceof DirectColorModel) {
/*  547 */       DirectColorModel dcm = (DirectColorModel)cm;
/*  548 */       this.redMask = dcm.getRedMask();
/*  549 */       this.greenMask = dcm.getGreenMask();
/*  550 */       this.blueMask = dcm.getBlueMask();
/*  551 */       this.alphaMask = dcm.getAlphaMask();
/*      */     } 
/*      */ 
/*      */     
/*  555 */     if (cm instanceof IndexColorModel) {
/*  556 */       IndexColorModel icm = (IndexColorModel)cm;
/*  557 */       this.paletteSize = icm.getMapSize();
/*      */       
/*  559 */       byte[] r = new byte[this.paletteSize];
/*  560 */       byte[] g = new byte[this.paletteSize];
/*  561 */       byte[] b = new byte[this.paletteSize];
/*      */       
/*  563 */       icm.getReds(r);
/*  564 */       icm.getGreens(g);
/*  565 */       icm.getBlues(b);
/*      */ 
/*      */       
/*  568 */       boolean isVersion2 = (this.bmpVersion != null && this.bmpVersion.equals("BMP v. 2.x"));
/*      */       
/*  570 */       this.palette = new byte[(isVersion2 ? 3 : 4) * this.paletteSize];
/*  571 */       for (int i = 0, j = 0; i < this.paletteSize; i++) {
/*  572 */         this.palette[j++] = b[i];
/*  573 */         this.palette[j++] = g[i];
/*  574 */         this.palette[j++] = r[i];
/*  575 */         if (!isVersion2) j++;
/*      */       
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/*  582 */     if (formatName.equals("com_sun_media_imageio_plugins_bmp_image_1.0")) {
/*  583 */       if (root == null) {
/*  584 */         throw new IllegalArgumentException("root == null!");
/*      */       }
/*  586 */       mergeNativeTree(root);
/*  587 */     } else if (formatName
/*  588 */       .equals("javax_imageio_1.0")) {
/*  589 */       if (root == null) {
/*  590 */         throw new IllegalArgumentException("root == null!");
/*      */       }
/*  592 */       mergeStandardTree(root);
/*      */     } else {
/*  594 */       throw new IllegalArgumentException("Not a recognized format!");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/*  600 */     Node node = root;
/*  601 */     if (!node.getNodeName().equals("com_sun_media_imageio_plugins_bmp_image_1.0")) {
/*  602 */       fatal(node, "Root must be com_sun_media_imageio_plugins_bmp_image_1.0");
/*      */     }
/*      */     
/*  605 */     byte[] r = null, g = null, b = null;
/*  606 */     int maxIndex = -1;
/*      */     
/*  608 */     node = node.getFirstChild();
/*  609 */     while (node != null) {
/*  610 */       String name = node.getNodeName();
/*      */       
/*  612 */       if (name.equals("BMPVersion")) {
/*  613 */         String value = getStringValue(node);
/*  614 */         if (value != null) this.bmpVersion = value; 
/*  615 */       } else if (name.equals("Width")) {
/*  616 */         Integer value = getIntegerValue(node);
/*  617 */         if (value != null) this.width = value.intValue(); 
/*  618 */       } else if (name.equals("Height")) {
/*  619 */         Integer value = getIntegerValue(node);
/*  620 */         if (value != null) this.height = value.intValue(); 
/*  621 */       } else if (name.equals("BitsPerPixel")) {
/*  622 */         Short value = getShortValue(node);
/*  623 */         if (value != null) this.bitsPerPixel = value.shortValue(); 
/*  624 */       } else if (name.equals("Compression")) {
/*  625 */         Integer value = getIntegerValue(node);
/*  626 */         if (value != null) this.compression = value.intValue(); 
/*  627 */       } else if (name.equals("ImageSize")) {
/*  628 */         Integer value = getIntegerValue(node);
/*  629 */         if (value != null) this.imageSize = value.intValue(); 
/*  630 */       } else if (name.equals("PixelsPerMeter")) {
/*  631 */         Node subNode = node.getFirstChild();
/*  632 */         while (subNode != null) {
/*  633 */           String subName = subNode.getNodeName();
/*  634 */           if (subName.equals("X")) {
/*  635 */             Integer value = getIntegerValue(subNode);
/*  636 */             if (value != null)
/*  637 */               this.xPixelsPerMeter = value.intValue(); 
/*  638 */           } else if (subName.equals("Y")) {
/*  639 */             Integer value = getIntegerValue(subNode);
/*  640 */             if (value != null)
/*  641 */               this.yPixelsPerMeter = value.intValue(); 
/*      */           } 
/*  643 */           subNode = subNode.getNextSibling();
/*      */         } 
/*  645 */       } else if (name.equals("ColorsUsed")) {
/*  646 */         Integer value = getIntegerValue(node);
/*  647 */         if (value != null) this.colorsUsed = value.intValue(); 
/*  648 */       } else if (name.equals("ColorsImportant")) {
/*  649 */         Integer value = getIntegerValue(node);
/*  650 */         if (value != null) this.colorsImportant = value.intValue(); 
/*  651 */       } else if (name.equals("Mask")) {
/*  652 */         Node subNode = node.getFirstChild();
/*  653 */         while (subNode != null) {
/*  654 */           String subName = subNode.getNodeName();
/*  655 */           if (subName.equals("Red")) {
/*  656 */             Integer value = getIntegerValue(subNode);
/*  657 */             if (value != null)
/*  658 */               this.redMask = value.intValue(); 
/*  659 */           } else if (subName.equals("Green")) {
/*  660 */             Integer value = getIntegerValue(subNode);
/*  661 */             if (value != null)
/*  662 */               this.greenMask = value.intValue(); 
/*  663 */           } else if (subName.equals("Blue")) {
/*  664 */             Integer value = getIntegerValue(subNode);
/*  665 */             if (value != null)
/*  666 */               this.blueMask = value.intValue(); 
/*  667 */           } else if (subName.equals("Alpha")) {
/*  668 */             Integer value = getIntegerValue(subNode);
/*  669 */             if (value != null)
/*  670 */               this.alphaMask = value.intValue(); 
/*      */           } 
/*  672 */           subNode = subNode.getNextSibling();
/*      */         } 
/*  674 */       } else if (name.equals("ColorSpace")) {
/*  675 */         Integer value = getIntegerValue(node);
/*  676 */         if (value != null) this.colorSpace = value.intValue(); 
/*  677 */       } else if (name.equals("CIEXYZEndpoints")) {
/*  678 */         Node subNode = node.getFirstChild();
/*  679 */         while (subNode != null) {
/*  680 */           String subName = subNode.getNodeName();
/*  681 */           if (subName.equals("Red")) {
/*  682 */             Node subNode1 = subNode.getFirstChild();
/*  683 */             while (subNode1 != null) {
/*  684 */               String subName1 = subNode1.getNodeName();
/*  685 */               if (subName1.equals("X")) {
/*  686 */                 Double value = getDoubleValue(subNode1);
/*  687 */                 if (value != null)
/*  688 */                   this.redX = value.doubleValue(); 
/*  689 */               } else if (subName1.equals("Y")) {
/*  690 */                 Double value = getDoubleValue(subNode1);
/*  691 */                 if (value != null)
/*  692 */                   this.redY = value.doubleValue(); 
/*  693 */               } else if (subName1.equals("Z")) {
/*  694 */                 Double value = getDoubleValue(subNode1);
/*  695 */                 if (value != null)
/*  696 */                   this.redZ = value.doubleValue(); 
/*      */               } 
/*  698 */               subNode1 = subNode1.getNextSibling();
/*      */             } 
/*  700 */           } else if (subName.equals("Green")) {
/*  701 */             Node subNode1 = subNode.getFirstChild();
/*  702 */             while (subNode1 != null) {
/*  703 */               String subName1 = subNode1.getNodeName();
/*  704 */               if (subName1.equals("X")) {
/*  705 */                 Double value = getDoubleValue(subNode1);
/*  706 */                 if (value != null)
/*  707 */                   this.greenX = value.doubleValue(); 
/*  708 */               } else if (subName1.equals("Y")) {
/*  709 */                 Double value = getDoubleValue(subNode1);
/*  710 */                 if (value != null)
/*  711 */                   this.greenY = value.doubleValue(); 
/*  712 */               } else if (subName1.equals("Z")) {
/*  713 */                 Double value = getDoubleValue(subNode1);
/*  714 */                 if (value != null)
/*  715 */                   this.greenZ = value.doubleValue(); 
/*      */               } 
/*  717 */               subNode1 = subNode1.getNextSibling();
/*      */             } 
/*  719 */           } else if (subName.equals("Blue")) {
/*  720 */             Node subNode1 = subNode.getFirstChild();
/*  721 */             while (subNode1 != null) {
/*  722 */               String subName1 = subNode1.getNodeName();
/*  723 */               if (subName1.equals("X")) {
/*  724 */                 Double value = getDoubleValue(subNode1);
/*  725 */                 if (value != null)
/*  726 */                   this.blueX = value.doubleValue(); 
/*  727 */               } else if (subName1.equals("Y")) {
/*  728 */                 Double value = getDoubleValue(subNode1);
/*  729 */                 if (value != null)
/*  730 */                   this.blueY = value.doubleValue(); 
/*  731 */               } else if (subName1.equals("Z")) {
/*  732 */                 Double value = getDoubleValue(subNode1);
/*  733 */                 if (value != null)
/*  734 */                   this.blueZ = value.doubleValue(); 
/*      */               } 
/*  736 */               subNode1 = subNode1.getNextSibling();
/*      */             } 
/*      */           } 
/*  739 */           subNode = subNode.getNextSibling();
/*      */         } 
/*  741 */       } else if (name.equals("Gamma")) {
/*  742 */         Node subNode = node.getFirstChild();
/*  743 */         while (subNode != null) {
/*  744 */           String subName = subNode.getNodeName();
/*  745 */           if (subName.equals("Red")) {
/*  746 */             Integer value = getIntegerValue(subNode);
/*  747 */             if (value != null)
/*  748 */               this.gammaRed = value.intValue(); 
/*  749 */           } else if (subName.equals("Green")) {
/*  750 */             Integer value = getIntegerValue(subNode);
/*  751 */             if (value != null)
/*  752 */               this.gammaGreen = value.intValue(); 
/*  753 */           } else if (subName.equals("Blue")) {
/*  754 */             Integer value = getIntegerValue(subNode);
/*  755 */             if (value != null)
/*  756 */               this.gammaBlue = value.intValue(); 
/*      */           } 
/*  758 */           subNode = subNode.getNextSibling();
/*      */         } 
/*  760 */       } else if (name.equals("Intent")) {
/*  761 */         Integer value = getIntegerValue(node);
/*  762 */         if (value != null) this.intent = value.intValue(); 
/*  763 */       } else if (name.equals("Palette")) {
/*  764 */         this.paletteSize = getIntAttribute(node, "sizeOfPalette");
/*      */         
/*  766 */         r = new byte[this.paletteSize];
/*  767 */         g = new byte[this.paletteSize];
/*  768 */         b = new byte[this.paletteSize];
/*  769 */         maxIndex = -1;
/*      */         
/*  771 */         Node paletteEntry = node.getFirstChild();
/*  772 */         if (paletteEntry == null) {
/*  773 */           fatal(node, "Palette has no entries!");
/*      */         }
/*      */         
/*  776 */         int numPaletteEntries = 0;
/*  777 */         while (paletteEntry != null) {
/*  778 */           if (!paletteEntry.getNodeName().equals("PaletteEntry")) {
/*  779 */             fatal(node, "Only a PaletteEntry may be a child of a Palette!");
/*      */           }
/*      */ 
/*      */           
/*  783 */           int index = -1;
/*  784 */           Node subNode = paletteEntry.getFirstChild();
/*  785 */           while (subNode != null) {
/*  786 */             String subName = subNode.getNodeName();
/*  787 */             if (subName.equals("Index")) {
/*  788 */               Integer value = getIntegerValue(subNode);
/*  789 */               if (value != null)
/*  790 */                 index = value.intValue(); 
/*  791 */               if (index < 0 || index > this.paletteSize - 1) {
/*  792 */                 fatal(node, "Bad value for PaletteEntry attribute index!");
/*      */               }
/*      */             }
/*  795 */             else if (subName.equals("Red")) {
/*  796 */               Integer value = getIntegerValue(subNode);
/*  797 */               if (value != null)
/*  798 */                 this.red = value.intValue(); 
/*  799 */             } else if (subName.equals("Green")) {
/*  800 */               Integer value = getIntegerValue(subNode);
/*  801 */               if (value != null)
/*  802 */                 this.green = value.intValue(); 
/*  803 */             } else if (subName.equals("Blue")) {
/*  804 */               Integer value = getIntegerValue(subNode);
/*  805 */               if (value != null)
/*  806 */                 this.blue = value.intValue(); 
/*      */             } 
/*  808 */             subNode = subNode.getNextSibling();
/*      */           } 
/*      */           
/*  811 */           if (index == -1) {
/*  812 */             index = numPaletteEntries;
/*      */           }
/*  814 */           if (index > maxIndex) {
/*  815 */             maxIndex = index;
/*      */           }
/*      */           
/*  818 */           r[index] = (byte)this.red;
/*  819 */           g[index] = (byte)this.green;
/*  820 */           b[index] = (byte)this.blue;
/*      */           
/*  822 */           numPaletteEntries++;
/*  823 */           paletteEntry = paletteEntry.getNextSibling();
/*      */         } 
/*  825 */       } else if (name.equals("CommentExtensions")) {
/*      */         
/*  827 */         Node commentExtension = node.getFirstChild();
/*  828 */         if (commentExtension == null) {
/*  829 */           fatal(node, "CommentExtensions has no entries!");
/*      */         }
/*      */         
/*  832 */         if (this.comments == null) {
/*  833 */           this.comments = new ArrayList();
/*      */         }
/*      */         
/*  836 */         while (commentExtension != null) {
/*  837 */           if (!commentExtension.getNodeName().equals("CommentExtension")) {
/*  838 */             fatal(node, "Only a CommentExtension may be a child of a CommentExtensions!");
/*      */           }
/*      */ 
/*      */           
/*  842 */           this.comments.add(getAttribute(commentExtension, "value"));
/*      */           
/*  844 */           commentExtension = commentExtension.getNextSibling();
/*      */         } 
/*      */       } else {
/*  847 */         fatal(node, "Unknown child of root node!");
/*      */       } 
/*      */       
/*  850 */       node = node.getNextSibling();
/*      */     } 
/*      */     
/*  853 */     if (r != null && g != null && b != null) {
/*      */       
/*  855 */       boolean isVersion2 = (this.bmpVersion != null && this.bmpVersion.equals("BMP v. 2.x"));
/*      */       
/*  857 */       int numEntries = maxIndex + 1;
/*  858 */       this.palette = new byte[(isVersion2 ? 3 : 4) * numEntries];
/*  859 */       for (int i = 0, j = 0; i < numEntries; i++) {
/*  860 */         this.palette[j++] = b[i];
/*  861 */         this.palette[j++] = g[i];
/*  862 */         this.palette[j++] = r[i];
/*  863 */         if (!isVersion2) j++;
/*      */       
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/*  870 */     Node node = root;
/*      */     
/*  872 */     if (!node.getNodeName().equals("javax_imageio_1.0")) {
/*  873 */       fatal(node, "Root must be javax_imageio_1.0");
/*      */     }
/*      */ 
/*      */     
/*  877 */     String colorSpaceType = null;
/*  878 */     int numChannels = 0;
/*  879 */     int[] bitsPerSample = null;
/*  880 */     boolean hasAlpha = false;
/*      */     
/*  882 */     byte[] r = null, g = null, b = null;
/*  883 */     int maxIndex = -1;
/*      */     
/*  885 */     node = node.getFirstChild();
/*  886 */     while (node != null) {
/*  887 */       String name = node.getNodeName();
/*      */       
/*  889 */       if (name.equals("Chroma")) {
/*  890 */         Node child = node.getFirstChild();
/*  891 */         while (child != null) {
/*  892 */           String childName = child.getNodeName();
/*  893 */           if (childName.equals("ColorSpaceType")) {
/*  894 */             colorSpaceType = getAttribute(child, "name");
/*  895 */           } else if (childName.equals("NumChannels")) {
/*  896 */             numChannels = getIntAttribute(child, "value");
/*  897 */           } else if (childName.equals("Gamma")) {
/*  898 */             this
/*  899 */               .gammaRed = this.gammaGreen = this.gammaBlue = (int)(getDoubleAttribute(child, "value") + 0.5D);
/*  900 */           } else if (childName.equals("Palette")) {
/*  901 */             r = new byte[256];
/*  902 */             g = new byte[256];
/*  903 */             b = new byte[256];
/*  904 */             maxIndex = -1;
/*      */             
/*  906 */             Node paletteEntry = child.getFirstChild();
/*  907 */             if (paletteEntry == null) {
/*  908 */               fatal(node, "Palette has no entries!");
/*      */             }
/*      */             
/*  911 */             while (paletteEntry != null) {
/*  912 */               if (!paletteEntry.getNodeName().equals("PaletteEntry")) {
/*  913 */                 fatal(node, "Only a PaletteEntry may be a child of a Palette!");
/*      */               }
/*      */ 
/*      */               
/*  917 */               int index = getIntAttribute(paletteEntry, "index");
/*  918 */               if (index < 0 || index > 255) {
/*  919 */                 fatal(node, "Bad value for PaletteEntry attribute index!");
/*      */               }
/*      */               
/*  922 */               if (index > maxIndex) {
/*  923 */                 maxIndex = index;
/*      */               }
/*  925 */               r[index] = 
/*  926 */                 (byte)getIntAttribute(paletteEntry, "red");
/*  927 */               g[index] = 
/*  928 */                 (byte)getIntAttribute(paletteEntry, "green");
/*  929 */               b[index] = 
/*  930 */                 (byte)getIntAttribute(paletteEntry, "blue");
/*      */               
/*  932 */               paletteEntry = paletteEntry.getNextSibling();
/*      */             } 
/*      */           } 
/*      */           
/*  936 */           child = child.getNextSibling();
/*      */         } 
/*  938 */       } else if (name.equals("Compression")) {
/*  939 */         Node child = node.getFirstChild();
/*  940 */         while (child != null) {
/*  941 */           String childName = child.getNodeName();
/*  942 */           if (childName.equals("CompressionTypeName")) {
/*  943 */             String compressionName = getAttribute(child, "value");
/*  944 */             this
/*  945 */               .compression = BMPImageWriter.getCompressionType(compressionName);
/*      */           } 
/*  947 */           child = child.getNextSibling();
/*      */         } 
/*  949 */       } else if (name.equals("Data")) {
/*  950 */         Node child = node.getFirstChild();
/*  951 */         while (child != null) {
/*  952 */           String childName = child.getNodeName();
/*  953 */           if (childName.equals("BitsPerSample")) {
/*  954 */             List<Integer> bps = new ArrayList(4);
/*  955 */             String s = getAttribute(child, "value");
/*  956 */             StringTokenizer t = new StringTokenizer(s);
/*  957 */             while (t.hasMoreTokens()) {
/*  958 */               bps.add(Integer.valueOf(t.nextToken()));
/*      */             }
/*  960 */             bitsPerSample = new int[bps.size()];
/*  961 */             for (int i = 0; i < bitsPerSample.length; i++) {
/*  962 */               bitsPerSample[i] = ((Integer)bps
/*  963 */                 .get(i)).intValue();
/*      */             }
/*      */             break;
/*      */           } 
/*  967 */           child = child.getNextSibling();
/*      */         } 
/*  969 */       } else if (name.equals("Dimension")) {
/*  970 */         boolean gotWidth = false;
/*  971 */         boolean gotHeight = false;
/*  972 */         boolean gotAspectRatio = false;
/*  973 */         boolean gotSpaceX = false;
/*  974 */         boolean gotSpaceY = false;
/*      */         
/*  976 */         double width = -1.0D;
/*  977 */         double height = -1.0D;
/*  978 */         double aspectRatio = -1.0D;
/*  979 */         double spaceX = -1.0D;
/*  980 */         double spaceY = -1.0D;
/*      */         
/*  982 */         Node child = node.getFirstChild();
/*  983 */         while (child != null) {
/*  984 */           String childName = child.getNodeName();
/*  985 */           if (childName.equals("PixelAspectRatio")) {
/*  986 */             aspectRatio = getDoubleAttribute(child, "value");
/*  987 */             gotAspectRatio = true;
/*  988 */           } else if (childName.equals("HorizontalPixelSize")) {
/*  989 */             width = getDoubleAttribute(child, "value");
/*  990 */             gotWidth = true;
/*  991 */           } else if (childName.equals("VerticalPixelSize")) {
/*  992 */             height = getDoubleAttribute(child, "value");
/*  993 */             gotHeight = true;
/*  994 */           } else if (childName.equals("HorizontalPhysicalPixelSpacing")) {
/*  995 */             spaceX = getDoubleAttribute(child, "value");
/*  996 */             gotSpaceX = true;
/*  997 */           } else if (childName.equals("VerticalPhysicalPixelSpacing")) {
/*  998 */             spaceY = getDoubleAttribute(child, "value");
/*  999 */             gotSpaceY = true;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1006 */           child = child.getNextSibling();
/*      */         } 
/*      */ 
/*      */         
/* 1010 */         if (!gotWidth && !gotHeight && (gotSpaceX || gotSpaceY)) {
/* 1011 */           width = spaceX;
/* 1012 */           gotWidth = gotSpaceX;
/* 1013 */           height = spaceY;
/* 1014 */           gotHeight = gotSpaceY;
/*      */         } 
/*      */ 
/*      */         
/* 1018 */         if (gotWidth && gotHeight) {
/* 1019 */           this.xPixelsPerMeter = (int)(1000.0D / width + 0.5D);
/* 1020 */           this.yPixelsPerMeter = (int)(1000.0D / height + 0.5D);
/* 1021 */         } else if (gotAspectRatio && aspectRatio != 0.0D) {
/* 1022 */           if (gotWidth) {
/* 1023 */             this.xPixelsPerMeter = (int)(1000.0D / width + 0.5D);
/* 1024 */             this.yPixelsPerMeter = (int)(aspectRatio * 1000.0D / width + 0.5D);
/*      */           }
/* 1026 */           else if (gotHeight) {
/* 1027 */             this.xPixelsPerMeter = (int)(1000.0D / height / aspectRatio + 0.5D);
/*      */             
/* 1029 */             this.yPixelsPerMeter = (int)(1000.0D / height + 0.5D);
/*      */           } 
/*      */         } 
/* 1032 */       } else if (name.equals("Document")) {
/* 1033 */         Node child = node.getFirstChild();
/* 1034 */         while (child != null) {
/* 1035 */           String childName = child.getNodeName();
/* 1036 */           if (childName.equals("FormatVersion")) {
/* 1037 */             this.bmpVersion = getAttribute(child, "value");
/*      */             break;
/*      */           } 
/* 1040 */           child = child.getNextSibling();
/*      */         } 
/* 1042 */       } else if (name.equals("Text")) {
/* 1043 */         Node child = node.getFirstChild();
/* 1044 */         while (child != null) {
/* 1045 */           String childName = child.getNodeName();
/* 1046 */           if (childName.equals("TextEntry")) {
/* 1047 */             if (this.comments == null) {
/* 1048 */               this.comments = new ArrayList();
/*      */             }
/* 1050 */             this.comments.add(getAttribute(child, "value"));
/*      */           } 
/* 1052 */           child = child.getNextSibling();
/*      */         } 
/* 1054 */       } else if (name.equals("Transparency")) {
/* 1055 */         Node child = node.getFirstChild();
/* 1056 */         while (child != null) {
/* 1057 */           String childName = child.getNodeName();
/* 1058 */           if (childName.equals("Alpha")) {
/*      */             
/* 1060 */             hasAlpha = !getAttribute(child, "value").equals("none");
/*      */             break;
/*      */           } 
/* 1063 */           child = child.getNextSibling();
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1069 */       node = node.getNextSibling();
/*      */     } 
/*      */ 
/*      */     
/* 1073 */     if (bitsPerSample != null) {
/* 1074 */       if (this.palette != null && this.paletteSize > 0) {
/* 1075 */         this.bitsPerPixel = (short)bitsPerSample[0];
/*      */       } else {
/* 1077 */         this.bitsPerPixel = 0;
/* 1078 */         for (int i = 0; i < bitsPerSample.length; i++) {
/* 1079 */           this.bitsPerPixel = (short)(this.bitsPerPixel + bitsPerSample[i]);
/*      */         }
/*      */       } 
/* 1082 */     } else if (this.palette != null) {
/* 1083 */       this.bitsPerPixel = 8;
/* 1084 */     } else if (numChannels == 1) {
/* 1085 */       this.bitsPerPixel = 8;
/* 1086 */     } else if (numChannels == 3) {
/* 1087 */       this.bitsPerPixel = 24;
/* 1088 */     } else if (numChannels == 4) {
/* 1089 */       this.bitsPerPixel = 32;
/* 1090 */     } else if (colorSpaceType.equals("GRAY")) {
/* 1091 */       this.bitsPerPixel = 8;
/* 1092 */     } else if (colorSpaceType.equals("RGB")) {
/* 1093 */       this.bitsPerPixel = (short)(hasAlpha ? 32 : 24);
/*      */     } 
/*      */ 
/*      */     
/* 1097 */     if ((bitsPerSample != null && bitsPerSample.length == 4) || this.bitsPerPixel >= 24) {
/*      */       
/* 1099 */       this.redMask = 16711680;
/* 1100 */       this.greenMask = 65280;
/* 1101 */       this.blueMask = 255;
/*      */     } 
/*      */ 
/*      */     
/* 1105 */     if ((bitsPerSample != null && bitsPerSample.length == 4) || this.bitsPerPixel > 24)
/*      */     {
/* 1107 */       this.alphaMask = -16777216;
/*      */     }
/*      */ 
/*      */     
/* 1111 */     if (r != null && g != null && b != null) {
/*      */       
/* 1113 */       boolean isVersion2 = (this.bmpVersion != null && this.bmpVersion.equals("BMP v. 2.x"));
/*      */       
/* 1115 */       this.paletteSize = maxIndex + 1;
/* 1116 */       this.palette = new byte[(isVersion2 ? 3 : 4) * this.paletteSize];
/* 1117 */       for (int i = 0, j = 0; i < this.paletteSize; i++) {
/* 1118 */         this.palette[j++] = b[i];
/* 1119 */         this.palette[j++] = g[i];
/* 1120 */         this.palette[j++] = r[i];
/* 1121 */         if (!isVersion2) j++;
/*      */       
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void reset() {
/* 1128 */     this.bmpVersion = null;
/* 1129 */     this.width = 0;
/* 1130 */     this.height = 0;
/* 1131 */     this.bitsPerPixel = 0;
/* 1132 */     this.compression = 0;
/* 1133 */     this.imageSize = 0;
/*      */ 
/*      */     
/* 1136 */     this.xPixelsPerMeter = 0;
/* 1137 */     this.yPixelsPerMeter = 0;
/*      */     
/* 1139 */     this.colorsUsed = 0;
/* 1140 */     this.colorsImportant = 0;
/*      */ 
/*      */     
/* 1143 */     this.redMask = 0;
/* 1144 */     this.greenMask = 0;
/* 1145 */     this.blueMask = 0;
/* 1146 */     this.alphaMask = 0;
/*      */     
/* 1148 */     this.colorSpace = 0;
/*      */ 
/*      */     
/* 1151 */     this.redX = 0.0D;
/* 1152 */     this.redY = 0.0D;
/* 1153 */     this.redZ = 0.0D;
/* 1154 */     this.greenX = 0.0D;
/* 1155 */     this.greenY = 0.0D;
/* 1156 */     this.greenZ = 0.0D;
/* 1157 */     this.blueX = 0.0D;
/* 1158 */     this.blueY = 0.0D;
/* 1159 */     this.blueZ = 0.0D;
/*      */ 
/*      */     
/* 1162 */     this.gammaRed = 0;
/* 1163 */     this.gammaGreen = 0;
/* 1164 */     this.gammaBlue = 0;
/*      */     
/* 1166 */     this.intent = 0;
/*      */ 
/*      */     
/* 1169 */     this.palette = null;
/* 1170 */     this.paletteSize = 0;
/* 1171 */     this.red = 0;
/* 1172 */     this.green = 0;
/* 1173 */     this.blue = 0;
/*      */ 
/*      */     
/* 1176 */     this.comments = null;
/*      */   }
/*      */   
/*      */   private String countBits(int num) {
/* 1180 */     int count = 0;
/* 1181 */     while (num != 0) {
/* 1182 */       if ((num & 0x1) == 1)
/* 1183 */         count++; 
/* 1184 */       num >>>= 1;
/*      */     } 
/*      */     
/* 1187 */     return (count == 0) ? "0" : ("" + count);
/*      */   }
/*      */   
/*      */   private void addXYZPoints(IIOMetadataNode root, String name, double x, double y, double z) {
/* 1191 */     IIOMetadataNode node = addChildNode(root, name, (Object)null);
/* 1192 */     addChildNode(node, "X", new Double(x));
/* 1193 */     addChildNode(node, "Y", new Double(y));
/* 1194 */     addChildNode(node, "Z", new Double(z));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private IIOMetadataNode addChildNode(IIOMetadataNode root, String name, Object object) {
/* 1200 */     IIOMetadataNode child = new IIOMetadataNode(name);
/* 1201 */     if (object != null) {
/* 1202 */       child.setUserObject(object);
/* 1203 */       child.setNodeValue(ImageUtil.convertObjectToString(object));
/*      */     } 
/* 1205 */     root.appendChild(child);
/* 1206 */     return child;
/*      */   }
/*      */   
/*      */   private Object getObjectValue(Node node) {
/* 1210 */     Object tmp = node.getNodeValue();
/*      */     
/* 1212 */     if (tmp == null && node instanceof IIOMetadataNode) {
/* 1213 */       tmp = ((IIOMetadataNode)node).getUserObject();
/*      */     }
/*      */     
/* 1216 */     return tmp;
/*      */   }
/*      */   
/*      */   private String getStringValue(Node node) {
/* 1220 */     Object tmp = getObjectValue(node);
/* 1221 */     return (tmp instanceof String) ? (String)tmp : null;
/*      */   }
/*      */   
/*      */   private Byte getByteValue(Node node) {
/* 1225 */     Object tmp = getObjectValue(node);
/* 1226 */     Byte value = null;
/* 1227 */     if (tmp instanceof String) {
/* 1228 */       value = Byte.valueOf((String)tmp);
/* 1229 */     } else if (tmp instanceof Byte) {
/* 1230 */       value = (Byte)tmp;
/*      */     } 
/* 1232 */     return value;
/*      */   }
/*      */   
/*      */   private Short getShortValue(Node node) {
/* 1236 */     Object tmp = getObjectValue(node);
/* 1237 */     Short value = null;
/* 1238 */     if (tmp instanceof String) {
/* 1239 */       value = Short.valueOf((String)tmp);
/* 1240 */     } else if (tmp instanceof Short) {
/* 1241 */       value = (Short)tmp;
/*      */     } 
/* 1243 */     return value;
/*      */   }
/*      */   
/*      */   private Integer getIntegerValue(Node node) {
/* 1247 */     Object tmp = getObjectValue(node);
/* 1248 */     Integer value = null;
/* 1249 */     if (tmp instanceof String) {
/* 1250 */       value = Integer.valueOf((String)tmp);
/* 1251 */     } else if (tmp instanceof Integer) {
/* 1252 */       value = (Integer)tmp;
/* 1253 */     } else if (tmp instanceof Byte) {
/* 1254 */       value = new Integer(((Byte)tmp).byteValue() & 0xFF);
/*      */     } 
/* 1256 */     return value;
/*      */   }
/*      */   
/*      */   private Double getDoubleValue(Node node) {
/* 1260 */     Object tmp = getObjectValue(node);
/* 1261 */     Double value = null;
/* 1262 */     if (tmp instanceof String) {
/* 1263 */       value = Double.valueOf((String)tmp);
/* 1264 */     } else if (tmp instanceof Double) {
/* 1265 */       value = (Double)tmp;
/*      */     } 
/* 1267 */     return value;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/bmp/BMPMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */