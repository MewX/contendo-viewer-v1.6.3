/*     */ package com.github.jaiimageio.impl.plugins.gif;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class GIFImageMetadata
/*     */   extends GIFMetadata
/*     */ {
/*     */   static final String nativeMetadataFormatName = "javax_imageio_gif_image_1.0";
/*  69 */   static final String[] disposalMethodNames = new String[] { "none", "doNotDispose", "restoreToBackgroundColor", "restoreToPrevious", "undefinedDisposalMethod4", "undefinedDisposalMethod5", "undefinedDisposalMethod6", "undefinedDisposalMethod7" };
/*     */ 
/*     */   
/*     */   public int imageLeftPosition;
/*     */ 
/*     */   
/*     */   public int imageTopPosition;
/*     */ 
/*     */   
/*     */   public int imageWidth;
/*     */ 
/*     */   
/*     */   public int imageHeight;
/*     */   
/*     */   public boolean interlaceFlag = false;
/*     */   
/*     */   public boolean sortFlag = false;
/*     */   
/*  87 */   public byte[] localColorTable = null;
/*     */ 
/*     */   
/*  90 */   public int disposalMethod = 0;
/*     */   public boolean userInputFlag = false;
/*     */   public boolean transparentColorFlag = false;
/*  93 */   public int delayTime = 0;
/*  94 */   public int transparentColorIndex = 0;
/*     */   
/*     */   public boolean hasPlainTextExtension = false;
/*     */   
/*     */   public int textGridLeft;
/*     */   
/*     */   public int textGridTop;
/*     */   
/*     */   public int textGridWidth;
/*     */   
/*     */   public int textGridHeight;
/*     */   public int characterCellWidth;
/*     */   public int characterCellHeight;
/*     */   public int textForegroundColor;
/*     */   public int textBackgroundColor;
/*     */   public byte[] text;
/* 110 */   public List applicationIDs = null;
/*     */ 
/*     */   
/* 113 */   public List authenticationCodes = null;
/*     */ 
/*     */   
/* 116 */   public List applicationData = null;
/*     */ 
/*     */ 
/*     */   
/* 120 */   public List comments = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GIFImageMetadata(boolean standardMetadataFormatSupported, String nativeMetadataFormatName, String nativeMetadataFormatClassName, String[] extraMetadataFormatNames, String[] extraMetadataFormatClassNames) {
/* 128 */     super(standardMetadataFormatSupported, nativeMetadataFormatName, nativeMetadataFormatClassName, extraMetadataFormatNames, extraMetadataFormatClassNames);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GIFImageMetadata() {
/* 136 */     this(true, "javax_imageio_gif_image_1.0", "com.github.jaiimageio.impl.plugins.gif.GIFImageMetadataFormat", null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/* 143 */     return true;
/*     */   }
/*     */   
/*     */   public Node getAsTree(String formatName) {
/* 147 */     if (formatName.equals("javax_imageio_gif_image_1.0"))
/* 148 */       return getNativeTree(); 
/* 149 */     if (formatName
/* 150 */       .equals("javax_imageio_1.0")) {
/* 151 */       return getStandardTree();
/*     */     }
/* 153 */     throw new IllegalArgumentException("Not a recognized format!");
/*     */   }
/*     */ 
/*     */   
/*     */   private String toISO8859(byte[] data) {
/*     */     try {
/* 159 */       return new String(data, "ISO-8859-1");
/* 160 */     } catch (UnsupportedEncodingException e) {
/* 161 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Node getNativeTree() {
/* 167 */     IIOMetadataNode root = new IIOMetadataNode("javax_imageio_gif_image_1.0");
/*     */ 
/*     */ 
/*     */     
/* 171 */     IIOMetadataNode node = new IIOMetadataNode("ImageDescriptor");
/* 172 */     node.setAttribute("imageLeftPosition", 
/* 173 */         Integer.toString(this.imageLeftPosition));
/* 174 */     node.setAttribute("imageTopPosition", 
/* 175 */         Integer.toString(this.imageTopPosition));
/* 176 */     node.setAttribute("imageWidth", Integer.toString(this.imageWidth));
/* 177 */     node.setAttribute("imageHeight", Integer.toString(this.imageHeight));
/* 178 */     node.setAttribute("interlaceFlag", this.interlaceFlag ? "true" : "false");
/*     */     
/* 180 */     root.appendChild(node);
/*     */ 
/*     */     
/* 183 */     if (this.localColorTable != null) {
/* 184 */       node = new IIOMetadataNode("LocalColorTable");
/* 185 */       int numEntries = this.localColorTable.length / 3;
/* 186 */       node.setAttribute("sizeOfLocalColorTable", 
/* 187 */           Integer.toString(numEntries));
/* 188 */       node.setAttribute("sortFlag", this.sortFlag ? "TRUE" : "FALSE");
/*     */ 
/*     */       
/* 191 */       for (int i = 0; i < numEntries; i++) {
/* 192 */         IIOMetadataNode entry = new IIOMetadataNode("ColorTableEntry");
/*     */         
/* 194 */         entry.setAttribute("index", Integer.toString(i));
/* 195 */         int r = this.localColorTable[3 * i] & 0xFF;
/* 196 */         int g = this.localColorTable[3 * i + 1] & 0xFF;
/* 197 */         int b = this.localColorTable[3 * i + 2] & 0xFF;
/* 198 */         entry.setAttribute("red", Integer.toString(r));
/* 199 */         entry.setAttribute("green", Integer.toString(g));
/* 200 */         entry.setAttribute("blue", Integer.toString(b));
/* 201 */         node.appendChild(entry);
/*     */       } 
/* 203 */       root.appendChild(node);
/*     */     } 
/*     */ 
/*     */     
/* 207 */     node = new IIOMetadataNode("GraphicControlExtension");
/* 208 */     node.setAttribute("disposalMethod", disposalMethodNames[this.disposalMethod]);
/*     */     
/* 210 */     node.setAttribute("userInputFlag", this.userInputFlag ? "true" : "false");
/*     */     
/* 212 */     node.setAttribute("transparentColorFlag", this.transparentColorFlag ? "true" : "false");
/*     */     
/* 214 */     node.setAttribute("delayTime", 
/* 215 */         Integer.toString(this.delayTime));
/* 216 */     node.setAttribute("transparentColorIndex", 
/* 217 */         Integer.toString(this.transparentColorIndex));
/* 218 */     root.appendChild(node);
/*     */     
/* 220 */     if (this.hasPlainTextExtension) {
/* 221 */       node = new IIOMetadataNode("PlainTextExtension");
/* 222 */       node.setAttribute("textGridLeft", 
/* 223 */           Integer.toString(this.textGridLeft));
/* 224 */       node.setAttribute("textGridTop", 
/* 225 */           Integer.toString(this.textGridTop));
/* 226 */       node.setAttribute("textGridWidth", 
/* 227 */           Integer.toString(this.textGridWidth));
/* 228 */       node.setAttribute("textGridHeight", 
/* 229 */           Integer.toString(this.textGridHeight));
/* 230 */       node.setAttribute("characterCellWidth", 
/* 231 */           Integer.toString(this.characterCellWidth));
/* 232 */       node.setAttribute("characterCellHeight", 
/* 233 */           Integer.toString(this.characterCellHeight));
/* 234 */       node.setAttribute("textForegroundColor", 
/* 235 */           Integer.toString(this.textForegroundColor));
/* 236 */       node.setAttribute("textBackgroundColor", 
/* 237 */           Integer.toString(this.textBackgroundColor));
/* 238 */       node.setAttribute("text", toISO8859(this.text));
/*     */       
/* 240 */       root.appendChild(node);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 245 */     int numAppExtensions = (this.applicationIDs == null) ? 0 : this.applicationIDs.size();
/* 246 */     if (numAppExtensions > 0) {
/* 247 */       node = new IIOMetadataNode("ApplicationExtensions");
/* 248 */       for (int i = 0; i < numAppExtensions; i++) {
/* 249 */         IIOMetadataNode appExtNode = new IIOMetadataNode("ApplicationExtension");
/*     */         
/* 251 */         byte[] applicationID = this.applicationIDs.get(i);
/* 252 */         appExtNode.setAttribute("applicationID", 
/* 253 */             toISO8859(applicationID));
/* 254 */         byte[] authenticationCode = this.authenticationCodes.get(i);
/* 255 */         appExtNode.setAttribute("authenticationCode", 
/* 256 */             toISO8859(authenticationCode));
/* 257 */         byte[] appData = this.applicationData.get(i);
/* 258 */         appExtNode.setUserObject(appData.clone());
/* 259 */         node.appendChild(appExtNode);
/*     */       } 
/*     */       
/* 262 */       root.appendChild(node);
/*     */     } 
/*     */ 
/*     */     
/* 266 */     int numComments = (this.comments == null) ? 0 : this.comments.size();
/* 267 */     if (numComments > 0) {
/* 268 */       node = new IIOMetadataNode("CommentExtensions");
/* 269 */       for (int i = 0; i < numComments; i++) {
/* 270 */         IIOMetadataNode commentNode = new IIOMetadataNode("CommentExtension");
/*     */         
/* 272 */         byte[] comment = this.comments.get(i);
/* 273 */         commentNode.setAttribute("value", toISO8859(comment));
/* 274 */         node.appendChild(commentNode);
/*     */       } 
/*     */       
/* 277 */       root.appendChild(node);
/*     */     } 
/*     */     
/* 280 */     return root;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardChromaNode() {
/* 284 */     IIOMetadataNode chroma_node = new IIOMetadataNode("Chroma");
/* 285 */     IIOMetadataNode node = null;
/*     */     
/* 287 */     node = new IIOMetadataNode("ColorSpaceType");
/* 288 */     node.setAttribute("name", "RGB");
/* 289 */     chroma_node.appendChild(node);
/*     */     
/* 291 */     node = new IIOMetadataNode("NumChannels");
/* 292 */     node.setAttribute("value", this.transparentColorFlag ? "4" : "3");
/* 293 */     chroma_node.appendChild(node);
/*     */ 
/*     */ 
/*     */     
/* 297 */     node = new IIOMetadataNode("BlackIsZero");
/* 298 */     node.setAttribute("value", "TRUE");
/* 299 */     chroma_node.appendChild(node);
/*     */     
/* 301 */     if (this.localColorTable != null) {
/* 302 */       node = new IIOMetadataNode("Palette");
/* 303 */       int numEntries = this.localColorTable.length / 3;
/* 304 */       for (int i = 0; i < numEntries; i++) {
/* 305 */         IIOMetadataNode entry = new IIOMetadataNode("PaletteEntry");
/*     */         
/* 307 */         entry.setAttribute("index", Integer.toString(i));
/* 308 */         entry.setAttribute("red", 
/* 309 */             Integer.toString(this.localColorTable[3 * i] & 0xFF));
/* 310 */         entry.setAttribute("green", 
/* 311 */             Integer.toString(this.localColorTable[3 * i + 1] & 0xFF));
/* 312 */         entry.setAttribute("blue", 
/* 313 */             Integer.toString(this.localColorTable[3 * i + 2] & 0xFF));
/* 314 */         node.appendChild(entry);
/*     */       } 
/* 316 */       chroma_node.appendChild(node);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 322 */     return chroma_node;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardCompressionNode() {
/* 326 */     IIOMetadataNode compression_node = new IIOMetadataNode("Compression");
/* 327 */     IIOMetadataNode node = null;
/*     */     
/* 329 */     node = new IIOMetadataNode("CompressionTypeName");
/* 330 */     node.setAttribute("value", "lzw");
/* 331 */     compression_node.appendChild(node);
/*     */     
/* 333 */     node = new IIOMetadataNode("Lossless");
/* 334 */     node.setAttribute("value", "TRUE");
/* 335 */     compression_node.appendChild(node);
/*     */     
/* 337 */     node = new IIOMetadataNode("NumProgressiveScans");
/* 338 */     node.setAttribute("value", this.interlaceFlag ? "4" : "1");
/* 339 */     compression_node.appendChild(node);
/*     */ 
/*     */ 
/*     */     
/* 343 */     return compression_node;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardDataNode() {
/* 347 */     IIOMetadataNode data_node = new IIOMetadataNode("Data");
/* 348 */     IIOMetadataNode node = null;
/*     */ 
/*     */ 
/*     */     
/* 352 */     node = new IIOMetadataNode("SampleFormat");
/* 353 */     node.setAttribute("value", "Index");
/* 354 */     data_node.appendChild(node);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 360 */     return data_node;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardDimensionNode() {
/* 364 */     IIOMetadataNode dimension_node = new IIOMetadataNode("Dimension");
/* 365 */     IIOMetadataNode node = null;
/*     */ 
/*     */ 
/*     */     
/* 369 */     node = new IIOMetadataNode("ImageOrientation");
/* 370 */     node.setAttribute("value", "Normal");
/* 371 */     dimension_node.appendChild(node);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 380 */     node = new IIOMetadataNode("HorizontalPixelOffset");
/* 381 */     node.setAttribute("value", Integer.toString(this.imageLeftPosition));
/* 382 */     dimension_node.appendChild(node);
/*     */     
/* 384 */     node = new IIOMetadataNode("VerticalPixelOffset");
/* 385 */     node.setAttribute("value", Integer.toString(this.imageTopPosition));
/* 386 */     dimension_node.appendChild(node);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 391 */     return dimension_node;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getStandardTextNode() {
/* 397 */     if (this.comments == null) {
/* 398 */       return null;
/*     */     }
/* 400 */     Iterator<byte[]> commentIter = this.comments.iterator();
/* 401 */     if (!commentIter.hasNext()) {
/* 402 */       return null;
/*     */     }
/*     */     
/* 405 */     IIOMetadataNode text_node = new IIOMetadataNode("Text");
/* 406 */     IIOMetadataNode node = null;
/*     */     
/* 408 */     while (commentIter.hasNext()) {
/* 409 */       byte[] comment = commentIter.next();
/* 410 */       String s = null;
/*     */       try {
/* 412 */         s = new String(comment, "ISO-8859-1");
/* 413 */       } catch (UnsupportedEncodingException e) {
/* 414 */         throw new RuntimeException("Encoding ISO-8859-1 unknown!");
/*     */       } 
/*     */       
/* 417 */       node = new IIOMetadataNode("TextEntry");
/* 418 */       node.setAttribute("value", s);
/* 419 */       node.setAttribute("encoding", "ISO-8859-1");
/* 420 */       node.setAttribute("compression", "none");
/* 421 */       text_node.appendChild(node);
/*     */     } 
/*     */     
/* 424 */     return text_node;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardTransparencyNode() {
/* 428 */     if (!this.transparentColorFlag) {
/* 429 */       return null;
/*     */     }
/*     */     
/* 432 */     IIOMetadataNode transparency_node = new IIOMetadataNode("Transparency");
/*     */     
/* 434 */     IIOMetadataNode node = null;
/*     */ 
/*     */ 
/*     */     
/* 438 */     node = new IIOMetadataNode("TransparentIndex");
/* 439 */     node.setAttribute("value", 
/* 440 */         Integer.toString(this.transparentColorIndex));
/* 441 */     transparency_node.appendChild(node);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 447 */     return transparency_node;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 453 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/* 458 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/* 463 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */   
/*     */   public void reset() {
/* 467 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFImageMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */