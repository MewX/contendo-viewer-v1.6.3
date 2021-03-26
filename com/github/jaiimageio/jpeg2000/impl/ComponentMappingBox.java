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
/*     */ public class ComponentMappingBox
/*     */   extends Box
/*     */ {
/*     */   private short[] components;
/*     */   private byte[] type;
/*     */   private byte[] map;
/*     */   
/*     */   public ComponentMappingBox(byte[] data) {
/*  69 */     super(8 + data.length, 1668112752, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentMappingBox(short[] comp, byte[] t, byte[] m) {
/*  76 */     super(8 + (comp.length << 2), 1668112752, null);
/*  77 */     this.components = comp;
/*  78 */     this.type = t;
/*  79 */     this.map = m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentMappingBox(Node node) throws IIOInvalidTreeException {
/*  86 */     super(node);
/*  87 */     NodeList children = node.getChildNodes();
/*  88 */     int len = children.getLength() / 3;
/*  89 */     this.components = new short[len];
/*  90 */     this.type = new byte[len];
/*  91 */     this.map = new byte[len];
/*     */     
/*  93 */     len *= 3;
/*  94 */     int index = 0;
/*     */     
/*  96 */     for (int i = 0; i < len; i++) {
/*  97 */       Node child = children.item(i);
/*  98 */       String name = child.getNodeName();
/*     */       
/* 100 */       if ("Component".equals(name)) {
/* 101 */         this.components[index] = Box.getShortElementValue(child);
/*     */       }
/*     */       
/* 104 */       if ("ComponentType".equals(name)) {
/* 105 */         this.type[index] = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 108 */       if ("ComponentAssociation".equals(name)) {
/* 109 */         this.map[index++] = Box.getByteElementValue(child);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parse(byte[] data) {
/* 116 */     int len = data.length / 4;
/* 117 */     this.components = new short[len];
/* 118 */     this.type = new byte[len];
/* 119 */     this.map = new byte[len];
/*     */     
/* 121 */     for (int i = 0, j = 0; i < len; i++) {
/* 122 */       this.components[i] = (short)((data[j++] & 0xFF) << 8 | data[j++] & 0xFF);
/*     */       
/* 124 */       this.type[i] = data[j++];
/* 125 */       this.map[i] = data[j++];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 134 */     IIOMetadataNode node = new IIOMetadataNode(Box.getName(getType()));
/* 135 */     setDefaultAttributes(node);
/*     */     
/* 137 */     for (int i = 0; i < this.components.length; i++) {
/* 138 */       IIOMetadataNode child = new IIOMetadataNode("Component");
/* 139 */       Short obj = new Short(this.components[i]);
/* 140 */       child.setUserObject(new Short(this.components[i]));
/* 141 */       child.setNodeValue("" + this.components[i]);
/* 142 */       node.appendChild(child);
/*     */       
/* 144 */       child = new IIOMetadataNode("ComponentType");
/* 145 */       child.setUserObject(new Byte(this.type[i]));
/* 146 */       child.setNodeValue("" + this.type[i]);
/* 147 */       node.appendChild(child);
/*     */       
/* 149 */       child = new IIOMetadataNode("ComponentAssociation");
/* 150 */       child.setUserObject(new Byte(this.map[i]));
/* 151 */       child.setNodeValue("" + this.map[i]);
/* 152 */       node.appendChild(child);
/*     */     } 
/*     */     
/* 155 */     return node;
/*     */   }
/*     */   
/*     */   public short[] getComponent() {
/* 159 */     return this.components;
/*     */   }
/*     */   
/*     */   public byte[] getComponentType() {
/* 163 */     return this.type;
/*     */   }
/*     */   
/*     */   public byte[] getComponentAssociation() {
/* 167 */     return this.map;
/*     */   }
/*     */   
/*     */   protected void compose() {
/* 171 */     if (this.data != null)
/*     */       return; 
/* 173 */     this.data = new byte[this.type.length << 2];
/*     */     
/* 175 */     for (int i = 0, j = 0; i < this.type.length; i++) {
/* 176 */       this.data[j++] = (byte)(this.components[i] >> 8);
/* 177 */       this.data[j++] = (byte)(this.components[i] & 0xFF);
/* 178 */       this.data[j++] = this.type[i];
/* 179 */       this.data[j++] = this.map[i];
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/ComponentMappingBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */