package com.comfortly.analyzedtripcatalog.services.beans;

import com.comfortly.analyzedtripcatalog.lib.AnalyzedTripData;
import com.comfortly.analyzedtripcatalog.lib.SummaryAnalyzedTripData;
import com.comfortly.analyzedtripcatalog.models.converters.AnalyzedTripDataConverter;
import com.comfortly.analyzedtripcatalog.models.entities.AnalyzedTripDataEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class AnalyzedTripDataBean {

    private Logger log = Logger.getLogger(AnalyzedTripDataBean.class.getName());

    @Inject
    private EntityManager emAnalyzedTrip;

    public List<SummaryAnalyzedTripData> getAnalyzedTripDataByUser(String userId) {

        // TODO selec only what is needed for the SummaryAnalyzedTripData
        TypedQuery<AnalyzedTripDataEntity> query = emAnalyzedTrip.createQuery("SELECT t FROM AnalyzedTripDataEntity t WHERE t.userId = :user", AnalyzedTripDataEntity.class);
        query.setParameter("user", userId);

        return query.getResultList().stream()
                .map(AnalyzedTripDataConverter::toSummaryDto).collect(Collectors.toList());
    }

    public AnalyzedTripData getAnalyzedTripData(Integer id) {

        AnalyzedTripDataEntity analyzedTripDataEntity = emAnalyzedTrip.find(AnalyzedTripDataEntity.class, id);

        if (analyzedTripDataEntity == null) {
            throw new NotFoundException();
        }

        AnalyzedTripData tripData = AnalyzedTripDataConverter.toDto(analyzedTripDataEntity);

        return tripData;
    }

    public boolean deleteAnalyzedTripData(Integer id) {

        AnalyzedTripDataEntity tripData = emAnalyzedTrip.find(AnalyzedTripDataEntity.class, id);

        if (tripData != null) {
            try {
                beginTx();
                emAnalyzedTrip.remove(tripData);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!emAnalyzedTrip.getTransaction().isActive()) {
            emAnalyzedTrip.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (emAnalyzedTrip.getTransaction().isActive()) {
            emAnalyzedTrip.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (emAnalyzedTrip.getTransaction().isActive()) {
            emAnalyzedTrip.getTransaction().rollback();
        }
    }
}
