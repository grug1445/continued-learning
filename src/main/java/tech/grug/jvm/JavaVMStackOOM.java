package tech.grug.jvm;

public class JavaVMStackOOM {
    private void donotStop(){
        while (true){
        }
    }

    public void stackLeakByThead(){
        while (true){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    donotStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable{
        JavaVMStackOOM javaVMStackOOM=new JavaVMStackOOM();
        javaVMStackOOM.stackLeakByThead();
        String str1=new String();
        str1.intern();
    }

}
