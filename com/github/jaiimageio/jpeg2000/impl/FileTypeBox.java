/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
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
/*     */ public class FileTypeBox
/*     */   extends Box
/*     */ {
/*  62 */   private static String[] elementNames = new String[] { "Brand", "MinorVersion", "CompatibilityList" };
/*     */   
/*     */   private int brand;
/*     */   
/*     */   private int minorVersion;
/*     */   private int[] compatibility;
/*     */   
/*     */   public static String[] getElementNames() {
/*  70 */     return elementNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileTypeBox(int br, int minorVersion, int[] comp) {
/*  82 */     super(16 + ((comp == null) ? 0 : (comp.length << 2)), 1718909296, null);
/*  83 */     this.brand = br;
/*  84 */     this.minorVersion = minorVersion;
/*  85 */     this.compatibility = comp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FileTypeBox(byte[] data) {
/*  91 */     super(8 + data.length, 1718909296, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileTypeBox(Node node) throws IIOInvalidTreeException {
/*  98 */     super(node);
/*  99 */     NodeList children = node.getChildNodes();
/*     */     
/* 101 */     for (int i = 0; i < children.getLength(); i++) {
/* 102 */       Node child = children.item(i);
/* 103 */       String name = child.getNodeName();
/*     */       
/* 105 */       if ("Brand".equals(name)) {
/* 106 */         this.brand = Box.getIntElementValue(child);
/*     */       }
/*     */       
/* 109 */       if ("MinorVersion".equals(name)) {
/* 110 */         this.minorVersion = Box.getIntElementValue(child);
/*     */       }
/*     */       
/* 113 */       if ("CompatibilityList".equals(name)) {
/* 114 */         this.compatibility = Box.getIntArrayElementValue(child);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrand() {
/* 121 */     return this.brand;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinorVersion() {
/* 126 */     return this.minorVersion;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getCompatibilityList() {
/* 131 */     return this.compatibility;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 139 */     return getNativeNodeForSimpleBox();
/*     */   }
/*     */   
/*     */   protected void parse(byte[] data) {
/* 143 */     if (data == null)
/*     */       return; 
/* 145 */     this.brand = (data[0] & 0xFF) << 24 | (data[1] & 0xFF) << 16 | (data[2] & 0xFF) << 8 | data[3] & 0xFF;
/*     */ 
/*     */     
/* 148 */     this.minorVersion = (data[4] & 0xFF) << 24 | (data[5] & 0xFF) << 16 | (data[6] & 0xFF) << 8 | data[7] & 0xFF;
/*     */ 
/*     */     
/* 151 */     int len = (data.length - 8) / 4;
/* 152 */     if (len > 0) {
/* 153 */       this.compatibility = new int[len];
/* 154 */       for (int i = 0, j = 8; i < len; i++, j += 4) {
/* 155 */         this.compatibility[i] = (data[j] & 0xFF) << 24 | (data[j + 1] & 0xFF) << 16 | (data[j + 2] & 0xFF) << 8 | data[j + 3] & 0xFF;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void compose() {
/* 163 */     if (this.data != null)
/*     */       return; 
/* 165 */     this.data = new byte[8 + ((this.compatibility != null) ? (this.compatibility.length << 2) : 0)];
/*     */ 
/*     */ 
/*     */     
/* 169 */     copyInt(this.data, 0, this.brand);
/* 170 */     copyInt(this.data, 4, this.minorVersion);
/* 171 */     if (this.compatibility != null)
/* 172 */       for (int i = 0, j = 8; i < this.compatibility.length; i++, j += 4)
/* 173 */         copyInt(this.data, j, this.compatibility[i]);  
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/FileTypeBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */