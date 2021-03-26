/*     */ package org.apache.pdfbox.pdmodel.interactive.measurement;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public class PDRectlinearMeasureDictionary
/*     */   extends PDMeasureDictionary
/*     */ {
/*     */   public static final String SUBTYPE = "RL";
/*     */   
/*     */   public PDRectlinearMeasureDictionary() {
/*  40 */     setSubtype("RL");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectlinearMeasureDictionary(COSDictionary dictionary) {
/*  50 */     super(dictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getScaleRatio() {
/*  60 */     return getCOSObject().getString(COSName.R);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScaleRatio(String scaleRatio) {
/*  70 */     getCOSObject().setString(COSName.R, scaleRatio);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberFormatDictionary[] getChangeXs() {
/*  80 */     COSArray x = (COSArray)getCOSObject().getDictionaryObject("X");
/*  81 */     if (x != null) {
/*     */ 
/*     */       
/*  84 */       PDNumberFormatDictionary[] retval = new PDNumberFormatDictionary[x.size()];
/*  85 */       for (int i = 0; i < x.size(); i++) {
/*     */         
/*  87 */         COSDictionary dic = (COSDictionary)x.get(i);
/*  88 */         retval[i] = new PDNumberFormatDictionary(dic);
/*     */       } 
/*  90 */       return retval;
/*     */     } 
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChangeXs(PDNumberFormatDictionary[] changeXs) {
/* 102 */     COSArray array = new COSArray();
/* 103 */     for (PDNumberFormatDictionary changeX : changeXs)
/*     */     {
/* 105 */       array.add(changeX);
/*     */     }
/* 107 */     getCOSObject().setItem("X", (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberFormatDictionary[] getChangeYs() {
/* 117 */     COSArray y = (COSArray)getCOSObject().getDictionaryObject("Y");
/* 118 */     if (y != null) {
/*     */ 
/*     */       
/* 121 */       PDNumberFormatDictionary[] retval = new PDNumberFormatDictionary[y.size()];
/* 122 */       for (int i = 0; i < y.size(); i++) {
/*     */         
/* 124 */         COSDictionary dic = (COSDictionary)y.get(i);
/* 125 */         retval[i] = new PDNumberFormatDictionary(dic);
/*     */       } 
/* 127 */       return retval;
/*     */     } 
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChangeYs(PDNumberFormatDictionary[] changeYs) {
/* 139 */     COSArray array = new COSArray();
/* 140 */     for (PDNumberFormatDictionary changeY : changeYs)
/*     */     {
/* 142 */       array.add(changeY);
/*     */     }
/* 144 */     getCOSObject().setItem("Y", (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberFormatDictionary[] getDistances() {
/* 154 */     COSArray d = (COSArray)getCOSObject().getDictionaryObject("D");
/* 155 */     if (d != null) {
/*     */ 
/*     */       
/* 158 */       PDNumberFormatDictionary[] retval = new PDNumberFormatDictionary[d.size()];
/* 159 */       for (int i = 0; i < d.size(); i++) {
/*     */         
/* 161 */         COSDictionary dic = (COSDictionary)d.get(i);
/* 162 */         retval[i] = new PDNumberFormatDictionary(dic);
/*     */       } 
/* 164 */       return retval;
/*     */     } 
/* 166 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDistances(PDNumberFormatDictionary[] distances) {
/* 176 */     COSArray array = new COSArray();
/* 177 */     for (PDNumberFormatDictionary distance : distances)
/*     */     {
/* 179 */       array.add(distance);
/*     */     }
/* 181 */     getCOSObject().setItem("D", (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberFormatDictionary[] getAreas() {
/* 191 */     COSArray a = (COSArray)getCOSObject().getDictionaryObject(COSName.A);
/* 192 */     if (a != null) {
/*     */ 
/*     */       
/* 195 */       PDNumberFormatDictionary[] retval = new PDNumberFormatDictionary[a.size()];
/* 196 */       for (int i = 0; i < a.size(); i++) {
/*     */         
/* 198 */         COSDictionary dic = (COSDictionary)a.get(i);
/* 199 */         retval[i] = new PDNumberFormatDictionary(dic);
/*     */       } 
/* 201 */       return retval;
/*     */     } 
/* 203 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAreas(PDNumberFormatDictionary[] areas) {
/* 213 */     COSArray array = new COSArray();
/* 214 */     for (PDNumberFormatDictionary area : areas)
/*     */     {
/* 216 */       array.add(area);
/*     */     }
/* 218 */     getCOSObject().setItem(COSName.A, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberFormatDictionary[] getAngles() {
/* 228 */     COSArray t = (COSArray)getCOSObject().getDictionaryObject("T");
/* 229 */     if (t != null) {
/*     */ 
/*     */       
/* 232 */       PDNumberFormatDictionary[] retval = new PDNumberFormatDictionary[t.size()];
/* 233 */       for (int i = 0; i < t.size(); i++) {
/*     */         
/* 235 */         COSDictionary dic = (COSDictionary)t.get(i);
/* 236 */         retval[i] = new PDNumberFormatDictionary(dic);
/*     */       } 
/* 238 */       return retval;
/*     */     } 
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAngles(PDNumberFormatDictionary[] angles) {
/* 250 */     COSArray array = new COSArray();
/* 251 */     for (PDNumberFormatDictionary angle : angles)
/*     */     {
/* 253 */       array.add(angle);
/*     */     }
/* 255 */     getCOSObject().setItem("T", (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberFormatDictionary[] getLineSloaps() {
/* 265 */     COSArray s = (COSArray)getCOSObject().getDictionaryObject("S");
/* 266 */     if (s != null) {
/*     */ 
/*     */       
/* 269 */       PDNumberFormatDictionary[] retval = new PDNumberFormatDictionary[s.size()];
/* 270 */       for (int i = 0; i < s.size(); i++) {
/*     */         
/* 272 */         COSDictionary dic = (COSDictionary)s.get(i);
/* 273 */         retval[i] = new PDNumberFormatDictionary(dic);
/*     */       } 
/* 275 */       return retval;
/*     */     } 
/* 277 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineSloaps(PDNumberFormatDictionary[] lineSloaps) {
/* 287 */     COSArray array = new COSArray();
/* 288 */     for (PDNumberFormatDictionary lineSloap : lineSloaps)
/*     */     {
/* 290 */       array.add(lineSloap);
/*     */     }
/* 292 */     getCOSObject().setItem("S", (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getCoordSystemOrigin() {
/* 302 */     COSArray o = (COSArray)getCOSObject().getDictionaryObject("O");
/* 303 */     if (o != null)
/*     */     {
/* 305 */       return o.toFloatArray();
/*     */     }
/* 307 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCoordSystemOrigin(float[] coordSystemOrigin) {
/* 317 */     COSArray array = new COSArray();
/* 318 */     array.setFloatArray(coordSystemOrigin);
/* 319 */     getCOSObject().setItem("O", (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCYX() {
/* 329 */     return getCOSObject().getFloat("CYX");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCYX(float cyx) {
/* 339 */     getCOSObject().setFloat("CYX", cyx);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/measurement/PDRectlinearMeasureDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */