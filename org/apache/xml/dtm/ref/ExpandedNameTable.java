/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExpandedNameTable
/*     */ {
/*     */   private ExtendedType[] m_extendedTypes;
/*  42 */   private static int m_initialSize = 128;
/*     */   
/*     */   private int m_nextType;
/*     */   
/*     */   public static final int ELEMENT = 1;
/*     */   
/*     */   public static final int ATTRIBUTE = 2;
/*     */   
/*     */   public static final int TEXT = 3;
/*     */   
/*     */   public static final int CDATA_SECTION = 4;
/*     */   
/*     */   public static final int ENTITY_REFERENCE = 5;
/*     */   
/*     */   public static final int ENTITY = 6;
/*     */   
/*     */   public static final int PROCESSING_INSTRUCTION = 7;
/*     */   
/*     */   public static final int COMMENT = 8;
/*     */   public static final int DOCUMENT = 9;
/*     */   public static final int DOCUMENT_TYPE = 10;
/*     */   public static final int DOCUMENT_FRAGMENT = 11;
/*     */   public static final int NOTATION = 12;
/*     */   public static final int NAMESPACE = 13;
/*  66 */   ExtendedType hashET = new ExtendedType(-1, "", "");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ExtendedType[] m_defaultExtendedTypes;
/*     */ 
/*     */ 
/*     */   
/*  75 */   private static float m_loadFactor = 0.75F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private static int m_initialCapacity = 203;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int m_capacity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int m_threshold;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HashEntry[] m_table;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 106 */     m_defaultExtendedTypes = new ExtendedType[14];
/*     */     
/* 108 */     for (int i = 0; i < 14; i++)
/*     */     {
/* 110 */       m_defaultExtendedTypes[i] = new ExtendedType(i, "", "");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpandedNameTable() {
/* 119 */     this.m_capacity = m_initialCapacity;
/* 120 */     this.m_threshold = (int)(this.m_capacity * m_loadFactor);
/* 121 */     this.m_table = new HashEntry[this.m_capacity];
/*     */     
/* 123 */     initExtendedTypes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initExtendedTypes() {
/* 133 */     this.m_extendedTypes = new ExtendedType[m_initialSize];
/* 134 */     for (int i = 0; i < 14; i++) {
/* 135 */       this.m_extendedTypes[i] = m_defaultExtendedTypes[i];
/* 136 */       this.m_table[i] = new HashEntry(m_defaultExtendedTypes[i], i, i, null);
/*     */     } 
/*     */     
/* 139 */     this.m_nextType = 14;
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
/*     */   public int getExpandedTypeID(String namespace, String localName, int type) {
/* 156 */     return getExpandedTypeID(namespace, localName, type, false);
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
/*     */   public int getExpandedTypeID(String namespace, String localName, int type, boolean searchOnly) {
/* 179 */     if (null == namespace)
/* 180 */       namespace = ""; 
/* 181 */     if (null == localName) {
/* 182 */       localName = "";
/*     */     }
/*     */     
/* 185 */     int hash = type + namespace.hashCode() + localName.hashCode();
/*     */ 
/*     */     
/* 188 */     this.hashET.redefine(type, namespace, localName, hash);
/*     */ 
/*     */     
/* 191 */     int index = hash % this.m_capacity;
/* 192 */     if (index < 0) {
/* 193 */       index = -index;
/*     */     }
/*     */ 
/*     */     
/* 197 */     for (HashEntry e = this.m_table[index]; e != null; e = e.next) {
/*     */       
/* 199 */       if (e.hash == hash && e.key.equals(this.hashET)) {
/* 200 */         return e.value;
/*     */       }
/*     */     } 
/* 203 */     if (searchOnly)
/*     */     {
/* 205 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 209 */     if (this.m_nextType > this.m_threshold) {
/* 210 */       rehash();
/* 211 */       index = hash % this.m_capacity;
/* 212 */       if (index < 0) {
/* 213 */         index = -index;
/*     */       }
/*     */     } 
/*     */     
/* 217 */     ExtendedType newET = new ExtendedType(type, namespace, localName, hash);
/*     */ 
/*     */     
/* 220 */     if (this.m_extendedTypes.length == this.m_nextType) {
/* 221 */       ExtendedType[] newArray = new ExtendedType[this.m_extendedTypes.length * 2];
/* 222 */       System.arraycopy(this.m_extendedTypes, 0, newArray, 0, this.m_extendedTypes.length);
/*     */       
/* 224 */       this.m_extendedTypes = newArray;
/*     */     } 
/*     */     
/* 227 */     this.m_extendedTypes[this.m_nextType] = newET;
/*     */ 
/*     */ 
/*     */     
/* 231 */     HashEntry entry = new HashEntry(newET, this.m_nextType, hash, this.m_table[index]);
/* 232 */     this.m_table[index] = entry;
/*     */     
/* 234 */     return this.m_nextType++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rehash() {
/* 245 */     int oldCapacity = this.m_capacity;
/* 246 */     HashEntry[] oldTable = this.m_table;
/*     */     
/* 248 */     int newCapacity = 2 * oldCapacity + 1;
/* 249 */     this.m_capacity = newCapacity;
/* 250 */     this.m_threshold = (int)(newCapacity * m_loadFactor);
/*     */     
/* 252 */     this.m_table = new HashEntry[newCapacity];
/* 253 */     for (int i = oldCapacity - 1; i >= 0; i--) {
/*     */       
/* 255 */       for (HashEntry old = oldTable[i]; old != null; ) {
/*     */         
/* 257 */         HashEntry e = old;
/* 258 */         old = old.next;
/*     */         
/* 260 */         int newIndex = e.hash % newCapacity;
/* 261 */         if (newIndex < 0) {
/* 262 */           newIndex = -newIndex;
/*     */         }
/* 264 */         e.next = this.m_table[newIndex];
/* 265 */         this.m_table[newIndex] = e;
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
/*     */   public int getExpandedTypeID(int type) {
/* 281 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName(int ExpandedNameID) {
/* 292 */     return this.m_extendedTypes[ExpandedNameID].getLocalName();
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
/*     */   public final int getLocalNameID(int ExpandedNameID) {
/* 304 */     if (this.m_extendedTypes[ExpandedNameID].getLocalName().equals("")) {
/* 305 */       return 0;
/*     */     }
/* 307 */     return ExpandedNameID;
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
/*     */   public String getNamespace(int ExpandedNameID) {
/* 320 */     String namespace = this.m_extendedTypes[ExpandedNameID].getNamespace();
/* 321 */     return namespace.equals("") ? null : namespace;
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
/*     */   public final int getNamespaceID(int ExpandedNameID) {
/* 333 */     if (this.m_extendedTypes[ExpandedNameID].getNamespace().equals("")) {
/* 334 */       return 0;
/*     */     }
/* 336 */     return ExpandedNameID;
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
/*     */   public final short getType(int ExpandedNameID) {
/* 348 */     return (short)this.m_extendedTypes[ExpandedNameID].getNodeType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 358 */     return this.m_nextType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedType[] getExtendedTypes() {
/* 368 */     return this.m_extendedTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class HashEntry
/*     */   {
/*     */     ExtendedType key;
/*     */     
/*     */     int value;
/*     */     
/*     */     int hash;
/*     */     
/*     */     HashEntry next;
/*     */ 
/*     */     
/*     */     protected HashEntry(ExtendedType key, int value, int hash, HashEntry next) {
/* 385 */       this.key = key;
/* 386 */       this.value = value;
/* 387 */       this.hash = hash;
/* 388 */       this.next = next;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/ExpandedNameTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */