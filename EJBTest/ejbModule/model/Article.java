package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ARTICLE database table.
 * 
 */
@Entity
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private List<Section> sections;

	public Article() {
	}


	@Id
	@SequenceGenerator(name="ARTICLE_ID_GENERATOR", sequenceName="ARTICLE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_ID_GENERATOR")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//bi-directional many-to-one association to Section
	@OneToMany(mappedBy="article", fetch=FetchType.EAGER)
	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Section addSection(Section section) {
		getSections().add(section);
		section.setArticle(this);

		return section;
	}

	public Section removeSection(Section section) {
		getSections().remove(section);
		section.setArticle(null);

		return section;
	}

}