/*    */ package org.apache.fontbox.ttf;
/*    */ 
/*    */ import java.awt.geom.GeneralPath;
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OpenTypeFont
/*    */   extends TrueTypeFont
/*    */ {
/*    */   private boolean isPostScript;
/*    */   
/*    */   OpenTypeFont(TTFDataStream fontData) {
/* 37 */     super(fontData);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void setVersion(float versionValue) {
/* 43 */     this.isPostScript = (Float.floatToIntBits(versionValue) == 1184802985);
/* 44 */     super.setVersion(versionValue);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CFFTable getCFF() throws IOException {
/* 54 */     if (!this.isPostScript)
/*    */     {
/* 56 */       throw new UnsupportedOperationException("TTF fonts do not have a CFF table");
/*    */     }
/* 58 */     return (CFFTable)getTable("CFF ");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public GlyphTable getGlyph() throws IOException {
/* 64 */     if (this.isPostScript)
/*    */     {
/* 66 */       throw new UnsupportedOperationException("OTF fonts do not have a glyf table");
/*    */     }
/* 68 */     return super.getGlyph();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public GeneralPath getPath(String name) throws IOException {
/* 74 */     int gid = nameToGID(name);
/* 75 */     return getCFF().getFont().getType2CharString(gid).getPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isPostScript() {
/* 83 */     return this.tables.containsKey("CFF ");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasLayoutTables() {
/* 91 */     return (this.tables.containsKey("BASE") || this.tables
/* 92 */       .containsKey("GDEF") || this.tables
/* 93 */       .containsKey("GPOS") || this.tables
/* 94 */       .containsKey("GSUB") || this.tables
/* 95 */       .containsKey("JSTF"));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/OpenTypeFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */