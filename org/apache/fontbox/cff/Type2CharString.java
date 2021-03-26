/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.fontbox.type1.Type1CharStringReader;
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
/*     */ public class Type2CharString
/*     */   extends Type1CharString
/*     */ {
/*  34 */   private float defWidthX = 0.0F;
/*  35 */   private float nominalWidthX = 0.0F;
/*  36 */   private int pathCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final List<Object> type2sequence;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int gid;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type2CharString(Type1CharStringReader font, String fontName, String glyphName, int gid, List<Object> sequence, int defaultWidthX, int nomWidthX) {
/*  53 */     super(font, fontName, glyphName);
/*  54 */     this.gid = gid;
/*  55 */     this.type2sequence = sequence;
/*  56 */     this.defWidthX = defaultWidthX;
/*  57 */     this.nominalWidthX = nomWidthX;
/*  58 */     convertType1ToType2(sequence);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGID() {
/*  66 */     return this.gid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> getType2Sequence() {
/*  74 */     return this.type2sequence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void convertType1ToType2(List<Object> sequence) {
/*  83 */     this.type1Sequence = new ArrayList();
/*  84 */     this.pathCount = 0;
/*  85 */     CharStringHandler handler = new CharStringHandler()
/*     */       {
/*     */         public List<Number> handleCommand(List<Number> numbers, CharStringCommand command)
/*     */         {
/*  89 */           return Type2CharString.this.handleCommand(numbers, command);
/*     */         }
/*     */       };
/*  92 */     handler.handleSequence(sequence);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Number> handleCommand(List<Number> numbers, CharStringCommand command) {
/*  98 */     this.commandCount++;
/*  99 */     String name = CharStringCommand.TYPE2_VOCABULARY.get(command.getKey());
/*     */     
/* 101 */     if ("hstem".equals(name)) {
/*     */       
/* 103 */       numbers = clearStack(numbers, (numbers.size() % 2 != 0));
/* 104 */       expandStemHints(numbers, true);
/*     */     }
/* 106 */     else if ("vstem".equals(name)) {
/*     */       
/* 108 */       numbers = clearStack(numbers, (numbers.size() % 2 != 0));
/* 109 */       expandStemHints(numbers, false);
/*     */     }
/* 111 */     else if ("vmoveto".equals(name)) {
/*     */       
/* 113 */       numbers = clearStack(numbers, (numbers.size() > 1));
/* 114 */       markPath();
/* 115 */       addCommand(numbers, command);
/*     */     }
/* 117 */     else if ("rlineto".equals(name)) {
/*     */       
/* 119 */       addCommandList(split(numbers, 2), command);
/*     */     }
/* 121 */     else if ("hlineto".equals(name)) {
/*     */       
/* 123 */       drawAlternatingLine(numbers, true);
/*     */     }
/* 125 */     else if ("vlineto".equals(name)) {
/*     */       
/* 127 */       drawAlternatingLine(numbers, false);
/*     */     }
/* 129 */     else if ("rrcurveto".equals(name)) {
/*     */       
/* 131 */       addCommandList(split(numbers, 6), command);
/*     */     }
/* 133 */     else if ("endchar".equals(name)) {
/*     */       
/* 135 */       numbers = clearStack(numbers, (numbers.size() == 5 || numbers.size() == 1));
/* 136 */       closePath();
/* 137 */       if (numbers.size() == 4)
/*     */       {
/*     */         
/* 140 */         numbers.add(0, Integer.valueOf(0));
/* 141 */         addCommand(numbers, new CharStringCommand(12, 6));
/*     */       }
/*     */       else
/*     */       {
/* 145 */         addCommand(numbers, command);
/*     */       }
/*     */     
/* 148 */     } else if ("rmoveto".equals(name)) {
/*     */       
/* 150 */       numbers = clearStack(numbers, (numbers.size() > 2));
/* 151 */       markPath();
/* 152 */       addCommand(numbers, command);
/*     */     }
/* 154 */     else if ("hmoveto".equals(name)) {
/*     */       
/* 156 */       numbers = clearStack(numbers, (numbers.size() > 1));
/* 157 */       markPath();
/* 158 */       addCommand(numbers, command);
/*     */     }
/* 160 */     else if ("vhcurveto".equals(name)) {
/*     */       
/* 162 */       drawAlternatingCurve(numbers, false);
/*     */     }
/* 164 */     else if ("hvcurveto".equals(name)) {
/*     */       
/* 166 */       drawAlternatingCurve(numbers, true);
/*     */     }
/* 168 */     else if ("hflex".equals(name)) {
/*     */       
/* 170 */       List<Number> first = Arrays.asList(new Number[] { numbers.get(0), Integer.valueOf(0), numbers
/* 171 */             .get(1), numbers.get(2), numbers.get(3), Integer.valueOf(0) });
/* 172 */       List<Number> second = Arrays.asList(new Number[] { numbers.get(4), Integer.valueOf(0), numbers
/* 173 */             .get(5), Float.valueOf(-((Number)numbers.get(2)).floatValue()), numbers
/* 174 */             .get(6), Integer.valueOf(0) });
/* 175 */       addCommandList(Arrays.asList((List<Number>[])new List[] { first, second }, ), new CharStringCommand(8));
/*     */     }
/* 177 */     else if ("flex".equals(name)) {
/*     */       
/* 179 */       List<Number> first = numbers.subList(0, 6);
/* 180 */       List<Number> second = numbers.subList(6, 12);
/* 181 */       addCommandList(Arrays.asList((List<Number>[])new List[] { first, second }, ), new CharStringCommand(8));
/*     */     }
/* 183 */     else if ("hflex1".equals(name)) {
/*     */       
/* 185 */       List<Number> first = Arrays.asList(new Number[] { numbers.get(0), numbers.get(1), numbers
/* 186 */             .get(2), numbers.get(3), numbers.get(4), Integer.valueOf(0) });
/* 187 */       List<Number> second = Arrays.asList(new Number[] { numbers.get(5), Integer.valueOf(0), numbers
/* 188 */             .get(6), numbers.get(7), numbers.get(8), Integer.valueOf(0) });
/* 189 */       addCommandList(Arrays.asList((List<Number>[])new List[] { first, second }, ), new CharStringCommand(8));
/*     */     }
/* 191 */     else if ("flex1".equals(name)) {
/*     */       
/* 193 */       int dx = 0;
/* 194 */       int dy = 0;
/* 195 */       for (int i = 0; i < 5; i++) {
/*     */         
/* 197 */         dx += ((Number)numbers.get(i * 2)).intValue();
/* 198 */         dy += ((Number)numbers.get(i * 2 + 1)).intValue();
/*     */       } 
/* 200 */       List<Number> first = numbers.subList(0, 6);
/* 201 */       List<Number> second = Arrays.asList(new Number[] { numbers.get(6), numbers.get(7), numbers.get(8), numbers
/* 202 */             .get(9), (Math.abs(dx) > Math.abs(dy)) ? numbers.get(10) : Integer.valueOf(-dx), 
/* 203 */             (Math.abs(dx) > Math.abs(dy)) ? Integer.valueOf(-dy) : numbers.get(10) });
/* 204 */       addCommandList(Arrays.asList((List<Number>[])new List[] { first, second }, ), new CharStringCommand(8));
/*     */     }
/* 206 */     else if ("hstemhm".equals(name)) {
/*     */       
/* 208 */       numbers = clearStack(numbers, (numbers.size() % 2 != 0));
/* 209 */       expandStemHints(numbers, true);
/*     */     }
/* 211 */     else if ("hintmask".equals(name) || "cntrmask".equals(name)) {
/*     */       
/* 213 */       numbers = clearStack(numbers, (numbers.size() % 2 != 0));
/* 214 */       if (numbers.size() > 0)
/*     */       {
/* 216 */         expandStemHints(numbers, false);
/*     */       }
/*     */     }
/* 219 */     else if ("vstemhm".equals(name)) {
/*     */       
/* 221 */       numbers = clearStack(numbers, (numbers.size() % 2 != 0));
/* 222 */       expandStemHints(numbers, false);
/*     */     }
/* 224 */     else if ("rcurveline".equals(name)) {
/*     */       
/* 226 */       if (numbers.size() >= 2)
/*     */       {
/* 228 */         addCommandList(split(numbers.subList(0, numbers.size() - 2), 6), new CharStringCommand(8));
/*     */         
/* 230 */         addCommand(numbers.subList(numbers.size() - 2, numbers.size()), new CharStringCommand(5));
/*     */       }
/*     */     
/*     */     }
/* 234 */     else if ("rlinecurve".equals(name)) {
/*     */       
/* 236 */       if (numbers.size() >= 6)
/*     */       {
/* 238 */         addCommandList(split(numbers.subList(0, numbers.size() - 6), 2), new CharStringCommand(5));
/*     */         
/* 240 */         addCommand(numbers.subList(numbers.size() - 6, numbers.size()), new CharStringCommand(8));
/*     */       }
/*     */     
/*     */     }
/* 244 */     else if ("vvcurveto".equals(name)) {
/*     */       
/* 246 */       drawCurve(numbers, false);
/*     */     }
/* 248 */     else if ("hhcurveto".equals(name)) {
/*     */       
/* 250 */       drawCurve(numbers, true);
/*     */     }
/*     */     else {
/*     */       
/* 254 */       addCommand(numbers, command);
/*     */     } 
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Number> clearStack(List<Number> numbers, boolean flag) {
/* 261 */     if (this.type1Sequence.isEmpty())
/*     */     {
/* 263 */       if (flag) {
/*     */         
/* 265 */         addCommand(Arrays.asList(new Number[] { Float.valueOf(0.0F), Float.valueOf(((Number)numbers.get(0)).floatValue() + this.nominalWidthX) }, ), new CharStringCommand(13));
/*     */         
/* 267 */         numbers = numbers.subList(1, numbers.size());
/*     */       }
/*     */       else {
/*     */         
/* 271 */         addCommand(Arrays.asList(new Number[] { Float.valueOf(0.0F), Float.valueOf(this.defWidthX) }, ), new CharStringCommand(13));
/*     */       } 
/*     */     }
/*     */     
/* 275 */     return numbers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void expandStemHints(List<Number> numbers, boolean horizontal) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void markPath() {
/* 289 */     if (this.pathCount > 0)
/*     */     {
/* 291 */       closePath();
/*     */     }
/* 293 */     this.pathCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void closePath() {
/* 299 */     CharStringCommand command = (this.pathCount > 0) ? (CharStringCommand)this.type1Sequence.get(this.type1Sequence.size() - 1) : null;
/*     */ 
/*     */     
/* 302 */     CharStringCommand closepathCommand = new CharStringCommand(9);
/* 303 */     if (command != null && !closepathCommand.equals(command))
/*     */     {
/* 305 */       addCommand(Collections.emptyList(), closepathCommand);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawAlternatingLine(List<Number> numbers, boolean horizontal) {
/* 311 */     while (numbers.size() > 0) {
/*     */       
/* 313 */       addCommand(numbers.subList(0, 1), new CharStringCommand(horizontal ? 6 : 7));
/*     */       
/* 315 */       numbers = numbers.subList(1, numbers.size());
/* 316 */       horizontal = !horizontal;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawAlternatingCurve(List<Number> numbers, boolean horizontal) {
/* 322 */     while (numbers.size() >= 4) {
/*     */       
/* 324 */       boolean last = (numbers.size() == 5);
/* 325 */       if (horizontal) {
/*     */         
/* 327 */         addCommand(Arrays.asList(new Number[] { numbers.get(0), Integer.valueOf(0), numbers
/* 328 */                 .get(1), numbers.get(2), last ? numbers.get(4) : 
/* 329 */                 Integer.valueOf(0), numbers.get(3) }, ), new CharStringCommand(8));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 334 */         addCommand(Arrays.asList(new Number[] { Integer.valueOf(0), numbers.get(0), numbers
/* 335 */                 .get(1), numbers.get(2), numbers.get(3), last ? numbers
/* 336 */                 .get(4) : Integer.valueOf(0) }, ), new CharStringCommand(8));
/*     */       } 
/*     */       
/* 339 */       numbers = numbers.subList(last ? 5 : 4, numbers.size());
/* 340 */       horizontal = !horizontal;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawCurve(List<Number> numbers, boolean horizontal) {
/* 346 */     while (numbers.size() >= 4) {
/*     */       
/* 348 */       boolean first = (numbers.size() % 4 == 1);
/*     */       
/* 350 */       if (horizontal) {
/*     */         
/* 352 */         addCommand(Arrays.asList(new Number[] { numbers.get(first ? 1 : 0), first ? numbers
/* 353 */                 .get(0) : Integer.valueOf(0), numbers
/* 354 */                 .get(first ? 2 : 1), numbers
/* 355 */                 .get(first ? 3 : 2), numbers.get(first ? 4 : 3), 
/* 356 */                 Integer.valueOf(0) }, ), new CharStringCommand(8));
/*     */       }
/*     */       else {
/*     */         
/* 360 */         addCommand(Arrays.asList(new Number[] { first ? numbers.get(0) : Integer.valueOf(0), numbers.get(first ? 1 : 0), numbers
/* 361 */                 .get(first ? 2 : 1), numbers.get(first ? 3 : 2), 
/* 362 */                 Integer.valueOf(0), numbers.get(first ? 4 : 3) }, ), new CharStringCommand(8));
/*     */       } 
/*     */       
/* 365 */       numbers = numbers.subList(first ? 5 : 4, numbers.size());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addCommandList(List<List<Number>> numbers, CharStringCommand command) {
/* 371 */     for (List<Number> ns : numbers)
/*     */     {
/* 373 */       addCommand(ns, command);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void addCommand(List<Number> numbers, CharStringCommand command) {
/* 379 */     this.type1Sequence.addAll(numbers);
/* 380 */     this.type1Sequence.add(command);
/*     */   }
/*     */ 
/*     */   
/*     */   private static <E> List<List<E>> split(List<E> list, int size) {
/* 385 */     List<List<E>> result = new ArrayList<List<E>>();
/* 386 */     for (int i = 0; i < list.size() / size; i++)
/*     */     {
/* 388 */       result.add(list.subList(i * size, (i + 1) * size));
/*     */     }
/* 390 */     return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/Type2CharString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */