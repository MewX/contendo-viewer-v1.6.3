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
/*     */ 
/*     */ 
/*     */ public class HeaderBox
/*     */   extends Box
/*     */ {
/*  64 */   private static String[] elementNames = new String[] { "Height", "Width", "NumComponents", "BitDepth", "CompressionType", "UnknownColorspace", "IntellectualProperty" };
/*     */   
/*     */   private int width;
/*     */   
/*     */   private int height;
/*     */   private short numComp;
/*     */   private byte bitDepth;
/*     */   private byte compressionType;
/*     */   private byte unknownColor;
/*     */   private byte intelProp;
/*     */   
/*     */   public static String[] getElementNames() {
/*  76 */     return elementNames;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public HeaderBox(int height, int width, int numComp, int bitDepth, int compressionType, int unknownColor, int intelProp) {
/*  91 */     super(22, 1768449138, null);
/*  92 */     this.height = height;
/*  93 */     this.width = width;
/*  94 */     this.numComp = (short)numComp;
/*  95 */     this.bitDepth = (byte)bitDepth;
/*  96 */     this.compressionType = (byte)compressionType;
/*  97 */     this.unknownColor = (byte)unknownColor;
/*  98 */     this.intelProp = (byte)intelProp;
/*     */   }
/*     */ 
/*     */   
/*     */   public HeaderBox(byte[] data) {
/* 103 */     super(8 + data.length, 1768449138, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public HeaderBox(Node node) throws IIOInvalidTreeException {
/* 108 */     super(node);
/* 109 */     NodeList children = node.getChildNodes();
/*     */     
/* 111 */     for (int i = 0; i < children.getLength(); i++) {
/* 112 */       Node child = children.item(i);
/* 113 */       String name = child.getNodeName();
/*     */       
/* 115 */       if ("Height".equals(name)) {
/* 116 */         this.height = Box.getIntElementValue(child);
/*     */       }
/*     */       
/* 119 */       if ("Width".equals(name)) {
/* 120 */         this.width = Box.getIntElementValue(child);
/*     */       }
/*     */       
/* 123 */       if ("NumComponents".equals(name)) {
/* 124 */         this.numComp = Box.getShortElementValue(child);
/*     */       }
/*     */       
/* 127 */       if ("BitDepth".equals(name)) {
/* 128 */         this.bitDepth = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 131 */       if ("CompressionType".equals(name)) {
/* 132 */         this.compressionType = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 135 */       if ("UnknownColorspace".equals(name)) {
/* 136 */         this.unknownColor = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 139 */       if ("IntellectualProperty".equals(name)) {
/* 140 */         this.intelProp = Box.getByteElementValue(child);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parse(byte[] data) {
/* 147 */     this.height = (data[0] & 0xFF) << 24 | (data[1] & 0xFF) << 16 | (data[2] & 0xFF) << 8 | data[3] & 0xFF;
/*     */ 
/*     */ 
/*     */     
/* 151 */     this.width = (data[4] & 0xFF) << 24 | (data[5] & 0xFF) << 16 | (data[6] & 0xFF) << 8 | data[7] & 0xFF;
/*     */ 
/*     */ 
/*     */     
/* 155 */     this.numComp = (short)((data[8] & 0xFF) << 8 | data[9] & 0xFF);
/* 156 */     this.bitDepth = data[10];
/* 157 */     this.compressionType = data[11];
/* 158 */     this.unknownColor = data[12];
/* 159 */     this.intelProp = data[13];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 164 */     return this.height;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 169 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getNumComponents() {
/* 174 */     return this.numComp;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getCompressionType() {
/* 179 */     return this.compressionType;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getBitDepth() {
/* 184 */     return this.bitDepth;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getUnknownColorspace() {
/* 189 */     return this.unknownColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getIntellectualProperty() {
/* 194 */     return this.intelProp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 202 */     return getNativeNodeForSimpleBox();
/*     */   }
/*     */   
/*     */   protected void compose() {
/* 206 */     if (this.data != null)
/*     */       return; 
/* 208 */     this.data = new byte[14];
/* 209 */     copyInt(this.data, 0, this.height);
/* 210 */     copyInt(this.data, 4, this.width);
/*     */     
/* 212 */     this.data[8] = (byte)(this.numComp >> 8);
/* 213 */     this.data[9] = (byte)(this.numComp & 0xFF);
/* 214 */     this.data[10] = this.bitDepth;
/* 215 */     this.data[11] = this.compressionType;
/* 216 */     this.data[12] = this.unknownColor;
/* 217 */     this.data[13] = this.intelProp;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/HeaderBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */