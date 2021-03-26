package org.xml.sax.helpers;

class NewInstance {
  private static final boolean b = true;
  
  static Class a;
  
  static Object a(ClassLoader paramClassLoader, String paramString) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Class clazz;
    if (paramClassLoader == null) {
      clazz = Class.forName(paramString);
    } else {
      try {
        clazz = paramClassLoader.loadClass(paramString);
      } catch (ClassNotFoundException classNotFoundException) {
        paramClassLoader = ((a == null) ? (a = a("org.xml.sax.helpers.NewInstance")) : a).getClassLoader();
        if (paramClassLoader != null) {
          clazz = paramClassLoader.loadClass(paramString);
        } else {
          clazz = Class.forName(paramString);
        } 
      } 
    } 
    return clazz.newInstance();
  }
  
  static ClassLoader a() {
    ClassLoader classLoader = SecuritySupport.a();
    if (classLoader == null)
      classLoader = ((a == null) ? (a = a("org.xml.sax.helpers.NewInstance")) : a).getClassLoader(); 
    return classLoader;
  }
  
  static Class a(String paramString) {
    try {
      return Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/helpers/NewInstance.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */