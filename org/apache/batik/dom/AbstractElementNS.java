/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.apache.batik.dom.util.DOMUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractElementNS
/*     */   extends AbstractElement
/*     */ {
/*     */   protected String namespaceURI;
/*     */   
/*     */   protected AbstractElementNS() {}
/*     */   
/*     */   protected AbstractElementNS(String nsURI, String qname, AbstractDocument owner) throws DOMException {
/*  63 */     super(qname, owner);
/*  64 */     if (nsURI != null && nsURI.length() == 0) {
/*  65 */       nsURI = null;
/*     */     }
/*  67 */     this.namespaceURI = nsURI;
/*  68 */     String prefix = DOMUtilities.getPrefix(qname);
/*  69 */     if (prefix != null && (
/*  70 */       nsURI == null || ("xml".equals(prefix) && !"http://www.w3.org/XML/1998/namespace".equals(nsURI))))
/*     */     {
/*     */       
/*  73 */       throw createDOMException((short)14, "namespace.uri", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), nsURI });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  88 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/*  95 */     super.export(n, d);
/*  96 */     AbstractElementNS ae = (AbstractElementNS)n;
/*  97 */     ae.namespaceURI = this.namespaceURI;
/*  98 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 105 */     super.deepExport(n, d);
/* 106 */     AbstractElementNS ae = (AbstractElementNS)n;
/* 107 */     ae.namespaceURI = this.namespaceURI;
/* 108 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 116 */     super.copyInto(n);
/* 117 */     AbstractElementNS ae = (AbstractElementNS)n;
/* 118 */     ae.namespaceURI = this.namespaceURI;
/* 119 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 127 */     super.deepCopyInto(n);
/* 128 */     AbstractElementNS ae = (AbstractElementNS)n;
/* 129 */     ae.namespaceURI = this.namespaceURI;
/* 130 */     return n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractElementNS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */