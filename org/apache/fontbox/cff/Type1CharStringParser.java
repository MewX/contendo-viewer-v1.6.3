/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class Type1CharStringParser
/*     */ {
/*  37 */   private static final Log LOG = LogFactory.getLog(Type1CharStringParser.class);
/*     */ 
/*     */   
/*     */   static final int RETURN = 11;
/*     */ 
/*     */   
/*     */   static final int CALLSUBR = 10;
/*     */ 
/*     */   
/*     */   static final int TWO_BYTE = 12;
/*     */   
/*     */   static final int CALLOTHERSUBR = 16;
/*     */   
/*     */   static final int POP = 17;
/*     */   
/*     */   private final String fontName;
/*     */   
/*     */   private final String glyphName;
/*     */ 
/*     */   
/*     */   public Type1CharStringParser(String fontName, String glyphName) {
/*  58 */     this.fontName = fontName;
/*  59 */     this.glyphName = glyphName;
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
/*     */   public List<Object> parse(byte[] bytes, List<byte[]> subrs) throws IOException {
/*  72 */     return parse(bytes, subrs, new ArrayList());
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Object> parse(byte[] bytes, List<byte[]> subrs, List<Object> sequence) throws IOException {
/*  77 */     DataInput input = new DataInput(bytes);
/*  78 */     while (input.hasRemaining()) {
/*     */       
/*  80 */       int b0 = input.readUnsignedByte();
/*  81 */       if (b0 == 10) {
/*     */ 
/*     */         
/*  84 */         Object obj = sequence.remove(sequence.size() - 1);
/*  85 */         if (!(obj instanceof Integer)) {
/*     */           
/*  87 */           LOG.warn("Parameter " + obj + " for CALLSUBR is ignored, integer expected in glyph '" + this.glyphName + "' of font " + this.fontName);
/*     */           
/*     */           continue;
/*     */         } 
/*  91 */         Integer operand = (Integer)obj;
/*     */         
/*  93 */         if (operand.intValue() >= 0 && operand.intValue() < subrs.size()) {
/*     */           
/*  95 */           byte[] subrBytes = subrs.get(operand.intValue());
/*  96 */           parse(subrBytes, subrs, sequence);
/*  97 */           Object lastItem = sequence.get(sequence.size() - 1);
/*  98 */           if (lastItem instanceof CharStringCommand && ((CharStringCommand)lastItem)
/*  99 */             .getKey().getValue()[0] == 11)
/*     */           {
/* 101 */             sequence.remove(sequence.size() - 1);
/*     */           }
/*     */           
/*     */           continue;
/*     */         } 
/* 106 */         LOG.warn("CALLSUBR is ignored, operand: " + operand + ", subrs.size(): " + subrs
/* 107 */             .size() + " in glyph '" + this.glyphName + "' of font " + this.fontName);
/*     */ 
/*     */         
/* 110 */         while (sequence.get(sequence.size() - 1) instanceof Integer)
/*     */         {
/* 112 */           sequence.remove(sequence.size() - 1);
/*     */         }
/*     */         continue;
/*     */       } 
/* 116 */       if (b0 == 12 && input.peekUnsignedByte(0) == 16) {
/*     */         int i;
/*     */         
/* 119 */         input.readByte();
/*     */         
/* 121 */         Integer othersubrNum = (Integer)sequence.remove(sequence.size() - 1);
/* 122 */         Integer numArgs = (Integer)sequence.remove(sequence.size() - 1);
/*     */ 
/*     */         
/* 125 */         Stack<Integer> results = new Stack<Integer>();
/* 126 */         switch (othersubrNum.intValue()) {
/*     */           
/*     */           case 0:
/* 129 */             results.push(removeInteger(sequence));
/* 130 */             results.push(removeInteger(sequence));
/* 131 */             sequence.remove(sequence.size() - 1);
/*     */             
/* 133 */             sequence.add(Integer.valueOf(0));
/* 134 */             sequence.add(new CharStringCommand(12, 16));
/*     */             break;
/*     */           
/*     */           case 1:
/* 138 */             sequence.add(Integer.valueOf(1));
/* 139 */             sequence.add(new CharStringCommand(12, 16));
/*     */             break;
/*     */           
/*     */           case 3:
/* 143 */             results.push(removeInteger(sequence));
/*     */             break;
/*     */           
/*     */           default:
/* 147 */             for (i = 0; i < numArgs.intValue(); i++)
/*     */             {
/* 149 */               results.push(removeInteger(sequence));
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 155 */         while (input.peekUnsignedByte(0) == 12 && input.peekUnsignedByte(1) == 17) {
/*     */           
/* 157 */           input.readByte();
/* 158 */           input.readByte();
/* 159 */           sequence.add(results.pop());
/*     */         } 
/*     */         
/* 162 */         if (results.size() > 0)
/*     */         {
/* 164 */           LOG.warn("Value left on the PostScript stack in glyph " + this.glyphName + " of font " + this.fontName); } 
/*     */         continue;
/*     */       } 
/* 167 */       if (b0 >= 0 && b0 <= 31) {
/*     */         
/* 169 */         sequence.add(readCommand(input, b0)); continue;
/*     */       } 
/* 171 */       if (b0 >= 32 && b0 <= 255) {
/*     */         
/* 173 */         sequence.add(readNumber(input, b0));
/*     */         
/*     */         continue;
/*     */       } 
/* 177 */       throw new IllegalArgumentException();
/*     */     } 
/*     */     
/* 180 */     return sequence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Integer removeInteger(List<Object> sequence) throws IOException {
/* 187 */     Object item = sequence.remove(sequence.size() - 1);
/* 188 */     if (item instanceof Integer)
/*     */     {
/* 190 */       return (Integer)item;
/*     */     }
/* 192 */     CharStringCommand command = (CharStringCommand)item;
/*     */ 
/*     */     
/* 195 */     if (command.getKey().getValue()[0] == 12 && command.getKey().getValue()[1] == 12) {
/*     */       
/* 197 */       int a = ((Integer)sequence.remove(sequence.size() - 1)).intValue();
/* 198 */       int b = ((Integer)sequence.remove(sequence.size() - 1)).intValue();
/* 199 */       return Integer.valueOf(b / a);
/*     */     } 
/* 201 */     throw new IOException("Unexpected char string command: " + command.getKey());
/*     */   }
/*     */ 
/*     */   
/*     */   private CharStringCommand readCommand(DataInput input, int b0) throws IOException {
/* 206 */     if (b0 == 12) {
/*     */       
/* 208 */       int b1 = input.readUnsignedByte();
/* 209 */       return new CharStringCommand(b0, b1);
/*     */     } 
/* 211 */     return new CharStringCommand(b0);
/*     */   }
/*     */ 
/*     */   
/*     */   private Integer readNumber(DataInput input, int b0) throws IOException {
/* 216 */     if (b0 >= 32 && b0 <= 246)
/*     */     {
/* 218 */       return Integer.valueOf(b0 - 139);
/*     */     }
/* 220 */     if (b0 >= 247 && b0 <= 250) {
/*     */       
/* 222 */       int b1 = input.readUnsignedByte();
/* 223 */       return Integer.valueOf((b0 - 247) * 256 + b1 + 108);
/*     */     } 
/* 225 */     if (b0 >= 251 && b0 <= 254) {
/*     */       
/* 227 */       int b1 = input.readUnsignedByte();
/* 228 */       return Integer.valueOf(-(b0 - 251) * 256 - b1 - 108);
/*     */     } 
/* 230 */     if (b0 == 255)
/*     */     {
/* 232 */       return Integer.valueOf(input.readInt());
/*     */     }
/*     */ 
/*     */     
/* 236 */     throw new IllegalArgumentException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/Type1CharStringParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */