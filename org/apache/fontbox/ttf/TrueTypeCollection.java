/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public class TrueTypeCollection
/*     */   implements Closeable
/*     */ {
/*     */   private final TTFDataStream stream;
/*     */   private final int numFonts;
/*     */   private final long[] fontOffsets;
/*     */   
/*     */   public TrueTypeCollection(File file) throws IOException {
/*  45 */     this(new RAFDataStream(file, "r"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TrueTypeCollection(InputStream stream) throws IOException {
/*  56 */     this(new MemoryTTFDataStream(stream));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TrueTypeCollection(TTFDataStream stream) throws IOException {
/*  67 */     this.stream = stream;
/*     */ 
/*     */     
/*  70 */     String tag = stream.readTag();
/*  71 */     if (!tag.equals("ttcf"))
/*     */     {
/*  73 */       throw new IOException("Missing TTC header");
/*     */     }
/*  75 */     float version = stream.read32Fixed();
/*  76 */     this.numFonts = (int)stream.readUnsignedInt();
/*  77 */     this.fontOffsets = new long[this.numFonts];
/*  78 */     for (int i = 0; i < this.numFonts; i++)
/*     */     {
/*  80 */       this.fontOffsets[i] = stream.readUnsignedInt();
/*     */     }
/*  82 */     if (version >= 2.0F) {
/*     */ 
/*     */       
/*  85 */       int ulDsigTag = stream.readUnsignedShort();
/*  86 */       int ulDsigLength = stream.readUnsignedShort();
/*  87 */       int j = stream.readUnsignedShort();
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
/*     */   public void processAllFonts(TrueTypeFontProcessor trueTypeFontProcessor) throws IOException {
/*  99 */     for (int i = 0; i < this.numFonts; i++) {
/*     */       
/* 101 */       TrueTypeFont font = getFontAtIndex(i);
/* 102 */       trueTypeFontProcessor.process(font);
/*     */     } 
/*     */   }
/*     */   
/*     */   private TrueTypeFont getFontAtIndex(int idx) throws IOException {
/*     */     TTFParser parser;
/* 108 */     this.stream.seek(this.fontOffsets[idx]);
/*     */     
/* 110 */     if (this.stream.readTag().equals("OTTO")) {
/*     */       
/* 112 */       parser = new OTFParser(false, true);
/*     */     }
/*     */     else {
/*     */       
/* 116 */       parser = new TTFParser(false, true);
/*     */     } 
/* 118 */     this.stream.seek(this.fontOffsets[idx]);
/* 119 */     return parser.parse(new TTCDataStream(this.stream));
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
/*     */   public TrueTypeFont getFontByName(String name) throws IOException {
/* 131 */     for (int i = 0; i < this.numFonts; i++) {
/*     */       
/* 133 */       TrueTypeFont font = getFontAtIndex(i);
/* 134 */       if (font.getName().equals(name))
/*     */       {
/* 136 */         return font;
/*     */       }
/*     */     } 
/* 139 */     return null;
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
/*     */   public void close() throws IOException {
/* 153 */     this.stream.close();
/*     */   }
/*     */   
/*     */   public static interface TrueTypeFontProcessor {
/*     */     void process(TrueTypeFont param1TrueTypeFont) throws IOException;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/TrueTypeCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */