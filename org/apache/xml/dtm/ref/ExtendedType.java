/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ExtendedType
/*     */ {
/*     */   private int nodetype;
/*     */   private String namespace;
/*     */   private String localName;
/*     */   private int hash;
/*     */   
/*     */   public ExtendedType(int nodetype, String namespace, String localName) {
/*  42 */     this.nodetype = nodetype;
/*  43 */     this.namespace = namespace;
/*  44 */     this.localName = localName;
/*  45 */     this.hash = nodetype + namespace.hashCode() + localName.hashCode();
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
/*     */   public ExtendedType(int nodetype, String namespace, String localName, int hash) {
/*  59 */     this.nodetype = nodetype;
/*  60 */     this.namespace = namespace;
/*  61 */     this.localName = localName;
/*  62 */     this.hash = hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void redefine(int nodetype, String namespace, String localName) {
/*  72 */     this.nodetype = nodetype;
/*  73 */     this.namespace = namespace;
/*  74 */     this.localName = localName;
/*  75 */     this.hash = nodetype + namespace.hashCode() + localName.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void redefine(int nodetype, String namespace, String localName, int hash) {
/*  85 */     this.nodetype = nodetype;
/*  86 */     this.namespace = namespace;
/*  87 */     this.localName = localName;
/*  88 */     this.hash = hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  96 */     return this.hash;
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
/*     */   public boolean equals(ExtendedType other) {
/*     */     
/* 109 */     try { return (other.nodetype == this.nodetype && other.localName.equals(this.localName) && other.namespace.equals(this.namespace)); } catch (NullPointerException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 115 */       return false; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNodeType() {
/* 124 */     return this.nodetype;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 132 */     return this.localName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespace() {
/* 140 */     return this.namespace;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/ExtendedType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */