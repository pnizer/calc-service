package nz.calc.documents

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

enum class Operation {
    SUM, PRODUCT, POWER
}

@Document
data class CalcDocument(
        @Id val id: ObjectId = ObjectId.get(),
        val operation: Operation,
        val parameters: List<Int>,
        val result: Int,
        val executionDate: LocalDateTime = LocalDateTime.now(),
)
