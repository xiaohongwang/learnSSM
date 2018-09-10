public class Outer {

    class Inter{
        private int a = 1;

         void f(){
             System.out.println(a);
         }

         private void m(){
             System.out.println("m");
         }
    }

    Inter  inter =  new Inter();

    void d(){
        int a = inter.a;
        inter.m();
    }

}
