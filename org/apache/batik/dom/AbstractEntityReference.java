/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.EntityReference;
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
/*     */ public abstract class AbstractEntityReference
/*     */   extends AbstractParentChildNode
/*     */   implements EntityReference
/*     */ {
/*     */   protected String nodeName;
/*     */   
/*     */   protected AbstractEntityReference() {}
/*     */   
/*     */   protected AbstractEntityReference(String name, AbstractDocument owner) throws DOMException {
/*  57 */     this.ownerDocument = owner;
/*     */     
/*  59 */     if (owner.getStrictErrorChecking() && !DOMUtilities.isValidName(name)) {
/*  60 */       throw createDOMException((short)5, "xml.name", new Object[] { name });
/*     */     }
/*     */ 
/*     */     
/*  64 */     this.nodeName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNodeType() {
/*  72 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeName(String v) {
/*  79 */     this.nodeName = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  87 */     return this.nodeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/*  94 */     super.export(n, d);
/*  95 */     AbstractEntityReference ae = (AbstractEntityReference)n;
/*  96 */     ae.nodeName = this.nodeName;
/*  97 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 104 */     super.deepExport(n, d);
/* 105 */     AbstractEntityReference ae = (AbstractEntityReference)n;
/* 106 */     ae.nodeName = this.nodeName;
/* 107 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 115 */     super.copyInto(n);
/* 116 */     AbstractEntityReference ae = (AbstractEntityReference)n;
/* 117 */     ae.nodeName = this.nodeName;
/* 118 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 126 */     super.deepCopyInto(n);
/* 127 */     AbstractEntityReference ae = (AbstractEntityReference)n;
/* 128 */     ae.nodeName = this.nodeName;
/* 129 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkChildType(Node n, boolean replace) {
/* 136 */     switch (n.getNodeType()) {
/*     */       case 1:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 7:
/*     */       case 8:
/*     */       case 11:
/*     */         return;
/*     */     } 
/* 146 */     throw createDOMException((short)3, "child.type", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), Integer.valueOf(n.getNodeType()), n.getNodeName() });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractEntityReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */