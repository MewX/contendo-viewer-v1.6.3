/*     */ package org.apache.batik.dom;
/*     */ 
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
/*     */ public class GenericElementNS
/*     */   extends AbstractElementNS
/*     */ {
/*     */   protected String nodeName;
/*     */   protected boolean readonly;
/*     */   
/*     */   protected GenericElementNS() {}
/*     */   
/*     */   public GenericElementNS(String nsURI, String name, AbstractDocument owner) {
/*  64 */     super(nsURI, name, owner);
/*  65 */     this.nodeName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeName(String v) {
/*  72 */     this.nodeName = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  80 */     return this.nodeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadonly() {
/*  89 */     return this.readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean v) {
/*  96 */     this.readonly = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 103 */     GenericElementNS ge = (GenericElementNS)super.export(n, d);
/* 104 */     ge.nodeName = this.nodeName;
/* 105 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 112 */     GenericElementNS ge = (GenericElementNS)super.deepExport(n, d);
/* 113 */     ge.nodeName = this.nodeName;
/* 114 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 122 */     GenericElementNS ge = (GenericElementNS)super.copyInto(n);
/* 123 */     ge.nodeName = this.nodeName;
/* 124 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 132 */     GenericElementNS ge = (GenericElementNS)super.deepCopyInto(n);
/* 133 */     ge.nodeName = this.nodeName;
/* 134 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 141 */     return new GenericElementNS();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericElementNS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */