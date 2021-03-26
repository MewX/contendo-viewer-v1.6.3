/*     */ package org.apache.pdfbox.pdmodel.font.encoding;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
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
/*     */ public abstract class Encoding
/*     */   implements COSObjectable
/*     */ {
/*     */   public static Encoding getInstance(COSName name) {
/*  42 */     if (COSName.STANDARD_ENCODING.equals(name))
/*     */     {
/*  44 */       return StandardEncoding.INSTANCE;
/*     */     }
/*  46 */     if (COSName.WIN_ANSI_ENCODING.equals(name))
/*     */     {
/*  48 */       return WinAnsiEncoding.INSTANCE;
/*     */     }
/*  50 */     if (COSName.MAC_ROMAN_ENCODING.equals(name))
/*     */     {
/*  52 */       return MacRomanEncoding.INSTANCE;
/*     */     }
/*  54 */     if (COSName.MAC_EXPERT_ENCODING.equals(name))
/*     */     {
/*  56 */       return MacExpertEncoding.INSTANCE;
/*     */     }
/*     */ 
/*     */     
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */   
/*  64 */   protected final Map<Integer, String> codeToName = new HashMap<Integer, String>(250);
/*  65 */   protected final Map<String, Integer> inverted = new HashMap<String, Integer>(250);
/*     */ 
/*     */ 
/*     */   
/*     */   private Set<String> names;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, String> getCodeToNameMap() {
/*  75 */     return Collections.unmodifiableMap(this.codeToName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Integer> getNameToCodeMap() {
/*  86 */     return Collections.unmodifiableMap(this.inverted);
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
/*     */   protected void add(int code, String name) {
/*  99 */     this.codeToName.put(Integer.valueOf(code), name);
/* 100 */     if (!this.inverted.containsKey(name))
/*     */     {
/* 102 */       this.inverted.put(name, Integer.valueOf(code));
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
/*     */   protected void overwrite(int code, String name) {
/* 117 */     String oldName = this.codeToName.get(Integer.valueOf(code));
/* 118 */     if (oldName != null) {
/*     */       
/* 120 */       Integer oldCode = this.inverted.get(oldName);
/* 121 */       if (oldCode != null && oldCode.intValue() == code)
/*     */       {
/* 123 */         this.inverted.remove(oldName);
/*     */       }
/*     */     } 
/* 126 */     this.inverted.put(name, Integer.valueOf(code));
/* 127 */     this.codeToName.put(Integer.valueOf(code), name);
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
/*     */   public boolean contains(String name) {
/* 139 */     if (this.names == null)
/*     */     {
/* 141 */       synchronized (this) {
/*     */ 
/*     */         
/* 144 */         Set<String> tmpSet = new HashSet<String>(this.codeToName.values());
/*     */         
/* 146 */         this.names = tmpSet;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 151 */     return this.names.contains(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(int code) {
/* 161 */     return this.codeToName.containsKey(Integer.valueOf(code));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(int code) {
/* 172 */     String name = this.codeToName.get(Integer.valueOf(code));
/* 173 */     if (name != null)
/*     */     {
/* 175 */       return name;
/*     */     }
/* 177 */     return ".notdef";
/*     */   }
/*     */   
/*     */   public abstract String getEncodingName();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/Encoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */