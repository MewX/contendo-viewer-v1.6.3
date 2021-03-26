/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.objects.XObject;
/*    */ import org.apache.xpath.objects.XString;
/*    */ import org.apache.xpath.res.XPATHMessages;
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
/*    */ public class FuncConcat
/*    */   extends FunctionMultiArgs
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 44 */     StringBuffer sb = new StringBuffer();
/*    */ 
/*    */     
/* 47 */     sb.append(this.m_arg0.execute(xctxt).str());
/* 48 */     sb.append(this.m_arg1.execute(xctxt).str());
/*    */     
/* 50 */     if (null != this.m_arg2) {
/* 51 */       sb.append(this.m_arg2.execute(xctxt).str());
/*    */     }
/* 53 */     if (null != this.m_args)
/*    */     {
/* 55 */       for (int i = 0; i < this.m_args.length; i++)
/*    */       {
/* 57 */         sb.append(this.m_args[i].execute(xctxt).str());
/*    */       }
/*    */     }
/*    */     
/* 61 */     return (XObject)new XString(sb.toString());
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
/*    */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {
/* 74 */     if (argNum < 2) {
/* 75 */       reportWrongNumberArgs();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 85 */     throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("gtone", null));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncConcat.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */