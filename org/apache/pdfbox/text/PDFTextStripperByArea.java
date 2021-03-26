/*     */ package org.apache.pdfbox.text;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFTextStripperByArea
/*     */   extends PDFTextStripper
/*     */ {
/*  35 */   private final List<String> regions = new ArrayList<String>();
/*  36 */   private final Map<String, Rectangle2D> regionArea = new HashMap<String, Rectangle2D>();
/*  37 */   private final Map<String, ArrayList<List<TextPosition>>> regionCharacterList = new HashMap<String, ArrayList<List<TextPosition>>>();
/*     */   
/*  39 */   private final Map<String, StringWriter> regionText = new HashMap<String, StringWriter>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFTextStripperByArea() throws IOException {
/*  47 */     super.setShouldSeparateByBeads(false);
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
/*     */   public final void setShouldSeparateByBeads(boolean aShouldSeparateByBeads) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRegion(String regionName, Rectangle2D rect) {
/*  69 */     this.regions.add(regionName);
/*  70 */     this.regionArea.put(regionName, rect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRegion(String regionName) {
/*  80 */     this.regions.remove(regionName);
/*  81 */     this.regionArea.remove(regionName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getRegions() {
/*  91 */     return this.regions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTextForRegion(String regionName) {
/* 102 */     StringWriter text = this.regionText.get(regionName);
/* 103 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void extractRegions(PDPage page) throws IOException {
/* 114 */     for (String region : this.regions) {
/*     */       
/* 116 */       setStartPage(getCurrentPageNo());
/* 117 */       setEndPage(getCurrentPageNo());
/*     */ 
/*     */       
/* 120 */       String regionName = region;
/* 121 */       ArrayList<List<TextPosition>> regionCharactersByArticle = new ArrayList<List<TextPosition>>();
/* 122 */       regionCharactersByArticle.add(new ArrayList<TextPosition>());
/* 123 */       this.regionCharacterList.put(regionName, regionCharactersByArticle);
/* 124 */       this.regionText.put(regionName, new StringWriter());
/*     */     } 
/*     */     
/* 127 */     if (page.hasContents())
/*     */     {
/* 129 */       processPage(page);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processTextPosition(TextPosition text) {
/* 140 */     for (String region : this.regionArea.keySet()) {
/*     */       
/* 142 */       Rectangle2D rect = this.regionArea.get(region);
/* 143 */       if (rect.contains(text.getX(), text.getY())) {
/*     */         
/* 145 */         this.charactersByArticle = this.regionCharacterList.get(region);
/* 146 */         super.processTextPosition(text);
/*     */       } 
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
/*     */   protected void writePage() throws IOException {
/* 160 */     for (String region : this.regionArea.keySet()) {
/*     */       
/* 162 */       this.charactersByArticle = this.regionCharacterList.get(region);
/* 163 */       this.output = this.regionText.get(region);
/* 164 */       super.writePage();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/text/PDFTextStripperByArea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */