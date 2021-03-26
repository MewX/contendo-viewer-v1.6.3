/*     */ package org.apache.xmlgraphics.ps.dsc.tools;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.xmlgraphics.ps.PSGenerator;
/*     */ import org.apache.xmlgraphics.ps.dsc.DSCException;
/*     */ import org.apache.xmlgraphics.ps.dsc.DSCFilter;
/*     */ import org.apache.xmlgraphics.ps.dsc.DSCListener;
/*     */ import org.apache.xmlgraphics.ps.dsc.DSCParser;
/*     */ import org.apache.xmlgraphics.ps.dsc.DSCParserConstants;
/*     */ import org.apache.xmlgraphics.ps.dsc.DefaultNestedDocumentHandler;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCComment;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentPage;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentPages;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCEvent;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCHeaderComment;
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
/*     */ public final class PageExtractor
/*     */   implements DSCParserConstants
/*     */ {
/*     */   public static void extractPages(InputStream in, OutputStream out, int from, int to) throws IOException, DSCException {
/*  59 */     if (from <= 0) {
/*  60 */       throw new IllegalArgumentException("'from' page number must be 1 or higher");
/*     */     }
/*  62 */     if (to < from) {
/*  63 */       throw new IllegalArgumentException("'to' page number must be equal or larger than the 'from' page number");
/*     */     }
/*     */ 
/*     */     
/*  67 */     DSCParser parser = new DSCParser(in);
/*  68 */     PSGenerator gen = new PSGenerator(out);
/*  69 */     parser.addListener((DSCListener)new DefaultNestedDocumentHandler(gen));
/*  70 */     int pageCount = 0;
/*     */ 
/*     */     
/*  73 */     DSCHeaderComment header = DSCTools.checkAndSkipDSC30Header(parser);
/*  74 */     header.generate(gen);
/*     */     
/*  76 */     DSCCommentPages pages = new DSCCommentPages(to - from + 1);
/*  77 */     pages.generate(gen);
/*     */     
/*  79 */     parser.setFilter(new DSCFilter() {
/*     */           public boolean accept(DSCEvent event) {
/*  81 */             if (event.isDSCComment())
/*     */             {
/*  83 */               return !event.asDSCComment().getName().equals("Pages");
/*     */             }
/*  85 */             return true;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  91 */     DSCComment pageOrTrailer = parser.nextDSCComment("Page", gen);
/*  92 */     if (pageOrTrailer == null) {
/*  93 */       throw new DSCException("Page expected, but none found");
/*     */     }
/*  95 */     parser.setFilter(null);
/*     */ 
/*     */     
/*     */     while (true) {
/*  99 */       DSCCommentPage page = (DSCCommentPage)pageOrTrailer;
/* 100 */       boolean validPage = (page.getPagePosition() >= from && page.getPagePosition() <= to);
/* 101 */       if (validPage) {
/* 102 */         page.setPagePosition(page.getPagePosition() - from + 1);
/* 103 */         page.generate(gen);
/* 104 */         pageCount++;
/*     */       } 
/* 106 */       pageOrTrailer = DSCTools.nextPageOrTrailer(parser, validPage ? gen : null);
/* 107 */       if (pageOrTrailer == null)
/* 108 */         throw new DSCException("File is not DSC-compliant: Unexpected end of file"); 
/* 109 */       if (!"Page".equals(pageOrTrailer.getName())) {
/* 110 */         pageOrTrailer.generate(gen);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 116 */         while (parser.hasNext()) {
/* 117 */           DSCEvent event = parser.nextEvent();
/* 118 */           event.generate(gen);
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/tools/PageExtractor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */