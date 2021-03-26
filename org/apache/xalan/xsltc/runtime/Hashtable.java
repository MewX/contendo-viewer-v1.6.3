/*     */ package org.apache.xalan.xsltc.runtime;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Hashtable
/*     */ {
/*     */   private transient HashtableEntry[] table;
/*     */   private transient int count;
/*     */   private int threshold;
/*     */   private float loadFactor;
/*     */   
/*     */   public Hashtable(int initialCapacity, float loadFactor) {
/*  66 */     if (initialCapacity <= 0) initialCapacity = 11; 
/*  67 */     if (loadFactor <= 0.0D) loadFactor = 0.75F; 
/*  68 */     this.loadFactor = loadFactor;
/*  69 */     this.table = new HashtableEntry[initialCapacity];
/*  70 */     this.threshold = (int)(initialCapacity * loadFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hashtable(int initialCapacity) {
/*  78 */     this(initialCapacity, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hashtable() {
/*  86 */     this(101, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  93 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 100 */     return (this.count == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration keys() {
/* 107 */     return new HashtableEnumerator(this, this.table, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/* 116 */     return new HashtableEnumerator(this, this.table, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Object value) {
/* 126 */     if (value == null) throw new NullPointerException();
/*     */ 
/*     */ 
/*     */     
/* 130 */     HashtableEntry[] tab = this.table;
/*     */     
/* 132 */     for (int i = tab.length; i-- > 0;) {
/* 133 */       for (HashtableEntry e = tab[i]; e != null; e = e.next) {
/* 134 */         if (e.value.equals(value)) {
/* 135 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 147 */     HashtableEntry[] tab = this.table;
/* 148 */     int hash = key.hashCode();
/* 149 */     int index = (hash & Integer.MAX_VALUE) % tab.length;
/*     */     
/* 151 */     for (HashtableEntry e = tab[index]; e != null; e = e.next) {
/* 152 */       if (e.hash == hash && e.key.equals(key))
/* 153 */         return true; 
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 163 */     HashtableEntry[] tab = this.table;
/* 164 */     int hash = key.hashCode();
/* 165 */     int index = (hash & Integer.MAX_VALUE) % tab.length;
/*     */     
/* 167 */     for (HashtableEntry e = tab[index]; e != null; e = e.next) {
/* 168 */       if (e.hash == hash && e.key.equals(key))
/* 169 */         return e.value; 
/*     */     } 
/* 171 */     return null;
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
/*     */   protected void rehash() {
/* 183 */     int oldCapacity = this.table.length;
/* 184 */     HashtableEntry[] oldTable = this.table;
/*     */     
/* 186 */     int newCapacity = oldCapacity * 2 + 1;
/* 187 */     HashtableEntry[] newTable = new HashtableEntry[newCapacity];
/*     */     
/* 189 */     this.threshold = (int)(newCapacity * this.loadFactor);
/* 190 */     this.table = newTable;
/*     */     
/* 192 */     for (int i = oldCapacity; i-- > 0;) {
/* 193 */       for (HashtableEntry old = oldTable[i]; old != null; ) {
/* 194 */         HashtableEntry e = old;
/* 195 */         old = old.next;
/* 196 */         int index = (e.hash & Integer.MAX_VALUE) % newCapacity;
/* 197 */         e.next = newTable[index];
/* 198 */         newTable[index] = e;
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
/*     */   public Object put(Object key, Object value) {
/* 213 */     if (value == null) throw new NullPointerException();
/*     */ 
/*     */ 
/*     */     
/* 217 */     HashtableEntry[] tab = this.table;
/* 218 */     int hash = key.hashCode();
/* 219 */     int index = (hash & Integer.MAX_VALUE) % tab.length;
/*     */     HashtableEntry e;
/* 221 */     for (e = tab[index]; e != null; e = e.next) {
/* 222 */       if (e.hash == hash && e.key.equals(key)) {
/* 223 */         Object old = e.value;
/* 224 */         e.value = value;
/* 225 */         return old;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 230 */     if (this.count >= this.threshold) {
/* 231 */       rehash();
/* 232 */       return put(key, value);
/*     */     } 
/*     */ 
/*     */     
/* 236 */     e = new HashtableEntry();
/* 237 */     e.hash = hash;
/* 238 */     e.key = key;
/* 239 */     e.value = value;
/* 240 */     e.next = tab[index];
/* 241 */     tab[index] = e;
/* 242 */     this.count++;
/* 243 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object key) {
/* 252 */     HashtableEntry[] tab = this.table;
/* 253 */     int hash = key.hashCode();
/* 254 */     int index = (hash & Integer.MAX_VALUE) % tab.length;
/* 255 */     for (HashtableEntry e = tab[index], prev = null; e != null; prev = e, e = e.next) {
/* 256 */       if (e.hash == hash && e.key.equals(key)) {
/* 257 */         if (prev != null) {
/* 258 */           prev.next = e.next;
/*     */         } else {
/* 260 */           tab[index] = e.next;
/* 261 */         }  this.count--;
/* 262 */         return e.value;
/*     */       } 
/*     */     } 
/* 265 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 272 */     HashtableEntry[] tab = this.table;
/* 273 */     for (int index = tab.length; --index >= 0;)
/* 274 */       tab[index] = null; 
/* 275 */     this.count = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 284 */     int max = size() - 1;
/* 285 */     StringBuffer buf = new StringBuffer();
/* 286 */     Enumeration k = keys();
/* 287 */     Enumeration e = elements();
/* 288 */     buf.append("{");
/*     */     
/* 290 */     for (int i = 0; i <= max; i++) {
/* 291 */       String s1 = k.nextElement().toString();
/* 292 */       String s2 = e.nextElement().toString();
/* 293 */       buf.append(s1 + "=" + s2);
/* 294 */       if (i < max) buf.append(", "); 
/*     */     } 
/* 296 */     buf.append("}");
/* 297 */     return buf.toString();
/*     */   }
/*     */   
/*     */   class HashtableEnumerator
/*     */     implements Enumeration
/*     */   {
/*     */     boolean keys;
/*     */     int index;
/*     */     HashtableEntry[] table;
/*     */     HashtableEntry entry;
/*     */     private final Hashtable this$0;
/*     */     
/*     */     HashtableEnumerator(Hashtable this$0, HashtableEntry[] table, boolean keys) {
/* 310 */       this.this$0 = this$0;
/* 311 */       this.table = table;
/* 312 */       this.keys = keys;
/* 313 */       this.index = table.length;
/*     */     }
/*     */     
/*     */     public boolean hasMoreElements() {
/* 317 */       if (this.entry != null) {
/* 318 */         return true;
/*     */       }
/* 320 */       while (this.index-- > 0) {
/* 321 */         if ((this.entry = this.table[this.index]) != null) {
/* 322 */           return true;
/*     */         }
/*     */       } 
/* 325 */       return false;
/*     */     }
/*     */     
/*     */     public Object nextElement() {
/* 329 */       if (this.entry == null) {
/* 330 */         do {  } while (this.index-- > 0 && (this.entry = this.table[this.index]) == null);
/*     */       }
/* 332 */       if (this.entry != null) {
/* 333 */         HashtableEntry e = this.entry;
/* 334 */         this.entry = e.next;
/* 335 */         return this.keys ? e.key : e.value;
/*     */       } 
/* 337 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/Hashtable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */