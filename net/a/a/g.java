/*     */ package net.a.a;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.concurrent.ThreadSafe;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ThreadSafe
/*     */ public class g
/*     */   implements EntityResolver
/*     */ {
/*     */   public static final String a = "http://www.w3.org/TR/MathML2/dtd/mathml2.dtd";
/*     */   public static final String b = "-//W3C//DTD MathML 2.0//EN";
/*     */   private static final String c = "http://www.w3.org/Math/DTD/mathml1";
/*  58 */   private static final Map<String, String> d = new HashMap<String, String>();
/*     */   
/*  60 */   private static final Map<String, String> e = new HashMap<String, String>();
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
/*     */   public InputSource resolveEntity(String paramString1, String paramString2) {
/*  72 */     InputSource inputSource = null;
/*     */ 
/*     */     
/*  75 */     String str = d.get(paramString1);
/*     */     
/*  77 */     if (str == null && paramString2
/*     */       
/*  79 */       .startsWith("http://www.w3.org/Math/DTD/mathml1"))
/*     */     {
/*     */       
/*  82 */       str = "/net/sourceforge/jeuclid/mathml.1.0.1" + paramString2.substring("http://www.w3.org/Math/DTD/mathml1"
/*  83 */           .length());
/*     */     }
/*  85 */     if (str != null) {
/*  86 */       inputSource = a(paramString1, paramString2, str);
/*     */     }
/*  88 */     return inputSource;
/*     */   }
/*     */ 
/*     */   
/*     */   private InputSource a(String paramString1, String paramString2, String paramString3) {
/*  93 */     InputSource inputSource = null;
/*     */     
/*  95 */     InputStream inputStream = g.class.getResourceAsStream(paramString3);
/*  96 */     if (inputStream != null) {
/*  97 */       inputSource = new InputSource(inputStream);
/*  98 */       inputSource.setPublicId(paramString1);
/*     */       
/* 100 */       String str = e.get(paramString1);
/* 101 */       if (str == null) {
/* 102 */         str = paramString2;
/*     */       }
/* 104 */       inputSource.setSystemId(str);
/*     */     } 
/* 106 */     return inputSource;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 111 */     d.put("-//OpenOffice.org//DTD Modified W3C MathML 1.01//EN", "/net/sourceforge/jeuclid/openoffice.mathml.1.0.1/math.dtd");
/*     */ 
/*     */ 
/*     */     
/* 115 */     d.put("-//W3C//DTD MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/mathml2.dtd");
/*     */ 
/*     */ 
/*     */     
/* 119 */     e.put("-//W3C//DTD MathML 2.0//EN", "http://www.w3.org/TR/MathML2/dtd/mathml2.dtd");
/*     */ 
/*     */ 
/*     */     
/* 123 */     d.put("-//W3C//DTD XHTML 1.1 plus MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/xhtml-math11-f.dtd");
/*     */ 
/*     */     
/* 126 */     e.put("-//W3C//DTD XHTML 1.1 plus MathML 2.0//EN", "http://www.w3.org/Math/DTD/mathml2/xhtml-math11-f.dtd");
/*     */ 
/*     */ 
/*     */     
/* 130 */     d.put("-//W3C//ENTITIES MathML 2.0 Qualified Names 1.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/mathml2-qname-1.mod");
/*     */ 
/*     */     
/* 133 */     d
/* 134 */       .put("-//W3C//ENTITIES Added Math Symbols: Arrow Relations for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isoamsa.ent");
/*     */ 
/*     */     
/* 137 */     d
/* 138 */       .put("-//W3C//ENTITIES Added Math Symbols: Binary Operators for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isoamsb.ent");
/*     */ 
/*     */     
/* 141 */     d
/* 142 */       .put("-//W3C//ENTITIES Added Math Symbols: Delimiters for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isoamsc.ent");
/*     */ 
/*     */     
/* 145 */     d
/* 146 */       .put("-//W3C//ENTITIES Added Math Symbols: Negated Relations for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isoamsn.ent");
/*     */ 
/*     */     
/* 149 */     d
/* 150 */       .put("-//W3C//ENTITIES Added Math Symbols: Ordinary for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isoamso.ent");
/*     */ 
/*     */     
/* 153 */     d
/* 154 */       .put("-//W3C//ENTITIES Added Math Symbols: Relations for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isoamsr.ent");
/*     */ 
/*     */     
/* 157 */     d.put("-//W3C//ENTITIES Greek Symbols for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isogrk3.ent");
/*     */ 
/*     */     
/* 160 */     d.put("-//W3C//ENTITIES Math Alphabets: Fraktur for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isomfrk.ent");
/*     */ 
/*     */     
/* 163 */     d
/* 164 */       .put("-//W3C//ENTITIES Math Alphabets: Open Face for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isomopf.ent");
/*     */ 
/*     */     
/* 167 */     d.put("-//W3C//ENTITIES Math Alphabets: Script for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isomscr.ent");
/*     */ 
/*     */     
/* 170 */     d.put("-//W3C//ENTITIES General Technical for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso9573-13/isotech.ent");
/*     */ 
/*     */     
/* 173 */     d.put("-//W3C//ENTITIES Box and Line Drawing for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso8879/isobox.ent");
/*     */ 
/*     */     
/* 176 */     d.put("-//W3C//ENTITIES Russian Cyrillic for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso8879/isocyr1.ent");
/*     */ 
/*     */     
/* 179 */     d.put("-//W3C//ENTITIES Non-Russian Cyrillic for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso8879/isocyr2.ent");
/*     */ 
/*     */     
/* 182 */     d.put("-//W3C//ENTITIES Diacritical Marks for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso8879/isodia.ent");
/*     */ 
/*     */     
/* 185 */     d.put("-//W3C//ENTITIES Added Latin 1 for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso8879/isolat1.ent");
/*     */ 
/*     */     
/* 188 */     d.put("-//W3C//ENTITIES Added Latin 2 for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso8879/isolat2.ent");
/*     */ 
/*     */     
/* 191 */     d
/* 192 */       .put("-//W3C//ENTITIES Numeric and Special Graphic for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso8879/isonum.ent");
/*     */ 
/*     */     
/* 195 */     d.put("-//W3C//ENTITIES Publishing for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/iso8879/isopub.ent");
/*     */ 
/*     */     
/* 198 */     d.put("-//W3C//ENTITIES Extra for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/mathml/mmlextra.ent");
/*     */ 
/*     */     
/* 201 */     d.put("-//W3C//ENTITIES Aliases for MathML 2.0//EN", "/net/sourceforge/jeuclid/mathml.2.0/mathml/mmlalias.ent");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */