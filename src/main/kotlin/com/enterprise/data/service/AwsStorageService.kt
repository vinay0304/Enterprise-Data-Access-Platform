package com.enterprise.data.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Service to simulate interactions with AWS S3 for data processing.
 */
@Service
class AwsStorageService {

    private val log = LoggerFactory.getLogger(AwsStorageService::class.java)

    /**
     * Simulates uploading data to an S3 bucket.
     * In a real implementation, this would use S3Client.
     */
    fun uploadData(content: String): String {
        val simulatedKey = "data-assets/${UUID.randomUUID()}.json"
        log.info("Simulating AWS S3 upload to key: {}", simulatedKey)
        // Simulated latency
        Thread.sleep(100)
        log.info("Successfully uploaded data to AWS S3: {}", simulatedKey)
        return simulatedKey
    }

    /**
     * Simulates fetching data from an S3 bucket.
     */
    fun fetchUrl(objectKey: String): String {
        return "https://s3.amazonaws.com/enterprise-data-bucket/$objectKey"
    }
}
