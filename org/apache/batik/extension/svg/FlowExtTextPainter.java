/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import java.text.AttributedCharacterIterator;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.batik.bridge.StrokingTextPainter;
/*    */ import org.apache.batik.bridge.TextNode;
/*    */ import org.apache.batik.bridge.TextPainter;
/*    */ import org.apache.batik.bridge.TextSpanLayout;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlowExtTextPainter
/*    */   extends StrokingTextPainter
/*    */ {
/* 44 */   protected static TextPainter singleton = (TextPainter)new FlowExtTextPainter();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static TextPainter getInstance() {
/* 50 */     return singleton;
/*    */   }
/*    */   
/*    */   public List getTextRuns(TextNode node, AttributedCharacterIterator aci) {
/* 54 */     List textRuns = node.getTextRuns();
/* 55 */     if (textRuns != null) {
/* 56 */       return textRuns;
/*    */     }
/*    */     
/* 59 */     AttributedCharacterIterator[] chunkACIs = getTextChunkACIs(aci);
/* 60 */     textRuns = computeTextRuns(node, aci, chunkACIs);
/*    */     
/* 62 */     aci.first();
/* 63 */     List rgns = (List)aci.getAttribute(FLOW_REGIONS);
/*    */     
/* 65 */     if (rgns != null) {
/* 66 */       Iterator<StrokingTextPainter.TextRun> i = textRuns.iterator();
/* 67 */       List<List> chunkLayouts = new ArrayList();
/* 68 */       StrokingTextPainter.TextRun tr = i.next();
/* 69 */       List<TextSpanLayout> layouts = new ArrayList();
/* 70 */       chunkLayouts.add(layouts);
/* 71 */       layouts.add(tr.getLayout());
/* 72 */       while (i.hasNext()) {
/* 73 */         tr = i.next();
/* 74 */         if (tr.isFirstRunInChunk()) {
/* 75 */           layouts = new ArrayList<TextSpanLayout>();
/* 76 */           chunkLayouts.add(layouts);
/*    */         } 
/* 78 */         layouts.add(tr.getLayout());
/*    */       } 
/*    */       
/* 81 */       FlowExtGlyphLayout.textWrapTextChunk(chunkACIs, chunkLayouts, rgns);
/*    */     } 
/*    */ 
/*    */     
/* 85 */     node.setTextRuns(textRuns);
/* 86 */     return textRuns;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/FlowExtTextPainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */