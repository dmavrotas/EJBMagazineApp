package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EDITION database table.
 * 
 */
@Entity
@NamedQuery(name="Edition.findAll", query="SELECT e FROM Edition e")
public class Edition implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String edition;
	private Catalog catalog;
	private Section section;

	public Edition() {
	}


	@Id
	@SequenceGenerator(name="EDITION_ID_GENERATOR", sequenceName="EDITION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EDITION_ID_GENERATOR")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getEdition() {
		return this.edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}


	//bi-directional many-to-one association to Catalog
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="ID")
	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}


	//bi-directional many-to-one association to Section
	@ManyToOne
	@JoinColumn(name="ID")
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}