/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractProcessingInstruction
/*     */   extends AbstractChildNode
/*     */   implements ProcessingInstruction
/*     */ {
/*     */   protected String data;
/*     */   
/*     */   public String getNodeName() {
/*  46 */     return getTarget();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNodeType() {
/*  54 */     return 7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeValue() throws DOMException {
/*  62 */     return getData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*  69 */     setData(nodeValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getData() {
/*  78 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(String data) throws DOMException {
/*  86 */     if (isReadonly()) {
/*  87 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  92 */     String val = this.data;
/*  93 */     this.data = data;
/*     */ 
/*     */     
/*  96 */     fireDOMCharacterDataModifiedEvent(val, this.data);
/*  97 */     if (getParentNode() != null) {
/*  98 */       ((AbstractParentNode)getParentNode()).fireDOMSubtreeModifiedEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTextContent() {
/* 107 */     return getNodeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 115 */     AbstractProcessingInstruction p = (AbstractProcessingInstruction)super.export(n, d);
/* 116 */     p.data = this.data;
/* 117 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 125 */     AbstractProcessingInstruction p = (AbstractProcessingInstruction)super.deepExport(n, d);
/* 126 */     p.data = this.data;
/* 127 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 136 */     AbstractProcessingInstruction p = (AbstractProcessingInstruction)super.copyInto(n);
/* 137 */     p.data = this.data;
/* 138 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 147 */     AbstractProcessingInstruction p = (AbstractProcessingInstruction)super.deepCopyInto(n);
/* 148 */     p.data = this.data;
/* 149 */     return p;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractProcessingInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */