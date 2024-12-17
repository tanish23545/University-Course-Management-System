public abstract class User {
    protected String name;
    protected String id;
    protected String pass;

    public User(String _name, String _id, String _pass){
        this.name = _name;
        this.id = _id;
        this.pass = _pass;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public abstract void menu();
}
