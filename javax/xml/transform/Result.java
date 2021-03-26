package javax.xml.transform;

public interface Result {
  public static final String PI_DISABLE_OUTPUT_ESCAPING = "javax.xml.transform.disable-output-escaping";
  
  public static final String PI_ENABLE_OUTPUT_ESCAPING = "javax.xml.transform.enable-output-escaping";
  
  void setSystemId(String paramString);
  
  String getSystemId();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/Result.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */