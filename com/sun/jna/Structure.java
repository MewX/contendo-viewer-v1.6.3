/*      */ package com.sun.jna;
/*      */ 
/*      */ import java.lang.annotation.Documented;
/*      */ import java.lang.annotation.ElementType;
/*      */ import java.lang.annotation.Retention;
/*      */ import java.lang.annotation.RetentionPolicy;
/*      */ import java.lang.annotation.Target;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.nio.Buffer;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.WeakHashMap;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Structure
/*      */ {
/*  114 */   private static final Logger LOG = Logger.getLogger(Structure.class.getName());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ALIGN_DEFAULT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ALIGN_NONE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ALIGN_GNUC = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ALIGN_MSVC = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final int CALCULATE_SIZE = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  147 */   static final Map<Class<?>, LayoutInfo> layoutInfo = new WeakHashMap<Class<?>, LayoutInfo>();
/*  148 */   static final Map<Class<?>, List<String>> fieldOrder = new WeakHashMap<Class<?>, List<String>>();
/*      */   
/*      */   private Pointer memory;
/*      */   
/*  152 */   private int size = -1;
/*      */   
/*      */   private int alignType;
/*      */   
/*      */   private String encoding;
/*      */   private int actualAlignType;
/*      */   private int structAlignment;
/*      */   private Map<String, StructField> structFields;
/*  160 */   private final Map<String, Object> nativeStrings = new HashMap<String, Object>();
/*      */   
/*      */   private TypeMapper typeMapper;
/*      */   
/*      */   private long typeInfo;
/*      */   
/*      */   private boolean autoRead = true;
/*      */   private boolean autoWrite = true;
/*      */   private Structure[] array;
/*      */   private boolean readCalled;
/*      */   
/*      */   protected Structure() {
/*  172 */     this(0);
/*      */   }
/*      */   
/*      */   protected Structure(TypeMapper mapper) {
/*  176 */     this(null, 0, mapper);
/*      */   }
/*      */   
/*      */   protected Structure(int alignType) {
/*  180 */     this((Pointer)null, alignType);
/*      */   }
/*      */   
/*      */   protected Structure(int alignType, TypeMapper mapper) {
/*  184 */     this(null, alignType, mapper);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Structure(Pointer p) {
/*  189 */     this(p, 0);
/*      */   }
/*      */   
/*      */   protected Structure(Pointer p, int alignType) {
/*  193 */     this(p, alignType, null);
/*      */   }
/*      */   
/*      */   protected Structure(Pointer p, int alignType, TypeMapper mapper) {
/*  197 */     setAlignType(alignType);
/*  198 */     setStringEncoding(Native.getStringEncoding(getClass()));
/*  199 */     initializeTypeMapper(mapper);
/*  200 */     validateFields();
/*  201 */     if (p != null) {
/*  202 */       useMemory(p, 0, true);
/*      */     } else {
/*      */       
/*  205 */       allocateMemory(-1);
/*      */     } 
/*  207 */     initializeFields();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Map<String, StructField> fields() {
/*  218 */     return this.structFields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   TypeMapper getTypeMapper() {
/*  225 */     return this.typeMapper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeTypeMapper(TypeMapper mapper) {
/*  235 */     if (mapper == null) {
/*  236 */       mapper = Native.getTypeMapper(getClass());
/*      */     }
/*  238 */     this.typeMapper = mapper;
/*  239 */     layoutChanged();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void layoutChanged() {
/*  246 */     if (this.size != -1) {
/*  247 */       this.size = -1;
/*  248 */       if (this.memory instanceof AutoAllocated) {
/*  249 */         this.memory = null;
/*      */       }
/*      */       
/*  252 */       ensureAllocated();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setStringEncoding(String encoding) {
/*  261 */     this.encoding = encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getStringEncoding() {
/*  269 */     return this.encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setAlignType(int alignType) {
/*  278 */     this.alignType = alignType;
/*  279 */     if (alignType == 0) {
/*  280 */       alignType = Native.getStructureAlignment(getClass());
/*  281 */       if (alignType == 0)
/*  282 */         if (Platform.isWindows()) {
/*  283 */           alignType = 3;
/*      */         } else {
/*  285 */           alignType = 2;
/*      */         }  
/*      */     } 
/*  288 */     this.actualAlignType = alignType;
/*  289 */     layoutChanged();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Memory autoAllocate(int size) {
/*  298 */     return new AutoAllocated(size);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void useMemory(Pointer m) {
/*  308 */     useMemory(m, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void useMemory(Pointer m, int offset) {
/*  320 */     useMemory(m, offset, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void useMemory(Pointer m, int offset, boolean force) {
/*      */     try {
/*  336 */       this.nativeStrings.clear();
/*      */       
/*  338 */       if (this instanceof ByValue && !force) {
/*      */ 
/*      */         
/*  341 */         byte[] buf = new byte[size()];
/*  342 */         m.read(0L, buf, 0, buf.length);
/*  343 */         this.memory.write(0L, buf, 0, buf.length);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  348 */         this.memory = m.share(offset);
/*  349 */         if (this.size == -1) {
/*  350 */           this.size = calculateSize(false);
/*      */         }
/*  352 */         if (this.size != -1) {
/*  353 */           this.memory = m.share(offset, this.size);
/*      */         }
/*      */       } 
/*  356 */       this.array = null;
/*  357 */       this.readCalled = false;
/*      */     }
/*  359 */     catch (IndexOutOfBoundsException e) {
/*  360 */       throw new IllegalArgumentException("Structure exceeds provided memory bounds", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void ensureAllocated() {
/*  367 */     ensureAllocated(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ensureAllocated(boolean avoidFFIType) {
/*  376 */     if (this.memory == null) {
/*  377 */       allocateMemory(avoidFFIType);
/*      */     }
/*  379 */     else if (this.size == -1) {
/*  380 */       this.size = calculateSize(true, avoidFFIType);
/*  381 */       if (!(this.memory instanceof AutoAllocated)) {
/*      */         
/*      */         try {
/*  384 */           this.memory = this.memory.share(0L, this.size);
/*      */         }
/*  386 */         catch (IndexOutOfBoundsException e) {
/*  387 */           throw new IllegalArgumentException("Structure exceeds provided memory bounds", e);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void allocateMemory() {
/*  397 */     allocateMemory(false);
/*      */   }
/*      */   
/*      */   private void allocateMemory(boolean avoidFFIType) {
/*  401 */     allocateMemory(calculateSize(true, avoidFFIType));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void allocateMemory(int size) {
/*  412 */     if (size == -1) {
/*      */       
/*  414 */       size = calculateSize(false);
/*      */     }
/*  416 */     else if (size <= 0) {
/*  417 */       throw new IllegalArgumentException("Structure size must be greater than zero: " + size);
/*      */     } 
/*      */ 
/*      */     
/*  421 */     if (size != -1) {
/*  422 */       if (this.memory == null || this.memory instanceof AutoAllocated)
/*      */       {
/*  424 */         this.memory = autoAllocate(size);
/*      */       }
/*  426 */       this.size = size;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  434 */     ensureAllocated();
/*  435 */     return this.size;
/*      */   }
/*      */ 
/*      */   
/*      */   public void clear() {
/*  440 */     ensureAllocated();
/*  441 */     this.memory.clear(size());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Pointer getPointer() {
/*  455 */     ensureAllocated();
/*  456 */     return this.memory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  465 */   private static final ThreadLocal<Map<Pointer, Structure>> reads = new ThreadLocal<Map<Pointer, Structure>>()
/*      */     {
/*      */       protected synchronized Map<Pointer, Structure> initialValue() {
/*  468 */         return new HashMap<Pointer, Structure>();
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */   
/*  474 */   private static final ThreadLocal<Set<Structure>> busy = new ThreadLocal<Set<Structure>>()
/*      */     {
/*      */       protected synchronized Set<Structure> initialValue() {
/*  477 */         return new Structure.StructureSet();
/*      */       }
/*      */     };
/*      */   
/*      */   static class StructureSet
/*      */     extends AbstractCollection<Structure>
/*      */     implements Set<Structure> {
/*      */     Structure[] elements;
/*      */     private int count;
/*      */     
/*      */     private void ensureCapacity(int size) {
/*  488 */       if (this.elements == null) {
/*  489 */         this.elements = new Structure[size * 3 / 2];
/*      */       }
/*  491 */       else if (this.elements.length < size) {
/*  492 */         Structure[] e = new Structure[size * 3 / 2];
/*  493 */         System.arraycopy(this.elements, 0, e, 0, this.elements.length);
/*  494 */         this.elements = e;
/*      */       } 
/*      */     }
/*      */     public Structure[] getElements() {
/*  498 */       return this.elements;
/*      */     }
/*      */     public int size() {
/*  501 */       return this.count;
/*      */     }
/*      */     public boolean contains(Object o) {
/*  504 */       return (indexOf((Structure)o) != -1);
/*      */     }
/*      */     
/*      */     public boolean add(Structure o) {
/*  508 */       if (!contains(o)) {
/*  509 */         ensureCapacity(this.count + 1);
/*  510 */         this.elements[this.count++] = o;
/*      */       } 
/*  512 */       return true;
/*      */     }
/*      */     private int indexOf(Structure s1) {
/*  515 */       for (int i = 0; i < this.count; i++) {
/*  516 */         Structure s2 = this.elements[i];
/*  517 */         if (s1 == s2 || (s1
/*  518 */           .getClass() == s2.getClass() && s1
/*  519 */           .size() == s2.size() && s1
/*  520 */           .getPointer().equals(s2.getPointer()))) {
/*  521 */           return i;
/*      */         }
/*      */       } 
/*  524 */       return -1;
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/*  528 */       int idx = indexOf((Structure)o);
/*  529 */       if (idx != -1) {
/*  530 */         if (--this.count >= 0) {
/*  531 */           this.elements[idx] = this.elements[this.count];
/*  532 */           this.elements[this.count] = null;
/*      */         } 
/*  534 */         return true;
/*      */       } 
/*  536 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<Structure> iterator() {
/*  543 */       Structure[] e = new Structure[this.count];
/*  544 */       if (this.count > 0) {
/*  545 */         System.arraycopy(this.elements, 0, e, 0, this.count);
/*      */       }
/*  547 */       return Arrays.<Structure>asList(e).iterator();
/*      */     }
/*      */   }
/*      */   
/*      */   static Set<Structure> busy() {
/*  552 */     return busy.get();
/*      */   }
/*      */   static Map<Pointer, Structure> reading() {
/*  555 */     return reads.get();
/*      */   }
/*      */ 
/*      */   
/*      */   void conditionalAutoRead() {
/*  560 */     if (!this.readCalled) {
/*  561 */       autoRead();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void read() {
/*  570 */     if (this.memory == PLACEHOLDER_MEMORY) {
/*      */       return;
/*      */     }
/*  573 */     this.readCalled = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  579 */     ensureAllocated();
/*      */ 
/*      */     
/*  582 */     if (busy().contains(this)) {
/*      */       return;
/*      */     }
/*  585 */     busy().add(this);
/*  586 */     if (this instanceof ByReference) {
/*  587 */       reading().put(getPointer(), this);
/*      */     }
/*      */     try {
/*  590 */       for (StructField structField : fields().values()) {
/*  591 */         readField(structField);
/*      */       }
/*      */     } finally {
/*      */       
/*  595 */       busy().remove(this);
/*  596 */       if (reading().get(getPointer()) == this) {
/*  597 */         reading().remove(getPointer());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int fieldOffset(String name) {
/*  607 */     ensureAllocated();
/*  608 */     StructField f = fields().get(name);
/*  609 */     if (f == null) {
/*  610 */       throw new IllegalArgumentException("No such field: " + name);
/*      */     }
/*  612 */     return f.offset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object readField(String name) {
/*  622 */     ensureAllocated();
/*  623 */     StructField f = fields().get(name);
/*  624 */     if (f == null)
/*  625 */       throw new IllegalArgumentException("No such field: " + name); 
/*  626 */     return readField(f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Object getFieldValue(Field field) {
/*      */     try {
/*  636 */       return field.get(this);
/*      */     }
/*  638 */     catch (Exception e) {
/*  639 */       throw new Error("Exception reading field '" + field.getName() + "' in " + getClass(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFieldValue(Field field, Object value) {
/*  648 */     setFieldValue(field, value, false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setFieldValue(Field field, Object value, boolean overrideFinal) {
/*      */     try {
/*  654 */       field.set(this, value);
/*      */     }
/*  656 */     catch (IllegalAccessException e) {
/*  657 */       int modifiers = field.getModifiers();
/*  658 */       if (Modifier.isFinal(modifiers)) {
/*  659 */         if (overrideFinal)
/*      */         {
/*      */           
/*  662 */           throw new UnsupportedOperationException("This VM does not support Structures with final fields (field '" + field.getName() + "' within " + getClass() + ")", e);
/*      */         }
/*  664 */         throw new UnsupportedOperationException("Attempt to write to read-only field '" + field.getName() + "' within " + getClass(), e);
/*      */       } 
/*  666 */       throw new Error("Unexpectedly unable to write to field '" + field.getName() + "' within " + getClass(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static <T extends Structure> T updateStructureByReference(Class<T> type, T s, Pointer address) {
/*  678 */     if (address == null) {
/*  679 */       s = null;
/*      */     
/*      */     }
/*  682 */     else if (s == null || !address.equals(s.getPointer())) {
/*  683 */       Structure s1 = reading().get(address);
/*  684 */       if (s1 != null && type.equals(s1.getClass())) {
/*  685 */         Structure structure = s1;
/*  686 */         structure.autoRead();
/*      */       } else {
/*      */         
/*  689 */         s = newInstance(type, address);
/*  690 */         s.conditionalAutoRead();
/*      */       } 
/*      */     } else {
/*      */       
/*  694 */       s.autoRead();
/*      */     } 
/*      */     
/*  697 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object readField(StructField structField) {
/*      */     Object result;
/*  709 */     int offset = structField.offset;
/*      */ 
/*      */     
/*  712 */     Class<?> fieldType = structField.type;
/*  713 */     FromNativeConverter readConverter = structField.readConverter;
/*  714 */     if (readConverter != null) {
/*  715 */       fieldType = readConverter.nativeType();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  724 */     Object currentValue = (Structure.class.isAssignableFrom(fieldType) || Callback.class.isAssignableFrom(fieldType) || (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(fieldType)) || Pointer.class.isAssignableFrom(fieldType) || NativeMapped.class.isAssignableFrom(fieldType) || fieldType.isArray()) ? getFieldValue(structField.field) : null;
/*      */ 
/*      */     
/*  727 */     if (fieldType == String.class) {
/*  728 */       Pointer p = this.memory.getPointer(offset);
/*  729 */       result = (p == null) ? null : p.getString(0L, this.encoding);
/*      */     } else {
/*      */       
/*  732 */       result = this.memory.getValue(offset, fieldType, currentValue);
/*      */     } 
/*  734 */     if (readConverter != null) {
/*  735 */       result = readConverter.fromNative(result, structField.context);
/*  736 */       if (currentValue != null && currentValue.equals(result)) {
/*  737 */         result = currentValue;
/*      */       }
/*      */     } 
/*      */     
/*  741 */     if (fieldType.equals(String.class) || fieldType
/*  742 */       .equals(WString.class)) {
/*  743 */       this.nativeStrings.put(structField.name + ".ptr", this.memory.getPointer(offset));
/*  744 */       this.nativeStrings.put(structField.name + ".val", result);
/*      */     } 
/*      */ 
/*      */     
/*  748 */     setFieldValue(structField.field, result, true);
/*  749 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write() {
/*  757 */     if (this.memory == PLACEHOLDER_MEMORY) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  764 */     ensureAllocated();
/*      */ 
/*      */     
/*  767 */     if (this instanceof ByValue) {
/*  768 */       getTypeInfo();
/*      */     }
/*      */ 
/*      */     
/*  772 */     if (busy().contains(this)) {
/*      */       return;
/*      */     }
/*  775 */     busy().add(this);
/*      */     
/*      */     try {
/*  778 */       for (StructField sf : fields().values()) {
/*  779 */         if (!sf.isVolatile) {
/*  780 */           writeField(sf);
/*      */         }
/*      */       } 
/*      */     } finally {
/*      */       
/*  785 */       busy().remove(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeField(String name) {
/*  795 */     ensureAllocated();
/*  796 */     StructField f = fields().get(name);
/*  797 */     if (f == null)
/*  798 */       throw new IllegalArgumentException("No such field: " + name); 
/*  799 */     writeField(f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeField(String name, Object value) {
/*  810 */     ensureAllocated();
/*  811 */     StructField structField = fields().get(name);
/*  812 */     if (structField == null)
/*  813 */       throw new IllegalArgumentException("No such field: " + name); 
/*  814 */     setFieldValue(structField.field, value);
/*  815 */     writeField(structField);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeField(StructField structField) {
/*  823 */     if (structField.isReadOnly) {
/*      */       return;
/*      */     }
/*      */     
/*  827 */     int offset = structField.offset;
/*      */ 
/*      */     
/*  830 */     Object value = getFieldValue(structField.field);
/*      */ 
/*      */     
/*  833 */     Class<?> fieldType = structField.type;
/*  834 */     ToNativeConverter converter = structField.writeConverter;
/*  835 */     if (converter != null) {
/*  836 */       value = converter.toNative(value, new StructureWriteContext(this, structField.field));
/*  837 */       fieldType = converter.nativeType();
/*      */     } 
/*      */ 
/*      */     
/*  841 */     if (String.class == fieldType || WString.class == fieldType) {
/*      */ 
/*      */       
/*  844 */       boolean wide = (fieldType == WString.class);
/*  845 */       if (value != null) {
/*      */ 
/*      */         
/*  848 */         if (this.nativeStrings.containsKey(structField.name + ".ptr") && value
/*  849 */           .equals(this.nativeStrings.get(structField.name + ".val"))) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/*  854 */         NativeString nativeString = wide ? new NativeString(value.toString(), true) : new NativeString(value.toString(), this.encoding);
/*      */ 
/*      */         
/*  857 */         this.nativeStrings.put(structField.name, nativeString);
/*  858 */         value = nativeString.getPointer();
/*      */       } else {
/*      */         
/*  861 */         this.nativeStrings.remove(structField.name);
/*      */       } 
/*  863 */       this.nativeStrings.remove(structField.name + ".ptr");
/*  864 */       this.nativeStrings.remove(structField.name + ".val");
/*      */     } 
/*      */     
/*      */     try {
/*  868 */       this.memory.setValue(offset, value, fieldType);
/*      */     }
/*  870 */     catch (IllegalArgumentException e) {
/*  871 */       String msg = "Structure field \"" + structField.name + "\" was declared as " + structField.type + ((structField.type == fieldType) ? "" : (" (native type " + fieldType + ")")) + ", which is not supported within a Structure";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  876 */       throw new IllegalArgumentException(msg, e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<String> getFieldOrder() {
/*  951 */     List<String> fields = new LinkedList<String>();
/*  952 */     for (Class<?> clazz = getClass(); clazz != Structure.class; clazz = clazz.getSuperclass()) {
/*  953 */       FieldOrder order = clazz.<FieldOrder>getAnnotation(FieldOrder.class);
/*  954 */       if (order != null) {
/*  955 */         fields.addAll(0, Arrays.asList(order.value()));
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  960 */     return Collections.unmodifiableList(fields);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void sortFields(List<Field> fields, List<String> names) {
/*  968 */     for (int i = 0; i < names.size(); i++) {
/*  969 */       String name = names.get(i);
/*  970 */       for (int f = 0; f < fields.size(); f++) {
/*  971 */         Field field = fields.get(f);
/*  972 */         if (name.equals(field.getName())) {
/*  973 */           Collections.swap(fields, i, f);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<Field> getFieldList() {
/*  985 */     List<Field> flist = new ArrayList<Field>();
/*  986 */     Class<?> cls = getClass();
/*  987 */     for (; !cls.equals(Structure.class); 
/*  988 */       cls = cls.getSuperclass()) {
/*  989 */       List<Field> classFields = new ArrayList<Field>();
/*  990 */       Field[] fields = cls.getDeclaredFields();
/*  991 */       for (int i = 0; i < fields.length; i++) {
/*  992 */         int modifiers = fields[i].getModifiers();
/*  993 */         if (!Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers))
/*      */         {
/*      */           
/*  996 */           classFields.add(fields[i]); } 
/*      */       } 
/*  998 */       flist.addAll(0, classFields);
/*      */     } 
/* 1000 */     return flist;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<String> fieldOrder() {
/* 1007 */     Class<?> clazz = getClass();
/* 1008 */     synchronized (fieldOrder) {
/* 1009 */       List<String> list = fieldOrder.get(clazz);
/* 1010 */       if (list == null) {
/* 1011 */         list = getFieldOrder();
/* 1012 */         fieldOrder.put(clazz, list);
/*      */       } 
/* 1014 */       return list;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static List<String> createFieldsOrder(List<String> baseFields, String... extraFields) {
/* 1019 */     return createFieldsOrder(baseFields, Arrays.asList(extraFields));
/*      */   }
/*      */   
/*      */   public static List<String> createFieldsOrder(List<String> baseFields, List<String> extraFields) {
/* 1023 */     List<String> fields = new ArrayList<String>(baseFields.size() + extraFields.size());
/* 1024 */     fields.addAll(baseFields);
/* 1025 */     fields.addAll(extraFields);
/* 1026 */     return Collections.unmodifiableList(fields);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> createFieldsOrder(String field) {
/* 1034 */     return Collections.unmodifiableList(Collections.singletonList(field));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> createFieldsOrder(String... fields) {
/* 1042 */     return Collections.unmodifiableList(Arrays.asList(fields));
/*      */   }
/*      */   
/*      */   private static <T extends Comparable<T>> List<T> sort(Collection<? extends T> c) {
/* 1046 */     List<T> list = new ArrayList<T>(c);
/* 1047 */     Collections.sort(list);
/* 1048 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<Field> getFields(boolean force) {
/* 1059 */     List<Field> flist = getFieldList();
/* 1060 */     Set<String> names = new HashSet<String>();
/* 1061 */     for (Field f : flist) {
/* 1062 */       names.add(f.getName());
/*      */     }
/*      */     
/* 1065 */     List<String> fieldOrder = fieldOrder();
/* 1066 */     if (fieldOrder.size() != flist.size() && flist.size() > 1) {
/* 1067 */       if (force) {
/* 1068 */         throw new Error("Structure.getFieldOrder() on " + getClass() + (
/* 1069 */             (fieldOrder.size() < flist.size()) ? " does not provide enough" : " provides too many") + " names [" + fieldOrder
/*      */ 
/*      */             
/* 1072 */             .size() + "] (" + 
/*      */             
/* 1074 */             sort(fieldOrder) + ") to match declared fields [" + flist
/* 1075 */             .size() + "] (" + 
/*      */             
/* 1077 */             sort(names) + ")");
/*      */       }
/*      */       
/* 1080 */       return null;
/*      */     } 
/*      */     
/* 1083 */     Set<String> orderedNames = new HashSet<String>(fieldOrder);
/* 1084 */     if (!orderedNames.equals(names)) {
/* 1085 */       throw new Error("Structure.getFieldOrder() on " + getClass() + " returns names (" + 
/*      */           
/* 1087 */           sort(fieldOrder) + ") which do not match declared field names (" + 
/*      */           
/* 1089 */           sort(names) + ")");
/*      */     }
/*      */     
/* 1092 */     sortFields(flist, fieldOrder);
/* 1093 */     return flist;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int calculateSize(boolean force) {
/* 1111 */     return calculateSize(force, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int size(Class<? extends Structure> type) {
/* 1119 */     return size(type, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static <T extends Structure> int size(Class<T> type, T value) {
/*      */     LayoutInfo info;
/* 1129 */     synchronized (layoutInfo) {
/* 1130 */       info = layoutInfo.get(type);
/*      */     } 
/* 1132 */     int sz = (info != null && !info.variable) ? info.size : -1;
/* 1133 */     if (sz == -1) {
/* 1134 */       if (value == null) {
/* 1135 */         value = newInstance(type, PLACEHOLDER_MEMORY);
/*      */       }
/* 1137 */       sz = value.size();
/*      */     } 
/* 1139 */     return sz;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int calculateSize(boolean force, boolean avoidFFIType) {
/*      */     LayoutInfo info;
/* 1150 */     int size = -1;
/* 1151 */     Class<?> clazz = getClass();
/*      */     
/* 1153 */     synchronized (layoutInfo) {
/* 1154 */       info = layoutInfo.get(clazz);
/*      */     } 
/* 1156 */     if (info == null || this.alignType != info
/* 1157 */       .alignType || this.typeMapper != info
/* 1158 */       .typeMapper) {
/* 1159 */       info = deriveLayout(force, avoidFFIType);
/*      */     }
/* 1161 */     if (info != null) {
/* 1162 */       this.structAlignment = info.alignment;
/* 1163 */       this.structFields = info.fields;
/*      */       
/* 1165 */       if (!info.variable) {
/* 1166 */         synchronized (layoutInfo) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1172 */           if (!layoutInfo.containsKey(clazz) || this.alignType != 0 || this.typeMapper != null)
/*      */           {
/*      */             
/* 1175 */             layoutInfo.put(clazz, info);
/*      */           }
/*      */         } 
/*      */       }
/* 1179 */       size = info.size;
/*      */     } 
/* 1181 */     return size;
/*      */   }
/*      */   
/*      */   private static class LayoutInfo
/*      */   {
/*      */     private LayoutInfo() {}
/*      */     
/* 1188 */     private int size = -1;
/* 1189 */     private int alignment = 1;
/* 1190 */     private final Map<String, Structure.StructField> fields = Collections.synchronizedMap(new LinkedHashMap<String, Structure.StructField>());
/* 1191 */     private int alignType = 0;
/*      */     
/*      */     private TypeMapper typeMapper;
/*      */     private boolean variable;
/*      */     private Structure.StructField typeInfoField;
/*      */   }
/*      */   
/*      */   private void validateField(String name, Class<?> type) {
/* 1199 */     if (this.typeMapper != null) {
/* 1200 */       ToNativeConverter toNative = this.typeMapper.getToNativeConverter(type);
/* 1201 */       if (toNative != null) {
/* 1202 */         validateField(name, toNative.nativeType());
/*      */         return;
/*      */       } 
/*      */     } 
/* 1206 */     if (type.isArray()) {
/* 1207 */       validateField(name, type.getComponentType());
/*      */     } else {
/*      */       
/*      */       try {
/* 1211 */         getNativeSize(type);
/*      */       }
/* 1213 */       catch (IllegalArgumentException e) {
/* 1214 */         String msg = "Invalid Structure field in " + getClass() + ", field name '" + name + "' (" + type + "): " + e.getMessage();
/* 1215 */         throw new IllegalArgumentException(msg, e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void validateFields() {
/* 1222 */     List<Field> fields = getFieldList();
/* 1223 */     for (Field f : fields) {
/* 1224 */       validateField(f.getName(), f.getType());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LayoutInfo deriveLayout(boolean force, boolean avoidFFIType) {
/* 1233 */     int calculatedSize = 0;
/* 1234 */     List<Field> fields = getFields(force);
/* 1235 */     if (fields == null) {
/* 1236 */       return null;
/*      */     }
/*      */     
/* 1239 */     LayoutInfo info = new LayoutInfo();
/* 1240 */     info.alignType = this.alignType;
/* 1241 */     info.typeMapper = this.typeMapper;
/*      */     
/* 1243 */     boolean firstField = true;
/* 1244 */     for (Iterator<Field> i = fields.iterator(); i.hasNext(); firstField = false) {
/* 1245 */       Field field = i.next();
/* 1246 */       int modifiers = field.getModifiers();
/*      */       
/* 1248 */       Class<?> type = field.getType();
/* 1249 */       if (type.isArray()) {
/* 1250 */         info.variable = true;
/*      */       }
/* 1252 */       StructField structField = new StructField();
/* 1253 */       structField.isVolatile = Modifier.isVolatile(modifiers);
/* 1254 */       structField.isReadOnly = Modifier.isFinal(modifiers);
/* 1255 */       if (structField.isReadOnly) {
/* 1256 */         if (!Platform.RO_FIELDS) {
/* 1257 */           throw new IllegalArgumentException("This VM does not support read-only fields (field '" + field
/* 1258 */               .getName() + "' within " + getClass() + ")");
/*      */         }
/*      */ 
/*      */         
/* 1262 */         field.setAccessible(true);
/*      */       } 
/* 1264 */       structField.field = field;
/* 1265 */       structField.name = field.getName();
/* 1266 */       structField.type = type;
/*      */ 
/*      */       
/* 1269 */       if (Callback.class.isAssignableFrom(type) && !type.isInterface()) {
/* 1270 */         throw new IllegalArgumentException("Structure Callback field '" + field
/* 1271 */             .getName() + "' must be an interface");
/*      */       }
/*      */       
/* 1274 */       if (type.isArray() && Structure.class
/* 1275 */         .equals(type.getComponentType())) {
/* 1276 */         String msg = "Nested Structure arrays must use a derived Structure type so that the size of the elements can be determined";
/*      */ 
/*      */         
/* 1279 */         throw new IllegalArgumentException(msg);
/*      */       } 
/*      */       
/* 1282 */       int fieldAlignment = 1;
/* 1283 */       if (Modifier.isPublic(field.getModifiers())) {
/*      */ 
/*      */ 
/*      */         
/* 1287 */         Object value = getFieldValue(structField.field);
/* 1288 */         if (value == null && type.isArray()) {
/* 1289 */           if (force) {
/* 1290 */             throw new IllegalStateException("Array fields must be initialized");
/*      */           }
/*      */           
/* 1293 */           return null;
/*      */         } 
/* 1295 */         Class<?> nativeType = type;
/* 1296 */         if (NativeMapped.class.isAssignableFrom(type)) {
/* 1297 */           NativeMappedConverter tc = NativeMappedConverter.getInstance(type);
/* 1298 */           nativeType = tc.nativeType();
/* 1299 */           structField.writeConverter = tc;
/* 1300 */           structField.readConverter = tc;
/* 1301 */           structField.context = new StructureReadContext(this, field);
/*      */         }
/* 1303 */         else if (this.typeMapper != null) {
/* 1304 */           ToNativeConverter writeConverter = this.typeMapper.getToNativeConverter(type);
/* 1305 */           FromNativeConverter readConverter = this.typeMapper.getFromNativeConverter(type);
/* 1306 */           if (writeConverter != null && readConverter != null) {
/* 1307 */             value = writeConverter.toNative(value, new StructureWriteContext(this, structField.field));
/*      */             
/* 1309 */             nativeType = (value != null) ? value.getClass() : Pointer.class;
/* 1310 */             structField.writeConverter = writeConverter;
/* 1311 */             structField.readConverter = readConverter;
/* 1312 */             structField.context = new StructureReadContext(this, field);
/*      */           }
/* 1314 */           else if (writeConverter != null || readConverter != null) {
/* 1315 */             String msg = "Structures require bidirectional type conversion for " + type;
/* 1316 */             throw new IllegalArgumentException(msg);
/*      */           } 
/*      */         } 
/*      */         
/* 1320 */         if (value == null) {
/* 1321 */           value = initializeField(structField.field, type);
/*      */         }
/*      */         
/*      */         try {
/* 1325 */           structField.size = getNativeSize(nativeType, value);
/* 1326 */           fieldAlignment = getNativeAlignment(nativeType, value, firstField);
/*      */         }
/* 1328 */         catch (IllegalArgumentException e) {
/*      */           
/* 1330 */           if (!force && this.typeMapper == null) {
/* 1331 */             return null;
/*      */           }
/* 1333 */           String msg = "Invalid Structure field in " + getClass() + ", field name '" + structField.name + "' (" + structField.type + "): " + e.getMessage();
/* 1334 */           throw new IllegalArgumentException(msg, e);
/*      */         } 
/*      */ 
/*      */         
/* 1338 */         if (fieldAlignment == 0) {
/* 1339 */           throw new Error("Field alignment is zero for field '" + structField.name + "' within " + getClass());
/*      */         }
/* 1341 */         info.alignment = Math.max(info.alignment, fieldAlignment);
/* 1342 */         if (calculatedSize % fieldAlignment != 0) {
/* 1343 */           calculatedSize += fieldAlignment - calculatedSize % fieldAlignment;
/*      */         }
/* 1345 */         if (this instanceof Union) {
/* 1346 */           structField.offset = 0;
/* 1347 */           calculatedSize = Math.max(calculatedSize, structField.size);
/*      */         } else {
/*      */           
/* 1350 */           structField.offset = calculatedSize;
/* 1351 */           calculatedSize += structField.size;
/*      */         } 
/*      */ 
/*      */         
/* 1355 */         info.fields.put(structField.name, structField);
/*      */         
/* 1357 */         if (info.typeInfoField == null || 
/* 1358 */           info.typeInfoField.size < structField.size || (
/* 1359 */           info.typeInfoField.size == structField.size && Structure.class
/* 1360 */           .isAssignableFrom(structField.type))) {
/* 1361 */           info.typeInfoField = structField;
/*      */         }
/*      */       } 
/*      */     } 
/* 1365 */     if (calculatedSize > 0) {
/* 1366 */       int size = addPadding(calculatedSize, info.alignment);
/*      */       
/* 1368 */       if (this instanceof ByValue && !avoidFFIType) {
/* 1369 */         getTypeInfo();
/*      */       }
/* 1371 */       info.size = size;
/* 1372 */       return info;
/*      */     } 
/*      */     
/* 1375 */     throw new IllegalArgumentException("Structure " + getClass() + " has unknown or zero size (ensure all fields are public)");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeFields() {
/* 1387 */     List<Field> flist = getFieldList();
/* 1388 */     for (Field f : flist) {
/*      */       try {
/* 1390 */         Object o = f.get(this);
/* 1391 */         if (o == null) {
/* 1392 */           initializeField(f, f.getType());
/*      */         }
/*      */       }
/* 1395 */       catch (Exception e) {
/* 1396 */         throw new Error("Exception reading field '" + f.getName() + "' in " + getClass(), e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private Object initializeField(Field field, Class<?> type) {
/* 1402 */     Object value = null;
/* 1403 */     if (Structure.class.isAssignableFrom(type) && 
/* 1404 */       !ByReference.class.isAssignableFrom(type)) {
/*      */       try {
/* 1406 */         value = newInstance(type, PLACEHOLDER_MEMORY);
/* 1407 */         setFieldValue(field, value);
/*      */       }
/* 1409 */       catch (IllegalArgumentException e) {
/* 1410 */         String msg = "Can't determine size of nested structure";
/* 1411 */         throw new IllegalArgumentException(msg, e);
/*      */       }
/*      */     
/* 1414 */     } else if (NativeMapped.class.isAssignableFrom(type)) {
/* 1415 */       NativeMappedConverter tc = NativeMappedConverter.getInstance(type);
/* 1416 */       value = tc.defaultValue();
/* 1417 */       setFieldValue(field, value);
/*      */     } 
/* 1419 */     return value;
/*      */   }
/*      */   
/*      */   private int addPadding(int calculatedSize) {
/* 1423 */     return addPadding(calculatedSize, this.structAlignment);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int addPadding(int calculatedSize, int alignment) {
/* 1429 */     if (this.actualAlignType != 1 && 
/* 1430 */       calculatedSize % alignment != 0) {
/* 1431 */       calculatedSize += alignment - calculatedSize % alignment;
/*      */     }
/*      */     
/* 1434 */     return calculatedSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getStructAlignment() {
/* 1441 */     if (this.size == -1)
/*      */     {
/* 1443 */       calculateSize(true);
/*      */     }
/* 1445 */     return this.structAlignment;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getNativeAlignment(Class<?> type, Object value, boolean isFirstElement) {
/* 1459 */     int alignment = 1;
/* 1460 */     if (NativeMapped.class.isAssignableFrom(type)) {
/* 1461 */       NativeMappedConverter tc = NativeMappedConverter.getInstance(type);
/* 1462 */       type = tc.nativeType();
/* 1463 */       value = tc.toNative(value, new ToNativeContext());
/*      */     } 
/* 1465 */     int size = Native.getNativeSize(type, value);
/* 1466 */     if (type.isPrimitive() || Long.class == type || Integer.class == type || Short.class == type || Character.class == type || Byte.class == type || Boolean.class == type || Float.class == type || Double.class == type) {
/*      */ 
/*      */ 
/*      */       
/* 1470 */       alignment = size;
/*      */     }
/* 1472 */     else if ((Pointer.class.isAssignableFrom(type) && !Function.class.isAssignableFrom(type)) || (Platform.HAS_BUFFERS && Buffer.class
/* 1473 */       .isAssignableFrom(type)) || Callback.class
/* 1474 */       .isAssignableFrom(type) || WString.class == type || String.class == type) {
/*      */ 
/*      */       
/* 1477 */       alignment = Native.POINTER_SIZE;
/*      */     }
/* 1479 */     else if (Structure.class.isAssignableFrom(type)) {
/* 1480 */       if (ByReference.class.isAssignableFrom(type)) {
/* 1481 */         alignment = Native.POINTER_SIZE;
/*      */       } else {
/*      */         
/* 1484 */         if (value == null)
/* 1485 */           value = newInstance(type, PLACEHOLDER_MEMORY); 
/* 1486 */         alignment = ((Structure)value).getStructAlignment();
/*      */       }
/*      */     
/* 1489 */     } else if (type.isArray()) {
/* 1490 */       alignment = getNativeAlignment(type.getComponentType(), null, isFirstElement);
/*      */     } else {
/*      */       
/* 1493 */       throw new IllegalArgumentException("Type " + type + " has unknown native alignment");
/*      */     } 
/*      */     
/* 1496 */     if (this.actualAlignType == 1) {
/* 1497 */       alignment = 1;
/*      */     }
/* 1499 */     else if (this.actualAlignType == 3) {
/* 1500 */       alignment = Math.min(8, alignment);
/*      */     }
/* 1502 */     else if (this.actualAlignType == 2) {
/*      */ 
/*      */       
/* 1505 */       if (!isFirstElement || !Platform.isMac() || !Platform.isPPC()) {
/* 1506 */         alignment = Math.min(Native.MAX_ALIGNMENT, alignment);
/*      */       }
/* 1508 */       if (!isFirstElement && Platform.isAIX() && (type == double.class || type == Double.class)) {
/* 1509 */         alignment = 4;
/*      */       }
/*      */     } 
/* 1512 */     return alignment;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1522 */     return toString(Boolean.getBoolean("jna.dump_memory"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString(boolean debug) {
/* 1531 */     return toString(0, true, debug);
/*      */   }
/*      */   
/*      */   private String format(Class<?> type) {
/* 1535 */     String s = type.getName();
/* 1536 */     int dot = s.lastIndexOf(".");
/* 1537 */     return s.substring(dot + 1);
/*      */   }
/*      */   
/*      */   private String toString(int indent, boolean showContents, boolean dumpMemory) {
/* 1541 */     ensureAllocated();
/* 1542 */     String LS = System.getProperty("line.separator");
/* 1543 */     String name = format(getClass()) + "(" + getPointer() + ")";
/* 1544 */     if (!(getPointer() instanceof Memory)) {
/* 1545 */       name = name + " (" + size() + " bytes)";
/*      */     }
/* 1547 */     String prefix = "";
/* 1548 */     for (int idx = 0; idx < indent; idx++) {
/* 1549 */       prefix = prefix + "  ";
/*      */     }
/* 1551 */     String contents = LS;
/* 1552 */     if (!showContents) {
/* 1553 */       contents = "...}";
/*      */     } else {
/* 1555 */       for (Iterator<StructField> i = fields().values().iterator(); i.hasNext(); ) {
/* 1556 */         StructField sf = i.next();
/* 1557 */         Object value = getFieldValue(sf.field);
/* 1558 */         String type = format(sf.type);
/* 1559 */         String index = "";
/* 1560 */         contents = contents + prefix;
/* 1561 */         if (sf.type.isArray() && value != null) {
/* 1562 */           type = format(sf.type.getComponentType());
/* 1563 */           index = "[" + Array.getLength(value) + "]";
/*      */         } 
/* 1565 */         contents = contents + String.format("  %s %s%s@0x%X", new Object[] { type, sf.name, index, Integer.valueOf(sf.offset) });
/* 1566 */         if (value instanceof Structure) {
/* 1567 */           value = ((Structure)value).toString(indent + 1, !(value instanceof ByReference), dumpMemory);
/*      */         }
/* 1569 */         contents = contents + "=";
/* 1570 */         if (value instanceof Long) {
/* 1571 */           contents = contents + String.format("0x%08X", new Object[] { value });
/*      */         }
/* 1573 */         else if (value instanceof Integer) {
/* 1574 */           contents = contents + String.format("0x%04X", new Object[] { value });
/*      */         }
/* 1576 */         else if (value instanceof Short) {
/* 1577 */           contents = contents + String.format("0x%02X", new Object[] { value });
/*      */         }
/* 1579 */         else if (value instanceof Byte) {
/* 1580 */           contents = contents + String.format("0x%01X", new Object[] { value });
/*      */         } else {
/*      */           
/* 1583 */           contents = contents + String.valueOf(value).trim();
/*      */         } 
/* 1585 */         contents = contents + LS;
/* 1586 */         if (!i.hasNext())
/* 1587 */           contents = contents + prefix + "}"; 
/*      */       } 
/*      */     } 
/* 1590 */     if (indent == 0 && dumpMemory) {
/* 1591 */       int BYTES_PER_ROW = 4;
/* 1592 */       contents = contents + LS + "memory dump" + LS;
/* 1593 */       byte[] buf = getPointer().getByteArray(0L, size());
/* 1594 */       for (int i = 0; i < buf.length; i++) {
/* 1595 */         if (i % 4 == 0) contents = contents + "["; 
/* 1596 */         if (buf[i] >= 0 && buf[i] < 16)
/* 1597 */           contents = contents + "0"; 
/* 1598 */         contents = contents + Integer.toHexString(buf[i] & 0xFF);
/* 1599 */         if (i % 4 == 3 && i < buf.length - 1)
/* 1600 */           contents = contents + "]" + LS; 
/*      */       } 
/* 1602 */       contents = contents + "]";
/*      */     } 
/* 1604 */     return name + " {" + contents;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Structure[] toArray(Structure[] array) {
/* 1616 */     ensureAllocated();
/* 1617 */     if (this.memory instanceof AutoAllocated) {
/*      */       
/* 1619 */       Memory m = (Memory)this.memory;
/* 1620 */       int requiredSize = array.length * size();
/* 1621 */       if (m.size() < requiredSize) {
/* 1622 */         useMemory(autoAllocate(requiredSize));
/*      */       }
/*      */     } 
/*      */     
/* 1626 */     array[0] = this;
/* 1627 */     int size = size();
/* 1628 */     for (int i = 1; i < array.length; i++) {
/* 1629 */       array[i] = newInstance(getClass(), this.memory.share((i * size), size));
/* 1630 */       array[i].conditionalAutoRead();
/*      */     } 
/*      */     
/* 1633 */     if (!(this instanceof ByValue))
/*      */     {
/* 1635 */       this.array = array;
/*      */     }
/*      */     
/* 1638 */     return array;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Structure[] toArray(int size) {
/* 1651 */     return toArray((Structure[])Array.newInstance(getClass(), size));
/*      */   }
/*      */   
/*      */   private Class<?> baseClass() {
/* 1655 */     if ((this instanceof ByReference || this instanceof ByValue) && Structure.class
/*      */       
/* 1657 */       .isAssignableFrom(getClass().getSuperclass())) {
/* 1658 */       return getClass().getSuperclass();
/*      */     }
/* 1660 */     return getClass();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean dataEquals(Structure s) {
/* 1669 */     return dataEquals(s, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean dataEquals(Structure s, boolean clear) {
/* 1679 */     if (clear) {
/* 1680 */       s.getPointer().clear(s.size());
/* 1681 */       s.write();
/* 1682 */       getPointer().clear(size());
/* 1683 */       write();
/*      */     } 
/* 1685 */     byte[] data = s.getPointer().getByteArray(0L, s.size());
/* 1686 */     byte[] ref = getPointer().getByteArray(0L, size());
/* 1687 */     if (data.length == ref.length) {
/* 1688 */       for (int i = 0; i < data.length; i++) {
/* 1689 */         if (data[i] != ref[i]) {
/* 1690 */           return false;
/*      */         }
/*      */       } 
/* 1693 */       return true;
/*      */     } 
/* 1695 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/* 1703 */     return (o instanceof Structure && o
/* 1704 */       .getClass() == getClass() && ((Structure)o)
/* 1705 */       .getPointer().equals(getPointer()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1713 */     Pointer p = getPointer();
/* 1714 */     if (p != null) {
/* 1715 */       return getPointer().hashCode();
/*      */     }
/* 1717 */     return getClass().hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void cacheTypeInfo(Pointer p) {
/* 1724 */     this.typeInfo = p.peer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Pointer getFieldTypeInfo(StructField f) {
/* 1732 */     Class<?> type = f.type;
/* 1733 */     Object value = getFieldValue(f.field);
/* 1734 */     if (this.typeMapper != null) {
/* 1735 */       ToNativeConverter nc = this.typeMapper.getToNativeConverter(type);
/* 1736 */       if (nc != null) {
/* 1737 */         type = nc.nativeType();
/* 1738 */         value = nc.toNative(value, new ToNativeContext());
/*      */       } 
/*      */     } 
/* 1741 */     return FFIType.get(value, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Pointer getTypeInfo() {
/* 1748 */     Pointer p = getTypeInfo(this);
/* 1749 */     cacheTypeInfo(p);
/* 1750 */     return p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoSynch(boolean auto) {
/* 1774 */     setAutoRead(auto);
/* 1775 */     setAutoWrite(auto);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoRead(boolean auto) {
/* 1783 */     this.autoRead = auto;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoRead() {
/* 1791 */     return this.autoRead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoWrite(boolean auto) {
/* 1799 */     this.autoWrite = auto;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoWrite() {
/* 1807 */     return this.autoWrite;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Pointer getTypeInfo(Object obj) {
/* 1815 */     return FFIType.get(obj);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T extends Structure> T newInstance(Class<T> type, long init) {
/*      */     try {
/* 1824 */       T s = newInstance(type, (init == 0L) ? PLACEHOLDER_MEMORY : new Pointer(init));
/* 1825 */       if (init != 0L) {
/* 1826 */         s.conditionalAutoRead();
/*      */       }
/* 1828 */       return s;
/*      */     }
/* 1830 */     catch (Throwable e) {
/* 1831 */       LOG.log(Level.WARNING, "JNA: Error creating structure", e);
/* 1832 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends Structure> T newInstance(Class<T> type, Pointer init) throws IllegalArgumentException {
/*      */     try {
/* 1845 */       Constructor<T> ctor = type.getConstructor(new Class[] { Pointer.class });
/* 1846 */       return ctor.newInstance(new Object[] { init });
/*      */     }
/* 1848 */     catch (NoSuchMethodException noSuchMethodException) {
/*      */ 
/*      */     
/* 1851 */     } catch (SecurityException securityException) {
/*      */ 
/*      */     
/* 1854 */     } catch (InstantiationException e) {
/* 1855 */       String msg = "Can't instantiate " + type;
/* 1856 */       throw new IllegalArgumentException(msg, e);
/*      */     }
/* 1858 */     catch (IllegalAccessException e) {
/* 1859 */       String msg = "Instantiation of " + type + " (Pointer) not allowed, is it public?";
/* 1860 */       throw new IllegalArgumentException(msg, e);
/*      */     }
/* 1862 */     catch (InvocationTargetException e) {
/* 1863 */       String msg = "Exception thrown while instantiating an instance of " + type;
/* 1864 */       throw new IllegalArgumentException(msg, e);
/*      */     } 
/* 1866 */     T s = newInstance(type);
/* 1867 */     if (init != PLACEHOLDER_MEMORY) {
/* 1868 */       s.useMemory(init);
/*      */     }
/* 1870 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends Structure> T newInstance(Class<T> type) throws IllegalArgumentException {
/* 1880 */     Structure structure = Klass.<Structure>newInstance(type);
/* 1881 */     if (structure instanceof ByValue) {
/* 1882 */       structure.allocateMemory();
/*      */     }
/* 1884 */     return (T)structure;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   StructField typeInfoField() {
/*      */     LayoutInfo info;
/* 1893 */     synchronized (layoutInfo) {
/* 1894 */       info = layoutInfo.get(getClass());
/*      */     } 
/* 1896 */     if (info != null) {
/* 1897 */       return info.typeInfoField;
/*      */     }
/* 1899 */     return null;
/*      */   }
/*      */   
/*      */   protected static class StructField {
/*      */     public String name;
/*      */     public Class<?> type;
/*      */     public Field field;
/* 1906 */     public int size = -1;
/* 1907 */     public int offset = -1;
/*      */     public boolean isVolatile;
/*      */     public boolean isReadOnly;
/*      */     public FromNativeConverter readConverter;
/*      */     public ToNativeConverter writeConverter;
/*      */     public FromNativeContext context;
/*      */     
/*      */     public String toString() {
/* 1915 */       return this.name + "@" + this.offset + "[" + this.size + "] (" + this.type + ")";
/*      */     }
/*      */   }
/*      */   
/*      */   @FieldOrder({"size", "alignment", "type", "elements"})
/*      */   static class FFIType
/*      */     extends Structure {
/*      */     public static class size_t
/*      */       extends IntegerType {
/*      */       private static final long serialVersionUID = 1L;
/*      */       
/*      */       public size_t() {
/* 1927 */         this(0L); } public size_t(long value) {
/* 1928 */         super(Native.SIZE_T_SIZE, value);
/*      */       }
/*      */     }
/* 1931 */     private static final Map<Object, Object> typeInfoMap = new WeakHashMap<Object, Object>(); private static final int FFI_TYPE_STRUCT = 13;
/*      */     public size_t size;
/*      */     public short alignment;
/*      */     public short type;
/*      */     public Pointer elements;
/*      */     
/*      */     private static class FFITypes {
/*      */       private static Pointer ffi_type_void;
/*      */       private static Pointer ffi_type_float;
/*      */       private static Pointer ffi_type_double;
/*      */       private static Pointer ffi_type_longdouble;
/*      */       private static Pointer ffi_type_uint8;
/*      */       private static Pointer ffi_type_sint8;
/*      */       private static Pointer ffi_type_uint16;
/*      */       private static Pointer ffi_type_sint16;
/*      */       private static Pointer ffi_type_uint32;
/*      */       private static Pointer ffi_type_sint32;
/*      */       private static Pointer ffi_type_uint64;
/*      */       private static Pointer ffi_type_sint64;
/*      */       private static Pointer ffi_type_pointer; }
/*      */     
/*      */     static {
/* 1953 */       if (Native.POINTER_SIZE == 0)
/* 1954 */         throw new Error("Native library not initialized"); 
/* 1955 */       if (FFITypes.ffi_type_void == null)
/* 1956 */         throw new Error("FFI types not initialized"); 
/* 1957 */       typeInfoMap.put(void.class, FFITypes.ffi_type_void);
/* 1958 */       typeInfoMap.put(Void.class, FFITypes.ffi_type_void);
/* 1959 */       typeInfoMap.put(float.class, FFITypes.ffi_type_float);
/* 1960 */       typeInfoMap.put(Float.class, FFITypes.ffi_type_float);
/* 1961 */       typeInfoMap.put(double.class, FFITypes.ffi_type_double);
/* 1962 */       typeInfoMap.put(Double.class, FFITypes.ffi_type_double);
/* 1963 */       typeInfoMap.put(long.class, FFITypes.ffi_type_sint64);
/* 1964 */       typeInfoMap.put(Long.class, FFITypes.ffi_type_sint64);
/* 1965 */       typeInfoMap.put(int.class, FFITypes.ffi_type_sint32);
/* 1966 */       typeInfoMap.put(Integer.class, FFITypes.ffi_type_sint32);
/* 1967 */       typeInfoMap.put(short.class, FFITypes.ffi_type_sint16);
/* 1968 */       typeInfoMap.put(Short.class, FFITypes.ffi_type_sint16);
/*      */       
/* 1970 */       Pointer ctype = (Native.WCHAR_SIZE == 2) ? FFITypes.ffi_type_uint16 : FFITypes.ffi_type_uint32;
/* 1971 */       typeInfoMap.put(char.class, ctype);
/* 1972 */       typeInfoMap.put(Character.class, ctype);
/* 1973 */       typeInfoMap.put(byte.class, FFITypes.ffi_type_sint8);
/* 1974 */       typeInfoMap.put(Byte.class, FFITypes.ffi_type_sint8);
/* 1975 */       typeInfoMap.put(Pointer.class, FFITypes.ffi_type_pointer);
/* 1976 */       typeInfoMap.put(String.class, FFITypes.ffi_type_pointer);
/* 1977 */       typeInfoMap.put(WString.class, FFITypes.ffi_type_pointer);
/* 1978 */       typeInfoMap.put(boolean.class, FFITypes.ffi_type_uint32);
/* 1979 */       typeInfoMap.put(Boolean.class, FFITypes.ffi_type_uint32);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private FFIType(Structure ref) {
/*      */       Pointer[] els;
/* 1986 */       this.type = 13;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1991 */       ref.ensureAllocated(true);
/*      */       
/* 1993 */       if (ref instanceof Union) {
/* 1994 */         Structure.StructField sf = ((Union)ref).typeInfoField();
/*      */         
/* 1996 */         els = new Pointer[] { get(ref.getFieldValue(sf.field), sf.type), null };
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2001 */         els = new Pointer[ref.fields().size() + 1];
/* 2002 */         int idx = 0;
/* 2003 */         for (Structure.StructField sf : ref.fields().values()) {
/* 2004 */           els[idx++] = ref.getFieldTypeInfo(sf);
/*      */         }
/*      */       } 
/* 2007 */       init(els);
/*      */     }
/*      */     private FFIType(Object array, Class<?> type) {
/*      */       this.type = 13;
/* 2011 */       int length = Array.getLength(array);
/* 2012 */       Pointer[] els = new Pointer[length + 1];
/* 2013 */       Pointer p = get((Object)null, type.getComponentType());
/* 2014 */       for (int i = 0; i < length; i++) {
/* 2015 */         els[i] = p;
/*      */       }
/* 2017 */       init(els);
/*      */     }
/*      */     
/*      */     private void init(Pointer[] els) {
/* 2021 */       this.elements = new Memory((Native.POINTER_SIZE * els.length));
/* 2022 */       this.elements.write(0L, els, 0, els.length);
/* 2023 */       write();
/*      */     }
/*      */ 
/*      */     
/*      */     static Pointer get(Object obj) {
/* 2028 */       if (obj == null)
/* 2029 */         return FFITypes.ffi_type_pointer; 
/* 2030 */       if (obj instanceof Class)
/* 2031 */         return get((Object)null, (Class)obj); 
/* 2032 */       return get(obj, obj.getClass());
/*      */     }
/*      */     
/*      */     private static Pointer get(Object obj, Class<?> cls) {
/* 2036 */       TypeMapper mapper = Native.getTypeMapper(cls);
/* 2037 */       if (mapper != null) {
/* 2038 */         ToNativeConverter nc = mapper.getToNativeConverter(cls);
/* 2039 */         if (nc != null) {
/* 2040 */           cls = nc.nativeType();
/*      */         }
/*      */       } 
/* 2043 */       synchronized (typeInfoMap) {
/* 2044 */         Object o = typeInfoMap.get(cls);
/* 2045 */         if (o instanceof Pointer) {
/* 2046 */           return (Pointer)o;
/*      */         }
/* 2048 */         if (o instanceof FFIType) {
/* 2049 */           return ((FFIType)o).getPointer();
/*      */         }
/* 2051 */         if ((Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(cls)) || Callback.class
/* 2052 */           .isAssignableFrom(cls)) {
/* 2053 */           typeInfoMap.put(cls, FFITypes.ffi_type_pointer);
/* 2054 */           return FFITypes.ffi_type_pointer;
/*      */         } 
/* 2056 */         if (Structure.class.isAssignableFrom(cls)) {
/* 2057 */           if (obj == null) obj = newInstance(cls, Structure.PLACEHOLDER_MEMORY); 
/* 2058 */           if (Structure.ByReference.class.isAssignableFrom(cls)) {
/* 2059 */             typeInfoMap.put(cls, FFITypes.ffi_type_pointer);
/* 2060 */             return FFITypes.ffi_type_pointer;
/*      */           } 
/* 2062 */           FFIType type = new FFIType((Structure)obj);
/* 2063 */           typeInfoMap.put(cls, type);
/* 2064 */           return type.getPointer();
/*      */         } 
/* 2066 */         if (NativeMapped.class.isAssignableFrom(cls)) {
/* 2067 */           NativeMappedConverter c = NativeMappedConverter.getInstance(cls);
/* 2068 */           return get(c.toNative(obj, new ToNativeContext()), c.nativeType());
/*      */         } 
/* 2070 */         if (cls.isArray()) {
/* 2071 */           FFIType type = new FFIType(obj, cls);
/*      */           
/* 2073 */           typeInfoMap.put(obj, type);
/* 2074 */           return type.getPointer();
/*      */         } 
/* 2076 */         throw new IllegalArgumentException("Unsupported type " + cls);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class AutoAllocated extends Memory {
/*      */     public AutoAllocated(int size) {
/* 2083 */       super(size);
/*      */       
/* 2085 */       clear();
/*      */     }
/*      */     
/*      */     public String toString() {
/* 2089 */       return "auto-" + super.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static void structureArrayCheck(Structure[] ss) {
/* 2094 */     if (ByReference[].class.isAssignableFrom(ss.getClass())) {
/*      */       return;
/*      */     }
/* 2097 */     Pointer base = ss[0].getPointer();
/* 2098 */     int size = ss[0].size();
/* 2099 */     for (int si = 1; si < ss.length; si++) {
/* 2100 */       if ((ss[si].getPointer()).peer != base.peer + (size * si)) {
/* 2101 */         String msg = "Structure array elements must use contiguous memory (bad backing address at Structure array index " + si + ")";
/*      */         
/* 2103 */         throw new IllegalArgumentException(msg);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void autoRead(Structure[] ss) {
/* 2109 */     structureArrayCheck(ss);
/* 2110 */     if ((ss[0]).array == ss) {
/* 2111 */       ss[0].autoRead();
/*      */     } else {
/*      */       
/* 2114 */       for (int si = 0; si < ss.length; si++) {
/* 2115 */         if (ss[si] != null) {
/* 2116 */           ss[si].autoRead();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void autoRead() {
/* 2123 */     if (getAutoRead()) {
/* 2124 */       read();
/* 2125 */       if (this.array != null) {
/* 2126 */         for (int i = 1; i < this.array.length; i++) {
/* 2127 */           this.array[i].autoRead();
/*      */         }
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void autoWrite(Structure[] ss) {
/* 2134 */     structureArrayCheck(ss);
/* 2135 */     if ((ss[0]).array == ss) {
/* 2136 */       ss[0].autoWrite();
/*      */     } else {
/*      */       
/* 2139 */       for (int si = 0; si < ss.length; si++) {
/* 2140 */         if (ss[si] != null) {
/* 2141 */           ss[si].autoWrite();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void autoWrite() {
/* 2148 */     if (getAutoWrite()) {
/* 2149 */       write();
/* 2150 */       if (this.array != null) {
/* 2151 */         for (int i = 1; i < this.array.length; i++) {
/* 2152 */           this.array[i].autoWrite();
/*      */         }
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getNativeSize(Class<?> nativeType) {
/* 2164 */     return getNativeSize(nativeType, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getNativeSize(Class<?> nativeType, Object value) {
/* 2174 */     return Native.getNativeSize(nativeType, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2180 */   private static final Pointer PLACEHOLDER_MEMORY = new Pointer(0L) {
/*      */       public Pointer share(long offset, long sz) {
/* 2182 */         return this;
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */   
/*      */   static void validate(Class<? extends Structure> cls) {
/*      */     try {
/* 2190 */       cls.getConstructor(new Class[0]);
/*      */       return;
/* 2192 */     } catch (NoSuchMethodException noSuchMethodException) {
/*      */     
/* 2194 */     } catch (SecurityException securityException) {}
/*      */     
/* 2196 */     throw new IllegalArgumentException("No suitable constructor found for class: " + cls.getName());
/*      */   }
/*      */   
/*      */   @Documented
/*      */   @Retention(RetentionPolicy.RUNTIME)
/*      */   @Target({ElementType.TYPE})
/*      */   public static @interface FieldOrder {
/*      */     String[] value();
/*      */   }
/*      */   
/*      */   public static interface ByReference {}
/*      */   
/*      */   public static interface ByValue {}
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/Structure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */