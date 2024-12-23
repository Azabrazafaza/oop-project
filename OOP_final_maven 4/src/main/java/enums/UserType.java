package enums;

public enum UserType {
    STUDENT(1),
    TEACHER(2),
    MANAGER(3),
    TECHSUPPORT(4),
    ADMIN(5);

    int typeId;

    UserType(int id){
        typeId = id;
    }

    public String toString() {
        return Integer.toString(this.typeId);
    }


}
