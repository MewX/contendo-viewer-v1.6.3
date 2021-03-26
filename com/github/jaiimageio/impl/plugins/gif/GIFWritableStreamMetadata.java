/*     */ package com.github.jaiimageio.impl.plugins.gif;
/*     */ 
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GIFWritableStreamMetadata
/*     */   extends GIFStreamMetadata
/*     */ {
/*     */   static final String NATIVE_FORMAT_NAME = "javax_imageio_gif_stream_1.0";
/*     */   
/*     */   public GIFWritableStreamMetadata() {
/*  69 */     super(true, "javax_imageio_gif_stream_1.0", "com.github.jaiimageio.impl.plugins.gif.GIFStreamMetadataFormat", (String[])null, (String[])null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     reset();
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/*  84 */     if (formatName.equals("javax_imageio_gif_stream_1.0")) {
/*  85 */       if (root == null) {
/*  86 */         throw new IllegalArgumentException("root == null!");
/*     */       }
/*  88 */       mergeNativeTree(root);
/*  89 */     } else if (formatName
/*  90 */       .equals("javax_imageio_1.0")) {
/*  91 */       if (root == null) {
/*  92 */         throw new IllegalArgumentException("root == null!");
/*     */       }
/*  94 */       mergeStandardTree(root);
/*     */     } else {
/*  96 */       throw new IllegalArgumentException("Not a recognized format!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset() {
/* 101 */     this.version = null;
/*     */     
/* 103 */     this.logicalScreenWidth = -1;
/* 104 */     this.logicalScreenHeight = -1;
/* 105 */     this.colorResolution = -1;
/* 106 */     this.pixelAspectRatio = 0;
/*     */     
/* 108 */     this.backgroundColorIndex = 0;
/* 109 */     this.sortFlag = false;
/* 110 */     this.globalColorTable = null;
/*     */   }
/*     */   
/*     */   protected void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/* 114 */     Node node = root;
/* 115 */     if (!node.getNodeName().equals("javax_imageio_gif_stream_1.0")) {
/* 116 */       fatal(node, "Root must be javax_imageio_gif_stream_1.0");
/*     */     }
/*     */     
/* 119 */     node = node.getFirstChild();
/* 120 */     while (node != null) {
/* 121 */       String name = node.getNodeName();
/*     */       
/* 123 */       if (name.equals("Version")) {
/* 124 */         this.version = getStringAttribute(node, "value", null, true, versionStrings);
/*     */       }
/* 126 */       else if (name.equals("LogicalScreenDescriptor")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 132 */         this.logicalScreenWidth = getIntAttribute(node, "logicalScreenWidth", -1, true, true, 1, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 138 */         this.logicalScreenHeight = getIntAttribute(node, "logicalScreenHeight", -1, true, true, 1, 65535);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 144 */         this.colorResolution = getIntAttribute(node, "colorResolution", -1, true, true, 1, 8);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 150 */         this.pixelAspectRatio = getIntAttribute(node, "pixelAspectRatio", 0, true, true, 0, 255);
/*     */ 
/*     */       
/*     */       }
/* 154 */       else if (name.equals("GlobalColorTable")) {
/*     */         
/* 156 */         int sizeOfGlobalColorTable = getIntAttribute(node, "sizeOfGlobalColorTable", true, 2, 256);
/*     */         
/* 158 */         if (sizeOfGlobalColorTable != 2 && sizeOfGlobalColorTable != 4 && sizeOfGlobalColorTable != 8 && sizeOfGlobalColorTable != 16 && sizeOfGlobalColorTable != 32 && sizeOfGlobalColorTable != 64 && sizeOfGlobalColorTable != 128 && sizeOfGlobalColorTable != 256)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 166 */           fatal(node, "Bad value for GlobalColorTable attribute sizeOfGlobalColorTable!");
/*     */         }
/*     */ 
/*     */         
/* 170 */         this.backgroundColorIndex = getIntAttribute(node, "backgroundColorIndex", 0, true, true, 0, 255);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 175 */         this.sortFlag = getBooleanAttribute(node, "sortFlag", false, true);
/*     */         
/* 177 */         this.globalColorTable = getColorTable(node, "ColorTableEntry", true, sizeOfGlobalColorTable);
/*     */       } else {
/*     */         
/* 180 */         fatal(node, "Unknown child of root node!");
/*     */       } 
/*     */       
/* 183 */       node = node.getNextSibling();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/* 189 */     Node node = root;
/*     */     
/* 191 */     if (!node.getNodeName().equals("javax_imageio_1.0")) {
/* 192 */       fatal(node, "Root must be javax_imageio_1.0");
/*     */     }
/*     */ 
/*     */     
/* 196 */     node = node.getFirstChild();
/* 197 */     while (node != null) {
/* 198 */       String name = node.getNodeName();
/*     */       
/* 200 */       if (name.equals("Chroma")) {
/* 201 */         Node childNode = node.getFirstChild();
/* 202 */         while (childNode != null) {
/* 203 */           String childName = childNode.getNodeName();
/* 204 */           if (childName.equals("Palette")) {
/* 205 */             this.globalColorTable = getColorTable(childNode, "PaletteEntry", false, -1);
/*     */ 
/*     */           
/*     */           }
/* 209 */           else if (childName.equals("BackgroundIndex")) {
/* 210 */             this.backgroundColorIndex = getIntAttribute(childNode, "value", -1, true, true, 0, 255);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 215 */           childNode = childNode.getNextSibling();
/*     */         } 
/* 217 */       } else if (name.equals("Data")) {
/* 218 */         Node childNode = node.getFirstChild();
/* 219 */         while (childNode != null) {
/* 220 */           String childName = childNode.getNodeName();
/* 221 */           if (childName.equals("BitsPerSample")) {
/* 222 */             this.colorResolution = getIntAttribute(childNode, "value", -1, true, true, 1, 8);
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 228 */           childNode = childNode.getNextSibling();
/*     */         } 
/* 230 */       } else if (name.equals("Dimension")) {
/* 231 */         Node childNode = node.getFirstChild();
/* 232 */         while (childNode != null) {
/* 233 */           String childName = childNode.getNodeName();
/* 234 */           if (childName.equals("PixelAspectRatio")) {
/* 235 */             float aspectRatio = getFloatAttribute(childNode, "value");
/*     */             
/* 237 */             if (aspectRatio == 1.0F) {
/* 238 */               this.pixelAspectRatio = 0;
/*     */             } else {
/* 240 */               int ratio = (int)(aspectRatio * 64.0F - 15.0F);
/* 241 */               this
/* 242 */                 .pixelAspectRatio = Math.max(Math.min(ratio, 255), 0);
/*     */             } 
/* 244 */           } else if (childName.equals("HorizontalScreenSize")) {
/* 245 */             this.logicalScreenWidth = getIntAttribute(childNode, "value", -1, true, true, 1, 65535);
/*     */ 
/*     */           
/*     */           }
/* 249 */           else if (childName.equals("VerticalScreenSize")) {
/* 250 */             this.logicalScreenHeight = getIntAttribute(childNode, "value", -1, true, true, 1, 65535);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 255 */           childNode = childNode.getNextSibling();
/*     */         } 
/* 257 */       } else if (name.equals("Document")) {
/* 258 */         Node childNode = node.getFirstChild();
/* 259 */         while (childNode != null) {
/* 260 */           String childName = childNode.getNodeName();
/* 261 */           if (childName.equals("FormatVersion")) {
/*     */             
/* 263 */             String formatVersion = getStringAttribute(childNode, "value", null, true, null);
/*     */             
/* 265 */             for (int i = 0; i < versionStrings.length; i++) {
/* 266 */               if (formatVersion.equals(versionStrings[i])) {
/* 267 */                 this.version = formatVersion;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             break;
/*     */           } 
/* 273 */           childNode = childNode.getNextSibling();
/*     */         } 
/*     */       } 
/*     */       
/* 277 */       node = node.getNextSibling();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 284 */     reset();
/* 285 */     mergeTree(formatName, root);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFWritableStreamMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */