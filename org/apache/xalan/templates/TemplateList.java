/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.patterns.NodeTest;
/*     */ import org.apache.xpath.patterns.StepPattern;
/*     */ import org.apache.xpath.patterns.UnionPattern;
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
/*     */ public class TemplateList
/*     */   implements Serializable
/*     */ {
/*     */   public void setTemplate(ElemTemplate template) {
/*  63 */     XPath matchXPath = template.getMatch();
/*     */     
/*  65 */     if (null == template.getName() && null == matchXPath)
/*     */     {
/*  67 */       template.error("ER_NEED_NAME_OR_MATCH_ATTRIB", new Object[] { "xsl:template" });
/*     */     }
/*     */ 
/*     */     
/*  71 */     if (null != template.getName()) {
/*     */       
/*  73 */       ElemTemplate existingTemplate = (ElemTemplate)this.m_namedTemplates.get(template.getName());
/*  74 */       if (null == existingTemplate) {
/*     */         
/*  76 */         this.m_namedTemplates.put(template.getName(), template);
/*     */       }
/*     */       else {
/*     */         
/*  80 */         int existingPrecedence = existingTemplate.getStylesheetComposed().getImportCountComposed();
/*     */         
/*  82 */         int newPrecedence = template.getStylesheetComposed().getImportCountComposed();
/*  83 */         if (newPrecedence > existingPrecedence) {
/*     */ 
/*     */           
/*  86 */           this.m_namedTemplates.put(template.getName(), template);
/*     */         }
/*  88 */         else if (newPrecedence == existingPrecedence) {
/*  89 */           template.error("ER_DUPLICATE_NAMED_TEMPLATE", new Object[] { template.getName() });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  96 */     if (null != matchXPath) {
/*     */       
/*  98 */       Expression matchExpr = matchXPath.getExpression();
/*     */       
/* 100 */       if (matchExpr instanceof StepPattern) {
/*     */         
/* 102 */         insertPatternInTable((StepPattern)matchExpr, template);
/*     */       }
/* 104 */       else if (matchExpr instanceof UnionPattern) {
/*     */         
/* 106 */         UnionPattern upat = (UnionPattern)matchExpr;
/* 107 */         StepPattern[] pats = upat.getPatterns();
/* 108 */         int n = pats.length;
/*     */         
/* 110 */         for (int i = 0; i < n; i++)
/*     */         {
/* 112 */           insertPatternInTable(pats[i], template);
/*     */         }
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
/*     */   static boolean DEBUG = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void dumpAssociationTables() {
/* 133 */     Enumeration associations = this.m_patternTable.elements();
/*     */     
/* 135 */     while (associations.hasMoreElements()) {
/*     */       
/* 137 */       TemplateSubPatternAssociation templateSubPatternAssociation = associations.nextElement();
/*     */ 
/*     */       
/* 140 */       while (null != templateSubPatternAssociation) {
/*     */         
/* 142 */         System.out.print("(" + templateSubPatternAssociation.getTargetString() + ", " + templateSubPatternAssociation.getPattern() + ")");
/*     */ 
/*     */         
/* 145 */         templateSubPatternAssociation = templateSubPatternAssociation.getNext();
/*     */       } 
/*     */       
/* 148 */       System.out.println("\n.....");
/*     */     } 
/*     */     
/* 151 */     TemplateSubPatternAssociation head = this.m_wildCardPatterns;
/*     */     
/* 153 */     System.out.print("wild card list: ");
/*     */     
/* 155 */     while (null != head) {
/*     */       
/* 157 */       System.out.print("(" + head.getTargetString() + ", " + head.getPattern() + ")");
/*     */ 
/*     */       
/* 160 */       head = head.getNext();
/*     */     } 
/*     */     
/* 163 */     System.out.println("\n.....");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compose(StylesheetRoot sroot) {
/* 173 */     if (DEBUG) {
/*     */       
/* 175 */       System.out.println("Before wildcard insert...");
/* 176 */       dumpAssociationTables();
/*     */     } 
/*     */     
/* 179 */     if (null != this.m_wildCardPatterns) {
/*     */       
/* 181 */       Enumeration associations = this.m_patternTable.elements();
/*     */       
/* 183 */       while (associations.hasMoreElements()) {
/*     */         
/* 185 */         TemplateSubPatternAssociation head = associations.nextElement();
/*     */         
/* 187 */         TemplateSubPatternAssociation wild = this.m_wildCardPatterns;
/*     */         
/* 189 */         while (null != wild) {
/*     */ 
/*     */ 
/*     */           
/* 193 */           try { head = insertAssociationIntoList(head, (TemplateSubPatternAssociation)wild.clone(), true); } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 198 */           wild = wild.getNext();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 203 */     if (DEBUG) {
/*     */       
/* 205 */       System.out.println("After wildcard insert...");
/* 206 */       dumpAssociationTables();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TemplateSubPatternAssociation insertAssociationIntoList(TemplateSubPatternAssociation head, TemplateSubPatternAssociation item, boolean isWildCardInsert) {
/*     */     TemplateSubPatternAssociation next;
/*     */     boolean bool;
/* 230 */     double priority = getPriorityOrScore(item);
/*     */     
/* 232 */     int importLevel = item.getImportLevel();
/* 233 */     int docOrder = item.getDocOrderPos();
/* 234 */     TemplateSubPatternAssociation insertPoint = head;
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
/*     */     while (true) {
/* 252 */       next = insertPoint.getNext();
/* 253 */       if (null == next) {
/*     */         break;
/*     */       }
/*     */       
/* 257 */       double workPriority = getPriorityOrScore(next);
/* 258 */       if (importLevel > next.getImportLevel())
/*     */         break; 
/* 260 */       if (importLevel < next.getImportLevel()) {
/* 261 */         insertPoint = next; continue;
/* 262 */       }  if (priority > workPriority)
/*     */         break; 
/* 264 */       if (priority < workPriority) {
/* 265 */         insertPoint = next; continue;
/* 266 */       }  if (docOrder >= next.getDocOrderPos()) {
/*     */         break;
/*     */       }
/* 269 */       insertPoint = next;
/*     */     } 
/*     */ 
/*     */     
/* 273 */     if (null == next || insertPoint == head) {
/*     */       
/* 275 */       double d = getPriorityOrScore(insertPoint);
/* 276 */       if (importLevel > insertPoint.getImportLevel()) {
/* 277 */         bool = true;
/* 278 */       } else if (importLevel < insertPoint.getImportLevel()) {
/* 279 */         bool = false;
/* 280 */       } else if (priority > d) {
/* 281 */         bool = true;
/* 282 */       } else if (priority < d) {
/* 283 */         bool = false;
/* 284 */       } else if (docOrder >= insertPoint.getDocOrderPos()) {
/* 285 */         bool = true;
/*     */       } else {
/* 287 */         bool = false;
/*     */       } 
/*     */     } else {
/* 290 */       bool = false;
/*     */     } 
/*     */ 
/*     */     
/* 294 */     if (isWildCardInsert) {
/*     */       
/* 296 */       if (bool) {
/*     */         
/* 298 */         item.setNext(insertPoint);
/*     */         
/* 300 */         String key = insertPoint.getTargetString();
/*     */         
/* 302 */         item.setTargetString(key);
/* 303 */         putHead(key, item);
/* 304 */         return item;
/*     */       } 
/*     */ 
/*     */       
/* 308 */       item.setNext(next);
/* 309 */       insertPoint.setNext(item);
/* 310 */       return head;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 315 */     if (bool) {
/*     */       
/* 317 */       item.setNext(insertPoint);
/*     */       
/* 319 */       if (insertPoint.isWild() || item.isWild()) {
/* 320 */         this.m_wildCardPatterns = item;
/*     */       } else {
/* 322 */         putHead(item.getTargetString(), item);
/* 323 */       }  return item;
/*     */     } 
/*     */ 
/*     */     
/* 327 */     item.setNext(next);
/* 328 */     insertPoint.setNext(item);
/* 329 */     return head;
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
/*     */   private void insertPatternInTable(StepPattern pattern, ElemTemplate template) {
/* 343 */     String target = pattern.getTargetString();
/*     */     
/* 345 */     if (null != target) {
/*     */       
/* 347 */       String pstring = template.getMatch().getPatternString();
/* 348 */       TemplateSubPatternAssociation association = new TemplateSubPatternAssociation(template, pattern, pstring);
/*     */ 
/*     */ 
/*     */       
/* 352 */       boolean isWildCard = association.isWild();
/* 353 */       TemplateSubPatternAssociation head = isWildCard ? this.m_wildCardPatterns : getHead(target);
/*     */ 
/*     */ 
/*     */       
/* 357 */       if (null == head) {
/*     */         
/* 359 */         if (isWildCard) {
/* 360 */           this.m_wildCardPatterns = association;
/*     */         } else {
/* 362 */           putHead(target, association);
/*     */         } 
/*     */       } else {
/*     */         
/* 366 */         insertAssociationIntoList(head, association, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double getPriorityOrScore(TemplateSubPatternAssociation matchPat) {
/* 389 */     double priority = matchPat.getTemplate().getPriority();
/*     */     
/* 391 */     if (priority == Double.NEGATIVE_INFINITY) {
/*     */       
/* 393 */       StepPattern stepPattern = matchPat.getStepPattern();
/*     */       
/* 395 */       if (stepPattern instanceof NodeTest)
/*     */       {
/* 397 */         return ((NodeTest)stepPattern).getDefaultScore();
/*     */       }
/*     */     } 
/*     */     
/* 401 */     return priority;
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
/*     */   public ElemTemplate getTemplate(QName qname) {
/* 413 */     return (ElemTemplate)this.m_namedTemplates.get(qname);
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
/*     */   public TemplateSubPatternAssociation getHead(XPathContext xctxt, int targetNode, DTM dtm) {
/*     */     TemplateSubPatternAssociation head;
/* 430 */     short targetNodeType = dtm.getNodeType(targetNode);
/*     */ 
/*     */     
/* 433 */     switch (targetNodeType) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 437 */         head = (TemplateSubPatternAssociation)this.m_patternTable.get(dtm.getLocalName(targetNode));
/*     */         break;
/*     */       
/*     */       case 3:
/*     */       case 4:
/* 442 */         head = this.m_textPatterns;
/*     */         break;
/*     */       case 5:
/*     */       case 6:
/* 446 */         head = (TemplateSubPatternAssociation)this.m_patternTable.get(dtm.getNodeName(targetNode));
/*     */         break;
/*     */       
/*     */       case 7:
/* 450 */         head = (TemplateSubPatternAssociation)this.m_patternTable.get(dtm.getLocalName(targetNode));
/*     */         break;
/*     */       
/*     */       case 8:
/* 454 */         head = this.m_commentPatterns;
/*     */         break;
/*     */       case 9:
/*     */       case 11:
/* 458 */         head = this.m_docPatterns;
/*     */         break;
/*     */       
/*     */       default:
/* 462 */         head = (TemplateSubPatternAssociation)this.m_patternTable.get(dtm.getNodeName(targetNode));
/*     */         break;
/*     */     } 
/*     */     
/* 466 */     return (null == head) ? this.m_wildCardPatterns : head;
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
/*     */   public ElemTemplate getTemplateFast(XPathContext xctxt, int targetNode, int expTypeID, QName mode, int maxImportLevel, boolean quietConflictWarnings, DTM dtm) throws TransformerException {
/*     */     TemplateSubPatternAssociation head;
/* 501 */     switch (dtm.getNodeType(targetNode)) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 505 */         head = (TemplateSubPatternAssociation)this.m_patternTable.get(dtm.getLocalNameFromExpandedNameID(expTypeID));
/*     */         break;
/*     */       
/*     */       case 3:
/*     */       case 4:
/* 510 */         head = this.m_textPatterns;
/*     */         break;
/*     */       case 5:
/*     */       case 6:
/* 514 */         head = (TemplateSubPatternAssociation)this.m_patternTable.get(dtm.getNodeName(targetNode));
/*     */         break;
/*     */       
/*     */       case 7:
/* 518 */         head = (TemplateSubPatternAssociation)this.m_patternTable.get(dtm.getLocalName(targetNode));
/*     */         break;
/*     */       
/*     */       case 8:
/* 522 */         head = this.m_commentPatterns;
/*     */         break;
/*     */       case 9:
/*     */       case 11:
/* 526 */         head = this.m_docPatterns;
/*     */         break;
/*     */       
/*     */       default:
/* 530 */         head = (TemplateSubPatternAssociation)this.m_patternTable.get(dtm.getNodeName(targetNode));
/*     */         break;
/*     */     } 
/*     */     
/* 534 */     if (null == head) {
/*     */       
/* 536 */       head = this.m_wildCardPatterns;
/* 537 */       if (null == head) {
/* 538 */         return null;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 544 */     xctxt.pushNamespaceContextNull();
/*     */ 
/*     */     
/*     */     try {
/*     */       while (true) {
/* 549 */         if (maxImportLevel <= -1 || head.getImportLevel() <= maxImportLevel) {
/*     */ 
/*     */ 
/*     */           
/* 553 */           ElemTemplate template = head.getTemplate();
/* 554 */           xctxt.setNamespaceContext(template);
/*     */           
/* 556 */           if (head.m_stepPattern.execute(xctxt, targetNode, dtm, expTypeID) != NodeTest.SCORE_NONE && head.matchMode(mode)) {
/*     */ 
/*     */             
/* 559 */             if (quietConflictWarnings) {
/* 560 */               checkConflicts(head, xctxt, targetNode, mode);
/*     */             }
/* 562 */             return template;
/*     */           } 
/*     */         } 
/* 565 */         if (null == (head = head.getNext()))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 572 */           return null;
/*     */         }
/*     */       } 
/*     */     } finally {
/*     */       xctxt.popNamespaceContext();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElemTemplate getTemplate(XPathContext xctxt, int targetNode, QName mode, boolean quietConflictWarnings, DTM dtm) throws TransformerException {
/* 598 */     TemplateSubPatternAssociation head = getHead(xctxt, targetNode, dtm);
/*     */     
/* 600 */     if (null != head) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 605 */       xctxt.pushNamespaceContextNull();
/* 606 */       xctxt.pushCurrentNodeAndExpression(targetNode, targetNode);
/*     */ 
/*     */       
/*     */       try {
/*     */         while (true) {
/* 611 */           ElemTemplate template = head.getTemplate();
/* 612 */           xctxt.setNamespaceContext(template);
/*     */           
/* 614 */           if (head.m_stepPattern.execute(xctxt, targetNode) != NodeTest.SCORE_NONE && head.matchMode(mode)) {
/*     */ 
/*     */             
/* 617 */             if (quietConflictWarnings) {
/* 618 */               checkConflicts(head, xctxt, targetNode, mode);
/*     */             }
/* 620 */             return template;
/*     */           } 
/*     */           
/* 623 */           if (null == (head = head.getNext()))
/*     */             // Byte code: goto -> 126 
/*     */         } 
/*     */       } finally {
/* 627 */         xctxt.popCurrentNodeAndExpression();
/* 628 */         xctxt.popNamespaceContext();
/*     */       } 
/*     */     } 
/*     */     
/* 632 */     return null;
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
/*     */   public ElemTemplate getTemplate(XPathContext xctxt, int targetNode, QName mode, int maxImportLevel, int endImportLevel, boolean quietConflictWarnings, DTM dtm) throws TransformerException {
/* 663 */     TemplateSubPatternAssociation head = getHead(xctxt, targetNode, dtm);
/*     */     
/* 665 */     if (null != head) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 670 */       xctxt.pushNamespaceContextNull();
/* 671 */       xctxt.pushCurrentNodeAndExpression(targetNode, targetNode);
/*     */ 
/*     */       
/*     */       try {
/*     */         while (true) {
/* 676 */           if (maxImportLevel <= -1 || head.getImportLevel() <= maxImportLevel) {
/*     */ 
/*     */ 
/*     */             
/* 680 */             if (head.getImportLevel() <= maxImportLevel - endImportLevel)
/* 681 */               return null; 
/* 682 */             ElemTemplate template = head.getTemplate();
/* 683 */             xctxt.setNamespaceContext(template);
/*     */             
/* 685 */             if (head.m_stepPattern.execute(xctxt, targetNode) != NodeTest.SCORE_NONE && head.matchMode(mode)) {
/*     */ 
/*     */               
/* 688 */               if (quietConflictWarnings) {
/* 689 */                 checkConflicts(head, xctxt, targetNode, mode);
/*     */               }
/* 691 */               return template;
/*     */             } 
/*     */           } 
/* 694 */           if (null == (head = head.getNext()))
/*     */             // Byte code: goto -> 167 
/*     */         } 
/*     */       } finally {
/* 698 */         xctxt.popCurrentNodeAndExpression();
/* 699 */         xctxt.popNamespaceContext();
/*     */       } 
/*     */     } 
/*     */     
/* 703 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TemplateWalker getWalker() {
/* 712 */     return new TemplateWalker();
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
/*     */   private void checkConflicts(TemplateSubPatternAssociation head, XPathContext xctxt, int targetNode, QName mode) {}
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
/*     */   private void addObjectIfNotFound(Object obj, Vector v) {
/* 739 */     int n = v.size();
/* 740 */     boolean addIt = true;
/*     */     
/* 742 */     for (int i = 0; i < n; i++) {
/*     */       
/* 744 */       if (v.elementAt(i) == obj) {
/*     */         
/* 746 */         addIt = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 752 */     if (addIt)
/*     */     {
/* 754 */       v.addElement(obj);
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
/* 765 */   private Hashtable m_namedTemplates = new Hashtable(89);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 774 */   private Hashtable m_patternTable = new Hashtable(89);
/*     */ 
/*     */ 
/*     */   
/* 778 */   private TemplateSubPatternAssociation m_wildCardPatterns = null;
/*     */ 
/*     */ 
/*     */   
/* 782 */   private TemplateSubPatternAssociation m_textPatterns = null;
/*     */ 
/*     */ 
/*     */   
/* 786 */   private TemplateSubPatternAssociation m_docPatterns = null;
/*     */ 
/*     */ 
/*     */   
/* 790 */   private TemplateSubPatternAssociation m_commentPatterns = null;
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
/*     */   private Hashtable getNamedTemplates() {
/* 803 */     return this.m_namedTemplates;
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
/*     */   private void setNamedTemplates(Hashtable v) {
/* 817 */     this.m_namedTemplates = v;
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
/*     */   private TemplateSubPatternAssociation getHead(String key) {
/* 830 */     return (TemplateSubPatternAssociation)this.m_patternTable.get(key);
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
/*     */   private void putHead(String key, TemplateSubPatternAssociation assoc) {
/* 842 */     if (key.equals("#text")) {
/* 843 */       this.m_textPatterns = assoc;
/* 844 */     } else if (key.equals("/")) {
/* 845 */       this.m_docPatterns = assoc;
/* 846 */     } else if (key.equals("#comment")) {
/* 847 */       this.m_commentPatterns = assoc;
/*     */     } 
/* 849 */     this.m_patternTable.put(key, assoc);
/*     */   }
/*     */ 
/*     */   
/*     */   public class TemplateWalker
/*     */   {
/*     */     private Enumeration hashIterator;
/*     */     
/*     */     private boolean inPatterns;
/*     */     
/*     */     private TemplateSubPatternAssociation curPattern;
/*     */     
/*     */     private Hashtable m_compilerCache;
/*     */     
/*     */     private final TemplateList this$0;
/*     */     
/*     */     private TemplateWalker(TemplateList this$0) {
/* 866 */       TemplateList.this = TemplateList.this; this.m_compilerCache = new Hashtable();
/* 867 */       this.hashIterator = TemplateList.this.m_patternTable.elements();
/* 868 */       this.inPatterns = true;
/* 869 */       this.curPattern = null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ElemTemplate next() {
/* 875 */       ElemTemplate ct, retValue = null;
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 880 */         if (this.inPatterns) {
/*     */           
/* 882 */           if (null != this.curPattern) {
/* 883 */             this.curPattern = this.curPattern.getNext();
/*     */           }
/* 885 */           if (null != this.curPattern) {
/* 886 */             retValue = this.curPattern.getTemplate();
/*     */           
/*     */           }
/* 889 */           else if (this.hashIterator.hasMoreElements()) {
/*     */             
/* 891 */             this.curPattern = this.hashIterator.nextElement();
/* 892 */             retValue = this.curPattern.getTemplate();
/*     */           }
/*     */           else {
/*     */             
/* 896 */             this.inPatterns = false;
/* 897 */             this.hashIterator = TemplateList.this.m_namedTemplates.elements();
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 902 */         if (!this.inPatterns)
/*     */         {
/* 904 */           if (this.hashIterator.hasMoreElements()) {
/* 905 */             retValue = this.hashIterator.nextElement();
/*     */           } else {
/* 907 */             return null;
/*     */           } 
/*     */         }
/* 910 */         ct = (ElemTemplate)this.m_compilerCache.get(new Integer(retValue.getUid()));
/* 911 */       } while (null != ct);
/*     */       
/* 913 */       this.m_compilerCache.put(new Integer(retValue.getUid()), retValue);
/* 914 */       return retValue;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/TemplateList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */