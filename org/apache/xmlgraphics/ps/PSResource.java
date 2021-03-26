/*     */ package org.apache.xmlgraphics.ps;
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
/*     */ 
/*     */ public class PSResource
/*     */   implements Comparable
/*     */ {
/*     */   public static final String TYPE_FILE = "file";
/*     */   public static final String TYPE_FONT = "font";
/*     */   public static final String TYPE_PROCSET = "procset";
/*     */   public static final String TYPE_PATTERN = "pattern";
/*     */   public static final String TYPE_FORM = "form";
/*     */   public static final String TYPE_ENCODING = "encoding";
/*     */   public static final String TYPE_CMAP = "cmap";
/*     */   public static final String TYPE_CIDFONT = "cidfont";
/*     */   private String type;
/*     */   private String name;
/*     */   
/*     */   public PSResource(String type, String name) {
/*  53 */     this.type = type;
/*  54 */     this.name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  59 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  64 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceSpecification() {
/*  69 */     StringBuffer sb = new StringBuffer();
/*  70 */     sb.append(getType()).append(" ").append(PSGenerator.convertStringToDSC(getName()));
/*  71 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  76 */     if (obj == this)
/*  77 */       return true; 
/*  78 */     if (obj instanceof PSResource) {
/*  79 */       PSResource other = (PSResource)obj;
/*  80 */       return other.toString().equals(toString());
/*     */     } 
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  88 */     return toString().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/*  93 */     PSResource other = (PSResource)o;
/*  94 */     if (this == other) {
/*  95 */       return 0;
/*     */     }
/*  97 */     int result = getType().compareTo(other.getType());
/*  98 */     if (result == 0) {
/*  99 */       result = getName().compareTo(other.getName());
/*     */     }
/* 101 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     return getResourceSpecification();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */