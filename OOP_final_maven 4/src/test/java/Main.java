import database.DatabaaseeActions;

public class Main {
    public static void main(String[] args) {
        Object a = DatabaaseeActions.findById("users",1);
        System.out.println(a.toString());
    }
}
