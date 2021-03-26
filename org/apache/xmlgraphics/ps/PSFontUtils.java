/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.io.EndianUtils;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.xmlgraphics.fonts.Glyphs;
/*     */ import org.apache.xmlgraphics.util.io.ASCIIHexOutputStream;
/*     */ import org.apache.xmlgraphics.util.io.SubInputStream;
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
/*     */ public class PSFontUtils
/*     */ {
/*     */   public static void embedType1Font(PSGenerator gen, InputStream in) throws IOException {
/*  52 */     boolean finished = false;
/*  53 */     while (!finished) {
/*  54 */       BufferedReader reader; String line; SubInputStream sin; ASCIIHexOutputStream hexOut; int segIndicator = in.read();
/*  55 */       if (segIndicator < 0)
/*  56 */         throw new IOException("Unexpected end-of-file while reading segment indicator"); 
/*  57 */       if (segIndicator != 128) {
/*  58 */         throw new IOException("Expected ASCII 128, found: " + segIndicator);
/*     */       }
/*  60 */       int segType = in.read();
/*  61 */       if (segType < 0) {
/*  62 */         throw new IOException("Unexpected end-of-file while reading segment type");
/*     */       }
/*  64 */       int dataSegLen = 0;
/*  65 */       switch (segType) {
/*     */         case 1:
/*  67 */           dataSegLen = EndianUtils.readSwappedInteger(in);
/*     */           
/*  69 */           reader = new BufferedReader(new InputStreamReader((InputStream)new SubInputStream(in, dataSegLen), "US-ASCII"));
/*     */ 
/*     */ 
/*     */           
/*  73 */           while ((line = reader.readLine()) != null) {
/*  74 */             gen.writeln(line);
/*     */           }
/*     */           continue;
/*     */         case 2:
/*  78 */           dataSegLen = EndianUtils.readSwappedInteger(in);
/*     */           
/*  80 */           sin = new SubInputStream(in, dataSegLen);
/*  81 */           hexOut = new ASCIIHexOutputStream(gen.getOutputStream());
/*  82 */           IOUtils.copy((InputStream)sin, (OutputStream)hexOut);
/*  83 */           gen.newLine();
/*     */           continue;
/*     */         case 3:
/*  86 */           finished = true; continue;
/*     */       } 
/*  88 */       throw new IOException("Unsupported segment type: " + segType);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public static final PSResource WINANSI_ENCODING_RESOURCE = new PSResource("encoding", "WinAnsiEncoding");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void defineWinAnsiEncoding(PSGenerator gen) throws IOException {
/* 103 */     gen.writeDSCComment("BeginResource", WINANSI_ENCODING_RESOURCE);
/* 104 */     gen.writeln("/WinAnsiEncoding [");
/* 105 */     for (int i = 0; i < Glyphs.WINANSI_ENCODING.length; i++) {
/* 106 */       if (i > 0) {
/* 107 */         if (i % 5 == 0) {
/* 108 */           gen.newLine();
/*     */         } else {
/* 110 */           gen.write(" ");
/*     */         } 
/*     */       }
/* 113 */       char ch = Glyphs.WINANSI_ENCODING[i];
/* 114 */       String glyphname = Glyphs.charToGlyphName(ch);
/* 115 */       if ("".equals(glyphname)) {
/* 116 */         gen.write("/.notdef");
/*     */       } else {
/* 118 */         gen.write("/");
/* 119 */         gen.write(glyphname);
/*     */       } 
/*     */     } 
/* 122 */     gen.newLine();
/* 123 */     gen.writeln("] def");
/* 124 */     gen.writeDSCComment("EndResource");
/* 125 */     gen.getResourceTracker().registerSuppliedResource(WINANSI_ENCODING_RESOURCE);
/*     */   }
/*     */ 
/*     */   
/* 129 */   public static final PSResource ADOBECYRILLIC_ENCODING_RESOURCE = new PSResource("encoding", "AdobeStandardCyrillicEncoding");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void defineAdobeCyrillicEncoding(PSGenerator gen) throws IOException {
/* 138 */     gen.writeDSCComment("BeginResource", ADOBECYRILLIC_ENCODING_RESOURCE);
/* 139 */     gen.writeln("/AdobeStandardCyrillicEncoding [");
/* 140 */     for (int i = 0; i < Glyphs.ADOBECYRILLIC_ENCODING.length; i++) {
/* 141 */       if (i > 0) {
/* 142 */         if (i % 5 == 0) {
/* 143 */           gen.newLine();
/*     */         } else {
/* 145 */           gen.write(" ");
/*     */         } 
/*     */       }
/* 148 */       char ch = Glyphs.ADOBECYRILLIC_ENCODING[i];
/* 149 */       String glyphname = Glyphs.charToGlyphName(ch);
/* 150 */       if ("".equals(glyphname)) {
/* 151 */         gen.write("/.notdef");
/*     */       } else {
/* 153 */         gen.write("/");
/* 154 */         gen.write(glyphname);
/*     */       } 
/*     */     } 
/* 157 */     gen.newLine();
/* 158 */     gen.writeln("] def");
/* 159 */     gen.writeDSCComment("EndResource");
/* 160 */     gen.getResourceTracker().registerSuppliedResource(ADOBECYRILLIC_ENCODING_RESOURCE);
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
/*     */   public static void redefineFontEncoding(PSGenerator gen, String fontName, String encoding) throws IOException {
/* 173 */     gen.writeln("/" + fontName + " findfont");
/* 174 */     gen.writeln("dup length dict begin");
/* 175 */     gen.writeln("  {1 index /FID ne {def} {pop pop} ifelse} forall");
/* 176 */     gen.writeln("  /Encoding " + encoding + " def");
/* 177 */     gen.writeln("  currentdict");
/* 178 */     gen.writeln("end");
/* 179 */     gen.writeln("/" + fontName + " exch definefont pop");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSFontUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */