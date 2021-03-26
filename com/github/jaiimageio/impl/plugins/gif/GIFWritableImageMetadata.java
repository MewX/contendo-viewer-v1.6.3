/*     */ package com.github.jaiimageio.impl.plugins.gif;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import org.w3c.dom.Node;
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
/*     */ class GIFWritableImageMetadata
/*     */   extends GIFImageMetadata
/*     */ {
/*     */   static final String NATIVE_FORMAT_NAME = "javax_imageio_gif_image_1.0";
/*     */   
/*     */   GIFWritableImageMetadata() {
/*  68 */     super(true, "javax_imageio_gif_image_1.0", "com.github.jaiimageio.impl.plugins.gif.GIFImageMetadataFormat", (String[])null, (String[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  80 */     this.imageLeftPosition = 0;
/*  81 */     this.imageTopPosition = 0;
/*  82 */     this.imageWidth = 0;
/*  83 */     this.imageHeight = 0;
/*  84 */     this.interlaceFlag = false;
/*  85 */     this.sortFlag = false;
/*  86 */     this.localColorTable = null;
/*     */ 
/*     */     
/*  89 */     this.disposalMethod = 0;
/*  90 */     this.userInputFlag = false;
/*  91 */     this.transparentColorFlag = false;
/*  92 */     this.delayTime = 0;
/*  93 */     this.transparentColorIndex = 0;
/*     */ 
/*     */     
/*  96 */     this.hasPlainTextExtension = false;
/*  97 */     this.textGridLeft = 0;
/*  98 */     this.textGridTop = 0;
/*  99 */     this.textGridWidth = 0;
/* 100 */     this.textGridHeight = 0;
/* 101 */     this.characterCellWidth = 0;
/* 102 */     this.characterCellHeight = 0;
/* 103 */     this.textForegroundColor = 0;
/* 104 */     this.textBackgroundColor = 0;
/* 105 */     this.text = null;
/*     */ 
/*     */     
/* 108 */     this.applicationIDs = null;
/* 109 */     this.authenticationCodes = null;
/* 110 */     this.applicationData = null;
/*     */ 
/*     */ 
/*     */     
/* 114 */     this.comments = null;
/*     */   }
/*     */   
/*     */   private byte[] fromISO8859(String data) {
/*     */     try {
/* 119 */       return data.getBytes("ISO-8859-1");
/* 120 */     } catch (UnsupportedEncodingException e) {
/* 121 */       return (new String("")).getBytes();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/* 126 */     Node node = root;
/* 127 */     if (!node.getNodeName().equals("javax_imageio_gif_image_1.0")) {
/* 128 */       fatal(node, "Root must be javax_imageio_gif_image_1.0");
/*     */     }
/*     */     
/* 131 */     node = node.getFirstChild();
/* 132 */     while (node != null) {
/* 133 */       String name = node.getNodeName();
/*     */       
/* 135 */       if (name.equals("ImageDescriptor")) {
/* 136 */         this.imageLeftPosition = getIntAttribute(node, "imageLeftPosition", -1, true, true, 0, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         this.imageTopPosition = getIntAttribute(node, "imageTopPosition", -1, true, true, 0, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 146 */         this.imageWidth = getIntAttribute(node, "imageWidth", -1, true, true, 1, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 151 */         this.imageHeight = getIntAttribute(node, "imageHeight", -1, true, true, 1, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 156 */         this.interlaceFlag = getBooleanAttribute(node, "interlaceFlag", false, true);
/*     */       }
/* 158 */       else if (name.equals("LocalColorTable")) {
/*     */         
/* 160 */         int sizeOfLocalColorTable = getIntAttribute(node, "sizeOfLocalColorTable", true, 2, 256);
/*     */         
/* 162 */         if (sizeOfLocalColorTable != 2 && sizeOfLocalColorTable != 4 && sizeOfLocalColorTable != 8 && sizeOfLocalColorTable != 16 && sizeOfLocalColorTable != 32 && sizeOfLocalColorTable != 64 && sizeOfLocalColorTable != 128 && sizeOfLocalColorTable != 256)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 170 */           fatal(node, "Bad value for LocalColorTable attribute sizeOfLocalColorTable!");
/*     */         }
/*     */ 
/*     */         
/* 174 */         this.sortFlag = getBooleanAttribute(node, "sortFlag", false, true);
/*     */         
/* 176 */         this.localColorTable = getColorTable(node, "ColorTableEntry", true, sizeOfLocalColorTable);
/*     */       }
/* 178 */       else if (name.equals("GraphicControlExtension")) {
/*     */         
/* 180 */         String disposalMethodName = getStringAttribute(node, "disposalMethod", null, true, disposalMethodNames);
/*     */         
/* 182 */         this.disposalMethod = 0;
/* 183 */         while (!disposalMethodName.equals(disposalMethodNames[this.disposalMethod])) {
/* 184 */           this.disposalMethod++;
/*     */         }
/*     */         
/* 187 */         this.userInputFlag = getBooleanAttribute(node, "userInputFlag", false, true);
/*     */ 
/*     */         
/* 190 */         this
/* 191 */           .transparentColorFlag = getBooleanAttribute(node, "transparentColorFlag", false, true);
/*     */ 
/*     */         
/* 194 */         this.delayTime = getIntAttribute(node, "delayTime", -1, true, true, 0, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 199 */         this
/* 200 */           .transparentColorIndex = getIntAttribute(node, "transparentColorIndex", -1, true, true, 0, 65535);
/*     */       
/*     */       }
/* 203 */       else if (name.equals("PlainTextExtension")) {
/* 204 */         this.hasPlainTextExtension = true;
/*     */         
/* 206 */         this.textGridLeft = getIntAttribute(node, "textGridLeft", -1, true, true, 0, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 211 */         this.textGridTop = getIntAttribute(node, "textGridTop", -1, true, true, 0, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 216 */         this.textGridWidth = getIntAttribute(node, "textGridWidth", -1, true, true, 1, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 221 */         this.textGridHeight = getIntAttribute(node, "textGridHeight", -1, true, true, 1, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 226 */         this.characterCellWidth = getIntAttribute(node, "characterCellWidth", -1, true, true, 1, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 231 */         this.characterCellHeight = getIntAttribute(node, "characterCellHeight", -1, true, true, 1, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 236 */         this.textForegroundColor = getIntAttribute(node, "textForegroundColor", -1, true, true, 0, 255);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 241 */         this.textBackgroundColor = getIntAttribute(node, "textBackgroundColor", -1, true, true, 0, 255);
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
/* 253 */         String textString = getStringAttribute(node, "text", "", false, null);
/* 254 */         this.text = fromISO8859(textString);
/* 255 */       } else if (name.equals("ApplicationExtensions")) {
/*     */         
/* 257 */         IIOMetadataNode applicationExtension = (IIOMetadataNode)node.getFirstChild();
/*     */         
/* 259 */         if (!applicationExtension.getNodeName().equals("ApplicationExtension")) {
/* 260 */           fatal(node, "Only a ApplicationExtension may be a child of a ApplicationExtensions!");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 265 */         String applicationIDString = getStringAttribute(applicationExtension, "applicationID", null, true, null);
/*     */ 
/*     */ 
/*     */         
/* 269 */         String authenticationCodeString = getStringAttribute(applicationExtension, "authenticationCode", null, true, null);
/*     */ 
/*     */ 
/*     */         
/* 273 */         Object applicationExtensionData = applicationExtension.getUserObject();
/* 274 */         if (applicationExtensionData == null || !(applicationExtensionData instanceof byte[]))
/*     */         {
/* 276 */           fatal(applicationExtension, "Bad user object in ApplicationExtension!");
/*     */         }
/*     */ 
/*     */         
/* 280 */         if (this.applicationIDs == null) {
/* 281 */           this.applicationIDs = new ArrayList();
/* 282 */           this.authenticationCodes = new ArrayList();
/* 283 */           this.applicationData = new ArrayList();
/*     */         } 
/*     */         
/* 286 */         this.applicationIDs.add(fromISO8859(applicationIDString));
/* 287 */         this.authenticationCodes.add(fromISO8859(authenticationCodeString));
/* 288 */         this.applicationData.add(applicationExtensionData);
/* 289 */       } else if (name.equals("CommentExtensions")) {
/* 290 */         Node commentExtension = node.getFirstChild();
/* 291 */         if (commentExtension != null) {
/* 292 */           while (commentExtension != null) {
/* 293 */             if (!commentExtension.getNodeName().equals("CommentExtension")) {
/* 294 */               fatal(node, "Only a CommentExtension may be a child of a CommentExtensions!");
/*     */             }
/*     */ 
/*     */             
/* 298 */             if (this.comments == null) {
/* 299 */               this.comments = new ArrayList();
/*     */             }
/*     */ 
/*     */             
/* 303 */             String comment = getStringAttribute(commentExtension, "value", null, true, null);
/*     */ 
/*     */             
/* 306 */             this.comments.add(fromISO8859(comment));
/*     */             
/* 308 */             commentExtension = commentExtension.getNextSibling();
/*     */           } 
/*     */         }
/*     */       } else {
/* 312 */         fatal(node, "Unknown child of root node!");
/*     */       } 
/*     */       
/* 315 */       node = node.getNextSibling();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/* 321 */     Node node = root;
/*     */     
/* 323 */     if (!node.getNodeName().equals("javax_imageio_1.0")) {
/* 324 */       fatal(node, "Root must be javax_imageio_1.0");
/*     */     }
/*     */ 
/*     */     
/* 328 */     node = node.getFirstChild();
/* 329 */     while (node != null) {
/* 330 */       String name = node.getNodeName();
/*     */       
/* 332 */       if (name.equals("Chroma")) {
/* 333 */         Node childNode = node.getFirstChild();
/* 334 */         while (childNode != null) {
/* 335 */           String childName = childNode.getNodeName();
/* 336 */           if (childName.equals("Palette")) {
/* 337 */             this.localColorTable = getColorTable(childNode, "PaletteEntry", false, -1);
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 342 */           childNode = childNode.getNextSibling();
/*     */         } 
/* 344 */       } else if (name.equals("Compression")) {
/* 345 */         Node childNode = node.getFirstChild();
/* 346 */         while (childNode != null) {
/* 347 */           String childName = childNode.getNodeName();
/* 348 */           if (childName.equals("NumProgressiveScans")) {
/*     */             
/* 350 */             int numProgressiveScans = getIntAttribute(childNode, "value", 4, false, true, 1, 2147483647);
/*     */             
/* 352 */             if (numProgressiveScans > 1) {
/* 353 */               this.interlaceFlag = true;
/*     */             }
/*     */             break;
/*     */           } 
/* 357 */           childNode = childNode.getNextSibling();
/*     */         } 
/* 359 */       } else if (name.equals("Dimension")) {
/* 360 */         Node childNode = node.getFirstChild();
/* 361 */         while (childNode != null) {
/* 362 */           String childName = childNode.getNodeName();
/* 363 */           if (childName.equals("HorizontalPixelOffset")) {
/* 364 */             this.imageLeftPosition = getIntAttribute(childNode, "value", -1, true, true, 0, 65535);
/*     */ 
/*     */           
/*     */           }
/* 368 */           else if (childName.equals("VerticalPixelOffset")) {
/* 369 */             this.imageTopPosition = getIntAttribute(childNode, "value", -1, true, true, 0, 65535);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 374 */           childNode = childNode.getNextSibling();
/*     */         } 
/* 376 */       } else if (name.equals("Text")) {
/* 377 */         Node childNode = node.getFirstChild();
/* 378 */         while (childNode != null) {
/* 379 */           String childName = childNode.getNodeName();
/* 380 */           if (childName.equals("TextEntry") && 
/* 381 */             getAttribute(childNode, "compression", "none", false)
/* 382 */             .equals("none") && 
/* 383 */             Charset.isSupported(getAttribute(childNode, "encoding", "ISO-8859-1", false))) {
/*     */ 
/*     */ 
/*     */             
/* 387 */             String value = getAttribute(childNode, "value");
/* 388 */             byte[] comment = fromISO8859(value);
/* 389 */             if (this.comments == null) {
/* 390 */               this.comments = new ArrayList();
/*     */             }
/* 392 */             this.comments.add(comment);
/*     */           } 
/* 394 */           childNode = childNode.getNextSibling();
/*     */         } 
/* 396 */       } else if (name.equals("Transparency")) {
/* 397 */         Node childNode = node.getFirstChild();
/* 398 */         while (childNode != null) {
/* 399 */           String childName = childNode.getNodeName();
/* 400 */           if (childName.equals("TransparentIndex")) {
/* 401 */             this.transparentColorIndex = getIntAttribute(childNode, "value", -1, true, true, 0, 255);
/*     */ 
/*     */ 
/*     */             
/* 405 */             this.transparentColorFlag = true;
/*     */             break;
/*     */           } 
/* 408 */           childNode = childNode.getNextSibling();
/*     */         } 
/*     */       } 
/*     */       
/* 412 */       node = node.getNextSibling();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 419 */     reset();
/* 420 */     mergeTree(formatName, root);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFWritableImageMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */