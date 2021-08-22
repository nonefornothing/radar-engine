package com.jox.radarengine.service.impl;

import com.jox.radarengine.businesslogic.IndexOperationsLogic;
import com.jox.radarengine.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;


public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexOperationsLogic indexOperationsLogic;

    @Override
    public Boolean createIndex(String indexName) {
        return indexOperationsLogic.createIndex(indexName);
    }

    @Override
    public Boolean checkExistingIndex(String indexName) {
        return indexOperationsLogic.checkExistingIndex(indexName);
    }

    @Override
    public Boolean resetDataIndex(String indexName) {
        return indexOperationsLogic.resetDataIndex(indexName);
    }

    @Override
    public Boolean deleteIndex(String indexName) {
        return indexOperationsLogic.deleteIndex(indexName);
    }
}
