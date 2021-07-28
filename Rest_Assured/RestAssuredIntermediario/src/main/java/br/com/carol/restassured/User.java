package br.com.carol.restassured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlAttribute
    private Long id;
    private String name;
    private Integer age;
    private Double salary;

    // construtor para o XML
    public User(){
    }

    // construtor JSON
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString(){
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }
}
