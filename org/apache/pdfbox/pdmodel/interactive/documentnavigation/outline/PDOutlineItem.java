/*     */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDOutlineItem
/*     */   extends PDOutlineNode
/*     */ {
/*     */   private static final int ITALIC_FLAG = 1;
/*     */   private static final int BOLD_FLAG = 2;
/*     */   
/*     */   public PDOutlineItem() {}
/*     */   
/*     */   public PDOutlineItem(COSDictionary dic) {
/*  65 */     super(dic);
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
/*     */   public void insertSiblingAfter(PDOutlineItem newSibling) {
/*  77 */     requireSingleNode(newSibling);
/*  78 */     PDOutlineNode parent = getParent();
/*  79 */     newSibling.setParent(parent);
/*  80 */     PDOutlineItem next = getNextSibling();
/*  81 */     setNextSibling(newSibling);
/*  82 */     newSibling.setPreviousSibling(this);
/*  83 */     if (next != null) {
/*     */       
/*  85 */       newSibling.setNextSibling(next);
/*  86 */       next.setPreviousSibling(newSibling);
/*     */     }
/*  88 */     else if (parent != null) {
/*     */       
/*  90 */       getParent().setLastChild(newSibling);
/*     */     } 
/*  92 */     updateParentOpenCountForAddedChild(newSibling);
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
/*     */   public void insertSiblingBefore(PDOutlineItem newSibling) {
/* 104 */     requireSingleNode(newSibling);
/* 105 */     PDOutlineNode parent = getParent();
/* 106 */     newSibling.setParent(parent);
/* 107 */     PDOutlineItem previous = getPreviousSibling();
/* 108 */     setPreviousSibling(newSibling);
/* 109 */     newSibling.setNextSibling(this);
/* 110 */     if (previous != null) {
/*     */       
/* 112 */       previous.setNextSibling(newSibling);
/* 113 */       newSibling.setPreviousSibling(previous);
/*     */     }
/* 115 */     else if (parent != null) {
/*     */       
/* 117 */       getParent().setFirstChild(newSibling);
/*     */     } 
/* 119 */     updateParentOpenCountForAddedChild(newSibling);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOutlineItem getPreviousSibling() {
/* 129 */     return getOutlineItem(COSName.PREV);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setPreviousSibling(PDOutlineNode outlineNode) {
/* 139 */     getCOSObject().setItem(COSName.PREV, (COSObjectable)outlineNode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOutlineItem getNextSibling() {
/* 147 */     return getOutlineItem(COSName.NEXT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNextSibling(PDOutlineNode outlineNode) {
/* 157 */     getCOSObject().setItem(COSName.NEXT, (COSObjectable)outlineNode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 167 */     return getCOSObject().getString(COSName.TITLE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 177 */     getCOSObject().setString(COSName.TITLE, title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDestination getDestination() throws IOException {
/* 188 */     return PDDestination.create(getCOSObject().getDictionaryObject(COSName.DEST));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestination(PDDestination dest) {
/* 198 */     getCOSObject().setItem(COSName.DEST, (COSObjectable)dest);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestination(PDPage page) {
/* 208 */     PDPageXYZDestination dest = null;
/* 209 */     if (page != null) {
/*     */       
/* 211 */       dest = new PDPageXYZDestination();
/* 212 */       dest.setPage(page);
/*     */     } 
/* 214 */     setDestination((PDDestination)dest);
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
/*     */   public PDPage findDestinationPage(PDDocument doc) throws IOException {
/* 230 */     PDDestination dest = getDestination();
/* 231 */     if (dest == null) {
/*     */       
/* 233 */       PDAction outlineAction = getAction();
/* 234 */       if (outlineAction instanceof PDActionGoTo)
/*     */       {
/* 236 */         dest = ((PDActionGoTo)outlineAction).getDestination();
/*     */       }
/*     */     } 
/* 239 */     if (dest == null)
/*     */     {
/* 241 */       return null;
/*     */     }
/*     */     
/* 244 */     PDPageDestination pageDestination = null;
/* 245 */     if (dest instanceof PDNamedDestination) {
/*     */       
/* 247 */       pageDestination = doc.getDocumentCatalog().findNamedDestinationPage((PDNamedDestination)dest);
/* 248 */       if (pageDestination == null)
/*     */       {
/* 250 */         return null;
/*     */       }
/*     */     }
/* 253 */     else if (dest instanceof PDPageDestination) {
/*     */       
/* 255 */       pageDestination = (PDPageDestination)dest;
/*     */     }
/*     */     else {
/*     */       
/* 259 */       throw new IOException("Error: Unknown destination type " + dest);
/*     */     } 
/*     */     
/* 262 */     PDPage page = pageDestination.getPage();
/* 263 */     if (page == null) {
/*     */ 
/*     */ 
/*     */       
/* 267 */       int pageNumber = pageDestination.getPageNumber();
/* 268 */       if (pageNumber != -1)
/*     */       {
/* 270 */         page = doc.getPage(pageNumber);
/*     */       }
/*     */     } 
/* 273 */     return page;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getAction() {
/* 283 */     return PDActionFactory.createAction((COSDictionary)getCOSObject().getDictionaryObject(COSName.A));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(PDAction action) {
/* 293 */     getCOSObject().setItem(COSName.A, (COSObjectable)action);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStructureElement getStructureElement() {
/* 303 */     PDStructureElement se = null;
/* 304 */     COSDictionary dic = (COSDictionary)getCOSObject().getDictionaryObject(COSName.SE);
/* 305 */     if (dic != null)
/*     */     {
/* 307 */       se = new PDStructureElement(dic);
/*     */     }
/* 309 */     return se;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStructureElement(PDStructureElement structureElement) {
/* 319 */     getCOSObject().setItem(COSName.SE, (COSObjectable)structureElement);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getTextColor() {
/* 330 */     COSArray csValues = (COSArray)getCOSObject().getDictionaryObject(COSName.C);
/* 331 */     if (csValues == null) {
/*     */       
/* 333 */       csValues = new COSArray();
/* 334 */       csValues.growToSize(3, (COSBase)new COSFloat(0.0F));
/* 335 */       getCOSObject().setItem(COSName.C, (COSBase)csValues);
/*     */     } 
/* 337 */     return new PDColor(csValues, (PDColorSpace)PDDeviceRGB.INSTANCE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextColor(PDColor textColor) {
/* 347 */     getCOSObject().setItem(COSName.C, (COSBase)textColor.toCOSArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextColor(Color textColor) {
/* 357 */     COSArray array = new COSArray();
/* 358 */     array.add((COSBase)new COSFloat(textColor.getRed() / 255.0F));
/* 359 */     array.add((COSBase)new COSFloat(textColor.getGreen() / 255.0F));
/* 360 */     array.add((COSBase)new COSFloat(textColor.getBlue() / 255.0F));
/* 361 */     getCOSObject().setItem(COSName.C, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItalic() {
/* 371 */     return getCOSObject().getFlag(COSName.F, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItalic(boolean italic) {
/* 381 */     getCOSObject().setFlag(COSName.F, 1, italic);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBold() {
/* 391 */     return getCOSObject().getFlag(COSName.F, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBold(boolean bold) {
/* 401 */     getCOSObject().setFlag(COSName.F, 2, bold);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/outline/PDOutlineItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */