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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResolutionBox
/*     */   extends Box
/*     */ {
/*     */   private short numV;
/*     */   private short numH;
/*     */   private short denomV;
/*     */   private short denomH;
/*     */   private byte expV;
/*     */   private byte expH;
/*     */   private float hRes;
/*     */   private float vRes;
/*     */   
/*     */   public ResolutionBox(int type, byte[] data) {
/*  77 */     super(18, type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResolutionBox(int type, float hRes, float vRes) {
/*  84 */     super(18, type, null);
/*  85 */     this.hRes = hRes;
/*  86 */     this.vRes = vRes;
/*  87 */     this.denomH = this.denomV = 1;
/*     */     
/*  89 */     this.expV = 0;
/*  90 */     if (vRes >= 32768.0F) {
/*  91 */       int temp = (int)vRes;
/*  92 */       while (temp >= 32768) {
/*  93 */         this.expV = (byte)(this.expV + 1);
/*  94 */         temp /= 10;
/*     */       } 
/*  96 */       this.numV = (short)(temp & 0xFFFF);
/*     */     } else {
/*  98 */       this.numV = (short)(int)vRes;
/*     */     } 
/*     */     
/* 101 */     this.expH = 0;
/* 102 */     if (hRes >= 32768.0F) {
/* 103 */       int temp = (int)hRes;
/* 104 */       while (temp >= 32768) {
/* 105 */         this.expH = (byte)(this.expH + 1);
/* 106 */         temp /= 10;
/*     */       } 
/* 108 */       this.numH = (short)(temp & 0xFFFF);
/*     */     } else {
/* 110 */       this.numH = (short)(int)hRes;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResolutionBox(Node node) throws IIOInvalidTreeException {
/* 118 */     super(node);
/* 119 */     NodeList children = node.getChildNodes();
/* 120 */     for (int i = 0; i < children.getLength(); i++) {
/* 121 */       Node child = children.item(i);
/* 122 */       String name = child.getNodeName();
/*     */       
/* 124 */       if ("VerticalResolutionNumerator".equals(name)) {
/* 125 */         this.numV = Box.getShortElementValue(child);
/*     */       }
/*     */       
/* 128 */       if ("VerticalResolutionDenominator".equals(name)) {
/* 129 */         this.denomV = Box.getShortElementValue(child);
/*     */       }
/*     */       
/* 132 */       if ("HorizontalResolutionNumerator".equals(name)) {
/* 133 */         this.numH = Box.getShortElementValue(child);
/*     */       }
/*     */       
/* 136 */       if ("HorizontalResolutionDenominator".equals(name)) {
/* 137 */         this.denomH = Box.getShortElementValue(child);
/*     */       }
/*     */       
/* 140 */       if ("VerticalResolutionExponent".equals(name)) {
/* 141 */         this.expV = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 144 */       if ("HorizontalResolutionExponent".equals(name)) {
/* 145 */         this.expH = Box.getByteElementValue(child);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHorizontalResolution() {
/* 152 */     return this.hRes;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getVerticalResolution() {
/* 157 */     return this.vRes;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parse(byte[] data) {
/* 162 */     this.numV = (short)((data[0] & 0xFF) << 8 | data[1] & 0xFF);
/* 163 */     this.denomV = (short)((data[2] & 0xFF) << 8 | data[3] & 0xFF);
/* 164 */     this.numH = (short)((data[4] & 0xFF) << 8 | data[5] & 0xFF);
/* 165 */     this.denomH = (short)((data[6] & 0xFF) << 8 | data[7] & 0xFF);
/* 166 */     this.expV = data[8];
/* 167 */     this.expH = data[9];
/* 168 */     this.vRes = (float)((this.numV & 0xFFFF) * Math.pow(10.0D, this.expV) / (this.denomV & 0xFFFF));
/* 169 */     this.hRes = (float)((this.numH & 0xFFFF) * Math.pow(10.0D, this.expH) / (this.denomH & 0xFFFF));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 177 */     IIOMetadataNode node = new IIOMetadataNode(Box.getName(getType()));
/* 178 */     setDefaultAttributes(node);
/*     */     
/* 180 */     IIOMetadataNode child = new IIOMetadataNode("VerticalResolutionNumerator");
/* 181 */     child.setUserObject(new Short(this.numV));
/* 182 */     child.setNodeValue("" + this.numV);
/* 183 */     node.appendChild(child);
/*     */     
/* 185 */     child = new IIOMetadataNode("VerticalResolutionDenominator");
/* 186 */     child.setUserObject(new Short(this.denomV));
/* 187 */     child.setNodeValue("" + this.denomV);
/* 188 */     node.appendChild(child);
/*     */     
/* 190 */     child = new IIOMetadataNode("HorizontalResolutionNumerator");
/* 191 */     child.setUserObject(new Short(this.numH));
/* 192 */     child.setNodeValue("" + this.numH);
/* 193 */     node.appendChild(child);
/*     */     
/* 195 */     child = new IIOMetadataNode("HorizontalResolutionDenominator");
/* 196 */     child.setUserObject(new Short(this.denomH));
/* 197 */     child.setNodeValue("" + this.denomH);
/* 198 */     node.appendChild(child);
/*     */     
/* 200 */     child = new IIOMetadataNode("VerticalResolutionExponent");
/* 201 */     child.setUserObject(new Byte(this.expV));
/* 202 */     child.setNodeValue("" + this.expV);
/* 203 */     node.appendChild(child);
/*     */     
/* 205 */     child = new IIOMetadataNode("HorizontalResolutionExponent");
/* 206 */     child.setUserObject(new Byte(this.expH));
/* 207 */     child.setNodeValue("" + this.expH);
/* 208 */     node.appendChild(child);
/*     */     
/* 210 */     return node;
/*     */   }
/*     */   
/*     */   protected void compose() {
/* 214 */     if (this.data != null)
/*     */       return; 
/* 216 */     this.data = new byte[10];
/* 217 */     this.data[0] = (byte)(this.numV >> 8);
/* 218 */     this.data[1] = (byte)(this.numV & 0xFF);
/* 219 */     this.data[2] = (byte)(this.denomV >> 8);
/* 220 */     this.data[3] = (byte)(this.denomV & 0xFF);
/*     */     
/* 222 */     this.data[4] = (byte)(this.numH >> 8);
/* 223 */     this.data[5] = (byte)(this.numH & 0xFF);
/* 224 */     this.data[6] = (byte)(this.denomH >> 8);
/* 225 */     this.data[7] = (byte)(this.denomH & 0xFF);
/*     */     
/* 227 */     this.data[8] = this.expV;
/* 228 */     this.data[9] = this.expH;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/ResolutionBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */