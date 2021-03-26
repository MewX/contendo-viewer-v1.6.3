/*     */ package org.apache.batik.ext.swing;
/*     */ 
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.PlainDocument;
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
/*     */ public class DoubleDocument
/*     */   extends PlainDocument
/*     */ {
/*     */   public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
/*  40 */     if (str == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  45 */     String curVal = getText(0, getLength());
/*  46 */     boolean hasDot = (curVal.indexOf('.') != -1);
/*     */ 
/*     */     
/*  49 */     char[] buffer = str.toCharArray();
/*  50 */     char[] digit = new char[buffer.length];
/*  51 */     int j = 0;
/*     */     
/*  53 */     if (offs == 0 && buffer != null && buffer.length > 0 && buffer[0] == '-') {
/*  54 */       digit[j++] = buffer[0];
/*     */     }
/*  56 */     for (char aBuffer : buffer) {
/*  57 */       if (Character.isDigit(aBuffer))
/*  58 */         digit[j++] = aBuffer; 
/*  59 */       if (!hasDot && aBuffer == '.') {
/*  60 */         digit[j++] = '.';
/*  61 */         hasDot = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  66 */     String added = new String(digit, 0, j);
/*     */     try {
/*  68 */       StringBuffer val = new StringBuffer(curVal);
/*  69 */       val.insert(offs, added);
/*  70 */       String valStr = val.toString();
/*  71 */       if (valStr.equals(".") || valStr.equals("-") || valStr.equals("-.")) {
/*  72 */         super.insertString(offs, added, a);
/*     */       } else {
/*  74 */         Double.valueOf(valStr);
/*  75 */         super.insertString(offs, added, a);
/*     */       } 
/*  77 */     } catch (NumberFormatException numberFormatException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(double d) {
/*     */     try {
/*  84 */       remove(0, getLength());
/*  85 */       insertString(0, String.valueOf(d), (AttributeSet)null);
/*  86 */     } catch (BadLocationException badLocationException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getValue() {
/*     */     try {
/*  94 */       String t = getText(0, getLength());
/*  95 */       if (t != null && t.length() > 0) {
/*  96 */         return Double.parseDouble(t);
/*     */       }
/*     */       
/*  99 */       return 0.0D;
/*     */     }
/* 101 */     catch (BadLocationException e) {
/*     */ 
/*     */       
/* 104 */       throw new RuntimeException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/swing/DoubleDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */