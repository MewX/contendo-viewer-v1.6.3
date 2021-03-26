/*     */ package org.apache.batik.script.rhino;
/*     */ 
/*     */ import org.mozilla.javascript.ClassShutter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RhinoClassShutter
/*     */   implements ClassShutter
/*     */ {
/*     */   public boolean visibleToScripts(String fullClassName) {
/*  59 */     if (fullClassName.startsWith("org.mozilla.javascript")) {
/*  60 */       return false;
/*     */     }
/*  62 */     if (fullClassName.startsWith("org.apache.batik.")) {
/*     */       
/*  64 */       String batikPkg = fullClassName.substring(17);
/*     */ 
/*     */       
/*  67 */       if (batikPkg.startsWith("script")) {
/*  68 */         return false;
/*     */       }
/*     */       
/*  71 */       if (batikPkg.startsWith("apps")) {
/*  72 */         return false;
/*     */       }
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
/*  84 */       if (batikPkg.startsWith("bridge.")) {
/*  85 */         String batikBridgeClass = batikPkg.substring(7);
/*  86 */         if (batikBridgeClass.startsWith("ScriptingEnvironment")) {
/*  87 */           if (batikBridgeClass.startsWith("$Window$", 20)) {
/*  88 */             String c = batikBridgeClass.substring(28);
/*  89 */             if (c.equals("IntervalScriptTimerTask") || c.equals("IntervalRunnableTimerTask") || c.equals("TimeoutScriptTimerTask") || c.equals("TimeoutRunnableTimerTask"))
/*     */             {
/*     */ 
/*     */               
/*  93 */               return true;
/*     */             }
/*     */           } 
/*  96 */           return false;
/*     */         } 
/*  98 */         if (batikBridgeClass.startsWith("BaseScriptingEnvironment")) {
/*  99 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/rhino/RhinoClassShutter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */