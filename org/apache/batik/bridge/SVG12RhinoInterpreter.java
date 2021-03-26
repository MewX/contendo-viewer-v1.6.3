/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.net.URL;
/*    */ import org.apache.batik.script.ImportInfo;
/*    */ import org.mozilla.javascript.Context;
/*    */ import org.mozilla.javascript.Scriptable;
/*    */ import org.mozilla.javascript.ScriptableObject;
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
/*    */ public class SVG12RhinoInterpreter
/*    */   extends RhinoInterpreter
/*    */ {
/*    */   public SVG12RhinoInterpreter(URL documentURL) {
/* 41 */     super(documentURL);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVG12RhinoInterpreter(URL documentURL, ImportInfo imports) {
/* 49 */     super(documentURL, imports);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void defineGlobalWrapperClass(Scriptable global) {
/*    */     try {
/* 57 */       ScriptableObject.defineClass(global, GlobalWrapper.class);
/* 58 */     } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ScriptableObject createGlobalObject(Context ctx) {
/* 67 */     return (ScriptableObject)new GlobalWrapper(ctx);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVG12RhinoInterpreter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */