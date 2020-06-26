package xyz.gabear.learn.javase.annotation.example;

@Table("group")
public class Group {
    @Column("id")
    private int id;
    @Column("cn_name")
    private String cnName;
    @Column("en_name")
    private String enName;
    @Column("contact")
    private String contact;
    @Column("amount")
    private int amount;
    @Column("desc")
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
