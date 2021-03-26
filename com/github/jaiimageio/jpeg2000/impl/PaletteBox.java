/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import java.awt.image.IndexColorModel;
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
/*     */ public class PaletteBox
/*     */   extends Box
/*     */ {
/*     */   private int numEntries;
/*     */   private int numComps;
/*     */   private byte[] bitDepth;
/*     */   private byte[][] lut;
/*     */   
/*     */   private static int computeLength(IndexColorModel icm) {
/*  75 */     int size = icm.getMapSize();
/*  76 */     int[] comp = icm.getComponentSize();
/*  77 */     return 11 + comp.length + size * comp.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] getCompSize(IndexColorModel icm) {
/*  84 */     int[] comp = icm.getComponentSize();
/*  85 */     int size = comp.length;
/*  86 */     byte[] buf = new byte[size];
/*  87 */     for (int i = 0; i < size; i++)
/*  88 */       buf[i] = (byte)(comp[i] - 1); 
/*  89 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[][] getLUT(IndexColorModel icm) {
/*  96 */     int[] comp = icm.getComponentSize();
/*  97 */     int size = icm.getMapSize();
/*  98 */     byte[][] lut = new byte[comp.length][size];
/*  99 */     icm.getReds(lut[0]);
/* 100 */     icm.getGreens(lut[1]);
/* 101 */     icm.getBlues(lut[2]);
/* 102 */     if (comp.length == 4)
/* 103 */       icm.getAlphas(lut[3]); 
/* 104 */     return lut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PaletteBox(IndexColorModel icm) {
/* 111 */     this(computeLength(icm), getCompSize(icm), getLUT(icm));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PaletteBox(Node node) throws IIOInvalidTreeException {
/* 118 */     super(node);
/* 119 */     byte[][] tlut = (byte[][])null;
/* 120 */     int index = 0;
/*     */     
/* 122 */     NodeList children = node.getChildNodes(); int i;
/* 123 */     for (i = 0; i < children.getLength(); i++) {
/* 124 */       Node child = children.item(i);
/* 125 */       String name = child.getNodeName();
/*     */       
/* 127 */       if ("NumberEntries".equals(name)) {
/* 128 */         this.numEntries = Box.getIntElementValue(child);
/*     */       }
/*     */       
/* 131 */       if ("NumberColors".equals(name)) {
/* 132 */         this.numComps = Box.getIntElementValue(child);
/*     */       }
/*     */       
/* 135 */       if ("BitDepth".equals(name)) {
/* 136 */         this.bitDepth = Box.getByteArrayElementValue(child);
/*     */       }
/*     */       
/* 139 */       if ("LUT".equals(name)) {
/* 140 */         tlut = new byte[this.numEntries][];
/*     */         
/* 142 */         NodeList children1 = child.getChildNodes();
/*     */         
/* 144 */         for (int j = 0; j < children1.getLength(); j++) {
/* 145 */           Node child1 = children1.item(j);
/* 146 */           name = child1.getNodeName();
/* 147 */           if ("LUTRow".equals(name)) {
/* 148 */             tlut[index++] = Box.getByteArrayElementValue(child1);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 156 */     this.lut = new byte[this.numComps][this.numEntries];
/*     */     
/* 158 */     for (i = 0; i < this.numComps; i++) {
/* 159 */       for (int j = 0; j < this.numEntries; j++) {
/* 160 */         this.lut[i][j] = tlut[j][i];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PaletteBox(int length, byte[] comp, byte[][] lut) {
/* 168 */     super(length, 1885564018, null);
/* 169 */     this.bitDepth = comp;
/* 170 */     this.lut = lut;
/* 171 */     this.numEntries = (lut[0]).length;
/* 172 */     this.numComps = lut.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PaletteBox(byte[] data) {
/* 178 */     super(8 + data.length, 1885564018, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumEntries() {
/* 183 */     return this.numEntries;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumComp() {
/* 188 */     return this.numComps;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getBitDepths() {
/* 193 */     return this.bitDepth;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[][] getLUT() {
/* 198 */     return this.lut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getNativeNode() {
/* 206 */     IIOMetadataNode node = new IIOMetadataNode(Box.getName(getType()));
/* 207 */     setDefaultAttributes(node);
/*     */     
/* 209 */     IIOMetadataNode child = new IIOMetadataNode("NumberEntries");
/* 210 */     child.setUserObject(new Integer(this.numEntries));
/* 211 */     child.setNodeValue("" + this.numEntries);
/* 212 */     node.appendChild(child);
/*     */     
/* 214 */     child = new IIOMetadataNode("NumberColors");
/* 215 */     child.setUserObject(new Integer(this.numComps));
/* 216 */     child.setNodeValue("" + this.numComps);
/* 217 */     node.appendChild(child);
/*     */     
/* 219 */     child = new IIOMetadataNode("BitDepth");
/* 220 */     child.setUserObject(this.bitDepth);
/* 221 */     child.setNodeValue(ImageUtil.convertObjectToString(this.bitDepth));
/* 222 */     node.appendChild(child);
/*     */     
/* 224 */     child = new IIOMetadataNode("LUT");
/* 225 */     for (int i = 0; i < this.numEntries; i++) {
/* 226 */       IIOMetadataNode child1 = new IIOMetadataNode("LUTRow");
/* 227 */       byte[] row = new byte[this.numComps];
/* 228 */       for (int j = 0; j < this.numComps; j++) {
/* 229 */         row[j] = this.lut[j][i];
/*     */       }
/* 231 */       child1.setUserObject(row);
/* 232 */       child1.setNodeValue(ImageUtil.convertObjectToString(row));
/* 233 */       child.appendChild(child1);
/*     */     } 
/* 235 */     node.appendChild(child);
/*     */     
/* 237 */     return node;
/*     */   }
/*     */   
/*     */   protected void parse(byte[] data) {
/* 241 */     if (data == null)
/*     */       return; 
/* 243 */     this.numEntries = (short)((data[0] & 0xFF) << 8 | data[1] & 0xFF);
/*     */     
/* 245 */     this.numComps = data[2];
/* 246 */     this.bitDepth = new byte[this.numComps];
/* 247 */     System.arraycopy(data, 3, this.bitDepth, 0, this.numComps);
/*     */     
/* 249 */     this.lut = new byte[this.numComps][this.numEntries];
/* 250 */     for (int i = 0, k = 3 + this.numComps; i < this.numEntries; i++) {
/* 251 */       for (int j = 0; j < this.numComps; j++)
/* 252 */         this.lut[j][i] = data[k++]; 
/*     */     } 
/*     */   }
/*     */   protected void compose() {
/* 256 */     if (this.data != null)
/*     */       return; 
/* 258 */     this.data = new byte[3 + this.numComps + this.numEntries * this.numComps];
/* 259 */     this.data[0] = (byte)(this.numEntries >> 8);
/* 260 */     this.data[1] = (byte)(this.numEntries & 0xFF);
/*     */     
/* 262 */     this.data[2] = (byte)this.numComps;
/* 263 */     System.arraycopy(this.bitDepth, 0, this.data, 3, this.numComps);
/*     */     
/* 265 */     for (int i = 0, k = 3 + this.numComps; i < this.numEntries; i++) {
/* 266 */       for (int j = 0; j < this.numComps; j++)
/* 267 */         this.data[k++] = this.lut[j][i]; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/PaletteBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */