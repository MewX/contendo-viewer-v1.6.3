/*     */ package net.a.a.e.c.e;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import net.a.a.c;
/*     */ import net.a.a.e.d.c.e;
/*     */ import net.a.a.e.d.c.g;
/*     */ import net.a.a.e.d.d;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLGlyphElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class b
/*     */   extends a
/*     */   implements g, MathMLGlyphElement
/*     */ {
/*     */   public static final String r = "mglyph";
/*     */   private static final String s = "alt";
/*     */   private static final String t = "fontfamily";
/*     */   private static final String u = "index";
/*     */   private static final long v = 1L;
/*     */   
/*     */   public b(String paramString, AbstractDocument paramAbstractDocument) {
/*  73 */     super(paramString, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  79 */     return (Node)new b(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributedCharacterIterator a(AttributedCharacterIterator paramAttributedCharacterIterator, c paramc) {
/*     */     AttributedString attributedString;
/*  87 */     String str2, str1 = getFontfamily();
/*     */     
/*  89 */     if (str1 == null) {
/*  90 */       str2 = "serif";
/*     */     } else {
/*  92 */       str2 = str1.trim();
/*     */     } 
/*  94 */     Font font = net.a.a.f.b.b().a(str2, 0, 
/*  95 */         d.a(paramc));
/*  96 */     int i = getIndex();
/*  97 */     if (i > 0 && font.getFamily().equalsIgnoreCase(str2) && font
/*  98 */       .canDisplay(i)) {
/*  99 */       attributedString = new AttributedString(new String(new int[] { i }, 0, 1));
/*     */       
/* 101 */       attributedString.addAttribute(TextAttribute.FONT, font);
/*     */     } else {
/* 103 */       attributedString = e.a(getAlt(), 
/* 104 */           d(), 
/* 105 */           d.a(paramc), paramc);
/*     */     } 
/* 107 */     return attributedString.getIterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAlt() {
/* 112 */     return a("alt");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFontfamily() {
/* 117 */     return a("fontfamily");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 122 */     int i = 0;
/* 123 */     String str = a("index");
/*     */     try {
/* 125 */       if (str != null) {
/* 126 */         i = Integer.parseInt(str);
/*     */       }
/* 128 */     } catch (NumberFormatException numberFormatException) {
/* 129 */       i = 0;
/*     */     } 
/* 131 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlt(String paramString) {
/* 136 */     setAttribute("alt", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFontfamily(String paramString) {
/* 141 */     setAttribute("fontfamily", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(int paramInt) {
/* 146 */     setAttribute("index", Integer.toString(paramInt));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/e/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */