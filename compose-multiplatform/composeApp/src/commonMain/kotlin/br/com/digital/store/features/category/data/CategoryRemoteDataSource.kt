package br.com.digital.store.features.category.data

import br.com.digital.store.common.category.dto.CategoryRequestDTO
import br.com.digital.store.common.category.dto.CategoryResponseDTO
import br.com.digital.store.common.category.dto.EditCategoryRequestDTO
import br.com.digital.store.features.account.data.LocalStorageImp
import br.com.digital.store.features.networking.utils.ObserveNetworkStateHandler
import br.com.digital.store.features.networking.utils.toResultFlow
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class CategoryRemoteDataSource(
    private val httpClient: HttpClient,
    private val localStorage: LocalStorageImp
) : CategoryRepository {

    private val accessToken = runBlocking {
        localStorage.getToken().accessToken
    }

    override fun findAllCategories(): Flow<ObserveNetworkStateHandler<List<CategoryResponseDTO>>> {
        return toResultFlow {
            httpClient.get {
                url(urlString = "/api/dashboard/company/categories/v1")
                headers {
                    append(HttpHeaders.Authorization, value = "Bearer $accessToken")
                }
            }
        }
    }

    override fun createNewCategory(category: CategoryRequestDTO): Flow<ObserveNetworkStateHandler<Unit>> {
        return toResultFlow {
            httpClient.post {
                url(urlString = "/api/dashboard/company/categories/v1")
                headers {
                    append(HttpHeaders.Authorization, value = "Bearer $accessToken")
                }
                setBody(category)
            }
        }
    }

    override fun editCategory(category: EditCategoryRequestDTO): Flow<ObserveNetworkStateHandler<Unit>> {
        return toResultFlow {
            httpClient.put {
                url(urlString = "/api/dashboard/company/categories/v1")
                headers {
                    append(HttpHeaders.Authorization, value = "Bearer $accessToken")
                }
                setBody(category)
            }
        }
    }

    override fun deleteCategory(id: Long): Flow<ObserveNetworkStateHandler<Unit>> {
        return toResultFlow {
            httpClient.delete {
                url(urlString = "/api/dashboard/company/categories/v1/$id")
                headers {
                    append(HttpHeaders.Authorization, value = "Bearer $accessToken")
                }
            }
        }
    }
}
