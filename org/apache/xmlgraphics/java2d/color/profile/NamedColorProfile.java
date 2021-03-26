/*     */ package org.apache.xmlgraphics.java2d.color.profile;
/*     */ 
/*     */ import org.apache.xmlgraphics.java2d.color.NamedColorSpace;
/*     */ import org.apache.xmlgraphics.java2d.color.RenderingIntent;
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
/*     */ public class NamedColorProfile
/*     */ {
/*     */   private String profileName;
/*     */   private String copyright;
/*     */   private NamedColorSpace[] namedColors;
/*  33 */   private RenderingIntent renderingIntent = RenderingIntent.PERCEPTUAL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedColorProfile(String profileName, String copyright, NamedColorSpace[] namedColors, RenderingIntent intent) {
/*  44 */     this.profileName = profileName;
/*  45 */     this.copyright = copyright;
/*  46 */     this.namedColors = namedColors;
/*  47 */     this.renderingIntent = intent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingIntent getRenderingIntent() {
/*  56 */     return this.renderingIntent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedColorSpace[] getNamedColors() {
/*  64 */     NamedColorSpace[] copy = new NamedColorSpace[this.namedColors.length];
/*  65 */     System.arraycopy(this.namedColors, 0, copy, 0, this.namedColors.length);
/*  66 */     return copy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedColorSpace getNamedColor(String name) {
/*  75 */     if (this.namedColors != null) {
/*  76 */       for (int i = 0, c = this.namedColors.length; i < c; i++) {
/*  77 */         if (this.namedColors[i].getColorName().equals(name)) {
/*  78 */           return this.namedColors[i];
/*     */         }
/*     */       } 
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProfileName() {
/*  90 */     return this.profileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCopyright() {
/*  98 */     return this.copyright;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     StringBuffer sb = new StringBuffer("Named color profile: ");
/* 105 */     sb.append(getProfileName());
/* 106 */     sb.append(", ").append(this.namedColors.length).append(" colors");
/* 107 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/profile/NamedColorProfile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */