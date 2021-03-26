/*    */ package org.apache.xpath.compiler;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.functions.Function;
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
/*    */ public class FuncLoader
/*    */ {
/*    */   private int m_funcID;
/*    */   private String m_funcName;
/*    */   
/*    */   public String getName() {
/* 50 */     return this.m_funcName;
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
/*    */ 
/*    */ 
/*    */   
/*    */   public FuncLoader(String funcName, int funcID) {
/* 68 */     this.m_funcID = funcID;
/* 69 */     this.m_funcName = funcName;
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
/*    */   public Function getFunction() throws TransformerException {
/*    */     
/* 84 */     try { String className = this.m_funcName;
/* 85 */       if (className.indexOf(".") < 0) {
/* 86 */         className = "org.apache.xpath.functions." + className;
/*    */       }
/*    */       
/* 89 */       return (Function)ObjectFactory.newInstance(className, ObjectFactory.findClassLoader(), true); } catch (ConfigurationError e)
/*    */     
/*    */     { 
/*    */ 
/*    */       
/* 94 */       throw new TransformerException(e.getException()); }
/*    */   
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/compiler/FuncLoader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */