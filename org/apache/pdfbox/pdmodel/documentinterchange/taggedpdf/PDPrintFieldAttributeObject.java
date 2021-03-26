/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDPrintFieldAttributeObject
/*     */   extends PDStandardAttributeObject
/*     */ {
/*     */   public static final String OWNER_PRINT_FIELD = "PrintField";
/*     */   private static final String ROLE = "Role";
/*     */   private static final String CHECKED = "checked";
/*     */   private static final String DESC = "Desc";
/*     */   public static final String ROLE_RB = "rb";
/*     */   public static final String ROLE_CB = "cb";
/*     */   public static final String ROLE_PB = "pb";
/*     */   public static final String ROLE_TV = "tv";
/*     */   public static final String CHECKED_STATE_ON = "on";
/*     */   public static final String CHECKED_STATE_OFF = "off";
/*     */   public static final String CHECKED_STATE_NEUTRAL = "neutral";
/*     */   
/*     */   public PDPrintFieldAttributeObject() {
/*  73 */     setOwner("PrintField");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPrintFieldAttributeObject(COSDictionary dictionary) {
/*  83 */     super(dictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRole() {
/*  94 */     return getName("Role");
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
/*     */ 
/*     */   
/*     */   public void setRole(String role) {
/* 110 */     setName("Role", role);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCheckedState() {
/* 120 */     return getName("checked", "off");
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
/*     */   
/*     */   public void setCheckedState(String checkedState) {
/* 135 */     setName("checked", checkedState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateName() {
/* 145 */     return getString("Desc");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateName(String alternateName) {
/* 155 */     setString("Desc", alternateName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     StringBuilder sb = (new StringBuilder()).append(super.toString());
/* 162 */     if (isSpecified("Role"))
/*     */     {
/* 164 */       sb.append(", Role=").append(getRole());
/*     */     }
/* 166 */     if (isSpecified("checked"))
/*     */     {
/* 168 */       sb.append(", Checked=").append(getCheckedState());
/*     */     }
/* 170 */     if (isSpecified("Desc"))
/*     */     {
/* 172 */       sb.append(", Desc=").append(getAlternateName());
/*     */     }
/* 174 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDPrintFieldAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */