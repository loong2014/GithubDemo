package leeco.hellogithub.sqlite.ormlite;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "ormliteinfo")
public class OrmLiteInfo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String userName;

    @DatabaseField
    private int age;

    @DatabaseField
    private boolean gender;

    private String info;

    public OrmLiteInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
