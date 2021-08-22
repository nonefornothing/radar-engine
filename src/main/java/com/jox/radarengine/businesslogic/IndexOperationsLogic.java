package com.jox.radarengine.businesslogic;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/index")
public class IndexOperationsLogic {

    private final RestHighLevelClient client = null;

    public boolean createIndex(String indexName) throws Exception {
        try {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            createIndexRequest.settings(
                    Settings.builder().put("index.number_of_shards", 1)
                            .put("index.number_of_replicas", 0));
            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT); // 2
            boolean acknowledgedCreate = createIndexResponse.isAcknowledged();
            if (acknowledgedCreate == true){
                return true;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return false;
    }

    @PostMapping("/check/{indexName}")
    public Boolean checkExistingIndex(@PathVariable String indexName){
        try {
            GetIndexRequest checkIndexRequest = new GetIndexRequest(indexName);
            checkIndexRequest.local(false);
            checkIndexRequest.humanReadable(true);
            checkIndexRequest.includeDefaults(false);
            checkIndexRequest.indicesOptions();
            boolean isExists = client.indices().exists(checkIndexRequest, RequestOptions.DEFAULT);
            if (isExists == true){
                return true;
            }
        }catch (ElasticsearchException | IOException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @PostMapping("/reset/{indexName}")
    public Boolean resetDataIndex(String indexName) throws IOException {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            deleteIndexRequest.timeout(TimeValue.timeValueMinutes(2));
            deleteIndexRequest.masterNodeTimeout(TimeValue.timeValueMinutes(1));
            AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            boolean acknowledgedDelete = deleteIndexResponse.isAcknowledged();
            if(acknowledgedDelete == true){
                try {
                    CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                    createIndexRequest.settings(
                            Settings.builder().put("index.number_of_shards", 1)
                                    .put("index.number_of_replicas", 0));
                    CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT); // 2
                    boolean acknowledgedCreate = createIndexResponse.isAcknowledged();
                    if (acknowledgedCreate == true){
                        return true;
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @DeleteMapping("/delete/{indexName}")
    public Boolean deleteIndex(@PathVariable String indexName) {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            deleteIndexRequest.timeout(TimeValue.timeValueMinutes(2));
            deleteIndexRequest.masterNodeTimeout(TimeValue.timeValueMinutes(1));
            AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            boolean acknowledged = deleteIndexResponse.isAcknowledged();
            return acknowledged;
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND){
                exception.printStackTrace();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }

}
