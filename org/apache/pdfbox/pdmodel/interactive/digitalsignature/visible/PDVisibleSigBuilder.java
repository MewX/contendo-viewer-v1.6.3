/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
/*     */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
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
/*     */ public class PDVisibleSigBuilder
/*     */   implements PDFTemplateBuilder
/*     */ {
/*     */   private final PDFTemplateStructure pdfStructure;
/*  55 */   private static final Log LOG = LogFactory.getLog(PDVisibleSigBuilder.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSigBuilder() {
/*  62 */     this.pdfStructure = new PDFTemplateStructure();
/*  63 */     LOG.info("PDF Structure has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createPage(PDVisibleSignDesigner properties) {
/*  70 */     PDPage page = new PDPage(new PDRectangle(properties.getPageWidth(), properties.getPageHeight()));
/*  71 */     this.pdfStructure.setPage(page);
/*  72 */     LOG.info("PDF page has been created");
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
/*     */   public void createTemplate(PDPage page) throws IOException {
/*  85 */     PDDocument template = new PDDocument();
/*  86 */     template.addPage(page);
/*  87 */     this.pdfStructure.setTemplate(template);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createAcroForm(PDDocument template) {
/*  93 */     PDAcroForm theAcroForm = new PDAcroForm(template);
/*  94 */     template.getDocumentCatalog().setAcroForm(theAcroForm);
/*  95 */     this.pdfStructure.setAcroForm(theAcroForm);
/*  96 */     LOG.info("AcroForm has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFTemplateStructure getStructure() {
/* 102 */     return this.pdfStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createSignatureField(PDAcroForm acroForm) throws IOException {
/* 108 */     PDSignatureField sf = new PDSignatureField(acroForm);
/* 109 */     this.pdfStructure.setSignatureField(sf);
/* 110 */     LOG.info("Signature field has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createSignature(PDSignatureField pdSignatureField, PDPage page, String signerName) throws IOException {
/* 117 */     PDSignature pdSignature = new PDSignature();
/* 118 */     PDAnnotationWidget widget = pdSignatureField.getWidgets().get(0);
/* 119 */     pdSignatureField.setValue(pdSignature);
/* 120 */     widget.setPage(page);
/* 121 */     page.getAnnotations().add(widget);
/* 122 */     if (!signerName.isEmpty())
/*     */     {
/* 124 */       pdSignature.setName(signerName);
/*     */     }
/* 126 */     this.pdfStructure.setPdSignature(pdSignature);
/* 127 */     LOG.info("PDSignature has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createAcroFormDictionary(PDAcroForm acroForm, PDSignatureField signatureField) throws IOException {
/* 135 */     List<PDField> acroFormFields = acroForm.getFields();
/* 136 */     COSDictionary acroFormDict = acroForm.getCOSObject();
/* 137 */     acroForm.setSignaturesExist(true);
/* 138 */     acroForm.setAppendOnly(true);
/* 139 */     acroFormDict.setDirect(true);
/* 140 */     acroFormFields.add(signatureField);
/* 141 */     acroForm.setDefaultAppearance("/sylfaen 0 Tf 0 g");
/* 142 */     this.pdfStructure.setAcroFormFields(acroFormFields);
/* 143 */     this.pdfStructure.setAcroFormDictionary(acroFormDict);
/* 144 */     LOG.info("AcroForm dictionary has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createSignatureRectangle(PDSignatureField signatureField, PDVisibleSignDesigner properties) throws IOException {
/* 152 */     PDRectangle rect = new PDRectangle();
/* 153 */     rect.setUpperRightX(properties.getxAxis() + properties.getWidth());
/* 154 */     rect.setUpperRightY(properties.getTemplateHeight() - properties.getyAxis());
/* 155 */     rect.setLowerLeftY(properties.getTemplateHeight() - properties.getyAxis() - properties
/* 156 */         .getHeight());
/* 157 */     rect.setLowerLeftX(properties.getxAxis());
/* 158 */     ((PDAnnotationWidget)signatureField.getWidgets().get(0)).setRectangle(rect);
/* 159 */     this.pdfStructure.setSignatureRectangle(rect);
/* 160 */     LOG.info("Signature rectangle has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void createAffineTransform(byte[] params) {
/* 172 */     AffineTransform transform = new AffineTransform(params[0], params[1], params[2], params[3], params[4], params[5]);
/*     */     
/* 174 */     this.pdfStructure.setAffineTransform(transform);
/* 175 */     LOG.info("Matrix has been added");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createAffineTransform(AffineTransform affineTransform) {
/* 181 */     this.pdfStructure.setAffineTransform(affineTransform);
/* 182 */     LOG.info("Matrix has been added");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createProcSetArray() {
/* 188 */     COSArray procSetArr = new COSArray();
/* 189 */     procSetArr.add((COSBase)COSName.getPDFName("PDF"));
/* 190 */     procSetArr.add((COSBase)COSName.getPDFName("Text"));
/* 191 */     procSetArr.add((COSBase)COSName.getPDFName("ImageB"));
/* 192 */     procSetArr.add((COSBase)COSName.getPDFName("ImageC"));
/* 193 */     procSetArr.add((COSBase)COSName.getPDFName("ImageI"));
/* 194 */     this.pdfStructure.setProcSet(procSetArr);
/* 195 */     LOG.info("ProcSet array has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createSignatureImage(PDDocument template, BufferedImage image) throws IOException {
/* 201 */     this.pdfStructure.setImage(LosslessFactory.createFromImage(template, image));
/* 202 */     LOG.info("Visible Signature Image has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void createFormatterRectangle(byte[] params) {
/* 214 */     PDRectangle formatterRectangle = new PDRectangle();
/* 215 */     formatterRectangle.setLowerLeftX(Math.min(params[0], params[2]));
/* 216 */     formatterRectangle.setLowerLeftY(Math.min(params[1], params[3]));
/* 217 */     formatterRectangle.setUpperRightX(Math.max(params[0], params[2]));
/* 218 */     formatterRectangle.setUpperRightY(Math.max(params[1], params[3]));
/*     */     
/* 220 */     this.pdfStructure.setFormatterRectangle(formatterRectangle);
/* 221 */     LOG.info("Formatter rectangle has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createFormatterRectangle(int[] params) {
/* 227 */     PDRectangle formatterRectangle = new PDRectangle();
/* 228 */     formatterRectangle.setLowerLeftX(Math.min(params[0], params[2]));
/* 229 */     formatterRectangle.setLowerLeftY(Math.min(params[1], params[3]));
/* 230 */     formatterRectangle.setUpperRightX(Math.max(params[0], params[2]));
/* 231 */     formatterRectangle.setUpperRightY(Math.max(params[1], params[3]));
/*     */     
/* 233 */     this.pdfStructure.setFormatterRectangle(formatterRectangle);
/* 234 */     LOG.info("Formatter rectangle has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createHolderFormStream(PDDocument template) {
/* 240 */     PDStream holderForm = new PDStream(template);
/* 241 */     this.pdfStructure.setHolderFormStream(holderForm);
/* 242 */     LOG.info("Holder form stream has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createHolderFormResources() {
/* 248 */     PDResources holderFormResources = new PDResources();
/* 249 */     this.pdfStructure.setHolderFormResources(holderFormResources);
/* 250 */     LOG.info("Holder form resources have been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createHolderForm(PDResources holderFormResources, PDStream holderFormStream, PDRectangle bbox) {
/* 258 */     PDFormXObject holderForm = new PDFormXObject(holderFormStream);
/* 259 */     holderForm.setResources(holderFormResources);
/* 260 */     holderForm.setBBox(bbox);
/* 261 */     holderForm.setFormType(1);
/* 262 */     this.pdfStructure.setHolderForm(holderForm);
/* 263 */     LOG.info("Holder form has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createAppearanceDictionary(PDFormXObject holderForml, PDSignatureField signatureField) throws IOException {
/* 271 */     PDAppearanceDictionary appearance = new PDAppearanceDictionary();
/* 272 */     appearance.getCOSObject().setDirect(true);
/*     */     
/* 274 */     PDAppearanceStream appearanceStream = new PDAppearanceStream(holderForml.getCOSObject());
/*     */     
/* 276 */     appearance.setNormalAppearance(appearanceStream);
/* 277 */     ((PDAnnotationWidget)signatureField.getWidgets().get(0)).setAppearance(appearance);
/*     */     
/* 279 */     this.pdfStructure.setAppearanceDictionary(appearance);
/* 280 */     LOG.info("PDF appearance dictionary has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInnerFormStream(PDDocument template) {
/* 286 */     PDStream innerFormStream = new PDStream(template);
/* 287 */     this.pdfStructure.setInnterFormStream(innerFormStream);
/* 288 */     LOG.info("Stream of another form (inner form - it will be inside holder form) has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInnerFormResource() {
/* 295 */     PDResources innerFormResources = new PDResources();
/* 296 */     this.pdfStructure.setInnerFormResources(innerFormResources);
/* 297 */     LOG.info("Resources of another form (inner form - it will be inside holder form)have been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInnerForm(PDResources innerFormResources, PDStream innerFormStream, PDRectangle bbox) {
/* 306 */     PDFormXObject innerForm = new PDFormXObject(innerFormStream);
/* 307 */     innerForm.setResources(innerFormResources);
/* 308 */     innerForm.setBBox(bbox);
/* 309 */     innerForm.setFormType(1);
/* 310 */     this.pdfStructure.setInnerForm(innerForm);
/* 311 */     LOG.info("Another form (inner form - it will be inside holder form) has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertInnerFormToHolderResources(PDFormXObject innerForm, PDResources holderFormResources) {
/* 318 */     holderFormResources.put(COSName.FRM, (PDXObject)innerForm);
/* 319 */     this.pdfStructure.setInnerFormName(COSName.FRM);
/* 320 */     LOG.info("Now inserted inner form inside holder form");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createImageFormStream(PDDocument template) {
/* 326 */     PDStream imageFormStream = new PDStream(template);
/* 327 */     this.pdfStructure.setImageFormStream(imageFormStream);
/* 328 */     LOG.info("Created image form stream");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createImageFormResources() {
/* 334 */     PDResources imageFormResources = new PDResources();
/* 335 */     this.pdfStructure.setImageFormResources(imageFormResources);
/* 336 */     LOG.info("Created image form resources");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createImageForm(PDResources imageFormResources, PDResources innerFormResource, PDStream imageFormStream, PDRectangle bbox, AffineTransform at, PDImageXObject img) throws IOException {
/* 344 */     PDFormXObject imageForm = new PDFormXObject(imageFormStream);
/* 345 */     imageForm.setBBox(bbox);
/* 346 */     imageForm.setMatrix(at);
/* 347 */     imageForm.setResources(imageFormResources);
/* 348 */     imageForm.setFormType(1);
/*     */     
/* 350 */     imageFormResources.getCOSObject().setDirect(true);
/*     */     
/* 352 */     COSName imageFormName = COSName.getPDFName("n2");
/* 353 */     innerFormResource.put(imageFormName, (PDXObject)imageForm);
/* 354 */     COSName imageName = imageFormResources.add((PDXObject)img, "img");
/* 355 */     this.pdfStructure.setImageForm(imageForm);
/* 356 */     this.pdfStructure.setImageFormName(imageFormName);
/* 357 */     this.pdfStructure.setImageName(imageName);
/* 358 */     LOG.info("Created image form");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createBackgroundLayerForm(PDResources innerFormResource, PDRectangle bbox) throws IOException {
/* 366 */     PDFormXObject n0Form = new PDFormXObject(this.pdfStructure.getTemplate().getDocument().createCOSStream());
/* 367 */     n0Form.setBBox(bbox);
/* 368 */     n0Form.setResources(new PDResources());
/* 369 */     n0Form.setFormType(1);
/* 370 */     innerFormResource.put(COSName.getPDFName("n0"), (PDXObject)n0Form);
/* 371 */     LOG.info("Created background layer form");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void injectProcSetArray(PDFormXObject innerForm, PDPage page, PDResources innerFormResources, PDResources imageFormResources, PDResources holderFormResources, COSArray procSet) {
/* 379 */     innerForm.getResources().getCOSObject().setItem(COSName.PROC_SET, (COSBase)procSet);
/* 380 */     page.getCOSObject().setItem(COSName.PROC_SET, (COSBase)procSet);
/* 381 */     innerFormResources.getCOSObject().setItem(COSName.PROC_SET, (COSBase)procSet);
/* 382 */     imageFormResources.getCOSObject().setItem(COSName.PROC_SET, (COSBase)procSet);
/* 383 */     holderFormResources.getCOSObject().setItem(COSName.PROC_SET, (COSBase)procSet);
/* 384 */     LOG.info("Inserted ProcSet to PDF");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void injectAppearanceStreams(PDStream holderFormStream, PDStream innerFormStream, PDStream imageFormStream, COSName imageFormName, COSName imageName, COSName innerFormName, PDVisibleSignDesigner properties) throws IOException {
/* 394 */     int width = (int)getStructure().getFormatterRectangle().getWidth();
/* 395 */     int height = (int)getStructure().getFormatterRectangle().getHeight();
/*     */     
/* 397 */     String imgFormContent = "q " + width + " 0 0 " + height + " 0 0 cm /" + imageName.getName() + " Do Q\n";
/* 398 */     String holderFormContent = "q 1 0 0 1 0 0 cm /" + innerFormName.getName() + " Do Q\n";
/* 399 */     String innerFormContent = "q 1 0 0 1 0 0 cm /n0 Do Q q 1 0 0 1 0 0 cm /" + imageFormName.getName() + " Do Q\n";
/*     */     
/* 401 */     appendRawCommands(this.pdfStructure.getHolderFormStream().createOutputStream(), holderFormContent);
/* 402 */     appendRawCommands(this.pdfStructure.getInnerFormStream().createOutputStream(), innerFormContent);
/* 403 */     appendRawCommands(this.pdfStructure.getImageFormStream().createOutputStream(), imgFormContent);
/* 404 */     LOG.info("Injected appearance stream to pdf");
/*     */   }
/*     */ 
/*     */   
/*     */   public void appendRawCommands(OutputStream os, String commands) throws IOException {
/* 409 */     os.write(commands.getBytes("UTF-8"));
/* 410 */     os.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createVisualSignature(PDDocument template) {
/* 416 */     this.pdfStructure.setVisualSignature(template.getDocument());
/* 417 */     LOG.info("Visible signature has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createWidgetDictionary(PDSignatureField signatureField, PDResources holderFormResources) throws IOException {
/* 424 */     COSDictionary widgetDict = ((PDAnnotationWidget)signatureField.getWidgets().get(0)).getCOSObject();
/* 425 */     widgetDict.setNeedToBeUpdated(true);
/* 426 */     widgetDict.setItem(COSName.DR, (COSBase)holderFormResources.getCOSObject());
/*     */     
/* 428 */     this.pdfStructure.setWidgetDictionary(widgetDict);
/* 429 */     LOG.info("WidgetDictionary has been created");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeTemplate(PDDocument template) throws IOException {
/* 435 */     template.close();
/* 436 */     this.pdfStructure.getTemplate().close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/visible/PDVisibleSigBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */