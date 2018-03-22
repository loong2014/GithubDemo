package leeco.hellogithub.sqlite.greendao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "greendaoinfo")
public class GreenDaoInfo {

    @Id(autoincrement = true)
    private long id;

    private String userName;

    @Property(nameInDb = "age")
    private int age;

    @Property
    private boolean gender;

    @Transient
    private String info;

    @Generated(hash = 76287441)
    public GreenDaoInfo(long id, String userName, int age, boolean gender) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
    }

    @Generated(hash = 918883251)
    public GreenDaoInfo() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getGender() {
        return this.gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

}
