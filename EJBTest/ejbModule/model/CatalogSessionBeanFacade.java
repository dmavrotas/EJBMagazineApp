package model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class CatalogSessionBeanFacade
 */
@Stateless(mappedName = "Model-SessionEJB")
@LocalBean
public class CatalogSessionBeanFacade implements CatalogSessionBeanFacadeRemote {

	/**
	 * Entity Manager
	 */
	@PersistenceContext(unitName="EJBTest")
	EntityManager EJBTest;
	
    /**
     * Default constructor. 
     */
    public CatalogSessionBeanFacade() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Getter Methods
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Edition> getAllEditions() {
    	ArrayList<Edition> editions = new ArrayList<Edition>();
    	
    	Query query = EJBTest.createNamedQuery("findEditionAll");
    	
    	if(query == null) return null;
    	
    	for(Object edition : query.getResultList()) {
    		if(edition instanceof Edition) {
        		editions.add((Edition)edition);
    		}
    	}
    	
    	return editions;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Section> getAllSections() {
    	ArrayList<Section> sections = new ArrayList<Section>();
    	
    	Query query = EJBTest.createNamedQuery("findSectionAll");
    	
    	if(query == null) return null;
    	
    	for(Object section : query.getResultList()) {
    		if(section instanceof Section) {
    			sections.add((Section)section);
    		}
    	}
    	
    	return sections;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Article> getAllArticles() {
    	ArrayList<Article> articles = new ArrayList<Article>();
    	
    	Query query = EJBTest.createNamedQuery("findArticleAll");
    	
    	if(query == null) return null;
    	
    	for(Object article : query.getResultList()) {
    		if(article instanceof Article) {
    			articles.add((Article)article);
    		}
    	}
    	
    	return articles;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Catalog> getAllCatalogs() {
    	ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
    	
    	Query query = EJBTest.createNamedQuery("findCatalogAll");
    	
    	if(query == null) return null;
    	
    	for(Object catalog : query.getResultList()) {
    		if(catalog instanceof Catalog) {
    			catalogs.add((Catalog)catalog);
    		}
    	}
    	
    	return catalogs;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Edition> getCatalogEditions(Catalog catalog) {
    	EJBTest.merge(catalog);
    	
    	List<Edition> editions = catalog.getEditions();
    	
    	if(editions.size() == 0) return null;
    	
    	ArrayList<Edition> editionList = new ArrayList<Edition>(editions.size());
    	
    	for(Edition edition : editions) {
    		editionList.add(edition);
    	}
    	
    	return editionList;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Section> getEditionSections(Edition edition) {
    	EJBTest.merge(edition);
    	
    	List<Section> sections = new ArrayList<Section>();
    	
    	Section section = edition.getSection();
    	
    	if(section == null) return null;
    	
    	sections.add(section);
    	
    	return sections;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Article> getSectionArticles(Section section) {
    	EJBTest.merge(section);
    	
    	List<Article> articles = new ArrayList<Article>();
    	
    	Article article = section.getArticle();
    	
    	if(article == null) return null;
    	
    	articles.add(article);
    	
    	return articles;
    }
    
    /**
     * Testing methods
     */
    public void createTestData() {
    	Catalog catalog = new Catalog();
    	
		catalog.setId(1);
		catalog.setJournal("Oracle Magazine");
		
		EJBTest.persist(catalog);
		EJBTest.flush();
    	
    	Edition edition = new Edition();
    	
		edition.setId(1);
		edition.setEdition("January/February 2009");
		
		EJBTest.persist(edition);
		
		EJBTest.flush();
		EJBTest.merge(catalog);
		
		catalog.addEdition(edition);
    	
    	Section features = new Section();
    	
    	features.setSectionname("FEATURES");
    	features.setId("1");
    	
    	EJBTest.persist(features);
    	EJBTest.merge(edition);
    	
    	edition.setSection(features);
    	
    	Article article = new Article();
    	
    	article.setId(1);
    	article.setTitle("Launching Performance");
    	
    	List<Section> featuresList = new ArrayList<Section>();
    	featuresList.add(features);
    	
    	article.setSections(featuresList);
    	
    	EJBTest.persist(article);
    	EJBTest.merge(features);
    	
    	features.setArticle(article);
    	
    	EJBTest.flush();
    	
    	Section technology = new Section();
    	
    	technology.setId("1");
    	technology.setSectionname("Techology");
    	
    	EJBTest.persist(technology);
    	EJBTest.merge(edition);
    	
    	edition.setSection(technology);
    	
    	article = new Article();
    	
    	article.setId(2);
    	article.setSections(featuresList);
    	article.setTitle("On Dynamic Sampling");
    	
    	EJBTest.persist(article);
    	EJBTest.merge(technology);
    	
    	technology.setArticle(article);
    	
    	EJBTest.flush();
    }
}
