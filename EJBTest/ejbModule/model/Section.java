package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "SECTION" database table.
 * 
 */
@Entity
@Table(name="\"SECTION\"")
@NamedQuery(name="Section.findAll", query="SELECT s FROM Section s")
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String sectionname;
	private Article article;
	private List<Edition> editions;

	public Section() {
	}


	@Id
	@SequenceGenerator(name="\"SECTION\"_ID_GENERATOR", sequenceName="\"SECTION\"_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="\"SECTION\"_ID_GENERATOR")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getSectionname() {
		return this.sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}


	//bi-directional many-to-one association to Article
	@ManyToOne
	@JoinColumn(name="ID")
	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}


	//bi-directional many-to-one association to Edition
	@OneToMany(mappedBy="section", fetch=FetchType.EAGER)
	public List<Edition> getEditions() {
		return this.editions;
	}

	public void setEditions(List<Edition> editions) {
		this.editions = editions;
	}

	public Edition addEdition(Edition edition) {
		getEditions().add(edition);
		edition.setSection(this);

		return edition;
	}

	public Edition removeEdition(Edition edition) {
		getEditions().remove(edition);
		edition.setSection(null);

		return edition;
	}

}