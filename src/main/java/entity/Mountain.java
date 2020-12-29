package entity;

import entity.BaseIdentify;
import entity.Group;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Mountains class that describe mountain
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Mountain.getMountainByName",
                query = "SELECT g FROM Mountain g WHERE g.name = :mountainName"),
        // SELECT groups FROM Mountain
        @NamedQuery(name = "Mountain.getMountainByCountry",
                query = "SELECT g FROM Mountain g WHERE g.country = :country")
})
public class Mountain extends BaseIdentify {


    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false)
    private int high;

    @OneToMany(mappedBy = "mountain", fetch = FetchType.LAZY)
    private List<Group> groups;

    /**
     * Mountain class constructor
     *
     * @param name name of mountain
     * @param high high of muntain
     */
    public Mountain(String name, int high) {
        this.setName(name);
        this.setHigh(high);
    }

    /**
     * Mountain class constructor
     *
     * @param name    name of mountain
     * @param high    high of mountain
     * @param country country with mountain
     */
    public Mountain(String name, int high, String country) {
        this.setName(name);
        this.setHigh(high);
        this.setCountry(country);
    }

    /**
     * Method for receive name of mountain
     *
     * @return name of mountain
     */
    public String getName() {
        return name;
    }

    /**
     * Method for set mountsan's name
     *
     * @param name name of mountains. Must be more 3 characters
     */
    private void setName(String name) {
        if (Objects.isNull(name) || name.length() < 4)
            throw new IllegalArgumentException("Name of mountain must be 4 or more characters");
        this.name = name;
    }

    /**
     * Method for receive country with mountain
     *
     * @return country with mountains
     */
    public String getCountry() {
        return country;
    }

    /**
     * Method for set country with mountain
     *
     * @param country country with mountain. Must be more 3 characters
     */
    public void setCountry(String country) {
        if (Objects.isNull(country) || country.length() < 4)
            throw new IllegalArgumentException("Name of country  must be 4 or more characters");
        this.country = country.toLowerCase();
    }

    /**
     * Method for receive high of mountain
     *
     * @return high of mountain
     */
    public int getHigh() {
        return high;
    }

    /**
     * Method for set high of mountain
     *
     * @param high high of mountain. Must be greater then 100 meters
     */
    private void setHigh(int high) {
        if (high < 100) throw new IllegalArgumentException("High of mountains must be more 100 meters");
        this.high = high;
    }
}
