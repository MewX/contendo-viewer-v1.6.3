/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.net.URL;
/*    */ import org.apache.batik.script.ImportInfo;
/*    */ import org.apache.batik.script.Interpreter;
/*    */ import org.apache.batik.script.InterpreterFactory;
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
/*    */ public class RhinoInterpreterFactory
/*    */   implements InterpreterFactory
/*    */ {
/* 39 */   public static final String[] RHINO_MIMETYPES = new String[] { "application/ecmascript", "application/javascript", "text/ecmascript", "text/javascript" };
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
/*    */   public String[] getMimeTypes() {
/* 56 */     return RHINO_MIMETYPES;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Interpreter createInterpreter(URL documentURL, boolean svg12) {
/* 66 */     return createInterpreter(documentURL, svg12, null);
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
/*    */   public Interpreter createInterpreter(URL documentURL, boolean svg12, ImportInfo imports) {
/* 79 */     if (svg12) {
/* 80 */       return new SVG12RhinoInterpreter(documentURL, imports);
/*    */     }
/* 82 */     return new RhinoInterpreter(documentURL, imports);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/RhinoInterpreterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */