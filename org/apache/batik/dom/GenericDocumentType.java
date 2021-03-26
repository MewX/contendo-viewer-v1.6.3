/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.NamedNodeMap;
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
/*     */ public class GenericDocumentType
/*     */   extends AbstractChildNode
/*     */   implements DocumentType
/*     */ {
/*     */   protected String qualifiedName;
/*     */   protected String publicId;
/*     */   protected String systemId;
/*     */   
/*     */   public GenericDocumentType(String qualifiedName, String publicId, String systemId) {
/*  57 */     this.qualifiedName = qualifiedName;
/*  58 */     this.publicId = publicId;
/*  59 */     this.systemId = systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  67 */     return this.qualifiedName;
/*     */   }
/*     */   
/*     */   public short getNodeType() {
/*  71 */     return 10;
/*     */   }
/*     */   
/*     */   public boolean isReadonly() {
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean ro) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  86 */     return this.qualifiedName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedNodeMap getEntities() {
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedNodeMap getNotations() {
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPublicId() {
/* 110 */     return this.publicId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/* 118 */     return this.systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInternalSubset() {
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   protected Node newNode() {
/* 130 */     return new GenericDocumentType(this.qualifiedName, this.publicId, this.systemId);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericDocumentType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */