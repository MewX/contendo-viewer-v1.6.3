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
/*     */ public class DataEntryURLBox
/*     */   extends Box
/*     */ {
/*  60 */   private static String[] elementNames = new String[] { "Version", "Flags", "URL" };
/*     */   private byte version;
/*     */   private byte[] flags;
/*     */   private String url;
/*     */   
/*     */   public static String[] getElementNames() {
/*  66 */     return elementNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataEntryURLBox(byte[] data) {
/*  76 */     super(8 + data.length, 1970433056, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataEntryURLBox(byte version, byte[] flags, String url) {
/*  81 */     super(12 + url.length(), 1970433056, null);
/*  82 */     this.version = version;
/*  83 */     this.flags = flags;
/*  84 */     this.url = url;
/*     */   }
/*     */ 
/*     */   
/*     */   public DataEntryURLBox(Node node) throws IIOInvalidTreeException {
/*  89 */     super(node);
/*  90 */     NodeList children = node.getChildNodes();
/*     */     
/*  92 */     for (int i = 0; i < children.getLength(); i++) {
/*  93 */       Node child = children.item(i);
/*  94 */       String name = child.getNodeName();
/*     */       
/*  96 */       if ("Version".equals(name)) {
/*  97 */         this.version = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 100 */       if ("Flags".equals(name)) {
/* 101 */         this.flags = Box.getByteArrayElementValue(child);
/*     */       }
/*     */       
/* 104 */       if ("URL".equals(name)) {
/* 105 */         this.url = Box.getStringElementValue(child);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parse(byte[] data) {
/* 112 */     this.version = data[0];
/* 113 */     this.flags = new byte[3];
/* 114 */     this.flags[0] = data[1];
/* 115 */     this.flags[1] = data[2];
/* 116 */     this.flags[2] = data[3];
/*     */     
/* 118 */     this.url = new String(data, 4, data.length - 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 126 */     return getNativeNodeForSimpleBox();
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getVersion() {
/* 131 */     return this.version;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getFlags() {
/* 136 */     return this.flags;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getURL() {
/* 141 */     return this.url;
/*     */   }
/*     */   
/*     */   protected void compose() {
/* 145 */     if (this.data != null)
/*     */       return; 
/* 147 */     this.data = new byte[4 + this.url.length()];
/*     */     
/* 149 */     this.data[0] = this.version;
/* 150 */     this.data[1] = this.flags[0];
/* 151 */     this.data[2] = this.flags[1];
/* 152 */     this.data[3] = this.flags[2];
/* 153 */     System.arraycopy(this.url.getBytes(), 0, this.data, 4, this.url.length());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/DataEntryURLBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */