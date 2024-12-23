package database;

public enum DatabaseTables {

    //
    //requests
    //news
    //

    USERS("users"),
    MESSAGES("messaages")











    ;

    String tableName;

    DatabaseTables(String name){
        tableName = name;
    }

    public String toString() {
        return this.tableName;
    }

}
