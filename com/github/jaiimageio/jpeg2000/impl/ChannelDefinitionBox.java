/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import java.awt.image.ColorModel;
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
/*     */ public class ChannelDefinitionBox
/*     */   extends Box
/*     */ {
/*     */   private short num;
/*     */   private short[] channels;
/*     */   private short[] types;
/*     */   private short[] associations;
/*     */   
/*     */   private static int computeLength(ColorModel colorModel) {
/*  71 */     int length = (colorModel.getComponentSize()).length - 1;
/*     */     
/*  73 */     return 10 + (colorModel.isAlphaPremultiplied() ? (length * 18) : (length * 12));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fillBasedOnBands(int numComps, boolean isPremultiplied, short[] c, short[] t, short[] a) {
/*  84 */     int num = numComps * (isPremultiplied ? 3 : 2);
/*  85 */     if (isPremultiplied) {
/*  86 */       for (int j = numComps * 2; j < num; j++) {
/*  87 */         c[j] = (short)(j - numComps * 2);
/*  88 */         t[j] = 2;
/*  89 */         a[j] = (short)(j + 1 - numComps * 2);
/*     */       } 
/*     */     }
/*     */     
/*  93 */     for (int i = 0; i < numComps; i++) {
/*  94 */       int j = i + numComps;
/*  95 */       c[i] = (short)i;
/*  96 */       t[i] = 0;
/*  97 */       a[i] = (short)(i + 1); a[j] = (short)(i + 1);
/*     */       
/*  99 */       c[j] = (short)numComps;
/* 100 */       t[j] = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelDefinitionBox(ColorModel colorModel) {
/* 108 */     super(computeLength(colorModel), 1667523942, null);
/*     */ 
/*     */     
/* 111 */     short length = (short)((colorModel.getComponentSize()).length - 1);
/* 112 */     this.num = (short)(length * (colorModel.isAlphaPremultiplied() ? 3 : 2));
/* 113 */     this.channels = new short[this.num];
/* 114 */     this.types = new short[this.num];
/* 115 */     this.associations = new short[this.num];
/*     */ 
/*     */     
/* 118 */     fillBasedOnBands(length, colorModel
/* 119 */         .isAlphaPremultiplied(), this.channels, this.types, this.associations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelDefinitionBox(byte[] data) {
/* 129 */     super(8 + data.length, 1667523942, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelDefinitionBox(short[] channel, short[] types, short[] associations) {
/* 137 */     super(10 + channel.length * 6, 1667523942, null);
/* 138 */     this.num = (short)channel.length;
/* 139 */     this.channels = channel;
/* 140 */     this.types = types;
/* 141 */     this.associations = associations;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelDefinitionBox(Node node) throws IIOInvalidTreeException {
/* 148 */     super(node);
/* 149 */     NodeList children = node.getChildNodes();
/* 150 */     int index = 0;
/*     */     
/* 152 */     for (int i = 0; i < children.getLength(); i++) {
/* 153 */       Node child = children.item(i);
/* 154 */       String name = child.getNodeName();
/*     */       
/* 156 */       if ("NumberOfDefinition".equals(name)) {
/* 157 */         this.num = Box.getShortElementValue(child);
/*     */       }
/*     */       
/* 160 */       if ("Definitions".equals(name)) {
/* 161 */         this.channels = new short[this.num];
/* 162 */         this.types = new short[this.num];
/* 163 */         this.associations = new short[this.num];
/*     */         
/* 165 */         NodeList children1 = child.getChildNodes();
/*     */         
/* 167 */         for (int j = 0; j < children1.getLength(); j++) {
/* 168 */           child = children1.item(j);
/* 169 */           name = child.getNodeName();
/* 170 */           if ("ChannelNumber".equals(name)) {
/* 171 */             this.channels[index] = Box.getShortElementValue(child);
/*     */           }
/*     */           
/* 174 */           if ("ChannelType".equals(name)) {
/* 175 */             this.types[index] = Box.getShortElementValue(child);
/*     */           }
/*     */           
/* 178 */           if ("Association".equals(name)) {
/* 179 */             this.associations[index++] = Box.getShortElementValue(child);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parse(byte[] data) {
/* 188 */     this.num = (short)(data[0] << 8 | data[1]);
/* 189 */     this.channels = new short[this.num];
/* 190 */     this.types = new short[this.num];
/* 191 */     this.associations = new short[this.num];
/*     */     
/* 193 */     for (int i = 0, j = 2; i < this.num; i++) {
/* 194 */       this.channels[i] = (short)(((data[j++] & 0xFF) << 8) + (data[j++] & 0xFF));
/*     */       
/* 196 */       this.types[i] = (short)(((data[j++] & 0xFF) << 8) + (data[j++] & 0xFF));
/* 197 */       this.associations[i] = (short)(((data[j++] & 0xFF) << 8) + (data[j++] & 0xFF));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public short[] getChannel() {
/* 204 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   public short[] getTypes() {
/* 209 */     return this.types;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short[] getAssociation() {
/* 216 */     return this.associations;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 224 */     IIOMetadataNode node = new IIOMetadataNode(Box.getName(getType()));
/* 225 */     setDefaultAttributes(node);
/*     */     
/* 227 */     IIOMetadataNode child = new IIOMetadataNode("NumberOfDefinition");
/* 228 */     child.setUserObject(new Short(this.num));
/* 229 */     child.setNodeValue("" + this.num);
/* 230 */     node.appendChild(child);
/*     */     
/* 232 */     child = new IIOMetadataNode("Definitions");
/* 233 */     node.appendChild(child);
/*     */     
/* 235 */     for (int i = 0; i < this.num; i++) {
/* 236 */       IIOMetadataNode child1 = new IIOMetadataNode("ChannelNumber");
/* 237 */       child1.setUserObject(new Short(this.channels[i]));
/* 238 */       child1.setNodeValue("" + this.channels[i]);
/* 239 */       child.appendChild(child1);
/*     */       
/* 241 */       child1 = new IIOMetadataNode("ChannelType");
/* 242 */       child1.setUserObject(new Short(this.types[i]));
/* 243 */       child1.setNodeValue("" + this.types[i]);
/* 244 */       child.appendChild(child1);
/*     */       
/* 246 */       child1 = new IIOMetadataNode("Association");
/* 247 */       child1.setUserObject(new Short(this.associations[i]));
/* 248 */       child1.setNodeValue("" + this.associations[i]);
/* 249 */       child.appendChild(child1);
/*     */     } 
/*     */     
/* 252 */     return node;
/*     */   }
/*     */   
/*     */   protected void compose() {
/* 256 */     if (this.data != null)
/*     */       return; 
/* 258 */     int len = this.num * 6 + 2;
/* 259 */     this.data = new byte[len];
/* 260 */     this.data[0] = (byte)(this.num >> 8);
/* 261 */     this.data[1] = (byte)(this.num & 0xFF);
/*     */     
/* 263 */     for (int i = 0, j = 2; i < this.num; i++) {
/* 264 */       this.data[j++] = (byte)(this.channels[i] >> 8);
/* 265 */       this.data[j++] = (byte)(this.channels[i] & 0xFF);
/*     */       
/* 267 */       this.data[j++] = (byte)(this.types[i] >> 8);
/* 268 */       this.data[j++] = (byte)(this.types[i] & 0xFF);
/*     */       
/* 270 */       this.data[j++] = (byte)(this.associations[i] >> 8);
/* 271 */       this.data[j++] = (byte)(this.associations[i] & 0xFF);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/ChannelDefinitionBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */