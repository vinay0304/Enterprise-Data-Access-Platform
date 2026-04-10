package com.enterprise.data.service

import com.enterprise.data.entity.DataAsset
import com.enterprise.data.repository.DataAssetRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class DataAssetService(
    private val repository: DataAssetRepository,
    private val awsStorageService: AwsStorageService
) {

    @Transactional
    fun createAsset(name: String, description: String, payload: String): DataAsset {
        // Here we simulate putting large payload to S3 and storing the metadata in PostgreSQL
        val objectKey = awsStorageService.uploadData(payload)
        
        val asset = DataAsset(name, description, objectKey, Instant.now())
        return repository.save(asset)
    }

    @Transactional(readOnly = true)
    fun getAllAssets(): List<Map<String, Any>> {
        return repository.findAll().map { asset ->
            mapOf(
                "id" to (asset.id ?: -1),
                "name" to (asset.name ?: ""),
                "description" to (asset.description ?: ""),
                "s3Url" to awsStorageService.fetchUrl(asset.s3ObjectKey),
                "createdAt" to (asset.createdAt.toString())
            )
        }
    }
}
