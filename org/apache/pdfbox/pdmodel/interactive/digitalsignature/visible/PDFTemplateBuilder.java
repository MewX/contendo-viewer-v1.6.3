package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;

public interface PDFTemplateBuilder {
  @Deprecated
  void createAffineTransform(byte[] paramArrayOfbyte);
  
  void createAffineTransform(AffineTransform paramAffineTransform);
  
  void createPage(PDVisibleSignDesigner paramPDVisibleSignDesigner);
  
  void createTemplate(PDPage paramPDPage) throws IOException;
  
  void createAcroForm(PDDocument paramPDDocument);
  
  void createSignatureField(PDAcroForm paramPDAcroForm) throws IOException;
  
  void createSignature(PDSignatureField paramPDSignatureField, PDPage paramPDPage, String paramString) throws IOException;
  
  void createAcroFormDictionary(PDAcroForm paramPDAcroForm, PDSignatureField paramPDSignatureField) throws IOException;
  
  void createSignatureRectangle(PDSignatureField paramPDSignatureField, PDVisibleSignDesigner paramPDVisibleSignDesigner) throws IOException;
  
  void createProcSetArray();
  
  void createSignatureImage(PDDocument paramPDDocument, BufferedImage paramBufferedImage) throws IOException;
  
  @Deprecated
  void createFormatterRectangle(byte[] paramArrayOfbyte);
  
  void createFormatterRectangle(int[] paramArrayOfint);
  
  void createHolderFormStream(PDDocument paramPDDocument);
  
  void createHolderFormResources();
  
  void createHolderForm(PDResources paramPDResources, PDStream paramPDStream, PDRectangle paramPDRectangle);
  
  void createAppearanceDictionary(PDFormXObject paramPDFormXObject, PDSignatureField paramPDSignatureField) throws IOException;
  
  void createInnerFormStream(PDDocument paramPDDocument);
  
  void createInnerFormResource();
  
  void createInnerForm(PDResources paramPDResources, PDStream paramPDStream, PDRectangle paramPDRectangle);
  
  void insertInnerFormToHolderResources(PDFormXObject paramPDFormXObject, PDResources paramPDResources);
  
  void createImageFormStream(PDDocument paramPDDocument);
  
  void createImageFormResources();
  
  void createImageForm(PDResources paramPDResources1, PDResources paramPDResources2, PDStream paramPDStream, PDRectangle paramPDRectangle, AffineTransform paramAffineTransform, PDImageXObject paramPDImageXObject) throws IOException;
  
  void createBackgroundLayerForm(PDResources paramPDResources, PDRectangle paramPDRectangle) throws IOException;
  
  void injectProcSetArray(PDFormXObject paramPDFormXObject, PDPage paramPDPage, PDResources paramPDResources1, PDResources paramPDResources2, PDResources paramPDResources3, COSArray paramCOSArray);
  
  void injectAppearanceStreams(PDStream paramPDStream1, PDStream paramPDStream2, PDStream paramPDStream3, COSName paramCOSName1, COSName paramCOSName2, COSName paramCOSName3, PDVisibleSignDesigner paramPDVisibleSignDesigner) throws IOException;
  
  void createVisualSignature(PDDocument paramPDDocument);
  
  void createWidgetDictionary(PDSignatureField paramPDSignatureField, PDResources paramPDResources) throws IOException;
  
  PDFTemplateStructure getStructure();
  
  void closeTemplate(PDDocument paramPDDocument) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/visible/PDFTemplateBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */