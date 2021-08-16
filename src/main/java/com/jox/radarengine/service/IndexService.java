package com.jox.radarengine.service;

public interface IndexService {

    public String createIndex(String indexName);

    public String checkExistingIndex(String indexName);

    public String resetDataIndex(String indexName);

    public String deleteIndex(String indexName);

}
