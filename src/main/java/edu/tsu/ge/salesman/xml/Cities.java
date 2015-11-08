
package edu.tsu.ge.salesman.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "city"
})
@XmlRootElement(name = "cities")
public class Cities {

    @XmlElement(required = true)
    protected List<City> city;
    @XmlAttribute(name = "total")
    protected Integer total;

    public List<City> getCity() {
        if (city == null) {
            city = new ArrayList<>();
        }
        return this.city;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer value) {
        this.total = value;
    }

}
