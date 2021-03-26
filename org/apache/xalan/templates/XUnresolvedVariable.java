/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xpath.VariableStack;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
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
/*     */ public class XUnresolvedVariable
/*     */   extends XObject
/*     */ {
/*     */   private transient int m_context;
/*     */   private transient TransformerImpl m_transformer;
/*  44 */   private transient int m_varStackPos = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private transient int m_varStackContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_isGlobal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private transient boolean m_doneEval = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XUnresolvedVariable(ElemVariable obj, int sourceNode, TransformerImpl transformer, int varStackPos, int varStackContext, boolean isGlobal) {
/*  79 */     super(obj);
/*  80 */     this.m_context = sourceNode;
/*  81 */     this.m_transformer = transformer;
/*     */ 
/*     */ 
/*     */     
/*  85 */     this.m_varStackPos = varStackPos;
/*     */ 
/*     */     
/*  88 */     this.m_varStackContext = varStackContext;
/*     */     
/*  90 */     this.m_isGlobal = isGlobal;
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 104 */     if (!this.m_doneEval)
/*     */     {
/* 106 */       this.m_transformer.getMsgMgr().error(xctxt.getSAXLocator(), "ER_REFERENCING_ITSELF", new Object[] { ((ElemVariable)object()).getName().getLocalName() });
/*     */     }
/*     */ 
/*     */     
/* 110 */     VariableStack vars = xctxt.getVarStack();
/*     */ 
/*     */     
/* 113 */     int currentFrame = vars.getStackFrame();
/*     */ 
/*     */ 
/*     */     
/* 117 */     ElemVariable velem = (ElemVariable)this.m_obj;
/*     */     
/*     */     try {
/* 120 */       this.m_doneEval = false;
/* 121 */       if (-1 != velem.m_frameSize)
/* 122 */         vars.link(velem.m_frameSize); 
/* 123 */       XObject var = velem.getValue(this.m_transformer, this.m_context);
/* 124 */       this.m_doneEval = true;
/* 125 */       return var;
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 132 */       if (-1 != velem.m_frameSize) {
/* 133 */         vars.unlink(currentFrame);
/*     */       }
/*     */     } 
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
/*     */   public void setVarStackPos(int top) {
/* 147 */     this.m_varStackPos = top;
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
/*     */   public void setVarStackContext(int bottom) {
/* 159 */     this.m_varStackContext = bottom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 169 */     return 600;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeString() {
/* 180 */     return "XUnresolvedVariable (" + object().getClass().getName() + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/XUnresolvedVariable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */