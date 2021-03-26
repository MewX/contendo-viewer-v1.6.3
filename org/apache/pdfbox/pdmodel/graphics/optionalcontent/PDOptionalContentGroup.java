/*     */ package org.apache.pdfbox.pdmodel.graphics.optionalcontent;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*     */ import org.apache.pdfbox.rendering.RenderDestination;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDOptionalContentGroup
/*     */   extends PDPropertyList
/*     */ {
/*     */   public PDOptionalContentGroup(String name) {
/*  35 */     this.dict.setItem(COSName.TYPE, (COSBase)COSName.OCG);
/*  36 */     setName(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOptionalContentGroup(COSDictionary dict) {
/*  45 */     super(dict);
/*  46 */     if (!dict.getItem(COSName.TYPE).equals(COSName.OCG))
/*     */     {
/*  48 */       throw new IllegalArgumentException("Provided dictionary is not of type '" + COSName.OCG + "'");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum RenderState
/*     */   {
/*  60 */     ON((String)COSName.ON),
/*     */     
/*  62 */     OFF((String)COSName.OFF);
/*     */     
/*     */     private final COSName name;
/*     */ 
/*     */     
/*     */     RenderState(COSName value) {
/*  68 */       this.name = value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public COSName getName() {
/*  94 */       return this.name;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 104 */     return this.dict.getString(COSName.NAME);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 113 */     this.dict.setString(COSName.NAME, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderState getRenderState(RenderDestination destination) {
/* 123 */     COSName state = null;
/* 124 */     COSDictionary usage = (COSDictionary)this.dict.getDictionaryObject("Usage");
/* 125 */     if (usage != null) {
/*     */       
/* 127 */       if (RenderDestination.PRINT.equals(destination)) {
/*     */         
/* 129 */         COSDictionary print = (COSDictionary)usage.getDictionaryObject("Print");
/* 130 */         state = (print == null) ? null : (COSName)print.getDictionaryObject("PrintState");
/*     */       }
/* 132 */       else if (RenderDestination.VIEW.equals(destination)) {
/*     */         
/* 134 */         COSDictionary view = (COSDictionary)usage.getDictionaryObject("View");
/* 135 */         state = (view == null) ? null : (COSName)view.getDictionaryObject("ViewState");
/*     */       } 
/*     */       
/* 138 */       if (state == null) {
/*     */         
/* 140 */         COSDictionary export = (COSDictionary)usage.getDictionaryObject("Export");
/* 141 */         state = (export == null) ? null : (COSName)export.getDictionaryObject("ExportState");
/*     */       } 
/*     */     } 
/* 144 */     return (state == null) ? null : RenderState.valueOf(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 150 */     return super.toString() + " (" + getName() + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/optionalcontent/PDOptionalContentGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */