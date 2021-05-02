package nz.calc.repository

import nz.calc.documents.CalcDocument
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CalcRepository : ReactiveMongoRepository<CalcDocument, String>