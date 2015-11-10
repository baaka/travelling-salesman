package edu.tsu.ge.salesman.input.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class City {
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "x")
    protected Integer x;
    @XmlAttribute(name = "y")
    protected Integer y;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer value) {
        this.x = value;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer value) {
        this.y = value;
    }
}