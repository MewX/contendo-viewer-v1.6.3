/*     */ package org.apache.xmlgraphics.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QName
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5225376740044770690L;
/*     */   private String namespaceURI;
/*     */   private String localName;
/*     */   private String prefix;
/*     */   private int hashCode;
/*     */   
/*     */   public QName(String namespaceURI, String prefix, String localName) {
/*  46 */     if (localName == null) {
/*  47 */       throw new NullPointerException("Parameter localName must not be null");
/*     */     }
/*  49 */     if (localName.length() == 0) {
/*  50 */       throw new IllegalArgumentException("Parameter localName must not be empty");
/*     */     }
/*  52 */     this.namespaceURI = namespaceURI;
/*  53 */     this.prefix = prefix;
/*  54 */     this.localName = localName;
/*  55 */     this.hashCode = toHashString().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QName(String namespaceURI, String qName) {
/*  64 */     if (qName == null) {
/*  65 */       throw new NullPointerException("Parameter localName must not be null");
/*     */     }
/*  67 */     if (qName.length() == 0) {
/*  68 */       throw new IllegalArgumentException("Parameter localName must not be empty");
/*     */     }
/*  70 */     this.namespaceURI = namespaceURI;
/*  71 */     int p = qName.indexOf(':');
/*  72 */     if (p > 0) {
/*  73 */       this.prefix = qName.substring(0, p);
/*  74 */       this.localName = qName.substring(p + 1);
/*     */     } else {
/*  76 */       this.prefix = null;
/*  77 */       this.localName = qName;
/*     */     } 
/*  79 */     this.hashCode = toHashString().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  84 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/*  89 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  94 */     return this.localName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQName() {
/*  99 */     return (getPrefix() != null) ? (getPrefix() + ':' + getLocalName()) : getLocalName();
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 104 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 109 */     if (obj == null)
/* 110 */       return false; 
/* 111 */     if (obj == this) {
/* 112 */       return true;
/*     */     }
/* 114 */     if (obj instanceof QName) {
/* 115 */       QName other = (QName)obj;
/* 116 */       if ((getNamespaceURI() == null && other.getNamespaceURI() == null) || getNamespaceURI().equals(other.getNamespaceURI()))
/*     */       {
/* 118 */         return getLocalName().equals(other.getLocalName());
/*     */       }
/*     */     } 
/*     */     
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 127 */     return (this.prefix != null) ? (this.prefix + ":" + this.localName) : toHashString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String toHashString() {
/* 133 */     return (this.namespaceURI != null) ? ("{" + this.namespaceURI + "}" + this.localName) : this.localName;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/QName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */