/*    */ package org.apache.fontbox.ttf;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SubstitutingCmapLookup
/*    */   implements CmapLookup
/*    */ {
/*    */   private final CmapSubtable cmap;
/*    */   private final GlyphSubstitutionTable gsub;
/*    */   private final List<String> enabledFeatures;
/*    */   
/*    */   public SubstitutingCmapLookup(CmapSubtable cmap, GlyphSubstitutionTable gsub, List<String> enabledFeatures) {
/* 35 */     this.cmap = cmap;
/* 36 */     this.gsub = gsub;
/* 37 */     this.enabledFeatures = enabledFeatures;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getGlyphId(int characterCode) {
/* 43 */     int gid = this.cmap.getGlyphId(characterCode);
/* 44 */     String[] scriptTags = OpenTypeScript.getScriptTags(characterCode);
/* 45 */     return this.gsub.getSubstitution(gid, scriptTags, this.enabledFeatures);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Integer> getCharCodes(int gid) {
/* 51 */     return this.cmap.getCharCodes(this.gsub.getUnsubstitution(gid));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/SubstitutingCmapLookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */