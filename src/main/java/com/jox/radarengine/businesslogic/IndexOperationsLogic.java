package com.jox.radarengine.businesslogic;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;


public class IndexOperationsLogic {

    private final RestHighLevelClient restHighLevelClient = null;

    public boolean createIndex(String indexName) throws Exception {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT); // 1
        } catch (Exception ignored) {
            return false;
        }
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        createIndexRequest.settings(
                Settings.builder().put("index.number_of_shards", 1)
                        .put("index.number_of_replicas", 0));
        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT); // 2
        return true;
    }

    public String checkExistingIndex(String index){

        return null;
    }

    public String resetDataIndex(String indexName) {
        return null;
    }

    public String deleteIndex(String indexName) {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            deleteIndexRequest.timeout(TimeValue.timeValueMinutes(2));
            deleteIndexRequest.masterNodeTimeout(TimeValue.timeValueMinutes(1));
            restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND){
                return "Index not found";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
        return "Delete Success";
    }

}
