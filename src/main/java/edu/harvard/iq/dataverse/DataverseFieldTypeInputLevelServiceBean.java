/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.harvard.iq.dataverse;

import edu.harvard.iq.dataverse.util.LruCache;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author skraffmiller
 */
@Stateless
@Named
public class DataverseFieldTypeInputLevelServiceBean {

//    private static final Logger logger = Logger.getLogger(DataverseFieldTypeInputLevelServiceBean.class.getCanonicalName());
    public static final LruCache<Long, List<DataverseFieldTypeInputLevel>> cache = new LruCache();

    @PersistenceContext(unitName = "VDCNet-ejbPU")
    private EntityManager em;

    public List<DataverseFieldTypeInputLevel> findByDataverseId(Long dataverseId) {
        List<DataverseFieldTypeInputLevel> res = cache.get(dataverseId);

        if (res == null) {
            Query query = em.createNamedQuery("DataverseFieldTypeInputLevel.findByDataverseId", DataverseFacet.class);
            query.setParameter("dataverseId", dataverseId);
            res = query.getResultList();
            cache.put(dataverseId, res);
        }

        return res;
    }
    
    private void msg(String s){
        //logger.fine(s);
    }
    
    /**
     * Find a list of DataverseFieldTypeInputLevel objects
     *  Search criteria: 
     *      - Dataverse Id, 
     *      - list of DatasetField Ids
     * 
     * @param dataverseId
     * @param datasetFieldIdList
     * @return List of DataverseFieldTypeInputLevel
     */
    public List<DataverseFieldTypeInputLevel> findByDataverseIdAndDatasetFieldTypeIdList( Long dataverseId, List<Long> datasetFieldIdList){
        msg("---- findByDataverseIdAndDatasetFieldTypeIdList ----");
        if (datasetFieldIdList==null || datasetFieldIdList.size()==0){
            return null;
        }
        if (dataverseId == null){                    
            return null;
        }
       
        Query query = em.createNamedQuery("DataverseFieldTypeInputLevel.findByDataverseIdAndDatasetFieldTypeIdList", DataverseFieldTypeInputLevel.class);

        query.setParameter("datasetFieldIdList", datasetFieldIdList);
        query.setParameter("dataverseId", dataverseId);
        
   
        try{
            return query.getResultList();
            /*List res = query.getResultList();
            msg("Number of results: " + res.size());
            return res;*/
        } catch ( NoResultException nre ) {  
            return null;
        }    
    }
            //     
    
    //    Query query = em.createQuery("select object(o) from MapLayerMetadata as o where o.dataset=:dataset");// order by o.name");
    //    query.setParameter("dataset", dataset);
    
    public DataverseFieldTypeInputLevel findByDataverseIdDatasetFieldTypeId(Long dataverseId, Long datasetFieldTypeId) {
        Query query = em.createNamedQuery("DataverseFieldTypeInputLevel.findByDataverseIdDatasetFieldTypeId", DataverseFieldTypeInputLevel.class);
        query.setParameter("dataverseId", dataverseId);
        query.setParameter("datasetFieldTypeId", datasetFieldTypeId);
        try{
            return (DataverseFieldTypeInputLevel) query.getSingleResult();
        } catch ( NoResultException nre ) {
            return null;
        }         
    }

    public void delete(DataverseFieldTypeInputLevel dataverseFieldTypeInputLevel) {
        em.remove(em.merge(dataverseFieldTypeInputLevel));
        cache.invalidate();
    }

    public void deleteFacetsFor(Dataverse d) {
        em.createNamedQuery("DataverseFieldTypeInputLevel.removeByOwnerId")
                .setParameter("ownerId", d.getId())
                .executeUpdate();
        cache.invalidate(d.getId());

    }

    public void create(DataverseFieldTypeInputLevel dataverseFieldTypeInputLevel) {

        em.persist(dataverseFieldTypeInputLevel);
    }

}
