/*      */ package com.github.jaiimageio.jpeg2000.impl;
/*      */ 
/*      */ import c.a.d.a.a;
/*      */ import c.a.f.f;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.color.ICC_ColorSpace;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import javax.imageio.ImageTypeSpecifier;
/*      */ import javax.imageio.ImageWriteParam;
/*      */ import javax.imageio.ImageWriter;
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
/*      */ public class J2KMetadata
/*      */   extends IIOMetadata
/*      */   implements Cloneable
/*      */ {
/*      */   static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_jpeg2000_image_1.0";
/*      */   private J2KMetadataFormat format;
/*   87 */   private ArrayList boxes = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public J2KMetadata() {
/*   93 */     super(true, "com_sun_media_imageio_plugins_jpeg2000_image_1.0", "com.github.jaiimageio.jpeg2000.impl.J2KMetadataFormat", null, null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   98 */     this.format = (J2KMetadataFormat)getMetadataFormat("com_sun_media_imageio_plugins_jpeg2000_image_1.0");
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
/*      */   public J2KMetadata(ImageInputStream iis, J2KImageReader reader) throws IOException {
/*  113 */     this();
/*  114 */     f in = new IISRandomAccessIO(iis);
/*      */     
/*  116 */     iis.mark();
/*      */ 
/*      */ 
/*      */     
/*  120 */     a ff = new a(in, this);
/*  121 */     ff.a();
/*  122 */     iis.reset();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public J2KMetadata(ImageWriteParam param, ImageWriter writer) {
/*  130 */     this((ImageTypeSpecifier)null, param, writer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public J2KMetadata(ImageTypeSpecifier imageType, ImageWriteParam param, ImageWriter writer) {
/*  140 */     this((imageType != null) ? imageType.getColorModel() : null, (imageType != null) ? imageType
/*  141 */         .getSampleModel() : null, 0, 0, param, writer);
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
/*      */   public J2KMetadata(ColorModel colorModel, SampleModel sampleModel, int width, int height, ImageWriteParam param, ImageWriter writer) {
/*  156 */     this();
/*  157 */     addNode(new SignatureBox());
/*  158 */     addNode(new FileTypeBox(1785737760, 0, new int[] { 1785737760 }));
/*      */     
/*  160 */     ImageTypeSpecifier destType = null;
/*      */     
/*  162 */     if (param != null) {
/*  163 */       destType = param.getDestinationType();
/*  164 */       if (colorModel == null && sampleModel == null) {
/*  165 */         colorModel = (destType == null) ? null : destType.getColorModel();
/*      */         
/*  167 */         sampleModel = (destType == null) ? null : destType.getSampleModel();
/*      */       } 
/*      */     } 
/*      */     
/*  171 */     int[] bitDepths = null;
/*  172 */     if (colorModel != null) {
/*  173 */       bitDepths = colorModel.getComponentSize();
/*  174 */     } else if (sampleModel != null) {
/*  175 */       bitDepths = sampleModel.getSampleSize();
/*      */     } 
/*      */     
/*  178 */     int bitsPerComponent = 255;
/*  179 */     if (bitDepths != null) {
/*  180 */       bitsPerComponent = bitDepths[0];
/*  181 */       int numComponents = bitDepths.length;
/*  182 */       for (int i = 1; i < numComponents; i++) {
/*      */ 
/*      */         
/*  185 */         if (bitDepths[i] > bitsPerComponent) {
/*  186 */           bitsPerComponent = bitDepths[i];
/*      */         }
/*      */       } 
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
/*  200 */     if (colorModel != null) {
/*  201 */       ColorSpace cs = colorModel.getColorSpace();
/*  202 */       boolean iccColor = cs instanceof ICC_ColorSpace;
/*  203 */       int type = cs.getType();
/*      */       
/*  205 */       if (type == 5) {
/*  206 */         addNode(new ColorSpecificationBox((byte)1, (byte)0, (byte)0, 16, null));
/*      */ 
/*      */       
/*      */       }
/*  210 */       else if (type == 6) {
/*  211 */         addNode(new ColorSpecificationBox((byte)1, (byte)0, (byte)0, 17, null));
/*      */ 
/*      */       
/*      */       }
/*  215 */       else if (cs instanceof ICC_ColorSpace) {
/*  216 */         addNode(new ColorSpecificationBox((byte)2, (byte)0, (byte)0, 0, ((ICC_ColorSpace)cs)
/*      */ 
/*      */               
/*  219 */               .getProfile()));
/*      */       } 
/*  221 */       if (colorModel.hasAlpha()) {
/*  222 */         addNode(new ChannelDefinitionBox(colorModel));
/*      */       }
/*      */       
/*  225 */       if (colorModel instanceof IndexColorModel) {
/*  226 */         addNode(new PaletteBox((IndexColorModel)colorModel));
/*  227 */         int numComp = (colorModel.getComponentSize()).length;
/*  228 */         short[] channels = new short[numComp];
/*  229 */         byte[] types = new byte[numComp];
/*  230 */         byte[] maps = new byte[numComp];
/*  231 */         for (int i = 0; i < numComp; i++) {
/*  232 */           channels[i] = 0;
/*  233 */           types[i] = 1;
/*  234 */           maps[i] = (byte)i;
/*      */         } 
/*  236 */         addNode(new ComponentMappingBox(channels, types, maps));
/*      */       } 
/*      */     } 
/*      */     
/*  240 */     if (sampleModel != null) {
/*  241 */       if (width <= 0)
/*  242 */         width = sampleModel.getWidth(); 
/*  243 */       if (height <= 0) {
/*  244 */         height = sampleModel.getHeight();
/*      */       }
/*      */       
/*  247 */       int bpc = (bitsPerComponent == 255) ? 255 : (bitsPerComponent - 1 | (isOriginalSigned(sampleModel) ? 128 : 0));
/*  248 */       addNode(new HeaderBox(height, width, sampleModel
/*      */             
/*  250 */             .getNumBands(), bpc, 7, (colorModel == null) ? 1 : 0, 
/*      */ 
/*      */ 
/*      */             
/*  254 */             (getElement("JPEG2000IntellectualPropertyRightsBox") == null) ? 0 : 1));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object clone() {
/*  259 */     J2KMetadata theClone = null;
/*      */     
/*      */     try {
/*  262 */       theClone = (J2KMetadata)super.clone();
/*  263 */     } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*      */     
/*  265 */     if (this.boxes != null) {
/*  266 */       int numBoxes = this.boxes.size();
/*  267 */       for (int i = 0; i < numBoxes; i++) {
/*  268 */         theClone.addNode(this.boxes.get(i));
/*      */       }
/*      */     } 
/*  271 */     return theClone;
/*      */   }
/*      */   
/*      */   public Node getAsTree(String formatName) {
/*  275 */     if (formatName == null) {
/*  276 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata0"));
/*      */     }
/*      */     
/*  279 */     if (formatName.equals("com_sun_media_imageio_plugins_jpeg2000_image_1.0")) {
/*  280 */       return getNativeTree();
/*      */     }
/*      */     
/*  283 */     if (formatName
/*  284 */       .equals("javax_imageio_1.0")) {
/*  285 */       return getStandardTree();
/*      */     }
/*      */     
/*  288 */     throw new IllegalArgumentException(I18N.getString("J2KMetadata1") + " " + formatName);
/*      */   }
/*      */ 
/*      */   
/*      */   IIOMetadataNode getNativeTree() {
/*  293 */     IIOMetadataNode root = new IIOMetadataNode("com_sun_media_imageio_plugins_jpeg2000_image_1.0");
/*      */ 
/*      */     
/*  296 */     Box signatureBox = null, fileTypeBox = null, headerBox = null;
/*  297 */     int signatureIndex = -1, fileTypeIndex = -1, headerIndex = -1;
/*      */     
/*  299 */     int numBoxes = this.boxes.size();
/*      */     
/*  301 */     int found = 0; int i;
/*  302 */     for (i = 0; i < numBoxes && found < 3; i++) {
/*  303 */       Box box = this.boxes.get(i);
/*  304 */       if (Box.getName(box.getType()).equals("JPEG2000SignatureBox")) {
/*  305 */         signatureBox = box;
/*  306 */         signatureIndex = i;
/*  307 */         found++;
/*  308 */       } else if (Box.getName(box.getType()).equals("JPEG2000FileTypeBox")) {
/*  309 */         fileTypeBox = box;
/*  310 */         fileTypeIndex = i;
/*  311 */         found++;
/*  312 */       } else if (Box.getName(box.getType()).equals("JPEG2000HeaderBox")) {
/*  313 */         headerBox = box;
/*  314 */         headerIndex = i;
/*  315 */         found++;
/*      */       } 
/*      */     } 
/*      */     
/*  319 */     if (signatureBox != null) {
/*  320 */       insertNodeIntoTree(root, signatureBox.getNativeNode());
/*      */     }
/*      */     
/*  323 */     if (fileTypeBox != null) {
/*  324 */       insertNodeIntoTree(root, fileTypeBox.getNativeNode());
/*      */     }
/*      */     
/*  327 */     if (headerBox != null) {
/*  328 */       insertNodeIntoTree(root, headerBox.getNativeNode());
/*      */     }
/*      */     
/*  331 */     for (i = 0; i < numBoxes; i++) {
/*  332 */       if (i != signatureIndex && i != fileTypeIndex && i != headerIndex) {
/*      */ 
/*      */         
/*  335 */         Box box = this.boxes.get(i);
/*  336 */         IIOMetadataNode node = box.getNativeNode();
/*  337 */         insertNodeIntoTree(root, node);
/*      */       } 
/*  339 */     }  return root;
/*      */   }
/*      */ 
/*      */   
/*      */   protected IIOMetadataNode getStandardChromaNode() {
/*  344 */     HeaderBox header = (HeaderBox)getElement("JPEG2000HeaderBox");
/*  345 */     PaletteBox palette = (PaletteBox)getElement("JPEG2000PaletteBox");
/*      */     
/*  347 */     ColorSpecificationBox color = (ColorSpecificationBox)getElement("JPEG2000ColorSpecificationBox");
/*      */     
/*  349 */     IIOMetadataNode node = new IIOMetadataNode("Chroma");
/*  350 */     IIOMetadataNode subNode = null;
/*  351 */     if (header != null) {
/*  352 */       if (header.getUnknownColorspace() == 0 && 
/*  353 */         color != null && color.getMethod() == 1) {
/*  354 */         subNode = new IIOMetadataNode("ColorSpaceType");
/*  355 */         int ecs = color.getEnumeratedColorSpace();
/*  356 */         if (ecs == 16)
/*  357 */           subNode.setAttribute("name", "RGB"); 
/*  358 */         if (ecs == 17)
/*  359 */           subNode.setAttribute("name", "GRAY"); 
/*  360 */         node.appendChild(subNode);
/*      */       } 
/*      */ 
/*      */       
/*  364 */       subNode = new IIOMetadataNode("NumChannels");
/*  365 */       subNode.setAttribute("value", "" + header.getNumComponents());
/*  366 */       node.appendChild(subNode);
/*      */       
/*  368 */       if (palette != null) {
/*  369 */         subNode.setAttribute("value", "" + palette.getNumComp());
/*  370 */         subNode = new IIOMetadataNode("Palette");
/*  371 */         byte[][] lut = palette.getLUT();
/*      */         
/*  373 */         int size = (lut[0]).length;
/*  374 */         int numComp = lut.length;
/*      */         
/*  376 */         for (int i = 0; i < size; i++) {
/*  377 */           IIOMetadataNode subNode1 = new IIOMetadataNode("PaletteEntry");
/*      */           
/*  379 */           subNode1.setAttribute("index", "" + i);
/*  380 */           subNode1.setAttribute("red", "" + (lut[0][i] & 0xFF));
/*  381 */           subNode1.setAttribute("green", "" + (lut[1][i] & 0xFF));
/*  382 */           subNode1.setAttribute("blue", "" + (lut[2][i] & 0xFF));
/*  383 */           if (numComp == 4)
/*  384 */             subNode1.setAttribute("alpha", "" + (lut[3][i] & 0xFF)); 
/*  385 */           subNode.appendChild(subNode1);
/*      */         } 
/*  387 */         node.appendChild(subNode);
/*      */       } 
/*      */     } 
/*  390 */     return node;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardCompressionNode() {
/*  394 */     IIOMetadataNode node = new IIOMetadataNode("Compression");
/*      */ 
/*      */     
/*  397 */     IIOMetadataNode subNode = new IIOMetadataNode("CompressionTypeName");
/*  398 */     subNode.setAttribute("value", "JPEG2000");
/*  399 */     node.appendChild(subNode);
/*  400 */     return node;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardDataNode() {
/*  404 */     IIOMetadataNode node = new IIOMetadataNode("Data");
/*  405 */     PaletteBox palette = (PaletteBox)getElement("JPEG2000PaletteBox");
/*  406 */     boolean sampleFormat = false;
/*      */     
/*  408 */     if (palette != null) {
/*  409 */       IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("SampleFormat");
/*  410 */       iIOMetadataNode.setAttribute("value", "Index");
/*  411 */       node.appendChild(iIOMetadataNode);
/*  412 */       sampleFormat = true;
/*      */     } 
/*      */ 
/*      */     
/*  416 */     BitsPerComponentBox bitDepth = (BitsPerComponentBox)getElement("JPEG2000BitsPerComponentBox");
/*  417 */     String value = "";
/*  418 */     boolean signed = false;
/*  419 */     boolean gotSampleInfo = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  424 */     if (bitDepth != null) {
/*  425 */       byte[] bits = bitDepth.getBitDepth();
/*  426 */       if ((bits[0] & 0x80) == 128) {
/*  427 */         signed = true;
/*      */       }
/*  429 */       int numComp = bits.length;
/*  430 */       for (int i = 0; i < numComp; i++) {
/*  431 */         value = value + ((bits[i] & Byte.MAX_VALUE) + 1);
/*  432 */         if (i != numComp - 1) value = value + " ";
/*      */       
/*      */       } 
/*  435 */       gotSampleInfo = true;
/*      */     } else {
/*  437 */       HeaderBox header = (HeaderBox)getElement("JPEG2000HeaderBox");
/*  438 */       if (header != null) {
/*  439 */         int bits = header.getBitDepth();
/*  440 */         if ((bits & 0x80) == 128)
/*  441 */           signed = true; 
/*  442 */         bits = (bits & 0x7F) + 1;
/*  443 */         int numComp = header.getNumComponents();
/*  444 */         for (int i = 0; i < numComp; i++) {
/*  445 */           value = value + bits;
/*  446 */           if (i != numComp - 1) value = value + " ";
/*      */         
/*      */         } 
/*  449 */         gotSampleInfo = true;
/*      */       } 
/*      */     } 
/*      */     
/*  453 */     IIOMetadataNode subNode = null;
/*      */     
/*  455 */     if (gotSampleInfo) {
/*  456 */       subNode = new IIOMetadataNode("BitsPerSample");
/*  457 */       subNode.setAttribute("value", value);
/*  458 */       node.appendChild(subNode);
/*      */     } 
/*      */     
/*  461 */     subNode = new IIOMetadataNode("PlanarConfiguration");
/*  462 */     subNode.setAttribute("value", "TileInterleaved");
/*  463 */     node.appendChild(subNode);
/*      */     
/*  465 */     if (!sampleFormat && gotSampleInfo) {
/*  466 */       subNode = new IIOMetadataNode("SampleFormat");
/*  467 */       subNode.setAttribute("value", signed ? "SignedIntegral" : "UnsignedIntegral");
/*      */       
/*  469 */       node.appendChild(subNode);
/*      */     } 
/*      */     
/*  472 */     return node;
/*      */   }
/*      */ 
/*      */   
/*      */   protected IIOMetadataNode getStandardDimensionNode() {
/*  477 */     ResolutionBox box = (ResolutionBox)getElement("JPEG2000CaptureResolutionBox");
/*  478 */     if (box != null) {
/*  479 */       IIOMetadataNode node = new IIOMetadataNode("Dimension");
/*  480 */       float hRes = box.getHorizontalResolution();
/*  481 */       float vRes = box.getVerticalResolution();
/*  482 */       float ratio = vRes / hRes;
/*  483 */       IIOMetadataNode subNode = new IIOMetadataNode("PixelAspectRatio");
/*  484 */       subNode.setAttribute("value", "" + ratio);
/*  485 */       node.appendChild(subNode);
/*      */       
/*  487 */       subNode = new IIOMetadataNode("HorizontalPixelSize");
/*  488 */       subNode.setAttribute("value", "" + (1000.0F / hRes));
/*  489 */       node.appendChild(subNode);
/*      */       
/*  491 */       subNode = new IIOMetadataNode("VerticalPixelSize");
/*  492 */       subNode.setAttribute("value", "" + (1000.0F / vRes));
/*  493 */       node.appendChild(subNode);
/*      */       
/*  495 */       return node;
/*      */     } 
/*      */     
/*  498 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected IIOMetadataNode getStandardTransparencyNode() {
/*  503 */     ChannelDefinitionBox channel = (ChannelDefinitionBox)getElement("JPEG2000ChannelDefinitionBox");
/*  504 */     if (channel != null) {
/*  505 */       IIOMetadataNode iIOMetadataNode1 = new IIOMetadataNode("Transparency");
/*      */       
/*  507 */       boolean hasAlpha = false;
/*  508 */       boolean isPremultiplied = false;
/*  509 */       short[] type = channel.getTypes();
/*      */       
/*  511 */       for (int i = 0; i < type.length; i++) {
/*  512 */         if (type[i] == 1)
/*  513 */           hasAlpha = true; 
/*  514 */         if (type[i] == 2) {
/*  515 */           isPremultiplied = true;
/*      */         }
/*      */       } 
/*  518 */       String value = "none";
/*  519 */       if (isPremultiplied) {
/*  520 */         value = "premultiplied";
/*  521 */       } else if (hasAlpha) {
/*  522 */         value = "nonpremultiplied";
/*      */       } 
/*  524 */       IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("Alpha");
/*  525 */       iIOMetadataNode2.setAttribute("value", value);
/*  526 */       iIOMetadataNode1.appendChild(iIOMetadataNode2);
/*      */       
/*  528 */       return iIOMetadataNode1;
/*      */     } 
/*      */     
/*  531 */     IIOMetadataNode node = new IIOMetadataNode("Transparency");
/*  532 */     IIOMetadataNode subNode = new IIOMetadataNode("Alpha");
/*  533 */     subNode.setAttribute("value", "none");
/*  534 */     node.appendChild(subNode);
/*      */     
/*  536 */     return null;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardTextNode() {
/*  540 */     if (this.boxes == null)
/*  541 */       return null; 
/*  542 */     IIOMetadataNode text = null;
/*      */     
/*  544 */     Iterator<Box> iterator = this.boxes.iterator();
/*      */     
/*  546 */     while (iterator.hasNext()) {
/*  547 */       Box box = iterator.next();
/*  548 */       if (box instanceof XMLBox) {
/*  549 */         if (text == null)
/*  550 */           text = new IIOMetadataNode("Text"); 
/*  551 */         IIOMetadataNode subNode = new IIOMetadataNode("TextEntry");
/*  552 */         String content = new String(box.getContent());
/*  553 */         subNode.setAttribute("value", content);
/*  554 */         text.appendChild(subNode);
/*      */       } 
/*      */     } 
/*  557 */     return text;
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() {
/*  561 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/*  566 */     if (formatName == null) {
/*  567 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata0"));
/*      */     }
/*      */     
/*  570 */     if (root == null) {
/*  571 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata2"));
/*      */     }
/*      */     
/*  574 */     if (formatName.equals("com_sun_media_imageio_plugins_jpeg2000_image_1.0") && root
/*  575 */       .getNodeName().equals("com_sun_media_imageio_plugins_jpeg2000_image_1.0")) {
/*  576 */       mergeNativeTree(root);
/*  577 */     } else if (formatName
/*  578 */       .equals("javax_imageio_1.0")) {
/*  579 */       mergeStandardTree(root);
/*      */     } else {
/*  581 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata1") + " " + formatName);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFromTree(String formatName, Node root) throws IIOInvalidTreeException {
/*  588 */     if (formatName == null) {
/*  589 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata0"));
/*      */     }
/*      */     
/*  592 */     if (root == null) {
/*  593 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata2"));
/*      */     }
/*      */     
/*  596 */     if (formatName.equals("com_sun_media_imageio_plugins_jpeg2000_image_1.0") && root
/*  597 */       .getNodeName().equals("com_sun_media_imageio_plugins_jpeg2000_image_1.0")) {
/*  598 */       this.boxes = new ArrayList();
/*  599 */       mergeNativeTree(root);
/*  600 */     } else if (formatName
/*  601 */       .equals("javax_imageio_1.0")) {
/*  602 */       this.boxes = new ArrayList();
/*  603 */       mergeStandardTree(root);
/*      */     } else {
/*  605 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata1") + " " + formatName);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void reset() {
/*  611 */     this.boxes.clear();
/*      */   }
/*      */   
/*      */   public void addNode(Box node) {
/*  615 */     if (this.boxes == null)
/*  616 */       this.boxes = new ArrayList(); 
/*  617 */     replace(Box.getName(node.getType()), node);
/*      */   }
/*      */   
/*      */   public Box getElement(String name) {
/*  621 */     for (int i = this.boxes.size() - 1; i >= 0; i--) {
/*  622 */       Box box = this.boxes.get(i);
/*  623 */       if (name.equals(Box.getName(box.getType())))
/*  624 */         return box; 
/*      */     } 
/*  626 */     return null;
/*      */   }
/*      */   
/*      */   private void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/*  630 */     NodeList list = root.getChildNodes();
/*  631 */     for (int i = list.getLength() - 1; i >= 0; i--) {
/*  632 */       Node node = list.item(i);
/*  633 */       String name = node.getNodeName();
/*  634 */       if (this.format.getParent(name) != null) {
/*  635 */         if (this.format.isLeaf(name))
/*  636 */         { String s = (String)Box.getAttribute(node, "Type");
/*  637 */           Box box = Box.createBox(Box.getTypeInt(s), node);
/*  638 */           if (this.format.singleInstance(name) && getElement(name) != null) {
/*  639 */             replace(name, box);
/*      */           } else {
/*  641 */             this.boxes.add(box);
/*      */           }  }
/*  643 */         else { mergeNativeTree(node); }
/*      */       
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/*  650 */     NodeList children = root.getChildNodes();
/*  651 */     int numComps = 0;
/*      */     
/*  653 */     for (int i = 0; i < children.getLength(); i++) {
/*  654 */       Node node = children.item(i);
/*  655 */       String name = node.getNodeName();
/*  656 */       if (name.equals("Chroma")) {
/*  657 */         NodeList children1 = node.getChildNodes();
/*  658 */         for (int j = 0; j < children1.getLength(); j++) {
/*  659 */           Node child = children1.item(j);
/*  660 */           String name1 = child.getNodeName();
/*      */           
/*  662 */           if (name1.equals("NumChannels")) {
/*  663 */             String s = (String)Box.getAttribute(child, "value");
/*  664 */             numComps = (new Integer(s)).intValue();
/*      */           } 
/*      */           
/*  667 */           if (name1.equals("ColorSpaceType")) {
/*  668 */             createColorSpecificationBoxFromStandardNode(child);
/*      */           }
/*  670 */           if (name1.equals("Palette")) {
/*  671 */             createPaletteBoxFromStandardNode(child);
/*      */           }
/*      */         } 
/*  674 */       } else if (!name.equals("Compression")) {
/*      */ 
/*      */ 
/*      */         
/*  678 */         if (name.equals("Data")) {
/*  679 */           createBitsPerComponentBoxFromStandardNode(node);
/*  680 */           createHeaderBoxFromStandardNode(node, numComps);
/*  681 */         } else if (name.equals("Dimension")) {
/*  682 */           createResolutionBoxFromStandardNode(node);
/*  683 */         } else if (name.equals("Document")) {
/*  684 */           createXMLBoxFromStandardNode(node);
/*  685 */         } else if (name.equals("Text")) {
/*  686 */           createXMLBoxFromStandardNode(node);
/*  687 */         } else if (name.equals("Transparency")) {
/*  688 */           createChannelDefinitionFromStandardNode(node);
/*      */         } else {
/*  690 */           throw new IIOInvalidTreeException(I18N.getString("J2KMetadata3") + " " + name, node);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void createColorSpecificationBoxFromStandardNode(Node node) {
/*  697 */     if (node.getNodeName() != "ColorSpaceType")
/*  698 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata4")); 
/*  699 */     String name = (String)Box.getAttribute(node, "name");
/*      */     
/*  701 */     int ecs = name.equals("RGB") ? 16 : (name.equals("Gray") ? 17 : 0);
/*      */     
/*  703 */     if (ecs == 16 || ecs == 17)
/*      */     {
/*  705 */       replace("JPEG2000ColorSpecificationBox", new ColorSpecificationBox((byte)1, (byte)0, (byte)0, ecs, null));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void createPaletteBoxFromStandardNode(Node node) {
/*      */     IndexColorModel icm;
/*  712 */     if (node.getNodeName() != "Palette")
/*  713 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata5")); 
/*  714 */     NodeList children = node.getChildNodes();
/*  715 */     int maxIndex = -1;
/*  716 */     boolean hasAlpha = false;
/*  717 */     for (int i = 0; i < children.getLength(); i++) {
/*  718 */       Node child = children.item(i);
/*  719 */       String name = child.getNodeName();
/*      */       
/*  721 */       if (name.equals("PaletteEntry")) {
/*  722 */         String s = (String)Box.getAttribute(child, "index");
/*  723 */         int index = (new Integer(s)).intValue();
/*  724 */         if (index > maxIndex) {
/*  725 */           maxIndex = index;
/*      */         }
/*  727 */         if (Box.getAttribute(child, "alpha") != null) {
/*  728 */           hasAlpha = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  734 */     int numBits = 32;
/*  735 */     int mask = Integer.MIN_VALUE;
/*  736 */     while (mask != 0 && (maxIndex & mask) == 0) {
/*  737 */       numBits--;
/*  738 */       mask >>>= 1;
/*      */     } 
/*  740 */     int size = 1 << numBits;
/*      */     
/*  742 */     byte[] red = new byte[size];
/*  743 */     byte[] green = new byte[size];
/*  744 */     byte[] blue = new byte[size];
/*  745 */     byte[] alpha = hasAlpha ? new byte[size] : null;
/*      */     
/*  747 */     for (int j = 0; j < children.getLength(); j++) {
/*  748 */       Node child = children.item(j);
/*  749 */       String name = child.getNodeName();
/*      */       
/*  751 */       if (name.equals("PaletteEntry")) {
/*  752 */         String s = (String)Box.getAttribute(child, "index");
/*  753 */         int index = (new Integer(s)).intValue();
/*  754 */         s = (String)Box.getAttribute(child, "red");
/*  755 */         red[index] = (byte)(new Integer(s)).intValue();
/*  756 */         s = (String)Box.getAttribute(child, "green");
/*  757 */         green[index] = (byte)(new Integer(s)).intValue();
/*  758 */         s = (String)Box.getAttribute(child, "blue");
/*  759 */         blue[index] = (byte)(new Integer(s)).intValue();
/*      */         
/*  761 */         byte t = -1;
/*  762 */         s = (String)Box.getAttribute(child, "alpha");
/*  763 */         if (s != null) {
/*  764 */           t = (byte)(new Integer(s)).intValue();
/*      */         }
/*      */         
/*  767 */         if (alpha != null) {
/*  768 */           alpha[index] = t;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  774 */     if (alpha == null) {
/*  775 */       icm = new IndexColorModel(numBits, size, red, green, blue);
/*      */     } else {
/*  777 */       icm = new IndexColorModel(numBits, size, red, green, blue, alpha);
/*      */     } 
/*  779 */     replace("JPEG2000PaletteBox", new PaletteBox(icm));
/*      */   }
/*      */   
/*      */   private void createBitsPerComponentBoxFromStandardNode(Node node) {
/*  783 */     if (node.getNodeName() != "Data") {
/*  784 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata6"));
/*      */     }
/*  786 */     NodeList children = node.getChildNodes();
/*      */     
/*  788 */     byte[] bits = null;
/*  789 */     boolean isSigned = false; int i;
/*  790 */     for (i = 0; i < children.getLength(); i++) {
/*  791 */       Node child = children.item(i);
/*  792 */       String name = child.getNodeName();
/*      */       
/*  794 */       if (name.equals("BitsPerSample")) {
/*  795 */         String s = (String)Box.getAttribute(child, "value");
/*  796 */         bits = (byte[])Box.parseByteArray(s).clone();
/*  797 */       } else if (name.equals("SampleFormat")) {
/*  798 */         String s = (String)Box.getAttribute(child, "value");
/*  799 */         isSigned = s.equals("SignedIntegral");
/*      */       } 
/*      */     } 
/*      */     
/*  803 */     if (bits != null) {
/*      */ 
/*      */ 
/*      */       
/*  807 */       for (i = 0; i < bits.length; i++) {
/*  808 */         bits[i] = (byte)((bits[i] & 0xFF) - 1);
/*  809 */         if (isSigned) {
/*  810 */           bits[i] = (byte)(bits[i] | 0x80);
/*      */         }
/*      */       } 
/*      */       
/*  814 */       replace("JPEG2000BitsPerComponent", new BitsPerComponentBox(bits));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void createResolutionBoxFromStandardNode(Node node) {
/*  820 */     if (node.getNodeName() != "Dimension")
/*  821 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata7")); 
/*  822 */     NodeList children = node.getChildNodes();
/*  823 */     float hRes = 0.0F;
/*  824 */     float vRes = 0.0F;
/*      */     
/*  826 */     boolean gotH = false, gotV = false;
/*      */     
/*  828 */     for (int i = 0; i < children.getLength(); i++) {
/*  829 */       Node child = children.item(i);
/*  830 */       String name = child.getNodeName();
/*      */       
/*  832 */       if (name.equals("HorizontalPixelSize")) {
/*  833 */         String s = (String)Box.getAttribute(child, "value");
/*  834 */         hRes = (new Float(s)).floatValue();
/*  835 */         hRes = 1000.0F / hRes;
/*  836 */         gotH = true;
/*      */       } 
/*      */       
/*  839 */       if (name.equals("VerticalPixelSize")) {
/*  840 */         String s = (String)Box.getAttribute(child, "value");
/*  841 */         vRes = (new Float(s)).floatValue();
/*  842 */         vRes = 1000.0F / vRes;
/*  843 */         gotV = true;
/*      */       } 
/*      */     } 
/*      */     
/*  847 */     if (gotH && !gotV) {
/*  848 */       vRes = hRes;
/*  849 */     } else if (gotV && !gotH) {
/*  850 */       hRes = vRes;
/*      */     } 
/*      */     
/*  853 */     if (gotH || gotV) {
/*  854 */       replace("JPEG2000CaptureResolutionBox", new ResolutionBox(1919251299, hRes, vRes));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void createXMLBoxFromStandardNode(Node node) {
/*  860 */     NodeList children = node.getChildNodes();
/*  861 */     String value = "<" + node.getNodeName() + ">";
/*      */     
/*  863 */     for (int i = 0; i < children.getLength(); i++) {
/*  864 */       Node child = children.item(i);
/*  865 */       String name = child.getNodeName();
/*  866 */       value = value + "<" + name + " ";
/*      */       
/*  868 */       NamedNodeMap map = child.getAttributes();
/*      */       
/*  870 */       for (int j = 0; j < map.getLength(); j++) {
/*  871 */         Node att = map.item(j);
/*      */         
/*  873 */         value = value + att.getNodeName() + "=\"" + att.getNodeValue() + "\" ";
/*      */       } 
/*      */       
/*  876 */       value = value + " />";
/*      */     } 
/*      */     
/*  879 */     value = value + "</" + node.getNodeName() + ">";
/*      */     
/*  881 */     this.boxes.add(new XMLBox(value.getBytes()));
/*      */   }
/*      */   
/*      */   private void createHeaderBoxFromStandardNode(Node node, int numComps) {
/*  885 */     HeaderBox header = (HeaderBox)getElement("JPEG2000HeaderBox");
/*      */     
/*  887 */     byte unknownColor = (byte)((getElement("JPEG2000ColorSpecificationBox") == null) ? 1 : 0);
/*  888 */     if (header != null) {
/*  889 */       if (numComps == 0);
/*  890 */       numComps = header.getNumComponents();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  897 */       header = new HeaderBox(header.getHeight(), header.getWidth(), numComps, header.getBitDepth(), header.getCompressionType(), unknownColor, header.getIntellectualProperty());
/*      */     } else {
/*  899 */       header = new HeaderBox(0, 0, numComps, 0, 0, unknownColor, 0);
/*      */     } 
/*  901 */     replace("JPEG2000HeaderBox", header);
/*      */   }
/*      */   
/*      */   private void createChannelDefinitionFromStandardNode(Node node) {
/*  905 */     if (node.getNodeName() != "Transparency") {
/*  906 */       throw new IllegalArgumentException(I18N.getString("J2KMetadata8"));
/*      */     }
/*  908 */     HeaderBox header = (HeaderBox)getElement("JPEG2000HeaderBox");
/*  909 */     int numComps = 3;
/*      */     
/*  911 */     if (header != null) {
/*  912 */       numComps = header.getNumComponents();
/*      */     }
/*      */     
/*  915 */     NodeList children = node.getChildNodes();
/*  916 */     boolean hasAlpha = false;
/*  917 */     boolean isPremultiplied = false;
/*      */     
/*  919 */     for (int i = 0; i < children.getLength(); i++) {
/*  920 */       Node child = children.item(i);
/*  921 */       String name = child.getNodeName();
/*      */       
/*  923 */       if (name.equals("Alpha")) {
/*  924 */         String value = (String)Box.getAttribute(child, "value");
/*  925 */         if (value.equals("premultiplied"))
/*  926 */           isPremultiplied = true; 
/*  927 */         if (value.equals("nonpremultiplied")) {
/*  928 */           hasAlpha = true;
/*      */         }
/*      */       } 
/*      */     } 
/*  932 */     if (!hasAlpha) {
/*      */       return;
/*      */     }
/*  935 */     int num = (short)(numComps * (isPremultiplied ? 3 : 2));
/*  936 */     short[] channels = new short[num];
/*  937 */     short[] types = new short[num];
/*  938 */     short[] associations = new short[num];
/*  939 */     ChannelDefinitionBox.fillBasedOnBands(numComps, isPremultiplied, channels, types, associations);
/*      */     
/*  941 */     replace("JPEG2000ChannelDefinitionBox", new ChannelDefinitionBox(channels, types, associations));
/*      */   }
/*      */ 
/*      */   
/*      */   private void replace(String name, Box box) {
/*  946 */     for (int i = this.boxes.size() - 1; i >= 0; i--) {
/*  947 */       Box box1 = this.boxes.get(i);
/*  948 */       if (name.equals(Box.getName(box1.getType()))) {
/*  949 */         this.boxes.set(i, box);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*  954 */     this.boxes.add(box);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean insertNodeIntoTree(IIOMetadataNode root, IIOMetadataNode node) {
/*  959 */     String name = node.getNodeName();
/*  960 */     String parent = this.format.getParent(name);
/*  961 */     if (parent == null) {
/*  962 */       return false;
/*      */     }
/*  964 */     IIOMetadataNode parentNode = getNodeFromTree(root, parent, name);
/*  965 */     if (parentNode == null)
/*  966 */       parentNode = createNodeIntoTree(root, parent); 
/*  967 */     parentNode.appendChild(node);
/*  968 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private IIOMetadataNode getNodeFromTree(IIOMetadataNode root, String name, String childName) {
/*  974 */     if (name.equals(root.getNodeName())) {
/*  975 */       return root;
/*      */     }
/*  977 */     NodeList list = root.getChildNodes();
/*  978 */     for (int i = 0; i < list.getLength(); i++) {
/*  979 */       IIOMetadataNode node = (IIOMetadataNode)list.item(i);
/*  980 */       if (node.getNodeName().equals(name)) {
/*  981 */         if (!name.equals("JPEG2000UUIDInfoBox") || 
/*  982 */           !checkUUIDInfoBox(node, childName))
/*      */         {
/*      */           
/*  985 */           return node; } 
/*      */       } else {
/*  987 */         node = getNodeFromTree(node, name, childName);
/*  988 */         if (node != null)
/*  989 */           return node; 
/*      */       } 
/*      */     } 
/*  992 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private IIOMetadataNode createNodeIntoTree(IIOMetadataNode root, String name) {
/*  997 */     IIOMetadataNode node = getNodeFromTree(root, name, (String)null);
/*  998 */     if (node != null) {
/*  999 */       return node;
/*      */     }
/* 1001 */     node = new IIOMetadataNode(name);
/*      */     
/* 1003 */     String parent = this.format.getParent(name);
/* 1004 */     IIOMetadataNode parentNode = createNodeIntoTree(root, parent);
/* 1005 */     parentNode.appendChild(node);
/*      */     
/* 1007 */     return node;
/*      */   }
/*      */   
/*      */   private boolean isOriginalSigned(SampleModel sampleModel) {
/* 1011 */     int type = sampleModel.getDataType();
/* 1012 */     if (type == 0 || type == 1)
/* 1013 */       return false; 
/* 1014 */     return true;
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
/*      */   private boolean checkUUIDInfoBox(Node node, String childName) {
/* 1026 */     NodeList list = node.getChildNodes();
/* 1027 */     for (int i = 0; i < list.getLength(); i++) {
/* 1028 */       IIOMetadataNode child = (IIOMetadataNode)list.item(i);
/* 1029 */       String name = child.getNodeName();
/*      */       
/* 1031 */       if (name.equals(childName)) {
/* 1032 */         return true;
/*      */       }
/*      */     } 
/* 1035 */     return false;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */