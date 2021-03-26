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
/*     */ public class UUIDBox
/*     */   extends Box
/*     */ {
/*  59 */   private static String[] elementNames = new String[] { "UUID", "Data" };
/*     */   
/*     */   private byte[] uuid;
/*     */   private byte[] udata;
/*     */   
/*     */   public static String[] getElementNames() {
/*  65 */     return elementNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UUIDBox(byte[] data) {
/*  74 */     super(8 + data.length, 1970628964, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UUIDBox(Node node) throws IIOInvalidTreeException {
/*  81 */     super(node);
/*  82 */     NodeList children = node.getChildNodes();
/*     */     
/*  84 */     for (int i = 0; i < children.getLength(); i++) {
/*  85 */       Node child = children.item(i);
/*  86 */       String name = child.getNodeName();
/*     */       
/*  88 */       if ("UUID".equals(name)) {
/*  89 */         this.uuid = Box.getByteArrayElementValue(child);
/*     */       }
/*     */       
/*  92 */       if ("Data".equals(name)) {
/*  93 */         this.udata = Box.getByteArrayElementValue(child);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parse(byte[] data) {
/* 100 */     this.uuid = new byte[16];
/* 101 */     System.arraycopy(data, 0, this.uuid, 0, 16);
/* 102 */     this.udata = new byte[data.length - 16];
/* 103 */     System.arraycopy(data, 16, this.udata, 0, this.udata.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getUUID() {
/* 108 */     return this.uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getData() {
/* 113 */     return this.udata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 121 */     return getNativeNodeForSimpleBox();
/*     */   }
/*     */   
/*     */   protected void compose() {
/* 125 */     if (this.data != null)
/*     */       return; 
/* 127 */     this.data = new byte[16 + this.udata.length];
/* 128 */     System.arraycopy(this.uuid, 0, this.data, 0, 16);
/* 129 */     System.arraycopy(this.udata, 0, this.data, 16, this.udata.length);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/UUIDBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */