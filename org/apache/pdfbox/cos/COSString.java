/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.util.Charsets;
/*     */ import org.apache.pdfbox.util.Hex;
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
/*     */ public final class COSString
/*     */   extends COSBase
/*     */ {
/*  48 */   private static final Log LOG = LogFactory.getLog(COSString.class);
/*     */ 
/*     */ 
/*     */   
/*  52 */   public static final boolean FORCE_PARSING = Boolean.getBoolean("org.apache.pdfbox.forceParsing");
/*     */ 
/*     */   
/*     */   private byte[] bytes;
/*     */ 
/*     */   
/*     */   private boolean forceHexForm;
/*     */ 
/*     */ 
/*     */   
/*     */   public static COSString parseHex(String hex) throws IOException {
/*  63 */     ByteArrayOutputStream bytes = new ByteArrayOutputStream();
/*  64 */     StringBuilder hexBuffer = new StringBuilder(hex.trim());
/*     */ 
/*     */     
/*  67 */     if (hexBuffer.length() % 2 != 0)
/*     */     {
/*  69 */       hexBuffer.append('0');
/*     */     }
/*     */     
/*  72 */     int length = hexBuffer.length();
/*  73 */     for (int i = 0; i < length; i += 2) {
/*     */ 
/*     */       
/*     */       try {
/*  77 */         bytes.write(Integer.parseInt(hexBuffer.substring(i, i + 2), 16));
/*     */       }
/*  79 */       catch (NumberFormatException e) {
/*     */         
/*  81 */         if (FORCE_PARSING) {
/*     */           
/*  83 */           LOG.warn("Encountered a malformed hex string");
/*  84 */           bytes.write(63);
/*     */         }
/*     */         else {
/*     */           
/*  88 */           throw new IOException("Invalid hex string: " + hex, e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     return new COSString(bytes.toByteArray());
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
/*     */   public COSString(byte[] bytes) {
/* 107 */     setValue(bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSString(String text) {
/* 118 */     boolean isOnlyPDFDocEncoding = true;
/* 119 */     for (char c : text.toCharArray()) {
/*     */       
/* 121 */       if (!PDFDocEncoding.containsChar(c)) {
/*     */         
/* 123 */         isOnlyPDFDocEncoding = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 128 */     if (isOnlyPDFDocEncoding) {
/*     */ 
/*     */       
/* 131 */       this.bytes = PDFDocEncoding.getBytes(text);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 136 */       byte[] data = text.getBytes(Charsets.UTF_16BE);
/* 137 */       ByteArrayOutputStream out = new ByteArrayOutputStream(data.length + 2);
/* 138 */       out.write(254);
/* 139 */       out.write(255);
/*     */       
/*     */       try {
/* 142 */         out.write(data);
/*     */       }
/* 144 */       catch (IOException e) {
/*     */ 
/*     */         
/* 147 */         throw new RuntimeException(e);
/*     */       } 
/* 149 */       this.bytes = out.toByteArray();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(byte[] value) {
/* 160 */     this.bytes = (byte[])value.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForceHexForm(boolean value) {
/* 171 */     this.forceHexForm = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getForceHexForm() {
/* 181 */     return this.forceHexForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString() {
/* 192 */     if (this.bytes.length >= 2) {
/*     */       
/* 194 */       if ((this.bytes[0] & 0xFF) == 254 && (this.bytes[1] & 0xFF) == 255)
/*     */       {
/*     */         
/* 197 */         return new String(this.bytes, 2, this.bytes.length - 2, Charsets.UTF_16BE);
/*     */       }
/* 199 */       if ((this.bytes[0] & 0xFF) == 255 && (this.bytes[1] & 0xFF) == 254)
/*     */       {
/*     */         
/* 202 */         return new String(this.bytes, 2, this.bytes.length - 2, Charsets.UTF_16LE);
/*     */       }
/*     */     } 
/*     */     
/* 206 */     return PDFDocEncoding.toString(this.bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getASCII() {
/* 217 */     return new String(this.bytes, Charsets.US_ASCII);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes() {
/* 227 */     return this.bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toHexString() {
/* 237 */     return Hex.getString(this.bytes);
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
/*     */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 250 */     return visitor.visitFromString(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 256 */     if (obj instanceof COSString) {
/*     */       
/* 258 */       COSString strObj = (COSString)obj;
/* 259 */       return (getString().equals(strObj.getString()) && this.forceHexForm == strObj.forceHexForm);
/*     */     } 
/*     */     
/* 262 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 268 */     int result = Arrays.hashCode(this.bytes);
/* 269 */     return result + (this.forceHexForm ? 17 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 275 */     return "COSString{" + getString() + "}";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */