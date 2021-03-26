/*    */ package org.apache.batik.apps.rasterizer;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.batik.transcoder.Transcoder;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultSVGConverterController
/*    */   implements SVGConverterController
/*    */ {
/*    */   public boolean proceedWithComputedTask(Transcoder transcoder, Map hints, List sources, List dest) {
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean proceedWithSourceTranscoding(SVGConverterSource source, File dest) {
/* 61 */     System.out.println("About to transcoder source of type: " + source.getClass().getName());
/* 62 */     return true;
/*    */   }
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
/*    */   public boolean proceedOnSourceTranscodingFailure(SVGConverterSource source, File dest, String errorCode) {
/* 77 */     return true;
/*    */   }
/*    */   
/*    */   public void onSourceTranscodingSuccess(SVGConverterSource source, File dest) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/DefaultSVGConverterController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */