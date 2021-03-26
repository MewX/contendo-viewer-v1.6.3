/*     */ package org.apache.xmlgraphics.java2d.color.profile;
/*     */ 
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ColorProfileUtil
/*     */ {
/*     */   public static String getICCProfileDescription(ICC_Profile profile) {
/*  46 */     byte[] data = profile.getData(1684370275);
/*  47 */     if (data == null) {
/*  48 */       return null;
/*     */     }
/*     */     
/*  51 */     int length = data[8] << 24 | data[9] << 16 | data[10] << 8 | data[11];
/*  52 */     length--;
/*     */     try {
/*  54 */       return new String(data, 12, length, "US-ASCII");
/*  55 */     } catch (UnsupportedEncodingException e) {
/*  56 */       throw new UnsupportedOperationException("Incompatible VM");
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
/*     */   public static boolean isDefaultsRGB(ICC_Profile profile) {
/*  68 */     if (!(profile instanceof java.awt.color.ICC_ProfileRGB)) {
/*  69 */       return false;
/*     */     }
/*     */     
/*  72 */     ICC_Profile sRGBProfile = ICC_Profile.getInstance(1000);
/*  73 */     if (profile.getProfileClass() != sRGBProfile.getProfileClass()) {
/*  74 */       return false;
/*     */     }
/*  76 */     if (profile.getMajorVersion() != sRGBProfile.getMajorVersion()) {
/*  77 */       return false;
/*     */     }
/*  79 */     if (profile.getMinorVersion() != sRGBProfile.getMinorVersion()) {
/*  80 */       return false;
/*     */     }
/*  82 */     if (!Arrays.equals(profile.getData(), sRGBProfile.getData())) {
/*  83 */       return false;
/*     */     }
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ICC_Profile getICC_Profile(byte[] data) {
/*  96 */     synchronized (ICC_Profile.class) {
/*  97 */       return ICC_Profile.getInstance(data);
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
/*     */   
/*     */   public static ICC_Profile getICC_Profile(int colorSpace) {
/* 111 */     synchronized (ICC_Profile.class) {
/* 112 */       return ICC_Profile.getInstance(colorSpace);
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
/*     */   
/*     */   public static ICC_Profile getICC_Profile(InputStream in) throws IOException {
/* 126 */     synchronized (ICC_Profile.class) {
/* 127 */       return ICC_Profile.getInstance(in);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static ICC_Profile getICC_Profile(String fileName) throws IOException {
/* 143 */     synchronized (ICC_Profile.class) {
/* 144 */       return ICC_Profile.getInstance(fileName);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/profile/ColorProfileUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */