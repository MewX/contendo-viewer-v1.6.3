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
/*     */ public class XMLBox
/*     */   extends Box
/*     */ {
/*  59 */   private static String[] elementNames = new String[] { "Content" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] getElementNames() {
/*  65 */     return elementNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLBox(byte[] data) {
/*  70 */     super(8 + data.length, 2020437024, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLBox(Node node) throws IIOInvalidTreeException {
/*  77 */     super(node);
/*  78 */     NodeList children = node.getChildNodes();
/*     */     
/*  80 */     for (int i = 0; i < children.getLength(); i++) {
/*  81 */       Node child = children.item(i);
/*  82 */       String name = child.getNodeName();
/*     */       
/*  84 */       if ("Content".equals(name)) {
/*  85 */         String value = child.getNodeValue();
/*  86 */         if (value != null) {
/*  87 */           this.data = value.getBytes();
/*  88 */         } else if (child instanceof IIOMetadataNode) {
/*  89 */           value = (String)((IIOMetadataNode)child).getUserObject();
/*  90 */           if (value != null) {
/*  91 */             this.data = value.getBytes();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/*     */     try {
/* 103 */       IIOMetadataNode node = new IIOMetadataNode(Box.getName(getType()));
/* 104 */       setDefaultAttributes(node);
/* 105 */       IIOMetadataNode child = new IIOMetadataNode("Content");
/* 106 */       String value = null;
/* 107 */       if (this.data != null)
/* 108 */         value = new String(this.data); 
/* 109 */       child.setUserObject(value);
/* 110 */       child.setNodeValue(value);
/* 111 */       node.appendChild(child);
/* 112 */       return node;
/* 113 */     } catch (Exception e) {
/* 114 */       throw new IllegalArgumentException(I18N.getString("Box0"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/XMLBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */