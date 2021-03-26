/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.serializer.NamespaceMappings;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.BoolStack;
/*     */ import org.apache.xml.utils.IntStack;
/*     */ import org.apache.xml.utils.NodeVector;
/*     */ import org.apache.xml.utils.ObjectStack;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.VariableStack;
/*     */ import org.apache.xpath.XPathContext;
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
/*     */ class TransformSnapshotImpl
/*     */   implements TransformSnapshot
/*     */ {
/*     */   private VariableStack m_variableStacks;
/*     */   private IntStack m_currentNodes;
/*     */   private IntStack m_currentExpressionNodes;
/*     */   private Stack m_contextNodeLists;
/*     */   private DTMIterator m_contextNodeList;
/*     */   private Stack m_axesIteratorStack;
/*     */   private BoolStack m_currentTemplateRuleIsNull;
/*     */   private ObjectStack m_currentTemplateElements;
/*     */   private Stack m_currentMatchTemplates;
/*     */   private NodeVector m_currentMatchNodes;
/*     */   private CountersTable m_countersTable;
/*     */   private Stack m_attrSetStack;
/*     */   boolean m_nsContextPushed;
/*     */   private NamespaceMappings m_nsSupport;
/*     */   
/*     */   TransformSnapshotImpl(TransformerImpl transformer) {
/*     */     
/* 150 */     try { SerializationHandler rtf = transformer.getResultTreeHandler();
/*     */ 
/*     */ 
/*     */       
/* 154 */       this.m_nsSupport = (NamespaceMappings)rtf.getNamespaceMappings().clone();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 159 */       XPathContext xpc = transformer.getXPathContext();
/*     */       
/* 161 */       this.m_variableStacks = (VariableStack)xpc.getVarStack().clone();
/* 162 */       this.m_currentNodes = (IntStack)xpc.getCurrentNodeStack().clone();
/* 163 */       this.m_currentExpressionNodes = (IntStack)xpc.getCurrentExpressionNodeStack().clone();
/*     */       
/* 165 */       this.m_contextNodeLists = (Stack)xpc.getContextNodeListsStack().clone();
/*     */       
/* 167 */       if (!this.m_contextNodeLists.empty()) {
/* 168 */         this.m_contextNodeList = (DTMIterator)xpc.getContextNodeList().clone();
/*     */       }
/*     */       
/* 171 */       this.m_axesIteratorStack = (Stack)xpc.getAxesIteratorStackStacks().clone();
/* 172 */       this.m_currentTemplateRuleIsNull = (BoolStack)transformer.m_currentTemplateRuleIsNull.clone();
/*     */       
/* 174 */       this.m_currentTemplateElements = (ObjectStack)transformer.m_currentTemplateElements.clone();
/*     */       
/* 176 */       this.m_currentMatchTemplates = (Stack)transformer.m_currentMatchTemplates.clone();
/*     */       
/* 178 */       this.m_currentMatchNodes = (NodeVector)transformer.m_currentMatchedNodes.clone();
/*     */       
/* 180 */       this.m_countersTable = (CountersTable)transformer.getCountersTable().clone();
/*     */ 
/*     */       
/* 183 */       if (transformer.m_attrSetStack != null)
/* 184 */         this.m_attrSetStack = (Stack)transformer.m_attrSetStack.clone();  } catch (CloneNotSupportedException cnse)
/*     */     
/*     */     { 
/*     */       
/* 188 */       throw new WrappedRuntimeException(cnse); }
/*     */   
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
/*     */ 
/*     */ 
/*     */   
/*     */   void apply(TransformerImpl transformer) {
/*     */     
/* 208 */     try { SerializationHandler rtf = transformer.getResultTreeHandler();
/*     */       
/* 210 */       if (rtf != null)
/*     */       {
/*     */         
/* 213 */         rtf.setNamespaceMappings((NamespaceMappings)this.m_nsSupport.clone());
/*     */       }
/*     */       
/* 216 */       XPathContext xpc = transformer.getXPathContext();
/*     */       
/* 218 */       xpc.setVarStack((VariableStack)this.m_variableStacks.clone());
/* 219 */       xpc.setCurrentNodeStack((IntStack)this.m_currentNodes.clone());
/* 220 */       xpc.setCurrentExpressionNodeStack((IntStack)this.m_currentExpressionNodes.clone());
/*     */       
/* 222 */       xpc.setContextNodeListsStack((Stack)this.m_contextNodeLists.clone());
/*     */       
/* 224 */       if (this.m_contextNodeList != null) {
/* 225 */         xpc.pushContextNodeList((DTMIterator)this.m_contextNodeList.clone());
/*     */       }
/* 227 */       xpc.setAxesIteratorStackStacks((Stack)this.m_axesIteratorStack.clone());
/*     */       
/* 229 */       transformer.m_currentTemplateRuleIsNull = (BoolStack)this.m_currentTemplateRuleIsNull.clone();
/*     */       
/* 231 */       transformer.m_currentTemplateElements = (ObjectStack)this.m_currentTemplateElements.clone();
/*     */       
/* 233 */       transformer.m_currentMatchTemplates = (Stack)this.m_currentMatchTemplates.clone();
/*     */       
/* 235 */       transformer.m_currentMatchedNodes = (NodeVector)this.m_currentMatchNodes.clone();
/*     */       
/* 237 */       transformer.m_countersTable = (CountersTable)this.m_countersTable.clone();
/*     */       
/* 239 */       if (this.m_attrSetStack != null)
/* 240 */         transformer.m_attrSetStack = (Stack)this.m_attrSetStack.clone();  } catch (CloneNotSupportedException cnse)
/*     */     
/*     */     { 
/*     */       
/* 244 */       throw new WrappedRuntimeException(cnse); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/TransformSnapshotImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */