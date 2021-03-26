/*     */ package com.github.jaiimageio.impl.plugins.pnm;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import com.github.jaiimageio.plugins.pnm.PNMImageWriteParam;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class PNMMetadata
/*     */   extends IIOMetadata
/*     */   implements Cloneable
/*     */ {
/*     */   static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_pnm_image_1.0";
/*     */   private int maxSample;
/*     */   private int width;
/*     */   private int height;
/*     */   private int variant;
/*     */   private ArrayList comments;
/*     */   private int maxSampleSize;
/*     */   
/*     */   PNMMetadata() {
/* 113 */     super(true, "com_sun_media_imageio_plugins_pnm_image_1.0", "com.github.jaiimageio.impl.plugins.pnm.PNMMetadataFormat", null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNMMetadata(IIOMetadata metadata) throws IIOInvalidTreeException {
/* 121 */     this();
/*     */     
/* 123 */     if (metadata != null) {
/* 124 */       List<String> formats = Arrays.asList(metadata.getMetadataFormatNames());
/*     */       
/* 126 */       if (formats.contains("com_sun_media_imageio_plugins_pnm_image_1.0")) {
/*     */         
/* 128 */         setFromTree("com_sun_media_imageio_plugins_pnm_image_1.0", metadata
/* 129 */             .getAsTree("com_sun_media_imageio_plugins_pnm_image_1.0"));
/* 130 */       } else if (metadata.isStandardMetadataFormatSupported()) {
/*     */         
/* 132 */         String format = "javax_imageio_1.0";
/*     */         
/* 134 */         setFromTree(format, metadata.getAsTree(format));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PNMMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 145 */     this();
/* 146 */     initialize(imageType, param);
/*     */   }
/*     */ 
/*     */   
/*     */   void initialize(ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 151 */     ImageTypeSpecifier destType = null;
/*     */     
/* 153 */     if (param != null) {
/* 154 */       destType = param.getDestinationType();
/* 155 */       if (destType == null) {
/* 156 */         destType = imageType;
/*     */       }
/*     */     } else {
/* 159 */       destType = imageType;
/*     */     } 
/*     */     
/* 162 */     if (destType != null) {
/* 163 */       SampleModel sm = destType.getSampleModel();
/* 164 */       int[] sampleSize = sm.getSampleSize();
/*     */       
/* 166 */       this.width = sm.getWidth();
/* 167 */       this.height = sm.getHeight();
/*     */       
/* 169 */       for (int i = 0; i < sampleSize.length; i++) {
/* 170 */         if (sampleSize[i] > this.maxSampleSize) {
/* 171 */           this.maxSampleSize = sampleSize[i];
/*     */         }
/*     */       } 
/* 174 */       this.maxSample = (1 << this.maxSampleSize) - 1;
/*     */       
/* 176 */       boolean isRaw = true;
/* 177 */       if (param instanceof PNMImageWriteParam) {
/* 178 */         isRaw = ((PNMImageWriteParam)param).getRaw();
/*     */       }
/*     */       
/* 181 */       if (this.maxSampleSize == 1) {
/* 182 */         this.variant = 49;
/* 183 */       } else if (sm.getNumBands() == 1) {
/* 184 */         this.variant = 50;
/* 185 */       } else if (sm.getNumBands() == 3) {
/* 186 */         this.variant = 51;
/*     */       } 
/*     */ 
/*     */       
/* 190 */       if (this.variant <= 51 && isRaw && this.maxSampleSize <= 8) {
/* 191 */         this.variant += 3;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object clone() {
/* 197 */     PNMMetadata theClone = null;
/*     */     
/*     */     try {
/* 200 */       theClone = (PNMMetadata)super.clone();
/* 201 */     } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*     */     
/* 203 */     if (this.comments != null) {
/* 204 */       int numComments = this.comments.size();
/* 205 */       for (int i = 0; i < numComments; i++) {
/* 206 */         theClone.addComment(this.comments.get(i));
/*     */       }
/*     */     } 
/* 209 */     return theClone;
/*     */   }
/*     */   
/*     */   public Node getAsTree(String formatName) {
/* 213 */     if (formatName == null) {
/* 214 */       throw new IllegalArgumentException(I18N.getString("PNMMetadata0"));
/*     */     }
/*     */     
/* 217 */     if (formatName.equals("com_sun_media_imageio_plugins_pnm_image_1.0")) {
/* 218 */       return getNativeTree();
/*     */     }
/*     */     
/* 221 */     if (formatName
/* 222 */       .equals("javax_imageio_1.0")) {
/* 223 */       return getStandardTree();
/*     */     }
/*     */     
/* 226 */     throw new IllegalArgumentException(I18N.getString("PNMMetadata1") + " " + formatName);
/*     */   }
/*     */ 
/*     */   
/*     */   IIOMetadataNode getNativeTree() {
/* 231 */     IIOMetadataNode root = new IIOMetadataNode("com_sun_media_imageio_plugins_pnm_image_1.0");
/*     */ 
/*     */     
/* 234 */     IIOMetadataNode child = new IIOMetadataNode("FormatName");
/* 235 */     child.setUserObject(getFormatName());
/* 236 */     child.setNodeValue(getFormatName());
/* 237 */     root.appendChild(child);
/*     */     
/* 239 */     child = new IIOMetadataNode("Variant");
/* 240 */     child.setUserObject(getVariant());
/* 241 */     child.setNodeValue(getVariant());
/* 242 */     root.appendChild(child);
/*     */     
/* 244 */     child = new IIOMetadataNode("Width");
/* 245 */     Object tmp = new Integer(this.width);
/* 246 */     child.setUserObject(tmp);
/* 247 */     child.setNodeValue(ImageUtil.convertObjectToString(tmp));
/* 248 */     root.appendChild(child);
/*     */     
/* 250 */     child = new IIOMetadataNode("Height");
/* 251 */     tmp = new Integer(this.height);
/* 252 */     child.setUserObject(tmp);
/* 253 */     child.setNodeValue(ImageUtil.convertObjectToString(tmp));
/* 254 */     root.appendChild(child);
/*     */     
/* 256 */     child = new IIOMetadataNode("MaximumSample");
/* 257 */     tmp = new Byte((byte)this.maxSample);
/* 258 */     child.setUserObject(tmp);
/* 259 */     child.setNodeValue(ImageUtil.convertObjectToString(new Integer(this.maxSample)));
/* 260 */     root.appendChild(child);
/*     */     
/* 262 */     if (this.comments != null) {
/* 263 */       for (int i = 0; i < this.comments.size(); i++) {
/* 264 */         child = new IIOMetadataNode("Comment");
/* 265 */         tmp = this.comments.get(i);
/* 266 */         child.setUserObject(tmp);
/* 267 */         child.setNodeValue(ImageUtil.convertObjectToString(tmp));
/* 268 */         root.appendChild(child);
/*     */       } 
/*     */     }
/*     */     
/* 272 */     return root;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IIOMetadataNode getStandardChromaNode() {
/* 277 */     IIOMetadataNode node = new IIOMetadataNode("Chroma");
/*     */     
/* 279 */     int temp = (this.variant - 49) % 3 + 1;
/*     */     
/* 281 */     IIOMetadataNode subNode = new IIOMetadataNode("ColorSpaceType");
/* 282 */     if (temp == 3) {
/* 283 */       subNode.setAttribute("name", "RGB");
/*     */     } else {
/* 285 */       subNode.setAttribute("name", "GRAY");
/*     */     } 
/* 287 */     node.appendChild(subNode);
/*     */     
/* 289 */     subNode = new IIOMetadataNode("NumChannels");
/* 290 */     subNode.setAttribute("value", "" + ((temp == 3) ? 3 : 1));
/* 291 */     node.appendChild(subNode);
/*     */     
/* 293 */     if (temp != 3) {
/* 294 */       subNode = new IIOMetadataNode("BlackIsZero");
/* 295 */       subNode.setAttribute("value", "TRUE");
/* 296 */       node.appendChild(subNode);
/*     */     } 
/*     */     
/* 299 */     return node;
/*     */   }
/*     */   
/*     */   protected IIOMetadataNode getStandardDataNode() {
/* 303 */     IIOMetadataNode node = new IIOMetadataNode("Data");
/*     */     
/* 305 */     IIOMetadataNode subNode = new IIOMetadataNode("SampleFormat");
/* 306 */     subNode.setAttribute("value", "UnsignedIntegral");
/* 307 */     node.appendChild(subNode);
/*     */     
/* 309 */     int temp = (this.variant - 49) % 3 + 1;
/* 310 */     subNode = new IIOMetadataNode("BitsPerSample");
/* 311 */     if (temp == 1) {
/* 312 */       subNode.setAttribute("value", "1");
/* 313 */     } else if (temp == 2) {
/* 314 */       subNode.setAttribute("value", "8");
/*     */     } else {
/* 316 */       subNode.setAttribute("value", "8 8 8");
/*     */     } 
/* 318 */     node.appendChild(subNode);
/*     */     
/* 320 */     subNode = new IIOMetadataNode("SignificantBitsPerSample");
/* 321 */     if (temp == 1 || temp == 2) {
/* 322 */       subNode.setAttribute("value", "" + this.maxSampleSize);
/*     */     } else {
/* 324 */       subNode.setAttribute("value", this.maxSampleSize + " " + this.maxSampleSize + " " + this.maxSampleSize);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 329 */     node.appendChild(subNode);
/*     */     
/* 331 */     return node;
/*     */   }
/*     */   
/*     */   protected IIOMetadataNode getStandardDimensionNode() {
/* 335 */     IIOMetadataNode node = new IIOMetadataNode("Dimension");
/*     */     
/* 337 */     IIOMetadataNode subNode = new IIOMetadataNode("ImageOrientation");
/* 338 */     subNode.setAttribute("value", "Normal");
/* 339 */     node.appendChild(subNode);
/*     */     
/* 341 */     return node;
/*     */   }
/*     */   
/*     */   protected IIOMetadataNode getStandardTextNode() {
/* 345 */     if (this.comments != null) {
/* 346 */       IIOMetadataNode node = new IIOMetadataNode("Text");
/* 347 */       Iterator<String> iter = this.comments.iterator();
/* 348 */       while (iter.hasNext()) {
/* 349 */         String comment = iter.next();
/* 350 */         IIOMetadataNode subNode = new IIOMetadataNode("TextEntry");
/* 351 */         subNode.setAttribute("keyword", "comment");
/* 352 */         subNode.setAttribute("value", comment);
/* 353 */         node.appendChild(subNode);
/*     */       } 
/* 355 */       return node;
/*     */     } 
/* 357 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/* 361 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 366 */     if (formatName == null) {
/* 367 */       throw new IllegalArgumentException(I18N.getString("PNMMetadata0"));
/*     */     }
/*     */     
/* 370 */     if (root == null) {
/* 371 */       throw new IllegalArgumentException(I18N.getString("PNMMetadata2"));
/*     */     }
/*     */     
/* 374 */     if (formatName.equals("com_sun_media_imageio_plugins_pnm_image_1.0") && root
/* 375 */       .getNodeName().equals("com_sun_media_imageio_plugins_pnm_image_1.0")) {
/* 376 */       mergeNativeTree(root);
/* 377 */     } else if (formatName
/* 378 */       .equals("javax_imageio_1.0")) {
/* 379 */       mergeStandardTree(root);
/*     */     } else {
/* 381 */       throw new IllegalArgumentException(I18N.getString("PNMMetadata1") + " " + formatName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 388 */     if (formatName == null) {
/* 389 */       throw new IllegalArgumentException(I18N.getString("PNMMetadata0"));
/*     */     }
/*     */     
/* 392 */     if (root == null) {
/* 393 */       throw new IllegalArgumentException(I18N.getString("PNMMetadata2"));
/*     */     }
/*     */     
/* 396 */     if (formatName.equals("com_sun_media_imageio_plugins_pnm_image_1.0") && root
/* 397 */       .getNodeName().equals("com_sun_media_imageio_plugins_pnm_image_1.0")) {
/* 398 */       mergeNativeTree(root);
/* 399 */     } else if (formatName
/* 400 */       .equals("javax_imageio_1.0")) {
/* 401 */       mergeStandardTree(root);
/*     */     } else {
/* 403 */       throw new IllegalArgumentException(I18N.getString("PNMMetadata2") + " " + formatName);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 409 */     this.maxSample = this.width = this.height = this.variant = this.maxSampleSize = 0;
/* 410 */     this.comments = null;
/*     */   }
/*     */   
/*     */   public String getFormatName() {
/* 414 */     int v = (this.variant - 49) % 3 + 1;
/* 415 */     if (v == 1)
/* 416 */       return "PBM"; 
/* 417 */     if (v == 2)
/* 418 */       return "PGM"; 
/* 419 */     if (v == 3)
/* 420 */       return "PPM"; 
/* 421 */     return null;
/*     */   }
/*     */   
/*     */   public String getVariant() {
/* 425 */     if (this.variant > 51)
/* 426 */       return "RAWBITS"; 
/* 427 */     return "ASCII";
/*     */   }
/*     */   
/*     */   boolean isRaw() {
/* 431 */     return getVariant().equals("RAWBITS");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVariant(int v) {
/* 436 */     this.variant = v;
/*     */   }
/*     */   
/*     */   public void setWidth(int w) {
/* 440 */     this.width = w;
/*     */   }
/*     */   
/*     */   public void setHeight(int h) {
/* 444 */     this.height = h;
/*     */   }
/*     */   
/*     */   int getMaxBitDepth() {
/* 448 */     return this.maxSampleSize;
/*     */   }
/*     */   
/*     */   int getMaxValue() {
/* 452 */     return this.maxSample;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxBitDepth(int maxValue) {
/* 460 */     this.maxSample = maxValue;
/*     */     
/* 462 */     this.maxSampleSize = 0;
/* 463 */     while (maxValue > 0) {
/* 464 */       maxValue >>>= 1;
/* 465 */       this.maxSampleSize++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void addComment(String comment) {
/* 470 */     if (this.comments == null) {
/* 471 */       this.comments = new ArrayList();
/*     */     }
/* 473 */     comment = comment.replaceAll("[\n\r\f]", " ");
/* 474 */     this.comments.add(comment);
/*     */   }
/*     */   
/*     */   Iterator getComments() {
/* 478 */     return (this.comments == null) ? null : this.comments.iterator();
/*     */   }
/*     */   
/*     */   private void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/* 482 */     NodeList list = root.getChildNodes();
/* 483 */     String format = null;
/* 484 */     String var = null;
/*     */     
/* 486 */     for (int i = list.getLength() - 1; i >= 0; i--) {
/* 487 */       IIOMetadataNode node = (IIOMetadataNode)list.item(i);
/* 488 */       String name = node.getNodeName();
/*     */       
/* 490 */       if (name.equals("Comment")) {
/* 491 */         addComment((String)node.getUserObject());
/* 492 */       } else if (name.equals("Width")) {
/* 493 */         this.width = ((Integer)node.getUserObject()).intValue();
/* 494 */       } else if (name.equals("Height")) {
/* 495 */         this.width = ((Integer)node.getUserObject()).intValue();
/* 496 */       } else if (name.equals("MaximumSample")) {
/* 497 */         int maxValue = ((Integer)node.getUserObject()).intValue();
/* 498 */         setMaxBitDepth(maxValue);
/* 499 */       } else if (name.equals("FormatName")) {
/* 500 */         format = (String)node.getUserObject();
/* 501 */       } else if (name.equals("Variant")) {
/* 502 */         var = (String)node.getUserObject();
/*     */       } 
/*     */     } 
/*     */     
/* 506 */     if (format.equals("PBM")) {
/* 507 */       this.variant = 49;
/* 508 */     } else if (format.equals("PGM")) {
/* 509 */       this.variant = 50;
/* 510 */     } else if (format.equals("PPM")) {
/* 511 */       this.variant = 51;
/*     */     } 
/* 513 */     if (var.equals("RAWBITS"))
/* 514 */       this.variant += 3; 
/*     */   }
/*     */   
/*     */   private void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/* 518 */     NodeList children = root.getChildNodes();
/*     */     
/* 520 */     String colorSpace = null;
/* 521 */     int numComps = 0;
/* 522 */     int[] bitsPerSample = null;
/*     */     
/* 524 */     for (int i = 0; i < children.getLength(); i++) {
/* 525 */       Node node = children.item(i);
/* 526 */       String name = node.getNodeName();
/* 527 */       if (name.equals("Chroma")) {
/* 528 */         NodeList children1 = node.getChildNodes();
/* 529 */         for (int j = 0; j < children1.getLength(); j++) {
/* 530 */           Node child = children1.item(j);
/* 531 */           String name1 = child.getNodeName();
/*     */           
/* 533 */           if (name1.equals("NumChannels")) {
/* 534 */             String s = (String)getAttribute(child, "value");
/* 535 */             numComps = (new Integer(s)).intValue();
/* 536 */           } else if (name1.equals("ColorSpaceType")) {
/* 537 */             colorSpace = (String)getAttribute(child, "name");
/*     */           } 
/*     */         } 
/* 540 */       } else if (!name.equals("Compression")) {
/*     */         
/* 542 */         if (name.equals("Data")) {
/* 543 */           NodeList children1 = node.getChildNodes();
/* 544 */           int maxBitDepth = -1;
/* 545 */           for (int j = 0; j < children1.getLength(); j++) {
/* 546 */             Node child = children1.item(j);
/* 547 */             String name1 = child.getNodeName();
/*     */             
/* 549 */             if (name1.equals("BitsPerSample")) {
/* 550 */               List<Integer> bps = new ArrayList(3);
/* 551 */               String s = (String)getAttribute(child, "value");
/* 552 */               StringTokenizer t = new StringTokenizer(s);
/* 553 */               while (t.hasMoreTokens()) {
/* 554 */                 bps.add(Integer.valueOf(t.nextToken()));
/*     */               }
/* 556 */               bitsPerSample = new int[bps.size()];
/* 557 */               for (int k = 0; k < bitsPerSample.length; k++) {
/* 558 */                 bitsPerSample[k] = ((Integer)bps
/* 559 */                   .get(k)).intValue();
/*     */               }
/* 561 */             } else if (name1.equals("SignificantBitsPerSample")) {
/* 562 */               String s = (String)getAttribute(child, "value");
/* 563 */               StringTokenizer t = new StringTokenizer(s);
/* 564 */               while (t.hasMoreTokens()) {
/*     */                 
/* 566 */                 int sbps = Integer.valueOf(t.nextToken()).intValue();
/* 567 */                 maxBitDepth = Math.max(sbps, maxBitDepth);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 573 */           if (maxBitDepth > 0) {
/* 574 */             setMaxBitDepth((1 << maxBitDepth) - 1);
/* 575 */           } else if (bitsPerSample != null) {
/* 576 */             for (int k = 0; k < bitsPerSample.length; k++) {
/* 577 */               if (bitsPerSample[k] > maxBitDepth) {
/* 578 */                 maxBitDepth = bitsPerSample[k];
/*     */               }
/*     */             } 
/* 581 */             setMaxBitDepth((1 << maxBitDepth) - 1);
/*     */           } 
/* 583 */         } else if (!name.equals("Dimension")) {
/*     */           
/* 585 */           if (!name.equals("Document"))
/*     */           {
/* 587 */             if (name.equals("Text")) {
/* 588 */               NodeList children1 = node.getChildNodes();
/* 589 */               for (int j = 0; j < children1.getLength(); j++) {
/* 590 */                 Node child = children1.item(j);
/* 591 */                 String name1 = child.getNodeName();
/*     */                 
/* 593 */                 if (name1.equals("TextEntry")) {
/* 594 */                   addComment((String)getAttribute(child, "value"));
/*     */                 }
/*     */               } 
/* 597 */             } else if (!name.equals("Transparency")) {
/*     */ 
/*     */               
/* 600 */               throw new IIOInvalidTreeException(I18N.getString("PNMMetadata3") + " " + name, node);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 606 */     if ((colorSpace != null && colorSpace.equals("RGB")) || numComps > 1 || bitsPerSample.length > 1) {
/*     */ 
/*     */       
/* 609 */       this.variant = 51;
/* 610 */     } else if (this.maxSampleSize > 1) {
/* 611 */       this.variant = 50;
/*     */     } else {
/* 613 */       this.variant = 49;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getAttribute(Node node, String name) {
/* 618 */     NamedNodeMap map = node.getAttributes();
/* 619 */     node = map.getNamedItem(name);
/* 620 */     return (node != null) ? node.getNodeValue() : null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pnm/PNMMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */