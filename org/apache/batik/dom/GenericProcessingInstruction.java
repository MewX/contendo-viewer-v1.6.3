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
/*     */ public class GenericProcessingInstruction
/*     */   extends AbstractProcessingInstruction
/*     */ {
/*     */   protected String target;
/*     */   protected boolean readonly;
/*     */   
/*     */   protected GenericProcessingInstruction() {}
/*     */   
/*     */   public GenericProcessingInstruction(String target, String data, AbstractDocument owner) {
/*  55 */     this.ownerDocument = owner;
/*  56 */     setTarget(target);
/*  57 */     setData(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeName(String v) {
/*  64 */     setTarget(v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadonly() {
/*  71 */     return this.readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean v) {
/*  78 */     this.readonly = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTarget() {
/*  87 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(String v) {
/*  94 */     this.target = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 102 */     GenericProcessingInstruction p = (GenericProcessingInstruction)super.export(n, d);
/* 103 */     p.setTarget(getTarget());
/* 104 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 112 */     GenericProcessingInstruction p = (GenericProcessingInstruction)super.deepExport(n, d);
/* 113 */     p.setTarget(getTarget());
/* 114 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 123 */     GenericProcessingInstruction p = (GenericProcessingInstruction)super.copyInto(n);
/* 124 */     p.setTarget(getTarget());
/* 125 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 134 */     GenericProcessingInstruction p = (GenericProcessingInstruction)super.deepCopyInto(n);
/* 135 */     p.setTarget(getTarget());
/* 136 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 143 */     return new GenericProcessingInstruction();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericProcessingInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */