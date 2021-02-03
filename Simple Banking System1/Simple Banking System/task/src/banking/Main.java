package banking;


import banking.controller.MainControl;

public class Main {
    public static void main(String[] args) {
        String[] args1 = {"-fileName", "db.s3db"};
        MainControl mainControl = new MainControl();
        mainControl.mainMethod(args1);
    }
}
