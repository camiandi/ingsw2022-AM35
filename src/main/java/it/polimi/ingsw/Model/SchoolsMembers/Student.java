package it.polimi.ingsw.Model.SchoolsMembers;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = -8625622548772539723L;
    private final Color color;

    /**
     * @param color is the attribute of Class COLOR of the object of Student
     * Student(Color c) is the constructor of the class Student which has as parameter an object of the Class Color
     */
    public Student(Color color){
        this.color = color;
    }

    /**
     * @return the Color value of the object Student
     */
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Student: " + "color = " + color ;
    }

    public String colorToString(){return color.toString();}
}
