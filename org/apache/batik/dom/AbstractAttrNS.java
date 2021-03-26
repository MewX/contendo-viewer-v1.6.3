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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractAttrNS
/*     */   extends AbstractAttr
/*     */ {
/*     */   protected String namespaceURI;
/*     */   
/*     */   protected AbstractAttrNS() {}
/*     */   
/*     */   protected AbstractAttrNS(String nsURI, String qname, AbstractDocument owner) throws DOMException {
/*  70 */     super(qname, owner);
/*  71 */     if (nsURI != null && nsURI.length() == 0) {
/*  72 */       nsURI = null;
/*     */     }
/*  74 */     this.namespaceURI = nsURI;
/*  75 */     String prefix = DOMUtilities.getPrefix(qname);
/*  76 */     if (!owner.getStrictErrorChecking()) {
/*     */       return;
/*     */     }
/*  79 */     if (prefix != null) {
/*  80 */       if (nsURI == null || ("xml".equals(prefix) && !"http://www.w3.org/XML/1998/namespace".equals(nsURI)) || ("xmlns".equals(prefix) && !"http://www.w3.org/2000/xmlns/".equals(nsURI)))
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*  85 */         throw createDOMException((short)14, "namespace.uri", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), nsURI });
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*  92 */     else if ("xmlns".equals(qname) && !"http://www.w3.org/2000/xmlns/".equals(nsURI)) {
/*     */       
/*  94 */       throw createDOMException((short)14, "namespace.uri", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), nsURI });
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
/*     */   public String getNamespaceURI() {
/* 107 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 114 */     super.export(n, d);
/* 115 */     AbstractAttrNS aa = (AbstractAttrNS)n;
/* 116 */     aa.namespaceURI = this.namespaceURI;
/* 117 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 124 */     super.deepExport(n, d);
/* 125 */     AbstractAttrNS aa = (AbstractAttrNS)n;
/* 126 */     aa.namespaceURI = this.namespaceURI;
/* 127 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 135 */     super.copyInto(n);
/* 136 */     AbstractAttrNS aa = (AbstractAttrNS)n;
/* 137 */     aa.namespaceURI = this.namespaceURI;
/* 138 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 146 */     super.deepCopyInto(n);
/* 147 */     AbstractAttrNS aa = (AbstractAttrNS)n;
/* 148 */     aa.namespaceURI = this.namespaceURI;
/* 149 */     return n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractAttrNS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */