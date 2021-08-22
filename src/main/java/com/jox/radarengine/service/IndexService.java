package com.jox.radarengine.service;

public interface IndexService {

    public Boolean createIndex(String indexName) throws Exception;

    public Boolean checkExistingIndex(String indexName);

    public Boolean resetDataIndex(String indexName);

    public Boolean deleteIndex(String indexName);

}
