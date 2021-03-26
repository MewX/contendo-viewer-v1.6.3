/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.util.XBLConstants;
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
/*     */ public abstract class XBLOMElement
/*     */   extends SVGOMElement
/*     */   implements XBLConstants
/*     */ {
/*     */   protected String prefix;
/*     */   
/*     */   protected XBLOMElement() {}
/*     */   
/*     */   protected XBLOMElement(String prefix, AbstractDocument owner) {
/*  54 */     this.ownerDocument = owner;
/*  55 */     setPrefix(prefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  62 */     if (this.prefix == null || this.prefix.equals("")) {
/*  63 */       return getLocalName();
/*     */     }
/*     */     
/*  66 */     return this.prefix + ':' + getLocalName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  73 */     return "http://www.w3.org/2004/xbl";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  80 */     if (isReadonly()) {
/*  81 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  86 */     if (prefix != null && !prefix.equals("") && !DOMUtilities.isValidName(prefix))
/*     */     {
/*     */       
/*  89 */       throw createDOMException((short)5, "prefix", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), prefix });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     this.prefix = prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 102 */     super.export(n, d);
/* 103 */     XBLOMElement e = (XBLOMElement)n;
/* 104 */     e.prefix = this.prefix;
/* 105 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 112 */     super.deepExport(n, d);
/* 113 */     XBLOMElement e = (XBLOMElement)n;
/* 114 */     e.prefix = this.prefix;
/* 115 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 123 */     super.copyInto(n);
/* 124 */     XBLOMElement e = (XBLOMElement)n;
/* 125 */     e.prefix = this.prefix;
/* 126 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 134 */     super.deepCopyInto(n);
/* 135 */     XBLOMElement e = (XBLOMElement)n;
/* 136 */     e.prefix = this.prefix;
/* 137 */     return n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/XBLOMElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */