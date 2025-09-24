package ca.tremblay95.billsplit.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import ca.tremblay95.billsplit.domain.model.OperationType

@Entity(
    tableName = "operation",
    indices = [Index(value = ["parent_id"])],
    foreignKeys = [
        ForeignKey(
            entity = OperationEntity::class,
            parentColumns = arrayOf("operation_id"),
            childColumns = arrayOf("parent_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OperationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("operation_id")
    val operationId : Int = 0,

    @ColumnInfo("parent_id")
    val parentId : Int?,

    @ColumnInfo("split_type")
    val operationType : OperationType,

    @ColumnInfo("parent_operation_index")
    val parentOperationIndex : Int?
)
