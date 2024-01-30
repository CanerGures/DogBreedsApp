package com.canergures.data.dataSource.remote.dto

data class BreedsListResponse(
    val status: String,
    val message: Map<String, List<String>>,
    val code: Int,
) {
    companion object {
        const val SUCCESS_STATUS = "success"
    }
}
