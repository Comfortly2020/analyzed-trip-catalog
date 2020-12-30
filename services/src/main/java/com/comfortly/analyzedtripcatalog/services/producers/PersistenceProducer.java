package com.comfortly.analyzedtripcatalog.services.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class PersistenceProducer {

    @PersistenceUnit(unitName = "analyzed-trip-catalog-jpa")
    private EntityManagerFactory emfAnalyzedTrip;

    @Produces
    @ApplicationScoped
    public EntityManager getTripEntityManager() {
        return emfAnalyzedTrip.createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager entityManager) {

        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
