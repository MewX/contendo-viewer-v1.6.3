/*     */ package org.apache.xml.dtm.ref.sax2dtm;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.dtm.DTMWSFilter;
/*     */ import org.apache.xml.dtm.ref.DTMDefaultBase;
/*     */ import org.apache.xml.utils.IntStack;
/*     */ import org.apache.xml.utils.IntVector;
/*     */ import org.apache.xml.utils.StringVector;
/*     */ import org.apache.xml.utils.XMLStringFactory;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SAX2RTFDTM
/*     */   extends SAX2DTM
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*  66 */   private int m_currentDocumentNode = -1;
/*     */ 
/*     */   
/*  69 */   IntStack mark_size = new IntStack();
/*     */   
/*  71 */   IntStack mark_data_size = new IntStack();
/*     */   
/*  73 */   IntStack mark_char_size = new IntStack();
/*     */   
/*  75 */   IntStack mark_doq_size = new IntStack();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   IntStack mark_nsdeclset_size = new IntStack();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   IntStack mark_nsdeclelem_size = new IntStack();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int m_emptyNodeCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int m_emptyNSDeclSetCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int m_emptyNSDeclSetElemsCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int m_emptyDataCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int m_emptyCharsCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int m_emptyDataQNCount;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SAX2RTFDTM(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing) {
/* 124 */     super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     this.m_useSourceLocationProperty = false;
/* 131 */     this.m_sourceSystemId = this.m_useSourceLocationProperty ? new StringVector() : null;
/*     */     
/* 133 */     this.m_sourceLine = this.m_useSourceLocationProperty ? new IntVector() : null;
/* 134 */     this.m_sourceColumn = this.m_useSourceLocationProperty ? new IntVector() : null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     this.m_emptyNodeCount = ((DTMDefaultBase)this).m_size;
/* 140 */     this.m_emptyNSDeclSetCount = (((DTMDefaultBase)this).m_namespaceDeclSets == null) ? 0 : ((DTMDefaultBase)this).m_namespaceDeclSets.size();
/*     */     
/* 142 */     this.m_emptyNSDeclSetElemsCount = (((DTMDefaultBase)this).m_namespaceDeclSetElements == null) ? 0 : ((DTMDefaultBase)this).m_namespaceDeclSetElements.size();
/*     */     
/* 144 */     this.m_emptyDataCount = this.m_data.size();
/* 145 */     this.m_emptyCharsCount = this.m_chars.size();
/* 146 */     this.m_emptyDataQNCount = this.m_dataOrQName.size();
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
/*     */   public int getDocument() {
/* 165 */     return makeNodeHandle(this.m_currentDocumentNode);
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
/*     */   public int getDocumentRoot(int nodeHandle) {
/* 181 */     for (int id = makeNodeIdentity(nodeHandle); id != -1; id = _parent(id)) {
/* 182 */       if (_type(id) == 9) {
/* 183 */         return makeNodeHandle(id);
/*     */       }
/*     */     } 
/*     */     
/* 187 */     return -1;
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
/*     */   protected int _documentRoot(int nodeIdentifier) {
/* 200 */     if (nodeIdentifier == -1) return -1;
/*     */     
/* 202 */     int parent = _parent(nodeIdentifier);
/* 203 */     while (parent != -1) {
/* 204 */       nodeIdentifier = parent; parent = _parent(nodeIdentifier);
/*     */     } 
/*     */     
/* 207 */     return nodeIdentifier;
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
/*     */   public void startDocument() throws SAXException {
/* 225 */     this.m_endDocumentOccured = false;
/* 226 */     this.m_prefixMappings = new Vector();
/* 227 */     this.m_contextIndexes = new IntStack();
/* 228 */     this.m_parents = new IntStack();
/*     */     
/* 230 */     this.m_currentDocumentNode = ((DTMDefaultBase)this).m_size;
/* 231 */     super.startDocument();
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
/*     */   public void endDocument() throws SAXException {
/* 248 */     charactersFlush();
/*     */     
/* 250 */     ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, this.m_currentDocumentNode);
/*     */     
/* 252 */     if (((DTMDefaultBase)this).m_firstch.elementAt(this.m_currentDocumentNode) == -2) {
/* 253 */       ((DTMDefaultBase)this).m_firstch.setElementAt(-1, this.m_currentDocumentNode);
/*     */     }
/* 255 */     if (-1 != this.m_previous) {
/* 256 */       ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, this.m_previous);
/*     */     }
/* 258 */     this.m_parents = null;
/* 259 */     this.m_prefixMappings = null;
/* 260 */     this.m_contextIndexes = null;
/*     */     
/* 262 */     this.m_currentDocumentNode = -1;
/* 263 */     this.m_endDocumentOccured = true;
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
/*     */   public void pushRewindMark() {
/* 279 */     if (((DTMDefaultBase)this).m_indexing || ((DTMDefaultBase)this).m_elemIndexes != null) {
/* 280 */       throw new NullPointerException("Coding error; Don't try to mark/rewind an indexed DTM");
/*     */     }
/*     */ 
/*     */     
/* 284 */     this.mark_size.push(((DTMDefaultBase)this).m_size);
/* 285 */     this.mark_nsdeclset_size.push((((DTMDefaultBase)this).m_namespaceDeclSets == null) ? 0 : ((DTMDefaultBase)this).m_namespaceDeclSets.size());
/*     */ 
/*     */     
/* 288 */     this.mark_nsdeclelem_size.push((((DTMDefaultBase)this).m_namespaceDeclSetElements == null) ? 0 : ((DTMDefaultBase)this).m_namespaceDeclSetElements.size());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     this.mark_data_size.push(this.m_data.size());
/* 294 */     this.mark_char_size.push(this.m_chars.size());
/* 295 */     this.mark_doq_size.push(this.m_dataOrQName.size());
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
/*     */   public boolean popRewindMark() {
/* 325 */     boolean top = this.mark_size.empty();
/*     */     
/* 327 */     ((DTMDefaultBase)this).m_size = top ? this.m_emptyNodeCount : this.mark_size.pop();
/* 328 */     ((DTMDefaultBase)this).m_exptype.setSize(((DTMDefaultBase)this).m_size);
/* 329 */     ((DTMDefaultBase)this).m_firstch.setSize(((DTMDefaultBase)this).m_size);
/* 330 */     ((DTMDefaultBase)this).m_nextsib.setSize(((DTMDefaultBase)this).m_size);
/* 331 */     ((DTMDefaultBase)this).m_prevsib.setSize(((DTMDefaultBase)this).m_size);
/* 332 */     ((DTMDefaultBase)this).m_parent.setSize(((DTMDefaultBase)this).m_size);
/*     */     
/* 334 */     ((DTMDefaultBase)this).m_elemIndexes = null;
/*     */     
/* 336 */     int ds = top ? this.m_emptyNSDeclSetCount : this.mark_nsdeclset_size.pop();
/* 337 */     if (((DTMDefaultBase)this).m_namespaceDeclSets != null) {
/* 338 */       ((DTMDefaultBase)this).m_namespaceDeclSets.setSize(ds);
/*     */     }
/*     */     
/* 341 */     int ds1 = top ? this.m_emptyNSDeclSetElemsCount : this.mark_nsdeclelem_size.pop();
/* 342 */     if (((DTMDefaultBase)this).m_namespaceDeclSetElements != null) {
/* 343 */       ((DTMDefaultBase)this).m_namespaceDeclSetElements.setSize(ds1);
/*     */     }
/*     */ 
/*     */     
/* 347 */     this.m_data.setSize(top ? this.m_emptyDataCount : this.mark_data_size.pop());
/* 348 */     this.m_chars.setLength(top ? this.m_emptyCharsCount : this.mark_char_size.pop());
/* 349 */     this.m_dataOrQName.setSize(top ? this.m_emptyDataQNCount : this.mark_doq_size.pop());
/*     */ 
/*     */     
/* 352 */     return (((DTMDefaultBase)this).m_size == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTreeIncomplete() {
/* 359 */     return !this.m_endDocumentOccured;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/sax2dtm/SAX2RTFDTM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */