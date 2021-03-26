/*     */ package org.apache.pdfbox.pdfwriter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.contentstream.operator.Operator;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.util.Charsets;
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
/*     */ public class ContentStreamWriter
/*     */ {
/*     */   private final OutputStream output;
/*  46 */   public static final byte[] SPACE = new byte[] { 32 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static final byte[] EOL = new byte[] { 10 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContentStreamWriter(OutputStream out) {
/*  60 */     this.output = out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToken(COSBase base) throws IOException {
/*  71 */     writeObject(base);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToken(Operator op) throws IOException {
/*  82 */     writeObject(op);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTokens(Object... tokens) throws IOException {
/*  93 */     for (Object token : tokens)
/*     */     {
/*  95 */       writeObject(token);
/*     */     }
/*  97 */     this.output.write("\n".getBytes(Charsets.US_ASCII));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTokens(List<?> tokens) throws IOException {
/* 108 */     for (Object token : tokens)
/*     */     {
/* 110 */       writeObject(token);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeObject(Object o) throws IOException {
/* 116 */     if (o instanceof COSString) {
/*     */       
/* 118 */       COSWriter.writeString((COSString)o, this.output);
/* 119 */       this.output.write(SPACE);
/*     */     }
/* 121 */     else if (o instanceof COSFloat) {
/*     */       
/* 123 */       ((COSFloat)o).writePDF(this.output);
/* 124 */       this.output.write(SPACE);
/*     */     }
/* 126 */     else if (o instanceof COSInteger) {
/*     */       
/* 128 */       ((COSInteger)o).writePDF(this.output);
/* 129 */       this.output.write(SPACE);
/*     */     }
/* 131 */     else if (o instanceof COSBoolean) {
/*     */       
/* 133 */       ((COSBoolean)o).writePDF(this.output);
/* 134 */       this.output.write(SPACE);
/*     */     }
/* 136 */     else if (o instanceof COSName) {
/*     */       
/* 138 */       ((COSName)o).writePDF(this.output);
/* 139 */       this.output.write(SPACE);
/*     */     }
/* 141 */     else if (o instanceof COSArray) {
/*     */       
/* 143 */       COSArray array = (COSArray)o;
/* 144 */       this.output.write(COSWriter.ARRAY_OPEN);
/* 145 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 147 */         writeObject(array.get(i));
/* 148 */         this.output.write(SPACE);
/*     */       } 
/*     */       
/* 151 */       this.output.write(COSWriter.ARRAY_CLOSE);
/*     */     }
/* 153 */     else if (o instanceof COSDictionary) {
/*     */       
/* 155 */       COSDictionary obj = (COSDictionary)o;
/* 156 */       this.output.write(COSWriter.DICT_OPEN);
/* 157 */       for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)obj.entrySet()) {
/*     */         
/* 159 */         if (entry.getValue() != null) {
/*     */           
/* 161 */           writeObject(entry.getKey());
/* 162 */           this.output.write(SPACE);
/* 163 */           writeObject(entry.getValue());
/* 164 */           this.output.write(SPACE);
/*     */         } 
/*     */       } 
/* 167 */       this.output.write(COSWriter.DICT_CLOSE);
/* 168 */       this.output.write(SPACE);
/*     */     }
/* 170 */     else if (o instanceof Operator) {
/*     */       
/* 172 */       Operator op = (Operator)o;
/* 173 */       if (op.getName().equals("BI"))
/*     */       {
/* 175 */         this.output.write("BI".getBytes(Charsets.ISO_8859_1));
/* 176 */         COSDictionary dic = op.getImageParameters();
/* 177 */         for (COSName key : dic.keySet()) {
/*     */           
/* 179 */           Object value = dic.getDictionaryObject(key);
/* 180 */           key.writePDF(this.output);
/* 181 */           this.output.write(SPACE);
/* 182 */           writeObject(value);
/* 183 */           this.output.write(EOL);
/*     */         } 
/* 185 */         this.output.write("ID".getBytes(Charsets.ISO_8859_1));
/* 186 */         this.output.write(EOL);
/* 187 */         this.output.write(op.getImageData());
/* 188 */         this.output.write(EOL);
/* 189 */         this.output.write("EI".getBytes(Charsets.ISO_8859_1));
/* 190 */         this.output.write(EOL);
/*     */       }
/*     */       else
/*     */       {
/* 194 */         this.output.write(op.getName().getBytes(Charsets.ISO_8859_1));
/* 195 */         this.output.write(EOL);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 200 */       throw new IOException("Error:Unknown type in content stream:" + o);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfwriter/ContentStreamWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */