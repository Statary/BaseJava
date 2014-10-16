package basejava.access_modifiers;

class OverrideCheck extends ProtClass{

    public static void main(String[] args) {
      OverrideCheck oc = new OverrideCheck();
      oc.TestProt();
    }

  @Override
  protected void TestProt() {
    System.out.println("overrided");
  }

    
}
