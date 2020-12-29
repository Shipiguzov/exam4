package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group extends BaseIdentify {

    @ManyToOne()
    private Mountain mountain;

    @Column(nullable = false)
    private boolean open = true;

    @Column()
    private LocalDate date = LocalDate.now();

    @Column(nullable = false)
    private int duration;

    @ManyToMany
    private List<Alpinist> alpinistList;

    public Group() {
        alpinistList = new ArrayList<>();
    }

    public Group(Mountain mountain, boolean open, LocalDate date) {
        this.setMountain(mountain);
        this.open = open;
        this.setDate(date);
    }

    /**
     * Method for receive mountain for this group
     * @return mountain for this group
     */
    public Mountain getMountain() {
        return mountain;
    }

    /**
     * Method for set mountain for this group
     * @param mountain mountain for this group
     */
    public void setMountain(Mountain mountain) {
        if (mountain == null) throw new IllegalArgumentException("Mountain is null");
        this.mountain = mountain;
    }

    /**
     * Is group still open for change alpinists list
     * @return true - group available for change alpinists list
     *         false - group is nit available for change alpinists list
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Method for set available group for change alpinists list
     * @param open true - group available for change alpinists list
     *             false - group is nit available for change alpinists list
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Method for receive data of group hike
     * @return date of group hike
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Method for get data of group hike
     * @param date group hie
     */
    public void setDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) throw new IllegalArgumentException("Date of start hiking in past");
        this.date = date;
    }

    /**
     * Method for receive duration of group hike
     * @return duration of group hike
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Method for set duration of group hike
     * @param duration duration of group hike
     */
    public void setDuration(int duration) {
        if (duration < 2) throw new IllegalArgumentException("Duration of hiking must be 1 or more days");
        this.duration = duration;
    }

    /**
     * Method for receive list of alpinist from group
     * @return list of alpinist from group
     */
    public List<Alpinist> getAlpinistList() {
        return alpinistList;
    }

    /**
     * Method add alpinist in group
     * @param alpinist alpinist which will add to group
     */
    public void addAlpinist(Alpinist alpinist) {
        if (alpinist == null) throw new IllegalArgumentException("Alpinist is null");
        if (alpinist.checkAlpinistGroups(this)) {
            alpinistList.add(alpinist);
            alpinist.addAlpinistToGroup(this);
        }
        else System.out.println("This alpinist go to the hiking in date jf this group");
    }
}
