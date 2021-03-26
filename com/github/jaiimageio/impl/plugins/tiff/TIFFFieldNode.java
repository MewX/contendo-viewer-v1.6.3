/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFDirectory;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFTag;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFFieldNode
/*     */   extends IIOMetadataNode
/*     */ {
/*     */   private boolean isIFD;
/*     */   
/*     */   private static String getNodeName(TIFFField f) {
/*  74 */     return (f.getData() instanceof TIFFDirectory) ? "TIFFIFD" : "TIFFField";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private Boolean isInitialized = Boolean.FALSE;
/*     */   
/*     */   private TIFFField field;
/*     */ 
/*     */   
/*     */   public TIFFFieldNode(TIFFField field) {
/*  87 */     super(getNodeName(field));
/*     */     
/*  89 */     this.isIFD = field.getData() instanceof TIFFDirectory;
/*     */     
/*  91 */     this.field = field;
/*     */     
/*  93 */     TIFFTag tag = field.getTag();
/*  94 */     int tagNumber = tag.getNumber();
/*  95 */     String tagName = tag.getName();
/*     */     
/*  97 */     if (this.isIFD) {
/*  98 */       if (tagNumber != 0) {
/*  99 */         setAttribute("parentTagNumber", Integer.toString(tagNumber));
/*     */       }
/* 101 */       if (tagName != null) {
/* 102 */         setAttribute("parentTagName", tagName);
/*     */       }
/*     */       
/* 105 */       TIFFDirectory dir = (TIFFDirectory)field.getData();
/* 106 */       TIFFTagSet[] tagSets = dir.getTagSets();
/* 107 */       if (tagSets != null) {
/* 108 */         String tagSetNames = "";
/* 109 */         for (int i = 0; i < tagSets.length; i++) {
/* 110 */           tagSetNames = tagSetNames + tagSets[i].getClass().getName();
/* 111 */           if (i != tagSets.length - 1) {
/* 112 */             tagSetNames = tagSetNames + ",";
/*     */           }
/*     */         } 
/* 115 */         setAttribute("tagSets", tagSetNames);
/*     */       } 
/*     */     } else {
/* 118 */       setAttribute("number", Integer.toString(tagNumber));
/* 119 */       setAttribute("name", tagName);
/*     */     } 
/*     */   }
/*     */   
/*     */   private synchronized void initialize() {
/* 124 */     if (this.isInitialized == Boolean.TRUE)
/*     */       return; 
/* 126 */     if (this.isIFD) {
/* 127 */       TIFFDirectory dir = (TIFFDirectory)this.field.getData();
/* 128 */       TIFFField[] fields = dir.getTIFFFields();
/* 129 */       if (fields != null) {
/* 130 */         TIFFTagSet[] tagSets = dir.getTagSets();
/* 131 */         List<TIFFTagSet> tagSetList = Arrays.asList(tagSets);
/* 132 */         int numFields = fields.length;
/* 133 */         for (int i = 0; i < numFields; i++) {
/* 134 */           TIFFField f = fields[i];
/* 135 */           int tagNumber = f.getTagNumber();
/* 136 */           TIFFTag tag = TIFFIFD.getTag(tagNumber, tagSetList);
/*     */           
/* 138 */           Node node = f.getAsNativeNode();
/*     */           
/* 140 */           if (node != null) {
/* 141 */             appendChild(node);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       IIOMetadataNode child;
/* 147 */       int count = this.field.getCount();
/* 148 */       if (this.field.getType() == 7) {
/* 149 */         child = new IIOMetadataNode("TIFFUndefined");
/*     */         
/* 151 */         byte[] data = this.field.getAsBytes();
/* 152 */         StringBuffer sb = new StringBuffer();
/* 153 */         for (int i = 0; i < count; i++) {
/* 154 */           sb.append(Integer.toString(data[i] & 0xFF));
/* 155 */           if (i < count - 1) {
/* 156 */             sb.append(",");
/*     */           }
/*     */         } 
/* 159 */         child.setAttribute("value", sb.toString());
/*     */       } else {
/*     */         
/* 162 */         child = new IIOMetadataNode("TIFF" + TIFFField.getTypeName(this.field.getType()) + "s");
/*     */ 
/*     */         
/* 165 */         TIFFTag tag = this.field.getTag();
/*     */         
/* 167 */         for (int i = 0; i < count; i++) {
/*     */ 
/*     */           
/* 170 */           IIOMetadataNode cchild = new IIOMetadataNode("TIFF" + TIFFField.getTypeName(this.field.getType()));
/*     */           
/* 172 */           cchild.setAttribute("value", this.field.getValueAsString(i));
/* 173 */           if (tag.hasValueNames() && this.field.isIntegral()) {
/* 174 */             int value = this.field.getAsInt(i);
/* 175 */             String name = tag.getValueName(value);
/* 176 */             if (name != null) {
/* 177 */               cchild.setAttribute("description", name);
/*     */             }
/*     */           } 
/*     */           
/* 181 */           child.appendChild(cchild);
/*     */         } 
/*     */       } 
/* 184 */       appendChild(child);
/*     */     } 
/*     */     
/* 187 */     this.isInitialized = Boolean.TRUE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Node appendChild(Node newChild) {
/* 193 */     if (newChild == null) {
/* 194 */       throw new IllegalArgumentException("newChild == null!");
/*     */     }
/*     */     
/* 197 */     return super.insertBefore(newChild, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasChildNodes() {
/* 203 */     initialize();
/* 204 */     return super.hasChildNodes();
/*     */   }
/*     */   
/*     */   public int getLength() {
/* 208 */     initialize();
/* 209 */     return super.getLength();
/*     */   }
/*     */   
/*     */   public Node getFirstChild() {
/* 213 */     initialize();
/* 214 */     return super.getFirstChild();
/*     */   }
/*     */   
/*     */   public Node getLastChild() {
/* 218 */     initialize();
/* 219 */     return super.getLastChild();
/*     */   }
/*     */   
/*     */   public Node getPreviousSibling() {
/* 223 */     initialize();
/* 224 */     return super.getPreviousSibling();
/*     */   }
/*     */   
/*     */   public Node getNextSibling() {
/* 228 */     initialize();
/* 229 */     return super.getNextSibling();
/*     */   }
/*     */ 
/*     */   
/*     */   public Node insertBefore(Node newChild, Node refChild) {
/* 234 */     initialize();
/* 235 */     return super.insertBefore(newChild, refChild);
/*     */   }
/*     */ 
/*     */   
/*     */   public Node replaceChild(Node newChild, Node oldChild) {
/* 240 */     initialize();
/* 241 */     return super.replaceChild(newChild, oldChild);
/*     */   }
/*     */   
/*     */   public Node removeChild(Node oldChild) {
/* 245 */     initialize();
/* 246 */     return super.removeChild(oldChild);
/*     */   }
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 250 */     initialize();
/* 251 */     return super.cloneNode(deep);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFFieldNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */