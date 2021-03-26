/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
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
/*     */ final class ToUnicodeWriter
/*     */ {
/*  38 */   private final Map<Integer, String> cidToUnicode = new TreeMap<Integer, String>();
/*     */ 
/*     */ 
/*     */   
/*     */   private int wMode;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int MAX_ENTRIES_PER_OPERATOR = 100;
/*     */ 
/*     */ 
/*     */   
/*     */   ToUnicodeWriter() {
/*  51 */     this.wMode = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWMode(int wMode) {
/*  61 */     this.wMode = wMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int cid, String text) {
/*  72 */     if (cid < 0 || cid > 65535)
/*     */     {
/*  74 */       throw new IllegalArgumentException("CID is not valid");
/*     */     }
/*     */     
/*  77 */     if (text == null || text.isEmpty())
/*     */     {
/*  79 */       throw new IllegalArgumentException("Text is null or empty");
/*     */     }
/*     */     
/*  82 */     this.cidToUnicode.put(Integer.valueOf(cid), text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException {
/*  93 */     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, Charsets.US_ASCII));
/*     */     
/*  95 */     writeLine(writer, "/CIDInit /ProcSet findresource begin");
/*  96 */     writeLine(writer, "12 dict begin\n");
/*     */     
/*  98 */     writeLine(writer, "begincmap");
/*  99 */     writeLine(writer, "/CIDSystemInfo");
/* 100 */     writeLine(writer, "<< /Registry (Adobe)");
/* 101 */     writeLine(writer, "/Ordering (UCS)");
/* 102 */     writeLine(writer, "/Supplement 0");
/* 103 */     writeLine(writer, ">> def\n");
/*     */     
/* 105 */     writeLine(writer, "/CMapName /Adobe-Identity-UCS def");
/* 106 */     writeLine(writer, "/CMapType 2 def\n");
/*     */     
/* 108 */     if (this.wMode != 0)
/*     */     {
/* 110 */       writeLine(writer, "/WMode /" + this.wMode + " def");
/*     */     }
/*     */ 
/*     */     
/* 114 */     writeLine(writer, "1 begincodespacerange");
/* 115 */     writeLine(writer, "<0000> <FFFF>");
/* 116 */     writeLine(writer, "endcodespacerange\n");
/*     */ 
/*     */     
/* 119 */     List<Integer> srcFrom = new ArrayList<Integer>();
/* 120 */     List<Integer> srcTo = new ArrayList<Integer>();
/* 121 */     List<String> dstString = new ArrayList<String>();
/*     */     
/* 123 */     int srcPrev = -1;
/* 124 */     String dstPrev = null;
/*     */     
/* 126 */     int srcCode1 = -1;
/*     */     
/* 128 */     for (Map.Entry<Integer, String> entry : this.cidToUnicode.entrySet()) {
/*     */       
/* 130 */       int cid = ((Integer)entry.getKey()).intValue();
/* 131 */       String text = entry.getValue();
/*     */       
/* 133 */       if (cid == srcPrev + 1 && dstPrev
/* 134 */         .codePointCount(0, dstPrev.length()) == 1 && text
/* 135 */         .codePointAt(0) == dstPrev.codePointAt(0) + 1 && dstPrev
/* 136 */         .codePointAt(0) + 1 <= 255 - cid - srcCode1) {
/*     */ 
/*     */         
/* 139 */         srcTo.set(srcTo.size() - 1, Integer.valueOf(cid));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 144 */         srcCode1 = cid;
/* 145 */         srcFrom.add(Integer.valueOf(cid));
/* 146 */         srcTo.add(Integer.valueOf(cid));
/* 147 */         dstString.add(text);
/*     */       } 
/* 149 */       srcPrev = cid;
/* 150 */       dstPrev = text;
/*     */     } 
/*     */ 
/*     */     
/* 154 */     int batchCount = (int)Math.ceil(srcFrom.size() / 100.0D);
/*     */     
/* 156 */     for (int batch = 0; batch < batchCount; batch++) {
/*     */ 
/*     */       
/* 159 */       int count = (batch == batchCount - 1) ? (srcFrom.size() - 100 * batch) : 100;
/*     */       
/* 161 */       writer.write(count + " beginbfrange\n");
/* 162 */       for (int j = 0; j < count; j++) {
/*     */         
/* 164 */         int index = batch * 100 + j;
/* 165 */         writer.write(60);
/* 166 */         writer.write(Hex.getChars(((Integer)srcFrom.get(index)).shortValue()));
/* 167 */         writer.write("> ");
/*     */         
/* 169 */         writer.write(60);
/* 170 */         writer.write(Hex.getChars(((Integer)srcTo.get(index)).shortValue()));
/* 171 */         writer.write("> ");
/*     */         
/* 173 */         writer.write(60);
/* 174 */         writer.write(Hex.getCharsUTF16BE(dstString.get(index)));
/* 175 */         writer.write(">\n");
/*     */       } 
/* 177 */       writeLine(writer, "endbfrange\n");
/*     */     } 
/*     */ 
/*     */     
/* 181 */     writeLine(writer, "endcmap");
/* 182 */     writeLine(writer, "CMapName currentdict /CMap defineresource pop");
/* 183 */     writeLine(writer, "end");
/* 184 */     writeLine(writer, "end");
/*     */     
/* 186 */     writer.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeLine(BufferedWriter writer, String text) throws IOException {
/* 191 */     writer.write(text);
/* 192 */     writer.write(10);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/ToUnicodeWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */