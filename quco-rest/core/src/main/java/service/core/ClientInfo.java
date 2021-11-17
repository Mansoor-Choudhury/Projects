package service.core;

import java.io.Serializable;

/**
 * Interface to define the state to be stored in ClientInfo objects
 *
 * @author Mansoor
 *
 */
public class ClientInfo implements Serializable {

    /**
     * Constructor to Clientinfo class
     *
     * @param name - client name
     * @param sex - gender
     * @param age - client's age
     * @param points - points
     * @param noClaims - claim number
     * @param licenseNumber - license number
     */
    public ClientInfo(String name, char sex, int age, int points, int noClaims, String licenseNumber) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.points = points;
        this.noClaims = noClaims;
        this.licenseNumber = licenseNumber;
    }

    public ClientInfo() {}

    /**
     * Public fields are used as modern best practice argues that use of set/get
     * methods is unnecessary as (1) set/get makes the field mutable anyway, and
     * (2) set/get introduces additional method calls, which reduces performance.
     */
    private String name;
    private char sex;
    private char gender;
    private int age;
    private int points;
    private int noClaims;
    private String licenseNumber;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setSex(char sex){
        this.sex = sex;
    }
    public char getSex(){
        return sex;
    }

    public void setGender(char gender){
        this.gender = gender;
    }
    public char getGender(){
        return gender;
    }

    public void setNoClaims(int noClaims){
        this.noClaims = noClaims;
    }
    public int getNoClaims(){
        return noClaims;
    }

    public void setPoints(int points){
        this.points = points;
    }
    public int getPoints(){
        return points;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }

    public void setLicenseNumber(String licenseNumber){
        this.licenseNumber = licenseNumber;
    }
    public String getLicenseNumber(){
        return licenseNumber;
    }
}
