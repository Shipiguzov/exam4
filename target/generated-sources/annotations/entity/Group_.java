package entity;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Group.class)
public abstract class Group_ extends entity.BaseIdentify_ {

	public static volatile SingularAttribute<Group, LocalDate> date;
	public static volatile SingularAttribute<Group, Integer> duration;
	public static volatile SingularAttribute<Group, Mountain> mountain;
	public static volatile SingularAttribute<Group, Boolean> open;
	public static volatile ListAttribute<Group, Alpinist> alpinistList;

	public static final String DATE = "date";
	public static final String DURATION = "duration";
	public static final String MOUNTAIN = "mountain";
	public static final String OPEN = "open";
	public static final String ALPINIST_LIST = "alpinistList";

}

