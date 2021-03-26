/*    */ package org.apache.pdfbox.pdmodel.font;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.fontbox.cmap.CMap;
/*    */ import org.apache.fontbox.cmap.CMapParser;
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
/*    */ final class CMapManager
/*    */ {
/* 34 */   static Map<String, CMap> cMapCache = Collections.synchronizedMap(new HashMap<String, CMap>());
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
/*    */   public static CMap getPredefinedCMap(String cMapName) throws IOException {
/* 49 */     CMap cmap = cMapCache.get(cMapName);
/* 50 */     if (cmap != null)
/*    */     {
/* 52 */       return cmap;
/*    */     }
/*    */     
/* 55 */     CMapParser parser = new CMapParser();
/* 56 */     CMap targetCmap = parser.parsePredefined(cMapName);
/*    */ 
/*    */     
/* 59 */     cMapCache.put(targetCmap.getName(), targetCmap);
/* 60 */     return targetCmap;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static CMap parseCMap(InputStream cMapStream) throws IOException {
/* 71 */     CMap targetCmap = null;
/* 72 */     if (cMapStream != null) {
/*    */       
/* 74 */       CMapParser parser = new CMapParser();
/* 75 */       targetCmap = parser.parse(cMapStream);
/*    */     } 
/* 77 */     return targetCmap;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/CMapManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */