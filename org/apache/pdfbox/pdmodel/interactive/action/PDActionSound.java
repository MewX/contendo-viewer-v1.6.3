/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSStream;
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
/*     */ public class PDActionSound
/*     */   extends PDAction
/*     */ {
/*     */   public static final String SUB_TYPE = "Sound";
/*     */   
/*     */   public PDActionSound() {
/*  45 */     setSubType("Sound");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDActionSound(COSDictionary a) {
/*  55 */     super(a);
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
/*     */   @Deprecated
/*     */   public String getS() {
/*  68 */     return this.action.getNameAsString(COSName.S);
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
/*     */   @Deprecated
/*     */   public void setS(String s) {
/*  81 */     this.action.setName(COSName.S, s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSound(COSStream sound) {
/*  91 */     this.action.setItem(COSName.SOUND, (COSBase)sound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStream getSound() {
/* 101 */     COSBase base = this.action.getDictionaryObject(COSName.SOUND);
/* 102 */     if (base instanceof COSStream)
/*     */     {
/* 104 */       return (COSStream)base;
/*     */     }
/* 106 */     return null;
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
/*     */   public void setVolume(float volume) {
/* 118 */     if (volume < -1.0F || volume > 1.0F)
/*     */     {
/* 120 */       throw new IllegalArgumentException("volume outside of the range −1.0 to 1.0");
/*     */     }
/* 122 */     this.action.setFloat(COSName.VOLUME, volume);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVolume() {
/* 132 */     COSBase base = this.action.getDictionaryObject(COSName.VOLUME);
/* 133 */     if (base instanceof COSNumber) {
/*     */       
/* 135 */       float volume = ((COSNumber)base).floatValue();
/* 136 */       if (volume < -1.0F || volume > 1.0F)
/*     */       {
/* 138 */         volume = 1.0F;
/*     */       }
/* 140 */       return volume;
/*     */     } 
/* 142 */     return 1.0F;
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
/*     */   public void setSynchronous(boolean synchronous) {
/* 154 */     this.action.setBoolean(COSName.SYNCHRONOUS, synchronous);
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
/*     */   public boolean getSynchronous() {
/* 167 */     COSBase base = this.action.getDictionaryObject(COSName.SYNCHRONOUS);
/* 168 */     if (base instanceof COSBoolean)
/*     */     {
/* 170 */       return ((COSBoolean)base).getValue();
/*     */     }
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRepeat(boolean repeat) {
/* 182 */     this.action.setBoolean(COSName.REPEAT, repeat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getRepeat() {
/* 192 */     COSBase base = this.action.getDictionaryObject(COSName.REPEAT);
/* 193 */     if (base instanceof COSBoolean)
/*     */     {
/* 195 */       return ((COSBoolean)base).getValue();
/*     */     }
/* 197 */     return false;
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
/*     */   public void setMix(boolean mix) {
/* 209 */     this.action.setBoolean(COSName.MIX, mix);
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
/*     */   public boolean getMix() {
/* 221 */     COSBase base = this.action.getDictionaryObject(COSName.MIX);
/* 222 */     if (base instanceof COSBoolean)
/*     */     {
/* 224 */       return ((COSBoolean)base).getValue();
/*     */     }
/* 226 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionSound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */