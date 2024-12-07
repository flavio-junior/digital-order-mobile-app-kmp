package br.com.dashboard.company.repository

import br.com.dashboard.company.entities.product.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    fun checkNameProductAlreadyExists(@Param("name") name: String): Product?

    @Query(
        """
            SELECT p FROM Product p
            WHERE :name IS NULL OR LOWER(CAST(p.name AS string)) LIKE LOWER(CONCAT('%', :name, '%'))
        """
    )
    fun findAllProducts(@Param("name") name: String?, pageable: Pageable): Page<Product>?

    @Query(
        value = "SELECT p FROM Product p WHERE p.user.id = :userId AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))"
    )
    fun findProductByName(
        @Param("userId") userId: Long,
        @Param("name") name: String?
    ): List<Product>

    @Modifying
    @Query("UPDATE Product p SET p.price =:price WHERE p.id =:id")
    fun updatePriceProduct(
        @Param("id") idProduct: Long,
        @Param("price") price: Double
    )

    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity + :quantity WHERE p.id = :id")
    fun restockProduct(
        @Param("id") idProduct: Long,
        @Param("quantity") quantity: Int
    )
}
