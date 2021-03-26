/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.stylesheets.LinkStyle;
/*     */ import org.w3c.dom.stylesheets.StyleSheet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StyleSheetProcessingInstruction
/*     */   extends AbstractProcessingInstruction
/*     */   implements LinkStyle
/*     */ {
/*     */   protected boolean readonly;
/*     */   protected transient StyleSheet sheet;
/*     */   protected StyleSheetFactory factory;
/*     */   protected transient HashMap<String, String> pseudoAttributes;
/*     */   
/*     */   protected StyleSheetProcessingInstruction() {}
/*     */   
/*     */   public StyleSheetProcessingInstruction(String data, AbstractDocument owner, StyleSheetFactory f) {
/*  74 */     this.ownerDocument = owner;
/*  75 */     setData(data);
/*  76 */     this.factory = f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadonly() {
/*  83 */     return this.readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean v) {
/*  90 */     this.readonly = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeName(String v) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTarget() {
/* 105 */     return "xml-stylesheet";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleSheet getSheet() {
/* 112 */     if (this.sheet == null) {
/* 113 */       this.sheet = this.factory.createStyleSheet(this, getPseudoAttributes());
/*     */     }
/* 115 */     return this.sheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HashMap<String, String> getPseudoAttributes() {
/* 122 */     if (this.pseudoAttributes == null) {
/* 123 */       this.pseudoAttributes = new HashMap<String, String>();
/* 124 */       this.pseudoAttributes.put("alternate", "no");
/* 125 */       this.pseudoAttributes.put("media", "all");
/* 126 */       DOMUtilities.parseStyleSheetPIData(this.data, this.pseudoAttributes);
/*     */     } 
/* 128 */     return this.pseudoAttributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(String data) throws DOMException {
/* 136 */     super.setData(data);
/* 137 */     this.sheet = null;
/* 138 */     this.pseudoAttributes = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 145 */     return new StyleSheetProcessingInstruction();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/StyleSheetProcessingInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */