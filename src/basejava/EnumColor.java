package basejava;


public enum EnumColor {
  YELLOW("YE")
  , BLACK("BL")
  , WHITE("WH")
  ;
  
  private String strColor;
  
  private EnumColor(String c)
  {
    strColor = c;
  }
  
  public String GetColor()
  {
    return strColor;
  }
  
  @Override  
  public String toString() {
    return super.toString(); //To change body of generated methods, choose Tools | Templates.
  }
}
