/*     */ package org.apache.pdfbox.pdmodel.font.encoding;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
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
/*     */ public class DictionaryEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private final COSDictionary encoding;
/*     */   private final Encoding baseEncoding;
/*  37 */   private final Map<Integer, String> differences = new HashMap<Integer, String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DictionaryEncoding(COSName baseEncoding, COSArray differences) {
/*  47 */     this.encoding = new COSDictionary();
/*  48 */     this.encoding.setItem(COSName.NAME, (COSBase)COSName.ENCODING);
/*  49 */     this.encoding.setItem(COSName.DIFFERENCES, (COSBase)differences);
/*  50 */     if (baseEncoding != COSName.STANDARD_ENCODING) {
/*     */       
/*  52 */       this.encoding.setItem(COSName.BASE_ENCODING, (COSBase)baseEncoding);
/*  53 */       this.baseEncoding = Encoding.getInstance(baseEncoding);
/*     */     }
/*     */     else {
/*     */       
/*  57 */       this.baseEncoding = Encoding.getInstance(baseEncoding);
/*     */     } 
/*     */     
/*  60 */     if (this.baseEncoding == null)
/*     */     {
/*  62 */       throw new IllegalArgumentException("Invalid encoding: " + baseEncoding);
/*     */     }
/*     */     
/*  65 */     this.codeToName.putAll(this.baseEncoding.codeToName);
/*  66 */     this.inverted.putAll(this.baseEncoding.inverted);
/*  67 */     applyDifferences();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DictionaryEncoding(COSDictionary fontEncoding) {
/*  77 */     this.encoding = fontEncoding;
/*  78 */     this.baseEncoding = null;
/*  79 */     applyDifferences();
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
/*     */   public DictionaryEncoding(COSDictionary fontEncoding, boolean isNonSymbolic, Encoding builtIn) {
/*  91 */     this.encoding = fontEncoding;
/*     */     
/*  93 */     Encoding base = null;
/*  94 */     boolean hasBaseEncoding = this.encoding.containsKey(COSName.BASE_ENCODING);
/*  95 */     if (hasBaseEncoding) {
/*     */       
/*  97 */       COSName name = this.encoding.getCOSName(COSName.BASE_ENCODING);
/*  98 */       base = Encoding.getInstance(name);
/*     */     } 
/*     */     
/* 101 */     if (base == null)
/*     */     {
/* 103 */       if (isNonSymbolic) {
/*     */ 
/*     */         
/* 106 */         base = StandardEncoding.INSTANCE;
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 111 */       else if (builtIn != null) {
/*     */         
/* 113 */         base = builtIn;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 119 */         throw new IllegalArgumentException("Symbolic fonts must have a built-in encoding");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 124 */     this.baseEncoding = base;
/*     */     
/* 126 */     this.codeToName.putAll(this.baseEncoding.codeToName);
/* 127 */     this.inverted.putAll(this.baseEncoding.inverted);
/* 128 */     applyDifferences();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void applyDifferences() {
/* 134 */     COSBase base = this.encoding.getDictionaryObject(COSName.DIFFERENCES);
/* 135 */     if (!(base instanceof COSArray)) {
/*     */       return;
/*     */     }
/*     */     
/* 139 */     COSArray diffArray = (COSArray)base;
/* 140 */     int currentIndex = -1;
/* 141 */     for (int i = 0; i < diffArray.size(); i++) {
/*     */       
/* 143 */       COSBase next = diffArray.getObject(i);
/* 144 */       if (next instanceof COSNumber) {
/*     */         
/* 146 */         currentIndex = ((COSNumber)next).intValue();
/*     */       }
/* 148 */       else if (next instanceof COSName) {
/*     */         
/* 150 */         COSName name = (COSName)next;
/* 151 */         overwrite(currentIndex, name.getName());
/* 152 */         this.differences.put(Integer.valueOf(currentIndex), name.getName());
/* 153 */         currentIndex++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Encoding getBaseEncoding() {
/* 163 */     return this.baseEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, String> getDifferences() {
/* 171 */     return this.differences;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 177 */     return (COSBase)this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodingName() {
/* 183 */     return this.baseEncoding.getEncodingName() + " with differences";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/DictionaryEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */