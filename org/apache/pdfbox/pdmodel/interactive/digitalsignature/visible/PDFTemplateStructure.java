/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdfwriter.COSWriter;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
/*     */ import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
/*     */ import org.apache.pdfbox.pdmodel.interactive.form.PDField;
/*     */ import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFTemplateStructure
/*     */ {
/*     */   private PDPage page;
/*     */   private PDDocument template;
/*     */   private PDAcroForm acroForm;
/*     */   private PDSignatureField signatureField;
/*     */   private PDSignature pdSignature;
/*     */   private COSDictionary acroFormDictionary;
/*     */   private PDRectangle signatureRectangle;
/*     */   private AffineTransform affineTransform;
/*     */   private COSArray procSet;
/*     */   private PDImageXObject image;
/*     */   private PDRectangle formatterRectangle;
/*     */   private PDStream holderFormStream;
/*     */   private PDResources holderFormResources;
/*     */   private PDFormXObject holderForm;
/*     */   private PDAppearanceDictionary appearanceDictionary;
/*     */   private PDStream innerFormStream;
/*     */   private PDResources innerFormResources;
/*     */   private PDFormXObject innerForm;
/*     */   private PDStream imageFormStream;
/*     */   private PDResources imageFormResources;
/*     */   private List<PDField> acroFormFields;
/*     */   private COSName innerFormName;
/*     */   private COSName imageFormName;
/*     */   private COSName imageName;
/*     */   private COSDocument visualSignature;
/*     */   private PDFormXObject imageForm;
/*     */   private COSDictionary widgetDictionary;
/*     */   
/*     */   public PDPage getPage() {
/*  83 */     return this.page;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPage(PDPage page) {
/*  92 */     this.page = page;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocument getTemplate() {
/* 103 */     return this.template;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTemplate(PDDocument template) {
/* 114 */     this.template = template;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAcroForm getAcroForm() {
/* 123 */     return this.acroForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAcroForm(PDAcroForm acroForm) {
/* 132 */     this.acroForm = acroForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignatureField getSignatureField() {
/* 141 */     return this.signatureField;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSignatureField(PDSignatureField signatureField) {
/* 150 */     this.signatureField = signatureField;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignature getPdSignature() {
/* 159 */     return this.pdSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPdSignature(PDSignature pdSignature) {
/* 168 */     this.pdSignature = pdSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getAcroFormDictionary() {
/* 178 */     return this.acroFormDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAcroFormDictionary(COSDictionary acroFormDictionary) {
/* 189 */     this.acroFormDictionary = acroFormDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getSignatureRectangle() {
/* 198 */     return this.signatureRectangle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSignatureRectangle(PDRectangle signatureRectangle) {
/* 207 */     this.signatureRectangle = signatureRectangle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getAffineTransform() {
/* 216 */     return this.affineTransform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAffineTransform(AffineTransform affineTransform) {
/* 225 */     this.affineTransform = affineTransform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getProcSet() {
/* 234 */     return this.procSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProcSet(COSArray procSet) {
/* 243 */     this.procSet = procSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDImageXObject getImage() {
/* 252 */     return this.image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImage(PDImageXObject image) {
/* 261 */     this.image = image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getFormatterRectangle() {
/* 270 */     return this.formatterRectangle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormatterRectangle(PDRectangle formatterRectangle) {
/* 279 */     this.formatterRectangle = formatterRectangle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream getHolderFormStream() {
/* 288 */     return this.holderFormStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHolderFormStream(PDStream holderFormStream) {
/* 297 */     this.holderFormStream = holderFormStream;
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
/*     */   public PDFormXObject getHolderForm() {
/* 309 */     return this.holderForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHolderForm(PDFormXObject holderForm) {
/* 318 */     this.holderForm = holderForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources getHolderFormResources() {
/* 327 */     return this.holderFormResources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHolderFormResources(PDResources holderFormResources) {
/* 336 */     this.holderFormResources = holderFormResources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAppearanceDictionary getAppearanceDictionary() {
/* 346 */     return this.appearanceDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearanceDictionary(PDAppearanceDictionary appearanceDictionary) {
/* 356 */     this.appearanceDictionary = appearanceDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream getInnerFormStream() {
/* 365 */     return this.innerFormStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInnterFormStream(PDStream innerFormStream) {
/* 374 */     this.innerFormStream = innerFormStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources getInnerFormResources() {
/* 383 */     return this.innerFormResources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInnerFormResources(PDResources innerFormResources) {
/* 392 */     this.innerFormResources = innerFormResources;
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
/*     */   public PDFormXObject getInnerForm() {
/* 404 */     return this.innerForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInnerForm(PDFormXObject innerForm) {
/* 414 */     this.innerForm = innerForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getInnerFormName() {
/* 423 */     return this.innerFormName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInnerFormName(COSName innerFormName) {
/* 432 */     this.innerFormName = innerFormName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream getImageFormStream() {
/* 441 */     return this.imageFormStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImageFormStream(PDStream imageFormStream) {
/* 450 */     this.imageFormStream = imageFormStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources getImageFormResources() {
/* 459 */     return this.imageFormResources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImageFormResources(PDResources imageFormResources) {
/* 468 */     this.imageFormResources = imageFormResources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormXObject getImageForm() {
/* 479 */     return this.imageForm;
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
/*     */   public void setImageForm(PDFormXObject imageForm) {
/* 493 */     this.imageForm = imageForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getImageFormName() {
/* 502 */     return this.imageFormName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImageFormName(COSName imageFormName) {
/* 511 */     this.imageFormName = imageFormName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getImageName() {
/* 520 */     return this.imageName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImageName(COSName imageName) {
/* 529 */     this.imageName = imageName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDocument getVisualSignature() {
/* 539 */     return this.visualSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisualSignature(COSDocument visualSignature) {
/* 550 */     this.visualSignature = visualSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDField> getAcroFormFields() {
/* 559 */     return this.acroFormFields;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAcroFormFields(List<PDField> acroFormFields) {
/* 568 */     this.acroFormFields = acroFormFields;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public ByteArrayInputStream getTemplateAppearanceStream() throws IOException {
/* 593 */     COSDocument visualSignature = getVisualSignature();
/* 594 */     ByteArrayOutputStream memoryOut = new ByteArrayOutputStream();
/* 595 */     COSWriter memoryWriter = new COSWriter(memoryOut);
/* 596 */     memoryWriter.write(visualSignature);
/*     */     
/* 598 */     ByteArrayInputStream input = new ByteArrayInputStream(memoryOut.toByteArray());
/*     */     
/* 600 */     getTemplate().close();
/*     */     
/* 602 */     return input;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getWidgetDictionary() {
/* 612 */     return this.widgetDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidgetDictionary(COSDictionary widgetDictionary) {
/* 622 */     this.widgetDictionary = widgetDictionary;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/visible/PDFTemplateStructure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */