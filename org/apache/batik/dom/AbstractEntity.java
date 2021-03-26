/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.w3c.dom.Entity;
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
/*     */ public abstract class AbstractEntity
/*     */   extends AbstractParentNode
/*     */   implements Entity
/*     */ {
/*     */   protected String nodeName;
/*     */   protected String publicId;
/*     */   protected String systemId;
/*     */   
/*     */   public short getNodeType() {
/*  55 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeName(String v) {
/*  62 */     this.nodeName = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  70 */     return this.nodeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPublicId() {
/*  78 */     return this.publicId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPublicId(String id) {
/*  85 */     this.publicId = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/*  93 */     return this.systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSystemId(String id) {
/* 100 */     this.systemId = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNotationName() {
/* 108 */     return getNodeName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNotationName(String name) {
/* 115 */     setNodeName(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInputEncoding() {
/* 122 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXmlEncoding() {
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXmlVersion() {
/* 136 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 143 */     super.export(n, d);
/* 144 */     AbstractEntity ae = (AbstractEntity)n;
/* 145 */     ae.nodeName = this.nodeName;
/* 146 */     ae.publicId = this.publicId;
/* 147 */     ae.systemId = this.systemId;
/* 148 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 155 */     super.deepExport(n, d);
/* 156 */     AbstractEntity ae = (AbstractEntity)n;
/* 157 */     ae.nodeName = this.nodeName;
/* 158 */     ae.publicId = this.publicId;
/* 159 */     ae.systemId = this.systemId;
/* 160 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 168 */     super.copyInto(n);
/* 169 */     AbstractEntity ae = (AbstractEntity)n;
/* 170 */     ae.nodeName = this.nodeName;
/* 171 */     ae.publicId = this.publicId;
/* 172 */     ae.systemId = this.systemId;
/* 173 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 181 */     super.deepCopyInto(n);
/* 182 */     AbstractEntity ae = (AbstractEntity)n;
/* 183 */     ae.nodeName = this.nodeName;
/* 184 */     ae.publicId = this.publicId;
/* 185 */     ae.systemId = this.systemId;
/* 186 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkChildType(Node n, boolean replace) {
/* 193 */     switch (n.getNodeType()) {
/*     */       case 1:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 7:
/*     */       case 8:
/*     */       case 11:
/*     */         return;
/*     */     } 
/* 203 */     throw createDOMException((short)3, "child.type", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), Integer.valueOf(n.getNodeType()), n.getNodeName() });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */