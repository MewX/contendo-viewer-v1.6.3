/*     */ package jp.cssj.sakae.sac.parser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultAttributeCondition
/*     */   extends AbstractAttributeCondition
/*     */ {
/*     */   protected String localName;
/*     */   protected String namespaceURI;
/*     */   protected boolean specified;
/*     */   
/*     */   public DefaultAttributeCondition(String localName, String namespaceURI, boolean specified, String value) {
/*  82 */     super(value);
/*  83 */     this.localName = localName;
/*  84 */     this.namespaceURI = namespaceURI;
/*  85 */     this.specified = specified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getConditionType() {
/*  93 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/* 101 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 109 */     return this.localName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSpecified() {
/* 117 */     return this.specified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     String qName;
/* 125 */     if (this.namespaceURI == null) {
/* 126 */       qName = this.localName;
/*     */     } else {
/* 128 */       qName = this.namespaceURI + "|" + this.localName;
/*     */     } 
/* 130 */     if (this.value == null) {
/* 131 */       return "[" + qName + "]";
/*     */     }
/* 133 */     return "[" + qName + "=\"" + this.value + "\"]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/DefaultAttributeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */