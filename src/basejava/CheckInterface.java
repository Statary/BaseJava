package basejava;


public class CheckInterface implements Interfacable{

  public void CheckInterface() {
//    super.();
  }
  @Override
  public void run() {
    System.out.println("sadasd");
  }

  public static void main(String[] args){
    CheckInterface CInterf = new CheckInterface();
    CInterf.run();
  }
}

interface Interfacable {
//  public Interfacable();
  public void run();
}
