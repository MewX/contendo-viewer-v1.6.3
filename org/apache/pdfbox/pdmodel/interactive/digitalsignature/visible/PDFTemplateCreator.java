/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdfwriter.COSWriter;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
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
/*     */ public class PDFTemplateCreator
/*     */ {
/*     */   private final PDFTemplateBuilder pdfBuilder;
/*  47 */   private static final Log LOG = LogFactory.getLog(PDFTemplateCreator.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFTemplateCreator(PDFTemplateBuilder templateBuilder) {
/*  56 */     this.pdfBuilder = templateBuilder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFTemplateStructure getPdfStructure() {
/*  66 */     return this.pdfBuilder.getStructure();
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
/*     */   public InputStream buildPDF(PDVisibleSignDesigner properties) throws IOException {
/*  78 */     LOG.info("pdf building has been started");
/*  79 */     PDFTemplateStructure pdfStructure = this.pdfBuilder.getStructure();
/*     */ 
/*     */     
/*  82 */     this.pdfBuilder.createProcSetArray();
/*     */ 
/*     */     
/*  85 */     this.pdfBuilder.createPage(properties);
/*  86 */     PDPage page = pdfStructure.getPage();
/*     */ 
/*     */     
/*  89 */     this.pdfBuilder.createTemplate(page);
/*  90 */     PDDocument template = pdfStructure.getTemplate();
/*     */ 
/*     */     
/*  93 */     this.pdfBuilder.createAcroForm(template);
/*  94 */     PDAcroForm acroForm = pdfStructure.getAcroForm();
/*     */ 
/*     */     
/*  97 */     this.pdfBuilder.createSignatureField(acroForm);
/*  98 */     PDSignatureField pdSignatureField = pdfStructure.getSignatureField();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     this.pdfBuilder.createSignature(pdSignatureField, page, "");
/*     */ 
/*     */     
/* 108 */     this.pdfBuilder.createAcroFormDictionary(acroForm, pdSignatureField);
/*     */ 
/*     */     
/* 111 */     this.pdfBuilder.createAffineTransform(properties.getTransform());
/* 112 */     AffineTransform transform = pdfStructure.getAffineTransform();
/*     */ 
/*     */     
/* 115 */     this.pdfBuilder.createSignatureRectangle(pdSignatureField, properties);
/* 116 */     this.pdfBuilder.createFormatterRectangle(properties.getFormatterRectangleParameters());
/* 117 */     PDRectangle bbox = pdfStructure.getFormatterRectangle();
/* 118 */     this.pdfBuilder.createSignatureImage(template, properties.getImage());
/*     */ 
/*     */     
/* 121 */     this.pdfBuilder.createHolderFormStream(template);
/* 122 */     PDStream holderFormStream = pdfStructure.getHolderFormStream();
/* 123 */     this.pdfBuilder.createHolderFormResources();
/* 124 */     PDResources holderFormResources = pdfStructure.getHolderFormResources();
/* 125 */     this.pdfBuilder.createHolderForm(holderFormResources, holderFormStream, bbox);
/*     */ 
/*     */     
/* 128 */     this.pdfBuilder.createAppearanceDictionary(pdfStructure.getHolderForm(), pdSignatureField);
/*     */ 
/*     */     
/* 131 */     this.pdfBuilder.createInnerFormStream(template);
/* 132 */     this.pdfBuilder.createInnerFormResource();
/* 133 */     PDResources innerFormResource = pdfStructure.getInnerFormResources();
/* 134 */     this.pdfBuilder.createInnerForm(innerFormResource, pdfStructure.getInnerFormStream(), bbox);
/* 135 */     PDFormXObject innerForm = pdfStructure.getInnerForm();
/*     */ 
/*     */     
/* 138 */     this.pdfBuilder.insertInnerFormToHolderResources(innerForm, holderFormResources);
/*     */ 
/*     */     
/* 141 */     this.pdfBuilder.createImageFormStream(template);
/* 142 */     PDStream imageFormStream = pdfStructure.getImageFormStream();
/* 143 */     this.pdfBuilder.createImageFormResources();
/* 144 */     PDResources imageFormResources = pdfStructure.getImageFormResources();
/* 145 */     this.pdfBuilder.createImageForm(imageFormResources, innerFormResource, imageFormStream, bbox, transform, pdfStructure
/* 146 */         .getImage());
/*     */     
/* 148 */     this.pdfBuilder.createBackgroundLayerForm(innerFormResource, bbox);
/*     */ 
/*     */     
/* 151 */     this.pdfBuilder.injectProcSetArray(innerForm, page, innerFormResource, imageFormResources, holderFormResources, pdfStructure
/* 152 */         .getProcSet());
/*     */     
/* 154 */     COSName imageFormName = pdfStructure.getImageFormName();
/* 155 */     COSName imageName = pdfStructure.getImageName();
/* 156 */     COSName innerFormName = pdfStructure.getInnerFormName();
/*     */ 
/*     */     
/* 159 */     this.pdfBuilder.injectAppearanceStreams(holderFormStream, imageFormStream, imageFormStream, imageFormName, imageName, innerFormName, properties);
/*     */     
/* 161 */     this.pdfBuilder.createVisualSignature(template);
/* 162 */     this.pdfBuilder.createWidgetDictionary(pdSignatureField, holderFormResources);
/*     */     
/* 164 */     InputStream in = getVisualSignatureAsStream(pdfStructure.getVisualSignature());
/* 165 */     LOG.info("stream returning started, size= " + in.available());
/*     */ 
/*     */     
/* 168 */     template.close();
/*     */ 
/*     */     
/* 171 */     return in;
/*     */   }
/*     */ 
/*     */   
/*     */   private InputStream getVisualSignatureAsStream(COSDocument visualSignature) throws IOException {
/* 176 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 177 */     COSWriter writer = new COSWriter(baos);
/* 178 */     writer.write(visualSignature);
/* 179 */     writer.close();
/* 180 */     return new ByteArrayInputStream(baos.toByteArray());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/visible/PDFTemplateCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */