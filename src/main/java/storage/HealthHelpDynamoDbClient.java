package storage;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Client for DynamoDB persistance layer for the Health help skill.
 */
public class HealthHelpDynamoDbClient {
    private final AmazonDynamoDBClient dynamoDBClient;

    public HealthHelpDynamoDbClient(final AmazonDynamoDBClient dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
    }

    /**
     * Loads an item from DynamoDB by primary Hash Key. Callers of this method should pass in an
     * object which represents an item in the DynamoDB table item with the primary key populated.
     * 
     * @param tableItem
     * @return
     */
//    public HealthHelpUserDataItem loadItem(final HealthHelpUserDataItem tableItem) {
//        DynamoDBMapper mapper = createDynamoDBMapper();
//        HealthHelpUserDataItem item = mapper.load(tableItem);
//        return item;
//    }
    
    public Appointment loadItem(final Appointment tableItem) {
        DynamoDBMapper mapper = createDynamoDBMapper();
       Appointment item = mapper.load(tableItem);
        return item;
    }

    /**
     * Stores an item to DynamoDB.
     * 
     * @param tableItem
     */
    public void saveItem(final Appointment tableItem) {
        DynamoDBMapper mapper = createDynamoDBMapper();
        mapper.save(tableItem);
    }

    
//    public void saveItem1(final Patient tableItem) {
//        DynamoDBMapper mapper = createDynamoDBMapper();
//        mapper.save(tableItem);
//    }

    /**
     * Creates a {@link DynamoDBMapper} using the default configurations.
     * 
     * @return
     */
    private DynamoDBMapper createDynamoDBMapper() {
        return new DynamoDBMapper(dynamoDBClient);
    }
}