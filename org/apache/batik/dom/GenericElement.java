/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.w3c.dom.DOMException;
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
/*     */ public class GenericElement
/*     */   extends AbstractElement
/*     */ {
/*     */   protected String nodeName;
/*     */   protected boolean readonly;
/*     */   
/*     */   protected GenericElement() {}
/*     */   
/*     */   public GenericElement(String name, AbstractDocument owner) throws DOMException {
/*  56 */     super(name, owner);
/*  57 */     this.nodeName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeName(String v) {
/*  64 */     this.nodeName = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  72 */     return this.nodeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadonly() {
/*  81 */     return this.readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean v) {
/*  88 */     this.readonly = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/*  95 */     super.export(n, d);
/*  96 */     GenericElement ge = (GenericElement)n;
/*  97 */     ge.nodeName = this.nodeName;
/*  98 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 105 */     super.deepExport(n, d);
/* 106 */     GenericElement ge = (GenericElement)n;
/* 107 */     ge.nodeName = this.nodeName;
/* 108 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 116 */     GenericElement ge = (GenericElement)super.copyInto(n);
/* 117 */     ge.nodeName = this.nodeName;
/* 118 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 126 */     GenericElement ge = (GenericElement)super.deepCopyInto(n);
/* 127 */     ge.nodeName = this.nodeName;
/* 128 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 135 */     return new GenericElement();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */