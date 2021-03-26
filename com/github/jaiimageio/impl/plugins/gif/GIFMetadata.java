/*     */ package com.github.jaiimageio.impl.plugins.gif;
/*     */ 
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
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
/*     */ 
/*     */ 
/*     */ abstract class GIFMetadata
/*     */   extends IIOMetadata
/*     */ {
/*     */   static final int UNDEFINED_INTEGER_VALUE = -1;
/*     */   
/*     */   protected static void fatal(Node node, String reason) throws IIOInvalidTreeException {
/*  72 */     throw new IIOInvalidTreeException(reason, node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String getStringAttribute(Node node, String name, String defaultValue, boolean required, String[] range) throws IIOInvalidTreeException {
/*  81 */     Node attr = node.getAttributes().getNamedItem(name);
/*  82 */     if (attr == null) {
/*  83 */       if (!required) {
/*  84 */         return defaultValue;
/*     */       }
/*  86 */       fatal(node, "Required attribute " + name + " not present!");
/*     */     } 
/*     */     
/*  89 */     String value = attr.getNodeValue();
/*     */     
/*  91 */     if (range != null) {
/*  92 */       if (value == null) {
/*  93 */         fatal(node, "Null value for " + node
/*  94 */             .getNodeName() + " attribute " + name + "!");
/*     */       }
/*     */       
/*  97 */       boolean validValue = false;
/*  98 */       int len = range.length;
/*  99 */       for (int i = 0; i < len; i++) {
/* 100 */         if (value.equals(range[i])) {
/* 101 */           validValue = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 105 */       if (!validValue) {
/* 106 */         fatal(node, "Bad value for " + node
/* 107 */             .getNodeName() + " attribute " + name + "!");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 112 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getIntAttribute(Node node, String name, int defaultValue, boolean required, boolean bounded, int min, int max) throws IIOInvalidTreeException {
/* 121 */     String value = getStringAttribute(node, name, null, required, null);
/* 122 */     if (value == null || "".equals(value)) {
/* 123 */       return defaultValue;
/*     */     }
/*     */     
/* 126 */     int intValue = defaultValue;
/*     */     try {
/* 128 */       intValue = Integer.parseInt(value);
/* 129 */     } catch (NumberFormatException e) {
/* 130 */       fatal(node, "Bad value for " + node
/* 131 */           .getNodeName() + " attribute " + name + "!");
/*     */     } 
/*     */     
/* 134 */     if (bounded && (intValue < min || intValue > max)) {
/* 135 */       fatal(node, "Bad value for " + node
/* 136 */           .getNodeName() + " attribute " + name + "!");
/*     */     }
/*     */     
/* 139 */     return intValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float getFloatAttribute(Node node, String name, float defaultValue, boolean required) throws IIOInvalidTreeException {
/* 147 */     String value = getStringAttribute(node, name, null, required, null);
/* 148 */     if (value == null) {
/* 149 */       return defaultValue;
/*     */     }
/* 151 */     return Float.parseFloat(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getIntAttribute(Node node, String name, boolean bounded, int min, int max) throws IIOInvalidTreeException {
/* 158 */     return getIntAttribute(node, name, -1, true, bounded, min, max);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float getFloatAttribute(Node node, String name) throws IIOInvalidTreeException {
/* 164 */     return getFloatAttribute(node, name, -1.0F, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean getBooleanAttribute(Node node, String name, boolean defaultValue, boolean required) throws IIOInvalidTreeException {
/* 172 */     Node attr = node.getAttributes().getNamedItem(name);
/* 173 */     if (attr == null) {
/* 174 */       if (!required) {
/* 175 */         return defaultValue;
/*     */       }
/* 177 */       fatal(node, "Required attribute " + name + " not present!");
/*     */     } 
/*     */     
/* 180 */     String value = attr.getNodeValue();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     if (value.equalsIgnoreCase("TRUE"))
/* 186 */       return true; 
/* 187 */     if (value.equalsIgnoreCase("FALSE")) {
/* 188 */       return false;
/*     */     }
/* 190 */     fatal(node, "Attribute " + name + " must be 'TRUE' or 'FALSE'!");
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean getBooleanAttribute(Node node, String name) throws IIOInvalidTreeException {
/* 198 */     return getBooleanAttribute(node, name, false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getEnumeratedAttribute(Node node, String name, String[] legalNames, int defaultValue, boolean required) throws IIOInvalidTreeException {
/* 208 */     Node attr = node.getAttributes().getNamedItem(name);
/* 209 */     if (attr == null) {
/* 210 */       if (!required) {
/* 211 */         return defaultValue;
/*     */       }
/* 213 */       fatal(node, "Required attribute " + name + " not present!");
/*     */     } 
/*     */     
/* 216 */     String value = attr.getNodeValue();
/* 217 */     for (int i = 0; i < legalNames.length; i++) {
/* 218 */       if (value.equals(legalNames[i])) {
/* 219 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 223 */     fatal(node, "Illegal value for attribute " + name + "!");
/* 224 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getEnumeratedAttribute(Node node, String name, String[] legalNames) throws IIOInvalidTreeException {
/* 232 */     return getEnumeratedAttribute(node, name, legalNames, -1, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String getAttribute(Node node, String name, String defaultValue, boolean required) throws IIOInvalidTreeException {
/* 239 */     Node attr = node.getAttributes().getNamedItem(name);
/* 240 */     if (attr == null) {
/* 241 */       if (!required) {
/* 242 */         return defaultValue;
/*     */       }
/* 244 */       fatal(node, "Required attribute " + name + " not present!");
/*     */     } 
/*     */     
/* 247 */     return attr.getNodeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String getAttribute(Node node, String name) throws IIOInvalidTreeException {
/* 253 */     return getAttribute(node, name, null, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GIFMetadata(boolean standardMetadataFormatSupported, String nativeMetadataFormatName, String nativeMetadataFormatClassName, String[] extraMetadataFormatNames, String[] extraMetadataFormatClassNames) {
/* 261 */     super(standardMetadataFormatSupported, nativeMetadataFormatName, nativeMetadataFormatClassName, extraMetadataFormatNames, extraMetadataFormatClassNames);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 270 */     if (formatName.equals(this.nativeMetadataFormatName)) {
/* 271 */       if (root == null) {
/* 272 */         throw new IllegalArgumentException("root == null!");
/*     */       }
/* 274 */       mergeNativeTree(root);
/* 275 */     } else if (formatName
/* 276 */       .equals("javax_imageio_1.0")) {
/* 277 */       if (root == null) {
/* 278 */         throw new IllegalArgumentException("root == null!");
/*     */       }
/* 280 */       mergeStandardTree(root);
/*     */     } else {
/* 282 */       throw new IllegalArgumentException("Not a recognized format!");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] getColorTable(Node colorTableNode, String entryNodeName, boolean lengthExpected, int expectedLength) throws IIOInvalidTreeException {
/* 291 */     byte[] red = new byte[256];
/* 292 */     byte[] green = new byte[256];
/* 293 */     byte[] blue = new byte[256];
/* 294 */     int maxIndex = -1;
/*     */     
/* 296 */     Node entry = colorTableNode.getFirstChild();
/* 297 */     if (entry == null) {
/* 298 */       fatal(colorTableNode, "Palette has no entries!");
/*     */     }
/*     */     
/* 301 */     while (entry != null) {
/* 302 */       if (!entry.getNodeName().equals(entryNodeName)) {
/* 303 */         fatal(colorTableNode, "Only a " + entryNodeName + " may be a child of a " + entry
/*     */             
/* 305 */             .getNodeName() + "!");
/*     */       }
/*     */       
/* 308 */       int index = getIntAttribute(entry, "index", true, 0, 255);
/* 309 */       if (index > maxIndex) {
/* 310 */         maxIndex = index;
/*     */       }
/* 312 */       red[index] = (byte)getIntAttribute(entry, "red", true, 0, 255);
/* 313 */       green[index] = (byte)getIntAttribute(entry, "green", true, 0, 255);
/* 314 */       blue[index] = (byte)getIntAttribute(entry, "blue", true, 0, 255);
/*     */       
/* 316 */       entry = entry.getNextSibling();
/*     */     } 
/*     */     
/* 319 */     int numEntries = maxIndex + 1;
/*     */     
/* 321 */     if (lengthExpected && numEntries != expectedLength) {
/* 322 */       fatal(colorTableNode, "Unexpected length for palette!");
/*     */     }
/*     */     
/* 325 */     byte[] colorTable = new byte[3 * numEntries];
/* 326 */     for (int i = 0, j = 0; i < numEntries; i++) {
/* 327 */       colorTable[j++] = red[i];
/* 328 */       colorTable[j++] = green[i];
/* 329 */       colorTable[j++] = blue[i];
/*     */     } 
/*     */     
/* 332 */     return colorTable;
/*     */   }
/*     */   
/*     */   protected abstract void mergeNativeTree(Node paramNode) throws IIOInvalidTreeException;
/*     */   
/*     */   protected abstract void mergeStandardTree(Node paramNode) throws IIOInvalidTreeException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */