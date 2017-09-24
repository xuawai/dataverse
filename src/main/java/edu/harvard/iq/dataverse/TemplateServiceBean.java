package edu.harvard.iq.dataverse;

import edu.harvard.iq.dataverse.search.IndexServiceBean;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author skraffmiller
 */
@Stateless
@Named
public class TemplateServiceBean {

    private static final Logger logger = Logger.getLogger(DatasetServiceBean.class.getCanonicalName());
    @EJB
    IndexServiceBean indexService;

    @PersistenceContext(unitName = "VDCNet-ejbPU")
    private EntityManager em;

    public Template find(Object pk) {
        return em.find(Template.class, pk);
    }

    public Template save(Template template) {
        /*
                if (template.getId() == null) {
            em.persist(template);
            return template;
        } else {
            return em.merge(template);
        } */
        return em.merge(template);
    }

    public Template findByDeafultTemplateOwnerId(Long ownerId) {
        Query query = em.createQuery("select object(o.defaultTemplate) from Dataverse as o where o.owner.id =:ownerId order by o.name");
        query.setParameter("ownerId", ownerId);
        return (Template) query.getSingleResult();
    }

    
    public List<Dataverse> findDataversesByDefaultTemplateId(Long defaultTemplateId) {
        Query query = em.createQuery("select object(o) from Dataverse as o where o.defaultTemplate.id =:defaultTemplateId order by o.name");
        query.setParameter("defaultTemplateId", defaultTemplateId);
        return query.getResultList();
    }
    
    public void incrementUsageCount(Long templateId) {

        Long usageCount = (Long) em.createNativeQuery(
                "select usageCount from  Template  "
                + "WHERE id=" + templateId
        ).getSingleResult();
        
        usageCount++;
        
        em.createNativeQuery(
                "update Template SET  usagecount = " + usageCount + " "
                + "WHERE id=" + templateId
        ).executeUpdate();
    }
}
