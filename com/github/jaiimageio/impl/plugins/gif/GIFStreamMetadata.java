/*     */ package com.github.jaiimageio.impl.plugins.gif;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GIFStreamMetadata
/*     */   extends GIFMetadata
/*     */ {
/*     */   static final String nativeMetadataFormatName = "javax_imageio_gif_stream_1.0";
/*  67 */   public static final String[] versionStrings = new String[] { "87a", "89a" };
/*     */   
/*     */   public String version;
/*     */   
/*     */   public int logicalScreenWidth;
/*     */   
/*     */   public int logicalScreenHeight;
/*     */   public int colorResolution;
/*     */   public int pixelAspectRatio;
/*     */   public int backgroundColorIndex;
/*     */   public boolean sortFlag;
/*  78 */   public static final String[] colorTableSizes = new String[] { "2", "4", "8", "16", "32", "64", "128", "256" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public byte[] globalColorTable = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GIFStreamMetadata(boolean standardMetadataFormatSupported, String nativeMetadataFormatName, String nativeMetadataFormatClassName, String[] extraMetadataFormatNames, String[] extraMetadataFormatClassNames) {
/*  91 */     super(standardMetadataFormatSupported, nativeMetadataFormatName, nativeMetadataFormatClassName, extraMetadataFormatNames, extraMetadataFormatClassNames);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GIFStreamMetadata() {
/*  99 */     this(true, "javax_imageio_gif_stream_1.0", "com.github.jaiimageio.impl.plugins.gif.GIFStreamMetadataFormat", null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/* 107 */     return true;
/*     */   }
/*     */   
/*     */   public Node getAsTree(String formatName) {
/* 111 */     if (formatName.equals("javax_imageio_gif_stream_1.0"))
/* 112 */       return getNativeTree(); 
/* 113 */     if (formatName
/* 114 */       .equals("javax_imageio_1.0")) {
/* 115 */       return getStandardTree();
/*     */     }
/* 117 */     throw new IllegalArgumentException("Not a recognized format!");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Node getNativeTree() {
/* 123 */     IIOMetadataNode root = new IIOMetadataNode("javax_imageio_gif_stream_1.0");
/*     */ 
/*     */     
/* 126 */     IIOMetadataNode node = new IIOMetadataNode("Version");
/* 127 */     node.setAttribute("value", this.version);
/* 128 */     root.appendChild(node);
/*     */ 
/*     */     
/* 131 */     node = new IIOMetadataNode("LogicalScreenDescriptor");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     node.setAttribute("logicalScreenWidth", (this.logicalScreenWidth == -1) ? "" : 
/*     */         
/* 138 */         Integer.toString(this.logicalScreenWidth));
/* 139 */     node.setAttribute("logicalScreenHeight", (this.logicalScreenHeight == -1) ? "" : 
/*     */         
/* 141 */         Integer.toString(this.logicalScreenHeight));
/*     */     
/* 143 */     node.setAttribute("colorResolution", (this.colorResolution == -1) ? "" : 
/*     */         
/* 145 */         Integer.toString(this.colorResolution));
/* 146 */     node.setAttribute("pixelAspectRatio", 
/* 147 */         Integer.toString(this.pixelAspectRatio));
/* 148 */     root.appendChild(node);
/*     */     
/* 150 */     if (this.globalColorTable != null) {
/* 151 */       node = new IIOMetadataNode("GlobalColorTable");
/* 152 */       int numEntries = this.globalColorTable.length / 3;
/* 153 */       node.setAttribute("sizeOfGlobalColorTable", 
/* 154 */           Integer.toString(numEntries));
/* 155 */       node.setAttribute("backgroundColorIndex", 
/* 156 */           Integer.toString(this.backgroundColorIndex));
/* 157 */       node.setAttribute("sortFlag", this.sortFlag ? "TRUE" : "FALSE");
/*     */ 
/*     */       
/* 160 */       for (int i = 0; i < numEntries; i++) {
/* 161 */         IIOMetadataNode entry = new IIOMetadataNode("ColorTableEntry");
/*     */         
/* 163 */         entry.setAttribute("index", Integer.toString(i));
/* 164 */         int r = this.globalColorTable[3 * i] & 0xFF;
/* 165 */         int g = this.globalColorTable[3 * i + 1] & 0xFF;
/* 166 */         int b = this.globalColorTable[3 * i + 2] & 0xFF;
/* 167 */         entry.setAttribute("red", Integer.toString(r));
/* 168 */         entry.setAttribute("green", Integer.toString(g));
/* 169 */         entry.setAttribute("blue", Integer.toString(b));
/* 170 */         node.appendChild(entry);
/*     */       } 
/* 172 */       root.appendChild(node);
/*     */     } 
/*     */     
/* 175 */     return root;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardChromaNode() {
/* 179 */     IIOMetadataNode chroma_node = new IIOMetadataNode("Chroma");
/* 180 */     IIOMetadataNode node = null;
/*     */     
/* 182 */     node = new IIOMetadataNode("ColorSpaceType");
/* 183 */     node.setAttribute("name", "RGB");
/* 184 */     chroma_node.appendChild(node);
/*     */     
/* 186 */     node = new IIOMetadataNode("BlackIsZero");
/* 187 */     node.setAttribute("value", "TRUE");
/* 188 */     chroma_node.appendChild(node);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     if (this.globalColorTable != null) {
/* 194 */       node = new IIOMetadataNode("Palette");
/* 195 */       int numEntries = this.globalColorTable.length / 3;
/* 196 */       for (int i = 0; i < numEntries; i++) {
/* 197 */         IIOMetadataNode entry = new IIOMetadataNode("PaletteEntry");
/*     */         
/* 199 */         entry.setAttribute("index", Integer.toString(i));
/* 200 */         entry.setAttribute("red", 
/* 201 */             Integer.toString(this.globalColorTable[3 * i] & 0xFF));
/* 202 */         entry.setAttribute("green", 
/* 203 */             Integer.toString(this.globalColorTable[3 * i + 1] & 0xFF));
/* 204 */         entry.setAttribute("blue", 
/* 205 */             Integer.toString(this.globalColorTable[3 * i + 2] & 0xFF));
/* 206 */         node.appendChild(entry);
/*     */       } 
/* 208 */       chroma_node.appendChild(node);
/*     */ 
/*     */       
/* 211 */       node = new IIOMetadataNode("BackgroundIndex");
/* 212 */       node.setAttribute("value", Integer.toString(this.backgroundColorIndex));
/* 213 */       chroma_node.appendChild(node);
/*     */     } 
/*     */     
/* 216 */     return chroma_node;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardCompressionNode() {
/* 220 */     IIOMetadataNode compression_node = new IIOMetadataNode("Compression");
/* 221 */     IIOMetadataNode node = null;
/*     */     
/* 223 */     node = new IIOMetadataNode("CompressionTypeName");
/* 224 */     node.setAttribute("value", "lzw");
/* 225 */     compression_node.appendChild(node);
/*     */     
/* 227 */     node = new IIOMetadataNode("Lossless");
/* 228 */     node.setAttribute("value", "true");
/* 229 */     compression_node.appendChild(node);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     return compression_node;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardDataNode() {
/* 238 */     IIOMetadataNode data_node = new IIOMetadataNode("Data");
/* 239 */     IIOMetadataNode node = null;
/*     */ 
/*     */ 
/*     */     
/* 243 */     node = new IIOMetadataNode("SampleFormat");
/* 244 */     node.setAttribute("value", "Index");
/* 245 */     data_node.appendChild(node);
/*     */     
/* 247 */     node = new IIOMetadataNode("BitsPerSample");
/* 248 */     node.setAttribute("value", (this.colorResolution == -1) ? "" : 
/*     */         
/* 250 */         Integer.toString(this.colorResolution));
/* 251 */     data_node.appendChild(node);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     return data_node;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardDimensionNode() {
/* 260 */     IIOMetadataNode dimension_node = new IIOMetadataNode("Dimension");
/* 261 */     IIOMetadataNode node = null;
/*     */     
/* 263 */     node = new IIOMetadataNode("PixelAspectRatio");
/* 264 */     float aspectRatio = 1.0F;
/* 265 */     if (this.pixelAspectRatio != 0) {
/* 266 */       aspectRatio = (this.pixelAspectRatio + 15) / 64.0F;
/*     */     }
/* 268 */     node.setAttribute("value", Float.toString(aspectRatio));
/* 269 */     dimension_node.appendChild(node);
/*     */     
/* 271 */     node = new IIOMetadataNode("ImageOrientation");
/* 272 */     node.setAttribute("value", "Normal");
/* 273 */     dimension_node.appendChild(node);
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
/* 284 */     node = new IIOMetadataNode("HorizontalScreenSize");
/* 285 */     node.setAttribute("value", (this.logicalScreenWidth == -1) ? "" : 
/*     */         
/* 287 */         Integer.toString(this.logicalScreenWidth));
/* 288 */     dimension_node.appendChild(node);
/*     */     
/* 290 */     node = new IIOMetadataNode("VerticalScreenSize");
/* 291 */     node.setAttribute("value", (this.logicalScreenHeight == -1) ? "" : 
/*     */         
/* 293 */         Integer.toString(this.logicalScreenHeight));
/* 294 */     dimension_node.appendChild(node);
/*     */     
/* 296 */     return dimension_node;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardDocumentNode() {
/* 300 */     IIOMetadataNode document_node = new IIOMetadataNode("Document");
/* 301 */     IIOMetadataNode node = null;
/*     */     
/* 303 */     node = new IIOMetadataNode("FormatVersion");
/* 304 */     node.setAttribute("value", this.version);
/* 305 */     document_node.appendChild(node);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     return document_node;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getStandardTextNode() {
/* 316 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getStandardTransparencyNode() {
/* 321 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 327 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/* 332 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/* 337 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */   
/*     */   public void reset() {
/* 341 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFStreamMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */