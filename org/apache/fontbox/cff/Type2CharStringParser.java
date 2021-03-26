/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
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
/*     */ public class Type2CharStringParser
/*     */ {
/*  30 */   private int hstemCount = 0;
/*  31 */   private int vstemCount = 0;
/*  32 */   private List<Object> sequence = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String fontName;
/*     */ 
/*     */   
/*     */   private final String glyphName;
/*     */ 
/*     */ 
/*     */   
/*     */   public Type2CharStringParser(String fontName, String glyphName) {
/*  44 */     this.fontName = fontName;
/*  45 */     this.glyphName = glyphName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type2CharStringParser(String fontName, int cid) {
/*  56 */     this.fontName = fontName;
/*  57 */     this.glyphName = String.format(Locale.US, "%04x", new Object[] { Integer.valueOf(cid) });
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
/*     */   public List<Object> parse(byte[] bytes, byte[][] globalSubrIndex, byte[][] localSubrIndex) throws IOException {
/*  71 */     return parse(bytes, globalSubrIndex, localSubrIndex, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Object> parse(byte[] bytes, byte[][] globalSubrIndex, byte[][] localSubrIndex, boolean init) throws IOException {
/*  76 */     if (init) {
/*     */       
/*  78 */       this.hstemCount = 0;
/*  79 */       this.vstemCount = 0;
/*  80 */       this.sequence = new ArrayList();
/*     */     } 
/*  82 */     DataInput input = new DataInput(bytes);
/*  83 */     boolean localSubroutineIndexProvided = (localSubrIndex != null && localSubrIndex.length > 0);
/*  84 */     boolean globalSubroutineIndexProvided = (globalSubrIndex != null && globalSubrIndex.length > 0);
/*     */     
/*  86 */     while (input.hasRemaining()) {
/*     */       
/*  88 */       int b0 = input.readUnsignedByte();
/*  89 */       if (b0 == 10 && localSubroutineIndexProvided) {
/*     */         
/*  91 */         Integer operand = (Integer)this.sequence.remove(this.sequence.size() - 1);
/*     */         
/*  93 */         int bias = 0;
/*  94 */         int nSubrs = localSubrIndex.length;
/*     */         
/*  96 */         if (nSubrs < 1240) {
/*     */           
/*  98 */           bias = 107;
/*     */         }
/* 100 */         else if (nSubrs < 33900) {
/*     */           
/* 102 */           bias = 1131;
/*     */         }
/*     */         else {
/*     */           
/* 106 */           bias = 32768;
/*     */         } 
/* 108 */         int subrNumber = bias + operand.intValue();
/* 109 */         if (subrNumber < localSubrIndex.length) {
/*     */           
/* 111 */           byte[] subrBytes = localSubrIndex[subrNumber];
/* 112 */           parse(subrBytes, globalSubrIndex, localSubrIndex, false);
/* 113 */           Object lastItem = this.sequence.get(this.sequence.size() - 1);
/* 114 */           if (lastItem instanceof CharStringCommand && ((CharStringCommand)lastItem).getKey().getValue()[0] == 11)
/*     */           {
/* 116 */             this.sequence.remove(this.sequence.size() - 1);
/*     */           }
/*     */         } 
/*     */         continue;
/*     */       } 
/* 121 */       if (b0 == 29 && globalSubroutineIndexProvided) {
/*     */         int bias;
/* 123 */         Integer operand = (Integer)this.sequence.remove(this.sequence.size() - 1);
/*     */ 
/*     */         
/* 126 */         int nSubrs = globalSubrIndex.length;
/*     */         
/* 128 */         if (nSubrs < 1240) {
/*     */           
/* 130 */           bias = 107;
/*     */         }
/* 132 */         else if (nSubrs < 33900) {
/*     */           
/* 134 */           bias = 1131;
/*     */         }
/*     */         else {
/*     */           
/* 138 */           bias = 32768;
/*     */         } 
/*     */         
/* 141 */         int subrNumber = bias + operand.intValue();
/* 142 */         if (subrNumber < globalSubrIndex.length) {
/*     */           
/* 144 */           byte[] subrBytes = globalSubrIndex[subrNumber];
/* 145 */           parse(subrBytes, globalSubrIndex, localSubrIndex, false);
/* 146 */           Object lastItem = this.sequence.get(this.sequence.size() - 1);
/* 147 */           if (lastItem instanceof CharStringCommand && ((CharStringCommand)lastItem).getKey().getValue()[0] == 11)
/*     */           {
/* 149 */             this.sequence.remove(this.sequence.size() - 1);
/*     */           }
/*     */         } 
/*     */         continue;
/*     */       } 
/* 154 */       if (b0 >= 0 && b0 <= 27) {
/*     */         
/* 156 */         this.sequence.add(readCommand(b0, input)); continue;
/*     */       } 
/* 158 */       if (b0 == 28) {
/*     */         
/* 160 */         this.sequence.add(readNumber(b0, input)); continue;
/*     */       } 
/* 162 */       if (b0 >= 29 && b0 <= 31) {
/*     */         
/* 164 */         this.sequence.add(readCommand(b0, input)); continue;
/*     */       } 
/* 166 */       if (b0 >= 32 && b0 <= 255) {
/*     */         
/* 168 */         this.sequence.add(readNumber(b0, input));
/*     */         
/*     */         continue;
/*     */       } 
/* 172 */       throw new IllegalArgumentException();
/*     */     } 
/*     */     
/* 175 */     return this.sequence;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CharStringCommand readCommand(int b0, DataInput input) throws IOException {
/* 181 */     if (b0 == 1 || b0 == 18) {
/*     */       
/* 183 */       this.hstemCount += peekNumbers().size() / 2;
/*     */     }
/* 185 */     else if (b0 == 3 || b0 == 19 || b0 == 20 || b0 == 23) {
/*     */       
/* 187 */       this.vstemCount += peekNumbers().size() / 2;
/*     */     } 
/*     */     
/* 190 */     if (b0 == 12) {
/*     */       
/* 192 */       int b1 = input.readUnsignedByte();
/*     */       
/* 194 */       return new CharStringCommand(b0, b1);
/*     */     } 
/* 196 */     if (b0 == 19 || b0 == 20) {
/*     */       
/* 198 */       int[] value = new int[1 + getMaskLength()];
/* 199 */       value[0] = b0;
/*     */       
/* 201 */       for (int i = 1; i < value.length; i++)
/*     */       {
/* 203 */         value[i] = input.readUnsignedByte();
/*     */       }
/*     */       
/* 206 */       return new CharStringCommand(value);
/*     */     } 
/*     */     
/* 209 */     return new CharStringCommand(b0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Number readNumber(int b0, DataInput input) throws IOException {
/* 215 */     if (b0 == 28)
/*     */     {
/* 217 */       return Integer.valueOf(input.readShort());
/*     */     }
/* 219 */     if (b0 >= 32 && b0 <= 246)
/*     */     {
/* 221 */       return Integer.valueOf(b0 - 139);
/*     */     }
/* 223 */     if (b0 >= 247 && b0 <= 250) {
/*     */       
/* 225 */       int b1 = input.readUnsignedByte();
/*     */       
/* 227 */       return Integer.valueOf((b0 - 247) * 256 + b1 + 108);
/*     */     } 
/* 229 */     if (b0 >= 251 && b0 <= 254) {
/*     */       
/* 231 */       int b1 = input.readUnsignedByte();
/*     */       
/* 233 */       return Integer.valueOf(-(b0 - 251) * 256 - b1 - 108);
/*     */     } 
/* 235 */     if (b0 == 255) {
/*     */       
/* 237 */       short value = input.readShort();
/*     */       
/* 239 */       double fraction = input.readUnsignedShort() / 65535.0D;
/* 240 */       return Double.valueOf(value + fraction);
/*     */     } 
/*     */ 
/*     */     
/* 244 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMaskLength() {
/* 250 */     int hintCount = this.hstemCount + this.vstemCount;
/* 251 */     int length = hintCount / 8;
/* 252 */     if (hintCount % 8 > 0)
/*     */     {
/* 254 */       length++;
/*     */     }
/* 256 */     return length;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Number> peekNumbers() {
/* 261 */     List<Number> numbers = new ArrayList<Number>();
/* 262 */     for (int i = this.sequence.size() - 1; i > -1; i--) {
/*     */       
/* 264 */       Object object = this.sequence.get(i);
/*     */       
/* 266 */       if (!(object instanceof Number))
/*     */       {
/* 268 */         return numbers;
/*     */       }
/* 270 */       numbers.add(0, (Number)object);
/*     */     } 
/* 272 */     return numbers;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/Type2CharStringParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */