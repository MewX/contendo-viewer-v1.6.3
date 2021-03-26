/*     */ package org.apache.batik.util.gui.xmleditor;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.Element;
/*     */ import javax.swing.text.PlainView;
/*     */ import javax.swing.text.Segment;
/*     */ import javax.swing.text.Utilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLView
/*     */   extends PlainView
/*     */ {
/*  40 */   protected XMLContext context = null;
/*  41 */   protected XMLScanner lexer = new XMLScanner();
/*  42 */   protected int tabSize = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLView(XMLContext context, Element elem) {
/*  49 */     super(elem);
/*  50 */     this.context = context;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabSize() {
/*  55 */     return this.tabSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int drawUnselectedText(Graphics g, int x, int y, int p0, int p1) throws BadLocationException {
/*  62 */     XMLDocument doc = (XMLDocument)getDocument();
/*  63 */     XMLToken token = doc.getScannerStart(p0);
/*     */     
/*  65 */     String str = doc.getText(token.getStartOffset(), p1 - token.getStartOffset() + 1);
/*     */ 
/*     */     
/*  68 */     this.lexer.setString(str);
/*  69 */     this.lexer.reset();
/*     */ 
/*     */     
/*  72 */     int pos = token.getStartOffset();
/*  73 */     int ctx = token.getContext();
/*  74 */     int lastCtx = ctx;
/*  75 */     while (pos < p0) {
/*  76 */       pos = this.lexer.scan(ctx) + token.getStartOffset();
/*  77 */       lastCtx = ctx;
/*  78 */       ctx = this.lexer.getScanValue();
/*     */     } 
/*  80 */     int mark = p0;
/*     */     
/*  82 */     while (pos < p1) {
/*  83 */       if (lastCtx != ctx) {
/*     */         
/*  85 */         g.setColor(this.context.getSyntaxForeground(lastCtx));
/*  86 */         g.setFont(this.context.getSyntaxFont(lastCtx));
/*  87 */         Segment segment = getLineBuffer();
/*  88 */         doc.getText(mark, pos - mark, segment);
/*  89 */         x = Utilities.drawTabbedText(segment, x, y, g, this, mark);
/*  90 */         mark = pos;
/*     */       } 
/*     */       
/*  93 */       pos = this.lexer.scan(ctx) + token.getStartOffset();
/*  94 */       lastCtx = ctx;
/*  95 */       ctx = this.lexer.getScanValue();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     g.setColor(this.context.getSyntaxForeground(lastCtx));
/* 102 */     g.setFont(this.context.getSyntaxFont(lastCtx));
/* 103 */     Segment text = getLineBuffer();
/* 104 */     doc.getText(mark, p1 - mark, text);
/* 105 */     x = Utilities.drawTabbedText(text, x, y, g, this, mark);
/*     */     
/* 107 */     return x;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/xmleditor/XMLView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */