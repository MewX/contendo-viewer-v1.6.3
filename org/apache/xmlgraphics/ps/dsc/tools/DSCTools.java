/*     */ package org.apache.xmlgraphics.ps.dsc.tools;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.xmlgraphics.ps.PSGenerator;
/*     */ import org.apache.xmlgraphics.ps.dsc.DSCException;
/*     */ import org.apache.xmlgraphics.ps.dsc.DSCParser;
/*     */ import org.apache.xmlgraphics.ps.dsc.DSCParserConstants;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCComment;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCEvent;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCHeaderComment;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.PostScriptComment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DSCTools
/*     */   implements DSCParserConstants
/*     */ {
/*     */   public static boolean headerCommentsEndHere(DSCEvent event) {
/*     */     DSCComment comment;
/*     */     String s;
/*     */     char c;
/*  50 */     switch (event.getEventType()) {
/*     */       case 1:
/*  52 */         comment = event.asDSCComment();
/*  53 */         return comment.getName().equals("EndComments");
/*     */       case 2:
/*  55 */         assert event instanceof PostScriptComment;
/*  56 */         s = ((PostScriptComment)event).getComment();
/*  57 */         if (s == null || s.length() == 0) {
/*  58 */           return true;
/*     */         }
/*  60 */         c = s.charAt(0);
/*  61 */         return ("\n\t ".indexOf(c) >= 0);
/*     */     } 
/*     */     
/*  64 */     return true;
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
/*     */   public static DSCHeaderComment checkAndSkipDSC30Header(DSCParser parser) throws DSCException, IOException {
/*  77 */     if (!parser.hasNext()) {
/*  78 */       throw new DSCException("File has no content");
/*     */     }
/*  80 */     DSCEvent event = parser.nextEvent();
/*  81 */     if (event.getEventType() == 0) {
/*  82 */       DSCHeaderComment header = (DSCHeaderComment)event;
/*  83 */       if (!header.isPSAdobe30()) {
/*  84 */         throw new DSCException("PostScript file does not start with '%!PS-Adobe-3.0'");
/*     */       }
/*     */       
/*  87 */       return header;
/*     */     } 
/*  89 */     throw new DSCException("PostScript file does not start with '%!PS-Adobe-3.0'");
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
/*     */   public static DSCComment nextPageOrTrailer(DSCParser parser, PSGenerator gen) throws IOException, DSCException {
/* 104 */     while (parser.hasNext()) {
/* 105 */       DSCEvent event = parser.nextEvent();
/* 106 */       if (event.getEventType() == 1) {
/* 107 */         DSCComment comment = event.asDSCComment();
/* 108 */         if ("Page".equals(comment.getName()))
/* 109 */           return comment; 
/* 110 */         if ("Trailer".equals(comment.getName())) {
/* 111 */           return comment;
/*     */         }
/* 113 */       } else if (event.getEventType() == 4) {
/*     */         
/* 115 */         return event.asDSCComment();
/*     */       } 
/* 117 */       if (gen != null) {
/* 118 */         event.generate(gen);
/*     */       }
/*     */     } 
/* 121 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/tools/DSCTools.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */