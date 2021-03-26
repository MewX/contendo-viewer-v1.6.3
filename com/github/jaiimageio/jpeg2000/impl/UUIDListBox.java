/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
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
/*     */ public class UUIDListBox
/*     */   extends Box
/*     */ {
/*     */   private short num;
/*     */   private byte[][] uuids;
/*     */   
/*     */   public UUIDListBox(short num, byte[][] uuids) {
/*  70 */     super(10 + (uuids.length << 4), 1970041716, null);
/*  71 */     this.num = num;
/*  72 */     this.uuids = uuids;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UUIDListBox(byte[] data) {
/*  79 */     super(8 + data.length, 1970041716, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UUIDListBox(Node node) throws IIOInvalidTreeException {
/*  86 */     super(node);
/*  87 */     NodeList children = node.getChildNodes();
/*  88 */     int index = 0;
/*     */     int i;
/*  90 */     for (i = 0; i < children.getLength(); i++) {
/*  91 */       Node child = children.item(i);
/*     */       
/*  93 */       if ("NumberUUID".equals(child.getNodeName())) {
/*  94 */         this.num = Box.getShortElementValue(child);
/*  95 */         this.uuids = new byte[this.num][];
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 100 */     for (i = 0; i < children.getLength(); i++) {
/* 101 */       Node child = children.item(i);
/*     */       
/* 103 */       if ("UUID".equals(child.getNodeName()) && index < this.num) {
/* 104 */         this.uuids[index++] = Box.getByteArrayElementValue(child);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parse(byte[] data) {
/* 111 */     this.num = (short)((data[0] & 0xFF) << 8 | data[1] & 0xFF);
/*     */     
/* 113 */     this.uuids = new byte[this.num][];
/* 114 */     int pos = 2;
/* 115 */     for (int i = 0; i < this.num; i++) {
/* 116 */       this.uuids[i] = new byte[16];
/* 117 */       System.arraycopy(data, pos, this.uuids[i], 0, 16);
/* 118 */       pos += 16;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 127 */     IIOMetadataNode node = new IIOMetadataNode(Box.getName(getType()));
/* 128 */     setDefaultAttributes(node);
/*     */     
/* 130 */     IIOMetadataNode child = new IIOMetadataNode("NumberUUID");
/* 131 */     child.setUserObject(new Short(this.num));
/* 132 */     child.setNodeValue("" + this.num);
/* 133 */     node.appendChild(child);
/*     */     
/* 135 */     for (int i = 0; i < this.num; i++) {
/* 136 */       child = new IIOMetadataNode("UUID");
/* 137 */       child.setUserObject(this.uuids[i]);
/* 138 */       child.setNodeValue(ImageUtil.convertObjectToString(this.uuids[i]));
/* 139 */       node.appendChild(child);
/*     */     } 
/*     */     
/* 142 */     return node;
/*     */   }
/*     */   
/*     */   protected void compose() {
/* 146 */     if (this.data != null)
/*     */       return; 
/* 148 */     this.data = new byte[2 + this.num * 16];
/*     */     
/* 150 */     this.data[0] = (byte)(this.num >> 8);
/* 151 */     this.data[1] = (byte)(this.num & 0xFF);
/*     */     
/* 153 */     for (int i = 0, pos = 2; i < this.num; i++) {
/* 154 */       System.arraycopy(this.uuids[i], 0, this.data, pos, 16);
/* 155 */       pos += 16;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/UUIDListBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */