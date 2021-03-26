/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import java.awt.color.ICC_Profile;
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
/*     */ public class ColorSpecificationBox
/*     */   extends Box
/*     */ {
/*     */   public static final int ECS_sRGB = 16;
/*     */   public static final int ECS_GRAY = 17;
/*     */   public static final int ECS_YCC = 18;
/*  68 */   private static String[] elementNames = new String[] { "Method", "Precedence", "ApproximationAccuracy", "EnumeratedColorSpace", "ICCProfile" };
/*     */   
/*     */   private byte method;
/*     */   private byte precedence;
/*     */   private byte approximation;
/*     */   private int ecs;
/*     */   private ICC_Profile profile;
/*     */   
/*     */   public static String[] getElementNames() {
/*  77 */     return elementNames;
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
/*     */   private static int computeLength(byte m, ICC_Profile profile) {
/*  89 */     int ret = 15;
/*  90 */     if (m == 2 && profile != null) {
/*  91 */       ret += (profile.getData()).length;
/*     */     }
/*  93 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorSpecificationBox(byte m, byte p, byte a, int ecs, ICC_Profile profile) {
/* 101 */     super(computeLength(m, profile), 1668246642, null);
/* 102 */     this.method = m;
/* 103 */     this.precedence = p;
/* 104 */     this.approximation = a;
/* 105 */     this.ecs = ecs;
/* 106 */     this.profile = profile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorSpecificationBox(byte[] data) {
/* 113 */     super(8 + data.length, 1668246642, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorSpecificationBox(Node node) throws IIOInvalidTreeException {
/* 120 */     super(node);
/* 121 */     NodeList children = node.getChildNodes();
/*     */     
/* 123 */     for (int i = 0; i < children.getLength(); i++) {
/* 124 */       Node child = children.item(i);
/* 125 */       String name = child.getNodeName();
/*     */       
/* 127 */       if ("Method".equals(name)) {
/* 128 */         this.method = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 131 */       if ("Precedence".equals(name)) {
/* 132 */         this.precedence = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 135 */       if ("ApproximationAccuracy".equals(name)) {
/* 136 */         this.approximation = Box.getByteElementValue(child);
/*     */       }
/*     */       
/* 139 */       if ("EnumeratedColorSpace".equals(name)) {
/* 140 */         this.ecs = Box.getIntElementValue(child);
/*     */       }
/*     */       
/* 143 */       if ("ICCProfile".equals(name)) {
/* 144 */         if (child instanceof IIOMetadataNode) {
/* 145 */           this
/* 146 */             .profile = (ICC_Profile)((IIOMetadataNode)child).getUserObject();
/*     */         } else {
/* 148 */           String value = node.getNodeValue();
/* 149 */           if (value != null) {
/* 150 */             this.profile = ICC_Profile.getInstance(Box.parseByteArray(value));
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte getMethod() {
/* 158 */     return this.method;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getPrecedence() {
/* 163 */     return this.precedence;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getApproximationAccuracy() {
/* 168 */     return this.approximation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEnumeratedColorSpace() {
/* 173 */     return this.ecs;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICC_Profile getICCProfile() {
/* 178 */     return this.profile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 186 */     return getNativeNodeForSimpleBox();
/*     */   }
/*     */   
/*     */   protected void parse(byte[] data) {
/* 190 */     this.method = data[0];
/* 191 */     this.precedence = data[1];
/* 192 */     this.approximation = data[2];
/* 193 */     if (this.method == 2) {
/* 194 */       byte[] proData = new byte[data.length - 3];
/* 195 */       System.arraycopy(data, 3, proData, 0, data.length - 3);
/* 196 */       this.profile = ICC_Profile.getInstance(proData);
/*     */     } else {
/* 198 */       this.ecs = (data[3] & 0xFF) << 24 | (data[4] & 0xFF) << 16 | (data[5] & 0xFF) << 8 | data[6] & 0xFF;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void compose() {
/* 204 */     if (this.data != null)
/*     */       return; 
/* 206 */     int len = 7;
/* 207 */     byte[] profileData = null;
/* 208 */     if (this.profile != null) {
/* 209 */       profileData = this.profile.getData();
/* 210 */       len += profileData.length;
/*     */     } 
/*     */     
/* 213 */     this.data = new byte[len];
/*     */     
/* 215 */     this.data[0] = this.method;
/* 216 */     this.data[1] = this.precedence;
/* 217 */     this.data[2] = this.approximation;
/*     */     
/* 219 */     copyInt(this.data, 3, this.ecs);
/*     */     
/* 221 */     if (this.profile != null)
/* 222 */       System.arraycopy(profileData, 0, this.data, 7, len - 7); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/ColorSpecificationBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */