package nz.calc.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import nz.calc.documents.CalcDocument
import nz.calc.documents.Operation
import nz.calc.repository.CalcRepository
import org.springframework.stereotype.Service

@Service
class CalcService(
        private val calcRepository: CalcRepository
) {
    suspend fun getASum(x: Int, y: Int): Int {
        val result = x + y;
        calcRepository.insert(CalcDocument(
                operation = Operation.SUM,
                parameters = listOf(x, y),
                result = result,
        )).awaitFirst()
        return result
    }

    suspend fun getAProduct(a: Int): Int {
        val result = a * 2;
        calcRepository.insert(CalcDocument(
                operation = Operation.PRODUCT,
                parameters = listOf(a),
                result = result,
        )).awaitFirst()
        return result
    }

    suspend fun getAPower(s: Int): Int {
        val result = s * s;
        calcRepository.insert(CalcDocument(
                operation = Operation.POWER,
                parameters = listOf(s),
                result = result,
        )).awaitFirst()
        return result
    }

    suspend fun getHistory(): Flow<CalcDocument> {
        return calcRepository.findAll().asFlow()
    }
}
