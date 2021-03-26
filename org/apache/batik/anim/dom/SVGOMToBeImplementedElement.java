/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
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
/*     */ public class SVGOMToBeImplementedElement
/*     */   extends SVGGraphicsElement
/*     */ {
/*     */   protected String localName;
/*     */   
/*     */   protected SVGOMToBeImplementedElement() {}
/*     */   
/*     */   public SVGOMToBeImplementedElement(String prefix, AbstractDocument owner, String localName) {
/*  54 */     super(prefix, owner);
/*  55 */     this.localName = localName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  62 */     return this.localName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  69 */     return (Node)new SVGOMToBeImplementedElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/*  76 */     super.export(n, d);
/*  77 */     SVGOMToBeImplementedElement ae = (SVGOMToBeImplementedElement)n;
/*  78 */     ae.localName = this.localName;
/*  79 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/*  86 */     super.deepExport(n, d);
/*  87 */     SVGOMToBeImplementedElement ae = (SVGOMToBeImplementedElement)n;
/*  88 */     ae.localName = this.localName;
/*  89 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/*  97 */     super.copyInto(n);
/*  98 */     SVGOMToBeImplementedElement ae = (SVGOMToBeImplementedElement)n;
/*  99 */     ae.localName = this.localName;
/* 100 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 108 */     super.deepCopyInto(n);
/* 109 */     SVGOMToBeImplementedElement ae = (SVGOMToBeImplementedElement)n;
/* 110 */     ae.localName = this.localName;
/* 111 */     return n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMToBeImplementedElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */