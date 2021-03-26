/*     */ package org.apache.pdfbox.pdmodel.encryption;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AccessPermission
/*     */ {
/*     */   private static final int DEFAULT_PERMISSIONS = -4;
/*     */   private static final int PRINT_BIT = 3;
/*     */   private static final int MODIFICATION_BIT = 4;
/*     */   private static final int EXTRACT_BIT = 5;
/*     */   private static final int MODIFY_ANNOTATIONS_BIT = 6;
/*     */   private static final int FILL_IN_FORM_BIT = 9;
/*     */   private static final int EXTRACT_FOR_ACCESSIBILITY_BIT = 10;
/*     */   private static final int ASSEMBLE_DOCUMENT_BIT = 11;
/*     */   private static final int DEGRADED_PRINT_BIT = 12;
/*  62 */   private int bytes = -4;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean readOnly = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccessPermission() {
/*  72 */     this.bytes = -4;
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
/*     */   public AccessPermission(byte[] b) {
/*  84 */     this.bytes = 0;
/*  85 */     this.bytes |= b[0] & 0xFF;
/*  86 */     this.bytes <<= 8;
/*  87 */     this.bytes |= b[1] & 0xFF;
/*  88 */     this.bytes <<= 8;
/*  89 */     this.bytes |= b[2] & 0xFF;
/*  90 */     this.bytes <<= 8;
/*  91 */     this.bytes |= b[3] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccessPermission(int permissions) {
/* 101 */     this.bytes = permissions;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isPermissionBitOn(int bit) {
/* 106 */     return ((this.bytes & 1 << bit - 1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean setPermissionBit(int bit, boolean value) {
/* 111 */     int permissions = this.bytes;
/* 112 */     if (value) {
/*     */       
/* 114 */       permissions |= 1 << bit - 1;
/*     */     }
/*     */     else {
/*     */       
/* 118 */       permissions &= 1 << bit - 1 ^ 0xFFFFFFFF;
/*     */     } 
/* 120 */     this.bytes = permissions;
/*     */     
/* 122 */     return ((this.bytes & 1 << bit - 1) != 0);
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
/*     */   public boolean isOwnerPermission() {
/* 136 */     return (canAssembleDocument() && 
/* 137 */       canExtractContent() && 
/* 138 */       canExtractForAccessibility() && 
/* 139 */       canFillInForm() && 
/* 140 */       canModify() && 
/* 141 */       canModifyAnnotations() && 
/* 142 */       canPrint() && 
/* 143 */       canPrintDegraded());
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
/*     */   public static AccessPermission getOwnerAccessPermission() {
/* 155 */     AccessPermission ret = new AccessPermission();
/* 156 */     ret.setCanAssembleDocument(true);
/* 157 */     ret.setCanExtractContent(true);
/* 158 */     ret.setCanExtractForAccessibility(true);
/* 159 */     ret.setCanFillInForm(true);
/* 160 */     ret.setCanModify(true);
/* 161 */     ret.setCanModifyAnnotations(true);
/* 162 */     ret.setCanPrint(true);
/* 163 */     ret.setCanPrintDegraded(true);
/* 164 */     return ret;
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
/*     */   public int getPermissionBytesForPublicKey() {
/* 178 */     setPermissionBit(1, true);
/* 179 */     setPermissionBit(7, false);
/* 180 */     setPermissionBit(8, false);
/* 181 */     for (int i = 13; i <= 32; i++)
/*     */     {
/* 183 */       setPermissionBit(i, false);
/*     */     }
/* 185 */     return this.bytes;
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
/*     */   public int getPermissionBytes() {
/* 197 */     return this.bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPrint() {
/* 207 */     return isPermissionBitOn(3);
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
/*     */   public void setCanPrint(boolean allowPrinting) {
/* 219 */     if (!this.readOnly)
/*     */     {
/* 221 */       setPermissionBit(3, allowPrinting);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canModify() {
/* 232 */     return isPermissionBitOn(4);
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
/*     */   public void setCanModify(boolean allowModifications) {
/* 244 */     if (!this.readOnly)
/*     */     {
/* 246 */       setPermissionBit(4, allowModifications);
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
/*     */   public boolean canExtractContent() {
/* 258 */     return isPermissionBitOn(5);
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
/*     */   public void setCanExtractContent(boolean allowExtraction) {
/* 271 */     if (!this.readOnly)
/*     */     {
/* 273 */       setPermissionBit(5, allowExtraction);
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
/*     */   public boolean canModifyAnnotations() {
/* 288 */     return isPermissionBitOn(6);
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
/*     */   public void setCanModifyAnnotations(boolean allowAnnotationModification) {
/* 304 */     if (!this.readOnly)
/*     */     {
/* 306 */       setPermissionBit(6, allowAnnotationModification);
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
/*     */   public boolean canFillInForm() {
/* 318 */     return isPermissionBitOn(9);
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
/*     */   public void setCanFillInForm(boolean allowFillingInForm) {
/* 333 */     if (!this.readOnly)
/*     */     {
/* 335 */       setPermissionBit(9, allowFillingInForm);
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
/*     */   public boolean canExtractForAccessibility() {
/* 348 */     return isPermissionBitOn(10);
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
/*     */   public void setCanExtractForAccessibility(boolean allowExtraction) {
/* 361 */     if (!this.readOnly)
/*     */     {
/* 363 */       setPermissionBit(10, allowExtraction);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAssembleDocument() {
/* 374 */     return isPermissionBitOn(11);
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
/*     */   public void setCanAssembleDocument(boolean allowAssembly) {
/* 386 */     if (!this.readOnly)
/*     */     {
/* 388 */       setPermissionBit(11, allowAssembly);
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
/*     */   public boolean canPrintDegraded() {
/* 400 */     return isPermissionBitOn(12);
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
/*     */   public void setCanPrintDegraded(boolean canPrintDegraded) {
/* 413 */     if (!this.readOnly)
/*     */     {
/* 415 */       setPermissionBit(12, canPrintDegraded);
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
/*     */   public void setReadOnly() {
/* 427 */     this.readOnly = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/* 438 */     return this.readOnly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasAnyRevision3PermissionSet() {
/* 448 */     if (canFillInForm())
/*     */     {
/* 450 */       return true;
/*     */     }
/* 452 */     if (canExtractForAccessibility())
/*     */     {
/* 454 */       return true;
/*     */     }
/* 456 */     if (canAssembleDocument())
/*     */     {
/* 458 */       return true;
/*     */     }
/* 460 */     return canPrintDegraded();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/AccessPermission.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */