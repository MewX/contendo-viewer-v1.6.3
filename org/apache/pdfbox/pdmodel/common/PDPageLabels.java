/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NavigableSet;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
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
/*     */ public class PDPageLabels
/*     */   implements COSObjectable
/*     */ {
/*     */   private Map<Integer, PDPageLabelRange> labels;
/*     */   private PDDocument doc;
/*     */   
/*     */   public PDPageLabels(PDDocument document) {
/*  64 */     this.labels = new TreeMap<Integer, PDPageLabelRange>();
/*  65 */     this.doc = document;
/*  66 */     PDPageLabelRange defaultRange = new PDPageLabelRange();
/*  67 */     defaultRange.setStyle("D");
/*  68 */     this.labels.put(Integer.valueOf(0), defaultRange);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageLabels(PDDocument document, COSDictionary dict) throws IOException {
/*  91 */     this(document);
/*  92 */     if (dict == null) {
/*     */       return;
/*     */     }
/*     */     
/*  96 */     PDNumberTreeNode root = new PDNumberTreeNode(dict, (Class)PDPageLabelRange.class);
/*  97 */     findLabels(root);
/*     */   }
/*     */ 
/*     */   
/*     */   private void findLabels(PDNumberTreeNode node) throws IOException {
/* 102 */     if (node.getKids() != null) {
/*     */       
/* 104 */       List<PDNumberTreeNode> kids = node.getKids();
/* 105 */       for (PDNumberTreeNode kid : kids)
/*     */       {
/* 107 */         findLabels(kid);
/*     */       }
/*     */     }
/* 110 */     else if (node.getNumbers() != null) {
/*     */       
/* 112 */       Map<Integer, COSObjectable> numbers = node.getNumbers();
/* 113 */       for (Map.Entry<Integer, COSObjectable> i : numbers.entrySet()) {
/*     */         
/* 115 */         if (((Integer)i.getKey()).intValue() >= 0)
/*     */         {
/* 117 */           this.labels.put(i.getKey(), (PDPageLabelRange)i.getValue());
/*     */         }
/*     */       } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPageRangeCount() {
/* 137 */     return this.labels.size();
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
/*     */   
/*     */   public PDPageLabelRange getPageLabelRange(int startPage) {
/* 152 */     return this.labels.get(Integer.valueOf(startPage));
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
/*     */   
/*     */   public void setLabelItem(int startPage, PDPageLabelRange item) {
/* 167 */     if (startPage < 0)
/*     */     {
/* 169 */       throw new IllegalArgumentException("startPage parameter of setLabelItem may not be < 0");
/*     */     }
/* 171 */     this.labels.put(Integer.valueOf(startPage), item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 180 */     COSDictionary dict = new COSDictionary();
/* 181 */     COSArray arr = new COSArray();
/* 182 */     for (Map.Entry<Integer, PDPageLabelRange> i : this.labels.entrySet()) {
/*     */       
/* 184 */       arr.add((COSBase)COSInteger.get(((Integer)i.getKey()).intValue()));
/* 185 */       arr.add(i.getValue());
/*     */     } 
/* 187 */     dict.setItem(COSName.NUMS, (COSBase)arr);
/* 188 */     return (COSBase)dict;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Integer> getPageIndicesByLabels() {
/* 208 */     final Map<String, Integer> labelMap = new HashMap<String, Integer>(this.doc.getNumberOfPages());
/* 209 */     computeLabels(new LabelHandler()
/*     */         {
/*     */           
/*     */           public void newLabel(int pageIndex, String label)
/*     */           {
/* 214 */             labelMap.put(label, Integer.valueOf(pageIndex));
/*     */           }
/*     */         });
/* 217 */     return labelMap;
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
/*     */   public String[] getLabelsByPageIndices() {
/* 229 */     final String[] map = new String[this.doc.getNumberOfPages()];
/* 230 */     computeLabels(new LabelHandler()
/*     */         {
/*     */           
/*     */           public void newLabel(int pageIndex, String label)
/*     */           {
/* 235 */             if (pageIndex < PDPageLabels.this.doc.getNumberOfPages())
/*     */             {
/* 237 */               map[pageIndex] = label;
/*     */             }
/*     */           }
/*     */         });
/* 241 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NavigableSet<Integer> getPageIndices() {
/* 251 */     return new TreeSet<Integer>(this.labels.keySet());
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
/*     */ 
/*     */   
/*     */   private void computeLabels(LabelHandler handler) {
/* 267 */     Iterator<Map.Entry<Integer, PDPageLabelRange>> iterator = this.labels.entrySet().iterator();
/* 268 */     if (!iterator.hasNext()) {
/*     */       return;
/*     */     }
/*     */     
/* 272 */     int pageIndex = 0;
/* 273 */     Map.Entry<Integer, PDPageLabelRange> lastEntry = iterator.next();
/* 274 */     while (iterator.hasNext()) {
/*     */       
/* 276 */       Map.Entry<Integer, PDPageLabelRange> entry = iterator.next();
/* 277 */       int numPages = ((Integer)entry.getKey()).intValue() - ((Integer)lastEntry.getKey()).intValue();
/* 278 */       LabelGenerator labelGenerator = new LabelGenerator(lastEntry.getValue(), numPages);
/*     */       
/* 280 */       while (labelGenerator.hasNext()) {
/*     */         
/* 282 */         handler.newLabel(pageIndex, labelGenerator.next());
/* 283 */         pageIndex++;
/*     */       } 
/* 285 */       lastEntry = entry;
/*     */     } 
/*     */     
/* 288 */     LabelGenerator gen = new LabelGenerator(lastEntry.getValue(), this.doc.getNumberOfPages() - ((Integer)lastEntry.getKey()).intValue());
/* 289 */     while (gen.hasNext()) {
/*     */       
/* 291 */       handler.newLabel(pageIndex, gen.next());
/* 292 */       pageIndex++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static interface LabelHandler
/*     */   {
/*     */     void newLabel(int param1Int, String param1String);
/*     */   }
/*     */   
/*     */   private static class LabelGenerator
/*     */     implements Iterator<String>
/*     */   {
/*     */     private final PDPageLabelRange labelInfo;
/*     */     private final int numPages;
/*     */     private int currentPage;
/*     */     
/*     */     LabelGenerator(PDPageLabelRange label, int pages) {
/* 310 */       this.labelInfo = label;
/* 311 */       this.numPages = pages;
/* 312 */       this.currentPage = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 318 */       return (this.currentPage < this.numPages);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String next() {
/* 324 */       if (!hasNext())
/*     */       {
/* 326 */         throw new NoSuchElementException();
/*     */       }
/* 328 */       StringBuilder buf = new StringBuilder();
/* 329 */       if (this.labelInfo.getPrefix() != null) {
/*     */         
/* 331 */         String label = this.labelInfo.getPrefix();
/*     */ 
/*     */         
/* 334 */         while (label.lastIndexOf(false) != -1)
/*     */         {
/* 336 */           label = label.substring(0, label.length() - 1);
/*     */         }
/* 338 */         buf.append(label);
/*     */       } 
/* 340 */       if (this.labelInfo.getStyle() != null)
/*     */       {
/* 342 */         buf.append(getNumber(this.labelInfo.getStart() + this.currentPage, this.labelInfo
/* 343 */               .getStyle()));
/*     */       }
/* 345 */       this.currentPage++;
/* 346 */       return buf.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     private String getNumber(int pageIndex, String style) {
/* 351 */       if ("D".equals(style))
/*     */       {
/* 353 */         return Integer.toString(pageIndex);
/*     */       }
/* 355 */       if ("a".equals(style))
/*     */       {
/* 357 */         return makeLetterLabel(pageIndex);
/*     */       }
/* 359 */       if ("A".equals(style))
/*     */       {
/* 361 */         return makeLetterLabel(pageIndex).toUpperCase();
/*     */       }
/* 363 */       if ("r".equals(style))
/*     */       {
/* 365 */         return makeRomanLabel(pageIndex);
/*     */       }
/* 367 */       if ("R".equals(style))
/*     */       {
/* 369 */         return makeRomanLabel(pageIndex).toUpperCase();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 374 */       return Integer.toString(pageIndex);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 381 */     private static final String[][] ROMANS = new String[][] { { "", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix" }, { "", "x", "xx", "xxx", "xl", "l", "lx", "lxx", "lxxx", "xc" }, { "", "c", "cc", "ccc", "cd", "d", "dc", "dcc", "dccc", "cm" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static String makeRomanLabel(int pageIndex) {
/* 389 */       StringBuilder buf = new StringBuilder();
/* 390 */       int power = 0;
/* 391 */       while (power < 3 && pageIndex > 0) {
/*     */         
/* 393 */         buf.insert(0, ROMANS[power][pageIndex % 10]);
/* 394 */         pageIndex /= 10;
/* 395 */         power++;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 404 */       for (int i = 0; i < pageIndex; i++)
/*     */       {
/* 406 */         buf.insert(0, 'm');
/*     */       }
/* 408 */       return buf.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static String makeLetterLabel(int num) {
/* 417 */       StringBuilder buf = new StringBuilder();
/* 418 */       int numLetters = num / 26 + Integer.signum(num % 26);
/* 419 */       int letter = num % 26 + 26 * (1 - Integer.signum(num % 26)) + 97 - 1;
/* 420 */       for (int i = 0; i < numLetters; i++)
/*     */       {
/* 422 */         buf.appendCodePoint(letter);
/*     */       }
/* 424 */       return buf.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void remove() {
/* 431 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDPageLabels.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */