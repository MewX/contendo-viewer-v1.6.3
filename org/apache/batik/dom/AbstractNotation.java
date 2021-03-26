/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.Notation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractNotation
/*     */   extends AbstractNode
/*     */   implements Notation
/*     */ {
/*     */   protected String nodeName;
/*     */   protected String publicId;
/*     */   protected String systemId;
/*     */   
/*     */   public short getNodeType() {
/*  55 */     return 12;
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
/*     */   public String getNodeName() {
/*  69 */     return this.nodeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPublicId() {
/*  77 */     return this.publicId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPublicId(String id) {
/*  84 */     this.publicId = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/*  92 */     return this.systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSystemId(String id) {
/*  99 */     this.systemId = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextContent(String s) throws DOMException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 112 */     super.export(n, d);
/* 113 */     AbstractNotation an = (AbstractNotation)n;
/* 114 */     an.nodeName = this.nodeName;
/* 115 */     an.publicId = this.publicId;
/* 116 */     an.systemId = this.systemId;
/* 117 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 124 */     super.deepExport(n, d);
/* 125 */     AbstractNotation an = (AbstractNotation)n;
/* 126 */     an.nodeName = this.nodeName;
/* 127 */     an.publicId = this.publicId;
/* 128 */     an.systemId = this.systemId;
/* 129 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 137 */     super.copyInto(n);
/* 138 */     AbstractNotation an = (AbstractNotation)n;
/* 139 */     an.nodeName = this.nodeName;
/* 140 */     an.publicId = this.publicId;
/* 141 */     an.systemId = this.systemId;
/* 142 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 150 */     super.deepCopyInto(n);
/* 151 */     AbstractNotation an = (AbstractNotation)n;
/* 152 */     an.nodeName = this.nodeName;
/* 153 */     an.publicId = this.publicId;
/* 154 */     an.systemId = this.systemId;
/* 155 */     return n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractNotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */