package com.enterprise.data.controller

import com.enterprise.data.service.DataAssetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class CreateAssetRequest(
    val name: String,
    val description: String,
    val payload: String
)

@RestController
@RequestMapping("/api/v1/assets")
class DataController(
    private val dataAssetService: DataAssetService
) {

    @PostMapping
    fun createAsset(@RequestBody request: CreateAssetRequest): ResponseEntity<Any> {
        val asset = dataAssetService.createAsset(
            request.name,
            request.description,
            request.payload
        )
        return ResponseEntity.ok(mapOf(
            "message" to "Asset created successfully",
            "id" to asset.id,
            "s3ObjectKey" to asset.s3ObjectKey
        ))
    }

    @GetMapping
    fun getAllAssets(): ResponseEntity<List<Map<String, Any>>> {
        return ResponseEntity.ok(dataAssetService.getAllAssets())
    }
}
