/*     */ package org.apache.batik.util.gui.xmleditor;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.text.StyleContext;
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
/*     */ public class XMLContext
/*     */   extends StyleContext
/*     */ {
/*     */   public static final String XML_DECLARATION_STYLE = "xml_declaration";
/*     */   public static final String DOCTYPE_STYLE = "doctype";
/*     */   public static final String COMMENT_STYLE = "comment";
/*     */   public static final String ELEMENT_STYLE = "element";
/*     */   public static final String CHARACTER_DATA_STYLE = "character_data";
/*     */   public static final String ATTRIBUTE_NAME_STYLE = "attribute_name";
/*     */   public static final String ATTRIBUTE_VALUE_STYLE = "attribute_value";
/*     */   public static final String CDATA_STYLE = "cdata";
/*  47 */   protected Map syntaxForegroundMap = null;
/*     */ 
/*     */   
/*  50 */   protected Map syntaxFontMap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLContext() {
/*  60 */     this.syntaxFontMap = new HashMap<Object, Object>();
/*  61 */     this.syntaxForegroundMap = new HashMap<Object, Object>();
/*     */     
/*  63 */     Font defaultFont = new Font("Monospaced", 0, 12);
/*     */     
/*  65 */     String syntaxName = "default";
/*  66 */     Font font = defaultFont;
/*  67 */     Color fontForeground = Color.black;
/*  68 */     this.syntaxFontMap.put(syntaxName, font);
/*  69 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */     
/*  71 */     syntaxName = "xml_declaration";
/*  72 */     font = defaultFont.deriveFont(1);
/*  73 */     fontForeground = new Color(0, 0, 124);
/*  74 */     this.syntaxFontMap.put(syntaxName, font);
/*  75 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */     
/*  77 */     syntaxName = "doctype";
/*  78 */     font = defaultFont.deriveFont(1);
/*  79 */     fontForeground = new Color(0, 0, 124);
/*  80 */     this.syntaxFontMap.put(syntaxName, font);
/*  81 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */     
/*  83 */     syntaxName = "comment";
/*  84 */     font = defaultFont;
/*  85 */     fontForeground = new Color(128, 128, 128);
/*  86 */     this.syntaxFontMap.put(syntaxName, font);
/*  87 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */     
/*  89 */     syntaxName = "element";
/*  90 */     font = defaultFont;
/*  91 */     fontForeground = new Color(0, 0, 255);
/*  92 */     this.syntaxFontMap.put(syntaxName, font);
/*  93 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */     
/*  95 */     syntaxName = "character_data";
/*  96 */     font = defaultFont;
/*  97 */     fontForeground = Color.black;
/*  98 */     this.syntaxFontMap.put(syntaxName, font);
/*  99 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */     
/* 101 */     syntaxName = "attribute_name";
/* 102 */     font = defaultFont;
/* 103 */     fontForeground = new Color(0, 124, 0);
/* 104 */     this.syntaxFontMap.put(syntaxName, font);
/* 105 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */     
/* 107 */     syntaxName = "attribute_value";
/* 108 */     font = defaultFont;
/* 109 */     fontForeground = new Color(153, 0, 107);
/* 110 */     this.syntaxFontMap.put(syntaxName, font);
/* 111 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */     
/* 113 */     syntaxName = "cdata";
/* 114 */     font = defaultFont;
/* 115 */     fontForeground = new Color(124, 98, 0);
/* 116 */     this.syntaxFontMap.put(syntaxName, font);
/* 117 */     this.syntaxForegroundMap.put(syntaxName, fontForeground);
/*     */   }
/*     */   
/*     */   public XMLContext(Map syntaxFontMap, Map syntaxForegroundMap) {
/* 121 */     setSyntaxFont(syntaxFontMap);
/* 122 */     setSyntaxForeground(syntaxForegroundMap);
/*     */   }
/*     */   
/*     */   public void setSyntaxForeground(Map syntaxForegroundMap) {
/* 126 */     if (syntaxForegroundMap == null) {
/* 127 */       throw new IllegalArgumentException("syntaxForegroundMap can not be null");
/*     */     }
/* 129 */     this.syntaxForegroundMap = syntaxForegroundMap;
/*     */   }
/*     */   
/*     */   public void setSyntaxFont(Map syntaxFontMap) {
/* 133 */     if (syntaxFontMap == null) {
/* 134 */       throw new IllegalArgumentException("syntaxFontMap can not be null");
/*     */     }
/* 136 */     this.syntaxFontMap = syntaxFontMap;
/*     */   }
/*     */   
/*     */   public Color getSyntaxForeground(int ctx) {
/* 140 */     String name = getSyntaxName(ctx);
/* 141 */     return getSyntaxForeground(name);
/*     */   }
/*     */   
/*     */   public Color getSyntaxForeground(String name) {
/* 145 */     return (Color)this.syntaxForegroundMap.get(name);
/*     */   }
/*     */   
/*     */   public Font getSyntaxFont(int ctx) {
/* 149 */     String name = getSyntaxName(ctx);
/* 150 */     return getSyntaxFont(name);
/*     */   }
/*     */   
/*     */   public Font getSyntaxFont(String name) {
/* 154 */     return (Font)this.syntaxFontMap.get(name);
/*     */   }
/*     */   
/*     */   public String getSyntaxName(int ctx) {
/* 158 */     String name = "character_data";
/* 159 */     switch (ctx)
/*     */     { case 6:
/* 161 */         name = "xml_declaration";
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
/* 186 */         return name;case 7: name = "doctype"; return name;case 1: name = "comment"; return name;case 2: name = "element"; return name;case 4: name = "attribute_name"; return name;case 5: name = "attribute_value"; return name;case 10: name = "cdata"; return name; }  name = "default"; return name;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/xmleditor/XMLContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */